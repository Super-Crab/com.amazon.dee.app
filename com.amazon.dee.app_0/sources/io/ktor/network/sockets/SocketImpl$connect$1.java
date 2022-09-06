package io.ktor.network.sockets;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.mode.debug.EmulateConnection;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SocketImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\n\b\u0000\u0010\u0002 \u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0080@ø\u0001\u0000"}, d2 = {EmulateConnection.EXTRA_CONNECT, "", ExifInterface.LATITUDE_SOUTH, "Ljava/nio/channels/SocketChannel;", "target", "Ljava/net/SocketAddress;", "continuation", "Lkotlin/coroutines/Continuation;", "Lio/ktor/network/sockets/Socket;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.network.sockets.SocketImpl", f = "SocketImpl.kt", i = {0, 0, 1, 1}, l = {27, 33}, m = "connect$ktor_network", n = {"this", "target", "this", "target"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes3.dex */
public final class SocketImpl$connect$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SocketImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SocketImpl$connect$1(SocketImpl socketImpl, Continuation continuation) {
        super(continuation);
        this.this$0 = socketImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.connect$ktor_network(null, this);
    }
}
