package com.amazon.deecomms.conversation;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class FireOSDirectiveHandlerService_Factory implements Factory<FireOSDirectiveHandlerService> {
    private final Provider<ConversationService> commsConversationServiceProvider;

    public FireOSDirectiveHandlerService_Factory(Provider<ConversationService> provider) {
        this.commsConversationServiceProvider = provider;
    }

    public static FireOSDirectiveHandlerService_Factory create(Provider<ConversationService> provider) {
        return new FireOSDirectiveHandlerService_Factory(provider);
    }

    public static FireOSDirectiveHandlerService newFireOSDirectiveHandlerService() {
        return new FireOSDirectiveHandlerService();
    }

    public static FireOSDirectiveHandlerService provideInstance(Provider<ConversationService> provider) {
        FireOSDirectiveHandlerService fireOSDirectiveHandlerService = new FireOSDirectiveHandlerService();
        FireOSDirectiveHandlerService_MembersInjector.injectCommsConversationService(fireOSDirectiveHandlerService, provider.mo10268get());
        return fireOSDirectiveHandlerService;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FireOSDirectiveHandlerService mo10268get() {
        return provideInstance(this.commsConversationServiceProvider);
    }
}
