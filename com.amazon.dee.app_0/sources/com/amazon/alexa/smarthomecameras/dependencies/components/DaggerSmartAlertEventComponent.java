package com.amazon.alexa.smarthomecameras.dependencies.components;

import android.content.Context;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.smarthomecameras.dependencies.modules.ContextModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.ContextModule_ProvidesContextFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.RoutingServiceModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.RoutingServiceModule_ProvidesRoutingServiceFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class DaggerSmartAlertEventComponent implements SmartAlertEventComponent {
    private Provider<Context> providesContextProvider;
    private Provider<RoutingService> providesRoutingServiceProvider;

    /* loaded from: classes10.dex */
    public static final class Builder {
        private ContextModule contextModule;
        private RoutingServiceModule routingServiceModule;

        public SmartAlertEventComponent build() {
            Preconditions.checkBuilderRequirement(this.contextModule, ContextModule.class);
            Preconditions.checkBuilderRequirement(this.routingServiceModule, RoutingServiceModule.class);
            return new DaggerSmartAlertEventComponent(this);
        }

        public Builder contextModule(ContextModule contextModule) {
            this.contextModule = (ContextModule) Preconditions.checkNotNull(contextModule);
            return this;
        }

        public Builder routingServiceModule(RoutingServiceModule routingServiceModule) {
            this.routingServiceModule = (RoutingServiceModule) Preconditions.checkNotNull(routingServiceModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.providesContextProvider = DoubleCheck.provider(ContextModule_ProvidesContextFactory.create(builder.contextModule));
        this.providesRoutingServiceProvider = DoubleCheck.provider(RoutingServiceModule_ProvidesRoutingServiceFactory.create(builder.routingServiceModule));
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.SmartAlertEventComponent
    public Context context() {
        return this.providesContextProvider.mo10268get();
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.SmartAlertEventComponent
    public RoutingService routingService() {
        return this.providesRoutingServiceProvider.mo10268get();
    }

    private DaggerSmartAlertEventComponent(Builder builder) {
        initialize(builder);
    }
}
