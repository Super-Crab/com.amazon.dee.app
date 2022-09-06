package kotlinx.io.core;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: Packet.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\u0005"}, d2 = {"setByteOrderForNonEmpty", "", "Lkotlinx/io/core/IoBuffer;", "newByteOrder", "Lkotlinx/io/core/ByteOrder;", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class PacketKt {
    public static final /* synthetic */ void access$setByteOrderForNonEmpty(IoBuffer ioBuffer, ByteOrder byteOrder) {
        setByteOrderForNonEmpty(ioBuffer, byteOrder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setByteOrderForNonEmpty(@NotNull IoBuffer ioBuffer, ByteOrder byteOrder) {
        if (ioBuffer.canRead()) {
            ioBuffer.setByteOrder(byteOrder);
        }
    }
}
