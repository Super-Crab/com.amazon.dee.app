package com.amazon.alexa.accessory.internal.bluetooth;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryDataBeaconRecord;
import com.amazon.alexa.accessory.AccessoryExplorer;
import com.amazon.alexa.accessory.AccessoryInquiryRecord;
import com.amazon.alexa.accessory.AccessoryScanRecord;
import com.amazon.alexa.accessory.AccessoryScanner;
import com.amazon.alexa.accessory.AccessoryScannerListener;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.internal.bluetooth.DefaultAccessoryScanner;
import com.amazon.alexa.accessory.internal.bluetooth.LowEnergyScanner;
import com.amazon.alexa.accessory.internal.util.AccessoryUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.StringSanitizer;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
public final class DefaultAccessoryScanner implements AccessoryScanner {
    private final AccessoryExplorer accessoryExplorer;
    private final DeviceSupplierV2 deviceSupplier;
    private final Map<AccessoryScannerListener, AccessoryExplorer.Observer> listenersMap;
    private final LowEnergyScanner.Listener lowEnergyScanListener;
    private final LowEnergyScanner lowEnergyScanner;
    private final StringSanitizer stringSanitizer;

    /* loaded from: classes.dex */
    private final class AccessoryExplorerAdapter implements AccessoryExplorer.Observer {
        private final AccessoryScannerListener listener;

        public AccessoryExplorerAdapter(AccessoryScannerListener accessoryScannerListener) {
            Preconditions.notNull(accessoryScannerListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.listener = accessoryScannerListener;
        }

        @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
        public void onKnownAccessoryFound(Accessory accessory) {
        }

        @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
        public void onKnownAccessoryLost(Accessory accessory) {
        }

        @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
        public void onUnknownAccessoryFound(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord) {
            Logger.d("Accessory Explorer has found an unknown accessory, accessory scanner is notifying this listener. accessory: %s, record: %s", accessory, accessoryInquiryRecord);
            this.listener.onConnectedAccessoryFound(accessory, accessoryInquiryRecord);
        }

        @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
        public void onUnknownAccessoryLost(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord) {
            Logger.d("Accessory Explorer has lost an unknown accessory, accessory scanner is notifying this listener. accessory: %s, record: %s", accessory, accessoryInquiryRecord);
            this.listener.onConnectedAccessoryLost(accessory, accessoryInquiryRecord);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class LowEnergyScanListener implements LowEnergyScanner.Listener {
        private LowEnergyScanListener() {
        }

        public /* synthetic */ void lambda$onPeripheralFound$1$DefaultAccessoryScanner$LowEnergyScanListener(AccessoryScanRecord accessoryScanRecord, int i, Accessory accessory) throws Throwable {
            DefaultAccessoryScanner.this.handleAccessoryFoundThroughScan(accessory, accessoryScanRecord, i);
        }

        @Override // com.amazon.alexa.accessory.internal.bluetooth.LowEnergyScanner.Listener
        public void onPeripheralFound(PeripheralDevice peripheralDevice, BleAdvertisementData bleAdvertisementData, final int i) {
            Preconditions.mainThread();
            AccessoryTransport.Type transportFromPeripheral = AccessoryUtils.getTransportFromPeripheral(peripheralDevice.getType());
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DefaultAccessoryScanner transportType: ");
            outline107.append(transportFromPeripheral.toString());
            Logger.d(outline107.toString());
            final AccessoryScanRecord accessoryScanRecord = null;
            try {
                Logger.d("DefaultAccessoryScanner scan data: ");
                accessoryScanRecord = DefaultAccessoryScanRecord.createFrom(bleAdvertisementData);
                if (accessoryScanRecord.prefersRFCOMMConnection() || accessoryScanRecord.isDiscoverableOverBTClassic()) {
                    transportFromPeripheral = AccessoryTransport.Type.RFCOMM;
                }
            } catch (IllegalArgumentException e) {
                Logger.v("Not able to create AccessoryScanRecord from advertisement data for device=[" + peripheralDevice + "]   transportType=[" + transportFromPeripheral + "]", e);
            }
            DefaultAccessoryScanner.this.filterOutKnownAccessory(new Accessory(peripheralDevice.getAddress(), transportFromPeripheral, DefaultAccessoryScanner.this.stringSanitizer.sanitize(peripheralDevice.getName()))).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryScanner$LowEnergyScanListener$UuOTsbpNs4sFZNO9jZrv8efuhD8
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DefaultAccessoryScanner.LowEnergyScanListener.this.lambda$onPeripheralFound$1$DefaultAccessoryScanner$LowEnergyScanListener(accessoryScanRecord, i, (Accessory) obj);
                }
            }, $$Lambda$DefaultAccessoryScanner$LowEnergyScanListener$6fvTG8m4vaxwjBlBYsRqmzfg0g.INSTANCE);
            try {
                AccessoryDataBeaconRecord createFrom = DefaultAccessoryDataBeaconRecord.createFrom(bleAdvertisementData);
                transportFromPeripheral = AccessoryTransport.Type.GATT;
                DefaultAccessoryScanner.this.handleDataBeaconFoundThroughScan(new Accessory(peripheralDevice.getAddress(), transportFromPeripheral, DefaultAccessoryScanner.this.stringSanitizer.sanitize(peripheralDevice.getName())), createFrom, i);
            } catch (IllegalArgumentException e2) {
                Logger.v("Not able to create AccessoryDataBeaconRecord from advertisement data for device=[" + peripheralDevice + "]   transportType=[" + transportFromPeripheral + "]", e2);
            }
        }

        @Override // com.amazon.alexa.accessory.internal.bluetooth.LowEnergyScanner.Listener
        public void onScanCancelled() {
            DefaultAccessoryScanner.this.notifyListeners($$Lambda$JhHUOaPpmqzL2q8WgnhfCfYHwHg.INSTANCE);
            DefaultAccessoryScanner.this.clearListenerMap();
        }

        @Override // com.amazon.alexa.accessory.internal.bluetooth.LowEnergyScanner.Listener
        public void onScanCompleted() {
            DefaultAccessoryScanner.this.lowEnergyScanner.startScan(this);
        }

        @Override // com.amazon.alexa.accessory.internal.bluetooth.LowEnergyScanner.Listener
        public void onScanFailed(final int i) {
            DefaultAccessoryScanner.this.notifyListeners(new com.amazon.alexa.accessory.internal.util.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryScanner$LowEnergyScanListener$KsMwVRQq_g4g1qFxO0VBdQKFc8k
                @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                public final void accept(Object obj) {
                    ((AccessoryScannerListener) obj).onAccessoryScanFailed(i);
                }
            });
            DefaultAccessoryScanner.this.clearListenerMap();
        }

        @Override // com.amazon.alexa.accessory.internal.bluetooth.LowEnergyScanner.Listener
        public void onScanStarted() {
        }
    }

    public DefaultAccessoryScanner(LowEnergyScanner lowEnergyScanner, DeviceSupplierV2 deviceSupplierV2, StringSanitizer stringSanitizer, AccessoryExplorer accessoryExplorer) {
        Preconditions.notNull(lowEnergyScanner, "lowEnergyScanner");
        Preconditions.notNull(deviceSupplierV2, "deviceSupplier");
        Preconditions.notNull(stringSanitizer, "stringSanitizer");
        Preconditions.notNull(accessoryExplorer, "accessoryExplorer");
        this.lowEnergyScanner = lowEnergyScanner;
        this.deviceSupplier = deviceSupplierV2;
        this.listenersMap = new HashMap();
        this.lowEnergyScanListener = new LowEnergyScanListener();
        this.stringSanitizer = stringSanitizer;
        this.accessoryExplorer = accessoryExplorer;
    }

    private void cancelScanInternal() {
        this.lowEnergyScanner.cancelScan(this.lowEnergyScanListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearListenerMap() {
        Preconditions.mainThread();
        Iterator<Map.Entry<AccessoryScannerListener, AccessoryExplorer.Observer>> it2 = this.listenersMap.entrySet().iterator();
        while (it2.hasNext()) {
            this.accessoryExplorer.cancel(it2.next().getValue());
            it2.remove();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Maybe<Accessory> filterOutKnownAccessory(final Accessory accessory) {
        return this.deviceSupplier.hasDeviceGroup(accessory.getAddress()).filter($$Lambda$DefaultAccessoryScanner$ZV8deflIPPjEEcjrg8eJ0bRs1lc.INSTANCE).map(new Function() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryScanner$zl0P-THitTIjlFMFYvbeTfuPHRY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Boolean bool = (Boolean) obj;
                return Accessory.this;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAccessoryFoundThroughScan(final Accessory accessory, final AccessoryScanRecord accessoryScanRecord, final int i) {
        Preconditions.mainThread();
        Logger.v("Accessory scanner notifying listeners: %s, scanRecord: %s, rssi: %d", accessory, accessoryScanRecord, Integer.valueOf(i));
        notifyListeners(new com.amazon.alexa.accessory.internal.util.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryScanner$MwpYPzjUuzmanLA0pZAdUswmXw8
            @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
            public final void accept(Object obj) {
                ((AccessoryScannerListener) obj).onBleAccessoryFoundNearby(Accessory.this, accessoryScanRecord, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDataBeaconFoundThroughScan(final Accessory accessory, final AccessoryDataBeaconRecord accessoryDataBeaconRecord, final int i) {
        Preconditions.mainThread();
        Logger.v("Accessory scanner notifying listeners: %s, dataBeaconRecord: %s, rssi: %d", accessory, accessoryDataBeaconRecord, Integer.valueOf(i));
        notifyListeners(new com.amazon.alexa.accessory.internal.util.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryScanner$VH_r_NezuVi-5qzGzkkVhBzb1io
            @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
            public final void accept(Object obj) {
                ((AccessoryScannerListener) obj).onBleDataBeaconFoundNearby(Accessory.this, accessoryDataBeaconRecord, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$filterOutKnownAccessory$0(Boolean bool) throws Throwable {
        return !bool.booleanValue();
    }

    static /* synthetic */ Accessory lambda$filterOutKnownAccessory$1(Accessory accessory, Boolean bool) throws Throwable {
        return accessory;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyListeners(com.amazon.alexa.accessory.internal.util.functions.Consumer<AccessoryScannerListener> consumer) {
        Preconditions.mainThread();
        for (AccessoryScannerListener accessoryScannerListener : new HashMap(this.listenersMap).keySet()) {
            consumer.accept(accessoryScannerListener);
        }
    }

    private boolean startScanInternal() {
        return this.lowEnergyScanner.startScan(this.lowEnergyScanListener);
    }

    @Override // com.amazon.alexa.accessory.AccessoryScanner
    public void cancelScan(AccessoryScannerListener accessoryScannerListener) {
        Preconditions.mainThread();
        Preconditions.notNull(accessoryScannerListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        if (!this.listenersMap.containsKey(accessoryScannerListener)) {
            return;
        }
        this.accessoryExplorer.cancel(this.listenersMap.remove(accessoryScannerListener));
        if (!this.listenersMap.isEmpty()) {
            return;
        }
        cancelScanInternal();
    }

    @Override // com.amazon.alexa.accessory.AccessoryScanner
    public boolean isScanning(AccessoryScannerListener accessoryScannerListener) {
        Preconditions.mainThread();
        Preconditions.notNull(accessoryScannerListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return this.listenersMap.containsKey(accessoryScannerListener);
    }

    @Override // com.amazon.alexa.accessory.AccessoryScanner
    public boolean startScan(AccessoryScannerListener accessoryScannerListener) {
        Preconditions.mainThread();
        Preconditions.notNull(accessoryScannerListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        if (this.listenersMap.containsKey(accessoryScannerListener)) {
            return true;
        }
        AccessoryExplorerAdapter accessoryExplorerAdapter = new AccessoryExplorerAdapter(accessoryScannerListener);
        this.accessoryExplorer.discover(accessoryExplorerAdapter);
        this.listenersMap.put(accessoryScannerListener, accessoryExplorerAdapter);
        accessoryScannerListener.onAccessoryScanStarted();
        if (this.listenersMap.size() != 1) {
            return true;
        }
        return startScanInternal();
    }
}
