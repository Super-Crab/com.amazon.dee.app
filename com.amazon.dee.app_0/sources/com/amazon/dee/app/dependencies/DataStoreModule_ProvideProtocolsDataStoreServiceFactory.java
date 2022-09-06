package com.amazon.dee.app.dependencies;

import com.amazon.alexa.protocols.datastore.DataStoreService;
import com.amazon.dee.app.services.datastore.DataStoreHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DataStoreModule_ProvideProtocolsDataStoreServiceFactory implements Factory<DataStoreService> {
    private final Provider<DataStoreHelper> dataStoreHelperProvider;
    private final DataStoreModule module;

    public DataStoreModule_ProvideProtocolsDataStoreServiceFactory(DataStoreModule dataStoreModule, Provider<DataStoreHelper> provider) {
        this.module = dataStoreModule;
        this.dataStoreHelperProvider = provider;
    }

    public static DataStoreModule_ProvideProtocolsDataStoreServiceFactory create(DataStoreModule dataStoreModule, Provider<DataStoreHelper> provider) {
        return new DataStoreModule_ProvideProtocolsDataStoreServiceFactory(dataStoreModule, provider);
    }

    public static DataStoreService provideInstance(DataStoreModule dataStoreModule, Provider<DataStoreHelper> provider) {
        return proxyProvideProtocolsDataStoreService(dataStoreModule, provider.mo10268get());
    }

    public static DataStoreService proxyProvideProtocolsDataStoreService(DataStoreModule dataStoreModule, DataStoreHelper dataStoreHelper) {
        return (DataStoreService) Preconditions.checkNotNull(dataStoreModule.provideProtocolsDataStoreService(dataStoreHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DataStoreService mo10268get() {
        return provideInstance(this.module, this.dataStoreHelperProvider);
    }
}
