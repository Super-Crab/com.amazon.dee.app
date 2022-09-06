package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class cm {
    private static final String gM = "com.amazon.identity.auth.device.cm";
    private final Map<String, String> iq = new HashMap();
    private final String iv;

    public cm(String str) {
        this.iv = str;
        aZ(str);
    }

    private void aZ(String str) {
        try {
            String query = new URL(str).getQuery();
            if (TextUtils.isEmpty(query)) {
                return;
            }
            for (String str2 : query.split(WebConstants.UriConstants.AMPERSAND_KEY)) {
                String[] split = str2.split(Config.Compare.EQUAL_TO);
                if (split.length > 1) {
                    this.iq.put(split[0], URLDecoder.decode(split[1]));
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Exception parsing Open ID redirect URL: ".concat(String.valueOf(e)), e);
        }
    }

    public String bX() {
        return this.iq.get("openid.oa2.authorization_code");
    }

    public Bundle bY() {
        Bundle bundle = new Bundle();
        Map<String, String> map = this.iq;
        if (map == null) {
            return bundle;
        }
        for (String str : map.keySet()) {
            if (!TextUtils.equals(str, "openid.oa2.scope") && !TextUtils.equals(str, "openid.oa2.access_token")) {
                bundle.putString(str, this.iq.get(str));
            }
        }
        return bundle;
    }

    public String bZ() {
        String str = this.iq.containsKey("openid.oa2.scope") ? this.iq.get("openid.oa2.scope") : "device_auth_refresh";
        GeneratedOutlineSupport1.outline161(str, "Token Scope = ", gM);
        return str;
    }

    public Boolean ca() {
        if ("1".equalsIgnoreCase(this.iq.get("new_account"))) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public String cb() {
        return this.iq.get("account_pool");
    }

    public String cc() {
        return this.iq.get(MAPAccountManager.KEY_CLAIM_TYPE);
    }

    public String getAccessToken() {
        if ("device_auth_access".equalsIgnoreCase(bZ())) {
            io.dm(gM);
            if (this.iq.containsKey("openid.oa2.access_token")) {
                return this.iq.get("openid.oa2.access_token");
            }
            if (!this.iq.containsKey("openid.oa2.refresh_token")) {
                return null;
            }
            return this.iq.get("openid.oa2.refresh_token");
        }
        return null;
    }

    public String getDirectedId() {
        if (this.iq.containsKey("openid.identity")) {
            String str = this.iq.get("openid.identity");
            return str.substring(str.lastIndexOf("/") + 1);
        }
        return null;
    }
}
