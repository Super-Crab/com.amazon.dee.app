package io.ktor.http.content;

import com.amazon.alexa.location.utils.MetricsUtil;
import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.ByteReadChannelJVMKt;
import kotlinx.coroutines.io.ByteWriteChannel;
import kotlinx.coroutines.io.WriterScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: OutgoingContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/WriterScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.http.content.OutgoingContent$ReadChannelContent$readFrom$1", f = "OutgoingContent.kt", i = {0, 1, 1}, l = {76, 78}, m = "invokeSuspend", n = {"source", "source", MetricsUtil.LegacyMetricTypes.LIMIT}, s = {"L$1", "L$0", "J$0"})
/* loaded from: classes3.dex */
public final class OutgoingContent$ReadChannelContent$readFrom$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LongRange $range;
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    private WriterScope p$;
    final /* synthetic */ OutgoingContent.ReadChannelContent this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OutgoingContent$ReadChannelContent$readFrom$1(OutgoingContent.ReadChannelContent readChannelContent, LongRange longRange, Continuation continuation) {
        super(2, continuation);
        this.this$0 = readChannelContent;
        this.$range = longRange;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        OutgoingContent$ReadChannelContent$readFrom$1 outgoingContent$ReadChannelContent$readFrom$1 = new OutgoingContent$ReadChannelContent$readFrom$1(this.this$0, this.$range, completion);
        outgoingContent$ReadChannelContent$readFrom$1.p$ = (WriterScope) obj;
        return outgoingContent$ReadChannelContent$readFrom$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((OutgoingContent$ReadChannelContent$readFrom$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        WriterScope writerScope;
        ByteReadChannel readFrom;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ByteReadChannel byteReadChannel = (ByteReadChannel) this.L$0;
                if (obj instanceof Result.Failure) {
                    throw ((Result.Failure) obj).exception;
                }
                return Unit.INSTANCE;
            }
            readFrom = (ByteReadChannel) this.L$1;
            WriterScope writerScope2 = (WriterScope) this.L$0;
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
            writerScope = writerScope2;
        } else if (!(obj instanceof Result.Failure)) {
            writerScope = this.p$;
            readFrom = this.this$0.readFrom();
            long longValue = this.$range.mo11373getStart().longValue();
            this.L$0 = writerScope;
            this.L$1 = readFrom;
            this.label = 1;
            if (readFrom.discard(longValue, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            throw ((Result.Failure) obj).exception;
        }
        long longValue2 = (this.$range.mo11372getEndInclusive().longValue() - this.$range.mo11373getStart().longValue()) + 1;
        ByteWriteChannel mo12311getChannel = writerScope.mo12311getChannel();
        this.L$0 = readFrom;
        this.J$0 = longValue2;
        this.label = 2;
        if (ByteReadChannelJVMKt.copyTo(readFrom, mo12311getChannel, longValue2, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
