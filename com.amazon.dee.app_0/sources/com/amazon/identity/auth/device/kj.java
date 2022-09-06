package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.kcpsdk.common.HttpVerb;
import com.amazon.identity.kcpsdk.common.WebProtocol;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class kj {
    private static final String TAG = "com.amazon.identity.auth.device.kj";
    private Bundle bT;
    private md rT;
    private boolean sm;
    private boolean sn;

    public void R(Bundle bundle) {
        this.bT = bundle;
    }

    public void hn() {
        this.sm = false;
    }

    public md ho() {
        md mdVar = this.rT;
        if (mdVar != null) {
            return mdVar;
        }
        this.rT = new md();
        this.rT.a(WebProtocol.WebProtocolHttps);
        this.rT.setHost(EnvironmentUtils.cd().cm());
        this.rT.setPath("/FirsProxy/disownFiona");
        this.rT.a(HttpVerb.HttpVerbGet);
        if (this.sm) {
            this.rT.aA("contentDeleted", "true");
        } else {
            this.rT.aA("contentDeleted", PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
        }
        if (this.sn) {
            this.rT.aA("deregisterExisting", "true");
        } else {
            this.rT.aA("deregisterExisting", PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
        }
        Bundle bundle = this.bT;
        if (bundle != null) {
            Bundle bundle2 = bundle.getBundle(MAPAccountManager.KEY_DEREGISTRATION_METADATA);
            if (bundle2 != null) {
                for (String str : bundle2.keySet()) {
                    String string = bundle2.getString(str);
                    if (string != null) {
                        this.rT.aA(str, string);
                    } else {
                        io.e(TAG, String.format("Not setting the value of key %s to the deregistration request as the type is unsupported.", str));
                    }
                }
            } else {
                io.dm(TAG);
            }
        }
        this.rT.setHeader("Content-Type", "text/xml");
        this.rT.m(true);
        String str2 = TAG;
        Object[] objArr = new Object[1];
        objArr[0] = this.sm ? "Yes" : "No";
        io.a(str2, "DeregisterDeviceRequest: getWebRequest: constructed a web request with:\nContent Deleted: %s", objArr);
        return this.rT;
    }

    public void j(boolean z) {
        this.sn = z;
    }
}
