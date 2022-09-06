package com.amazon.photos.discovery.internal.dagger.module;

import android.content.ContentResolver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DataModule_ProvideMediaStoreContentResolverFactory implements Factory<ContentResolver> {
    private final DataModule module;

    public DataModule_ProvideMediaStoreContentResolverFactory(DataModule dataModule) {
        this.module = dataModule;
    }

    public static DataModule_ProvideMediaStoreContentResolverFactory create(DataModule dataModule) {
        return new DataModule_ProvideMediaStoreContentResolverFactory(dataModule);
    }

    public static ContentResolver provideMediaStoreContentResolver(DataModule dataModule) {
        return (ContentResolver) Preconditions.checkNotNull(dataModule.provideMediaStoreContentResolver(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ContentResolver mo10268get() {
        return provideMediaStoreContentResolver(this.module);
    }
}
