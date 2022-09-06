package kotlinx.coroutines.io.jvm.javaio;

import com.amazon.alexa.location.utils.MetricsUtil;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.io.ByteReadChannel;
import org.jetbrains.annotations.Nullable;
/* compiled from: Writing.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"copyTo", "", "Lkotlinx/coroutines/io/ByteReadChannel;", "out", "Ljava/io/OutputStream;", MetricsUtil.LegacyMetricTypes.LIMIT, "(Lkotlinx/coroutines/io/ByteReadChannel;Ljava/io/OutputStream;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class WritingKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0082 A[Catch: all -> 0x00b0, TryCatch #0 {all -> 0x00b0, blocks: (B:35:0x009e, B:42:0x00b2, B:32:0x0082, B:39:0x00aa, B:29:0x0077), top: B:51:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a8  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x009b -> B:35:0x009e). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteReadChannel r18, @org.jetbrains.annotations.NotNull java.io.OutputStream r19, long r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Long> r22) {
        /*
            Method dump skipped, instructions count: 219
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.jvm.javaio.WritingKt.copyTo(kotlinx.coroutines.io.ByteReadChannel, java.io.OutputStream, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static /* synthetic */ Object copyTo$default(ByteReadChannel byteReadChannel, OutputStream outputStream, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyTo(byteReadChannel, outputStream, j, continuation);
    }
}
