package com.batch.escalog;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.LayoutBase;
import org.slf4j.Marker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.batch.escalog.LogFmtLayout.NativeKey.*;

/**
 * Logback Layout that format logs with logfmt format (ie. level="debug" ... key1="value1" key2="value2" ...)
 * @author Guillaume PERRUDIN
 */
public class LogFmtLayout extends LayoutBase<ILoggingEvent>
{
    private static final String LOGFMT_CLASS = com.batch.escalog.LogFmt.class.getName();

    /**
     * Formats the time field
     */
    private ThreadLocal<SimpleDateFormat> simpleDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

// ----------------------------------->
// logback.xml parameters

    /**
     * All the log lines will be prepended by this prefix (if present)
     */
    private String prefix = null;

    /**
     * The app name (adds the key app_name=[appName])
     */
    private String appName = null;

// ----------------------------------->

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

// ----------------------------------->

    @Override
    public String doLayout(ILoggingEvent iLoggingEvent)
    {
        StringBuilder sb = new StringBuilder();

        // prefix
        if ( prefix != null )
        {
            sb.append(prefix).append(' ');
        }

        // app_name
        if ( appName != null )
        {
            appendKeyValueAndEscape(sb, APP.toString(), appName);
        }


        appendKeyValueAndEscape(sb, TIME.toString(), simpleDateFormat.get().format(new Date(iLoggingEvent.getTimeStamp())));
        appendKeyValueAndEscape(sb, LEVEL.toString(), formatLogLevel(iLoggingEvent.getLevel()));


        // package and module

        StackTraceElement[] callerData = iLoggingEvent.getCallerData();
        if( callerData != null && callerData.length > 0 )
        {
            String className = callerData[0].getClassName();

            // FIXME this is dirty. Find a way to remove last callerData when log from LogFmt
            if( className.equals(LOGFMT_CLASS) )
            {
                className = null;
                if( callerData.length > 1 )
                {
                    className = callerData[1].getClassName();
                }

            }

            if( className != null )
            {
                int lastPointPosition = className.lastIndexOf('.');
                if( lastPointPosition >= 0 )
                {
                    appendKeyValueAndEscape(sb, "package", className.substring(0, lastPointPosition));
                    appendKeyValueAndEscape(sb, "module", className.substring(lastPointPosition+1, className.length()));
                }
                // else (if root package)
                else
                {
                    appendKeyValueAndEscape(sb, "package", "");
                    appendKeyValueAndEscape(sb, "module", className);
                }
            }
        }

        appendKeyValueAndEscape(sb, THREAD.toString(), iLoggingEvent.getThreadName());
        appendKeyValueAndEscape(sb, MESSAGE.toString(), iLoggingEvent.getFormattedMessage());

        // MDC
        Map<String, String> mdc = iLoggingEvent.getMDCPropertyMap();
        if ( mdc != null )
        {
            mdc.forEach((k, v) ->
            {
                if ( !isNativeKey(k) )
                {
                    appendKeyValueAndEscape(sb, k, v);
                }
            });
        }

        // key-values
        Marker marker = iLoggingEvent.getMarker();
        if ( marker != null && marker instanceof LogFmtMarker )
        {
            LogFmtMarker keyValueMarker = (LogFmtMarker) marker;
            keyValueMarker.forEach((k, v) ->
            {
                if ( !isNativeKey(k) )
                {
                    appendKeyValueAndEscape(sb, k, v);
                }
            });
        }

        // exception
        if ( iLoggingEvent.getThrowableProxy() != null )
        {
            appendKeyValueAndEscape(sb, ERROR.toString(), ThrowableProxyUtil.asString(iLoggingEvent.getThrowableProxy()));
        }

        // removes the last space char and adds a carriage return
        sb.setCharAt(sb.length() - 1, '\n');

        return sb.toString();
    }

    /**
     * Appends the given key and value (escaped with escapeJava(String string)) to the given StringBuilder (appends key="value")
     */
    private StringBuilder appendKeyValueAndEscape(StringBuilder sb, String key, Object value)
    {
        if ( key == null )
        {
            return sb;
        }

        if ( value == null )
        {
            value = "null";
        }

        sb.append(key).append('=');

        String valueStr = value.toString();

        if ( needsQuoting(valueStr) )
        {
            sb.append('"').append(escapeValue(valueStr)).append('"');
        }
        else
        {
            sb.append(valueStr);
        }

        sb.append(' ');
        return sb;
    }

    private static boolean needsQuoting(String value)
    {
        for ( int i = 0; i < value.length(); i++ )
        {
            char c = value.charAt(i);

            if ( !((c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                (c >= '0' && c <= '9') ||
                c == '-' || c == '.' || c == '_' || c == '/' || c == '@' || c == '^' || c == '+') )
            {
                return true;
            }
        }
        return false;
    }

// ----------------------------------->

    /**
     * Returns a StringBuilder representing the given string with characters escaped (see list of escaped characters here : https://docs.oracle.com/javase/tutorial/java/data/characters.html)
     */
    public static StringBuilder escapeValue(String string)
    {
        StringBuilder sb = new StringBuilder();

        for ( int i = 0; i < string.length(); i++ )
        {
            char c = string.charAt(i);
            switch ( c )
            {
                case '\t': sb.append("\\t");    break;
                case '\b': sb.append("\\b");    break;
                case '\n': sb.append("\\n");    break;
                case '\r': sb.append("\\r");    break;
                case '\f': sb.append("\\f");    break;
                case '\'': sb.append("\\'");    break;
                case '\"': sb.append("\\\"");   break;
                case '\\': sb.append("\\\\");   break;
                default:   sb.append(c);
            }
        }

        return sb;
    }

    private static String formatLogLevel(Level level)
    {
        if ( level == Level.WARN )
        {
            return "warning";
        }

        return level.toString().toLowerCase();
    }

    /**
     * Native keys that are automatically added by the Layout.
     * Cannot be used with Markers and MDC
     */
    enum NativeKey
    {
        TIME("time"),
        LEVEL("level"),
        MESSAGE("msg"),
        APP("app"),
        THREAD("thread"),
        ERROR("error");

    // ----------------------------------->

        private final String text;

    // ----------------------------------->

        NativeKey(final String text)
        {
            this.text = text;
        }

    // ----------------------------------->

        @Override
        public String toString()
        {
            return text;
        }

        public static boolean isNativeKey(String key)
        {
            NativeKey[] keys = values();
            for ( int i = 0; i < keys.length; i++ )
            {
                if ( keys[ i ].text.equals(key) )
                {
                    return true;
                }
            }

            return false;
        }
    }
}
