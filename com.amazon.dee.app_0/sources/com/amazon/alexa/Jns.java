package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.audio.ScaledVolumeProcessor;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* compiled from: VolumeCalculatingAttachment.java */
/* loaded from: classes.dex */
public class Jns extends Aml {
    public static final String zZm = "Jns";
    public final InputStream BIo;
    public final Aml zQM;
    public boolean zyO;

    public Jns(AlexaClientEventBus alexaClientEventBus, ScaledVolumeProcessor scaledVolumeProcessor, Aml aml) {
        super(aml.getAttachmentIdentifier());
        StringBuilder zZm2 = C0179Pya.zZm("Create attachment: ");
        zZm2.append(this.attachmentID);
        zZm2.toString();
        this.zQM = aml;
        this.BIo = new HSA(this.zQM.getInputStream(), alexaClientEventBus, scaledVolumeProcessor);
    }

    @Override // com.amazon.alexa.Aml
    public void close() {
        this.zyO = true;
        try {
            this.BIo.close();
            this.zQM.close();
        } catch (IOException unused) {
            Log.e(zZm, "IOException - Stream not closed");
        }
    }

    @Override // com.amazon.alexa.Aml
    public ZVp getDataFormat() {
        return this.zQM.getDataFormat();
    }

    @Override // com.amazon.alexa.Aml
    public InputStream getInputStream() {
        return this.BIo;
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
        return this.zyO;
    }
}
