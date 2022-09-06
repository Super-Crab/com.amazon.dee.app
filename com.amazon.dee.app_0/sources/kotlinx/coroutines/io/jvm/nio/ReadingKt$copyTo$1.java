package kotlinx.coroutines.io.jvm.nio;

import com.amazon.alexa.location.utils.MetricsUtil;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.io.ByteWriteChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Reading.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bH\u0086@ø\u0001\u0000"}, d2 = {"copyTo", "", "Ljava/nio/channels/ReadableByteChannel;", "ch", "Lkotlinx/coroutines/io/ByteWriteChannel;", MetricsUtil.LegacyMetricTypes.LIMIT, "", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx.coroutines.io.jvm.nio.ReadingKt", f = "Reading.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {40}, m = "copyTo", n = {"$receiver", "ch", MetricsUtil.LegacyMetricTypes.LIMIT, "copied", "eof", "copy", "needFlush"}, s = {"L$0", "L$1", "J$0", "L$2", "L$3", "L$4", "I$0"})
/* loaded from: classes4.dex */
public final class ReadingKt$copyTo$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReadingKt$copyTo$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ReadingKt.copyTo((ReadableByteChannel) null, (ByteWriteChannel) null, 0L, this);
    }
}
