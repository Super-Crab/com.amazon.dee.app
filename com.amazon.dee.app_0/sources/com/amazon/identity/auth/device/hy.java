package com.amazon.identity.auth.device;

import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class hy {
    private String nn;
    private String qZ;
    private String ra;

    public void gu() {
        try {
            byte[] bArr = new byte[32];
            new SecureRandom().nextBytes(bArr);
            this.nn = Base64.encodeToString(bArr, 11);
            this.qZ = "SHA-256";
            this.ra = Base64.encodeToString(MessageDigest.getInstance("SHA-256").digest(this.nn.getBytes()), 11);
            io.dm("CodeChallengeUtil");
        } catch (NoSuchAlgorithmException e) {
            io.e("CodeChallengeUtil", "Your JRE does not support the required SHA-256 algorithm.", e);
        }
    }

    public String gv() {
        return this.nn;
    }

    public String gw() {
        return this.ra;
    }

    public String gx() {
        return this.qZ;
    }
}
