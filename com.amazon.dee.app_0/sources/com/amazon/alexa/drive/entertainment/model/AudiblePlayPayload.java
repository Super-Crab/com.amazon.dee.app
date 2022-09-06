package com.amazon.alexa.drive.entertainment.model;

import android.util.Log;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class AudiblePlayPayload extends PlayPayload {
    private static final String TAG = "AudiblePlayPayload";
    private String asin;

    public AudiblePlayPayload(JSONObject jSONObject) {
        super(null);
        try {
            this.asin = jSONObject.getString(EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_ASIN);
            this.deviceType = jSONObject.getString("deviceType");
            this.deviceSerialNumber = jSONObject.getString("deviceSerialNumber");
            this.mediaOwnerCustomerId = jSONObject.getString("mediaOwnerCustomerId");
        } catch (JSONException e) {
            String str = TAG;
            Log.e(str, "AudiblePlayPayload() | JSONException: " + e);
        }
    }

    public String getAsin() {
        return this.asin;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    @Override // com.amazon.alexa.drive.entertainment.model.PlayPayload
    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103(EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_ASIN);
        outline103.append(this.asin);
        outline103.append("deviceType");
        outline103.append(this.deviceType);
        outline103.append("deviceSerialNumber");
        outline103.append(this.deviceSerialNumber);
        outline103.append("mediaOwnerCustomerId");
        outline103.append(this.mediaOwnerCustomerId);
        return outline103.toString();
    }
}
