package com.amazon.alexa.drive.entertainment.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class PlayPayload {
    private static final String TAG = "PlayPayload";
    protected String deviceSerialNumber;
    protected String deviceType;
    protected String mediaOwnerCustomerId;
    protected String queueId;

    public PlayPayload(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                this.queueId = jSONObject.getString(EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_QUEUE_ID);
                this.deviceType = jSONObject.getString("deviceType");
                this.deviceSerialNumber = jSONObject.getString("deviceSerialNumber");
                this.mediaOwnerCustomerId = jSONObject.getString("mediaOwnerCustomerId");
            } catch (JSONException e) {
                String str = "PlayPayload() | JSONException: " + e;
            }
        }
    }

    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getMediaOwnerCustomerId() {
        return this.mediaOwnerCustomerId;
    }

    public String getQueueId() {
        return this.queueId;
    }

    public void setDeviceSerialNumber(String str) {
        this.deviceSerialNumber = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setMediaOwnerCustomerId(String str) {
        this.mediaOwnerCustomerId = str;
    }

    public void setQueueId(String str) {
        this.queueId = str;
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103(EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_QUEUE_ID);
        outline103.append(this.queueId);
        outline103.append("deviceType");
        outline103.append(this.deviceType);
        outline103.append("deviceSerialNumber");
        outline103.append(this.deviceSerialNumber);
        outline103.append("mediaOwnerCustomerId");
        outline103.append(this.mediaOwnerCustomerId);
        return outline103.toString();
    }
}
