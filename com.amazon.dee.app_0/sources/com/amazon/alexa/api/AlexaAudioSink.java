package com.amazon.alexa.api;

import android.os.ParcelFileDescriptor;
import java.io.IOException;
/* loaded from: classes6.dex */
public class AlexaAudioSink extends AlexaDataSink {
    private static final String TAG = AlexaAudioSink.class.getSimpleName();

    public AlexaAudioSink() throws IOException {
        setTag(TAG);
    }

    public AlexaAudioSink(ParcelFileDescriptor parcelFileDescriptor) {
        this(parcelFileDescriptor, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AlexaAudioSink(ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2) {
        super(parcelFileDescriptor, parcelFileDescriptor2);
        setTag(TAG);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AlexaAudioSink(AlexaAudioSink alexaAudioSink) {
        this(alexaAudioSink.readDescriptor, alexaAudioSink.writeDescriptor);
    }
}
