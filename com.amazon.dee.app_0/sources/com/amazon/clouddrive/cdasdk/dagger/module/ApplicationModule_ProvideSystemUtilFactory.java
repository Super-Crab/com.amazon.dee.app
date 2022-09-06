package com.amazon.clouddrive.cdasdk.dagger.module;

import com.amazon.clouddrive.cdasdk.util.SystemUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class ApplicationModule_ProvideSystemUtilFactory implements Factory<SystemUtil> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideSystemUtilFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideSystemUtilFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideSystemUtilFactory(applicationModule);
    }

    public static SystemUtil provideSystemUtil(ApplicationModule applicationModule) {
        return (SystemUtil) Preconditions.checkNotNull(applicationModule.provideSystemUtil(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SystemUtil mo10268get() {
        return provideSystemUtil(this.module);
    }
}
