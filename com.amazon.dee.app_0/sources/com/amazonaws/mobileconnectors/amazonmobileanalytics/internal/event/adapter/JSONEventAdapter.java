package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.adapter;

import android.util.Log;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.voice.tta.Constants;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.idresolver.Id;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.AndroidAppDetails;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.AndroidDeviceDetails;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.JSONBuilder;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.SDKInfo;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.ClientContext;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.DefaultEvent;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes13.dex */
public class JSONEventAdapter implements EventAdapter<JSONObject> {
    private static final String TAG = "JSONEventAdapter";

    public String toString() {
        return new JSONBuilder(this).toString();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.adapter.EventAdapter
    public JSONObject translateFromEvent(InternalEvent internalEvent) {
        if (internalEvent == null) {
            Log.w(TAG, "The Event provided was null");
            return null;
        }
        JSONObject jSONObject = internalEvent.toJSONObject();
        if (jSONObject.has("class")) {
            jSONObject.remove("class");
        }
        if (jSONObject.has("hashCode")) {
            jSONObject.remove("hashCode");
        }
        return jSONObject;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.adapter.EventAdapter
    public InternalEvent translateToEvent(JSONObject jSONObject) throws JSONException {
        String str;
        Long l;
        Long l2;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        AndroidAppDetails androidAppDetails = new AndroidAppDetails(jSONObject.optString("app_package_name"), jSONObject.optString("app_version_code"), jSONObject.optString("app_version_name"), jSONObject.optString("app_title"), jSONObject.optString(ClientContext.APP_ID_KEY));
        SDKInfo sDKInfo = new SDKInfo(jSONObject.optString("sdk_version"), jSONObject.optString("sdk_name"));
        AndroidDeviceDetails androidDeviceDetails = new AndroidDeviceDetails(jSONObject.optString(AMPDInformationProvider.DEVICE_CARRIER_KEY));
        String string = jSONObject.getString("event_type");
        Long valueOf = Long.valueOf(jSONObject.getLong("timestamp"));
        Id valueOf2 = Id.valueOf(jSONObject.getString("unique_id"));
        JSONObject jSONObject2 = jSONObject.getJSONObject("session");
        Long l3 = null;
        if (jSONObject2 != null) {
            String string2 = jSONObject2.getString("id");
            Long valueOf3 = Long.valueOf(jSONObject2.getLong(Constants.IntentParameters.START_TIMESTAMP));
            l2 = Long.valueOf(jSONObject2.optLong("stopTimestamp"));
            l = Long.valueOf(jSONObject2.optLong("duration"));
            str = string2;
            l3 = valueOf3;
        } else {
            str = "";
            l = null;
            l2 = null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("attributes");
        if (optJSONObject != null) {
            for (Iterator keys = optJSONObject.keys(); keys.hasNext(); keys = keys) {
                String str2 = (String) keys.next();
                hashMap.put(str2, optJSONObject.optString(str2));
            }
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("metrics");
        if (optJSONObject2 != null) {
            Iterator keys2 = optJSONObject2.keys();
            while (keys2.hasNext()) {
                String str3 = (String) keys2.next();
                try {
                    hashMap2.put(str3, Double.valueOf(optJSONObject2.getDouble(str3)));
                } catch (JSONException e) {
                    Log.e(TAG, "Failed to convert metric back to double from JSON value", e);
                    optJSONObject2 = optJSONObject2;
                }
            }
        }
        return DefaultEvent.newInstance(string, hashMap, hashMap2, sDKInfo, str, l3, l2, l, valueOf.longValue(), valueOf2, androidAppDetails, androidDeviceDetails);
    }
}
