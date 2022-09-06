package com.amazon.alexa.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.api.LeaderSelectionAuthority;
import com.amazon.alexa.api.Releasable;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.security.ComponentEnabler;
import com.amazon.alexa.utils.security.SignatureVerifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public abstract class AlexaConnection<T extends Releasable> extends ManagedServiceConnection<T> {
    private static final String TAG = AlexaServicesConnection.class.getSimpleName();
    private final Map<AlexaDialogController, AlexaDialogControllerProxy> alexaDialogControllers;
    private final Map<AlexaDialogControllerV2, AlexaDialogControllerProxyV2> alexaDialogControllersV2;
    private final BroadcastSender broadcastSender;
    private MessageReceiver<ClientConnectionControllerMessageType> clientConnectionController;
    private volatile boolean connectAfterLeaderSelection;
    private volatile boolean hasUserLoggedIn;
    private volatile LeaderSelectionAuthority.LeaderSelectionFailureReason leaderSelectionFailureReason;
    private volatile boolean leaderSelectionInProgress;
    private final LeaderSelectionListener leaderSelectionListener;
    private ComponentName serviceComponentName;
    private final Map<AlexaStateListener, AlexaStateListenerProxy> stateListeners;

    /* renamed from: com.amazon.alexa.api.AlexaConnection$4  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$LeaderSelectionAuthority$LeaderSelectionFailureReason = new int[LeaderSelectionAuthority.LeaderSelectionFailureReason.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$LeaderSelectionAuthority$LeaderSelectionFailureReason[LeaderSelectionAuthority.LeaderSelectionFailureReason.NOT_VERIFIED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$LeaderSelectionAuthority$LeaderSelectionFailureReason[LeaderSelectionAuthority.LeaderSelectionFailureReason.MISSING_PACKAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$LeaderSelectionAuthority$LeaderSelectionFailureReason[LeaderSelectionAuthority.LeaderSelectionFailureReason.TIMEOUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$LeaderSelectionAuthority$LeaderSelectionFailureReason[LeaderSelectionAuthority.LeaderSelectionFailureReason.DISABLED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$LeaderSelectionAuthority$LeaderSelectionFailureReason[LeaderSelectionAuthority.LeaderSelectionFailureReason.UNKNOWN_LEADER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$LeaderSelectionAuthority$LeaderSelectionFailureReason[LeaderSelectionAuthority.LeaderSelectionFailureReason.UNKNOWN_REASON.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class BroadcastSender {
        static final String ACTION = "amazon.alexa.intent.action.REPORT_METRIC";
        static final String EXTRA_NAME = "amazon.alexa.intent.extras.EVENT_NAME";
        private final Context context;

        private BroadcastSender(Context context) {
            this.context = context;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void sendEvent(AlexaMetricsName alexaMetricsName) {
            Intent intent = new Intent(ACTION);
            intent.setPackage(this.context.getPackageName());
            intent.putExtra(EXTRA_NAME, alexaMetricsName.getValue());
            this.context.sendBroadcast(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class ClientConnectionControllerProcessor extends ai {
        private ClientConnectionControllerProcessor() {
        }

        @Override // com.amazon.alexa.api.ai
        public void onForceDisconnect() {
            String str = AlexaConnection.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Force disconnecting client: ");
            outline107.append(AlexaConnection.this.getClient().getId());
            Log.i(str, outline107.toString());
            AlexaConnection.this.disconnect();
        }

        @Override // com.amazon.alexa.api.ai
        protected void onStartService() {
            AlexaConnection.this.startService();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class LeaderSelectionListener implements LeaderSelectionAuthority.LeaderSelectionListener {
        private final AlexaConnection alexaConnection;
        private final Lock lock;

        LeaderSelectionListener(AlexaConnection alexaConnection) {
            this.alexaConnection = alexaConnection;
            this.lock = alexaConnection.lock;
        }

        @Override // com.amazon.alexa.api.LeaderSelectionAuthority.LeaderSelectionListener
        public void onLeaderSelected(final ComponentName componentName) {
            this.alexaConnection.executeOnInternalConnectionThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaConnection.LeaderSelectionListener.1
                @Override // java.lang.Runnable
                public void run() {
                    LeaderSelectionListener.this.lock.lock();
                    try {
                        LeaderSelectionListener.this.alexaConnection.onLeaderSelected(componentName);
                    } finally {
                        LeaderSelectionListener.this.lock.unlock();
                    }
                }
            });
        }

        @Override // com.amazon.alexa.api.LeaderSelectionAuthority.LeaderSelectionListener
        public void onLeaderSelectionFailed(final LeaderSelectionAuthority.LeaderSelectionFailureReason leaderSelectionFailureReason, final Throwable th) {
            this.alexaConnection.executeOnInternalConnectionThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaConnection.LeaderSelectionListener.2
                @Override // java.lang.Runnable
                public void run() {
                    LeaderSelectionListener.this.lock.lock();
                    try {
                        LeaderSelectionListener.this.alexaConnection.onLeaderSelectionFailed(leaderSelectionFailureReason, th);
                    } finally {
                        LeaderSelectionListener.this.lock.unlock();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaConnection(@NonNull Context context, @NonNull SignatureVerifier signatureVerifier, @NonNull Handler handler, @NonNull Handler handler2) {
        super(context, signatureVerifier, handler, handler2);
        this.stateListeners = new ConcurrentHashMap();
        this.alexaDialogControllers = new ConcurrentHashMap();
        this.alexaDialogControllersV2 = new ConcurrentHashMap();
        this.leaderSelectionListener = new LeaderSelectionListener(this);
        this.broadcastSender = new BroadcastSender(context);
    }

    public AlexaConnection(@NonNull Context context, @Nullable String str) {
        super(context, str);
        this.stateListeners = new ConcurrentHashMap();
        this.alexaDialogControllers = new ConcurrentHashMap();
        this.alexaDialogControllersV2 = new ConcurrentHashMap();
        this.leaderSelectionListener = new LeaderSelectionListener(this);
        this.broadcastSender = new BroadcastSender(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doDeregisterClientConnectionController() {
        MessageReceiver<ClientConnectionControllerMessageType> messageReceiver = this.clientConnectionController;
        if (messageReceiver != null) {
            deregisterClientConnectionController(messageReceiver);
            getMessageReceiversManager().removeMessageReceiver(this.clientConnectionController);
            this.clientConnectionController = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaStateListenerProxy addListener(AlexaStateListener alexaStateListener, AlexaStateListenerProxy alexaStateListenerProxy) {
        return this.stateListeners.put(alexaStateListener, alexaStateListenerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaDialogControllerProxy addProxy(AlexaDialogController alexaDialogController, AlexaDialogControllerProxy alexaDialogControllerProxy) {
        return this.alexaDialogControllers.put(alexaDialogController, alexaDialogControllerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaDialogControllerProxyV2 addProxy(AlexaDialogControllerV2 alexaDialogControllerV2, AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2) {
        return this.alexaDialogControllersV2.put(alexaDialogControllerV2, alexaDialogControllerProxyV2);
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void connect() {
        executeOnInternalConnectionThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaConnection.3
            @Override // java.lang.Runnable
            public void run() {
                AlexaConnection.this.lock.lock();
                try {
                    if (!AlexaConnection.this.isServiceComponentNameResolved()) {
                        AlexaConnection.this.connectAfterLeaderSelection = true;
                    }
                    AlexaConnection.super.connect();
                } finally {
                    AlexaConnection.this.lock.unlock();
                }
            }
        });
    }

    MessageReceiver<ClientConnectionControllerMessageType> createClientConnectionController() {
        return getMessageReceiversManager().createMessageReceiver(new ClientConnectionControllerProcessor());
    }

    protected abstract void deregisterClientConnectionController(MessageReceiver<ClientConnectionControllerMessageType> messageReceiver);

    public BroadcastSender getBroadcastSender() {
        return this.broadcastSender;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LeaderSelectionAuthority.LeaderSelectionFailureReason getLeaderSelectionFailureReason() {
        return this.leaderSelectionFailureReason;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaDialogControllerProxy getProxy(AlexaDialogController alexaDialogController) {
        return this.alexaDialogControllers.get(alexaDialogController);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaDialogControllerProxyV2 getProxy(AlexaDialogControllerV2 alexaDialogControllerV2) {
        return this.alexaDialogControllersV2.get(alexaDialogControllerV2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public ComponentName getServiceComponentName() {
        return this.serviceComponentName;
    }

    protected abstract String getServiceName();

    public void initialize() {
        Log.i(TAG, "initialize");
        executeOnInternalConnectionThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaConnection.1
            @Override // java.lang.Runnable
            public void run() {
                AlexaConnection.this.lock.lock();
                try {
                    if (!AlexaConnection.this.isServiceComponentNameResolved() && !AlexaConnection.this.leaderSelectionInProgress) {
                        AlexaConnection.this.resolveServiceComponentName();
                    }
                } finally {
                    AlexaConnection.this.lock.unlock();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public boolean internalIsConnected() {
        return super.internalIsConnected() && this.hasUserLoggedIn;
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    protected boolean isServiceComponentNameResolved() {
        return this.serviceComponentName != null;
    }

    protected abstract boolean isUserLoggedIn();

    protected void onClientConnected() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onClientDisconnected() {
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    protected void onConnectedToService() {
        doDeregisterClientConnectionController();
        this.clientConnectionController = createClientConnectionController();
        registerClientConnectionController(this.clientConnectionController);
        this.hasUserLoggedIn = isUserLoggedIn();
        updateConnectedState();
        if (this.hasUserLoggedIn) {
            onClientConnected();
            return;
        }
        sendConnectingFailed(AlexaConnectingFailedReason.NO_ALEXA_SERVICES_ACCOUNT_REGISTERED, "The AlexaServices does not have a registered account.");
        disconnect();
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    protected void onDisconnectedFromService() {
        onClientDisconnected();
        doDeregisterClientConnectionController();
        if (!ComponentEnabler.checkIfServiceExists(getContext().getPackageManager(), this.serviceComponentName)) {
            this.serviceComponentName = null;
        }
    }

    void onLeaderSelected(ComponentName componentName) {
        String str = TAG;
        Log.i(str, "Leading Alexa service: " + componentName);
        this.serviceComponentName = componentName;
        this.leaderSelectionInProgress = false;
        this.leaderSelectionFailureReason = null;
        if (this.connectAfterLeaderSelection) {
            this.connectAfterLeaderSelection = false;
            connect();
        }
    }

    void onLeaderSelectionFailed(LeaderSelectionAuthority.LeaderSelectionFailureReason leaderSelectionFailureReason, Throwable th) {
        BroadcastSender broadcastSender;
        AlexaMetricsName alexaMetricsName;
        Log.e(TAG, "Failed to select leading Alexa service!", th);
        this.leaderSelectionInProgress = false;
        this.leaderSelectionFailureReason = leaderSelectionFailureReason;
        int ordinal = leaderSelectionFailureReason.ordinal();
        if (ordinal == 1) {
            sendConnectingFailed(AlexaConnectingFailedReason.NO_ALEXA_SERVICES_TO_CONNECT_TO, "Could not find an AlexaServices to connect to.");
            broadcastSender = this.broadcastSender;
            alexaMetricsName = AlexaMetricsName.LeaderSelection.UNKNOWN_LEADER;
        } else if (ordinal == 2) {
            sendConnectingFailed(AlexaConnectingFailedReason.TIMEOUT, "Timed out finding an AlexaServices to connect to.");
            broadcastSender = this.broadcastSender;
            alexaMetricsName = AlexaMetricsName.LeaderSelection.TIMEOUT;
        } else if (ordinal == 3) {
            sendConnectingFailed(AlexaConnectingFailedReason.UNAUTHORIZED, "Signature verification error");
            broadcastSender = this.broadcastSender;
            alexaMetricsName = AlexaMetricsName.LeaderSelection.INCORRECT_SIGNATURE;
        } else if (ordinal == 4) {
            sendConnectingFailed(AlexaConnectingFailedReason.UNAUTHORIZED, "Signature verification error - package name was null");
            broadcastSender = this.broadcastSender;
            alexaMetricsName = AlexaMetricsName.LeaderSelection.MISSING_PACKAGENAME;
        } else if (ordinal != 5) {
            sendConnectingFailed(AlexaConnectingFailedReason.UNKNOWN, "Unknown error encountered while searching for AlexaServices to connect to");
            broadcastSender = this.broadcastSender;
            alexaMetricsName = AlexaMetricsName.LeaderSelection.UNKNOWN_ERROR;
        } else {
            sendConnectingFailed(AlexaConnectingFailedReason.ALEXA_SERVICES_DISABLED, "Leader Selection Service disabled");
            broadcastSender = this.broadcastSender;
            alexaMetricsName = AlexaMetricsName.LeaderSelection.DISABLED;
        }
        broadcastSender.sendEvent(alexaMetricsName);
    }

    protected abstract void registerClientConnectionController(MessageReceiver<ClientConnectionControllerMessageType> messageReceiver);

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void release() {
        executeOnInternalConnectionThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaConnection.2
            @Override // java.lang.Runnable
            public void run() {
                AlexaConnection.this.lock.lock();
                try {
                    AlexaConnection.this.doDeregisterClientConnectionController();
                    AlexaConnection.super.release();
                } finally {
                    AlexaConnection.this.lock.unlock();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaStateListenerProxy removeListener(AlexaStateListener alexaStateListener) {
        return this.stateListeners.remove(alexaStateListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaDialogControllerProxy removeProxy(AlexaDialogController alexaDialogController) {
        return this.alexaDialogControllers.remove(alexaDialogController);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaDialogControllerProxyV2 removeProxy(AlexaDialogControllerV2 alexaDialogControllerV2) {
        return this.alexaDialogControllersV2.remove(alexaDialogControllerV2);
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    protected void resolveServiceComponentName() {
        if (!this.leaderSelectionInProgress) {
            resolveTargetServiceComponentName();
        }
    }

    void resolveTargetServiceComponentName() {
        this.leaderSelectionInProgress = true;
        LeaderSelectionAuthority leaderSelectionAuthority = new LeaderSelectionAuthority(getContext());
        String serviceName = getServiceName();
        this.broadcastSender.sendEvent(AlexaMetricsName.LeaderSelection.ATTEMPTED);
        leaderSelectionAuthority.pickAlexaService(serviceName, this.leaderSelectionListener);
    }
}
