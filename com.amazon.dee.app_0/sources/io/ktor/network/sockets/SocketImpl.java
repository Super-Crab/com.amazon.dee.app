package io.ktor.network.sockets;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.mode.debug.EmulateConnection;
import io.ktor.network.selector.SelectInterest;
import io.ktor.network.selector.SelectorManager;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SocketImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004B\u001d\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001b\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u000fH\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0012\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0002R\u0016\u0010\u0005\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lio/ktor/network/sockets/SocketImpl;", ExifInterface.LATITUDE_SOUTH, "Ljava/nio/channels/SocketChannel;", "Lio/ktor/network/sockets/NIOSocketImpl;", "Lio/ktor/network/sockets/Socket;", "channel", "socket", "Ljava/net/Socket;", "selector", "Lio/ktor/network/selector/SelectorManager;", "(Ljava/nio/channels/SocketChannel;Ljava/net/Socket;Lio/ktor/network/selector/SelectorManager;)V", "getChannel", "()Ljava/nio/channels/SocketChannel;", "Ljava/nio/channels/SocketChannel;", "localAddress", "Ljava/net/SocketAddress;", "getLocalAddress", "()Ljava/net/SocketAddress;", "remoteAddress", "getRemoteAddress", EmulateConnection.EXTRA_CONNECT, "target", "connect$ktor_network", "(Ljava/net/SocketAddress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "wantConnect", "", "state", "", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class SocketImpl<S extends SocketChannel> extends NIOSocketImpl<S> implements Socket {
    @NotNull
    private final S channel;
    private final java.net.Socket socket;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SocketImpl(@NotNull S channel, @NotNull java.net.Socket socket, @NotNull SelectorManager selector) {
        super(channel, selector, null);
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(socket, "socket");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        this.channel = channel;
        this.socket = socket;
        if (!mo10306getChannel().isBlocking()) {
            return;
        }
        throw new IllegalArgumentException("channel need to be configured as non-blocking".toString());
    }

    private final void wantConnect(boolean z) {
        interestOp(SelectInterest.CONNECT, z);
    }

    static /* synthetic */ void wantConnect$default(SocketImpl socketImpl, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        socketImpl.wantConnect(z);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0089  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object connect$ktor_network(@org.jetbrains.annotations.NotNull java.net.SocketAddress r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.ktor.network.sockets.Socket> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.network.sockets.SocketImpl$connect$1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.network.sockets.SocketImpl$connect$1 r0 = (io.ktor.network.sockets.SocketImpl$connect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.sockets.SocketImpl$connect$1 r0 = new io.ktor.network.sockets.SocketImpl$connect$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L54
            if (r2 == r4) goto L42
            if (r2 != r3) goto L3a
            java.lang.Object r7 = r0.L$1
            java.net.SocketAddress r7 = (java.net.SocketAddress) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.network.sockets.SocketImpl r2 = (io.ktor.network.sockets.SocketImpl) r2
            boolean r5 = r8 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L35
            goto L7a
        L35:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        L3a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L42:
            java.lang.Object r7 = r0.L$1
            java.net.SocketAddress r7 = (java.net.SocketAddress) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.network.sockets.SocketImpl r2 = (io.ktor.network.sockets.SocketImpl) r2
            boolean r5 = r8 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L4f
            goto L7a
        L4f:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        L54:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L9f
            java.nio.channels.SocketChannel r8 = r6.mo10306getChannel()
            boolean r8 = r8.connect(r7)
            if (r8 == 0) goto L63
            return r6
        L63:
            r6.wantConnect(r4)
            io.ktor.network.selector.SelectorManager r8 = r6.getSelector()
            io.ktor.network.selector.SelectInterest r2 = io.ktor.network.selector.SelectInterest.CONNECT
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r8 = r8.select(r6, r2, r0)
            if (r8 != r1) goto L79
            return r1
        L79:
            r2 = r6
        L7a:
            java.nio.channels.SocketChannel r8 = r2.mo10306getChannel()
            boolean r8 = r8.finishConnect()
            if (r8 == 0) goto L89
            r7 = 0
            r2.wantConnect(r7)
            return r2
        L89:
            r2.wantConnect(r4)
            io.ktor.network.selector.SelectorManager r8 = r2.getSelector()
            io.ktor.network.selector.SelectInterest r5 = io.ktor.network.selector.SelectInterest.CONNECT
            r0.L$0 = r2
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = r8.select(r2, r5, r0)
            if (r8 != r1) goto L7a
            return r1
        L9f:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.SocketImpl.connect$ktor_network(java.net.SocketAddress, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // io.ktor.network.sockets.ABoundSocket
    @NotNull
    public SocketAddress getLocalAddress() {
        SocketAddress localSocketAddress = this.socket.getLocalSocketAddress();
        Intrinsics.checkExpressionValueIsNotNull(localSocketAddress, "socket.localSocketAddress");
        return localSocketAddress;
    }

    @Override // io.ktor.network.sockets.AConnectedSocket
    @NotNull
    public SocketAddress getRemoteAddress() {
        SocketAddress remoteSocketAddress = this.socket.getRemoteSocketAddress();
        Intrinsics.checkExpressionValueIsNotNull(remoteSocketAddress, "socket.remoteSocketAddress");
        return remoteSocketAddress;
    }

    @Override // io.ktor.network.sockets.NIOSocketImpl, io.ktor.network.selector.SelectableBase, io.ktor.network.selector.Selectable
    @NotNull
    /* renamed from: getChannel  reason: collision with other method in class */
    public S mo10306getChannel() {
        return this.channel;
    }
}
