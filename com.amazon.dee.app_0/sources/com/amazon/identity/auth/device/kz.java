package com.amazon.identity.auth.device;

import android.text.TextUtils;
import com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse;
import com.amazon.identity.kcpsdk.auth.PandaError;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class kz<T> {
    private static final String TAG = "com.amazon.identity.auth.device.kz";

    protected abstract T b(kc kcVar) throws JSONException;

    protected abstract T b(PandaError pandaError);

    protected abstract T w(JSONObject jSONObject) throws JSONException;

    protected abstract T x(JSONObject jSONObject) throws JSONException;

    public T y(JSONObject jSONObject) {
        JSONObject jSONObject2;
        try {
            jSONObject2 = jSONObject.getJSONObject("response");
        } catch (JSONException unused) {
            io.e(TAG, "Panda Response is not correctly formatted.");
        }
        if (jSONObject2.has("success")) {
            return x(jSONObject2.getJSONObject("success"));
        }
        if (jSONObject2.has("error")) {
            String a = ik.a(ik.b(jSONObject2, "error"), "index", null);
            if (!TextUtils.isEmpty(a)) {
                io.e(TAG, "Received Panda error index when parsing the error response: ".concat(String.valueOf(a)));
                io.dm(TAG);
            }
            JSONObject jSONObject3 = jSONObject2.getJSONObject("error");
            String string = jSONObject.getString(AbstractJSONTokenResponse.REQUEST_ID);
            PandaError pandaError = PandaError.getPandaError(jSONObject3.getString("code"));
            if (pandaError == null) {
                return w(jSONObject3);
            }
            io.w(TAG, String.format("Panda Error:  %s. Request ID: %s", jSONObject3.toString(), string));
            return b(pandaError);
        } else if (jSONObject2.has("challenge")) {
            return b(kc.s(jSONObject2.getJSONObject("challenge")));
        } else {
            io.e(TAG, "Panda Response is not correctly formatted.");
            return b(PandaError.PandaErrorUnknown);
        }
    }
}
