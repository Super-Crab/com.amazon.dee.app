package com.amazon.dee.app.dependencies;

import com.amazon.deecomms.conversation.ConversationRouting;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ConversationModule_ProvideConversationRoutingFactory implements Factory<ConversationRouting> {
    private final ConversationModule module;

    public ConversationModule_ProvideConversationRoutingFactory(ConversationModule conversationModule) {
        this.module = conversationModule;
    }

    public static ConversationModule_ProvideConversationRoutingFactory create(ConversationModule conversationModule) {
        return new ConversationModule_ProvideConversationRoutingFactory(conversationModule);
    }

    public static ConversationRouting provideInstance(ConversationModule conversationModule) {
        return proxyProvideConversationRouting(conversationModule);
    }

    public static ConversationRouting proxyProvideConversationRouting(ConversationModule conversationModule) {
        return (ConversationRouting) Preconditions.checkNotNull(conversationModule.provideConversationRouting(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ConversationRouting mo10268get() {
        return provideInstance(this.module);
    }
}
