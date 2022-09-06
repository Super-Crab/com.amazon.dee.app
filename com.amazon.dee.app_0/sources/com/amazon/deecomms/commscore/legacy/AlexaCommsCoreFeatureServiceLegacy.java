package com.amazon.deecomms.commscore.legacy;

import androidx.annotation.NonNull;
import com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService;
import com.amazon.commscore.api.featureflag.FeatureFlag;
import com.amazon.commscore.api.featureflag.FeaturePoolOperation;
import com.amazon.deecomms.api.CommsDynamicFeature;
import com.amazon.deecomms.core.FeatureFlagManager;
/* loaded from: classes12.dex */
public class AlexaCommsCoreFeatureServiceLegacy implements AlexaCommsCoreFeatureService {
    private FeatureFlagManager featureFlagManager;

    public AlexaCommsCoreFeatureServiceLegacy(FeatureFlagManager featureFlagManager) {
        this.featureFlagManager = featureFlagManager;
    }

    @Override // com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService
    @NonNull
    public FeatureFlag isFeatureEnabled(@NonNull final String str) {
        CommsDynamicFeature featureFromName = CommsDynamicFeature.getFeatureFromName(str);
        final boolean z = false;
        if (featureFromName != null) {
            z = this.featureFlagManager.isFeatureEnabled(featureFromName, false);
        }
        return new FeatureFlag() { // from class: com.amazon.deecomms.commscore.legacy.AlexaCommsCoreFeatureServiceLegacy.1
            @Override // com.amazon.commscore.api.featureflag.FeatureFlag
            @NonNull
            public String getName() {
                return str;
            }

            @Override // com.amazon.commscore.api.featureflag.FeatureFlag
            public boolean getValue() {
                return z;
            }

            @Override // com.amazon.commscore.api.featureflag.FeatureFlag
            @NonNull
            public FeaturePoolOperation withFeaturePool(@NonNull String str2) {
                CommsDynamicFeature featureFromName2 = CommsDynamicFeature.getFeatureFromName(str2);
                final boolean z2 = false;
                if (featureFromName2 != null) {
                    z2 = AlexaCommsCoreFeatureServiceLegacy.this.featureFlagManager.isFeatureEnabled(featureFromName2, false);
                }
                return new FeaturePoolOperation() { // from class: com.amazon.deecomms.commscore.legacy.AlexaCommsCoreFeatureServiceLegacy.1.1
                    @Override // com.amazon.commscore.api.featureflag.FeaturePoolOperation
                    @NonNull
                    public FeaturePoolOperation and(@NonNull String str3) {
                        return this;
                    }

                    @Override // com.amazon.commscore.api.featureflag.FeaturePoolOperation
                    public boolean getValue() {
                        return z || z2;
                    }
                };
            }
        };
    }
}
