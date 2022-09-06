package com.amazon.identity.auth.device;

import android.content.Context;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class fn {
    public static Context mO;
    public static mu mP;

    public static void bS(String str) {
        ey();
        c("NetworkFailure", mp.eS(str));
    }

    public static void c(String str, String... strArr) {
        mu muVar = mP;
        if (muVar == null) {
            return;
        }
        muVar.a("GenericMetrics_WebserviceCall", str, strArr);
    }

    public static synchronized void ey() {
        synchronized (fn.class) {
            if (mO != null && mP == null) {
                mP = mq.aO(mO);
            }
        }
    }
}
