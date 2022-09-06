package com.amazon.alexa.accessorykit.cloudpairing;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Cloudpairing;
import com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.text.ParseException;
/* loaded from: classes6.dex */
public class CloudPairingRepositoryModule {
    private final SessionSupplier clientSessionSupplier;
    private final MapModelTransformer<Cloudpairing.CloudPairingAttributes> cloudPairingAttributesTransformer;
    private final MapModelTransformer<Cloudpairing.CloudPairingKeys> cloudPairingKeysTransformer;
    private final MapModelTransformer<Cloudpairing.Seed> cloudPairingSeedTransformer;

    public CloudPairingRepositoryModule(SessionSupplier sessionSupplier, ContainerProvider containerProvider) {
        Preconditions.notNull(sessionSupplier, "clientSessionSupplier");
        Preconditions.notNull(containerProvider, "containerProvider");
        this.clientSessionSupplier = sessionSupplier;
        this.cloudPairingAttributesTransformer = new CloudPairingAttributesTransformer(containerProvider);
        this.cloudPairingKeysTransformer = new CloudPairingKeysTransformer();
        this.cloudPairingSeedTransformer = new CloudPairingSeedTransformer();
    }

    public void getCloudPairingAttributes(String str, final Promise promise) {
        Single<Cloudpairing.CloudPairingAttributes> cloudPairingAttributes = this.clientSessionSupplier.getSession(str).getCloudPairingRepository().getCloudPairingAttributes();
        final MapModelTransformer<Cloudpairing.CloudPairingAttributes> mapModelTransformer = this.cloudPairingAttributesTransformer;
        mapModelTransformer.getClass();
        Single<R> map = cloudPairingAttributes.map(new Function() { // from class: com.amazon.alexa.accessorykit.cloudpairing.-$$Lambda$33cxwfQKz5Mh87tjUKLktXyVR78
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MapModelTransformer.this.transformToMap((Cloudpairing.CloudPairingAttributes) obj);
            }
        });
        promise.getClass();
        Consumer consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.cloudpairing.-$$Lambda$FlWipKiXyqlqsdu4VNbVtXrTjl0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve((WritableMap) obj);
            }
        };
        promise.getClass();
        map.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    public void getCloudPairingStatus(String str, ReadableMap readableMap, final Promise promise) {
        try {
            Single<Cloudpairing.CloudPairingStatus> cloudPairingStatus = this.clientSessionSupplier.getSession(str).getCloudPairingRepository().getCloudPairingStatus(this.cloudPairingSeedTransformer.mo626transform(readableMap));
            Consumer<? super Cloudpairing.CloudPairingStatus> consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.cloudpairing.-$$Lambda$CloudPairingRepositoryModule$nDm0y7b1wFHU4WTKd2yjrMsJ-HQ
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    Promise.this.resolve(Boolean.valueOf(((Cloudpairing.CloudPairingStatus) obj).getPairingStatus()));
                }
            };
            promise.getClass();
            cloudPairingStatus.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
        } catch (ParseException e) {
            promise.reject("CloudPairingRepositoryModule: ParseException while trying to getPairingStatus", e);
        }
    }

    public void removeCloudPairingKeys(String str, ReadableMap readableMap, final Promise promise) {
        try {
            Completable removeCloudPairingKeys = this.clientSessionSupplier.getSession(str).getCloudPairingRepository().removeCloudPairingKeys(this.cloudPairingSeedTransformer.mo626transform(readableMap));
            Action action = new Action() { // from class: com.amazon.alexa.accessorykit.cloudpairing.-$$Lambda$CloudPairingRepositoryModule$3e1u0mEKUehjgY58I9ySLqRbcRM
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    Promise.this.resolve(null);
                }
            };
            promise.getClass();
            removeCloudPairingKeys.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
        } catch (ParseException e) {
            promise.reject("CloudPairingRepositoryModule: ParseException while trying to removeKeys", e);
        }
    }

    public void setCloudPairingKeys(String str, ReadableMap readableMap, final Promise promise) {
        try {
            Completable cloudPairingKeys = this.clientSessionSupplier.getSession(str).getCloudPairingRepository().setCloudPairingKeys(this.cloudPairingKeysTransformer.mo626transform(readableMap));
            Action action = new Action() { // from class: com.amazon.alexa.accessorykit.cloudpairing.-$$Lambda$CloudPairingRepositoryModule$l5RfzZ3Ipt72_DfTCMLWb6pU0-o
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    Promise.this.resolve(null);
                }
            };
            promise.getClass();
            cloudPairingKeys.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
        } catch (ParseException e) {
            promise.reject("CloudPairingRepositoryModule: ParseException while trying to setKeys", e);
        }
    }
}
