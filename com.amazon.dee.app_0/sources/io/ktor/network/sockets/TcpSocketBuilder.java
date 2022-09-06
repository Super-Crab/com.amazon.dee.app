package io.ktor.network.sockets;

import com.amazon.alexa.mode.debug.EmulateConnection;
import com.amazon.communication.remotesetting.StageSwitchService;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.Configurable;
import io.ktor.network.sockets.SocketOptions;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Builders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J-\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013J5\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013J4\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000e2\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ<\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001dR\u001a\u0010\u0005\u001a\u00020\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lio/ktor/network/sockets/TcpSocketBuilder;", "Lio/ktor/network/sockets/Configurable;", "Lio/ktor/network/sockets/SocketOptions;", "selector", "Lio/ktor/network/selector/SelectorManager;", "options", "(Lio/ktor/network/selector/SelectorManager;Lio/ktor/network/sockets/SocketOptions;)V", "getOptions", "()Lio/ktor/network/sockets/SocketOptions;", "setOptions", "(Lio/ktor/network/sockets/SocketOptions;)V", "bind", "Lio/ktor/network/sockets/ServerSocket;", "localAddress", "Ljava/net/SocketAddress;", "configure", "Lkotlin/Function1;", "Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;", "", "Lkotlin/ExtensionFunctionType;", StageSwitchService.CONFIG_HOSTNAME_KEY, "", "port", "", EmulateConnection.EXTRA_CONNECT, "Lio/ktor/network/sockets/Socket;", "remoteAddress", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "(Ljava/net/SocketAddress;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Ljava/lang/String;ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class TcpSocketBuilder implements Configurable<TcpSocketBuilder, SocketOptions> {
    @NotNull
    private SocketOptions options;
    private final SelectorManager selector;

    public TcpSocketBuilder(@NotNull SelectorManager selector, @NotNull SocketOptions options) {
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        Intrinsics.checkParameterIsNotNull(options, "options");
        this.selector = selector;
        this.options = options;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* synthetic */ ServerSocket bind$default(TcpSocketBuilder tcpSocketBuilder, String str, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "0.0.0.0";
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            function1 = TcpSocketBuilder$bind$1.INSTANCE;
        }
        return tcpSocketBuilder.bind(str, i, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static /* synthetic */ Object connect$default(TcpSocketBuilder tcpSocketBuilder, String str, int i, Function1 function1, Continuation continuation, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            function1 = TcpSocketBuilder$connect$2.INSTANCE;
        }
        return tcpSocketBuilder.connect(str, i, function1, continuation);
    }

    @NotNull
    public final ServerSocket bind(@NotNull String hostname, int i, @NotNull Function1<? super SocketOptions.AcceptorOptions, Unit> configure) {
        Intrinsics.checkParameterIsNotNull(hostname, "hostname");
        Intrinsics.checkParameterIsNotNull(configure, "configure");
        return bind(new InetSocketAddress(hostname, i), configure);
    }

    @Nullable
    public final Object connect(@NotNull String str, int i, @NotNull Function1<? super SocketOptions.TCPClientSocketOptions, Unit> function1, @NotNull Continuation<? super Socket> continuation) {
        return connect(new InetSocketAddress(str, i), function1, continuation);
    }

    @Override // io.ktor.network.sockets.Configurable
    @NotNull
    /* renamed from: getOptions */
    public SocketOptions mo10322getOptions() {
        return this.options;
    }

    @Override // io.ktor.network.sockets.Configurable
    public void setOptions(@NotNull SocketOptions socketOptions) {
        Intrinsics.checkParameterIsNotNull(socketOptions, "<set-?>");
        this.options = socketOptions;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* synthetic */ ServerSocket bind$default(TcpSocketBuilder tcpSocketBuilder, SocketAddress socketAddress, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            socketAddress = null;
        }
        if ((i & 2) != 0) {
            function1 = TcpSocketBuilder$bind$2.INSTANCE;
        }
        return tcpSocketBuilder.bind(socketAddress, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static /* synthetic */ Object connect$default(TcpSocketBuilder tcpSocketBuilder, SocketAddress socketAddress, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = TcpSocketBuilder$connect$4.INSTANCE;
        }
        return tcpSocketBuilder.connect(socketAddress, function1, continuation);
    }

    @NotNull
    public final ServerSocket bind(@Nullable SocketAddress socketAddress, @NotNull Function1<? super SocketOptions.AcceptorOptions, Unit> configure) {
        Intrinsics.checkParameterIsNotNull(configure, "configure");
        ServerSocketChannel openServerSocketChannel = this.selector.getProvider().openServerSocketChannel();
        try {
            SocketOptions.AcceptorOptions acceptor$ktor_network = mo10322getOptions().acceptor$ktor_network();
            configure.mo12165invoke(acceptor$ktor_network);
            JavaSocketOptionsKt.assignOptions(openServerSocketChannel, acceptor$ktor_network);
            BuildersKt.nonBlocking(openServerSocketChannel);
            Intrinsics.checkExpressionValueIsNotNull(openServerSocketChannel, "this");
            ServerSocketImpl serverSocketImpl = new ServerSocketImpl(openServerSocketChannel, this.selector);
            serverSocketImpl.mo10306getChannel().socket().bind(socketAddress);
            return serverSocketImpl;
        } catch (Throwable th) {
            openServerSocketChannel.close();
            throw th;
        }
    }

    @Override // io.ktor.network.sockets.Configurable
    @NotNull
    public TcpSocketBuilder configure(@NotNull Function1<? super SocketOptions, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        return (TcpSocketBuilder) Configurable.DefaultImpls.configure(this, block);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
    /* JADX WARN: Type inference failed for: r10v5, types: [java.io.Closeable] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object connect(@org.jetbrains.annotations.NotNull java.net.SocketAddress r9, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super io.ktor.network.sockets.SocketOptions.TCPClientSocketOptions, kotlin.Unit> r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.ktor.network.sockets.Socket> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof io.ktor.network.sockets.TcpSocketBuilder$connect$3
            if (r0 == 0) goto L13
            r0 = r11
            io.ktor.network.sockets.TcpSocketBuilder$connect$3 r0 = (io.ktor.network.sockets.TcpSocketBuilder$connect$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.sockets.TcpSocketBuilder$connect$3 r0 = new io.ktor.network.sockets.TcpSocketBuilder$connect$3
            r0.<init>(r8, r11)
        L18:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L5e
            if (r2 != r3) goto L56
            java.lang.Object r9 = r0.L$8
            io.ktor.network.sockets.SocketImpl r9 = (io.ktor.network.sockets.SocketImpl) r9
            java.lang.Object r9 = r0.L$7
            io.ktor.network.sockets.SocketImpl r9 = (io.ktor.network.sockets.SocketImpl) r9
            java.lang.Object r10 = r0.L$6
            io.ktor.network.sockets.SocketOptions$TCPClientSocketOptions r10 = (io.ktor.network.sockets.SocketOptions.TCPClientSocketOptions) r10
            java.lang.Object r10 = r0.L$5
            java.io.Closeable r10 = (java.io.Closeable) r10
            java.lang.Object r1 = r0.L$4
            java.nio.channels.SocketChannel r1 = (java.nio.channels.SocketChannel) r1
            java.lang.Object r1 = r0.L$3
            io.ktor.network.selector.SelectorManager r1 = (io.ktor.network.selector.SelectorManager) r1
            java.lang.Object r1 = r0.L$2
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
            java.lang.Object r1 = r0.L$1
            java.net.SocketAddress r1 = (java.net.SocketAddress) r1
            java.lang.Object r0 = r0.L$0
            io.ktor.network.sockets.TcpSocketBuilder r0 = (io.ktor.network.sockets.TcpSocketBuilder) r0
            boolean r0 = r11 instanceof kotlin.Result.Failure     // Catch: java.lang.Throwable -> L53
            if (r0 != 0) goto L4e
            goto Lad
        L4e:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11     // Catch: java.lang.Throwable -> L53
            java.lang.Throwable r9 = r11.exception     // Catch: java.lang.Throwable -> L53
            throw r9     // Catch: java.lang.Throwable -> L53
        L53:
            r9 = move-exception
            r2 = r10
            goto Laf
        L56:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L5e:
            boolean r2 = r11 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Lb3
            io.ktor.network.selector.SelectorManager r11 = r8.selector
            java.nio.channels.spi.SelectorProvider r2 = r11.getProvider()
            java.nio.channels.SocketChannel r2 = r2.openSocketChannel()
            io.ktor.network.sockets.SocketOptions r4 = r8.mo10322getOptions()     // Catch: java.lang.Throwable -> Lae
            io.ktor.network.sockets.SocketOptions$PeerSocketOptions r4 = r4.peer$ktor_network()     // Catch: java.lang.Throwable -> Lae
            io.ktor.network.sockets.SocketOptions$TCPClientSocketOptions r4 = r4.tcp$ktor_network()     // Catch: java.lang.Throwable -> Lae
            r10.mo12165invoke(r4)     // Catch: java.lang.Throwable -> Lae
            io.ktor.network.sockets.JavaSocketOptionsKt.assignOptions(r2, r4)     // Catch: java.lang.Throwable -> Lae
            io.ktor.network.sockets.BuildersKt.access$nonBlocking(r2)     // Catch: java.lang.Throwable -> Lae
            io.ktor.network.sockets.SocketImpl r5 = new io.ktor.network.sockets.SocketImpl     // Catch: java.lang.Throwable -> Lae
            java.net.Socket r6 = r2.socket()     // Catch: java.lang.Throwable -> Lae
            if (r6 != 0) goto L8c
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Throwable -> Lae
        L8c:
            io.ktor.network.selector.SelectorManager r7 = r8.selector     // Catch: java.lang.Throwable -> Lae
            r5.<init>(r2, r6, r7)     // Catch: java.lang.Throwable -> Lae
            r0.L$0 = r8     // Catch: java.lang.Throwable -> Lae
            r0.L$1 = r9     // Catch: java.lang.Throwable -> Lae
            r0.L$2 = r10     // Catch: java.lang.Throwable -> Lae
            r0.L$3 = r11     // Catch: java.lang.Throwable -> Lae
            r0.L$4 = r2     // Catch: java.lang.Throwable -> Lae
            r0.L$5 = r2     // Catch: java.lang.Throwable -> Lae
            r0.L$6 = r4     // Catch: java.lang.Throwable -> Lae
            r0.L$7 = r5     // Catch: java.lang.Throwable -> Lae
            r0.L$8 = r5     // Catch: java.lang.Throwable -> Lae
            r0.label = r3     // Catch: java.lang.Throwable -> Lae
            java.lang.Object r9 = r5.connect$ktor_network(r9, r0)     // Catch: java.lang.Throwable -> Lae
            if (r9 != r1) goto Lac
            return r1
        Lac:
            r9 = r5
        Lad:
            return r9
        Lae:
            r9 = move-exception
        Laf:
            r2.close()
            throw r9
        Lb3:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r9 = r11.exception
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.TcpSocketBuilder.connect(java.net.SocketAddress, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
