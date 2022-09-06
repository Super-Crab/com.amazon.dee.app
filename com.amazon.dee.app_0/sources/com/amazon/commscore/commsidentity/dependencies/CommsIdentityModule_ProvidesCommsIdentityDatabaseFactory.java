package com.amazon.commscore.commsidentity.dependencies;

import android.content.Context;
import com.amazon.commscore.commsidentity.database.roomdb.CommsCoreIdentityDatabase;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsIdentityModule_ProvidesCommsIdentityDatabaseFactory implements Factory<CommsCoreIdentityDatabase> {
    private final Provider<Context> contextProvider;
    private final CommsIdentityModule module;

    public CommsIdentityModule_ProvidesCommsIdentityDatabaseFactory(CommsIdentityModule commsIdentityModule, Provider<Context> provider) {
        this.module = commsIdentityModule;
        this.contextProvider = provider;
    }

    public static CommsIdentityModule_ProvidesCommsIdentityDatabaseFactory create(CommsIdentityModule commsIdentityModule, Provider<Context> provider) {
        return new CommsIdentityModule_ProvidesCommsIdentityDatabaseFactory(commsIdentityModule, provider);
    }

    public static CommsCoreIdentityDatabase provideInstance(CommsIdentityModule commsIdentityModule, Provider<Context> provider) {
        return proxyProvidesCommsIdentityDatabase(commsIdentityModule, provider.mo10268get());
    }

    public static CommsCoreIdentityDatabase proxyProvidesCommsIdentityDatabase(CommsIdentityModule commsIdentityModule, Context context) {
        return (CommsCoreIdentityDatabase) Preconditions.checkNotNull(commsIdentityModule.providesCommsIdentityDatabase(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsCoreIdentityDatabase mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
