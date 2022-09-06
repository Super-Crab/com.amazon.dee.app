package com.amazon.alexa.handsfree.audio;

import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes8.dex */
public class ReadableAlexaAudioSink extends AlexaAudioSink {
    private static final String TAG = ReadableAlexaAudioSink.class.getSimpleName();
    private final InputStream inputStream;

    public ReadableAlexaAudioSink() throws IOException {
        this.inputStream = createInputStream();
    }

    @Override // com.amazon.alexa.api.AlexaDataSink
    public synchronized void abandon() {
        super.abandon();
        try {
            if (this.inputStream != null) {
                this.inputStream.close();
            }
        } catch (IOException e) {
            Log.e(TAG, "failed to abandon input stream.", e, new Object[0]);
        }
    }

    @Override // com.amazon.alexa.api.AlexaDataSink, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        super.close();
        try {
            if (this.inputStream != null) {
                this.inputStream.close();
            }
        } catch (IOException e) {
            Log.e(TAG, "failed to close input stream.", e, new Object[0]);
        }
    }

    @VisibleForTesting
    InputStream createInputStream() {
        return new ParcelFileDescriptor.AutoCloseInputStream(this.readDescriptor);
    }

    public InputStream openForReading() {
        return this.inputStream;
    }

    public ReadableAlexaAudioSink(@NonNull ParcelFileDescriptor parcelFileDescriptor) {
        super(parcelFileDescriptor);
        this.inputStream = createInputStream();
    }
}
