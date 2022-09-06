package com.amazon.commscore.commsidentity.repo.mapper;

import dagger.internal.Factory;
/* loaded from: classes12.dex */
public final class NameMapper_Factory implements Factory<NameMapper> {
    private static final NameMapper_Factory INSTANCE = new NameMapper_Factory();

    public static NameMapper_Factory create() {
        return INSTANCE;
    }

    public static NameMapper newNameMapper() {
        return new NameMapper();
    }

    public static NameMapper provideInstance() {
        return new NameMapper();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NameMapper mo10268get() {
        return provideInstance();
    }
}
