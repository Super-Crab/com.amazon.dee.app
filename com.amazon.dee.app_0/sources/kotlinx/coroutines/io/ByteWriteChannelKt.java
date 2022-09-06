package kotlinx.coroutines.io;

import com.facebook.common.callercontext.ContextChain;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.BytePacketBuilder;
import kotlinx.io.core.ByteReadPacket;
import kotlinx.io.core.PacketJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteWriteChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001d\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\b\u001a\u00020\t*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\u001d\u0010\f\u001a\u00020\t*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u001d\u0010\u000e\u001a\u00020\t*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u001d\u0010\u0012\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\u0013\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a8\u0010\u0017\u001a\u00020\t*\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u00042\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\t0\u001a¢\u0006\u0002\b\u001cH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001a>\u0010\u001e\u001a\u00020\t*\u00020\u00022'\u0010\u0019\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0 \u0012\u0006\u0012\u0004\u0018\u00010!0\u001f¢\u0006\u0002\b\u001cH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\"\u001a\u001d\u0010#\u001a\u00020\t*\u00020\u00022\u0006\u0010$\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u001d\u0010%\u001a\u00020\t*\u00020\u00022\u0006\u0010$\u001a\u00020&H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010'\u001a\u001d\u0010%\u001a\u00020\t*\u00020\u00022\u0006\u0010$\u001a\u00020(H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010)\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, d2 = {"close", "", "Lkotlinx/coroutines/io/ByteWriteChannel;", "writeAvailable", "", "src", "", "(Lkotlinx/coroutines/io/ByteWriteChannel;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeBoolean", "", "b", "(Lkotlinx/coroutines/io/ByteWriteChannel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeByte", "(Lkotlinx/coroutines/io/ByteWriteChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeChar", "ch", "", "(Lkotlinx/coroutines/io/ByteWriteChannel;CLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFully", "writeInt", ContextChain.TAG_INFRA, "", "(Lkotlinx/coroutines/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writePacket", "headerSizeHint", "builder", "Lkotlin/Function1;", "Lkotlinx/io/core/BytePacketBuilder;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/io/ByteWriteChannel;ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writePacketSuspend", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/io/ByteWriteChannel;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeShort", "s", "writeStringUtf8", "", "(Lkotlinx/coroutines/io/ByteWriteChannel;Ljava/lang/CharSequence;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Lkotlinx/coroutines/io/ByteWriteChannel;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteWriteChannelKt {
    public static final boolean close(@NotNull ByteWriteChannel receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.close(null);
    }

    @Nullable
    public static final Object writeAvailable(@NotNull ByteWriteChannel byteWriteChannel, @NotNull byte[] bArr, @NotNull Continuation<? super Integer> continuation) {
        return byteWriteChannel.writeAvailable(bArr, 0, bArr.length, continuation);
    }

    @Nullable
    public static final Object writeBoolean(@NotNull ByteWriteChannel byteWriteChannel, boolean z, @NotNull Continuation<? super Unit> continuation) {
        return writeByte(byteWriteChannel, z ? 1 : 0, continuation);
    }

    @Nullable
    public static final Object writeByte(@NotNull ByteWriteChannel byteWriteChannel, int i, @NotNull Continuation<? super Unit> continuation) {
        return byteWriteChannel.writeByte((byte) (i & 255), continuation);
    }

    @Nullable
    public static final Object writeChar(@NotNull ByteWriteChannel byteWriteChannel, char c, @NotNull Continuation<? super Unit> continuation) {
        return writeShort(byteWriteChannel, c, continuation);
    }

    @Nullable
    public static final Object writeFully(@NotNull ByteWriteChannel byteWriteChannel, @NotNull byte[] bArr, @NotNull Continuation<? super Unit> continuation) {
        return byteWriteChannel.writeFully(bArr, 0, bArr.length, continuation);
    }

    @Nullable
    public static final Object writeInt(@NotNull ByteWriteChannel byteWriteChannel, long j, @NotNull Continuation<? super Unit> continuation) {
        return byteWriteChannel.writeInt((int) j, continuation);
    }

    @Nullable
    public static final Object writePacket(@NotNull ByteWriteChannel byteWriteChannel, int i, @NotNull Function1<? super BytePacketBuilder, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(i);
        try {
            function1.mo12165invoke(BytePacketBuilder);
            return byteWriteChannel.writePacket(BytePacketBuilder.build(), continuation);
        } catch (Throwable th) {
            BytePacketBuilder.release();
            throw th;
        }
    }

    @Nullable
    private static final Object writePacket$$forInline(@NotNull ByteWriteChannel byteWriteChannel, int i, @NotNull Function1 function1, @NotNull Continuation continuation) {
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(i);
        try {
            function1.mo12165invoke(BytePacketBuilder);
            ByteReadPacket build = BytePacketBuilder.build();
            InlineMarker.mark(0);
            Object writePacket = byteWriteChannel.writePacket(build, continuation);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return writePacket;
        } catch (Throwable th) {
            BytePacketBuilder.release();
            throw th;
        }
    }

    @Nullable
    public static /* synthetic */ Object writePacket$default(ByteWriteChannel byteWriteChannel, int i, Function1 function1, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(i);
        try {
            function1.mo12165invoke(BytePacketBuilder);
            ByteReadPacket build = BytePacketBuilder.build();
            InlineMarker.mark(0);
            Object writePacket = byteWriteChannel.writePacket(build, continuation);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return writePacket;
        } catch (Throwable th) {
            BytePacketBuilder.release();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0097 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0098 A[PHI: r9 
      PHI: (r9v6 java.lang.Object) = (r9v5 java.lang.Object), (r9v1 java.lang.Object) binds: [B:36:0x0095, B:13:0x0032] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object writePacketSuspend(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteWriteChannel r7, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super kotlinx.io.core.BytePacketBuilder, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.io.ByteWriteChannelKt$writePacketSuspend$1
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.io.ByteWriteChannelKt$writePacketSuspend$1 r0 = (kotlinx.coroutines.io.ByteWriteChannelKt$writePacketSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteWriteChannelKt$writePacketSuspend$1 r0 = new kotlinx.coroutines.io.ByteWriteChannelKt$writePacketSuspend$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L65
            if (r2 == r4) goto L42
            if (r2 != r3) goto L3a
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.io.ByteWriteChannel r7 = (kotlinx.coroutines.io.ByteWriteChannel) r7
            boolean r7 = r9 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L35
            goto L98
        L35:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r7 = r9.exception
            throw r7
        L3a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L42:
            java.lang.Object r7 = r0.L$4
            kotlinx.coroutines.io.ByteWriteChannel r7 = (kotlinx.coroutines.io.ByteWriteChannel) r7
            java.lang.Object r8 = r0.L$3
            kotlinx.io.core.BytePacketBuilder r8 = (kotlinx.io.core.BytePacketBuilder) r8
            java.lang.Object r8 = r0.L$2
            kotlinx.io.core.BytePacketBuilder r8 = (kotlinx.io.core.BytePacketBuilder) r8
            int r2 = r0.I$0
            java.lang.Object r2 = r0.L$1
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteWriteChannel r4 = (kotlinx.coroutines.io.ByteWriteChannel) r4
            boolean r5 = r9 instanceof kotlin.Result.Failure     // Catch: java.lang.Throwable -> L62
            if (r5 != 0) goto L5d
            goto L87
        L5d:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9     // Catch: java.lang.Throwable -> L62
            java.lang.Throwable r7 = r9.exception     // Catch: java.lang.Throwable -> L62
            throw r7     // Catch: java.lang.Throwable -> L62
        L62:
            r7 = move-exception
            r2 = r8
            goto L9a
        L65:
            boolean r2 = r9 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L9e
            r9 = 0
            kotlinx.io.core.BytePacketBuilder r2 = kotlinx.io.core.PacketJVMKt.BytePacketBuilder(r9)
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L99
            r0.L$1 = r8     // Catch: java.lang.Throwable -> L99
            r0.I$0 = r9     // Catch: java.lang.Throwable -> L99
            r0.L$2 = r2     // Catch: java.lang.Throwable -> L99
            r0.L$3 = r2     // Catch: java.lang.Throwable -> L99
            r0.L$4 = r7     // Catch: java.lang.Throwable -> L99
            r0.label = r4     // Catch: java.lang.Throwable -> L99
            java.lang.Object r9 = r8.mo12248invoke(r2, r0)     // Catch: java.lang.Throwable -> L99
            if (r9 != r1) goto L83
            return r1
        L83:
            r4 = r7
            r6 = r2
            r2 = r8
            r8 = r6
        L87:
            kotlinx.io.core.ByteReadPacket r8 = r8.build()     // Catch: java.lang.Throwable -> L62
            r0.L$0 = r4
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r9 = r7.writePacket(r8, r0)
            if (r9 != r1) goto L98
            return r1
        L98:
            return r9
        L99:
            r7 = move-exception
        L9a:
            r2.release()
            throw r7
        L9e:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r7 = r9.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteWriteChannelKt.writePacketSuspend(kotlinx.coroutines.io.ByteWriteChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static final Object writeShort(@NotNull ByteWriteChannel byteWriteChannel, int i, @NotNull Continuation<? super Unit> continuation) {
        return byteWriteChannel.writeShort((short) (i & 65535), continuation);
    }

    @Nullable
    public static final Object writeStringUtf8(@NotNull ByteWriteChannel byteWriteChannel, @NotNull CharSequence charSequence, @NotNull Continuation<? super Unit> continuation) {
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(0);
        try {
            BytePacketBuilder.writeStringUtf8(charSequence);
            return byteWriteChannel.writePacket(BytePacketBuilder.build(), continuation);
        } catch (Throwable th) {
            BytePacketBuilder.release();
            throw th;
        }
    }

    @Nullable
    public static final Object writeStringUtf8(@NotNull ByteWriteChannel byteWriteChannel, @NotNull String str, @NotNull Continuation<? super Unit> continuation) {
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(0);
        try {
            BytePacketBuilder.writeStringUtf8(str);
            return byteWriteChannel.writePacket(BytePacketBuilder.build(), continuation);
        } catch (Throwable th) {
            BytePacketBuilder.release();
            throw th;
        }
    }
}
