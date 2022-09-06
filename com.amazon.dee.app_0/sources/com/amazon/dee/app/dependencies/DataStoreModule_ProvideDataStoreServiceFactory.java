package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.services.datastore.DataStoreHelper;
import com.amazon.dee.app.services.datastore.DataStoreService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DataStoreModule_ProvideDataStoreServiceFactory implements Factory<DataStoreService> {
    private final Provider<DataStoreHelper> dataStoreHelperProvider;
    private final DataStoreModule module;

    public DataStoreModule_ProvideDataStoreServiceFactory(DataStoreModule dataStoreModule, Provider<DataStoreHelper> provider) {
        this.module = dataStoreModule;
        this.dataStoreHelperProvider = provider;
    }

    public static DataStoreModule_ProvideDataStoreServiceFactory create(DataStoreModule dataStoreModule, Provider<DataStoreHelper> provider) {
        return new DataStoreModule_ProvideDataStoreServiceFactory(dataStoreModule, provider);
    }

    public static DataStoreService provideInstance(DataStoreModule dataStoreModule, Provider<DataStoreHelper> provider) {
        return proxyProvideDataStoreService(dataStoreModule, provider.mo10268get());
    }

    public static DataStoreService proxyProvideDataStoreService(DataStoreModule dataStoreModule, DataStoreHelper dataStoreHelper) {
        return (DataStoreService) Preconditions.checkNotNull(dataStoreModule.provideDataStoreService(dataStoreHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DataStoreService mo10268get() {
        return provideInstance(this.module, this.dataStoreHelperProvider);
    }
}
