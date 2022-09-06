package com.amazon.alexa.sharing.comms.dependencies;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.network.api.HttpClient;
import com.amazon.alexa.sharing.comms.AlexaSharingClient;
import com.amazon.alexa.sharing.comms.CommsMetricsEmitter;
import com.amazon.alexa.sharing.media.clouddrive.AlexaRequestAuthenticator;
import com.amazon.alexa.sharing.media.clouddrive.MAPAuthenticatedURLConnectionFactory;
import com.amazon.alexa.sharing.media.clouddrive.SharedPreferenceEndpointsCache;
import com.amazon.alexa.sharing.media.transmitter.FileTransmitter;
import com.amazon.alexa.sharing.media.transmitter.MediaDownloadManager;
import com.amazon.alexa.sharing.media.transmitter.MediaUploadManager;
import com.amazon.alexa.sharing.sharingsdk.ContentSharingService;
import com.amazon.alexa.sharing.util.FeatureServiceUtil;
import com.amazon.alexa.sharing.util.SharedPreferenceUtils;
import com.amazon.clouddrive.auth.RequestAuthenticator;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.configuration.EndpointsCache;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtendedClient;
import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
@Module
/* loaded from: classes10.dex */
public class AlexaSharingModule {
    private static final String USER_AGENT = "com.amazon.alexa.sharing/";
    private Lazy<CommsBridgeService> commsBridgeServiceLazy;
    private Lazy<AlexaCommsCoreRemoteConfigurationService> commsConfigLazy;
    private Lazy<AlexaCommsCoreIdentityService> commsIdentityLazy;
    private Lazy<AlexaCommsCoreMetricsService> commsMetricsLazy;
    private Context context;
    private Lazy<EventBus> eventBusLazy;
    private Lazy<FeatureServiceV2> featureServiceV2Lazy;
    private Lazy<IdentityService> identityServiceLazy;

    public AlexaSharingModule(@NonNull Lazy<CommsBridgeService> lazy, @NonNull Lazy<AlexaCommsCoreIdentityService> lazy2, @NonNull Lazy<AlexaCommsCoreMetricsService> lazy3, @NonNull Lazy<AlexaCommsCoreRemoteConfigurationService> lazy4, @NonNull Lazy<FeatureServiceV2> lazy5, @NonNull Lazy<EventBus> lazy6, @NonNull Lazy<IdentityService> lazy7, @NonNull Context context) {
        this.commsBridgeServiceLazy = lazy;
        this.commsIdentityLazy = lazy2;
        this.commsMetricsLazy = lazy3;
        this.commsConfigLazy = lazy4;
        this.featureServiceV2Lazy = lazy5;
        this.eventBusLazy = lazy6;
        this.identityServiceLazy = lazy7;
        this.context = context;
    }

    @Provides
    public AccountConfiguration provideAccountConfiguration(@NonNull MAPAuthenticatedURLConnectionFactory mAPAuthenticatedURLConnectionFactory, @NonNull EndpointsCache endpointsCache, @NonNull RequestAuthenticator requestAuthenticator) {
        return new AccountConfiguration.Builder().setAuthenticatedURLConnectionFactory(mAPAuthenticatedURLConnectionFactory).setEndpointsCache(endpointsCache).setRequestAuthenticator(requestAuthenticator).build();
    }

    @Provides
    public AmazonCloudDriveExtendedClient provideAmazonCloudDriveExtendedClient(AccountConfiguration accountConfiguration, ClientConfiguration clientConfiguration) {
        return new AmazonCloudDriveExtendedClient(accountConfiguration, clientConfiguration);
    }

    @Provides
    public ClientConfiguration provideClientConfiguration(OkHttpClient okHttpClient) {
        return new ClientConfiguration.Builder().setUserAgent(USER_AGENT).setApplicationId("amzn1.application.d2530b50b1cf4631b6d12ea1de22d365").setHttpClient(okHttpClient).build();
    }

    @Provides
    public EndpointsCache provideEndpointsCache(Context context, IdentityService identityService, EventBus eventBus) {
        return new SharedPreferenceEndpointsCache(context, identityService, eventBus, 1L, TimeUnit.DAYS);
    }

    @Provides
    public OkHttpClient provideHttpClient() {
        return ((HttpClient) GeneratedOutlineSupport1.outline20(HttpClient.class)).httpClient();
    }

    @Provides
    public MAPAuthenticatedURLConnectionFactory provideMAPAuthenticatedURLConnectionFactory(Context context, IdentityService identityService) {
        return new MAPAuthenticatedURLConnectionFactory(context, identityService);
    }

    @Provides
    public RequestAuthenticator provideRequestAuthenticator(IdentityService identityService) {
        return new AlexaRequestAuthenticator(identityService);
    }

    @Provides
    public AlexaSharingClient providesAlexaSharingClient(@NonNull Lazy<CommsBridgeService> lazy, @NonNull Lazy<AlexaCommsCoreIdentityService> lazy2, @NonNull Lazy<AlexaCommsCoreMetricsService> lazy3, @NonNull Lazy<AlexaCommsCoreRemoteConfigurationService> lazy4, @NonNull Lazy<FeatureServiceV2> lazy5, @NonNull FileTransmitter fileTransmitter) {
        return new AlexaSharingClient.Builder().setBridgeService(lazy.mo358get()).setCommsMetrics(lazy3).setCommsIdentityService(lazy2).setCommsConfigService(lazy4).withMediaFileManager().withMediaDownloadManager(fileTransmitter, lazy.mo358get(), lazy3).withMediaUploadManager(fileTransmitter, lazy3).withContentSharingService(lazy3, lazy2.mo358get(), lazy5.mo358get(), this.context).withDefaultGson().withSource("AlexaSharingModule:Android").withNetworkClientConfig("AlexaSharingModule:Android", lazy4).withSocialFeedRequestHandler(fileTransmitter, lazy.mo358get(), lazy3, lazy2.mo358get(), this.context).build();
    }

    @Provides
    public CommsBridgeService providesCommsBridgeService() {
        return this.commsBridgeServiceLazy.mo358get();
    }

    @Provides
    public AlexaCommsCoreRemoteConfigurationService providesCommsConfigService() {
        return this.commsConfigLazy.mo358get();
    }

    @Provides
    public AlexaCommsCoreIdentityService providesCommsIdentityService() {
        return this.commsIdentityLazy.mo358get();
    }

    @Provides
    public AlexaCommsCoreMetricsService providesCommsMetricsService() {
        return this.commsMetricsLazy.mo358get();
    }

    @Provides
    public ContentSharingService providesContentSharingService(Lazy<AlexaCommsCoreMetricsService> lazy, FeatureServiceV2 featureServiceV2, AlexaCommsCoreIdentityService alexaCommsCoreIdentityService, Context context) {
        return new ContentSharingService(new CommsMetricsEmitter(lazy, ContentSharingService.class.getSimpleName()), alexaCommsCoreIdentityService, new FeatureServiceUtil(featureServiceV2), context);
    }

    @Provides
    public Context providesContext() {
        return this.context;
    }

    @Provides
    public EventBus providesEventBus() {
        return this.eventBusLazy.mo358get();
    }

    @Provides
    public FeatureServiceV2 providesFeatureServiceV2() {
        return this.featureServiceV2Lazy.mo358get();
    }

    @Provides
    public FileTransmitter providesFileTransmitter(AmazonCloudDriveExtendedClient amazonCloudDriveExtendedClient, Lazy<AlexaCommsCoreMetricsService> lazy) {
        return new FileTransmitter(this.context, this.identityServiceLazy.mo358get(), amazonCloudDriveExtendedClient, this.eventBusLazy.mo358get(), new SharedPreferenceUtils(), new CommsMetricsEmitter(lazy, FileTransmitter.class.getSimpleName()));
    }

    @Provides
    public IdentityService providesIdentityService() {
        return this.identityServiceLazy.mo358get();
    }

    @Provides
    public MediaDownloadManager providesMediaDownloadManager(FileTransmitter fileTransmitter, Lazy<AlexaCommsCoreMetricsService> lazy) {
        return new MediaDownloadManager(fileTransmitter, this.commsBridgeServiceLazy.mo358get(), new CommsMetricsEmitter(lazy, MediaDownloadManager.class.getSimpleName()));
    }

    @Provides
    public MediaUploadManager providesMediaUploadManager(FileTransmitter fileTransmitter, Lazy<AlexaCommsCoreMetricsService> lazy) {
        return new MediaUploadManager(fileTransmitter, new CommsMetricsEmitter(lazy, MediaUploadManager.class.getSimpleName()));
    }
}
