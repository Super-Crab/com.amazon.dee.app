package amazon.speech.audio;

import amazon.speech.audio.AudioStream;
import android.os.Parcel;
/* loaded from: classes.dex */
public class AudioStreamV1Factory implements AudioStreamFactory {
    @Override // amazon.speech.audio.AudioStreamFactory
    public IAudioStream create(AudioStream.AudioStreamParams audioStreamParams) {
        return AudioStream.create(audioStreamParams);
    }

    @Override // amazon.speech.audio.AudioStreamFactory
    public IAudioStream createFromParcel(Parcel parcel) {
        return AudioStream.CREATOR.createFromParcel(parcel);
    }
}
