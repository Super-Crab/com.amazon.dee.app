package com.amazon.comms.calling.dependency;

import android.content.Context;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.dee.app.metrics.MetricsService;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class DaggerCallUiComponent implements CallUiComponent {
    private Provider<ComponentRegistry> provideComponentRegistryProvider;
    private Provider<MetricsService> provideMetricsServiceProvider;
    private Provider<Context> providesContextProvider;

    /* loaded from: classes10.dex */
    public static final class Builder {
        private ApplicationModule applicationModule;

        public Builder applicationModule(ApplicationModule applicationModule) {
            this.applicationModule = (ApplicationModule) Preconditions.checkNotNull(applicationModule);
            return this;
        }

        public CallUiComponent build() {
            Preconditions.checkBuilderRequirement(this.applicationModule, ApplicationModule.class);
            return new DaggerCallUiComponent(this);
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.providesContextProvider = DoubleCheck.provider(ApplicationModule_ProvidesContextFactory.create(builder.applicationModule));
        this.provideComponentRegistryProvider = DoubleCheck.provider(ApplicationModule_ProvideComponentRegistryFactory.create(builder.applicationModule));
        this.provideMetricsServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideMetricsServiceFactory.create(builder.applicationModule, this.provideComponentRegistryProvider));
    }

    @Override // com.amazon.comms.calling.dependency.CallUiComponent
    public Context getContext() {
        return this.providesContextProvider.mo10268get();
    }

    @Override // com.amazon.comms.calling.dependency.CallUiComponent
    public MetricsService getMetricsService() {
        return this.provideMetricsServiceProvider.mo10268get();
    }

    private DaggerCallUiComponent(Builder builder) {
        initialize(builder);
    }
}
