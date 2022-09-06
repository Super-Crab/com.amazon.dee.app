package kotlinx.io.core;

import java.nio.ByteBuffer;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.internal.DangerousInternalIoApi;
import kotlinx.io.core.internal.UnsafeKt;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: BytePacketBuilderJVM.kt */
@Deprecated(level = DeprecationLevel.ERROR, message = "Will be removed in future releases.")
@DangerousInternalIoApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0015\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lkotlinx/io/core/BytePacketBuilderPlatformBase;", "Lkotlinx/io/core/BytePacketBuilderBase;", "pool", "Lkotlinx/io/pool/ObjectPool;", "Lkotlinx/io/core/IoBuffer;", "(Lkotlinx/io/pool/ObjectPool;)V", "writeFully", "", "bb", "Ljava/nio/ByteBuffer;", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public abstract class BytePacketBuilderPlatformBase extends BytePacketBuilderBase {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BytePacketBuilderPlatformBase(@NotNull ObjectPool<IoBuffer> pool) {
        super(pool);
        Intrinsics.checkParameterIsNotNull(pool, "pool");
    }

    @Override // kotlinx.io.core.Output
    public void writeFully(@NotNull ByteBuffer bb) {
        Intrinsics.checkParameterIsNotNull(bb, "bb");
        int limit = bb.limit();
        IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(this, 1, null);
        while (true) {
            try {
                bb.limit(bb.position() + Math.min(bb.remaining(), prepareWriteHead.getWriteRemaining()));
                prepareWriteHead.writeFully(bb);
                bb.limit(limit);
                if (!bb.hasRemaining()) {
                    return;
                }
                prepareWriteHead = UnsafeKt.prepareWriteHead(this, 1, prepareWriteHead);
            } finally {
                UnsafeKt.afterHeadWrite(this, prepareWriteHead);
            }
        }
    }
}
