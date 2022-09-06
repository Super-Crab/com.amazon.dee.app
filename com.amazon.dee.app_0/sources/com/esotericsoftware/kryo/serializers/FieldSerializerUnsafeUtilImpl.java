package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.util.UnsafeUtil;
import java.lang.reflect.Field;
/* loaded from: classes2.dex */
final class FieldSerializerUnsafeUtilImpl implements FieldSerializerUnsafeUtil {
    private FieldSerializer serializer;

    public FieldSerializerUnsafeUtilImpl(FieldSerializer fieldSerializer) {
        this.serializer = fieldSerializer;
    }

    private int fieldSizeOf(Class<?> cls) {
        if (cls == Integer.TYPE || cls == Float.TYPE) {
            return 4;
        }
        if (cls == Long.TYPE || cls == Double.TYPE) {
            return 8;
        }
        if (cls == Byte.TYPE || cls == Boolean.TYPE) {
            return 1;
        }
        if (cls != Short.TYPE && cls != Character.TYPE) {
            return UnsafeUtil.unsafe().addressSize();
        }
        return 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00fd  */
    @Override // com.esotericsoftware.kryo.serializers.FieldSerializerUnsafeUtil
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void createUnsafeCacheFieldsAndRegions(java.util.List<java.lang.reflect.Field> r27, java.util.List<com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField> r28, int r29, com.esotericsoftware.kryo.util.IntArray r30) {
        /*
            Method dump skipped, instructions count: 384
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.kryo.serializers.FieldSerializerUnsafeUtilImpl.createUnsafeCacheFieldsAndRegions(java.util.List, java.util.List, int, com.esotericsoftware.kryo.util.IntArray):void");
    }

    @Override // com.esotericsoftware.kryo.serializers.FieldSerializerUnsafeUtil
    public long getObjectFieldOffset(Field field) {
        return UnsafeUtil.unsafe().objectFieldOffset(field);
    }
}
