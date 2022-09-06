package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes2.dex */
public class JdkDeserializers {
    private static final HashSet<String> _classNames = new HashSet<>();

    static {
        for (Class cls : new Class[]{UUID.class, AtomicBoolean.class, AtomicInteger.class, AtomicLong.class, StackTraceElement.class, ByteBuffer.class, Void.class}) {
            _classNames.add(cls.getName());
        }
        for (Class<?> cls2 : FromStringDeserializer.types()) {
            _classNames.add(cls2.getName());
        }
    }

    public static JsonDeserializer<?> find(Class<?> cls, String str) {
        if (_classNames.contains(str)) {
            FromStringDeserializer<?> findDeserializer = FromStringDeserializer.findDeserializer(cls);
            if (findDeserializer != null) {
                return findDeserializer;
            }
            if (cls == UUID.class) {
                return new UUIDDeserializer();
            }
            if (cls == StackTraceElement.class) {
                return new StackTraceElementDeserializer();
            }
            if (cls == AtomicBoolean.class) {
                return new AtomicBooleanDeserializer();
            }
            if (cls == AtomicInteger.class) {
                return new AtomicIntegerDeserializer();
            }
            if (cls == AtomicLong.class) {
                return new AtomicLongDeserializer();
            }
            if (cls == ByteBuffer.class) {
                return new ByteBufferDeserializer();
            }
            if (cls != Void.class) {
                return null;
            }
            return NullifyingDeserializer.instance;
        }
        return null;
    }

    public static boolean hasDeserializerFor(Class<?> cls) {
        return _classNames.contains(cls.getName());
    }
}
