package com.amazon.alexa.accessorykit.accessories.session.state;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRN;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRNEventId;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import java.text.ParseException;
/* loaded from: classes6.dex */
public final class StateRepositoryModule {
    private final SessionSupplier clientSessionSupplier;
    private final RxRN rxRN;
    private final MapModelTransformer<StateOuterClass.State> stateModelTransformer;

    public StateRepositoryModule(ContainerProvider containerProvider, RxRN rxRN, SessionSupplier sessionSupplier) {
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(rxRN, "rxRN");
        Preconditions.notNull(sessionSupplier, "clientSessionSupplier");
        this.stateModelTransformer = new StateModelTransformer(containerProvider);
        this.rxRN = rxRN;
        this.clientSessionSupplier = sessionSupplier;
    }

    public void query(ReadableMap readableMap, String str, int i) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.stateModelTransformer, this.clientSessionSupplier.getSession(str).getStateRepository().query(StateFeature.from(i)).toObservable());
    }

    public void set(String str, ReadableMap readableMap, final Promise promise) {
        try {
            Completable completable = this.clientSessionSupplier.getSession(str).getStateRepository().set(this.stateModelTransformer.mo626transform(readableMap));
            Action action = new Action() { // from class: com.amazon.alexa.accessorykit.accessories.session.state.-$$Lambda$StateRepositoryModule$oQEeCMT6UHpWUJvv6LDZHPKQsL0
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    Promise.this.resolve(null);
                }
            };
            promise.getClass();
            completable.subscribe(action, new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.state.-$$Lambda$uy64BVvzETLGSEBSP5-7B5ZCU4o
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    Promise.this.reject((Throwable) obj);
                }
            });
        } catch (ParseException e) {
            promise.reject("Bridge call stateRepositorySet failed.", e);
        }
    }
}
