package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class cc extends cg {
    private static cc hR;
    private final boolean hS;
    private final MultipleAccountManager hT;
    private ce hU;
    private final Context mContext;

    private cc(Context context, boolean z) {
        super(context);
        this.hS = z;
        this.mContext = context;
        this.hT = new MultipleAccountManager(this.mContext);
    }

    public static synchronized cc a(Context context, boolean z) {
        cc ccVar;
        synchronized (cc.class) {
            if (hR == null || jk.gR()) {
                a(context, Boolean.valueOf(z));
            }
            ccVar = hR;
        }
        return ccVar;
    }

    private synchronized ce bI() {
        if (this.hU == null) {
            this.hU = new ce(ed.M(this.mContext));
        }
        return this.hU;
    }

    @Override // com.amazon.identity.auth.device.cg, com.amazon.identity.auth.device.ci
    public cf aP(String str) throws DeviceDataStoreException {
        im dl = im.dl(str);
        if (this.hS && ("Default COR".equals(dl.getKey()) || "Default PFM".equals(dl.getKey()))) {
            return bI().aP(str);
        }
        return super.aP(str);
    }

    public ea bJ() {
        return new cs(this.mContext, this.hT);
    }

    public static void a(Context context, Boolean bool) {
        boolean b;
        if (bool != null) {
            b = bool.booleanValue();
        } else {
            b = mz.b(context, ce.hW);
        }
        hR = new cc(context.getApplicationContext(), b);
    }
}
