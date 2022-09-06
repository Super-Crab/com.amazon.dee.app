package com.amazon.alexa.location.provider.util;

import androidx.annotation.NonNull;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
/* loaded from: classes9.dex */
public final class GatedFeatures {
    public static final String FEATURE_ENABLE_LOCATION_SKILLS = "LOCATION_ANDROID_ENABLE_LOCATION_SKILLS";

    private GatedFeatures() {
    }

    public static boolean hasFeature(@NonNull String str) {
        return ((FeatureServiceV2) ComponentRegistry.getInstance().getLazy(FeatureServiceV2.class).mo10268get()).hasAccess(str, false);
    }
}
