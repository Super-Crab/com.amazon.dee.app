package com.amazon.photos.discovery.internal.dagger.module;

import android.content.SharedPreferences;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.photos.discovery.internal.dedupe.filter.FilterEvents;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DataModule_ProvideFilterEventsFactory implements Factory<FilterEvents> {
    private final Provider<Logger> loggerProvider;
    private final DataModule module;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public DataModule_ProvideFilterEventsFactory(DataModule dataModule, Provider<SharedPreferences> provider, Provider<Logger> provider2) {
        this.module = dataModule;
        this.sharedPreferencesProvider = provider;
        this.loggerProvider = provider2;
    }

    public static DataModule_ProvideFilterEventsFactory create(DataModule dataModule, Provider<SharedPreferences> provider, Provider<Logger> provider2) {
        return new DataModule_ProvideFilterEventsFactory(dataModule, provider, provider2);
    }

    public static FilterEvents provideFilterEvents(DataModule dataModule, SharedPreferences sharedPreferences, Logger logger) {
        return (FilterEvents) Preconditions.checkNotNull(dataModule.provideFilterEvents(sharedPreferences, logger), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FilterEvents mo10268get() {
        return provideFilterEvents(this.module, this.sharedPreferencesProvider.mo10268get(), this.loggerProvider.mo10268get());
    }
}
