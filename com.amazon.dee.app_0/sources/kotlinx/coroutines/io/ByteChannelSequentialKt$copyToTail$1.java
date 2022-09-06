package kotlinx.coroutines.io;

import com.amazon.alexa.location.utils.MetricsUtil;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteChannelSequential.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0082@ø\u0001\u0000"}, d2 = {"copyToTail", "", "Lkotlinx/coroutines/io/ByteChannelSequentialBase;", "dst", MetricsUtil.LegacyMetricTypes.LIMIT, "", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx.coroutines.io.ByteChannelSequentialKt", f = "ByteChannelSequential.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {47, 53}, m = "copyToTail", n = {"$receiver", "dst", MetricsUtil.LegacyMetricTypes.LIMIT, "lastPiece", "$receiver", "dst", MetricsUtil.LegacyMetricTypes.LIMIT, "lastPiece", "rc"}, s = {"L$0", "L$1", "J$0", "L$2", "L$0", "L$1", "J$0", "L$2", "I$0"})
/* loaded from: classes4.dex */
public final class ByteChannelSequentialKt$copyToTail$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteChannelSequentialKt$copyToTail$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ByteChannelSequentialKt.copyToTail(null, null, 0L, this);
    }
}
