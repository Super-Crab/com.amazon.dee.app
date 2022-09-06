package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class db implements kn {
    private final ej bR;
    final jw iJ;
    private final Context mContext;
    Handler mHandler;

    public db(Context context, md mdVar, final km kmVar, lk lkVar, ej ejVar) {
        this.mContext = context;
        this.bR = ejVar;
        Looper mainLooper = Looper.getMainLooper();
        if (mainLooper != null) {
            this.mHandler = new Handler(mainLooper);
        } else {
            this.mHandler = new Handler();
        }
        this.iJ = new jw(this.mContext, mdVar, new km() { // from class: com.amazon.identity.auth.device.db.1
            @Override // com.amazon.identity.auth.device.km
            public void a(final byte[] bArr, final int i) {
                ig.a(db.this.mHandler, new Runnable() { // from class: com.amazon.identity.auth.device.db.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        kmVar.a(bArr, i);
                    }
                });
            }

            @Override // com.amazon.identity.auth.device.km
            public void cE() {
                ig.a(db.this.mHandler, new Runnable() { // from class: com.amazon.identity.auth.device.db.1.4
                    @Override // java.lang.Runnable
                    public void run() {
                        kmVar.cE();
                    }
                });
            }

            @Override // com.amazon.identity.auth.device.km
            public void cF() {
                ig.a(db.this.mHandler, new Runnable() { // from class: com.amazon.identity.auth.device.db.1.5
                    @Override // java.lang.Runnable
                    public void run() {
                        kmVar.cF();
                    }
                });
            }

            @Override // com.amazon.identity.auth.device.km
            public void onNetworkError() {
                ig.a(db.this.mHandler, new Runnable() { // from class: com.amazon.identity.auth.device.db.1.3
                    @Override // java.lang.Runnable
                    public void run() {
                        kmVar.onNetworkError();
                    }
                });
            }

            @Override // com.amazon.identity.auth.device.km
            public void a(final me meVar) {
                ig.a(db.this.mHandler, new Runnable() { // from class: com.amazon.identity.auth.device.db.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        kmVar.a(meVar);
                    }
                });
            }
        }, lkVar, this.bR);
    }

    @Override // com.amazon.identity.auth.device.kn
    public void cD() {
        ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.db.2
            @Override // java.lang.Runnable
            public void run() {
                db.this.iJ.cD();
            }
        });
    }
}
