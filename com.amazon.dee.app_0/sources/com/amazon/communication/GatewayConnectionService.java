package com.amazon.communication;

import amazon.communication.CommunicationFactoryBase;
import amazon.communication.ICommunicationManager;
import amazon.communication.connection.ConnectionClosedDetails;
import amazon.communication.connection.IConnection;
import amazon.communication.connection.KeepAlive;
import amazon.communication.connection.Policy;
import amazon.communication.connection.Purpose;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.EndpointIdentityFactory;
import amazon.communication.identity.ServiceIdentity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.alexa.mode.debug.EmulateConnection;
import com.amazon.communication.remotesetting.RemoteSettingManager;
import com.amazon.communication.remotesetting.SettingUpdateListener;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.dp.utils.FailFast;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class GatewayConnectionService extends Service implements IConnection.ConnectionListener {
    public static final boolean ENABLE_ALWAYS_ON_D2D_DEFAULT = false;
    public static final String ENABLE_ALWAYS_ON_D2D_KEY = "Setting.EnableAlwaysOnD2D";
    public static final boolean ENABLE_GATEWAY_DEFAULT = true;
    public static final String ENABLE_GATEWAY_KEY = "Setting.EnableGateway";
    protected amazon.communication.connection.IConnection mD2DGwConnection;
    protected amazon.communication.connection.IConnection mGatewayConnection;
    private Handler mHandler;
    private SettingUpdateListener mSettingsListener;
    private static final DPLogger log = new DPLogger("TComm.GatewayConnectionService");
    public static final ServiceIdentity GATEWAY_ENDPOINT = EndpointIdentityFactory.createServiceIdentity("DPGatewayService");
    public static final ServiceIdentity DIRECTED_GATEWAY_ENDPOINT = EndpointIdentityFactory.createServiceIdentity("Gateway");
    public static final List<EndpointIdentity> GATEWAY_ALIASES = new ArrayList<EndpointIdentity>() { // from class: com.amazon.communication.GatewayConnectionService.1
        {
            add(EndpointIdentityFactory.createServiceIdentity("DPGatewayService"));
            add(EndpointIdentityFactory.createServiceIdentity("Gateway"));
            add(EndpointIdentityFactory.createServiceIdentity("DPGatewayProbingService"));
            add(EndpointIdentityFactory.createServiceIdentity("AIVRelayService"));
        }
    };
    public static final Policy GATEWAY_POLICY = new Policy.Builder().setIsClearText(false).setIsLowLatencyNecessary(false).setIsRequestResponseOnly(false).setIsAnonymousCredentialsAllowed(true).setReconnectOnFailure(true).setKeepAlive(KeepAlive.ADAPTIVE).setPurpose(Purpose.REGULAR).build();
    public static final Policy D2D_GW_POLICY = new Policy.Builder().setIsClearText(false).setIsLowLatencyNecessary(false).setIsRequestResponseOnly(false).setIsAnonymousCredentialsAllowed(true).setReconnectOnFailure(true).setKeepAlive(KeepAlive.STATIC).setPurpose(Purpose.D2D_MESSAGING).build();
    public static final ServiceIdentity GATEWAY_PROBING_ENDPOINT = EndpointIdentityFactory.createServiceIdentity("DPGatewayProbingService");
    public static final Purpose HEARTBEAT_PROBING_PURPOSE = new Purpose("HeartbeatProbing");
    public static final Policy GATEWAY_PROBING_POLICY = new Policy.Builder().setIsClearText(false).setIsLowLatencyNecessary(false).setIsRequestResponseOnly(false).setIsDedicated(true).setIsAnonymousCredentialsAllowed(true).setPurpose(HEARTBEAT_PROBING_PURPOSE).setKeepAlive(KeepAlive.ADAPTIVE).build();
    protected boolean mGatewayEnabled = false;
    protected boolean mD2DGwEnabled = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class GatewayEnableAppLocalSetting {
        private GatewayEnableAppLocalSetting() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Boolean isAlwaysOnD2DEnabled() {
            Boolean booleanValue = RemoteSettingManager.getBooleanValue(GatewayConnectionService.ENABLE_ALWAYS_ON_D2D_KEY);
            if (booleanValue == null) {
                return false;
            }
            return booleanValue;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Boolean isGatewayEnabled() {
            Boolean booleanValue = RemoteSettingManager.getBooleanValue(GatewayConnectionService.ENABLE_GATEWAY_KEY);
            if (booleanValue == null) {
                return true;
            }
            return booleanValue;
        }
    }

    /* loaded from: classes12.dex */
    public static final class InitializationReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            GatewayConnectionService.log.info("onReceive", "received intent", MAPAccountManager.KEY_INTENT, intent);
            if (CommunicationServiceConstants.COMMUNICATION_SERVICE_INITIALIZED.equals(intent.getAction())) {
                context.startService(new Intent(context, GatewayConnectionService.class));
            }
        }
    }

    protected amazon.communication.connection.IConnection connect(EndpointIdentity endpointIdentity, Policy policy) {
        FailFast.expectFalse(Looper.myLooper() == Looper.getMainLooper(), "Cannot be executed on main looper");
        log.info(EmulateConnection.EXTRA_CONNECT, "connecting", "endpoint", endpointIdentity);
        try {
            return getCommunicationManager().acquireConnection(endpointIdentity, policy, this);
        } catch (Exception e) {
            this.mGatewayEnabled = false;
            this.mD2DGwEnabled = false;
            log.error(EmulateConnection.EXTRA_CONNECT, "error", "endpoint", endpointIdentity, e);
            return null;
        }
    }

    protected void disconnect(EndpointIdentity endpointIdentity, amazon.communication.connection.IConnection iConnection) {
        log.info(Metrics.DISCONNECT, "disconnecting", "endpoint", endpointIdentity);
        if (iConnection == null) {
            log.warn("disconnect()", "null connection object. This should never happen!", "endpoint", endpointIdentity);
        } else {
            iConnection.release();
        }
    }

    protected ICommunicationManager getCommunicationManager() {
        return CommunicationFactoryBase.getCommunicationManager(this);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // amazon.communication.connection.IConnection.ConnectionListener
    public void onClosed(amazon.communication.connection.IConnection iConnection, ConnectionClosedDetails connectionClosedDetails) {
        log.info("onClosed", "connection closed", "connection", iConnection, "details", connectionClosedDetails);
    }

    @Override // android.app.Service
    public void onCreate() {
        log.info("onCreate", "service created", new Object[0]);
        this.mHandler = new Handler();
        this.mSettingsListener = new SettingUpdateListener() { // from class: com.amazon.communication.GatewayConnectionService.2
            @Override // com.amazon.communication.remotesetting.SettingUpdateListener
            public void onSettingUpdated() {
                GatewayConnectionService.this.mHandler.post(new Runnable() { // from class: com.amazon.communication.GatewayConnectionService.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GatewayConnectionService.log.info("GatewayConnectionService", "SettingsUpdateCallBack", "Toggling connection.");
                        GatewayConnectionService.this.toggleConnections();
                    }
                });
            }
        };
        RemoteSettingManager.addSettingUpdateListener(this.mSettingsListener);
    }

    @Override // android.app.Service
    public void onDestroy() {
        log.info("onDestroy", "service destroyed", new Object[0]);
        RemoteSettingManager.removeSettingUpdateListener(this.mSettingsListener);
    }

    @Override // amazon.communication.connection.IConnection.ConnectionListener
    public void onOpened(amazon.communication.connection.IConnection iConnection) {
        log.info("onOpened", "connection opened", "connection", iConnection);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        log.info("onStartCommand", "service started", MAPAccountManager.KEY_INTENT, intent, "flags", Integer.valueOf(i), AppUrl.ACMS.QueryParam.Keys.MESSAGE_START_ID, Integer.valueOf(i2));
        toggleConnections();
        return 1;
    }

    protected void toggleConnections() {
        FailFast.expectTrue(Looper.myLooper() == Looper.getMainLooper(), "Should only be called on main looper");
        boolean booleanValue = GatewayEnableAppLocalSetting.isGatewayEnabled().booleanValue();
        boolean booleanValue2 = GatewayEnableAppLocalSetting.isAlwaysOnD2DEnabled().booleanValue();
        log.info("toggleConnections", "got settings values", "gatewayEnabled", Boolean.valueOf(this.mGatewayEnabled), "Settings value", Boolean.valueOf(booleanValue), "d2dEnabled", Boolean.valueOf(this.mD2DGwEnabled), "Settings value", Boolean.valueOf(booleanValue2));
        if (!this.mGatewayEnabled && booleanValue) {
            this.mGatewayEnabled = true;
            AsyncTask.execute(new Runnable() { // from class: com.amazon.communication.GatewayConnectionService.3
                @Override // java.lang.Runnable
                public void run() {
                    GatewayConnectionService gatewayConnectionService = GatewayConnectionService.this;
                    gatewayConnectionService.mGatewayConnection = gatewayConnectionService.connect(GatewayConnectionService.GATEWAY_ENDPOINT, GatewayConnectionService.GATEWAY_POLICY);
                }
            });
        } else if (this.mGatewayEnabled && !booleanValue) {
            this.mGatewayEnabled = false;
            AsyncTask.execute(new Runnable() { // from class: com.amazon.communication.GatewayConnectionService.4
                @Override // java.lang.Runnable
                public void run() {
                    GatewayConnectionService gatewayConnectionService = GatewayConnectionService.this;
                    gatewayConnectionService.disconnect(GatewayConnectionService.GATEWAY_ENDPOINT, gatewayConnectionService.mGatewayConnection);
                    GatewayConnectionService.this.mGatewayConnection = null;
                }
            });
        }
        if (!this.mD2DGwEnabled && booleanValue2) {
            this.mD2DGwEnabled = true;
            AsyncTask.execute(new Runnable() { // from class: com.amazon.communication.GatewayConnectionService.5
                @Override // java.lang.Runnable
                public void run() {
                    GatewayConnectionService gatewayConnectionService = GatewayConnectionService.this;
                    gatewayConnectionService.mD2DGwConnection = gatewayConnectionService.connect(GatewayConnectionService.DIRECTED_GATEWAY_ENDPOINT, GatewayConnectionService.D2D_GW_POLICY);
                }
            });
        } else if (!this.mD2DGwEnabled || booleanValue2) {
        } else {
            this.mD2DGwEnabled = false;
            AsyncTask.execute(new Runnable() { // from class: com.amazon.communication.GatewayConnectionService.6
                @Override // java.lang.Runnable
                public void run() {
                    GatewayConnectionService gatewayConnectionService = GatewayConnectionService.this;
                    gatewayConnectionService.disconnect(GatewayConnectionService.DIRECTED_GATEWAY_ENDPOINT, gatewayConnectionService.mD2DGwConnection);
                    GatewayConnectionService.this.mD2DGwConnection = null;
                }
            });
        }
    }
}
