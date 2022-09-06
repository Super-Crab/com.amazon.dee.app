package com.amazon.alexa.mode.util;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public class AutomotiveAccessoryConnectivityObserver {
    private DeviceSupplierV2 deviceSupplier;
    private Single<Map<String, String>> deviceTypesSupplier;
    private BehaviorSubject<Boolean> mAutomotiveAccessoryConnected;
    private SessionSupplier sessionSupplier;

    private Observable<List<String>> activeSessionAddresses() {
        return Observable.concat(Observable.just(0), Observable.merge(this.sessionSupplier.observeSessionConnected().map($$Lambda$AutomotiveAccessoryConnectivityObserver$fr5HV0CaT4juD2XjT5D5e8A86kw.INSTANCE), this.sessionSupplier.observeSessionReleased().map($$Lambda$AutomotiveAccessoryConnectivityObserver$ubGw9qQMxmihSInxCPEu5b7Y5R4.INSTANCE))).flatMapSingle(new Function() { // from class: com.amazon.alexa.mode.util.-$$Lambda$AutomotiveAccessoryConnectivityObserver$HwyV9esThCB82rI1JvPiaHXPYVM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AutomotiveAccessoryConnectivityObserver.this.lambda$activeSessionAddresses$5$AutomotiveAccessoryConnectivityObserver((Integer) obj);
            }
        }).map($$Lambda$AutomotiveAccessoryConnectivityObserver$nkfCNkoq35wPhUi2oDEeAO0rX5E.INSTANCE).observeOn(AndroidSchedulers.mainThread());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Integer lambda$activeSessionAddresses$3(Accessory accessory) throws Throwable {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Integer lambda$activeSessionAddresses$4(Accessory accessory) throws Throwable {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$activeSessionAddresses$6(List list) throws Throwable {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(((Accessory) it2.next()).getAddress());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$null$1(Map map, List list) throws Throwable {
        HashMap hashMap = new HashMap();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            DeviceGroup deviceGroup = (DeviceGroup) it2.next();
            Device device = deviceGroup.getDevice();
            if (device != null && map.containsKey(device.getType())) {
                hashMap.put(deviceGroup.getIdentifier(), deviceGroup);
            }
        }
        return hashMap;
    }

    private Observable<Map<String, DeviceGroup>> querySetupAutomotiveDeviceGroups() {
        return this.deviceTypesSupplier.flatMapObservable(new Function() { // from class: com.amazon.alexa.mode.util.-$$Lambda$AutomotiveAccessoryConnectivityObserver$jwWdGAwo1NzTz_AsFvRfZvMs90E
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AutomotiveAccessoryConnectivityObserver.this.lambda$querySetupAutomotiveDeviceGroups$2$AutomotiveAccessoryConnectivityObserver((Map) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<DeviceGroup>> connectedAutomotiveDeviceGroups() {
        return Observable.combineLatest(querySetupAutomotiveDeviceGroups(), activeSessionAddresses(), new BiFunction() { // from class: com.amazon.alexa.mode.util.-$$Lambda$AutomotiveAccessoryConnectivityObserver$1bAN15wT1QaJ7JdqwxFB7i5A88A
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return AutomotiveAccessoryConnectivityObserver.this.lambda$connectedAutomotiveDeviceGroups$0$AutomotiveAccessoryConnectivityObserver((Map) obj, (List) obj2);
            }
        });
    }

    public void initialize(@NonNull Single<Map<String, String>> single, @NonNull SessionSupplier sessionSupplier, @NonNull DeviceSupplierV2 deviceSupplierV2) {
        this.mAutomotiveAccessoryConnected = BehaviorSubject.createDefault(false);
        this.deviceTypesSupplier = single;
        this.sessionSupplier = sessionSupplier;
        this.deviceSupplier = deviceSupplierV2;
    }

    public BehaviorSubject<Boolean> isAutomotiveAccessoryConnected() {
        return this.mAutomotiveAccessoryConnected;
    }

    public /* synthetic */ SingleSource lambda$activeSessionAddresses$5$AutomotiveAccessoryConnectivityObserver(Integer num) throws Throwable {
        return this.sessionSupplier.getActiveAccessories();
    }

    public /* synthetic */ List lambda$connectedAutomotiveDeviceGroups$0$AutomotiveAccessoryConnectivityObserver(Map map, List list) throws Throwable {
        ArrayList arrayList = new ArrayList();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            DeviceGroup deviceGroup = (DeviceGroup) map.get((String) it2.next());
            if (deviceGroup != null) {
                arrayList.add(deviceGroup);
            }
        }
        this.mAutomotiveAccessoryConnected.onNext(Boolean.valueOf(arrayList.size() > 0));
        return arrayList;
    }

    public /* synthetic */ ObservableSource lambda$querySetupAutomotiveDeviceGroups$2$AutomotiveAccessoryConnectivityObserver(final Map map) throws Throwable {
        return this.deviceSupplier.queryDeviceGroups().map(new Function() { // from class: com.amazon.alexa.mode.util.-$$Lambda$AutomotiveAccessoryConnectivityObserver$SMbS3OC0E2lYJ_hGg4j6QJomFF4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AutomotiveAccessoryConnectivityObserver.lambda$null$1(map, (List) obj);
            }
        });
    }
}
