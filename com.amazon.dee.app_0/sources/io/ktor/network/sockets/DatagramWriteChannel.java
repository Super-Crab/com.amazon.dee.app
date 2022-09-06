package io.ktor.network.sockets;

import com.amazon.deecomms.common.metrics.MetricKeys;
import io.ktor.util.KtorExperimentalAPI;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.channels.SendChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Datagram.kt */
@KtorExperimentalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\nR\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"Lio/ktor/network/sockets/DatagramWriteChannel;", "", MetricKeys.VALUE_DIRECTION_OUTGOING, "Lkotlinx/coroutines/channels/SendChannel;", "Lio/ktor/network/sockets/Datagram;", "getOutgoing", "()Lkotlinx/coroutines/channels/SendChannel;", "send", "", "datagram", "(Lio/ktor/network/sockets/Datagram;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface DatagramWriteChannel {

    /* compiled from: Datagram.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        @Nullable
        public static Object send(DatagramWriteChannel datagramWriteChannel, @NotNull Datagram datagram, @NotNull Continuation<? super Unit> continuation) {
            return datagramWriteChannel.getOutgoing().send(datagram, continuation);
        }
    }

    @NotNull
    SendChannel<Datagram> getOutgoing();

    @Nullable
    Object send(@NotNull Datagram datagram, @NotNull Continuation<? super Unit> continuation);
}
