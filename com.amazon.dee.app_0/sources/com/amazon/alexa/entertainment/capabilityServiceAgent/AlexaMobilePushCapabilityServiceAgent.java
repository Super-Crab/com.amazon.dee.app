package com.amazon.alexa.entertainment.capabilityServiceAgent;

import com.amazon.alexa.api.AlexaCapability;
import com.amazon.alexa.api.AlexaCapabilityAgentService;
import com.amazon.alexa.api.AlexaDirective;
import com.amazon.alexa.entertainment.constants.MetricConstants;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes7.dex */
public class AlexaMobilePushCapabilityServiceAgent extends AlexaCapabilityAgentService {
    public static final String ALEXA_MOBILE_PUSH_CAPABILITY = "Alexa.Mobile.Push";
    public static final String ALEXA_MOBILE_PUSH_CAPABILITY_VERSION = "1.0";
    private static final String DELIMITER = ":";
    private static final String DESTINATION_ROUTE = "EventBus";
    private static final String DIRECTIVE_KEY_RENDERING_UPDATES = "renderingUpdates";
    private static final String DIRECTIVE_KEY_RESOURCE_ID = "resourceId";
    private static final String DIRECTIVE_KEY_RESOURCE_METADATA = "resourceMetadata";
    private static final String DIRECTIVE_KEY_ROUTE = "route";
    private static final String DIRECTIVE_KEY_TIMESTAMP = "timeStamp";
    public static final String HEADER = "RenderUpdate";
    private static final String TIMER_EVENT_NAME_SUFFIX = "_LATENCY";
    private static final String WEBLAB_PROTEGO_ANDROID = "ENTERTAINMENT_PROJECT_PROTEGO_ANDROID";
    private static final String WEBLAB_PROTEGO_ANDROID_METRIC = "ENTERTAINMENT_PROJECT_PROTEGO_ANDROID_METRICS";
    public EventBus eventBus;
    public FeatureQuery featureQuery;
    public Mobilytics mobilytics;
    private static final String TAG = AlexaMobilePushCapabilityServiceAgent.class.getSimpleName();
    private static final JsonParser jsonParser = new JsonParser();

    private JsonArray extractDirectivePayload(AlexaDirective alexaDirective) {
        return jsonParser.parse(alexaDirective.getAlexaPayload().getPayload()).getAsJsonObject().get(DIRECTIVE_KEY_RENDERING_UPDATES).getAsJsonArray();
    }

    private synchronized EventBus getEventBus() {
        if (this.eventBus == null) {
            this.eventBus = (EventBus) ComponentRegistry.getInstance().get(EventBus.class).get();
        }
        return this.eventBus;
    }

    private synchronized FeatureQuery getFeatureQuery() {
        if (this.featureQuery == null) {
            this.featureQuery = (FeatureQuery) ComponentRegistry.getInstance().get(FeatureQuery.class).get();
        }
        return this.featureQuery;
    }

    private boolean shouldProcessDirective(AlexaDirective alexaDirective) {
        return alexaDirective != null && HEADER.equals(alexaDirective.getName()) && ALEXA_MOBILE_PUSH_CAPABILITY.equals(alexaDirective.getNamespace());
    }

    private boolean validateRoute(String str) {
        return str != null && str.equals(DESTINATION_ROUTE);
    }

    public String extractEventPayload(JsonObject jsonObject) {
        String asString = jsonObject.get(DIRECTIVE_KEY_RESOURCE_METADATA).getAsString();
        if (asString == null || asString.isEmpty()) {
            return null;
        }
        return jsonParser.parse(asString).toString();
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public Set<AlexaCapability> getCapabilities() {
        if (getFeatureQuery().isActive(WEBLAB_PROTEGO_ANDROID)) {
            HashSet hashSet = new HashSet();
            hashSet.add(AlexaCapability.create(ALEXA_MOBILE_PUSH_CAPABILITY, "1.0"));
            return hashSet;
        }
        return null;
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mobilytics = (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public boolean process(AlexaDirective alexaDirective) {
        if (shouldProcessDirective(alexaDirective)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("process: Processing ");
            outline107.append(alexaDirective.getNamespace());
            outline107.toString();
            EventBus eventBus = getEventBus();
            JsonArray extractDirectivePayload = extractDirectivePayload(alexaDirective);
            for (int i = 0; i < extractDirectivePayload.size(); i++) {
                JsonObject asJsonObject = extractDirectivePayload.get(i).getAsJsonObject();
                String extractEventPayload = extractEventPayload(asJsonObject);
                String[] split = asJsonObject.get("route").getAsString().split(":", 2);
                if (extractEventPayload == null || !validateRoute(split[0])) {
                    return false;
                }
                if (this.mobilytics != null && getFeatureQuery().isActive(WEBLAB_PROTEGO_ANDROID_METRIC)) {
                    String asString = asJsonObject.get(DIRECTIVE_KEY_RESOURCE_ID).getAsString();
                    String asString2 = jsonParser.parse(extractEventPayload).getAsJsonObject().get(DIRECTIVE_KEY_TIMESTAMP).getAsString();
                    this.mobilytics.recordOccurrence(asString, true, MetricConstants.COMPONENT, MetricConstants.SubComponents.ANDROID_CAPABILITY_AGENT);
                    this.mobilytics.recordTimer(this.mobilytics.createTimer(GeneratedOutlineSupport1.outline72(asString, TIMER_EVENT_NAME_SUFFIX), MetricConstants.COMPONENT, MetricConstants.SubComponents.ANDROID_CAPABILITY_AGENT, System.currentTimeMillis() - Long.parseLong(asString2), true));
                }
                eventBus.publish(new Message.Builder().setEventType(split[1]).setPayload(extractEventPayload).build());
            }
            return true;
        }
        return false;
    }
}
