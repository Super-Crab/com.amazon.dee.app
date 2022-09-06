package kotlinx.io.core.internal;

import com.amazon.alexa.location.utils.MetricsUtil;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UTF8.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\u0006\u0010\u0005\u001a\u00020\u00062$\u0010\u0007\u001a \b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tH\u0087@ø\u0001\u0000"}, d2 = {"decodeUTF8LineLoopSuspend", "", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", MetricsUtil.LegacyMetricTypes.LIMIT, "", "nextChunk", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "Lkotlinx/io/core/ByteReadPacketBase;", "continuation", ""}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx.io.core.internal.UTF8Kt", f = "UTF8.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {29}, m = "decodeUTF8LineLoopSuspend", n = {"out", MetricsUtil.LegacyMetricTypes.LIMIT, "nextChunk", "decoded", "size", "cr", "end"}, s = {"L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* loaded from: classes4.dex */
public final class UTF8Kt$decodeUTF8LineLoopSuspend$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UTF8Kt$decodeUTF8LineLoopSuspend$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return UTF8Kt.decodeUTF8LineLoopSuspend(null, 0, null, this);
    }
}
