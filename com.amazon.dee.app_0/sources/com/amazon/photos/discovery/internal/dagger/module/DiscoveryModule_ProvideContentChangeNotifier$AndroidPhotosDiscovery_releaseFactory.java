package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.photos.discovery.internal.observers.ContentChangeNotifier;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideContentChangeNotifier$AndroidPhotosDiscovery_releaseFactory implements Factory<ContentChangeNotifier> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideContentChangeNotifier$AndroidPhotosDiscovery_releaseFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideContentChangeNotifier$AndroidPhotosDiscovery_releaseFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideContentChangeNotifier$AndroidPhotosDiscovery_releaseFactory(discoveryModule);
    }

    public static ContentChangeNotifier provideContentChangeNotifier$AndroidPhotosDiscovery_release(DiscoveryModule discoveryModule) {
        return (ContentChangeNotifier) Preconditions.checkNotNull(discoveryModule.provideContentChangeNotifier$AndroidPhotosDiscovery_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ContentChangeNotifier mo10268get() {
        return provideContentChangeNotifier$AndroidPhotosDiscovery_release(this.module);
    }
}
