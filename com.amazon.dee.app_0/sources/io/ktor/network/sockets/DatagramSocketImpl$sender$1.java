package io.ktor.network.sockets;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ActorScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DatagramSocketImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", "Lio/ktor/network/sockets/Datagram;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.network.sockets.DatagramSocketImpl$sender$1", f = "DatagramSocketImpl.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2}, l = {110, 110, 26}, m = "invokeSuspend", n = {"$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "$receiver$iv", "$receiver$iv$iv", "cause$iv$iv", "$receiver$iv", "e$iv", "datagram"}, s = {"L$0", "L$2", "L$3", "L$4", "L$0", "L$2", "L$3", "L$4", "L$0", "L$2", "L$3", "L$4", "L$6", "L$7"})
/* loaded from: classes3.dex */
final class DatagramSocketImpl$sender$1 extends SuspendLambda implements Function2<ActorScope<Datagram>, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    private ActorScope p$;
    final /* synthetic */ DatagramSocketImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DatagramSocketImpl$sender$1(DatagramSocketImpl datagramSocketImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = datagramSocketImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DatagramSocketImpl$sender$1 datagramSocketImpl$sender$1 = new DatagramSocketImpl$sender$1(this.this$0, completion);
        datagramSocketImpl$sender$1.p$ = (ActorScope) obj;
        return datagramSocketImpl$sender$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(ActorScope<Datagram> actorScope, Continuation<? super Unit> continuation) {
        return ((DatagramSocketImpl$sender$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x0101: INVOKE  (r7 I:kotlinx.coroutines.channels.ReceiveChannel), (r15 I:java.lang.Throwable) type: INTERFACE call: kotlinx.coroutines.channels.ReceiveChannel.cancel(java.lang.Throwable):boolean, block:B:51:0x0101 */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c1 A[Catch: all -> 0x008c, TryCatch #1 {all -> 0x008c, blocks: (B:8:0x002d, B:33:0x00a0, B:37:0x00b9, B:39:0x00c1, B:42:0x00d6, B:46:0x00f7, B:11:0x0036, B:12:0x003a, B:16:0x005b, B:19:0x0063, B:20:0x0067, B:22:0x0080, B:25:0x0087, B:26:0x008b, B:32:0x0096), top: B:57:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00f3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f7 A[Catch: all -> 0x008c, TRY_LEAVE, TryCatch #1 {all -> 0x008c, blocks: (B:8:0x002d, B:33:0x00a0, B:37:0x00b9, B:39:0x00c1, B:42:0x00d6, B:46:0x00f7, B:11:0x0036, B:12:0x003a, B:16:0x005b, B:19:0x0063, B:20:0x0067, B:22:0x0080, B:25:0x0087, B:26:0x008b, B:32:0x0096), top: B:57:0x0009 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00f4 -> B:33:0x00a0). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r15) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.DatagramSocketImpl$sender$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
