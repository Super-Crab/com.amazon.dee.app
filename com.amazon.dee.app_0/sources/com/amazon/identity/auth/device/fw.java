package com.amazon.identity.auth.device;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import javax.crypto.BadPaddingException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class fw {
    private static final String TAG = "com.amazon.identity.auth.device.fw";

    private byte[] bZ(String str) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            io.e(TAG, "Cannot encode a string as UTF-8 on this platform.");
            return null;
        }
    }

    private cr eN() {
        byte[] cq = cq();
        if (cq == null) {
            io.e(TAG, "Aborting encrypt/decrypt because a valid cipher could not be created.");
            return null;
        }
        return new cr(cq);
    }

    private String f(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            io.e(TAG, "Cannot encode the given bytes as aUTF-8 string on this platform.");
            return null;
        }
    }

    @Deprecated
    public String bX(String str) {
        byte[] b;
        byte[] bZ = bZ(str);
        cr eN = eN();
        if (bZ == null || eN == null || (b = eN.b(bZ)) == null) {
            return null;
        }
        return Base64.encodeToString(b, 0);
    }

    @Deprecated
    public String bY(String str) throws BadPaddingException {
        cr eN = eN();
        if (eN == null || str == null) {
            return null;
        }
        return f(eN.c(Base64.decode(str, 0)));
    }

    public abstract byte[] cq();
}
