package io.ktor.network.util;

import io.ktor.util.InternalAPI;
import java.nio.ByteBuffer;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: Pools.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\"\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\t\u001a\u00020\n8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\r\"\u001c\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0004\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"DefaultDatagramByteBufferPool", "Lkotlinx/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "DefaultDatagramByteBufferPool$annotations", "()V", "getDefaultDatagramByteBufferPool", "()Lkotlinx/io/pool/ObjectPool;", "cpuCount", "", "ioCoroutineDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "ioCoroutineDispatcher$annotations", "getIoCoroutineDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "ioThreadGroup", "Ljava/lang/ThreadGroup;", "ioThreadGroup$annotations", "getIoThreadGroup", "()Ljava/lang/ThreadGroup;", "ktor-network"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class PoolsKt {
    @NotNull
    private static final ThreadGroup ioThreadGroup = new ThreadGroup("io-pool-group");
    private static final int cpuCount = Runtime.getRuntime().availableProcessors();
    @NotNull
    private static final ObjectPool<ByteBuffer> DefaultDatagramByteBufferPool = new DirectByteBufferPool(65535, 2048);

    @InternalAPI
    public static /* synthetic */ void DefaultDatagramByteBufferPool$annotations() {
    }

    @NotNull
    public static final ObjectPool<ByteBuffer> getDefaultDatagramByteBufferPool() {
        return DefaultDatagramByteBufferPool;
    }

    @NotNull
    public static final CoroutineDispatcher getIoCoroutineDispatcher() {
        return Dispatchers.getIO();
    }

    @NotNull
    public static final ThreadGroup getIoThreadGroup() {
        return ioThreadGroup;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Dispatchers.IO instead for both blocking and non-blocking I/O", replaceWith = @ReplaceWith(expression = "Dispatchers.IO", imports = {"kotlinx.coroutines.Dispatchers"}))
    public static /* synthetic */ void ioCoroutineDispatcher$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is going to be removed")
    public static /* synthetic */ void ioThreadGroup$annotations() {
    }
}
