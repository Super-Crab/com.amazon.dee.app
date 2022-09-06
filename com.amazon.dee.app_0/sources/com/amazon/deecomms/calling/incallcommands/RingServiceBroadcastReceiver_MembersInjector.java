package com.amazon.deecomms.calling.incallcommands;

import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.deecomms.alexa.CommsAudioInteraction;
import com.amazon.deecomms.alexa.CommsAudioInteractionScheduler;
import com.amazon.deecomms.alexa.CommsEventSender;
import com.amazon.deecomms.alexa.connection.enpoint.a2a.A2AConnectionEndpointHandler;
import com.amazon.deecomms.alexa.unsent.event.a2a.A2AQueuedEvents;
import com.amazon.deecomms.calling.incallcommands.models.InCallCommandModelFactory;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class RingServiceBroadcastReceiver_MembersInjector implements MembersInjector<RingServiceBroadcastReceiver> {
    private final Provider<A2AConnectionEndpointHandler> a2AEndpointHandlerProvider;
    private final Provider<A2AQueuedEvents> a2AUnsentEventsManagerProvider;
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<CommsAudioInteraction> commsAudioInteractionProvider;
    private final Provider<CommsAudioInteractionScheduler> commsAudioInteractionSchedulerProvider;
    private final Provider<CommsEventSender> commsEventSenderProvider;
    private final Provider<InCallCommandModelFactory> factoryProvider;

    public RingServiceBroadcastReceiver_MembersInjector(Provider<AlexaServicesConnection> provider, Provider<CommsAudioInteraction> provider2, Provider<InCallCommandModelFactory> provider3, Provider<CommsEventSender> provider4, Provider<A2AConnectionEndpointHandler> provider5, Provider<CommsAudioInteractionScheduler> provider6, Provider<A2AQueuedEvents> provider7) {
        this.alexaServicesConnectionProvider = provider;
        this.commsAudioInteractionProvider = provider2;
        this.factoryProvider = provider3;
        this.commsEventSenderProvider = provider4;
        this.a2AEndpointHandlerProvider = provider5;
        this.commsAudioInteractionSchedulerProvider = provider6;
        this.a2AUnsentEventsManagerProvider = provider7;
    }

    public static MembersInjector<RingServiceBroadcastReceiver> create(Provider<AlexaServicesConnection> provider, Provider<CommsAudioInteraction> provider2, Provider<InCallCommandModelFactory> provider3, Provider<CommsEventSender> provider4, Provider<A2AConnectionEndpointHandler> provider5, Provider<CommsAudioInteractionScheduler> provider6, Provider<A2AQueuedEvents> provider7) {
        return new RingServiceBroadcastReceiver_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static void injectA2AEndpointHandler(RingServiceBroadcastReceiver ringServiceBroadcastReceiver, A2AConnectionEndpointHandler a2AConnectionEndpointHandler) {
        ringServiceBroadcastReceiver.a2AEndpointHandler = a2AConnectionEndpointHandler;
    }

    public static void injectA2AUnsentEventsManager(RingServiceBroadcastReceiver ringServiceBroadcastReceiver, A2AQueuedEvents a2AQueuedEvents) {
        ringServiceBroadcastReceiver.a2AUnsentEventsManager = a2AQueuedEvents;
    }

    public static void injectAlexaServicesConnection(RingServiceBroadcastReceiver ringServiceBroadcastReceiver, AlexaServicesConnection alexaServicesConnection) {
        ringServiceBroadcastReceiver.alexaServicesConnection = alexaServicesConnection;
    }

    public static void injectCommsAudioInteraction(RingServiceBroadcastReceiver ringServiceBroadcastReceiver, CommsAudioInteraction commsAudioInteraction) {
        ringServiceBroadcastReceiver.commsAudioInteraction = commsAudioInteraction;
    }

    public static void injectCommsAudioInteractionScheduler(RingServiceBroadcastReceiver ringServiceBroadcastReceiver, CommsAudioInteractionScheduler commsAudioInteractionScheduler) {
        ringServiceBroadcastReceiver.commsAudioInteractionScheduler = commsAudioInteractionScheduler;
    }

    public static void injectCommsEventSender(RingServiceBroadcastReceiver ringServiceBroadcastReceiver, CommsEventSender commsEventSender) {
        ringServiceBroadcastReceiver.commsEventSender = commsEventSender;
    }

    public static void injectFactory(RingServiceBroadcastReceiver ringServiceBroadcastReceiver, InCallCommandModelFactory inCallCommandModelFactory) {
        ringServiceBroadcastReceiver.factory = inCallCommandModelFactory;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(RingServiceBroadcastReceiver ringServiceBroadcastReceiver) {
        ringServiceBroadcastReceiver.alexaServicesConnection = this.alexaServicesConnectionProvider.mo10268get();
        ringServiceBroadcastReceiver.commsAudioInteraction = this.commsAudioInteractionProvider.mo10268get();
        ringServiceBroadcastReceiver.factory = this.factoryProvider.mo10268get();
        ringServiceBroadcastReceiver.commsEventSender = this.commsEventSenderProvider.mo10268get();
        ringServiceBroadcastReceiver.a2AEndpointHandler = this.a2AEndpointHandlerProvider.mo10268get();
        ringServiceBroadcastReceiver.commsAudioInteractionScheduler = this.commsAudioInteractionSchedulerProvider.mo10268get();
        ringServiceBroadcastReceiver.a2AUnsentEventsManager = this.a2AUnsentEventsManagerProvider.mo10268get();
    }
}
