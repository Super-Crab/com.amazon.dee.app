package com.amazon.blueshift.bluefront.android.audio;
/* loaded from: classes11.dex */
public interface IAudioRecordProvider {
    public static final int CHANNELS = 16;
    public static final int DEFAULT_RECORDER_POSITION_NOTIFICATION_PERIOD = 100;
    public static final int FORMAT = 2;
    public static final int SAMPLE_RATE = 16000;

    AudioRecordWrapper getAudioRecord();

    void releaseAudioRecord();
}
