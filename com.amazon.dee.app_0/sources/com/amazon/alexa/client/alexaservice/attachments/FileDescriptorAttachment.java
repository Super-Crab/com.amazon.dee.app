package com.amazon.alexa.client.alexaservice.attachments;

import android.util.Log;
import com.amazon.alexa.Aml;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.ZVp;
import com.amazon.alexa.ZZq;
import com.amazon.alexa.cIy;
import com.amazon.alexa.client.alexaservice.audioprovider.AlexaAudioSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes6.dex */
public class FileDescriptorAttachment extends Aml {
    public static final String TAG = "FileDescriptorAttachment";
    public volatile boolean closed;
    public final ZVp dataFormat;
    public final InputStream inputStream;

    public FileDescriptorAttachment(AlexaAudioSource alexaAudioSource) {
        super(cIy.zZm());
        StringBuilder zZm = C0179Pya.zZm("Create attachment: ");
        zZm.append(getAttachmentIdentifier());
        zZm.toString();
        this.inputStream = alexaAudioSource.openForReading();
        this.dataFormat = ZVp.zZm(alexaAudioSource.getAudioFormat());
    }

    @Override // com.amazon.alexa.Aml
    public void close() {
        this.closed = true;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Close attachment: ");
            sb.append(getAttachmentIdentifier());
            sb.toString();
            this.inputStream.close();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @Override // com.amazon.alexa.Aml
    public ZVp getDataFormat() {
        return this.dataFormat;
    }

    @Override // com.amazon.alexa.Aml
    public InputStream getInputStream() {
        return this.inputStream;
    }

    @Override // com.amazon.alexa.Aml
    public OutputStream getOutputStream() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.alexa.Aml
    public InputStream getRetryInputStream() {
        return null;
    }

    @Override // com.amazon.alexa.Aml
    public boolean isClosed() {
        return this.closed;
    }

    public FileDescriptorAttachment(ZZq zZq) {
        super(cIy.zZm());
        StringBuilder zZm = C0179Pya.zZm("Create attachment: ");
        zZm.append(getAttachmentIdentifier());
        zZm.toString();
        this.inputStream = zZq.BIo;
        this.dataFormat = ZVp.BINARY;
    }
}
