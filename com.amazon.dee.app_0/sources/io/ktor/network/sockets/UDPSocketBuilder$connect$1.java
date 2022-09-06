package io.ktor.network.sockets;

import io.ktor.network.sockets.SocketOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: Builders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class UDPSocketBuilder$connect$1 extends Lambda implements Function1<SocketOptions.UDPSocketOptions, Unit> {
    public static final UDPSocketBuilder$connect$1 INSTANCE = new UDPSocketBuilder$connect$1();

    UDPSocketBuilder$connect$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(SocketOptions.UDPSocketOptions uDPSocketOptions) {
        invoke2(uDPSocketOptions);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull SocketOptions.UDPSocketOptions receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
    }
}
