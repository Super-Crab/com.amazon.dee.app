package com.amazon.commscore.featureflag.dependencies;

import androidx.annotation.NonNull;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService;
import com.amazon.commscore.featureflag.DefaultAlexaCommsCoreFeatureService;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes12.dex */
public class FeatureFlagModule {
    private Lazy<IdentityService> identityServiceLazy;

    public FeatureFlagModule(@NonNull Lazy<IdentityService> lazy) {
        this.identityServiceLazy = lazy;
    }

    @Provides
    @Singleton
    public AlexaCommsCoreFeatureService providesFeatureService() {
        return new DefaultAlexaCommsCoreFeatureService(this.identityServiceLazy);
    }
}
