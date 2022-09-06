package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.common.util.DeviceUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesMetricsServiceFactory implements Factory<MetricsService> {
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<CommsInternal> commsInternalProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DeviceUtils> deviceUtilsProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesMetricsServiceFactory(LibraryModule libraryModule, Provider<CommsIdentityManager> provider, Provider<ApplicationManager> provider2, Provider<DeviceUtils> provider3, Provider<CommsInternal> provider4, Provider<Context> provider5) {
        this.module = libraryModule;
        this.commsIdentityManagerProvider = provider;
        this.applicationManagerProvider = provider2;
        this.deviceUtilsProvider = provider3;
        this.commsInternalProvider = provider4;
        this.contextProvider = provider5;
    }

    public static LibraryModule_ProvidesMetricsServiceFactory create(LibraryModule libraryModule, Provider<CommsIdentityManager> provider, Provider<ApplicationManager> provider2, Provider<DeviceUtils> provider3, Provider<CommsInternal> provider4, Provider<Context> provider5) {
        return new LibraryModule_ProvidesMetricsServiceFactory(libraryModule, provider, provider2, provider3, provider4, provider5);
    }

    public static MetricsService provideInstance(LibraryModule libraryModule, Provider<CommsIdentityManager> provider, Provider<ApplicationManager> provider2, Provider<DeviceUtils> provider3, Provider<CommsInternal> provider4, Provider<Context> provider5) {
        return (MetricsService) Preconditions.checkNotNull(libraryModule.providesMetricsService(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static MetricsService proxyProvidesMetricsService(LibraryModule libraryModule, CommsIdentityManager commsIdentityManager, ApplicationManager applicationManager, DeviceUtils deviceUtils, CommsInternal commsInternal, Context context) {
        return (MetricsService) Preconditions.checkNotNull(libraryModule.providesMetricsService(commsIdentityManager, applicationManager, deviceUtils, commsInternal, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsService mo10268get() {
        return provideInstance(this.module, this.commsIdentityManagerProvider, this.applicationManagerProvider, this.deviceUtilsProvider, this.commsInternalProvider, this.contextProvider);
    }
}
