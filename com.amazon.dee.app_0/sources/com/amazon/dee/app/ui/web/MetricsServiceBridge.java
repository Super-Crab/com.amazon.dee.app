package com.amazon.dee.app.ui.web;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import com.dee.app.metrics.MetricsService;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public final class MetricsServiceBridge extends JavaScriptBridge {
    private final Gson gson;
    private final JavaScriptDelegate javaScriptDelegate;
    private final Map<String, String> jsToNativeNameMap;
    private final Map<String, JavaScriptBridgeMethod> mMethods;
    private final MetricsService metricsService;

    /* loaded from: classes12.dex */
    private class IncrementCounterMethod implements JavaScriptBridgeMethod {
        private IncrementCounterMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            MetricsServiceBridge.this.metricsService.incrementCounter(jSONObject.optString("EventName"));
            MetricsServiceBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class RecordBatchMethod implements JavaScriptBridgeMethod {
        private RecordBatchMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            JSONArray optJSONArray = jSONObject.optJSONArray(DefaultDeliveryClient.EVENTS_DIRECTORY);
            if (optJSONArray == null || optJSONArray.length() == 0) {
                return;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                String string = jSONObject2.getString(MetricsConstants.NativeFetch.METHOD);
                JSONObject optJSONObject = jSONObject2.optJSONObject("parameters");
                if (!TextUtils.isEmpty(string) && optJSONObject != null) {
                    String str = (String) MetricsServiceBridge.this.jsToNativeNameMap.get(string);
                    if (str != null) {
                        MetricsServiceBridge.this.callMetricService(str, optJSONObject);
                    } else if ("recordCounter".equals(string)) {
                        MetricsServiceBridge.this.metricsService.recordEvent(optJSONObject.optString("EventName"), AlexaMetricsConstants.MetricsComponents.METRICS_BRIDGE, null);
                    } else if ("incrementCounter".equals(string)) {
                        MetricsServiceBridge.this.metricsService.incrementCounter(optJSONObject.optString("EventName"));
                    }
                }
            }
            MetricsServiceBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class RecordEngagementMethod implements JavaScriptBridgeMethod {
        private RecordEngagementMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            MetricsServiceBridge.this.callMetricService("Engagement", jSONObject);
            MetricsServiceBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class RecordEventMethod implements JavaScriptBridgeMethod {
        private RecordEventMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            MetricsServiceBridge.this.callMetricService("General", jSONObject);
            MetricsServiceBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class RecordImpressionMethod implements JavaScriptBridgeMethod {
        private RecordImpressionMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            MetricsServiceBridge.this.callMetricService("Impression", jSONObject);
            MetricsServiceBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class ReocordCounterMethod implements JavaScriptBridgeMethod {
        private ReocordCounterMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            MetricsServiceBridge.this.metricsService.recordEvent(jSONObject.optString("EventName"), AlexaMetricsConstants.MetricsComponents.METRICS_BRIDGE, null);
            MetricsServiceBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class ReocordTimerMethod implements JavaScriptBridgeMethod {
        private ReocordTimerMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            MetricsServiceBridge.this.callMetricService("RecordTimer", jSONObject);
            MetricsServiceBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class StartCounterMethod implements JavaScriptBridgeMethod {
        private StartCounterMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            MetricsServiceBridge.this.callMetricService("Counter", jSONObject);
            MetricsServiceBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class StartTimerMethod implements JavaScriptBridgeMethod {
        private StartTimerMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            MetricsServiceBridge.this.callMetricService("StartTimer", jSONObject);
            MetricsServiceBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public MetricsServiceBridge(@NonNull JavaScriptDelegate javaScriptDelegate, @NonNull JavaScriptInjector javaScriptInjector, @NonNull JavaScriptResponseQueue javaScriptResponseQueue, @NonNull MetricsService metricsService, @NonNull Gson gson) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.jsToNativeNameMap = new HashMap();
        this.javaScriptDelegate = javaScriptDelegate;
        this.metricsService = metricsService;
        this.gson = gson;
        this.mMethods.put("recordEvent", new RecordEventMethod());
        this.mMethods.put("recordImpression", new RecordImpressionMethod());
        this.mMethods.put("recordEngagement", new RecordEngagementMethod());
        this.mMethods.put("startTimer", new StartTimerMethod());
        this.mMethods.put("recordTimer", new ReocordTimerMethod());
        this.mMethods.put("startCounter", new StartCounterMethod());
        this.mMethods.put("incrementCounter", new IncrementCounterMethod());
        this.mMethods.put("recordCounter", new ReocordCounterMethod());
        this.mMethods.put("recordBatch", new RecordBatchMethod());
        this.jsToNativeNameMap.put("recordEvent", "General");
        this.jsToNativeNameMap.put("recordImpression", "Impression");
        this.jsToNativeNameMap.put("recordEngagement", "Engagement");
        this.jsToNativeNameMap.put("startTimer", "StartTimer");
        this.jsToNativeNameMap.put("startCounter", "Counter");
        this.jsToNativeNameMap.put("recordTimer", "RecordTimer");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callMetricService(String str, JSONObject jSONObject) throws JSONException {
        HashMap<String, Object> hashMap;
        try {
            String optString = jSONObject.optString("EventName");
            String optString2 = jSONObject.optString("AppComponent");
            JSONObject optJSONObject = jSONObject.optJSONObject(AlexaMetricsConstants.EventConstants.CUSTOM_ENTRIES);
            if (optJSONObject != null) {
                hashMap = (HashMap) this.gson.fromJson(optJSONObject.toString(), (Class<Object>) HashMap.class);
                Double d = (Double) hashMap.get("EventTimestamp");
                if (d != null) {
                    hashMap.put("EventTimestamp", Long.valueOf(BigDecimal.valueOf(d.doubleValue()).longValue()));
                }
            } else {
                hashMap = null;
            }
            if (optString == null || optString.length() == 0) {
                this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.METRICS_BRIDGE_ERROR, ":Event name form JS null!", AlexaMetricsConstants.MetricsComponents.METRICS_BRIDGE, null);
                return;
            }
            char c = 65535;
            switch (str.hashCode()) {
                case -1672483364:
                    if (str.equals("Counter")) {
                        c = 5;
                        break;
                    }
                    break;
                case -1320133292:
                    if (str.equals("RecordTimer")) {
                        c = 4;
                        break;
                    }
                    break;
                case 409836579:
                    if (str.equals("StartTimer")) {
                        c = 3;
                        break;
                    }
                    break;
                case 428749919:
                    if (str.equals("Engagement")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1584505032:
                    if (str.equals("General")) {
                        c = 0;
                        break;
                    }
                    break;
                case 2114088489:
                    if (str.equals("Impression")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                this.metricsService.recordEvent(optString, optString2, hashMap);
            } else if (c == 1) {
                this.metricsService.recordEngagement(optString, optString2, setSubComponent(hashMap));
            } else if (c == 2) {
                this.metricsService.recordImpression(optString, optString2, setSubComponent(hashMap));
            } else if (c == 3) {
                this.metricsService.startTimer(optString, optString2, hashMap);
            } else if (c == 4) {
                this.metricsService.recordTimer(optString, hashMap);
            } else if (c != 5) {
                Log.e(JavaScriptBridge.TAG, "callMetricService called with improper type.");
            } else {
                this.metricsService.startCounter(optString, optString2, hashMap);
            }
        } catch (Exception e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error parsing arguments from JS. \nArgs = ");
            outline107.append(jSONObject.toString());
            outline107.append("\n Error = ");
            outline107.append(e.toString());
            String sb = outline107.toString();
            Log.e(JavaScriptBridge.TAG, sb);
            this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.METRICS_BRIDGE_ERROR, sb, AlexaMetricsConstants.MetricsComponents.METRICS_BRIDGE, null);
        }
    }

    private HashMap<String, Object> setSubComponent(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        hashMap.put("subComponent", AlexaMetricsConstants.MetricsComponents.METRICS_BRIDGE_METRIC_SERVICE_SUBCOMPONENT);
        return hashMap;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public String getJavaScriptInterfaceName() {
        return "MetricsServiceBridge";
    }
}
