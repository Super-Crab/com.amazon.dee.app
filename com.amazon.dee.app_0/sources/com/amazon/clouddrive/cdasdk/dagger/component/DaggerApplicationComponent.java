package com.amazon.clouddrive.cdasdk.dagger.component;

import com.amazon.clouddrive.cdasdk.cdp.CDPUtil;
import com.amazon.clouddrive.cdasdk.dagger.module.ApplicationModule;
import com.amazon.clouddrive.cdasdk.dagger.module.ApplicationModule_ProvideCDPUtilFactory;
import com.amazon.clouddrive.cdasdk.dagger.module.ApplicationModule_ProvideLoggerFactory;
import com.amazon.clouddrive.cdasdk.dagger.module.ApplicationModule_ProvideSystemUtilFactory;
import com.amazon.clouddrive.cdasdk.util.Logger;
import com.amazon.clouddrive.cdasdk.util.SystemUtil;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class DaggerApplicationComponent implements ApplicationComponent {
    private final ApplicationModule applicationModule;

    /* loaded from: classes11.dex */
    public static final class Builder {
        private ApplicationModule applicationModule;

        public Builder applicationModule(ApplicationModule applicationModule) {
            this.applicationModule = (ApplicationModule) Preconditions.checkNotNull(applicationModule);
            return this;
        }

        public ApplicationComponent build() {
            Preconditions.checkBuilderRequirement(this.applicationModule, ApplicationModule.class);
            return new DaggerApplicationComponent(this.applicationModule);
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // com.amazon.clouddrive.cdasdk.dagger.component.ApplicationComponent
    public CDPUtil getCDPUtil() {
        return ApplicationModule_ProvideCDPUtilFactory.provideCDPUtil(this.applicationModule);
    }

    @Override // com.amazon.clouddrive.cdasdk.dagger.component.ApplicationComponent
    public Logger getLogger() {
        return ApplicationModule_ProvideLoggerFactory.provideLogger(this.applicationModule);
    }

    @Override // com.amazon.clouddrive.cdasdk.dagger.component.ApplicationComponent
    public SystemUtil getSystemUtil() {
        return ApplicationModule_ProvideSystemUtilFactory.provideSystemUtil(this.applicationModule);
    }

    private DaggerApplicationComponent(ApplicationModule applicationModule) {
        this.applicationModule = applicationModule;
    }
}
