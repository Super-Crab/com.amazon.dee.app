package io.ktor.network.util;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.amazon.device.messaging.ADMRegistrationConstants;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Coroutines.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0011\u001a\u00020\n2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0001J\u0011\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0097\u0001J\t\u0010\u0018\u001a\u00020\u0015H\u0097\u0001J2\u0010\u0019\u001a\u00020\u00152'\u0010\u001a\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00150\u001bj\u0002`\u001eH\u0096\u0001J\u001e\u0010\u001f\u001a\u00020\u00152\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000!H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\"J$\u0010#\u001a\u0004\u0018\u00010\u00172\u0006\u0010$\u001a\u00028\u00002\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0017H\u0097\u0001¢\u0006\u0002\u0010&J\u0012\u0010'\u001a\u0004\u0018\u00010\u00172\u0006\u0010(\u001a\u00020\u0013H\u0016J\u001a\u0010)\u001a\u00020\u0015*\u00020*2\u0006\u0010$\u001a\u00028\u0000H\u0097\u0001¢\u0006\u0002\u0010+J\u0015\u0010,\u001a\u00020\u0015*\u00020*2\u0006\u0010(\u001a\u00020\u0013H\u0097\u0001R\u0012\u0010\u0005\u001a\u00020\u0006X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\nX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u0012\u0010\f\u001a\u00020\nX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\u000bR\u0012\u0010\r\u001a\u00020\nX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0012\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, d2 = {"Lio/ktor/network/util/TrackingContinuation;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CancellableContinuation;", "delegate", "(Lkotlinx/coroutines/CancellableContinuation;)V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "isActive", "", "()Z", "isCancelled", "isCompleted", "suspensionPoint", "Ljava/lang/Exception;", "Lkotlin/Exception;", DeviceStateModule.CANCEL, "cause", "", "completeResume", "", "token", "", "initCancellability", "invokeOnCancellation", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "tryResume", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "tryResumeWithException", ADMRegistrationConstants.CALL_EXCEPTION, "resumeUndispatched", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class TrackingContinuation<T> implements CancellableContinuation<T> {
    private final CancellableContinuation<T> delegate;
    private final Exception suspensionPoint;

    /* JADX WARN: Multi-variable type inference failed */
    public TrackingContinuation(@NotNull CancellableContinuation<? super T> delegate) {
        Intrinsics.checkParameterIsNotNull(delegate, "delegate");
        this.delegate = delegate;
        Exception exc = new Exception("Suspension point");
        exc.fillInStackTrace();
        this.suspensionPoint = exc;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean cancel(@Nullable Throwable th) {
        return this.delegate.cancel(th);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    @InternalCoroutinesApi
    public void completeResume(@NotNull Object token) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        this.delegate.completeResume(token);
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return this.delegate.getContext();
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This function is no longer used. It is left for binary compatibility with code compiled before kotlinx.coroutines 1.1.0. ")
    @InternalCoroutinesApi
    public /* synthetic */ void initCancellability() {
        this.delegate.initCancellability();
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void invokeOnCancellation(@NotNull Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.delegate.invokeOnCancellation(handler);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isActive() {
        return this.delegate.isActive();
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isCancelled() {
        return this.delegate.isCancelled();
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isCompleted() {
        return this.delegate.isCompleted();
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    @ExperimentalCoroutinesApi
    public void resumeUndispatched(@NotNull CoroutineDispatcher receiver$0, T t) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        this.delegate.resumeUndispatched(receiver$0, t);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    @ExperimentalCoroutinesApi
    public void resumeUndispatchedWithException(@NotNull CoroutineDispatcher receiver$0, @NotNull Throwable exception) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        this.delegate.resumeUndispatchedWithException(receiver$0, exception);
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object obj) {
        if (Result.m10385isSuccessimpl(obj)) {
            this.delegate.resumeWith(obj);
            return;
        }
        this.suspensionPoint.initCause(Result.m10381exceptionOrNullimpl(obj));
        CancellableContinuation<T> cancellableContinuation = this.delegate;
        Exception exc = this.suspensionPoint;
        Result.Companion companion = Result.Companion;
        cancellableContinuation.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(exc)));
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    @InternalCoroutinesApi
    @Nullable
    public Object tryResume(T t, @Nullable Object obj) {
        return this.delegate.tryResume(t, obj);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    @Nullable
    public Object tryResumeWithException(@NotNull Throwable exception) {
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        Object tryResumeWithException = this.delegate.tryResumeWithException(this.suspensionPoint);
        if (tryResumeWithException != null) {
            this.suspensionPoint.initCause(exception);
        }
        return tryResumeWithException;
    }
}
