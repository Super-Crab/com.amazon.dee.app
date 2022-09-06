package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.configuration.FeatureServiceConfiguration;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {BaseModule.class, NetworkModule.class, DatabaseModule.class, RepositoryModule.class, ImplementationModule.class})
@Singleton
/* loaded from: classes7.dex */
public interface FeatureServiceComponent {
    FeatureServiceV2 getFeatureService();

    FeatureServiceConfiguration getTestConfiguration();
}
