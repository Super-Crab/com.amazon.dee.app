package com.amazon.alexa.accessorykit.accessories.session.input;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRN;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRNEventId;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import java.text.ParseException;
/* loaded from: classes6.dex */
public final class InputRepositoryModule {
    private final SessionSupplier clientSessionSupplier;
    private final MapModelTransformer<Input.InputBehaviorConfigurationSet> configurationSetTransformer;
    private final MapModelTransformer<Input.InputBehaviorConfiguration> configurationTransformer;
    private final RxRN rxRN;

    public InputRepositoryModule(ContainerProvider containerProvider, RxRN rxRN, SessionSupplier sessionSupplier) {
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(rxRN, "rxRN");
        Preconditions.notNull(sessionSupplier, "clientSessionSupplier");
        this.clientSessionSupplier = sessionSupplier;
        this.configurationSetTransformer = new InputBehaviorConfigurationSetTransformer(containerProvider);
        this.configurationTransformer = new InputBehaviorConfigurationTransformer(containerProvider);
        this.rxRN = rxRN;
    }

    public void queryConfiguration(ReadableMap readableMap, String str, int i) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.configurationSetTransformer, this.clientSessionSupplier.getSession(str).getInputRepository().queryConfiguration(i).toObservable());
    }

    @SuppressLint({"CheckResult"})
    public void resetConfiguration(String str, int i, final Promise promise) {
        Completable resetConfiguration = this.clientSessionSupplier.getSession(str).getInputRepository().resetConfiguration(i);
        Action action = new Action() { // from class: com.amazon.alexa.accessorykit.accessories.session.input.-$$Lambda$InputRepositoryModule$RlMH_ZW2XCrBziHo_9nfkGMluyA
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(null);
            }
        };
        promise.getClass();
        resetConfiguration.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @SuppressLint({"CheckResult"})
    public void setConfiguration(String str, int i, ReadableMap readableMap, final Promise promise) {
        try {
            Completable configuration = this.clientSessionSupplier.getSession(str).getInputRepository().setConfiguration(i, this.configurationTransformer.mo626transform(readableMap));
            Action action = new Action() { // from class: com.amazon.alexa.accessorykit.accessories.session.input.-$$Lambda$InputRepositoryModule$eXxlxZE_44swmDWuoTMRqtj0kg0
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    Promise.this.resolve(null);
                }
            };
            promise.getClass();
            configuration.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
        } catch (ParseException e) {
            promise.reject("Bridge call inputRepositorySetConfiguration failed.", e);
        }
    }
}
