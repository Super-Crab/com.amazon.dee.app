package com.amazon.alexa.utils.audio;

import android.media.AudioRecord;
import android.util.Log;
/* loaded from: classes10.dex */
public final class MicUtils {
    private static final int AUDIO_ENCODING = 2;
    private static final int CHANNEL_COUNT = 16;
    private static final int SAMPLE_RATE = 16000;
    private static final String TAG = "MicUtils";

    private MicUtils() {
        throw new UnsupportedOperationException("do not instantiate");
    }

    public static boolean canStartRecording() {
        int minBufferSize = AudioRecord.getMinBufferSize(16000, 16, 2);
        boolean z = false;
        if (minBufferSize == -2 || minBufferSize == -1) {
            Log.e(TAG, "Fail to get buffer size for AudioRecord");
            return false;
        }
        try {
            AudioRecord audioRecord = new AudioRecord(1, 16000, 16, 2, minBufferSize);
            try {
                if (audioRecord.getState() != 1) {
                    return false;
                }
                if (audioRecord.getRecordingState() != 1) {
                    return false;
                }
                audioRecord.startRecording();
                if (audioRecord.getRecordingState() == 3) {
                    z = true;
                }
                return z;
            } finally {
                audioRecord.release();
            }
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Fail to new AudioRecord", e);
            return false;
        }
    }
}
