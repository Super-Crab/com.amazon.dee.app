package io.ktor.network.sockets;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProducerScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DatagramSocketImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Lio/ktor/network/sockets/Datagram;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.network.sockets.DatagramSocketImpl$receiver$1", f = "DatagramSocketImpl.kt", i = {}, l = {32, 32}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class DatagramSocketImpl$receiver$1 extends SuspendLambda implements Function2<ProducerScope<? super Datagram>, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;
    private ProducerScope p$;
    final /* synthetic */ DatagramSocketImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DatagramSocketImpl$receiver$1(DatagramSocketImpl datagramSocketImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = datagramSocketImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DatagramSocketImpl$receiver$1 datagramSocketImpl$receiver$1 = new DatagramSocketImpl$receiver$1(this.this$0, completion);
        datagramSocketImpl$receiver$1.p$ = (ProducerScope) obj;
        return datagramSocketImpl$receiver$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(ProducerScope<? super Datagram> producerScope, Continuation<? super Unit> continuation) {
        return ((DatagramSocketImpl$receiver$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0053 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0063 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0064  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0064 -> B:22:0x0041). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L39
            if (r1 == r3) goto L25
            if (r1 != r2) goto L1d
            java.lang.Object r1 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            boolean r4 = r8 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L18
            r8 = r1
            goto L3f
        L18:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        L1d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L25:
            java.lang.Object r1 = r7.L$1
            kotlinx.coroutines.channels.SendChannel r1 = (kotlinx.coroutines.channels.SendChannel) r1
            java.lang.Object r4 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            boolean r5 = r8 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L34
            r5 = r0
            r0 = r7
            goto L59
        L34:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        L39:
            boolean r1 = r8 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L67
            kotlinx.coroutines.channels.ProducerScope r8 = r7.p$
        L3f:
            r1 = r0
            r0 = r7
        L41:
            kotlinx.coroutines.channels.SendChannel r4 = r8.getChannel()
            io.ktor.network.sockets.DatagramSocketImpl r5 = r0.this$0
            r0.L$0 = r8
            r0.L$1 = r4
            r0.label = r3
            java.lang.Object r5 = r5.receiveImpl(r0)
            if (r5 != r1) goto L54
            return r1
        L54:
            r6 = r4
            r4 = r8
            r8 = r5
            r5 = r1
            r1 = r6
        L59:
            r0.L$0 = r4
            r0.label = r2
            java.lang.Object r8 = r1.send(r8, r0)
            if (r8 != r5) goto L64
            return r5
        L64:
            r8 = r4
            r1 = r5
            goto L41
        L67:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.DatagramSocketImpl$receiver$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
