package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class di {
    private static final String TAG = "com.amazon.identity.auth.device.di";
    private static di jA;
    private static final long jz = TimeUnit.MILLISECONDS.convert(15, TimeUnit.SECONDS);
    private final CountDownLatch dH = new CountDownLatch(1);
    private final AtomicBoolean jB = new AtomicBoolean(false);
    private final ed o;
    private final gg w;

    di(Context context) {
        this.o = ed.M(context);
        this.w = ((gh) this.o.getSystemService("dcp_data_storage_factory")).dV();
    }

    public static synchronized di A(Context context) {
        di diVar;
        synchronized (di.class) {
            if (jA == null) {
                jA = new di(context.getApplicationContext());
            }
            diVar = jA;
        }
        return diVar;
    }

    public static boolean B(Context context) {
        if (!hx.ak(context) || hx.aj(context)) {
            return true;
        }
        io.dm(TAG);
        return false;
    }

    static /* synthetic */ void a(di diVar) {
        if (hx.ak(diVar.o)) {
            dh.z(diVar.o).cM();
            io.i(TAG, String.format("Central model has no race conditions in terms of common info and hence can be generated locally. Generated version %d", 1));
            return;
        }
        int d = dh.d(diVar.w);
        if (d > 0) {
            String str = TAG;
            String.format("No need to generate more common info. Our needed version is %d and currently we have version %d", 1, Integer.valueOf(d));
            io.dm(str);
            return;
        }
        Integer cR = diVar.cR();
        if (cR != null && cR.intValue() > 0) {
            return;
        }
        String str2 = TAG;
        Object[] objArr = new Object[2];
        objArr[0] = 1;
        objArr[1] = cR != null ? Integer.toString(cR.intValue()) : "<Not Found>";
        io.e(str2, String.format("The main MAP app cannot generate version high enough to function properly. We need version %d or higher, but got %s.", objArr));
    }

    private void cQ() {
        if (!this.jB.get()) {
            io.i(TAG, "Common Info Generator not initialized yet, starting init");
            init();
        }
        try {
            if (this.dH.await(jz, TimeUnit.MILLISECONDS)) {
                return;
            }
            io.e(TAG, "We timed out waiting for common info to be generated");
        } catch (InterruptedException e) {
            io.e(TAG, "We were interrupted waiting for common info to be generated", e);
        }
    }

    private Integer cR() {
        String str = TAG;
        String.format("%s now do generateCommonInfo", this.o.getPackageName());
        io.dm(str);
        Iterator<du> it2 = MAPApplicationInformationQueryer.E(this.o).cY().iterator();
        while (it2.hasNext()) {
            du next = it2.next();
            io.a("Calling Package %s to generate common info", next.getPackageName());
            try {
                String str2 = TAG;
                new StringBuilder("CommonInfoGetter.generateCommonInfo sync: ").append(next.toString());
                io.dm(str2);
                return Integer.valueOf(next.cM());
            } catch (RemoteMAPException e) {
                String str3 = TAG;
                io.w(str3, "Failed to initialize common info from " + next.getPackageName(), e);
                MAPApplicationInformationQueryer.E(this.o).P();
            }
        }
        io.e(TAG, "Cannot find other package to generate common info from.");
        return null;
    }

    public String cO() {
        cQ();
        String c = dh.c(this.w);
        if (c == null) {
            io.e(TAG, "Cannot generate the dsn", new Throwable());
        }
        return c;
    }

    public String cP() {
        cQ();
        String b = dh.b(this.w);
        if (b == null) {
            io.e(TAG, "Cannot generate the token key", new Throwable());
        }
        return b;
    }

    public void init() {
        if (this.jB.getAndSet(true)) {
            io.i(TAG, "Common Data has already been initialized");
        } else if (!B(this.o)) {
            io.dm(TAG);
        } else {
            io.dm(TAG);
            ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.di.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        di.a(di.this);
                    } finally {
                        di.this.dH.countDown();
                    }
                }
            });
        }
    }
}
