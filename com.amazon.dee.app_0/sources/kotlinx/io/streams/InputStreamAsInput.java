package kotlinx.io.streams;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.AbstractInput;
import kotlinx.io.core.IoBuffer;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Input.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0014J\n\u0010\n\u001a\u0004\u0018\u00010\u0006H\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlinx/io/streams/InputStreamAsInput;", "Lkotlinx/io/core/AbstractInput;", "stream", "Ljava/io/InputStream;", "pool", "Lkotlinx/io/pool/ObjectPool;", "Lkotlinx/io/core/IoBuffer;", "(Ljava/io/InputStream;Lkotlinx/io/pool/ObjectPool;)V", "closeSource", "", "fill", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class InputStreamAsInput extends AbstractInput {
    private final InputStream stream;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InputStreamAsInput(@NotNull InputStream stream, @NotNull ObjectPool<IoBuffer> pool) {
        super(null, 0L, pool, 3, null);
        Intrinsics.checkParameterIsNotNull(stream, "stream");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        this.stream = stream;
    }

    @Override // kotlinx.io.core.AbstractInput, kotlinx.io.core.ByteReadPacketBase
    protected void closeSource() {
        this.stream.close();
    }

    @Override // kotlinx.io.core.AbstractInput, kotlinx.io.core.ByteReadPacketBase
    @Nullable
    /* renamed from: fill */
    protected IoBuffer mo12334fill() {
        IoBuffer ioBuffer;
        byte[] mo12351borrow = ByteArraysKt.getByteArrayPool().mo12351borrow();
        try {
            int read = this.stream.read(mo12351borrow, 0, 4096 - IoBuffer.Companion.getReservedSize());
            if (read >= 0) {
                IoBuffer mo12351borrow2 = getPool().mo12351borrow();
                IoBuffer ioBuffer2 = mo12351borrow2;
                ioBuffer2.reserveEndGap(IoBuffer.Companion.getReservedSize());
                ioBuffer2.writeFully(mo12351borrow, 0, read);
                ioBuffer = mo12351borrow2;
            } else {
                ioBuffer = null;
            }
            return ioBuffer;
        } finally {
            ByteArraysKt.getByteArrayPool().recycle(mo12351borrow);
        }
    }
}
