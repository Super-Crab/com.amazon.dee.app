package io.ktor.util.cio;

import com.amazon.alexa.location.utils.MetricsUtil;
import io.ktor.util.InternalAPI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlinx.coroutines.CancellableContinuation;
/* compiled from: Semaphore.kt */
@InternalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\r\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0006\u0010\u000f\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lio/ktor/util/cio/Semaphore;", "", MetricsUtil.LegacyMetricTypes.LIMIT, "", "(I)V", "getLimit", "()I", "permits", "Lkotlinx/atomicfu/AtomicInt;", "waiters", "Ljava/util/concurrent/ConcurrentHashMap;", "Lkotlinx/coroutines/CancellableContinuation;", "", "enter", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "leave", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class Semaphore {
    static final AtomicIntegerFieldUpdater permits$FU = AtomicIntegerFieldUpdater.newUpdater(Semaphore.class, "permits");
    private final int limit;
    volatile int permits;
    private final ConcurrentHashMap<CancellableContinuation<Unit>, Unit> waiters = new ConcurrentHashMap<>();

    public Semaphore(int i) {
        this.limit = i;
        this.permits = this.limit;
        if (this.limit > 0) {
            return;
        }
        throw new IllegalStateException("Semaphore limit should be > 0".toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0092 A[SYNTHETIC] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object enter(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof io.ktor.util.cio.Semaphore$enter$1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.util.cio.Semaphore$enter$1 r0 = (io.ktor.util.cio.Semaphore$enter$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.util.cio.Semaphore$enter$1 r0 = new io.ktor.util.cio.Semaphore$enter$1
            r0.<init>(r6, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3e
            if (r2 != r3) goto L36
            int r2 = r0.I$0
            java.lang.Object r2 = r0.L$0
            io.ktor.util.cio.Semaphore r2 = (io.ktor.util.cio.Semaphore) r2
            boolean r4 = r7 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L31
            r7 = r2
            goto L43
        L31:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r7 = r7.exception
            throw r7
        L36:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L3e:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L95
            r7 = r6
        L43:
            int r2 = r7.permits
            if (r2 <= 0) goto L54
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r4 = io.ktor.util.cio.Semaphore.permits$FU
            int r5 = r2 + (-1)
            boolean r4 = r4.compareAndSet(r7, r2, r5)
            if (r4 == 0) goto L54
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L54:
            r0.L$0 = r7
            r0.I$0 = r2
            r0.label = r3
            kotlinx.coroutines.CancellableContinuationImpl r2 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)
            r2.<init>(r4, r3)
            java.util.concurrent.ConcurrentHashMap r4 = access$getWaiters$p(r7)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            r4.put(r2, r5)
            int r4 = r7.permits
            if (r4 <= 0) goto L85
            java.util.concurrent.ConcurrentHashMap r4 = access$getWaiters$p(r7)
            java.lang.Object r4 = r4.remove(r2)
            if (r4 == 0) goto L85
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            kotlin.Result$Companion r5 = kotlin.Result.Companion
            java.lang.Object r4 = kotlin.Result.m10378constructorimpl(r4)
            r2.resumeWith(r4)
        L85:
            java.lang.Object r2 = r2.getResult()
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r2 != r4) goto L92
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L92:
            if (r2 != r1) goto L43
            return r1
        L95:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r7 = r7.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.cio.Semaphore.enter(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final int getLimit() {
        return this.limit;
    }

    public final void leave() {
        int incrementAndGet = permits$FU.incrementAndGet(this);
        while (incrementAndGet > 0 && (!this.waiters.isEmpty())) {
            CancellableContinuation<Unit> nextElement = this.waiters.keys().nextElement();
            if (nextElement != null && this.waiters.remove(nextElement) != null) {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.Companion;
                nextElement.resumeWith(Result.m10378constructorimpl(unit));
                incrementAndGet = this.permits;
            }
        }
    }
}
