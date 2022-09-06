package com.amazon.deecomms.core;

import androidx.annotation.NonNull;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsDynamicFeature;
import com.amazon.deecomms.common.Constants;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import rx.subjects.BehaviorSubject;
/* loaded from: classes12.dex */
public class FeatureFlagManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, FeatureFlagManager.class);
    private final EnumMap<CommsDynamicFeature, BehaviorSubject<Boolean>> featureFlags = new EnumMap<>(CommsDynamicFeature.class);
    private final Lazy<FeatureServiceV2> featureServiceV2Lazy;

    @Inject
    public FeatureFlagManager(Lazy<FeatureServiceV2> lazy) {
        for (CommsDynamicFeature commsDynamicFeature : CommsDynamicFeature.values()) {
            this.featureFlags.put((EnumMap<CommsDynamicFeature, BehaviorSubject<Boolean>>) commsDynamicFeature, (CommsDynamicFeature) BehaviorSubject.create(false));
        }
        this.featureServiceV2Lazy = lazy;
    }

    private boolean hasAccess(String str, boolean z) {
        return this.featureServiceV2Lazy.mo358get().hasAccess(str, z);
    }

    public ArrayList<String> getAllFeatures() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (CommsDynamicFeature commsDynamicFeature : this.featureFlags.keySet()) {
            arrayList.add(commsDynamicFeature.getPrimaryFeatureName());
        }
        return arrayList;
    }

    public boolean isCommsAllFeatureEnabled() {
        return hasAccess(CommsDynamicFeature.COMMS_ALL_FEATURES.getPrimaryFeatureName(), false) || hasAccess(CommsDynamicFeature.COMMS_ALL_FEATURES_NEW.getPrimaryFeatureName(), false);
    }

    public boolean isFeatureEnabled(@NonNull CommsDynamicFeature commsDynamicFeature) {
        return isFeatureEnabled(commsDynamicFeature, true);
    }

    public void observeFeature(@NonNull CommsDynamicFeature commsDynamicFeature, FeatureServiceV2.FeatureUpdateListener featureUpdateListener) {
        this.featureServiceV2Lazy.mo358get().observeFeature(commsDynamicFeature.getPrimaryFeatureName(), featureUpdateListener);
    }

    public void setFeature(@NonNull CommsDynamicFeature commsDynamicFeature, @NonNull Boolean bool) {
        CommsLogger commsLogger = LOG;
        commsLogger.d("setting feature: " + commsDynamicFeature + " to " + bool);
        this.featureFlags.get(commsDynamicFeature).onNext(bool);
    }

    public void setFeatures(EnumMap<CommsDynamicFeature, Boolean> enumMap) {
        CommsLogger commsLogger = LOG;
        commsLogger.d("setting features: " + enumMap);
        for (Map.Entry<CommsDynamicFeature, Boolean> entry : enumMap.entrySet()) {
            if (entry.getValue() != null) {
                setFeature(entry.getKey(), entry.getValue());
            }
        }
    }

    public boolean isFeatureEnabled(@NonNull CommsDynamicFeature commsDynamicFeature, boolean z) {
        if (z) {
            return hasAccess(commsDynamicFeature.getPrimaryFeatureName(), false) || isCommsAllFeatureEnabled();
        }
        return hasAccess(commsDynamicFeature.getPrimaryFeatureName(), false);
    }

    public boolean isFeatureEnabled(@NonNull List<CommsDynamicFeature> list) {
        for (int i = 0; i < list.size(); i++) {
            if (hasAccess(list.get(i).getPrimaryFeatureName(), false)) {
                return true;
            }
        }
        return false;
    }
}
