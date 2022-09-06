package io.ktor.util.cio;

import com.amazon.alexa.location.utils.MetricsUtil;
import io.ktor.util.InternalAPI;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.ByteWriteChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Readers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006H\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001aA\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\"\u0010\u0005\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\bH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\u001f\u0010\f\u001a\u00020\r*\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a&\u0010\u0011\u001a\u00020\u0001*\u00020\u00122\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0013H\u0086\b\u001a>\u0010\u0011\u001a\u00020\u0001*\u00020\u00122'\u0010\u0005\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b¢\u0006\u0002\b\u0013H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"pass", "", "Lkotlinx/coroutines/io/ByteReadChannel;", "buffer", "Ljava/nio/ByteBuffer;", "block", "Lkotlin/Function1;", "(Lkotlinx/coroutines/io/ByteReadChannel;Ljava/nio/ByteBuffer;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/io/ByteReadChannel;Ljava/nio/ByteBuffer;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toByteArray", "", MetricsUtil.LegacyMetricTypes.LIMIT, "", "(Lkotlinx/coroutines/io/ByteReadChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "use", "Lkotlinx/coroutines/io/ByteWriteChannel;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/io/ByteWriteChannel;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ReadersKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x005f -> B:25:0x0062). Please submit an issue!!! */
    @io.ktor.util.InternalAPI
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object pass(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteReadChannel r5, @org.jetbrains.annotations.NotNull java.nio.ByteBuffer r6, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, kotlin.Unit> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.util.cio.ReadersKt$pass$1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.util.cio.ReadersKt$pass$1 r0 = (io.ktor.util.cio.ReadersKt$pass$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.util.cio.ReadersKt$pass$1 r0 = new io.ktor.util.cio.ReadersKt$pass$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L46
            if (r2 != r3) goto L3e
            java.lang.Object r5 = r0.L$2
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r6 = r0.L$1
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.io.ByteReadChannel r7 = (kotlinx.coroutines.io.ByteReadChannel) r7
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L39
            r4 = r7
            r7 = r5
            r5 = r4
            goto L62
        L39:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r5 = r8.exception
            throw r5
        L3e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L46:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L6c
        L4a:
            boolean r8 = r5.isClosedForRead()
            if (r8 != 0) goto L69
            r6.clear()
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r3
            java.lang.Object r8 = r5.readAvailable(r6, r0)
            if (r8 != r1) goto L62
            return r1
        L62:
            r6.flip()
            r7.mo12165invoke(r6)
            goto L4a
        L69:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L6c:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r5 = r8.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.cio.ReadersKt.pass(kotlinx.coroutines.io.ByteReadChannel, java.nio.ByteBuffer, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @InternalAPI
    @Nullable
    private static final Object pass$$forInline(@NotNull ByteReadChannel byteReadChannel, @NotNull ByteBuffer byteBuffer, @NotNull Function1 function1, @NotNull Continuation continuation) {
        while (!byteReadChannel.isClosedForRead()) {
            byteBuffer.clear();
            InlineMarker.mark(0);
            byteReadChannel.readAvailable(byteBuffer, continuation);
            InlineMarker.mark(1);
            byteBuffer.flip();
            function1.mo12165invoke(byteBuffer);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003d  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object toByteArray(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteReadChannel r6, int r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super byte[]> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.util.cio.ReadersKt$toByteArray$1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.util.cio.ReadersKt$toByteArray$1 r0 = (io.ktor.util.cio.ReadersKt$toByteArray$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.util.cio.ReadersKt$toByteArray$1 r0 = new io.ktor.util.cio.ReadersKt$toByteArray$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            int r6 = r0.I$0
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteReadChannel r6 = (kotlinx.coroutines.io.ByteReadChannel) r6
            boolean r6 = r8 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L30
            goto L4f
        L30:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        L35:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3d:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L58
            long r4 = (long) r7
            r0.L$0 = r6
            r0.I$0 = r7
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.io.ByteReadChannelKt.readRemaining(r6, r4, r0)
            if (r8 != r1) goto L4f
            return r1
        L4f:
            kotlinx.io.core.ByteReadPacket r8 = (kotlinx.io.core.ByteReadPacket) r8
            r6 = 0
            r7 = 0
            byte[] r6 = kotlinx.io.core.StringsKt.readBytes$default(r8, r6, r3, r7)
            return r6
        L58:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.cio.ReadersKt.toByteArray(kotlinx.coroutines.io.ByteReadChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static /* synthetic */ Object toByteArray$default(ByteReadChannel byteReadChannel, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Integer.MAX_VALUE;
        }
        return toByteArray(byteReadChannel, i, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0041  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "")
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ java.lang.Object use(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteWriteChannel r4, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super kotlinx.coroutines.io.ByteWriteChannel, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.util.cio.ReadersKt$use$1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.util.cio.ReadersKt$use$1 r0 = (io.ktor.util.cio.ReadersKt$use$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.util.cio.ReadersKt$use$1 r0 = new io.ktor.util.cio.ReadersKt$use$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L41
            if (r2 != r3) goto L39
            java.lang.Object r4 = r0.L$1
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteWriteChannel r4 = (kotlinx.coroutines.io.ByteWriteChannel) r4
            boolean r5 = r6 instanceof kotlin.Result.Failure     // Catch: java.lang.Throwable -> L37
            if (r5 != 0) goto L32
            goto L52
        L32:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6     // Catch: java.lang.Throwable -> L37
            java.lang.Throwable r5 = r6.exception     // Catch: java.lang.Throwable -> L37
            throw r5     // Catch: java.lang.Throwable -> L37
        L37:
            r5 = move-exception
            goto L58
        L39:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L41:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L61
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L37
            r0.L$1 = r5     // Catch: java.lang.Throwable -> L37
            r0.label = r3     // Catch: java.lang.Throwable -> L37
            java.lang.Object r5 = r5.mo12248invoke(r4, r0)     // Catch: java.lang.Throwable -> L37
            if (r5 != r1) goto L52
            return r1
        L52:
            kotlinx.coroutines.io.ByteWriteChannelKt.close(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L58:
            r4.close(r5)     // Catch: java.lang.Throwable -> L5c
            throw r5     // Catch: java.lang.Throwable -> L5c
        L5c:
            r5 = move-exception
            kotlinx.coroutines.io.ByteWriteChannelKt.close(r4)
            throw r5
        L61:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r4 = r6.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.cio.ReadersKt.use(kotlinx.coroutines.io.ByteWriteChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x009d -> B:27:0x0070). Please submit an issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    @io.ktor.util.InternalAPI
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ java.lang.Object pass(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteReadChannel r7, @org.jetbrains.annotations.NotNull java.nio.ByteBuffer r8, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.nio.ByteBuffer, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.util.cio.ReadersKt$pass$2
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.util.cio.ReadersKt$pass$2 r0 = (io.ktor.util.cio.ReadersKt$pass$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.util.cio.ReadersKt$pass$2 r0 = new io.ktor.util.cio.ReadersKt$pass$2
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L68
            if (r2 == r4) goto L4e
            if (r2 != r3) goto L46
            java.lang.Object r7 = r0.L$4
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r7 = r0.L$3
            kotlinx.coroutines.io.ByteReadChannel r7 = (kotlinx.coroutines.io.ByteReadChannel) r7
            java.lang.Object r8 = r0.L$2
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            java.lang.Object r9 = r0.L$1
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteReadChannel r2 = (kotlinx.coroutines.io.ByteReadChannel) r2
            boolean r5 = r10 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L41
            goto L70
        L41:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r7 = r10.exception
            throw r7
        L46:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L4e:
            java.lang.Object r7 = r0.L$3
            kotlinx.coroutines.io.ByteReadChannel r7 = (kotlinx.coroutines.io.ByteReadChannel) r7
            java.lang.Object r8 = r0.L$2
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            java.lang.Object r9 = r0.L$1
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteReadChannel r2 = (kotlinx.coroutines.io.ByteReadChannel) r2
            boolean r5 = r10 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L63
            goto L8a
        L63:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r7 = r10.exception
            throw r7
        L68:
            boolean r2 = r10 instanceof kotlin.Result.Failure
            if (r2 != 0) goto La3
            r2 = r7
            r6 = r9
            r9 = r8
            r8 = r6
        L70:
            boolean r10 = r7.isClosedForRead()
            if (r10 != 0) goto La0
            r9.clear()
            r0.L$0 = r2
            r0.L$1 = r9
            r0.L$2 = r8
            r0.L$3 = r7
            r0.label = r4
            java.lang.Object r10 = r7.readAvailable(r9, r0)
            if (r10 != r1) goto L8a
            return r1
        L8a:
            r9.flip()
            r0.L$0 = r2
            r0.L$1 = r9
            r0.L$2 = r8
            r0.L$3 = r7
            r0.L$4 = r9
            r0.label = r3
            java.lang.Object r10 = r8.mo12248invoke(r9, r0)
            if (r10 != r1) goto L70
            return r1
        La0:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        La3:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r7 = r10.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.cio.ReadersKt.pass(kotlinx.coroutines.io.ByteReadChannel, java.nio.ByteBuffer, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final void use(@NotNull ByteWriteChannel receiver$0, @NotNull Function1<? super ByteWriteChannel, Unit> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        try {
            block.mo12165invoke(receiver$0);
        } finally {
        }
    }
}
