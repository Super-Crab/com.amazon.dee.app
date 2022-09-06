package amazon.speech.io;

import amazon.speech.audio.AudioMetadata;
import amazon.speech.audio.AudioStreamMajorVersion;
import amazon.speech.io.DataStreamFactory;
import android.media.AudioFormat;
/* loaded from: classes.dex */
public class InProcDataStreamFactory implements DataStreamFactory {
    @Override // amazon.speech.io.DataStreamFactory
    public DataStream create(int i) {
        return create(new DataStreamFactory.Params().withBufferSize(i).withNumPipes(1));
    }

    @Override // amazon.speech.io.DataStreamFactory
    public DataStream create(int i, int i2) {
        return create(new DataStreamFactory.Params().withBufferSize(i).withNumPipes(i2));
    }

    @Override // amazon.speech.io.DataStreamFactory
    public DataStream create(DataStreamFactory.Params params) {
        if (AudioStreamMajorVersion.getVersion() == AudioStreamMajorVersion.Version.V2) {
            return new AudioStreamDataStream(params.mName, params.mBufferSize, params.mNumPipes, params.mAudioFormat);
        }
        AudioFormat audioFormat = params.mAudioFormat;
        return new InProcDataStream(params.mBufferSize, params.mNumPipes, audioFormat != null ? AudioMetadata.fromAudioFormat(audioFormat) : null);
    }
}
