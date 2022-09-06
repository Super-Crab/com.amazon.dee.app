package com.amazon.alexa.location.provider.interactor.sync;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.location.provider.LocationSkillRule;
import com.amazon.alexa.location.provider.interactor.event.LocationEventInteractor;
import com.amazon.alexa.location.provider.interactor.event.SensorEventInteractor;
import com.amazon.alexa.location.provider.util.Metrics;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.sensor.api.location.LocationSensorAccess;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public class DemoRulesSyncInteractor implements SyncInteractor {
    private static final String EVENT_TYPE = "geofence::sync";
    private static final String METRIC_APP_STATE_CHANGED = "app_state_changed";
    private static final String METRIC_EVENT_RECEIVED = "event_received";
    private static final String METRIC_LOCATION_TRACKING_STARTED = "location_tracking_started";
    private static final String METRIC_LOCATION_TRACKING_STOPPED = "location_tracking_stopped";
    private static final String METRIC_OS_PERMISSION_DENIED = "os_permission_denied";
    private static final String METRIC_RULES_CONSTRUCTED = "rules_constructed";
    private static final String METRIC_RULE_CONSTRUCTION_ERROR = "rule_construction_error";
    private static final String METRIC_RULE_SYNC_STARTED = "rule_sync_started";
    private static final String METRIC_RULE_SYNC_SUCCESS = "rule_sync_success";
    private static final String RULE_ID_SAMPLE_1 = "sample_rule_1";
    private static final String SUB_COMPONENT_DEMO_SYNC_INTERACTOR = "demo_sync_interactor";
    private static final String TAG = "DemoRulesSyncInteractor";
    @VisibleForTesting
    final Map<String, SensorEventInteractor> eventInteractorMap = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$getMessageFilter$0(Message message) {
        String.format("Received event with type %s", message.getEventType());
        boolean equals = EVENT_TYPE.equals(message.getEventType());
        GeneratedOutlineSupport1.outline172("Filter result: ", equals);
        return equals;
    }

    @VisibleForTesting
    void configureInteractors(@NonNull List<LocationSkillRule> list) {
        HashSet<LocationSkillRule> hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        HashSet<String> hashSet2 = new HashSet();
        for (LocationSkillRule locationSkillRule : list) {
            if (this.eventInteractorMap.containsKey(locationSkillRule.ruleId)) {
                hashMap.put(locationSkillRule.ruleId, locationSkillRule);
            } else {
                hashSet.add(locationSkillRule);
            }
        }
        for (String str : this.eventInteractorMap.keySet()) {
            if (!hashMap.containsKey(str)) {
                hashSet2.add(str);
            }
        }
        for (String str2 : hashSet2) {
            this.eventInteractorMap.get(str2).stop();
            Metrics.recordOccurrence("location_tracking_stopped", SUB_COMPONENT_DEMO_SYNC_INTERACTOR);
            this.eventInteractorMap.remove(str2);
        }
        for (LocationSkillRule locationSkillRule2 : hashSet) {
            LocationEventInteractor locationEventInteractor = new LocationEventInteractor(ComponentRegistry.getInstance().getLazy(LocationSensorAccess.class));
            this.eventInteractorMap.put(locationSkillRule2.ruleId, locationEventInteractor);
            locationEventInteractor.execute();
            Metrics.recordOccurrence("location_tracking_started", SUB_COMPONENT_DEMO_SYNC_INTERACTOR);
        }
    }

    @NonNull
    @VisibleForTesting
    List<LocationSkillRule> fetchRules() {
        Metrics.recordOccurrence(METRIC_RULE_SYNC_STARTED, SUB_COMPONENT_DEMO_SYNC_INTERACTOR);
        Metrics.recordSuccess(METRIC_RULE_SYNC_SUCCESS, true, SUB_COMPONENT_DEMO_SYNC_INTERACTOR);
        ArrayList arrayList = new ArrayList(1);
        if (!this.eventInteractorMap.containsKey(RULE_ID_SAMPLE_1)) {
            arrayList.add(new LocationSkillRule(RULE_ID_SAMPLE_1));
        }
        Metrics.recordValue(METRIC_RULES_CONSTRUCTED, arrayList.size(), SUB_COMPONENT_DEMO_SYNC_INTERACTOR);
        return arrayList;
    }

    @Override // com.amazon.alexa.location.provider.interactor.sync.SyncInteractor
    public MessageFilter getMessageFilter() {
        return $$Lambda$DemoRulesSyncInteractor$8Q2wdZnEc2n7Fi6ZwJi6ghPMcAA.INSTANCE;
    }

    @Override // com.amazon.alexa.location.provider.interactor.sync.SyncInteractor
    public MessageHandler getMessageHandler() {
        return new MessageHandler() { // from class: com.amazon.alexa.location.provider.interactor.sync.-$$Lambda$DemoRulesSyncInteractor$r8mo90nd6YZaLRd2VGQo8QVxG2g
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DemoRulesSyncInteractor.this.lambda$getMessageHandler$1$DemoRulesSyncInteractor(message);
            }
        };
    }

    @Override // com.amazon.alexa.location.provider.interactor.sync.SyncInteractor
    public String getName() {
        return TAG;
    }

    public /* synthetic */ void lambda$getMessageHandler$1$DemoRulesSyncInteractor(Message message) {
        Metrics.recordOccurrence(METRIC_EVENT_RECEIVED, SUB_COMPONENT_DEMO_SYNC_INTERACTOR);
        configureInteractors(fetchRules());
        reportSetupResults();
    }

    @VisibleForTesting
    void reportSetupResults() {
    }
}
