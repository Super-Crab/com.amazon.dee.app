package kotlinx.coroutines.io;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.ByteReadPacket;
import kotlinx.io.core.IoBuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteReadChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a'\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\u0015\u0010\t\u001a\u00020\u0004*\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\u001d\u0010\u000b\u001a\u00020\f*\u00020\u00022\u0006\u0010\r\u001a\u00020\u0004H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a\u001d\u0010\u000f\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0011H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u001d\u0010\u0013\u001a\u00020\f*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0011H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u001d\u0010\u0013\u001a\u00020\f*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0014H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001d\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u0015\u0010\u001a\u001a\u00020\u0017*\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\u001d\u0010\u001a\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a\u0017\u0010\u001b\u001a\u0004\u0018\u00010\u001c*\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a!\u0010\u001d\u001a\u00020\u0001*\u00020\u00022\n\u0010\u001e\u001a\u00060\u001fj\u0002` H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {DeviceStateModule.CANCEL, "", "Lkotlinx/coroutines/io/ByteReadChannel;", "copyAndClose", "", "dst", "Lkotlinx/coroutines/io/ByteWriteChannel;", MetricsUtil.LegacyMetricTypes.LIMIT, "(Lkotlinx/coroutines/io/ByteReadChannel;Lkotlinx/coroutines/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "discard", "(Lkotlinx/coroutines/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "discardExact", "", JsonReportFormat.COUNT, "(Lkotlinx/coroutines/io/ByteReadChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAvailable", "", "", "(Lkotlinx/coroutines/io/ByteReadChannel;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readFully", "Lkotlinx/io/core/IoBuffer;", "(Lkotlinx/coroutines/io/ByteReadChannel;Lkotlinx/io/core/IoBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readPacket", "Lkotlinx/io/core/ByteReadPacket;", "size", "(Lkotlinx/coroutines/io/ByteReadChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readRemaining", "readUTF8Line", "", "readUTF8LineTo", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "(Lkotlinx/coroutines/io/ByteReadChannel;Ljava/lang/Appendable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteReadChannelKt {
    public static final boolean cancel(@NotNull ByteReadChannel receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.cancel(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyAndClose(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteReadChannel r4, @org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteWriteChannel r5, long r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Long> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.io.ByteReadChannelKt$copyAndClose$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.ByteReadChannelKt$copyAndClose$1 r0 = (kotlinx.coroutines.io.ByteReadChannelKt$copyAndClose$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteReadChannelKt$copyAndClose$1 r0 = new kotlinx.coroutines.io.ByteReadChannelKt$copyAndClose$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L42
            if (r2 != r3) goto L3a
            long r4 = r0.J$0
            java.lang.Object r4 = r0.L$1
            r5 = r4
            kotlinx.coroutines.io.ByteWriteChannel r5 = (kotlinx.coroutines.io.ByteWriteChannel) r5
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteReadChannel r4 = (kotlinx.coroutines.io.ByteReadChannel) r4
            boolean r4 = r8 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L35
            goto L55
        L35:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r4 = r8.exception
            throw r4
        L3a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L42:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L63
            r0.L$0 = r4
            r0.L$1 = r5
            r0.J$0 = r6
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.io.ByteReadChannelJVMKt.copyTo(r4, r5, r6, r0)
            if (r8 != r1) goto L55
            return r1
        L55:
            java.lang.Number r8 = (java.lang.Number) r8
            long r6 = r8.longValue()
            kotlinx.coroutines.io.ByteWriteChannelKt.close(r5)
            java.lang.Long r4 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r6)
            return r4
        L63:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r4 = r8.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteReadChannelKt.copyAndClose(kotlinx.coroutines.io.ByteReadChannel, kotlinx.coroutines.io.ByteWriteChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static /* synthetic */ Object copyAndClose$default(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyAndClose(byteReadChannel, byteWriteChannel, j, continuation);
    }

    @Nullable
    public static final Object discard(@NotNull ByteReadChannel byteReadChannel, @NotNull Continuation<? super Long> continuation) {
        return byteReadChannel.discard(Long.MAX_VALUE, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object discardExact(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteReadChannel r4, long r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.io.ByteReadChannelKt$discardExact$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.io.ByteReadChannelKt$discardExact$1 r0 = (kotlinx.coroutines.io.ByteReadChannelKt$discardExact$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteReadChannelKt$discardExact$1 r0 = new kotlinx.coroutines.io.ByteReadChannelKt$discardExact$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            long r5 = r0.J$0
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteReadChannel r4 = (kotlinx.coroutines.io.ByteReadChannel) r4
            boolean r4 = r7 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L30
            goto L4e
        L30:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r4 = r7.exception
            throw r4
        L35:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3d:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L69
            r0.L$0 = r4
            r0.J$0 = r5
            r0.label = r3
            java.lang.Object r7 = r4.discard(r5, r0)
            if (r7 != r1) goto L4e
            return r1
        L4e:
            java.lang.Number r7 = (java.lang.Number) r7
            long r0 = r7.longValue()
            int r4 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r4 != 0) goto L5b
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L5b:
            java.io.EOFException r4 = new java.io.EOFException
            java.lang.String r7 = "Unable to discard "
            java.lang.String r0 = " bytes"
            java.lang.String r5 = com.android.tools.r8.GeneratedOutlineSupport1.outline57(r7, r5, r0)
            r4.<init>(r5)
            throw r4
        L69:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r4 = r7.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteReadChannelKt.discardExact(kotlinx.coroutines.io.ByteReadChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    private static final Object discardExact$$forInline(@NotNull ByteReadChannel byteReadChannel, long j, @NotNull Continuation continuation) {
        InlineMarker.mark(0);
        Object discard = byteReadChannel.discard(j, continuation);
        InlineMarker.mark(1);
        if (((Number) discard).longValue() == j) {
            return Unit.INSTANCE;
        }
        throw new EOFException(GeneratedOutlineSupport1.outline57("Unable to discard ", j, " bytes"));
    }

    @Nullable
    public static final Object readAvailable(@NotNull ByteReadChannel byteReadChannel, @NotNull byte[] bArr, @NotNull Continuation<? super Integer> continuation) {
        return byteReadChannel.readAvailable(bArr, 0, bArr.length, continuation);
    }

    @Nullable
    public static final Object readFully(@NotNull ByteReadChannel byteReadChannel, @NotNull IoBuffer ioBuffer, @NotNull Continuation<? super Unit> continuation) {
        return byteReadChannel.readFully(ioBuffer, ioBuffer.getWriteRemaining(), continuation);
    }

    @Nullable
    public static final Object readPacket(@NotNull ByteReadChannel byteReadChannel, int i, @NotNull Continuation<? super ByteReadPacket> continuation) {
        return byteReadChannel.readPacket(i, 0, continuation);
    }

    @Nullable
    public static final Object readRemaining(@NotNull ByteReadChannel byteReadChannel, long j, @NotNull Continuation<? super ByteReadPacket> continuation) {
        return byteReadChannel.readRemaining(j, 0, continuation);
    }

    @Nullable
    public static final Object readUTF8Line(@NotNull ByteReadChannel byteReadChannel, @NotNull Continuation<? super String> continuation) {
        return byteReadChannel.readUTF8Line(Integer.MAX_VALUE, continuation);
    }

    @Nullable
    public static final Object readUTF8LineTo(@NotNull ByteReadChannel byteReadChannel, @NotNull Appendable appendable, @NotNull Continuation<? super Boolean> continuation) {
        return byteReadChannel.readUTF8LineTo(appendable, Integer.MAX_VALUE, continuation);
    }

    @Nullable
    public static final Object readFully(@NotNull ByteReadChannel byteReadChannel, @NotNull byte[] bArr, @NotNull Continuation<? super Unit> continuation) {
        return byteReadChannel.readFully(bArr, 0, bArr.length, continuation);
    }

    @Nullable
    public static final Object readRemaining(@NotNull ByteReadChannel byteReadChannel, @NotNull Continuation<? super ByteReadPacket> continuation) {
        return byteReadChannel.readRemaining(Long.MAX_VALUE, 0, continuation);
    }
}
