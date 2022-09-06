package com.amazon.identity.auth.map.device.token;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.Time;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;
import org.apache.http.cookie.Cookie;
/* loaded from: classes12.dex */
public class MAPCookie implements Cookie, Token, Serializable {
    private static final String COOKIE_ATTRIBUTE_SEPERATOR = ";";
    public static final String COOKIE_DATE_FORMAT = "dd MMM yyyy kk:mm:ss z";
    private static final String COOKIE_NAME_VALUE_SEPERATOR = "=";
    private static final String DOMAIN_PREFIX = "www";
    private static final String DOT = ".";
    private static final String EMPTY_COOKIE = "";
    private static final String GMT = "GMT";
    public static final String KEY_COMMENT = "Comment";
    public static final String KEY_COMMENT_URL = "CommentUrl";
    public static final String KEY_DIRECTED_ID = "DirectedId";
    public static final String KEY_DOMAIN = "Domain";
    public static final String KEY_EXPIRES = "Expires";
    public static final String KEY_HTTP_ONLY = "HttpOnly";
    public static final String KEY_NAME = "Name";
    public static final String KEY_PATH = "Path";
    public static final String KEY_PERSISTANT = "Persistant";
    public static final String KEY_SECURE = "Secure";
    public static final String KEY_VALUE = "Value";
    public static final String KEY_VERSION = "Version";
    private static final String LOG_TAG = MAPCookie.class.getName();
    public static final int NO_VERSION = -1;
    private static final long serialVersionUID = 551200964665L;
    private final Map<String, String> _cookieData;
    private final transient Time _localCreationTimestamp;
    private int[] _ports;

    /* loaded from: classes12.dex */
    private class CookieAttribute {
        private static final String DOMAIN = "; domain=";
        private static final String EXPIRES = "; expires=";
        private static final String HTTP_ONLY = "; httponly";
        private static final String PATH = "; path=/";
        private static final String SECURE = "; secure";

        private CookieAttribute() {
        }
    }

    public MAPCookie(String str, String str2, String str3, String str4, boolean z) {
        this._localCreationTimestamp = new Time();
        this._cookieData = new HashMap();
        this._cookieData.put(KEY_NAME, str);
        this._cookieData.put(KEY_VALUE, str2);
        this._cookieData.put("DirectedId", str4);
        this._cookieData.put(KEY_DOMAIN, str3);
        setSecure(z);
        logCookie();
    }

    public static void clearCookieInCookieManager(Context context, Cookie cookie, String str, String str2) throws AuthError {
        CookieSyncManager cookieSyncManager;
        try {
            cookieSyncManager = CookieSyncManager.getInstance();
        } catch (IllegalStateException unused) {
            MAPLog.i(LOG_TAG, "CookieSyncManager not yet created... creating");
            CookieSyncManager.createInstance(context);
            cookieSyncManager = CookieSyncManager.getInstance();
        }
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieSyncManager.sync();
        cookieManager.setCookie(str, getClearSetCookieHeader(cookie));
        cookieSyncManager.sync();
    }

    public static String[] extractCookieStringArray(List<Cookie> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (Cookie cookie : list) {
                arrayList.add(getSetCookieHeader(cookie));
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        return null;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(COOKIE_DATE_FORMAT, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(GMT));
        return simpleDateFormat.format(date);
    }

    private static final String getClearSetCookieHeader(Cookie cookie) {
        StringBuilder sb = new StringBuilder(cookie.getName().trim());
        sb.append("=");
        sb.append("");
        sb.append("; path=/");
        sb.append("; domain=" + cookie.getDomain().trim());
        if (cookie.isSecure()) {
            sb.append("; secure");
        }
        Date expiryDate = cookie.getExpiryDate();
        if (expiryDate != null) {
            sb.append("; expires=");
            if (expiryDate.before(Calendar.getInstance().getTime())) {
                String str = LOG_TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cookie ");
                outline107.append(cookie.getName());
                outline107.append(" expired : ");
                outline107.append(expiryDate);
                MAPLog.i(str, outline107.toString());
            }
            sb.append(formatDate(expiryDate));
        }
        return sb.toString();
    }

    private static Date getCookieExpireDate(String str) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(COOKIE_DATE_FORMAT, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(GMT));
        return simpleDateFormat.parse(str);
    }

    public static List<Cookie> getCookiesFromCookieManager(Context context, String str, String str2) throws AuthError {
        String cookiesStringFromCookieManager = getCookiesStringFromCookieManager(context, str);
        String str3 = LOG_TAG;
        String outline72 = GeneratedOutlineSupport1.outline72("Extracting cookie list for domain=", str);
        MAPLog.pii(str3, outline72, "directedId=" + str2);
        ArrayList arrayList = new ArrayList();
        if (cookiesStringFromCookieManager != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(cookiesStringFromCookieManager, COOKIE_ATTRIBUTE_SEPERATOR);
            while (stringTokenizer.hasMoreTokens()) {
                StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken().trim(), "=");
                if (stringTokenizer2.hasMoreTokens()) {
                    arrayList.add(new MAPCookie(stringTokenizer2.nextToken(), stringTokenizer2.hasMoreTokens() ? stringTokenizer2.nextToken() : "", str, str2, false));
                }
            }
        } else {
            String str4 = LOG_TAG;
            MAPLog.i(str4, "No cookies in Cookie manager for " + str);
        }
        return arrayList;
    }

    public static String getCookiesStringFromCookieManager(Context context, String str) {
        CookieSyncManager cookieSyncManager;
        try {
            cookieSyncManager = CookieSyncManager.getInstance();
        } catch (IllegalStateException unused) {
            MAPLog.i(LOG_TAG, "CookieSyncManager not yet created... creating");
            CookieSyncManager.createInstance(context);
            cookieSyncManager = CookieSyncManager.getInstance();
        }
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieSyncManager.sync();
        if (str.startsWith(".")) {
            str = GeneratedOutlineSupport1.outline72(DOMAIN_PREFIX, str);
        }
        String cookie = cookieManager.getCookie(str);
        String str2 = LOG_TAG;
        MAPLog.i(str2, "Extracting cookies from CookieManager for domain=" + str);
        return cookie;
    }

    public static final String getSetCookieHeader(Cookie cookie) {
        StringBuilder sb = new StringBuilder(cookie.getName().trim());
        sb.append("=");
        sb.append(cookie.getValue().trim());
        sb.append("; path=/");
        sb.append("; domain=" + cookie.getDomain().trim());
        if (cookie.isSecure()) {
            sb.append("; secure");
        }
        Date expiryDate = cookie.getExpiryDate();
        if (expiryDate != null) {
            sb.append("; expires=");
            if (expiryDate.before(Calendar.getInstance().getTime())) {
                String str = LOG_TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cookie ");
                outline107.append(cookie.getName());
                outline107.append(" expired : ");
                outline107.append(expiryDate);
                MAPLog.i(str, outline107.toString());
            }
            sb.append(formatDate(expiryDate));
        }
        return sb.toString();
    }

    private void logCookie() {
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Creating Cookie from data. name=");
        outline107.append(getName());
        String sb = outline107.toString();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("domain:");
        outline1072.append(getDomain());
        outline1072.append(" directedId:");
        outline1072.append(getDirectedId());
        outline1072.append(" cookie:");
        outline1072.append(getValue());
        MAPLog.pii(str, sb, outline1072.toString());
    }

    public String getAttribute(String str) {
        return this._cookieData.get(str);
    }

    public String getComment() {
        return getAttribute(KEY_COMMENT);
    }

    public String getCommentURL() {
        return getAttribute(KEY_COMMENT_URL);
    }

    @Override // com.amazon.identity.auth.map.device.token.Token
    public Map<String, String> getData() {
        return this._cookieData;
    }

    @Override // com.amazon.identity.auth.map.device.token.Token
    public String getDirectedId() {
        return getAttribute("DirectedId");
    }

    public String getDomain() {
        return getAttribute(KEY_DOMAIN);
    }

    public Date getExpiryDate() {
        String attribute = getAttribute("Expires");
        if (attribute != null) {
            try {
                return getCookieExpireDate(attribute);
            } catch (ParseException e) {
                MAPLog.e(LOG_TAG, "Date parse error on MAP Cookie", e);
            }
        }
        return null;
    }

    @Override // com.amazon.identity.auth.map.device.token.Token
    public Time getLocalTimestamp() {
        return this._localCreationTimestamp;
    }

    public String getName() {
        return getAttribute(KEY_NAME);
    }

    public String getPath() {
        return getAttribute(KEY_PATH);
    }

    public int[] getPorts() {
        return this._ports;
    }

    @Override // com.amazon.identity.auth.map.device.token.Token
    public String getType() {
        return getName();
    }

    public String getValue() {
        return getAttribute(KEY_VALUE);
    }

    public int getVersion() {
        if (TextUtils.isEmpty(getAttribute("Version"))) {
            return -1;
        }
        return Integer.parseInt(getAttribute("Version"));
    }

    public boolean hasExpired() {
        return isExpired(Calendar.getInstance().getTime());
    }

    public boolean isExpired(Date date) {
        if (getExpiryDate() == null) {
            return false;
        }
        if (date == null) {
            date = Calendar.getInstance().getTime();
        }
        return getExpiryDate().before(date);
    }

    public boolean isHttpOnly() {
        String attribute = getAttribute(KEY_HTTP_ONLY);
        if (TextUtils.isEmpty(attribute)) {
            return false;
        }
        return Boolean.parseBoolean(attribute);
    }

    public boolean isPersistent() {
        return Boolean.parseBoolean(getAttribute(KEY_PERSISTANT));
    }

    public boolean isSecure() {
        return Boolean.parseBoolean(getAttribute(KEY_SECURE));
    }

    public String setAttribute(String str, String str2) {
        return this._cookieData.put(str, str2);
    }

    public void setExpiryDate(String str) {
        setAttribute("Expires", str);
    }

    public void setHttpOnly(boolean z) {
        this._cookieData.put(KEY_HTTP_ONLY, Boolean.toString(z));
    }

    public void setPath(String str) {
        setAttribute(KEY_PATH, str);
    }

    public void setPorts(int[] iArr) {
        this._ports = new int[iArr.length];
        System.arraycopy(iArr, 0, this._ports, 0, iArr.length);
    }

    protected void setSecure(boolean z) {
        setAttribute(KEY_SECURE, Boolean.toString(z));
    }

    public MAPCookie(Map<String, String> map) throws AuthError {
        this._localCreationTimestamp = new Time();
        this._cookieData = map;
        logCookie();
    }
}
