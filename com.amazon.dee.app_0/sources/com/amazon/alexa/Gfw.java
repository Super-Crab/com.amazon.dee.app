package com.amazon.alexa;

import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.utils.security.SignatureVerifier;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: MessengerModule_ProvidesMessageReceiverManagerFactory.java */
/* loaded from: classes.dex */
public final class Gfw implements Factory<MessageReceiversManager> {
    public static MessageReceiversManager zZm(C0178PyL c0178PyL, SignatureVerifier signatureVerifier) {
        return (MessageReceiversManager) Preconditions.checkNotNull(c0178PyL.zZm(signatureVerifier), "Cannot return null from a non-@Nullable @Provides method");
    }
}
