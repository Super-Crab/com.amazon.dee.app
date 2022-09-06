package com.amazon.alexa.mobilytics.dependencies;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class MobilyticsModule_ProvideContextFactory implements Factory<Context> {
    private final MobilyticsModule module;

    public MobilyticsModule_ProvideContextFactory(MobilyticsModule mobilyticsModule) {
        this.module = mobilyticsModule;
    }

    public static MobilyticsModule_ProvideContextFactory create(MobilyticsModule mobilyticsModule) {
        return new MobilyticsModule_ProvideContextFactory(mobilyticsModule);
    }

    public static Context provideInstance(MobilyticsModule mobilyticsModule) {
        return proxyProvideContext(mobilyticsModule);
    }

    public static Context proxyProvideContext(MobilyticsModule mobilyticsModule) {
        return (Context) Preconditions.checkNotNull(mobilyticsModule.provideContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
