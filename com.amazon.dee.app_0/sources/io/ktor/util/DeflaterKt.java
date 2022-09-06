package io.ktor.util;

import io.ktor.util.cio.ByteBufferPoolKt;
import java.nio.ByteBuffer;
import java.util.zip.Checksum;
import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.ByteWriteChannel;
import kotlinx.coroutines.io.CoroutinesKt;
import kotlinx.coroutines.io.ReaderScope;
import kotlinx.coroutines.io.WriterScope;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: Deflater.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0014\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a3\u0010\t\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a0\u0010\u0011\u001a\u00020\u0012*\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001a0\u0010\u0011\u001a\u00020\n*\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001a\u0015\u0010\u0018\u001a\u00020\u0005*\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a%\u0010\u001a\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u000b\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001a\u0014\u0010\u001e\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\f\u001a\u00020\bH\u0002\u001a\u0014\u0010\u001f\u001a\u00020\u0005*\u00020\u001c2\u0006\u0010\f\u001a\u00020\bH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"GZIP_MAGIC", "", "headerPadding", "", "deflate", "", "Ljava/util/zip/Deflater;", "outBuffer", "Ljava/nio/ByteBuffer;", "deflateWhile", "Lkotlinx/coroutines/io/ByteWriteChannel;", "deflater", "buffer", "predicate", "Lkotlin/Function0;", "", "(Lkotlinx/coroutines/io/ByteWriteChannel;Ljava/util/zip/Deflater;Ljava/nio/ByteBuffer;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deflated", "Lkotlinx/coroutines/io/ByteReadChannel;", "gzip", "pool", "Lkotlinx/io/pool/ObjectPool;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "putGzipHeader", "(Lkotlinx/coroutines/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "putGzipTrailer", "crc", "Ljava/util/zip/Checksum;", "(Lkotlinx/coroutines/io/ByteWriteChannel;Ljava/util/zip/Checksum;Ljava/util/zip/Deflater;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setInput", "updateKeepPosition", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class DeflaterKt {
    private static final int GZIP_MAGIC = 35615;
    private static final byte[] headerPadding = new byte[7];

    private static final void deflate(@NotNull Deflater deflater, ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            byteBuffer.position(byteBuffer.position() + deflater.deflate(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.remaining()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005d  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ java.lang.Object deflateWhile(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteWriteChannel r6, @org.jetbrains.annotations.NotNull java.util.zip.Deflater r7, @org.jetbrains.annotations.NotNull java.nio.ByteBuffer r8, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<java.lang.Boolean> r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.util.DeflaterKt$deflateWhile$1
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.util.DeflaterKt$deflateWhile$1 r0 = (io.ktor.util.DeflaterKt$deflateWhile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.util.DeflaterKt$deflateWhile$1 r0 = new io.ktor.util.DeflaterKt$deflateWhile$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L4d
            if (r2 != r3) goto L45
            java.lang.Object r6 = r0.L$3
            kotlin.jvm.functions.Function0 r6 = (kotlin.jvm.functions.Function0) r6
            java.lang.Object r7 = r0.L$2
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r8 = r0.L$1
            java.util.zip.Deflater r8 = (java.util.zip.Deflater) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.io.ByteWriteChannel r9 = (kotlinx.coroutines.io.ByteWriteChannel) r9
            boolean r2 = r10 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L40
            r4 = r9
            r9 = r6
            r6 = r4
            r5 = r8
            r8 = r7
            r7 = r5
            goto L51
        L40:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r6 = r10.exception
            throw r6
        L45:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L4d:
            boolean r2 = r10 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L7a
        L51:
            java.lang.Object r10 = r9.mo12560invoke()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L77
            r8.clear()
            deflate(r7, r8)
            r8.flip()
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.L$3 = r9
            r0.label = r3
            java.lang.Object r10 = r6.writeFully(r8, r0)
            if (r10 != r1) goto L51
            return r1
        L77:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L7a:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r6 = r10.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.DeflaterKt.deflateWhile(kotlinx.coroutines.io.ByteWriteChannel, java.util.zip.Deflater, java.nio.ByteBuffer, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @KtorExperimentalAPI
    @NotNull
    public static final ByteReadChannel deflated(@NotNull ByteReadChannel receiver$0, boolean z, @NotNull ObjectPool<ByteBuffer> pool, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        return CoroutinesKt.writer((CoroutineScope) GlobalScope.INSTANCE, coroutineContext, true, (Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object>) new DeflaterKt$deflated$1(receiver$0, pool, z, null)).mo12310getChannel();
    }

    @KtorExperimentalAPI
    @NotNull
    public static /* synthetic */ ByteReadChannel deflated$default(ByteReadChannel byteReadChannel, boolean z, ObjectPool objectPool, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            objectPool = ByteBufferPoolKt.getKtorDefaultPool();
        }
        if ((i & 4) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return deflated(byteReadChannel, z, objectPool, coroutineContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x008a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x008b A[PHI: r7 
      PHI: (r7v11 java.lang.Object) = (r7v10 java.lang.Object), (r7v1 java.lang.Object) binds: [B:39:0x0088, B:14:0x0031] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ java.lang.Object putGzipHeader(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteWriteChannel r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.util.DeflaterKt$putGzipHeader$1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.util.DeflaterKt$putGzipHeader$1 r0 = (io.ktor.util.DeflaterKt$putGzipHeader$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.util.DeflaterKt$putGzipHeader$1 r0 = new io.ktor.util.DeflaterKt$putGzipHeader$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L5d
            if (r2 == r5) goto L4f
            if (r2 == r4) goto L41
            if (r2 != r3) goto L39
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteWriteChannel r6 = (kotlinx.coroutines.io.ByteWriteChannel) r6
            boolean r6 = r7 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L34
            goto L8b
        L34:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L39:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L41:
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteWriteChannel r6 = (kotlinx.coroutines.io.ByteWriteChannel) r6
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L4a
            goto L7e
        L4a:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L4f:
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteWriteChannel r6 = (kotlinx.coroutines.io.ByteWriteChannel) r6
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L58
            goto L70
        L58:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L5d:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L8c
            r7 = 35615(0x8b1f, float:4.9907E-41)
            short r7 = (short) r7
            r0.L$0 = r6
            r0.label = r5
            java.lang.Object r7 = r6.writeShort(r7, r0)
            if (r7 != r1) goto L70
            return r1
        L70:
            r7 = 8
            byte r7 = (byte) r7
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = r6.writeByte(r7, r0)
            if (r7 != r1) goto L7e
            return r1
        L7e:
            byte[] r7 = io.ktor.util.DeflaterKt.headerPadding
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.io.ByteWriteChannelKt.writeFully(r6, r7, r0)
            if (r7 != r1) goto L8b
            return r1
        L8b:
            return r7
        L8c:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.DeflaterKt.putGzipHeader(kotlinx.coroutines.io.ByteWriteChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0088 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0089 A[PHI: r10 
      PHI: (r10v7 java.lang.Object) = (r10v6 java.lang.Object), (r10v1 java.lang.Object) binds: [B:30:0x0086, B:13:0x0036] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ java.lang.Object putGzipTrailer(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteWriteChannel r7, @org.jetbrains.annotations.NotNull java.util.zip.Checksum r8, @org.jetbrains.annotations.NotNull java.util.zip.Deflater r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.util.DeflaterKt$putGzipTrailer$1
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.util.DeflaterKt$putGzipTrailer$1 r0 = (io.ktor.util.DeflaterKt$putGzipTrailer$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.util.DeflaterKt$putGzipTrailer$1 r0 = new io.ktor.util.DeflaterKt$putGzipTrailer$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L5e
            if (r2 == r4) goto L46
            if (r2 != r3) goto L3e
            java.lang.Object r7 = r0.L$2
            java.util.zip.Deflater r7 = (java.util.zip.Deflater) r7
            java.lang.Object r7 = r0.L$1
            java.util.zip.Checksum r7 = (java.util.zip.Checksum) r7
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.io.ByteWriteChannel r7 = (kotlinx.coroutines.io.ByteWriteChannel) r7
            boolean r7 = r10 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L39
            goto L89
        L39:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r7 = r10.exception
            throw r7
        L3e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L46:
            java.lang.Object r7 = r0.L$2
            r9 = r7
            java.util.zip.Deflater r9 = (java.util.zip.Deflater) r9
            java.lang.Object r7 = r0.L$1
            r8 = r7
            java.util.zip.Checksum r8 = (java.util.zip.Checksum) r8
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.io.ByteWriteChannel r7 = (kotlinx.coroutines.io.ByteWriteChannel) r7
            boolean r2 = r10 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L59
            goto L76
        L59:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r7 = r10.exception
            throw r7
        L5e:
            boolean r2 = r10 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L8a
            long r5 = r8.getValue()
            int r10 = (int) r5
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.label = r4
            java.lang.Object r10 = r7.writeInt(r10, r0)
            if (r10 != r1) goto L76
            return r1
        L76:
            int r10 = r9.getTotalIn()
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.label = r3
            java.lang.Object r10 = r7.writeInt(r10, r0)
            if (r10 != r1) goto L89
            return r1
        L89:
            return r10
        L8a:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r7 = r10.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.DeflaterKt.putGzipTrailer(kotlinx.coroutines.io.ByteWriteChannel, java.util.zip.Checksum, java.util.zip.Deflater, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setInput(@NotNull Deflater deflater, ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            deflater.setInput(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.remaining());
            return;
        }
        throw new IllegalArgumentException("buffer need to be array-backed".toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateKeepPosition(@NotNull Checksum checksum, ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            checksum.update(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.remaining());
            return;
        }
        throw new IllegalArgumentException("buffer need to be array-backed".toString());
    }

    @KtorExperimentalAPI
    @NotNull
    public static final ByteWriteChannel deflated(@NotNull ByteWriteChannel receiver$0, boolean z, @NotNull ObjectPool<ByteBuffer> pool, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        return CoroutinesKt.reader((CoroutineScope) GlobalScope.INSTANCE, coroutineContext, true, (Function2<? super ReaderScope, ? super Continuation<? super Unit>, ? extends Object>) new DeflaterKt$deflated$2(receiver$0, z, pool, coroutineContext, null)).mo12310getChannel();
    }

    @KtorExperimentalAPI
    @NotNull
    public static /* synthetic */ ByteWriteChannel deflated$default(ByteWriteChannel byteWriteChannel, boolean z, ObjectPool objectPool, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            objectPool = ByteBufferPoolKt.getKtorDefaultPool();
        }
        if ((i & 4) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return deflated(byteWriteChannel, z, objectPool, coroutineContext);
    }
}
