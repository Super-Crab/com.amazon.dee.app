package com.amazon.identity.auth.device;

import amazon.speech.simclient.settings.Settings;
import android.text.TextUtils;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.kcpsdk.common.HttpVerb;
import com.amazon.identity.kcpsdk.common.WebProtocol;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class le extends lg {
    private static final String TAG = "com.amazon.identity.auth.device.le";
    private String bn;
    private String mReason;
    private String tW;
    private String tX;
    private mc tY;
    private String td;
    private String te;
    private String tf;
    private String tg;
    private Map<String, mb> tv;

    public boolean b(mc mcVar) {
        if (!mcVar.isValid()) {
            io.e(TAG, "setVersionNumber: version number was invalid. Cannot set.");
            return false;
        }
        this.tY = mcVar;
        return true;
    }

    public boolean dZ(String str) {
        this.td = str;
        return true;
    }

    public void ei(String str) {
        this.bn = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean eu(java.lang.String r5) {
        /*
            r4 = this;
            boolean r0 = com.amazon.identity.auth.device.ma.isNullOrEmpty(r5)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L11
            java.lang.String r0 = com.amazon.identity.auth.device.le.TAG
            java.lang.String r3 = "isValidRadioId: returning false because a null or empty radio id was given."
            com.amazon.identity.auth.device.io.i(r0, r3)
        Lf:
            r0 = r2
            goto L20
        L11:
            boolean r0 = com.amazon.identity.auth.device.ma.eI(r5)
            if (r0 != 0) goto L1f
            java.lang.String r0 = com.amazon.identity.auth.device.le.TAG
            java.lang.String r3 = "isValidRadioId: returning false because a non alpha radio id number was given."
            com.amazon.identity.auth.device.io.i(r0, r3)
            goto Lf
        L1f:
            r0 = r1
        L20:
            if (r0 != 0) goto L2b
            java.lang.String r5 = com.amazon.identity.auth.device.le.TAG
            java.lang.String r0 = "setRadioId: radio id was invalid. Cannot set."
            com.amazon.identity.auth.device.io.e(r5, r0)
            return r2
        L2b:
            r4.tW = r5
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.le.eu(java.lang.String):boolean");
    }

    public boolean ev(String str) {
        boolean z;
        if (ma.isNullOrEmpty(str)) {
            io.i(TAG, "isValidReason: returning false because a null or empty reason was given.");
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            io.e(TAG, "setReason: reason was invalid. Cannot set.");
            return false;
        }
        this.mReason = str;
        return true;
    }

    @Override // com.amazon.identity.auth.device.lg
    public md ho() {
        if (!isValid()) {
            io.e(TAG, "getWebRequest: Cannot construct a WebRequest because the RegisterDeviceWithSecretRequest is invalid. (See previous warnings from RegisterDeviceWithSecretRequest::isValid for details.)");
            return null;
        }
        md mdVar = this.rT;
        if (mdVar != null) {
            return mdVar;
        }
        this.rT = new md();
        String str = this.td;
        if (str != null) {
            this.rT.setHeader("Accept-Language", str);
        }
        this.rT.a(WebProtocol.WebProtocolHttps);
        this.rT.setHost(EnvironmentUtils.cd().cm());
        this.rT.setPath("/FirsProxy/getNewDeviceCredentials");
        this.rT.a(HttpVerb.HttpVerbPost);
        this.rT.aA("deviceType", this.bk);
        this.rT.aA("deviceSerialNumber", this.sz);
        this.rT.aA("secret", this.ua);
        this.rT.aA("radioId", this.tW);
        String str2 = this.tX;
        if (str2 != null) {
            this.rT.aA("secondaryRadioId", str2);
        }
        String str3 = this.mReason;
        if (str3 != null) {
            this.rT.aA(Settings.ListeningSettings.EXTRA_REASON, str3);
        }
        mc mcVar = this.tY;
        if (mcVar != null && mcVar.isValid()) {
            this.rT.aA("softwareVersion", this.tY.getString());
        }
        String str4 = this.bn;
        if (str4 != null) {
            this.rT.aA("softwareComponentId", str4);
        }
        String str5 = this.te;
        if (str5 != null && this.tf != null && this.tg != null) {
            this.rT.aA("publicKeyData", str5);
            this.rT.aA("publicKeyFormat", this.tf);
            this.rT.aA("publicKeyAlgorithm", this.tg);
        }
        String ib = ib();
        if (!TextUtils.isEmpty(ib)) {
            this.rT.aA("deviceRequestVerificationData", ib);
        }
        this.rT.setHeader("Content-Type", "text/xml");
        Map<String, mb> map = this.tv;
        if (map != null && map.size() > 0) {
            mi miVar = new mi("request", new mj[0]);
            miVar.a(new mh(this.tv));
            this.rT.eL(miVar.iH());
        }
        this.rT.m(false);
        String str6 = TAG;
        Object[] objArr = new Object[5];
        objArr[0] = this.bk;
        objArr[1] = this.mReason;
        mc mcVar2 = this.tY;
        objArr[2] = mcVar2 != null ? mcVar2.getString() : "";
        String str7 = this.bn;
        if (str7 == null) {
            str7 = "None";
        }
        objArr[3] = str7;
        String str8 = this.td;
        if (str8 == null) {
            str8 = "Default";
        }
        objArr[4] = str8;
        io.a(str6, "getWebRequest: constructed a web request with:\nDevice Type: %s\nReason: %s\nVersion Number: %s\nSoftware Component Id: %s\nLocale: %s", objArr);
        io.b("Device Serial Number: %s\nRadio Id: %s\nSecondary Radio Id: %s\nSecret: %s", this.sz, this.tW, this.tX, this.ua);
        return this.rT;
    }

    public boolean isValid() {
        if (this.bk == null) {
            io.w(TAG, "isValid: returning false because a valid device type has not been set.");
            return false;
        } else if (this.sz == null) {
            io.w(TAG, "isValid: returning false because a valid device serial number has not been set.");
            return false;
        } else if (this.ua == null) {
            io.w(TAG, "isValid: returning false because a valid secret has not been set.");
            return false;
        } else if (this.tW != null) {
            return true;
        } else {
            io.w(TAG, "isValid: returning false because a valid radio id has not been set.");
            return false;
        }
    }

    public void l(Map<String, mb> map) {
        this.tv = new HashMap(map);
    }
}
