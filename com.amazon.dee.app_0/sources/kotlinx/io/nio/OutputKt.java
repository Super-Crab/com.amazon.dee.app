package kotlinx.io.nio;

import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.IoBuffer;
import kotlinx.io.core.Output;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: Output.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0006"}, d2 = {"asOutput", "Lkotlinx/io/core/Output;", "Ljava/nio/channels/WritableByteChannel;", "pool", "Lkotlinx/io/pool/ObjectPool;", "Lkotlinx/io/core/IoBuffer;", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class OutputKt {
    @NotNull
    public static final Output asOutput(@NotNull WritableByteChannel receiver$0, @NotNull ObjectPool<IoBuffer> pool) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        return new ChannelAsOutput(pool, receiver$0);
    }

    @NotNull
    public static /* synthetic */ Output asOutput$default(WritableByteChannel writableByteChannel, ObjectPool objectPool, int i, Object obj) {
        if ((i & 1) != 0) {
            objectPool = IoBuffer.Companion.getPool();
        }
        return asOutput(writableByteChannel, objectPool);
    }
}
