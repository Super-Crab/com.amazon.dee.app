package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class fp {
    private static final String TAG = "fp";
    private String bP;
    private String kn;
    private String mU;
    private String mV;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface a {
        int bE();

        String bF();

        fp eE();
    }

    public static fp E(Bundle bundle) {
        fp bT = eB().bT(bundle.getString("com.amazon.dcp.sso.property.account.acctId"));
        bT.mU = bundle.getString("key_recover_context_reason");
        return bT.bV(bundle.getString("key_recover_context_url")).bW(bundle.getString("key_recover_context_action"));
    }

    public static fp a(MAPCallbackErrorException mAPCallbackErrorException) {
        Bundle bundle;
        Bundle errorBundle = mAPCallbackErrorException.getErrorBundle();
        if (errorBundle == null || (bundle = errorBundle.getBundle("com.amazon.identity.mobi.account.recover.context")) == null) {
            return null;
        }
        return E(bundle);
    }

    public static fp eB() {
        io.dm(TAG);
        return new fp().bW("action_confirm_credential");
    }

    public static void eD() throws AuthenticatedURLConnection.AccountNeedsRecoveryException {
    }

    public fp bT(String str) {
        String str2 = TAG;
        io.i(str2, "Building an account recover context bundle for: ***" + str.substring(str.length() - Math.min(2, str.length())));
        this.bP = str;
        return this;
    }

    public fp bU(String str) {
        mq.b("BuildAccountRecoverContext:".concat(String.valueOf(str)), new String[0]);
        this.mU = str;
        return this;
    }

    public fp bV(String str) {
        this.mV = str;
        return this;
    }

    public fp bW(String str) {
        this.kn = str;
        return this;
    }

    public Bundle eC() {
        Bundle bundle = new Bundle();
        bundle.putBundle("com.amazon.identity.mobi.account.recover.context", toBundle());
        return bundle;
    }

    public String getDirectedId() {
        return this.bP;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.dcp.sso.property.account.acctId", this.bP);
        bundle.putString("key_recover_context_reason", this.mU);
        bundle.putString("key_recover_context_url", this.mV);
        bundle.putString("key_recover_context_action", this.kn);
        return bundle;
    }
}
