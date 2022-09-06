package com.amazon.deecomms.commscore;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService;
import com.amazon.commscore.api.featureflag.FeatureFlag;
import com.amazon.commscore.featureflag.dependencies.AlexaCommsCoreFeatureServiceWrapper;
import com.amazon.deecomms.api.CommsDynamicFeature;
import com.amazon.deecomms.commscore.legacy.AlexaCommsCoreFeatureServiceLegacy;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.core.FeatureFlagManager;
/* loaded from: classes12.dex */
public class AlexaCommsCoreFeatureServiceImpl implements AlexaCommsCoreFeatureService {
    private AlexaCommsCoreFeatureService alexaCommsCoreFeatureService;
    private AlexaCommsCoreFeatureServiceWrapper alexaCommsCoreFeatureServiceWrapper;
    private FeatureFlagManager featureFlagManager;

    public AlexaCommsCoreFeatureServiceImpl(ComponentGetter componentGetter, Context context) {
        if (CommsDaggerWrapper.getComponent() != null) {
            this.featureFlagManager = CommsDaggerWrapper.getComponent().getFeatureFlagManager();
            this.alexaCommsCoreFeatureServiceWrapper = new AlexaCommsCoreFeatureServiceWrapper(componentGetter, context);
            initiatilize();
        }
    }

    private void initiatilize() {
        if (this.featureFlagManager.isFeatureEnabled(CommsDynamicFeature.COMMS_CORE_FEATURE_SERVICE, false)) {
            this.alexaCommsCoreFeatureService = this.alexaCommsCoreFeatureServiceWrapper;
        } else {
            this.alexaCommsCoreFeatureService = new AlexaCommsCoreFeatureServiceLegacy(this.featureFlagManager);
        }
    }

    @VisibleForTesting
    AlexaCommsCoreFeatureService getFeatureServiceImplementation() {
        return this.alexaCommsCoreFeatureService;
    }

    @Override // com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService
    @NonNull
    public FeatureFlag isFeatureEnabled(@NonNull String str) {
        return this.alexaCommsCoreFeatureService.isFeatureEnabled(str);
    }

    @VisibleForTesting
    AlexaCommsCoreFeatureServiceImpl(FeatureFlagManager featureFlagManager, AlexaCommsCoreFeatureServiceWrapper alexaCommsCoreFeatureServiceWrapper) {
        this.featureFlagManager = featureFlagManager;
        this.alexaCommsCoreFeatureServiceWrapper = alexaCommsCoreFeatureServiceWrapper;
        initiatilize();
    }
}
