package com.amazon.commscore.commsidentity.dependencies;

import com.amazon.commscore.commsidentity.database.dao.IdentityV2Dao;
import com.amazon.commscore.commsidentity.database.roomdb.CommsCoreIdentityDatabase;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsIdentityModule_ProvidesIdentityV2DaoFactory implements Factory<IdentityV2Dao> {
    private final Provider<CommsCoreIdentityDatabase> dbProvider;
    private final CommsIdentityModule module;

    public CommsIdentityModule_ProvidesIdentityV2DaoFactory(CommsIdentityModule commsIdentityModule, Provider<CommsCoreIdentityDatabase> provider) {
        this.module = commsIdentityModule;
        this.dbProvider = provider;
    }

    public static CommsIdentityModule_ProvidesIdentityV2DaoFactory create(CommsIdentityModule commsIdentityModule, Provider<CommsCoreIdentityDatabase> provider) {
        return new CommsIdentityModule_ProvidesIdentityV2DaoFactory(commsIdentityModule, provider);
    }

    public static IdentityV2Dao provideInstance(CommsIdentityModule commsIdentityModule, Provider<CommsCoreIdentityDatabase> provider) {
        return proxyProvidesIdentityV2Dao(commsIdentityModule, provider.mo10268get());
    }

    public static IdentityV2Dao proxyProvidesIdentityV2Dao(CommsIdentityModule commsIdentityModule, CommsCoreIdentityDatabase commsCoreIdentityDatabase) {
        return (IdentityV2Dao) Preconditions.checkNotNull(commsIdentityModule.providesIdentityV2Dao(commsCoreIdentityDatabase), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityV2Dao mo10268get() {
        return provideInstance(this.module, this.dbProvider);
    }
}
