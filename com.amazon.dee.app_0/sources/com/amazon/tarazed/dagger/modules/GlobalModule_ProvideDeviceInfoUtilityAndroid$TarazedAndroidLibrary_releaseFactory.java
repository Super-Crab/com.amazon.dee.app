package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class GlobalModule_ProvideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_releaseFactory implements Factory<DeviceInfoUtilityAndroid> {
    private final GlobalModule module;

    public GlobalModule_ProvideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_releaseFactory(GlobalModule globalModule) {
        this.module = globalModule;
    }

    public static GlobalModule_ProvideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_releaseFactory create(GlobalModule globalModule) {
        return new GlobalModule_ProvideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_releaseFactory(globalModule);
    }

    public static DeviceInfoUtilityAndroid provideInstance(GlobalModule globalModule) {
        return proxyProvideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_release(globalModule);
    }

    public static DeviceInfoUtilityAndroid proxyProvideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_release(GlobalModule globalModule) {
        return (DeviceInfoUtilityAndroid) Preconditions.checkNotNull(globalModule.provideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceInfoUtilityAndroid mo10268get() {
        return provideInstance(this.module);
    }
}
