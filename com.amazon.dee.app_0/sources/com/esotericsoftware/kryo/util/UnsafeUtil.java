package com.esotericsoftware.kryo.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.minlog.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import sun.misc.Cleaner;
import sun.misc.Unsafe;
import sun.nio.ch.DirectBuffer;
/* loaded from: classes2.dex */
public class UnsafeUtil {
    private static final Unsafe _unsafe;
    public static final long byteArrayBaseOffset;
    public static final long charArrayBaseOffset;
    static Constructor<? extends ByteBuffer> directByteBufferConstr;
    public static final long doubleArrayBaseOffset;
    public static final long floatArrayBaseOffset;
    public static final long intArrayBaseOffset;
    public static final long longArrayBaseOffset;
    public static final long shortArrayBaseOffset;

    /* JADX WARN: Removed duplicated region for block: B:38:0x008f  */
    static {
        /*
            Method dump skipped, instructions count: 211
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.kryo.util.UnsafeUtil.<clinit>():void");
    }

    public static final ByteBuffer getDirectBufferAt(long j, int i) {
        Constructor<? extends ByteBuffer> constructor = directByteBufferConstr;
        if (constructor == null) {
            return null;
        }
        try {
            return constructor.newInstance(Long.valueOf(j), Integer.valueOf(i), null);
        } catch (Exception e) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline56("Cannot allocate ByteBuffer at a given address: ", j), e);
        }
    }

    public static void releaseBuffer(ByteBuffer byteBuffer) {
        Cleaner cleaner;
        if (byteBuffer == null || !byteBuffer.isDirect() || (cleaner = ((DirectBuffer) byteBuffer).cleaner()) == null) {
            return;
        }
        cleaner.clean();
    }

    public static Field[] sortFieldsByOffset(List<Field> list) {
        Field[] fieldArr = (Field[]) list.toArray(new Field[0]);
        Arrays.sort(fieldArr, new Comparator<Field>() { // from class: com.esotericsoftware.kryo.util.UnsafeUtil.1
            @Override // java.util.Comparator
            public int compare(Field field, Field field2) {
                int i = (UnsafeUtil.unsafe().objectFieldOffset(field) > UnsafeUtil.unsafe().objectFieldOffset(field2) ? 1 : (UnsafeUtil.unsafe().objectFieldOffset(field) == UnsafeUtil.unsafe().objectFieldOffset(field2) ? 0 : -1));
                if (i < 0) {
                    return -1;
                }
                return i == 0 ? 0 : 1;
            }
        });
        for (Field field : list) {
            if (Log.TRACE) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Field '");
                outline107.append(field.getName());
                outline107.append("' at offset ");
                outline107.append(unsafe().objectFieldOffset(field));
                Log.trace("kryo", outline107.toString());
            }
        }
        return fieldArr;
    }

    public static final Unsafe unsafe() {
        return _unsafe;
    }
}
