package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.serializers.ObjectField;
import java.lang.reflect.Field;
/* loaded from: classes2.dex */
class ObjectCachedFieldFactory implements FieldSerializer.CachedFieldFactory {
    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedFieldFactory
    public FieldSerializer.CachedField createCachedField(Class cls, Field field, FieldSerializer fieldSerializer) {
        if (cls.isPrimitive()) {
            if (cls == Boolean.TYPE) {
                return new ObjectField.ObjectBooleanField(fieldSerializer);
            }
            if (cls == Byte.TYPE) {
                return new ObjectField.ObjectByteField(fieldSerializer);
            }
            if (cls == Character.TYPE) {
                return new ObjectField.ObjectCharField(fieldSerializer);
            }
            if (cls == Short.TYPE) {
                return new ObjectField.ObjectShortField(fieldSerializer);
            }
            if (cls == Integer.TYPE) {
                return new ObjectField.ObjectIntField(fieldSerializer);
            }
            if (cls == Long.TYPE) {
                return new ObjectField.ObjectLongField(fieldSerializer);
            }
            if (cls == Float.TYPE) {
                return new ObjectField.ObjectFloatField(fieldSerializer);
            }
            if (cls == Double.TYPE) {
                return new ObjectField.ObjectDoubleField(fieldSerializer);
            }
            return new ObjectField(fieldSerializer);
        }
        return new ObjectField(fieldSerializer);
    }
}
