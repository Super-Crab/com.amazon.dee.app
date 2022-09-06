package com.amazon.alexa.accessory.internal.repositories;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes.dex */
public abstract class BaseProducer<E> implements Producer<E> {
    private E handler;

    @Override // com.amazon.alexa.accessory.repositories.Producer
    public final void attachActionHandler(E e) {
        Preconditions.mainThread();
        Preconditions.notNull(e, "handler");
        if (this.handler == null) {
            this.handler = e;
            return;
        }
        throw new IllegalStateException("Action handle is already attached!");
    }

    @Override // com.amazon.alexa.accessory.repositories.Producer
    public final void detachActionHandler(E e) {
        Preconditions.mainThread();
        Preconditions.notNull(e, "handler");
        if (this.handler == e) {
            this.handler = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final E getHandler() {
        E e = this.handler;
        if (e != null) {
            return e;
        }
        throw new IllegalStateException("Action handler is not attached!");
    }
}
