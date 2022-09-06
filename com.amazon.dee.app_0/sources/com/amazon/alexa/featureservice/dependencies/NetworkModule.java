package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.featureservice.dependencies.annotation.FeatureServiceOkHttpClient;
import com.amazon.alexa.featureservice.remote.client.FeatureServiceClient;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
@Module
/* loaded from: classes7.dex */
public class NetworkModule {
    private OkHttpClient okHttpClient;

    public NetworkModule(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public FeatureServiceClient providesFeatureServiceClient(@FeatureServiceOkHttpClient OkHttpClient okHttpClient, EnvironmentService environmentService) {
        return new FeatureServiceClient(okHttpClient, environmentService);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @FeatureServiceOkHttpClient
    public OkHttpClient providesOkHttpClient() {
        return this.okHttpClient;
    }
}
