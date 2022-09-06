package kotlinx.io.core;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import java.nio.ByteBuffer;
import java.nio.charset.CharsetDecoder;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.charsets.CharsetJVMKt;
import kotlinx.io.core.Input;
import kotlinx.io.utils.AtomicKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: PacketJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a\u001e\u0010\u0007\u001a\u00020\b*\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\f\u001a*\u0010\r\u001a\u00020\u0001*\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0001H\u0007\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003*\n\u0010\u0014\"\u00020\u00152\u00020\u0015¨\u0006\u0016"}, d2 = {"PACKET_MAX_COPY_SIZE", "", "getPACKET_MAX_COPY_SIZE", "()I", "BytePacketBuilder", "Lkotlinx/io/core/BytePacketBuilder;", "headerSizeHint", "readByteBuffer", "Ljava/nio/ByteBuffer;", "Lkotlinx/io/core/ByteReadPacket;", JsonReportFormat.COUNT, "direct", "", "readText", "decoder", "Ljava/nio/charset/CharsetDecoder;", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", ReactProperties.GEOFENCE_MAXIMUM_RANGE, "EOFException", "Ljava/io/EOFException;", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class PacketJVMKt {
    private static final int PACKET_MAX_COPY_SIZE = AtomicKt.getIOIntProperty("max.copy.size", 500);

    @NotNull
    public static final BytePacketBuilder BytePacketBuilder(int i) {
        return new BytePacketBuilder(i, IoBuffer.Companion.getPool());
    }

    @NotNull
    public static /* synthetic */ BytePacketBuilder BytePacketBuilder$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return BytePacketBuilder(i);
    }

    public static final int getPACKET_MAX_COPY_SIZE() {
        return PACKET_MAX_COPY_SIZE;
    }

    @NotNull
    public static final ByteBuffer readByteBuffer(@NotNull ByteReadPacket receiver$0, int i, boolean z) {
        ByteBuffer allocate;
        String str;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (z) {
            allocate = ByteBuffer.allocateDirect(i);
            str = "ByteBuffer.allocateDirect(n)";
        } else {
            allocate = ByteBuffer.allocate(i);
            str = "ByteBuffer.allocate(n)";
        }
        Intrinsics.checkExpressionValueIsNotNull(allocate, str);
        Input.DefaultImpls.readFully$default(receiver$0, allocate, 0, 2, (Object) null);
        allocate.clear();
        return allocate;
    }

    @NotNull
    public static /* synthetic */ ByteBuffer readByteBuffer$default(ByteReadPacket byteReadPacket, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            long m12336getRemaining = byteReadPacket.m12336getRemaining();
            if (m12336getRemaining > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("Unable to make a ByteBuffer: packet is too big");
            }
            i = (int) m12336getRemaining;
        }
        if ((i2 & 2) != 0) {
            z = false;
        }
        return readByteBuffer(byteReadPacket, i, z);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Migrate parameters order", replaceWith = @ReplaceWith(expression = "readText(out, decoder, max)", imports = {}))
    public static final int readText(@NotNull ByteReadPacket receiver$0, @NotNull CharsetDecoder decoder, @NotNull Appendable out, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(out, "out");
        return CharsetJVMKt.decode(decoder, receiver$0, out, i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Migrate parameters order", replaceWith = @ReplaceWith(expression = "readText(out, decoder, max)", imports = {}))
    public static /* synthetic */ int readText$default(ByteReadPacket byteReadPacket, CharsetDecoder charsetDecoder, Appendable appendable, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readText(byteReadPacket, charsetDecoder, appendable, i);
    }
}
