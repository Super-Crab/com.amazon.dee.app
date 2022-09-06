package com.amazon.dee.app.ui.clouddrive;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.services.alexadevicebackground.BackgroundImageService;
import com.amazon.dee.app.services.photos.PhotoService;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ViewBoxFragment_MembersInjector implements MembersInjector<ViewBoxFragment> {
    private final Provider<BackgroundImageService> deviceBackgroundImageServiceProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<PhotoService> photoServiceProvider;

    public ViewBoxFragment_MembersInjector(Provider<IdentityService> provider, Provider<PhotoService> provider2, Provider<EnvironmentService> provider3, Provider<BackgroundImageService> provider4, Provider<MetricsService> provider5) {
        this.identityServiceProvider = provider;
        this.photoServiceProvider = provider2;
        this.environmentServiceProvider = provider3;
        this.deviceBackgroundImageServiceProvider = provider4;
        this.metricsServiceProvider = provider5;
    }

    public static MembersInjector<ViewBoxFragment> create(Provider<IdentityService> provider, Provider<PhotoService> provider2, Provider<EnvironmentService> provider3, Provider<BackgroundImageService> provider4, Provider<MetricsService> provider5) {
        return new ViewBoxFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectDeviceBackgroundImageService(ViewBoxFragment viewBoxFragment, Lazy<BackgroundImageService> lazy) {
        viewBoxFragment.deviceBackgroundImageService = lazy;
    }

    public static void injectEnvironmentService(ViewBoxFragment viewBoxFragment, Lazy<EnvironmentService> lazy) {
        viewBoxFragment.environmentService = lazy;
    }

    public static void injectIdentityService(ViewBoxFragment viewBoxFragment, Lazy<IdentityService> lazy) {
        viewBoxFragment.identityService = lazy;
    }

    public static void injectMetricsService(ViewBoxFragment viewBoxFragment, Lazy<MetricsService> lazy) {
        viewBoxFragment.metricsService = lazy;
    }

    public static void injectPhotoService(ViewBoxFragment viewBoxFragment, Lazy<PhotoService> lazy) {
        viewBoxFragment.photoService = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ViewBoxFragment viewBoxFragment) {
        injectIdentityService(viewBoxFragment, DoubleCheck.lazy(this.identityServiceProvider));
        injectPhotoService(viewBoxFragment, DoubleCheck.lazy(this.photoServiceProvider));
        injectEnvironmentService(viewBoxFragment, DoubleCheck.lazy(this.environmentServiceProvider));
        injectDeviceBackgroundImageService(viewBoxFragment, DoubleCheck.lazy(this.deviceBackgroundImageServiceProvider));
        injectMetricsService(viewBoxFragment, DoubleCheck.lazy(this.metricsServiceProvider));
    }
}
