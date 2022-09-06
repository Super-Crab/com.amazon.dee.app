package kotlinx.coroutines.io;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.drew.metadata.iptc.IptcDirectory;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteChannelSequential.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@ø\u0001\u0000"}, d2 = {"readRemaining", "", MetricsUtil.LegacyMetricTypes.LIMIT, "", "headerSizeHint", "", "continuation", "Lkotlin/coroutines/Continuation;", "Lkotlinx/io/core/ByteReadPacket;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx.coroutines.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", i = {0, 0, 0, 0, 0}, l = {IptcDirectory.TAG_DATE_SENT}, m = "readRemaining$suspendImpl", n = {"this", MetricsUtil.LegacyMetricTypes.LIMIT, "headerSizeHint", "builder", "remaining"}, s = {"L$0", "J$0", "I$0", "L$1", "J$1"})
/* loaded from: classes4.dex */
public final class ByteChannelSequentialBase$readRemaining$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteChannelSequentialBase this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteChannelSequentialBase$readRemaining$1(ByteChannelSequentialBase byteChannelSequentialBase, Continuation continuation) {
        super(continuation);
        this.this$0 = byteChannelSequentialBase;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ByteChannelSequentialBase.readRemaining$suspendImpl(this.this$0, 0L, 0, this);
    }
}
