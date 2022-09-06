package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.protocols.messaging.MessagingService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.dee.app.services.messaging.MessageCrypto;
import com.amazon.dee.app.services.messaging.MessagingHandler;
import com.amazon.dee.app.services.messaging.MessagingSettings;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.device.messaging.ADM;
import com.dee.app.http.CoralService;
import com.dee.app.metrics.MetricsService;
import com.google.firebase.iid.FirebaseInstanceId;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideMessagingServiceFactory implements Factory<MessagingService> {
    private final Provider<ADM> admProvider;
    private final Provider<CommsDeviceSupport> commsDeviceSupportProvider;
    private final Provider<CommsManager> commsManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<FirebaseInstanceId> instanceIDProvider;
    private final Provider<MessageCrypto> messageCryptoProvider;
    private final Provider<MessagingHandler> messagingHandlerProvider;
    private final Provider<MessagingSettings> messagingSettingsProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final ServiceModule module;
    private final Provider<NetworkService> networkServiceProvider;
    private final Provider<PersonIdProvider> personIdProvider;

    public ServiceModule_ProvideMessagingServiceFactory(ServiceModule serviceModule, Provider<Context> provider, Provider<CoralService> provider2, Provider<IdentityService> provider3, Provider<ADM> provider4, Provider<FirebaseInstanceId> provider5, Provider<MessagingHandler> provider6, Provider<MessagingSettings> provider7, Provider<CommsManager> provider8, Provider<CommsDeviceSupport> provider9, Provider<MetricsService> provider10, Provider<NetworkService> provider11, Provider<DeviceInformation> provider12, Provider<MessageCrypto> provider13, Provider<PersonIdProvider> provider14, Provider<EventBus> provider15) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.coralServiceProvider = provider2;
        this.identityServiceProvider = provider3;
        this.admProvider = provider4;
        this.instanceIDProvider = provider5;
        this.messagingHandlerProvider = provider6;
        this.messagingSettingsProvider = provider7;
        this.commsManagerProvider = provider8;
        this.commsDeviceSupportProvider = provider9;
        this.metricsServiceProvider = provider10;
        this.networkServiceProvider = provider11;
        this.deviceInformationProvider = provider12;
        this.messageCryptoProvider = provider13;
        this.personIdProvider = provider14;
        this.eventBusProvider = provider15;
    }

    public static ServiceModule_ProvideMessagingServiceFactory create(ServiceModule serviceModule, Provider<Context> provider, Provider<CoralService> provider2, Provider<IdentityService> provider3, Provider<ADM> provider4, Provider<FirebaseInstanceId> provider5, Provider<MessagingHandler> provider6, Provider<MessagingSettings> provider7, Provider<CommsManager> provider8, Provider<CommsDeviceSupport> provider9, Provider<MetricsService> provider10, Provider<NetworkService> provider11, Provider<DeviceInformation> provider12, Provider<MessageCrypto> provider13, Provider<PersonIdProvider> provider14, Provider<EventBus> provider15) {
        return new ServiceModule_ProvideMessagingServiceFactory(serviceModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15);
    }

    public static MessagingService provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<CoralService> provider2, Provider<IdentityService> provider3, Provider<ADM> provider4, Provider<FirebaseInstanceId> provider5, Provider<MessagingHandler> provider6, Provider<MessagingSettings> provider7, Provider<CommsManager> provider8, Provider<CommsDeviceSupport> provider9, Provider<MetricsService> provider10, Provider<NetworkService> provider11, Provider<DeviceInformation> provider12, Provider<MessageCrypto> provider13, Provider<PersonIdProvider> provider14, Provider<EventBus> provider15) {
        return proxyProvideMessagingService(serviceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4, provider5, DoubleCheck.lazy(provider6), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get(), provider11.mo10268get(), provider12.mo10268get(), provider13.mo10268get(), provider14.mo10268get(), provider15.mo10268get());
    }

    public static MessagingService proxyProvideMessagingService(ServiceModule serviceModule, Context context, CoralService coralService, IdentityService identityService, Provider<ADM> provider, Provider<FirebaseInstanceId> provider2, Lazy<MessagingHandler> lazy, MessagingSettings messagingSettings, CommsManager commsManager, CommsDeviceSupport commsDeviceSupport, MetricsService metricsService, NetworkService networkService, DeviceInformation deviceInformation, MessageCrypto messageCrypto, PersonIdProvider personIdProvider, EventBus eventBus) {
        return (MessagingService) Preconditions.checkNotNull(serviceModule.provideMessagingService(context, coralService, identityService, provider, provider2, lazy, messagingSettings, commsManager, commsDeviceSupport, metricsService, networkService, deviceInformation, messageCrypto, personIdProvider, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessagingService mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.coralServiceProvider, this.identityServiceProvider, this.admProvider, this.instanceIDProvider, this.messagingHandlerProvider, this.messagingSettingsProvider, this.commsManagerProvider, this.commsDeviceSupportProvider, this.metricsServiceProvider, this.networkServiceProvider, this.deviceInformationProvider, this.messageCryptoProvider, this.personIdProvider, this.eventBusProvider);
    }
}
