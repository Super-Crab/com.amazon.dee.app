package com.amazon.deecomms.accessories;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccount;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.alexa.CommsEventSender;
import com.amazon.deecomms.alexa.connection.enpoint.pcc.PCCConnectionEndpointHandler;
import com.amazon.deecomms.alexa.fireos.CommsAlexaServicesConnectionListener;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public class CommsAccessorySessionListener {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsAccessorySessionListener.class);
    private static AtomicBoolean mAccessorySessionListenerAttached = new AtomicBoolean(false);
    private final AlexaServicesConnection alexaServicesConnection;
    private final CallManager callManager;
    private final CapabilitiesManager capabilitiesManager;
    private Accessories clientAccessories;
    private final CommsAlexaServicesConnectionListener commsAlexaServicesConnectionListener;
    private final CommsEventSender commsEventSender;
    private Device.DeviceInformation connectedAccessoryInformation;
    private final Context context;
    private final PCCConnectionEndpointHandler pccConnectionEndpointHandler;
    @VisibleForTesting
    boolean isHFPConnectionAvailable = false;
    @VisibleForTesting
    boolean isA2DPDConnectionAvailable = false;
    private final HashMap<String, String> deviceMasterIdTable = new HashMap<>();
    private final HashMap<String, Device.DeviceInformation> deviceInformationTable = new HashMap<>();

    @VisibleForTesting
    /* loaded from: classes12.dex */
    public interface UpdateAccessory {
        void updateAccessoryInformation();
    }

    public CommsAccessorySessionListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull CommsAlexaServicesConnectionListener commsAlexaServicesConnectionListener, @NonNull Context context, @NonNull CapabilitiesManager capabilitiesManager, @NonNull CallManager callManager, @NonNull CommsEventSender commsEventSender, @NonNull PCCConnectionEndpointHandler pCCConnectionEndpointHandler) {
        this.alexaServicesConnection = alexaServicesConnection;
        this.commsAlexaServicesConnectionListener = commsAlexaServicesConnectionListener;
        this.context = context;
        this.capabilitiesManager = capabilitiesManager;
        this.callManager = callManager;
        this.commsEventSender = commsEventSender;
        this.pccConnectionEndpointHandler = pCCConnectionEndpointHandler;
    }

    private void activate() {
        LOG.i("CommsAccessorySessionListener: activate");
        setClientAccessories(Accessories.Shared.INSTANCE.get(this.context));
        this.clientAccessories.getSessionSupplier().observeSessionConnected().subscribe(new Consumer() { // from class: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$QUOFyEI0Vz4T75KtQGTvf6S1VzY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CommsAccessorySessionListener.this.lambda$activate$0$CommsAccessorySessionListener((Accessory) obj);
            }
        }, $$Lambda$CommsAccessorySessionListener$rshs3gXl4IWSOPbjSrY625GJP0.INSTANCE);
        this.clientAccessories.getSessionSupplier().observeSessionReleased().subscribe(new Consumer() { // from class: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$JuJRsNPHdVxS9pkQN5QD-GaFTRA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CommsAccessorySessionListener.this.lambda$activate$2$CommsAccessorySessionListener((Accessory) obj);
            }
        }, $$Lambda$CommsAccessorySessionListener$L9yx1xIK7QZfH_vbwjUCo4Lgmek.INSTANCE);
        this.clientAccessories.getSessionSupplier().getActiveAccessories().subscribe(new Consumer() { // from class: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$nyu5HjzSjqB2KEhQQ4B55NixdgU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CommsAccessorySessionListener.this.lambda$activate$4$CommsAccessorySessionListener((List) obj);
            }
        }, $$Lambda$CommsAccessorySessionListener$n1GmH4QuA6Ifm8KTp8I2PipIhGo.INSTANCE);
    }

    private Disposable determineStateForSessions(@NonNull List<AccessorySession> list, @Nullable final UpdateAccessory updateAccessory) {
        ArrayList arrayList = new ArrayList();
        for (final AccessorySession accessorySession : list) {
            arrayList.add(provideDeviceInformationQuery(accessorySession).firstOrError().flatMapObservable(new Function() { // from class: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$11Fe6gYeljedOljKdwubBnAT-qw
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return CommsAccessorySessionListener.this.lambda$determineStateForSessions$11$CommsAccessorySessionListener(accessorySession, (Set) obj);
                }
            }));
        }
        return Observable.combineLatest(arrayList, $$Lambda$CommsAccessorySessionListener$fP64uUGDcXby9CZnBn5a0uIAU6o.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$1bQZ12ZTB_fTkbm9K5yA7xcCAMI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CommsAccessorySessionListener.this.lambda$determineStateForSessions$13$CommsAccessorySessionListener(updateAccessory, (List) obj);
            }
        }, new Consumer() { // from class: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$NChn0ScvRhJPCrTRelAAaePV_zM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CommsAccessorySessionListener.this.lambda$determineStateForSessions$14$CommsAccessorySessionListener(updateAccessory, (Throwable) obj);
            }
        });
    }

    public static void initializeAccessoryComponents() {
        LOG.i("Trying to initialize Accessory components and listeners...");
        if (mAccessorySessionListenerAttached.get()) {
            LOG.i("Accessory related Alexa Device components already initialized");
            return;
        }
        LOG.i("Initializing Accessory related Alexa Device Components");
        CommsDaggerWrapper.getComponent().getCommsAccessorySessionListener().activate();
        mAccessorySessionListenerAttached.set(true);
        LOG.i("Attempt to initialize PCC for accessories");
        CommsDaggerWrapper.getComponent().getPhoneCallControllerManager().registerPhoneCallController();
        CommsDaggerWrapper.getComponent().getMessagingControllerManager().initializeMessagingController();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$determineStateForSessions$12(Object[] objArr) throws Throwable {
        ArrayList arrayList = new ArrayList();
        for (Object obj : objArr) {
            arrayList.add((Pair) obj);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$getPrimaryDeviceInformation$15(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation2.getDeviceId() - deviceInformation.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Pair lambda$null$10(StateOuterClass.State state, StateOuterClass.State state2) throws Throwable {
        return new Pair(state, state2);
    }

    private void onAccessorySessionConnected() {
        LOG.i("CommsAccessorySessionListener: New Accessory session connected");
        resetAccessoryConnectionInformation(new UpdateAccessory() { // from class: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$vfysl-wSjV-sTBgKR5d2a8cfIfs
            @Override // com.amazon.deecomms.accessories.CommsAccessorySessionListener.UpdateAccessory
            public final void updateAccessoryInformation() {
                CommsAccessorySessionListener.this.lambda$onAccessorySessionConnected$7$CommsAccessorySessionListener();
            }
        });
    }

    @NonNull
    @VisibleForTesting
    private static Flowable<StateOuterClass.State> provideA2DPInformationQuery(@NonNull AccessorySession accessorySession) {
        return accessorySession.getStateRepository().query(StateFeature.BLUETOOTH_A2DP_CONNECTED);
    }

    @NonNull
    @VisibleForTesting
    private static Flowable<StateOuterClass.State> provideHFPInformationQuery(@NonNull AccessorySession accessorySession) {
        return accessorySession.getStateRepository().query(StateFeature.BLUETOOTH_HFP_CONNECTED);
    }

    @VisibleForTesting
    public void clearStoredAccessoriesInfo() {
        this.deviceMasterIdTable.clear();
        this.deviceInformationTable.clear();
        this.connectedAccessoryInformation = null;
        this.isHFPConnectionAvailable = false;
        this.isA2DPDConnectionAvailable = false;
    }

    @VisibleForTesting
    void fetchAndStoreDeviceMasterId(final Device.DeviceInformation deviceInformation) {
        provideDeviceAccountQuery(deviceInformation).subscribe(new Consumer() { // from class: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$FU_gazVPS3uT1Liat2BytcFb4oI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CommsAccessorySessionListener.this.lambda$fetchAndStoreDeviceMasterId$16$CommsAccessorySessionListener(deviceInformation, (DeviceAccount) obj);
            }
        }, $$Lambda$CommsAccessorySessionListener$zbRVnoCwTkULUIbx_7gtBSmdQ.INSTANCE);
    }

    public String getCachedDeviceMasterId(String str) {
        return this.deviceMasterIdTable.get(str);
    }

    Device.DeviceInformation getConnectedAccessoryInformation() {
        return this.connectedAccessoryInformation;
    }

    public Device.DeviceInformation getDeviceInformationFromTable(String str) {
        return this.deviceInformationTable.get(str);
    }

    @VisibleForTesting
    public HashMap<String, Device.DeviceInformation> getDeviceInformationTable() {
        return this.deviceInformationTable;
    }

    @VisibleForTesting
    Device.DeviceInformation getPrimaryDeviceInformation(Set<Device.DeviceInformation> set) {
        TreeSet treeSet = new TreeSet($$Lambda$CommsAccessorySessionListener$HhuH6xJD52kZi5u998_mHY3a2yg.INSTANCE);
        treeSet.addAll(set);
        return (Device.DeviceInformation) treeSet.first();
    }

    public boolean isA2DPAvailable() {
        return this.isA2DPDConnectionAvailable;
    }

    public boolean isAnyAccessoryConnected() {
        return this.connectedAccessoryInformation != null;
    }

    public boolean isHFPAvailable() {
        return this.isHFPConnectionAvailable;
    }

    public /* synthetic */ void lambda$activate$0$CommsAccessorySessionListener(Accessory accessory) throws Throwable {
        onAccessorySessionConnected();
    }

    public /* synthetic */ void lambda$activate$2$CommsAccessorySessionListener(Accessory accessory) throws Throwable {
        onAccessorySessionReleased();
    }

    public /* synthetic */ void lambda$activate$4$CommsAccessorySessionListener(List list) throws Throwable {
        if (!list.isEmpty()) {
            onAccessorySessionConnected();
        }
    }

    public /* synthetic */ ObservableSource lambda$determineStateForSessions$11$CommsAccessorySessionListener(AccessorySession accessorySession, Set set) throws Throwable {
        Device.DeviceInformation primaryDeviceInformation = getPrimaryDeviceInformation(set);
        this.deviceInformationTable.put(primaryDeviceInformation.getName(), primaryDeviceInformation);
        this.connectedAccessoryInformation = primaryDeviceInformation;
        fetchAndStoreDeviceMasterId(primaryDeviceInformation);
        StateOuterClass.State mo10084build = StateOuterClass.State.newBuilder().setBoolean(false).mo10084build();
        return Flowable.combineLatest(Flowable.concat(Flowable.just(mo10084build), accessorySession.getStateRepository().query(StateFeature.BLUETOOTH_HFP_CONNECTED)), Flowable.concat(Flowable.just(mo10084build), accessorySession.getStateRepository().query(StateFeature.BLUETOOTH_A2DP_CONNECTED)), $$Lambda$CommsAccessorySessionListener$nHr7w1S6FASzzru7TjhNxvPWiLg.INSTANCE).toObservable();
    }

    public /* synthetic */ void lambda$determineStateForSessions$13$CommsAccessorySessionListener(UpdateAccessory updateAccessory, List list) throws Throwable {
        Iterator it2 = list.iterator();
        boolean z = false;
        boolean z2 = false;
        while (it2.hasNext()) {
            Pair pair = (Pair) it2.next();
            if (((StateOuterClass.State) pair.first).getBoolean()) {
                z = true;
            }
            if (((StateOuterClass.State) pair.second).getBoolean()) {
                z2 = true;
            }
            this.isHFPConnectionAvailable = z;
            this.isA2DPDConnectionAvailable = z2;
        }
        LOG.i("HFP single-pass finished for %d accessories. deviceType: %s,isHFPConnectionAvailable: %b, isA2DPConnectionAvailable: %b", Integer.valueOf(list.size()), this.connectedAccessoryInformation.getDeviceType(), Boolean.valueOf(this.isHFPConnectionAvailable), Boolean.valueOf(this.isA2DPDConnectionAvailable));
        if (updateAccessory != null) {
            updateAccessory.updateAccessoryInformation();
        }
    }

    public /* synthetic */ void lambda$determineStateForSessions$14$CommsAccessorySessionListener(UpdateAccessory updateAccessory, Throwable th) throws Throwable {
        LOG.e("Caught error while determining HFP/A2DP status.", th);
        CommsLogger commsLogger = LOG;
        Object[] objArr = new Object[3];
        Device.DeviceInformation deviceInformation = this.connectedAccessoryInformation;
        objArr[0] = deviceInformation != null ? deviceInformation.getDeviceType() : "UNKNOWN";
        objArr[1] = Boolean.valueOf(this.isHFPConnectionAvailable);
        objArr[2] = Boolean.valueOf(this.isA2DPDConnectionAvailable);
        commsLogger.e("Failed to determine HFP/A2DP status. Updating with deviceType: %s, isHFPConnectionAvailable: %b, , isA2DPConnectionAvailable: %b", objArr);
        if (updateAccessory != null) {
            updateAccessory.updateAccessoryInformation();
        }
    }

    public /* synthetic */ void lambda$fetchAndStoreDeviceMasterId$16$CommsAccessorySessionListener(Device.DeviceInformation deviceInformation, DeviceAccount deviceAccount) throws Throwable {
        storeDeviceMasterId(deviceInformation.getName(), deviceAccount.getDeviceAccountResponse().getDeviceAccountId());
    }

    public /* synthetic */ void lambda$onAccessorySessionConnected$7$CommsAccessorySessionListener() {
        LocalBroadcastManager.getInstance(this.context).sendBroadcast(new Intent(Constants.ACCESSORY_UPDATED));
        if (isAnyAccessoryConnected() && !this.alexaServicesConnection.isConnected()) {
            LOG.i("Accessory is connected but ASC is not connected. Connecting ASC...");
            this.alexaServicesConnection.registerListener(this.commsAlexaServicesConnectionListener);
            LOG.i("Attempting to connect the ASC now...");
            this.alexaServicesConnection.connect();
            return;
        }
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Accessory session: ");
        outline1.append(isAnyAccessoryConnected());
        outline1.append("ASC Connection status: ");
        outline1.append(this.alexaServicesConnection.isConnected());
        commsLogger.i(outline1.toString());
    }

    public /* synthetic */ void lambda$onAccessorySessionReleased$6$CommsAccessorySessionListener() {
        if (this.pccConnectionEndpointHandler.shouldDisconnect()) {
            LOG.i("Accessory session is not available, disconnecting ASC.");
            this.alexaServicesConnection.disconnect();
            LocalBroadcastManager.getInstance(this.context).sendBroadcast(new Intent(Constants.ACCESSORY_UPDATED));
            return;
        }
        LOG.i("Accessory is connected or call in progress, not disconnecting ASC");
    }

    public /* synthetic */ void lambda$resetAccessoryConnectionInformation$8$CommsAccessorySessionListener(UpdateAccessory updateAccessory, CompositeDisposable compositeDisposable, List list) throws Throwable {
        if (list.isEmpty()) {
            LOG.i("No active accessories connected");
            if (updateAccessory == null) {
                return;
            }
            updateAccessory.updateAccessoryInformation();
            return;
        }
        LOG.i("Detected %d accessory session(s), checking Accessory/HFP/A2DP status.", Integer.valueOf(list.size()));
        compositeDisposable.add(determineStateForSessions(list, updateAccessory));
    }

    public void onAccessorySessionReleased() {
        LOG.i("CommsAccessorySessionListener: Accessory session released");
        resetAccessoryConnectionInformation(new UpdateAccessory() { // from class: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$3cvQgdx-nNevleqJz2XJ4hEqogc
            @Override // com.amazon.deecomms.accessories.CommsAccessorySessionListener.UpdateAccessory
            public final void updateAccessoryInformation() {
                CommsAccessorySessionListener.this.lambda$onAccessorySessionReleased$6$CommsAccessorySessionListener();
            }
        });
    }

    @VisibleForTesting
    public Single<DeviceAccount> provideDeviceAccountQuery(Device.DeviceInformation deviceInformation) {
        return this.clientAccessories.getDeviceAccountSupplier().getDeviceAccount(deviceInformation.getDeviceType(), deviceInformation.getSerialNumber());
    }

    @NonNull
    @VisibleForTesting
    Observable<Set<Device.DeviceInformation>> provideDeviceInformationQuery(@NonNull AccessorySession accessorySession) {
        return accessorySession.getDeviceRepository().queryDeviceInformationSet();
    }

    void resetAccessoryConnectionInformation(@Nullable final UpdateAccessory updateAccessory) {
        clearStoredAccessoriesInfo();
        final CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(Accessories.Shared.INSTANCE.get(this.context).getSessionSupplier().getActiveSessions().subscribe(new Consumer() { // from class: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$eht6N5yaV-hENruIeEE29S_3Wuk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CommsAccessorySessionListener.this.lambda$resetAccessoryConnectionInformation$8$CommsAccessorySessionListener(updateAccessory, compositeDisposable, (List) obj);
            }
        }, $$Lambda$CommsAccessorySessionListener$nZMJVpt0GQy2rMiijZTR3WTE.INSTANCE));
        CommsDaggerWrapper.getComponent().getCommsDisposableManager().dispose();
        CommsDaggerWrapper.getComponent().getCommsDisposableManager().add(compositeDisposable);
    }

    @VisibleForTesting
    void setClientAccessories(Accessories accessories) {
        this.clientAccessories = accessories;
    }

    public void setConnectedAccessoryInformation(@Nullable Device.DeviceInformation deviceInformation) {
        this.connectedAccessoryInformation = deviceInformation;
    }

    public void storeDeviceMasterId(String str, String str2) {
        this.deviceMasterIdTable.put(str, str2);
    }
}
