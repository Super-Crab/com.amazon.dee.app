package com.amazon.alexa.accessorykit.accessories.accessory;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.AccessoryModelTransformer;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import java.text.ParseException;
/* loaded from: classes6.dex */
public final class AccessorySupplierModule {
    private final MapModelTransformer<Accessory> accessoryTransformer;
    private final Accessories clientAccessories;

    public AccessorySupplierModule(ContainerProvider containerProvider, Accessories accessories) {
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(accessories, "clientAccessories");
        this.accessoryTransformer = new AccessoryModelTransformer(containerProvider);
        this.clientAccessories = accessories;
    }

    public void link(ReadableMap readableMap, final Promise promise) {
        try {
            Completable linkAccessory = this.clientAccessories.linkAccessory(this.accessoryTransformer.mo626transform(readableMap));
            Action action = new Action() { // from class: com.amazon.alexa.accessorykit.accessories.accessory.-$$Lambda$AccessorySupplierModule$85zlhWjEJNXckrSghtoU6tf-mf8
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    Promise.this.resolve(null);
                }
            };
            promise.getClass();
            linkAccessory.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
        } catch (ParseException e) {
            promise.reject(e);
        }
    }

    public void unlink(ReadableMap readableMap, final Promise promise) {
        try {
            Completable unlinkAccessory = this.clientAccessories.unlinkAccessory(this.accessoryTransformer.mo626transform(readableMap));
            Action action = new Action() { // from class: com.amazon.alexa.accessorykit.accessories.accessory.-$$Lambda$AccessorySupplierModule$EXbCrLKKzpMeTrdxf6_NIm4Tv3E
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    Promise.this.resolve(null);
                }
            };
            promise.getClass();
            unlinkAccessory.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
        } catch (ParseException e) {
            promise.reject(e);
        }
    }
}
