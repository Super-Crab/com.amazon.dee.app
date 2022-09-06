package com.amazon.commscore.commsidentity.repo.repository;

import com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao;
import com.amazon.commscore.commsidentity.remote.client.AcmsClient;
import com.amazon.commscore.commsidentity.repo.mapper.AccountForDirectedIdMapper;
import com.amazon.commscore.commsidentity.repo.mapper.IdentityV2Mapper;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsIdentityRepo_Factory implements Factory<CommsIdentityRepo> {
    private final Provider<AccountForDirectedIdMapper> accountForDirectedIdMapperProvider;
    private final Provider<AcmsClient> acmsClientProvider;
    private final Provider<CommsIdentityDao> commsIdentityDaoProvider;
    private final Provider<IdentityV2Mapper> identityV2MapperProvider;

    public CommsIdentityRepo_Factory(Provider<AcmsClient> provider, Provider<CommsIdentityDao> provider2, Provider<IdentityV2Mapper> provider3, Provider<AccountForDirectedIdMapper> provider4) {
        this.acmsClientProvider = provider;
        this.commsIdentityDaoProvider = provider2;
        this.identityV2MapperProvider = provider3;
        this.accountForDirectedIdMapperProvider = provider4;
    }

    public static CommsIdentityRepo_Factory create(Provider<AcmsClient> provider, Provider<CommsIdentityDao> provider2, Provider<IdentityV2Mapper> provider3, Provider<AccountForDirectedIdMapper> provider4) {
        return new CommsIdentityRepo_Factory(provider, provider2, provider3, provider4);
    }

    public static CommsIdentityRepo newCommsIdentityRepo(AcmsClient acmsClient, CommsIdentityDao commsIdentityDao, IdentityV2Mapper identityV2Mapper, AccountForDirectedIdMapper accountForDirectedIdMapper) {
        return new CommsIdentityRepo(acmsClient, commsIdentityDao, identityV2Mapper, accountForDirectedIdMapper);
    }

    public static CommsIdentityRepo provideInstance(Provider<AcmsClient> provider, Provider<CommsIdentityDao> provider2, Provider<IdentityV2Mapper> provider3, Provider<AccountForDirectedIdMapper> provider4) {
        return new CommsIdentityRepo(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsIdentityRepo mo10268get() {
        return provideInstance(this.acmsClientProvider, this.commsIdentityDaoProvider, this.identityV2MapperProvider, this.accountForDirectedIdMapperProvider);
    }
}
