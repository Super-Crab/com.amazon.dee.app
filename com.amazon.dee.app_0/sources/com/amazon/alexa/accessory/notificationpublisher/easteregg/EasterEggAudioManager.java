package com.amazon.alexa.accessory.notificationpublisher.easteregg;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusManager;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioInteractionScheduler;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes.dex */
public final class EasterEggAudioManager extends AudioFocusManager {
    private static final String EASTEREGG_AUDIO_URL = "https://m.media-amazon.com/images/G/01/Alexa/Zion/Videos/EasterEgg_AudioFile.mp3";
    public static final String EASTER_EGG_AUDIO_UUID = AudioFilePlayer.getInstance().getRandomRequestId();
    public static final String PLAY_EASTEREGG_AUDIO = "EasterEgg_AudioFile.mp3";
    private static final String TAG = "EasterEggAudioManager";
    private static EasterEggAudioManager audioFocusInstance = null;
    static boolean saved = false;
    private String folderPath;
    private PlaybackState playbackState = PlaybackState.NONE;
    private Context context = DependencyProvider.getContext();

    /* loaded from: classes.dex */
    public enum PlaybackState {
        NONE,
        PLAYING
    }

    private EasterEggAudioManager() {
        AudioFocusManager.getInstance().addPropertyChangeListener("priorityAudioFocusRequestReceived", new PropertyChangeListener() { // from class: com.amazon.alexa.accessory.notificationpublisher.easteregg.EasterEggAudioManager.1
            @Override // java.beans.PropertyChangeListener
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                EasterEggAudioManager.this.stopAudio();
            }
        });
    }

    public static synchronized EasterEggAudioManager getInstance() {
        EasterEggAudioManager easterEggAudioManager;
        synchronized (EasterEggAudioManager.class) {
            if (audioFocusInstance == null) {
                audioFocusInstance = new EasterEggAudioManager();
            }
            easterEggAudioManager = audioFocusInstance;
        }
        return easterEggAudioManager;
    }

    @VisibleForTesting
    static synchronized void releaseInstance() {
        synchronized (EasterEggAudioManager.class) {
            audioFocusInstance = null;
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusManager, com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusChangeListener
    public void audioFocusAcquired() {
        Log.d(TAG, "audioFocusAquired");
        super.audioFocusAcquired();
        playAudio();
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusManager, com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusChangeListener
    public void audioFocusLost() {
        Log.d(TAG, "audioFocusLost");
        super.audioFocusLost();
        if (getPlaybackState() == PlaybackState.PLAYING) {
            setPlaybackState(PlaybackState.NONE);
        }
    }

    public boolean canPlayEasterEggAudio() {
        if (AudioFocusManager.easterEggAudioTriggered) {
            if (!this.audioManager.isMusicActive() && !AudioInteractionScheduler.getInstance().isScheduled()) {
                return true;
            }
            Log.d(TAG, "audio is scheduled, cannot request Easter Egg audio");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.EASTER_EGG_AUDIO_FOCUS_REQUEST_FAILED);
            AudioFocusManager.easterEggAudioTriggered = false;
            return false;
        }
        return true;
    }

    public void downloadAndSaveAudio() {
        if (new File(GeneratedOutlineSupport1.outline92(new StringBuilder(), this.folderPath, "/", PLAY_EASTEREGG_AUDIO)).exists()) {
            return;
        }
        OkHttpClient httpClient = DependencyProvider.getHttpClient();
        this.folderPath = this.context.getFilesDir().getAbsoluteFile() + "/EasterEgg";
        httpClient.newCall(new Request.Builder().url(EASTEREGG_AUDIO_URL).build()).enqueue(new Callback() { // from class: com.amazon.alexa.accessory.notificationpublisher.easteregg.EasterEggAudioManager.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Log.d(EasterEggAudioManager.TAG, "requestDownload - onFailure. Exception.", iOException);
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.EASTER_EGG_AUDIO_DOWNLOAD_ERROR);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                String str = EasterEggAudioManager.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("requestDownload - onResponse success: ");
                outline107.append(response.isSuccessful());
                Log.d(str, outline107.toString());
                if (!response.isSuccessful()) {
                    Log.d(EasterEggAudioManager.TAG, "Easter Egg download unsuccessful");
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.EASTER_EGG_AUDIO_DOWNLOAD_ERROR);
                    return;
                }
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.EASTER_EGG_AUDIO_DOWNLOAD_SUCCESSFUL);
                boolean z = true;
                File file = new File(EasterEggAudioManager.this.folderPath);
                if (!file.exists()) {
                    String str2 = EasterEggAudioManager.TAG;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("saveFile - Creating folder - ");
                    outline1072.append(file.getAbsolutePath());
                    Log.d(str2, outline1072.toString());
                    z = file.mkdir();
                }
                if (!z) {
                    String str3 = EasterEggAudioManager.TAG;
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("saveFile - Cannot save file, folder does not exist - ");
                    outline1073.append(file.getAbsolutePath());
                    Log.d(str3, outline1073.toString());
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.EASTER_EGG_AUDIO_SAVE_ERROR);
                    return;
                }
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(EasterEggAudioManager.this.folderPath + "/" + EasterEggAudioManager.PLAY_EASTEREGG_AUDIO, false);
                    fileOutputStream.write(response.body().bytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e) {
                    Log.e(EasterEggAudioManager.TAG, "downloadAndSaveAudio - Exception.", e);
                }
                EasterEggAudioManager.saved = new File(GeneratedOutlineSupport1.outline92(new StringBuilder(), EasterEggAudioManager.this.folderPath, "/", EasterEggAudioManager.PLAY_EASTEREGG_AUDIO)).exists();
                if (EasterEggAudioManager.saved) {
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.EASTER_EGG_AUDIO_SAVE_SUCCESSFUL);
                } else {
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.EASTER_EGG_AUDIO_SAVE_ERROR);
                }
            }
        });
    }

    public synchronized PlaybackState getPlaybackState() {
        return this.playbackState;
    }

    public void playAudio() {
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.EASTER_EGG_AUDIO_FOCUS_REQUEST_GRANTED);
        downloadAndSaveAudio();
        setPlaybackState(PlaybackState.PLAYING);
        AudioFocusManager.easterEggAudioTriggered = false;
        AudioFilePlayer.getInstance().playAudioFile(this.context, GeneratedOutlineSupport1.outline92(new StringBuilder(), this.folderPath, "/", PLAY_EASTEREGG_AUDIO), EASTER_EGG_AUDIO_UUID, new AudioFilePlayer.AudioPlaybackCompleteListener() { // from class: com.amazon.alexa.accessory.notificationpublisher.easteregg.EasterEggAudioManager.3
            @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.AudioPlaybackCompleteListener
            public void onAudioPlaybackCompleted(String str) {
                GeneratedOutlineSupport1.outline165("playTTS - onAudioPlaybackCompleted: ", str, EasterEggAudioManager.TAG);
                EasterEggAudioManager.this.stopAudio();
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.EASTER_EGG_AUDIO_PLAY_SUCCESSFUL);
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.AudioPlaybackCompleteListener
            public void onAudioPlaybackError(String str) {
                GeneratedOutlineSupport1.outline165("playTTS - onAudioPlaybackError: ", str, EasterEggAudioManager.TAG);
                EasterEggAudioManager.this.stopAudio();
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.EASTER_EGG_AUDIO_PLAY_ERROR);
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.AudioPlaybackCompleteListener
            public void onAudioPlaybackInterrupted(String str) {
                GeneratedOutlineSupport1.outline165("playTTS -  onAudioPlaybackInterrupted: ", str, EasterEggAudioManager.TAG);
                EasterEggAudioManager.this.stopAudio();
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.EASTER_EGG_AUDIO_PLAY_INTERRUPTED);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusManager
    public synchronized void requestAudioFocus() {
        if (!canPlayEasterEggAudio()) {
            return;
        }
        if (!okToRequestFocus()) {
            Log.d(TAG, "requestAudioFocus - Not ok to request audio focus");
            if (AudioFocusManager.easterEggAudioTriggered) {
                AudioFocusManager.easterEggAudioTriggered = false;
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.EASTER_EGG_AUDIO_FOCUS_REQUEST_FAILED);
                return;
            }
        }
        if (!AudioInteractionScheduler.getInstance().schedule(this)) {
            Log.d(TAG, "requestAudioFocus - Schedule failed");
            if (AudioFocusManager.easterEggAudioTriggered) {
                AudioFocusManager.easterEggAudioTriggered = false;
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.EASTER_EGG_AUDIO_FOCUS_SCHEDULE_FAILED);
            }
        }
    }

    public synchronized void setPlaybackState(PlaybackState playbackState) {
        this.playbackState = playbackState;
    }

    public void stopAudio() {
        Log.d(TAG, "stopAudio");
        if (getPlaybackState() == PlaybackState.PLAYING) {
            AudioFilePlayer.getInstance().stopAudioPlayback(EASTER_EGG_AUDIO_UUID);
            releaseAudioFocus();
            setPlaybackState(PlaybackState.NONE);
        }
    }
}
