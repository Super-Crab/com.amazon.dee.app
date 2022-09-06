package com.amazon.commscore.commsidentity.dependencies;

import com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao;
import com.amazon.commscore.commsidentity.database.roomdb.CommsCoreIdentityDatabase;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsIdentityModule_ProvidesCommsIdentityDaoFactory implements Factory<CommsIdentityDao> {
    private final Provider<CommsCoreIdentityDatabase> dbProvider;
    private final CommsIdentityModule module;

    public CommsIdentityModule_ProvidesCommsIdentityDaoFactory(CommsIdentityModule commsIdentityModule, Provider<CommsCoreIdentityDatabase> provider) {
        this.module = commsIdentityModule;
        this.dbProvider = provider;
    }

    public static CommsIdentityModule_ProvidesCommsIdentityDaoFactory create(CommsIdentityModule commsIdentityModule, Provider<CommsCoreIdentityDatabase> provider) {
        return new CommsIdentityModule_ProvidesCommsIdentityDaoFactory(commsIdentityModule, provider);
    }

    public static CommsIdentityDao provideInstance(CommsIdentityModule commsIdentityModule, Provider<CommsCoreIdentityDatabase> provider) {
        return proxyProvidesCommsIdentityDao(commsIdentityModule, provider.mo10268get());
    }

    public static CommsIdentityDao proxyProvidesCommsIdentityDao(CommsIdentityModule commsIdentityModule, CommsCoreIdentityDatabase commsCoreIdentityDatabase) {
        return (CommsIdentityDao) Preconditions.checkNotNull(commsIdentityModule.providesCommsIdentityDao(commsCoreIdentityDatabase), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsIdentityDao mo10268get() {
        return provideInstance(this.module, this.dbProvider);
    }
}
