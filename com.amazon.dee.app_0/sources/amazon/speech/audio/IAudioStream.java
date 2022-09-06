package amazon.speech.audio;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public interface IAudioStream extends Parcelable {
    @Override // android.os.Parcelable
    int describeContents();

    int getChannelCount();

    int getEncoding();

    int getSampleRate();

    AudioStreamReader openReader();

    AudioStreamWriter openWriter();

    void readFromParcel(Parcel parcel);

    void recycle();

    @Override // android.os.Parcelable
    void writeToParcel(Parcel parcel, int i);
}
