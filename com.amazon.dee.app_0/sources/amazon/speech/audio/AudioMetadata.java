package amazon.speech.audio;

import amazon.speech.io.DataStream;
import android.media.AudioFormat;
/* loaded from: classes.dex */
public abstract class AudioMetadata implements DataStream.Metadata {
    public static AudioMetadata fromAudioFormat(AudioFormat audioFormat) {
        final int bitCount = Integer.bitCount(audioFormat.getChannelMask());
        final int encoding = audioFormat.getEncoding();
        final int sampleRate = audioFormat.getSampleRate();
        return new AudioMetadata() { // from class: amazon.speech.audio.AudioMetadata.1
            @Override // amazon.speech.audio.AudioMetadata
            public int getChannelCount() {
                return bitCount;
            }

            @Override // amazon.speech.audio.AudioMetadata
            public int getEncoding() {
                return encoding;
            }

            @Override // amazon.speech.audio.AudioMetadata
            public int getSampleRate() {
                return sampleRate;
            }
        };
    }

    public static int getBytesPerSampleForEncoding(int i) {
        if (i != 1 && i != 2) {
            if (i == 3) {
                return 1;
            }
            if (i == 4) {
                return 4;
            }
            if (i != 1000 && i != 1001) {
                throw new UnsupportedOperationException("Encoding not supported or invalid.");
            }
        }
        return 2;
    }

    public static int getChannelCountForMask(int i) {
        return Integer.bitCount(i);
    }

    public abstract int getChannelCount();

    public abstract int getEncoding();

    public abstract int getSampleRate();
}
