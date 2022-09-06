package com.amazon.identity.auth.device;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class lg {
    private static final String TAG = "com.amazon.identity.auth.device.lg";
    protected String bk;
    protected md rT;
    protected ks sE;
    protected String sz;
    protected String tZ;
    protected String ua;
    protected String ub;

    void a(ks ksVar) {
        this.sE = ksVar;
    }

    public final boolean dT(String str) {
        if (!ma.eG(str)) {
            io.e(TAG, "setDeviceType: deviceType was invalid. Cannot be set.");
            return false;
        }
        this.bk = str;
        return true;
    }

    public final boolean dU(String str) {
        if (!ma.eH(str)) {
            io.e(TAG, "setDeviceSerialNumber: device serial number was invalid. Cannot be set.");
            return false;
        }
        this.sz = str;
        this.tZ = ku.dO(this.sz);
        return true;
    }

    public void e(ej ejVar) {
        ks hw = ks.hw();
        if (hw != null && hw.hv()) {
            io.i(TAG, "TrustZone signer is available on this device.");
            a(hw);
            if (ejVar == null) {
                return;
            }
            ejVar.bA("TrustZoneAvailable");
            return;
        }
        io.i(TAG, "TrustZone signer is not available on this device.");
    }

    public final boolean ew(String str) {
        if (ma.isNullOrEmpty(str)) {
            io.dm(TAG);
            return false;
        }
        this.ua = str;
        return true;
    }

    public final void ex(String str) {
        this.ub = str;
        this.rT = null;
    }

    protected JSONObject hJ() throws JSONException {
        return jv.ha();
    }

    public abstract md ho();

    public void ia() {
        a(ks.hw());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String ib() {
        if (this.sE == null) {
            return null;
        }
        try {
            return this.sE.d("drvV1", jv.a(jv.w(this.bk, this.sz, this.ua), hJ(), this.ub));
        } catch (Exception e) {
            io.e(TAG, "Failed to sign JWT", e);
            return null;
        }
    }
}
