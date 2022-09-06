package kotlinx.io.streams;

import com.amazon.alexa.presence.service.PresenceJobService;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.AbstractOutput;
import kotlinx.io.core.Input;
import kotlinx.io.core.IoBuffer;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: Output.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0014J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0004H\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/io/streams/OutputStreamAdapter;", "Lkotlinx/io/core/AbstractOutput;", "pool", "Lkotlinx/io/pool/ObjectPool;", "Lkotlinx/io/core/IoBuffer;", "stream", "Ljava/io/OutputStream;", "(Lkotlinx/io/pool/ObjectPool;Ljava/io/OutputStream;)V", "closeDestination", "", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "buffer", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class OutputStreamAdapter extends AbstractOutput {
    private final OutputStream stream;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OutputStreamAdapter(@NotNull ObjectPool<IoBuffer> pool, @NotNull OutputStream stream) {
        super(pool);
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        Intrinsics.checkParameterIsNotNull(stream, "stream");
        this.stream = stream;
    }

    @Override // kotlinx.io.core.AbstractOutput
    protected void closeDestination() {
        this.stream.close();
    }

    @Override // kotlinx.io.core.AbstractOutput
    protected void flush(@NotNull IoBuffer buffer) {
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        byte[] mo12351borrow = ByteArraysKt.getByteArrayPool().mo12351borrow();
        while (buffer.canRead()) {
            try {
                int readAvailable$default = kotlinx.io.core.InputKt.readAvailable$default((Input) buffer, mo12351borrow, 0, 0, 6, (Object) null);
                if (readAvailable$default > 0) {
                    this.stream.write(mo12351borrow, 0, readAvailable$default);
                }
            } finally {
                ByteArraysKt.getByteArrayPool().recycle(mo12351borrow);
            }
        }
    }
}
