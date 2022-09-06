package kotlinx.coroutines.flow.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Merge.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/internal/ChannelLimitedFlowMerge$collectTo$2$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class ChannelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SendingCollector $collector$inlined;
    final /* synthetic */ Flow $flow;
    final /* synthetic */ ProducerScope $scope$inlined;
    Object L$0;
    int label;
    private CoroutineScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1(Flow flow, Continuation continuation, ProducerScope producerScope, SendingCollector sendingCollector) {
        super(2, continuation);
        this.$flow = flow;
        this.$scope$inlined = producerScope;
        this.$collector$inlined = sendingCollector;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1 channelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1 = new ChannelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1(this.$flow, continuation, this.$scope$inlined, this.$collector$inlined);
        channelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1.p$ = (CoroutineScope) obj;
        return channelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChannelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            Flow flow = this.$flow;
            SendingCollector sendingCollector = this.$collector$inlined;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (flow.collect(sendingCollector, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
