package com.amazon.alexa.accessorykit.accessories.session.transport;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier;
import com.facebook.react.bridge.Promise;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes6.dex */
public class TransportRepositoryModule {
    private final SessionSupplier clientSessionSupplier;

    public TransportRepositoryModule(SessionSupplier sessionSupplier) {
        Preconditions.notNull(sessionSupplier, "clientSessionSupplier");
        this.clientSessionSupplier = sessionSupplier;
    }

    public void requestUpgrade(String str, final Promise promise) {
        Completable requestUpgrade = this.clientSessionSupplier.getSession(str).getTransportRepository().requestUpgrade();
        Action action = new Action() { // from class: com.amazon.alexa.accessorykit.accessories.session.transport.-$$Lambda$TransportRepositoryModule$CeQkVxHPsaZbuWtgAw26IMxZ0Do
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(null);
            }
        };
        promise.getClass();
        requestUpgrade.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    public void shouldUpgrade(String str, final Promise promise) {
        Single<Boolean> shouldUpgrade = this.clientSessionSupplier.getSession(str).getTransportRepository().shouldUpgrade();
        promise.getClass();
        Consumer<? super Boolean> consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.transport.-$$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve((Boolean) obj);
            }
        };
        promise.getClass();
        shouldUpgrade.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }
}
