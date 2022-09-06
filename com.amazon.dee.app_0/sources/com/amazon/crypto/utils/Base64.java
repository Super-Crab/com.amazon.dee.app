package com.amazon.crypto.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public final class Base64 {
    private Base64() {
        UtilityInstanceAttempt.in(this);
    }

    public static String base64FromBase64Url(@NonNull String str) {
        return GeneratedOutlineSupport1.outline72(str.replace('-', '+').replace('_', '/'), new String(new char[4 - (str.length() % 4)]).replace("\u0000", Config.Compare.EQUAL_TO));
    }

    public static byte[] decode(@Nullable byte[] bArr) throws IllegalArgumentException {
        return bArr == null ? bArr : android.util.Base64.decode(bArr, 2);
    }

    @Nullable
    public static byte[] encode(@Nullable byte[] bArr) {
        return bArr == null ? bArr : android.util.Base64.encode(bArr, 2);
    }
}
