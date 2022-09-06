package com.amazon.identity.auth.device;

import android.content.Context;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class jl {
    private static final String TAG = "com.amazon.identity.auth.device.jl";

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static class a extends UrlQuerySanitizer {
        private String rJ;

        public a(String str) {
            this.rJ = str;
        }

        @Override // android.net.UrlQuerySanitizer
        protected void addSanitizedEntry(String str, String str2) {
            if (str == null) {
                io.w(jl.TAG, "ignoring <null> named parameter to a Sanitizer", new IllegalArgumentException());
            } else if (str2 == null) {
                String str3 = jl.TAG;
                io.w(str3, "ignoring <null> value to parameter '" + str + "' to a Sanitizer", new IllegalArgumentException());
            } else {
                String trim = str.trim();
                String trim2 = str2.trim();
                if (!trim.equals(str) || !trim2.equals(str2)) {
                    String str4 = jl.TAG;
                    io.i(str4, "Parameter or value was trimmed on the parameter " + trim + " (" + trim.equals(str) + ", " + trim2.equals(str2) + "). MAP trims the query parameter keys and values in the URL, however auth portal does not accept non-trimmed parameters. Please consider fixing the auth portal parameters passed to MAP.");
                }
                super.addSanitizedEntry(trim, trim2);
            }
        }

        @Override // android.net.UrlQuerySanitizer
        public String unescape(String str) {
            if (str == null) {
                io.w(jl.TAG, "Unescaping <null> string");
                return null;
            }
            try {
                return URLDecoder.decode(str, this.rJ);
            } catch (UnsupportedEncodingException e) {
                String str2 = jl.TAG;
                io.e(str2, this.rJ + " is not supported. Using decoder without encoding.", e);
                return super.unescape(str);
            }
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class b {
        private static final String TAG = "com.amazon.identity.auth.device.jl$b";
        private Uri.Builder rK = new Uri.Builder();

        public b aI(Context context) {
            av("app_name", context.getPackageName());
            av("app_version", hv.gt());
            av("di.sdk.version", ip.gF());
            return this;
        }

        public b av(String str, String str2) {
            if (str2 == null) {
                String str3 = TAG;
                io.w(str3, "Parameter '" + str + "' has <null> value, will not append parameter to query string.", new IllegalArgumentException());
                return this;
            }
            try {
                this.rK.appendQueryParameter(URLEncoder.encode(str, "UTF-8"), URLEncoder.encode(str2, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                io.wtf(TAG, "UTF-8 is not supported. This should not happen according to http://developer.android.com/reference/java/nio/charset/Charset.html", e);
            }
            return this;
        }

        public b aw(String str, String str2) {
            if (str == null) {
                io.w(TAG, "Parameter name is <null>, will not append parameter to query string.", new IllegalArgumentException());
                return this;
            } else if (str2 == null) {
                String str3 = TAG;
                io.w(str3, "Parameter '" + str + "' has <null> value, will not append parameter to query string.", new IllegalArgumentException());
                return this;
            } else {
                this.rK.appendQueryParameter(str, str2);
                return this;
            }
        }

        public String gU() {
            return this.rK.build().getQuery();
        }
    }

    private jl() {
    }

    public static UrlQuerySanitizer dI(String str) {
        a aVar = new a("UTF-8");
        aVar.setAllowUnregisteredParamaters(true);
        aVar.setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.getAllButNulLegal());
        if (str != null) {
            aVar.parseUrl(str);
        } else {
            io.w(TAG, "Attempted to retrive a Sanitizer for a <null> url (string).");
        }
        return aVar;
    }

    public static URL dJ(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            io.e(TAG, "Failed to construct URL object.", e);
            return null;
        }
    }

    public static b gT() {
        return new b();
    }
}
