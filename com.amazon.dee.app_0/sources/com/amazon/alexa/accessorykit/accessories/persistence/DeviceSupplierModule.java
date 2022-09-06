package com.amazon.alexa.accessorykit.accessories.persistence;

import android.annotation.SuppressLint;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.CurrentTimeMillisProvider;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRN;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRNEventId;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
/* loaded from: classes6.dex */
public class DeviceSupplierModule {
    private final DeviceSupplierV2 clientDeviceSupplierV2;
    private final CurrentTimeMillisProvider currentTimeMillisProvider;
    private final ArrayModelTransformer<List<DeviceGroup>> deviceGroupListMapper;
    private final MapModelTransformer<DeviceGroup> deviceGroupMapper;
    private final RxRN rxRN;

    public DeviceSupplierModule(ContainerProvider containerProvider, RxRN rxRN, DeviceSupplierV2 deviceSupplierV2, CurrentTimeMillisProvider currentTimeMillisProvider) {
        this(rxRN, deviceSupplierV2, currentTimeMillisProvider, new DeviceGroupModelTransformer(containerProvider, currentTimeMillisProvider), new DeviceGroupModelTransformer(containerProvider, currentTimeMillisProvider));
    }

    @SuppressLint({"CheckResult"})
    public void getDeviceGroup(String str, final Promise promise) {
        Single<DeviceGroup> deviceGroup = this.clientDeviceSupplierV2.getDeviceGroup(str);
        final MapModelTransformer<DeviceGroup> mapModelTransformer = this.deviceGroupMapper;
        mapModelTransformer.getClass();
        Single<R> map = deviceGroup.map(new Function() { // from class: com.amazon.alexa.accessorykit.accessories.persistence.-$$Lambda$8EkM1mmH1QnLezU4IOfYMP4KRNc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MapModelTransformer.this.transformToMap((DeviceGroup) obj);
            }
        });
        promise.getClass();
        Consumer consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.persistence.-$$Lambda$FlWipKiXyqlqsdu4VNbVtXrTjl0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve((WritableMap) obj);
            }
        };
        promise.getClass();
        map.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    public void getDeviceGroupCondition(String str, final Promise promise) {
        final long provideCurrentTimeMillis = this.currentTimeMillisProvider.provideCurrentTimeMillis();
        Single<R> map = this.clientDeviceSupplierV2.getDeviceGroup(str).map(new Function() { // from class: com.amazon.alexa.accessorykit.accessories.persistence.-$$Lambda$DeviceSupplierModule$yk6YmJd7sUmwvIc6HGNTqrU-cM8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                String str2;
                str2 = ((DeviceGroup) obj).getCondition(provideCurrentTimeMillis).toString();
                return str2;
            }
        });
        promise.getClass();
        Consumer consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.persistence.-$$Lambda$JXMiO5RS-1OjCh6hdfNXQ8VBx2E
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve((String) obj);
            }
        };
        promise.getClass();
        map.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @SuppressLint({"CheckResult"})
    public void hasDeviceGroup(String str, final Promise promise) {
        Single<Boolean> hasDeviceGroup = this.clientDeviceSupplierV2.hasDeviceGroup(str);
        promise.getClass();
        Consumer<? super Boolean> consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.persistence.-$$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve((Boolean) obj);
            }
        };
        promise.getClass();
        hasDeviceGroup.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    public void queryDeviceGroups(ReadableMap readableMap) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.deviceGroupListMapper, this.clientDeviceSupplierV2.queryDeviceGroups());
    }

    @SuppressLint({"CheckResult"})
    public void removeDeviceGroup(long j, final Promise promise) {
        Completable removeDeviceGroup = this.clientDeviceSupplierV2.removeDeviceGroup(j);
        Action action = new Action() { // from class: com.amazon.alexa.accessorykit.accessories.persistence.-$$Lambda$DeviceSupplierModule$8SoK-KO1Xib3VnA57grI8Ui3slM
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(null);
            }
        };
        promise.getClass();
        removeDeviceGroup.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @VisibleForTesting
    DeviceSupplierModule(RxRN rxRN, DeviceSupplierV2 deviceSupplierV2, CurrentTimeMillisProvider currentTimeMillisProvider, ArrayModelTransformer<List<DeviceGroup>> arrayModelTransformer, MapModelTransformer<DeviceGroup> mapModelTransformer) {
        Preconditions.notNull(rxRN, "rxRN");
        Preconditions.notNull(deviceSupplierV2, "clientDeviceSupplierV2");
        Preconditions.notNull(currentTimeMillisProvider, "currentTimeMillisProvider");
        Preconditions.notNull(arrayModelTransformer, "deviceGroupListMapper");
        Preconditions.notNull(mapModelTransformer, "deviceGroupMapper");
        this.rxRN = rxRN;
        this.clientDeviceSupplierV2 = deviceSupplierV2;
        this.currentTimeMillisProvider = currentTimeMillisProvider;
        this.deviceGroupListMapper = arrayModelTransformer;
        this.deviceGroupMapper = mapModelTransformer;
    }
}
