package com.amazon.alexa.externalnotifications.capability.dependencies;

import android.os.Handler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class ExternalNotificationsCapabilityAgentModule_ProvidesDirectiveReceiverHandlerFactory implements Factory<Handler> {
    private final ExternalNotificationsCapabilityAgentModule module;

    public ExternalNotificationsCapabilityAgentModule_ProvidesDirectiveReceiverHandlerFactory(ExternalNotificationsCapabilityAgentModule externalNotificationsCapabilityAgentModule) {
        this.module = externalNotificationsCapabilityAgentModule;
    }

    public static ExternalNotificationsCapabilityAgentModule_ProvidesDirectiveReceiverHandlerFactory create(ExternalNotificationsCapabilityAgentModule externalNotificationsCapabilityAgentModule) {
        return new ExternalNotificationsCapabilityAgentModule_ProvidesDirectiveReceiverHandlerFactory(externalNotificationsCapabilityAgentModule);
    }

    public static Handler provideInstance(ExternalNotificationsCapabilityAgentModule externalNotificationsCapabilityAgentModule) {
        return proxyProvidesDirectiveReceiverHandler(externalNotificationsCapabilityAgentModule);
    }

    public static Handler proxyProvidesDirectiveReceiverHandler(ExternalNotificationsCapabilityAgentModule externalNotificationsCapabilityAgentModule) {
        return (Handler) Preconditions.checkNotNull(externalNotificationsCapabilityAgentModule.providesDirectiveReceiverHandler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Handler mo10268get() {
        return provideInstance(this.module);
    }
}
