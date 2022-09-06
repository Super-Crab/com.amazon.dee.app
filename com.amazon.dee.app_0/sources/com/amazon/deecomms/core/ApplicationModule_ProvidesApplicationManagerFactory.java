package com.amazon.deecomms.core;

import com.amazon.deecomms.common.ApplicationManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvidesApplicationManagerFactory implements Factory<ApplicationManager> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesApplicationManagerFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesApplicationManagerFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesApplicationManagerFactory(applicationModule);
    }

    public static ApplicationManager provideInstance(ApplicationModule applicationModule) {
        return (ApplicationManager) Preconditions.checkNotNull(applicationModule.providesApplicationManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ApplicationManager proxyProvidesApplicationManager(ApplicationModule applicationModule) {
        return (ApplicationManager) Preconditions.checkNotNull(applicationModule.providesApplicationManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ApplicationManager mo10268get() {
        return provideInstance(this.module);
    }
}
