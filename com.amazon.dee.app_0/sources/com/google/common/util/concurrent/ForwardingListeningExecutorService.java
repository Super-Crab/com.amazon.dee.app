package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
@CanIgnoreReturnValue
@GwtIncompatible
/* loaded from: classes3.dex */
public abstract class ForwardingListeningExecutorService extends ForwardingExecutorService implements ListeningExecutorService {
    protected ForwardingListeningExecutorService() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.ForwardingExecutorService, com.google.common.collect.ForwardingObject
    /* renamed from: delegate */
    public abstract ListeningExecutorService mo8280delegate();

    @Override // com.google.common.util.concurrent.ForwardingExecutorService, java.util.concurrent.ExecutorService
    /* renamed from: submit  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Future mo8277submit(Runnable runnable, Object obj) {
        return mo8277submit(runnable, (Runnable) obj);
    }

    @Override // com.google.common.util.concurrent.ForwardingExecutorService, java.util.concurrent.ExecutorService
    /* renamed from: submit */
    public <T> ListenableFuture<T> mo8278submit(Callable<T> callable) {
        return mo8280delegate().mo8259submit((Callable) callable);
    }

    @Override // com.google.common.util.concurrent.ForwardingExecutorService, java.util.concurrent.ExecutorService
    /* renamed from: submit */
    public ListenableFuture<?> mo8276submit(Runnable runnable) {
        return mo8280delegate().mo8257submit(runnable);
    }

    @Override // com.google.common.util.concurrent.ForwardingExecutorService, java.util.concurrent.ExecutorService
    /* renamed from: submit */
    public <T> ListenableFuture<T> mo8277submit(Runnable runnable, T t) {
        return mo8280delegate().mo8258submit(runnable, (Runnable) t);
    }
}
