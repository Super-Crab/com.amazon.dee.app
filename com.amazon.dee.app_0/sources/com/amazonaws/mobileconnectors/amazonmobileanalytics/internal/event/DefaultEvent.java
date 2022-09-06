package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event;

import android.util.Log;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.voice.tta.Constants;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.idresolver.Id;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.AppDetails;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.DeviceDetails;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.JSONBuilder;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.SDKInfo;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.ClientContext;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes13.dex */
public final class DefaultEvent implements InternalEvent {
    private static final String TAG = "DefaultEvent";
    private final AppDetails appDetails;
    private final DeviceDetails deviceDetails;
    private final String eventType;
    private final String sdkName;
    private final String sdkVersion;
    private final Long sessionDuration;
    private final String sessionId;
    private final Long sessionStart;
    private final Long sessionStop;
    private final Long timestamp;
    private final Id uniqueId;
    private final Map<String, String> attributes = new ConcurrentHashMap();
    private final Map<String, Double> metrics = new ConcurrentHashMap();

    DefaultEvent(String str, Map<String, String> map, Map<String, Double> map2, SDKInfo sDKInfo, String str2, long j, Long l, Long l2, long j2, Id id, AppDetails appDetails, DeviceDetails deviceDetails) {
        this.sdkName = sDKInfo.getName();
        this.sdkVersion = sDKInfo.getVersion();
        this.sessionId = str2;
        this.sessionStart = Long.valueOf(j);
        this.sessionStop = l;
        this.sessionDuration = l2;
        this.timestamp = Long.valueOf(j2);
        this.uniqueId = id;
        this.eventType = str;
        this.appDetails = appDetails;
        this.deviceDetails = deviceDetails;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                addAttribute(entry.getKey(), entry.getValue());
            }
        }
        if (map2 != null) {
            for (Map.Entry<String, Double> entry2 : map2.entrySet()) {
                addMetric(entry2.getKey(), entry2.getValue());
            }
        }
    }

    public static DefaultEvent createFromEvent(AnalyticsContext analyticsContext, String str, long j, InternalEvent internalEvent) {
        return new DefaultEvent(internalEvent.getEventType(), internalEvent.getAllAttributes(), internalEvent.getAllMetrics(), analyticsContext.getSDKInfo(), str, internalEvent.getSessionStart(), internalEvent.getSessionStop(), internalEvent.getSessionDuration(), j, analyticsContext.getUniqueId(), analyticsContext.getSystem().getAppDetails(), analyticsContext.getSystem().getDeviceDetails());
    }

    public static DefaultEvent newInstance(AnalyticsContext analyticsContext, String str, Long l, Long l2, Long l3, long j, String str2) {
        return new DefaultEvent(str2, null, null, analyticsContext.getSDKInfo(), str, l.longValue(), l2, l3, j, analyticsContext.getUniqueId(), analyticsContext.getSystem().getAppDetails(), analyticsContext.getSystem().getDeviceDetails());
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public void addAttribute(String str, String str2) {
        if (str == null) {
            return;
        }
        if (str2 != null) {
            this.attributes.put(str, str2);
        } else {
            this.attributes.remove(str);
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public void addMetric(String str, Double d) {
        if (str == null) {
            return;
        }
        if (d != null) {
            this.metrics.put(str, d);
        } else {
            this.metrics.remove(str);
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public ClientContext createClientContext(String str) {
        ClientContext.ClientContextBuilder clientContextBuilder = new ClientContext.ClientContextBuilder();
        clientContextBuilder.withAppPackageName(this.appDetails.packageName()).withAppVersionCode(this.appDetails.versionCode()).withAppVersionName(this.appDetails.versionName()).withLocale(this.deviceDetails.locale().toString()).withMake(this.deviceDetails.manufacturer()).withModel(this.deviceDetails.model()).withPlatformVersion(this.deviceDetails.platformVersion()).withUniqueId(this.uniqueId.getValue()).withAppTitle(this.appDetails.getAppTitle()).withNetworkType(str).withCarrier(this.deviceDetails.carrier()).withAppId(this.appDetails.getAppId());
        return clientContextBuilder.build();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public Map<String, String> getAllAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public Map<String, Double> getAllMetrics() {
        return Collections.unmodifiableMap(this.metrics);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public String getAttribute(String str) {
        if (str == null) {
            return null;
        }
        return this.attributes.get(str);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public Long getEventTimestamp() {
        return this.timestamp;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public String getEventType() {
        return this.eventType;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public Double getMetric(String str) {
        if (str == null) {
            return null;
        }
        return this.metrics.get(str);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public String getSdkName() {
        return this.sdkName;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public String getSdkVersion() {
        return this.sdkVersion;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public Long getSessionDuration() {
        return this.sessionDuration;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public String getSessionId() {
        return this.sessionId;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public long getSessionStart() {
        return this.sessionStart.longValue();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public Long getSessionStop() {
        return this.sessionStop;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public Id getUniqueId() {
        return this.uniqueId;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public boolean hasAttribute(String str) {
        if (str == null) {
            return false;
        }
        return this.attributes.containsKey(str);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public boolean hasMetric(String str) {
        if (str == null) {
            return false;
        }
        return this.metrics.containsKey(str);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.JSONSerializable
    public JSONObject toJSONObject() {
        Locale locale = this.deviceDetails.locale();
        String locale2 = locale != null ? locale.toString() : "UNKNOWN";
        JSONBuilder jSONBuilder = new JSONBuilder(this);
        jSONBuilder.withAttribute("event_type", getEventType());
        jSONBuilder.withAttribute("unique_id", getUniqueId().getValue());
        jSONBuilder.withAttribute("timestamp", getEventTimestamp());
        jSONBuilder.withAttribute(MetricsConfiguration.PLATFORM, this.deviceDetails.platform());
        jSONBuilder.withAttribute("platform_version", this.deviceDetails.platformVersion());
        jSONBuilder.withAttribute("make", this.deviceDetails.manufacturer());
        jSONBuilder.withAttribute("model", this.deviceDetails.model());
        jSONBuilder.withAttribute("locale", locale2);
        jSONBuilder.withAttribute(AMPDInformationProvider.DEVICE_CARRIER_KEY, this.deviceDetails.carrier());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.sessionId);
            if (this.sessionStart != null) {
                jSONObject.put(Constants.IntentParameters.START_TIMESTAMP, this.sessionStart);
            }
            if (this.sessionStop != null) {
                jSONObject.put("stopTimestamp", this.sessionStop);
            }
            if (this.sessionDuration != null) {
                jSONObject.put("duration", this.sessionDuration.longValue());
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error serializing session information", e);
        }
        jSONBuilder.withAttribute("session", jSONObject);
        jSONBuilder.withAttribute("sdk_version", this.sdkVersion);
        jSONBuilder.withAttribute("sdk_name", this.sdkName);
        jSONBuilder.withAttribute("app_version_name", this.appDetails.versionName());
        jSONBuilder.withAttribute("app_version_code", this.appDetails.versionCode());
        jSONBuilder.withAttribute("app_package_name", this.appDetails.packageName());
        jSONBuilder.withAttribute("app_title", this.appDetails.getAppTitle());
        jSONBuilder.withAttribute(ClientContext.APP_ID_KEY, this.appDetails.getAppId());
        JSONObject jSONObject2 = new JSONObject();
        for (Map.Entry<String, String> entry : getAllAttributes().entrySet()) {
            try {
                jSONObject2.put(entry.getKey(), entry.getValue());
            } catch (JSONException unused) {
            }
        }
        JSONObject jSONObject3 = new JSONObject();
        for (Map.Entry<String, Double> entry2 : getAllMetrics().entrySet()) {
            try {
                jSONObject3.put(entry2.getKey(), entry2.getValue());
            } catch (JSONException e2) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("error serializing metric. key:'");
                outline107.append(entry2.getKey());
                outline107.append("', value: ");
                outline107.append(entry2.getValue().toString());
                Log.e(TAG, outline107.toString(), e2);
            }
        }
        if (jSONObject2.length() > 0) {
            jSONBuilder.withAttribute("attributes", jSONObject2);
        }
        if (jSONObject3.length() > 0) {
            jSONBuilder.withAttribute("metrics", jSONObject3);
        }
        return jSONBuilder.toJSONObject();
    }

    public String toString() {
        JSONObject jSONObject = toJSONObject();
        try {
            return jSONObject.toString(4);
        } catch (JSONException unused) {
            return jSONObject.toString();
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    /* renamed from: withAttribute  reason: collision with other method in class */
    public DefaultEvent mo6692withAttribute(String str, String str2) {
        addAttribute(str, str2);
        return this;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    /* renamed from: withMetric  reason: collision with other method in class */
    public DefaultEvent mo6693withMetric(String str, Double d) {
        addMetric(str, d);
        return this;
    }

    public static DefaultEvent newInstance(String str, Map<String, String> map, Map<String, Double> map2, SDKInfo sDKInfo, String str2, Long l, Long l2, Long l3, long j, Id id, AppDetails appDetails, DeviceDetails deviceDetails) {
        return new DefaultEvent(str, map, map2, sDKInfo, str2, l.longValue(), l2, l3, j, id, appDetails, deviceDetails);
    }
}
