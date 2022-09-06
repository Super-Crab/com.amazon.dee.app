package com.amazon.alexa;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
/* compiled from: SpeechmarkParsingAttachment.java */
/* loaded from: classes.dex */
public class kBv extends Aml {
    public static final String zZm = "kBv";
    public final VRD BIo;
    public final PipedInputStream zQM;
    public volatile boolean zyO;

    public kBv(cIy ciy, DtB dtB) {
        super(ciy);
        this.BIo = new VRD(ciy, dtB);
        this.zQM = new PipedInputStream(384000);
        try {
            this.BIo.connect(this.zQM);
        } catch (IOException e) {
            Log.e(zZm, e.getMessage());
        }
    }

    @Override // com.amazon.alexa.Aml
    public void close() {
        this.zyO = true;
        try {
            this.zQM.close();
            this.BIo.close();
        } catch (IOException unused) {
            Log.e(zZm, "IOException - Stream not closed");
        }
    }

    @Override // com.amazon.alexa.Aml
    public ZVp getDataFormat() {
        return ZVp.LPCM16;
    }

    @Override // com.amazon.alexa.Aml
    public InputStream getInputStream() {
        return this.zQM;
    }

    @Override // com.amazon.alexa.Aml
    public OutputStream getOutputStream() {
        return this.BIo;
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
