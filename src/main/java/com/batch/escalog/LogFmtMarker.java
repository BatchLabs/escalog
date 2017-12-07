package com.batch.escalog;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Marker that stores key/value data meant to be used by LogFmtLayout.
 * @author Guillaume PERRUDIN
 */
public class LogFmtMarker implements Marker
{
    /**
     * Common marker for all LogFmtMarkers
     */
    private static final Marker underlying = MarkerFactory.getMarker("LOGFMT");

    /**
     * List of key-value pairs
     */
    private List<KeyValue> keyValues = new LinkedList<>();

// ----------------------------------->

    /**
     * Creates a new empty LogFmtMarker
     */
    public LogFmtMarker() {}

// ----------------------------------->

    /**
     * <p>Adds the given key/value pair to the marker.</p>
     *
     * <p>If the key is null, this key/value is ignored.
     * However a value null is kept, and will be converted later as a <code>"null"</code> string.<br>
     * Keep in mind that for each value, the {@link Object#toString()} method will be invoked.
     * If you want a particular format, provide a string formatted as you wish.</p>
     *
     * @param key    the key of this marker
     * @param value  the value, to be associated with the key
     */
    public LogFmtMarker and(String key, Object value)
    {
        if ( key != null )
        {
            this.keyValues.add(new KeyValue(key, value));
        }

        return this;
    }

    /**
     * <p>Adds the given key/value pair to the marker.</p>
     *
     * @param key    the key of this marker, that will be converted to string.
     * @param value  the value, to be associated with the key
     *
     * @see #and(String, Object)
     */
    public LogFmtMarker and(Object key, Object value)
    {
        if ( key != null )
        {
            return and(key.toString(), value);
        }

        return this;
    }

    /**
     * Applies the key-value pairs to the given consumer
     */
    public void forEach(BiConsumer<String, Object> consumer)
    {
        keyValues.forEach( keyValue -> consumer.accept(keyValue.key, keyValue.value) );
    }

    /**
     * Creates a new LogFmtMarker with the given key and value
     */
    public static LogFmtMarker with(String key, Object value)
    {
        return new LogFmtMarker().and(key, value);
    }

    /**
     * Creates a new LogFmtMarker with the given LogKey and value
     */
    public static LogFmtMarker with(Object key, Object value)
    {
        return new LogFmtMarker().and(key, value);
    }

// ----------------------------------->
// Marker interface methods

    @Override
    public String getName()
    {
        return underlying.getName();
    }

    @Override
    public void add(Marker reference)
    {
        underlying.add(reference);
    }

    @Override
    public boolean remove(Marker reference)
    {
        return underlying.remove(reference);
    }

    @Override
    public boolean hasChildren()
    {
        return underlying.hasChildren();
    }

    @Override
    public boolean hasReferences()
    {
        return underlying.hasReferences();
    }

    @Override
    public Iterator iterator()
    {
        return underlying.iterator();
    }

    @Override
    public boolean contains(Marker other)
    {
        return underlying.contains(other);
    }

    @Override
    public boolean contains(String name)
    {
        return underlying.contains(name);
    }

// ----------------------------------->

    private static class KeyValue
    {
        final String key;
        final Object value;

        KeyValue(String key, Object value)
        {
            this.key = key;
            this.value = value;
        }
    }
}
