package kotlinx.coroutines.io.jvm.javaio;

import com.amazon.alexa.location.utils.MetricsUtil;
import java.io.InputStream;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.ByteWriteChannel;
import kotlinx.coroutines.io.CoroutinesKt;
import kotlinx.coroutines.io.WriterScope;
import kotlinx.io.core.ExperimentalIoApi;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Reading.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a$\u0010\u0007\u001a\u00020\b*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0007\u001a+\u0010\u0007\u001a\u00020\b*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u000e0\fH\u0007¢\u0006\u0002\b\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"copyTo", "", "Ljava/io/InputStream;", "channel", "Lkotlinx/coroutines/io/ByteWriteChannel;", MetricsUtil.LegacyMetricTypes.LIMIT, "(Ljava/io/InputStream;Lkotlinx/coroutines/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toByteReadChannel", "Lkotlinx/coroutines/io/ByteReadChannel;", "context", "Lkotlin/coroutines/CoroutineContext;", "pool", "Lkotlinx/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "", "toByteReadChannelWithArrayPool", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ReadingKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0090 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x00a6 -> B:41:0x00a9). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyTo(@org.jetbrains.annotations.NotNull java.io.InputStream r19, @org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteWriteChannel r20, long r21, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Long> r23) {
        /*
            Method dump skipped, instructions count: 214
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.jvm.javaio.ReadingKt.copyTo(java.io.InputStream, kotlinx.coroutines.io.ByteWriteChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static /* synthetic */ Object copyTo$default(InputStream inputStream, ByteWriteChannel byteWriteChannel, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyTo(inputStream, byteWriteChannel, j, continuation);
    }

    @ExperimentalIoApi
    @NotNull
    public static final ByteReadChannel toByteReadChannel(@NotNull InputStream receiver$0, @NotNull CoroutineContext context, @NotNull ObjectPool<ByteBuffer> pool) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        return CoroutinesKt.writer((CoroutineScope) GlobalScope.INSTANCE, context, true, (Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object>) new ReadingKt$toByteReadChannel$1(receiver$0, pool, null)).mo12310getChannel();
    }

    @ExperimentalIoApi
    @NotNull
    public static /* synthetic */ ByteReadChannel toByteReadChannel$default(InputStream inputStream, CoroutineContext coroutineContext, ObjectPool objectPool, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getIO();
        }
        return toByteReadChannel(inputStream, coroutineContext, objectPool);
    }

    @ExperimentalIoApi
    @JvmName(name = "toByteReadChannelWithArrayPool")
    @NotNull
    public static final ByteReadChannel toByteReadChannelWithArrayPool(@NotNull InputStream receiver$0, @NotNull CoroutineContext context, @NotNull ObjectPool<byte[]> pool) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        return CoroutinesKt.writer((CoroutineScope) GlobalScope.INSTANCE, context, true, (Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object>) new ReadingKt$toByteReadChannel$2(receiver$0, pool, null)).mo12310getChannel();
    }

    @ExperimentalIoApi
    @JvmName(name = "toByteReadChannelWithArrayPool")
    @NotNull
    public static /* synthetic */ ByteReadChannel toByteReadChannelWithArrayPool$default(InputStream inputStream, CoroutineContext coroutineContext, ObjectPool objectPool, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getIO();
        }
        if ((i & 2) != 0) {
            objectPool = ByteArrayPoolKt.getByteArrayPool();
        }
        return toByteReadChannelWithArrayPool(inputStream, coroutineContext, objectPool);
    }
}
