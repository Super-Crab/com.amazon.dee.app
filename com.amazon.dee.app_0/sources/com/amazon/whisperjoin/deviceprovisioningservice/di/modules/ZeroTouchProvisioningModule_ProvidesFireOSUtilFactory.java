package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.util.FireOSUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ZeroTouchProvisioningModule_ProvidesFireOSUtilFactory implements Factory<FireOSUtil> {
    private final ZeroTouchProvisioningModule module;

    public ZeroTouchProvisioningModule_ProvidesFireOSUtilFactory(ZeroTouchProvisioningModule zeroTouchProvisioningModule) {
        this.module = zeroTouchProvisioningModule;
    }

    public static ZeroTouchProvisioningModule_ProvidesFireOSUtilFactory create(ZeroTouchProvisioningModule zeroTouchProvisioningModule) {
        return new ZeroTouchProvisioningModule_ProvidesFireOSUtilFactory(zeroTouchProvisioningModule);
    }

    public static FireOSUtil provideInstance(ZeroTouchProvisioningModule zeroTouchProvisioningModule) {
        return proxyProvidesFireOSUtil(zeroTouchProvisioningModule);
    }

    public static FireOSUtil proxyProvidesFireOSUtil(ZeroTouchProvisioningModule zeroTouchProvisioningModule) {
        return (FireOSUtil) Preconditions.checkNotNull(zeroTouchProvisioningModule.providesFireOSUtil(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FireOSUtil mo10268get() {
        return provideInstance(this.module);
    }
}
