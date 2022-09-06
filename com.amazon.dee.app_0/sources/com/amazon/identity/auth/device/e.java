package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.Map;
import org.json.JSONArray;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class e {
    private Bundle l = new Bundle();

    public void a(String str) {
        this.l.putString(AccountConstants.KEY_USER_EMAIL, str);
    }

    public void b(String str) {
        this.l.putString("com.amazon.dcp.sso.property.username", str);
    }

    public void c(String str) {
        this.l.putString("com.amazon.dcp.sso.property.firstname", str);
    }

    public void d(String str) {
        this.l.putString("com.amazon.dcp.sso.property.devicename", str);
    }

    public void e(String str) {
        this.l.putString(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN, str);
    }

    public void f(String str) {
        this.l.putString(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY, str);
    }

    public void g(String str) {
        this.l.putString(AccountConstants.TOKEN_TYPE_COOKIE_XMAIN_TOKEN, str);
    }

    public void h(String str) {
        this.l.putString("com.amazon.dcp.sso.token.cookie.xmainAndXabcCookies", str);
    }

    public void i(String str) {
        this.l.putString("com.amazon.identity.cookies.xfsn", str);
    }

    public void j(String str) {
        this.l.putString("com.amazon.dcp.sso.token.device.accountpool", str);
    }

    public void k(String str) {
        this.l.putString(AccountConstants.KEY_CUSTOMER_REGION, str);
    }

    public void l(String str) {
        this.l.putString(AccountConstants.KEY_COR, str);
    }

    public void m(String str) {
        this.l.putString(AccountConstants.KEY_COR_SOURCE, str);
    }

    public void n(String str) {
        this.l.putString(AccountConstants.KEY_PFM, str);
    }

    public void o(String str) {
        this.l.putString("com.amazon.dcp.sso.property.deviceemail", str);
    }

    public void p(String str) {
        this.l.putString("com.amazon.dcp.sso.token.oauth.amazon.access_token", str);
    }

    public void q(String str) {
        this.l.putString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN, str);
    }

    public void setDeviceSerialNumber(String str) {
        this.l.putString("com.amazon.dcp.sso.token.device.deviceserialname", str);
    }

    public void setDeviceType(String str) {
        this.l.putString("com.amazon.dcp.sso.token.devicedevicetype", str);
    }

    public void setDirectedId(String str) {
        this.l.putString("com.amazon.dcp.sso.property.account.acctId", str);
    }

    public void a(Map<String, Map<String, String>> map) {
        String k = kg.k(map);
        if (k != null) {
            this.l.putString(AccountConstants.KEY_DEVICE_CREDENTIALS, k);
        }
    }

    public Bundle b() {
        return (Bundle) this.l.clone();
    }

    public Bundle c() {
        d();
        return b();
    }

    public void d() {
        if (this.l.containsKey("com.amazon.dcp.sso.property.username")) {
            if (this.l.containsKey("com.amazon.dcp.sso.property.firstname")) {
                if (this.l.containsKey("com.amazon.dcp.sso.property.devicename")) {
                    if (this.l.containsKey(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN)) {
                        if (this.l.containsKey(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY)) {
                            if (this.l.containsKey("com.amazon.dcp.sso.token.device.deviceserialname")) {
                                if (!this.l.containsKey("com.amazon.dcp.sso.token.devicedevicetype")) {
                                    throw new IllegalArgumentException("UserData is invalid because the device type has not been set");
                                }
                                return;
                            }
                            throw new IllegalArgumentException("UserData is invalid because the serial number has not been set");
                        }
                        io.gE();
                        throw new IllegalArgumentException("UserData privatekey is invalid because it is not fulfilled on DMS, please contact DMS to fulfill your device.");
                    }
                    io.gE();
                    throw new IllegalArgumentException("UserData ADPtoken is invalid because it is not fulfilled on DMS, please contact DMS to fulfill your device.");
                }
                io.gE();
                throw new IllegalArgumentException("UserData devicename is invalid because it is not fulfilled on DMS, please contact DMS to fulfill your device.");
            }
            io.gE();
            throw new IllegalArgumentException("UserData firstname is invalid because it is not fulfilled on DMS, please contact DMS to fulfill your device.");
        }
        io.gE();
        throw new IllegalArgumentException("UserData username is invalid because it is not fulfilled on DMS, please contact DMS to fulfill your device.");
    }

    public void a(JSONArray jSONArray) {
        this.l.putString(AccountConstants.KEY_WEBSITE_COOKIES_JSON_ARRAY, jSONArray.toString());
    }

    public void c(int i) {
        this.l.putString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT, Integer.toString(i));
    }
}
