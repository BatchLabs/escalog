package com.batch.escalog;

import org.slf4j.Marker;

import java.util.Objects;

/**
 * <p>A log fmt builder, that will helps to build a log entry, with markers attached.</p>
 * <p><b>This builder is absolutely not thread safe</b>, and should not be shared.</p>
 *
 * @author Nicolas DOUILLET
 * @author Guillaume PERRUDIN
 */
public class LogFmtBuilder implements org.slf4j.Logger
{
    /**
     * The underlying logger instance
     */
    private org.slf4j.Logger underlying;

    /**
     * The logFmtMarker (that contains key-values)
     */
    private final LogFmtMarker logFmtMarker;

// ----------------------------------->

    /**
     * Create a new log fmt builder
     */
    LogFmtBuilder(org.slf4j.Logger underlying)
    {
        Objects.requireNonNull(underlying, "Can't create a new log fmt builder with no logger");
        this.underlying = underlying;
        this.logFmtMarker = new LogFmtMarker();
    }

// ----------------------------------->

    /**
     * <p>Adds a new key/value marker to the current log fmt builder.</p>
     *
     * @param key   the key
     * @param value the value, that will be converted into a string as well, if not null.
     *
     * @return this instance
     * @see LogFmtMarker#and(Object, Object)
     */
    public LogFmtBuilder and(Object key, Object value)
    {
        logFmtMarker.and(key, value);
        return this;
    }

    /**
     * <p>Adds a new key/value marker to the current log fmt builder, using a key type of string</p>
     *
     * @param key   the string key
     * @param value the value, that will be converted into a string as well, if not null.
     *
     * @return this instance
     * @see LogFmtMarker#and(String, Object)
     */
    public LogFmtBuilder and(String key, Object value)
    {
        logFmtMarker.and(key, value);
        return this;
    }

// ----------------------------------->
// org.slf4j.Logger interface methods implementation

    @Override
    public String getName()
    {
        return underlying.getName();
    }

    @Override
    public boolean isTraceEnabled()
    {
        return underlying.isTraceEnabled();
    }

    @Override
    public void trace(String s)
    {
        underlying.trace(logFmtMarker, s);
    }

    @Override
    public void trace(String s, Object o)
    {
        underlying.trace(logFmtMarker, s, o);
    }

    @Override
    public void trace(String s, Object o, Object o1)
    {
        underlying.trace(logFmtMarker, s, o, o1);
    }

    @Override
    public void trace(String s, Object... objects)
    {
        underlying.trace(logFmtMarker, s, objects);
    }

    @Override
    public void trace(String s, Throwable throwable)
    {
        underlying.trace(logFmtMarker, s, throwable);
    }

    @Override
    public boolean isTraceEnabled(Marker marker)
    {
        return underlying.isTraceEnabled(marker);
    }

    @Override
    public void trace(Marker marker, String s)
    {
        underlying.trace(marker, s);
    }

    @Override
    public void trace(Marker marker, String s, Object o)
    {
        underlying.trace(marker, s, o);
    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o1)
    {
        underlying.trace(marker, s, o, o1);
    }

    @Override
    public void trace(Marker marker, String s, Object... objects)
    {
        underlying.trace(marker, s, objects);
    }

    @Override
    public void trace(Marker marker, String s, Throwable throwable)
    {
        underlying.trace(marker, s, throwable);
    }

    @Override
    public boolean isDebugEnabled()
    {
        return underlying.isDebugEnabled();
    }

    @Override
    public void debug(String s)
    {
        underlying.debug(logFmtMarker, s);
    }

    @Override
    public void debug(String s, Object o)
    {
        underlying.debug(logFmtMarker, s, o);
    }

    @Override
    public void debug(String s, Object o, Object o1)
    {
        underlying.debug(logFmtMarker, s, o, o1);
    }

    @Override
    public void debug(String s, Object... objects)
    {
        underlying.debug(logFmtMarker, s, objects);
    }

    @Override
    public void debug(String s, Throwable throwable)
    {
        underlying.debug(logFmtMarker, s, throwable);
    }

    @Override
    public boolean isDebugEnabled(Marker marker)
    {
        return underlying.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String s)
    {
        underlying.debug(marker, s);
    }

    @Override
    public void debug(Marker marker, String s, Object o)
    {
        underlying.debug(marker, s, o);
    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o1)
    {
        underlying.debug(marker, s, o, o1);
    }

    @Override
    public void debug(Marker marker, String s, Object... objects)
    {
        underlying.debug(marker, s, objects);
    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable)
    {
        underlying.debug(marker, s, throwable);
    }

    @Override
    public boolean isInfoEnabled()
    {
        return underlying.isInfoEnabled();
    }

    @Override
    public void info(String s)
    {
        underlying.info(logFmtMarker, s);
    }

    @Override
    public void info(String s, Object o)
    {
        underlying.info(logFmtMarker, s, o);
    }

    @Override
    public void info(String s, Object o, Object o1)
    {
        underlying.info(logFmtMarker, s, o, o1);
    }

    @Override
    public void info(String s, Object... objects)
    {
        underlying.info(logFmtMarker, s, objects);
    }

    @Override
    public void info(String s, Throwable throwable)
    {
        underlying.info(logFmtMarker, s, throwable);
    }

    @Override
    public boolean isInfoEnabled(Marker marker)
    {
        return underlying.isInfoEnabled(marker);
    }

    @Override
    public void info(Marker marker, String s)
    {
        underlying.info(marker, s);
    }

    @Override
    public void info(Marker marker, String s, Object o)
    {
        underlying.info(marker, s, o);
    }

    @Override
    public void info(Marker marker, String s, Object o, Object o1)
    {
        underlying.info(marker, s, o, o1);
    }

    @Override
    public void info(Marker marker, String s, Object... objects)
    {
        underlying.info(marker, s, objects);
    }

    @Override
    public void info(Marker marker, String s, Throwable throwable)
    {
        underlying.info(marker, s, throwable);
    }

    @Override
    public boolean isWarnEnabled()
    {
        return underlying.isWarnEnabled();
    }

    @Override
    public void warn(String s)
    {
        underlying.warn(logFmtMarker, s);
    }

    @Override
    public void warn(String s, Object o)
    {
        underlying.warn(logFmtMarker, s, o);
    }

    @Override
    public void warn(String s, Object... objects)
    {
        underlying.warn(logFmtMarker, s, objects);
    }

    @Override
    public void warn(String s, Object o, Object o1)
    {
        underlying.warn(logFmtMarker, s, o, o1);
    }

    @Override
    public void warn(String s, Throwable throwable)
    {
        underlying.warn(logFmtMarker, s, throwable);
    }

    @Override
    public boolean isWarnEnabled(Marker marker)
    {
        return underlying.isWarnEnabled(marker);
    }

    @Override
    public void warn(Marker marker, String s)
    {
        underlying.warn(marker, s);
    }

    @Override
    public void warn(Marker marker, String s, Object o)
    {
        underlying.warn(marker, s, o);
    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o1)
    {
        underlying.warn(marker, s, o, o1);
    }

    @Override
    public void warn(Marker marker, String s, Object... objects)
    {
        underlying.warn(marker, s, objects);
    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable)
    {
        underlying.warn(marker, s, throwable);
    }

    @Override
    public boolean isErrorEnabled()
    {
        return underlying.isErrorEnabled();
    }

    @Override
    public void error(String s)
    {
        underlying.error(logFmtMarker, s);
    }

    @Override
    public void error(String s, Object o)
    {
        underlying.error(logFmtMarker, s, o);
    }

    @Override
    public void error(String s, Object o, Object o1)
    {
        underlying.error(logFmtMarker, s, o, o1);
    }

    @Override
    public void error(String s, Object... objects)
    {
        underlying.error(logFmtMarker, s, objects);
    }

    @Override
    public void error(String s, Throwable throwable)
    {
        underlying.error(logFmtMarker, s, throwable);
    }

    @Override
    public boolean isErrorEnabled(Marker marker)
    {
        return underlying.isErrorEnabled(marker);
    }

    @Override
    public void error(Marker marker, String s)
    {
        underlying.error(marker, s);
    }

    @Override
    public void error(Marker marker, String s, Object o)
    {
        underlying.error(marker, s, o);
    }

    @Override
    public void error(Marker marker, String s, Object o, Object o1)
    {
        underlying.error(marker, s, o, o1);
    }

    @Override
    public void error(Marker marker, String s, Object... objects)
    {
        underlying.error(marker, s, objects);
    }

    @Override
    public void error(Marker marker, String s, Throwable throwable)
    {
        underlying.error(marker, s, throwable);
    }
}
