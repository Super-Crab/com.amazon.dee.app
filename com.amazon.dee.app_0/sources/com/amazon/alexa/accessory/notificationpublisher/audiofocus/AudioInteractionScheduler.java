package com.amazon.alexa.accessory.notificationpublisher.audiofocus;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.api.AlexaAudioInteraction;
import com.amazon.alexa.api.AlexaAudioPlaybackListener;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaPlaybackState;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.alexa.api.compat.AlexaState;
import com.amazon.alexa.api.compat.AlexaStateListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public final class AudioInteractionScheduler implements AlexaServicesConnection.ConnectionListener {
    static final int ALEXA_SERVICES_CONNECTION_RETRY_TIMES_LIMIT = 2;
    private static final long CONNECTION_WAIT_TIME_MS = 2000;
    private static final String TAG = "AudioInteractionScheduler";
    private static AudioInteractionScheduler instance;
    private AlexaAudioInteraction audioInteraction;
    private Runnable connectionRunnable;
    private boolean scheduled;
    private int alexaServicesConnectionRetryCount = 0;
    private AlexaState alexaState = AlexaState.IDLE;
    private AlexaPlaybackState alexaPlaybackState = AlexaPlaybackState.NONE;
    private NotificationPlaybackState notificationPlaybackState = NotificationPlaybackState.NONE;
    private AlexaStateListener alexaStateListener = new AlexaStateListener() { // from class: com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioInteractionScheduler.1
        @Override // com.amazon.alexa.api.compat.AlexaStateListener
        public void onAlexaStateChanged(@NonNull AlexaState alexaState) {
            String str = AudioInteractionScheduler.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onAlexaStateChanged - New state = ");
            outline107.append(alexaState.name());
            Log.d(str, outline107.toString());
            AudioInteractionScheduler.this.alexaState = alexaState;
        }
    };
    private AlexaAudioPlaybackListener alexaPlaybackStateListener = new AlexaAudioPlaybackListener() { // from class: com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioInteractionScheduler.2
        @Override // com.amazon.alexa.api.AlexaAudioPlaybackListener
        public void onAudioPlaybackChanged(@NonNull AlexaPlaybackState alexaPlaybackState) {
            String str = AudioInteractionScheduler.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onAudioPlaybackChanged - New state = ");
            outline107.append(alexaPlaybackState.name());
            Log.d(str, outline107.toString());
            AudioInteractionScheduler.this.alexaPlaybackState = alexaPlaybackState;
        }
    };
    private AlexaServicesConnection alexaServicesConnection = DependencyProvider.createAlexaServiceConnection();

    /* loaded from: classes.dex */
    public enum NotificationPlaybackState {
        NONE,
        PLAYING
    }

    private AudioInteractionScheduler() {
        this.alexaServicesConnection.registerListener(this);
        this.alexaServicesConnection.setKeepAlive(true);
        startConnectionThread();
    }

    public static AudioInteractionScheduler getInstance() {
        if (instance == null) {
            instance = new AudioInteractionScheduler();
        }
        return instance;
    }

    public static void releaseInstance() {
        Log.d(TAG, "releaseInstance");
        try {
            try {
                AlexaServices.Recognizer.deregisterListener(instance.alexaServicesConnection, instance.alexaStateListener);
                AlexaServices.AudioPlaybackControl.deregisterListener(instance.alexaServicesConnection, instance.alexaPlaybackStateListener);
                instance.alexaServicesConnection.disconnect();
                instance.alexaServicesConnection.deregisterListener(instance);
                instance.alexaServicesConnection.release();
            } catch (Exception e) {
                Log.w(TAG, "releaseInstance failed.", e);
            }
        } finally {
            instance = null;
        }
    }

    private void startConnectionThread() {
        Log.d(TAG, "startConnectionThread");
        this.connectionRunnable = new Runnable() { // from class: com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioInteractionScheduler.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(2000L);
                    Log.d(AudioInteractionScheduler.TAG, "startConnectionThread - Runnable calling connect");
                    AudioInteractionScheduler.this.alexaServicesConnection.connect();
                } catch (InterruptedException unused) {
                }
            }
        };
        new Thread(this.connectionRunnable).start();
    }

    public void cancelUserInteraction() {
        AlexaServices.Recognizer.cancelUserInteraction(this.alexaServicesConnection);
    }

    public void connectAlexaServiceConnection() {
        this.alexaServicesConnection.connect();
    }

    public AlexaPlaybackState getAlexaPlaybackState() {
        return this.alexaPlaybackState;
    }

    public AlexaServicesConnection getAlexaServicesConnection() {
        return this.alexaServicesConnection;
    }

    public AlexaState getAlexaState() {
        return this.alexaState;
    }

    public synchronized NotificationPlaybackState getNotificationPlaybackState() {
        return this.notificationPlaybackState;
    }

    @VisibleForTesting
    synchronized void handleAlexaServicesConnectionFailure() {
        this.alexaServicesConnectionRetryCount++;
        if (this.alexaServicesConnectionRetryCount <= 2 && !this.alexaServicesConnection.isConnected()) {
            Log.i(TAG, "handleConnectionFailure - Retry Number: " + this.alexaServicesConnectionRetryCount);
            this.alexaServicesConnection.connect();
        }
    }

    public boolean isAlexaServiceConnected() {
        return this.alexaServicesConnection.isConnected();
    }

    public boolean isScheduled() {
        return this.scheduled;
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public synchronized void onConnected() {
        Log.d(TAG, "onConnected - Alexa services connection is connected");
        this.alexaServicesConnectionRetryCount = 0;
        AlexaServices.Recognizer.registerListener(this.alexaServicesConnection, this.alexaStateListener);
        AlexaServices.AudioPlaybackControl.registerListener(this.alexaServicesConnection, this.alexaPlaybackStateListener);
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public synchronized void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        String str2 = TAG;
        Log.w(str2, "onConnectingFailed - Alexa services connection failed with reason: " + alexaConnectingFailedReason);
        if (alexaConnectingFailedReason == AlexaConnectingFailedReason.TIMEOUT || alexaConnectingFailedReason == AlexaConnectingFailedReason.UNKNOWN) {
            handleAlexaServicesConnectionFailure();
        }
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public synchronized void onDisconnected() {
        Log.w(TAG, "onDisconnected - Alexa services connection disconnected");
    }

    public boolean schedule(@NonNull AudioFocusChangeListener audioFocusChangeListener) {
        Log.d(TAG, "schedule");
        if (instance.alexaServicesConnection.isConnected()) {
            Log.d(TAG, "schedule - Service is connected");
            this.audioInteraction = new AnnounceWithContentAudioInteraction(audioFocusChangeListener);
            AlexaServices.InteractionScheduler.schedule(this.alexaServicesConnection, this.audioInteraction);
            this.scheduled = true;
            return true;
        }
        Log.w(TAG, "schedule - Service is not connected, try to connect");
        instance.alexaServicesConnection.connect();
        return false;
    }

    public synchronized void setNotificationPlaybackState(NotificationPlaybackState notificationPlaybackState, String str) {
        String str2 = TAG;
        Log.i(str2, "setNotificationPlaybackState: " + notificationPlaybackState + " tag: " + str);
        this.notificationPlaybackState = notificationPlaybackState;
    }

    public void stop() {
        AlexaServices.AudioPlaybackControl.stop(this.alexaServicesConnection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean unschedule() {
        if (instance.alexaServicesConnection.isConnected()) {
            Log.d(TAG, "unschedule - Service is connected");
            AlexaAudioInteraction alexaAudioInteraction = this.audioInteraction;
            if (alexaAudioInteraction != null) {
                AlexaServices.InteractionScheduler.unschedule(this.alexaServicesConnection, alexaAudioInteraction);
                this.scheduled = false;
                this.audioInteraction = null;
            }
            return true;
        }
        Log.w(TAG, "unschedule - Service is not connected, try to connect");
        instance.alexaServicesConnection.connect();
        return false;
    }
}
