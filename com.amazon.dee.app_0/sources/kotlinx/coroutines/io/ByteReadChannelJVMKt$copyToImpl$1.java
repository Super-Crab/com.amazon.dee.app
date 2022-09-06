package kotlinx.coroutines.io;

import com.amazon.alexa.location.utils.MetricsUtil;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteReadChannelJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bH\u0082@ø\u0001\u0000"}, d2 = {"copyToImpl", "", "Lkotlinx/coroutines/io/ByteReadChannel;", "dst", "Lkotlinx/coroutines/io/ByteWriteChannel;", MetricsUtil.LegacyMetricTypes.LIMIT, "", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx.coroutines.io.ByteReadChannelJVMKt", f = "ByteReadChannelJVM.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, l = {255, 258}, m = "copyToImpl", n = {"$receiver", "dst", MetricsUtil.LegacyMetricTypes.LIMIT, "buffer", "dstNeedsFlush", "copied", "remaining", "$receiver", "dst", MetricsUtil.LegacyMetricTypes.LIMIT, "buffer", "dstNeedsFlush", "copied", "remaining", "size"}, s = {"L$0", "L$1", "J$0", "L$2", "I$0", "J$1", "J$2", "L$0", "L$1", "J$0", "L$2", "I$0", "J$1", "J$2", "I$1"})
/* loaded from: classes4.dex */
public final class ByteReadChannelJVMKt$copyToImpl$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    long J$0;
    long J$1;
    long J$2;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteReadChannelJVMKt$copyToImpl$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ByteReadChannelJVMKt.copyToImpl(null, null, 0L, this);
    }
}
