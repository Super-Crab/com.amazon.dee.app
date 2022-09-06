package com.amazon.alexa.mode.service;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessoryclient.client.AlexaAccessoryClient;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.mode.ModeName;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.mode.bluetooth.AutoBluetoothObserver;
import com.amazon.alexa.mode.dependencies.DaggerModeComponent;
import com.amazon.alexa.mode.dependencies.ModeModule;
import com.amazon.alexa.mode.drive.HomeChannelInteractor;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.mode.statemachine.DriveModeStateMachineImpl;
import com.amazon.alexa.mode.statemachine.Event;
import com.amazon.alexa.mode.statemachine.StateDependencies;
import com.amazon.alexa.mode.statemachine.StateMachineEventObserver;
import com.amazon.alexa.mode.statemachine.StateTransitionHelper;
import com.amazon.alexa.mode.userstudy.ModeStatusLog;
import com.amazon.alexa.mode.util.AccessoryAutoBluetoothMetricsHelper;
import com.amazon.alexa.mode.util.AutomotiveAccessoryConnectivityObserver;
import com.amazon.alexa.mode.util.AutomotiveDeviceRegistry;
import com.amazon.alexa.mode.util.CatapultTtsDeviceMonitor;
import com.amazon.alexa.mode.util.DriveModeFTUEHelper;
import com.amazon.alexa.mode.util.DriveModePreferences;
import com.amazon.alexa.mode.util.FeatureChecker;
import com.amazon.alexa.mode.util.IdentityServiceHelper;
import com.amazon.alexa.mode.util.LifecycleHelperV2;
import com.amazon.alexa.mode.util.LogTag;
import com.amazon.alexa.mode.util.NetworkStatusHelper;
import com.amazon.alexa.mode.util.NotificationHelper;
import com.amazon.alexa.mode.util.PermissionChecker;
import com.amazon.alexa.mode.util.PreferredNavigationAppContentResolver;
import com.amazon.alexa.mode.util.PrefsDialogHelper;
import com.amazon.alexa.mode.util.charging.wireless.WirelessChargingStatusObserver;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class DefaultModeServiceV2 implements ModeService {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = LogTag.forClass(DefaultModeServiceV2.class);
    private final AlexaAccessoryClient mAccessoriesClient;
    @VisibleForTesting
    AccessoryAutoBluetoothMetricsHelper mAccessoryAutoMetricsHelper;
    @VisibleForTesting
    AutoBluetoothObserver mAutoBluetoothObserver;
    @Inject
    AutomotiveAccessoryConnectivityObserver mAutomotiveDeviceObserver;
    @Inject
    AutomotiveDeviceRegistry mAutomotiveDeviceRegistry;
    @Inject
    CatapultTtsDeviceMonitor mCatapultTtsDeviceMonitor;
    private final Context mContext;
    @Inject
    Lazy<DriveModeMetrics> mDriveModeMetricsLazy;
    @Inject
    DriveModePreferences mDriveModePreferences;
    @Inject
    HomeChannelInteractor mHomeChannelInteractor;
    @VisibleForTesting
    IdentityServiceHelper mIdentityServiceHelper;
    @VisibleForTesting
    ModeServiceVoiceHandlerV2 mModeServiceVoiceHandler;
    @Inject
    ModeStatusLog mModeStatusLog;
    @Inject
    Lazy<NotificationHelper> mNotificationHelper;
    @Inject
    PrefsDialogHelper mPrefsDialogHelper;
    private StateMachineEventObserver mStateMachineEventObserver;
    @VisibleForTesting
    WirelessChargingStatusObserver mWirelessChargingStatusObserver;
    private NetworkStatusHelper networkStatus;
    private final FeatureChecker mFeatureChecker = new FeatureChecker();
    @VisibleForTesting
    DriveModeStateMachineImpl mDriveModeStateMachine = new DriveModeStateMachineImpl();
    private final LifecycleHelperV2 mLifeCycleHelper = new LifecycleHelperV2();

    public DefaultModeServiceV2(@NonNull Context context) {
        this.mContext = context;
        this.mAccessoriesClient = new AlexaAccessoryClient(this.mContext);
        this.mModeServiceVoiceHandler = new ModeServiceVoiceHandlerV2(this.mLifeCycleHelper, this, new AlexaServicesConnection(this.mContext));
        DaggerModeComponent.builder().modeModule(new ModeModule(context)).build().inject(this);
    }

    private String getDriveModeType() {
        return this.mDriveModeStateMachine.isCurrentDefaultMode() ? "Default" : Boolean.TRUE.equals(isDriveModeAccessoryDeviceConnected().getValue()) ? "Accessory" : "AutoBluetooth";
    }

    private void initializeAccessoryObserver(final AlexaAccessoryClient alexaAccessoryClient) {
        this.mAutomotiveDeviceObserver.initialize(this.mAutomotiveDeviceRegistry.fetchAutomotiveDevices(this.mContext), alexaAccessoryClient.getAccessories().getSessionSupplier(), alexaAccessoryClient.getAccessories().getDeviceSupplier());
        new Handler().post(new Runnable() { // from class: com.amazon.alexa.mode.service.-$$Lambda$XEs4YSzaM7fH7dVQ6mckRq73qDY
            @Override // java.lang.Runnable
            public final void run() {
                AlexaAccessoryClient.this.initialize();
            }
        });
    }

    private void initializeStateMachine() {
        this.mFeatureChecker.subscribeForUserLogout(new FeatureChecker.UserLogoutListener() { // from class: com.amazon.alexa.mode.service.-$$Lambda$DefaultModeServiceV2$TtXBrFzU-93cvA0rHtiprlp3Z8g
            @Override // com.amazon.alexa.mode.util.FeatureChecker.UserLogoutListener
            public final void onLogout() {
                DefaultModeServiceV2.this.onLogout();
            }
        });
        this.mHomeChannelInteractor.initialize();
        RoutingService routingService = (RoutingService) ComponentRegistry.getInstance().get(RoutingService.class).orNull();
        StateTransitionHelper stateTransitionHelper = new StateTransitionHelper();
        stateTransitionHelper.registerStateTransitionCallback(this.mDriveModeStateMachine);
        this.mDriveModeStateMachine.init(new StateDependencies(this.mPrefsDialogHelper, routingService, new DriveModeFTUEHelper(new PreferredNavigationAppContentResolver(this.mContext), this.mPrefsDialogHelper, new PermissionChecker(this.mContext)), this.mNotificationHelper, this.mDriveModeMetricsLazy, stateTransitionHelper, this.mHomeChannelInteractor, this.mContext, this.mIdentityServiceHelper, this.mAutoBluetoothObserver), this.mLifeCycleHelper.isBackground());
        this.mWirelessChargingStatusObserver = new WirelessChargingStatusObserver(this.mContext, this.mAutomotiveDeviceObserver);
        this.mStateMachineEventObserver = new StateMachineEventObserver(this.mContext, this.mDriveModeStateMachine, this.mAutomotiveDeviceObserver, this.mLifeCycleHelper, this.mCatapultTtsDeviceMonitor, this.mAutoBluetoothObserver, this.mWirelessChargingStatusObserver);
        this.mStateMachineEventObserver.subscribeForEvents();
    }

    private void logSessionEndedMetrics(int i) {
        String driveModeType = getDriveModeType();
        String str = "logSessionEndedMetrics | egressType: " + i + " |  driveModeType: " + driveModeType;
        DriveModeMetrics mo358get = this.mDriveModeMetricsLazy.mo358get();
        mo358get.logSessionEndedWithTimers(driveModeType);
        RouteContext currentRoute = ((RoutingService) ComponentRegistry.getInstance().get(RoutingService.class).orNull()).getCurrentRoute();
        if (currentRoute != null) {
            if (i == 0) {
                mo358get.logSessionEnded(driveModeType, DriveModeMetrics.EgressType.MANUALEGRESS, currentRoute.getRoute().getName());
            } else if (i == 1) {
                mo358get.logSessionEnded(driveModeType, DriveModeMetrics.EgressType.DEVICEDISCONNECTIONEGRESS, currentRoute.getRoute().getName());
            } else if (i != 2) {
            } else {
                mo358get.logSessionEnded(driveModeType, DriveModeMetrics.EgressType.APPTERMINATIONEGRESS, currentRoute.getRoute().getName());
            }
        }
    }

    private void logSessionStartedMetrics(int i) {
        String driveModeType = getDriveModeType();
        String str = "logSessionStartedMetrics | ingressType: " + i + " |  driveModeType: " + driveModeType;
        DriveModeMetrics mo358get = this.mDriveModeMetricsLazy.mo358get();
        mo358get.logSessionStartedWithTimers(driveModeType);
        this.networkStatus = new NetworkStatusHelper(this.mContext);
        mo358get.logSessionStartedWithNetworkStatus(this.networkStatus.getNetworkStatus());
        boolean booleanValue = isDriveModeAccessoryDeviceConnected().getValue().booleanValue();
        String str2 = DriveModeMetrics.ConnectionStatus.CONNECTED;
        mo358get.logSessionStartedWithAccessoryStatus(driveModeType, booleanValue ? str2 : DriveModeMetrics.ConnectionStatus.DISCONNECTED);
        if (!isAutoBluetoothDeviceConnected().getValue().booleanValue()) {
            str2 = DriveModeMetrics.ConnectionStatus.DISCONNECTED;
        }
        mo358get.logSessionStartedWithHeadUnitStatus(driveModeType, str2);
        if (i == 0) {
            mo358get.logSessionStartedWithIngressType(driveModeType, DriveModeMetrics.IngressType.AUTOINGRESS);
        } else if (i == 1) {
            mo358get.logSessionStartedWithIngressType(driveModeType, DriveModeMetrics.IngressType.CARDINGRESS);
        } else if (i == 2) {
            mo358get.logSessionStartedWithIngressType(driveModeType, DriveModeMetrics.IngressType.NOTIFICATIONINGRESS);
        } else if (i != 3) {
        } else {
            mo358get.logSessionStartedWithIngressType(driveModeType, DriveModeMetrics.IngressType.DEVICESETUPINGRESS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLogout() {
        this.mDriveModePreferences.resetPreferencesToDefaults();
    }

    private void uninitializeStateMachine() {
        this.mHomeChannelInteractor.removeDriveModeIngressCard();
        this.mHomeChannelInteractor.unInitialize();
        this.mDriveModeStateMachine.tearDown();
        StateMachineEventObserver stateMachineEventObserver = this.mStateMachineEventObserver;
        if (stateMachineEventObserver != null) {
            stateMachineEventObserver.unsubscribeFromEvents();
            this.mStateMachineEventObserver = null;
        }
    }

    @Override // com.amazon.alexa.mode.ModeService
    public void destroy() {
        Log.i(TAG, "destroy");
        this.mFeatureChecker.unInit();
        uninitializeStateMachine();
        this.mAccessoriesClient.disconnect();
        this.mModeServiceVoiceHandler.uninitialize();
        this.mAutoBluetoothObserver.uninitialize();
        this.mAccessoryAutoMetricsHelper.destroy();
    }

    @Override // com.amazon.alexa.mode.ModeService
    public void exitDriveMode(int i) {
        logSessionEndedMetrics(i);
        this.mDriveModeStateMachine.onEvent(Event.DriveModeEgressRequested);
    }

    @Override // com.amazon.alexa.mode.ModeService
    public Observable<List<DeviceGroup>> getConnectedAutomotiveAccessories() {
        return this.mAutomotiveDeviceObserver.connectedAutomotiveDeviceGroups();
    }

    @Override // com.amazon.alexa.mode.ModeService
    public String getMode() {
        return this.mDriveModeStateMachine.getCurrentMode();
    }

    void initAutoBluetoothObserver(AutoBluetoothObserver autoBluetoothObserver) {
        this.mAutoBluetoothObserver = autoBluetoothObserver;
    }

    @Override // com.amazon.alexa.mode.ModeService
    public void initialize() {
        Log.i(TAG, "initialize");
        initializeAccessoryObserver(this.mAccessoriesClient);
        initAutoBluetoothObserver(new AutoBluetoothObserver(this.mContext, this.mDriveModeMetricsLazy));
        this.mIdentityServiceHelper = new IdentityServiceHelper();
        this.mFeatureChecker.init();
        initializeStateMachine();
        this.mModeServiceVoiceHandler.initialize();
        this.mAccessoryAutoMetricsHelper = new AccessoryAutoBluetoothMetricsHelper(this.mAutoBluetoothObserver, this.mAutomotiveDeviceObserver, this.mDriveModeMetricsLazy, this.mAutomotiveDeviceRegistry.fetchAutomotiveDevices(this.mContext));
        this.mAccessoryAutoMetricsHelper.init();
    }

    @Override // com.amazon.alexa.mode.ModeService
    public BehaviorSubject<Boolean> isAutoBluetoothDeviceConnected() {
        return this.mAutoBluetoothObserver.isConnectedToAutoBluetooth();
    }

    @Override // com.amazon.alexa.mode.ModeService
    public BehaviorSubject<Boolean> isDriveModeAccessoryDeviceConnected() {
        return this.mAutomotiveDeviceObserver.isAutomotiveAccessoryConnected();
    }

    @Override // com.amazon.alexa.mode.ModeService
    public boolean isDriveModeForeground() {
        return !this.mLifeCycleHelper.isBackground() && this.mDriveModeStateMachine.getCurrentMode().equals(ModeName.DRIVE_MODE);
    }

    @Override // com.amazon.alexa.mode.ModeService
    public boolean isErrorInWirelessCharging() {
        WirelessChargingStatusObserver wirelessChargingStatusObserver = this.mWirelessChargingStatusObserver;
        if (wirelessChargingStatusObserver != null) {
            return wirelessChargingStatusObserver.isErrorInWirelessCharging();
        }
        Log.w(TAG, "WirelessChargingStatusObserver instance is not yet initialized, returning false");
        return false;
    }

    @Override // com.amazon.alexa.mode.ModeService
    public boolean isTtsDeviceJustRegistered() {
        return this.mCatapultTtsDeviceMonitor.isTtsDeviceJustRegistered();
    }

    @Override // com.amazon.alexa.mode.ModeService
    public void logModeStatus(String str, String str2, String str3, String str4) {
        this.mModeStatusLog.logStatus(str, str2, str3, str4);
    }

    @Override // com.amazon.alexa.mode.ModeService
    public void startDriveMode(int i) {
        logSessionStartedMetrics(i);
        this.mDriveModeStateMachine.onEvent(Event.DriveModeIngressRequested);
    }
}
