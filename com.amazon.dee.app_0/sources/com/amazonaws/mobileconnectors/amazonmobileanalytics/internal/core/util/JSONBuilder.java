package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util;

import org.json.JSONException;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes13.dex */
public class JSONBuilder implements JSONSerializable {
    private JSONObject json = new JSONObject();

    public JSONBuilder(Object obj) {
        if (obj != null) {
            withAttribute("class", obj.getClass().getName());
            withAttribute("hashCode", Integer.toHexString(obj.hashCode()));
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.JSONSerializable
    public JSONObject toJSONObject() {
        return this.json;
    }

    public String toString() {
        try {
            return this.json != null ? this.json.toString(4) : "";
        } catch (JSONException unused) {
            return this.json.toString();
        }
    }

    public JSONBuilder withAttribute(String str, Object obj) {
        if (obj instanceof JSONSerializable) {
            obj = ((JSONSerializable) obj).toJSONObject();
        }
        try {
            this.json.putOpt(str, obj);
        } catch (JSONException unused) {
        }
        return this;
    }
}
