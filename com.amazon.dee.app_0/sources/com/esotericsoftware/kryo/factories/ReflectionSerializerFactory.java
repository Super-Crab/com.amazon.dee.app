package com.esotericsoftware.kryo.factories;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.util.Util;
/* loaded from: classes2.dex */
public class ReflectionSerializerFactory implements SerializerFactory {
    private final Class<? extends Serializer> serializerClass;

    public ReflectionSerializerFactory(Class<? extends Serializer> cls) {
        this.serializerClass = cls;
    }

    @Override // com.esotericsoftware.kryo.factories.SerializerFactory
    public Serializer makeSerializer(Kryo kryo, Class<?> cls) {
        return makeSerializer(kryo, this.serializerClass, cls);
    }

    public static Serializer makeSerializer(Kryo kryo, Class<? extends Serializer> cls, Class<?> cls2) {
        try {
            try {
                try {
                    try {
                        return cls.getConstructor(Kryo.class, Class.class).newInstance(kryo, cls2);
                    } catch (NoSuchMethodException unused) {
                        return cls.newInstance();
                    }
                } catch (Exception e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to create serializer \"");
                    outline107.append(cls.getName());
                    outline107.append("\" for class: ");
                    outline107.append(Util.className(cls2));
                    throw new IllegalArgumentException(outline107.toString(), e);
                }
            } catch (NoSuchMethodException unused2) {
                return cls.getConstructor(Kryo.class).newInstance(kryo);
            }
        } catch (NoSuchMethodException unused3) {
            return cls.getConstructor(Class.class).newInstance(cls2);
        }
    }
}
