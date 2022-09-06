package com.amazon.alexa.api;

import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.utils.Provider;
/* loaded from: classes6.dex */
abstract class x<T> {
    protected final Provider<MessageReceiversManager> messageReceiversManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public x(Provider<MessageReceiversManager> provider) {
        this.messageReceiversManager = provider;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract w createBundlerFor(T t);
}
