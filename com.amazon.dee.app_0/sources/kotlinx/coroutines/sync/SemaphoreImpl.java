package kotlinx.coroutines.sync;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.SegmentQueue;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Semaphore.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u00002\u00020\u001d2\b\u0012\u0004\u0012\u00020\u000e0\u001eB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0007\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\t\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\bJ\r\u0010\n\u001a\u00020\u0001¢\u0006\u0004\b\n\u0010\u000bJ!\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0014\u0010\u0013J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001a\u001a\u00020\u00018V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00018\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreImpl;", "", "permits", "acquiredPermits", "<init>", "(II)V", "", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addToQueueAndSuspend", "incPermits", "()I", "", "id", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "prev", "newSegment", "(JLkotlinx/coroutines/sync/SemaphoreSegment;)Lkotlinx/coroutines/sync/SemaphoreSegment;", "release", "()V", "resumeNextFromQueue$kotlinx_coroutines_core", "resumeNextFromQueue", "", "tryAcquire", "()Z", "getAvailablePermits", "availablePermits", "I", "kotlinx-coroutines-core", "Lkotlinx/coroutines/sync/Semaphore;", "Lkotlinx/coroutines/internal/SegmentQueue;"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SemaphoreImpl extends SegmentQueue<SemaphoreSegment> implements Semaphore {
    private volatile int _availablePermits;
    private volatile long deqIdx;
    volatile long enqIdx;
    private final int permits;
    private static final AtomicIntegerFieldUpdater _availablePermits$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreImpl.class, "_availablePermits");
    static final AtomicLongFieldUpdater enqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "enqIdx");
    private static final AtomicLongFieldUpdater deqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "deqIdx");

    public SemaphoreImpl(int i, int i2) {
        this.permits = i;
        boolean z = true;
        if (!(this.permits > 0)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Semaphore should have at least 1 permit, but had ");
            outline107.append(this.permits);
            throw new IllegalArgumentException(outline107.toString().toString());
        }
        if ((i2 < 0 || this.permits < i2) ? false : z) {
            this._availablePermits = this.permits - i2;
            this.enqIdx = 0L;
            this.deqIdx = 0L;
            return;
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("The number of acquired permits should be in 0..");
        outline1072.append(this.permits);
        throw new IllegalArgumentException(outline1072.toString().toString());
    }

    public static final /* synthetic */ SemaphoreSegment access$getSegment(SemaphoreImpl semaphoreImpl, SemaphoreSegment semaphoreSegment, long j) {
        return semaphoreImpl.getSegment(semaphoreSegment, j);
    }

    public static final /* synthetic */ SemaphoreSegment access$getTail$p(SemaphoreImpl semaphoreImpl) {
        return semaphoreImpl.getTail();
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    @Nullable
    public Object acquire(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        if (_availablePermits$FU.getAndDecrement(this) > 0) {
            return Unit.INSTANCE;
        }
        Object addToQueueAndSuspend = addToQueueAndSuspend(continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return addToQueueAndSuspend == coroutine_suspended ? addToQueueAndSuspend : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x005a  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final /* synthetic */ java.lang.Object addToQueueAndSuspend(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r6 = this;
            kotlin.coroutines.Continuation r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r7)
            kotlinx.coroutines.CancellableContinuationImpl r0 = kotlinx.coroutines.CancellableContinuationKt.getOrCreateCancellableContinuation(r0)
            kotlinx.coroutines.sync.SemaphoreSegment r1 = access$getTail$p(r6)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = kotlinx.coroutines.sync.SemaphoreImpl.enqIdx$FU
            long r2 = r2.getAndIncrement(r6)
            int r4 = kotlinx.coroutines.sync.SemaphoreKt.access$getSEGMENT_SIZE$p()
            long r4 = (long) r4
            long r4 = r2 / r4
            kotlinx.coroutines.sync.SemaphoreSegment r1 = access$getSegment(r6, r1, r4)
            int r4 = kotlinx.coroutines.sync.SemaphoreKt.access$getSEGMENT_SIZE$p()
            long r4 = (long) r4
            long r2 = r2 % r4
            int r2 = (int) r2
            if (r1 == 0) goto L45
            java.util.concurrent.atomic.AtomicReferenceArray r3 = r1.acquirers
            java.lang.Object r3 = r3.get(r2)
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.sync.SemaphoreKt.access$getRESUMED$p()
            if (r3 == r4) goto L45
            r3 = 0
            java.util.concurrent.atomic.AtomicReferenceArray r4 = r1.acquirers
            boolean r3 = r4.compareAndSet(r2, r3, r0)
            if (r3 != 0) goto L3c
            goto L45
        L3c:
            kotlinx.coroutines.sync.CancelSemaphoreAcquisitionHandler r3 = new kotlinx.coroutines.sync.CancelSemaphoreAcquisitionHandler
            r3.<init>(r6, r1, r2)
            r0.invokeOnCancellation(r3)
            goto L50
        L45:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            kotlin.Result$Companion r2 = kotlin.Result.Companion
            java.lang.Object r1 = kotlin.Result.m10378constructorimpl(r1)
            r0.resumeWith(r1)
        L50:
            java.lang.Object r0 = r0.getResult()
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r0 != r1) goto L5d
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r7)
        L5d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreImpl.addToQueueAndSuspend(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public int getAvailablePermits() {
        return Math.max(this._availablePermits, 0);
    }

    public final int incPermits() {
        int i;
        do {
            i = this._availablePermits;
            if (!(i < this.permits)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("The number of released permits cannot be greater than ");
                outline107.append(this.permits);
                throw new IllegalStateException(outline107.toString().toString());
            }
        } while (!_availablePermits$FU.compareAndSet(this, i, i + 1));
        return i;
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public void release() {
        if (incPermits() >= 0) {
            return;
        }
        resumeNextFromQueue$kotlinx_coroutines_core();
    }

    public final void resumeNextFromQueue$kotlinx_coroutines_core() {
        int i;
        int i2;
        Symbol symbol;
        Symbol symbol2;
        while (true) {
            long andIncrement = deqIdx$FU.getAndIncrement(this);
            i = SemaphoreKt.SEGMENT_SIZE;
            SemaphoreSegment segmentAndMoveHead = getSegmentAndMoveHead(getHead(), andIncrement / i);
            if (segmentAndMoveHead != null) {
                i2 = SemaphoreKt.SEGMENT_SIZE;
                symbol = SemaphoreKt.RESUMED;
                Object andSet = segmentAndMoveHead.acquirers.getAndSet((int) (andIncrement % i2), symbol);
                if (andSet == null) {
                    return;
                }
                symbol2 = SemaphoreKt.CANCELLED;
                if (andSet != symbol2) {
                    Unit unit = Unit.INSTANCE;
                    Result.Companion companion = Result.Companion;
                    ((CancellableContinuation) andSet).resumeWith(Result.m10378constructorimpl(unit));
                    return;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public boolean tryAcquire() {
        int i;
        do {
            i = this._availablePermits;
            if (i <= 0) {
                return false;
            }
        } while (!_availablePermits$FU.compareAndSet(this, i, i - 1));
        return true;
    }

    @Override // kotlinx.coroutines.internal.SegmentQueue
    @NotNull
    public SemaphoreSegment newSegment(long j, @Nullable SemaphoreSegment semaphoreSegment) {
        return new SemaphoreSegment(j, semaphoreSegment);
    }
}
