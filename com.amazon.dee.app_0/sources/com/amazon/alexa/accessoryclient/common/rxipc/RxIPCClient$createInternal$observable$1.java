package com.amazon.alexa.accessoryclient.common.rxipc;

import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCEventId;
import com.amazon.alexa.accessoryclient.common.util.Logger;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Cancellable;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RxIPCClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u000e\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "O", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "emitter", "Lio/reactivex/rxjava3/core/ObservableEmitter;", "Landroid/os/Bundle;", "subscribe"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RxIPCClient$createInternal$observable$1<T> implements ObservableOnSubscribe<T> {
    final /* synthetic */ ApiIdentifier $apiIdentifier;
    final /* synthetic */ Exception $calledFromLocationException;
    final /* synthetic */ Query.Request $request;
    final /* synthetic */ RxIPCClient.RxType $rxType;
    final /* synthetic */ RxIPCClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RxIPCClient$createInternal$observable$1(RxIPCClient rxIPCClient, ApiIdentifier apiIdentifier, Query.Request request, RxIPCClient.RxType rxType, Exception exc) {
        this.this$0 = rxIPCClient;
        this.$apiIdentifier = apiIdentifier;
        this.$request = request;
        this.$rxType = rxType;
        this.$calledFromLocationException = exc;
    }

    @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
    public final void subscribe(@NotNull ObservableEmitter<Bundle> emitter) {
        Object obj;
        Exception exc;
        Map map;
        Exception exc2;
        Intrinsics.checkParameterIsNotNull(emitter, "emitter");
        final RxIPCEventId rxIPCEventId = new RxIPCEventId(RxIPCEventId.Action.INIT);
        RxIPCEvent rxIPCEvent = new RxIPCEvent(rxIPCEventId, new ApiIdentifier.ApiRequest(this.$apiIdentifier, this.$request).toBundle());
        RxIPCClient.RxTypeEmitter rxTypeEmitter = new RxIPCClient.RxTypeEmitter(emitter, this.$rxType, this.$calledFromLocationException);
        obj = this.this$0.lock;
        synchronized (obj) {
            exc = this.this$0.releasedReason;
            if (exc == null) {
                map = this.this$0.emitterMap;
                map.put(rxIPCEventId.getUuid(), rxTypeEmitter);
                Logger.INSTANCE.v(new RxIPCClient$createInternal$observable$1$$special$$inlined$synchronized$lambda$1(this, rxTypeEmitter, rxIPCEventId, rxIPCEvent));
                RxIPCClient.access$getOutputStream$p(this.this$0).handleBundle(rxIPCEvent.toBundle());
                Unit unit = Unit.INSTANCE;
                emitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient$createInternal$observable$1.2
                    @Override // io.reactivex.rxjava3.functions.Cancellable
                    public final void cancel() {
                        Object obj2;
                        RxIPCClient.RxTypeEmitter clearEmitter;
                        Map map2;
                        obj2 = RxIPCClient$createInternal$observable$1.this.this$0.lock;
                        synchronized (obj2) {
                            clearEmitter = RxIPCClient$createInternal$observable$1.this.this$0.clearEmitter(rxIPCEventId.getUuid());
                            if (clearEmitter != null) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("RxIPCClient: Dispose from downstream observable ");
                                sb.append(rxIPCEventId.getUuid());
                                sb.append(", ");
                                sb.append("sending dispose notification to service, map size:");
                                map2 = RxIPCClient$createInternal$observable$1.this.this$0.emitterMap;
                                sb.append(map2.size());
                                com.amazon.alexa.accessory.internal.util.Logger.d(sb.toString());
                                RxIPCClient.access$getOutputStream$p(RxIPCClient$createInternal$observable$1.this.this$0).handleBundle(new RxIPCEvent(rxIPCEventId, new Bundle()).toBundle(RxIPCEventId.Action.ON_DISPOSE));
                                Unit unit2 = Unit.INSTANCE;
                            }
                        }
                    }
                });
                return;
            }
            RxIPCClient rxIPCClient = this.this$0;
            String uuid = rxIPCEventId.getUuid();
            exc2 = this.this$0.releasedReason;
            rxIPCClient.provideEmitterError(rxTypeEmitter, uuid, String.valueOf(exc2));
        }
    }
}
