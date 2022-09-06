package com.amazon.alexa.voice.handsfree.features;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.voice.feature.FeatureAvailability;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class FeatureAvailabilityForUi implements FeatureAvailability {
    private static final String TAG = "FeatureAvailabilityForUi";

    @Override // com.amazon.alexa.voice.feature.FeatureAvailability
    public boolean hasAccessToFeature(String str) {
        return ((FeatureServiceV2) GeneratedOutlineSupport1.outline20(FeatureServiceV2.class)).hasAccess(str, false);
    }
}
