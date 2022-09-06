package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingObject;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
@CanIgnoreReturnValue
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class ForwardingFuture<V> extends ForwardingObject implements Future<V> {

    /* loaded from: classes3.dex */
    public static abstract class SimpleForwardingFuture<V> extends ForwardingFuture<V> {
        private final Future<V> delegate;

        protected SimpleForwardingFuture(Future<V> future) {
            this.delegate = (Future) Preconditions.checkNotNull(future);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.ForwardingFuture, com.google.common.collect.ForwardingObject
        /* renamed from: delegate  reason: collision with other method in class */
        public final Future<V> mo8280delegate() {
            return this.delegate;
        }
    }

    public boolean cancel(boolean z) {
        return mo8280delegate().cancel(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    /* renamed from: delegate  reason: collision with other method in class */
    public abstract Future<? extends V> mo8280delegate();

    @Override // java.util.concurrent.Future
    public V get() throws InterruptedException, ExecutionException {
        return mo8280delegate().get();
    }

    public boolean isCancelled() {
        return mo8280delegate().isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return mo8280delegate().isDone();
    }

    @Override // java.util.concurrent.Future
    public V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return mo8280delegate().get(j, timeUnit);
    }
}
