package amazon.speech.io;

import amazon.speech.io.DataStreamFactory;
/* loaded from: classes.dex */
public class AudioStreamDataStreamFactory implements DataStreamFactory {
    @Override // amazon.speech.io.DataStreamFactory
    public DataStream create(int i) {
        return DataStream.create(i);
    }

    @Override // amazon.speech.io.DataStreamFactory
    public DataStream create(int i, int i2) {
        return DataStream.create(i, i2);
    }

    @Override // amazon.speech.io.DataStreamFactory
    public DataStream create(DataStreamFactory.Params params) {
        return new AudioStreamDataStream(params.mName, params.mBufferSize, params.mNumPipes, params.mAudioFormat);
    }
}
