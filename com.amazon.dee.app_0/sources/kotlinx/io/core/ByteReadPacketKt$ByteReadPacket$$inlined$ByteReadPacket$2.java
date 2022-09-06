package kotlinx.io.core;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: ByteReadPacketExtensions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "it", "Ljava/nio/ByteBuffer;", "invoke", "kotlinx/io/core/ByteReadPacketExtensionsKt$ByteReadPacket$1"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteReadPacketKt$ByteReadPacket$$inlined$ByteReadPacket$2 extends Lambda implements Function1<ByteBuffer, Unit> {
    final /* synthetic */ byte[] $array;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteReadPacketKt$ByteReadPacket$$inlined$ByteReadPacket$2(byte[] bArr) {
        super(1);
        this.$array = bArr;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(ByteBuffer byteBuffer) {
        invoke2(byteBuffer);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull ByteBuffer it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
    }
}
