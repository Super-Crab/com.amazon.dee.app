package com.amazon.alexa.accessoryservice.service.rxipc;

import android.os.Handler;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessoryclient.common.api.AccessoryTransportChange;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Cancellable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AccessoriesRequestHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u000124\u0010\u0002\u001a0\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0017\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u0003¢\u0006\u0002\b\u00060\u0003¢\u0006\u0002\b\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "emitter", "Lio/reactivex/rxjava3/core/ObservableEmitter;", "Lcom/amazon/alexa/accessoryclient/common/api/AccessoryTransportChange;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "subscribe"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AccessoriesRequestHandler$observeSessionTransportChanged$observeSessionTransportChangeObservable$1<T> implements ObservableOnSubscribe<T> {
    final /* synthetic */ AccessoriesRequestHandler this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccessoriesRequestHandler$observeSessionTransportChanged$observeSessionTransportChangeObservable$1(AccessoriesRequestHandler accessoriesRequestHandler) {
        this.this$0 = accessoriesRequestHandler;
    }

    @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
    public final void subscribe(final ObservableEmitter<AccessoryTransportChange> observableEmitter) {
        SessionSupplier sessionSupplier;
        final AccessorySessionListener accessorySessionListener = new AccessorySessionListener() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$observeSessionTransportChanged$observeSessionTransportChangeObservable$1$sessionListener$1
            @Override // com.amazon.alexa.accessory.AccessorySessionListener
            public void onAccessorySessionTransportChanged(@NotNull Accessory oldAccessory, @NotNull AccessoryTransport.Type oldTransport, @NotNull Accessory newAccessory, @NotNull AccessoryTransport.Type newTransport) {
                Intrinsics.checkParameterIsNotNull(oldAccessory, "oldAccessory");
                Intrinsics.checkParameterIsNotNull(oldTransport, "oldTransport");
                Intrinsics.checkParameterIsNotNull(newAccessory, "newAccessory");
                Intrinsics.checkParameterIsNotNull(newTransport, "newTransport");
                ObservableEmitter.this.onNext(new AccessoryTransportChange(oldAccessory, oldTransport, newAccessory, newTransport));
            }
        };
        sessionSupplier = this.this$0.sessionSupplier;
        sessionSupplier.addSessionListener(accessorySessionListener);
        observableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$observeSessionTransportChanged$observeSessionTransportChangeObservable$1.1
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                Handler handler;
                handler = AccessoriesRequestHandler$observeSessionTransportChanged$observeSessionTransportChangeObservable$1.this.this$0.mainThreadHandler;
                handler.post(new Runnable() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler.observeSessionTransportChanged.observeSessionTransportChangeObservable.1.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        SessionSupplier sessionSupplier2;
                        sessionSupplier2 = AccessoriesRequestHandler$observeSessionTransportChanged$observeSessionTransportChangeObservable$1.this.this$0.sessionSupplier;
                        sessionSupplier2.removeSessionListener(accessorySessionListener);
                    }
                });
            }
        });
    }
}
