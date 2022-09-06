package com.amazon.photos.discovery.internal.dagger.module;

import android.content.SharedPreferences;
import com.amazon.photos.discovery.internal.dedupe.filter.DedupeFilter;
import com.amazon.photos.discovery.internal.dedupe.filter.FilterUtils;
import com.amazon.photos.discovery.internal.util.FileUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DataModule_ProvideDedupeFilterFactory implements Factory<DedupeFilter> {
    private final Provider<FileUtils> fileUtilsProvider;
    private final DataModule module;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<FilterUtils> utilsProvider;

    public DataModule_ProvideDedupeFilterFactory(DataModule dataModule, Provider<FileUtils> provider, Provider<SharedPreferences> provider2, Provider<FilterUtils> provider3) {
        this.module = dataModule;
        this.fileUtilsProvider = provider;
        this.sharedPreferencesProvider = provider2;
        this.utilsProvider = provider3;
    }

    public static DataModule_ProvideDedupeFilterFactory create(DataModule dataModule, Provider<FileUtils> provider, Provider<SharedPreferences> provider2, Provider<FilterUtils> provider3) {
        return new DataModule_ProvideDedupeFilterFactory(dataModule, provider, provider2, provider3);
    }

    public static DedupeFilter provideDedupeFilter(DataModule dataModule, FileUtils fileUtils, SharedPreferences sharedPreferences, FilterUtils filterUtils) {
        return (DedupeFilter) Preconditions.checkNotNull(dataModule.provideDedupeFilter(fileUtils, sharedPreferences, filterUtils), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DedupeFilter mo10268get() {
        return provideDedupeFilter(this.module, this.fileUtilsProvider.mo10268get(), this.sharedPreferencesProvider.mo10268get(), this.utilsProvider.mo10268get());
    }
}
