package com.amazon.alexa.accessorykit.features;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.service.api.LazyComponent;
/* loaded from: classes6.dex */
public final class AccessoryFeatureChecker implements FeatureChecker {
    @VisibleForTesting
    static final String APP_COMPONENT_ACCESSORIES = "accessories";
    @VisibleForTesting
    static final String METRIC_FEATURE_SERVICE_IS_NULL = "featureServiceIsNull";
    private static final String TAG = "AccessoryFeatureChecker";
    private final LazyComponent<FeatureServiceV2> featureServiceV2Lazy;

    public AccessoryFeatureChecker(LazyComponent<FeatureServiceV2> lazyComponent) {
        Preconditions.notNull(lazyComponent, "featureServiceV2Lazy");
        this.featureServiceV2Lazy = lazyComponent;
    }

    @Override // com.amazon.alexa.accessory.utils.feature.FeatureChecker
    public boolean hasAccess(AccessoryFeature accessoryFeature) {
        FeatureServiceV2 mo10268get = this.featureServiceV2Lazy.mo10268get();
        if (mo10268get == null) {
            Logger.e("%s: FeatureServiceV2 is null", TAG);
            AccessoryMetricsServiceHolder.getInstance().get().recordCounter(METRIC_FEATURE_SERVICE_IS_NULL, "accessories", 1.0d, null);
            return false;
        }
        boolean hasAccess = mo10268get.hasAccess(accessoryFeature.getName(), false);
        Logger.d("%s: feature: %s hasAccess: %s", TAG, accessoryFeature.getName(), Boolean.valueOf(hasAccess));
        return hasAccess;
    }
}
