package com.amazon.identity.auth.device;

import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class kq {
    private static final String TAG = "com.amazon.identity.auth.device.kq";
    private final Object[] fD = new Object[0];
    private PrivateKey sq;
    private String sr;

    public String hr() {
        String str = this.sr;
        if (str != null) {
            return str;
        }
        String a = y().a();
        if (a == null) {
            this.sr = null;
        } else if (a.contains("-----BEGIN EC PRIVATE KEY-----")) {
            this.sr = "SHA256withECDSA";
        } else {
            this.sr = "SHA256WithRSA";
        }
        return this.sr;
    }

    public PrivateKey hs() {
        PrivateKey privateKey;
        synchronized (this.fD) {
            if (this.sq == null) {
                try {
                    this.sq = il.getPrivateKey(y().a());
                } catch (InvalidKeySpecException e) {
                    String str = TAG;
                    io.e(str, "parseKey: Could not parse private key because it was invalid. Error: " + e.getMessage());
                }
            }
            privateKey = this.sq;
        }
        return privateKey;
    }

    public abstract a y();
}
