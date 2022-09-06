package com.amazon.alexa.accessory.notificationpublisher.audio;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes.dex */
final class AudioFilePlayerTask implements Runnable {
    static final String ASSETS_DIRECTORY = "/assets/";
    private static final String TAG = AudioFilePlayerTask.class.getSimpleName();
    private String audioFilePath;
    private int audioMode;
    private final Condition audioPlayComplete;
    private AudioPlayerActionCompleteListener audioPlayerActionCompleteListener;
    private final Lock audioPlayerLock;
    private final MediaPlayer.OnCompletionListener completeListener;
    private Context context;
    private MediaPlayer mediaPlayer;
    private String requestId;

    /* loaded from: classes.dex */
    public static class Builder {
        private Context appContext;
        private String audioFilePath;
        private AudioPlayerActionCompleteListener audioPlayerActionCompleteListener;
        private MediaPlayer mediaPlayer;
        private String requestId = "";
        private int audioMode = -1;

        public Builder audioFilePath(String str) {
            this.audioFilePath = str;
            return this;
        }

        public Builder audioMode(int i) {
            this.audioMode = i;
            return this;
        }

        public Builder audioPlayerActionCompleteListener(AudioPlayerActionCompleteListener audioPlayerActionCompleteListener) {
            this.audioPlayerActionCompleteListener = audioPlayerActionCompleteListener;
            return this;
        }

        public AudioFilePlayerTask build() throws IllegalArgumentException {
            Log.d(AudioFilePlayerTask.TAG, "Build AudioFilePlayer instance");
            if (this.appContext == null) {
                Log.e(AudioFilePlayerTask.TAG, "Cannot build AudioFilePlayer instance because there is no Context");
                throw new IllegalArgumentException("Context is required for file playback");
            }
            String str = this.audioFilePath;
            if (str == null || str.isEmpty()) {
                Log.e(AudioFilePlayerTask.TAG, "Cannot build AudioFilePlayer instance because path to audio file is null or empty");
                throw new IllegalArgumentException("Path to an audio file is required for file playback.");
            }
            Log.d(AudioFilePlayerTask.TAG, "Preconditions met for building AudioPlayer instance");
            AudioFilePlayerTask audioFilePlayerTask = new AudioFilePlayerTask();
            audioFilePlayerTask.context = this.appContext;
            audioFilePlayerTask.audioFilePath = this.audioFilePath;
            audioFilePlayerTask.requestId = this.requestId;
            audioFilePlayerTask.audioMode = this.audioMode;
            audioFilePlayerTask.audioPlayerActionCompleteListener = this.audioPlayerActionCompleteListener;
            MediaPlayer mediaPlayer = this.mediaPlayer;
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();
            }
            audioFilePlayerTask.mediaPlayer = mediaPlayer;
            return audioFilePlayerTask;
        }

        public Builder context(Context context) {
            this.appContext = context;
            return this;
        }

        @VisibleForTesting
        Builder mediaPlayer(MediaPlayer mediaPlayer) {
            this.mediaPlayer = mediaPlayer;
            return this;
        }

        public Builder requestId(String str) {
            this.requestId = str;
            return this;
        }
    }

    @VisibleForTesting
    void configureMediaPlayer(Context context, String str, MediaPlayer.OnCompletionListener onCompletionListener) throws IOException {
        if (str.startsWith("/assets/")) {
            String str2 = TAG;
            Log.d(str2, "configureMediaPlayer - Playing a file from assets folder. File Path: " + str);
            AssetFileDescriptor openFd = context.getAssets().openFd(str.substring(8));
            this.mediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            openFd.close();
        } else {
            GeneratedOutlineSupport1.outline165("configureMediaPlayer -Playing a file from app or external storage. File Path: ", str, TAG);
            this.mediaPlayer.setDataSource(str);
        }
        this.mediaPlayer.setOnCompletionListener(onCompletionListener);
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        int i = this.audioMode;
        if (i != 5 && i != 2) {
            if (!audioManager.isBluetoothScoOn() && audioManager.getMode() != 2 && audioManager.getMode() != 3) {
                Log.i(TAG, "configureMediaPlayer - Play on Music stream");
                this.mediaPlayer.setAudioStreamType(3);
                return;
            }
            Log.i(TAG, "configureMediaPlayer - Play on voice call stream");
            this.mediaPlayer.setAudioStreamType(0);
            return;
        }
        String str3 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("configureMediaPlayer - Play on Notification or Ringer stream. Audio Mode: ");
        outline107.append(this.audioMode);
        Log.i(str3, outline107.toString());
        this.mediaPlayer.setAudioStreamType(this.audioMode);
    }

    @VisibleForTesting
    MediaPlayer.OnCompletionListener getCompleteListener() {
        return this.completeListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.amazon.alexa.accessory.notificationpublisher.audio.AudioPlayerActionCompleteListener] */
    @Override // java.lang.Runnable
    public void run() {
        String str = "run - Stop and release the media player";
        Log.d(TAG, "run - Execution started");
        this.audioPlayerLock.lock();
        AudioFilePlayer.AudioPlayStatus audioPlayStatus = AudioFilePlayer.AudioPlayStatus.Error;
        try {
            try {
                configureMediaPlayer(this.context, this.audioFilePath, this.completeListener);
                Log.d(TAG, "run - Start audio playback");
                this.mediaPlayer.prepare();
                this.mediaPlayer.start();
                this.audioPlayComplete.await();
                Log.d(TAG, "run - File playback successful");
                audioPlayStatus = AudioFilePlayer.AudioPlayStatus.Completed;
            } catch (InterruptedException e) {
                Log.w(TAG, "run - audio playback is interrupted.", e);
                audioPlayStatus = AudioFilePlayer.AudioPlayStatus.Interrupted;
            } catch (Exception e2) {
                Log.e(TAG, "Exception when playing audio file.", e2);
            }
            this.audioPlayerLock.unlock();
            Log.d(TAG, str);
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
            str = this.audioPlayerActionCompleteListener;
            if (str == 0) {
                return;
            }
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("run - Signaling audio player action complete event for request ID = ");
            outline107.append(this.requestId);
            Log.d(str2, outline107.toString());
            this.audioPlayerActionCompleteListener.audioPlayerComplete(audioPlayStatus, this.requestId);
        } catch (Throwable th) {
            this.audioPlayerLock.unlock();
            Log.d(TAG, str);
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
            throw th;
        }
    }

    private AudioFilePlayerTask() {
        this.requestId = "";
        this.audioPlayerLock = new ReentrantLock();
        this.audioPlayComplete = this.audioPlayerLock.newCondition();
        this.completeListener = new MediaPlayer.OnCompletionListener() { // from class: com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayerTask.1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                AudioFilePlayerTask.this.audioPlayerLock.lock();
                AudioFilePlayerTask.this.audioPlayComplete.signal();
                AudioFilePlayerTask.this.audioPlayerLock.unlock();
            }
        };
    }
}
