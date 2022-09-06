package io.ktor.network.sockets;

import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import io.ktor.network.selector.InterestSuspensionsMap;
import io.ktor.network.selector.SelectInterest;
import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectableBase;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.ServerSocket;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ServerSocketImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\u001d\u001a\u00020\u001eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0011\u0010 \u001a\u00020\u001eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0010\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u0016H\u0016J\b\u0010%\u001a\u00020\u0016H\u0016J\u0019\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0096\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001a8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"Lio/ktor/network/sockets/ServerSocketImpl;", "Lio/ktor/network/sockets/ServerSocket;", "Lio/ktor/network/selector/Selectable;", "channel", "Ljava/nio/channels/ServerSocketChannel;", "selector", "Lio/ktor/network/selector/SelectorManager;", "(Ljava/nio/channels/ServerSocketChannel;Lio/ktor/network/selector/SelectorManager;)V", "getChannel", "()Ljava/nio/channels/ServerSocketChannel;", "interestedOps", "", "getInterestedOps", "()I", "localAddress", "Ljava/net/SocketAddress;", "getLocalAddress", "()Ljava/net/SocketAddress;", "getSelector", "()Lio/ktor/network/selector/SelectorManager;", "socketContext", "Lkotlinx/coroutines/CompletableDeferred;", "", "getSocketContext", "()Lkotlinx/coroutines/CompletableDeferred;", "suspensions", "Lio/ktor/network/selector/InterestSuspensionsMap;", "getSuspensions", "()Lio/ktor/network/selector/InterestSuspensionsMap;", "accept", "Lio/ktor/network/sockets/Socket;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "acceptSuspend", BizMetricsConstants.ACCEPTED_BY_CUSTOMER_TRUE, "nioChannel", "Ljava/nio/channels/SocketChannel;", "close", "dispose", "interestOp", "interest", "Lio/ktor/network/selector/SelectInterest;", "state", "", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ServerSocketImpl implements ServerSocket, Selectable {
    private final /* synthetic */ SelectableBase $$delegate_0;
    @NotNull
    private final ServerSocketChannel channel;
    @NotNull
    private final SelectorManager selector;
    @NotNull
    private final CompletableDeferred<Unit> socketContext;

    public ServerSocketImpl(@NotNull ServerSocketChannel channel, @NotNull SelectorManager selector) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        this.$$delegate_0 = new SelectableBase(channel);
        this.channel = channel;
        this.selector = selector;
        if (!mo10306getChannel().isBlocking()) {
            this.socketContext = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
            return;
        }
        throw new IllegalArgumentException("channel need to be configured as non-blocking".toString());
    }

    private final Socket accepted(SocketChannel socketChannel) {
        interestOp(SelectInterest.ACCEPT, false);
        java.net.Socket socket = socketChannel.socket();
        if (socket == null) {
            Intrinsics.throwNpe();
        }
        socketChannel.configureBlocking(false);
        socket.setTcpNoDelay(true);
        return new SocketImpl(socketChannel, socket, this.selector);
    }

    @Override // io.ktor.network.sockets.Acceptable
    @Nullable
    public Object accept(@NotNull Continuation<? super Socket> continuation) {
        SocketChannel accept = mo10306getChannel().accept();
        return accept != null ? accepted(accept) : acceptSuspend(continuation);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0054 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0052 -> B:24:0x0055). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final /* synthetic */ java.lang.Object acceptSuspend(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.ktor.network.sockets.Socket> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof io.ktor.network.sockets.ServerSocketImpl$acceptSuspend$1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.network.sockets.ServerSocketImpl$acceptSuspend$1 r0 = (io.ktor.network.sockets.ServerSocketImpl$acceptSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.sockets.ServerSocketImpl$acceptSuspend$1 r0 = new io.ktor.network.sockets.ServerSocketImpl$acceptSuspend$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3c
            if (r2 != r3) goto L34
            java.lang.Object r2 = r0.L$0
            io.ktor.network.sockets.ServerSocketImpl r2 = (io.ktor.network.sockets.ServerSocketImpl) r2
            boolean r4 = r6 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L2f
            r6 = r2
            goto L55
        L2f:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L34:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L3c:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L64
            r6 = r5
        L41:
            io.ktor.network.selector.SelectInterest r2 = io.ktor.network.selector.SelectInterest.ACCEPT
            r6.interestOp(r2, r3)
            io.ktor.network.selector.SelectorManager r2 = r6.selector
            io.ktor.network.selector.SelectInterest r4 = io.ktor.network.selector.SelectInterest.ACCEPT
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r2 = r2.select(r6, r4, r0)
            if (r2 != r1) goto L55
            return r1
        L55:
            java.nio.channels.ServerSocketChannel r2 = r6.mo10306getChannel()
            java.nio.channels.SocketChannel r2 = r2.accept()
            if (r2 == 0) goto L41
            io.ktor.network.sockets.Socket r6 = r6.accepted(r2)
            return r6
        L64:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.ServerSocketImpl.acceptSuspend(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            mo10306getChannel().close();
            this.selector.notifyClosed(this);
            mo10305getSocketContext().complete(Unit.INSTANCE);
        } catch (Throwable th) {
            mo10305getSocketContext().completeExceptionally(th);
        }
    }

    @Override // io.ktor.network.sockets.ASocket, kotlinx.coroutines.DisposableHandle
    public void dispose() {
        ServerSocket.DefaultImpls.dispose(this);
    }

    @Override // io.ktor.network.selector.Selectable
    public int getInterestedOps() {
        return this.$$delegate_0.getInterestedOps();
    }

    @Override // io.ktor.network.sockets.ABoundSocket
    @NotNull
    public SocketAddress getLocalAddress() {
        java.net.ServerSocket socket = mo10306getChannel().socket();
        Intrinsics.checkExpressionValueIsNotNull(socket, "channel.socket()");
        SocketAddress localSocketAddress = socket.getLocalSocketAddress();
        Intrinsics.checkExpressionValueIsNotNull(localSocketAddress, "channel.socket().localSocketAddress");
        return localSocketAddress;
    }

    @NotNull
    public final SelectorManager getSelector() {
        return this.selector;
    }

    @Override // io.ktor.network.selector.Selectable
    @NotNull
    public InterestSuspensionsMap getSuspensions() {
        return this.$$delegate_0.getSuspensions();
    }

    @Override // io.ktor.network.selector.Selectable
    public void interestOp(@NotNull SelectInterest interest, boolean z) {
        Intrinsics.checkParameterIsNotNull(interest, "interest");
        this.$$delegate_0.interestOp(interest, z);
    }

    @Override // io.ktor.network.selector.Selectable
    @NotNull
    /* renamed from: getChannel  reason: collision with other method in class */
    public ServerSocketChannel mo10306getChannel() {
        return this.channel;
    }

    @Override // io.ktor.network.sockets.ASocket
    @NotNull
    /* renamed from: getSocketContext */
    public CompletableDeferred<Unit> mo10305getSocketContext() {
        return this.socketContext;
    }
}
