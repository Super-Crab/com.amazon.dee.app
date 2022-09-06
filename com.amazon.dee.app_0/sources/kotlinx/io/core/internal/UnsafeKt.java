package kotlinx.io.core.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.BytePacketBuilder;
import kotlinx.io.core.BytePacketBuilderBase;
import kotlinx.io.core.ByteReadPacket;
import kotlinx.io.core.ByteReadPacketBase;
import kotlinx.io.core.Input;
import kotlinx.io.core.InputKt;
import kotlinx.io.core.IoBuffer;
import kotlinx.io.core.Output;
import kotlinx.io.core.OutputKt;
import kotlinx.io.core.PacketJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Unsafe.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a\u0014\u0010\t\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\u0014\u0010\n\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a\u0014\u0010\f\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\u0016\u0010\r\u001a\u0004\u0018\u00010\b*\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\u0016\u0010\u000e\u001a\u0004\u0018\u00010\b*\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007\u001a\u0016\u0010\u0011\u001a\u0004\u0018\u00010\b*\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a\u0016\u0010\u0012\u001a\u0004\u0018\u00010\b*\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a\u001e\u0010\u0013\u001a\u00020\b*\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00102\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007\u001a\u0016\u0010\u0015\u001a\u00020\b*\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002¨\u0006\u0016"}, d2 = {"$unsafeAppend$", "", "Lkotlinx/io/core/ByteReadPacket;", "builder", "Lkotlinx/io/core/BytePacketBuilder;", "afterHeadWrite", "Lkotlinx/io/core/Output;", "current", "Lkotlinx/io/core/IoBuffer;", "afterWriteHeadFallback", "completeReadHead", "Lkotlinx/io/core/Input;", "completeReadHeadFallback", "prepareNextReadHeadFallback", "prepareReadFirstHead", "minSize", "", "prepareReadHeadFallback", "prepareReadNextHead", "prepareWriteHead", "capacity", "prepareWriteHeadFallback", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class UnsafeKt {
    @DangerousInternalIoApi
    public static final void $unsafeAppend$(@NotNull ByteReadPacket receiver$0, @NotNull BytePacketBuilder builder) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        int size = builder.getSize();
        IoBuffer head = builder.getHead();
        if (size <= PacketJVMKt.getPACKET_MAX_COPY_SIZE() && head.getNext() == null && receiver$0.tryWriteAppend$kotlinx_io(head)) {
            builder.afterBytesStolen$kotlinx_io();
            return;
        }
        IoBuffer stealAll$kotlinx_io = builder.stealAll$kotlinx_io();
        if (stealAll$kotlinx_io == null) {
            return;
        }
        receiver$0.append$kotlinx_io(stealAll$kotlinx_io);
    }

    @DangerousInternalIoApi
    public static final void afterHeadWrite(@NotNull Output receiver$0, @NotNull IoBuffer current) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(current, "current");
        if (receiver$0 instanceof BytePacketBuilderBase) {
            ((BytePacketBuilderBase) receiver$0).afterHeadWrite();
        } else {
            afterWriteHeadFallback(receiver$0, current);
        }
    }

    private static final void afterWriteHeadFallback(@NotNull Output output, IoBuffer ioBuffer) {
        OutputKt.writeFully$default(output, ioBuffer, 0, 2, null);
        ioBuffer.release(IoBuffer.Companion.getPool());
    }

    @DangerousInternalIoApi
    public static final void completeReadHead(@NotNull Input receiver$0, @NotNull IoBuffer current) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(current, "current");
        if (current == receiver$0) {
            return;
        }
        if (receiver$0 instanceof ByteReadPacketBase) {
            int readRemaining = current.getReadRemaining();
            if (readRemaining == 0) {
                ((ByteReadPacketBase) receiver$0).ensureNext(current);
                return;
            } else if (current.getEndGap() < IoBuffer.Companion.getReservedSize()) {
                ((ByteReadPacketBase) receiver$0).fixGapAfterRead(current);
                return;
            } else {
                ((ByteReadPacketBase) receiver$0).updateHeadRemaining(readRemaining);
                return;
            }
        }
        completeReadHeadFallback(receiver$0, current);
    }

    private static final void completeReadHeadFallback(@NotNull Input input, IoBuffer ioBuffer) {
        InputKt.discardExact(input, (ioBuffer.getCapacity() - ioBuffer.getWriteRemaining()) - ioBuffer.getReadRemaining());
        ioBuffer.release(IoBuffer.Companion.getPool());
    }

    private static final IoBuffer prepareNextReadHeadFallback(@NotNull Input input, IoBuffer ioBuffer) {
        InputKt.discardExact(input, (ioBuffer.getCapacity() - ioBuffer.getWriteRemaining()) - ioBuffer.getReadRemaining());
        ioBuffer.resetForWrite();
        if (input.getEndOfInput() || input.peekTo(ioBuffer) <= 0) {
            ioBuffer.release(IoBuffer.Companion.getPool());
            return null;
        }
        return ioBuffer;
    }

    @DangerousInternalIoApi
    @Nullable
    public static final IoBuffer prepareReadFirstHead(@NotNull Input receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (receiver$0 instanceof ByteReadPacketBase) {
            return ((ByteReadPacketBase) receiver$0).prepareReadHead(i);
        }
        if (receiver$0 instanceof IoBuffer) {
            IoBuffer ioBuffer = (IoBuffer) receiver$0;
            if (!ioBuffer.canRead()) {
                return null;
            }
            return ioBuffer;
        }
        return prepareReadHeadFallback(receiver$0, i);
    }

    private static final IoBuffer prepareReadHeadFallback(@NotNull Input input, int i) {
        if (input.getEndOfInput()) {
            return null;
        }
        IoBuffer mo12351borrow = IoBuffer.Companion.getPool().mo12351borrow();
        if (input.peekTo(mo12351borrow) >= i) {
            return mo12351borrow;
        }
        mo12351borrow.release(IoBuffer.Companion.getPool());
        return null;
    }

    @DangerousInternalIoApi
    @Nullable
    public static final IoBuffer prepareReadNextHead(@NotNull Input receiver$0, @NotNull IoBuffer current) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(current, "current");
        if (current == receiver$0) {
            IoBuffer ioBuffer = (IoBuffer) receiver$0;
            if (!ioBuffer.canRead()) {
                return null;
            }
            return ioBuffer;
        } else if (receiver$0 instanceof ByteReadPacketBase) {
            return ((ByteReadPacketBase) receiver$0).ensureNextHead(current);
        } else {
            return prepareNextReadHeadFallback(receiver$0, current);
        }
    }

    @DangerousInternalIoApi
    @NotNull
    public static final IoBuffer prepareWriteHead(@NotNull Output receiver$0, int i, @Nullable IoBuffer ioBuffer) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (receiver$0 instanceof BytePacketBuilderBase) {
            return ((BytePacketBuilderBase) receiver$0).prepareWriteHead(i);
        }
        return prepareWriteHeadFallback(receiver$0, ioBuffer);
    }

    private static final IoBuffer prepareWriteHeadFallback(@NotNull Output output, IoBuffer ioBuffer) {
        if (ioBuffer != null) {
            OutputKt.writeFully$default(output, ioBuffer, 0, 2, null);
            ioBuffer.resetForWrite();
            return ioBuffer;
        }
        return IoBuffer.Companion.getPool().mo12351borrow();
    }
}
