package com.amazon.blueshift.bluefront.android.audio;

import android.media.AudioRecord;
import com.google.common.annotations.VisibleForTesting;
/* loaded from: classes11.dex */
public class AudioRecordProvider implements IAudioRecordProvider {
    public static final AudioRecordProvider INSTANCE = new AudioRecordProvider(new AudioRecordBuilder());
    private AudioRecordWrapper mAudioRecord;
    private final AudioRecordBuilder mAudioRecordBuilder;

    /* loaded from: classes11.dex */
    static class AudioRecordBuilder {
        AudioRecordBuilder() {
        }

        public AudioRecordWrapper build() {
            return new AudioRecordWrapper(6, 16000, 16, 2, AudioRecord.getMinBufferSize(16000, 16, 2));
        }
    }

    @VisibleForTesting
    AudioRecordProvider(AudioRecordBuilder audioRecordBuilder) {
        this.mAudioRecordBuilder = audioRecordBuilder;
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.IAudioRecordProvider
    public synchronized AudioRecordWrapper getAudioRecord() {
        if (this.mAudioRecord == null) {
            this.mAudioRecord = this.mAudioRecordBuilder.build();
            this.mAudioRecord.setPositionNotificationPeriod(100);
        }
        return this.mAudioRecord;
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.IAudioRecordProvider
    public void releaseAudioRecord() {
        AudioRecordWrapper audioRecordWrapper = this.mAudioRecord;
        if (audioRecordWrapper != null) {
            try {
                audioRecordWrapper.release();
            } catch (Exception unused) {
            } catch (Throwable th) {
                this.mAudioRecord = null;
                throw th;
            }
            this.mAudioRecord = null;
        }
    }
}
