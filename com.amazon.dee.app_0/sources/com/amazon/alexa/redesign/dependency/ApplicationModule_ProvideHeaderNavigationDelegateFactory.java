package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.redesign.header.HeaderNavigationDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideHeaderNavigationDelegateFactory implements Factory<HeaderNavigationDelegate> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideHeaderNavigationDelegateFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideHeaderNavigationDelegateFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideHeaderNavigationDelegateFactory(applicationModule);
    }

    public static HeaderNavigationDelegate provideInstance(ApplicationModule applicationModule) {
        return proxyProvideHeaderNavigationDelegate(applicationModule);
    }

    public static HeaderNavigationDelegate proxyProvideHeaderNavigationDelegate(ApplicationModule applicationModule) {
        return (HeaderNavigationDelegate) Preconditions.checkNotNull(applicationModule.provideHeaderNavigationDelegate(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HeaderNavigationDelegate mo10268get() {
        return provideInstance(this.module);
    }
}
