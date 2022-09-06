package com.amazon.alexa.mobilytics.event.serializer.handlers;

import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mobilytics.configuration.DeviceConfiguration;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.google.common.base.Preconditions;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;
@Singleton
/* loaded from: classes9.dex */
public class DeviceDataHandler implements DataHandler {
    private static final String KEY = "device";
    private static final String TAG = Log.tag(DeviceDataHandler.class);
    private static final String TIMEZONE_KEY = "timezone";
    private final DeviceConfiguration deviceConfiguration;

    @Inject
    public DeviceDataHandler(@NonNull DeviceConfiguration deviceConfiguration) {
        this.deviceConfiguration = (DeviceConfiguration) Preconditions.checkNotNull(deviceConfiguration);
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.handlers.DataHandler
    @Nullable
    public Pair<String, JSONObject> process(@NonNull MobilyticsEvent mobilyticsEvent) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("deviceType", this.deviceConfiguration.deviceType());
        jSONObject.put("deviceId", this.deviceConfiguration.deviceSerialNumber());
        jSONObject.put(TIMEZONE_KEY, this.deviceConfiguration.timezone());
        jSONObject.put(MetricsConfiguration.PLATFORM, new JSONObject().put("name", this.deviceConfiguration.operatingSystemType()).put("version", this.deviceConfiguration.operatingSystemVersion()));
        jSONObject.put("make", this.deviceConfiguration.manufacturer()).put("model", this.deviceConfiguration.model()).put("screen", new JSONObject().put(EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_SCREEN_WIDTH, this.deviceConfiguration.screenWidth()).put("screenHeight", this.deviceConfiguration.screenHeight()).put("screenDensity", this.deviceConfiguration.screenDensity())).put("networkType", this.deviceConfiguration.networkType()).put("locale", new JSONObject().put("country", this.deviceConfiguration.country()).put("language", this.deviceConfiguration.language()));
        return Pair.create("device", jSONObject);
    }
}
