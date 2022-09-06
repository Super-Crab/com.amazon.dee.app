package com.amazon.alexa.externalnotifications.capability;

import com.amazon.alexa.externalnotifications.capability.events.ExternalNotificationsEventSender;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class ExternalNotificationsCapabilityAgent_MembersInjector implements MembersInjector<ExternalNotificationsCapabilityAgent> {
    private final Provider<ExternalNotificationsDirectiveReceiver> directiveReceiverProvider;
    private final Provider<ExternalNotificationsEventSender> eventSenderProvider;

    public ExternalNotificationsCapabilityAgent_MembersInjector(Provider<ExternalNotificationsEventSender> provider, Provider<ExternalNotificationsDirectiveReceiver> provider2) {
        this.eventSenderProvider = provider;
        this.directiveReceiverProvider = provider2;
    }

    public static MembersInjector<ExternalNotificationsCapabilityAgent> create(Provider<ExternalNotificationsEventSender> provider, Provider<ExternalNotificationsDirectiveReceiver> provider2) {
        return new ExternalNotificationsCapabilityAgent_MembersInjector(provider, provider2);
    }

    public static void injectDirectiveReceiver(ExternalNotificationsCapabilityAgent externalNotificationsCapabilityAgent, ExternalNotificationsDirectiveReceiver externalNotificationsDirectiveReceiver) {
        externalNotificationsCapabilityAgent.directiveReceiver = externalNotificationsDirectiveReceiver;
    }

    public static void injectEventSender(ExternalNotificationsCapabilityAgent externalNotificationsCapabilityAgent, ExternalNotificationsEventSender externalNotificationsEventSender) {
        externalNotificationsCapabilityAgent.eventSender = externalNotificationsEventSender;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ExternalNotificationsCapabilityAgent externalNotificationsCapabilityAgent) {
        injectEventSender(externalNotificationsCapabilityAgent, this.eventSenderProvider.mo10268get());
        injectDirectiveReceiver(externalNotificationsCapabilityAgent, this.directiveReceiverProvider.mo10268get());
    }
}
