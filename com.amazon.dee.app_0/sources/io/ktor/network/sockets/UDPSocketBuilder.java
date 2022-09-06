package io.ktor.network.sockets;

import com.amazon.alexa.mode.debug.EmulateConnection;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.Configurable;
import io.ktor.network.sockets.SocketOptions;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Builders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J-\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0002\b\u0012J5\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000e2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0002\b\u0012R\u001a\u0010\u0005\u001a\u00020\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lio/ktor/network/sockets/UDPSocketBuilder;", "Lio/ktor/network/sockets/Configurable;", "Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "selector", "Lio/ktor/network/selector/SelectorManager;", "options", "(Lio/ktor/network/selector/SelectorManager;Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;)V", "getOptions", "()Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "setOptions", "(Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;)V", "bind", "Lio/ktor/network/sockets/BoundDatagramSocket;", "localAddress", "Ljava/net/SocketAddress;", "configure", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", EmulateConnection.EXTRA_CONNECT, "Lio/ktor/network/sockets/ConnectedDatagramSocket;", "remoteAddress", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class UDPSocketBuilder implements Configurable<UDPSocketBuilder, SocketOptions.UDPSocketOptions> {
    @NotNull
    private SocketOptions.UDPSocketOptions options;
    private final SelectorManager selector;

    public UDPSocketBuilder(@NotNull SelectorManager selector, @NotNull SocketOptions.UDPSocketOptions options) {
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        Intrinsics.checkParameterIsNotNull(options, "options");
        this.selector = selector;
        this.options = options;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* synthetic */ BoundDatagramSocket bind$default(UDPSocketBuilder uDPSocketBuilder, SocketAddress socketAddress, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            socketAddress = null;
        }
        if ((i & 2) != 0) {
            function1 = UDPSocketBuilder$bind$1.INSTANCE;
        }
        return uDPSocketBuilder.bind(socketAddress, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* synthetic */ ConnectedDatagramSocket connect$default(UDPSocketBuilder uDPSocketBuilder, SocketAddress socketAddress, SocketAddress socketAddress2, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            socketAddress2 = null;
        }
        if ((i & 4) != 0) {
            function1 = UDPSocketBuilder$connect$1.INSTANCE;
        }
        return uDPSocketBuilder.connect(socketAddress, socketAddress2, function1);
    }

    @NotNull
    public final BoundDatagramSocket bind(@Nullable SocketAddress socketAddress, @NotNull Function1<? super SocketOptions.UDPSocketOptions, Unit> configure) {
        Intrinsics.checkParameterIsNotNull(configure, "configure");
        DatagramChannel openDatagramChannel = this.selector.getProvider().openDatagramChannel();
        try {
            SocketOptions.UDPSocketOptions udp$ktor_network = mo10322getOptions().udp$ktor_network();
            configure.mo12165invoke(udp$ktor_network);
            JavaSocketOptionsKt.assignOptions(openDatagramChannel, udp$ktor_network);
            BuildersKt.nonBlocking(openDatagramChannel);
            Intrinsics.checkExpressionValueIsNotNull(openDatagramChannel, "this");
            DatagramSocketImpl datagramSocketImpl = new DatagramSocketImpl(openDatagramChannel, this.selector);
            datagramSocketImpl.mo10306getChannel().socket().bind(socketAddress);
            return datagramSocketImpl;
        } catch (Throwable th) {
            openDatagramChannel.close();
            throw th;
        }
    }

    @NotNull
    public final ConnectedDatagramSocket connect(@NotNull SocketAddress remoteAddress, @Nullable SocketAddress socketAddress, @NotNull Function1<? super SocketOptions.UDPSocketOptions, Unit> configure) {
        Intrinsics.checkParameterIsNotNull(remoteAddress, "remoteAddress");
        Intrinsics.checkParameterIsNotNull(configure, "configure");
        DatagramChannel openDatagramChannel = this.selector.getProvider().openDatagramChannel();
        try {
            SocketOptions.UDPSocketOptions udp$ktor_network = mo10322getOptions().udp$ktor_network();
            configure.mo12165invoke(udp$ktor_network);
            JavaSocketOptionsKt.assignOptions(openDatagramChannel, udp$ktor_network);
            BuildersKt.nonBlocking(openDatagramChannel);
            Intrinsics.checkExpressionValueIsNotNull(openDatagramChannel, "this");
            DatagramSocketImpl datagramSocketImpl = new DatagramSocketImpl(openDatagramChannel, this.selector);
            datagramSocketImpl.mo10306getChannel().socket().bind(socketAddress);
            datagramSocketImpl.mo10306getChannel().connect(remoteAddress);
            return datagramSocketImpl;
        } catch (Throwable th) {
            openDatagramChannel.close();
            throw th;
        }
    }

    @Override // io.ktor.network.sockets.Configurable
    @NotNull
    public UDPSocketBuilder configure(@NotNull Function1<? super SocketOptions.UDPSocketOptions, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        return (UDPSocketBuilder) Configurable.DefaultImpls.configure(this, block);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.ktor.network.sockets.Configurable
    @NotNull
    /* renamed from: getOptions */
    public SocketOptions.UDPSocketOptions mo10322getOptions() {
        return this.options;
    }

    @Override // io.ktor.network.sockets.Configurable
    public void setOptions(@NotNull SocketOptions.UDPSocketOptions uDPSocketOptions) {
        Intrinsics.checkParameterIsNotNull(uDPSocketOptions, "<set-?>");
        this.options = uDPSocketOptions;
    }
}
