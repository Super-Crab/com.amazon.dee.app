package amazon.speech.audio;

import amazon.speech.util.NativeLibHelper;
import android.media.AudioFormat;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
/* loaded from: classes.dex */
public class AudioStream implements IAudioStream {
    private long mNativeInstance = 0;
    private static final String TAG = AudioStream.class.getSimpleName();
    public static int READERS_MAX = 16;
    public static int WRITERS_MAX = 1;
    public static final Parcelable.Creator<AudioStream> CREATOR = new Parcelable.Creator<AudioStream>() { // from class: amazon.speech.audio.AudioStream.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AudioStream mo9createFromParcel(Parcel parcel) {
            if (parcel != null) {
                AudioStream audioStream = new AudioStream();
                audioStream.readFromParcel(parcel);
                return audioStream;
            }
            throw new IllegalArgumentException();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AudioStream[] mo10newArray(int i) {
            return new AudioStream[i];
        }
    };

    /* loaded from: classes.dex */
    public static class AudioStreamParams {
        public String name = null;
        public AudioFormat format = null;
        public int frameCount = 0;
        public int numPipes = 0;

        public static void validate(AudioStreamParams audioStreamParams) {
            if (audioStreamParams != null) {
                if (!TextUtils.isEmpty(audioStreamParams.name)) {
                    if (audioStreamParams.format != null) {
                        if (audioStreamParams.frameCount > 0) {
                            int i = audioStreamParams.numPipes;
                            if (i >= 0 && i < amazon.speech.audio.v2.AudioStream.READERS_MAX) {
                                return;
                            }
                            throw new IllegalArgumentException("Number of reserved pipes must be in valid range.");
                        }
                        throw new IllegalArgumentException("frameCount is zestan or equal to zero.");
                    }
                    throw new IllegalArgumentException("format is null.");
                }
                throw new IllegalArgumentException("name is empty or invalid.");
            }
            throw new IllegalArgumentException("params is null.");
        }

        public AudioStreamParams setFormat(AudioFormat audioFormat) {
            this.format = audioFormat;
            return this;
        }

        /* renamed from: setFrameCount */
        public AudioStreamParams mo14setFrameCount(int i) {
            this.frameCount = i;
            return this;
        }

        /* renamed from: setName */
        public AudioStreamParams mo15setName(String str) {
            this.name = str;
            return this;
        }

        /* renamed from: setPiped */
        public AudioStreamParams mo16setPiped(int i) {
            this.numPipes = i;
            return this;
        }
    }

    static {
        try {
            NativeLibHelper.loadLibrary("audiostream_jni");
        } catch (UnsatisfiedLinkError unused) {
            NativeLibHelper.loadLibrary("audiostream_jni_v2_fireos");
        }
    }

    public static AudioStream create(String str, AudioFormat audioFormat, int i) {
        if (str != null && audioFormat != null && i >= 0) {
            return create(new AudioStreamParams().mo15setName(str).setFormat(audioFormat).mo14setFrameCount(i));
        }
        throw new IllegalArgumentException("Null/Invalid argument name " + str + " format " + audioFormat + " frameCount " + i);
    }

    @Deprecated
    public static int getBytesPerSampleForEncoding(int i) {
        return AudioMetadata.getBytesPerSampleForEncoding(i);
    }

    @Deprecated
    public static int getChannelCountForMask(int i) {
        return AudioMetadata.getChannelCountForMask(i);
    }

    private native long nCreate(String str, int i, int i2, int i3, int i4, int i5);

    private native int nGetChannelCount();

    private native int nGetEncoding();

    private native int nGetSampleRate();

    private native long nOpenReader(AudioStreamReader audioStreamReader);

    private native long nOpenWriter(AudioStreamWriter audioStreamWriter);

    private native void nReadFromParcel(Parcel parcel);

    private native void nRelease();

    private native void nWriteToParcel(Parcel parcel);

    @Override // amazon.speech.audio.IAudioStream, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (this.mNativeInstance != 0) {
                String str = TAG;
                Log.w(str, "recycle() should have been explicitly called on " + this);
                recycle();
            }
        } finally {
            super.finalize();
        }
    }

    @Override // amazon.speech.audio.IAudioStream
    public int getChannelCount() {
        return nGetChannelCount();
    }

    @Override // amazon.speech.audio.IAudioStream
    public int getEncoding() {
        return nGetEncoding();
    }

    @Override // amazon.speech.audio.IAudioStream
    public int getSampleRate() {
        return nGetSampleRate();
    }

    @Override // amazon.speech.audio.IAudioStream
    public AudioStreamReader openReader() {
        Trace.beginSection("AudioStream_openReader");
        try {
            if (this.mNativeInstance != 0) {
                AudioStreamReader audioStreamReader = new AudioStreamReader();
                if (nOpenReader(audioStreamReader) == 0) {
                    throw new IllegalStateException("Could not open stream for reading.");
                }
                return audioStreamReader;
            }
            throw new IllegalStateException("AudioStream has been recycled");
        } finally {
            Trace.endSection();
        }
    }

    @Override // amazon.speech.audio.IAudioStream
    public AudioStreamWriter openWriter() {
        Trace.beginSection("AudioStream_openWriter");
        try {
            if (this.mNativeInstance != 0) {
                AudioStreamWriter audioStreamWriter = new AudioStreamWriter();
                if (nOpenWriter(audioStreamWriter) == 0) {
                    throw new IllegalStateException("Could not open stream for writing.");
                }
                return audioStreamWriter;
            }
            throw new IllegalStateException("AudioStream has been recycled");
        } finally {
            Trace.endSection();
        }
    }

    @Override // amazon.speech.audio.IAudioStream
    public void readFromParcel(Parcel parcel) {
        if (parcel != null) {
            nReadFromParcel(parcel);
            return;
        }
        throw new IllegalArgumentException("source must not be null");
    }

    @Override // amazon.speech.audio.IAudioStream
    public void recycle() {
        nRelease();
    }

    @Override // amazon.speech.audio.IAudioStream, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            nWriteToParcel(parcel);
            return;
        }
        throw new IllegalArgumentException("dest must not be null");
    }

    public static AudioStream create(AudioStreamParams audioStreamParams) {
        AudioStreamParams.validate(audioStreamParams);
        AudioStream audioStream = new AudioStream();
        if (audioStream.nCreate(audioStreamParams.name, audioStreamParams.format.getEncoding(), audioStreamParams.format.getSampleRate(), Integer.bitCount(audioStreamParams.format.getChannelMask()), audioStreamParams.frameCount, audioStreamParams.numPipes) == 0) {
            Log.e(TAG, "Error creating audio stream.");
            return null;
        }
        return audioStream;
    }
}
