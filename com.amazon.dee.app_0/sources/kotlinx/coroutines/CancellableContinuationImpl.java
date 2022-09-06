package kotlinx.coroutines;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CancellableContinuationImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0011\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00028\u00000z2\b\u0012\u0004\u0012\u00028\u00000{2\u00060bj\u0002`cB\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0012\u0010\u0011J!\u0010\u0016\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\rH\u0010¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001a\u0010\fJ\u000f\u0010\u001d\u001a\u00020\nH\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\nH\u0002¢\u0006\u0004\b\u001e\u0010\u001cJ\u0017\u0010 \u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0004H\u0002¢\u0006\u0004\b \u0010!J\u0017\u0010$\u001a\u00020\r2\u0006\u0010#\u001a\u00020\"H\u0016¢\u0006\u0004\b$\u0010%J\u0011\u0010&\u001a\u0004\u0018\u00010\bH\u0001¢\u0006\u0004\b&\u0010'J\u0017\u0010*\u001a\n\u0018\u00010(j\u0004\u0018\u0001`)H\u0016¢\u0006\u0004\b*\u0010+J\u001f\u0010.\u001a\u00028\u0001\"\u0004\b\u0001\u0010\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\bH\u0010¢\u0006\u0004\b,\u0010-J\u000f\u0010/\u001a\u00020\nH\u0016¢\u0006\u0004\b/\u0010\u001cJ\u001e\u00102\u001a\u00020\n2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\n00H\u0082\b¢\u0006\u0004\b2\u00103J8\u00109\u001a\u00020\n2'\u00108\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\n04j\u0002`7H\u0016¢\u0006\u0004\b9\u0010:J\u000f\u0010;\u001a\u00020\u000fH\u0002¢\u0006\u0004\b;\u0010\u0018J8\u0010=\u001a\u00020<2'\u00108\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\n04j\u0002`7H\u0002¢\u0006\u0004\b=\u0010>JB\u0010?\u001a\u00020\n2'\u00108\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\n04j\u0002`72\b\u0010\u0013\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b?\u0010@J\u000f\u0010B\u001a\u00020AH\u0014¢\u0006\u0004\bB\u0010CJ\u0017\u0010F\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\rH\u0000¢\u0006\u0004\bD\u0010EJ\u000f\u0010G\u001a\u00020\u000fH\u0001¢\u0006\u0004\bG\u0010\u0018J:\u0010J\u001a\u00020\n2\u0006\u0010H\u001a\u00028\u00002!\u0010I\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\n04H\u0016¢\u0006\u0004\bJ\u0010KJ#\u0010M\u001a\u0004\u0018\u00010L2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\bM\u0010NJ \u0010Q\u001a\u00020\n2\f\u0010P\u001a\b\u0012\u0004\u0012\u00028\u00000OH\u0016ø\u0001\u0000¢\u0006\u0004\bQ\u0010\fJ\u000f\u0010R\u001a\u00020\nH\u0002¢\u0006\u0004\bR\u0010\u001cJ\u0011\u0010T\u001a\u0004\u0018\u00010\bH\u0010¢\u0006\u0004\bS\u0010'J\u000f\u0010U\u001a\u00020AH\u0016¢\u0006\u0004\bU\u0010CJ\u000f\u0010V\u001a\u00020\u000fH\u0002¢\u0006\u0004\bV\u0010\u0018J#\u0010V\u001a\u0004\u0018\u00010\b2\u0006\u0010H\u001a\u00028\u00002\b\u0010W\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\bV\u0010XJ\u0019\u0010Z\u001a\u0004\u0018\u00010\b2\u0006\u0010Y\u001a\u00020\rH\u0016¢\u0006\u0004\bZ\u0010[J\u000f\u0010\\\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\\\u0010\u0018J\u001b\u0010^\u001a\u00020\n*\u00020]2\u0006\u0010H\u001a\u00028\u0000H\u0016¢\u0006\u0004\b^\u0010_J\u001b\u0010`\u001a\u00020\n*\u00020]2\u0006\u0010Y\u001a\u00020\rH\u0016¢\u0006\u0004\b`\u0010aR\u001e\u0010f\u001a\n\u0018\u00010bj\u0004\u0018\u0001`c8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bd\u0010eR\u001c\u0010h\u001a\u00020g8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\bh\u0010i\u001a\u0004\bj\u0010kR\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010l\u001a\u0004\bm\u0010nR\u0016\u0010o\u001a\u00020\u000f8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bo\u0010\u0018R\u0016\u0010p\u001a\u00020\u000f8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bp\u0010\u0018R\u0016\u0010q\u001a\u00020\u000f8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bq\u0010\u0018R(\u0010w\u001a\u0004\u0018\u00010r2\b\u0010H\u001a\u0004\u0018\u00010r8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\bs\u0010t\"\u0004\bu\u0010vR\u0018\u0010\u0013\u001a\u0004\u0018\u00010\b8@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\bx\u0010'\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006y"}, d2 = {"Lkotlinx/coroutines/CancellableContinuationImpl;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/coroutines/Continuation;", "delegate", "", "resumeMode", "<init>", "(Lkotlin/coroutines/Continuation;I)V", "", "proposedUpdate", "", "alreadyResumedError", "(Ljava/lang/Object;)V", "", "cause", "", DeviceStateModule.CANCEL, "(Ljava/lang/Throwable;)Z", "cancelLater", "state", "cancelResult$kotlinx_coroutines_core", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "cancelResult", "checkCompleted", "()Z", "token", "completeResume", "detachChild$kotlinx_coroutines_core", "()V", "detachChild", "detachChildIfNonResuable", "mode", "dispatchResume", "(I)V", "Lkotlinx/coroutines/Job;", RouteParameter.PARENT, "getContinuationCancellationCause", "(Lkotlinx/coroutines/Job;)Ljava/lang/Throwable;", "getResult", "()Ljava/lang/Object;", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "getSuccessfulResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Object;", "getSuccessfulResult", "initCancellability", "Lkotlin/Function0;", "block", "invokeHandlerSafely", "(Lkotlin/jvm/functions/Function0;)V", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "handler", "invokeOnCancellation", "(Lkotlin/jvm/functions/Function1;)V", "isReusable", "Lkotlinx/coroutines/CancelHandler;", "makeHandler", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/CancelHandler;", "multipleHandlersError", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)V", "", "nameString", "()Ljava/lang/String;", "parentCancelled$kotlinx_coroutines_core", "(Ljava/lang/Throwable;)V", "parentCancelled", "resetState", "value", "onCancellation", "resume", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "Lkotlinx/coroutines/CancelledContinuation;", "resumeImpl", "(Ljava/lang/Object;I)Lkotlinx/coroutines/CancelledContinuation;", "Lkotlin/Result;", "result", "resumeWith", "setupCancellation", "takeState$kotlinx_coroutines_core", "takeState", "toString", "tryResume", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", ADMRegistrationConstants.CALL_EXCEPTION, "tryResumeWithException", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "trySuspend", "Lkotlinx/coroutines/CoroutineDispatcher;", "resumeUndispatched", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Throwable;)V", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "callerFrame", "Lkotlin/coroutines/CoroutineContext;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "Lkotlin/coroutines/Continuation;", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "isActive", "isCancelled", "isCompleted", "Lkotlinx/coroutines/DisposableHandle;", "getParentHandle", "()Lkotlinx/coroutines/DisposableHandle;", "setParentHandle", "(Lkotlinx/coroutines/DisposableHandle;)V", "parentHandle", "getState$kotlinx_coroutines_core", "kotlinx-coroutines-core", "Lkotlinx/coroutines/DispatchedTask;", "Lkotlinx/coroutines/CancellableContinuation;"}, k = 1, mv = {1, 1, 16})
@PublishedApi
/* loaded from: classes4.dex */
public class CancellableContinuationImpl<T> extends DispatchedTask<T> implements CancellableContinuation<T>, CoroutineStackFrame {
    private static final AtomicIntegerFieldUpdater _decision$FU = AtomicIntegerFieldUpdater.newUpdater(CancellableContinuationImpl.class, "_decision");
    private static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableContinuationImpl.class, Object.class, "_state");
    private volatile int _decision;
    private volatile Object _parentHandle;
    private volatile Object _state;
    @NotNull
    private final CoroutineContext context;
    @NotNull
    private final Continuation<T> delegate;

    /* JADX WARN: Multi-variable type inference failed */
    public CancellableContinuationImpl(@NotNull Continuation<? super T> continuation, int i) {
        super(i);
        this.delegate = continuation;
        this.context = this.delegate.getContext();
        this._decision = 0;
        this._state = Active.INSTANCE;
        this._parentHandle = null;
    }

    private final void alreadyResumedError(Object obj) {
        throw new IllegalStateException(GeneratedOutlineSupport1.outline70("Already resumed, but proposed with update ", obj).toString());
    }

    private final boolean cancelLater(Throwable th) {
        if (this.resumeMode != 0) {
            return false;
        }
        Continuation<T> continuation = this.delegate;
        if (!(continuation instanceof DispatchedContinuation)) {
            continuation = null;
        }
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
        if (dispatchedContinuation == null) {
            return false;
        }
        return dispatchedContinuation.postponeCancellation(th);
    }

    private final boolean checkCompleted() {
        Throwable checkPostponedCancellation;
        boolean isCompleted = isCompleted();
        if (this.resumeMode != 0) {
            return isCompleted;
        }
        Continuation<T> continuation = this.delegate;
        if (!(continuation instanceof DispatchedContinuation)) {
            continuation = null;
        }
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
        if (dispatchedContinuation == null || (checkPostponedCancellation = dispatchedContinuation.checkPostponedCancellation(this)) == null) {
            return isCompleted;
        }
        if (!isCompleted) {
            cancel(checkPostponedCancellation);
        }
        return true;
    }

    private final void detachChildIfNonResuable() {
        if (!isReusable()) {
            detachChild$kotlinx_coroutines_core();
        }
    }

    private final void dispatchResume(int i) {
        if (tryResume()) {
            return;
        }
        DispatchedTaskKt.dispatch(this, i);
    }

    private final DisposableHandle getParentHandle() {
        return (DisposableHandle) this._parentHandle;
    }

    private final void invokeHandlerSafely(Function0<Unit> function0) {
        try {
            function0.mo12560invoke();
        } catch (Throwable th) {
            CoroutineContext context = getContext();
            CoroutineExceptionHandlerKt.handleCoroutineException(context, new CompletionHandlerException("Exception in cancellation handler for " + this, th));
        }
    }

    private final boolean isReusable() {
        Continuation<T> continuation = this.delegate;
        return (continuation instanceof DispatchedContinuation) && ((DispatchedContinuation) continuation).isReusable(this);
    }

    private final CancelHandler makeHandler(Function1<? super Throwable, Unit> function1) {
        return function1 instanceof CancelHandler ? (CancelHandler) function1 : new InvokeOnCancel(function1);
    }

    private final void multipleHandlersError(Function1<? super Throwable, Unit> function1, Object obj) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + function1 + ", already has " + obj).toString());
    }

    private final CancelledContinuation resumeImpl(Object obj, int i) {
        while (true) {
            Object obj2 = this._state;
            if (obj2 instanceof NotCompleted) {
                if (_state$FU.compareAndSet(this, obj2, obj)) {
                    detachChildIfNonResuable();
                    dispatchResume(i);
                    return null;
                }
            } else {
                if (obj2 instanceof CancelledContinuation) {
                    CancelledContinuation cancelledContinuation = (CancelledContinuation) obj2;
                    if (cancelledContinuation.makeResumed()) {
                        return cancelledContinuation;
                    }
                }
                alreadyResumedError(obj);
            }
        }
    }

    private final void setParentHandle(DisposableHandle disposableHandle) {
        this._parentHandle = disposableHandle;
    }

    private final void setupCancellation() {
        Job job;
        if (!checkCompleted() && getParentHandle() == null && (job = (Job) this.delegate.getContext().get(Job.Key)) != null) {
            job.start();
            DisposableHandle invokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new ChildContinuation(job, this), 2, null);
            setParentHandle(invokeOnCompletion$default);
            if (!isCompleted() || isReusable()) {
                return;
            }
            invokeOnCompletion$default.dispose();
            setParentHandle(NonDisposableHandle.INSTANCE);
        }
    }

    private final boolean tryResume() {
        do {
            int i = this._decision;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("Already resumed".toString());
                }
                return false;
            }
        } while (!_decision$FU.compareAndSet(this, 0, 2));
        return true;
    }

    private final boolean trySuspend() {
        do {
            int i = this._decision;
            if (i != 0) {
                if (i != 2) {
                    throw new IllegalStateException("Already suspended".toString());
                }
                return false;
            }
        } while (!_decision$FU.compareAndSet(this, 0, 1));
        return true;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean cancel(@Nullable Throwable th) {
        Object obj;
        boolean z;
        do {
            obj = this._state;
            if (!(obj instanceof NotCompleted)) {
                return false;
            }
            z = obj instanceof CancelHandler;
        } while (!_state$FU.compareAndSet(this, obj, new CancelledContinuation(this, th, z)));
        if (z) {
            try {
                ((CancelHandler) obj).invoke(th);
            } catch (Throwable th2) {
                CoroutineContext context = getContext();
                CoroutineExceptionHandlerKt.handleCoroutineException(context, new CompletionHandlerException("Exception in cancellation handler for " + this, th2));
            }
        }
        detachChildIfNonResuable();
        dispatchResume(0);
        return true;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void cancelResult$kotlinx_coroutines_core(@Nullable Object obj, @NotNull Throwable th) {
        if (obj instanceof CompletedWithCancellation) {
            try {
                ((CompletedWithCancellation) obj).onCancellation.mo12165invoke(th);
            } catch (Throwable th2) {
                CoroutineContext context = getContext();
                CoroutineExceptionHandlerKt.handleCoroutineException(context, new CompletionHandlerException("Exception in cancellation handler for " + this, th2));
            }
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void completeResume(@NotNull Object obj) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(obj == CancellableContinuationImplKt.RESUME_TOKEN)) {
                throw new AssertionError();
            }
        }
        dispatchResume(this.resumeMode);
    }

    public final void detachChild$kotlinx_coroutines_core() {
        DisposableHandle parentHandle = getParentHandle();
        if (parentHandle != null) {
            parentHandle.dispose();
        }
        setParentHandle(NonDisposableHandle.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.delegate;
        if (!(continuation instanceof CoroutineStackFrame)) {
            continuation = null;
        }
        return (CoroutineStackFrame) continuation;
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return this.context;
    }

    @NotNull
    public Throwable getContinuationCancellationCause(@NotNull Job job) {
        return job.getCancellationException();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    @NotNull
    public final Continuation<T> getDelegate$kotlinx_coroutines_core() {
        return this.delegate;
    }

    @PublishedApi
    @Nullable
    public final Object getResult() {
        Job job;
        Object coroutine_suspended;
        setupCancellation();
        if (trySuspend()) {
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            return coroutine_suspended;
        }
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            Throwable th = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
            if (!DebugKt.getRECOVER_STACK_TRACES()) {
                throw th;
            }
            throw StackTraceRecoveryKt.access$recoverFromStackFrame(th, this);
        } else if (this.resumeMode == 1 && (job = (Job) getContext().get(Job.Key)) != null && !job.isActive()) {
            CancellationException cancellationException = job.getCancellationException();
            cancelResult$kotlinx_coroutines_core(state$kotlinx_coroutines_core, cancellationException);
            if (!DebugKt.getRECOVER_STACK_TRACES()) {
                throw cancellationException;
            }
            throw StackTraceRecoveryKt.access$recoverFromStackFrame(cancellationException, this);
        } else {
            return getSuccessfulResult$kotlinx_coroutines_core(state$kotlinx_coroutines_core);
        }
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @Nullable
    public final Object getState$kotlinx_coroutines_core() {
        return this._state;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.DispatchedTask
    public <T> T getSuccessfulResult$kotlinx_coroutines_core(@Nullable Object obj) {
        return obj instanceof CompletedIdempotentResult ? (T) ((CompletedIdempotentResult) obj).result : obj instanceof CompletedWithCancellation ? (T) ((CompletedWithCancellation) obj).result : obj;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public /* synthetic */ void initCancellability() {
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void invokeOnCancellation(@NotNull Function1<? super Throwable, Unit> function1) {
        Throwable th = null;
        CancelHandler cancelHandler = null;
        while (true) {
            Object obj = this._state;
            if (obj instanceof Active) {
                if (cancelHandler == null) {
                    cancelHandler = makeHandler(function1);
                }
                if (_state$FU.compareAndSet(this, obj, cancelHandler)) {
                    return;
                }
            } else if (!(obj instanceof CancelHandler)) {
                if (!(obj instanceof CancelledContinuation)) {
                    return;
                }
                if (!((CancelledContinuation) obj).makeHandled()) {
                    multipleHandlersError(function1, obj);
                }
                try {
                    if (!(obj instanceof CompletedExceptionally)) {
                        obj = null;
                    }
                    CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
                    if (completedExceptionally != null) {
                        th = completedExceptionally.cause;
                    }
                    function1.mo12165invoke(th);
                    return;
                } catch (Throwable th2) {
                    CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in cancellation handler for " + this, th2));
                    return;
                }
            } else {
                multipleHandlersError(function1, obj);
            }
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isActive() {
        return getState$kotlinx_coroutines_core() instanceof NotCompleted;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isCancelled() {
        return getState$kotlinx_coroutines_core() instanceof CancelledContinuation;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof NotCompleted);
    }

    @NotNull
    protected String nameString() {
        return "CancellableContinuation";
    }

    public final void parentCancelled$kotlinx_coroutines_core(@NotNull Throwable th) {
        if (cancelLater(th)) {
            return;
        }
        cancel(th);
        detachChildIfNonResuable();
    }

    @JvmName(name = "resetState")
    public final boolean resetState() {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getParentHandle() != NonDisposableHandle.INSTANCE)) {
                throw new AssertionError();
            }
        }
        Object obj = this._state;
        if (!DebugKt.getASSERTIONS_ENABLED() || (!(obj instanceof NotCompleted))) {
            if (obj instanceof CompletedIdempotentResult) {
                detachChild$kotlinx_coroutines_core();
                return false;
            }
            this._decision = 0;
            this._state = Active.INSTANCE;
            return true;
        }
        throw new AssertionError();
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resume(T t, @NotNull Function1<? super Throwable, Unit> function1) {
        CancelledContinuation resumeImpl = resumeImpl(new CompletedWithCancellation(t, function1), this.resumeMode);
        if (resumeImpl != null) {
            try {
                function1.mo12165invoke(resumeImpl.cause);
            } catch (Throwable th) {
                CoroutineContext context = getContext();
                CoroutineExceptionHandlerKt.handleCoroutineException(context, new CompletionHandlerException("Exception in cancellation handler for " + this, th));
            }
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resumeUndispatched(@NotNull CoroutineDispatcher coroutineDispatcher, T t) {
        Continuation<T> continuation = this.delegate;
        CoroutineDispatcher coroutineDispatcher2 = null;
        if (!(continuation instanceof DispatchedContinuation)) {
            continuation = null;
        }
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
        if (dispatchedContinuation != null) {
            coroutineDispatcher2 = dispatchedContinuation.dispatcher;
        }
        resumeImpl(t, coroutineDispatcher2 == coroutineDispatcher ? 2 : this.resumeMode);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resumeUndispatchedWithException(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull Throwable th) {
        Continuation<T> continuation = this.delegate;
        CoroutineDispatcher coroutineDispatcher2 = null;
        if (!(continuation instanceof DispatchedContinuation)) {
            continuation = null;
        }
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
        int i = 2;
        CompletedExceptionally completedExceptionally = new CompletedExceptionally(th, false, 2, null);
        if (dispatchedContinuation != null) {
            coroutineDispatcher2 = dispatchedContinuation.dispatcher;
        }
        if (coroutineDispatcher2 != coroutineDispatcher) {
            i = this.resumeMode;
        }
        resumeImpl(completedExceptionally, i);
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object obj) {
        resumeImpl(CompletedExceptionallyKt.toState(obj, this), this.resumeMode);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    @Nullable
    public Object takeState$kotlinx_coroutines_core() {
        return getState$kotlinx_coroutines_core();
    }

    @NotNull
    public String toString() {
        return nameString() + '(' + DebugStringsKt.toDebugString(this.delegate) + "){" + getState$kotlinx_coroutines_core() + "}@" + DebugStringsKt.getHexAddress(this);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    @Nullable
    public Object tryResumeWithException(@NotNull Throwable th) {
        Object obj;
        do {
            obj = this._state;
            if (!(obj instanceof NotCompleted)) {
                return null;
            }
        } while (!_state$FU.compareAndSet(this, obj, new CompletedExceptionally(th, false, 2, null)));
        detachChildIfNonResuable();
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    @Nullable
    public Object tryResume(T t, @Nullable Object obj) {
        Object obj2;
        do {
            obj2 = this._state;
            if (!(obj2 instanceof NotCompleted)) {
                if (!(obj2 instanceof CompletedIdempotentResult)) {
                    return null;
                }
                CompletedIdempotentResult completedIdempotentResult = (CompletedIdempotentResult) obj2;
                if (completedIdempotentResult.idempotentResume != obj) {
                    return null;
                }
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(completedIdempotentResult.result == t)) {
                        throw new AssertionError();
                    }
                }
                return CancellableContinuationImplKt.RESUME_TOKEN;
            }
        } while (!_state$FU.compareAndSet(this, obj2, obj == null ? t : new CompletedIdempotentResult(obj, t)));
        detachChildIfNonResuable();
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }
}
