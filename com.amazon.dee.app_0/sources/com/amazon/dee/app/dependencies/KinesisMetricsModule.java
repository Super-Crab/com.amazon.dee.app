package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.services.metrics.KinesisMetricsConnector;
import com.amazon.dee.app.services.metrics.kinesis.DefaultKinesisEnvironment;
import com.amazon.dee.app.services.metrics.kinesis.KinesisEnvironment;
import com.amazon.dee.app.services.metrics.kinesis.KinesisManager;
import com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient;
import com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
@Module
/* loaded from: classes12.dex */
public class KinesisMetricsModule {
    private static final String KINESIS_IDENTITY_CREDENTIALS_PROVIDER = "kinesisIdentityCredentialsProvider";

    @Provides
    public KinesisEnvironment provideKinesisEnvironment() {
        return new DefaultKinesisEnvironment();
    }

    @Provides
    @ApplicationScope
    public KinesisEventClient provideKinesisEventClient(KinesisManager kinesisManager) {
        return kinesisManager.getEventClient();
    }

    @Provides
    @ApplicationScope
    @Named(KINESIS_IDENTITY_CREDENTIALS_PROVIDER)
    public AWSCredentialsProvider provideKinesisIdentityCredentialsProvider(Context context, KinesisEnvironment kinesisEnvironment) {
        return new CognitoCachingCredentialsProvider(context, kinesisEnvironment.getKinesisMetricsIdentityPoolId(), Regions.fromName(kinesisEnvironment.getKinesisMetricsAwsRegion()));
    }

    @Provides
    @ApplicationScope
    public KinesisManager provideKinesisManager(Context context, KinesisEnvironment kinesisEnvironment, PersistentStorage.Factory factory, @Named("kinesisIdentityCredentialsProvider") AWSCredentialsProvider aWSCredentialsProvider, IdentityService identityService) {
        return KinesisManager.getOrCreateInstance(context, kinesisEnvironment.getKinesisMetricsIdentityPoolId(), kinesisEnvironment.getKinesisMetricsStreamName(), Regions.fromName(kinesisEnvironment.getKinesisMetricsAwsRegion()), factory, aWSCredentialsProvider, identityService);
    }

    @Provides
    public KinesisMetricsConnector provideKinesisMetricsConnector(Context context, Lazy<IdentityService> lazy, EnvironmentService environmentService, DeviceInformation deviceInformation, Lazy<AppSessionClient> lazy2, Lazy<KinesisEventClient> lazy3) {
        return new KinesisMetricsConnector(context, lazy, environmentService, deviceInformation, lazy2, lazy3);
    }

    @Provides
    @ApplicationScope
    public AppSessionClient provideKinesisSessionClient(KinesisManager kinesisManager) {
        return kinesisManager.getAppSessionClient();
    }
}
