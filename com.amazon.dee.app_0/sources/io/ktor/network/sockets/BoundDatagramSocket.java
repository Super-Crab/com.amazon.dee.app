package io.ktor.network.sockets;

import io.ktor.network.sockets.ASocket;
import io.ktor.network.sockets.DatagramReadWriteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Datagram.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004¨\u0006\u0005"}, d2 = {"Lio/ktor/network/sockets/BoundDatagramSocket;", "Lio/ktor/network/sockets/ASocket;", "Lio/ktor/network/sockets/ABoundSocket;", "Lio/ktor/network/sockets/AReadable;", "Lio/ktor/network/sockets/DatagramReadWriteChannel;", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface BoundDatagramSocket extends ASocket, ABoundSocket, AReadable, DatagramReadWriteChannel {

    /* compiled from: Datagram.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static void dispose(BoundDatagramSocket boundDatagramSocket) {
            ASocket.DefaultImpls.dispose(boundDatagramSocket);
        }

        @Nullable
        public static Object receive(BoundDatagramSocket boundDatagramSocket, @NotNull Continuation<? super Datagram> continuation) {
            return DatagramReadWriteChannel.DefaultImpls.receive(boundDatagramSocket, continuation);
        }

        @Nullable
        public static Object send(BoundDatagramSocket boundDatagramSocket, @NotNull Datagram datagram, @NotNull Continuation<? super Unit> continuation) {
            return DatagramReadWriteChannel.DefaultImpls.send(boundDatagramSocket, datagram, continuation);
        }
    }
}
