package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.util.IntArray;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
/* loaded from: classes2.dex */
interface FieldSerializerUnsafeUtil {

    /* loaded from: classes2.dex */
    public static class Factory {
        static Constructor<FieldSerializerUnsafeUtil> fieldSerializerUnsafeUtilConstructor;

        static {
            try {
                fieldSerializerUnsafeUtilConstructor = FieldSerializer.class.getClassLoader().loadClass("com.esotericsoftware.kryo.serializers.FieldSerializerUnsafeUtilImpl").getConstructor(FieldSerializer.class);
            } catch (Throwable unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static FieldSerializerUnsafeUtil getInstance(FieldSerializer fieldSerializer) {
            Constructor<FieldSerializerUnsafeUtil> constructor = fieldSerializerUnsafeUtilConstructor;
            if (constructor != null) {
                try {
                    return constructor.newInstance(fieldSerializer);
                } catch (Exception unused) {
                    return null;
                }
            }
            return null;
        }
    }

    void createUnsafeCacheFieldsAndRegions(List<Field> list, List<FieldSerializer.CachedField> list2, int i, IntArray intArray);

    long getObjectFieldOffset(Field field);
}
