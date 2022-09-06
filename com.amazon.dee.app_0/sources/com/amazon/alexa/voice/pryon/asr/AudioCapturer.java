package com.amazon.alexa.voice.pryon.asr;

import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.NoiseSuppressor;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes11.dex */
public class AudioCapturer {
    private static final int SIXTEEN_KHZ = 16000;
    private static final String TAG = "AudioCapturer";
    private static final int TEST_SAMPLE_RATE = 44100;
    private short[] audioBuffer;
    private AudioRecord audioRecord;
    private final int bufferSize;
    private final int bytesBufferSize;
    private AcousticEchoCanceler acousticEchoCanceler = null;
    private NoiseSuppressor noiseSuppressor = null;

    public AudioCapturer(int i, boolean z, boolean z2) {
        this.bufferSize = i;
        this.bytesBufferSize = i * 2;
        this.audioBuffer = new short[i];
        try {
            this.audioRecord = buildAudioRecord();
            if (z) {
                requestAcousticEchoCanceler(this.audioRecord.getAudioSessionId());
            }
            if (z2) {
                requestNoiseSuppressor(this.audioRecord.getAudioSessionId());
            }
            try {
                this.audioRecord.startRecording();
            } catch (IllegalStateException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AudioRecord startRecording() failed. ");
                outline107.append(logCurrentAudioRecord());
                String sb = outline107.toString();
                this.audioRecord.release();
                throw new AudioRecordException(sb + logTestAudioRecord(TEST_SAMPLE_RATE), e);
            }
        } catch (IllegalArgumentException e2) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unable to instantiate test Audio Record object with audioSource: 1, sampleRate: 16000, channel: 16, encoding: 2, bufferSize: ");
            outline1072.append(this.bytesBufferSize);
            throw new AudioRecordException(outline1072.toString(), e2);
        }
    }

    private AudioRecord buildAudioRecord() {
        Log.i(TAG, "AudioCapturer AudioRecord is constructed.");
        return new AudioRecord(1, 16000, 16, 2, this.bytesBufferSize);
    }

    private String buildAudioRecordData(int i, int i2, int i3, int i4, int i5) {
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("AudioRecord {audioSource: ", i, ", sampleRate: ", i2, ", channel: ");
        GeneratedOutlineSupport1.outline175(outline110, i3, ", encoding: ", i4, ", bufferSize: ");
        outline110.append(i5);
        return outline110.toString();
    }

    private String checkAudioRecord(AudioRecord audioRecord, int i) {
        String str = buildAudioRecordData(audioRecord.getAudioSource(), audioRecord.getSampleRate(), audioRecord.getChannelConfiguration(), audioRecord.getAudioFormat(), i) + ", minRequiredBufferSize: " + AudioRecord.getMinBufferSize(audioRecord.getSampleRate(), audioRecord.getChannelConfiguration(), audioRecord.getAudioFormat()) + ", ";
        if (audioRecord.getState() == 1) {
            return GeneratedOutlineSupport1.outline72(str, "initialized: true} ");
        }
        return GeneratedOutlineSupport1.outline72(str, "initialized: false} ");
    }

    private String logCurrentAudioRecord() {
        return checkAudioRecord(this.audioRecord, this.bytesBufferSize);
    }

    private String logTestAudioRecord(int i) {
        AudioRecord audioRecord;
        String outline52 = GeneratedOutlineSupport1.outline52("Testing if device supports sampling rate of ", i, ". ");
        try {
            audioRecord = new AudioRecord(1, i, 16, 2, this.bytesBufferSize);
        } catch (IllegalArgumentException unused) {
            outline52 = GeneratedOutlineSupport1.outline75(outline52, "Unable to instantiate test Audio Record object with ", buildAudioRecordData(1, i, 16, 2, this.bytesBufferSize));
            audioRecord = null;
        }
        if (audioRecord != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(outline52);
            outline107.append(checkAudioRecord(audioRecord, this.bytesBufferSize));
            String sb = outline107.toString();
            audioRecord.release();
            return sb;
        }
        return outline52;
    }

    private void releaseAcousticEchoCanceler() {
        AcousticEchoCanceler acousticEchoCanceler = this.acousticEchoCanceler;
        if (acousticEchoCanceler != null) {
            acousticEchoCanceler.setEnabled(false);
            this.acousticEchoCanceler.release();
            this.acousticEchoCanceler = null;
        }
    }

    private void releaseNoiseSuppressor() {
        NoiseSuppressor noiseSuppressor = this.noiseSuppressor;
        if (noiseSuppressor != null) {
            noiseSuppressor.setEnabled(false);
            this.noiseSuppressor.release();
            this.noiseSuppressor = null;
        }
    }

    private void requestAcousticEchoCanceler(int i) {
        if (AcousticEchoCanceler.isAvailable()) {
            this.acousticEchoCanceler = AcousticEchoCanceler.create(i);
            this.acousticEchoCanceler.setEnabled(true);
            return;
        }
        Log.i(TAG, "AcousticEchoCanceller is not available");
    }

    private void requestNoiseSuppressor(int i) {
        if (NoiseSuppressor.isAvailable()) {
            this.noiseSuppressor = NoiseSuppressor.create(i);
            NoiseSuppressor noiseSuppressor = this.noiseSuppressor;
            if (noiseSuppressor != null) {
                noiseSuppressor.setEnabled(true);
                return;
            } else {
                Log.i(TAG, "NoiseSuppressor.create() returned null.");
                return;
            }
        }
        Log.i(TAG, "NoiseSuppressor is not available");
    }

    public short[] read() throws IOException {
        int read = this.audioRecord.read(this.audioBuffer, 0, this.bufferSize);
        if (this.bufferSize == read) {
            return this.audioBuffer;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Buffer underrun -- wanted ");
        outline107.append(this.bufferSize);
        outline107.append(" many shorts, but got ");
        outline107.append(read);
        throw new IOException(outline107.toString());
    }

    public void release() {
        Log.i(TAG, "AudioCapturer AudioRecord.stop() and AudioRecord.release() are called.");
        releaseAcousticEchoCanceler();
        releaseNoiseSuppressor();
        this.audioRecord.stop();
        this.audioRecord.release();
        this.audioRecord = null;
    }
}
