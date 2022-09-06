package amazon.speech.audio.v2;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class AudioStreamReader extends amazon.speech.audio.AudioStreamReader implements Parcelable {
    private long mNativeInstance = 0;
    private boolean mRemoteObject = false;
    private static final String TAG = AudioStreamReader.class.getSimpleName();
    public static final Parcelable.Creator<AudioStreamReader> CREATOR = new Parcelable.Creator<AudioStreamReader>() { // from class: amazon.speech.audio.v2.AudioStreamReader.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AudioStreamReader mo17createFromParcel(Parcel parcel) {
            if (parcel != null) {
                int i = AudioStream.READERS_MAX;
                AudioStreamReader audioStreamReader = new AudioStreamReader();
                audioStreamReader.readFromParcel(parcel);
                return audioStreamReader;
            }
            throw new IllegalArgumentException();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AudioStreamReader[] mo18newArray(int i) {
            return new AudioStreamReader[i];
        }
    };

    private native int nAvailable();

    private native void nClose();

    private native long nGetPosition();

    private native void nInterrupt();

    private native int nReadByteArray(byte[] bArr, int i, int i2);

    private native void nReadFromParcel(Parcel parcel);

    private native long nSetPosition(long j);

    private native long nSynchronize();

    private native void nWriteToParcel(Parcel parcel);

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // amazon.speech.audio.AudioStreamReader
    protected long getNativeInstance() {
        return this.mNativeInstance;
    }

    @Override // amazon.speech.audio.AudioStreamReader
    protected boolean isRemoteObject() {
        return this.mRemoteObject;
    }

    @Override // amazon.speech.audio.AudioStreamReader
    protected int jniAvailable() {
        return nAvailable();
    }

    @Override // amazon.speech.audio.AudioStreamReader
    protected void jniClose() {
        nClose();
    }

    @Override // amazon.speech.audio.AudioStreamReader
    protected long jniGetPosition() {
        return nGetPosition();
    }

    @Override // amazon.speech.audio.AudioStreamReader
    protected void jniInterrupt() {
        nInterrupt();
    }

    @Override // amazon.speech.audio.AudioStreamReader
    protected int jniReadByteArray(byte[] bArr, int i, int i2) {
        return nReadByteArray(bArr, i, i2);
    }

    @Override // amazon.speech.audio.AudioStreamReader
    protected long jniSetPosition(long j) {
        return nSetPosition(j);
    }

    @Override // amazon.speech.audio.AudioStreamReader
    protected long jniSynchronize() {
        return nSynchronize();
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel != null) {
            nReadFromParcel(parcel);
            this.mRemoteObject = true;
            return;
        }
        throw new IllegalArgumentException("source must not be null");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            nWriteToParcel(parcel);
            return;
        }
        throw new IllegalArgumentException("dest must not be null");
    }
}
