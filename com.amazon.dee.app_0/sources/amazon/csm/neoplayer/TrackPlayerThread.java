package amazon.csm.neoplayer;

import android.media.AudioTrack;
import android.media.MediaCodec;
import android.os.Process;
import android.os.Trace;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public class TrackPlayerThread extends Thread {
    static final long BUFFER_IO_TIMEOUT_US = 100000;
    static final long MAX_AUDIO_RECORD_LATENCY_MS = 5000;
    private static final int MAX_CONSECUTIVE_WRITE_RETRIES = 5;
    private static final String TAG = TrackPlayerThread.class.getSimpleName();
    private static final int TOTAL_ALLOWED_CODEC_RESETS = 5;
    private static final long WAIT_FOR_STOP_FINISHED_TIMEOUT_MS = 1000;
    final NeoAudioTrack mAudioTrack;
    Method mAudioTrackGetLatencyMethod;
    private ByteBuffer mInputBuffer;
    private int mInputBufferIndex;
    private int mInputDataTypeFrameLength;
    private ByteBuffer mLeftoverBuffer;
    MediaCodecWrapper mMediaCodecWrapper;
    final Mp3Extractor mMediaExtractor;
    private ByteBuffer mOutputBuffer;
    int mPreferredBufferSize;
    CountDownLatch mStateBecameUninitializedSignal;
    volatile boolean mStopRequested;
    final StreamStateChangeListener mStreamEndedListener;
    TrackPlayer mTrackPlayer;
    int mWriteRetryCount;
    volatile TrackPlayerState mPlayerState = TrackPlayerState.NOT_STARTED;
    int DEFAULT_BUFFER_SIZE = 512;

    /* renamed from: amazon.csm.neoplayer.TrackPlayerThread$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$amazon$csm$neoplayer$TrackPlayerThread$ReadStatus = new int[ReadStatus.values().length];

        static {
            try {
                $SwitchMap$amazon$csm$neoplayer$TrackPlayerThread$ReadStatus[ReadStatus.END_OF_STREAM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$amazon$csm$neoplayer$TrackPlayerThread$ReadStatus[ReadStatus.CODEC_INPUT_BUFFER_UNAVAILABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$amazon$csm$neoplayer$TrackPlayerThread$ReadStatus[ReadStatus.CONTINUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum ReadStatus {
        CONTINUE,
        CODEC_INPUT_BUFFER_UNAVAILABLE,
        END_OF_STREAM
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public interface StreamStateChangeListener {
        void onStreamEnded(TrackPlayerThread trackPlayerThread);

        void onStreamError(TrackPlayerThread trackPlayerThread, Exception exc);

        void onStreamFlushed();

        void onStreamStarted();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum TrackPlayerState {
        NOT_STARTED,
        RUNNING,
        STOPPED,
        COMPLETED,
        ERROR
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum WriteStatus {
        SUCCESS,
        TIMEOUT,
        FORMAT_CHANGE,
        BUFFERS_CHANGE,
        UNEXPECTED_INDEX
    }

    public TrackPlayerThread(TrackPlayer trackPlayer, Mp3Extractor mp3Extractor, MediaCodecWrapper mediaCodecWrapper, NeoAudioTrack neoAudioTrack, StreamStateChangeListener streamStateChangeListener) {
        Trace.beginSection("TrackPlayerThread create");
        this.mTrackPlayer = trackPlayer;
        try {
            this.mAudioTrackGetLatencyMethod = AudioTrack.class.getMethod("getLatency", null);
        } catch (NoSuchMethodException unused) {
        }
        try {
            if (mp3Extractor != null && mediaCodecWrapper != null && neoAudioTrack != null && streamStateChangeListener != null) {
                this.mMediaExtractor = mp3Extractor;
                this.mMediaCodecWrapper = mediaCodecWrapper;
                this.mAudioTrack = neoAudioTrack;
                this.mStreamEndedListener = streamStateChangeListener;
                this.mInputDataTypeFrameLength = this.mMediaExtractor.getFrameLength();
                this.mStateBecameUninitializedSignal = new CountDownLatch(1);
                this.mPreferredBufferSize = AudioTrack.getMinBufferSize(this.mMediaExtractor.getSampleRate(), 4, 2);
                if (this.mPreferredBufferSize < 1) {
                    this.mPreferredBufferSize = this.DEFAULT_BUFFER_SIZE;
                    Log.w(TAG, "Could not determine buffer size.  Using default value");
                }
                this.mOutputBuffer = ByteBuffer.allocateDirect(this.mPreferredBufferSize * 4);
                this.mLeftoverBuffer = ByteBuffer.allocateDirect(this.mPreferredBufferSize);
                this.mLeftoverBuffer.limit(0);
                return;
            }
            throw new IllegalArgumentException("Arguments cannot be null. Arguments were: extractor: " + mp3Extractor + ", codecWrapper: " + mediaCodecWrapper + " track: " + neoAudioTrack + ", streamListener: " + streamStateChangeListener);
        } finally {
            Trace.endSection();
        }
    }

    WriteStatus doContinue() {
        WriteStatus writeOutputToSpeakers;
        this.mWriteRetryCount = -1;
        do {
            writeOutputToSpeakers = writeOutputToSpeakers();
            this.mWriteRetryCount++;
            if (writeOutputToSpeakers != WriteStatus.BUFFERS_CHANGE && writeOutputToSpeakers != WriteStatus.FORMAT_CHANGE) {
                break;
            }
        } while (this.mWriteRetryCount < 5);
        if (writeOutputToSpeakers == WriteStatus.UNEXPECTED_INDEX) {
            this.mPlayerState = TrackPlayerState.ERROR;
        }
        if (this.mWriteRetryCount >= 5) {
            Log.e(TAG, "Too many retries writing frame, aborting.  The last frame of this TTS won't be written.");
        }
        return writeOutputToSpeakers;
    }

    void ensureAudioTrackStarted() {
        int initialBufferSize = this.mAudioTrack.getInitialBufferSize() - this.mAudioTrack.getBytesWritten();
        if (initialBufferSize > 0) {
            this.mAudioTrack.write(ByteBuffer.allocate(initialBufferSize), initialBufferSize, 0);
        }
    }

    void flushOutputToSpeakers() {
        Log.i(TAG, "flushing");
        try {
            if (this.mLeftoverBuffer.limit() > 0) {
                this.mAudioTrack.write(this.mLeftoverBuffer, this.mLeftoverBuffer.limit(), 0);
                ensureAudioTrackStarted();
                this.mLeftoverBuffer.limit(0);
            }
            long audioTrackLatency = getAudioTrackLatency(this.mAudioTrackGetLatencyMethod);
            if (audioTrackLatency > 0) {
                Thread.sleep(audioTrackLatency);
            }
            this.mAudioTrack.flush();
        } catch (Exception e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception encountered during flush: ");
            outline107.append(e.getMessage());
            Log.i(str, outline107.toString());
        }
    }

    long getAudioTrackLatency(Method method) {
        long j = 0;
        if (method == null) {
            return 0L;
        }
        try {
            long max = Math.max(((Integer) method.invoke(this.mAudioTrack, null)).intValue() * 1000, 0L) / 1000;
            if (max > 5000) {
                String str = "Ignoring impossibly large audio track latency " + max;
            } else {
                j = max;
            }
        } catch (Exception unused) {
        }
        String str2 = "AudioTrack has: " + j + "ms of latency";
        return j;
    }

    public boolean hasCompletedNormally() {
        return this.mPlayerState == TrackPlayerState.COMPLETED;
    }

    ReadStatus readInputFromStream() throws IOException {
        Trace.beginSection("TrackPlayerThread readInputFromStream");
        try {
            this.mInputBufferIndex = this.mMediaCodecWrapper.dequeueInputBuffer(100000L);
            if (this.mInputBufferIndex < 0) {
                Log.w(TAG, "MediaCodec timed out waiting for available input buffers.");
                return ReadStatus.CODEC_INPUT_BUFFER_UNAVAILABLE;
            }
            this.mInputBuffer = this.mMediaCodecWrapper.getInputBuffer(this.mInputBufferIndex);
            int readBytes = this.mMediaExtractor.readBytes(this.mInputBuffer);
            if (readBytes < this.mInputDataTypeFrameLength) {
                if (readBytes < 0) {
                    Log.i(TAG, "MediaCodec reached end of stream.");
                } else {
                    String str = TAG;
                    Log.w(str, "MediaCodec reached end of stream, found partial frame with " + readBytes + " bytes. Expected " + this.mInputDataTypeFrameLength + " bytes. Attempting to play partial frame.");
                    this.mMediaCodecWrapper.queueInputBuffer(this.mInputBufferIndex, 0, readBytes, TimeUnit.MILLISECONDS.toMicros(this.mMediaExtractor.getCurrentPosition()), 4);
                }
                return ReadStatus.END_OF_STREAM;
            }
            this.mMediaCodecWrapper.queueInputBuffer(this.mInputBufferIndex, 0, readBytes, TimeUnit.MILLISECONDS.toMicros(this.mMediaExtractor.getCurrentPosition()), 0);
            return ReadStatus.CONTINUE;
        } finally {
            Trace.endSection();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Trace.beginSection("TrackPlayerThread doInBackground");
        Process.setThreadPriority(-16);
        try {
            Log.i(TAG, "TrackPlayer thread started.");
            int i = 0;
            while (true) {
                if (this.mPlayerState != TrackPlayerState.RUNNING) {
                    break;
                }
                try {
                    int ordinal = readInputFromStream().ordinal();
                    if (ordinal == 0) {
                        doContinue();
                    } else if (ordinal == 1) {
                        this.mPlayerState = TrackPlayerState.ERROR;
                    } else if (ordinal == 2) {
                        this.mPlayerState = TrackPlayerState.COMPLETED;
                    }
                    if (this.mStopRequested) {
                        this.mPlayerState = TrackPlayerState.STOPPED;
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Error performing read or write.", e);
                    this.mPlayerState = TrackPlayerState.ERROR;
                    this.mTrackPlayer.releaseCodec(this.mMediaCodecWrapper);
                    this.mStreamEndedListener.onStreamError(this, e);
                } catch (IllegalStateException e2) {
                    i++;
                    if (i < 5) {
                        Log.w(TAG, "readInput or writeOutput encountered state error.");
                        if (!tryRecreateCodec()) {
                            break;
                        }
                    } else {
                        Log.e(TAG, "readInput or writeOutput encountered state error.", e2);
                        this.mPlayerState = TrackPlayerState.ERROR;
                        this.mTrackPlayer.releaseCodec(this.mMediaCodecWrapper);
                        this.mStreamEndedListener.onStreamError(this, e2);
                        break;
                    }
                }
            }
        } finally {
            flushOutputToSpeakers();
            Log.i(TAG, "thread finished.");
            this.mStreamEndedListener.onStreamEnded(this);
            try {
                this.mMediaCodecWrapper.flush();
            } catch (IllegalStateException unused) {
                Log.i(TAG, "called flush in illegal state");
            }
            this.mStateBecameUninitializedSignal.countDown();
            this.mStreamEndedListener.onStreamFlushed();
        }
    }

    public synchronized void startPlayback() throws TimeoutException {
        Trace.beginSection("TrackPlayerThread startPlayback");
        this.mPlayerState = TrackPlayerState.RUNNING;
        start();
        Trace.endSection();
    }

    public synchronized void stopAndWait() throws TimeoutException {
        Trace.beginSection("TrackPlayerThread stopAndWait");
        if (this.mPlayerState == TrackPlayerState.RUNNING) {
            try {
                this.mStopRequested = true;
                if (!this.mStateBecameUninitializedSignal.await(1000L, TimeUnit.MILLISECONDS)) {
                    throw new TimeoutException("Timed out waiting for doInBackground to finish.");
                }
            } catch (InterruptedException e) {
                this.mStreamEndedListener.onStreamError(this, e);
                Log.e(TAG, "CountDownLatch was inturupted waiting for writer to stop.", e);
            }
        }
        Trace.endSection();
    }

    boolean tryRecreateCodec() {
        try {
            this.mMediaCodecWrapper = this.mTrackPlayer.releaseAndRecreateCodec(this.mMediaCodecWrapper);
            return true;
        } catch (CodecException | NullPointerException e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not recreate mp3 codec: ");
            outline107.append(e.getLocalizedMessage());
            Log.wtf(str, outline107.toString(), e);
            this.mStreamEndedListener.onStreamError(this, e);
            return false;
        }
    }

    WriteStatus writeOutputToSpeakers() {
        int i;
        Throwable th;
        MediaCodec.BufferInfo mediaCodecBufferInfo;
        Trace.beginSection("TrackPlayerThread writeOutputToSpeakers");
        try {
            mediaCodecBufferInfo = this.mMediaCodecWrapper.getMediaCodecBufferInfo();
            i = this.mMediaCodecWrapper.dequeueOutputBuffer(mediaCodecBufferInfo, 100000L);
        } catch (Throwable th2) {
            i = -1;
            th = th2;
        }
        try {
            if (i >= 0) {
                ByteBuffer outputBuffer = this.mMediaCodecWrapper.getOutputBuffer(i);
                Trace.beginSection("TrackPlayerThread writeOutputToSpeakers write");
                writePreferredSize(outputBuffer, mediaCodecBufferInfo.size);
                Trace.endSection();
                WriteStatus writeStatus = WriteStatus.SUCCESS;
                if (i >= 0) {
                    this.mMediaCodecWrapper.releaseOutputBuffer(i, false);
                }
                Trace.endSection();
                return writeStatus;
            } else if (i == -1) {
                Log.w(TAG, "MediaCodec timed out waiting for output buffer.");
                WriteStatus writeStatus2 = WriteStatus.TIMEOUT;
                if (i >= 0) {
                    this.mMediaCodecWrapper.releaseOutputBuffer(i, false);
                }
                Trace.endSection();
                return writeStatus2;
            } else if (i == -2) {
                Log.i(TAG, "MediaCodec output format changed. Is now: " + this.mMediaCodecWrapper.getOutputFormat());
                WriteStatus writeStatus3 = WriteStatus.FORMAT_CHANGE;
                if (i >= 0) {
                    this.mMediaCodecWrapper.releaseOutputBuffer(i, false);
                }
                Trace.endSection();
                return writeStatus3;
            } else if (i == -3) {
                Log.i(TAG, "MediaCodec output buffer changed.");
                WriteStatus writeStatus4 = WriteStatus.BUFFERS_CHANGE;
                if (i >= 0) {
                    this.mMediaCodecWrapper.releaseOutputBuffer(i, false);
                }
                Trace.endSection();
                return writeStatus4;
            } else {
                Log.e(TAG, "Unexpected output buffer index: " + i + ". Aborting.");
                WriteStatus writeStatus5 = WriteStatus.UNEXPECTED_INDEX;
                if (i >= 0) {
                    this.mMediaCodecWrapper.releaseOutputBuffer(i, false);
                }
                Trace.endSection();
                return writeStatus5;
            }
        } catch (Throwable th3) {
            th = th3;
            if (i >= 0) {
                this.mMediaCodecWrapper.releaseOutputBuffer(i, false);
            }
            Trace.endSection();
            throw th;
        }
    }

    void writePreferredSize(ByteBuffer byteBuffer, int i) {
        byteBuffer.limit(i);
        this.mOutputBuffer.clear();
        int limit = this.mLeftoverBuffer.limit();
        if (limit > 0) {
            this.mOutputBuffer.put(this.mLeftoverBuffer);
            this.mLeftoverBuffer.clear();
            this.mLeftoverBuffer.limit(0);
        }
        this.mOutputBuffer.put(byteBuffer);
        this.mOutputBuffer.position(0);
        this.mOutputBuffer.limit(limit + i);
        int remaining = this.mOutputBuffer.remaining();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = this.mPreferredBufferSize;
            if (remaining >= i4) {
                int write = this.mAudioTrack.write(this.mOutputBuffer, i4, 0);
                if (write < 0 && write != i2) {
                    Log.w(TAG, "AudioTrack write failed with code: " + write);
                }
                i3 += this.mPreferredBufferSize;
                this.mOutputBuffer.position(i3);
                i2 = write;
                remaining = this.mOutputBuffer.remaining();
            } else {
                this.mLeftoverBuffer.limit(this.mOutputBuffer.remaining());
                this.mLeftoverBuffer.put(this.mOutputBuffer);
                this.mLeftoverBuffer.position(0);
                return;
            }
        }
    }
}
