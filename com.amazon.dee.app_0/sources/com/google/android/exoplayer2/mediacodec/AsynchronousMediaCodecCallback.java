package com.google.android.exoplayer2.mediacodec;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.IntArrayQueue;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayDeque;
import org.aspectj.lang.JoinPoint;
/* JADX INFO: Access modifiers changed from: package-private */
@RequiresApi(23)
/* loaded from: classes2.dex */
public final class AsynchronousMediaCodecCallback extends MediaCodec.Callback {
    private final HandlerThread callbackThread;
    @Nullable
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private MediaFormat currentFormat;
    private Handler handler;
    @Nullable
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private IllegalStateException internalException;
    @Nullable
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private MediaCodec.CodecException mediaCodecException;
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private long pendingFlushCount;
    @Nullable
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private MediaFormat pendingOutputFormat;
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private boolean shutDown;
    private final Object lock = new Object();
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private final IntArrayQueue availableInputBuffers = new IntArrayQueue();
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private final IntArrayQueue availableOutputBuffers = new IntArrayQueue();
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private final ArrayDeque<MediaCodec.BufferInfo> bufferInfos = new ArrayDeque<>();
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private final ArrayDeque<MediaFormat> formats = new ArrayDeque<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AsynchronousMediaCodecCallback(HandlerThread handlerThread) {
        this.callbackThread = handlerThread;
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private void addOutputFormat(MediaFormat mediaFormat) {
        this.availableOutputBuffers.add(-2);
        this.formats.add(mediaFormat);
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private void flushInternal() {
        if (!this.formats.isEmpty()) {
            this.pendingOutputFormat = this.formats.getLast();
        }
        this.availableInputBuffers.clear();
        this.availableOutputBuffers.clear();
        this.bufferInfos.clear();
        this.formats.clear();
        this.mediaCodecException = null;
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private boolean isFlushingOrShutdown() {
        return this.pendingFlushCount > 0 || this.shutDown;
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private void maybeThrowException() {
        maybeThrowInternalException();
        maybeThrowMediaCodecException();
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private void maybeThrowInternalException() {
        IllegalStateException illegalStateException = this.internalException;
        if (illegalStateException == null) {
            return;
        }
        this.internalException = null;
        throw illegalStateException;
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private void maybeThrowMediaCodecException() {
        MediaCodec.CodecException codecException = this.mediaCodecException;
        if (codecException == null) {
            return;
        }
        this.mediaCodecException = null;
        throw codecException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onFlushCompleted */
    public void lambda$flushAsync$0$AsynchronousMediaCodecCallback(Runnable runnable) {
        synchronized (this.lock) {
            onFlushCompletedSynchronized(runnable);
        }
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private void onFlushCompletedSynchronized(Runnable runnable) {
        if (this.shutDown) {
            return;
        }
        this.pendingFlushCount--;
        long j = this.pendingFlushCount;
        if (j > 0) {
            return;
        }
        if (j < 0) {
            setInternalException(new IllegalStateException());
            return;
        }
        flushInternal();
        try {
            runnable.run();
        } catch (IllegalStateException e) {
            setInternalException(e);
        } catch (Exception e2) {
            setInternalException(new IllegalStateException(e2));
        }
    }

    private void setInternalException(IllegalStateException illegalStateException) {
        synchronized (this.lock) {
            this.internalException = illegalStateException;
        }
    }

    public int dequeueInputBufferIndex() {
        synchronized (this.lock) {
            int i = -1;
            if (isFlushingOrShutdown()) {
                return -1;
            }
            maybeThrowException();
            if (!this.availableInputBuffers.isEmpty()) {
                i = this.availableInputBuffers.remove();
            }
            return i;
        }
    }

    public int dequeueOutputBufferIndex(MediaCodec.BufferInfo bufferInfo) {
        synchronized (this.lock) {
            if (isFlushingOrShutdown()) {
                return -1;
            }
            maybeThrowException();
            if (this.availableOutputBuffers.isEmpty()) {
                return -1;
            }
            int remove = this.availableOutputBuffers.remove();
            if (remove >= 0) {
                Assertions.checkStateNotNull(this.currentFormat);
                MediaCodec.BufferInfo remove2 = this.bufferInfos.remove();
                bufferInfo.set(remove2.offset, remove2.size, remove2.presentationTimeUs, remove2.flags);
            } else if (remove == -2) {
                this.currentFormat = this.formats.remove();
            }
            return remove;
        }
    }

    public void flushAsync(final Runnable runnable) {
        synchronized (this.lock) {
            this.pendingFlushCount++;
            ((Handler) Util.castNonNull(this.handler)).post(new Runnable() { // from class: com.google.android.exoplayer2.mediacodec.-$$Lambda$AsynchronousMediaCodecCallback$OMb9exBAoH-BTVNrGYNnKqyt5To
                @Override // java.lang.Runnable
                public final void run() {
                    AsynchronousMediaCodecCallback.this.lambda$flushAsync$0$AsynchronousMediaCodecCallback(runnable);
                }
            });
        }
    }

    public MediaFormat getOutputFormat() {
        MediaFormat mediaFormat;
        synchronized (this.lock) {
            if (this.currentFormat != null) {
                mediaFormat = this.currentFormat;
            } else {
                throw new IllegalStateException();
            }
        }
        return mediaFormat;
    }

    public void initialize(MediaCodec mediaCodec) {
        Assertions.checkState(this.handler == null);
        this.callbackThread.start();
        Handler handler = new Handler(this.callbackThread.getLooper());
        mediaCodec.setCallback(this, handler);
        this.handler = handler;
    }

    @Override // android.media.MediaCodec.Callback
    public void onError(@NonNull MediaCodec mediaCodec, @NonNull MediaCodec.CodecException codecException) {
        synchronized (this.lock) {
            this.mediaCodecException = codecException;
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onInputBufferAvailable(@NonNull MediaCodec mediaCodec, int i) {
        synchronized (this.lock) {
            this.availableInputBuffers.add(i);
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputBufferAvailable(@NonNull MediaCodec mediaCodec, int i, @NonNull MediaCodec.BufferInfo bufferInfo) {
        synchronized (this.lock) {
            if (this.pendingOutputFormat != null) {
                addOutputFormat(this.pendingOutputFormat);
                this.pendingOutputFormat = null;
            }
            this.availableOutputBuffers.add(i);
            this.bufferInfos.add(bufferInfo);
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputFormatChanged(@NonNull MediaCodec mediaCodec, @NonNull MediaFormat mediaFormat) {
        synchronized (this.lock) {
            addOutputFormat(mediaFormat);
            this.pendingOutputFormat = null;
        }
    }

    public void shutdown() {
        synchronized (this.lock) {
            this.shutDown = true;
            this.callbackThread.quit();
            flushInternal();
        }
    }
}
