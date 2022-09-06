package com.amazon.alexa;

import android.util.Log;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import org.apache.commons.io.output.TeeOutputStream;
/* compiled from: BufferedAttachment.java */
/* loaded from: classes.dex */
public class DvY extends Aml {
    public static final String zZm = "DvY";
    public volatile boolean BIo;
    public PipedInputStream JTe;
    public PipedInputStream LPk;
    public final ZVp Qle;
    public final PipedOutputStream jiA;
    public final TeeOutputStream zQM;
    public final PipedOutputStream zyO;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DvY(cIy ciy) {
        super(ciy);
        ZVp zVp = ZVp.LPCM16;
        this.zyO = new PipedOutputStream();
        this.jiA = new PipedOutputStream();
        this.Qle = zVp;
        this.zQM = new TeeOutputStream(this.zyO, this.jiA);
        this.JTe = new PipedInputStream(384000);
        this.LPk = new PipedInputStream(384000);
        try {
            this.zyO.connect(this.JTe);
            this.jiA.connect(this.LPk);
        } catch (IOException e) {
            Log.e(zZm, e.getMessage());
        }
    }

    @Override // com.amazon.alexa.Aml
    public void close() {
        this.BIo = true;
        zZm(this.zQM);
        zZm(this.JTe);
        zZm(this.LPk);
    }

    @Override // com.amazon.alexa.Aml
    public ZVp getDataFormat() {
        return this.Qle;
    }

    @Override // com.amazon.alexa.Aml
    public InputStream getInputStream() {
        return this.JTe;
    }

    @Override // com.amazon.alexa.Aml
    public OutputStream getOutputStream() {
        return this.zQM;
    }

    @Override // com.amazon.alexa.Aml
    public InputStream getRetryInputStream() {
        return this.LPk;
    }

    @Override // com.amazon.alexa.Aml
    public boolean isClosed() {
        return this.BIo;
    }

    public final void zZm(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException unused) {
            Log.e(zZm, "IOException - Stream not closed");
        }
    }
}
