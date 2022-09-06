package com.amazon.identity.auth.device;

import android.content.Context;
import java.util.concurrent.TimeUnit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class hd {
    private static final long pQ = jj.c(1, TimeUnit.MILLISECONDS);
    private final ds ia;
    private final gm pA;

    public hd(Context context, gm gmVar) {
        this.ia = (ds) ed.M(context).getSystemService("sso_platform");
        this.pA = gmVar;
    }

    public synchronized boolean cL(String str) {
        if (this.ia.m4061do()) {
            return false;
        }
        Long cM = cM(str);
        if (cM == null) {
            return true;
        }
        return System.currentTimeMillis() - cM.longValue() >= pQ;
    }

    public Long cM(String str) {
        String b = this.pA.b(str, "3PLastRegistrationCheckTimeKey");
        if (b == null) {
            return null;
        }
        return je.dB(b);
    }

    public void cN(String str) {
        if (!this.ia.m4061do()) {
            this.pA.a(str, "3PLastRegistrationCheckTimeKey", Long.toString(System.currentTimeMillis()));
        }
    }
}
