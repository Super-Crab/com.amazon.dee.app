package com.amazon.alexa.smarthomecameras.dependencies.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class SmartAlertEventModule_ProvidesImageUrlFactory implements Factory<String> {
    private final SmartAlertEventModule module;

    public SmartAlertEventModule_ProvidesImageUrlFactory(SmartAlertEventModule smartAlertEventModule) {
        this.module = smartAlertEventModule;
    }

    public static SmartAlertEventModule_ProvidesImageUrlFactory create(SmartAlertEventModule smartAlertEventModule) {
        return new SmartAlertEventModule_ProvidesImageUrlFactory(smartAlertEventModule);
    }

    public static String provideInstance(SmartAlertEventModule smartAlertEventModule) {
        return proxyProvidesImageUrl(smartAlertEventModule);
    }

    public static String proxyProvidesImageUrl(SmartAlertEventModule smartAlertEventModule) {
        return (String) Preconditions.checkNotNull(smartAlertEventModule.providesImageUrl(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public String mo10268get() {
        return provideInstance(this.module);
    }
}
