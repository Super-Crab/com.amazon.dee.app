package io.ktor.network.sockets;

import io.ktor.network.sockets.ASocket;
import io.ktor.network.sockets.DatagramReadWriteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Datagram.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005¨\u0006\u0006"}, d2 = {"Lio/ktor/network/sockets/ConnectedDatagramSocket;", "Lio/ktor/network/sockets/ASocket;", "Lio/ktor/network/sockets/ABoundSocket;", "Lio/ktor/network/sockets/AConnectedSocket;", "Lio/ktor/network/sockets/ReadWriteSocket;", "Lio/ktor/network/sockets/DatagramReadWriteChannel;", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface ConnectedDatagramSocket extends ASocket, ABoundSocket, AConnectedSocket, ReadWriteSocket, DatagramReadWriteChannel {

    /* compiled from: Datagram.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static void dispose(ConnectedDatagramSocket connectedDatagramSocket) {
            ASocket.DefaultImpls.dispose(connectedDatagramSocket);
        }

        @Nullable
        public static Object receive(ConnectedDatagramSocket connectedDatagramSocket, @NotNull Continuation<? super Datagram> continuation) {
            return DatagramReadWriteChannel.DefaultImpls.receive(connectedDatagramSocket, continuation);
        }

        @Nullable
        public static Object send(ConnectedDatagramSocket connectedDatagramSocket, @NotNull Datagram datagram, @NotNull Continuation<? super Unit> continuation) {
            return DatagramReadWriteChannel.DefaultImpls.send(connectedDatagramSocket, datagram, continuation);
        }
    }
}
