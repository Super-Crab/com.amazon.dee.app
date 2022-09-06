package com.amazon.commscore.dependencies;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvidesBuildTypeFactory implements Factory<String> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesBuildTypeFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesBuildTypeFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesBuildTypeFactory(applicationModule);
    }

    public static String provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesBuildType(applicationModule);
    }

    public static String proxyProvidesBuildType(ApplicationModule applicationModule) {
        return (String) Preconditions.checkNotNull(applicationModule.providesBuildType(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public String mo10268get() {
        return provideInstance(this.module);
    }
}
