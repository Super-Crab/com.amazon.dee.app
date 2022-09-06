package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class hc {
    private AuthEndpointErrorParser aU = new AuthEndpointErrorParser();
    private Context mContext;
    private ip pD;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class a {
        private final String pJ;
        private final int pK;

        /* synthetic */ a(String str, int i, byte b) {
            this(str, i);
        }

        public int fV() {
            return this.pK;
        }

        public String getAccessToken() {
            return this.pJ;
        }

        public boolean isValid() {
            return !TextUtils.isEmpty(this.pJ) && this.pK > 0;
        }

        private a(String str, int i) {
            this.pJ = str;
            this.pK = i;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class b {
        private final String mId;
        private final String pJ;

        b(String str, String str2) {
            this.pJ = str;
            this.mId = str2;
        }

        public String fW() {
            return this.pJ;
        }

        public boolean isValid() {
            return !TextUtils.isEmpty(this.pJ);
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class c {
        private final String bk;
        b pL;
        a pM;
        b pN;
        a pO;

        /* synthetic */ c(String str, byte b) {
            this(str);
        }

        public a fX() {
            return this.pO;
        }

        public b fY() {
            return this.pN;
        }

        public a fZ() {
            return this.pM;
        }

        public b ga() {
            return this.pL;
        }

        public String getDeviceType() {
            return this.bk;
        }

        private c(String str) {
            this.bk = str;
        }
    }

    public hc(Context context) {
        this.mContext = context;
        this.pD = new ip(context);
    }

    public JSONObject a(@NonNull String str, @NonNull Map<String, String> map, String str2, @NonNull Map<String, String> map2, @NonNull String str3, Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("code", str3);
        if (bundle != null && bundle.getBoolean("authorizationCode")) {
            io.dm("PandaOAuthUpgradeRequestHelper");
            jSONObject2.put(AccountConstants.KEY_CODE_VERIFIER, bundle.getString(AccountConstants.KEY_CODE_VERIFIER));
            jSONObject2.put("code_algorithm", bundle.getString("code_challenge_method"));
            jSONObject2.put(AccountConstants.KEY_CLIENT_DOMAIN, bundle.getString(AccountConstants.KEY_CLIENT_DOMAIN));
            jSONObject2.put("client_id", bundle.getString("client_id"));
            jSONObject2.put("code_type", "authorizationCode");
        }
        JSONArray jSONArray = new JSONArray();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            JSONObject jSONObject3 = new JSONObject();
            if (!TextUtils.isEmpty(str2)) {
                String str4 = map2.get(key);
                if (!TextUtils.isEmpty(str4)) {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("token", str4);
                    jSONObject3.put("actor_refresh_token", jSONObject4);
                }
            }
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("token", value);
            jSONObject3.put("account_refresh_token", jSONObject5);
            jSONObject3.put("device_type", key);
            jSONArray.put(jSONObject3);
        }
        if (!TextUtils.isEmpty(str2)) {
            jSONObject.put("actor_id", str2);
        }
        jSONObject.put("account_id", str);
        jSONObject.put("upgrade_source", jSONObject2);
        jSONObject.put("device_tokens", jSONArray);
        jSONObject.put("app_name", this.mContext.getPackageName());
        jSONObject.put("app_version", hv.gt());
        jSONObject.put("map_version", this.pD.gG());
        io.a("Upgrade token body:" + jSONObject.toString(), new Object[0]);
        return jSONObject;
    }

    public List<c> q(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = jSONObject.getJSONArray("device_tokens");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            c cVar = new c(jSONObject2.getString("device_type"), (byte) 0);
            JSONObject optJSONObject = jSONObject2.optJSONObject("actor_access_token");
            if (optJSONObject != null) {
                cVar.pO = new a(optJSONObject.optString("token"), optJSONObject.optInt("expires_in"), (byte) 0);
            }
            JSONObject optJSONObject2 = jSONObject2.optJSONObject("actor_refresh_token");
            if (optJSONObject2 != null) {
                cVar.pN = new b(optJSONObject2.optString("token"), optJSONObject2.optString("actor_id"));
            }
            JSONObject optJSONObject3 = jSONObject2.optJSONObject("account_access_token");
            if (optJSONObject3 != null) {
                cVar.pM = new a(optJSONObject3.optString("token"), optJSONObject3.optInt("expires_in"), (byte) 0);
            }
            JSONObject optJSONObject4 = jSONObject2.optJSONObject("account_refresh_token");
            if (optJSONObject4 != null) {
                cVar.pL = new b(optJSONObject4.optString("token"), optJSONObject4.optString("account_id"));
            }
            arrayList.add(cVar);
        }
        return arrayList;
    }

    public AuthEndpointErrorParser.a r(JSONObject jSONObject) {
        if (jSONObject == null) {
            io.dm("PandaOAuthUpgradeRequestHelper");
            return null;
        }
        return this.aU.g(jSONObject);
    }

    public boolean a(Integer num) {
        return AuthEndpointErrorParser.a(num);
    }
}
