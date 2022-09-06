package kotlinx.io.nio;

import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.Input;
import kotlinx.io.core.IoBuffer;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: Input.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0006"}, d2 = {"asInput", "Lkotlinx/io/core/Input;", "Ljava/nio/channels/ReadableByteChannel;", "pool", "Lkotlinx/io/pool/ObjectPool;", "Lkotlinx/io/core/IoBuffer;", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class InputKt {
    @NotNull
    public static final Input asInput(@NotNull ReadableByteChannel receiver$0, @NotNull ObjectPool<IoBuffer> pool) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        return new ChannelAsInput(receiver$0, pool);
    }

    @NotNull
    public static /* synthetic */ Input asInput$default(ReadableByteChannel readableByteChannel, ObjectPool objectPool, int i, Object obj) {
        if ((i & 1) != 0) {
            objectPool = IoBuffer.Companion.getPool();
        }
        return asInput(readableByteChannel, objectPool);
    }
}
