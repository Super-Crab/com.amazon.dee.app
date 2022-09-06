package com.amazon.deecomms.calling.ui;

import com.amazon.deecomms.conversation.ConversationService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DirectiveReceiverActivity_MembersInjector implements MembersInjector<DirectiveReceiverActivity> {
    private final Provider<ConversationService> commsConversationServiceProvider;

    public DirectiveReceiverActivity_MembersInjector(Provider<ConversationService> provider) {
        this.commsConversationServiceProvider = provider;
    }

    public static MembersInjector<DirectiveReceiverActivity> create(Provider<ConversationService> provider) {
        return new DirectiveReceiverActivity_MembersInjector(provider);
    }

    public static void injectCommsConversationService(DirectiveReceiverActivity directiveReceiverActivity, ConversationService conversationService) {
        directiveReceiverActivity.commsConversationService = conversationService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DirectiveReceiverActivity directiveReceiverActivity) {
        directiveReceiverActivity.commsConversationService = this.commsConversationServiceProvider.mo10268get();
    }
}
