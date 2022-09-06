package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.services.messaging.MessagingSettings;
import com.amazon.dee.app.services.messaging.MessagingSettingsMetricsHandler;
import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MessagingModule_ProvideMessagingSettingsFactory implements Factory<MessagingSettings> {
    private final Provider<Context> contextProvider;
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<MessagingSettingsMetricsHandler> messagingSettingsMetricsHandlerProvider;
    private final MessagingModule module;
    private final Provider<NetworkService> networkServiceProvider;
    private final Provider<PersistentStorage.Factory> storageFactoryProvider;

    public MessagingModule_ProvideMessagingSettingsFactory(MessagingModule messagingModule, Provider<PersistentStorage.Factory> provider, Provider<MessagingSettingsMetricsHandler> provider2, Provider<DeviceInformation> provider3, Provider<CoralService> provider4, Provider<EventBus> provider5, Provider<IdentityService> provider6, Provider<NetworkService> provider7, Provider<Context> provider8) {
        this.module = messagingModule;
        this.storageFactoryProvider = provider;
        this.messagingSettingsMetricsHandlerProvider = provider2;
        this.deviceInformationProvider = provider3;
        this.coralServiceProvider = provider4;
        this.eventBusProvider = provider5;
        this.identityServiceProvider = provider6;
        this.networkServiceProvider = provider7;
        this.contextProvider = provider8;
    }

    public static MessagingModule_ProvideMessagingSettingsFactory create(MessagingModule messagingModule, Provider<PersistentStorage.Factory> provider, Provider<MessagingSettingsMetricsHandler> provider2, Provider<DeviceInformation> provider3, Provider<CoralService> provider4, Provider<EventBus> provider5, Provider<IdentityService> provider6, Provider<NetworkService> provider7, Provider<Context> provider8) {
        return new MessagingModule_ProvideMessagingSettingsFactory(messagingModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static MessagingSettings provideInstance(MessagingModule messagingModule, Provider<PersistentStorage.Factory> provider, Provider<MessagingSettingsMetricsHandler> provider2, Provider<DeviceInformation> provider3, Provider<CoralService> provider4, Provider<EventBus> provider5, Provider<IdentityService> provider6, Provider<NetworkService> provider7, Provider<Context> provider8) {
        return proxyProvideMessagingSettings(messagingModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get());
    }

    public static MessagingSettings proxyProvideMessagingSettings(MessagingModule messagingModule, PersistentStorage.Factory factory, MessagingSettingsMetricsHandler messagingSettingsMetricsHandler, DeviceInformation deviceInformation, CoralService coralService, EventBus eventBus, IdentityService identityService, NetworkService networkService, Context context) {
        return (MessagingSettings) Preconditions.checkNotNull(messagingModule.provideMessagingSettings(factory, messagingSettingsMetricsHandler, deviceInformation, coralService, eventBus, identityService, networkService, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessagingSettings mo10268get() {
        return provideInstance(this.module, this.storageFactoryProvider, this.messagingSettingsMetricsHandlerProvider, this.deviceInformationProvider, this.coralServiceProvider, this.eventBusProvider, this.identityServiceProvider, this.networkServiceProvider, this.contextProvider);
    }
}
