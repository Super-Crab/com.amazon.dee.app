package com.amazon.alexa.location.provider.interactor.sync.contextualevents;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.location.provider.interactor.event.LocationEventInteractor;
import com.amazon.alexa.location.provider.interactor.event.SensorEventInteractor;
import com.amazon.alexa.location.provider.interactor.sync.SyncInteractor;
import com.amazon.alexa.location.provider.util.Metrics;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.sensor.api.location.LocationConfigurationRequest;
import com.amazon.alexa.sensor.api.location.LocationSensorAccess;
import com.amazon.dee.app.framework.EventBusMessagingReceiver;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class ContextualEventRulesSyncInteractor implements SyncInteractor, LocationEventInteractor.Callback {
    private static final String EVENT_TYPE = "AlexaSubscriptionRuntimeService.SubscriptionNotification";
    private static final String EVENT_TYPE_SIGN_OUT = "identity:signOut:success";
    private static final String METRIC_APP_STATE_CHANGED = "app_state_changed";
    private static final String METRIC_EVENT_RECEIVED = "event_received";
    private static final String METRIC_FIELD_REPORTING_RULE = "reporting_rule";
    private static final String METRIC_FIELD_SUBSCRIPTION_ID = "subscription_id";
    private static final String METRIC_LOCATION_TRACKING_STARTED = "location_tracking_started";
    private static final String METRIC_LOCATION_TRACKING_STOPPED = "location_tracking_stopped";
    private static final String METRIC_MISSING_NECESSARY_FIELDS_PREFIX = "missing_necessary_fields_";
    private static final String METRIC_OS_PERMISSION_DENIED = "os_permission_denied";
    private static final String METRIC_RULES_CONSTRUCTED = "rules_constructed";
    private static final String METRIC_RULE_CONSTRUCTION_ERROR = "rule_construction_error";
    private static final String SUB_COMPONENT_RULES_SYNC_INTERACTOR = "rules_sync_interactor";
    private static final String TAG = "ContextualInteractor";
    private final Gson gson = new Gson();
    final Map<String, SensorEventInteractor> eventInteractorMap = new HashMap();

    private LocationConfigurationRequest buildLocationRequest(@NonNull Subscription subscription) {
        LocationConfigurationRequest locationConfigurationRequest = new LocationConfigurationRequest();
        locationConfigurationRequest.mode = 3;
        locationConfigurationRequest.locationAccuracy = 0;
        locationConfigurationRequest.minimumDeliveryTimeInterval = subscription.getReportingRule().getIntervalInSeconds() * 1000;
        locationConfigurationRequest.minimumDeliveryDistance = 0.0f;
        locationConfigurationRequest.appStateRequirement = 2;
        return locationConfigurationRequest;
    }

    private List<Subscription> extractSubscriptions(String str) {
        JSONObject jSONObject;
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            String str2 = "Payload array length: " + jSONArray.length();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (jSONObject2.has("subscriptions")) {
                    String str3 = "Subscription object: " + jSONObject.getString(EventBusMessagingReceiver.NotificationEvent.ORIGINAL_URI);
                    arrayList.addAll((Collection) this.gson.fromJson(jSONObject2.getJSONObject("subscriptions").getString(EventBusMessagingReceiver.NotificationEvent.ORIGINAL_URI), new TypeToken<Collection<Subscription>>() { // from class: com.amazon.alexa.location.provider.interactor.sync.contextualevents.ContextualEventRulesSyncInteractor.1
                    }.getType()));
                }
            }
        } catch (JSONException e) {
            Metrics.recordOccurrence(METRIC_RULE_CONSTRUCTION_ERROR, SUB_COMPONENT_RULES_SYNC_INTERACTOR);
            Log.e(TAG, "Error parsing Event Bus message; no subscriptions extracted", e);
        }
        Metrics.recordValue(METRIC_RULES_CONSTRUCTED, arrayList.size(), SUB_COMPONENT_RULES_SYNC_INTERACTOR);
        if (arrayList.size() > 1) {
            Log.w(TAG, "Found multiple subscriptions within a single event bus message. Only one subscription will be set in P0 implementation.");
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$getMessageFilter$0(Message message) {
        return EVENT_TYPE.equals(message.getEventType()) || "identity:signOut:success".equals(message.getEventType());
    }

    private void recordNotEnoughMetrics(Subscription subscription) {
        if (subscription.getSubscriptionId() == null) {
            Metrics.recordOccurrence("missing_necessary_fields_subscription_id", SUB_COMPONENT_RULES_SYNC_INTERACTOR);
        }
        if (subscription.getReportingRule() == null) {
            Metrics.recordOccurrence("missing_necessary_fields_reporting_rule", SUB_COMPONENT_RULES_SYNC_INTERACTOR);
        }
    }

    @VisibleForTesting
    void configureInteractors(@NonNull List<Subscription> list) {
        HashSet<Subscription> hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        HashSet<String> hashSet2 = new HashSet();
        for (Subscription subscription : list) {
            if (subscription.isLocationSubscription()) {
                recordNotEnoughMetrics(subscription);
                if (this.eventInteractorMap.containsKey(subscription.getSubscriptionId())) {
                    hashMap.put(subscription.getSubscriptionId(), subscription);
                } else {
                    hashSet.add(subscription);
                }
            }
        }
        for (String str : this.eventInteractorMap.keySet()) {
            if (!hashMap.containsKey(str)) {
                hashSet2.add(str);
            }
        }
        for (String str2 : hashSet2) {
            SensorEventInteractor sensorEventInteractor = this.eventInteractorMap.get(str2);
            if (sensorEventInteractor != null) {
                sensorEventInteractor.stop();
            }
        }
        for (Subscription subscription2 : hashSet) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Setting up location event interactor with correlation token ");
            outline107.append(subscription2.getCorrelationToken());
            outline107.append(", type ");
            outline107.append(subscription2.getReportingRule().getType());
            outline107.append(" and interval of ");
            outline107.append(subscription2.getReportingRule().getIntervalInSeconds());
            outline107.append(" seconds");
            Log.i(TAG, outline107.toString());
            LocationEventInteractor locationEventInteractor = new LocationEventInteractor(ComponentRegistry.getInstance().getLazy(LocationSensorAccess.class));
            locationEventInteractor.setSubscriptionId(subscription2.getSubscriptionId());
            locationEventInteractor.setLocationConfigurationRequest(buildLocationRequest(subscription2));
            locationEventInteractor.setCallback(this);
            this.eventInteractorMap.put(subscription2.getSubscriptionId(), locationEventInteractor);
            locationEventInteractor.execute();
            Metrics.recordOccurrence("location_tracking_started", SUB_COMPONENT_RULES_SYNC_INTERACTOR);
        }
    }

    @Override // com.amazon.alexa.location.provider.interactor.sync.SyncInteractor
    public MessageFilter getMessageFilter() {
        return $$Lambda$ContextualEventRulesSyncInteractor$f308UqDgnkVyPLl9RJI02OvFVlo.INSTANCE;
    }

    @Override // com.amazon.alexa.location.provider.interactor.sync.SyncInteractor
    public MessageHandler getMessageHandler() {
        return new MessageHandler() { // from class: com.amazon.alexa.location.provider.interactor.sync.contextualevents.-$$Lambda$ContextualEventRulesSyncInteractor$zrGEfraXOdG959Tc1ybBx7ScuVs
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                ContextualEventRulesSyncInteractor.this.lambda$getMessageHandler$1$ContextualEventRulesSyncInteractor(message);
            }
        };
    }

    @Override // com.amazon.alexa.location.provider.interactor.sync.SyncInteractor
    public String getName() {
        return TAG;
    }

    public /* synthetic */ void lambda$getMessageHandler$1$ContextualEventRulesSyncInteractor(Message message) {
        if (EVENT_TYPE.equals(message.getEventType())) {
            Metrics.recordOccurrence(METRIC_EVENT_RECEIVED, SUB_COMPONENT_RULES_SYNC_INTERACTOR);
            Log.i(TAG, "Received location skill event; setting up trackers");
            String payloadAsString = message.getPayloadAsString();
            GeneratedOutlineSupport1.outline158("Message payload: ", payloadAsString);
            configureInteractors(extractSubscriptions(payloadAsString));
        } else if (!"identity:signOut:success".equals(message.getEventType())) {
        } else {
            Log.i(TAG, "User logged out, clearing all location skill trackers");
            stopAllEventInteractors();
        }
    }

    @Override // com.amazon.alexa.location.provider.interactor.event.LocationEventInteractor.Callback
    public void onInteractorStopped(String str) {
        Metrics.recordOccurrence("location_tracking_stopped", SUB_COMPONENT_RULES_SYNC_INTERACTOR);
        this.eventInteractorMap.remove(str);
    }

    public void stopAllEventInteractors() {
        for (String str : this.eventInteractorMap.keySet()) {
            SensorEventInteractor sensorEventInteractor = this.eventInteractorMap.get(str);
            if (sensorEventInteractor != null) {
                sensorEventInteractor.stop();
                Metrics.recordOccurrence("location_tracking_stopped", SUB_COMPONENT_RULES_SYNC_INTERACTOR);
            }
        }
        this.eventInteractorMap.clear();
    }
}
