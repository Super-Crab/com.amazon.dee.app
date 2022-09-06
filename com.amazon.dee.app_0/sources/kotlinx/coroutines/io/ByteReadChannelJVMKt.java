package kotlinx.coroutines.io;

import com.amazon.alexa.location.utils.MetricsUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteReadChannelJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a%\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a%\u0010\b\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a%\u0010\r\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\f*j\u0010\u000f\"2\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u000b0\u001022\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u000b0\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"copyTo", "", "Lkotlinx/coroutines/io/ByteReadChannel;", "dst", "Lkotlinx/coroutines/io/ByteWriteChannel;", MetricsUtil.LegacyMetricTypes.LIMIT, "(Lkotlinx/coroutines/io/ByteReadChannel;Lkotlinx/coroutines/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyToImpl", "joinTo", "", "closeOnEnd", "", "(Lkotlinx/coroutines/io/ByteReadChannel;Lkotlinx/coroutines/io/ByteWriteChannel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "joinToImplSuspend", "close", "ConsumeEachBufferVisitor", "Lkotlin/Function2;", "Ljava/nio/ByteBuffer;", "Lkotlin/ParameterName;", "name", "buffer", "last", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteReadChannelJVMKt {
    @Nullable
    public static final Object copyTo(@NotNull ByteReadChannel byteReadChannel, @NotNull ByteWriteChannel byteWriteChannel, long j, @NotNull Continuation<? super Long> continuation) {
        boolean z = true;
        if (byteReadChannel != byteWriteChannel) {
            if (j < 0) {
                z = false;
            }
            if (z) {
                if ((byteReadChannel instanceof ByteBufferChannel) && (byteWriteChannel instanceof ByteBufferChannel)) {
                    return ((ByteBufferChannel) byteWriteChannel).copyDirect$kotlinx_coroutines_io((ByteBufferChannel) byteReadChannel, j, null, continuation);
                }
                if ((byteReadChannel instanceof ByteChannelSequentialBase) && (byteWriteChannel instanceof ByteChannelSequentialBase)) {
                    return ByteChannelSequentialKt.copyTo$default((ByteChannelSequentialBase) byteReadChannel, (ByteChannelSequentialBase) byteWriteChannel, 0L, continuation, 2, null);
                }
                return copyToImpl(byteReadChannel, byteWriteChannel, j, continuation);
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ad A[Catch: all -> 0x004f, TRY_ENTER, TryCatch #2 {all -> 0x004f, blocks: (B:13:0x0040, B:51:0x010a, B:53:0x010e, B:55:0x0114, B:37:0x00ad, B:41:0x00d7, B:47:0x00ef, B:44:0x00e1, B:16:0x004a, B:17:0x004e), top: B:68:0x0040 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ef A[Catch: all -> 0x004f, TRY_ENTER, TryCatch #2 {all -> 0x004f, blocks: (B:13:0x0040, B:51:0x010a, B:53:0x010e, B:55:0x0114, B:37:0x00ad, B:41:0x00d7, B:47:0x00ef, B:44:0x00e1, B:16:0x004a, B:17:0x004e), top: B:68:0x0040 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x0109 -> B:51:0x010a). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ java.lang.Object copyToImpl(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteReadChannel r18, @org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteWriteChannel r19, long r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Long> r22) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteReadChannelJVMKt.copyToImpl(kotlinx.coroutines.io.ByteReadChannel, kotlinx.coroutines.io.ByteWriteChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static final Object joinTo(@NotNull ByteReadChannel byteReadChannel, @NotNull ByteWriteChannel byteWriteChannel, boolean z, @NotNull Continuation<? super Unit> continuation) {
        if (byteWriteChannel != byteReadChannel) {
            if ((byteReadChannel instanceof ByteBufferChannel) && (byteWriteChannel instanceof ByteBufferChannel)) {
                return ((ByteBufferChannel) byteWriteChannel).joinFrom$kotlinx_coroutines_io((ByteBufferChannel) byteReadChannel, z, continuation);
            }
            return joinToImplSuspend(byteReadChannel, byteWriteChannel, z, continuation);
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0060  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ java.lang.Object joinToImplSuspend(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteReadChannel r6, @org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteWriteChannel r7, boolean r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.io.ByteReadChannelJVMKt$joinToImplSuspend$1
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.io.ByteReadChannelJVMKt$joinToImplSuspend$1 r0 = (kotlinx.coroutines.io.ByteReadChannelJVMKt$joinToImplSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteReadChannelJVMKt$joinToImplSuspend$1 r0 = new kotlinx.coroutines.io.ByteReadChannelJVMKt$joinToImplSuspend$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L42
            if (r2 != r3) goto L3a
            boolean r8 = r0.Z$0
            java.lang.Object r6 = r0.L$1
            r7 = r6
            kotlinx.coroutines.io.ByteWriteChannel r7 = (kotlinx.coroutines.io.ByteWriteChannel) r7
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteReadChannel r6 = (kotlinx.coroutines.io.ByteReadChannel) r6
            boolean r6 = r9 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L35
            goto L5a
        L35:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r6 = r9.exception
            throw r6
        L3a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L42:
            boolean r2 = r9 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L66
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.Z$0 = r8
            r0.label = r3
            java.lang.Object r6 = copyTo(r6, r7, r4, r0)
            if (r6 != r1) goto L5a
            return r1
        L5a:
            if (r8 == 0) goto L60
            kotlinx.coroutines.io.ByteWriteChannelKt.close(r7)
            goto L63
        L60:
            r7.flush()
        L63:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L66:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r6 = r9.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteReadChannelJVMKt.joinToImplSuspend(kotlinx.coroutines.io.ByteReadChannel, kotlinx.coroutines.io.ByteWriteChannel, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
