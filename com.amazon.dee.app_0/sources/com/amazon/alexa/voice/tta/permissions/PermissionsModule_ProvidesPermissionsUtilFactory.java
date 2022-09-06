package com.amazon.alexa.voice.tta.permissions;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class PermissionsModule_ProvidesPermissionsUtilFactory implements Factory<PermissionsUtil> {
    private final PermissionsModule module;

    public PermissionsModule_ProvidesPermissionsUtilFactory(PermissionsModule permissionsModule) {
        this.module = permissionsModule;
    }

    public static PermissionsModule_ProvidesPermissionsUtilFactory create(PermissionsModule permissionsModule) {
        return new PermissionsModule_ProvidesPermissionsUtilFactory(permissionsModule);
    }

    public static PermissionsUtil provideInstance(PermissionsModule permissionsModule) {
        return proxyProvidesPermissionsUtil(permissionsModule);
    }

    public static PermissionsUtil proxyProvidesPermissionsUtil(PermissionsModule permissionsModule) {
        return (PermissionsUtil) Preconditions.checkNotNull(permissionsModule.providesPermissionsUtil(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PermissionsUtil mo10268get() {
        return provideInstance(this.module);
    }
}
