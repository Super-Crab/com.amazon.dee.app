package com.amazon.deecomms.common.audio;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.PlatformUtils;
import java.io.IOException;
/* loaded from: classes12.dex */
public final class AlexaAudioPlayer {
    private static final String AUDIO_RESOURCE_ID = "RESOURCE_ID";
    private static final String AUDIO_SHOULD_REPEAT = "REPEAT";
    private static final String AUDIO_STREAM_TYPE = "STREAM_TYPE";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AlexaAudioPlayer.class);
    public static final int MSG_PLAY_AUDIO = 2;
    public static final int MSG_PLAY_RINGTONE = 1;
    private final AudioManager mAudioManager;
    private Handler mAudioPlayerHandler;
    private final Context mContext;
    private MediaPlayer mMediaPlayer;

    public AlexaAudioPlayer(Context context) {
        this.mContext = context;
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
    }

    private void destroyMediaPlayer() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }

    private Handler getNewHandler() {
        HandlerThread handlerThread = new HandlerThread("AudioHandlerThread");
        handlerThread.start();
        return new Handler(handlerThread.getLooper()) { // from class: com.amazon.deecomms.common.audio.AlexaAudioPlayer.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i != 1 && i != 2) {
                    CommsLogger commsLogger = AlexaAudioPlayer.LOG;
                    commsLogger.w("Message is not recognized. This should not happen." + message);
                    return;
                }
                AlexaAudioPlayer.this.handlePlay(message.getData());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void handlePlay(Bundle bundle) {
        if (this.mAudioPlayerHandler == null) {
            LOG.e("AudioHandler is Null. Stopping play");
            return;
        }
        LOG.i("Handling play");
        if (bundle == null) {
            LOG.e("Empty data for handling audio");
            return;
        }
        int i = bundle.getInt(AUDIO_STREAM_TYPE);
        boolean z = bundle.getBoolean(AUDIO_SHOULD_REPEAT);
        int i2 = bundle.getInt(AUDIO_RESOURCE_ID);
        if (this.mMediaPlayer != null) {
            LOG.i("Destroying previously created MediaPlayer object");
            destroyMediaPlayer();
        }
        if (i2 == 0) {
            LOG.e("Invalid resource ID passed to play Audio");
            return;
        }
        this.mMediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor openRawResourceFd = this.mContext.getResources().openRawResourceFd(i2);
            this.mMediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getDeclaredLength());
            this.mMediaPlayer.setAudioStreamType(i);
            this.mMediaPlayer.setOnErrorListener($$Lambda$AlexaAudioPlayer$w6CrrlujTJe_D0dMKcDFY5yEDgs.INSTANCE);
            this.mMediaPlayer.setLooping(z);
            if (this.mAudioManager.getStreamVolume(i) != 0) {
                try {
                    this.mMediaPlayer.prepare();
                    this.mMediaPlayer.start();
                } catch (IOException e) {
                    LOG.e("Exception while preparing Media player", e);
                    destroyMediaPlayer();
                }
            }
        } catch (Resources.NotFoundException | IOException e2) {
            LOG.e("Exception while trying to play.", e2);
            destroyMediaPlayer();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$handlePlay$0(MediaPlayer mediaPlayer, int i, int i2) {
        CommsLogger commsLogger = LOG;
        commsLogger.e("OnError called for media player, what:" + i + ",extra:" + i2);
        return false;
    }

    public synchronized void play(int i, boolean z, int i2, int i3) {
        if (i == 0) {
            LOG.e("Invalid resource ID passed to play Audio");
            return;
        }
        if (this.mAudioPlayerHandler == null) {
            this.mAudioPlayerHandler = getNewHandler();
        }
        if (this.mMediaPlayer != null) {
            LOG.i("Trying to play a sound while we are playing another sound");
            if (this.mAudioPlayerHandler.hasMessages(i3)) {
                this.mAudioPlayerHandler.removeMessages(i3);
            }
        }
        CommsLogger commsLogger = LOG;
        commsLogger.i("Playing audio: " + i3);
        Message obtainMessage = this.mAudioPlayerHandler.obtainMessage(i3);
        Bundle bundle = new Bundle();
        bundle.putInt(AUDIO_RESOURCE_ID, i);
        bundle.putBoolean(AUDIO_SHOULD_REPEAT, z);
        bundle.putInt(AUDIO_STREAM_TYPE, i2);
        obtainMessage.setData(bundle);
        this.mAudioPlayerHandler.sendMessage(obtainMessage);
    }

    public synchronized void stop(int i) {
        if (this.mAudioPlayerHandler == null) {
            return;
        }
        LOG.i("Stopping audio.");
        if (this.mMediaPlayer != null) {
            destroyMediaPlayer();
        }
        Handler handler = this.mAudioPlayerHandler;
        if (handler != null) {
            if (handler.hasMessages(i)) {
                this.mAudioPlayerHandler.removeMessages(i);
            }
            this.mAudioPlayerHandler.getLooper().quitSafely();
        }
        this.mAudioPlayerHandler = null;
        PlatformUtils.getInstance().stopRingerVibration(this.mContext);
    }

    public synchronized void play(int i, boolean z, int i2, boolean z2, int i3) {
        if (i == 0) {
            LOG.e("Invalid resource ID passed to play Audio");
            return;
        }
        play(i, z, i2, i3);
        if (z2) {
            PlatformUtils.getInstance().performRingerVibration(this.mContext);
        }
    }
}
