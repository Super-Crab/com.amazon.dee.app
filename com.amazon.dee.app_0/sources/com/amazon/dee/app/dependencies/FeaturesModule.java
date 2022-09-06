package com.amazon.dee.app.dependencies;

import androidx.annotation.NonNull;
import com.amazon.alexa.feature.provider.api.FeatureStore;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.features.FeatureConstraints;
import com.amazon.alexa.protocols.features.FeatureFilter;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.dee.app.services.features.DefaultFeatureConstraints;
import com.amazon.dee.app.services.features.DefaultFeatureQuery;
import com.amazon.dee.app.services.features.VoiceIngressFeatureFilter;
import com.amazon.deecomms.core.decoupling.AlexaCommsService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import java.util.Set;
@Module
/* loaded from: classes12.dex */
public class FeaturesModule {
    @Provides
    @ApplicationScope
    @IntoSet
    public FeatureFilter provideCommsFeatureFilter(@NonNull AlexaCommsService alexaCommsService) {
        return alexaCommsService.getCommsFeatureFilter();
    }

    @Provides
    @ApplicationScope
    public FeatureConstraints provideFeatureConstraints(Set<FeatureFilter> set, EnvironmentService environmentService) {
        return new DefaultFeatureConstraints(set, environmentService);
    }

    @Provides
    @ApplicationScope
    public FeatureQuery provideFeatureQuery(Lazy<IdentityService> lazy) {
        return new DefaultFeatureQuery(lazy);
    }

    @Provides
    @ApplicationScope
    public FeatureStore provideFeatureStore() {
        return (FeatureStore) GeneratedOutlineSupport1.outline20(FeatureStore.class);
    }

    @Provides
    @ApplicationScope
    @IntoSet
    public FeatureFilter provideVoiceIngressFeatureFilter(Lazy<VoiceService> lazy) {
        return new VoiceIngressFeatureFilter(lazy);
    }
}
