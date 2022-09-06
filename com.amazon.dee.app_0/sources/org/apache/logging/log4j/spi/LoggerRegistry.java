package org.apache.logging.log4j.spi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.ExtendedLogger;
/* loaded from: classes4.dex */
public class LoggerRegistry<T extends ExtendedLogger> {
    private static final String DEFAULT_FACTORY_KEY = AbstractLogger.DEFAULT_MESSAGE_FACTORY_CLASS.getName();
    private final MapFactory<T> factory;
    private final Map<String, Map<String, T>> map;

    /* loaded from: classes4.dex */
    public static class ConcurrentMapFactory<T extends ExtendedLogger> implements MapFactory<T> {
        @Override // org.apache.logging.log4j.spi.LoggerRegistry.MapFactory
        public Map<String, T> createInnerMap() {
            return new ConcurrentHashMap();
        }

        @Override // org.apache.logging.log4j.spi.LoggerRegistry.MapFactory
        public Map<String, Map<String, T>> createOuterMap() {
            return new ConcurrentHashMap();
        }

        @Override // org.apache.logging.log4j.spi.LoggerRegistry.MapFactory
        public void putIfAbsent(Map<String, T> map, String str, T t) {
            ((ConcurrentMap) map).putIfAbsent(str, t);
        }
    }

    /* loaded from: classes4.dex */
    public interface MapFactory<T extends ExtendedLogger> {
        Map<String, T> createInnerMap();

        Map<String, Map<String, T>> createOuterMap();

        void putIfAbsent(Map<String, T> map, String str, T t);
    }

    /* loaded from: classes4.dex */
    public static class WeakMapFactory<T extends ExtendedLogger> implements MapFactory<T> {
        @Override // org.apache.logging.log4j.spi.LoggerRegistry.MapFactory
        public Map<String, T> createInnerMap() {
            return new WeakHashMap();
        }

        @Override // org.apache.logging.log4j.spi.LoggerRegistry.MapFactory
        public Map<String, Map<String, T>> createOuterMap() {
            return new WeakHashMap();
        }

        @Override // org.apache.logging.log4j.spi.LoggerRegistry.MapFactory
        public void putIfAbsent(Map<String, T> map, String str, T t) {
            map.put(str, t);
        }
    }

    public LoggerRegistry() {
        this(new ConcurrentMapFactory());
    }

    private static String factoryClassKey(Class<? extends MessageFactory> cls) {
        return cls == null ? DEFAULT_FACTORY_KEY : cls.getName();
    }

    private static String factoryKey(MessageFactory messageFactory) {
        return messageFactory == null ? DEFAULT_FACTORY_KEY : messageFactory.getClass().getName();
    }

    private Map<String, T> getOrCreateInnerMap(String str) {
        Map<String, T> map = this.map.get(str);
        if (map == null) {
            Map<String, T> createInnerMap = this.factory.createInnerMap();
            this.map.put(str, createInnerMap);
            return createInnerMap;
        }
        return map;
    }

    public T getLogger(String str) {
        return getOrCreateInnerMap(DEFAULT_FACTORY_KEY).get(str);
    }

    public Collection<T> getLoggers() {
        return getLoggers(new ArrayList());
    }

    public boolean hasLogger(String str) {
        return getOrCreateInnerMap(DEFAULT_FACTORY_KEY).containsKey(str);
    }

    public void putIfAbsent(String str, MessageFactory messageFactory, T t) {
        this.factory.putIfAbsent(getOrCreateInnerMap(factoryKey(messageFactory)), str, t);
    }

    public LoggerRegistry(MapFactory<T> mapFactory) {
        this.factory = (MapFactory) Objects.requireNonNull(mapFactory, "factory");
        this.map = mapFactory.createOuterMap();
    }

    public T getLogger(String str, MessageFactory messageFactory) {
        return getOrCreateInnerMap(factoryKey(messageFactory)).get(str);
    }

    public Collection<T> getLoggers(Collection<T> collection) {
        for (Map<String, T> map : this.map.values()) {
            collection.addAll(map.values());
        }
        return collection;
    }

    public boolean hasLogger(String str, MessageFactory messageFactory) {
        return getOrCreateInnerMap(factoryKey(messageFactory)).containsKey(str);
    }

    public boolean hasLogger(String str, Class<? extends MessageFactory> cls) {
        return getOrCreateInnerMap(factoryClassKey(cls)).containsKey(str);
    }
}
