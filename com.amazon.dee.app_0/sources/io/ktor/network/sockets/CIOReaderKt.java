package io.ktor.network.sockets;

import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectorManager;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.io.ByteChannel;
import kotlinx.coroutines.io.CoroutinesKt;
import kotlinx.coroutines.io.WriterJob;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: CIOReader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000\u001a:\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0000¨\u0006\u000f"}, d2 = {"attachForReadingDirectImpl", "Lkotlinx/coroutines/io/WriterJob;", "Lkotlinx/coroutines/CoroutineScope;", "channel", "Lkotlinx/coroutines/io/ByteChannel;", "nioChannel", "Ljava/nio/channels/ReadableByteChannel;", "selectable", "Lio/ktor/network/selector/Selectable;", "selector", "Lio/ktor/network/selector/SelectorManager;", "attachForReadingImpl", "pool", "Lkotlinx/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "ktor-network"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class CIOReaderKt {
    @NotNull
    public static final WriterJob attachForReadingDirectImpl(@NotNull CoroutineScope receiver$0, @NotNull ByteChannel channel, @NotNull ReadableByteChannel nioChannel, @NotNull Selectable selectable, @NotNull SelectorManager selector) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(nioChannel, "nioChannel");
        Intrinsics.checkParameterIsNotNull(selectable, "selectable");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        return CoroutinesKt.writer(receiver$0, Dispatchers.getUnconfined(), channel, new CIOReaderKt$attachForReadingDirectImpl$1(selectable, channel, nioChannel, selector, null));
    }

    @NotNull
    public static final WriterJob attachForReadingImpl(@NotNull CoroutineScope receiver$0, @NotNull ByteChannel channel, @NotNull ReadableByteChannel nioChannel, @NotNull Selectable selectable, @NotNull SelectorManager selector, @NotNull ObjectPool<ByteBuffer> pool) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(nioChannel, "nioChannel");
        Intrinsics.checkParameterIsNotNull(selectable, "selectable");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        return CoroutinesKt.writer(receiver$0, Dispatchers.getUnconfined(), channel, new CIOReaderKt$attachForReadingImpl$1(nioChannel, pool.mo12351borrow(), channel, selectable, selector, pool, null));
    }
}
