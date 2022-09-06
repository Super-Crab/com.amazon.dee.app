package com.amazon.alexa.accessory.frames.topContact;

import com.amazon.alexa.accessory.frames.contacts.ContactsModule;
import com.amazon.alexa.accessory.frames.downloader.FileDownloader;
import com.amazon.alexa.accessory.frames.eventbus.EventBusManager;
import com.amazon.alexa.accessory.frames.metrics.MetricsConstants;
import com.amazon.alexa.accessory.frames.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.frames.provider.AccessoryUtil;
import com.amazon.alexa.accessory.frames.service.TtsManager;
import com.amazon.alexa.accessory.frames.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusManager;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioInteractionScheduler;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.storage.StorageWrapper;
import com.amazon.alexa.accessory.notificationpublisher.utils.AlexaServiceHelper;
import com.amazon.alexa.api.compat.AlexaState;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class TopContactManager extends AudioFocusManager {
    private static final String TAG = "TopContactManager";
    public static String deviceAccountId;
    public static JSONObject latestContactDetails;
    private static TopContactManager topContactManager;
    public static TouchpadCustomizationState touchpadCustomizationState;
    public static MobilyticsMetricsTimer ttsDownloadTimer;
    public static MobilyticsMetricsTimer ttsRequestTimer;
    private EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline20(EventBus.class);
    StorageWrapper storageWrapper = new StorageWrapper(DependencyProvider.getNativeLocalStorageModule(), SettingsStorageModule.DEVICE_ACCOUNT_ID_KEY);
    public static final String TOP_CONTACT_UUID = AudioFilePlayer.getInstance().getRandomRequestId();
    public static TTSState ttsState = TTSState.NONE;
    public static AtomicBoolean topContactFlowInprogress = new AtomicBoolean(false);
    private static AtomicBoolean canForwardATCommand = new AtomicBoolean(false);
    public static String folderPath = DependencyProvider.getContext().getFilesDir().getAbsoluteFile() + TopContactConstants.TOP_CONTACT_FOLDER;
    public static AtomicBoolean contactNameHasChanged = new AtomicBoolean(false);

    /* loaded from: classes.dex */
    public enum TTSState {
        SUCCESSFUL,
        CONTACT_SETUP,
        REQUESTED,
        NONE,
        ERROR,
        CONTACT_VERIFICATION
    }

    /* loaded from: classes.dex */
    public enum TouchpadCustomizationState {
        OS_ASSISTANT,
        NOT_SUPPORTED,
        CALLING
    }

    protected TopContactManager() {
    }

    public static synchronized TopContactManager getInstance() {
        TopContactManager topContactManager2;
        synchronized (TopContactManager.class) {
            if (topContactManager == null) {
                topContactManager = new TopContactManager();
            }
            topContactManager2 = topContactManager;
        }
        return topContactManager2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getTopContactTTS$0(JSONObject jSONObject) {
        try {
            TtsManager.getInstance().createDownloadableSpeechFromText(jSONObject);
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "createDownloadableSpeechFromText: failed with exception " + e);
        }
    }

    private void startTTSDownloadTimer() {
        new Timer().schedule(new TimerTask() { // from class: com.amazon.alexa.accessory.frames.topContact.TopContactManager.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (TopContactManager.this.getTTSState() == TTSState.REQUESTED) {
                    TopContactManager.this.setTTSState(TTSState.ERROR);
                    TopContactManager.this.requestAudioFocus();
                    return;
                }
                TopContactManager.this.setTTSState(TTSState.NONE);
            }
        }, 5000L);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusManager, com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusChangeListener
    public void audioFocusAcquired() {
        Log.d(TAG, "In audioFocusAcquired");
        super.audioFocusAcquired();
        if (this.result == 1) {
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_AUDIO_FOCUS_GRANTED);
            playAudio();
            return;
        }
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_AUDIO_FOCUS_FAILED_TO_ACQUIRE);
        topContactFlowEnded();
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusManager, com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusChangeListener
    public void audioFocusLost() {
        Log.d(TAG, "In audioFocusLost");
        super.audioFocusLost();
        topContactFlowEnded();
        canForwardATCommand.set(false);
    }

    public synchronized void deleteExpiredTopContactAudio() {
        FileDownloader.getInstance().deleteExpiredAudio(DependencyProvider.getContext().getFilesDir().getAbsoluteFile() + TopContactConstants.TOP_CONTACT_FOLDER, TopContactConstants.TOP_CONTACT_CALLING_AUDIO);
    }

    public synchronized TTSState getTTSState() {
        return ttsState;
    }

    public void getTopContactFromLocalStore() {
        Log.i(TAG, " In getTopContactFromLocalStore");
        latestContactDetails = null;
        try {
            String obj = this.storageWrapper.get(SettingsStorageModule.DEVICE_ACCOUNT_ID_KEY).toString();
            if (obj != null) {
                latestContactDetails = (JSONObject) this.storageWrapper.get(obj);
            }
            if (latestContactDetails != null && !latestContactDetails.isNull("id")) {
                String str = TAG;
                Log.d(str, "latest contact details " + latestContactDetails.toString());
                String str2 = TAG;
                Log.d(str2, "getTopContactFromLocalStore - Valid contactId: " + latestContactDetails.optString("id"));
                File file = new File(folderPath + TopContactConstants.TOP_CONTACT_CALLING_AUDIO);
                Object obj2 = this.storageWrapper.get(TopContactConstants.NUM_CONTACT_TTS_PLAYED_KEY);
                int intValue = obj2 == null ? 0 : ((Integer) Integer.class.cast(obj2)).intValue();
                if (!contactNameHasChanged.get() && file.exists() && intValue != 3) {
                    setTTSState(TTSState.SUCCESSFUL);
                    this.storageWrapper.putLocal(TopContactConstants.NUM_CONTACT_TTS_PLAYED_KEY, Integer.valueOf(intValue + 1));
                    return;
                }
                String str3 = TAG;
                Log.i(str3, "getTopContactFromLocalStore - caching contact for contactId: " + latestContactDetails.optString("id"));
                if (file.exists()) {
                    file.delete();
                }
                contactNameHasChanged.set(false);
                getTopContactTTS(latestContactDetails.optString("name"), intValue);
                this.storageWrapper.putLocal(TopContactConstants.NUM_CONTACT_TTS_PLAYED_KEY, Integer.valueOf(intValue + 1));
                return;
            }
            setTTSState(TTSState.CONTACT_SETUP);
        } catch (Exception e) {
            String str4 = TAG;
            Log.e(str4, "getTopContactFromLocalStore - Failed with Exception " + e);
        }
    }

    public void getTopContactTTS(String str, int i) throws Exception {
        Log.d(TAG, " In getTopContactTTS");
        final JSONObject createTtsRequest = TtsManager.getInstance().createTtsRequest(String.format(i < 3 ? TopContactConstants.TTS_FOR_CALLING_WITH_INSTRUCTION : TopContactConstants.TTS_FOR_CALLING, str), TopContactConstants.TOP_CONTACT_PRIMARY_TEXT_TYPE, TopContactConstants.TOP_CONTACT_REQUEST_TYPE);
        if (createTtsRequest == null || !TtsManager.getInstance().addIncomingTtsRequest(createTtsRequest)) {
            return;
        }
        setTTSState(TTSState.REQUESTED);
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_TTS_REQUESTED);
        ttsRequestTimer = MetricsRecorder.getInstance().startTimer(MetricsConstants.TOP_CONTACT_CREATE_TTS_LATENCY);
        startTTSDownloadTimer();
        TtsManager.getInstance();
        TtsManager.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.frames.topContact.-$$Lambda$TopContactManager$Wu5CbAcVszr12Bt2V8kpQeudXto
            @Override // java.lang.Runnable
            public final void run() {
                TopContactManager.lambda$getTopContactTTS$0(JSONObject.this);
            }
        });
    }

    public synchronized TouchpadCustomizationState getTouchpadCustomizationState() {
        return touchpadCustomizationState;
    }

    public void handleTopContactTTSDownloadResponse(boolean z) {
        if (getInstance().getTTSState() == TTSState.REQUESTED) {
            if (z) {
                Log.i(TAG, "handleTopContactTTSDownloadResponse - contact TTS download succeeded");
                getInstance().setTTSState(TTSState.SUCCESSFUL);
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_TTS_DOWNLOAD_SUCCEEDED);
                MetricsRecorder.getInstance().recordTimer(ttsDownloadTimer);
            } else {
                Log.w(TAG, "handleTopContactTTSDownloadResponse - contact TTS download failed");
                getInstance().setTTSState(TTSState.ERROR);
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_TTS_DOWNLOAD_FAILED);
            }
            getInstance().requestAudioFocus();
            return;
        }
        getInstance().topContactFlowEnded();
    }

    void playAudio() {
        String str;
        Log.d(TAG, " In playAudio");
        if (getTTSState() == TTSState.CONTACT_SETUP) {
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_TTS_PLAY_SCHEDULED_CONTACT_SETUP);
            str = "/assets/TopContact_SetUp_Instruction.mp3";
        } else if (getTTSState() == TTSState.SUCCESSFUL) {
            str = GeneratedOutlineSupport1.outline91(new StringBuilder(), folderPath, TopContactConstants.TOP_CONTACT_CALLING_AUDIO);
            canForwardATCommand.set(true);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_TTS_PLAY_SCHEDULED_CONTACT_CALLING);
        } else {
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_TTS_PLAY_SCHEDULED_CALLING_UNAVAILABLE);
            str = "/assets/TopContact_Calling_Unavailable.mp3";
        }
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_TTS_PLAY_SCHEDULED);
        String str2 = TAG;
        Log.i(str2, "Playing audio file: " + str);
        AudioFilePlayer.getInstance().playAudioFile(DependencyProvider.getContext(), str, TOP_CONTACT_UUID, new AudioFilePlayer.AudioPlaybackCompleteListener() { // from class: com.amazon.alexa.accessory.frames.topContact.TopContactManager.1
            @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.AudioPlaybackCompleteListener
            public void onAudioPlaybackCompleted(String str3) {
                String str4 = TopContactManager.TAG;
                Log.i(str4, "playTTS - onAudioPlaybackCompleted: " + str3);
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_TTS_PLAY_SUCCESSFUL);
                if (TopContactManager.canForwardATCommand.get()) {
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_TTS_PLAY_SUCCESSFUL_CONTACT_CALLING);
                    AccessoryUtil.forwardATCommand("ATD" + TopContactManager.latestContactDetails.optString("phoneNumber"), DependencyProvider.getContext());
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_CALL_PLACED);
                }
                TopContactManager.this.audioFocusLost();
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.AudioPlaybackCompleteListener
            public void onAudioPlaybackError(String str3) {
                String str4 = TopContactManager.TAG;
                Log.e(str4, "playTTS - onAudioPlaybackError: " + str3);
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_TTS_PLAY_ERROR);
                if (TopContactManager.canForwardATCommand.get()) {
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_TTS_PLAY_ERROR_CONTACT_CALLING);
                }
                TopContactManager.this.audioFocusLost();
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.AudioPlaybackCompleteListener
            public void onAudioPlaybackInterrupted(String str3) {
                String str4 = TopContactManager.TAG;
                Log.i(str4, "playTTS -  onAudioPlaybackInterrupted: " + str3);
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_TTS_PLAY_INTERRUPTED);
                if (TopContactManager.canForwardATCommand.get()) {
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_TTS_PLAY_INTERRUPTED_CONTACT_CALLING);
                }
                TopContactManager.this.audioFocusLost();
            }
        });
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusManager
    public synchronized void requestAudioFocus() {
        Log.d(TAG, "In requestAudioFocus");
        this.propertyChangeSupport.firePropertyChange("priorityAudioFocusRequestReceived", (Object) null, (Object) null);
        if (AudioInteractionScheduler.getInstance().getAlexaState() != AlexaState.IDLE) {
            AlexaServiceHelper.stopAlexa();
        }
        if (AudioInteractionScheduler.getInstance().isScheduled()) {
            AudioFocusManager.getInstance().audioFocusLost();
        }
        if (this.audioManager.getMode() != 0) {
            Log.i(TAG, "requestAudioFocus - Not ok to request audio focus");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_AUDIO_FOCUS_NOT_REQUEST);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_AUDIO_FOCUS_NOT_REQUEST_AUDIO_BUSY);
            return;
        }
        if (!AudioInteractionScheduler.getInstance().schedule(this)) {
            Log.w(TAG, "requestAudioFocus - Schedule failed");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_AUDIO_FOCUS_NOT_REQUEST);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_AUDIO_FOCUS_NOT_REQUEST_SCHEDULE_FAIL);
        } else {
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_AUDIO_FOCUS_REQUESTED);
        }
    }

    public void requestAudioFocusIfRequired() {
        Log.d(TAG, "In requestAudioFocusIfRequired");
        getTopContactFromLocalStore();
        if (getTTSState() == TTSState.NONE) {
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_AUDIO_FOCUS_NOT_REQUEST);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_AUDIO_FOCUS_NOT_REQUEST_TTS_STATE);
            topContactFlowEnded();
        } else if (getTTSState() == TTSState.REQUESTED) {
        } else {
            requestAudioFocus();
        }
    }

    public void requestContactDetails() {
        Log.i(TAG, " In requestContactDetails");
        latestContactDetails = null;
        try {
            deviceAccountId = this.storageWrapper.get(SettingsStorageModule.DEVICE_ACCOUNT_ID_KEY).toString();
            if (deviceAccountId != null) {
                latestContactDetails = (JSONObject) this.storageWrapper.get(deviceAccountId);
            }
            if (latestContactDetails != null && !latestContactDetails.isNull("id")) {
                getInstance().setTTSState(TTSState.CONTACT_VERIFICATION);
                String uuid = UUID.randomUUID().toString();
                String createContactQueryPayload = ContactsModule.getInstance().createContactQueryPayload(latestContactDetails.optString("id"));
                EventBus eventBus = this.eventBus;
                eventBus.publish(EventBusManager.createMessage(TopContactConstants.CONTACT_VERIFICATION_REQUEST_EVENT_TYPE + uuid, createContactQueryPayload));
                ContactsModule.getInstance().startContactEventBusResponseTimer();
            } else if (!topContactFlowInprogress.get()) {
            } else {
                setTTSState(TTSState.CONTACT_SETUP);
                requestAudioFocus();
            }
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "requestContactDetails - Failed with Exception " + e);
        }
    }

    public synchronized void setTTSState(TTSState tTSState) {
        String str = TAG;
        Log.i(str, "setTTSState: " + tTSState);
        ttsState = tTSState;
    }

    public synchronized void setTouchpadCustomizationState(TouchpadCustomizationState touchpadCustomizationState2) {
        String str = TAG;
        Log.i(str, "setTouchpadCustomizationState: " + touchpadCustomizationState2);
        touchpadCustomizationState = touchpadCustomizationState2;
    }

    public void topContactFlowEnded() {
        Log.d(TAG, "In topContactFlowEnded");
        setTTSState(TTSState.NONE);
        topContactFlowInprogress.set(false);
        this.eventBus.publish(EventBusManager.createMessage("Zion:EventBus:HigherPriorityAudioFlowEnded", ""));
    }
}
