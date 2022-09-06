package com.amazon.vizzini.voiceservice.tts;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.vizzini.voiceservice.tts.ITTSCallback;
import com.amazon.vizzini.voiceservice.tts.ITTSPlayback;
import java.io.FileInputStream;
import java.io.IOException;
/* loaded from: classes13.dex */
public class TTSPlaybackClient {
    public static final String TAG = "TTSPlaybackClient";
    public static final int TTS_DOESNOTEXIST = -1;
    public static final int TTS_PLAYING = 0;
    public static final int TTS_QUEUED = 1;
    public static final String TTS_SERVICE_CLASS = "com.amazon.vizzini.voiceservice.dualmode.tts.TTSPlaybackService";
    public static final String TTS_SERVICE_INTENT = "com.amazon.vizzini.PLAY_TTS";
    public static final String VIZZINI_PACKAGE = "com.amazon.vizzini";
    private Callback mActiveCallback;
    private ServiceConnection mService;
    private ITTSPlayback mTTSService;
    private long mUID;
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private boolean mInternalSpeaker = false;
    private State mState = State.Disconnected;
    private ITTSCallback mTTSCallback = new ITTSCallback.Stub() { // from class: com.amazon.vizzini.voiceservice.tts.TTSPlaybackClient.1
        @Override // com.amazon.vizzini.voiceservice.tts.ITTSCallback
        public void notifyTTSPlaybackInterrupted(long j) throws RemoteException {
            TTSPlaybackClient.this.mMediaPlayer.stop();
            TTSPlaybackClient.this.mState = State.Idle;
            if (TTSPlaybackClient.this.mUID != j) {
                TTSPlaybackClient.this.mActiveCallback.onError(new RuntimeException("Mismatched uid!"));
            }
            if (TTSPlaybackClient.this.mActiveCallback != null) {
                TTSPlaybackClient.this.mActiveCallback.onPlaybackEnded();
            }
        }

        @Override // com.amazon.vizzini.voiceservice.tts.ITTSCallback
        public void notifyTTSPlaybackStarted(final long j) throws RemoteException {
            TTSPlaybackClient.this.mState = State.Playing;
            TTSPlaybackClient.this.mMediaPlayer.start();
            TTSPlaybackClient.this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.amazon.vizzini.voiceservice.tts.TTSPlaybackClient.1.1
                @Override // android.media.MediaPlayer.OnCompletionListener
                public void onCompletion(MediaPlayer mediaPlayer) {
                    TTSPlaybackClient.this.mState = State.Idle;
                    try {
                        TTSPlaybackClient.this.mTTSService.endTTS(j);
                    } catch (RemoteException e) {
                        TTSPlaybackClient.this.mState = State.Disconnected;
                        Log.e(TTSPlaybackClient.TAG, "Unable to end TTS: " + e);
                        if (TTSPlaybackClient.this.mActiveCallback == null) {
                            return;
                        }
                        TTSPlaybackClient.this.mActiveCallback.onError(e);
                    }
                }
            });
            if (TTSPlaybackClient.this.mActiveCallback != null) {
                TTSPlaybackClient.this.mActiveCallback.onPlaybackStarted();
            }
        }

        @Override // com.amazon.vizzini.voiceservice.tts.ITTSCallback
        public boolean ttsHeartbeat(long j) throws RemoteException {
            return TTSPlaybackClient.this.mMediaPlayer.isPlaying();
        }
    };

    /* renamed from: com.amazon.vizzini.voiceservice.tts.TTSPlaybackClient$3  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$vizzini$voiceservice$tts$TTSPlaybackClient$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$com$amazon$vizzini$voiceservice$tts$TTSPlaybackClient$State[State.Idle.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$vizzini$voiceservice$tts$TTSPlaybackClient$State[State.Playing.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$vizzini$voiceservice$tts$TTSPlaybackClient$State[State.Disconnected.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$vizzini$voiceservice$tts$TTSPlaybackClient$State[State.PendingService.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class Callback {
        void onError(Exception exc) {
        }

        void onPlaybackEnded() {
        }

        void onPlaybackStarted() {
        }
    }

    /* loaded from: classes13.dex */
    public enum State {
        Disconnected,
        PendingService,
        Playing,
        Idle
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void beginTTS() {
        try {
            this.mUID = this.mTTSService.playTTS(this.mTTSCallback, this.mInternalSpeaker);
        } catch (RemoteException e) {
            this.mState = State.Disconnected;
            Log.e(TAG, "Unable to play TTS: " + e);
            Callback callback = this.mActiveCallback;
            if (callback == null) {
                return;
            }
            callback.onError(e);
        }
    }

    private void internalPlay(Context context, Callback callback) throws IOException {
        this.mActiveCallback = callback;
        this.mUID = 0L;
        this.mMediaPlayer.prepare();
        queueTTS(context);
    }

    private void queueTTS(Context context) {
        int ordinal = this.mState.ordinal();
        if (ordinal == 0) {
            this.mService = new ServiceConnection() { // from class: com.amazon.vizzini.voiceservice.tts.TTSPlaybackClient.2
                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    TTSPlaybackClient.this.mTTSService = ITTSPlayback.Stub.asInterface(iBinder);
                    TTSPlaybackClient.this.mState = State.Idle;
                    TTSPlaybackClient.this.beginTTS();
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                    TTSPlaybackClient.this.mState = State.Disconnected;
                    TTSPlaybackClient.this.mTTSService = null;
                    TTSPlaybackClient.this.mService = null;
                }
            };
            Intent intent = new Intent("com.amazon.vizzini.PLAY_TTS");
            intent.setClassName("com.amazon.vizzini", TTS_SERVICE_CLASS);
            context.bindService(intent, this.mService, 1);
            this.mState = State.PendingService;
        } else if (ordinal == 2) {
            cancel();
            beginTTS();
        } else if (ordinal != 3) {
        } else {
            beginTTS();
        }
    }

    public boolean cancel() {
        if (this.mState != State.Playing) {
            return false;
        }
        try {
            this.mTTSService.endTTS(this.mUID);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to cancel playback: " + e);
            return false;
        }
    }

    public State getState() {
        return this.mState;
    }

    public void play(FileInputStream fileInputStream, Context context) throws IOException {
        play(fileInputStream, context, null);
    }

    public void setForceInternalSpeaker(boolean z) {
        this.mInternalSpeaker = z;
    }

    public void play(FileInputStream fileInputStream, Context context, Callback callback) throws IOException {
        this.mMediaPlayer.reset();
        this.mMediaPlayer.setDataSource(fileInputStream.getFD());
        internalPlay(context, callback);
    }
}
