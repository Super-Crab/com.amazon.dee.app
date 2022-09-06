package com.amazon.deecomms.core;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvidesDeviceNameTemplateFactory implements Factory<String> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesDeviceNameTemplateFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesDeviceNameTemplateFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesDeviceNameTemplateFactory(applicationModule);
    }

    public static String provideInstance(ApplicationModule applicationModule) {
        return (String) Preconditions.checkNotNull(applicationModule.providesDeviceNameTemplate(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static String proxyProvidesDeviceNameTemplate(ApplicationModule applicationModule) {
        return (String) Preconditions.checkNotNull(applicationModule.providesDeviceNameTemplate(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public String mo10268get() {
        return provideInstance(this.module);
    }
}
