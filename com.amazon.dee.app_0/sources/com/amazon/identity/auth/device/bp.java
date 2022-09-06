package com.amazon.identity.auth.device;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class bp {

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        public final String hz;
        public final String mErrorCode;
        public final String mErrorMessage;

        a(String str, String str2, String str3) {
            this.mErrorCode = TextUtils.isEmpty(str) ? "Unrecognized" : str;
            this.mErrorMessage = TextUtils.isEmpty(str2) ? "Unrecognized" : str2;
            this.hz = TextUtils.isEmpty(str3) ? "No error index" : str3;
        }
    }

    public static a d(JSONObject jSONObject) {
        String str;
        String str2;
        String str3 = null;
        try {
            str2 = jSONObject.getString("error");
            try {
                String string = jSONObject.getString("error_description");
                try {
                    str3 = jSONObject.getString("error_index");
                    return new a(str2, string, str3);
                } catch (JSONException unused) {
                    str = str3;
                    str3 = string;
                    return new a(str2, str3, str);
                }
            } catch (JSONException unused2) {
                str = null;
            }
        } catch (JSONException unused3) {
            str = null;
            str2 = null;
        }
    }
}
