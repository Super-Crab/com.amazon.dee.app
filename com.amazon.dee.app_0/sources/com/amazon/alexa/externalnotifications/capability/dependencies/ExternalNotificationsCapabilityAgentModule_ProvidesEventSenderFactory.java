package com.amazon.alexa.externalnotifications.capability.dependencies;

import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.externalnotifications.capability.events.ExternalNotificationsEventSender;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class ExternalNotificationsCapabilityAgentModule_ProvidesEventSenderFactory implements Factory<ExternalNotificationsEventSender> {
    private final Provider<AlexaMobileFrameworkApis> amfApisProvider;
    private final Provider<Gson> gsonProvider;
    private final ExternalNotificationsCapabilityAgentModule module;

    public ExternalNotificationsCapabilityAgentModule_ProvidesEventSenderFactory(ExternalNotificationsCapabilityAgentModule externalNotificationsCapabilityAgentModule, Provider<Gson> provider, Provider<AlexaMobileFrameworkApis> provider2) {
        this.module = externalNotificationsCapabilityAgentModule;
        this.gsonProvider = provider;
        this.amfApisProvider = provider2;
    }

    public static ExternalNotificationsCapabilityAgentModule_ProvidesEventSenderFactory create(ExternalNotificationsCapabilityAgentModule externalNotificationsCapabilityAgentModule, Provider<Gson> provider, Provider<AlexaMobileFrameworkApis> provider2) {
        return new ExternalNotificationsCapabilityAgentModule_ProvidesEventSenderFactory(externalNotificationsCapabilityAgentModule, provider, provider2);
    }

    public static ExternalNotificationsEventSender provideInstance(ExternalNotificationsCapabilityAgentModule externalNotificationsCapabilityAgentModule, Provider<Gson> provider, Provider<AlexaMobileFrameworkApis> provider2) {
        return proxyProvidesEventSender(externalNotificationsCapabilityAgentModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static ExternalNotificationsEventSender proxyProvidesEventSender(ExternalNotificationsCapabilityAgentModule externalNotificationsCapabilityAgentModule, Gson gson, AlexaMobileFrameworkApis alexaMobileFrameworkApis) {
        return (ExternalNotificationsEventSender) Preconditions.checkNotNull(externalNotificationsCapabilityAgentModule.providesEventSender(gson, alexaMobileFrameworkApis), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ExternalNotificationsEventSender mo10268get() {
        return provideInstance(this.module, this.gsonProvider, this.amfApisProvider);
    }
}
