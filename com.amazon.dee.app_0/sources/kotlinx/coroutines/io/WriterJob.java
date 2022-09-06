package kotlinx.coroutines.io;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Coroutines.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/io/WriterJob;", "Lkotlinx/coroutines/Job;", "channel", "Lkotlinx/coroutines/io/ByteReadChannel;", "getChannel", "()Lkotlinx/coroutines/io/ByteReadChannel;", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface WriterJob extends Job {

    /* compiled from: Coroutines.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static <R> R fold(WriterJob writerJob, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> operation) {
            Intrinsics.checkParameterIsNotNull(operation, "operation");
            return (R) Job.DefaultImpls.fold(writerJob, r, operation);
        }

        @Nullable
        public static <E extends CoroutineContext.Element> E get(WriterJob writerJob, @NotNull CoroutineContext.Key<E> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return (E) Job.DefaultImpls.get(writerJob, key);
        }

        @NotNull
        public static CoroutineContext minusKey(WriterJob writerJob, @NotNull CoroutineContext.Key<?> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return Job.DefaultImpls.minusKey(writerJob, key);
        }

        @NotNull
        public static CoroutineContext plus(WriterJob writerJob, @NotNull CoroutineContext context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return Job.DefaultImpls.plus(writerJob, context);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        @NotNull
        public static Job plus(WriterJob writerJob, @NotNull Job other) {
            Intrinsics.checkParameterIsNotNull(other, "other");
            return Job.DefaultImpls.plus((Job) writerJob, other);
        }
    }

    @NotNull
    /* renamed from: getChannel */
    ByteReadChannel mo12310getChannel();
}
