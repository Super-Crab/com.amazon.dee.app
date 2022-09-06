package com.amazon.CoralAndroidClient.ClientBase;

import com.amazon.CoralAndroidClient.Exception.NativeException;
import com.amazon.CoralAndroidClient.Model.BlobValue;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
/* loaded from: classes.dex */
public class Helper {
    public static ByteBuffer Base64StringToByteBuffer(String str) throws NativeException {
        if (str != null) {
            BlobValue blobValue = new BlobValue();
            blobValue.setValue(str);
            return ByteBuffer.wrap(blobValue.getValue());
        }
        throw new NativeException("null string");
    }

    public static Date StringSecondToDate(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new Date((long) (Double.parseDouble(str) * 1000.0d));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static int hash(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static boolean isStringNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
