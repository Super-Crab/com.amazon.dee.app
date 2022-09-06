package com.amazon.alexa.accessory.notificationpublisher.audiofocus;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.consumption.BaseComponent;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.renderer.RenderManager;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.api.compat.AlexaState;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Locale;
/* loaded from: classes.dex */
public class AudioFocusManager extends BaseComponent implements AudioFocusChangeListener {
    public static final int AUDIO_FOCUS_ACQUIRED = 1;
    public static final int AUDIO_FOCUS_CANNOT_REQUEST = 3;
    public static final int AUDIO_FOCUS_LOST = 2;
    public static final int AUDIO_FOCUS_UNEXPECTED_STATE = 4;
    private static final String TAG = "AudioFocusManager";
    private static final boolean activeMusicStateCheckOptional = true;
    private static AudioFocusManager audioFocusInstance;
    public static boolean easterEggAudioTriggered;
    private static HigherPriorityAudioPlaybackState higherAudioPlaybackState = HigherPriorityAudioPlaybackState.NONE;
    protected AudioManager audioManager;
    private volatile ZionAudioFocusRequest currentRequest;
    private AudioManager.OnAudioFocusChangeListener listener;
    private AudioAttributes playbackAttributes;
    protected final PropertyChangeSupport propertyChangeSupport;
    protected int result;

    /* loaded from: classes.dex */
    public enum HigherPriorityAudioPlaybackState {
        NONE,
        PLAYING
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AudioFocusManager() {
        super(7);
        this.listener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusManager.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i) {
                String str = AudioFocusManager.TAG;
                Log.d(str, "onAudioFocusChange - New state = " + i);
                if (i == -1 || i == -2 || i == -3) {
                    Log.d(AudioFocusManager.TAG, "onAudioFocusChange - Audio focus lost");
                    AudioFocusManager.this.audioFocusLost();
                }
            }
        };
        this.playbackAttributes = new AudioAttributes.Builder().setUsage(1).setContentType(2).build();
        this.audioManager = (AudioManager) DependencyProvider.getContext().getSystemService("audio");
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public static synchronized AudioFocusManager getInstance() {
        AudioFocusManager audioFocusManager;
        synchronized (AudioFocusManager.class) {
            if (audioFocusInstance == null) {
                audioFocusInstance = new AudioFocusManager();
            }
            audioFocusManager = audioFocusInstance;
        }
        return audioFocusManager;
    }

    @VisibleForTesting
    static synchronized void releaseInstance() {
        synchronized (AudioFocusManager.class) {
            audioFocusInstance = null;
        }
    }

    private void unscheduleAudioInteraction() {
        Log.d(TAG, "unscheduleAudioInteraction");
        AudioInteractionScheduler.getInstance().unschedule();
    }

    public void addPropertyChangeListener(String str, PropertyChangeListener propertyChangeListener) {
        this.propertyChangeSupport.addPropertyChangeListener(str, propertyChangeListener);
    }

    public void audioFocusAcquired() {
        Log.d(TAG, "audioFocusAcquired");
        AudioFocusRequest build = new AudioFocusRequest.Builder(4).setAudioAttributes(this.playbackAttributes).setOnAudioFocusChangeListener(this.listener).build();
        Log.i(TAG, "audioFocusAcquired api level >= 26, use new requestAudioFocus API.");
        this.result = this.audioManager.requestAudioFocus(build);
        if (this.result == 1 && higherAudioPlaybackState == HigherPriorityAudioPlaybackState.NONE) {
            if (easterEggAudioTriggered) {
                return;
            }
            Log.i(TAG, "audioFocusAcquired - Post message with request data");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.AUDIOFOCUS_ACQUIRED);
            postEventMessage(1, this.currentRequest);
            return;
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("audioFocusAcquired - Failed to get system audio focus. audioFocus = ");
        outline107.append(this.result);
        Log.w(str, outline107.toString());
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.AUDIOFOCUS_FAILEDTOACQUIRE);
        unscheduleAudioInteraction();
        postEventMessage(3, this.currentRequest);
    }

    public void audioFocusLost() {
        Log.d(TAG, "audioFocusLost");
        RenderManager.getInstance().stopAllAudio();
        releaseAudioFocus();
        if (!easterEggAudioTriggered) {
            HashMap hashMap = null;
            if (FeatureAccessChecker.hasOtgVipFilterAccess()) {
                hashMap = new HashMap();
                hashMap.put("deviceType_accessory", AccessoryProvider.getLastDisconnectedAccessoryDeviceType());
            }
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.AUDIOFOCUS_LOST, hashMap);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.AUDIOFOCUS_INTERRUPTED, hashMap);
            postEventMessage(2, this.currentRequest);
        }
    }

    public synchronized HigherPriorityAudioPlaybackState getHigherPriorityPlaybackState() {
        return higherAudioPlaybackState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean okToRequestFocus() {
        Log.d(TAG, "okToRequestFocus");
        AudioInteractionScheduler audioInteractionScheduler = AudioInteractionScheduler.getInstance();
        Log.i(TAG, String.format(Locale.US, "okToRequestFocus - isMusicActive: %b,AudioManager.mode: %d, AlexaState: %s, AlexaPlaybackState: %s, HigherPriorityAudioPlaybackState: %s", Boolean.valueOf(this.audioManager.isMusicActive()), Integer.valueOf(this.audioManager.getMode()), audioInteractionScheduler.getAlexaState().name(), audioInteractionScheduler.getAlexaPlaybackState().name(), getHigherPriorityPlaybackState()));
        this.audioManager.isMusicActive();
        if (this.audioManager.getMode() == 0 && audioInteractionScheduler.getAlexaState() == AlexaState.IDLE && higherAudioPlaybackState != HigherPriorityAudioPlaybackState.PLAYING) {
            return true;
        }
        Log.w(TAG, "okToRequestFocus - Phone audio or Alexa audio is busy. Do not request audio focus");
        return false;
    }

    public synchronized void releaseAudioFocus() {
        HashMap hashMap;
        Log.d(TAG, "releaseAudioFocus");
        if (FeatureAccessChecker.hasOtgVipFilterAccess()) {
            hashMap = new HashMap();
            hashMap.put("deviceType_accessory", AccessoryProvider.getLastDisconnectedAccessoryDeviceType());
        } else {
            hashMap = null;
        }
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.AUDIOFOCUS_RELEASED, hashMap);
        int abandonAudioFocus = this.audioManager.abandonAudioFocus(this.listener);
        Log.d(TAG, "releaseAudioFocus - Result for abandonAudioFocus is " + abandonAudioFocus);
        unscheduleAudioInteraction();
        this.currentRequest = null;
    }

    public synchronized void requestAudioFocus(@Nullable ZionAudioFocusRequest zionAudioFocusRequest) {
        this.propertyChangeSupport.firePropertyChange("priorityAudioFocusRequestReceived", (Object) null, (Object) null);
        if (!okToRequestFocus()) {
            Log.w(TAG, "requestAudioFocus - Not ok to request audio focus");
            postEventMessage(3, zionAudioFocusRequest);
            return;
        }
        if (!AudioInteractionScheduler.getInstance().schedule(this)) {
            Log.w(TAG, "requestAudioFocus - Schedule failed");
            postEventMessage(3, zionAudioFocusRequest);
        }
        Log.d(TAG, "requestAudioFocus successful");
        this.currentRequest = zionAudioFocusRequest;
    }

    public synchronized void setHigherPriorityPlaybackState(HigherPriorityAudioPlaybackState higherPriorityAudioPlaybackState) {
        String str = TAG;
        Log.i(str, "setHigherPriorityAudioPlaybackState: " + higherPriorityAudioPlaybackState);
        higherAudioPlaybackState = higherPriorityAudioPlaybackState;
    }

    /* renamed from: clone */
    public AudioFocusManager m333clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }

    public synchronized void requestAudioFocus() {
        Log.d(TAG, "requestAudioFocus");
        requestAudioFocus(null);
    }
}
