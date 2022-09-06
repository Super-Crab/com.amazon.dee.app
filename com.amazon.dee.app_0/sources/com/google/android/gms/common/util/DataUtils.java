package com.google.android.gms.common.util;

import android.database.CharArrayBuffer;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.ByteArrayOutputStream;
@KeepForSdk
/* loaded from: classes2.dex */
public final class DataUtils {
    @KeepForSdk
    public static void copyStringToBuffer(String str, CharArrayBuffer charArrayBuffer) {
        if (TextUtils.isEmpty(str)) {
            charArrayBuffer.sizeCopied = 0;
        } else {
            char[] cArr = charArrayBuffer.data;
            if (cArr != null && cArr.length >= str.length()) {
                str.getChars(0, str.length(), charArrayBuffer.data, 0);
            } else {
                charArrayBuffer.data = str.toCharArray();
            }
        }
        charArrayBuffer.sizeCopied = str.length();
    }

    @KeepForSdk
    public static byte[] loadImageBytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
