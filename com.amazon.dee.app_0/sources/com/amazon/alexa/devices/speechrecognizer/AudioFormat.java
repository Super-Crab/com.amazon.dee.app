package com.amazon.alexa.devices.speechrecognizer;
/* loaded from: classes6.dex */
public class AudioFormat {
    public Encoding encoding;
    public SampleRate sampleRate;

    /* loaded from: classes6.dex */
    public enum Encoding {
        ENCODING_PCM_16BIT,
        ENCODING_OPUS_16BIT,
        ENCODING_OPUS_16BIT_16KBPS
    }

    /* loaded from: classes6.dex */
    public enum SampleRate {
        SAMPLE_RATE_16K(16000);
        
        private final int sampleRate;

        SampleRate(int i) {
            this.sampleRate = i;
        }

        public int getSampleRate() {
            return this.sampleRate;
        }
    }

    public AudioFormat() {
        this(SampleRate.SAMPLE_RATE_16K, Encoding.ENCODING_PCM_16BIT);
    }

    public AudioFormat(SampleRate sampleRate, Encoding encoding) {
        this.sampleRate = sampleRate;
        this.encoding = encoding;
    }
}
