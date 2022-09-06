package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import amazon.speech.simclient.genericconnection.ConnectionResult;
import amazon.speech.simclient.genericconnection.GenericConnectionClient;
import amazon.speech.simclient.genericconnection.GenericConnectionStatusCallback;
import amazon.speech.simclient.genericconnection.ServiceSupportedCallback;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.android.codahalemetricreporter.impl.TextReportGenerator;
import com.amazon.communication.ICommunicationService;
import com.amazon.communication.ir.IIdentityResolver;
import com.amazon.communication.rlm.IAckHandler;
import com.amazon.communication.support.CsmPackageChangedBroadcastReceiver;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.SharedMetricRegistries;
import com.dp.utils.FailFast;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class AndroidTCommService extends Service {
    private static final long BIND_TIMEOUT_MS = 10000;
    private static final DPLogger log = new DPLogger("TComm.AndroidTCommService");
    static CountDownLatch mInitializeLatch;
    public static ITCommService mTCommService;
    private final IBinder mBinder = new ICommunicationService.Stub() { // from class: com.amazon.communication.AndroidTCommService.2
        @Override // com.amazon.communication.ICommunicationService
        public IConnection acquireConnection(ParcelableEndpointIdentity parcelableEndpointIdentity, ParcelableConnectionPolicy parcelableConnectionPolicy, IConnectionListener iConnectionListener, ParcelableStatus parcelableStatus) throws RemoteException {
            AndroidTCommService.this.waitForITCommServiceInit();
            return ((ICommunicationService) AndroidTCommService.mTCommService.getBinder()).acquireConnection(parcelableEndpointIdentity, parcelableConnectionPolicy, iConnectionListener, parcelableStatus);
        }

        @Override // com.amazon.communication.ICommunicationService
        public IConnection acquireConnectionEx(ParcelableEndpointIdentity parcelableEndpointIdentity, ParcelablePolicy parcelablePolicy, IConnectionListener iConnectionListener, ParcelableStatus parcelableStatus) throws RemoteException {
            AndroidTCommService.this.waitForITCommServiceInit();
            return ((ICommunicationService) AndroidTCommService.mTCommService.getBinder()).acquireConnectionEx(parcelableEndpointIdentity, parcelablePolicy, iConnectionListener, parcelableStatus);
        }

        @Override // com.amazon.communication.ICommunicationService
        public void deregisterMessageHandler(int i) throws RemoteException {
            AndroidTCommService.this.waitForITCommServiceInit();
            ((ICommunicationService) AndroidTCommService.mTCommService.getBinder()).deregisterMessageHandler(i);
        }

        @Override // com.amazon.communication.ICommunicationService
        public IGatewayConnectivity getGatewayConnectivity(IConnectionListener iConnectionListener, ParcelableStatus parcelableStatus) throws RemoteException {
            AndroidTCommService.this.waitForITCommServiceInit();
            return ((ICommunicationService) AndroidTCommService.mTCommService.getBinder()).getGatewayConnectivity(iConnectionListener, parcelableStatus);
        }

        @Override // com.amazon.communication.ICommunicationService
        public IIdentityResolver getIdentityResolver() throws RemoteException {
            AndroidTCommService.this.waitForITCommServiceInit();
            return ((ICommunicationService) AndroidTCommService.mTCommService.getBinder()).getIdentityResolver();
        }

        @Override // com.amazon.communication.ICommunicationService
        public boolean isInitialized() throws RemoteException {
            AndroidTCommService.this.waitForITCommServiceInit();
            return ((ICommunicationService) AndroidTCommService.mTCommService.getBinder()).isInitialized();
        }

        @Override // com.amazon.communication.ICommunicationService
        public int registerMessageHandler(int i, IMessageHandler iMessageHandler) throws RemoteException {
            AndroidTCommService.this.waitForITCommServiceInit();
            return ((ICommunicationService) AndroidTCommService.mTCommService.getBinder()).registerMessageHandler(i, iMessageHandler);
        }

        @Override // com.amazon.communication.ICommunicationService
        public void removeAckHandler() throws RemoteException {
            AndroidTCommService.this.waitForITCommServiceInit();
            ((ICommunicationService) AndroidTCommService.mTCommService.getBinder()).removeAckHandler();
        }

        @Override // com.amazon.communication.ICommunicationService
        public void routeMessage(ParcelableEndpointIdentity parcelableEndpointIdentity, MessageEnvelope messageEnvelope, int i) throws RemoteException {
            AndroidTCommService.this.waitForITCommServiceInit();
            ((ICommunicationService) AndroidTCommService.mTCommService.getBinder()).routeMessage(parcelableEndpointIdentity, messageEnvelope, i);
        }

        @Override // com.amazon.communication.ICommunicationService
        public void routeMessageFragment(ParcelableEndpointIdentity parcelableEndpointIdentity, int i, MessageEnvelope messageEnvelope, boolean z, int i2) throws RemoteException {
            AndroidTCommService.this.waitForITCommServiceInit();
            ((ICommunicationService) AndroidTCommService.mTCommService.getBinder()).routeMessageFragment(parcelableEndpointIdentity, i, messageEnvelope, z, i2);
        }

        @Override // com.amazon.communication.ICommunicationService
        public int setAckHandler(IAckHandler iAckHandler) throws RemoteException {
            AndroidTCommService.this.waitForITCommServiceInit();
            return ((ICommunicationService) AndroidTCommService.mTCommService.getBinder()).setAckHandler(iAckHandler);
        }
    };
    private GenericConnectionClient mGenericConnectionClient;
    private CsmPackageChangedBroadcastReceiver mReceiver;

    private void initialize() {
        try {
            log.info("initialize", "AndroidTCommService initializing", new Object[0]);
            registerReceiverForCsmApkUpdates();
            TCommRestartSwitch.getInstance().initialize(this);
            if (GenericConnectionClient.isGenericConnectionServiceAvailable(this)) {
                if (this.mGenericConnectionClient == null) {
                    this.mGenericConnectionClient = new GenericConnectionClient(this);
                }
                this.mGenericConnectionClient.queryServiceSupported(new ServiceSupportedCallback() { // from class: com.amazon.communication.AndroidTCommService.1
                    @Override // amazon.speech.simclient.genericconnection.ServiceSupportedCallback
                    public void onResult(boolean z) {
                        if (z) {
                            AndroidTCommService.log.info("initialize.onResult", "GenericConnectionClient available and supported", new Object[0]);
                            AndroidTCommService.this.mGenericConnectionClient.queryConnectionStatus(new GenericConnectionStatusCallback() { // from class: com.amazon.communication.AndroidTCommService.1.1
                                @Override // amazon.speech.simclient.genericconnection.GenericConnectionStatusCallback
                                public void onResult(ConnectionResult connectionResult) {
                                    if (connectionResult == ConnectionResult.TCOMM_DISCONNECTED) {
                                        AndroidTCommService.log.info("initialize.onResult.onResult", "CSM connection is not connected; using WebSockets", "status", connectionResult.toString());
                                        AndroidTCommService.mTCommService = new TCommService();
                                    } else {
                                        AndroidTCommService.log.info("initialize.onResult.onResult", "CSM creating connection; using HTTP/2 connection", "status", connectionResult.toString());
                                        AndroidTCommService.mTCommService = new CsmTCommService(AndroidTCommService.this.mGenericConnectionClient);
                                    }
                                    AndroidTCommService.mTCommService.initialize(AndroidTCommService.this, AndroidTCommService.mInitializeLatch);
                                }
                            });
                            return;
                        }
                        AndroidTCommService.log.info("initialize.onResult", "GenericConnectionClient available but not supported; using WebSockets", new Object[0]);
                        AndroidTCommService.mTCommService = new TCommService();
                        AndroidTCommService.mTCommService.initialize(AndroidTCommService.this, AndroidTCommService.mInitializeLatch);
                    }
                });
                return;
            }
            log.info("initialize", "GenericConnectionClient not available; using WebSockets", new Object[0]);
            mTCommService = new TCommService();
            mTCommService.initialize(this, mInitializeLatch);
        } catch (Exception e) {
            log.error("initialize", "initialization failed", e);
            FailFast.expectTrue(false, "initialization failed");
        }
    }

    private void registerReceiverForCsmApkUpdates() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        intentFilter.addDataSchemeSpecificPart(CsmPackageChangedBroadcastReceiver.CSM_PACKAGE_NAME, 0);
        intentFilter.addDataSchemeSpecificPart("com.amazon.vizzini", 0);
        this.mReceiver = new CsmPackageChangedBroadcastReceiver();
        getApplicationContext().registerReceiver(this.mReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitForITCommServiceInit() {
        try {
            if (mInitializeLatch.await(10000L, TimeUnit.MILLISECONDS)) {
                return;
            }
            log.warn("waitForITCommServiceInit", "ITCommService Binder was not initialized before timeout", new Object[0]);
        } catch (InterruptedException unused) {
            log.error("waitForITCommServiceInit", "Thread was interrupted while waiting for the Binder to initialize", new Object[0]);
            Thread.currentThread().interrupt();
        }
    }

    @Override // android.app.Service
    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        WriterOutputStream writerOutputStream = new WriterOutputStream(printWriter, Charset.forName("UTF-8").newDecoder());
        try {
            printWriter.println("[metrics]");
            TextReportGenerator.builder().build().generate(SharedMetricRegistries.getOrCreate(RouteName.MAIN), MetricFilter.ALL, writerOutputStream);
            writerOutputStream.flush();
        } catch (IOException e) {
            log.error("dump", "failed to dump state", e);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        log.info("onBind", "bind request received", MAPAccountManager.KEY_INTENT, intent);
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        mInitializeLatch = new CountDownLatch(1);
        initialize();
    }

    @Override // android.app.Service
    public void onDestroy() {
        log.info("onDestroy", "destroy request received", new Object[0]);
        ITCommService iTCommService = mTCommService;
        if (iTCommService != null) {
            iTCommService.shutdown();
            mTCommService = null;
        }
        GenericConnectionClient genericConnectionClient = this.mGenericConnectionClient;
        if (genericConnectionClient != null) {
            genericConnectionClient.teardown();
            this.mGenericConnectionClient = null;
        }
        if (this.mReceiver != null) {
            getApplicationContext().unregisterReceiver(this.mReceiver);
            this.mReceiver = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        log.debug("onStartCommand", "service started", MAPAccountManager.KEY_INTENT, intent, "flags", Integer.valueOf(i), AppUrl.ACMS.QueryParam.Keys.MESSAGE_START_ID, Integer.valueOf(i2));
        return 1;
    }

    public void routeMessage(EndpointIdentity endpointIdentity, Message message, int i) {
        mTCommService.routeMessage(endpointIdentity, message, i);
    }

    public void routeMessageFragment(ParcelableEndpointIdentity parcelableEndpointIdentity, int i, MessageEnvelope messageEnvelope, boolean z, int i2) {
        mTCommService.routeMessageFragment(parcelableEndpointIdentity, i, messageEnvelope, z, i2);
    }
}
