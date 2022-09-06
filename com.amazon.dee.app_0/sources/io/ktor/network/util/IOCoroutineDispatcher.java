package io.ktor.network.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.util.KtorExperimentalAPI;
import io.ktor.util.internal.LockFreeLinkedListHead;
import io.ktor.util.internal.LockFreeLinkedListNode;
import java.io.Closeable;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: IOCoroutineDispatcher.kt */
@KtorExperimentalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0003\u001d\u001e\u001fB\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u001c\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0017j\u0002`\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0012H\u0002J\u0010\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u0014\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"Lio/ktor/network/util/IOCoroutineDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Ljava/io/Closeable;", "Lkotlinx/io/core/Closeable;", "nThreads", "", "(I)V", "dispatcherThreadGroup", "Ljava/lang/ThreadGroup;", "dispatcherThreadGroup$annotations", "()V", "tasks", "Lio/ktor/util/internal/LockFreeLinkedListHead;", "threads", "", "Lio/ktor/network/util/IOCoroutineDispatcher$IOThread;", "[Lio/ktor/network/util/IOCoroutineDispatcher$IOThread;", "close", "", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "resumeAllThreads", "resumeAnyThread", "node", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "IODispatchedTask", "IOThread", "Poison", "ktor-network"}, k = 1, mv = {1, 1, 13})
@Deprecated(level = DeprecationLevel.ERROR, message = "This is going to be deprecated. Use kotlinx.coroutines dispatchers")
/* loaded from: classes3.dex */
public final class IOCoroutineDispatcher extends CoroutineDispatcher implements Closeable {
    private final int nThreads;
    private final IOThread[] threads;
    private final ThreadGroup dispatcherThreadGroup = new ThreadGroup(PoolsKt.getIoThreadGroup(), "io-pool-group-sub");
    private final LockFreeLinkedListHead tasks = new LockFreeLinkedListHead();

    /* compiled from: IOCoroutineDispatcher.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B\u0011\u0012\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\tH\u0096\u0001R\u0015\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lio/ktor/network/util/IOCoroutineDispatcher$IODispatchedTask;", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "r", "(Ljava/lang/Runnable;)V", "getR", "()Ljava/lang/Runnable;", "run", "", "ktor-network"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    private static final class IODispatchedTask extends LockFreeLinkedListNode implements Runnable {
        @NotNull
        private final Runnable r;

        public IODispatchedTask(@NotNull Runnable r) {
            Intrinsics.checkParameterIsNotNull(r, "r");
            this.r = r;
        }

        @NotNull
        public final Runnable getR() {
            return this.r;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.r.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: IOCoroutineDispatcher.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0019\u0010\u0012\u001a\n\u0018\u00010\u0013j\u0004\u0018\u0001`\u0014H\u0082Hø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u0019\u0010\u0016\u001a\n\u0018\u00010\u0013j\u0004\u0018\u0001`\u0014H\u0082Hø\u0001\u0000¢\u0006\u0002\u0010\u0015J\b\u0010\u0017\u001a\u00020\fH\u0016J\u0006\u0010\u0018\u001a\u00020\u0019J\u0011\u0010\u001a\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0015R\"\u0010\t\u001a\u0016\u0012\f\u0012\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\u0004\u0012\u00020\r0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lio/ktor/network/util/IOCoroutineDispatcher$IOThread;", "Ljava/lang/Thread;", "number", "", "tasks", "Lio/ktor/util/internal/LockFreeLinkedListHead;", "dispatcherThreadGroup", "Ljava/lang/ThreadGroup;", "(ILio/ktor/util/internal/LockFreeLinkedListHead;Ljava/lang/ThreadGroup;)V", "awaitSuspendBlock", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "cont", "onException", "t", "", "receiveOrNull", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrNullSuspend", "run", "tryResume", "", "waitForTasks", "Companion", "ktor-network"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class IOThread extends Thread {
        public static final Companion Companion = new Companion(null);
        private static final AtomicReferenceFieldUpdater<IOThread, Continuation<Unit>> ThreadCont;
        private final Function1<Continuation<? super Unit>, Object> awaitSuspendBlock;
        private volatile Continuation<? super Unit> cont;
        private final int number;
        private final LockFreeLinkedListHead tasks;

        /* compiled from: IOCoroutineDispatcher.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R*\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00060\u00048\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002¨\u0006\t"}, d2 = {"Lio/ktor/network/util/IOCoroutineDispatcher$IOThread$Companion;", "", "()V", "ThreadCont", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "Lio/ktor/network/util/IOCoroutineDispatcher$IOThread;", "Lkotlin/coroutines/Continuation;", "", "ThreadCont$annotations", "ktor-network"}, k = 1, mv = {1, 1, 13})
        /* loaded from: classes3.dex */
        public static final class Companion {
            private Companion() {
            }

            @JvmStatic
            private static /* synthetic */ void ThreadCont$annotations() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        static {
            AtomicReferenceFieldUpdater<IOThread, Continuation<Unit>> newUpdater = AtomicReferenceFieldUpdater.newUpdater(IOThread.class, Continuation.class, "cont");
            if (newUpdater != null) {
                ThreadCont = newUpdater;
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.util.concurrent.atomic.AtomicReferenceFieldUpdater<io.ktor.network.util.IOCoroutineDispatcher.IOThread, kotlin.coroutines.Continuation<kotlin.Unit>?>");
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IOThread(int i, @NotNull LockFreeLinkedListHead tasks, @NotNull ThreadGroup dispatcherThreadGroup) {
            super(dispatcherThreadGroup, "io-thread-" + i);
            Intrinsics.checkParameterIsNotNull(tasks, "tasks");
            Intrinsics.checkParameterIsNotNull(dispatcherThreadGroup, "dispatcherThreadGroup");
            this.number = i;
            this.tasks = tasks;
            setDaemon(true);
            this.awaitSuspendBlock = new IOCoroutineDispatcher$IOThread$awaitSuspendBlock$1(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void onException(Throwable th) {
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
            currentThread.getUncaughtExceptionHandler().uncaughtException(this, th);
        }

        private final /* synthetic */ Object receiveOrNull(Continuation<? super Runnable> continuation) {
            LockFreeLinkedListNode lockFreeLinkedListNode;
            LockFreeLinkedListNode lockFreeLinkedListNode2;
            LockFreeLinkedListHead lockFreeLinkedListHead = this.tasks;
            while (true) {
                Object next = lockFreeLinkedListHead.getNext();
                if (next != null) {
                    lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
                    if (lockFreeLinkedListNode != lockFreeLinkedListHead && (lockFreeLinkedListNode instanceof Runnable)) {
                        if (lockFreeLinkedListNode.remove()) {
                            break;
                        }
                        lockFreeLinkedListNode.helpDelete();
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type io.ktor.util.internal.Node /* = io.ktor.util.internal.LockFreeLinkedListNode */");
                }
            }
            lockFreeLinkedListNode = null;
            Runnable runnable = (Runnable) lockFreeLinkedListNode;
            if (runnable != null) {
                return runnable;
            }
            while (true) {
                LockFreeLinkedListHead lockFreeLinkedListHead2 = this.tasks;
                while (true) {
                    Object next2 = lockFreeLinkedListHead2.getNext();
                    if (next2 != null) {
                        lockFreeLinkedListNode2 = (LockFreeLinkedListNode) next2;
                        if (lockFreeLinkedListNode2 != lockFreeLinkedListHead2 && (lockFreeLinkedListNode2 instanceof Runnable)) {
                            if (lockFreeLinkedListNode2.remove()) {
                                break;
                            }
                            lockFreeLinkedListNode2.helpDelete();
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type io.ktor.util.internal.Node /* = io.ktor.util.internal.LockFreeLinkedListNode */");
                    }
                }
                lockFreeLinkedListNode2 = null;
                Runnable runnable2 = (Runnable) lockFreeLinkedListNode2;
                if (runnable2 != null) {
                    return runnable2;
                }
                if (this.tasks.getNext() instanceof Poison) {
                    return null;
                }
                InlineMarker.mark(0);
                waitForTasks(continuation);
                InlineMarker.mark(2);
                InlineMarker.mark(1);
            }
        }

        private final /* synthetic */ Object receiveOrNullSuspend(Continuation<? super Runnable> continuation) {
            LockFreeLinkedListNode lockFreeLinkedListNode;
            while (true) {
                LockFreeLinkedListHead lockFreeLinkedListHead = this.tasks;
                while (true) {
                    Object next = lockFreeLinkedListHead.getNext();
                    if (next != null) {
                        lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
                        if (lockFreeLinkedListNode != lockFreeLinkedListHead && (lockFreeLinkedListNode instanceof Runnable)) {
                            if (lockFreeLinkedListNode.remove()) {
                                break;
                            }
                            lockFreeLinkedListNode.helpDelete();
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type io.ktor.util.internal.Node /* = io.ktor.util.internal.LockFreeLinkedListNode */");
                    }
                }
                lockFreeLinkedListNode = null;
                Runnable runnable = (Runnable) lockFreeLinkedListNode;
                if (runnable != null) {
                    return runnable;
                }
                if (this.tasks.getNext() instanceof Poison) {
                    return null;
                }
                InlineMarker.mark(0);
                waitForTasks(continuation);
                InlineMarker.mark(2);
                InlineMarker.mark(1);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("io-dispatcher-executor-");
            outline107.append(this.number);
            BuildersKt.runBlocking(new CoroutineName(outline107.toString()), new IOCoroutineDispatcher$IOThread$run$1(this, null));
        }

        public final boolean tryResume() {
            Continuation<Unit> andSet = ThreadCont.getAndSet(this, null);
            if (andSet != null) {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.Companion;
                andSet.resumeWith(Result.m10378constructorimpl(unit));
                return true;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final /* synthetic */ Object waitForTasks(@NotNull Continuation<? super Unit> continuation) {
            Object coroutine_suspended;
            Object mo12165invoke = this.awaitSuspendBlock.mo12165invoke(continuation);
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (mo12165invoke == coroutine_suspended) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return mo12165invoke;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: IOCoroutineDispatcher.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0003"}, d2 = {"Lio/ktor/network/util/IOCoroutineDispatcher$Poison;", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "()V", "ktor-network"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Poison extends LockFreeLinkedListNode {
    }

    public IOCoroutineDispatcher(int i) {
        this.nThreads = i;
        if (this.nThreads > 0) {
            IOThread[] iOThreadArr = new IOThread[this.nThreads];
            int length = iOThreadArr.length;
            int i2 = 0;
            while (i2 < length) {
                int i3 = i2 + 1;
                iOThreadArr[i2] = new IOThread(i3, this.tasks, this.dispatcherThreadGroup);
                i2 = i3;
            }
            this.threads = iOThreadArr;
            for (IOThread iOThread : this.threads) {
                iOThread.start();
            }
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("nThreads should be positive but "), this.nThreads, " specified").toString());
    }

    private static /* synthetic */ void dispatcherThreadGroup$annotations() {
    }

    private final void resumeAllThreads() {
        IOThread[] iOThreadArr = this.threads;
        int i = this.nThreads;
        for (int i2 = 0; i2 < i; i2++) {
            iOThreadArr[i2].tryResume();
        }
    }

    private final void resumeAnyThread(LockFreeLinkedListNode lockFreeLinkedListNode) {
        IOThread[] iOThreadArr = this.threads;
        int i = this.nThreads;
        for (int i2 = 0; i2 < i && !iOThreadArr[i2].tryResume() && !lockFreeLinkedListNode.isRemoved(); i2++) {
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        if (this.tasks.getPrev() instanceof Poison) {
            return;
        }
        LockFreeLinkedListHead lockFreeLinkedListHead = this.tasks;
        Poison poison = new Poison();
        do {
            Object prev = lockFreeLinkedListHead.getPrev();
            if (prev != null) {
                lockFreeLinkedListNode = (LockFreeLinkedListNode) prev;
                if (!(!(lockFreeLinkedListNode instanceof Poison))) {
                    break;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type io.ktor.util.internal.Node /* = io.ktor.util.internal.LockFreeLinkedListNode */");
            }
        } while (!lockFreeLinkedListNode.addNext(poison, lockFreeLinkedListHead));
        resumeAllThreads();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* renamed from: dispatch */
    public void mo12302dispatch(@NotNull CoroutineContext context, @NotNull Runnable block) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(block, "block");
        IODispatchedTask iODispatchedTask = new IODispatchedTask(block);
        this.tasks.addLast(iODispatchedTask);
        resumeAnyThread(iODispatchedTask);
    }
}
