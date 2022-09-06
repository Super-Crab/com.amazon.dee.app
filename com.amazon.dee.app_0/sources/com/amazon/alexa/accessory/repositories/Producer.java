package com.amazon.alexa.accessory.repositories;
/* loaded from: classes6.dex */
public interface Producer<E> {

    /* loaded from: classes6.dex */
    public interface Result<T> {
        void complete(T t);

        void completeWithError(Throwable th);
    }

    void attachActionHandler(E e);

    void detachActionHandler(E e);
}
