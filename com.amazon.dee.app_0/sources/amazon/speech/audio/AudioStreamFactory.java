package amazon.speech.audio;

import amazon.speech.audio.AudioStream;
import android.os.Parcel;
/* loaded from: classes.dex */
public interface AudioStreamFactory {
    IAudioStream create(AudioStream.AudioStreamParams audioStreamParams);

    IAudioStream createFromParcel(Parcel parcel);
}
