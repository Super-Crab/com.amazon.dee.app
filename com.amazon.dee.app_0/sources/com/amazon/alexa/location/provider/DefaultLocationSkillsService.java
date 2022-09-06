package com.amazon.alexa.location.provider;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.location.provider.interactor.EventBusInteractor;
import com.amazon.alexa.location.provider.interactor.sync.contextualevents.ContextualEventRulesSyncInteractor;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
/* loaded from: classes9.dex */
public class DefaultLocationSkillsService implements LocationSkillsService {
    private static final String TAG = "DefaultLocSkillsService";
    private final Context context;
    @VisibleForTesting
    EventBusInteractor eventBusInteractor;
    private final LazyComponent<FeatureServiceV2> featureServiceV2;
    private final FeatureServiceV2.FeatureUpdateListener featureUpdateListener;
    @VisibleForTesting
    ContextualEventRulesSyncInteractor rulesSyncInteractor;

    public DefaultLocationSkillsService(@NonNull ComponentGetter componentGetter, @NonNull Context context) {
        this.context = context;
        this.eventBusInteractor = new EventBusInteractor(componentGetter.get(EventBus.class));
        this.rulesSyncInteractor = new ContextualEventRulesSyncInteractor();
        this.featureServiceV2 = componentGetter.get(FeatureServiceV2.class);
        this.featureUpdateListener = new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.alexa.location.provider.-$$Lambda$DefaultLocationSkillsService$bE5VlWS-dVjIy98ZPsHjPbSI7H0
            @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
            public final void onFeatureUpdate(String str) {
                DefaultLocationSkillsService.this.handleFeatureUpdates(str);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFeatureUpdates(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        boolean hasAccess = this.featureServiceV2.mo10268get().hasAccess(str, false);
        Object[] objArr = new Object[2];
        objArr[0] = str;
        objArr[1] = hasAccess ? "on" : "off";
        Log.i(TAG, String.format("Feature %s had been turned %s", objArr));
        if (!"LOCATION_ANDROID_ENABLE_LOCATION_SKILLS".equals(str)) {
            return;
        }
        if (hasAccess) {
            Log.i(TAG, "Enabling LSA sync interactor");
            this.eventBusInteractor.subscribeToEventBus(this.rulesSyncInteractor);
            return;
        }
        Log.i(TAG, "Disabling LSA sync interactor");
        this.rulesSyncInteractor.stopAllEventInteractors();
        this.eventBusInteractor.unsubscribeToEventBus(this.rulesSyncInteractor);
    }

    @Override // com.amazon.alexa.location.provider.LocationSkillsService
    public void start() {
        Log.i(TAG, "DefaultLocationSkillsService started - listening for feature changes");
        if (this.featureServiceV2.mo10268get().hasAccess("LOCATION_ANDROID_ENABLE_LOCATION_SKILLS", false)) {
            Log.i(TAG, String.format("Feature %s is initially on; Enabling LSA sync interactor", "LOCATION_ANDROID_ENABLE_LOCATION_SKILLS"));
            this.eventBusInteractor.subscribeToEventBus(this.rulesSyncInteractor);
        } else {
            Log.i(TAG, String.format("Feature %s is initially off; Skipping LSA sync interactor", "LOCATION_ANDROID_ENABLE_LOCATION_SKILLS"));
        }
        this.featureServiceV2.mo10268get().observeFeature("LOCATION_ANDROID_ENABLE_LOCATION_SKILLS", this.featureUpdateListener);
    }

    @Override // com.amazon.alexa.location.provider.LocationSkillsService
    public void stop() {
        Log.i(TAG, "DefaultLocationSkillsService stopped - un-subscribing sync interactors from Event Bus");
        this.rulesSyncInteractor.stopAllEventInteractors();
        this.eventBusInteractor.unsubscribeToEventBus(this.rulesSyncInteractor);
    }

    @VisibleForTesting
    DefaultLocationSkillsService(@NonNull Context context) {
        this.context = context;
        this.eventBusInteractor = new EventBusInteractor(ComponentRegistry.getInstance().getLazy(EventBus.class));
        this.rulesSyncInteractor = new ContextualEventRulesSyncInteractor();
        this.featureServiceV2 = ComponentRegistry.getInstance().getLazy(FeatureServiceV2.class);
        this.featureUpdateListener = new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.alexa.location.provider.-$$Lambda$DefaultLocationSkillsService$bE5VlWS-dVjIy98ZPsHjPbSI7H0
            @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
            public final void onFeatureUpdate(String str) {
                DefaultLocationSkillsService.this.handleFeatureUpdates(str);
            }
        };
    }
}
