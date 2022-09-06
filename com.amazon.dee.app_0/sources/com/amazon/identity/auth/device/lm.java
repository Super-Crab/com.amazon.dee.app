package com.amazon.identity.auth.device;

import amazon.speech.simclient.settings.Settings;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.kcpsdk.common.HttpVerb;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class lm {
    private static final String TAG = "com.amazon.identity.auth.device.lm";
    private String bn;
    private String mReason;
    private md rT;
    private String sk = getDefaultUrl();
    private Map<String, mb> tv;
    private mc un;

    public static boolean eA(String str) {
        return md.isValidUrl(str);
    }

    public static String getDefaultUrl() {
        return "https://" + EnvironmentUtils.cd().cm() + "/FirsProxy/getDeviceCredentials";
    }

    public boolean c(mc mcVar) {
        this.un = mcVar;
        return true;
    }

    public void ei(String str) {
        this.bn = str;
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
            io.e(TAG, "setReason: reason was invalid. Cannot be set.");
            return false;
        }
        this.mReason = str;
        return true;
    }

    public boolean ez(String str) {
        if (!md.isValidUrl(str)) {
            io.e(TAG, "setURL: url is invalid. Cannot be set. Invalidating default URL to prevent it from being used.");
            this.sk = null;
            return false;
        }
        this.sk = str;
        return true;
    }

    public md ho() {
        if (!isValid()) {
            io.e(TAG, "getWebRequest: Cannot construct a WebRequest because the UpdateDeviceCredentialsRequest is invalid. (See previous warnings from UpdateDeviceCredentialsRequest::isValid for details.)");
            return null;
        }
        md mdVar = this.rT;
        if (mdVar != null) {
            return mdVar;
        }
        this.rT = new md();
        this.rT.dM(this.sk);
        this.rT.a(HttpVerb.HttpVerbGet);
        String str = this.mReason;
        if (str != null) {
            this.rT.aA(Settings.ListeningSettings.EXTRA_REASON, str);
        }
        mc mcVar = this.un;
        if (mcVar != null) {
            this.rT.aA("softwareVersion", mcVar.getString());
        }
        String str2 = this.bn;
        if (str2 != null) {
            this.rT.aA("softwareComponentId", str2);
        }
        this.rT.setHeader("Content-Type", "text/xml");
        Map<String, mb> map = this.tv;
        if (map != null && map.size() > 0) {
            mi miVar = new mi("request", new mj[0]);
            miVar.a(new mh(this.tv));
            this.rT.eL(miVar.iH());
            this.rT.a(HttpVerb.HttpVerbPost);
        }
        this.rT.m(true);
        io.a(TAG, "getWebRequest: constructed a web request with:\nReason: %s", this.mReason);
        return this.rT;
    }

    public boolean isValid() {
        if (ma.isNullOrEmpty(this.sk)) {
            io.w(TAG, "isValid: returning false because a valid url has not been set.");
            return false;
        }
        return true;
    }

    public void l(Map<String, mb> map) {
        this.tv = new HashMap(map);
    }
}
