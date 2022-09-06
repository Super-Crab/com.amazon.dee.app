package amazon.speech.io;

import amazon.speech.audio.AudioMetadata;
import amazon.speech.audio.AudioStream;
import amazon.speech.audio.AudioStreamMajorVersion;
import amazon.speech.audio.AudioStreamReader;
import amazon.speech.audio.AudioStreamWriter;
import amazon.speech.audio.IAudioStream;
import amazon.speech.io.DataStream;
import android.media.AudioFormat;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.InputStream;
import java.io.OutputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AudioStreamDataStream extends DataStream {
    public static final Parcelable.Creator<AudioStreamDataStream> CREATOR = new Parcelable.Creator<AudioStreamDataStream>() { // from class: amazon.speech.io.AudioStreamDataStream.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AudioStreamDataStream mo19createFromParcel(Parcel parcel) {
            return new AudioStreamDataStream(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AudioStreamDataStream[] mo20newArray(int i) {
            return new AudioStreamDataStream[i];
        }
    };
    private final IAudioStream mStream;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioStreamDataStream(String str, int i, int i2, AudioFormat audioFormat) {
        if (i > 0) {
            this.mStream = AudioStreamMajorVersion.createAudioStreamFactory().create(new AudioStream.AudioStreamParams().mo15setName(str == null ? "DataStream" : str).setFormat(audioFormat == null ? new AudioFormat.Builder().setEncoding(3).setChannelMask(16).build() : audioFormat).mo14setFrameCount(i).mo16setPiped(i2));
            return;
        }
        throw new IllegalArgumentException("buffersize must be postive and greater than zero.");
    }

    @Override // amazon.speech.io.DataStream
    public InputStream createInputStream() {
        return openReader();
    }

    @Override // amazon.speech.io.DataStream
    public OutputStream createOutputStream() {
        return openWriter();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // amazon.speech.io.DataStream
    public DataStream.Metadata getMetadata() {
        return new AudioMetadata() { // from class: amazon.speech.io.AudioStreamDataStream.1
            @Override // amazon.speech.audio.AudioMetadata
            public int getChannelCount() {
                return AudioStreamDataStream.this.mStream.getChannelCount();
            }

            @Override // amazon.speech.audio.AudioMetadata
            public int getEncoding() {
                return AudioStreamDataStream.this.mStream.getEncoding();
            }

            @Override // amazon.speech.audio.AudioMetadata
            public int getSampleRate() {
                return AudioStreamDataStream.this.mStream.getSampleRate();
            }
        };
    }

    @Override // amazon.speech.io.DataStream
    public DataStreamReader openReader() {
        AudioStreamReader openReader = this.mStream.openReader();
        if (openReader != null) {
            openReader.setOverrunExceptionsEnabled(true);
            return openReader;
        }
        throw new IllegalStateException("Could not get InputStream.");
    }

    @Override // amazon.speech.io.DataStream
    public DataStreamWriter openWriter() {
        AudioStreamWriter openWriter = this.mStream.openWriter();
        if (openWriter != null) {
            return openWriter;
        }
        throw new IllegalStateException("Could not get InputStream.");
    }

    @Override // amazon.speech.io.DataStream
    public void recycle() {
        this.mStream.recycle();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(AudioStreamDataStream.class.getName());
        this.mStream.writeToParcel(parcel, i);
    }

    AudioStreamDataStream(Parcel parcel) {
        this.mStream = AudioStreamMajorVersion.createAudioStreamFactory().createFromParcel(parcel);
    }
}
