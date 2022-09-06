package com.amazon.dee.app.dependencies;

import android.content.Context;
import android.webkit.CookieManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.services.clouddrive.CloudDriveService;
import com.amazon.dee.app.services.coral.AcceptHeaderRequestInterceptor;
import com.amazon.dee.app.services.coral.AcceptLanguageRequestInterceptor;
import com.amazon.dee.app.services.coral.CookieAuthenticationRequestInterceptor;
import com.amazon.dee.app.services.coral.HttpCoralAuthenticationResponseInterceptor;
import com.amazon.dee.app.services.coral.HttpCoralDefaultInterceptor;
import com.amazon.dee.app.services.coral.TimestampHeaderRequestInterceptor;
import com.amazon.dee.app.services.coral.UserAgentRequestInterceptor;
import com.amazon.dee.app.services.photos.AlexaPhotosBackgroundService;
import com.amazon.dee.app.services.photos.AlexaPhotosBackgroundServiceUrlResolver;
import com.amazon.dee.app.services.photos.PhotoService;
import com.amazon.dee.app.services.photos.PhotoServiceFactory;
import com.dee.app.http.HttpCoralService;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsServiceV2;
import com.google.gson.Gson;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
@Module
/* loaded from: classes12.dex */
public class PhotoServiceModule {
    @Provides
    @ApplicationScope
    public AlexaPhotosBackgroundService provideAlexaPhotosBackgroundService(Context context, Lazy<CookieManager> lazy, OkHttpClient okHttpClient, Gson gson, Lazy<IdentityService> lazy2, Lazy<AccountService> lazy3, Lazy<RoutingService> lazy4, AlexaPhotosBackgroundServiceUrlResolver alexaPhotosBackgroundServiceUrlResolver, Lazy<MetricsService> lazy5, MetricsServiceV2 metricsServiceV2, Lazy<Mobilytics> lazy6, Lazy<FeatureServiceV2> lazy7, Lazy<EnvironmentService> lazy8) {
        HttpCoralService httpCoralService = new HttpCoralService(okHttpClient, gson, alexaPhotosBackgroundServiceUrlResolver, metricsServiceV2);
        HttpCoralDefaultInterceptor httpCoralDefaultInterceptor = new HttpCoralDefaultInterceptor();
        httpCoralService.addResponseInterceptor(new HttpCoralAuthenticationResponseInterceptor(okHttpClient, lazy2, lazy3, lazy4, lazy6));
        httpCoralService.addResponseInterceptor(httpCoralDefaultInterceptor);
        httpCoralService.addRequestInterceptor(new UserAgentRequestInterceptor());
        httpCoralService.addRequestInterceptor(new AcceptHeaderRequestInterceptor());
        httpCoralService.addRequestInterceptor(new TimestampHeaderRequestInterceptor());
        httpCoralService.addRequestInterceptor(new CookieAuthenticationRequestInterceptor(lazy, lazy2, lazy6, lazy7, lazy8));
        httpCoralService.addRequestInterceptor(new AcceptLanguageRequestInterceptor());
        httpCoralService.addRequestInterceptor(httpCoralDefaultInterceptor);
        return new AlexaPhotosBackgroundService(context, httpCoralService);
    }

    @Provides
    @ApplicationScope
    public AlexaPhotosBackgroundServiceUrlResolver provideAlexaPhotosBackgroundServiceUrlResolver(IdentityService identityService) {
        return new AlexaPhotosBackgroundServiceUrlResolver(identityService);
    }

    @Provides
    @ApplicationScope
    public PhotoService providePhotoService(PhotoServiceFactory photoServiceFactory) {
        return photoServiceFactory.getPhotoService();
    }

    @Provides
    @ApplicationScope
    public PhotoServiceFactory providePhotoServiceFactory(AlexaPhotosBackgroundService alexaPhotosBackgroundService, CloudDriveService cloudDriveService, IdentityService identityService, EventBus eventBus) {
        return new PhotoServiceFactory(alexaPhotosBackgroundService, cloudDriveService, identityService, eventBus);
    }
}
