package com.amazon.alexa.handsfree.audio;

import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.io.IOException;
/* loaded from: classes8.dex */
public class AlexaAudioSinkWrapper {
    private static final String TAG = "AlexaAudioSinkWrapper";
    private ReadableAlexaAudioSink mReadableAlexaAudioSink;

    public AlexaAudioSinkWrapper() {
        try {
            this.mReadableAlexaAudioSink = createEmptyReadableAlexaAudioSink();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @VisibleForTesting
    ReadableAlexaAudioSink createEmptyReadableAlexaAudioSink() throws IOException {
        return new ReadableAlexaAudioSink();
    }

    @NonNull
    public ReadableAlexaAudioSink getReadableAlexaAudioSink() {
        return this.mReadableAlexaAudioSink;
    }

    @VisibleForTesting
    boolean isAlexaAudioSinkAvailable() {
        ReadableAlexaAudioSink readableAlexaAudioSink = this.mReadableAlexaAudioSink;
        return readableAlexaAudioSink != null && readableAlexaAudioSink.isWriteable();
    }

    public synchronized void recordAudio(byte[] bArr) {
        recordAudio(bArr, bArr.length);
    }

    public synchronized void reset() {
        Log.i(TAG, "resetting AlexaAudioSink");
        this.mReadableAlexaAudioSink.abandon();
        try {
            this.mReadableAlexaAudioSink = createEmptyReadableAlexaAudioSink();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void recordAudio(byte[] bArr, int i) {
        if (!isAlexaAudioSinkAvailable()) {
            return;
        }
        try {
            this.mReadableAlexaAudioSink.openForWriting().write(bArr, 0, i);
        } catch (IOException e) {
            Log.e(TAG, "Error in writing to AlexaAudioSink", e, new Object[0]);
            this.mReadableAlexaAudioSink.abandon();
        }
    }

    public AlexaAudioSinkWrapper(@NonNull ParcelFileDescriptor parcelFileDescriptor) {
        this.mReadableAlexaAudioSink = new ReadableAlexaAudioSink(parcelFileDescriptor);
    }

    @VisibleForTesting
    AlexaAudioSinkWrapper(@NonNull ReadableAlexaAudioSink readableAlexaAudioSink) {
        this.mReadableAlexaAudioSink = readableAlexaAudioSink;
    }
}
