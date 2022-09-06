package com.amazon.identity.auth.device;

import android.text.TextUtils;
import com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse;
import com.amazon.identity.auth.map.device.AccountManagerConstants;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class lr {
    public static final String TAG = "com.amazon.identity.auth.device.lr";
    private String mAccessToken;
    private String ne;
    private int uu;
    private Map<String, JSONArray> uv = new HashMap();
    private String uw;

    private lr() {
    }

    public static lr eF(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            lr lrVar = new lr();
            LinkedList<String> linkedList = new LinkedList();
            JSONObject optJSONObject = jSONObject.optJSONObject(HttpClientModule.ElementsRequestKey.BEARER);
            if (optJSONObject == null) {
                linkedList.add(HttpClientModule.ElementsRequestKey.BEARER);
            } else {
                int optInt = optJSONObject.optInt("expires_in", -1);
                if (optInt < 0) {
                    linkedList.add("bearer.expires_in");
                } else {
                    lrVar.uu = optInt;
                }
                String optString = optJSONObject.optString(AbstractJSONTokenResponse.ACCESS_TOKEN);
                if (TextUtils.isEmpty(optString)) {
                    linkedList.add("bearer.access_token");
                } else {
                    lrVar.mAccessToken = optString;
                }
                String optString2 = optJSONObject.optString(AbstractJSONTokenResponse.REFRESH_TOKEN);
                if (TextUtils.isEmpty(optString2)) {
                    linkedList.add("bearer.refresh_token");
                } else {
                    lrVar.ne = optString2;
                }
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("website_cookies");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                if (optJSONArray.length() > 1) {
                    io.w(TAG, "Panda returned more than one domain's cookies. Using first in list.");
                }
                for (int i = 0; i <= 0; i++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                    if (optJSONObject2 == null) {
                        linkedList.add("website_cookies.cookiesObject".concat(String.valueOf(i)));
                    } else {
                        String optString3 = optJSONObject2.optString("domain");
                        if (TextUtils.isEmpty(optString3)) {
                            linkedList.add("website_cookies.cookiesObject" + i + ".domain");
                        } else {
                            lrVar.uw = optString3;
                            JSONArray optJSONArray2 = optJSONObject2.optJSONArray(AccountManagerConstants.GetCookiesParams.COOKIES);
                            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                lrVar.uv.put(optString3, optJSONArray2);
                            } else {
                                linkedList.add("website_cookies.cookiesObject" + i + ".cookiesArray");
                            }
                        }
                    }
                }
            } else {
                linkedList.add("website_cookies");
            }
            if (!linkedList.isEmpty()) {
                io.w(TAG, "Missing values from Panda: " + TextUtils.join(",", linkedList));
                for (String str2 : linkedList) {
                    mq.b("ExchangeTokenResponseMissing:".concat(String.valueOf(str2)), new String[0]);
                }
            }
            return lrVar;
        } catch (JSONException e) {
            io.e(TAG, "Invalid JSON from Panda: " + e.getMessage());
            return null;
        }
    }

    public String fW() {
        return this.ne;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public int im() {
        return this.uu;
    }

    public JSONArray in() {
        String str = this.uw;
        if (str == null) {
            return new JSONArray();
        }
        JSONArray jSONArray = this.uv.get(str);
        return jSONArray == null ? new JSONArray() : jSONArray;
    }
}
