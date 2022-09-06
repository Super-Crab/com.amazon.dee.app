package com.amazon.alexa;

import android.util.Log;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* compiled from: WakeWordAudioCapturer.java */
/* loaded from: classes.dex */
public class nzu {
    public static final String zZm = "nzu";
    public final InputStream BIo;
    public boolean JTe = true;
    public volatile boolean LPk = true;
    public int Qle;
    public final byte[] jiA;
    public final OutputStream zQM;
    public final int zyO;

    public nzu(InputStream inputStream, OutputStream outputStream, int i) {
        this.BIo = inputStream;
        this.zQM = outputStream;
        this.zyO = i * 2;
        this.jiA = new byte[this.zyO];
    }

    public void BIo() {
        Log.i(zZm, "release");
        try {
            this.BIo.close();
            this.zQM.close();
        } catch (IOException e) {
            Log.e(zZm, "Failed to close input stream", e);
        }
    }

    public void zQM() {
        Log.i(zZm, "wake word: stopProcessing");
        this.LPk = false;
    }

    public short[] zZm() throws IOException {
        zyO();
        int i = this.Qle;
        if (i == this.zyO) {
            ByteBuffer wrap = ByteBuffer.wrap(this.jiA);
            wrap.order(ByteOrder.LITTLE_ENDIAN);
            short[] sArr = new short[wrap.limit() / 2];
            wrap.asShortBuffer().get(sArr);
            return sArr;
        } else if (i == -1) {
            throw new EOFException("Stream closed early");
        } else {
            StringBuilder zZm2 = C0179Pya.zZm("Buffer underrun -- wanted ");
            zZm2.append(this.zyO);
            zZm2.append(" samples, but got ");
            zZm2.append(this.Qle);
            throw new IOException(zZm2.toString());
        }
    }

    public void zyO() throws IOException {
        boolean z = false;
        this.Qle = this.BIo.read(this.jiA, 0, this.zyO);
        if (this.Qle > 0 && this.LPk) {
            this.zQM.write(this.jiA, 0, this.Qle);
        }
        if (this.Qle == this.zyO) {
            z = true;
        }
        this.JTe = z;
    }
}
