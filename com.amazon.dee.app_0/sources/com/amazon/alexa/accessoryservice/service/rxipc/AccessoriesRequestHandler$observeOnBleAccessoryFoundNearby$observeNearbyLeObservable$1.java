package com.amazon.alexa.accessoryservice.service.rxipc;

import android.os.Handler;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryScanRecord;
import com.amazon.alexa.accessory.AccessoryScanner;
import com.amazon.alexa.accessory.AccessoryScannerAdapter;
import com.amazon.alexa.accessoryclient.common.api.AccessoryScanResult;
import com.amazon.alexa.accessoryclient.common.query.response.AccessoryScanResultResponse;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Cancellable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AccessoriesRequestHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u000124\u0010\u0002\u001a0\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0017\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u0003¢\u0006\u0002\b\u00060\u0003¢\u0006\u0002\b\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "emitter", "Lio/reactivex/rxjava3/core/ObservableEmitter;", "Lcom/amazon/alexa/accessoryclient/common/query/response/AccessoryScanResultResponse;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "subscribe"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AccessoriesRequestHandler$observeOnBleAccessoryFoundNearby$observeNearbyLeObservable$1<T> implements ObservableOnSubscribe<T> {
    final /* synthetic */ AccessoriesRequestHandler this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccessoriesRequestHandler$observeOnBleAccessoryFoundNearby$observeNearbyLeObservable$1(AccessoriesRequestHandler accessoriesRequestHandler) {
        this.this$0 = accessoriesRequestHandler;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.amazon.alexa.accessory.AccessoryScannerListener, com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$observeOnBleAccessoryFoundNearby$observeNearbyLeObservable$1$scanner$1] */
    @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
    public final void subscribe(final ObservableEmitter<AccessoryScanResultResponse> observableEmitter) {
        AccessoryScanner accessoryScanner;
        final ?? r0 = new AccessoryScannerAdapter() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$observeOnBleAccessoryFoundNearby$observeNearbyLeObservable$1$scanner$1
            @Override // com.amazon.alexa.accessory.AccessoryScannerAdapter, com.amazon.alexa.accessory.AccessoryScannerListener
            public void onBleAccessoryFoundNearby(@NotNull Accessory accessory, @Nullable AccessoryScanRecord accessoryScanRecord, int i) {
                Intrinsics.checkParameterIsNotNull(accessory, "accessory");
                ObservableEmitter.this.onNext(new AccessoryScanResultResponse(new AccessoryScanResult(accessory, accessoryScanRecord, i)));
            }
        };
        accessoryScanner = this.this$0.accessoryScanner;
        accessoryScanner.startScan(r0);
        observableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$observeOnBleAccessoryFoundNearby$observeNearbyLeObservable$1.1
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                Handler handler;
                handler = AccessoriesRequestHandler$observeOnBleAccessoryFoundNearby$observeNearbyLeObservable$1.this.this$0.mainThreadHandler;
                handler.post(new Runnable() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler.observeOnBleAccessoryFoundNearby.observeNearbyLeObservable.1.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AccessoryScanner accessoryScanner2;
                        accessoryScanner2 = AccessoriesRequestHandler$observeOnBleAccessoryFoundNearby$observeNearbyLeObservable$1.this.this$0.accessoryScanner;
                        accessoryScanner2.cancelScan(r0);
                    }
                });
            }
        });
    }
}
