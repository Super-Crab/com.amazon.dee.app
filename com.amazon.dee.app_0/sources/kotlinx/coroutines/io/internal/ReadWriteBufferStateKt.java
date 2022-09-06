package kotlinx.coroutines.io.internal;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ReadWriteBufferState.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u000e\u0010\b\u001a\u00020\tX\u0080T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"EmptyByteBuffer", "Ljava/nio/ByteBuffer;", "getEmptyByteBuffer", "()Ljava/nio/ByteBuffer;", "EmptyCapacity", "Lkotlinx/coroutines/io/internal/RingBufferCapacity;", "getEmptyCapacity", "()Lkotlinx/coroutines/io/internal/RingBufferCapacity;", "RESERVED_SIZE", "", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ReadWriteBufferStateKt {
    @NotNull
    private static final ByteBuffer EmptyByteBuffer;
    @NotNull
    private static final RingBufferCapacity EmptyCapacity;
    public static final int RESERVED_SIZE = 8;

    static {
        ByteBuffer allocate = ByteBuffer.allocate(0);
        Intrinsics.checkExpressionValueIsNotNull(allocate, "ByteBuffer.allocate(0)");
        EmptyByteBuffer = allocate;
        EmptyCapacity = new RingBufferCapacity(0);
    }

    @NotNull
    public static final ByteBuffer getEmptyByteBuffer() {
        return EmptyByteBuffer;
    }

    @NotNull
    public static final RingBufferCapacity getEmptyCapacity() {
        return EmptyCapacity;
    }
}
