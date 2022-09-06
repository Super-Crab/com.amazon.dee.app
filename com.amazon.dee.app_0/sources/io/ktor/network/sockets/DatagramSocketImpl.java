package io.ktor.network.sockets;

import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import io.ktor.network.selector.SelectInterest;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.BoundDatagramSocket;
import io.ktor.network.util.PoolsKt;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.io.core.BytePacketBuilder;
import kotlinx.io.core.ByteReadPacket;
import kotlinx.io.core.Input;
import kotlinx.io.core.PacketJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DatagramSocketImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0011\u0010 \u001a\u00020\rH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010!J\u0019\u0010\"\u001a\u00020\r2\u0006\u0010#\u001a\u00020$H\u0082Pø\u0001\u0000¢\u0006\u0002\u0010%J\u0019\u0010&\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\rH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(J!\u0010)\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020$2\u0006\u0010*\u001a\u00020\u0011H\u0082Pø\u0001\u0000¢\u0006\u0002\u0010+R\u0014\u0010\u0005\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0013R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"Lio/ktor/network/sockets/DatagramSocketImpl;", "Lio/ktor/network/sockets/BoundDatagramSocket;", "Lio/ktor/network/sockets/ConnectedDatagramSocket;", "Lio/ktor/network/sockets/NIOSocketImpl;", "Ljava/nio/channels/DatagramChannel;", "channel", "selector", "Lio/ktor/network/selector/SelectorManager;", "(Ljava/nio/channels/DatagramChannel;Lio/ktor/network/selector/SelectorManager;)V", "getChannel", "()Ljava/nio/channels/DatagramChannel;", MetricKeys.VALUE_DIRECTION_INCOMING, "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lio/ktor/network/sockets/Datagram;", "getIncoming", "()Lkotlinx/coroutines/channels/ReceiveChannel;", "localAddress", "Ljava/net/SocketAddress;", "getLocalAddress", "()Ljava/net/SocketAddress;", MetricKeys.VALUE_DIRECTION_OUTGOING, "Lkotlinx/coroutines/channels/SendChannel;", "getOutgoing", "()Lkotlinx/coroutines/channels/SendChannel;", Constants.BUNDLE_RECEIVER_TAG, "remoteAddress", "getRemoteAddress", "sender", "socket", "Ljava/net/DatagramSocket;", "close", "", "receiveImpl", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveSuspend", "buffer", "Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendImpl", "datagram", "(Lio/ktor/network/sockets/Datagram;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSuspend", "address", "(Ljava/nio/ByteBuffer;Ljava/net/SocketAddress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class DatagramSocketImpl extends NIOSocketImpl<DatagramChannel> implements BoundDatagramSocket, ConnectedDatagramSocket {
    @NotNull
    private final DatagramChannel channel;
    private final ReceiveChannel<Datagram> receiver;
    private final SendChannel<Datagram> sender;
    private final DatagramSocket socket;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DatagramSocketImpl(@NotNull DatagramChannel channel, @NotNull SelectorManager selector) {
        super(channel, selector, PoolsKt.getDefaultDatagramByteBufferPool());
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        this.channel = channel;
        DatagramSocket socket = mo10306getChannel().socket();
        if (socket == null) {
            Intrinsics.throwNpe();
        }
        this.socket = socket;
        this.sender = ActorKt.actor$default(this, Dispatchers.getIO(), 0, null, null, new DatagramSocketImpl$sender$1(this, null), 14, null);
        this.receiver = ProduceKt.produce$default(this, Dispatchers.getIO(), 0, new DatagramSocketImpl$receiver$1(this, null), 2, null);
    }

    @Override // io.ktor.network.sockets.NIOSocketImpl, io.ktor.network.selector.SelectableBase, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.receiver.cancel();
        SendChannel.DefaultImpls.close$default(this.sender, null, 1, null);
        super.close();
    }

    @Override // io.ktor.network.sockets.NIOSocketImpl, io.ktor.network.selector.SelectableBase, io.ktor.network.selector.Selectable
    @NotNull
    /* renamed from: getChannel */
    public DatagramChannel mo10306getChannel() {
        return this.channel;
    }

    @Override // io.ktor.network.sockets.DatagramReadChannel
    @NotNull
    public ReceiveChannel<Datagram> getIncoming() {
        return this.receiver;
    }

    @Override // io.ktor.network.sockets.ABoundSocket
    @NotNull
    public SocketAddress getLocalAddress() {
        SocketAddress localSocketAddress = this.socket.getLocalSocketAddress();
        if (localSocketAddress != null) {
            return localSocketAddress;
        }
        throw new IllegalStateException("Channel is not yet bound");
    }

    @Override // io.ktor.network.sockets.DatagramWriteChannel
    @NotNull
    public SendChannel<Datagram> getOutgoing() {
        return this.sender;
    }

    @Override // io.ktor.network.sockets.AConnectedSocket
    @NotNull
    public SocketAddress getRemoteAddress() {
        SocketAddress remoteSocketAddress = this.socket.getRemoteSocketAddress();
        if (remoteSocketAddress != null) {
            return remoteSocketAddress;
        }
        throw new IllegalStateException("Channel is not yet connected");
    }

    @Override // io.ktor.network.sockets.DatagramReadChannel
    @Nullable
    public Object receive(@NotNull Continuation<? super Datagram> continuation) {
        return BoundDatagramSocket.DefaultImpls.receive(this, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final /* synthetic */ Object receiveImpl(@NotNull Continuation<? super Datagram> continuation) {
        ByteBuffer mo12351borrow = PoolsKt.getDefaultDatagramByteBufferPool().mo12351borrow();
        try {
            SocketAddress receive = mo10306getChannel().receive(mo12351borrow);
            if (receive != null) {
                interestOp(SelectInterest.READ, false);
                mo12351borrow.flip();
                BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(0);
                try {
                    BytePacketBuilder.writeFully(mo12351borrow);
                    Datagram datagram = new Datagram(BytePacketBuilder.build(), receive);
                    return datagram;
                } catch (Throwable th) {
                    BytePacketBuilder.release();
                    throw th;
                }
            }
            return receiveSuspend(mo12351borrow, continuation);
        } finally {
            PoolsKt.getDefaultDatagramByteBufferPool().recycle(mo12351borrow);
        }
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x005c -> B:39:0x005f). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final /* synthetic */ java.lang.Object receiveSuspend(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.ktor.network.sockets.Datagram> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.network.sockets.DatagramSocketImpl$receiveSuspend$1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.network.sockets.DatagramSocketImpl$receiveSuspend$1 r0 = (io.ktor.network.sockets.DatagramSocketImpl$receiveSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.sockets.DatagramSocketImpl$receiveSuspend$1 r0 = new io.ktor.network.sockets.DatagramSocketImpl$receiveSuspend$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L41
            if (r2 != r3) goto L39
            java.lang.Object r6 = r0.L$1
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r2 = r0.L$0
            io.ktor.network.sockets.DatagramSocketImpl r2 = (io.ktor.network.sockets.DatagramSocketImpl) r2
            boolean r4 = r7 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L34
            r7 = r6
            r6 = r2
            goto L5f
        L34:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L39:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L41:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L99
            r7 = r6
            r6 = r5
        L47:
            io.ktor.network.selector.SelectInterest r2 = io.ktor.network.selector.SelectInterest.READ
            r6.interestOp(r2, r3)
            io.ktor.network.selector.SelectorManager r2 = r6.getSelector()
            io.ktor.network.selector.SelectInterest r4 = io.ktor.network.selector.SelectInterest.READ
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r2 = r2.select(r6, r4, r0)
            if (r2 != r1) goto L5f
            return r1
        L5f:
            java.nio.channels.DatagramChannel r2 = r6.mo10306getChannel()     // Catch: java.lang.Throwable -> L90
            java.net.SocketAddress r2 = r2.receive(r7)     // Catch: java.lang.Throwable -> L90
            if (r2 != 0) goto L6a
            goto L47
        L6a:
            io.ktor.network.selector.SelectInterest r0 = io.ktor.network.selector.SelectInterest.READ
            r1 = 0
            r6.interestOp(r0, r1)
            r7.flip()
            kotlinx.io.core.BytePacketBuilder r6 = kotlinx.io.core.PacketJVMKt.BytePacketBuilder(r1)
            r6.writeFully(r7)     // Catch: java.lang.Throwable -> L8b
            kotlinx.io.core.ByteReadPacket r6 = r6.build()     // Catch: java.lang.Throwable -> L8b
            io.ktor.network.sockets.Datagram r0 = new io.ktor.network.sockets.Datagram
            r0.<init>(r6, r2)
            kotlinx.io.pool.ObjectPool r6 = io.ktor.network.util.PoolsKt.getDefaultDatagramByteBufferPool()
            r6.recycle(r7)
            return r0
        L8b:
            r7 = move-exception
            r6.release()
            throw r7
        L90:
            r6 = move-exception
            kotlinx.io.pool.ObjectPool r0 = io.ktor.network.util.PoolsKt.getDefaultDatagramByteBufferPool()
            r0.recycle(r7)
            throw r6
        L99:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.DatagramSocketImpl.receiveSuspend(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // io.ktor.network.sockets.DatagramWriteChannel
    @Nullable
    public Object send(@NotNull Datagram datagram, @NotNull Continuation<? super Unit> continuation) {
        return BoundDatagramSocket.DefaultImpls.send(this, datagram, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final /* synthetic */ Object sendImpl(@NotNull Datagram datagram, @NotNull Continuation<? super Unit> continuation) {
        ByteBuffer buffer = ByteBuffer.allocateDirect((int) datagram.getPacket().m12336getRemaining());
        ByteReadPacket packet = datagram.getPacket();
        Intrinsics.checkExpressionValueIsNotNull(buffer, "buffer");
        Input.DefaultImpls.readAvailable$default(packet, buffer, 0, 2, null);
        buffer.flip();
        if (mo10306getChannel().send(buffer, datagram.getAddress()) == 0) {
            return sendSuspend(buffer, datagram.getAddress(), continuation);
        }
        interestOp(SelectInterest.WRITE, false);
        return Unit.INSTANCE;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0065 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0071  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0063 -> B:24:0x0066). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final /* synthetic */ java.lang.Object sendSuspend(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r6, @org.jetbrains.annotations.NotNull java.net.SocketAddress r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ktor.network.sockets.DatagramSocketImpl$sendSuspend$1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.network.sockets.DatagramSocketImpl$sendSuspend$1 r0 = (io.ktor.network.sockets.DatagramSocketImpl$sendSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.sockets.DatagramSocketImpl$sendSuspend$1 r0 = new io.ktor.network.sockets.DatagramSocketImpl$sendSuspend$1
            r0.<init>(r5, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L45
            if (r2 != r3) goto L3d
            java.lang.Object r6 = r0.L$2
            java.net.SocketAddress r6 = (java.net.SocketAddress) r6
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.network.sockets.DatagramSocketImpl r2 = (io.ktor.network.sockets.DatagramSocketImpl) r2
            boolean r4 = r8 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L38
            r8 = r6
            r6 = r2
            goto L66
        L38:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        L3d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L45:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L7a
            r8 = r7
            r7 = r6
            r6 = r5
        L4c:
            io.ktor.network.selector.SelectInterest r2 = io.ktor.network.selector.SelectInterest.WRITE
            r6.interestOp(r2, r3)
            io.ktor.network.selector.SelectorManager r2 = r6.getSelector()
            io.ktor.network.selector.SelectInterest r4 = io.ktor.network.selector.SelectInterest.WRITE
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r2 = r2.select(r6, r4, r0)
            if (r2 != r1) goto L66
            return r1
        L66:
            java.nio.channels.DatagramChannel r2 = r6.mo10306getChannel()
            int r2 = r2.send(r7, r8)
            if (r2 != 0) goto L71
            goto L4c
        L71:
            io.ktor.network.selector.SelectInterest r7 = io.ktor.network.selector.SelectInterest.WRITE
            r8 = 0
            r6.interestOp(r7, r8)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L7a:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.DatagramSocketImpl.sendSuspend(java.nio.ByteBuffer, java.net.SocketAddress, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
