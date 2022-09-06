package kotlinx.io.nio;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.BytePacketBuilder;
import kotlinx.io.core.ByteReadPacket;
import kotlinx.io.core.IoBuffer;
import kotlinx.io.core.PacketJVMKt;
import kotlinx.io.internal.jvm.ErrorsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Channels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\t\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\n\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b\u001a\u001c\u0010\u000b\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0002\u001a\u0012\u0010\u000e\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004\u001a%\u0010\u0010\u001a\u0004\u0018\u00010\u0006*\u00020\u000f2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012¢\u0006\u0002\b\u0015\u001a\u0012\u0010\u0010\u001a\u00020\u0016*\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0006¨\u0006\u0018"}, d2 = {"read", "", "Ljava/nio/channels/ReadableByteChannel;", "buffer", "Lkotlinx/io/core/IoBuffer;", "readPacketAtLeast", "Lkotlinx/io/core/ByteReadPacket;", JsonReportFormat.COUNT, "", "readPacketAtMost", "readPacketExact", "readPacketImpl", ReactProperties.GEOFENCE_MINIMUM_RANGE, ReactProperties.GEOFENCE_MAXIMUM_RANGE, "write", "Ljava/nio/channels/WritableByteChannel;", "writePacket", "builder", "Lkotlin/Function1;", "Lkotlinx/io/core/BytePacketBuilder;", "", "Lkotlin/ExtensionFunctionType;", "", "p", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ChannelsKt {
    public static final int read(@NotNull ReadableByteChannel receiver$0, @NotNull IoBuffer buffer) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        if (buffer.getWriteRemaining() == 0) {
            return 0;
        }
        int read = receiver$0.read(buffer.writeBuffer);
        buffer.readBuffer.limit(buffer.writeBuffer.position());
        return read;
    }

    @NotNull
    public static final ByteReadPacket readPacketAtLeast(@NotNull ReadableByteChannel receiver$0, long j) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return readPacketImpl(receiver$0, j, Long.MAX_VALUE);
    }

    @NotNull
    public static final ByteReadPacket readPacketAtMost(@NotNull ReadableByteChannel receiver$0, long j) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return readPacketImpl(receiver$0, 1L, j);
    }

    @NotNull
    public static final ByteReadPacket readPacketExact(@NotNull ReadableByteChannel receiver$0, long j) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return readPacketImpl(receiver$0, j, j);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x003f, code lost:
        return new kotlinx.io.core.ByteReadPacket(r9, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00c6, code lost:
        kotlinx.io.internal.jvm.ErrorsKt.wrongBufferPositionChangeError(r8, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00cb, code lost:
        throw null;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061 A[Catch: all -> 0x00fa, TryCatch #1 {all -> 0x00fa, blocks: (B:25:0x0046, B:41:0x0070, B:42:0x0074, B:47:0x0080, B:49:0x0094, B:50:0x009c, B:52:0x00a5, B:36:0x0061, B:39:0x006c), top: B:75:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0070 A[Catch: all -> 0x00fa, TryCatch #1 {all -> 0x00fa, blocks: (B:25:0x0046, B:41:0x0070, B:42:0x0074, B:47:0x0080, B:49:0x0094, B:50:0x009c, B:52:0x00a5, B:36:0x0061, B:39:0x006c), top: B:75:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0080 A[Catch: all -> 0x00fa, TryCatch #1 {all -> 0x00fa, blocks: (B:25:0x0046, B:41:0x0070, B:42:0x0074, B:47:0x0080, B:49:0x0094, B:50:0x009c, B:52:0x00a5, B:36:0x0061, B:39:0x006c), top: B:75:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00ec A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final kotlinx.io.core.ByteReadPacket readPacketImpl(@org.jetbrains.annotations.NotNull java.nio.channels.ReadableByteChannel r18, long r19, long r21) {
        /*
            Method dump skipped, instructions count: 297
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.nio.ChannelsKt.readPacketImpl(java.nio.channels.ReadableByteChannel, long, long):kotlinx.io.core.ByteReadPacket");
    }

    public static final int write(@NotNull WritableByteChannel receiver$0, @NotNull IoBuffer buffer) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        return receiver$0.write(buffer.readBuffer);
    }

    @Nullable
    public static final ByteReadPacket writePacket(@NotNull WritableByteChannel receiver$0, @NotNull Function1<? super BytePacketBuilder, Unit> builder) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(0);
        try {
            builder.mo12165invoke(BytePacketBuilder);
            ByteReadPacket build = BytePacketBuilder.build();
            try {
                if (!writePacket(receiver$0, build)) {
                    return build;
                }
                return null;
            } catch (Throwable th) {
                build.release();
                throw th;
            }
        } catch (Throwable th2) {
            BytePacketBuilder.release();
            throw th2;
        }
    }

    public static final boolean writePacket(@NotNull WritableByteChannel receiver$0, @NotNull ByteReadPacket p) {
        int i;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(p, "p");
        do {
            try {
                IoBuffer head = p.getHead();
                int readRemaining = head.getReadRemaining();
                if (readRemaining < 1) {
                    head = p.prepareRead(1, head);
                    readRemaining = head != null ? head.getReadRemaining() : 0;
                }
                if (head != null) {
                    ByteBuffer byteBuffer = head.readBuffer;
                    int position = byteBuffer.position();
                    int limit = byteBuffer.limit();
                    i = receiver$0.write(byteBuffer);
                    int position2 = byteBuffer.position() - position;
                    if (position2 >= 0) {
                        if (byteBuffer.limit() == limit) {
                            int readRemaining2 = head.getReadRemaining();
                            int i2 = readRemaining - readRemaining2;
                            if (i2 > 0) {
                                p.setHeadRemaining(p.getHeadRemaining() - i2);
                            }
                            if (readRemaining2 == 0) {
                                p.ensureNext(head);
                            }
                        } else {
                            ErrorsKt.limitChangeError();
                            throw null;
                        }
                    } else {
                        ErrorsKt.negativeShiftError(position2);
                        throw null;
                    }
                } else {
                    i = 0;
                }
                if (p.isEmpty()) {
                    return true;
                }
            } catch (Throwable th) {
                p.release();
                throw th;
            }
        } while (i != 0);
        return false;
    }
}
