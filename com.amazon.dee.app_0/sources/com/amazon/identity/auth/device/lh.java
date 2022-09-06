package com.amazon.identity.auth.device;

import android.text.TextUtils;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.kcpsdk.common.HttpVerb;
import com.amazon.identity.kcpsdk.common.WebProtocol;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class lh {
    private static final String TAG = "com.amazon.identity.auth.device.lh";
    private md rT;
    private String sS;

    public boolean ey(String str) {
        if (TextUtils.isEmpty(str)) {
            io.e(TAG, "setDeviceName: device name was invalid. Cannot be set.");
            return false;
        }
        this.sS = str;
        return true;
    }

    public md ho() {
        if (this.sS == null) {
            io.e(TAG, "getWebRequest: Cannot construct a WebRequest because the RenameDeviceRequest is invalid. (See previous warnings from RenameDeviceRequest::isValidDeviceName for details.)");
            return null;
        }
        md mdVar = this.rT;
        if (mdVar != null) {
            return mdVar;
        }
        this.rT = new md();
        this.rT.a(WebProtocol.WebProtocolHttps);
        this.rT.setHost(EnvironmentUtils.cd().cm());
        this.rT.setPath("/FirsProxy/renameFiona");
        this.rT.a(HttpVerb.HttpVerbGet);
        this.rT.aA("nickname", this.sS);
        this.rT.setHeader("Content-Type", "text/xml");
        this.rT.m(true);
        io.i(TAG, "getWebRequest: getWebRequest: constructed a web request.");
        io.b("Device new name: %s", this.sS);
        return this.rT;
    }
}
