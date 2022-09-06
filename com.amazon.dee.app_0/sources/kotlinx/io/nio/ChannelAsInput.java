package kotlinx.io.nio;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectableChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.AbstractInput;
import kotlinx.io.core.Input;
import kotlinx.io.core.IoBuffer;
import kotlinx.io.core.internal.RequireFailureCapture;
import kotlinx.io.internal.jvm.ErrorsKt;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Input.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0014J\n\u0010\u000b\u001a\u0004\u0018\u00010\u0007H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/io/nio/ChannelAsInput;", "Lkotlinx/io/core/AbstractInput;", "Lkotlinx/io/core/Input;", "channel", "Ljava/nio/channels/ReadableByteChannel;", "pool", "Lkotlinx/io/pool/ObjectPool;", "Lkotlinx/io/core/IoBuffer;", "(Ljava/nio/channels/ReadableByteChannel;Lkotlinx/io/pool/ObjectPool;)V", "closeSource", "", "fill", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class ChannelAsInput extends AbstractInput implements Input {
    private final ReadableByteChannel channel;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelAsInput(@NotNull ReadableByteChannel channel, @NotNull ObjectPool<IoBuffer> pool) {
        super(null, 0L, pool, 3, null);
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        this.channel = channel;
        ReadableByteChannel readableByteChannel = this.channel;
        if (!(readableByteChannel instanceof SelectableChannel) || !((SelectableChannel) readableByteChannel).isBlocking()) {
            return;
        }
        throw new IllegalArgumentException("Non-blocking channels are not supported".toString());
    }

    @Override // kotlinx.io.core.AbstractInput, kotlinx.io.core.ByteReadPacketBase
    protected void closeSource() {
        this.channel.close();
    }

    @Override // kotlinx.io.core.AbstractInput, kotlinx.io.core.ByteReadPacketBase
    @Nullable
    /* renamed from: fill */
    protected IoBuffer mo12334fill() {
        IoBuffer mo12351borrow = getPool().mo12351borrow();
        mo12351borrow.reserveEndGap(IoBuffer.Companion.getReservedSize());
        try {
            final int writeRemaining = mo12351borrow.getWriteRemaining();
            if (1 <= writeRemaining) {
                ByteBuffer byteBuffer = mo12351borrow.writeBuffer;
                int position = byteBuffer.position();
                int read = this.channel.read(byteBuffer);
                int position2 = byteBuffer.position() - position;
                if (position2 >= 0 && position2 <= writeRemaining) {
                    mo12351borrow.readBuffer.limit(mo12351borrow.writeBuffer.position());
                    if (read != -1) {
                        return mo12351borrow;
                    }
                    return null;
                }
                ErrorsKt.wrongBufferPositionChangeError(position2, 1);
                throw null;
            }
            new RequireFailureCapture() { // from class: kotlinx.io.nio.ChannelAsInput$writeDirect$$inlined$require$1
                @Override // kotlinx.io.core.internal.RequireFailureCapture
                @NotNull
                public Void doFail() {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("size ");
                    outline107.append(r1);
                    outline107.append(" is greater than buffer's remaining capacity ");
                    outline107.append(writeRemaining);
                    throw new IllegalArgumentException(outline107.toString());
                }
            }.doFail();
            throw null;
        } finally {
            mo12351borrow.release(getPool());
        }
    }
}
