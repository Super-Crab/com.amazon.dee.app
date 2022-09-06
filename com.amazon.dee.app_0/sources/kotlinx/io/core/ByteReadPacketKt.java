package kotlinx.io.core;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ByteReadPacket.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0086\b¨\u0006\u0007"}, d2 = {"ByteReadPacket", "Lkotlinx/io/core/ByteReadPacket;", "array", "", "offset", "", "length", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteReadPacketKt {
    @NotNull
    public static final ByteReadPacket ByteReadPacket(@NotNull byte[] array, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        ByteBuffer wrap = ByteBuffer.wrap(array, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(wrap, "ByteBuffer.wrap(array, offset, length)");
        return ByteReadPacketExtensionsKt.ByteReadPacket(wrap, new ByteReadPacketKt$ByteReadPacket$$inlined$ByteReadPacket$1(array));
    }

    @NotNull
    public static /* synthetic */ ByteReadPacket ByteReadPacket$default(byte[] array, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = array.length;
        }
        Intrinsics.checkParameterIsNotNull(array, "array");
        ByteBuffer wrap = ByteBuffer.wrap(array, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(wrap, "ByteBuffer.wrap(array, offset, length)");
        return ByteReadPacketExtensionsKt.ByteReadPacket(wrap, new ByteReadPacketKt$ByteReadPacket$$inlined$ByteReadPacket$2(array));
    }
}
