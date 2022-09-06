package com.amazon.identity.auth.device;

import android.content.Context;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class ms {
    public static ms aS(Context context) {
        if (context != null) {
            if (mq.aP(context)) {
                try {
                    return mw.aT(context);
                } catch (Throwable th) {
                    io.e("PeriodicMetricsCollector", "Cannot create ThirdPartyPeriodicMetricsCollector", th);
                }
            } else if (mq.iN()) {
                try {
                    return mm.aK(context);
                } catch (Throwable th2) {
                    io.e("PeriodicMetricsCollector", "Cannot create FireOSPeriodicMetricsCollector", th2);
                }
            }
        }
        io.i("PeriodicMetricsCollector", "Appropriate metrics component cannot be found");
        return new mr();
    }

    public abstract void bA(String str);

    public abstract mv eN(String str);
}
