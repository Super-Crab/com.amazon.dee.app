package com.amazon.alexa.externalnotifications.capability;

import android.os.Handler;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsCapabilityAgent;
import com.amazon.alexa.externalnotifications.capability.dependencies.AlexaMobileFrameworkModule;
import com.amazon.alexa.externalnotifications.capability.dependencies.AlexaMobileFrameworkModule_ProvidesAmfApisFactory;
import com.amazon.alexa.externalnotifications.capability.dependencies.ContextModule;
import com.amazon.alexa.externalnotifications.capability.dependencies.ContextModule_ProvidesContextFactory;
import com.amazon.alexa.externalnotifications.capability.dependencies.ExternalNotificationsCapabilityAgentModule;
import com.amazon.alexa.externalnotifications.capability.dependencies.ExternalNotificationsCapabilityAgentModule_ProvidesDirectiveReceiverFactory;
import com.amazon.alexa.externalnotifications.capability.dependencies.ExternalNotificationsCapabilityAgentModule_ProvidesDirectiveReceiverHandlerFactory;
import com.amazon.alexa.externalnotifications.capability.dependencies.ExternalNotificationsCapabilityAgentModule_ProvidesEventSenderFactory;
import com.amazon.alexa.externalnotifications.capability.dependencies.GsonModule;
import com.amazon.alexa.externalnotifications.capability.dependencies.GsonModule_ProvidesGsonFactory;
import com.amazon.alexa.externalnotifications.capability.events.ExternalNotificationsEventSender;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class DaggerExternalNotificationsCapabilityAgent_Injector implements ExternalNotificationsCapabilityAgent.Injector {
    private Provider<AlexaMobileFrameworkApis> providesAmfApisProvider;
    private ContextModule_ProvidesContextFactory providesContextProvider;
    private Provider<Handler> providesDirectiveReceiverHandlerProvider;
    private Provider<ExternalNotificationsDirectiveReceiver> providesDirectiveReceiverProvider;
    private Provider<ExternalNotificationsEventSender> providesEventSenderProvider;
    private Provider<Gson> providesGsonProvider;

    /* loaded from: classes7.dex */
    public static final class Builder {
        private AlexaMobileFrameworkModule alexaMobileFrameworkModule;
        private ContextModule contextModule;
        private ExternalNotificationsCapabilityAgentModule externalNotificationsCapabilityAgentModule;
        private GsonModule gsonModule;

        public Builder alexaMobileFrameworkModule(AlexaMobileFrameworkModule alexaMobileFrameworkModule) {
            this.alexaMobileFrameworkModule = (AlexaMobileFrameworkModule) Preconditions.checkNotNull(alexaMobileFrameworkModule);
            return this;
        }

        public ExternalNotificationsCapabilityAgent.Injector build() {
            Preconditions.checkBuilderRequirement(this.externalNotificationsCapabilityAgentModule, ExternalNotificationsCapabilityAgentModule.class);
            if (this.gsonModule == null) {
                this.gsonModule = new GsonModule();
            }
            if (this.alexaMobileFrameworkModule == null) {
                this.alexaMobileFrameworkModule = new AlexaMobileFrameworkModule();
            }
            Preconditions.checkBuilderRequirement(this.contextModule, ContextModule.class);
            return new DaggerExternalNotificationsCapabilityAgent_Injector(this);
        }

        public Builder contextModule(ContextModule contextModule) {
            this.contextModule = (ContextModule) Preconditions.checkNotNull(contextModule);
            return this;
        }

        public Builder externalNotificationsCapabilityAgentModule(ExternalNotificationsCapabilityAgentModule externalNotificationsCapabilityAgentModule) {
            this.externalNotificationsCapabilityAgentModule = (ExternalNotificationsCapabilityAgentModule) Preconditions.checkNotNull(externalNotificationsCapabilityAgentModule);
            return this;
        }

        public Builder gsonModule(GsonModule gsonModule) {
            this.gsonModule = (GsonModule) Preconditions.checkNotNull(gsonModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.providesGsonProvider = DoubleCheck.provider(GsonModule_ProvidesGsonFactory.create(builder.gsonModule));
        this.providesContextProvider = ContextModule_ProvidesContextFactory.create(builder.contextModule);
        this.providesAmfApisProvider = DoubleCheck.provider(AlexaMobileFrameworkModule_ProvidesAmfApisFactory.create(builder.alexaMobileFrameworkModule, this.providesContextProvider));
        this.providesEventSenderProvider = DoubleCheck.provider(ExternalNotificationsCapabilityAgentModule_ProvidesEventSenderFactory.create(builder.externalNotificationsCapabilityAgentModule, this.providesGsonProvider, this.providesAmfApisProvider));
        this.providesDirectiveReceiverHandlerProvider = DoubleCheck.provider(ExternalNotificationsCapabilityAgentModule_ProvidesDirectiveReceiverHandlerFactory.create(builder.externalNotificationsCapabilityAgentModule));
        this.providesDirectiveReceiverProvider = DoubleCheck.provider(ExternalNotificationsCapabilityAgentModule_ProvidesDirectiveReceiverFactory.create(builder.externalNotificationsCapabilityAgentModule, this.providesGsonProvider, this.providesContextProvider, this.providesDirectiveReceiverHandlerProvider));
    }

    @CanIgnoreReturnValue
    private ExternalNotificationsCapabilityAgent injectExternalNotificationsCapabilityAgent(ExternalNotificationsCapabilityAgent externalNotificationsCapabilityAgent) {
        ExternalNotificationsCapabilityAgent_MembersInjector.injectEventSender(externalNotificationsCapabilityAgent, this.providesEventSenderProvider.mo10268get());
        ExternalNotificationsCapabilityAgent_MembersInjector.injectDirectiveReceiver(externalNotificationsCapabilityAgent, this.providesDirectiveReceiverProvider.mo10268get());
        return externalNotificationsCapabilityAgent;
    }

    @Override // com.amazon.alexa.externalnotifications.capability.ExternalNotificationsCapabilityAgent.Injector
    public void inject(ExternalNotificationsCapabilityAgent externalNotificationsCapabilityAgent) {
        injectExternalNotificationsCapabilityAgent(externalNotificationsCapabilityAgent);
    }

    private DaggerExternalNotificationsCapabilityAgent_Injector(Builder builder) {
        initialize(builder);
    }
}
