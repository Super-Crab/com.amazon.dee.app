package com.amazon.dee.app.dependencies;

import android.app.Activity;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ExternalUIModule_ProvideActivityFactory implements Factory<Activity> {
    private final ExternalUIModule module;

    public ExternalUIModule_ProvideActivityFactory(ExternalUIModule externalUIModule) {
        this.module = externalUIModule;
    }

    public static ExternalUIModule_ProvideActivityFactory create(ExternalUIModule externalUIModule) {
        return new ExternalUIModule_ProvideActivityFactory(externalUIModule);
    }

    public static Activity provideInstance(ExternalUIModule externalUIModule) {
        return proxyProvideActivity(externalUIModule);
    }

    public static Activity proxyProvideActivity(ExternalUIModule externalUIModule) {
        return (Activity) Preconditions.checkNotNull(externalUIModule.provideActivity(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Activity mo10268get() {
        return provideInstance(this.module);
    }
}
