package com.amazon.alexa.accessory.internal.bluetooth;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.ParcelUuid;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryAttributes;
import com.amazon.alexa.accessory.AccessoryExplorer;
import com.amazon.alexa.accessory.AccessoryInquiryRecord;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.AccessorySupplier;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.internal.bluetooth.DefaultAccessoryExplorer;
import com.amazon.alexa.accessory.internal.bluetooth.SdpRepository;
import com.amazon.alexa.accessory.internal.bluetooth.spp.SppSocketSupplier;
import com.amazon.alexa.accessory.internal.monitor.AclDeviceMonitor;
import com.amazon.alexa.accessory.internal.monitor.BluetoothProfileMonitor;
import com.amazon.alexa.accessory.internal.monitor.DefaultAclDeviceMonitor;
import com.amazon.alexa.accessory.internal.monitor.DefaultBluetoothProfileMonitor;
import com.amazon.alexa.accessory.internal.monitor.DefaultGattDeviceMonitor;
import com.amazon.alexa.accessory.internal.monitor.GattDeviceMonitor;
import com.amazon.alexa.accessory.internal.monitor.SppSocketConnectionMonitor;
import com.amazon.alexa.accessory.internal.repositories.DefaultPeripheralDeviceRepository;
import com.amazon.alexa.accessory.internal.repositories.PeripheralDeviceRepository;
import com.amazon.alexa.accessory.internal.session.AccessoryInquirySession;
import com.amazon.alexa.accessory.internal.util.AccessoryUtils;
import com.amazon.alexa.accessory.internal.util.ListUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.RedactionUtil;
import com.amazon.alexa.accessory.internal.util.functions.Consumer;
import com.amazon.alexa.accessory.monitor.BluetoothStateMonitor;
import com.amazon.alexa.accessory.monitor.DefaultBluetoothStateMonitor;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class DefaultAccessoryExplorer implements AccessoryExplorer {
    private final AccessoryAttributes accessoryAttributes;
    private final AccessorySupplier accessorySupplier;
    private final AclDeviceMonitor aclDeviceMonitor;
    private final List<String> blockedNamePrefixes;
    private final BluetoothProfileMonitor bluetoothProfileMonitor;
    private final BluetoothStateMonitor bluetoothStateMonitor;
    private final BluetoothStateMonitor.Observer bluetoothStateObserver;
    private CompositeDisposable compositeDisposable;
    private final Map<Accessory, AccessoryInquiryRecord> connectedAccessories;
    private final Map<Accessory, AccessoryInquiryRecord> connectedUnknownAccessories;
    private final Set<Accessory> connectedUnknownUnidentifiedAccessories;
    private final Context context;
    private final DeviceConnectivityObserver deviceConnectivityObserver;
    private final DeviceSupplierV2 deviceSupplier;
    private final Map<Accessory, AccessoryInquiryRecord> disconnectedCachedRecords;
    private final GattDeviceMonitor gattDeviceMonitor;
    private final GattDeviceMonitor.Observer gattDeviceObserver;
    private final AccessoryInquirySession.Factory inquirySessionFactory;
    private final PublishSubject<Set<String>> leConnections;
    private final Set<String> observedGattDevices;
    private final Set<AccessoryExplorer.Observer> observers;
    private boolean observing;
    private final PeripheralDeviceRepository peripheralDeviceRepository;
    private final SdpRepository sdpRepository;
    private final AccessorySessionListener sessionListener;
    private final SessionSupplier sessionSupplier;
    private final boolean shouldExcludeInquirySession;
    private final SppSocketSupplier sppSocketSupplier;

    /* loaded from: classes.dex */
    private final class BluetoothStateMonitorObserver implements BluetoothStateMonitor.Observer {
        private BluetoothStateMonitorObserver() {
        }

        @Override // com.amazon.alexa.accessory.monitor.BluetoothStateMonitor.Observer
        public void onBluetoothDisabled() {
            DefaultAccessoryExplorer.this.stopObservingDevices();
        }

        @Override // com.amazon.alexa.accessory.monitor.BluetoothStateMonitor.Observer
        public void onBluetoothEnabled() {
            DefaultAccessoryExplorer.this.startObservingDevices();
        }
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        AccessoryAttributes accessoryAttributes;
        AccessorySupplier accessorySupplier;
        AclDeviceMonitor aclDeviceMonitor;
        List<String> blockedNamePrefixes;
        BluetoothProfileMonitor bluetoothProfileMonitor;
        BluetoothStateMonitor bluetoothStateMonitor;
        Context context;
        DeviceSupplierV2 deviceSupplier;
        GattDeviceMonitor gattDeviceMonitor;
        AccessoryInquirySession.Factory inquirySessionFactory;
        PeripheralDeviceRepository peripheralDeviceRepository;
        SdpRepository sdpRepository;
        SessionSupplier sessionSupplier;
        boolean shouldExcludeInquirySession;
        SppSocketSupplier sppSocketSupplier;

        public Builder accessoryAttributes(AccessoryAttributes accessoryAttributes) {
            this.accessoryAttributes = accessoryAttributes;
            return this;
        }

        public Builder accessorySupplier(AccessorySupplier accessorySupplier) {
            this.accessorySupplier = accessorySupplier;
            return this;
        }

        public Builder aclDeviceMonitor(AclDeviceMonitor aclDeviceMonitor) {
            this.aclDeviceMonitor = aclDeviceMonitor;
            return this;
        }

        public Builder blockedNamePrefixes(List<String> list) {
            this.blockedNamePrefixes = list;
            return this;
        }

        public Builder bluetoothProfileMonitor(BluetoothProfileMonitor bluetoothProfileMonitor) {
            this.bluetoothProfileMonitor = bluetoothProfileMonitor;
            return this;
        }

        public Builder bluetoothStateMonitor(BluetoothStateMonitor bluetoothStateMonitor) {
            this.bluetoothStateMonitor = bluetoothStateMonitor;
            return this;
        }

        public DefaultAccessoryExplorer build() {
            Preconditions.notNull(this.context, "context");
            Preconditions.notNull(this.accessorySupplier, "accessorySupplier");
            Preconditions.notNull(this.deviceSupplier, "deviceSupplier");
            Preconditions.notNull(this.sessionSupplier, "sessionSupplier");
            Preconditions.notNull(this.inquirySessionFactory, "inquirySessionFactory");
            Preconditions.notNull(this.accessoryAttributes, "accessoryAttributes");
            Preconditions.notNull(this.sppSocketSupplier, "sppSocketSupplier");
            if (this.peripheralDeviceRepository == null) {
                this.peripheralDeviceRepository = new DefaultPeripheralDeviceRepository(this.context);
            }
            if (this.bluetoothStateMonitor == null) {
                this.bluetoothStateMonitor = new DefaultBluetoothStateMonitor(this.context);
            }
            if (this.aclDeviceMonitor == null) {
                this.aclDeviceMonitor = new DefaultAclDeviceMonitor(this.context);
            }
            if (this.gattDeviceMonitor == null) {
                this.gattDeviceMonitor = new DefaultGattDeviceMonitor(this.context);
            }
            if (this.bluetoothProfileMonitor == null) {
                this.bluetoothProfileMonitor = new DefaultBluetoothProfileMonitor(this.context);
            }
            if (this.sdpRepository == null) {
                this.sdpRepository = new DefaultSdpRepository(this.context);
            }
            if (this.blockedNamePrefixes == null) {
                this.blockedNamePrefixes = Collections.emptyList();
            }
            return new DefaultAccessoryExplorer(this);
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder deviceSupplier(DeviceSupplierV2 deviceSupplierV2) {
            this.deviceSupplier = deviceSupplierV2;
            return this;
        }

        public Builder gattDeviceMonitor(GattDeviceMonitor gattDeviceMonitor) {
            this.gattDeviceMonitor = gattDeviceMonitor;
            return this;
        }

        public Builder inquirySessionFactory(AccessoryInquirySession.Factory factory) {
            this.inquirySessionFactory = factory;
            return this;
        }

        public Builder peripheralDeviceRepository(PeripheralDeviceRepository peripheralDeviceRepository) {
            this.peripheralDeviceRepository = peripheralDeviceRepository;
            return this;
        }

        public Builder sdpRepository(SdpRepository sdpRepository) {
            this.sdpRepository = sdpRepository;
            return this;
        }

        public Builder sessionSupplier(SessionSupplier sessionSupplier) {
            this.sessionSupplier = sessionSupplier;
            return this;
        }

        public Builder shouldExcludeInquirySession(boolean z) {
            this.shouldExcludeInquirySession = z;
            return this;
        }

        public Builder sppSocketSupplier(SppSocketSupplier sppSocketSupplier) {
            this.sppSocketSupplier = sppSocketSupplier;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class DeviceConnectivityObserver implements PeripheralDeviceRepository.Callback, AclDeviceMonitor.Observer, BluetoothProfileMonitor.Observer, SppSocketConnectionMonitor.Listener {
        private final Set<Accessory> inquirySessionsInProgress = new HashSet();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.amazon.alexa.accessory.internal.bluetooth.DefaultAccessoryExplorer$DeviceConnectivityObserver$1  reason: invalid class name */
        /* loaded from: classes.dex */
        public class AnonymousClass1 implements SdpRepository.FetchListener {
            AnonymousClass1() {
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ void lambda$onSuccess$4(Accessory accessory, Throwable th) throws Throwable {
                Logger.d("ERROR: DefaultAccessoryExplorer: Error determining whether to do inquiry for this accessory: %s", th, accessory);
                Logger.e("DefaultAccessoryExplorer: Error determining whether to do inquiry for this accessory: %s", th, RedactionUtil.redact(accessory));
            }

            public /* synthetic */ void lambda$null$2$DefaultAccessoryExplorer$DeviceConnectivityObserver$1(final Accessory accessory, final AccessoryInquiryRecord accessoryInquiryRecord) {
                DefaultAccessoryExplorer.this.connectedUnknownAccessories.put(accessory, accessoryInquiryRecord);
                DefaultAccessoryExplorer.this.notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$1$g--6UmRbg6ycmoR_w9Na1Veu_kY
                    @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                    public final void accept(Object obj) {
                        ((AccessoryExplorer.Observer) obj).onUnknownAccessoryFound(Accessory.this, accessoryInquiryRecord);
                    }
                });
            }

            public /* synthetic */ void lambda$onSuccess$3$DefaultAccessoryExplorer$DeviceConnectivityObserver$1(final Accessory accessory, Boolean bool) throws Throwable {
                Logger.d("Determined if inquiry record can be requested currently: " + bool);
                if (bool.booleanValue()) {
                    DeviceConnectivityObserver.this.fetchAccessoryInquiryRecord(accessory, new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$1$n4H69GWtUNR33NbwO7QCHO6uMTU
                        @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                        public final void accept(Object obj) {
                            DefaultAccessoryExplorer.DeviceConnectivityObserver.AnonymousClass1.this.lambda$null$2$DefaultAccessoryExplorer$DeviceConnectivityObserver$1(accessory, (AccessoryInquiryRecord) obj);
                        }
                    });
                }
            }

            @Override // com.amazon.alexa.accessory.internal.bluetooth.SdpRepository.FetchListener
            public void onError(PeripheralDevice peripheralDevice, Throwable th) {
                Logger.d("ERROR: DefaultAccessoryExplorer: Error fetching SDP records for peripheral", th, peripheralDevice);
                Logger.e("DefaultAccessoryExplorer: Error fetching SDP records for peripheral", th, RedactionUtil.redact(peripheralDevice));
            }

            @Override // com.amazon.alexa.accessory.internal.bluetooth.SdpRepository.FetchListener
            public void onSuccess(PeripheralDevice peripheralDevice, SdpRecords sdpRecords) {
                final Accessory accessoryFromPeripheral = AccessoryUtils.getAccessoryFromPeripheral(peripheralDevice);
                if (sdpRecords.containsUuid(DefaultAccessoryExplorer.this.accessoryAttributes.rfcommChannelId())) {
                    if (DefaultAccessoryExplorer.this.shouldExcludeInquirySession) {
                        DefaultAccessoryExplorer.this.connectedUnknownUnidentifiedAccessories.add(accessoryFromPeripheral);
                        DefaultAccessoryExplorer.this.notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$1$zqDWZZueKSMtIVaiNpehZ2b05-A
                            @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                            public final void accept(Object obj) {
                                ((AccessoryExplorer.Observer) obj).onUnknownAccessoryFound(Accessory.this, null);
                            }
                        });
                        return;
                    }
                    DefaultAccessoryExplorer.this.compositeDisposable.add(DeviceConnectivityObserver.this.shouldRequestInquiryRecord(accessoryFromPeripheral).subscribe(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$1$rK4Zk9BNeXdUOEbo0R1dt3Ip1DU
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        public final void accept(Object obj) {
                            DefaultAccessoryExplorer.DeviceConnectivityObserver.AnonymousClass1.this.lambda$onSuccess$3$DefaultAccessoryExplorer$DeviceConnectivityObserver$1(accessoryFromPeripheral, (Boolean) obj);
                        }
                    }, new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$1$Mr83HzcFrzFswaNSXenMfLSstoA
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        public final void accept(Object obj) {
                            DefaultAccessoryExplorer.DeviceConnectivityObserver.AnonymousClass1.lambda$onSuccess$4(Accessory.this, (Throwable) obj);
                        }
                    }));
                    return;
                }
                Logger.d("DefaultAccessoryExplorer: Connected peripheral %s does not have the expected UUID %s in SDP records.", peripheralDevice, DefaultAccessoryExplorer.this.accessoryAttributes.rfcommChannelId());
            }
        }

        public DeviceConnectivityObserver() {
        }

        private Single<Boolean> activeSessionExistsThatNeedsTransportUpgrade() {
            return Observable.defer(new Supplier() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$z-ZV4qk0JDVJyTjc6QdXanwtpQU
                @Override // io.reactivex.rxjava3.functions.Supplier
                /* renamed from: get */
                public final Object mo10365get() {
                    return DefaultAccessoryExplorer.DeviceConnectivityObserver.this.lambda$activeSessionExistsThatNeedsTransportUpgrade$15$DefaultAccessoryExplorer$DeviceConnectivityObserver();
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).flatMap($$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$NzOyndGuMFIbSRzrKOsiYEGpfDU.INSTANCE).filter($$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$tAug8n0dctgEGpzrvjKHx0TjXGE.INSTANCE).first(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void fetchAccessoryInquiryRecord(final Accessory accessory, final Consumer<AccessoryInquiryRecord> consumer) {
            Logger.d("DefaultAccessoryExplorer: Fetching inquiry record for %s", accessory);
            AccessoryInquiryRecord accessoryInquiryRecord = (AccessoryInquiryRecord) DefaultAccessoryExplorer.this.connectedUnknownAccessories.get(accessory);
            if (accessoryInquiryRecord != null) {
                Logger.d("DefaultAccessoryExplorer: Unknown accessory already cached. %s", accessory);
                consumer.accept(accessoryInquiryRecord);
                return;
            }
            Logger.d("Beginning inquiry session for accessory %s", accessory);
            this.inquirySessionsInProgress.add(accessory);
            AccessoryInquirySession create = DefaultAccessoryExplorer.this.inquirySessionFactory.create(accessory);
            create.connect();
            Logger.d("Connecting iniquiry session for accessory %s", accessory);
            DefaultAccessoryExplorer.this.compositeDisposable.add(create.queryInquiryRecord().observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$zfvWRelpIgQ6auGTcA61dvM8wJE
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DefaultAccessoryExplorer.DeviceConnectivityObserver.this.lambda$fetchAccessoryInquiryRecord$18$DefaultAccessoryExplorer$DeviceConnectivityObserver(accessory, consumer, (AccessoryInquiryRecord) obj);
                }
            }, new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$0L6e3RtG9TEX_S6PnXrnTmEopmk
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DefaultAccessoryExplorer.DeviceConnectivityObserver.this.lambda$fetchAccessoryInquiryRecord$19$DefaultAccessoryExplorer$DeviceConnectivityObserver(accessory, (Throwable) obj);
                }
            }));
        }

        private void handlePossibleAccessoryConnected(final PeripheralDevice peripheralDevice) {
            if (peripheralDevice.getName() != null && !peripheralDevice.getName().isEmpty()) {
                for (String str : DefaultAccessoryExplorer.this.blockedNamePrefixes) {
                    if (peripheralDevice.getName().startsWith(str)) {
                        Logger.e("DefaultAccessoryExplorer: Peripheral with name %s connected but is being ignored because it is in the blockedNamePrefixes", peripheralDevice.getName());
                        return;
                    }
                }
            }
            DefaultAccessoryExplorer.this.compositeDisposable.add(DefaultAccessoryExplorer.this.deviceSupplier.getDeviceGroup(peripheralDevice.getAddress()).observeOn(AndroidSchedulers.mainThread()).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$EwZa7_Moh-h5KyJL_eTbKb5IFVM
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return DefaultAccessoryExplorer.DeviceConnectivityObserver.this.lambda$handlePossibleAccessoryConnected$5$DefaultAccessoryExplorer$DeviceConnectivityObserver(peripheralDevice, (DeviceGroup) obj);
                }
            }).subscribe(Functions.EMPTY_ACTION, new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$6Dr198HJG4mERJe_qIfckknLlbI
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DefaultAccessoryExplorer.DeviceConnectivityObserver.this.lambda$handlePossibleAccessoryConnected$6$DefaultAccessoryExplorer$DeviceConnectivityObserver(peripheralDevice, (Throwable) obj);
                }
            }));
        }

        private void handlePossibleAccessoryDisconnected(final PeripheralDevice peripheralDevice) {
            DefaultAccessoryExplorer.this.compositeDisposable.add(DefaultAccessoryExplorer.this.deviceSupplier.hasDeviceGroup(peripheralDevice.getAddress()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$f6lNK7vB-OLq_iU6TIkjfhrFm-A
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DefaultAccessoryExplorer.DeviceConnectivityObserver.this.lambda$handlePossibleAccessoryDisconnected$11$DefaultAccessoryExplorer$DeviceConnectivityObserver(peripheralDevice, (Boolean) obj);
                }
            }, new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$qQMWWq2cy9_MA12jIdYl-osB_j8
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DefaultAccessoryExplorer.DeviceConnectivityObserver.lambda$handlePossibleAccessoryDisconnected$12(PeripheralDevice.this, (Throwable) obj);
                }
            }));
        }

        private void handlePossibleUnknownAccessoryConnected(PeripheralDevice peripheralDevice) {
            Preconditions.mainThread();
            final Accessory accessoryFromPeripheral = AccessoryUtils.getAccessoryFromPeripheral(peripheralDevice);
            AccessoryInquiryRecord accessoryInquiryRecord = (AccessoryInquiryRecord) DefaultAccessoryExplorer.this.disconnectedCachedRecords.get(accessoryFromPeripheral);
            if (accessoryInquiryRecord != null) {
                Logger.d("Had a previously cached inquiry record from a previous connection with this accessory, using it instead of generating a new inquiry record.");
                DefaultAccessoryExplorer.this.connectedUnknownAccessories.put(accessoryFromPeripheral, accessoryInquiryRecord);
            }
            final AccessoryInquiryRecord accessoryInquiryRecord2 = (AccessoryInquiryRecord) DefaultAccessoryExplorer.this.connectedUnknownAccessories.get(accessoryFromPeripheral);
            if (accessoryInquiryRecord2 != null) {
                Logger.d("DefaultAccessoryExplorer: Unknown accessory already cached. %s", accessoryInquiryRecord2);
                DefaultAccessoryExplorer.this.notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$zlbKLucm200R9MRLJaaJAwkl1Cs
                    @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                    public final void accept(Object obj) {
                        ((AccessoryExplorer.Observer) obj).onUnknownAccessoryFound(Accessory.this, accessoryInquiryRecord2);
                    }
                });
                return;
            }
            DefaultAccessoryExplorer.this.sdpRepository.getOrFetch(peripheralDevice, new AnonymousClass1(), new ParcelUuid(DefaultAccessoryExplorer.this.accessoryAttributes.rfcommChannelId()));
        }

        private void handleUnknownAccessoryConnectedViaSpp(PeripheralDevice peripheralDevice) {
            final Accessory accessoryFromPeripheral = AccessoryUtils.getAccessoryFromPeripheral(peripheralDevice);
            if (DefaultAccessoryExplorer.this.shouldExcludeInquirySession) {
                DefaultAccessoryExplorer.this.connectedUnknownUnidentifiedAccessories.add(accessoryFromPeripheral);
                DefaultAccessoryExplorer.this.notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$VM6ogp9Sza1AyNmYGgaiuMnUVEs
                    @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                    public final void accept(Object obj) {
                        ((AccessoryExplorer.Observer) obj).onUnknownAccessoryFound(Accessory.this, null);
                    }
                });
                return;
            }
            shouldRequestInquiryRecord(accessoryFromPeripheral).subscribe(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$sz-fAMu8lDhojU2AO0Qroz4k700
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DefaultAccessoryExplorer.DeviceConnectivityObserver.this.lambda$handleUnknownAccessoryConnectedViaSpp$23$DefaultAccessoryExplorer$DeviceConnectivityObserver(accessoryFromPeripheral, (Boolean) obj);
                }
            }, new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$9DaJyAGYTN5Wt8qQ-qvDNSggddM
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DefaultAccessoryExplorer.DeviceConnectivityObserver.this.lambda$handleUnknownAccessoryConnectedViaSpp$24$DefaultAccessoryExplorer$DeviceConnectivityObserver(accessoryFromPeripheral, (Throwable) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$handlePossibleAccessoryDisconnected$12(PeripheralDevice peripheralDevice, Throwable th) throws Throwable {
            Logger.d("DefaultAccessoryExplorer: Unable to handle possible accessory disconnected: %s", peripheralDevice);
            Logger.e("DefaultAccessoryExplorer: Unable to handle possible accessory disconnected: %s", RedactionUtil.redact(peripheralDevice));
        }

        private void removeAndCloseCachedSppSocket(String str) {
            BluetoothSocket andRemoveCachedConnectedSocket = DefaultAccessoryExplorer.this.sppSocketSupplier.getAndRemoveCachedConnectedSocket(str);
            if (andRemoveCachedConnectedSocket != null) {
                try {
                    andRemoveCachedConnectedSocket.close();
                    Logger.d("Cached SPP socket closed for device %s", str);
                    return;
                } catch (IOException e) {
                    Logger.d("ERROR: Failed to close cached SPP socket for device %s", e, str);
                    Logger.e("Failed to close cached SPP socket for device", e);
                    return;
                }
            }
            Logger.d("ERROR: Expected SPP socket not found in cache for device address %s", str);
            Logger.e("Expected SPP socket not found in cache for device");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Single<Boolean> shouldRequestInquiryRecord(final Accessory accessory) {
            return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$83UUnyu24v9GztcWev9aiGqleiM
                @Override // io.reactivex.rxjava3.functions.Supplier
                /* renamed from: get */
                public final Object mo10365get() {
                    return DefaultAccessoryExplorer.DeviceConnectivityObserver.this.lambda$shouldRequestInquiryRecord$14$DefaultAccessoryExplorer$DeviceConnectivityObserver(accessory);
                }
            }).subscribeOn(AndroidSchedulers.mainThread());
        }

        public /* synthetic */ ObservableSource lambda$activeSessionExistsThatNeedsTransportUpgrade$15$DefaultAccessoryExplorer$DeviceConnectivityObserver() throws Throwable {
            return Observable.fromIterable(DefaultAccessoryExplorer.this.sessionSupplier.getActiveSessions());
        }

        public /* synthetic */ void lambda$fetchAccessoryInquiryRecord$18$DefaultAccessoryExplorer$DeviceConnectivityObserver(Accessory accessory, Consumer consumer, AccessoryInquiryRecord accessoryInquiryRecord) throws Throwable {
            Logger.d("DefaultAccessoryExplorer: Retrieved inquiry record for %s. Record: %s", accessory, accessoryInquiryRecord);
            consumer.accept(accessoryInquiryRecord);
            this.inquirySessionsInProgress.remove(accessory);
        }

        public /* synthetic */ void lambda$fetchAccessoryInquiryRecord$19$DefaultAccessoryExplorer$DeviceConnectivityObserver(Accessory accessory, Throwable th) throws Throwable {
            Logger.d("ERROR: DefaultAccessoryExplorer: Unable to query inquiry record for connected accessory: %s", th, accessory);
            Logger.e("DefaultAccessoryExplorer: Unable to query inquiry record for connected accessory: %s", th, RedactionUtil.redact(accessory));
            this.inquirySessionsInProgress.remove(accessory);
        }

        public /* synthetic */ CompletableSource lambda$handlePossibleAccessoryConnected$5$DefaultAccessoryExplorer$DeviceConnectivityObserver(PeripheralDevice peripheralDevice, DeviceGroup deviceGroup) throws Throwable {
            if (DefaultAccessoryExplorer.this.connectedAccessories.containsKey(AccessoryUtils.getAccessoryFromPeripheral(peripheralDevice))) {
                return Completable.complete();
            }
            final Accessory accessoryFromPeripheral = AccessoryUtils.getAccessoryFromPeripheral(peripheralDevice);
            DefaultAccessoryExplorer.this.connectedAccessories.put(accessoryFromPeripheral, new AccessoryInquiryRecord(deviceGroup));
            DefaultAccessoryExplorer.this.notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$kEfoyujyS5QAtKmH_yHhb5dNt_E
                @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                public final void accept(Object obj) {
                    ((AccessoryExplorer.Observer) obj).onKnownAccessoryFound(Accessory.this);
                }
            });
            return Completable.complete();
        }

        public /* synthetic */ void lambda$handlePossibleAccessoryConnected$6$DefaultAccessoryExplorer$DeviceConnectivityObserver(PeripheralDevice peripheralDevice, Throwable th) throws Throwable {
            Logger.d("DefaultAccessoryExplorer: Connected peripheral is not known. Will attempt to see if its an unknown accessory. Peripheral: %s.", th, peripheralDevice);
            handlePossibleUnknownAccessoryConnected(peripheralDevice);
        }

        public /* synthetic */ void lambda$handlePossibleAccessoryDisconnected$11$DefaultAccessoryExplorer$DeviceConnectivityObserver(PeripheralDevice peripheralDevice, Boolean bool) throws Throwable {
            final Accessory accessoryFromPeripheral = AccessoryUtils.getAccessoryFromPeripheral(peripheralDevice);
            if (bool.booleanValue()) {
                AccessoryInquiryRecord accessoryInquiryRecord = (AccessoryInquiryRecord) DefaultAccessoryExplorer.this.connectedAccessories.remove(accessoryFromPeripheral);
                if (accessoryInquiryRecord == null || accessoryInquiryRecord.getDevices() == null) {
                    return;
                }
                DefaultAccessoryExplorer.this.disconnectedCachedRecords.put(accessoryFromPeripheral, accessoryInquiryRecord);
                DefaultAccessoryExplorer.this.notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$L5ePDRBrLEMyK7JxZAKjIvyFWfQ
                    @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                    public final void accept(Object obj) {
                        ((AccessoryExplorer.Observer) obj).onKnownAccessoryLost(Accessory.this);
                    }
                });
                return;
            }
            final AccessoryInquiryRecord accessoryInquiryRecord2 = (AccessoryInquiryRecord) DefaultAccessoryExplorer.this.connectedUnknownAccessories.remove(accessoryFromPeripheral);
            if (accessoryInquiryRecord2 != null) {
                DefaultAccessoryExplorer.this.disconnectedCachedRecords.put(accessoryFromPeripheral, accessoryInquiryRecord2);
                DefaultAccessoryExplorer.this.notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$KdC45RCm8Y-SZGAOjd_PpiIHy0M
                    @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                    public final void accept(Object obj) {
                        ((AccessoryExplorer.Observer) obj).onUnknownAccessoryLost(Accessory.this, accessoryInquiryRecord2);
                    }
                });
            }
            if (!DefaultAccessoryExplorer.this.shouldExcludeInquirySession) {
                return;
            }
            DefaultAccessoryExplorer.this.connectedUnknownUnidentifiedAccessories.remove(accessoryFromPeripheral);
            DefaultAccessoryExplorer.this.notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$dbl8I29K4H2R2Rg-op5gTF5b7QQ
                @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                public final void accept(Object obj) {
                    ((AccessoryExplorer.Observer) obj).onUnknownAccessoryLost(Accessory.this, null);
                }
            });
        }

        public /* synthetic */ void lambda$handleUnknownAccessoryConnectedViaSpp$23$DefaultAccessoryExplorer$DeviceConnectivityObserver(final Accessory accessory, Boolean bool) throws Throwable {
            Logger.d("Determined if inquiry record can be requested for spp connected accessory: " + bool);
            if (bool.booleanValue()) {
                fetchAccessoryInquiryRecord(accessory, new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$3IerGXQ1-16XxXDwZHppBfEnYA0
                    @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                    public final void accept(Object obj) {
                        DefaultAccessoryExplorer.DeviceConnectivityObserver.this.lambda$null$22$DefaultAccessoryExplorer$DeviceConnectivityObserver(accessory, (AccessoryInquiryRecord) obj);
                    }
                });
            } else {
                removeAndCloseCachedSppSocket(accessory.getAddress());
            }
        }

        public /* synthetic */ void lambda$handleUnknownAccessoryConnectedViaSpp$24$DefaultAccessoryExplorer$DeviceConnectivityObserver(Accessory accessory, Throwable th) throws Throwable {
            Logger.d("ERROR: Failed to determine if inquiry record should be requested for spp connected accessory: %s", th, accessory);
            Logger.e("Failed to determine if inquiry record should be requested for spp connected accessory: %s", th, RedactionUtil.redact(accessory));
            removeAndCloseCachedSppSocket(accessory.getAddress());
        }

        public /* synthetic */ Boolean lambda$null$13$DefaultAccessoryExplorer$DeviceConnectivityObserver(Accessory accessory, Boolean bool) throws Throwable {
            return Boolean.valueOf(!bool.booleanValue() && !DefaultAccessoryExplorer.this.sessionSupplier.hasActiveSession(accessory));
        }

        public /* synthetic */ void lambda$null$22$DefaultAccessoryExplorer$DeviceConnectivityObserver(final Accessory accessory, final AccessoryInquiryRecord accessoryInquiryRecord) {
            DefaultAccessoryExplorer.this.connectedUnknownAccessories.put(accessory, accessoryInquiryRecord);
            DefaultAccessoryExplorer.this.notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$d-HNyt_QyK7h9p6fIme7L6PLUd4
                @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                public final void accept(Object obj) {
                    ((AccessoryExplorer.Observer) obj).onUnknownAccessoryFound(Accessory.this, accessoryInquiryRecord);
                }
            });
        }

        public /* synthetic */ MaybeSource lambda$onSocketConnected$0$DefaultAccessoryExplorer$DeviceConnectivityObserver(PeripheralDevice peripheralDevice, DeviceGroup deviceGroup) throws Throwable {
            Accessory accessoryFromPeripheral = AccessoryUtils.getAccessoryFromPeripheral(peripheralDevice);
            if (DefaultAccessoryExplorer.this.sessionSupplier.getSession(accessoryFromPeripheral) == null) {
                DefaultAccessoryExplorer.this.connectedAccessories.put(accessoryFromPeripheral, new AccessoryInquiryRecord(deviceGroup));
                return Maybe.just(accessoryFromPeripheral);
            }
            Logger.d("ERROR: There is already an active session with SPP connected peripheral: %s", peripheralDevice);
            Logger.e("There is already an active session with SPP connected peripheral: %s", RedactionUtil.redact(peripheralDevice));
            removeAndCloseCachedSppSocket(accessoryFromPeripheral.getAddress());
            return Maybe.empty();
        }

        public /* synthetic */ void lambda$onSocketConnected$2$DefaultAccessoryExplorer$DeviceConnectivityObserver(final Accessory accessory) throws Throwable {
            DefaultAccessoryExplorer.this.notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$JZJ-XD7bBAfm_NY3jXeWI2JaUeo
                @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                public final void accept(Object obj) {
                    ((AccessoryExplorer.Observer) obj).onKnownAccessoryFound(Accessory.this);
                }
            });
        }

        public /* synthetic */ void lambda$onSocketConnected$3$DefaultAccessoryExplorer$DeviceConnectivityObserver(PeripheralDevice peripheralDevice, Throwable th) throws Throwable {
            Logger.d("ERROR: SPP connected peripheral is not known. Peripheral: %s", th, peripheralDevice);
            Logger.e("SPP connected peripheral is not known. Peripheral: %s", th, RedactionUtil.redact(peripheralDevice));
            handleUnknownAccessoryConnectedViaSpp(peripheralDevice);
        }

        public /* synthetic */ SingleSource lambda$shouldRequestInquiryRecord$14$DefaultAccessoryExplorer$DeviceConnectivityObserver(final Accessory accessory) throws Throwable {
            Logger.d("Determining if an inquiry session is eligible to be executed for accessory %s", accessory);
            if (!this.inquirySessionsInProgress.contains(accessory)) {
                if (DefaultAccessoryExplorer.this.sessionSupplier.hasActiveSession(accessory)) {
                    Logger.d("Session supplier already contains a session for accessory %s", accessory);
                    return Single.just(false);
                }
                return activeSessionExistsThatNeedsTransportUpgrade().map(new Function() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$5bHKEHBwJsyKzQ2lGBB-NDpAsnI
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply */
                    public final Object mo10358apply(Object obj) {
                        return DefaultAccessoryExplorer.DeviceConnectivityObserver.this.lambda$null$13$DefaultAccessoryExplorer$DeviceConnectivityObserver(accessory, (Boolean) obj);
                    }
                });
            }
            Logger.d("Inquiry session already in progress for %s", accessory);
            return Single.just(false);
        }

        @Override // com.amazon.alexa.accessory.internal.monitor.AclDeviceMonitor.Observer
        public void onAclConnected(PeripheralDevice peripheralDevice) {
            handlePossibleAccessoryConnected(peripheralDevice);
        }

        @Override // com.amazon.alexa.accessory.internal.monitor.AclDeviceMonitor.Observer
        public void onAclDisconnected(PeripheralDevice peripheralDevice) {
            handlePossibleAccessoryDisconnected(peripheralDevice);
        }

        @Override // com.amazon.alexa.accessory.internal.repositories.PeripheralDeviceRepository.Callback
        public void onConnectedBluetoothDevicesFound(Set<PeripheralDevice> set) {
            for (PeripheralDevice peripheralDevice : set) {
                handlePossibleAccessoryConnected(peripheralDevice);
            }
        }

        @Override // com.amazon.alexa.accessory.internal.monitor.BluetoothProfileMonitor.Observer
        public void onProfileConnected(PeripheralDevice peripheralDevice, int i) {
            handlePossibleAccessoryConnected(peripheralDevice);
        }

        @Override // com.amazon.alexa.accessory.internal.monitor.BluetoothProfileMonitor.Observer
        public void onProfileDisconnected(PeripheralDevice peripheralDevice, int i) {
            Logger.d("DefaultAccessoryExplorer: Profile %d disconnected for device %s, ignoring this event", Integer.valueOf(i), peripheralDevice);
        }

        @Override // com.amazon.alexa.accessory.internal.repositories.PeripheralDeviceRepository.Callback
        public void onQueryFailed(Throwable th) {
            Logger.e("DefaultAccessoryExplorer: Get connected devices query failed. ", th);
        }

        @Override // com.amazon.alexa.accessory.internal.monitor.SppSocketConnectionMonitor.Listener
        public void onSocketConnected(final PeripheralDevice peripheralDevice, BluetoothSocket bluetoothSocket) {
            Logger.d("A client has connected via SPP server: %s", peripheralDevice);
            DefaultAccessoryExplorer.this.deviceSupplier.getDeviceGroup(peripheralDevice.getAddress()).observeOn(AndroidSchedulers.mainThread()).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$Y2dM2ugZqO3kOkmHrQ1UzM1pctw
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return DefaultAccessoryExplorer.DeviceConnectivityObserver.this.lambda$onSocketConnected$0$DefaultAccessoryExplorer$DeviceConnectivityObserver(peripheralDevice, (DeviceGroup) obj);
                }
            }).subscribe(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$vVg1p1vmh7lH6HQG4BRks7JXZBU
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DefaultAccessoryExplorer.DeviceConnectivityObserver.this.lambda$onSocketConnected$2$DefaultAccessoryExplorer$DeviceConnectivityObserver((Accessory) obj);
                }
            }, new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$Qo8n6iYMdl8OO4J7YwO8zGEzeSQ
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DefaultAccessoryExplorer.DeviceConnectivityObserver.this.lambda$onSocketConnected$3$DefaultAccessoryExplorer$DeviceConnectivityObserver(peripheralDevice, (Throwable) obj);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    private final class ExplorerSessionListener extends AccessorySessionListener {
        private ExplorerSessionListener() {
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionConnected(Accessory accessory) {
            DefaultAccessoryExplorer.this.leConnections.onNext(DefaultAccessoryExplorer.this.getActiveLeConnections());
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionReleased(Accessory accessory) {
            DefaultAccessoryExplorer.this.leConnections.onNext(DefaultAccessoryExplorer.this.getActiveLeConnections());
        }
    }

    /* loaded from: classes.dex */
    private final class GattDeviceMonitorObserver implements GattDeviceMonitor.Observer {
        private GattDeviceMonitorObserver() {
        }

        @Override // com.amazon.alexa.accessory.internal.monitor.GattDeviceMonitor.Observer
        public void onConnected(final PeripheralDevice peripheralDevice) {
            DefaultAccessoryExplorer.this.notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$GattDeviceMonitorObserver$bK8lZsLITvjv0hpip-OX1LfJdHM
                @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                public final void accept(Object obj) {
                    ((AccessoryExplorer.Observer) obj).onKnownAccessoryFound(new Accessory(PeripheralDevice.this.getAddress(), AccessoryTransport.Type.GATT));
                }
            });
        }

        @Override // com.amazon.alexa.accessory.internal.monitor.GattDeviceMonitor.Observer
        public void onDisconnected(final PeripheralDevice peripheralDevice) {
            DefaultAccessoryExplorer.this.notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$GattDeviceMonitorObserver$g4CwJmLxyiOfcV22ezmXc6BPmkY
                @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                public final void accept(Object obj) {
                    ((AccessoryExplorer.Observer) obj).onKnownAccessoryLost(new Accessory(PeripheralDevice.this.getAddress(), AccessoryTransport.Type.GATT));
                }
            });
        }
    }

    private void cacheNewConnectedDeviceGroupsAsKnownAccessories(List<DeviceGroup> list) {
        for (DeviceGroup deviceGroup : list) {
            Accessory accessoryFromDeviceGroup = AccessoryUtils.getAccessoryFromDeviceGroup(deviceGroup);
            AccessorySession session = this.sessionSupplier.getSession(accessoryFromDeviceGroup);
            if (!deviceGroup.getDevices().isEmpty() && !this.connectedAccessories.containsKey(accessoryFromDeviceGroup) && session != null && session.isConnected()) {
                AccessoryInquiryRecord accessoryInquiryRecord = new AccessoryInquiryRecord(deviceGroup);
                Logger.d("DefaultAccessoryExplorer: Connected accessory is now known. Record: %s", accessoryInquiryRecord);
                this.connectedAccessories.put(accessoryFromDeviceGroup, accessoryInquiryRecord);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Set<String> getActiveLeConnections() {
        HashSet hashSet = new HashSet();
        for (AccessorySession accessorySession : this.sessionSupplier.getActiveSessions()) {
            if (accessorySession.getTransport() == AccessoryTransport.Type.GATT) {
                hashSet.add(accessorySession.getAddress());
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MaybeSource lambda$null$15(DeviceGroup deviceGroup, Boolean bool) throws Throwable {
        return bool.booleanValue() ? Maybe.just(deviceGroup) : Maybe.empty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$null$17(Set set, DeviceGroup deviceGroup) throws Throwable {
        boolean z = deviceGroup.getTransportType() == AccessoryTransport.Type.GATT && !set.contains(deviceGroup.getIdentifier());
        Logger.d("DefaultAccessoryExplorer: Explorer found a device %s.Will try to reconnect over LE: %b", deviceGroup, Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Set lambda$observeAccessoriesPersistenceChanges$7(List list) throws Throwable {
        HashSet hashSet = new HashSet(list.size());
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            DeviceGroup deviceGroup = (DeviceGroup) it2.next();
            hashSet.add(new Accessory(deviceGroup.getIdentifier(), deviceGroup.getTransportType()));
        }
        return hashSet;
    }

    private void notifyObserverOfConnectedAccessories(AccessoryExplorer.Observer observer) {
        Logger.d("DefaultAccessoryExplorer: Reporting connected accessories: %s", this.connectedAccessories.toString());
        for (Accessory accessory : this.connectedAccessories.keySet()) {
            observer.onKnownAccessoryFound(accessory);
        }
        Logger.d("DefaultAccessoryExplorer: Reporting connected unknown accessories: %s", this.connectedUnknownAccessories.toString());
        for (Map.Entry<Accessory, AccessoryInquiryRecord> entry : this.connectedUnknownAccessories.entrySet()) {
            observer.onUnknownAccessoryFound(entry.getKey(), entry.getValue());
        }
        if (!this.connectedUnknownUnidentifiedAccessories.isEmpty()) {
            Logger.d("DefaultAccessoryExplorer: Reporting connected unknown unidentified accessories: %s", this.connectedUnknownUnidentifiedAccessories.toString());
        }
        for (Accessory accessory2 : this.connectedUnknownUnidentifiedAccessories) {
            observer.onUnknownAccessoryFound(accessory2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyObservers(Consumer<AccessoryExplorer.Observer> consumer) {
        Preconditions.mainThread();
        for (AccessoryExplorer.Observer observer : new ArrayList(this.observers)) {
            consumer.accept(observer);
        }
    }

    private void observeAccessoriesPersistenceChanges() {
        this.compositeDisposable.add(this.deviceSupplier.queryDeviceGroups().skip(1L).distinctUntilChanged().observeOn(AndroidSchedulers.mainThread()).map(new Function() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$NPZg_nxF7eoyOL1L5MyNXuZUIV0
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultAccessoryExplorer.this.lambda$observeAccessoriesPersistenceChanges$6$DefaultAccessoryExplorer((List) obj);
            }
        }).map($$Lambda$DefaultAccessoryExplorer$KxhMvouJXmI8mYksz_xPnnICe5U.INSTANCE).subscribe(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$_Gia4U-coC5siItu9mtWpd6eHKk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultAccessoryExplorer.this.lambda$observeAccessoriesPersistenceChanges$14$DefaultAccessoryExplorer((Set) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void observeLeDevice(String str) {
        this.observedGattDevices.add(str);
        this.gattDeviceMonitor.monitor(new PeripheralDevice(str, PeripheralDevice.Type.BLUETOOTH_LE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void observeLeDevices(List<String> list) {
        Preconditions.mainThread();
        ListUtils.ListDifference diff = ListUtils.diff(new ArrayList(this.observedGattDevices), list);
        for (T t : diff.removed) {
            stopObservingLeDevice(t);
        }
        for (T t2 : diff.added) {
            observeLeDevice(t2);
        }
    }

    private void observeStandbyAccessories() {
        Disposable subscribe = this.accessorySupplier.queryStandbyAccessories().map($$Lambda$i_QtZSEG53cMITP481NvO6gVIbg.INSTANCE).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$byL-X5B13tOoRh0mmYF-XVcz2YY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultAccessoryExplorer.this.stopObservingLeDevice((String) obj);
            }
        }, $$Lambda$DefaultAccessoryExplorer$iTWIoPewje2P7Grtu1asgb5ghfM.INSTANCE);
        Disposable subscribe2 = this.accessorySupplier.queryExpiredStandbyAccessories().observeOn(AndroidSchedulers.mainThread()).map($$Lambda$i_QtZSEG53cMITP481NvO6gVIbg.INSTANCE).filter(new Predicate() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$H9lyceS6FEwt6BsUNX-To4cDOkg
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return DefaultAccessoryExplorer.this.lambda$observeStandbyAccessories$4$DefaultAccessoryExplorer((String) obj);
            }
        }).subscribe(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$KdRG4M_tE6qwkRBCH-t6ULeZ8Uw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultAccessoryExplorer.this.observeLeDevice((String) obj);
            }
        }, $$Lambda$DefaultAccessoryExplorer$sBq35U2ViwNZl3ZuMY5BVSkzFEc.INSTANCE);
        this.compositeDisposable.add(subscribe);
        this.compositeDisposable.add(subscribe2);
    }

    private void startObservingBluetoothForAccessories() {
        this.bluetoothStateMonitor.addObserver(this.bluetoothStateObserver);
        if (BluetoothUtils.isBluetoothOn(this.context)) {
            startObservingDevices();
        }
    }

    private void startObservingConnectedBluetoothDevices() {
        this.aclDeviceMonitor.addObserver(this.deviceConnectivityObserver);
        this.bluetoothProfileMonitor.addObserver(this.deviceConnectivityObserver);
        this.sppSocketSupplier.setListener(this.deviceConnectivityObserver);
        this.peripheralDeviceRepository.queryConnectedBluetoothDevices(this.deviceConnectivityObserver);
        observeAccessoriesPersistenceChanges();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startObservingDevices() {
        if (this.observing) {
            return;
        }
        this.observing = true;
        this.sessionSupplier.addSessionListener(this.sessionListener);
        startObservingConnectedBluetoothDevices();
        startObservingLeDevices();
        observeStandbyAccessories();
    }

    private void startObservingLeDevices() {
        if (!BluetoothUtils.isLeSupported(this.context)) {
            Logger.d("DefaultAccessoryExplorer: LE isn't supported, cannot observe LE devices.");
            return;
        }
        this.gattDeviceMonitor.addObserver(this.gattDeviceObserver);
        this.compositeDisposable.add(this.deviceSupplier.queryDeviceGroups().observeOn(AndroidSchedulers.mainThread()).switchMap(new Function() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$G_PN6_br2fBg_wxvixn2dvIaAP8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultAccessoryExplorer.this.lambda$startObservingLeDevices$19$DefaultAccessoryExplorer((List) obj);
            }
        }).subscribe(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$HjNPobM2bb1UsxK7mwWGKyyAraQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultAccessoryExplorer.this.observeLeDevices((List) obj);
            }
        }, $$Lambda$DefaultAccessoryExplorer$VgJX_wYNvGqHaZrCK3WFGUfiY4.INSTANCE));
    }

    private void stopObservingConnectedBluetoothDevices() {
        this.aclDeviceMonitor.removeObserver(this.deviceConnectivityObserver);
        this.bluetoothProfileMonitor.removeObserver(this.deviceConnectivityObserver);
        this.sppSocketSupplier.unsetListener(this.deviceConnectivityObserver);
        for (final Accessory accessory : this.connectedAccessories.keySet()) {
            notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$Ty4BeroSJQDTN2vpIykLVIzfR80
                @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                public final void accept(Object obj) {
                    ((AccessoryExplorer.Observer) obj).onKnownAccessoryLost(Accessory.this);
                }
            });
        }
        for (final Map.Entry<Accessory, AccessoryInquiryRecord> entry : this.connectedUnknownAccessories.entrySet()) {
            notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$1-sS3Vo2186l-44180uCnsMDDm0
                @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                public final void accept(Object obj) {
                    ((AccessoryExplorer.Observer) obj).onUnknownAccessoryLost((Accessory) r0.getKey(), (AccessoryInquiryRecord) entry.getValue());
                }
            });
        }
        for (final Accessory accessory2 : this.connectedUnknownUnidentifiedAccessories) {
            notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$J0vn14ceqQ0giYzzd2f5Nwv8Rfg
                @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                public final void accept(Object obj) {
                    ((AccessoryExplorer.Observer) obj).onUnknownAccessoryLost(Accessory.this, null);
                }
            });
        }
        this.connectedAccessories.clear();
        this.connectedUnknownAccessories.clear();
        this.connectedUnknownUnidentifiedAccessories.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopObservingDevices() {
        if (!this.observing) {
            return;
        }
        this.observing = false;
        this.sessionSupplier.removeSessionListener(this.sessionListener);
        stopObservingConnectedBluetoothDevices();
        stopObservingLeDevices();
        this.compositeDisposable.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopObservingLeDevice(String str) {
        Preconditions.mainThread();
        this.observedGattDevices.remove(str);
        this.gattDeviceMonitor.cancel(new PeripheralDevice(str, PeripheralDevice.Type.BLUETOOTH_LE));
    }

    private void stopObservingLeDevices() {
        for (String str : this.observedGattDevices) {
            this.gattDeviceMonitor.cancel(new PeripheralDevice(str, PeripheralDevice.Type.BLUETOOTH_LE));
        }
        this.observedGattDevices.clear();
        this.gattDeviceMonitor.removeObserver(this.gattDeviceObserver);
    }

    @Override // com.amazon.alexa.accessory.AccessoryExplorer
    public void cancel(AccessoryExplorer.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        Logger.d("DefaultAccessoryExplorer: Cancelling discovery for observer");
        if (!this.observers.remove(observer) || !this.observers.isEmpty()) {
            return;
        }
        this.bluetoothStateMonitor.removeObserver(this.bluetoothStateObserver);
        stopObservingDevices();
    }

    @Override // com.amazon.alexa.accessory.AccessoryExplorer
    public void discover(AccessoryExplorer.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        Logger.d("DefaultAccessoryExplorer: Adding an observer");
        if (this.observers.isEmpty()) {
            startObservingBluetoothForAccessories();
        } else {
            notifyObserverOfConnectedAccessories(observer);
        }
        this.observers.add(observer);
    }

    @Override // com.amazon.alexa.accessory.AccessoryExplorer
    public AccessoryAttributes getAccessoryAttributes() {
        return this.accessoryAttributes;
    }

    @Override // com.amazon.alexa.accessory.AccessoryExplorer
    public SdpRepository getSdpRepository() {
        return this.sdpRepository;
    }

    public /* synthetic */ MaybeSource lambda$null$16$DefaultAccessoryExplorer(final DeviceGroup deviceGroup) throws Throwable {
        return this.accessorySupplier.isConnectible(new Accessory(deviceGroup.getIdentifier(), deviceGroup.getTransportType())).observeOn(AndroidSchedulers.mainThread()).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$Qt6DRJ-b_PQ2CTHskZfPYMGoNUE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultAccessoryExplorer.lambda$null$15(DeviceGroup.this, (Boolean) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$null$18$DefaultAccessoryExplorer(List list, final Set set) throws Throwable {
        return Observable.fromIterable(list).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$0hkeXz4ZVHvGSsGna8UyFwy4Z64
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultAccessoryExplorer.this.lambda$null$16$DefaultAccessoryExplorer((DeviceGroup) obj);
            }
        }).filter(new Predicate() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$dnLMgEg4Vc_qw8WhKzzqiNozYEo
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return DefaultAccessoryExplorer.lambda$null$17(set, (DeviceGroup) obj);
            }
        }).map($$Lambda$BawggZJfidgCs2CntNc8YXhpEU.INSTANCE).toList();
    }

    public /* synthetic */ void lambda$observeAccessoriesPersistenceChanges$14$DefaultAccessoryExplorer(Set set) throws Throwable {
        Iterator<Accessory> it2 = this.connectedUnknownUnidentifiedAccessories.iterator();
        while (it2.hasNext()) {
            final Accessory next = it2.next();
            if (set.contains(next)) {
                Logger.d("DefaultAccessoryExplorer: Unknown unidentified accessory is now known: %s", next);
                it2.remove();
                notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$r4vtCPuLc_s1dH6EIBcgv8bM_Hw
                    @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                    public final void accept(Object obj) {
                        ((AccessoryExplorer.Observer) obj).onUnknownAccessoryLost(Accessory.this, null);
                    }
                });
                notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$859-DmFV16yon7v4mdMGea0m8Bk
                    @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                    public final void accept(Object obj) {
                        ((AccessoryExplorer.Observer) obj).onKnownAccessoryFound(Accessory.this);
                    }
                });
            }
        }
        Iterator<Map.Entry<Accessory, AccessoryInquiryRecord>> it3 = this.connectedUnknownAccessories.entrySet().iterator();
        while (it3.hasNext()) {
            final Map.Entry<Accessory, AccessoryInquiryRecord> next2 = it3.next();
            if (set.contains(next2.getKey())) {
                Logger.d("DefaultAccessoryExplorer: Unknown accessory is now known. Record: %s", next2.getValue());
                it3.remove();
                notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$Ty5C7TwRs2UTpfJRIkTlJXAARe0
                    @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                    public final void accept(Object obj) {
                        ((AccessoryExplorer.Observer) obj).onUnknownAccessoryLost((Accessory) r0.getKey(), (AccessoryInquiryRecord) next2.getValue());
                    }
                });
                this.connectedAccessories.put(next2.getKey(), next2.getValue());
                notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$IhENupo71uchQG90zoqnvtAlgXo
                    @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                    public final void accept(Object obj) {
                        ((AccessoryExplorer.Observer) obj).onKnownAccessoryFound((Accessory) next2.getKey());
                    }
                });
            }
        }
        Iterator<Map.Entry<Accessory, AccessoryInquiryRecord>> it4 = this.connectedAccessories.entrySet().iterator();
        while (it4.hasNext()) {
            final Map.Entry<Accessory, AccessoryInquiryRecord> next3 = it4.next();
            if (!set.contains(next3.getKey())) {
                Logger.d("DefaultAccessoryExplorer: Known accessory is now unknown. Record: %s", next3.getValue());
                it4.remove();
                notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$YA8_lOXqED1z6u-PyHWlDf7YPq8
                    @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                    public final void accept(Object obj) {
                        ((AccessoryExplorer.Observer) obj).onKnownAccessoryLost((Accessory) next3.getKey());
                    }
                });
                this.connectedUnknownAccessories.put(next3.getKey(), next3.getValue());
                notifyObservers(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$IoTLXen_95oPbqUw-079unGmot8
                    @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
                    public final void accept(Object obj) {
                        ((AccessoryExplorer.Observer) obj).onUnknownAccessoryFound((Accessory) r0.getKey(), (AccessoryInquiryRecord) next3.getValue());
                    }
                });
            }
        }
    }

    public /* synthetic */ List lambda$observeAccessoriesPersistenceChanges$6$DefaultAccessoryExplorer(List list) throws Throwable {
        cacheNewConnectedDeviceGroupsAsKnownAccessories(list);
        return list;
    }

    public /* synthetic */ boolean lambda$observeStandbyAccessories$4$DefaultAccessoryExplorer(String str) throws Throwable {
        return !getActiveLeConnections().contains(str);
    }

    public /* synthetic */ ObservableSource lambda$startObservingLeDevices$19$DefaultAccessoryExplorer(final List list) throws Throwable {
        return this.leConnections.startWithArray(getActiveLeConnections()).flatMapSingle(new Function() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$_0g0ToydLQcEVg7QpwELuZUDJKI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultAccessoryExplorer.this.lambda$null$18$DefaultAccessoryExplorer(list, (Set) obj);
            }
        });
    }

    private DefaultAccessoryExplorer(Builder builder) {
        Preconditions.notNull(builder, "builder");
        this.context = builder.context;
        this.accessorySupplier = builder.accessorySupplier;
        this.deviceSupplier = builder.deviceSupplier;
        this.peripheralDeviceRepository = builder.peripheralDeviceRepository;
        this.bluetoothStateMonitor = builder.bluetoothStateMonitor;
        this.gattDeviceMonitor = builder.gattDeviceMonitor;
        this.aclDeviceMonitor = builder.aclDeviceMonitor;
        this.bluetoothProfileMonitor = builder.bluetoothProfileMonitor;
        this.sppSocketSupplier = builder.sppSocketSupplier;
        this.sessionSupplier = builder.sessionSupplier;
        this.sdpRepository = builder.sdpRepository;
        this.inquirySessionFactory = builder.inquirySessionFactory;
        this.accessoryAttributes = builder.accessoryAttributes;
        this.blockedNamePrefixes = builder.blockedNamePrefixes;
        this.shouldExcludeInquirySession = builder.shouldExcludeInquirySession;
        this.leConnections = PublishSubject.create();
        this.observers = new HashSet();
        this.connectedAccessories = new HashMap();
        this.connectedUnknownAccessories = new HashMap();
        this.connectedUnknownUnidentifiedAccessories = new HashSet();
        this.disconnectedCachedRecords = new HashMap();
        this.observedGattDevices = new HashSet();
        this.sessionListener = new ExplorerSessionListener();
        this.bluetoothStateObserver = new BluetoothStateMonitorObserver();
        this.gattDeviceObserver = new GattDeviceMonitorObserver();
        this.deviceConnectivityObserver = new DeviceConnectivityObserver();
        this.compositeDisposable = new CompositeDisposable();
    }
}
