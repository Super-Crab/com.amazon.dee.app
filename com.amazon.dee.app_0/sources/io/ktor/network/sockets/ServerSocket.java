package io.ktor.network.sockets;

import io.ktor.network.sockets.ASocket;
import kotlin.Metadata;
/* compiled from: Sockets.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003¨\u0006\u0005"}, d2 = {"Lio/ktor/network/sockets/ServerSocket;", "Lio/ktor/network/sockets/ASocket;", "Lio/ktor/network/sockets/ABoundSocket;", "Lio/ktor/network/sockets/Acceptable;", "Lio/ktor/network/sockets/Socket;", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface ServerSocket extends ASocket, ABoundSocket, Acceptable<Socket> {

    /* compiled from: Sockets.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static void dispose(ServerSocket serverSocket) {
            ASocket.DefaultImpls.dispose(serverSocket);
        }
    }
}
