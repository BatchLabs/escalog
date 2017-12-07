package com.batch.escalog;

import org.slf4j.Marker;
import java.util.Objects;

/**
 * @author Guillaume PERRUDIN
 */
public class LogFmt implements org.slf4j.Logger
{
    /**
     * The underlying logger instance
     */
    private org.slf4j.Logger underlying;

// ----------------------------------->

    private LogFmt(org.slf4j.Logger underlying)
    {
        Objects.requireNonNull(underlying, "Cannot create a Logger with a null underlying Logger");
        this.underlying = underlying;
    }

// ----------------------------------->

    /**
     * Starts a new LogFmtBuilder and invoke LogFmtBuild{@link #with(Object, Object)}
     * @see LogFmtBuilder#and(Object, Object)
     */
    public LogFmtBuilder with(Object key, Object value)
    {
        return new LogFmtBuilder(underlying).and(key, value);
    }

    /**
     * Starts a new LogFmtBuilder and invoke LogFmtBuild{@link #with(String, Object)}
     * @see LogFmtBuilder#and(String, Object)
     */
    public LogFmtBuilder with(String key, Object value)
    {
        return new LogFmtBuilder(underlying).and(key, value);
    }

// ----------------------------------->

    /**
     * Creates a new LogFmt from org.slf4j.Logger
     */
    public static LogFmt wrap(org.slf4j.Logger logger)
    {
        return new LogFmt(logger);
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
        underlying.trace(s);
    }

    @Override
    public void trace(String s, Object o)
    {
        underlying.trace(s, o);
    }

    @Override
    public void trace(String s, Object o, Object o1)
    {
        underlying.trace(s, o, o1);
    }

    @Override
    public void trace(String s, Object... objects)
    {
        underlying.trace(s, objects);
    }

    @Override
    public void trace(String s, Throwable throwable)
    {
        underlying.trace(s, throwable);
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
        underlying.debug(s);
    }

    @Override
    public void debug(String s, Object o)
    {
        underlying.debug(s, o);
    }

    @Override
    public void debug(String s, Object o, Object o1)
    {
        underlying.debug(s, o, o1);
    }

    @Override
    public void debug(String s, Object... objects)
    {
        underlying.debug(s, objects);
    }

    @Override
    public void debug(String s, Throwable throwable)
    {
        underlying.debug(s, throwable);
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
        underlying.info(s);
    }

    @Override
    public void info(String s, Object o)
    {
        underlying.info(s, o);
    }

    @Override
    public void info(String s, Object o, Object o1)
    {
        underlying.info(s, o, o1);
    }

    @Override
    public void info(String s, Object... objects)
    {
        underlying.info(s, objects);
    }

    @Override
    public void info(String s, Throwable throwable)
    {
        underlying.info(s, throwable);
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
        underlying.warn(s);
    }

    @Override
    public void warn(String s, Object o)
    {
        underlying.warn(s, o);
    }

    @Override
    public void warn(String s, Object... objects)
    {
        underlying.warn(s, objects);
    }

    @Override
    public void warn(String s, Object o, Object o1)
    {
        underlying.warn(s, o, o1);
    }

    @Override
    public void warn(String s, Throwable throwable)
    {
        underlying.warn(s, throwable);
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
        underlying.error(s);
    }

    @Override
    public void error(String s, Object o)
    {
        underlying.error(s, o);
    }

    @Override
    public void error(String s, Object o, Object o1)
    {
        underlying.error(s, o, o1);
    }

    @Override
    public void error(String s, Object... objects)
    {
        underlying.error(s, objects);
    }

    @Override
    public void error(String s, Throwable throwable)
    {
        underlying.error(s, throwable);
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

// ----------------------------------->

    /**
     * Creates a new LogFmt logger from org.slf4j.Logger
     */
    public static LogFmt from(org.slf4j.Logger logger)
    {
        return new LogFmt(logger);
    }
}
