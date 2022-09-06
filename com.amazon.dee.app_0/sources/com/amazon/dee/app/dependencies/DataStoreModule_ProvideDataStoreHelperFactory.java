package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.dee.app.services.datastore.DataStoreHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DataStoreModule_ProvideDataStoreHelperFactory implements Factory<DataStoreHelper> {
    private final Provider<Context> contextProvider;
    private final DataStoreModule module;

    public DataStoreModule_ProvideDataStoreHelperFactory(DataStoreModule dataStoreModule, Provider<Context> provider) {
        this.module = dataStoreModule;
        this.contextProvider = provider;
    }

    public static DataStoreModule_ProvideDataStoreHelperFactory create(DataStoreModule dataStoreModule, Provider<Context> provider) {
        return new DataStoreModule_ProvideDataStoreHelperFactory(dataStoreModule, provider);
    }

    public static DataStoreHelper provideInstance(DataStoreModule dataStoreModule, Provider<Context> provider) {
        return proxyProvideDataStoreHelper(dataStoreModule, provider.mo10268get());
    }

    public static DataStoreHelper proxyProvideDataStoreHelper(DataStoreModule dataStoreModule, Context context) {
        return (DataStoreHelper) Preconditions.checkNotNull(dataStoreModule.provideDataStoreHelper(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DataStoreHelper mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
