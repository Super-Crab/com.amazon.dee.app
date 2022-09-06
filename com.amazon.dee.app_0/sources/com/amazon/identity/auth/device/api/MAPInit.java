package com.amazon.identity.auth.device.api;

import android.content.Context;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.di;
import com.amazon.identity.auth.device.dq;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.fn;
import com.amazon.identity.auth.device.hv;
import com.amazon.identity.auth.device.id;
import com.amazon.identity.auth.device.in;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.ip;
import com.amazon.identity.auth.device.ji;
import com.amazon.identity.auth.device.metrics.SSOMetrics;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.ms;
import com.amazon.identity.auth.device.mv;
import com.amazon.identity.auth.device.mz;
import com.amazon.identity.platform.setting.PlatformSettings;
import java.util.Locale;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class MAPInit {
    private static final String TAG = "com.amazon.identity.auth.device.api.MAPInit";
    private static boolean gy = false;
    private static MAPInit gz;
    private final Context gA;
    private boolean mInitialized;

    private MAPInit(Context context) {
        this.gA = context;
    }

    static /* synthetic */ void b(MAPInit mAPInit) {
        PlatformSettings.aU(mAPInit.gA).addListener(new Runnable() { // from class: com.amazon.identity.auth.device.api.MAPInit.2
            @Override // java.lang.Runnable
            public void run() {
                io.dm(MAPInit.TAG);
                MAPInit.this.bk();
            }
        });
        mAPInit.bk();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bk() {
        EnvironmentUtils.ce();
        id.at(this.gA);
    }

    @FireOsSdk
    public static synchronized MAPInit getInstance(Context context) {
        synchronized (MAPInit.class) {
            in.a(context, "context");
            if (gz != null) {
                return gz;
            }
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                return new MAPInit(null);
            }
            MAPInit mAPInit = new MAPInit(applicationContext);
            gz = mAPInit;
            return mAPInit;
        }
    }

    public static boolean isRunningInFunctionalTest() {
        return gy;
    }

    public static void setIsRunningInFunctionalTest(boolean z) {
        gy = z;
    }

    @FireOsSdk
    public synchronized void initialize() {
        if (this.gA == null) {
            return;
        }
        if (this.mInitialized) {
            return;
        }
        this.mInitialized = true;
        io.setPackageName(this.gA.getPackageName());
        io.i(TAG, String.format(Locale.ENGLISH, "Initializing MAP (%s) for app %s. MAP release version: %s", hv.gs(), this.gA.getPackageName(), ip.gF()));
        fn.mO = this.gA.getApplicationContext();
        SSOMetrics.P(this.gA);
        mq.P(this.gA);
        ej by = ej.by("MAPInit:initialize");
        final mv f = by.f(this.gA, "NecessaryTime");
        final mv f2 = by.f(this.gA, "TotalTime");
        ms aS = ms.aS(this.gA);
        boolean gP = ji.gP();
        aS.bA("MAPInitOnMainThread:".concat(String.valueOf(gP)));
        io.i(TAG, "Running MAPInit on main thread: ".concat(String.valueOf(gP)));
        ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.api.MAPInit.1
            @Override // java.lang.Runnable
            public void run() {
                PlatformSettings.aU(MAPInit.this.gA);
                mz.bo(MAPInit.this.gA);
                if (di.B(MAPInit.this.gA)) {
                    di.A(MAPInit.this.gA).init();
                }
                MAPInit.b(MAPInit.this);
                MAPInit.a(MAPInit.this, f2);
                f.stop();
            }
        });
    }

    static /* synthetic */ void a(MAPInit mAPInit, final mv mvVar) {
        ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.api.MAPInit.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    new dq(MAPInit.this.gA).dc();
                } finally {
                    mvVar.stop();
                }
            }
        });
    }
}
