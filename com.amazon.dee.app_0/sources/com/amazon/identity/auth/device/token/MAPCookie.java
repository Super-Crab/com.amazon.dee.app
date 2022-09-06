package com.amazon.identity.auth.device.token;

import android.text.TextUtils;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.identity.auth.device.ia;
import com.amazon.identity.auth.device.io;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class MAPCookie implements Serializable {
    private static final String gM = MAPCookie.class.getName();
    private static final long serialVersionUID = 551200964665L;
    private final Map<String, String> mCookieData = new HashMap();
    private int[] mPorts;

    public MAPCookie(String str, String str2, String str3, String str4, boolean z) {
        this.mCookieData.put(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_NAME, str);
        this.mCookieData.put(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_VALUE, str2);
        this.mCookieData.put("DirectedId", str4);
        this.mCookieData.put(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_DOMAIN, str3);
        setSecure(z);
        fN();
    }

    private void fN() {
        io.a("Creating Cookie: %s , domain: %s , for id: %s from cookie data %s", getName(), getDomain(), getDirectedId(), getValue());
    }

    public String Y(String str, String str2) {
        return this.mCookieData.put(str, str2);
    }

    public void cI(String str) {
        Y("Expires", str);
    }

    public String fO() {
        StringBuilder sb = new StringBuilder(getName().trim());
        sb.append(Config.Compare.EQUAL_TO);
        sb.append(getValue().trim());
        sb.append("; path=");
        String path = getPath();
        if (TextUtils.isEmpty(path)) {
            sb.append("/");
        } else {
            sb.append(path);
        }
        sb.append("; domain=" + getDomain().trim());
        if (isSecure()) {
            sb.append("; secure");
        }
        if (isHttpOnly()) {
            sb.append("; httponly");
        }
        Date expiryDate = getExpiryDate();
        if (expiryDate != null) {
            sb.append("; expires=");
            if (expiryDate.before(Calendar.getInstance().getTime())) {
                io.a("Cookie %s expired : ", getName(), expiryDate.toGMTString());
            }
            sb.append(ia.gy().format(expiryDate));
        }
        return sb.toString();
    }

    public String getAttribute(String str) {
        return this.mCookieData.get(str);
    }

    public String getDirectedId() {
        return getAttribute("DirectedId");
    }

    public String getDomain() {
        return getAttribute(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_DOMAIN);
    }

    public Date getExpiryDate() {
        String attribute = getAttribute("Expires");
        if (attribute != null) {
            try {
                return ia.gy().parse(attribute);
            } catch (ParseException unused) {
                io.dn(gM);
            }
        }
        return null;
    }

    public String getName() {
        return getAttribute(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_NAME);
    }

    public String getPath() {
        return getAttribute(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_PATH);
    }

    public String getValue() {
        return getAttribute(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_VALUE);
    }

    public boolean isHttpOnly() {
        String attribute = getAttribute(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_HTTP_ONLY);
        if (TextUtils.isEmpty(attribute)) {
            return false;
        }
        return Boolean.parseBoolean(attribute);
    }

    public boolean isSecure() {
        return Boolean.parseBoolean(getAttribute(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_SECURE));
    }

    public void setDomain(String str) {
        this.mCookieData.put(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_DOMAIN, str);
    }

    public void setHttpOnly(boolean z) {
        this.mCookieData.put(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_HTTP_ONLY, Boolean.toString(z));
    }

    public void setPath(String str) {
        Y(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_PATH, str);
    }

    protected void setSecure(boolean z) {
        Y(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_SECURE, Boolean.toString(z));
    }

    public MAPCookie(String str, String str2, String str3, String str4, String str5, String str6, boolean z, boolean z2) {
        this.mCookieData.put(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_NAME, str);
        this.mCookieData.put(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_VALUE, str2);
        this.mCookieData.put(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_DOMAIN, str3);
        this.mCookieData.put("Expires", str4);
        this.mCookieData.put(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_PATH, str5);
        this.mCookieData.put("DirectedId", str6);
        setSecure(z);
        setHttpOnly(z2);
        fN();
    }
}
