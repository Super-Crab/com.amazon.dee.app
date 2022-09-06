package io.ktor.network.sockets;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.network.sockets.SocketOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: Builders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0012\b\u0000\u0010\u0002*\f\u0012\u0004\u0012\u0002H\u0002\u0012\u0002\b\u00030\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/network/sockets/Configurable;", "Lio/ktor/network/sockets/SocketOptions;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class BuildersKt$tcpNoDelay$1 extends Lambda implements Function1<SocketOptions, Unit> {
    public static final BuildersKt$tcpNoDelay$1 INSTANCE = new BuildersKt$tcpNoDelay$1();

    BuildersKt$tcpNoDelay$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(SocketOptions socketOptions) {
        invoke2(socketOptions);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull SocketOptions receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (receiver$0 instanceof SocketOptions.TCPClientSocketOptions) {
            ((SocketOptions.TCPClientSocketOptions) receiver$0).setNoDelay(true);
        }
    }
}
