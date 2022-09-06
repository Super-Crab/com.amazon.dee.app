package io.ktor.network.sockets;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.SocketAddress;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.ByteReadPacket;
import org.jetbrains.annotations.NotNull;
/* compiled from: Datagram.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lio/ktor/network/sockets/Datagram;", "", "packet", "Lkotlinx/io/core/ByteReadPacket;", "address", "Ljava/net/SocketAddress;", "(Lkotlinx/io/core/ByteReadPacket;Ljava/net/SocketAddress;)V", "getAddress", "()Ljava/net/SocketAddress;", "getPacket", "()Lkotlinx/io/core/ByteReadPacket;", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class Datagram {
    @NotNull
    private final SocketAddress address;
    @NotNull
    private final ByteReadPacket packet;

    public Datagram(@NotNull ByteReadPacket packet, @NotNull SocketAddress address) {
        Intrinsics.checkParameterIsNotNull(packet, "packet");
        Intrinsics.checkParameterIsNotNull(address, "address");
        this.packet = packet;
        this.address = address;
        if (this.packet.m12336getRemaining() <= ((long) 65535)) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Datagram size limit exceeded: ");
        outline107.append(this.packet.m12336getRemaining());
        outline107.append(" of possible 65535");
        throw new IllegalArgumentException(outline107.toString().toString());
    }

    @NotNull
    public final SocketAddress getAddress() {
        return this.address;
    }

    @NotNull
    public final ByteReadPacket getPacket() {
        return this.packet;
    }
}
