package com.amazon.commscore.featureflag;

import androidx.annotation.NonNull;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService;
import com.amazon.commscore.api.featureflag.FeatureFlag;
import dagger.Lazy;
/* loaded from: classes12.dex */
public class DefaultAlexaCommsCoreFeatureService implements AlexaCommsCoreFeatureService {
    private Lazy<IdentityService> mIdentityServiceLazy;

    public DefaultAlexaCommsCoreFeatureService(@NonNull Lazy<IdentityService> lazy) {
        this.mIdentityServiceLazy = lazy;
    }

    @Override // com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService
    @NonNull
    public FeatureFlag isFeatureEnabled(@NonNull String str) {
        return new DefaultFeatureFlag(this.mIdentityServiceLazy.mo358get(), str);
    }
}
