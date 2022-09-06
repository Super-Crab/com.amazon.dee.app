package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.dee.app.BuildConfig;
import com.amazon.identity.auth.device.framework.RetryLogic;
import com.amazon.identity.kcpsdk.common.HttpVerb;
import com.amazon.identity.kcpsdk.common.WebProtocol;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class md {
    private static final String TAG = "com.amazon.identity.auth.device.md";
    private String sk;
    private String uQ;
    private String uR;
    private String uS;
    private String uT;
    private boolean uW;
    private String uX;
    private String uP = "http";
    private HttpVerb uU = HttpVerb.HttpVerbGet;
    private final Map<String, String> hQ = new HashMap();
    private final List<String> uO = new ArrayList();
    private byte[] uV = new byte[0];

    private boolean eM(String str) {
        if (str != null && !str.equals("")) {
            try {
                URI uri = new URI(str);
                this.uP = uri.getScheme();
                this.uQ = uri.getHost();
                int port = uri.getPort();
                if (port != -1) {
                    this.uR = Integer.toString(port);
                } else {
                    this.uR = null;
                }
                this.uS = uri.getRawPath();
                if (this.uS != null && !"".equals(this.uS) && !this.uS.startsWith("/")) {
                    this.uS = "/" + this.uS;
                }
                this.uT = uri.getRawQuery();
                return true;
            } catch (URISyntaxException e) {
                String str2 = TAG;
                io.e(str2, "tryToParseUrl: URL is malformed: " + e.getMessage());
            }
        }
        return false;
    }

    public static boolean isValidUrl(String str) {
        return new md().eM(str);
    }

    public HttpURLConnection a(RetryLogic retryLogic, Context context, ej ejVar) throws IOException {
        return dy.a(new URL(getUrl()), retryLogic, ejVar, context);
    }

    public void aA(String str, String str2) {
        if (str != null && !"".equals(str) && str2 != null) {
            if (this.uT != null) {
                this.uT = GeneratedOutlineSupport1.outline91(new StringBuilder(), this.uT, WebConstants.UriConstants.AMPERSAND_KEY);
            } else {
                this.uT = "";
            }
            try {
                this.uT += String.format("%s=%s", URLEncoder.encode(str, "UTF-8"), URLEncoder.encode(str2, "UTF-8"));
                return;
            } catch (UnsupportedEncodingException e) {
                io.e(TAG, "addQueryParameter: Could not add query parameter because of UnsupportedEncodingException: " + e.getMessage());
                return;
            }
        }
        io.e(TAG, "addQueryParameter: could not add query parameter because the supplied arguments are invalid (null or empty name or null value).");
    }

    public boolean dM(String str) {
        if (!eM(str)) {
            io.e(TAG, "setUrl: url was malformed. Cannot be set.");
            return false;
        }
        return true;
    }

    public String eK(String str) {
        if (str == null) {
            return null;
        }
        return this.hQ.get(str.toLowerCase(Locale.US));
    }

    public void eL(String str) {
        try {
            j(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            String str2 = TAG;
            io.e(str2, "setBody: UnsupportedEncodingException error: " + e.getMessage());
        }
    }

    public String getUrl() {
        StringBuilder sb = new StringBuilder();
        String str = this.uP;
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append("://");
        String str2 = this.uQ;
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        if (this.uR != null) {
            sb.append(":" + this.uR);
        }
        sb.append(iy());
        this.sk = sb.toString();
        return this.sk;
    }

    public HttpVerb iA() {
        return this.uU;
    }

    public String iB() {
        HttpVerb httpVerb = this.uU;
        if (httpVerb != null) {
            return httpVerb.getValue();
        }
        return null;
    }

    public int iC() {
        return this.uO.size();
    }

    public byte[] iD() {
        return this.uV;
    }

    public boolean iE() {
        return this.uW;
    }

    public void iF() {
        this.uR = Integer.toString(443);
    }

    public String iy() {
        String str = this.uS;
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        if (this.uT != null) {
            str2 = WebConstants.UriConstants.QUESTIONMARK_KEY + this.uT;
        }
        this.uX = GeneratedOutlineSupport1.outline72(str, str2);
        return this.uX;
    }

    public String iz() {
        String str = this.uT;
        return str == null ? "" : str;
    }

    public void j(byte[] bArr) {
        this.uV = bArr;
    }

    public void m(boolean z) {
        this.uW = z;
    }

    public String o(int i) {
        if (i >= 0 && i < iC()) {
            return this.uO.get(i);
        }
        io.e(TAG, "getHeader: index is out of range");
        return null;
    }

    public String p(int i) {
        return eK(o(i));
    }

    public void setHeader(String str, String str2) {
        if (str == null || "".equals(str)) {
            io.w(TAG, "setHeader: failed because the given header name was null or empty.");
        } else if (str2 == null) {
            this.uO.remove(str);
            this.hQ.remove(str.toLowerCase(Locale.US));
        } else {
            String replace = str2.replace("\n", "\n ");
            this.uO.add(str);
            this.hQ.put(str.toLowerCase(Locale.US), replace);
        }
    }

    public void setHost(String str) {
        this.uQ = str;
    }

    public void setPath(String str) {
        if (!str.equals("") && !str.startsWith("/")) {
            str = "/".concat(str);
        }
        try {
            this.uS = new URI("http", BuildConfig.AUTH_HOST, str, null).getRawPath();
        } catch (URISyntaxException e) {
            String str2 = TAG;
            io.e(str2, "setPath: Could not set path because of URISyntaxException: " + e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    public void a(WebProtocol webProtocol) {
        if (webProtocol != null) {
            this.uP = webProtocol.getValue();
        } else {
            this.uP = null;
        }
    }

    public void a(HttpVerb httpVerb) {
        this.uU = httpVerb;
    }
}
