package com.amazon.alexa.featureservice.dependencies;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class BaseModule_ProvidesContextFactory implements Factory<Context> {
    private final BaseModule module;

    public BaseModule_ProvidesContextFactory(BaseModule baseModule) {
        this.module = baseModule;
    }

    public static BaseModule_ProvidesContextFactory create(BaseModule baseModule) {
        return new BaseModule_ProvidesContextFactory(baseModule);
    }

    public static Context provideInstance(BaseModule baseModule) {
        return proxyProvidesContext(baseModule);
    }

    public static Context proxyProvidesContext(BaseModule baseModule) {
        return (Context) Preconditions.checkNotNull(baseModule.providesContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
