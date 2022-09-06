package com.amazon.clouddrive.cdasdk.dagger.module;

import com.amazon.clouddrive.cdasdk.cdp.CDPUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class ApplicationModule_ProvideCDPUtilFactory implements Factory<CDPUtil> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideCDPUtilFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideCDPUtilFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideCDPUtilFactory(applicationModule);
    }

    public static CDPUtil provideCDPUtil(ApplicationModule applicationModule) {
        return (CDPUtil) Preconditions.checkNotNull(applicationModule.provideCDPUtil(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CDPUtil mo10268get() {
        return provideCDPUtil(this.module);
    }
}
