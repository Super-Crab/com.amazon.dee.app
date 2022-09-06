package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.photos.discovery.internal.util.FileUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideFileUtilsFactory implements Factory<FileUtils> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideFileUtilsFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideFileUtilsFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideFileUtilsFactory(discoveryModule);
    }

    public static FileUtils provideFileUtils(DiscoveryModule discoveryModule) {
        return (FileUtils) Preconditions.checkNotNull(discoveryModule.provideFileUtils(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FileUtils mo10268get() {
        return provideFileUtils(this.module);
    }
}
