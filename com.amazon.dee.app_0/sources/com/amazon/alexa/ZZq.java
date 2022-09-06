package com.amazon.alexa;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.amazon.alexa.api.AlexaDataSink;
import com.amazon.alexa.client.alexaservice.audioprovider.AlexaAudioSource;
import java.io.IOException;
import java.io.InputStream;
/* compiled from: AlexaDataSource.java */
/* loaded from: classes.dex */
public class ZZq extends AlexaDataSink {
    public static final String zZm = AlexaAudioSource.class.getSimpleName();
    public final InputStream BIo;

    public ZZq(AlexaDataSink alexaDataSink) {
        super(alexaDataSink);
        this.BIo = new ParcelFileDescriptor.AutoCloseInputStream(this.readDescriptor);
    }

    @Override // com.amazon.alexa.api.AlexaDataSink
    public void abandon() {
        super.abandon();
        try {
            if (this.BIo == null) {
                return;
            }
            this.BIo.close();
        } catch (IOException e) {
            Log.e(zZm, "Could not abandon input stream.", e);
        }
    }

    @Override // com.amazon.alexa.api.AlexaDataSink, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            if (this.BIo == null) {
                return;
            }
            this.BIo.close();
        } catch (IOException e) {
            Log.e(zZm, "Could not close input stream.", e);
        }
    }
}
