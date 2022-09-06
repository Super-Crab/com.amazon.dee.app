package com.amazon.dee.app.dependencies;

import com.amazon.alexa.photos.util.SystemUtility;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvideSystemUtilityFactory implements Factory<SystemUtility> {
    private final CloudDriveModule module;

    public CloudDriveModule_ProvideSystemUtilityFactory(CloudDriveModule cloudDriveModule) {
        this.module = cloudDriveModule;
    }

    public static CloudDriveModule_ProvideSystemUtilityFactory create(CloudDriveModule cloudDriveModule) {
        return new CloudDriveModule_ProvideSystemUtilityFactory(cloudDriveModule);
    }

    public static SystemUtility provideInstance(CloudDriveModule cloudDriveModule) {
        return proxyProvideSystemUtility(cloudDriveModule);
    }

    public static SystemUtility proxyProvideSystemUtility(CloudDriveModule cloudDriveModule) {
        return (SystemUtility) Preconditions.checkNotNull(cloudDriveModule.provideSystemUtility(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SystemUtility mo10268get() {
        return provideInstance(this.module);
    }
}
