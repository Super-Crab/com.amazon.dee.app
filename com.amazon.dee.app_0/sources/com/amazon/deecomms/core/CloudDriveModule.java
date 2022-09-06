package com.amazon.deecomms.core;

import android.content.Context;
import android.util.Base64;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.configuration.EndpointsCache;
import com.amazon.deecomms.auth.AuthHeaderProvider;
import com.amazon.deecomms.auth.CommsRequestAuthenticator;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.media.photos.MAPAuthenticatedURLConnectionFactory;
import com.amazon.deecomms.media.photos.SharedPreferenceEndpointsCache;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import dagger.Module;
import dagger.Provides;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
@Module
/* loaded from: classes12.dex */
public class CloudDriveModule {
    private static final String USER_AGENT = "com.amazon.deecomms/";
    private static final Integer DEFAULT_MAX_RETRY_COUNT = 3;
    private static final String CDS_APPLICATION_ID = "amzn1.application.e3872649259f473dba65027a9cd7c9d7";
    public static final String BASE_64_APP_ID = Base64.encodeToString(CDS_APPLICATION_ID.getBytes(StandardCharsets.UTF_8), 2).replaceAll(Config.Compare.EQUAL_TO, "");

    @Provides
    public AccountConfiguration provideAccountConfiguration(@NonNull MAPAuthenticatedURLConnectionFactory mAPAuthenticatedURLConnectionFactory, @NonNull EndpointsCache endpointsCache, @NonNull CommsRequestAuthenticator commsRequestAuthenticator) {
        return new AccountConfiguration.Builder().setAuthenticatedURLConnectionFactory(mAPAuthenticatedURLConnectionFactory).setRequestAuthenticator(commsRequestAuthenticator).setEndpointsCache(endpointsCache).build();
    }

    @Provides
    public ClientConfiguration provideClientConfiguration(@NonNull ArcusConfig arcusConfig, @NonNull OkHttpClientWrapper okHttpClientWrapper) {
        Integer configInteger = arcusConfig.getConfigInteger(RemoteConfigKeys.CDS_ANDROID_SDK_MAX_RETRY);
        if (configInteger == null) {
            configInteger = DEFAULT_MAX_RETRY_COUNT;
        }
        return new ClientConfiguration.Builder().setUserAgent(USER_AGENT).setMaxErrorRetry(configInteger.intValue()).setApplicationId(CDS_APPLICATION_ID).setHttpClient(okHttpClientWrapper.getClient()).build();
    }

    @Provides
    public EndpointsCache provideEndpointsCache(Context context, IdentityService identityService, EventBus eventBus) {
        return new SharedPreferenceEndpointsCache(context, identityService, 1L, TimeUnit.DAYS, eventBus);
    }

    @Provides
    public MAPAuthenticatedURLConnectionFactory provideMAPAuthenticatedURLConnectionFactory(Context context, IdentityService identityService, EventBus eventBus) {
        return new MAPAuthenticatedURLConnectionFactory(context, identityService, eventBus);
    }

    @Provides
    public OkHttpClientWrapper provideOkHttpClientWrapper() {
        return new OkHttpClientWrapper(new JacksonJSONConverter(), new AuthHeaderProvider(), OkHttpClientWrapper.MESSAGING_CLIENT);
    }

    @Provides
    public CommsRequestAuthenticator provideRequestAuthenticator(ApplicationManager applicationManager) {
        return new CommsRequestAuthenticator(applicationManager.getAccountManager());
    }
}
