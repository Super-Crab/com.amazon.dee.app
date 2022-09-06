package kotlinx.io.core;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.internal.jvm.ErrorsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteBuffers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0001\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0004H\u0001\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0001\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0001\u001a\u0016\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0004H\u0001\u001a\u0014\u0010\t\u001a\u00020\n*\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0004H\u0001\u001a\u0016\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0004H\u0001\u001a\u0016\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004H\u0001\u001a\u001d\u0010\f\u001a\u00020\u0004*\u00020\u00072\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u0004H\u0082\u0010\u001a\u0012\u0010\u000f\u001a\u00020\u0004*\u00020\u00072\u0006\u0010\u0010\u001a\u00020\n\u001a)\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00042\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0013H\u0086\b\u001a)\u0010\u0011\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00042\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0013H\u0086\b\u001a)\u0010\u0011\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00042\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0013H\u0087\b\u001a\u0012\u0010\u0014\u001a\u00020\u0004*\u00020\u00072\u0006\u0010\u0010\u001a\u00020\n\u001a)\u0010\u0015\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00042\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0013H\u0086\b\u001a\u0014\u0010\u0016\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0017\u001a\u00020\nH\u0007¨\u0006\u0018"}, d2 = {"afterNioBufferUsed", "", "Lkotlinx/io/core/AbstractInput;", "read", "", "Lkotlinx/io/core/BytePacketBuilder;", "written", "Lkotlinx/io/core/ByteReadPacket;", "Lkotlinx/io/core/ByteReadPacketBase;", "nioBuffer", "Ljava/nio/ByteBuffer;", "size", "readAsMuchAsPossible", "bb", "copied", "readAvailable", "dst", "readDirect", "block", "Lkotlin/Function1;", "readFully", "writeDirect", "writeFully", "src", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteBuffersKt {
    @PublishedApi
    public static final void afterNioBufferUsed(@NotNull BytePacketBuilder receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        IoBuffer head = receiver$0.getHead();
        if (i >= 0 && i <= head.getWriteRemaining()) {
            head.readBuffer.limit(head.writeBuffer.position());
            receiver$0.addSize(i);
            return;
        }
        ErrorsKt.wrongBufferPositionChangeError(i, receiver$0.getSize());
        throw null;
    }

    @PublishedApi
    @NotNull
    public static final ByteBuffer nioBuffer(@NotNull BytePacketBuilder receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.prepareWriteHead(i).writeBuffer;
    }

    private static final int readAsMuchAsPossible(@NotNull ByteReadPacket byteReadPacket, ByteBuffer byteBuffer, int i) {
        IoBuffer prepareRead;
        while (byteBuffer.hasRemaining() && (prepareRead = byteReadPacket.prepareRead(1, byteReadPacket.getHead())) != null) {
            int remaining = byteBuffer.remaining();
            int readRemaining = prepareRead.getReadRemaining();
            if (remaining >= readRemaining) {
                prepareRead.readFully(byteBuffer, readRemaining);
                byteReadPacket.releaseHead$kotlinx_io(prepareRead);
                i += readRemaining;
            } else {
                prepareRead.readFully(byteBuffer, remaining);
                byteReadPacket.updateHeadRemaining(prepareRead.getReadRemaining());
                return i + remaining;
            }
        }
        return i;
    }

    public static final int readAvailable(@NotNull ByteReadPacket receiver$0, @NotNull ByteBuffer dst) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        return readAsMuchAsPossible(receiver$0, dst, 0);
    }

    public static final void readDirect(@NotNull ByteReadPacket receiver$0, int i, @NotNull Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        ByteBuffer nioBuffer = nioBuffer(receiver$0, i);
        if (nioBuffer != null) {
            int position = nioBuffer.position();
            try {
                block.mo12165invoke(nioBuffer);
            } finally {
                InlineMarker.finallyStart(1);
                afterNioBufferUsed(receiver$0, nioBuffer.position() - position);
                InlineMarker.finallyEnd(1);
            }
        }
    }

    public static final int readFully(@NotNull ByteReadPacket receiver$0, @NotNull ByteBuffer dst) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int readAsMuchAsPossible = readAsMuchAsPossible(receiver$0, dst, 0);
        if (!dst.hasRemaining()) {
            return readAsMuchAsPossible;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Not enough data in packet to fill buffer: ");
        outline107.append(dst.remaining());
        outline107.append(" more bytes required");
        throw new EOFException(outline107.toString());
    }

    public static final void writeDirect(@NotNull BytePacketBuilder receiver$0, int i, @NotNull Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        ByteBuffer nioBuffer = nioBuffer(receiver$0, i);
        int position = nioBuffer.position();
        block.mo12165invoke(nioBuffer);
        afterNioBufferUsed(receiver$0, nioBuffer.position() - position);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Should be resolved to member function instead")
    public static final /* synthetic */ void writeFully(@NotNull BytePacketBuilder receiver$0, @NotNull ByteBuffer src) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(src, "src");
        receiver$0.writeFully(src);
    }

    @PublishedApi
    @Nullable
    public static final ByteBuffer nioBuffer(@NotNull ByteReadPacket receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        IoBuffer prepareRead = receiver$0.prepareRead(i, receiver$0.getHead());
        if (prepareRead != null) {
            return prepareRead.writeBuffer;
        }
        return null;
    }

    @PublishedApi
    @Nullable
    public static final ByteBuffer nioBuffer(@NotNull AbstractInput receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        IoBuffer prepareRead = receiver$0.prepareRead(i, receiver$0.getHead());
        if (prepareRead != null) {
            return prepareRead.writeBuffer;
        }
        return null;
    }

    @PublishedApi
    @Nullable
    public static final ByteBuffer nioBuffer(@NotNull ByteReadPacketBase receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        IoBuffer prepareRead = receiver$0.prepareRead(i, receiver$0.getHead());
        if (prepareRead != null) {
            return prepareRead.writeBuffer;
        }
        return null;
    }

    @PublishedApi
    public static final void afterNioBufferUsed(@NotNull ByteReadPacket receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        afterNioBufferUsed((ByteReadPacketBase) receiver$0, i);
    }

    @PublishedApi
    public static final void afterNioBufferUsed(@NotNull AbstractInput receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        afterNioBufferUsed((ByteReadPacketBase) receiver$0, i);
    }

    public static final void readDirect(@NotNull AbstractInput receiver$0, int i, @NotNull Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        ByteBuffer nioBuffer = nioBuffer(receiver$0, i);
        if (nioBuffer != null) {
            int position = nioBuffer.position();
            try {
                block.mo12165invoke(nioBuffer);
            } finally {
                InlineMarker.finallyStart(1);
                afterNioBufferUsed(receiver$0, nioBuffer.position() - position);
                InlineMarker.finallyEnd(1);
            }
        }
    }

    @PublishedApi
    public static final void afterNioBufferUsed(@NotNull ByteReadPacketBase receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        int headRemaining = receiver$0.getHeadRemaining();
        if (i >= 0 && headRemaining >= i) {
            receiver$0.setHeadRemaining(headRemaining - i);
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("read count shouldn't be negative: ", i).toString());
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Removed")
    public static final /* synthetic */ void readDirect(@NotNull ByteReadPacketBase receiver$0, int i, @NotNull Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        ByteBuffer nioBuffer = nioBuffer(receiver$0, i);
        if (nioBuffer != null) {
            int position = nioBuffer.position();
            try {
                block.mo12165invoke(nioBuffer);
            } finally {
                InlineMarker.finallyStart(1);
                afterNioBufferUsed(receiver$0, nioBuffer.position() - position);
                InlineMarker.finallyEnd(1);
            }
        }
    }
}
