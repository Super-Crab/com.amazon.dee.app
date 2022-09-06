package io.ktor.network.sockets;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.network.selector.SelectorManager;
import java.nio.channels.SelectableChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Builders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0002\u001a#\u0010\u0007\u001a\u0002H\b\"\u0012\b\u0000\u0010\b*\f\u0012\u0004\u0012\u0002H\b\u0012\u0002\b\u00030\t*\u0002H\b¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"aSocket", "Lio/ktor/network/sockets/SocketBuilder;", "selector", "Lio/ktor/network/selector/SelectorManager;", "nonBlocking", "", "Ljava/nio/channels/SelectableChannel;", "tcpNoDelay", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/network/sockets/Configurable;", "(Lio/ktor/network/sockets/Configurable;)Lio/ktor/network/sockets/Configurable;", "ktor-network"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class BuildersKt {
    @NotNull
    public static final SocketBuilder aSocket(@NotNull SelectorManager selector) {
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        return new SocketBuilder(selector, SocketOptions.Companion.create$ktor_network());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void nonBlocking(@NotNull SelectableChannel selectableChannel) {
        selectableChannel.configureBlocking(false);
    }

    @NotNull
    public static final <T extends Configurable<? extends T, ?>> T tcpNoDelay(@NotNull T receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return (T) receiver$0.configure(BuildersKt$tcpNoDelay$1.INSTANCE);
    }
}
