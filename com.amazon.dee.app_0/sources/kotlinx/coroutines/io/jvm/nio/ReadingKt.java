package kotlinx.coroutines.io.jvm.nio;

import com.amazon.alexa.location.utils.MetricsUtil;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.io.ByteWriteChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Reading.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"copyTo", "", "Ljava/nio/channels/Pipe;", "ch", "Lkotlinx/coroutines/io/ByteWriteChannel;", MetricsUtil.LegacyMetricTypes.LIMIT, "(Ljava/nio/channels/Pipe;Lkotlinx/coroutines/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Ljava/nio/channels/ReadableByteChannel;", "(Ljava/nio/channels/ReadableByteChannel;Lkotlinx/coroutines/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ReadingKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c8  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x00c3 -> B:40:0x00c6). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyTo(@org.jetbrains.annotations.NotNull java.nio.channels.ReadableByteChannel r17, @org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteWriteChannel r18, long r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Long> r21) {
        /*
            Method dump skipped, instructions count: 232
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.jvm.nio.ReadingKt.copyTo(java.nio.channels.ReadableByteChannel, kotlinx.coroutines.io.ByteWriteChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static /* synthetic */ Object copyTo$default(ReadableByteChannel readableByteChannel, ByteWriteChannel byteWriteChannel, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyTo(readableByteChannel, byteWriteChannel, j, continuation);
    }

    @Nullable
    public static /* synthetic */ Object copyTo$default(Pipe pipe, ByteWriteChannel byteWriteChannel, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyTo(pipe, byteWriteChannel, j, continuation);
    }

    @Nullable
    public static final Object copyTo(@NotNull Pipe pipe, @NotNull ByteWriteChannel byteWriteChannel, long j, @NotNull Continuation<? super Long> continuation) {
        Pipe.SourceChannel source = pipe.source();
        Intrinsics.checkExpressionValueIsNotNull(source, "source()");
        return copyTo(source, byteWriteChannel, j, continuation);
    }
}
