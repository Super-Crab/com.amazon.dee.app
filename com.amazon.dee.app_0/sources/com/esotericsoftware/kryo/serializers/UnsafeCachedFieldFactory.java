package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.serializers.UnsafeCacheFields;
import java.lang.reflect.Field;
/* loaded from: classes2.dex */
class UnsafeCachedFieldFactory implements FieldSerializer.CachedFieldFactory {
    UnsafeCachedFieldFactory() {
    }

    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedFieldFactory
    public FieldSerializer.CachedField createCachedField(Class cls, Field field, FieldSerializer fieldSerializer) {
        if (cls.isPrimitive()) {
            if (cls == Boolean.TYPE) {
                return new UnsafeCacheFields.UnsafeBooleanField(field);
            }
            if (cls == Byte.TYPE) {
                return new UnsafeCacheFields.UnsafeByteField(field);
            }
            if (cls == Character.TYPE) {
                return new UnsafeCacheFields.UnsafeCharField(field);
            }
            if (cls == Short.TYPE) {
                return new UnsafeCacheFields.UnsafeShortField(field);
            }
            if (cls == Integer.TYPE) {
                return new UnsafeCacheFields.UnsafeIntField(field);
            }
            if (cls == Long.TYPE) {
                return new UnsafeCacheFields.UnsafeLongField(field);
            }
            if (cls == Float.TYPE) {
                return new UnsafeCacheFields.UnsafeFloatField(field);
            }
            if (cls == Double.TYPE) {
                return new UnsafeCacheFields.UnsafeDoubleField(field);
            }
            return new UnsafeCacheFields.UnsafeObjectField(fieldSerializer);
        } else if (cls == String.class && (!fieldSerializer.kryo.getReferences() || !fieldSerializer.kryo.getReferenceResolver().useReferences(String.class))) {
            return new UnsafeCacheFields.UnsafeStringField(field);
        } else {
            return new UnsafeCacheFields.UnsafeObjectField(fieldSerializer);
        }
    }
}
