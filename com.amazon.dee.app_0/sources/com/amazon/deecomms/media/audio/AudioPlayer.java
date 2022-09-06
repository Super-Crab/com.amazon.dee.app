package com.amazon.deecomms.media.audio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.service.MediaButtonService;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.controller.AudioStateManager;
import com.amazon.deecomms.messaging.model.client.ClientMessageIdentifier;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class AudioPlayer {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AudioPlayer.class);
    private final Context appContext;
    private BroadcastReceiver handSetPlugReceiver;
    private final AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;
    private Intent mediaButtonIntent;
    private String mediaFileUri;
    private int audioVolume = -1;
    private boolean isVolumeDucked = false;
    private boolean playing = false;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.amazon.deecomms.media.audio.-$$Lambda$AudioPlayer$q516BraXIXhEJTO4AZdjnt490ms
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public final void onAudioFocusChange(int i) {
            AudioPlayer.this.lambda$new$0$AudioPlayer(i);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class HandSetPlugReceiver extends BroadcastReceiver {
        private HandSetPlugReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !"android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                return;
            }
            AudioPlayer.this.stopPlaying();
        }

        /* synthetic */ HandSetPlugReceiver(AnonymousClass1 anonymousClass1) {
        }
    }

    /* loaded from: classes12.dex */
    private class MediaCompletedListener implements MediaPlayer.OnCompletionListener {
        private final MediaPlayer.OnCompletionListener chainedCompletionListener;
        private final ClientMessageIdentifier clientMessageIdentifier;

        public MediaCompletedListener(ClientMessageIdentifier clientMessageIdentifier, MediaPlayer.OnCompletionListener onCompletionListener) {
            this.clientMessageIdentifier = clientMessageIdentifier;
            this.chainedCompletionListener = onCompletionListener;
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            AudioPlayer.LOG.i("MediaPlayer completed");
            if (CommsDaggerWrapper.getComponent().getAudioStateManager().getAudioMessageState(this.clientMessageIdentifier) == 1) {
                CommsLogger commsLogger = AudioPlayer.LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("Releasing media player mediaId: ");
                outline1.append(AudioPlayer.LOG.sensitive(this.clientMessageIdentifier.getMediaID()));
                commsLogger.i(outline1.toString());
                AudioPlayer.this.releaseMediaPlayer(true);
            }
            MediaPlayer.OnCompletionListener onCompletionListener = this.chainedCompletionListener;
            if (onCompletionListener != null) {
                onCompletionListener.onCompletion(mediaPlayer);
            }
        }
    }

    public AudioPlayer(Context context, AudioManager audioManager) {
        this.appContext = context;
        this.mAudioManager = audioManager;
    }

    private void duckVolume() {
        this.audioVolume = this.mAudioManager.getStreamVolume(3);
        this.mAudioManager.setStreamVolume(3, this.audioVolume / 2, 0);
        this.isVolumeDucked = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$playAudio$1(MediaPlayer.OnErrorListener onErrorListener, MediaPlayer mediaPlayer, int i, int i2) {
        CommsLogger commsLogger = LOG;
        commsLogger.e("Error received: Error code: " + i + " Extra: " + i2);
        if (onErrorListener != null) {
            onErrorListener.onError(mediaPlayer, i, i2);
            return false;
        }
        return false;
    }

    private void registerForHeadsetPlug() {
        this.handSetPlugReceiver = new HandSetPlugReceiver(null);
        try {
            this.appContext.registerReceiver(this.handSetPlugReceiver, GeneratedOutlineSupport1.outline10("android.media.AUDIO_BECOMING_NOISY"));
        } catch (IllegalArgumentException e) {
            LOG.e("RegisterReceiver returned error", e);
        }
    }

    private synchronized void registerServices() {
        registerForHeadsetPlug();
        this.mediaButtonIntent = new Intent(this.appContext, MediaButtonService.class);
        this.appContext.startService(this.mediaButtonIntent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseMediaPlayer(boolean z) {
        synchronized (this) {
            unregisterServices();
            this.playing = false;
            this.mediaFileUri = null;
            if (this.mMediaPlayer != null) {
                LOG.i("Releasing Mediaplayer resources");
                AudioStateManager audioStateManager = CommsDaggerWrapper.getComponent().getAudioStateManager();
                audioStateManager.setAudioMessageState(audioStateManager.getCurrentlyPlayingClientMessageIdentifier(), 2, z);
                this.mMediaPlayer.release();
                this.mMediaPlayer = null;
            } else {
                LOG.i("MediaPlayer is already null");
            }
        }
        if (this.mAudioManager != null) {
            LOG.i("Abandoning audio focus");
            this.mAudioManager.abandonAudioFocus(this.mOnAudioFocusChangeListener);
        }
    }

    private void resumeNormalVolume() {
        int i;
        if (!this.isVolumeDucked || (i = this.audioVolume) == -1) {
            return;
        }
        this.mAudioManager.setStreamVolume(3, i, 0);
        this.isVolumeDucked = false;
    }

    private void unregisterForHeadsetPlug() {
        BroadcastReceiver broadcastReceiver = this.handSetPlugReceiver;
        if (broadcastReceiver != null) {
            try {
                this.appContext.unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException e) {
                LOG.e("UnregisterReceiver returned error", e);
            }
            this.handSetPlugReceiver = null;
        }
    }

    private synchronized void unregisterServices() {
        unregisterForHeadsetPlug();
        if (this.mediaButtonIntent != null) {
            LOG.i("Stopping button receiver");
            this.appContext.stopService(this.mediaButtonIntent);
            this.mediaButtonIntent = null;
        }
    }

    @Nullable
    public String getCurrentlyPlayingMediaFileName() {
        if (isPlaying()) {
            return this.mediaFileUri;
        }
        return null;
    }

    public boolean isPlaying() {
        return this.playing;
    }

    public /* synthetic */ void lambda$new$0$AudioPlayer(int i) {
        if (i == -2) {
            stopPlaying();
        } else if (i == -1) {
            stopPlaying();
        } else if (i == -3) {
            duckVolume();
        } else if (i == 1) {
            resumeNormalVolume();
        } else if (i != 2) {
        } else {
            resumeNormalVolume();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c5, code lost:
        if (r10 == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:?, code lost:
        return;
     */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0106  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void playAudio(@androidx.annotation.Nullable com.amazon.deecomms.media.model.MediaFileContent r10, @androidx.annotation.Nullable java.lang.String r11, @androidx.annotation.NonNull com.amazon.deecomms.messaging.model.client.ClientMessageIdentifier r12, @androidx.annotation.Nullable android.media.MediaPlayer.OnCompletionListener r13, @androidx.annotation.Nullable final android.media.MediaPlayer.OnErrorListener r14) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.media.audio.AudioPlayer.playAudio(com.amazon.deecomms.media.model.MediaFileContent, java.lang.String, com.amazon.deecomms.messaging.model.client.ClientMessageIdentifier, android.media.MediaPlayer$OnCompletionListener, android.media.MediaPlayer$OnErrorListener):void");
    }

    public void stopPlaying() {
        releaseMediaPlayer(false);
    }
}
