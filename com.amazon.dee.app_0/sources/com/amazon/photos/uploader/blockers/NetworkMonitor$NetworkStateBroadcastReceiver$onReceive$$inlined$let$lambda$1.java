package com.amazon.photos.uploader.blockers;

import com.amazon.photos.uploader.blockers.NetworkMonitor;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NetworkMonitor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/amazon/photos/uploader/blockers/NetworkMonitor$NetworkStateBroadcastReceiver$onReceive$1$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class NetworkMonitor$NetworkStateBroadcastReceiver$onReceive$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $it;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ NetworkMonitor.NetworkStateBroadcastReceiver this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkMonitor$NetworkStateBroadcastReceiver$onReceive$$inlined$let$lambda$1(String str, Continuation continuation, NetworkMonitor.NetworkStateBroadcastReceiver networkStateBroadcastReceiver) {
        super(2, continuation);
        this.$it = str;
        this.this$0 = networkStateBroadcastReceiver;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        NetworkMonitor$NetworkStateBroadcastReceiver$onReceive$$inlined$let$lambda$1 networkMonitor$NetworkStateBroadcastReceiver$onReceive$$inlined$let$lambda$1 = new NetworkMonitor$NetworkStateBroadcastReceiver$onReceive$$inlined$let$lambda$1(this.$it, completion, this.this$0);
        networkMonitor$NetworkStateBroadcastReceiver$onReceive$$inlined$let$lambda$1.p$ = (CoroutineScope) obj;
        return networkMonitor$NetworkStateBroadcastReceiver$onReceive$$inlined$let$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NetworkMonitor$NetworkStateBroadcastReceiver$onReceive$$inlined$let$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$it.equals(BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION)) {
                NetworkMonitor.this.getLogger().i$AndroidPhotosUploader_release(NetworkMonitor.TAG, "Network broadcast received");
                NetworkMonitor.this.notifyOnNetworkChange(new NetworkState(NetworkMonitor.this.getSystemUtil()));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
