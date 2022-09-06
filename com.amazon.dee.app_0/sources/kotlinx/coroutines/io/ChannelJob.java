package kotlinx.coroutines.io;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.dee.app.metrics.MetricsConstants;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.ChildHandle;
import kotlinx.coroutines.ChildJob;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.selects.SelectClause0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Coroutines.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0097\u0001J\t\u0010\u001f\u001a\u00020 H\u0096\u0001J\u0015\u0010\u001f\u001a\u00020\u000f2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"H\u0097\u0001J\u000e\u0010#\u001a\u00020\u000fH\u0097\u0001¢\u0006\u0002\b\u001fJ6\u0010$\u001a\u0002H%\"\u0004\b\u0000\u0010%2\u0006\u0010&\u001a\u0002H%2\u0018\u0010'\u001a\u0014\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u0002H%0(H\u0096\u0001¢\u0006\u0002\u0010*J(\u0010+\u001a\u0004\u0018\u0001H,\"\b\b\u0000\u0010,*\u00020)2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H,0\u0014H\u0096\u0003¢\u0006\u0002\u0010-J\r\u0010.\u001a\u00060/j\u0002`0H\u0097\u0001JF\u00101\u001a\u0002022\b\b\u0002\u00103\u001a\u00020\u000f2\b\b\u0002\u00104\u001a\u00020\u000f2'\u00105\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\"¢\u0006\f\b7\u0012\b\b8\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020 06j\u0002`9H\u0097\u0001J2\u00101\u001a\u0002022'\u00105\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\"¢\u0006\f\b7\u0012\b\b8\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020 06j\u0002`9H\u0096\u0001J\u0011\u0010:\u001a\u00020 H\u0096Aø\u0001\u0000¢\u0006\u0002\u0010;J\u0015\u0010<\u001a\u00020=2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0096\u0001J\u0011\u0010>\u001a\u00020=2\u0006\u0010?\u001a\u00020=H\u0096\u0003J\u0011\u0010>\u001a\u00020\u00032\u0006\u0010@\u001a\u00020\u0003H\u0097\u0003J\t\u0010A\u001a\u00020\u000fH\u0096\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u000fX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010R\u0012\u0010\u0012\u001a\u00020\u000fX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0016\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u0018X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006B"}, d2 = {"Lkotlinx/coroutines/io/ChannelJob;", "Lkotlinx/coroutines/io/ReaderJob;", "Lkotlinx/coroutines/io/WriterJob;", "Lkotlinx/coroutines/Job;", "delegate", "channel", "Lkotlinx/coroutines/io/ByteChannel;", "(Lkotlinx/coroutines/Job;Lkotlinx/coroutines/io/ByteChannel;)V", "getChannel", "()Lkotlinx/coroutines/io/ByteChannel;", "children", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "isActive", "", "()Z", "isCancelled", "isCompleted", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "onJoin", "Lkotlinx/coroutines/selects/SelectClause0;", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "attachChild", "Lkotlinx/coroutines/ChildHandle;", MetricKeys.VALUE_IS_CHILD, "Lkotlinx/coroutines/ChildJob;", DeviceStateModule.CANCEL, "", "cause", "", "cancel0", "fold", "R", Constants.ACCESSORY_PRIVACY_INITIAL_STATUS, "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", MetricsConstants.Method.CACHE_GET, ExifInterface.LONGITUDE_EAST, "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "getCancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "invokeOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "onCancelling", "invokeImmediately", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "join", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "minusKey", "Lkotlin/coroutines/CoroutineContext;", "plus", "context", "other", "start", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ChannelJob implements ReaderJob, WriterJob, Job {
    private final /* synthetic */ Job $$delegate_0;
    @NotNull
    private final ByteChannel channel;

    public ChannelJob(@NotNull Job delegate, @NotNull ByteChannel channel) {
        Intrinsics.checkParameterIsNotNull(delegate, "delegate");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        this.$$delegate_0 = delegate;
        this.channel = channel;
    }

    @Override // kotlinx.coroutines.Job
    @InternalCoroutinesApi
    @NotNull
    public ChildHandle attachChild(@NotNull ChildJob child) {
        Intrinsics.checkParameterIsNotNull(child, "child");
        return this.$$delegate_0.attachChild(child);
    }

    @Override // kotlinx.coroutines.Job
    /* renamed from: cancel */
    public void mo12309cancel() {
        this.$$delegate_0.mo12309cancel();
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Left here for binary compatibility")
    @JvmName(name = DeviceStateModule.CANCEL)
    /* renamed from: cancel  reason: collision with other method in class */
    public /* synthetic */ boolean mo12309cancel() {
        return this.$$delegate_0.mo12309cancel();
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.WARNING, message = "Use CompletableDeferred.completeExceptionally(cause) or Job.cancel() instead", replaceWith = @ReplaceWith(expression = "cancel()", imports = {}))
    @ObsoleteCoroutinesApi
    public boolean cancel(@Nullable Throwable th) {
        return this.$$delegate_0.cancel(th);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <R> R fold(R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        return (R) this.$$delegate_0.fold(r, operation);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    @Nullable
    public <E extends CoroutineContext.Element> E get(@NotNull CoroutineContext.Key<E> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return (E) this.$$delegate_0.get(key);
    }

    @Override // kotlinx.coroutines.Job
    @InternalCoroutinesApi
    @NotNull
    public CancellationException getCancellationException() {
        return this.$$delegate_0.getCancellationException();
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public Sequence<Job> getChildren() {
        return this.$$delegate_0.getChildren();
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    @NotNull
    public CoroutineContext.Key<?> getKey() {
        return this.$$delegate_0.getKey();
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public SelectClause0 getOnJoin() {
        return this.$$delegate_0.getOnJoin();
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public DisposableHandle invokeOnCompletion(@NotNull Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        return this.$$delegate_0.invokeOnCompletion(handler);
    }

    @Override // kotlinx.coroutines.Job
    @InternalCoroutinesApi
    @NotNull
    public DisposableHandle invokeOnCompletion(boolean z, boolean z2, @NotNull Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        return this.$$delegate_0.invokeOnCompletion(z, z2, handler);
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        return this.$$delegate_0.isActive();
    }

    @Override // kotlinx.coroutines.Job
    public boolean isCancelled() {
        return this.$$delegate_0.isCancelled();
    }

    @Override // kotlinx.coroutines.Job
    public boolean isCompleted() {
        return this.$$delegate_0.isCompleted();
    }

    @Override // kotlinx.coroutines.Job
    @Nullable
    public Object join(@NotNull Continuation<? super Unit> continuation) {
        return this.$$delegate_0.join(continuation);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    @NotNull
    public CoroutineContext minusKey(@NotNull CoroutineContext.Key<?> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.$$delegate_0.minusKey(key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return this.$$delegate_0.plus(context);
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    @NotNull
    public Job plus(@NotNull Job other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return this.$$delegate_0.plus(other);
    }

    @Override // kotlinx.coroutines.Job
    public boolean start() {
        return this.$$delegate_0.start();
    }

    @Override // kotlinx.coroutines.io.ReaderJob, kotlinx.coroutines.io.WriterJob
    @NotNull
    /* renamed from: getChannel */
    public ByteChannel mo12310getChannel() {
        return this.channel;
    }
}
