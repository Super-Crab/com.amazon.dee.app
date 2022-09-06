package amazon.speech.audio.v2;

import amazon.speech.audio.AudioMetadata;
import amazon.speech.audio.AudioStream;
import amazon.speech.util.NativeLibHelper;
import android.media.AudioFormat;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
/* loaded from: classes.dex */
public class AudioStream extends amazon.speech.audio.AudioStream {
    private int audioFormatExtendedEncoding;
    private int capacity;
    private long mNativeInstance = 0;
    private static final String TAG = AudioStream.class.getSimpleName();
    public static int READERS_MAX = 16;
    public static int WRITERS_MAX = 1;
    public static final Parcelable.Creator<AudioStream> CREATOR = new Parcelable.Creator<AudioStream>() { // from class: amazon.speech.audio.v2.AudioStream.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AudioStream mo12createFromParcel(Parcel parcel) {
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
        public AudioStream[] mo13newArray(int i) {
            return new AudioStream[i];
        }
    };

    /* loaded from: classes.dex */
    public static class AudioStreamParamsExtended extends AudioStream.AudioStreamParams {
        public int channelMask;
        public int encodingExtended;
        public int sampleRate;

        public AudioStreamParamsExtended setChannelMask(int i) {
            this.channelMask = i;
            return this;
        }

        public AudioStreamParamsExtended setEncodingExtended(int i) {
            this.encodingExtended = i;
            return this;
        }

        public AudioStreamParamsExtended setSampleRate(int i) {
            this.sampleRate = i;
            return this;
        }

        public void validate() {
            if (!TextUtils.isEmpty(this.name)) {
                if (this.sampleRate > 0) {
                    if (this.channelMask > 0) {
                        int i = this.encodingExtended;
                        if (i != 1000 && i != 1001) {
                            throw new IllegalArgumentException("Extended encoding not supported");
                        }
                        if (this.frameCount > 0) {
                            int i2 = this.numPipes;
                            if (i2 >= 0 && i2 < AudioStream.READERS_MAX) {
                                return;
                            }
                            throw new IllegalArgumentException("Number of reserved pipes must be in valid range.");
                        }
                        throw new IllegalArgumentException("frameCount is less than or equal to zero.");
                    }
                    throw new IllegalArgumentException("channelMask is less than or equal to zero");
                }
                throw new IllegalArgumentException("sampleRate is less than or equal to zero.");
            }
            throw new IllegalArgumentException("name is empty or invalid.");
        }

        @Override // amazon.speech.audio.AudioStream.AudioStreamParams
        /* renamed from: setFrameCount  reason: collision with other method in class */
        public AudioStreamParamsExtended mo14setFrameCount(int i) {
            this.frameCount = i;
            return this;
        }

        @Override // amazon.speech.audio.AudioStream.AudioStreamParams
        /* renamed from: setName  reason: collision with other method in class */
        public AudioStreamParamsExtended mo15setName(String str) {
            this.name = str;
            return this;
        }

        @Override // amazon.speech.audio.AudioStream.AudioStreamParams
        /* renamed from: setPiped  reason: collision with other method in class */
        public AudioStreamParamsExtended mo16setPiped(int i) {
            this.numPipes = i;
            return this;
        }
    }

    static {
        try {
            NativeLibHelper.loadLibrary("audiostream_jni_v2_fireos");
        } catch (UnsatisfiedLinkError unused) {
            NativeLibHelper.loadLibrary("audiostream_jni");
        }
    }

    public static AudioStream create(String str, AudioFormat audioFormat, int i) {
        if (str != null && audioFormat != null && i >= 0) {
            return create(new AudioStream.AudioStreamParams().mo15setName(str).setFormat(audioFormat).mo14setFrameCount(i));
        }
        throw new IllegalArgumentException();
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

    private void setCapacity(int i) {
        this.capacity = i;
    }

    private void setEncodingExtended(int i) {
        this.audioFormatExtendedEncoding = i;
    }

    @Override // amazon.speech.audio.AudioStream, amazon.speech.audio.IAudioStream, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // amazon.speech.audio.AudioStream
    protected void finalize() throws Throwable {
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

    public int getCapacity() {
        return this.capacity;
    }

    @Override // amazon.speech.audio.AudioStream, amazon.speech.audio.IAudioStream
    public int getChannelCount() {
        return nGetChannelCount();
    }

    @Override // amazon.speech.audio.AudioStream, amazon.speech.audio.IAudioStream
    public int getEncoding() {
        int i = this.audioFormatExtendedEncoding;
        return i != 0 ? i : nGetEncoding();
    }

    @Override // amazon.speech.audio.AudioStream, amazon.speech.audio.IAudioStream
    public int getSampleRate() {
        return nGetSampleRate();
    }

    @Override // amazon.speech.audio.AudioStream, amazon.speech.audio.IAudioStream
    public amazon.speech.audio.AudioStreamReader openReader() {
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

    @Override // amazon.speech.audio.AudioStream, amazon.speech.audio.IAudioStream
    public amazon.speech.audio.AudioStreamWriter openWriter() {
        String str = TAG;
        Log.i(str, "openWriter ENTER " + this);
        Trace.beginSection("AudioStream_openWriter");
        try {
            if (this.mNativeInstance != 0) {
                AudioStreamWriter audioStreamWriter = new AudioStreamWriter();
                if (nOpenWriter(audioStreamWriter) != 0) {
                    String str2 = TAG;
                    Log.i(str2, "openWriter EXIT " + this + " writer: " + audioStreamWriter);
                    return audioStreamWriter;
                }
                throw new IllegalStateException("Could not open stream for writing.");
            }
            throw new IllegalStateException("AudioStream has been recycled");
        } finally {
            Trace.endSection();
        }
    }

    @Override // amazon.speech.audio.AudioStream, amazon.speech.audio.IAudioStream
    public void readFromParcel(Parcel parcel) {
        if (parcel != null) {
            nReadFromParcel(parcel);
            return;
        }
        throw new IllegalArgumentException("source must not be null");
    }

    @Override // amazon.speech.audio.AudioStream, amazon.speech.audio.IAudioStream
    public void recycle() {
        String str = TAG;
        Log.i(str, "recycle ENTER " + this);
        nRelease();
    }

    @Override // amazon.speech.audio.AudioStream, amazon.speech.audio.IAudioStream, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            nWriteToParcel(parcel);
            return;
        }
        throw new IllegalArgumentException("dest must not be null");
    }

    public static AudioStream create(AudioStream.AudioStreamParams audioStreamParams) {
        AudioStream.AudioStreamParams.validate(audioStreamParams);
        AudioStream audioStream = new AudioStream();
        int bitCount = Integer.bitCount(audioStreamParams.format.getChannelMask());
        audioStream.setCapacity(audioStreamParams.frameCount);
        if (audioStream.nCreate(audioStreamParams.name, audioStreamParams.format.getEncoding(), audioStreamParams.format.getSampleRate(), bitCount, audioStreamParams.frameCount, audioStreamParams.numPipes) == 0) {
            Log.e(TAG, "Error creating audio stream.");
            return null;
        }
        return audioStream;
    }

    public static AudioStream create(AudioStreamParamsExtended audioStreamParamsExtended) {
        if (audioStreamParamsExtended != null) {
            audioStreamParamsExtended.validate();
            AudioStream audioStream = new AudioStream();
            int bitCount = Integer.bitCount(audioStreamParamsExtended.channelMask);
            audioStream.setCapacity(audioStreamParamsExtended.frameCount);
            audioStream.setEncodingExtended(audioStreamParamsExtended.encodingExtended);
            if (audioStream.nCreate(audioStreamParamsExtended.name, audioStreamParamsExtended.encodingExtended, audioStreamParamsExtended.sampleRate, bitCount, audioStreamParamsExtended.frameCount, audioStreamParamsExtended.numPipes) != 0) {
                return audioStream;
            }
            Log.e(TAG, "Error creating audio stream.");
            return null;
        }
        throw new IllegalArgumentException("Audio Stream Params Extended is null");
    }
}
