package amazon.speech.io;

import android.media.AudioFormat;
/* loaded from: classes.dex */
public interface DataStreamFactory {

    /* loaded from: classes.dex */
    public static class Params {
        AudioFormat mAudioFormat;
        int mBufferSize;
        String mName;
        int mNumPipes;

        public Params withAudioMetadata(AudioFormat audioFormat) {
            this.mAudioFormat = audioFormat;
            return this;
        }

        public Params withBufferSize(int i) {
            this.mBufferSize = i;
            return this;
        }

        public Params withName(String str) {
            this.mName = str;
            return this;
        }

        public Params withNumPipes(int i) {
            this.mNumPipes = i;
            return this;
        }
    }

    DataStream create(int i);

    DataStream create(int i, int i2);

    DataStream create(Params params);
}
