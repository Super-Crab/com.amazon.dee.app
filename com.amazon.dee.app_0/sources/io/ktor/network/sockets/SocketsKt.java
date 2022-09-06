package io.ktor.network.sockets;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.io.ByteChannel;
import kotlinx.coroutines.io.ByteChannelKt;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.ByteWriteChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Sockets.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0004\u001a\u00020\u0005*\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a\n\u0010\u0007\u001a\u00020\b*\u00020\t\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u0001\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"isClosed", "", "Lio/ktor/network/sockets/ASocket;", "(Lio/ktor/network/sockets/ASocket;)Z", "awaitClosed", "", "(Lio/ktor/network/sockets/ASocket;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openReadChannel", "Lkotlinx/coroutines/io/ByteReadChannel;", "Lio/ktor/network/sockets/AReadable;", "openWriteChannel", "Lkotlinx/coroutines/io/ByteWriteChannel;", "Lio/ktor/network/sockets/AWritable;", "autoFlush", "ktor-network"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class SocketsKt {
    @Nullable
    public static final Object awaitClosed(@NotNull ASocket aSocket, @NotNull Continuation<? super Unit> continuation) {
        return aSocket.mo10305getSocketContext().await(continuation);
    }

    public static final boolean isClosed(@NotNull ASocket receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.mo10305getSocketContext().isCompleted();
    }

    @NotNull
    public static final ByteReadChannel openReadChannel(@NotNull AReadable receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ByteChannel ByteChannel = ByteChannelKt.ByteChannel(false);
        receiver$0.attachForReading(ByteChannel);
        return ByteChannel;
    }

    @NotNull
    public static final ByteWriteChannel openWriteChannel(@NotNull AWritable receiver$0, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ByteChannel ByteChannel = ByteChannelKt.ByteChannel(z);
        receiver$0.attachForWriting(ByteChannel);
        return ByteChannel;
    }

    @NotNull
    public static /* synthetic */ ByteWriteChannel openWriteChannel$default(AWritable aWritable, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return openWriteChannel(aWritable, z);
    }
}
