package io.ktor.network.sockets;

import io.ktor.network.sockets.SocketOptions;
import java.net.DatagramSocket;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: JavaSocketOptions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {JavaSocketOptionsKt.SO_REUSEPORT, "", "assignOptions", "", "Ljava/nio/channels/SelectableChannel;", "options", "Lio/ktor/network/sockets/SocketOptions;", "ktor-network"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class JavaSocketOptionsKt {
    private static final String SO_REUSEPORT = "SO_REUSEPORT";

    public static final void assignOptions(@NotNull SelectableChannel receiver$0, @NotNull SocketOptions options) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(options, "options");
        boolean z = false;
        if (receiver$0 instanceof SocketChannel) {
            SocketChannel socketChannel = (SocketChannel) receiver$0;
            java.net.Socket socket = socketChannel.socket();
            if (socket == null) {
                Intrinsics.throwNpe();
            }
            if (!Intrinsics.areEqual(TypeOfService.m10313boximpl(options.getTypeOfService()), TypeOfService.m10313boximpl(TypeOfService.Companion.getUNDEFINED()))) {
                socket.setTrafficClass(options.getTypeOfService() & 255);
            }
            socket.setReuseAddress(options.getReuseAddress());
            if (options.getReusePort()) {
                SocketOptionsPlatformCapabilities.INSTANCE.setReusePort(socketChannel);
            }
            if (options instanceof SocketOptions.PeerSocketOptions) {
                SocketOptions.PeerSocketOptions peerSocketOptions = (SocketOptions.PeerSocketOptions) options;
                Integer valueOf = Integer.valueOf(peerSocketOptions.getReceiveBufferSize());
                if (!(valueOf.intValue() > 0)) {
                    valueOf = null;
                }
                if (valueOf != null) {
                    socket.setReceiveBufferSize(valueOf.intValue());
                }
                Integer valueOf2 = Integer.valueOf(peerSocketOptions.getSendBufferSize());
                if (!(valueOf2.intValue() > 0)) {
                    valueOf2 = null;
                }
                if (valueOf2 != null) {
                    socket.setSendBufferSize(valueOf2.intValue());
                }
            }
            if (options instanceof SocketOptions.TCPClientSocketOptions) {
                SocketOptions.TCPClientSocketOptions tCPClientSocketOptions = (SocketOptions.TCPClientSocketOptions) options;
                Integer valueOf3 = Integer.valueOf(tCPClientSocketOptions.getLingerSeconds());
                if (!(valueOf3.intValue() >= 0)) {
                    valueOf3 = null;
                }
                if (valueOf3 != null) {
                    socket.setSoLinger(true, valueOf3.intValue());
                }
                Boolean keepAlive = tCPClientSocketOptions.getKeepAlive();
                if (keepAlive != null) {
                    socket.setKeepAlive(keepAlive.booleanValue());
                }
                socket.setTcpNoDelay(tCPClientSocketOptions.getNoDelay());
            }
        }
        if (receiver$0 instanceof ServerSocketChannel) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) receiver$0;
            java.net.ServerSocket socket2 = serverSocketChannel.socket();
            if (socket2 == null) {
                Intrinsics.throwNpe();
            }
            if (options.getReuseAddress()) {
                socket2.setReuseAddress(true);
            }
            if (options.getReusePort()) {
                SocketOptionsPlatformCapabilities.INSTANCE.setReusePort(serverSocketChannel);
            }
        }
        if (receiver$0 instanceof DatagramChannel) {
            DatagramChannel datagramChannel = (DatagramChannel) receiver$0;
            DatagramSocket socket3 = datagramChannel.socket();
            if (socket3 == null) {
                Intrinsics.throwNpe();
            }
            if (!Intrinsics.areEqual(TypeOfService.m10313boximpl(options.getTypeOfService()), TypeOfService.m10313boximpl(TypeOfService.Companion.getUNDEFINED()))) {
                socket3.setTrafficClass(options.getTypeOfService() & 255);
            }
            if (options.getReuseAddress()) {
                socket3.setReuseAddress(true);
            }
            if (options.getReusePort()) {
                SocketOptionsPlatformCapabilities.INSTANCE.setReusePort(datagramChannel);
            }
            if (!(options instanceof SocketOptions.PeerSocketOptions)) {
                return;
            }
            SocketOptions.PeerSocketOptions peerSocketOptions2 = (SocketOptions.PeerSocketOptions) options;
            Integer valueOf4 = Integer.valueOf(peerSocketOptions2.getReceiveBufferSize());
            if (!(valueOf4.intValue() > 0)) {
                valueOf4 = null;
            }
            if (valueOf4 != null) {
                socket3.setReceiveBufferSize(valueOf4.intValue());
            }
            Integer valueOf5 = Integer.valueOf(peerSocketOptions2.getSendBufferSize());
            if (valueOf5.intValue() > 0) {
                z = true;
            }
            if (!z) {
                valueOf5 = null;
            }
            if (valueOf5 == null) {
                return;
            }
            socket3.setSendBufferSize(valueOf5.intValue());
        }
    }
}
