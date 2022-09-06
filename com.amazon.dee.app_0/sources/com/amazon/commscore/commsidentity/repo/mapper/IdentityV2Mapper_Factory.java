package com.amazon.commscore.commsidentity.repo.mapper;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityV2Mapper_Factory implements Factory<IdentityV2Mapper> {
    private final Provider<NameMapper> nameMapperProvider;

    public IdentityV2Mapper_Factory(Provider<NameMapper> provider) {
        this.nameMapperProvider = provider;
    }

    public static IdentityV2Mapper_Factory create(Provider<NameMapper> provider) {
        return new IdentityV2Mapper_Factory(provider);
    }

    public static IdentityV2Mapper newIdentityV2Mapper(NameMapper nameMapper) {
        return new IdentityV2Mapper(nameMapper);
    }

    public static IdentityV2Mapper provideInstance(Provider<NameMapper> provider) {
        return new IdentityV2Mapper(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityV2Mapper mo10268get() {
        return provideInstance(this.nameMapperProvider);
    }
}
