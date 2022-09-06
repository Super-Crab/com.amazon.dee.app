package com.amazon.deecomms.conversation;

import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class FireOSDirectiveHandlerService_MembersInjector implements MembersInjector<FireOSDirectiveHandlerService> {
    private final Provider<ConversationService> commsConversationServiceProvider;

    public FireOSDirectiveHandlerService_MembersInjector(Provider<ConversationService> provider) {
        this.commsConversationServiceProvider = provider;
    }

    public static MembersInjector<FireOSDirectiveHandlerService> create(Provider<ConversationService> provider) {
        return new FireOSDirectiveHandlerService_MembersInjector(provider);
    }

    public static void injectCommsConversationService(FireOSDirectiveHandlerService fireOSDirectiveHandlerService, ConversationService conversationService) {
        fireOSDirectiveHandlerService.commsConversationService = conversationService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FireOSDirectiveHandlerService fireOSDirectiveHandlerService) {
        fireOSDirectiveHandlerService.commsConversationService = this.commsConversationServiceProvider.mo10268get();
    }
}
