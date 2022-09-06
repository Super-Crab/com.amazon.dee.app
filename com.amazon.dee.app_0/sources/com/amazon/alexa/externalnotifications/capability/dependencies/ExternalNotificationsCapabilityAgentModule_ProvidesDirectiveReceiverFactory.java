package com.amazon.alexa.externalnotifications.capability.dependencies;

import android.content.Context;
import android.os.Handler;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsDirectiveReceiver;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class ExternalNotificationsCapabilityAgentModule_ProvidesDirectiveReceiverFactory implements Factory<ExternalNotificationsDirectiveReceiver> {
    private final Provider<Context> contextProvider;
    private final Provider<Gson> gsonProvider;
    private final Provider<Handler> handlerProvider;
    private final ExternalNotificationsCapabilityAgentModule module;

    public ExternalNotificationsCapabilityAgentModule_ProvidesDirectiveReceiverFactory(ExternalNotificationsCapabilityAgentModule externalNotificationsCapabilityAgentModule, Provider<Gson> provider, Provider<Context> provider2, Provider<Handler> provider3) {
        this.module = externalNotificationsCapabilityAgentModule;
        this.gsonProvider = provider;
        this.contextProvider = provider2;
        this.handlerProvider = provider3;
    }

    public static ExternalNotificationsCapabilityAgentModule_ProvidesDirectiveReceiverFactory create(ExternalNotificationsCapabilityAgentModule externalNotificationsCapabilityAgentModule, Provider<Gson> provider, Provider<Context> provider2, Provider<Handler> provider3) {
        return new ExternalNotificationsCapabilityAgentModule_ProvidesDirectiveReceiverFactory(externalNotificationsCapabilityAgentModule, provider, provider2, provider3);
    }

    public static ExternalNotificationsDirectiveReceiver provideInstance(ExternalNotificationsCapabilityAgentModule externalNotificationsCapabilityAgentModule, Provider<Gson> provider, Provider<Context> provider2, Provider<Handler> provider3) {
        return proxyProvidesDirectiveReceiver(externalNotificationsCapabilityAgentModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static ExternalNotificationsDirectiveReceiver proxyProvidesDirectiveReceiver(ExternalNotificationsCapabilityAgentModule externalNotificationsCapabilityAgentModule, Gson gson, Context context, Handler handler) {
        return (ExternalNotificationsDirectiveReceiver) Preconditions.checkNotNull(externalNotificationsCapabilityAgentModule.providesDirectiveReceiver(gson, context, handler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ExternalNotificationsDirectiveReceiver mo10268get() {
        return provideInstance(this.module, this.gsonProvider, this.contextProvider, this.handlerProvider);
    }
}
