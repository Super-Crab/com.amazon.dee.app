package io.ktor.network.sockets;

import io.ktor.network.sockets.DatagramReadChannel;
import io.ktor.network.sockets.DatagramWriteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Datagram.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/network/sockets/DatagramReadWriteChannel;", "Lio/ktor/network/sockets/DatagramReadChannel;", "Lio/ktor/network/sockets/DatagramWriteChannel;", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface DatagramReadWriteChannel extends DatagramReadChannel, DatagramWriteChannel {

    /* compiled from: Datagram.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        @Nullable
        public static Object receive(DatagramReadWriteChannel datagramReadWriteChannel, @NotNull Continuation<? super Datagram> continuation) {
            return DatagramReadChannel.DefaultImpls.receive(datagramReadWriteChannel, continuation);
        }

        @Nullable
        public static Object send(DatagramReadWriteChannel datagramReadWriteChannel, @NotNull Datagram datagram, @NotNull Continuation<? super Unit> continuation) {
            return DatagramWriteChannel.DefaultImpls.send(datagramReadWriteChannel, datagram, continuation);
        }
    }
}
