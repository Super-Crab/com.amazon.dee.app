package com.google.android.exoplayer2.mediacodec;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Surface;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.decoder.CryptoInfo;
import com.google.android.exoplayer2.mediacodec.SynchronousMediaCodecAdapter;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public interface MediaCodecAdapter {

    /* loaded from: classes2.dex */
    public interface Factory {
        public static final Factory DEFAULT = new SynchronousMediaCodecAdapter.Factory();

        /* renamed from: createAdapter */
        MediaCodecAdapter mo7293createAdapter(MediaCodec mediaCodec);
    }

    /* loaded from: classes2.dex */
    public interface OnFrameRenderedListener {
        void onFrameRendered(MediaCodecAdapter mediaCodecAdapter, long j, long j2);
    }

    void configure(@Nullable MediaFormat mediaFormat, @Nullable Surface surface, @Nullable MediaCrypto mediaCrypto, int i);

    int dequeueInputBufferIndex();

    int dequeueOutputBufferIndex(MediaCodec.BufferInfo bufferInfo);

    void flush();

    @Nullable
    ByteBuffer getInputBuffer(int i);

    @Nullable
    ByteBuffer getOutputBuffer(int i);

    MediaFormat getOutputFormat();

    void queueInputBuffer(int i, int i2, int i3, long j, int i4);

    void queueSecureInputBuffer(int i, int i2, CryptoInfo cryptoInfo, long j, int i3);

    void release();

    @RequiresApi(21)
    void releaseOutputBuffer(int i, long j);

    void releaseOutputBuffer(int i, boolean z);

    @RequiresApi(23)
    void setOnFrameRenderedListener(OnFrameRenderedListener onFrameRenderedListener, Handler handler);

    @RequiresApi(23)
    void setOutputSurface(Surface surface);

    @RequiresApi(19)
    void setParameters(Bundle bundle);

    void setVideoScalingMode(int i);

    void start();
}
