package io.ktor.util.cio;

import io.ktor.util.KtorExperimentalAPI;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.ByteWriteChannel;
import kotlinx.coroutines.io.CoroutinesKt;
import kotlinx.coroutines.io.ReaderScope;
import kotlinx.coroutines.io.WriterScope;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: FileChannels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u001c\u0010\b\u001a\u00020\t*\u00020\u00022\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0007¨\u0006\r"}, d2 = {"readChannel", "Lkotlinx/coroutines/io/ByteReadChannel;", "Ljava/io/File;", "start", "", "endInclusive", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "writeChannel", "Lkotlinx/coroutines/io/ByteWriteChannel;", "pool", "Lkotlinx/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class FileChannelsKt {
    @KtorExperimentalAPI
    @NotNull
    public static final ByteReadChannel readChannel(@NotNull File receiver$0, long j, long j2, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        return CoroutinesKt.writer(CoroutineScopeKt.CoroutineScope(coroutineContext), coroutineContext, false, (Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object>) new FileChannelsKt$readChannel$1(j, j2, receiver$0.length(), new RandomAccessFile(receiver$0, "r"), null)).mo12310getChannel();
    }

    @KtorExperimentalAPI
    @NotNull
    public static /* synthetic */ ByteReadChannel readChannel$default(File file, long j, long j2, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 0;
        }
        long j3 = j;
        if ((i & 2) != 0) {
            j2 = -1;
        }
        long j4 = j2;
        if ((i & 4) != 0) {
            coroutineContext = Dispatchers.getIO();
        }
        return readChannel(file, j3, j4, coroutineContext);
    }

    @KtorExperimentalAPI
    @NotNull
    public static final ByteWriteChannel writeChannel(@NotNull File receiver$0, @NotNull ObjectPool<ByteBuffer> pool) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        return CoroutinesKt.reader((CoroutineScope) GlobalScope.INSTANCE, (CoroutineContext) Dispatchers.getUnconfined(), true, (Function2<? super ReaderScope, ? super Continuation<? super Unit>, ? extends Object>) new FileChannelsKt$writeChannel$1(receiver$0, pool, null)).mo12310getChannel();
    }

    @KtorExperimentalAPI
    @NotNull
    public static /* synthetic */ ByteWriteChannel writeChannel$default(File file, ObjectPool objectPool, int i, Object obj) {
        if ((i & 1) != 0) {
            objectPool = ByteBufferPoolKt.getKtorDefaultPool();
        }
        return writeChannel(file, objectPool);
    }
}
