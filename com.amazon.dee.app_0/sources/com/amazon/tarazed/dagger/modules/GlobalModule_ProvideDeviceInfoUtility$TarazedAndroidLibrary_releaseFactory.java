package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class GlobalModule_ProvideDeviceInfoUtility$TarazedAndroidLibrary_releaseFactory implements Factory<DeviceInfoUtility> {
    private final GlobalModule module;

    public GlobalModule_ProvideDeviceInfoUtility$TarazedAndroidLibrary_releaseFactory(GlobalModule globalModule) {
        this.module = globalModule;
    }

    public static GlobalModule_ProvideDeviceInfoUtility$TarazedAndroidLibrary_releaseFactory create(GlobalModule globalModule) {
        return new GlobalModule_ProvideDeviceInfoUtility$TarazedAndroidLibrary_releaseFactory(globalModule);
    }

    public static DeviceInfoUtility provideInstance(GlobalModule globalModule) {
        return proxyProvideDeviceInfoUtility$TarazedAndroidLibrary_release(globalModule);
    }

    public static DeviceInfoUtility proxyProvideDeviceInfoUtility$TarazedAndroidLibrary_release(GlobalModule globalModule) {
        return (DeviceInfoUtility) Preconditions.checkNotNull(globalModule.provideDeviceInfoUtility$TarazedAndroidLibrary_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceInfoUtility mo10268get() {
        return provideInstance(this.module);
    }
}
