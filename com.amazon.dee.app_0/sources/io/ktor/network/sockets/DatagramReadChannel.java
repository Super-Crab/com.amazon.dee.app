package io.ktor.network.sockets;

import com.amazon.deecomms.common.metrics.MetricKeys;
import io.ktor.util.KtorExperimentalAPI;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.channels.ReceiveChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Datagram.kt */
@KtorExperimentalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0011\u0010\u0007\u001a\u00020\u0004H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\bR\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Lio/ktor/network/sockets/DatagramReadChannel;", "", MetricKeys.VALUE_DIRECTION_INCOMING, "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lio/ktor/network/sockets/Datagram;", "getIncoming", "()Lkotlinx/coroutines/channels/ReceiveChannel;", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface DatagramReadChannel {

    /* compiled from: Datagram.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        @Nullable
        public static Object receive(DatagramReadChannel datagramReadChannel, @NotNull Continuation<? super Datagram> continuation) {
            return datagramReadChannel.getIncoming().receive(continuation);
        }
    }

    @NotNull
    ReceiveChannel<Datagram> getIncoming();

    @Nullable
    Object receive(@NotNull Continuation<? super Datagram> continuation);
}
