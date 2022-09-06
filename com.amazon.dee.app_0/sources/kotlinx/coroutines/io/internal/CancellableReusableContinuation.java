package kotlinx.coroutines.io.internal;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CancellableReusableContinuation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0004J\u0014\u0010\r\u001a\u00020\u00022\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003J\u001a\u0010\u000f\u001a\u00020\u00102\u0010\u0010\u0011\u001a\f0\u000bR\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0002J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u001e\u0010\u0013\u001a\u00020\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u0018\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0018\u00010\u000bR\b\u0012\u0004\u0012\u00028\u00000\u00000\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lkotlinx/coroutines/io/internal/CancellableReusableContinuation;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlin/coroutines/Continuation;", "()V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "jobCancellationHandler", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/io/internal/CancellableReusableContinuation$JobRelation;", "state", "completeSuspendBlock", "actual", "notParent", "", "relation", RouteParameter.PARENT, "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "resumeWithExceptionContinuationOnly", "job", "Lkotlinx/coroutines/Job;", ADMRegistrationConstants.CALL_EXCEPTION, "", "JobRelation", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CancellableReusableContinuation<T> implements Continuation<T> {
    private static final AtomicReferenceFieldUpdater state$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableReusableContinuation.class, Object.class, "state");
    private static final AtomicReferenceFieldUpdater jobCancellationHandler$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableReusableContinuation.class, Object.class, "jobCancellationHandler");
    private volatile Object state = null;
    private volatile Object jobCancellationHandler = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CancellableReusableContinuation.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007B\r\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u000f\u001a\u00020\u0006J\u0013\u0010\u0010\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0096\u0002R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/io/internal/CancellableReusableContinuation$JobRelation;", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/io/internal/CancellableReusableContinuation;Lkotlinx/coroutines/Job;)V", "handler", "Lkotlinx/coroutines/DisposableHandle;", "getJob", "()Lkotlinx/coroutines/Job;", "dispose", "invoke", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public final class JobRelation implements Function1<Throwable, Unit> {
        private DisposableHandle handler;
        @NotNull
        private final Job job;
        final /* synthetic */ CancellableReusableContinuation this$0;

        public JobRelation(@NotNull CancellableReusableContinuation cancellableReusableContinuation, Job job) {
            Intrinsics.checkParameterIsNotNull(job, "job");
            this.this$0 = cancellableReusableContinuation;
            this.job = job;
            DisposableHandle invokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(this.job, true, false, this, 2, null);
            if (this.job.isActive()) {
                this.handler = invokeOnCompletion$default;
            }
        }

        public final void dispose() {
            DisposableHandle disposableHandle = this.handler;
            if (disposableHandle != null) {
                this.handler = null;
                disposableHandle.dispose();
            }
        }

        @NotNull
        public final Job getJob() {
            return this.job;
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12165invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public void invoke2(@Nullable Throwable th) {
            this.this$0.notParent(this);
            dispose();
            if (th != null) {
                this.this$0.resumeWithExceptionContinuationOnly(this.job, th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notParent(CancellableReusableContinuation<T>.JobRelation jobRelation) {
        jobCancellationHandler$FU.compareAndSet(this, jobRelation, null);
    }

    private final void parent(CoroutineContext coroutineContext) {
        Object obj;
        JobRelation jobRelation;
        Job job = (Job) coroutineContext.get(Job.Key);
        JobRelation jobRelation2 = (JobRelation) this.jobCancellationHandler;
        if ((jobRelation2 != null ? jobRelation2.getJob() : null) == job) {
            return;
        }
        if (job == null) {
            JobRelation jobRelation3 = (JobRelation) jobCancellationHandler$FU.getAndSet(this, null);
            if (jobRelation3 == null) {
                return;
            }
            jobRelation3.dispose();
            return;
        }
        JobRelation jobRelation4 = new JobRelation(this, job);
        do {
            obj = this.jobCancellationHandler;
            jobRelation = (JobRelation) obj;
            if (jobRelation != null && jobRelation.getJob() == job) {
                jobRelation4.dispose();
                return;
            }
        } while (!jobCancellationHandler$FU.compareAndSet(this, obj, jobRelation4));
        if (jobRelation == null) {
            return;
        }
        jobRelation.dispose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeWithExceptionContinuationOnly(Job job, Throwable th) {
        Object obj;
        do {
            obj = this.state;
            if (!(obj instanceof Continuation) || ((Job) ((Continuation) obj).getContext().get(Job.Key)) != job) {
                return;
            }
        } while (!state$FU.compareAndSet(this, obj, null));
        if (obj != null) {
            Result.Companion companion = Result.Companion;
            GeneratedOutlineSupport1.outline186(th, (Continuation) obj);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.Continuation<T>");
    }

    @NotNull
    public final Object completeSuspendBlock(@NotNull Continuation<? super T> actual) {
        Object coroutine_suspended;
        Intrinsics.checkParameterIsNotNull(actual, "actual");
        while (true) {
            Object obj = this.state;
            if (obj == null) {
                if (state$FU.compareAndSet(this, null, actual)) {
                    parent(actual.getContext());
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    return coroutine_suspended;
                }
            } else if (state$FU.compareAndSet(this, obj, null)) {
                if (obj instanceof Throwable) {
                    throw ((Throwable) obj);
                }
                return obj;
            }
        }
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        CoroutineContext context;
        Object obj = this.state;
        if (!(obj instanceof Continuation)) {
            obj = null;
        }
        Continuation continuation = (Continuation) obj;
        return (continuation == null || (context = continuation.getContext()) == null) ? EmptyCoroutineContext.INSTANCE : context;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object obj) {
        Object obj2;
        Object obj3;
        do {
            obj2 = this.state;
            if (obj2 == null) {
                obj3 = Result.m10381exceptionOrNullimpl(obj);
                if (obj3 == null) {
                    ResultKt.throwOnFailure(obj);
                    obj3 = obj;
                }
            } else if (!(obj2 instanceof Continuation)) {
                return;
            } else {
                obj3 = null;
            }
        } while (!state$FU.compareAndSet(this, obj2, obj3));
        if (obj2 instanceof Continuation) {
            ((Continuation) obj2).resumeWith(obj);
        }
    }
}
