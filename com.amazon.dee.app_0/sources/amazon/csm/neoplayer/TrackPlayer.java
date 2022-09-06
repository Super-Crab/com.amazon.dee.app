package amazon.csm.neoplayer;

import amazon.csm.neoplayer.NeoPlayer;
import amazon.csm.neoplayer.TrackPlayerThread;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.media.MediaFormat;
import android.os.Trace;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public class TrackPlayer implements NeoPlayer, IExtractorAlerts {
    private static final int MAX_CODEC_RETRIES = 4;
    private static final String TAG = "TrackPlayer";
    static Map<String, MediaCodecWrapper> sMediaCodecWrapperMap = new HashMap();
    AudioAttributes mAudioAttr;
    int mAudioSessionId;
    final int mAudioStream;
    NeoAudioTrack mAudioTrack;
    NeoPlayer.Listener mExternalNeoPlayerEventListener;
    MediaCodecWrapperFactory mMediaCodecWrapperFactory;
    Mp3Extractor mMediaExtractor;
    NeoPlayer.State mState;
    TrackPlayerThread.StreamStateChangeListener mStreamListener;
    TrackPlayerThread mTrackPlayerThread;
    private final Object mTrackPlayerThreadLock;
    float mVolume;

    static {
        MediaFormat createAudioFormat = MediaFormat.createAudioFormat(MimeTypes.AUDIO_MPEG, 24000, 1);
        createAudioFormat.setInteger("bitrate", 48000);
        try {
            getCodec(createAudioFormat, new MediaCodecWrapperFactory());
        } catch (CodecException e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not create initial mp3 codec: ");
            outline107.append(e.getLocalizedMessage());
            Log.wtf(str, outline107.toString(), e);
        }
    }

    public TrackPlayer(int i) {
        this(i, new MediaCodecWrapperFactory());
    }

    private synchronized void configureInternal() throws TimeoutException {
        Trace.beginSection("TrackPlayer configureInternal");
        try {
            stop();
            MediaFormat trackFormat = this.mMediaExtractor.getTrackFormat();
            String str = "MediaFormat = " + trackFormat.toString();
            Trace.beginSection("TrackPlayer configureInternal mediaCodec");
            MediaCodecWrapper codec = getCodec(trackFormat, this.mMediaCodecWrapperFactory);
            Trace.endSection();
            Trace.beginSection("TrackPlayer configureInternal audioTrack");
            try {
                this.mAudioTrack = newAudioTrack(trackFormat.getInteger("sample-rate"));
                if (this.mAudioTrack.getState() != 1) {
                    Log.e(TAG, "AudioTrack failed to initialize. State: " + this.mAudioTrack.getState());
                }
                this.mAudioTrack.setVolume(this.mVolume);
                this.mAudioTrack.play();
                this.mStreamListener.onStreamStarted();
                Trace.endSection();
                synchronized (this.mTrackPlayerThreadLock) {
                    this.mTrackPlayerThread = newTrackPlayerThread(codec);
                }
                this.mState = NeoPlayer.State.STATE_CONFIGURED;
            } finally {
                Trace.endSection();
            }
        } catch (CodecException e) {
            Log.wtf(TAG, "Could not create mp3 codec: " + e.getLocalizedMessage());
            if (this.mExternalNeoPlayerEventListener != null) {
                this.mExternalNeoPlayerEventListener.onPlayerError(e);
            }
        }
    }

    private static int getBytesPerFrame(int i) {
        if (i != 2) {
            if (i == 3) {
                return 1;
            }
            if (i != 4) {
                throw new RuntimeException("Unsupported format type!");
            }
            return 4;
        }
        return 2;
    }

    private static int getChannelCount(int i) {
        if (i != 4) {
            if (i != 12) {
                throw new RuntimeException("Unsupported format type!");
            }
            return 2;
        }
        return 1;
    }

    static MediaCodecWrapper getCodec(MediaFormat mediaFormat, MediaCodecWrapperFactory mediaCodecWrapperFactory) throws CodecException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Getting codec for format: ");
        outline107.append(getMediaFormatString(mediaFormat));
        outline107.toString();
        MediaCodecWrapper mediaCodecWrapper = sMediaCodecWrapperMap.get(mediaFormat.toString());
        if (mediaCodecWrapper == null) {
            String.format("Creating codec for format: %s", getMediaFormatString(mediaFormat));
            try {
                mediaCodecWrapper = mediaCodecWrapperFactory.createMediaCodec(MimeTypes.AUDIO_MPEG);
                mediaCodecWrapper.configure(mediaFormat, null, null, 0);
                mediaCodecWrapper.start();
                sMediaCodecWrapperMap.put(mediaFormat.toString(), mediaCodecWrapper);
            } catch (IllegalArgumentException e) {
                throw new CodecException("Could not initialize codec to parse mp3", e);
            }
        }
        return mediaCodecWrapper;
    }

    private static String getMediaFormatString(MediaFormat mediaFormat) {
        int integer = mediaFormat.getInteger("channel-count");
        return String.format("Media format = {channel-count=%d, bitrate=%d, mime=%s, sample-rate=%d}", Integer.valueOf(integer), Integer.valueOf(mediaFormat.getInteger("bitrate")), mediaFormat.getString("mime"), Integer.valueOf(mediaFormat.getInteger("sample-rate") / integer));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void releaseAudioTrack() throws RuntimeException {
        if (this.mAudioTrack != null) {
            this.mAudioTrack.release();
        }
    }

    private void reset() {
        reset_internal();
    }

    public AudioAttributes getAttributes() {
        return this.mAudioAttr;
    }

    @Override // amazon.csm.neoplayer.NeoPlayer
    public long getCurrentPosition() {
        return this.mMediaExtractor.getCurrentPosition();
    }

    synchronized NeoPlayer.State getCurrentState() {
        return this.mState;
    }

    public int getSessionId() {
        return this.mAudioSessionId;
    }

    @Override // amazon.csm.neoplayer.NeoPlayer
    public synchronized void loadStream(InputStream inputStream) throws IOException, TimeoutException {
        Trace.beginSection("TrackPlayer loadStream");
        if (this.mState != NeoPlayer.State.STATE_RELEASED) {
            stop();
            if (this.mExternalNeoPlayerEventListener != null) {
                this.mExternalNeoPlayerEventListener.onPlayerStateChanged(NeoPlayer.NeoPlayerState.STATE_PREPARING);
            }
            this.mMediaExtractor.setDataSource(inputStream);
            configureInternal();
            Trace.endSection();
        } else {
            throw new IllegalStateException("Tried to loadStream from " + this.mState);
        }
    }

    NeoAudioTrack newAudioTrack(int i) {
        Trace.beginSection("TrackPlayer newAudioTrack");
        try {
            return new NeoAudioTrack(this.mAudioAttr, new AudioFormat.Builder().setSampleRate(i).setEncoding(2).setChannelMask(4).build(), Math.max(((int) (getChannelCount(4) * i * 0.33d)) * getBytesPerFrame(2), AudioTrack.getMinBufferSize(i, 4, 2)), 1, this.mAudioSessionId);
        } finally {
            Trace.endSection();
        }
    }

    Mp3Extractor newMp3Extractor() {
        if (this.mMediaExtractor == null) {
            this.mMediaExtractor = new Mp3Extractor();
        }
        if (this.mMediaExtractor.getExtractorAlertsListener() == null) {
            this.mMediaExtractor.setExtractorAlertsListener(this);
        }
        return this.mMediaExtractor;
    }

    TrackPlayerThread newTrackPlayerThread(MediaCodecWrapper mediaCodecWrapper) {
        return new TrackPlayerThread(this, this.mMediaExtractor, mediaCodecWrapper, this.mAudioTrack, this.mStreamListener);
    }

    @Override // amazon.csm.neoplayer.IExtractorAlerts
    public synchronized void onSlowRead(long j, long j2) {
        if (this.mExternalNeoPlayerEventListener != null) {
            this.mExternalNeoPlayerEventListener.onSlowReadDetected(j, j2);
        }
    }

    @Override // amazon.csm.neoplayer.NeoPlayer
    public synchronized void play() throws TimeoutException {
        Trace.beginSection("TrackPlayer play");
        String str = "Playing from " + this.mState + " state";
        if (this.mState == NeoPlayer.State.STATE_CONFIGURED) {
            this.mTrackPlayerThread.startPlayback();
            if (this.mExternalNeoPlayerEventListener != null) {
                this.mExternalNeoPlayerEventListener.onPlayerStateChanged(NeoPlayer.NeoPlayerState.STATE_READY);
            }
            this.mState = NeoPlayer.State.STATE_RUNNING;
            Trace.endSection();
        } else {
            throw new IllegalStateException("Tried to play track when in state " + this.mState.name());
        }
    }

    @Override // amazon.csm.neoplayer.NeoPlayer
    public synchronized void release() {
        Trace.beginSection("TrackPlayer release");
        if (this.mState == NeoPlayer.State.STATE_RELEASED) {
            Log.w(TAG, "Releasing, but was already released.");
            Trace.endSection();
            return;
        }
        try {
            stop();
        } catch (TimeoutException e) {
            Log.e(TAG, "Stop timed out waiting from task to finish.", e);
        }
        this.mExternalNeoPlayerEventListener = null;
        this.mState = NeoPlayer.State.STATE_RELEASED;
        this.mMediaExtractor.release();
        Trace.endSection();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaCodecWrapper releaseAndRecreateCodec(MediaCodecWrapper mediaCodecWrapper) throws CodecException {
        String str = "releasing and recreating codec " + mediaCodecWrapper;
        MediaFormat trackFormat = this.mMediaExtractor.getTrackFormat();
        releaseCodec(mediaCodecWrapper);
        return getCodec(trackFormat, this.mMediaCodecWrapperFactory);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releaseCodec(MediaCodecWrapper mediaCodecWrapper) {
        String str = "releasing codec " + mediaCodecWrapper;
        if (mediaCodecWrapper != null) {
            mediaCodecWrapper.release();
            sMediaCodecWrapperMap.values().remove(mediaCodecWrapper);
        }
    }

    List<Exception> reset_internal() {
        ArrayList arrayList = new ArrayList();
        Trace.beginSection("TrackPlayer reset");
        try {
            if (this.mAudioTrack != null) {
                try {
                    this.mAudioTrack.stop();
                } catch (RuntimeException e) {
                    Log.i(TAG, "Exception trying to stop the audio track: " + e.getMessage());
                    arrayList.add(e);
                }
                try {
                    releaseAudioTrack();
                } catch (RuntimeException e2) {
                    Log.i(TAG, "Exception trying to release the audio track: " + e2.getMessage());
                    arrayList.add(e2);
                }
                this.mAudioTrack = null;
            }
            this.mState = NeoPlayer.State.STATE_STOPPED;
            if (this.mExternalNeoPlayerEventListener != null) {
                this.mExternalNeoPlayerEventListener.onPlayerStateChanged(NeoPlayer.NeoPlayerState.STATE_IDLE);
            }
            String str = "Stopped. " + this.mState;
            return arrayList;
        } finally {
            Trace.endSection();
        }
    }

    @Override // amazon.csm.neoplayer.NeoPlayer
    public void setAttributes(AudioAttributes audioAttributes) {
        this.mAudioAttr = new AudioAttributes.Builder(audioAttributes).build();
        reset();
    }

    @Override // amazon.csm.neoplayer.NeoPlayer
    public synchronized void setListener(NeoPlayer.Listener listener) {
        this.mExternalNeoPlayerEventListener = listener;
    }

    public void setSessionId(int i) {
        this.mAudioSessionId = i;
        reset();
    }

    public void setVolume(float f) {
        this.mVolume = f;
        reset();
    }

    @Override // amazon.csm.neoplayer.NeoPlayer
    public synchronized void stop() throws TimeoutException {
        Trace.beginSection("TrackPlayer stop");
        String str = "Stopping from " + this.mState;
        if (this.mState == NeoPlayer.State.STATE_RUNNING) {
            this.mTrackPlayerThread.stopAndWait();
            this.mState = NeoPlayer.State.STATE_STOPPED;
        } else if (this.mState != NeoPlayer.State.STATE_STOPPED) {
            Log.e(TAG, "Tried to stop from unexpected state: " + this.mState);
        }
        reset();
        Trace.endSection();
    }

    TrackPlayer(int i, MediaCodecWrapperFactory mediaCodecWrapperFactory) {
        this.mTrackPlayerThreadLock = new Object();
        this.mVolume = 1.0f;
        Trace.beginSection("TrackPlayer create");
        try {
            if (mediaCodecWrapperFactory != null) {
                this.mMediaCodecWrapperFactory = mediaCodecWrapperFactory;
                this.mAudioStream = i;
                this.mAudioAttr = new AudioAttributes.Builder().setLegacyStreamType(this.mAudioStream).build();
                this.mMediaExtractor = newMp3Extractor();
                this.mStreamListener = new TrackPlayerThread.StreamStateChangeListener() { // from class: amazon.csm.neoplayer.TrackPlayer.1
                    @Override // amazon.csm.neoplayer.TrackPlayerThread.StreamStateChangeListener
                    public void onStreamEnded(TrackPlayerThread trackPlayerThread) {
                        String unused = TrackPlayer.TAG;
                        synchronized (TrackPlayer.this.mTrackPlayerThreadLock) {
                            if (TrackPlayer.this.mExternalNeoPlayerEventListener != null && TrackPlayer.this.mTrackPlayerThread.hasCompletedNormally() && TrackPlayer.this.mTrackPlayerThread != null && TrackPlayer.this.mTrackPlayerThread.equals(trackPlayerThread)) {
                                TrackPlayer.this.mExternalNeoPlayerEventListener.onPlayerStateChanged(NeoPlayer.NeoPlayerState.STATE_ENDED);
                            }
                        }
                    }

                    @Override // amazon.csm.neoplayer.TrackPlayerThread.StreamStateChangeListener
                    public void onStreamError(TrackPlayerThread trackPlayerThread, Exception exc) {
                        Log.e(TrackPlayer.TAG, "onStreamError", exc);
                        synchronized (TrackPlayer.this.mTrackPlayerThreadLock) {
                            if (TrackPlayer.this.mExternalNeoPlayerEventListener != null && TrackPlayer.this.mTrackPlayerThread != null && TrackPlayer.this.mTrackPlayerThread.equals(trackPlayerThread)) {
                                TrackPlayer.this.mExternalNeoPlayerEventListener.onPlayerError(exc);
                            }
                        }
                    }

                    @Override // amazon.csm.neoplayer.TrackPlayerThread.StreamStateChangeListener
                    public void onStreamFlushed() {
                        try {
                            TrackPlayer.this.releaseAudioTrack();
                        } catch (RuntimeException e) {
                            String str = TrackPlayer.TAG;
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception trying to release the audio track: ");
                            outline107.append(e.getMessage());
                            Log.wtf(str, outline107.toString());
                        }
                    }

                    @Override // amazon.csm.neoplayer.TrackPlayerThread.StreamStateChangeListener
                    public void onStreamStarted() {
                        String unused = TrackPlayer.TAG;
                        NeoPlayer.Listener listener = TrackPlayer.this.mExternalNeoPlayerEventListener;
                        if (listener != null) {
                            listener.onPlayerStateChanged(NeoPlayer.NeoPlayerState.STATE_READY);
                        }
                    }
                };
                this.mState = NeoPlayer.State.STATE_STOPPED;
                this.mAudioSessionId = 0;
                return;
            }
            throw new IllegalArgumentException("MediaCodecWrapperFactory cannot be null");
        } finally {
            Trace.endSection();
        }
    }
}
