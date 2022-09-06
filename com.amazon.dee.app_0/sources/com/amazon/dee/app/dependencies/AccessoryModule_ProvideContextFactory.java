package com.amazon.dee.app.dependencies;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class AccessoryModule_ProvideContextFactory implements Factory<Context> {
    private final AccessoryModule module;

    public AccessoryModule_ProvideContextFactory(AccessoryModule accessoryModule) {
        this.module = accessoryModule;
    }

    public static AccessoryModule_ProvideContextFactory create(AccessoryModule accessoryModule) {
        return new AccessoryModule_ProvideContextFactory(accessoryModule);
    }

    public static Context provideInstance(AccessoryModule accessoryModule) {
        return proxyProvideContext(accessoryModule);
    }

    public static Context proxyProvideContext(AccessoryModule accessoryModule) {
        return (Context) Preconditions.checkNotNull(accessoryModule.provideContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
