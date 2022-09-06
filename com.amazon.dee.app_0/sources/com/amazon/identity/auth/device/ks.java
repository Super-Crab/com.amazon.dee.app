package com.amazon.identity.auth.device;

import android.util.Base64;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class ks {
    private static final String TAG = "com.amazon.identity.auth.device.ks";
    private static kt sv;

    public static synchronized ks hw() {
        synchronized (ks.class) {
            if (sv == null) {
                io.i(TAG, "Jwt Signer Factory is null");
                return null;
            }
            return sv.hx();
        }
    }

    static String t(JSONObject jSONObject) {
        try {
            return Base64.encodeToString(jSONObject.toString().getBytes("UTF-8"), 11);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public final String d(String str, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (hu() != null) {
                str = hu();
            }
            jSONObject2.put("typ", str);
            String str2 = t(jSONObject2) + "." + t(jSONObject);
            try {
                str2.getBytes("UTF-8");
                byte[] ht = ht();
                StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str2, ".");
                outline113.append(Base64.encodeToString(ht, 11));
                return outline113.toString();
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        } catch (JSONException e2) {
            throw new RuntimeException(e2);
        }
    }

    abstract byte[] ht();

    abstract String hu();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean hv();
}
