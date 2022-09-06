package io.ktor.network.sockets;

import com.amazon.alexa.mode.debug.EmulateConnection;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Builders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0019\b\u0002\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0086@ø\u0001\u0000"}, d2 = {EmulateConnection.EXTRA_CONNECT, "", "remoteAddress", "Ljava/net/SocketAddress;", "configure", "Lkotlin/Function1;", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "", "Lkotlin/ExtensionFunctionType;", "continuation", "Lkotlin/coroutines/Continuation;", "Lio/ktor/network/sockets/Socket;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.network.sockets.TcpSocketBuilder", f = "Builders.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {104}, m = EmulateConnection.EXTRA_CONNECT, n = {"this", "remoteAddress", "configure", "$receiver$iv", "$receiver", "result$iv", "options", "$receiver"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8"})
/* loaded from: classes3.dex */
public final class TcpSocketBuilder$connect$3 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TcpSocketBuilder this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TcpSocketBuilder$connect$3(TcpSocketBuilder tcpSocketBuilder, Continuation continuation) {
        super(continuation);
        this.this$0 = tcpSocketBuilder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.connect(null, null, this);
    }
}
