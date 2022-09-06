package com.amazon.deecomms.calling.phonecallcontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.api.AlexaContextProvider;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.alexa.CommsAudioInteractionScheduler;
import com.amazon.deecomms.alexa.unsent.event.pcc.PCCQueuedEvents;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import dagger.Lazy;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public class PhoneCallControllerManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, PhoneCallControllerManager.class);
    private final Lazy<AlexaServicesConnection> alexaServicesConnection;
    private final Lazy<CallStateListener> callStateListener;
    private final Lazy<CommsAudioInteractionScheduler> commsAudioInteractionScheduler;
    private final Lazy<Context> context;
    private final Lazy<PCCContextProvider> pccContextProvider;
    private final Lazy<PCCQueuedEvents> pccUnsentEventsManager;
    private final Lazy<TelephonyManager> telephonyManager;
    @VisibleForTesting
    final AlexaServiceConnectionReceiver alexaServiceConnectionReceiver = new AlexaServiceConnectionReceiver();
    @VisibleForTesting
    final AtomicBoolean isRegistered = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes12.dex */
    public class AlexaServiceConnectionReceiver extends BroadcastReceiver {
        AlexaServiceConnectionReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            PhoneCallControllerManager.LOG.i("Received intent in PCC ASC Receiver");
            if (Constants.ALEXA_SERVICE_CONNECTION_CONNECTED.equals(intent.getAction())) {
                PhoneCallControllerManager.LOG.i("Alexa Service connection for PCC connected");
                AlexaServices.ContextProvider.register((AlexaServicesConnection) PhoneCallControllerManager.this.alexaServicesConnection.mo358get(), (AlexaContextProvider) PhoneCallControllerManager.this.pccContextProvider.mo358get());
                ((PCCQueuedEvents) PhoneCallControllerManager.this.pccUnsentEventsManager.mo358get()).send();
                ((CommsAudioInteractionScheduler) PhoneCallControllerManager.this.commsAudioInteractionScheduler.mo358get()).acquireOrReleaseCommsFocus((AlexaServicesConnection) PhoneCallControllerManager.this.alexaServicesConnection.mo358get());
            } else if (!Constants.ALEXA_SERVICE_CONNECTION_DISCONNECTED.equals(intent.getAction())) {
            } else {
                PhoneCallControllerManager.LOG.i("Alexa Service connection for PCC dis-connected. Clearing pending events.");
                PhoneCallControllerManager.this.cleanUpVoxForPCC();
            }
        }
    }

    public PhoneCallControllerManager(@NonNull Lazy<AlexaServicesConnection> lazy, @NonNull Lazy<TelephonyManager> lazy2, @NonNull Lazy<CallStateListener> lazy3, @NonNull Lazy<Context> lazy4, @NonNull Lazy<PCCContextProvider> lazy5, @NonNull Lazy<PCCQueuedEvents> lazy6, @NonNull Lazy<CommsAudioInteractionScheduler> lazy7) {
        this.telephonyManager = lazy2;
        this.callStateListener = lazy3;
        this.context = lazy4;
        this.pccUnsentEventsManager = lazy6;
        this.alexaServicesConnection = lazy;
        this.pccContextProvider = lazy5;
        this.commsAudioInteractionScheduler = lazy7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void cleanUpVoxForPCC() {
        this.pccUnsentEventsManager.mo358get().clear();
        if (!this.commsAudioInteractionScheduler.mo358get().shouldAcquireCommsFocus()) {
            this.commsAudioInteractionScheduler.mo358get().releaseCommsFocus(this.alexaServicesConnection.mo358get());
        }
    }

    public synchronized void deregisterPhoneCallController() {
        LOG.i("Deregistring PCC.");
        LocalBroadcastManager.getInstance(this.context.mo358get()).unregisterReceiver(this.alexaServiceConnectionReceiver);
        this.isRegistered.set(false);
        cleanUpVoxForPCC();
    }

    public synchronized void registerPhoneCallController() {
        if (!CommsDaggerWrapper.getComponent().getPackageManager().hasSystemFeature("android.hardware.telephony")) {
            LOG.i("Device does not have telephony fearure...skipping PCC initialization");
        } else if (this.isRegistered.getAndSet(true)) {
            LOG.i("PCC Component already initialized");
        } else {
            LOG.i("Initializing PCC Component");
            this.telephonyManager.mo358get().listen(this.callStateListener.mo358get(), 32);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Constants.ALEXA_SERVICE_CONNECTION_CONNECTED);
            intentFilter.addAction(Constants.ALEXA_SERVICE_CONNECTION_DISCONNECTED);
            LOG.i("Registering receiver for ASC PCC");
            LocalBroadcastManager.getInstance(this.context.mo358get()).registerReceiver(this.alexaServiceConnectionReceiver, intentFilter);
        }
    }
}
