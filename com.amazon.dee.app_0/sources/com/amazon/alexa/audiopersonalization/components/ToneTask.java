package com.amazon.alexa.audiopersonalization.components;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public class ToneTask implements Runnable {
    private static final int NUM_CHANNELS = 2;
    private static final int SAMPLE_RATE = 44100;
    public static final double SCALAR_MAX_VOLUME = 1000.0d;
    private static final String TAG = ToneTask.class.getSimpleName();
    private final Tone mTone;

    public ToneTask(Tone tone) {
        this.mTone = tone;
    }

    private void adjustBufferToFadeIn(float[] fArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 2;
            float f = i2 / i;
            fArr[i3] = fArr[i3] * f;
            int i4 = i3 + 1;
            fArr[i4] = fArr[i4] * f;
        }
    }

    private AudioTrack constructAudioTrack() {
        return new AudioTrack(new AudioAttributes.Builder().setContentType(2).setUsage(1).build(), new AudioFormat.Builder().setSampleRate(SAMPLE_RATE).setEncoding(4).setChannelMask(12).build(), AudioTrack.getMinBufferSize(SAMPLE_RATE, 12, 4), 1, 0);
    }

    private float[] generateAudioBuffer(Tone tone) {
        int i = (((tone.fade * 2) + tone.duration) * SAMPLE_RATE) / 1000;
        float[] fArr = new float[i * 2];
        for (int i2 = 0; i2 < i; i2++) {
            float sin = (float) ((Math.sin(((tone.frequency * 6.283185307179586d) * i2) / 44100.0d) * getScalarVolumeFromDBFS(tone.volume)) / 1000.0d);
            if (tone.isLeftChannelOn()) {
                fArr[i2 * 2] = sin;
            }
            if (tone.isRightChannelOn()) {
                fArr[(i2 * 2) + 1] = sin;
            }
        }
        return fArr;
    }

    private double getScalarVolumeFromDBFS(double d) {
        return Math.pow(10.0d, d / 20.0d) * 1000.0d;
    }

    private void playFadeOut(AudioTrack audioTrack, float[] fArr, int i, int i2) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Fading out tone with id: ");
        outline107.append(this.mTone.requestId);
        outline107.toString();
        for (int i3 = 0; i3 < i2; i3++) {
            audioTrack.setVolume((float) Math.pow((i2 - i3) / i2, 7.0d));
            if (audioTrack.write(fArr, (i + i3) * 2, 2, 0) != 2) {
                Log.e(TAG, "Error writing data in tone fade out.");
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Started playing tone with id: ");
        outline107.append(this.mTone.requestId);
        outline107.toString();
        AudioTrack constructAudioTrack = constructAudioTrack();
        float[] generateAudioBuffer = generateAudioBuffer(this.mTone);
        int i = (this.mTone.fade * SAMPLE_RATE) / 1000;
        adjustBufferToFadeIn(generateAudioBuffer, i);
        int length = (generateAudioBuffer.length / 2) - i;
        constructAudioTrack.play();
        int i2 = 0;
        while (i2 < length && !Thread.interrupted()) {
            if (constructAudioTrack.write(generateAudioBuffer, i2 * 2, 2, 0) != 2) {
                Log.e(TAG, "Error writing tone audio data.");
            }
            i2++;
        }
        playFadeOut(constructAudioTrack, generateAudioBuffer, i2, i);
        constructAudioTrack.release();
        String str = "Finished playing tone with id: " + this.mTone.requestId;
    }
}
