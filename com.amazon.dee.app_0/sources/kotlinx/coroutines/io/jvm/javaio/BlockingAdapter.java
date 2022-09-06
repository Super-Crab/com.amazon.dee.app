package kotlinx.coroutines.io.jvm.javaio;

import com.amazon.alexa.routing.api.RouteParameter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.io.internal.CoroutinesEventLoop;
import kotlinx.coroutines.io.internal.EventLoopExperimentalKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Blocking.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0000\b\"\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u000bH\u0004J\u0011\u0010\u001b\u001a\u00020\tH¤@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0019\u0010 \u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u000bH\u0084Hø\u0001\u0000¢\u0006\u0002\u0010!J\u0006\u0010\"\u001a\u00020\tJ\u000e\u0010#\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u0001J\u001e\u0010#\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0017\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, d2 = {"Lkotlinx/coroutines/io/jvm/javaio/BlockingAdapter;", "", RouteParameter.PARENT, "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;)V", "disposable", "Lkotlinx/coroutines/DisposableHandle;", "end", "Lkotlin/coroutines/Continuation;", "", "<set-?>", "", "length", "getLength", "()I", "offset", "getOffset", "getParent", "()Lkotlinx/coroutines/Job;", "result", "Lkotlinx/atomicfu/AtomicInt;", "state", "Lkotlinx/atomicfu/AtomicRef;", "state$annotations", "()V", "finish", "rc", "loop", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parkingLoop", "thread", "Ljava/lang/Thread;", "rendezvous", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shutdown", "submitAndAwait", "jobToken", "buffer", "", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
abstract class BlockingAdapter {
    private final DisposableHandle disposable;
    private final Continuation<Unit> end;
    private int length;
    private int offset;
    @Nullable
    private final Job parent;
    volatile int result;
    volatile Object state;
    static final AtomicReferenceFieldUpdater state$FU = AtomicReferenceFieldUpdater.newUpdater(BlockingAdapter.class, Object.class, "state");
    static final AtomicIntegerFieldUpdater result$FU = AtomicIntegerFieldUpdater.newUpdater(BlockingAdapter.class, "result");

    public BlockingAdapter() {
        this(null, 1, null);
    }

    public BlockingAdapter(@Nullable Job job) {
        this.parent = job;
        this.end = new Continuation<Unit>() { // from class: kotlinx.coroutines.io.jvm.javaio.BlockingAdapter$end$1
            @NotNull
            private final CoroutineContext context;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.context = BlockingAdapter.this.getParent() != null ? UnsafeBlockingTrampoline.INSTANCE.plus(BlockingAdapter.this.getParent()) : UnsafeBlockingTrampoline.INSTANCE;
            }

            @Override // kotlin.coroutines.Continuation
            @NotNull
            public CoroutineContext getContext() {
                return this.context;
            }

            @Override // kotlin.coroutines.Continuation
            public void resumeWith(@NotNull Object obj) {
                Object obj2;
                boolean z;
                Throwable m10381exceptionOrNullimpl;
                DisposableHandle disposableHandle;
                Job parent;
                Object m10381exceptionOrNullimpl2 = Result.m10381exceptionOrNullimpl(obj);
                if (m10381exceptionOrNullimpl2 == null) {
                    m10381exceptionOrNullimpl2 = Unit.INSTANCE;
                }
                BlockingAdapter blockingAdapter = BlockingAdapter.this;
                do {
                    obj2 = blockingAdapter.state;
                    z = obj2 instanceof Thread;
                    if (!z && !(obj2 instanceof Continuation) && !Intrinsics.areEqual(obj2, this)) {
                        return;
                    }
                } while (!BlockingAdapter.state$FU.compareAndSet(blockingAdapter, obj2, m10381exceptionOrNullimpl2));
                if (z) {
                    LockSupport.unpark((Thread) obj2);
                } else if ((obj2 instanceof Continuation) && (m10381exceptionOrNullimpl = Result.m10381exceptionOrNullimpl(obj)) != null) {
                    Result.Companion companion = Result.Companion;
                    GeneratedOutlineSupport1.outline186(m10381exceptionOrNullimpl, (Continuation) obj2);
                }
                if (Result.m10384isFailureimpl(obj) && !(Result.m10381exceptionOrNullimpl(obj) instanceof CancellationException) && (parent = BlockingAdapter.this.getParent()) != null) {
                    parent.mo12309cancel();
                }
                disposableHandle = BlockingAdapter.this.disposable;
                if (disposableHandle != null) {
                    disposableHandle.dispose();
                }
            }
        };
        this.state = this;
        boolean z = false;
        this.result = 0;
        Job job2 = this.parent;
        this.disposable = job2 != null ? job2.invokeOnCompletion(new BlockingAdapter$disposable$1(this)) : null;
        ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(new BlockingAdapter$block$1(this, null), 1)).mo12165invoke(this.end);
        if (this.state != this ? true : z) {
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private final void parkingLoop(Thread thread) {
        if (this.state != thread) {
            return;
        }
        CoroutinesEventLoop detectEventLoop = EventLoopExperimentalKt.detectEventLoop();
        while (true) {
            long processEventLoop$kotlinx_coroutines_io = detectEventLoop.processEventLoop$kotlinx_coroutines_io();
            if (this.state != thread) {
                return;
            }
            if (processEventLoop$kotlinx_coroutines_io > 0) {
                LockSupport.parkNanos(processEventLoop$kotlinx_coroutines_io);
            }
        }
    }

    @Nullable
    private final Object rendezvous$$forInline(int i, @NotNull Continuation continuation) {
        Object obj;
        Continuation intercepted;
        Object coroutine_suspended;
        Object coroutine_suspended2;
        this.result = i;
        InlineMarker.mark(0);
        Thread thread = null;
        do {
            obj = this.state;
            if (obj instanceof Thread) {
                thread = (Thread) obj;
                intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
            } else if (!Intrinsics.areEqual(obj, this)) {
                throw new IllegalStateException("Already suspended or in finished state");
            } else {
                intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
            }
        } while (!state$FU.compareAndSet(this, obj, intercepted));
        if (thread != null) {
            LockSupport.unpark(thread);
        }
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (coroutine_suspended == coroutine_suspended2) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return coroutine_suspended;
    }

    private static /* synthetic */ void state$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void finish(int i) {
        this.result = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getLength() {
        return this.length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getOffset() {
        return this.offset;
    }

    @Nullable
    public final Job getParent() {
        return this.parent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public abstract Object loop(@NotNull Continuation<? super Unit> continuation);

    @Nullable
    protected final Object rendezvous(int i, @NotNull Continuation<Object> continuation) {
        Object obj;
        Continuation intercepted;
        Object coroutine_suspended;
        Object coroutine_suspended2;
        this.result = i;
        Thread thread = null;
        do {
            obj = this.state;
            if (obj instanceof Thread) {
                thread = (Thread) obj;
                intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
            } else if (!Intrinsics.areEqual(obj, this)) {
                throw new IllegalStateException("Already suspended or in finished state");
            } else {
                intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
            }
        } while (!state$FU.compareAndSet(this, obj, intercepted));
        if (thread != null) {
            LockSupport.unpark(thread);
        }
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (coroutine_suspended == coroutine_suspended2) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return coroutine_suspended;
    }

    public final void shutdown() {
        DisposableHandle disposableHandle = this.disposable;
        if (disposableHandle != null) {
            disposableHandle.dispose();
        }
        Continuation<Unit> continuation = this.end;
        CancellationException cancellationException = new CancellationException("Stream closed");
        Result.Companion companion = Result.Companion;
        continuation.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(cancellationException)));
    }

    public final int submitAndAwait(@NotNull byte[] buffer, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        this.offset = i;
        this.length = i2;
        return submitAndAwait(buffer);
    }

    public final int submitAndAwait(@NotNull Object jobToken) {
        Object obj;
        Object noWhenBranchMatchedException;
        Intrinsics.checkParameterIsNotNull(jobToken, "jobToken");
        Thread currentThread = Thread.currentThread();
        if (currentThread == null) {
            Intrinsics.throwNpe();
        }
        Continuation continuation = null;
        do {
            obj = this.state;
            if (obj instanceof Continuation) {
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any>");
                }
                continuation = (Continuation) obj;
                noWhenBranchMatchedException = currentThread;
            } else if (obj instanceof Unit) {
                return this.result;
            } else {
                if (!(obj instanceof Throwable)) {
                    if (!(obj instanceof Thread)) {
                        if (!Intrinsics.areEqual(obj, this)) {
                            noWhenBranchMatchedException = new NoWhenBranchMatchedException();
                        } else {
                            throw new IllegalStateException("Not yet started");
                        }
                    } else {
                        throw new IllegalStateException("There is already thread owning adapter");
                    }
                } else {
                    throw ((Throwable) obj);
                }
            }
        } while (!state$FU.compareAndSet(this, obj, noWhenBranchMatchedException));
        if (continuation == null) {
            Intrinsics.throwNpe();
        }
        Result.Companion companion = Result.Companion;
        continuation.resumeWith(Result.m10378constructorimpl(jobToken));
        parkingLoop(currentThread);
        Object obj2 = this.state;
        if (!(obj2 instanceof Throwable)) {
            return this.result;
        }
        throw ((Throwable) obj2);
    }

    public /* synthetic */ BlockingAdapter(Job job, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : job);
    }
}
