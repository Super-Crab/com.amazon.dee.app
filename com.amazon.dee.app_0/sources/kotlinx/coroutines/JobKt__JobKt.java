package kotlinx.coroutines;

import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Job.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u001a\u0019\u0010\u0004\u001a\u00020\u00052\u000e\b\u0004\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0087\b\u001a\u0012\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u001a\u0019\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007¢\u0006\u0002\b\t\u001a\f\u0010\u000e\u001a\u00020\b*\u00020\u0002H\u0007\u001a\u0018\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007\u001a\u001c\u0010\u000e\u001a\u00020\b*\u00020\u00022\u0010\b\u0002\u0010\u000f\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012\u001a\u001e\u0010\u000e\u001a\u00020\b*\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u001a\u0015\u0010\u0015\u001a\u00020\b*\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a\f\u0010\u0017\u001a\u00020\b*\u00020\u0002H\u0007\u001a\u0018\u0010\u0017\u001a\u00020\b*\u00020\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007\u001a\u001c\u0010\u0017\u001a\u00020\b*\u00020\u00022\u0010\b\u0002\u0010\u000f\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012\u001a\f\u0010\u0017\u001a\u00020\b*\u00020\fH\u0007\u001a\u0018\u0010\u0017\u001a\u00020\b*\u00020\f2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007\u001a\u001c\u0010\u0017\u001a\u00020\b*\u00020\f2\u0010\b\u0002\u0010\u000f\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012\u001a\u0014\u0010\u0018\u001a\u00020\u0005*\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0005H\u0000\u001a\n\u0010\u001a\u001a\u00020\b*\u00020\u0002\u001a\n\u0010\u001a\u001a\u00020\b*\u00020\f\u001a\u001b\u0010\u001b\u001a\u00020\u0010*\u0004\u0018\u00010\u00102\u0006\u0010\u001c\u001a\u00020\fH\u0002¢\u0006\u0002\b\u001d\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"isActive", "", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)Z", "DisposableHandle", "Lkotlinx/coroutines/DisposableHandle;", "block", "Lkotlin/Function0;", "", "Job", "Lkotlinx/coroutines/CompletableJob;", RouteParameter.PARENT, "Lkotlinx/coroutines/Job;", "Job0", DeviceStateModule.CANCEL, "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "message", "", "cancelAndJoin", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelChildren", "disposeOnCompletion", "handle", "ensureActive", "orCancellation", "job", "orCancellation$JobKt__JobKt", "kotlinx-coroutines-core"}, k = 5, mv = {1, 1, 16}, xs = "kotlinx/coroutines/JobKt")
/* loaded from: classes4.dex */
public final /* synthetic */ class JobKt__JobKt {
    @InternalCoroutinesApi
    @NotNull
    public static final DisposableHandle DisposableHandle(@NotNull final Function0<Unit> function0) {
        return new DisposableHandle() { // from class: kotlinx.coroutines.JobKt__JobKt$DisposableHandle$1
            @Override // kotlinx.coroutines.DisposableHandle
            public void dispose() {
                Function0.this.mo12560invoke();
            }
        };
    }

    @NotNull
    public static final CompletableJob Job(@Nullable Job job) {
        return new JobImpl(job);
    }

    public static /* synthetic */ CompletableJob Job$default(Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = null;
        }
        return JobKt.Job(job);
    }

    public static final void cancel(@NotNull CoroutineContext coroutineContext, @Nullable CancellationException cancellationException) {
        Job job = (Job) coroutineContext.get(Job.Key);
        if (job != null) {
            job.cancel(cancellationException);
        }
    }

    public static /* synthetic */ void cancel$default(CoroutineContext coroutineContext, CancellationException cancellationException, int i, Object obj) {
        if ((i & 1) != 0) {
            cancellationException = null;
        }
        JobKt.cancel(coroutineContext, cancellationException);
    }

    @Nullable
    public static final Object cancelAndJoin(@NotNull Job job, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        Object join = job.join(continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return join == coroutine_suspended ? join : Unit.INSTANCE;
    }

    public static final void cancelChildren(@NotNull Job job, @Nullable CancellationException cancellationException) {
        for (Job job2 : job.getChildren()) {
            job2.cancel(cancellationException);
        }
    }

    public static /* synthetic */ void cancelChildren$default(Job job, CancellationException cancellationException, int i, Object obj) {
        if ((i & 1) != 0) {
            cancellationException = null;
        }
        JobKt.cancelChildren(job, cancellationException);
    }

    @NotNull
    public static final DisposableHandle disposeOnCompletion(@NotNull Job job, @NotNull DisposableHandle disposableHandle) {
        return job.invokeOnCompletion(new DisposeOnCompletion(job, disposableHandle));
    }

    public static final void ensureActive(@NotNull Job job) {
        if (job.isActive()) {
            return;
        }
        throw job.getCancellationException();
    }

    public static final boolean isActive(@NotNull CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.get(Job.Key);
        return job != null && job.isActive();
    }

    private static final Throwable orCancellation$JobKt__JobKt(@Nullable Throwable th, Job job) {
        return th != null ? th : new JobCancellationException("Job was cancelled", null, job);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    @JvmName(name = "Job")
    @NotNull
    /* renamed from: Job */
    public static final /* synthetic */ Job m12239Job(@Nullable Job job) {
        return JobKt.Job(job);
    }

    /* renamed from: Job$default */
    public static /* synthetic */ Job m12240Job$default(Job job, int i, Object obj) {
        Job m12239Job;
        if ((i & 1) != 0) {
            job = null;
        }
        m12239Job = m12239Job(job);
        return m12239Job;
    }

    public static /* synthetic */ void cancel$default(Job job, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        JobKt.cancel(job, str, th);
    }

    public static /* synthetic */ void cancelChildren$default(Job job, Throwable th, int i, Object obj) {
        if ((i & 1) != 0) {
            th = null;
        }
        cancelChildren(job, th);
    }

    public static final void ensureActive(@NotNull CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.get(Job.Key);
        if (job != null) {
            JobKt.ensureActive(job);
            return;
        }
        throw new IllegalStateException(("Context cannot be checked for liveness because it does not have a job: " + coroutineContext).toString());
    }

    public static final void cancel(@NotNull Job job, @NotNull String str, @Nullable Throwable th) {
        job.cancel(ExceptionsKt.CancellationException(str, th));
    }

    public static /* synthetic */ boolean cancel$default(CoroutineContext coroutineContext, Throwable th, int i, Object obj) {
        boolean cancel;
        if ((i & 1) != 0) {
            th = null;
        }
        cancel = cancel(coroutineContext, th);
        return cancel;
    }

    public static /* synthetic */ void cancelChildren$default(CoroutineContext coroutineContext, CancellationException cancellationException, int i, Object obj) {
        if ((i & 1) != 0) {
            cancellationException = null;
        }
        JobKt.cancelChildren(coroutineContext, cancellationException);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final /* synthetic */ boolean cancel(@NotNull CoroutineContext coroutineContext, @Nullable Throwable th) {
        CoroutineContext.Element element = coroutineContext.get(Job.Key);
        if (!(element instanceof JobSupport)) {
            element = null;
        }
        JobSupport jobSupport = (JobSupport) element;
        if (jobSupport != null) {
            jobSupport.cancelInternal(orCancellation$JobKt__JobKt(th, jobSupport));
            return true;
        }
        return false;
    }

    public static /* synthetic */ void cancelChildren$default(CoroutineContext coroutineContext, Throwable th, int i, Object obj) {
        if ((i & 1) != 0) {
            th = null;
        }
        cancelChildren(coroutineContext, th);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final /* synthetic */ void cancelChildren(@NotNull Job job, @Nullable Throwable th) {
        for (Job job2 : job.getChildren()) {
            if (!(job2 instanceof JobSupport)) {
                job2 = null;
            }
            JobSupport jobSupport = (JobSupport) job2;
            if (jobSupport != null) {
                jobSupport.cancelInternal(orCancellation$JobKt__JobKt(th, job));
            }
        }
    }

    public static final void cancelChildren(@NotNull CoroutineContext coroutineContext, @Nullable CancellationException cancellationException) {
        Sequence<Job> children;
        Job job = (Job) coroutineContext.get(Job.Key);
        if (job == null || (children = job.getChildren()) == null) {
            return;
        }
        for (Job job2 : children) {
            job2.cancel(cancellationException);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final /* synthetic */ void cancelChildren(@NotNull CoroutineContext coroutineContext, @Nullable Throwable th) {
        Job job = (Job) coroutineContext.get(Job.Key);
        if (job != null) {
            for (Job job2 : job.getChildren()) {
                if (!(job2 instanceof JobSupport)) {
                    job2 = null;
                }
                JobSupport jobSupport = (JobSupport) job2;
                if (jobSupport != null) {
                    jobSupport.cancelInternal(orCancellation$JobKt__JobKt(th, job));
                }
            }
        }
    }
}
