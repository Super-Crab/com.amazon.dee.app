package com.amazon.alexa.audiopersonalization.components;

import amazon.speech.simclient.metrics.upl.UPLConstants;
import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class Tone {
    public final String channel;
    public final int duration;
    public final int fade;
    public final int frequency;
    public final String requestId;
    public final double volume;

    public Tone(String str, int i, double d, String str2, int i2, int i3) {
        this.requestId = str;
        this.frequency = i;
        this.volume = d > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR ? d * (-1.0d) : d;
        this.channel = str2;
        this.duration = i2;
        this.fade = i3;
    }

    public static Tone fromJSONObject(JSONObject jSONObject) throws JSONException {
        return new Tone(jSONObject.getString("requestId"), jSONObject.getInt(EventBusConstants.FREQUENCY_KEY), jSONObject.getDouble(EventBusConstants.VOLUME_KEY), jSONObject.getString("channel"), jSONObject.getInt("duration"), jSONObject.getInt(EventBusConstants.FADE_KEY));
    }

    public boolean isLeftChannelOn() {
        return this.channel.equals("left") || this.channel.equals(UPLConstants.Channel.BOTH);
    }

    public boolean isRightChannelOn() {
        return this.channel.equals("right") || this.channel.equals(UPLConstants.Channel.BOTH);
    }
}
