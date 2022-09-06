package com.amazon.alexa.accessory.internal.util;
/* loaded from: classes.dex */
public final class WrapperUtil {

    /* loaded from: classes.dex */
    public interface ConsumerWithException<I, X extends Exception> {
        void consume(I i) throws Exception;
    }

    /* loaded from: classes.dex */
    public interface ParamedSupplier<O, I, X extends Exception> {
        O get(I i) throws Exception;
    }

    /* loaded from: classes.dex */
    public interface Supplier<O, X extends Exception> {
        O get() throws Exception;
    }

    /* loaded from: classes.dex */
    public interface TwoParamedSupplier<O, P1, P2, X extends Exception> {
        O get(P1 p1, P2 p2) throws Exception;
    }

    private WrapperUtil() {
    }
}
