package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.opus.OpusEncoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* compiled from: AudioFormatConvertingAttachment.java */
/* loaded from: classes.dex */
public class GLA extends Aml {
    public static final String zZm = "GLA";
    public final wul BIo;
    public final Aml zQM;

    public GLA(Aml aml) {
        super(aml.getAttachmentIdentifier());
        this.zQM = aml;
        this.BIo = new wul(this.zQM.getInputStream());
    }

    @Override // com.amazon.alexa.Aml
    public void close() {
        try {
            wul wulVar = this.BIo;
            wulVar.LPk = false;
            wulVar.Qle.shutdown();
            wulVar.jiA.close();
            wulVar.zQM.close();
            OpusEncoder opusEncoder = wulVar.JTe;
            if (opusEncoder != null) {
                opusEncoder.close();
            }
            this.zQM.close();
        } catch (IOException unused) {
            Log.e(zZm, "IOException - Stream not closed");
        }
    }

    @Override // com.amazon.alexa.Aml
    public ZVp getDataFormat() {
        ZVp zVp = ZVp.OPUS32;
        zVp.zZm(true);
        return zVp;
    }

    @Override // com.amazon.alexa.Aml
    public InputStream getInputStream() {
        return this.BIo;
    }

    @Override // com.amazon.alexa.Aml
    @Nullable
    public OutputStream getOutputStream() {
        try {
            return this.zQM.getOutputStream();
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }

    @Override // com.amazon.alexa.Aml
    public InputStream getRetryInputStream() {
        return null;
    }

    @Override // com.amazon.alexa.Aml
    public boolean isClosed() {
        return this.zQM.isClosed();
    }
}
