package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.internal.ChannelFlow;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Context.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¢\u0006\u0002\b\u0004\u001a(\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u001a\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u0006H\u0007\u001a&\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a[\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0006\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\r*\b\u0012\u0004\u0012\u0002H\u00070\u00062\u0006\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\t2#\u0010\u0010\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u00060\u0011¢\u0006\u0002\b\u0012H\u0007¨\u0006\u0013"}, d2 = {"checkFlowContext", "", "context", "Lkotlin/coroutines/CoroutineContext;", "checkFlowContext$FlowKt__ContextKt", "buffer", "Lkotlinx/coroutines/flow/Flow;", ExifInterface.GPS_DIRECTION_TRUE, "capacity", "", "conflate", "flowOn", "flowWith", "R", "flowContext", "bufferSize", "builder", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 1, 16}, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: classes4.dex */
public final /* synthetic */ class FlowKt__ContextKt {
    @ExperimentalCoroutinesApi
    @NotNull
    public static final <T> Flow<T> buffer(@NotNull Flow<? extends T> flow, int i) {
        if (i >= 0 || i == -2 || i == -1) {
            if (flow instanceof ChannelFlow) {
                return ChannelFlow.update$default((ChannelFlow) flow, null, i, 1, null);
            }
            return new ChannelFlowOperatorImpl(flow, null, i, 2, null);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Buffer size should be non-negative, BUFFERED, or CONFLATED, but was ", i).toString());
    }

    public static /* synthetic */ Flow buffer$default(Flow flow, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = -2;
        }
        return FlowKt.buffer(flow, i);
    }

    private static final void checkFlowContext$FlowKt__ContextKt(CoroutineContext coroutineContext) {
        if (coroutineContext.get(Job.Key) == null) {
            return;
        }
        throw new IllegalArgumentException(("Flow context cannot contain job in it. Had " + coroutineContext).toString());
    }

    @ExperimentalCoroutinesApi
    @NotNull
    public static final <T> Flow<T> conflate(@NotNull Flow<? extends T> flow) {
        return FlowKt.buffer(flow, -1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ExperimentalCoroutinesApi
    @NotNull
    public static final <T> Flow<T> flowOn(@NotNull Flow<? extends T> flow, @NotNull CoroutineContext coroutineContext) {
        checkFlowContext$FlowKt__ContextKt(coroutineContext);
        return Intrinsics.areEqual(coroutineContext, EmptyCoroutineContext.INSTANCE) ? flow : flow instanceof ChannelFlow ? ChannelFlow.update$default((ChannelFlow) flow, coroutineContext, 0, 2, null) : new ChannelFlowOperatorImpl(flow, coroutineContext, 0, 4, null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "flowWith is deprecated without replacement, please refer to its KDoc for an explanation")
    @FlowPreview
    @NotNull
    public static final <T, R> Flow<R> flowWith(@NotNull final Flow<? extends T> flow, @NotNull final CoroutineContext coroutineContext, final int i, @NotNull final Function1<? super Flow<? extends T>, ? extends Flow<? extends R>> function1) {
        checkFlowContext$FlowKt__ContextKt(coroutineContext);
        return new Flow<R>() { // from class: kotlinx.coroutines.flow.FlowKt__ContextKt$flowWith$$inlined$unsafeFlow$1
            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public Object collect(@NotNull final FlowCollector flowCollector, @NotNull Continuation continuation) {
                Object coroutine_suspended;
                Object collect = FlowKt.buffer(FlowKt.flowOn((Flow) function1.mo12165invoke(FlowKt.buffer(FlowKt.flowOn(Flow.this, continuation.getContext().minusKey(Job.Key)), i)), coroutineContext), i).collect(new FlowCollector<R>() { // from class: kotlinx.coroutines.flow.FlowKt__ContextKt$flowWith$$inlined$unsafeFlow$1$lambda$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    @Nullable
                    public Object emit(Object obj, @NotNull Continuation continuation2) {
                        Object coroutine_suspended2;
                        Object emit = FlowCollector.this.emit(obj, continuation2);
                        coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        return emit == coroutine_suspended2 ? emit : Unit.INSTANCE;
                    }
                }, continuation);
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                return collect == coroutine_suspended ? collect : Unit.INSTANCE;
            }
        };
    }

    public static /* synthetic */ Flow flowWith$default(Flow flow, CoroutineContext coroutineContext, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = -2;
        }
        return FlowKt.flowWith(flow, coroutineContext, i, function1);
    }
}
