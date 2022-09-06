package com.amazon.alexa.accessory.internal.interactor;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.Interactor;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.internal.PeripheralDevices;
import com.amazon.alexa.accessory.internal.interactor.DeviceInteractor;
import com.amazon.alexa.accessory.internal.monitor.BluetoothBondMonitor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.monitor.BluetoothStateMonitor;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public final class DeviceInteractor implements Interactor {
    private final AccessorySessionListener accessorySessionListener;
    private boolean active;
    private final BluetoothBondMonitor bluetoothBondMonitor;
    private final BluetoothBondMonitor.Observer bluetoothBondObserver;
    private final BluetoothStateMonitor bluetoothStateMonitor;
    private final BluetoothStateMonitor.Observer bluetoothStateObserver;
    private final DeviceSupplierV2 deviceSupplier;
    private Disposable disposable;
    private final SessionSupplier sessionSupplier;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.accessory.internal.interactor.DeviceInteractor$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements Function<List<DeviceGroup>, List<DeviceGroup>> {
        private Set<Long> previousDeviceGroupIds = new HashSet();

        AnonymousClass1() {
        }

        public /* synthetic */ boolean lambda$apply$0$DeviceInteractor$1(DeviceGroup deviceGroup) throws Throwable {
            return !this.previousDeviceGroupIds.contains(Long.valueOf(deviceGroup.getId()));
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply  reason: avoid collision after fix types in other method */
        public List<DeviceGroup> mo10358apply(@NonNull List<DeviceGroup> list) throws Exception {
            List<DeviceGroup> list2 = (List) Observable.fromIterable(list).filter(new Predicate() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$1$rd92YsZZMJC4h3FGJyU-N13GCdo
                @Override // io.reactivex.rxjava3.functions.Predicate
                public final boolean test(Object obj) {
                    return DeviceInteractor.AnonymousClass1.this.lambda$apply$0$DeviceInteractor$1((DeviceGroup) obj);
                }
            }).toList().blockingGet();
            this.previousDeviceGroupIds = new HashSet((Collection) Observable.fromIterable(list).map($$Lambda$nXbGl_Fend_M8Xhczr8WDr7GTYo.INSTANCE).toList().blockingGet());
            return list2;
        }
    }

    /* loaded from: classes.dex */
    private final class AccessoryInteractorSessionListener extends AccessorySessionListener {
        private AccessoryInteractorSessionListener() {
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        @SuppressLint({"CheckResult"})
        public void onAccessorySessionConnected(final Accessory accessory) {
            Single<DeviceGroup> observeOn = DeviceInteractor.this.deviceSupplier.getDeviceGroup(accessory.getAddress()).observeOn(AndroidSchedulers.mainThread());
            final DeviceInteractor deviceInteractor = DeviceInteractor.this;
            observeOn.flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$AccessoryInteractorSessionListener$UdX93pb7Mafw0wOQR-E45_6C-eA
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return DeviceInteractor.this.keepDeviceGroupInformationUpdated((DeviceGroup) obj);
                }
            }).subscribe(new Action() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$AccessoryInteractorSessionListener$8_Afitc9nIU-ZkEDJuwm5qBbRXU
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    Logger.d("Finished updating Device information for %s during a session", Accessory.this);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$AccessoryInteractorSessionListener$qBowlVhtR0CNpow7RetFv9Tz_KA
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    Logger.e("Unable to update device information for (%s)", (Throwable) obj, Accessory.this);
                }
            });
        }

        /* synthetic */ AccessoryInteractorSessionListener(DeviceInteractor deviceInteractor, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class BluetoothBondObserver implements BluetoothBondMonitor.Observer {
        private BluetoothBondObserver() {
        }

        @SuppressLint({"CheckResult"})
        private void removeUnpairedDeviceGroup(final PeripheralDevice peripheralDevice) {
            Logger.d("DeviceInteractor checking if we need to remove a device with address %s from device supplier due to its Bluetooth bond being removed", peripheralDevice.getAddress());
            Maybe<DeviceGroup> filter = DeviceInteractor.this.deviceSupplier.getDeviceGroup(peripheralDevice.getAddress()).filter(new Predicate() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$BluetoothBondObserver$-6JPQdYodFPEG9imTtzRSMdlHvo
                @Override // io.reactivex.rxjava3.functions.Predicate
                public final boolean test(Object obj) {
                    return DeviceInteractor.BluetoothBondObserver.this.lambda$removeUnpairedDeviceGroup$0$DeviceInteractor$BluetoothBondObserver((DeviceGroup) obj);
                }
            });
            final DeviceSupplierV2 deviceSupplierV2 = DeviceInteractor.this.deviceSupplier;
            deviceSupplierV2.getClass();
            filter.flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$1EizC4Vi2mO6G-W-nl9x7lz68mw
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return DeviceSupplierV2.this.removeDeviceGroup((DeviceGroup) obj);
                }
            }).subscribe(new Action() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$BluetoothBondObserver$txBmdbIwMNAqEpqbXLDU7rJNR-o
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    Logger.d("DeviceInteractor device with address %s was removed from device supplier due to its Bluetooth bond being removed", PeripheralDevice.this.getAddress());
                }
            }, $$Lambda$DeviceInteractor$BluetoothBondObserver$RvOWPK9apizcxF29bsDzNfT6HMI.INSTANCE);
        }

        public /* synthetic */ boolean lambda$removeUnpairedDeviceGroup$0$DeviceInteractor$BluetoothBondObserver(DeviceGroup deviceGroup) throws Throwable {
            return DeviceInteractor.this.active;
        }

        @Override // com.amazon.alexa.accessory.internal.monitor.BluetoothBondMonitor.Observer
        public void onBondRemoved(PeripheralDevice peripheralDevice) {
            Logger.d("DeviceInteractor notified that bond was removed for %s", peripheralDevice.getAddress());
            removeUnpairedDeviceGroup(peripheralDevice);
        }

        @Override // com.amazon.alexa.accessory.internal.monitor.BluetoothBondMonitor.Observer
        public void onBondedAdded(PeripheralDevice peripheralDevice) {
            Logger.d("DeviceInteractor notified that bond was added for %s", peripheralDevice.getAddress());
        }

        /* synthetic */ BluetoothBondObserver(DeviceInteractor deviceInteractor, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* loaded from: classes.dex */
    private final class BluetoothStateObserver implements BluetoothStateMonitor.Observer {
        private BluetoothStateObserver() {
        }

        @Override // com.amazon.alexa.accessory.monitor.BluetoothStateMonitor.Observer
        public void onBluetoothDisabled() {
            Logger.d("DeviceInteractor notified that bluetooth is disabled");
        }

        @Override // com.amazon.alexa.accessory.monitor.BluetoothStateMonitor.Observer
        public void onBluetoothEnabled() {
            Logger.d("DeviceInteractor notified that bluetooth is enabled");
            DeviceInteractor.this.removeUnpairedDeviceGroups();
        }

        /* synthetic */ BluetoothStateObserver(DeviceInteractor deviceInteractor, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public DeviceInteractor(DeviceSupplierV2 deviceSupplierV2, SessionSupplier sessionSupplier, BluetoothBondMonitor bluetoothBondMonitor, BluetoothStateMonitor bluetoothStateMonitor) {
        Preconditions.notNull(deviceSupplierV2, "deviceSupplier");
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(bluetoothBondMonitor, "bluetoothBondMonitor");
        Preconditions.notNull(bluetoothStateMonitor, "bluetoothStateMonitor");
        this.bluetoothBondMonitor = bluetoothBondMonitor;
        this.bluetoothStateMonitor = bluetoothStateMonitor;
        this.deviceSupplier = deviceSupplierV2;
        this.sessionSupplier = sessionSupplier;
        this.bluetoothBondObserver = new BluetoothBondObserver(this, null);
        this.bluetoothStateObserver = new BluetoothStateObserver(this, null);
        this.accessorySessionListener = new AccessoryInteractorSessionListener(this, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<Device> deviceInformationSetToDevices(Set<Device.DeviceInformation> set) {
        ArrayList arrayList = new ArrayList();
        for (Device.DeviceInformation deviceInformation : set) {
            arrayList.add(new Device.Builder().deviceId(Integer.valueOf(deviceInformation.getDeviceId())).name(deviceInformation.getName()).serialNumber(deviceInformation.getSerialNumber()).type(deviceInformation.getDeviceType()).color(Integer.valueOf(deviceInformation.getProductColor())).build());
        }
        return arrayList;
    }

    private Function<List<DeviceGroup>, List<DeviceGroup>> getAddedDeviceGroups() {
        return new AnonymousClass1();
    }

    private Single<DeviceRepositoryV2> getDeviceRepository(DeviceGroup deviceGroup) {
        AccessorySession session = this.sessionSupplier.getSession(new Accessory(deviceGroup.getIdentifier(), deviceGroup.getTransportType()));
        if (session == null) {
            return Single.error(new IllegalStateException("DeviceInteractor cannot get device repository. No session for " + deviceGroup));
        }
        return Single.just(session.getDeviceRepositoryV2());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<DeviceGroup> getUnpairedDeviceGroups(List<DeviceGroup> list) {
        final PeripheralDevices bondedDevices = this.bluetoothBondMonitor.getBondedDevices();
        if (bondedDevices != null && bondedDevices.areAvailable()) {
            return Observable.fromIterable(list).filter(new Predicate() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$mbBgvhYmp2XhVHAElJbxiGAVEqo
                @Override // io.reactivex.rxjava3.functions.Predicate
                public final boolean test(Object obj) {
                    return DeviceInteractor.lambda$getUnpairedDeviceGroups$12(PeripheralDevices.this, (DeviceGroup) obj);
                }
            });
        }
        return Observable.error(new IllegalStateException("Bonded devices are not available"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Completable keepDeviceGroupInformationUpdated(final DeviceGroup deviceGroup) {
        return getDeviceRepository(deviceGroup).flatMapObservable($$Lambda$2v2_5L05HBG2KNqNXx7ppY4Hg.INSTANCE).firstOrError().map($$Lambda$DeviceInteractor$cyRcbRjXaDNVbsOkvnbjwkimHs.INSTANCE).map(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$nmhn81Nj0VNyXjUCNTV1VaZrzOI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                DeviceGroup build;
                build = new DeviceGroup.Builder(DeviceGroup.this).setConditionActive().devices((List) obj).build();
                return build;
            }
        }).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$2oxL0_lgTuCEsANaEqOAZzR7Cgg
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DeviceInteractor.this.lambda$keepDeviceGroupInformationUpdated$6$DeviceInteractor((DeviceGroup) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$getUnpairedDeviceGroups$12(PeripheralDevices peripheralDevices, DeviceGroup deviceGroup) throws Throwable {
        return !peripheralDevices.contains(new PeripheralDevice(deviceGroup));
    }

    private void observeLinkedDevices() {
        this.disposable = this.deviceSupplier.queryDeviceGroups().observeOn(AndroidSchedulers.mainThread()).map(getAddedDeviceGroups()).switchMap($$Lambda$tNLLyz36wpjmL1kezURjOHIEA.INSTANCE).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$7SQ9vIgX_uSR--r7WRkBn0J1k2g
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DeviceInteractor.this.lambda$observeLinkedDevices$0$DeviceInteractor((DeviceGroup) obj);
            }
        }).subscribe($$Lambda$DeviceInteractor$p3XQ5DgkMwQqx6wboXiTeEKPxEI.INSTANCE, $$Lambda$DeviceInteractor$8Ipa7nK1BvFybDSqvzGX8Q7FTs.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    public void removeUnpairedDeviceGroups() {
        this.deviceSupplier.queryDeviceGroups().firstOrError().filter(new Predicate() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$86XZKkxj2RrX0Wi-6f7uwGAi4DE
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return DeviceInteractor.this.lambda$removeUnpairedDeviceGroups$7$DeviceInteractor((List) obj);
            }
        }).flatMapObservable(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$lFB6Nq7EhMdGu6YdCzmCzuSfNiw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Observable unpairedDeviceGroups;
                unpairedDeviceGroups = DeviceInteractor.this.getUnpairedDeviceGroups((List) obj);
                return unpairedDeviceGroups;
            }
        }).doOnNext($$Lambda$DeviceInteractor$ySG_ONdZD8IIabzL1cCwuFhxVH4.INSTANCE).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$BkNQh73i7-DzQMvl-ViL9AKIRMc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DeviceInteractor.this.lambda$removeUnpairedDeviceGroups$9$DeviceInteractor((DeviceGroup) obj);
            }
        }).subscribe($$Lambda$DeviceInteractor$yb9OgFh1xj3oTNf9Bsc_7EkCrs.INSTANCE, $$Lambda$DeviceInteractor$_uXYw8FWqUbbyD9UDqbWtPoCVs8.INSTANCE);
    }

    @Override // com.amazon.alexa.accessory.Interactor
    public void activate() {
        Preconditions.mainThread();
        if (this.active) {
            return;
        }
        this.active = true;
        Logger.d("DeviceInteractor activating");
        observeLinkedDevices();
        removeUnpairedDeviceGroups();
        this.sessionSupplier.addSessionListener(this.accessorySessionListener);
        this.bluetoothStateMonitor.addObserver(this.bluetoothStateObserver);
        this.bluetoothBondMonitor.addObserver(this.bluetoothBondObserver);
    }

    @Override // com.amazon.alexa.accessory.Interactor
    public void deactivate() {
        Preconditions.mainThread();
        if (!this.active) {
            return;
        }
        this.active = false;
        Logger.d("DeviceInteractor deactivating");
        this.sessionSupplier.removeSessionListener(this.accessorySessionListener);
        this.bluetoothStateMonitor.removeObserver(this.bluetoothStateObserver);
        this.bluetoothBondMonitor.removeObserver(this.bluetoothBondObserver);
        ObservableUtils.dispose(this.disposable);
    }

    public /* synthetic */ CompletableSource lambda$keepDeviceGroupInformationUpdated$6$DeviceInteractor(final DeviceGroup deviceGroup) throws Throwable {
        return this.deviceSupplier.updateDeviceGroup(deviceGroup).doOnComplete(new Action() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$wMll1mQyRo689Fy_v3P9xKyiMFU
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Logger.d("DeviceInteractor successfully updated device supplier with device information for %s.", DeviceGroup.this);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$nTjQBdSPv3JkdSqeYDXDy8VqRXc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Logger.e("DeviceInteractor unable to update device information for %s. This is expected if the device is not yet linked and does not impact connectivity.", DeviceGroup.this, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ CompletableSource lambda$observeLinkedDevices$0$DeviceInteractor(DeviceGroup deviceGroup) throws Throwable {
        return keepDeviceGroupInformationUpdated(deviceGroup).onErrorComplete();
    }

    public /* synthetic */ boolean lambda$removeUnpairedDeviceGroups$7$DeviceInteractor(List list) throws Throwable {
        return this.active;
    }

    public /* synthetic */ CompletableSource lambda$removeUnpairedDeviceGroups$9$DeviceInteractor(DeviceGroup deviceGroup) throws Throwable {
        return this.deviceSupplier.removeDeviceGroup(deviceGroup);
    }
}
