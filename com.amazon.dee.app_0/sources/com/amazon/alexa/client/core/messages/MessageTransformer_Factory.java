package com.amazon.alexa.client.core.messages;

import dagger.internal.Factory;
/* loaded from: classes6.dex */
public final class MessageTransformer_Factory implements Factory<MessageTransformer> {
    private static final MessageTransformer_Factory INSTANCE = new MessageTransformer_Factory();

    public static MessageTransformer_Factory create() {
        return INSTANCE;
    }

    public static MessageTransformer newMessageTransformer() {
        return new MessageTransformer();
    }

    public static MessageTransformer provideInstance() {
        return new MessageTransformer();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessageTransformer mo10268get() {
        return provideInstance();
    }
}
