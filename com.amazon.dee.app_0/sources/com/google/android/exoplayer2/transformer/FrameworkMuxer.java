package com.google.android.exoplayer2.transformer;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.ParcelFileDescriptor;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.mediacodec.MediaFormatUtil;
import com.google.android.exoplayer2.transformer.Muxer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
/* JADX INFO: Access modifiers changed from: package-private */
@RequiresApi(18)
/* loaded from: classes2.dex */
public final class FrameworkMuxer implements Muxer {
    private final MediaCodec.BufferInfo bufferInfo;
    private boolean isStarted;
    private final MediaMuxer mediaMuxer;
    private final String outputMimeType;

    /* JADX INFO: Access modifiers changed from: private */
    public static int mimeTypeToMuxerOutputFormat(String str) {
        if (str.equals(MimeTypes.VIDEO_MP4)) {
            return 0;
        }
        if (Util.SDK_INT >= 21 && str.equals(MimeTypes.VIDEO_WEBM)) {
            return 1;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unsupported output MIME type: ", str));
    }

    @Override // com.google.android.exoplayer2.transformer.Muxer
    public int addTrack(Format format) {
        MediaFormat createVideoFormat;
        String str = (String) Assertions.checkNotNull(format.sampleMimeType);
        if (MimeTypes.isAudio(str)) {
            createVideoFormat = MediaFormat.createAudioFormat((String) Util.castNonNull(str), format.sampleRate, format.channelCount);
        } else {
            createVideoFormat = MediaFormat.createVideoFormat((String) Util.castNonNull(str), format.width, format.height);
            this.mediaMuxer.setOrientationHint(format.rotationDegrees);
        }
        MediaFormatUtil.setCsdBuffers(createVideoFormat, format.initializationData);
        return this.mediaMuxer.addTrack(createVideoFormat);
    }

    @Override // com.google.android.exoplayer2.transformer.Muxer
    public void release(boolean z) {
        if (!this.isStarted) {
            return;
        }
        this.isStarted = false;
        try {
            try {
                this.mediaMuxer.stop();
            } catch (IllegalStateException e) {
                if (Util.SDK_INT < 30) {
                    try {
                        Field declaredField = MediaMuxer.class.getDeclaredField("MUXER_STATE_STOPPED");
                        declaredField.setAccessible(true);
                        int intValue = ((Integer) Util.castNonNull((Integer) declaredField.get(this.mediaMuxer))).intValue();
                        Field declaredField2 = MediaMuxer.class.getDeclaredField("mState");
                        declaredField2.setAccessible(true);
                        declaredField2.set(this.mediaMuxer, Integer.valueOf(intValue));
                    } catch (Exception unused) {
                    }
                }
                if (!z) {
                    throw e;
                }
            }
        } finally {
            this.mediaMuxer.release();
        }
    }

    @Override // com.google.android.exoplayer2.transformer.Muxer
    public boolean supportsSampleMimeType(@Nullable String str) {
        boolean isAudio = MimeTypes.isAudio(str);
        boolean isVideo = MimeTypes.isVideo(str);
        if (this.outputMimeType.equals(MimeTypes.VIDEO_MP4)) {
            if (isVideo) {
                if (MimeTypes.VIDEO_H263.equals(str) || "video/avc".equals(str) || MimeTypes.VIDEO_MP4V.equals(str)) {
                    return true;
                }
                return Util.SDK_INT >= 24 && MimeTypes.VIDEO_H265.equals(str);
            } else if (isAudio) {
                return MimeTypes.AUDIO_AAC.equals(str) || MimeTypes.AUDIO_AMR_NB.equals(str) || MimeTypes.AUDIO_AMR_WB.equals(str);
            }
        } else if (this.outputMimeType.equals(MimeTypes.VIDEO_WEBM) && Util.SDK_INT >= 21) {
            if (isVideo) {
                if ("video/x-vnd.on2.vp8".equals(str)) {
                    return true;
                }
                return Util.SDK_INT >= 24 && "video/x-vnd.on2.vp9".equals(str);
            } else if (isAudio) {
                return MimeTypes.AUDIO_VORBIS.equals(str);
            }
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.transformer.Muxer
    public void writeSampleData(int i, ByteBuffer byteBuffer, boolean z, long j) {
        if (!this.isStarted) {
            this.isStarted = true;
            this.mediaMuxer.start();
        }
        int position = byteBuffer.position();
        this.bufferInfo.set(position, byteBuffer.limit() - position, j, z ? 1 : 0);
        this.mediaMuxer.writeSampleData(i, byteBuffer, this.bufferInfo);
    }

    /* loaded from: classes2.dex */
    public static final class Factory implements Muxer.Factory {
        @Override // com.google.android.exoplayer2.transformer.Muxer.Factory
        public boolean supportsOutputMimeType(String str) {
            try {
                FrameworkMuxer.mimeTypeToMuxerOutputFormat(str);
                return true;
            } catch (IllegalStateException unused) {
                return false;
            }
        }

        @Override // com.google.android.exoplayer2.transformer.Muxer.Factory
        /* renamed from: create */
        public FrameworkMuxer mo7449create(String str, String str2) throws IOException {
            return new FrameworkMuxer(new MediaMuxer(str, FrameworkMuxer.mimeTypeToMuxerOutputFormat(str2)), str2);
        }

        @Override // com.google.android.exoplayer2.transformer.Muxer.Factory
        @RequiresApi(26)
        /* renamed from: create */
        public FrameworkMuxer mo7448create(ParcelFileDescriptor parcelFileDescriptor, String str) throws IOException {
            return new FrameworkMuxer(new MediaMuxer(parcelFileDescriptor.getFileDescriptor(), FrameworkMuxer.mimeTypeToMuxerOutputFormat(str)), str);
        }
    }

    private FrameworkMuxer(MediaMuxer mediaMuxer, String str) {
        this.mediaMuxer = mediaMuxer;
        this.outputMimeType = str;
        this.bufferInfo = new MediaCodec.BufferInfo();
    }
}
