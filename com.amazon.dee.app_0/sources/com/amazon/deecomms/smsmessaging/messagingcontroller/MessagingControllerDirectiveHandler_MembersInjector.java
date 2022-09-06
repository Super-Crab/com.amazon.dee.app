package com.amazon.deecomms.smsmessaging.messagingcontroller;

import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MessagingControllerDirectiveHandler_MembersInjector implements MembersInjector<MessagingControllerDirectiveHandler> {
    private final Provider<MessagingControllerManager> mMessagingControllerManagerProvider;

    public MessagingControllerDirectiveHandler_MembersInjector(Provider<MessagingControllerManager> provider) {
        this.mMessagingControllerManagerProvider = provider;
    }

    public static MembersInjector<MessagingControllerDirectiveHandler> create(Provider<MessagingControllerManager> provider) {
        return new MessagingControllerDirectiveHandler_MembersInjector(provider);
    }

    public static void injectMMessagingControllerManager(MessagingControllerDirectiveHandler messagingControllerDirectiveHandler, MessagingControllerManager messagingControllerManager) {
        messagingControllerDirectiveHandler.mMessagingControllerManager = messagingControllerManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MessagingControllerDirectiveHandler messagingControllerDirectiveHandler) {
        messagingControllerDirectiveHandler.mMessagingControllerManager = this.mMessagingControllerManagerProvider.mo10268get();
    }
}
