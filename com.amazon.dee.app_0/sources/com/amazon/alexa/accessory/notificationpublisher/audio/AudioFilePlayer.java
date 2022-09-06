package com.amazon.alexa.accessory.notificationpublisher.audio;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.frames.topContact.TopContactConstants;
import com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayerTask;
import com.amazon.alexa.accessory.notificationpublisher.renderer.RenderManager;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/* loaded from: classes.dex */
public final class AudioFilePlayer {
    private static final String TAG = "AudioFilePlayer";
    private static AudioFilePlayer audioFilePlayerInstance;
    private static Map<String, AudioPlaybackCompleteListener> audioPlaybackCompleteListeners = new ConcurrentHashMap();
    private AudioPlayerActionCompleteListener audioPlayerActionCompleteListener;
    private Map<String, Future<?>> audioRequestMap;
    private ExecutorService executorService;

    /* renamed from: com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer$2  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$audio$AudioFilePlayer$AudioPlayStatus = new int[AudioPlayStatus.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$audio$AudioFilePlayer$AudioPlayStatus[AudioPlayStatus.Completed.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$audio$AudioFilePlayer$AudioPlayStatus[AudioPlayStatus.Interrupted.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$audio$AudioFilePlayer$AudioPlayStatus[AudioPlayStatus.Error.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum AudioPlayStatus {
        Completed,
        Interrupted,
        Error
    }

    /* loaded from: classes.dex */
    public interface AudioPlaybackCompleteListener {
        void onAudioPlaybackCompleted(String str);

        void onAudioPlaybackError(String str);

        void onAudioPlaybackInterrupted(String str);
    }

    private AudioFilePlayer() {
    }

    public static synchronized AudioFilePlayer getInstance() {
        AudioFilePlayer audioFilePlayer;
        synchronized (AudioFilePlayer.class) {
            Log.d(TAG, "getInstance called");
            if (audioFilePlayerInstance == null) {
                Log.d(TAG, "getInstance - Creating new instance");
                audioFilePlayerInstance = new AudioFilePlayer();
                audioFilePlayerInstance.audioRequestMap = new ConcurrentHashMap();
                audioFilePlayerInstance.executorService = Executors.newCachedThreadPool();
                audioFilePlayerInstance.audioPlayerActionCompleteListener = new AudioPlayerActionCompleteListener() { // from class: com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.1
                    @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioPlayerActionCompleteListener
                    public void audioPlayerComplete(AudioPlayStatus audioPlayStatus, String str) {
                        String str2 = AudioFilePlayer.TAG;
                        Log.d(str2, "audioPlayerComplete - Completed request ID " + str + " with status " + audioPlayStatus);
                        AudioFilePlayer.audioFilePlayerInstance.audioRequestMap.remove(str);
                        if (AudioFilePlayer.audioPlaybackCompleteListeners.containsKey(str)) {
                            String str3 = AudioFilePlayer.TAG;
                            Log.d(str3, "Listener attached for requestId: " + str + " status: " + audioPlayStatus);
                            AudioPlaybackCompleteListener audioPlaybackCompleteListener = (AudioPlaybackCompleteListener) AudioFilePlayer.audioPlaybackCompleteListeners.get(str);
                            int ordinal = audioPlayStatus.ordinal();
                            if (ordinal == 0) {
                                audioPlaybackCompleteListener.onAudioPlaybackCompleted(str);
                            } else if (ordinal == 1) {
                                audioPlaybackCompleteListener.onAudioPlaybackInterrupted(str);
                            } else if (ordinal != 2) {
                                String str4 = AudioFilePlayer.TAG;
                                Log.w(str4, "Invalid audio play status " + audioPlayStatus);
                            } else {
                                audioPlaybackCompleteListener.onAudioPlaybackError(str);
                            }
                            AudioFilePlayer.audioPlaybackCompleteListeners.remove(str);
                        }
                    }
                };
            }
            audioFilePlayer = audioFilePlayerInstance;
        }
        return audioFilePlayer;
    }

    public static synchronized void releaseInstance() {
        synchronized (AudioFilePlayer.class) {
            Log.d(TAG, "releaseInstance called");
            if (audioFilePlayerInstance != null) {
                audioFilePlayerInstance.stopAllAudio();
                audioFilePlayerInstance.audioRequestMap.clear();
                audioFilePlayerInstance.audioRequestMap = null;
                audioPlaybackCompleteListeners.clear();
                audioFilePlayerInstance.audioPlayerActionCompleteListener = null;
                audioFilePlayerInstance.executorService.shutdown();
                audioFilePlayerInstance = null;
            }
        }
    }

    @VisibleForTesting
    Map<String, AudioPlaybackCompleteListener> getAudioPlaybackCompleteListeners() {
        return audioPlaybackCompleteListeners;
    }

    @VisibleForTesting
    AudioPlayerActionCompleteListener getAudioPlayerActionCompleteListener() {
        return this.audioPlayerActionCompleteListener;
    }

    @VisibleForTesting
    Map<String, Future<?>> getAudioRequestMap() {
        return this.audioRequestMap;
    }

    public String getRandomRequestId() {
        return UUID.randomUUID().toString();
    }

    public boolean playAudioAsset(@NonNull Context context, @NonNull String str, @Nullable String str2) {
        return playAudioAsset(context, str, str2, null);
    }

    public boolean playAudioFile(@NonNull Context context, @NonNull String str, @Nullable String str2, @Nullable AudioPlaybackCompleteListener audioPlaybackCompleteListener) {
        if (context == null) {
            Log.e(TAG, "playAudioFile - Context is null. Cannot play audio file");
            return false;
        } else if (Strings.isNullOrEmpty(str)) {
            Log.e(TAG, "playAudioFile - file path is empty or null. Cannot play audio file");
            return false;
        } else if (!str.startsWith(TopContactConstants.ASSETS_FOLDER_PATH) && !new File(str).exists()) {
            Log.e(TAG, "playAudioFile - Audio file does not exist. Cannot play the file.");
            return false;
        } else {
            if (Strings.isNullOrEmpty(str2)) {
                Log.d(TAG, "playAudioFile - Request ID is null or empty. Create a random request ID");
                str2 = getRandomRequestId();
            }
            if (!this.audioRequestMap.isEmpty() && (Objects.equals(str2, RenderManager.REPLY_PROMPT_FOR_RECORDING_UUID) || Objects.equals(str2, RenderManager.REPLY_CANCELED_INSTRUCTION_UUID))) {
                Log.d(TAG, "playAudioFile - Found pending reply audio request(s). Clearing all pending audio tasks.");
                stopAllAudio();
            }
            if (audioPlaybackCompleteListener != null) {
                try {
                    audioPlaybackCompleteListeners.put(str2, audioPlaybackCompleteListener);
                } catch (Exception e) {
                    Log.e(TAG, "playAudioFile - Exception when playing audio file.", e);
                    return false;
                }
            }
            this.audioRequestMap.put(str2, this.executorService.submit(new AudioFilePlayerTask.Builder().context(context).audioFilePath(str).requestId(str2).audioPlayerActionCompleteListener(this.audioPlayerActionCompleteListener).build()));
            return true;
        }
    }

    public void removeAudioPlaybackCompleteListener(@NonNull String str) {
        if (audioPlaybackCompleteListeners.containsKey(str)) {
            audioPlaybackCompleteListeners.remove(str);
        }
    }

    @VisibleForTesting
    void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void stopAllAudio() {
        Log.d(TAG, "stopAllAudio");
        for (Future<?> future : this.audioRequestMap.values()) {
            future.cancel(true);
        }
    }

    public void stopAudioPlayback(@NonNull String str) {
        String str2 = TAG;
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("stopAudioPlayback - requestId: ", str, " audioRequestMap: ");
        outline115.append(this.audioRequestMap.keySet());
        Log.d(str2, outline115.toString());
        if (Strings.isNullOrEmpty(str) || !this.audioRequestMap.containsKey(str)) {
            return;
        }
        boolean cancel = this.audioRequestMap.get(str).cancel(true);
        String str3 = TAG;
        Log.d(str3, "stopAudioPlayback - requestId: " + str + " cancelled: " + cancel);
    }

    public boolean playAudioAsset(@NonNull Context context, @NonNull String str, @Nullable String str2, @Nullable AudioPlaybackCompleteListener audioPlaybackCompleteListener) {
        if (Strings.isNullOrEmpty(str)) {
            Log.e(TAG, "playAudioAsset - Asset path and name is empty or null. Cannot play audio asset");
            return false;
        }
        String str3 = TAG;
        Log.d(str3, "playAudioAsset " + str);
        if (!str.startsWith(TopContactConstants.ASSETS_FOLDER_PATH)) {
            str = GeneratedOutlineSupport1.outline72("/assets//", str);
        }
        return playAudioFile(context, str, str2, audioPlaybackCompleteListener);
    }
}
