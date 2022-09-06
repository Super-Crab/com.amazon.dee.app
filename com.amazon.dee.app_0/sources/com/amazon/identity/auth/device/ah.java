package com.amazon.identity.auth.device;

import android.content.Context;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ah implements s {
    private static final String TAG = "com.amazon.identity.auth.device.ah";
    private final cz bj;
    private final Context mContext;

    public ah(Context context) {
        this.mContext = ed.M(context);
        this.bj = (cz) this.mContext.getSystemService("sso_webservice_caller_creator");
    }

    @Override // com.amazon.identity.auth.device.s
    public void a(String str, md mdVar, final t tVar, ej ejVar) {
        kh khVar = new kh() { // from class: com.amazon.identity.auth.device.ah.1
            @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
            public void a(Object obj) {
                tVar.c((lb) obj);
            }

            @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
            public void k() {
                tVar.onNetworkError();
            }

            @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
            public void l() {
                tVar.onBadResponse();
            }

            @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
            public void onAuthenticationFailed() {
                tVar.onAuthenticationFailed();
            }
        };
        this.bj.c(str, ejVar).b(mdVar, new ld(), khVar).cD();
    }
}
