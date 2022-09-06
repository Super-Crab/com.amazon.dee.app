package amazon.speech.audio.v2;

import amazon.speech.audio.AudioStream;
import amazon.speech.audio.AudioStreamFactory;
import amazon.speech.audio.IAudioStream;
import android.os.Parcel;
/* loaded from: classes.dex */
public class AudioStreamV2Factory implements AudioStreamFactory {
    @Override // amazon.speech.audio.AudioStreamFactory
    public IAudioStream create(AudioStream.AudioStreamParams audioStreamParams) {
        return AudioStream.create(audioStreamParams);
    }

    @Override // amazon.speech.audio.AudioStreamFactory
    public IAudioStream createFromParcel(Parcel parcel) {
        return AudioStream.CREATOR.createFromParcel(parcel);
    }
}
