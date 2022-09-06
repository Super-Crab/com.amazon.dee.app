package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ej {
    private final String lw;
    private final int lx;
    private final int ly;
    private mu lz;
    private long mStartTime;
    private static final AtomicInteger lv = new AtomicInteger(0);
    private static final String TAG = ej.class.getSimpleName();

    private ej(int i, String str, int i2) {
        this.mStartTime = System.nanoTime();
        this.lx = i;
        this.lw = str;
        this.ly = i2;
        this.lz = mq.eT(this.lw);
        io.a("Tracer created. Tracer Id : %s API Name : %s", Integer.valueOf(this.lx), this.lw);
    }

    public static ej b(Intent intent, String str) {
        if (intent == null) {
            return new ej(str);
        }
        int intExtra = intent.getIntExtra("traceId", dZ());
        String stringExtra = intent.getStringExtra("apiName");
        int intExtra2 = intent.getIntExtra("callingUid", -1);
        if (TextUtils.isEmpty(stringExtra)) {
            String str2 = TAG;
            StringBuilder sb = new StringBuilder("There is no tracer info in intent, creating tracer using new traceId and defaultApiName, traceId:");
            sb.append(intExtra);
            sb.append(" apiName:");
            sb.append(str);
            io.dm(str2);
            return new ej(intExtra, str, intExtra2);
        }
        String str3 = TAG;
        StringBuilder sb2 = new StringBuilder("Creating Tracer from intent, traceId:");
        sb2.append(intExtra);
        sb2.append(" apiName:");
        sb2.append(stringExtra);
        io.dm(str3);
        return new ej(intExtra, stringExtra, intExtra2);
    }

    public static ej by(String str) {
        return new ej(str);
    }

    public static ej d(Bundle bundle, String str) {
        if (bundle == null) {
            return new ej(str);
        }
        int i = bundle.getInt("traceId", dZ());
        String string = bundle.getString("apiName");
        int i2 = bundle.getInt("callingUid", -1);
        if (TextUtils.isEmpty(string)) {
            return new ej(i, str, i2);
        }
        return new ej(i, string, i2);
    }

    private static int dZ() {
        return (Process.myPid() * 1000) + (lv.incrementAndGet() % 1000);
    }

    public void D(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.putInt("traceId", this.lx);
        bundle.putString("apiName", this.lw);
    }

    public String O(Context context) {
        String[] packagesForUid;
        try {
            return (this.ly == -1 || (packagesForUid = context.getPackageManager().getPackagesForUid(this.ly)) == null) ? "unknown" : Arrays.toString(packagesForUid);
        } catch (Exception e) {
            io.w(TAG, String.format("Couldn't get packages for the calling uid.Error Message : %s", e.getMessage()));
            return "unknown";
        }
    }

    public void bA(String str) {
        this.lz.bA(str);
    }

    public mv bz(String str) {
        mv eO = this.lz.eO(str);
        eO.start();
        return eO;
    }

    public void e(Intent intent) {
        if (intent == null) {
            return;
        }
        intent.putExtra("traceId", this.lx);
        intent.putExtra("apiName", this.lw);
    }

    public mv ea() {
        mv eO = this.lz.eO("Time");
        eO.start();
        return eO;
    }

    public void eb() {
        this.lz.iJ();
    }

    public mv f(Context context, String str) {
        ms aS = ms.aS(context);
        mv eN = aS.eN(this.lw + ":" + str);
        eN.start();
        return eN;
    }

    public void incrementCounter(String str, double d) {
        this.lz.incrementCounter(str, d);
    }

    private ej(String str) {
        this(dZ(), str, Binder.getCallingUid());
    }

    public void b(String str, String... strArr) {
        this.lz.b(str, strArr);
    }
}
