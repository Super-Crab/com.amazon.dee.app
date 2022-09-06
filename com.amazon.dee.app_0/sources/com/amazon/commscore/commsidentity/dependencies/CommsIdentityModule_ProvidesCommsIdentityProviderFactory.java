package com.amazon.commscore.commsidentity.dependencies;

import com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao;
import com.amazon.commscore.commsidentity.remote.client.AcmsClient;
import com.amazon.commscore.commsidentity.repo.mapper.AccountForDirectedIdMapper;
import com.amazon.commscore.commsidentity.repo.mapper.IdentityV2Mapper;
import com.amazon.commscore.commsidentity.repo.provider.CommsIdentityProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsIdentityModule_ProvidesCommsIdentityProviderFactory implements Factory<CommsIdentityProvider> {
    private final Provider<AccountForDirectedIdMapper> accountMapperProvider;
    private final Provider<AcmsClient> acmsClientProvider;
    private final Provider<CommsIdentityDao> commsIdentityDaoProvider;
    private final Provider<IdentityV2Mapper> identityMapperProvider;
    private final CommsIdentityModule module;

    public CommsIdentityModule_ProvidesCommsIdentityProviderFactory(CommsIdentityModule commsIdentityModule, Provider<AcmsClient> provider, Provider<CommsIdentityDao> provider2, Provider<IdentityV2Mapper> provider3, Provider<AccountForDirectedIdMapper> provider4) {
        this.module = commsIdentityModule;
        this.acmsClientProvider = provider;
        this.commsIdentityDaoProvider = provider2;
        this.identityMapperProvider = provider3;
        this.accountMapperProvider = provider4;
    }

    public static CommsIdentityModule_ProvidesCommsIdentityProviderFactory create(CommsIdentityModule commsIdentityModule, Provider<AcmsClient> provider, Provider<CommsIdentityDao> provider2, Provider<IdentityV2Mapper> provider3, Provider<AccountForDirectedIdMapper> provider4) {
        return new CommsIdentityModule_ProvidesCommsIdentityProviderFactory(commsIdentityModule, provider, provider2, provider3, provider4);
    }

    public static CommsIdentityProvider provideInstance(CommsIdentityModule commsIdentityModule, Provider<AcmsClient> provider, Provider<CommsIdentityDao> provider2, Provider<IdentityV2Mapper> provider3, Provider<AccountForDirectedIdMapper> provider4) {
        return proxyProvidesCommsIdentityProvider(commsIdentityModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static CommsIdentityProvider proxyProvidesCommsIdentityProvider(CommsIdentityModule commsIdentityModule, AcmsClient acmsClient, CommsIdentityDao commsIdentityDao, IdentityV2Mapper identityV2Mapper, AccountForDirectedIdMapper accountForDirectedIdMapper) {
        return (CommsIdentityProvider) Preconditions.checkNotNull(commsIdentityModule.providesCommsIdentityProvider(acmsClient, commsIdentityDao, identityV2Mapper, accountForDirectedIdMapper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsIdentityProvider mo10268get() {
        return provideInstance(this.module, this.acmsClientProvider, this.commsIdentityDaoProvider, this.identityMapperProvider, this.accountMapperProvider);
    }
}
