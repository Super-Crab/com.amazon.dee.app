package com.amazon.scxml.internal;

import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.facebook.react.bridge.BaseJavaModule;
import java.util.LinkedList;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DispatchQueue.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u000f\u001a\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\u001c\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\b\u0010\u0014\u001a\u00020\u000eH\u0002J\u0014\u0010\u0015\u001a\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/scxml/internal/DispatchQueue;", "", "()V", "executeSemaphore", "Ljava/util/concurrent/Semaphore;", "executor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "queueSemaphore", OperationalEventType.TIMER, "Ljava/util/Timer;", "workQueue", "Ljava/util/LinkedList;", "Lkotlin/Function0;", "", BaseJavaModule.METHOD_TYPE_ASYNC, "completion", "asyncAfter", "timeout", "", "processNextWorkItem", BaseJavaModule.METHOD_TYPE_SYNC, "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DispatchQueue {
    private final Semaphore queueSemaphore = new Semaphore(1, true);
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Timer timer = new Timer();
    private final Semaphore executeSemaphore = new Semaphore(1, false);
    private final LinkedList<Function0<Unit>> workQueue = new LinkedList<>();

    public static final /* synthetic */ Semaphore access$getQueueSemaphore$p(DispatchQueue dispatchQueue) {
        return dispatchQueue.queueSemaphore;
    }

    public static final /* synthetic */ LinkedList access$getWorkQueue$p(DispatchQueue dispatchQueue) {
        return dispatchQueue.workQueue;
    }

    public static final /* synthetic */ void access$processNextWorkItem(DispatchQueue dispatchQueue) {
        dispatchQueue.processNextWorkItem();
    }

    public final void processNextWorkItem() {
        this.queueSemaphore.acquire();
        try {
            if (this.workQueue.isEmpty()) {
                return;
            }
            if (!this.executeSemaphore.tryAcquire()) {
                return;
            }
            Function0<Unit> removeFirst = this.workQueue.removeFirst();
            Intrinsics.checkExpressionValueIsNotNull(removeFirst, "workQueue.removeFirst()");
            final Function0<Unit> function0 = removeFirst;
            this.queueSemaphore.release();
            this.executor.submit(new Runnable() { // from class: com.amazon.scxml.internal.DispatchQueue$processNextWorkItem$1
                @Override // java.lang.Runnable
                public final void run() {
                    Semaphore semaphore;
                    try {
                        function0.mo12560invoke();
                    } finally {
                        semaphore = DispatchQueue.this.executeSemaphore;
                        semaphore.release();
                        DispatchQueue.this.processNextWorkItem();
                    }
                }
            });
        } finally {
            this.queueSemaphore.release();
        }
    }

    public final void async(@NotNull Function0<Unit> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        this.queueSemaphore.acquire();
        try {
            this.workQueue.add(new DispatchQueue$async$1(completion));
            this.queueSemaphore.release();
            processNextWorkItem();
        } catch (Throwable th) {
            this.queueSemaphore.release();
            throw th;
        }
    }

    public final void asyncAfter(double d, @NotNull Function0<Unit> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        this.timer.schedule(new DispatchQueue$asyncAfter$$inlined$schedule$1(this, completion), (long) (d * 1000));
    }

    public final void sync(@NotNull Function0<Unit> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.queueSemaphore.acquire();
        try {
            this.workQueue.add(new DispatchQueue$sync$1(completion, countDownLatch));
            this.queueSemaphore.release();
            processNextWorkItem();
            countDownLatch.await();
        } catch (Throwable th) {
            this.queueSemaphore.release();
            throw th;
        }
    }
}
