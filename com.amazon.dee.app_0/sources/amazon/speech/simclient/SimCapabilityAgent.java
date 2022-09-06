package amazon.speech.simclient;

import amazon.speech.model.DirectiveEnvelope;
import amazon.speech.simclient.ISimCapabilityManager;
import amazon.speech.simclient.ISimClientService;
import amazon.speech.util.DebugUtil;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes.dex */
public abstract class SimCapabilityAgent extends Service implements ISimClientService {
    private static final String SIM_AIDL_ROUTER_SERVICE_NAME = "amazon.speech.sim.router.aidlrouterservice";
    private static final int SIM_DROP_DIRECTIVES = 2;
    private static final String SIM_NAMESPACE = "amazon.speech.sim";
    private static final int SIM_PREPARE_DIRECTIVES = 0;
    private static final int SIM_START_DIRECTIVES = 1;
    private Handler mHandler;
    private ISimCapabilityManager mISIMService;
    private final SimClientServiceStub mSimClientServiceStub = new SimClientServiceStub();
    private ServiceConnection mSimConnection = new ServiceConnection() { // from class: amazon.speech.simclient.SimCapabilityAgent.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            SimCapabilityAgent.this.mISIMService = ISimCapabilityManager.Stub.asInterface(iBinder);
            if (SimCapabilityAgent.DEBUG) {
                String unused = SimCapabilityAgent.TAG;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (SimCapabilityAgent.DEBUG) {
                String unused = SimCapabilityAgent.TAG;
            }
            SimCapabilityAgent.this.mISIMService = null;
            SimCapabilityAgent.this.connectToServer();
        }
    };
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.SIM, SimCapabilityAgent.class);
    private static final boolean DEBUG = DebugUtil.getShouldDebug(DebugUtil.Module.SIM);

    /* loaded from: classes.dex */
    class SimClientServiceStub extends ISimClientService.Stub {
        SimClientServiceStub() {
        }

        @Override // amazon.speech.simclient.ISimClientService
        public void onDropDirectives(List<DirectiveEnvelope> list) {
            SimCapabilityAgent.this.mHandler.sendMessage(SimCapabilityAgent.this.mHandler.obtainMessage(2, new SIMMessage(list)));
        }

        @Override // amazon.speech.simclient.ISimClientService
        public void onPrepareDirectives(List<DirectiveEnvelope> list) {
            SimCapabilityAgent.this.mHandler.sendMessage(SimCapabilityAgent.this.mHandler.obtainMessage(0, new SIMMessage(list)));
        }

        @Override // amazon.speech.simclient.ISimClientService
        public void onStartDirectives(List<DirectiveEnvelope> list) {
            SimCapabilityAgent.this.mHandler.sendMessage(SimCapabilityAgent.this.mHandler.obtainMessage(1, new SIMMessage(list)));
        }
    }

    /* loaded from: classes.dex */
    class SimIncomingHandler extends Handler {
        public SimIncomingHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                SimCapabilityAgent.this.onPrepareDirectives(((SIMMessage) message.obj).mDirectives);
            } else if (i == 1) {
                SimCapabilityAgent.this.onStartDirectives(((SIMMessage) message.obj).mDirectives);
            } else if (i == 2) {
                SimCapabilityAgent.this.onDropDirectives(((SIMMessage) message.obj).mDirectives);
            } else {
                throw new IllegalStateException("Msg not supported");
            }
            if (SimCapabilityAgent.this.mISIMService == null) {
                SimCapabilityAgent.this.connectToServer();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectToServer() {
        Intent intent = new Intent();
        intent.setPackage("amazon.speech.sim");
        intent.setAction(SIM_AIDL_ROUTER_SERVICE_NAME);
        GeneratedOutlineSupport1.outline173("connectToServer, bind to sim client service returns = ", bindService(intent, this.mSimConnection, 1), TAG);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return null;
    }

    protected final IBinder getCapabilityAgentBinder() {
        return this.mSimClientServiceStub;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (DEBUG) {
            String str = "onBind: " + intent;
        }
        return this.mSimClientServiceStub;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (DEBUG) {
            Log.i(TAG, "SimCapabilityAgent Service is starting");
        }
        this.mHandler = new SimIncomingHandler(getMainLooper());
    }

    protected void onDirectiveDropped(DirectiveEnvelope directiveEnvelope) {
        if (this.mISIMService != null) {
            if (DEBUG) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("sending onDirectiveDropped to SIM for Directive ");
                outline107.append(directiveEnvelope.getNamespace());
                outline107.toString();
            }
            try {
                this.mISIMService.onDirectiveDropped(directiveEnvelope);
                return;
            } catch (RemoteException unused) {
                Log.e(TAG, "Directive status could not be sent to SIM.");
                return;
            }
        }
        Log.e(TAG, "Not connected to SIM");
    }

    protected void onDirectiveError(DirectiveEnvelope directiveEnvelope) {
        if (this.mISIMService != null) {
            if (DEBUG) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("sending onDirectiveError to SIM for Directive ");
                outline107.append(directiveEnvelope.getNamespace());
                outline107.toString();
            }
            try {
                this.mISIMService.onDirectiveError(directiveEnvelope);
                return;
            } catch (RemoteException unused) {
                Log.e(TAG, "Directive status could not be sent to SIM.");
                return;
            }
        }
        Log.e(TAG, "Not connected to SIM");
    }

    protected void onDirectiveFinished(DirectiveEnvelope directiveEnvelope) {
        if (this.mISIMService != null) {
            if (DEBUG) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("sending onDirectiveFinished to SIM for Directive ");
                outline107.append(directiveEnvelope.getNamespace());
                outline107.toString();
            }
            try {
                this.mISIMService.onDirectiveFinished(directiveEnvelope);
                return;
            } catch (RemoteException unused) {
                Log.e(TAG, "Directive status could not be sent to SIM.");
                return;
            }
        }
        Log.e(TAG, "Not connected to SIM");
    }

    @Override // amazon.speech.simclient.ISimClientService
    public abstract void onDropDirectives(List<DirectiveEnvelope> list);

    @Override // amazon.speech.simclient.ISimClientService
    public abstract void onPrepareDirectives(List<DirectiveEnvelope> list);

    @Override // amazon.speech.simclient.ISimClientService
    public abstract void onStartDirectives(List<DirectiveEnvelope> list);
}
