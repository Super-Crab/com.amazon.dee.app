package kotlinx.coroutines.io.jvm.nio;

import com.amazon.alexa.location.utils.MetricsUtil;
import java.nio.channels.Pipe;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.io.ByteReadChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Writing.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"copyTo", "", "Lkotlinx/coroutines/io/ByteReadChannel;", "pipe", "Ljava/nio/channels/Pipe;", MetricsUtil.LegacyMetricTypes.LIMIT, "(Lkotlinx/coroutines/io/ByteReadChannel;Ljava/nio/channels/Pipe;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "channel", "Ljava/nio/channels/WritableByteChannel;", "(Lkotlinx/coroutines/io/ByteReadChannel;Ljava/nio/channels/WritableByteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class WritingKt {
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a9, code lost:
        if (r8.isClosedForRead() == false) goto L14;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004d  */
    /* JADX WARN: Type inference failed for: r8v13, types: [kotlin.jvm.functions.Function1] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00a2 -> B:42:0x00a5). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteReadChannel r8, @org.jetbrains.annotations.NotNull java.nio.channels.WritableByteChannel r9, long r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Long> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.io.jvm.nio.WritingKt$copyTo$1
            if (r0 == 0) goto L13
            r0 = r12
            kotlinx.coroutines.io.jvm.nio.WritingKt$copyTo$1 r0 = (kotlinx.coroutines.io.jvm.nio.WritingKt$copyTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.jvm.nio.WritingKt$copyTo$1 r0 = new kotlinx.coroutines.io.jvm.nio.WritingKt$copyTo$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L4d
            if (r2 != r4) goto L45
            java.lang.Object r8 = r0.L$3
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r0.L$2
            kotlin.jvm.internal.Ref$LongRef r9 = (kotlin.jvm.internal.Ref.LongRef) r9
            long r10 = r0.J$0
            java.lang.Object r2 = r0.L$1
            java.nio.channels.WritableByteChannel r2 = (java.nio.channels.WritableByteChannel) r2
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.io.ByteReadChannel r5 = (kotlinx.coroutines.io.ByteReadChannel) r5
            boolean r6 = r12 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L40
            r12 = r2
            r2 = r8
            r8 = r5
            goto La5
        L40:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r8 = r12.exception
            throw r8
        L45:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L4d:
            boolean r2 = r12 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Lc2
            r5 = 0
            int r12 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
            if (r12 < 0) goto L59
            r12 = r4
            goto L5a
        L59:
            r12 = r3
        L5a:
            if (r12 == 0) goto Lb2
            boolean r12 = r9 instanceof java.nio.channels.SelectableChannel
            if (r12 == 0) goto L72
            r12 = r9
            java.nio.channels.SelectableChannel r12 = (java.nio.channels.SelectableChannel) r12
            boolean r12 = r12.isBlocking()
            if (r12 == 0) goto L6a
            goto L72
        L6a:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "Non-blocking channels are not supported"
            r8.<init>(r9)
            throw r8
        L72:
            boolean r12 = r8.isClosedForRead()
            if (r12 == 0) goto L7d
            java.lang.Long r8 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r5)
            return r8
        L7d:
            kotlin.jvm.internal.Ref$LongRef r12 = new kotlin.jvm.internal.Ref$LongRef
            r12.<init>()
            r12.element = r5
            kotlinx.coroutines.io.jvm.nio.WritingKt$copyTo$copy$1 r2 = new kotlinx.coroutines.io.jvm.nio.WritingKt$copyTo$copy$1
            r2.<init>(r10, r12, r9)
            r7 = r12
            r12 = r9
            r9 = r7
        L8c:
            long r5 = r9.element
            int r5 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r5 >= 0) goto Lab
            r0.L$0 = r8
            r0.L$1 = r12
            r0.J$0 = r10
            r0.L$2 = r9
            r0.L$3 = r2
            r0.label = r4
            java.lang.Object r5 = r8.read(r3, r2, r0)
            if (r5 != r1) goto La5
            return r1
        La5:
            boolean r5 = r8.isClosedForRead()
            if (r5 == 0) goto L8c
        Lab:
            long r8 = r9.element
            java.lang.Long r8 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r8)
            return r8
        Lb2:
            java.lang.String r8 = "Limit shouldn't be negative: "
            java.lang.String r8 = com.android.tools.r8.GeneratedOutlineSupport1.outline56(r8, r10)
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r8 = r8.toString()
            r9.<init>(r8)
            throw r9
        Lc2:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r8 = r12.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.jvm.nio.WritingKt.copyTo(kotlinx.coroutines.io.ByteReadChannel, java.nio.channels.WritableByteChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static /* synthetic */ Object copyTo$default(ByteReadChannel byteReadChannel, WritableByteChannel writableByteChannel, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyTo(byteReadChannel, writableByteChannel, j, continuation);
    }

    @Nullable
    public static /* synthetic */ Object copyTo$default(ByteReadChannel byteReadChannel, Pipe pipe, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyTo(byteReadChannel, pipe, j, continuation);
    }

    @Nullable
    public static final Object copyTo(@NotNull ByteReadChannel byteReadChannel, @NotNull Pipe pipe, long j, @NotNull Continuation<? super Long> continuation) {
        Pipe.SinkChannel sink = pipe.sink();
        Intrinsics.checkExpressionValueIsNotNull(sink, "pipe.sink()");
        return copyTo(byteReadChannel, sink, j, continuation);
    }
}
