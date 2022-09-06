package com.amazon.alexa;

import android.text.TextUtils;
import com.amazon.alexa.api.AudioFormat;
import com.amazon.alexa.audiocapturer.RecordingRunnable;
import com.amazon.comms.config.util.DeviceConfigConstants;
/* compiled from: DataFormat.java */
/* loaded from: classes.dex */
public enum ZVp {
    LPCM16(32000, DeviceConfigConstants.VIDEO_WIDTH_320, RecordingRunnable.USER_SPEECH_AUDIO_FORMAT),
    OPUS16(2000, 80, "AUDIO_X_CBR_OPUS_WITH_PREAMBLE_SIZE_0_BIT_RATE_16000_FRAME_SIZE_MILLISECONDS_20"),
    OPUS32(4000, 80, "OPUS"),
    MSBC(-1, 60, "MSBC"),
    BINARY(-1, DeviceConfigConstants.VIDEO_WIDTH_320, "BINARY");
    
    public final String audioFormatToString;
    public final long bytesPerSecond;
    public final int dataFrameSize;
    public boolean isOpusEncoded = false;

    ZVp(long j, int i, String str) {
        this.bytesPerSecond = j;
        this.dataFrameSize = i;
        this.audioFormatToString = str;
    }

    public long BIo() {
        return zQM() / 1000;
    }

    public boolean Qle() {
        return this.isOpusEncoded;
    }

    public boolean jiA() {
        return this.bytesPerSecond >= 0;
    }

    public long zQM() {
        return this.bytesPerSecond;
    }

    public void zZm(boolean z) {
        this.isOpusEncoded = z;
    }

    public int zyO() {
        return this.dataFrameSize;
    }

    public String zZm() {
        return this.audioFormatToString;
    }

    public static ZVp zZm(String str) {
        if (TextUtils.equals(AudioFormat.AUDIO_L16_RATE_16000_CHANNELS_1.name(), str)) {
            return LPCM16;
        }
        if (TextUtils.equals(AudioFormat.AUDIO_X_CBR_OPUS_WITH_PREAMBLE_SIZE_0_BIT_RATE_16000_FRAME_SIZE_MILLISECONDS_20.name(), str)) {
            return OPUS16;
        }
        if (TextUtils.equals(AudioFormat.AUDIO_X_CBR_OPUS_WITH_PREAMBLE_SIZE_0_BIT_RATE_32000_FRAME_SIZE_MILLISECONDS_20.name(), str)) {
            return OPUS32;
        }
        if (TextUtils.equals(AudioFormat.OPUS.name(), str)) {
            return OPUS32;
        }
        if (TextUtils.equals(AudioFormat.MSBC.name(), str)) {
            return MSBC;
        }
        return BINARY;
    }
}
