package com.amazon.commscore.commsidentity.repo.mapper;

import dagger.internal.Factory;
/* loaded from: classes12.dex */
public final class AccountForDirectedIdMapper_Factory implements Factory<AccountForDirectedIdMapper> {
    private static final AccountForDirectedIdMapper_Factory INSTANCE = new AccountForDirectedIdMapper_Factory();

    public static AccountForDirectedIdMapper_Factory create() {
        return INSTANCE;
    }

    public static AccountForDirectedIdMapper newAccountForDirectedIdMapper() {
        return new AccountForDirectedIdMapper();
    }

    public static AccountForDirectedIdMapper provideInstance() {
        return new AccountForDirectedIdMapper();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccountForDirectedIdMapper mo10268get() {
        return provideInstance();
    }
}
