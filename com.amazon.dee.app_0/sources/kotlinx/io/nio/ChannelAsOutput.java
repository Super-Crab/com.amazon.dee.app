package kotlinx.io.nio;

import com.amazon.alexa.presence.service.PresenceJobService;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.AbstractOutput;
import kotlinx.io.core.IoBuffer;
import kotlinx.io.internal.jvm.ErrorsKt;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: Output.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0014J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0004H\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lkotlinx/io/nio/ChannelAsOutput;", "Lkotlinx/io/core/AbstractOutput;", "pool", "Lkotlinx/io/pool/ObjectPool;", "Lkotlinx/io/core/IoBuffer;", "channel", "Ljava/nio/channels/WritableByteChannel;", "(Lkotlinx/io/pool/ObjectPool;Ljava/nio/channels/WritableByteChannel;)V", "getChannel", "()Ljava/nio/channels/WritableByteChannel;", "closeDestination", "", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "buffer", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class ChannelAsOutput extends AbstractOutput {
    @NotNull
    private final WritableByteChannel channel;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelAsOutput(@NotNull ObjectPool<IoBuffer> pool, @NotNull WritableByteChannel channel) {
        super(pool);
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        this.channel = channel;
    }

    @Override // kotlinx.io.core.AbstractOutput
    protected void closeDestination() {
        this.channel.close();
    }

    @Override // kotlinx.io.core.AbstractOutput
    protected void flush(@NotNull IoBuffer buffer) {
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        ByteBuffer byteBuffer = buffer.readBuffer;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        while (byteBuffer.hasRemaining()) {
            this.channel.write(byteBuffer);
        }
        int position2 = byteBuffer.position() - position;
        if (position2 >= 0) {
            if (byteBuffer.limit() == limit) {
                return;
            }
            ErrorsKt.limitChangeError();
            throw null;
        }
        ErrorsKt.negativeShiftError(position2);
        throw null;
    }

    @NotNull
    public final WritableByteChannel getChannel() {
        return this.channel;
    }
}
