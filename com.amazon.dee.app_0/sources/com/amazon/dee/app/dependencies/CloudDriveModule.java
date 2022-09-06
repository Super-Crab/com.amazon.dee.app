package com.amazon.dee.app.dependencies;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.photos.AlexaPhotosChooser;
import com.amazon.alexa.photos.CdsPhotosUploader;
import com.amazon.alexa.photos.PhotosAppInfoProvider;
import com.amazon.alexa.photos.PhotosFeatureGuardian;
import com.amazon.alexa.photos.UploadBundleManager;
import com.amazon.alexa.photos.api.PhotosChooser;
import com.amazon.alexa.photos.api.PhotosUploader;
import com.amazon.alexa.photos.hva.HVAManager;
import com.amazon.alexa.photos.metrics.CloudDriveMetrics;
import com.amazon.alexa.photos.util.SystemUtility;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.clouddrive.auth.RequestAuthenticator;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.configuration.EndpointsCache;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtendedClient;
import com.amazon.dee.app.R;
import com.amazon.dee.app.services.clouddrive.AlexaRequestAuthenticator;
import com.amazon.dee.app.services.clouddrive.MAPAuthenticatedURLConnectionFactory;
import com.amazon.dee.app.services.clouddrive.SharedPreferenceEndpointsCache;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.main.MainActivity;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
@Module
/* loaded from: classes12.dex */
public class CloudDriveModule {
    private static final String TAG = Log.tag(CloudDriveModule.class);
    private static final String USER_AGENT = "com.amazon.alexa.photos/";

    @Provides
    @ApplicationScope
    public AccountConfiguration provideAccountConfiguration(@NonNull MAPAuthenticatedURLConnectionFactory mAPAuthenticatedURLConnectionFactory, @NonNull EndpointsCache endpointsCache, @NonNull RequestAuthenticator requestAuthenticator) {
        return new AccountConfiguration.Builder().setAuthenticatedURLConnectionFactory(mAPAuthenticatedURLConnectionFactory).setEndpointsCache(endpointsCache).setRequestAuthenticator(requestAuthenticator).build();
    }

    @Provides
    @ApplicationScope
    public AmazonCloudDriveExtendedClient provideAmazonCloudDriveExtendedClient(AccountConfiguration accountConfiguration, ClientConfiguration clientConfiguration) {
        return new AmazonCloudDriveExtendedClient(accountConfiguration, clientConfiguration);
    }

    @Provides
    @ApplicationScope
    public ClientConfiguration provideClientConfiguration(OkHttpClient okHttpClient, Context context) {
        return new ClientConfiguration.Builder().setUserAgent(USER_AGENT).setApplicationId(context.getString(R.string.APP_ID)).setHttpClient(okHttpClient).build();
    }

    @Provides
    @ApplicationScope
    public CloudDriveMetrics provideCloudDriveMetrics(Lazy<Mobilytics> lazy) {
        return new CloudDriveMetrics(lazy);
    }

    @Provides
    @ApplicationScope
    public EndpointsCache provideEndpointsCache(Context context, IdentityService identityService, EventBus eventBus, PersistentStorage.Factory factory) {
        SharedPreferenceEndpointsCache sharedPreferenceEndpointsCache = new SharedPreferenceEndpointsCache(context, factory, identityService, eventBus, 1L, TimeUnit.DAYS);
        sharedPreferenceEndpointsCache.setEndpointsToBuildConfigDefaults();
        return sharedPreferenceEndpointsCache;
    }

    @Provides
    @ApplicationScope
    public HVAManager provideHVAManager(Lazy<CloudDriveMetrics> lazy) {
        return new HVAManager(ComponentRegistry.getInstance(), lazy);
    }

    @Provides
    @ApplicationScope
    public MAPAuthenticatedURLConnectionFactory provideMAPAuthenticatedURLConnectionFactory(Context context, IdentityService identityService) {
        return new MAPAuthenticatedURLConnectionFactory(context, identityService);
    }

    @Provides
    @ApplicationScope
    public PhotosAppInfoProvider providePhotosApplicationIdProvider(Context context) {
        return new PhotosAppInfoProvider(context, USER_AGENT);
    }

    @Provides
    @ApplicationScope
    public PhotosChooser providePhotosChooser() {
        return new AlexaPhotosChooser();
    }

    @Provides
    @ApplicationScope
    public PhotosFeatureGuardian providePhotosFeatureGuardian() {
        return new PhotosFeatureGuardian(ComponentRegistry.getInstance().getLazy(FeatureServiceV2.class));
    }

    @Provides
    @ApplicationScope
    public UploadBundleManager providePhotosUploadBundleManager(@NonNull Context context, @NonNull HVAManager hVAManager, @NonNull PhotosAppInfoProvider photosAppInfoProvider, @NonNull Lazy<CloudDriveMetrics> lazy, @NonNull Lazy<MAPAuthenticatedURLConnectionFactory> lazy2, @NonNull Lazy<PhotosFeatureGuardian> lazy3) {
        return new UploadBundleManager(context, photosAppInfoProvider, lazy, ComponentRegistry.getInstance(), MainActivity.class, lazy2, lazy3);
    }

    @Provides
    @ApplicationScope
    public PhotosUploader providePhotosUploader(Context context, Lazy<AmazonCloudDriveExtendedClient> lazy, Lazy<UploadBundleManager> lazy2, Lazy<PhotosFeatureGuardian> lazy3, Lazy<CloudDriveMetrics> lazy4, Lazy<SystemUtility> lazy5) {
        return new CdsPhotosUploader(context, lazy, lazy2, lazy3, ComponentRegistry.getInstance(), lazy4, lazy5);
    }

    @Provides
    @ApplicationScope
    public RequestAuthenticator provideRequestAuthenticator(IdentityService identityService) {
        return new AlexaRequestAuthenticator(identityService);
    }

    @Provides
    @ApplicationScope
    public SystemUtility provideSystemUtility() {
        return new SystemUtility();
    }
}
