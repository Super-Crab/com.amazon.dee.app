package com.amazon.alexa.accessory.internal.interactor;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryExplorer;
import com.amazon.alexa.accessory.AccessoryInquiryRecord;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.AccessorySessionOptions;
import com.amazon.alexa.accessory.AccessorySupplier;
import com.amazon.alexa.accessory.Interactor;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.PeripheralConnectivity;
import com.amazon.alexa.accessory.internal.interactor.ConnectivityInteractor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.monitor.BluetoothStateMonitor;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.internal.functions.Functions;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class ConnectivityInteractor implements Interactor {
    private static final int MAX_RECONNECT_ALLOWED = 4;
    private static final int RECONNECT_MSG_ID = 0;
    private static final String TAG = "ConnectivityInteractor: ";
    private final AccessoryExplorer accessoryExplorer;
    private final AccessoryMetricsService accessoryMetricsService;
    private final AccessorySupplier accessorySupplier;
    private boolean active;
    private final BluetoothStateMonitor bluetoothStateMonitor;
    private final BluetoothStateMonitor.Observer bluetoothStateMonitorObserver;
    private final CompositeDisposable compositeDisposable;
    private final Map<String, Integer> deviceReconnectMap;
    private final DeviceSupplierV2 deviceSupplier;
    private Disposable disposable;
    private final FeatureChecker featureChecker;
    private final Set<Accessory> handlerMessageKeys;
    private boolean internallyActivated;
    private final Handler mHandler;
    private final AccessoryExplorer.Observer observer;
    private final PeripheralConnectivity peripheralConnectivity;
    private final AccessorySession.Factory sessionFactory;
    private final SessionListener sessionListener;
    private final SessionSupplier sessionSupplier;
    private Disposable userDisposable;
    private final UserSupplier userSupplier;

    /* loaded from: classes.dex */
    private final class AccessoryExplorerObserver implements AccessoryExplorer.Observer {
        private AccessoryExplorerObserver() {
        }

        @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
        public void onKnownAccessoryFound(Accessory accessory) {
            Logger.d("%s onKnownAccessoryFound %s", ConnectivityInteractor.TAG, accessory);
            ConnectivityInteractor.this.deviceReconnectMap.remove(accessory.getAddress());
            ConnectivityInteractor.this.removeMessages(accessory);
            ConnectivityInteractor.this.reconnect(accessory, MetricsConstants.Session.RECONNECT_SESSION_ON_KNOWN_ACCESSORY);
        }

        @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
        public void onKnownAccessoryLost(Accessory accessory) {
        }

        @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
        public void onUnknownAccessoryFound(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord) {
        }

        @Override // com.amazon.alexa.accessory.AccessoryExplorer.Observer
        public void onUnknownAccessoryLost(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord) {
        }
    }

    /* loaded from: classes.dex */
    private final class BluetoothStateObserver implements BluetoothStateMonitor.Observer {
        private BluetoothStateObserver() {
        }

        @Override // com.amazon.alexa.accessory.monitor.BluetoothStateMonitor.Observer
        public void onBluetoothDisabled() {
            ConnectivityInteractor.this.releaseAllSessions();
        }

        @Override // com.amazon.alexa.accessory.monitor.BluetoothStateMonitor.Observer
        public void onBluetoothEnabled() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SessionListener extends AccessorySessionListener {
        private SessionListener() {
        }

        public /* synthetic */ Long lambda$null$1$ConnectivityInteractor$SessionListener(Accessory accessory, Boolean bool) throws Throwable {
            Integer valueOf;
            Integer num = (Integer) ConnectivityInteractor.this.deviceReconnectMap.get(accessory.getAddress());
            if (num == null) {
                valueOf = 0;
            } else {
                valueOf = Integer.valueOf(Math.min(4, num.intValue() + 1));
            }
            ConnectivityInteractor.this.deviceReconnectMap.put(accessory.getAddress(), valueOf);
            return Long.valueOf((1 << valueOf.intValue()) * 1000);
        }

        public /* synthetic */ void lambda$null$2$ConnectivityInteractor$SessionListener(Accessory accessory, Long l) throws Throwable {
            ConnectivityInteractor.recordSessionRetryDelay(l.longValue());
            ConnectivityInteractor.this.sendMessageDelayed(accessory, l.longValue());
        }

        public /* synthetic */ void lambda$null$6$ConnectivityInteractor$SessionListener(Accessory accessory, Boolean bool) throws Throwable {
            ConnectivityInteractor.this.reconnect(accessory, MetricsConstants.Session.RECONNECT_SESSION_AFTER_FAILURE);
        }

        public /* synthetic */ void lambda$onAccessorySessionFailed$4$ConnectivityInteractor$SessionListener(final Accessory accessory, PeripheralConnectivity.ConnectionStatus connectionStatus) {
            boolean z = connectionStatus == PeripheralConnectivity.ConnectionStatus.CONNECTED;
            Logger.d("%s%s session failed. Connection status is: %s", ConnectivityInteractor.TAG, accessory, connectionStatus.name());
            if (!z) {
                return;
            }
            ConnectivityInteractor.this.compositeDisposable.add(ConnectivityInteractor.this.accessorySupplier.isConnectible(accessory).filter(new Predicate() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$SessionListener$fzpLf0HWSt48Dnx04VGi5clB7L4
                @Override // io.reactivex.rxjava3.functions.Predicate
                public final boolean test(Object obj) {
                    return Logger.d("%sSession failed with accessory %s and connectible: %b", ConnectivityInteractor.TAG, Accessory.this, (Boolean) obj);
                }
            }).map(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$SessionListener$c8q-mjTI1p4CWS2DJD_fB0HPXRs
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return ConnectivityInteractor.SessionListener.this.lambda$null$1$ConnectivityInteractor$SessionListener(accessory, (Boolean) obj);
                }
            }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$SessionListener$FlRFgR_HZZhJm4GD7uXgm2qRlMg
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    ConnectivityInteractor.SessionListener.this.lambda$null$2$ConnectivityInteractor$SessionListener(accessory, (Long) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$SessionListener$CSohANBjPSAjaf6eVnN4JLtBXeg
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    Logger.e("%sFailed to reconnect with the Accessory %s", (Throwable) obj, ConnectivityInteractor.TAG, Accessory.this);
                }
            }));
        }

        public /* synthetic */ void lambda$onAccessorySessionFailed$8$ConnectivityInteractor$SessionListener(final Accessory accessory, PeripheralConnectivity.ConnectionStatus connectionStatus) {
            boolean z = connectionStatus == PeripheralConnectivity.ConnectionStatus.CONNECTED;
            Logger.d("%s%s session failed. Connection status is : %s", ConnectivityInteractor.TAG, accessory, connectionStatus.name());
            if (!z) {
                return;
            }
            ConnectivityInteractor.this.accessorySupplier.isConnectible(accessory).filter(new Predicate() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$SessionListener$h5m6bDwfklv1J6StY5DdFKX3O-c
                @Override // io.reactivex.rxjava3.functions.Predicate
                public final boolean test(Object obj) {
                    Boolean bool = (Boolean) obj;
                    return Logger.d("%sSession failed with accessory %s and it is not connectible. Will not reconnect", ConnectivityInteractor.TAG, Accessory.this);
                }
            }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$SessionListener$wXqTwupbRNP4nVp0KNthFMA4acs
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    ConnectivityInteractor.SessionListener.this.lambda$null$6$ConnectivityInteractor$SessionListener(accessory, (Boolean) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$SessionListener$1jq9dFQuOJlTE0x4UKvHIqhrI5w
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    Logger.e("%sFailed to get connectible status for %s", ConnectivityInteractor.TAG, (Throwable) obj, Accessory.this);
                }
            });
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionConnected(Accessory accessory) {
            Logger.d("%s onAccessorySessionConnected:", ConnectivityInteractor.TAG);
            ConnectivityInteractor.this.deviceReconnectMap.remove(accessory.getAddress());
            ConnectivityInteractor.this.removeMessages(accessory);
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionFailed(final Accessory accessory, Throwable th) {
            Logger.d("%sA session with %s failed. Checking if we should reconnect...", th, ConnectivityInteractor.TAG, accessory);
            if (ConnectivityInteractor.this.featureChecker.hasAccess(AccessoryFeature.ALEXA_ACCESSORY_ANDROID_SESSION_RETRY)) {
                ConnectivityInteractor.this.peripheralConnectivity.getConnectionStatus(accessory, new PeripheralConnectivity.Callback() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$SessionListener$xOUDmR9PvgLb0n3G6ULKAPVPzsg
                    @Override // com.amazon.alexa.accessory.internal.PeripheralConnectivity.Callback
                    public final void onConnectionStatus(PeripheralConnectivity.ConnectionStatus connectionStatus) {
                        ConnectivityInteractor.SessionListener.this.lambda$onAccessorySessionFailed$4$ConnectivityInteractor$SessionListener(accessory, connectionStatus);
                    }
                });
            } else {
                ConnectivityInteractor.this.peripheralConnectivity.getConnectionStatus(accessory, new PeripheralConnectivity.Callback() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$SessionListener$g6Pnf_Rthqx12jZAZQiRJJblP1A
                    @Override // com.amazon.alexa.accessory.internal.PeripheralConnectivity.Callback
                    public final void onConnectionStatus(PeripheralConnectivity.ConnectionStatus connectionStatus) {
                        ConnectivityInteractor.SessionListener.this.lambda$onAccessorySessionFailed$8$ConnectivityInteractor$SessionListener(accessory, connectionStatus);
                    }
                });
            }
        }
    }

    public ConnectivityInteractor(AccessorySession.Factory factory, SessionSupplier sessionSupplier, AccessorySupplier accessorySupplier, AccessoryExplorer accessoryExplorer, PeripheralConnectivity peripheralConnectivity, BluetoothStateMonitor bluetoothStateMonitor, DeviceSupplierV2 deviceSupplierV2, UserSupplier userSupplier, FeatureChecker featureChecker) {
        Preconditions.notNull(factory, "sessionFactory");
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(accessorySupplier, "accessorySupplier");
        Preconditions.notNull(accessoryExplorer, "accessoryExplorer");
        Preconditions.notNull(peripheralConnectivity, "peripheralConnectivity");
        Preconditions.notNull(bluetoothStateMonitor, "bluetoothStateMonitor");
        Preconditions.notNull(deviceSupplierV2, "deviceSupplier");
        Preconditions.notNull(userSupplier, "userSupplier");
        Preconditions.notNull(featureChecker, "featureChecker");
        this.sessionFactory = factory;
        this.sessionSupplier = sessionSupplier;
        this.accessorySupplier = accessorySupplier;
        this.accessoryExplorer = accessoryExplorer;
        this.peripheralConnectivity = peripheralConnectivity;
        this.bluetoothStateMonitor = bluetoothStateMonitor;
        this.deviceSupplier = deviceSupplierV2;
        this.userSupplier = userSupplier;
        this.featureChecker = featureChecker;
        this.deviceReconnectMap = new HashMap();
        this.handlerMessageKeys = new HashSet();
        this.observer = new AccessoryExplorerObserver();
        this.sessionListener = new SessionListener();
        this.bluetoothStateMonitorObserver = new BluetoothStateObserver();
        this.accessoryMetricsService = AccessoryMetricsServiceHolder.getInstance().get();
        this.compositeDisposable = new CompositeDisposable();
        this.mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$47CbafcJO3ciqG83MGS4cmB-5qk
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return ConnectivityInteractor.this.lambda$new$0$ConnectivityInteractor(message);
            }
        });
    }

    private AccessorySessionOptions getAccessorySessionOptions() {
        AccessorySessionOptions accessorySessionOptions = new AccessorySessionOptions();
        if (this.featureChecker.hasAccess(AccessoryFeature.VERSION_NOTIFICATION_RESPONSE)) {
            accessorySessionOptions.setDeviceKnown(true);
        }
        return accessorySessionOptions;
    }

    private void internalActivate() {
        Preconditions.mainThread();
        if (this.internallyActivated) {
            return;
        }
        this.internallyActivated = true;
        Logger.d("ConnectivityInteractor: Internal activate");
        this.deviceReconnectMap.clear();
        this.accessoryExplorer.discover(this.observer);
        this.sessionSupplier.addSessionListener(this.sessionListener);
        this.bluetoothStateMonitor.addObserver(this.bluetoothStateMonitorObserver);
        this.disposable = this.accessorySupplier.queryExpiredStandbyAccessories().subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$QP_7D1NlsvFrPEdWRMTiQ1wnl0o
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ConnectivityInteractor.this.lambda$internalActivate$1$ConnectivityInteractor((Accessory) obj);
            }
        }, $$Lambda$ConnectivityInteractor$rtpc5P86N0t3kXiVKhzRkgguqM.INSTANCE);
        monitorStandbyTimeoutAccessories();
    }

    private void internalDeactivate() {
        Preconditions.mainThread();
        if (!this.internallyActivated) {
            return;
        }
        this.internallyActivated = false;
        this.deviceReconnectMap.clear();
        Logger.d("ConnectivityInteractor: Internal deactivate");
        this.accessoryExplorer.cancel(this.observer);
        this.sessionSupplier.removeSessionListener(this.sessionListener);
        this.bluetoothStateMonitor.removeObserver(this.bluetoothStateMonitorObserver);
        ObservableUtils.dispose(this.disposable);
        ObservableUtils.dispose(this.compositeDisposable);
        releaseAllSessions();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$reconnect$6(Accessory accessory, Throwable th) throws Throwable {
        Logger.d("%sERROR: Unable to reconnect to found accessory %s.", th, TAG, accessory.getAddress());
        Logger.e("%sUnable to reconnect to found accessory.", th, TAG);
    }

    private void monitorStandbyTimeoutAccessories() {
        Observable map = this.deviceSupplier.queryDeviceGroups().firstOrError().flatMapObservable($$Lambda$tNLLyz36wpjmL1kezURjOHIEA.INSTANCE).map($$Lambda$vkzZl8NfqCwljoWW2fVi1YIQJs8.INSTANCE);
        final AccessorySupplier accessorySupplier = this.accessorySupplier;
        accessorySupplier.getClass();
        map.flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$eyDQ1pR3OIVQTqLBZ6hL8tlWSzc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AccessorySupplier.this.monitorUnexpiredStandbyTimeoutAccessory((Accessory) obj);
            }
        }).subscribe(Functions.EMPTY_ACTION, $$Lambda$ConnectivityInteractor$ujSAaut1tFhkY7dUiR77Wou0fac.INSTANCE);
    }

    private Disposable observeUser() {
        return this.userSupplier.queryUser().distinctUntilChanged().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$QNtYLxoc7pXQglyO-Zvp4l0Fpts
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ConnectivityInteractor.this.userChanged((User) obj);
            }
        }, $$Lambda$ConnectivityInteractor$40DV1zXmYUZbY0_Y4x67cGrvHS8.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reconnect(final Accessory accessory, final String str) {
        this.deviceSupplier.getDeviceGroup(accessory.getAddress()).filter(new Predicate() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$K9xPf4tdOrFfzKVJBl1EV8oodXc
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return ConnectivityInteractor.this.lambda$reconnect$4$ConnectivityInteractor((DeviceGroup) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$zOloQim2hDKTuKHHcrfAFkp2CDs
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ConnectivityInteractor.this.lambda$reconnect$5$ConnectivityInteractor(accessory, str, (DeviceGroup) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$sKE94DIlqRpxwdjYs48vHK19sQ8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ConnectivityInteractor.lambda$reconnect$6(Accessory.this, (Throwable) obj);
            }
        });
    }

    private void reconnectAfterDelay(final Accessory accessory) {
        this.peripheralConnectivity.getConnectionStatus(accessory, new PeripheralConnectivity.Callback() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$kSltUX--ylLgCnT2H0PAs_z6Svc
            @Override // com.amazon.alexa.accessory.internal.PeripheralConnectivity.Callback
            public final void onConnectionStatus(PeripheralConnectivity.ConnectionStatus connectionStatus) {
                ConnectivityInteractor.this.lambda$reconnectAfterDelay$7$ConnectivityInteractor(accessory, connectionStatus);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void recordSessionRetryDelay(long j) {
        AccessoryMetricsServiceHolder.getInstance().get().recordTime(MetricsConstants.Session.SESSION_CONNECTION, MetricsConstants.Session.SESSION_RETRY_DELAY, j, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseAllSessions() {
        for (AccessorySession accessorySession : this.sessionSupplier.getActiveSessions()) {
            accessorySession.release();
            this.accessoryMetricsService.recordOccurrence(MetricsConstants.Session.SESSION_RELEASED, MetricsConstants.Session.SESSION_RELEASED_REASON_CONNECTIVITY_INTERACTOR_RELEASE_ALL, true, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeMessages(Accessory accessory) {
        if (!this.handlerMessageKeys.contains(accessory)) {
            return;
        }
        this.mHandler.removeMessages(0, accessory);
        this.handlerMessageKeys.remove(accessory);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessageDelayed(Accessory accessory, long j) {
        this.handlerMessageKeys.add(accessory);
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, accessory), j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void userChanged(User user) {
        if (user == User.ABSENT) {
            Logger.d("ConnectivityInteractor: No user is active, calling internal deactivate.");
            internalDeactivate();
            return;
        }
        Logger.d("ConnectivityInteractor: User is active, calling internal activate.");
        internalActivate();
    }

    @Override // com.amazon.alexa.accessory.Interactor
    public void activate() {
        Preconditions.mainThread();
        if (this.active) {
            return;
        }
        Logger.d("ConnectivityInteractor: Activate");
        this.active = true;
        this.userDisposable = observeUser();
    }

    @Override // com.amazon.alexa.accessory.Interactor
    public void deactivate() {
        Preconditions.mainThread();
        if (!this.active) {
            return;
        }
        Logger.d("ConnectivityInteractor: Deactivate");
        this.active = false;
        ObservableUtils.dispose(this.userDisposable);
        internalDeactivate();
    }

    public /* synthetic */ void lambda$internalActivate$1$ConnectivityInteractor(Accessory accessory) throws Throwable {
        this.deviceReconnectMap.remove(accessory.getAddress());
        removeMessages(accessory);
        reconnect(accessory, MetricsConstants.Session.RECONNECT_SESSION_STANDBY_EXPIRED);
    }

    public /* synthetic */ boolean lambda$new$0$ConnectivityInteractor(Message message) {
        if (message.what == 0) {
            Accessory accessory = (Accessory) message.obj;
            Logger.d("%sReconnect with accessory %s", TAG, accessory);
            reconnectAfterDelay(accessory);
            return true;
        }
        return false;
    }

    public /* synthetic */ boolean lambda$reconnect$4$ConnectivityInteractor(DeviceGroup deviceGroup) throws Throwable {
        return this.active;
    }

    public /* synthetic */ void lambda$reconnect$5$ConnectivityInteractor(Accessory accessory, String str, DeviceGroup deviceGroup) throws Throwable {
        Accessory accessory2 = new Accessory(deviceGroup.getIdentifier(), deviceGroup.getTransportType(), accessory.getName());
        if (!this.sessionSupplier.hasActiveSession(accessory2)) {
            Logger.d("%sreconnecting to accessory %s", TAG, accessory);
            AccessorySessionOptions accessorySessionOptions = getAccessorySessionOptions();
            this.accessoryMetricsService.recordCounter(str, MetricsConstants.Session.SESSION_CONNECTION, 1.0d, null);
            this.sessionSupplier.createSession(accessory2, this.sessionFactory, null, accessorySessionOptions).connect();
        }
    }

    public /* synthetic */ void lambda$reconnectAfterDelay$7$ConnectivityInteractor(Accessory accessory, PeripheralConnectivity.ConnectionStatus connectionStatus) {
        boolean z = connectionStatus == PeripheralConnectivity.ConnectionStatus.CONNECTED;
        Logger.d("%s%s Reconnect after delay. Device connection status is : %s", TAG, accessory, connectionStatus.name());
        if (!z) {
            return;
        }
        reconnect(accessory, MetricsConstants.Session.RECONNECT_SESSION_AFTER_DELAY);
    }
}
