package amazon.speech.simclient;

import amazon.speech.model.DeviceContext;
import amazon.speech.model.DirectiveEnvelope;
import amazon.speech.model.Event;
import amazon.speech.model.IEventCallback;
import amazon.speech.model.IIdleTimeCallback;
import amazon.speech.model.KeyValueDeviceContext;
import amazon.speech.model.PayloadDeviceContext;
import amazon.speech.simclient.ISimClientManager;
import amazon.speech.simclient.internal.C;
import amazon.speech.util.RuntimeDeviceTypeHelper;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.comms.ringservice.Sdp;
import com.amazon.communication.support.CsmPackageChangedBroadcastReceiver;
import com.amazon.metrics.MetricsUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class SimClient {
    public static final int AUDIO_CHANNEL_ALERT = 1;
    public static final int AUDIO_CHANNEL_COMMS = 4;
    public static final int AUDIO_CHANNEL_CONTENT = 2;
    public static final int AUDIO_CHANNEL_DIALOG = 3;
    protected static final int BIND_FLAGS = 1;
    public static final int EVENT_ALEXA_BLUECHROME_DISMISSED = 101;
    public static final int EVENT_ALEXA_BLUECHROME_SHOWN = 100;
    public static final int EVENT_ALEXA_DIALOG_DISMISSED = 103;
    public static final int EVENT_ALEXA_DIALOG_SHOWN = 102;
    public static final String EVENT_DATA_KEY_EVENT = "EVENT";
    public static final String EVENT_DATA_KEY_TIMESTAMP = "TIMESTAMP";
    public static final String EVENT_DATA_KEY_TRIGGER = "TRIGGER";
    public static final int EVENT_FLAG_DEFAULT = 0;
    public static final int EVENT_TRIGGER_AUTOMATIC = 100;
    public static final int EVENT_TRIGGER_NA = -1;
    public static final int EVENT_TRIGGER_USER = 101;
    static final String METADATA_KEY_CALLER = "caller";
    static final String METADATA_KEY_ERROR_MSG = "error_msg";
    static final String METRIC_PROGRAM_NAME = "SimClientAPI";
    static final String METRIC_SIMCLIENT_COMMUNICATION_FAILURE = "SIMClientAPI.SIMClient.SimCommunicationFailure";
    static final String METRIC_SIMCLIENT_CRASH = "SIMClientAPI.SIMClient.SimClientCrash";
    static final String METRIC_SIMCLIENT_INSTANCE_TO_SERVICE_CONNECT_TIME = "SIMClientAPI.SIMClient.SimClientConnectTime";
    static final String METRIC_SIMCLIENT_LISTEN = "SIMClientAPI.SIMClient.ListenInvoked";
    static final String METRIC_SIMCLIENT_SERVICE_DISCONNECTION = "SIMClientAPI.SIMClient.SimServiceDisconnection";
    static final String METRIC_SIMCLIENT_SERVICE_RECONNECT_TIME = "SIMClientAPI.SIMClient.SimServiceReconnectTime";
    static final String METRIC_SOURCE = "SimClientAPI_Metrics";
    protected static final String SERVICE_ACTION = "amazon.speech.intent.action.GET_SIMCLIENT";
    protected static final String SERVICE_PERMISSION = "amazon.speech.permission.SEND_DATA_TO_ALEXA";
    private static final String TAG = "SimClient";
    public static final int VISUAL_CHANNEL_INFOCUS = 51;
    static TtsPlayer sTtsPlayer;
    private final IBinder mBinder;
    private Context mContext;
    private IConnectionListener mListener;
    private final MetricsUtil mMetricsUtil;
    protected ISimClientManager mService;
    ServiceConnection mServiceConnection;
    TtsPlayer mTtsPlayer;

    /* loaded from: classes.dex */
    private class SimServiceConnection implements ServiceConnection {
        private SimServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            SimClient.this.onConnectedToService(componentName, iBinder);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            SimClient.this.onDisconnectedFromService(componentName);
        }
    }

    protected SimClient(Context context, IConnectionListener iConnectionListener) {
        this(context, iConnectionListener, MetricsUtil.getInstance());
    }

    private int addAudioFocus(String str, int i) {
        if (str != null) {
            if (this.mService == null) {
                return SimError.SIM_CONNECTION_UNAVAILABLE;
            }
            try {
                try {
                    Trace.beginSection("addAudioFocus");
                    this.mService.addAudioFocus(str, i, this.mBinder);
                    Trace.endSection();
                    return 0;
                } catch (RemoteException e) {
                    Log.e(TAG, "RemoteException on addAudioFocus", e);
                    recordCommFailureMetric("RemoteException on addAudioFocus: " + e.toString());
                    Trace.endSection();
                    return SimError.SIM_COMMUNICATION_FAILURE;
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        throw new IllegalArgumentException("Namespace cannot be null");
    }

    private void clearAllMetricsTimers() {
        this.mMetricsUtil.removeTimer(this.mContext, METRIC_SIMCLIENT_SERVICE_RECONNECT_TIME);
        this.mMetricsUtil.removeTimer(this.mContext, METRIC_SIMCLIENT_INSTANCE_TO_SERVICE_CONNECT_TIME);
    }

    public static boolean createClient(Context context, IConnectionListener iConnectionListener) {
        return createClient(context, iConnectionListener, MetricsUtil.getInstance());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent getBindIntent(Context context) {
        String str;
        String str2;
        Intent intent = new Intent(SERVICE_ACTION);
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            str = CsmPackageChangedBroadcastReceiver.CSM_PACKAGE_NAME;
            str2 = "amazon.speech.sim.service.SpeechInteractionService";
        } else {
            for (ResolveInfo resolveInfo : queryIntentServices) {
                String str3 = resolveInfo.serviceInfo.packageName;
                if (packageManager.checkPermission("amazon.speech.permission.SEND_DATA_TO_ALEXA", str3) != 0) {
                    Log.w(TAG, String.format("Package (%s) has NOT declared support for (%s)", str3, "amazon.speech.permission.SEND_DATA_TO_ALEXA"));
                    queryIntentServices.remove(resolveInfo);
                }
            }
            if (!queryIntentServices.isEmpty()) {
                if (queryIntentServices.size() > 1) {
                    Log.wtf(TAG, "More than one service exists to handle intent");
                }
                str = queryIntentServices.get(0).serviceInfo.packageName;
                str2 = queryIntentServices.get(0).serviceInfo.name;
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No service exists with proper permission to handle intent: ");
                outline107.append(intent.getAction());
                throw new RuntimeException(outline107.toString());
            }
        }
        String str4 = "Found " + str + "/" + str2 + " to handle intent";
        intent.setClassName(str, str2);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void onConnectedToService(ComponentName componentName, IBinder iBinder) {
        if (this.mContext == null) {
            Log.e(TAG, "Client has been destroyed - service connect no-op");
            return;
        }
        this.mService = ISimClientManager.Stub.asInterface(iBinder);
        try {
            this.mService.attachClientInstance(this.mBinder);
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to attach client instance to SIM", e);
            recordCrashMetric("Unable to attach client instance to SIM: " + e.toString());
        }
        recordServiceConnectMetrics();
        this.mListener.onConnect(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void onDisconnectedFromService(ComponentName componentName) {
        if (this.mContext == null) {
            Log.e(TAG, "Client has been destroyed - service disconnect no-op");
            return;
        }
        this.mListener.onDisconnect();
        this.mService = null;
        recordServiceDisconnectMetrics();
    }

    private void recordClientCreationMetrics() {
        clearAllMetricsTimers();
        this.mMetricsUtil.startTimer(this.mContext, METRIC_SOURCE, METRIC_PROGRAM_NAME, METRIC_SIMCLIENT_INSTANCE_TO_SERVICE_CONNECT_TIME);
    }

    private void recordClientDestructionMetrics() {
        clearAllMetricsTimers();
    }

    private boolean recordCrashMetric(String str) {
        return this.mMetricsUtil.recordOccurrence(this.mContext, METRIC_SOURCE, METRIC_PROGRAM_NAME, METRIC_SIMCLIENT_CRASH, new MetricsUtil.MetadataHelper(METADATA_KEY_ERROR_MSG, str));
    }

    private boolean recordListenMetric() {
        return this.mMetricsUtil.recordOccurrence(this.mContext, METRIC_SOURCE, METRIC_PROGRAM_NAME, METRIC_SIMCLIENT_LISTEN, new MetricsUtil.MetadataHelper("caller", this.mContext.getPackageName()));
    }

    private void recordServiceConnectMetrics() {
        this.mMetricsUtil.stopTimer(this.mContext, METRIC_SIMCLIENT_SERVICE_RECONNECT_TIME, null);
        this.mMetricsUtil.stopTimer(this.mContext, METRIC_SIMCLIENT_INSTANCE_TO_SERVICE_CONNECT_TIME, null);
    }

    private void recordServiceDisconnectMetrics() {
        this.mMetricsUtil.startTimer(this.mContext, METRIC_SOURCE, METRIC_PROGRAM_NAME, METRIC_SIMCLIENT_SERVICE_RECONNECT_TIME);
        this.mMetricsUtil.recordOccurrence(this.mContext, METRIC_SOURCE, METRIC_PROGRAM_NAME, METRIC_SIMCLIENT_SERVICE_DISCONNECTION, null);
    }

    private int releaseAudioFocus(String str, int i) {
        if (str != null) {
            if (this.mService == null) {
                return SimError.SIM_CONNECTION_UNAVAILABLE;
            }
            try {
                try {
                    Trace.beginSection("releaseAudioFocus");
                    this.mService.releaseAudioFocus(str, i, this.mBinder);
                    Trace.endSection();
                    return 0;
                } catch (RemoteException e) {
                    Log.e(TAG, "RemoteException on releaseAudioFocus", e);
                    recordCommFailureMetric("RemoteException on releaseAudioFocus: " + e.toString());
                    Trace.endSection();
                    return SimError.SIM_COMMUNICATION_FAILURE;
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        throw new IllegalArgumentException("Namespace cannot be null");
    }

    public int acknowledgeDirective(String str) {
        try {
            if (this.mService == null) {
                return SimError.SIM_CONNECTION_UNAVAILABLE;
            }
            try {
                Trace.beginSection("acknowledgeDirective");
                this.mService.acknowledgeDirective(str);
                Trace.endSection();
                return 0;
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException on acknowledgeDirective", e);
                recordCommFailureMetric("RemoteException on acknowledgeDirective: " + e.toString());
                Trace.endSection();
                return SimError.SIM_COMMUNICATION_FAILURE;
            }
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public int addAudioAlertFocus(String str) {
        return addAudioFocus(str, 1);
    }

    public int addAudioCommunicationsFocus(String str) {
        return addAudioFocus(str, 4);
    }

    public int addAudioContentFocus(String str) {
        return addAudioFocus(str, 2);
    }

    public int addAudioDialogFocus(String str) {
        return addAudioFocus(str, 3);
    }

    public int addDeviceContext(Header header, String str) {
        return addDeviceContext(header, str, false);
    }

    public int addVisualFocus(String str) {
        if (str != null) {
            if (this.mService == null) {
                return SimError.SIM_CONNECTION_UNAVAILABLE;
            }
            try {
                try {
                    Trace.beginSection("addVisualFocus");
                    this.mService.addVisualFocus(str, this.mBinder);
                    Trace.endSection();
                    return 0;
                } catch (RemoteException e) {
                    Log.e(TAG, "RemoteException on addVisualFocus", e);
                    recordCommFailureMetric("RemoteException on addVisualFocus: " + e.toString());
                    Trace.endSection();
                    return SimError.SIM_COMMUNICATION_FAILURE;
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        throw new IllegalArgumentException("Namespace cannot be null");
    }

    public int cancelSpeechDialogue(String str) {
        if (str != null) {
            try {
                if (this.mService == null) {
                    return SimError.SIM_CONNECTION_UNAVAILABLE;
                }
                try {
                    Trace.beginSection("cancelSpeechDialogue");
                    this.mService.cancelSpeechDialogue(str);
                    Trace.endSection();
                    return 0;
                } catch (RemoteException e) {
                    Log.e(TAG, "RemoteException on cancelSpeechDialogue", e);
                    recordCommFailureMetric("RemoteException on cancelSpeechDialogue: " + e.toString());
                    Trace.endSection();
                    return SimError.SIM_COMMUNICATION_FAILURE;
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        throw new IllegalArgumentException("receipt cannot be null");
    }

    public int clearDeathEvents() {
        try {
            if (this.mService == null) {
                return SimError.SIM_CONNECTION_UNAVAILABLE;
            }
            try {
                Trace.beginSection("ClearDeathEvent");
                this.mService.clearDeathEvents(this.mBinder);
                Trace.endSection();
                return 0;
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException on clearDeathEvent", e);
                recordCommFailureMetric("RemoteException on clearDeathEvent: " + e.toString());
                Trace.endSection();
                return SimError.SIM_COMMUNICATION_FAILURE;
            }
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public synchronized void destroy() {
        Log.i(TAG, "SimClient destroy()");
        if (this.mContext != null && this.mServiceConnection != null) {
            this.mContext.unbindService(this.mServiceConnection);
            this.mServiceConnection.onServiceDisconnected(new ComponentName(Sdp.SUSPEND_TRIGGER, Metrics.DISCONNECT));
        }
        if (sTtsPlayer != null) {
            sTtsPlayer.releaseAudioFocusLock();
            sTtsPlayer.setAudioFocusChangeListener(null);
        }
        if (this.mTtsPlayer != null) {
            this.mTtsPlayer.releaseAudioFocusLock();
            this.mTtsPlayer.setAudioFocusChangeListener(null);
        }
        recordClientDestructionMetrics();
        this.mServiceConnection = null;
        this.mContext = null;
        this.mListener = null;
    }

    public Uri getAudioUri(String str) {
        if (this.mService == null) {
            Log.w(TAG, "SIM Service connection unavailable");
            return null;
        } else if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "getAudioUri was called with an empty or null String");
            return null;
        } else {
            try {
                try {
                    Trace.beginSection("getAudioUri");
                    if (str.startsWith("cid:")) {
                        str = this.mService.getAudioUrl(str);
                    }
                    Trace.endSection();
                    if (!TextUtils.isEmpty(str)) {
                        return Uri.parse(str);
                    }
                    return null;
                } catch (RemoteException e) {
                    Log.w(TAG, "RemoteException on getAudioUrl", e);
                    recordCrashMetric("RemoteException on getAudioUrl: " + e.toString());
                    Trace.endSection();
                    return null;
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
    }

    public List<DeviceContext> getDeviceContext() {
        ISimClientManager iSimClientManager = this.mService;
        if (iSimClientManager != null) {
            try {
                return iSimClientManager.getDeviceContext();
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException on getDeviceContext", e);
                recordCrashMetric("RemoteException on getDeviceContext: " + e.toString());
                throw new RuntimeException(e);
            }
        }
        throw new IllegalStateException("Service not yet connected");
    }

    public synchronized TtsPlayer getTtsPlayer() {
        if (this.mService != null && this.mContext != null) {
            if (RuntimeDeviceTypeHelper.isDeviceAmazon()) {
                if (sTtsPlayer == null) {
                    sTtsPlayer = new TtsPlayer(this.mContext, this);
                } else {
                    sTtsPlayer.setSimClientForTts(this);
                    sTtsPlayer.setAudioFocusChangeListener(new TtsAudioFocusChangeListener(sTtsPlayer));
                }
                return sTtsPlayer;
            }
            if (this.mTtsPlayer == null) {
                this.mTtsPlayer = new TtsPlayer(this.mContext, this);
            } else {
                this.mTtsPlayer.setSimClientForTts(this);
                this.mTtsPlayer.setAudioFocusChangeListener(new TtsAudioFocusChangeListener(this.mTtsPlayer));
            }
            return this.mTtsPlayer;
        }
        Log.w(TAG, "TtsPlayer cannot be created before the service is connected.");
        return null;
    }

    public int listen(int i, IListenCallback iListenCallback) {
        try {
            if (this.mService == null) {
                return SimError.SIM_CONNECTION_UNAVAILABLE;
            }
            try {
                Trace.beginSection("listenWithTimeout");
                Log.wtf(TAG, "Deprecated listen() API invoked!");
                recordListenMetric();
                this.mService.listenWithTimeout(i, iListenCallback);
                Trace.endSection();
                return 0;
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException on listen", e);
                recordCommFailureMetric("RemoteException on listen: " + e.toString());
                Trace.endSection();
                return SimError.SIM_COMMUNICATION_FAILURE;
            }
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean recordCommFailureMetric(String str) {
        return this.mMetricsUtil.recordOccurrence(this.mContext, METRIC_SOURCE, METRIC_PROGRAM_NAME, METRIC_SIMCLIENT_COMMUNICATION_FAILURE, new MetricsUtil.MetadataHelper(METADATA_KEY_ERROR_MSG, str));
    }

    public int releaseAudioAlertFocus(String str) {
        return releaseAudioFocus(str, 1);
    }

    public int releaseAudioCommunicationsFocus(String str) {
        return releaseAudioFocus(str, 4);
    }

    public int releaseAudioContentFocus(String str) {
        return releaseAudioFocus(str, 2);
    }

    public int releaseAudioDialogFocus(String str) {
        return releaseAudioFocus(str, 3);
    }

    public int releaseVisualFocus(String str) {
        if (str != null) {
            if (this.mService == null) {
                return SimError.SIM_CONNECTION_UNAVAILABLE;
            }
            try {
                try {
                    Trace.beginSection("releaseVisualFocus");
                    this.mService.releaseVisualFocus(str, this.mBinder);
                    Trace.endSection();
                    return 0;
                } catch (RemoteException e) {
                    Log.e(TAG, "RemoteException on releaseVisualFocus", e);
                    recordCommFailureMetric("RemoteException on releaseVisualFocus: " + e.toString());
                    Trace.endSection();
                    return SimError.SIM_COMMUNICATION_FAILURE;
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        throw new IllegalArgumentException("Namespace cannot be null");
    }

    public int removeDeviceContext(Header header) {
        if (this.mService == null) {
            return SimError.SIM_CONNECTION_UNAVAILABLE;
        }
        KeyValueDeviceContext keyValueDeviceContext = new KeyValueDeviceContext(header.getNamespace(), header.getName());
        try {
            try {
                Trace.beginSection("removeDeviceContext");
                this.mService.removeDeviceContext(keyValueDeviceContext);
                Trace.endSection();
                return 0;
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException on removeDeviceContext", e);
                recordCommFailureMetric("RemoteException on removeDeviceContext: " + e.toString());
                Trace.endSection();
                return SimError.SIM_COMMUNICATION_FAILURE;
            }
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public int retrieveDeviceIdleTime(IIdleTimeCallback iIdleTimeCallback) {
        if (iIdleTimeCallback != null) {
            try {
                if (this.mService == null) {
                    return SimError.SIM_CONNECTION_UNAVAILABLE;
                }
                try {
                    Trace.beginSection("getDeviceIdleTime");
                    this.mService.retrieveDeviceIdleTime(iIdleTimeCallback);
                    Trace.endSection();
                    return 0;
                } catch (RemoteException e) {
                    Log.e(TAG, "RemoteException on getDeviceIdleTime", e);
                    recordCommFailureMetric("RemoteException on getDeviceIdleTime: " + e.toString());
                    Trace.endSection();
                    return SimError.SIM_COMMUNICATION_FAILURE;
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        throw new IllegalArgumentException("Callback cannot be null");
    }

    void sendDestroy() {
        try {
            if (this.mService == null) {
                return;
            }
            try {
                Trace.beginSection("sendDestroy");
                this.mService.sendDestroy();
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException on sendDestroy", e);
                recordCrashMetric("RemoteException on sendDestroy: " + e.toString());
            }
        } finally {
            Trace.endSection();
        }
    }

    public int sendEvent(Header header, String str, int i) {
        if (header != null && str != null) {
            if (this.mService == null) {
                return SimError.SIM_CONNECTION_UNAVAILABLE;
            }
            Event event = new Event(header.getNamespace(), header.getName(), header.getLabel(), header.getTimestamp(), str);
            try {
                try {
                    Trace.beginSection("sendEvent");
                    this.mService.sendEvent(event);
                    Trace.endSection();
                    return 0;
                } catch (RemoteException e) {
                    Log.e(TAG, "RemoteException on sendEvent", e);
                    recordCommFailureMetric("RemoteException on sendEvent: " + e.toString());
                    Trace.endSection();
                    return SimError.SIM_COMMUNICATION_FAILURE;
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        throw new IllegalArgumentException();
    }

    public int sendLocalEvent(Header header, String str, final IEventStatusCallback iEventStatusCallback, final ILocalResponseCallback iLocalResponseCallback, int i) {
        if (header != null && str != null && iEventStatusCallback != null && iLocalResponseCallback != null) {
            if (this.mService == null) {
                return SimError.SIM_CONNECTION_UNAVAILABLE;
            }
            Event event = new Event(header.getNamespace(), header.getName(), header.getLabel(), header.getTimestamp(), str);
            IEventCallback.Stub stub = new IEventCallback.Stub() { // from class: amazon.speech.simclient.SimClient.2
                @Override // amazon.speech.model.IEventCallback
                public boolean isLocal() {
                    return true;
                }

                @Override // amazon.speech.model.IEventCallback
                public void onError(int i2) {
                    try {
                        Trace.beginSection("onError");
                        String descriptionFor = SimError.getDescriptionFor(i2);
                        Log.e(SimClient.TAG, String.format("onError(%d): (%s)", Integer.valueOf(i2), descriptionFor));
                        iEventStatusCallback.onError(descriptionFor, i2);
                    } finally {
                        Trace.endSection();
                    }
                }

                @Override // amazon.speech.model.IEventCallback
                public void onResponse(List<DirectiveEnvelope> list) {
                    try {
                        Trace.beginSection("onResponse");
                        String unused = SimClient.TAG;
                        String.format("onResponse received with (%d) directives", Integer.valueOf(list.size()));
                        ArrayList arrayList = new ArrayList();
                        for (DirectiveEnvelope directiveEnvelope : list) {
                            String fullJson = directiveEnvelope.getFullJson();
                            if (fullJson != null) {
                                arrayList.add(fullJson);
                            } else {
                                Log.w(SimClient.TAG, String.format("Directive (%s_%s) did not contain full json", directiveEnvelope.getNamespace(), directiveEnvelope.getName()));
                            }
                        }
                        iLocalResponseCallback.onResponse(arrayList);
                        iEventStatusCallback.onFinish();
                    } finally {
                        Trace.endSection();
                    }
                }
            };
            try {
                try {
                    Trace.beginSection("sendEventWithNotification");
                    this.mService.sendEventWithNotification(event, stub);
                    Trace.endSection();
                    return 0;
                } catch (RemoteException e) {
                    Log.e(TAG, "RemoteException on sendLocalEvent", e);
                    recordCommFailureMetric("RemoteException on sendLocalEvent: " + e.toString());
                    Trace.endSection();
                    return SimError.SIM_COMMUNICATION_FAILURE;
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        throw new IllegalArgumentException();
    }

    public int setDeathEvent(Header header, String str, int i) {
        if (header != null && str != null) {
            if (this.mService == null) {
                return SimError.SIM_CONNECTION_UNAVAILABLE;
            }
            Event event = new Event(header.getNamespace(), header.getName(), str);
            ArrayList arrayList = new ArrayList();
            arrayList.add(event);
            try {
                try {
                    Trace.beginSection("SetDeathEvent");
                    this.mService.setDeathEvents(arrayList, this.mBinder);
                    Trace.endSection();
                    return 0;
                } catch (RemoteException e) {
                    Log.e(TAG, "RemoteException on setDeathEvent", e);
                    recordCommFailureMetric("RemoteException on setDeathEvent: " + e.toString());
                    Trace.endSection();
                    return SimError.SIM_COMMUNICATION_FAILURE;
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimClient(Context context, IConnectionListener iConnectionListener, MetricsUtil metricsUtil) {
        if (context != null) {
            if (metricsUtil != null) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append(TAG);
                sb.append("API version (");
                sb.append(context.getPackageName());
                sb.append("): ");
                GeneratedOutlineSupport1.outline179(sb, C.BF_BUILD_NUMBER, str);
                this.mContext = context;
                this.mListener = iConnectionListener;
                this.mMetricsUtil = metricsUtil;
                this.mBinder = new Binder();
                recordClientCreationMetrics();
                if (this.mListener == null) {
                    return;
                }
                this.mServiceConnection = new SimServiceConnection();
                return;
            }
            throw new IllegalArgumentException("Metrics cannot be null");
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    static boolean createClient(Context context, IConnectionListener iConnectionListener, MetricsUtil metricsUtil) {
        if (iConnectionListener != null) {
            if (metricsUtil != null) {
                return context.bindService(getBindIntent(context), new SimClient(context, iConnectionListener, metricsUtil).mServiceConnection, 1);
            }
            throw new IllegalArgumentException("Metrics cannot be null");
        }
        throw new IllegalArgumentException("Listener cannot be null");
    }

    public int addDeviceContext(Header header, String str, boolean z) {
        if (this.mService == null) {
            return SimError.SIM_CONNECTION_UNAVAILABLE;
        }
        PayloadDeviceContext payloadDeviceContext = new PayloadDeviceContext(header.getNamespace(), header.getName(), str);
        try {
            try {
                Trace.beginSection("addDeviceContext");
                this.mService.addDeviceContext(payloadDeviceContext, z);
                Trace.endSection();
                return 0;
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException on addDeviceContext", e);
                recordCommFailureMetric("RemoteException on addDeviceContext: " + e.toString());
                Trace.endSection();
                return SimError.SIM_COMMUNICATION_FAILURE;
            }
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public int acknowledgeDirective(String str, long j) {
        try {
            if (this.mService == null) {
                return SimError.SIM_CONNECTION_UNAVAILABLE;
            }
            try {
                Trace.beginSection("acknowledgeDirective");
                this.mService.acknowledgeDirectiveWithTimestamp(str, j);
                Trace.endSection();
                return 0;
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException on acknowledgeDirective", e);
                recordCommFailureMetric("RemoteException on acknowledgeDirective: " + e.toString());
                Trace.endSection();
                return SimError.SIM_COMMUNICATION_FAILURE;
            }
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public synchronized int sendEvent(Header header, String str, final IEventStatusCallback iEventStatusCallback, int i) {
        if (header != null && str != null && iEventStatusCallback != null) {
            if (this.mService == null) {
                return SimError.SIM_CONNECTION_UNAVAILABLE;
            }
            Event event = new Event(header.getNamespace(), header.getName(), header.getLabel(), header.getTimestamp(), str);
            IEventCallback.Stub stub = new IEventCallback.Stub() { // from class: amazon.speech.simclient.SimClient.1
                @Override // amazon.speech.model.IEventCallback
                public boolean isLocal() {
                    return false;
                }

                @Override // amazon.speech.model.IEventCallback
                public void onError(int i2) {
                    try {
                        Trace.beginSection("onError");
                        String descriptionFor = SimError.getDescriptionFor(i2);
                        Log.e(SimClient.TAG, String.format("onError(%d): (%s)", Integer.valueOf(i2), descriptionFor));
                        iEventStatusCallback.onError(descriptionFor, i2);
                    } finally {
                        Trace.endSection();
                    }
                }

                @Override // amazon.speech.model.IEventCallback
                public void onResponse(List<DirectiveEnvelope> list) {
                    try {
                        Trace.beginSection("onResponse");
                        String unused = SimClient.TAG;
                        String.format("onResponse received with (%d) directives", Integer.valueOf(list.size()));
                        iEventStatusCallback.onFinish();
                    } finally {
                        Trace.endSection();
                    }
                }
            };
            try {
                Trace.beginSection("sendEventWithNotification");
                this.mService.sendEventWithNotification(event, stub);
                Trace.endSection();
                return 0;
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException on sendEvent", e);
                recordCommFailureMetric("RemoteException on sendEvent: " + e.toString());
                Trace.endSection();
                return SimError.SIM_COMMUNICATION_FAILURE;
            }
        }
        throw new IllegalArgumentException();
    }
}
