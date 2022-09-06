package kotlinx.io.core;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Builder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a,\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0086\b\u001a.\u0010\t\u001a\u0004\u0018\u0001H\n\"\u0004\b\u0000\u0010\n*\u0002H\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\f0\u0005H\u0082\b¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"buildPacket", "Lkotlinx/io/core/ByteReadPacket;", "headerSizeHint", "", "block", "Lkotlin/Function1;", "Lkotlinx/io/core/BytePacketBuilder;", "", "Lkotlin/ExtensionFunctionType;", "takeUnless", ExifInterface.GPS_DIRECTION_TRUE, "predicate", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class BuilderKt {
    @NotNull
    public static final ByteReadPacket buildPacket(int i, @NotNull Function1<? super BytePacketBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(i);
        try {
            block.mo12165invoke(BytePacketBuilder);
            return BytePacketBuilder.build();
        } catch (Throwable th) {
            BytePacketBuilder.release();
            throw th;
        }
    }

    @NotNull
    public static /* synthetic */ ByteReadPacket buildPacket$default(int i, Function1 block, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(block, "block");
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(i);
        try {
            block.mo12165invoke(BytePacketBuilder);
            return BytePacketBuilder.build();
        } catch (Throwable th) {
            BytePacketBuilder.release();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> T takeUnless(T t, Function1<? super T, Boolean> function1) {
        if (!function1.mo12165invoke(t).booleanValue()) {
            return t;
        }
        return null;
    }
}
