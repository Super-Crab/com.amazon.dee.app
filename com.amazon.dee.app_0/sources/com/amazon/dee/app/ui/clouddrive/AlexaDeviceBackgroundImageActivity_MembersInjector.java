package com.amazon.dee.app.ui.clouddrive;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.dee.app.services.alexadevicebackground.BackgroundImageService;
import com.amazon.dee.app.services.photos.PhotoService;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AlexaDeviceBackgroundImageActivity_MembersInjector implements MembersInjector<AlexaDeviceBackgroundImageActivity> {
    private final Provider<BackgroundImageService> deviceBackgroundImageServiceProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<PhotoService> photoServiceProvider;

    public AlexaDeviceBackgroundImageActivity_MembersInjector(Provider<IdentityService> provider, Provider<PhotoService> provider2, Provider<BackgroundImageService> provider3, Provider<MetricsService> provider4) {
        this.identityServiceProvider = provider;
        this.photoServiceProvider = provider2;
        this.deviceBackgroundImageServiceProvider = provider3;
        this.metricsServiceProvider = provider4;
    }

    public static MembersInjector<AlexaDeviceBackgroundImageActivity> create(Provider<IdentityService> provider, Provider<PhotoService> provider2, Provider<BackgroundImageService> provider3, Provider<MetricsService> provider4) {
        return new AlexaDeviceBackgroundImageActivity_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectDeviceBackgroundImageService(AlexaDeviceBackgroundImageActivity alexaDeviceBackgroundImageActivity, Lazy<BackgroundImageService> lazy) {
        alexaDeviceBackgroundImageActivity.deviceBackgroundImageService = lazy;
    }

    public static void injectIdentityService(AlexaDeviceBackgroundImageActivity alexaDeviceBackgroundImageActivity, Lazy<IdentityService> lazy) {
        alexaDeviceBackgroundImageActivity.identityService = lazy;
    }

    public static void injectMetricsService(AlexaDeviceBackgroundImageActivity alexaDeviceBackgroundImageActivity, Lazy<MetricsService> lazy) {
        alexaDeviceBackgroundImageActivity.metricsService = lazy;
    }

    public static void injectPhotoService(AlexaDeviceBackgroundImageActivity alexaDeviceBackgroundImageActivity, Lazy<PhotoService> lazy) {
        alexaDeviceBackgroundImageActivity.photoService = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlexaDeviceBackgroundImageActivity alexaDeviceBackgroundImageActivity) {
        injectIdentityService(alexaDeviceBackgroundImageActivity, DoubleCheck.lazy(this.identityServiceProvider));
        injectPhotoService(alexaDeviceBackgroundImageActivity, DoubleCheck.lazy(this.photoServiceProvider));
        injectDeviceBackgroundImageService(alexaDeviceBackgroundImageActivity, DoubleCheck.lazy(this.deviceBackgroundImageServiceProvider));
        injectMetricsService(alexaDeviceBackgroundImageActivity, DoubleCheck.lazy(this.metricsServiceProvider));
    }
}
