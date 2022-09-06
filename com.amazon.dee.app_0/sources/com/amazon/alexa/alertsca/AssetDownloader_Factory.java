package com.amazon.alexa.alertsca;

import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.io.File;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AssetDownloader_Factory implements Factory<AssetDownloader> {
    private final Provider<File> cacheDirectoryProvider;

    public AssetDownloader_Factory(Provider<File> provider) {
        this.cacheDirectoryProvider = provider;
    }

    public static AssetDownloader_Factory create(Provider<File> provider) {
        return new AssetDownloader_Factory(provider);
    }

    public static AssetDownloader newAssetDownloader(Lazy<File> lazy) {
        return new AssetDownloader(lazy);
    }

    public static AssetDownloader provideInstance(Provider<File> provider) {
        return new AssetDownloader(DoubleCheck.lazy(provider));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AssetDownloader mo10268get() {
        return provideInstance(this.cacheDirectoryProvider);
    }
}
