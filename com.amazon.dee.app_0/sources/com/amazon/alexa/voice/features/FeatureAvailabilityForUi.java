package com.amazon.alexa.voice.features;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.voice.feature.FeatureAvailability;
/* loaded from: classes11.dex */
public class FeatureAvailabilityForUi implements FeatureAvailability {
    private final FeatureServiceV2 featureService;

    public FeatureAvailabilityForUi(FeatureServiceV2 featureServiceV2) {
        this.featureService = featureServiceV2;
    }

    @Override // com.amazon.alexa.voice.feature.FeatureAvailability
    public boolean hasAccessToFeature(String str) {
        return this.featureService.hasAccess(str, false);
    }
}
