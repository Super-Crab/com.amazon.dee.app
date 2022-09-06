package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.amazon.alexa.utils.validation.Assertions;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
/* compiled from: SpeechmarkParsingPipedOutputStream.java */
/* loaded from: classes.dex */
public class VRD extends PipedOutputStream implements Runnable, xDc {
    public static final String zZm = "VRD";
    public final ExecutorService BIo;
    public final ArrayList<Byte> JTe;
    public boolean LPk;
    public boolean Mlj;
    public final Object Qle;
    public final Object jiA;
    public int lOf;
    public boolean yPL;
    public final Vpd zQM;
    public final cIy zyO;
    public int zzR;

    public VRD(cIy ciy, Vpd vpd) {
        StringBuilder zZm2 = C0179Pya.zZm("wakeword-output-stream");
        zZm2.append(ciy.getValue());
        ScheduledExecutorService newSingleThreadScheduledExecutor = ManagedExecutorFactory.newSingleThreadScheduledExecutor(zZm2.toString());
        this.jiA = new Object();
        this.Qle = new Object();
        this.JTe = new ArrayList<>(100);
        this.LPk = false;
        this.yPL = false;
        this.Mlj = false;
        this.zzR = 0;
        this.lOf = -1;
        this.zyO = ciy;
        this.BIo = newSingleThreadScheduledExecutor;
        this.zQM = vpd;
    }

    public boolean BIo() throws IOException {
        boolean z;
        synchronized (this.jiA) {
            if (this.yPL && this.JTe.size() - this.zzR <= 0) {
                z = false;
            }
            z = true;
        }
        return z;
    }

    @Override // java.io.PipedOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.jiA) {
            this.yPL = true;
        }
        synchronized (this.Qle) {
            if (!this.LPk) {
                zZm();
            }
        }
    }

    public void jiA() throws IOException {
        synchronized (this.jiA) {
            if (this.lOf != -1) {
                this.zzR = this.lOf;
                this.lOf = -1;
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.Qle) {
            try {
                ((DtB) this.zQM).zZm(this, this.zyO);
            } catch (IOException e) {
                Log.w(zZm, e.getMessage());
            }
        }
    }

    @Override // java.io.PipedOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        synchronized (this.jiA) {
            if (this.yPL) {
                Log.w(zZm, "Pipe is closed. Unable to write any more data");
                return;
            }
            zyO();
            this.JTe.add(Byte.valueOf((byte) i));
        }
    }

    public void zQM() {
        synchronized (this.jiA) {
            this.lOf = this.zzR;
        }
    }

    public int zZm(byte[] bArr, int i) throws IOException {
        int BIo = BIo(bArr, i);
        synchronized (this.jiA) {
            if (this.yPL && this.zzR >= this.JTe.size()) {
                zZm();
            }
        }
        return BIo;
    }

    public final void zyO() {
        if (!this.LPk) {
            this.LPk = true;
            try {
                this.BIo.execute(this);
            } catch (RejectedExecutionException e) {
                Log.w(zZm, e.getMessage());
            }
        }
    }

    public final int BIo(byte[] bArr, int i) throws IOException {
        int i2;
        int i3 = 0;
        while (i3 < i) {
            synchronized (this.jiA) {
                int i4 = i - i3;
                int size = this.JTe.size() - this.zzR;
                Assertions.isFalse(size < 0, "remainingBytes should always be positive");
                int min = Math.min(size, i4);
                i2 = i3;
                int i5 = 0;
                while (i5 < min) {
                    ArrayList<Byte> arrayList = this.JTe;
                    int i6 = this.zzR;
                    this.zzR = i6 + 1;
                    bArr[i2] = arrayList.get(i6).byteValue();
                    i5++;
                    i2++;
                }
                if (this.yPL && i2 < i && this.zzR == this.JTe.size()) {
                    zZm();
                    throw new IOException("Stream is closed and no more data is available");
                }
            }
            i3 = i2;
        }
        return i3;
    }

    public long zZm(int i) throws IOException {
        byte[] bArr = new byte[i];
        int BIo = BIo(bArr, i);
        super.write(bArr, 0, BIo);
        synchronized (this.jiA) {
            if (this.yPL && this.zzR >= this.JTe.size()) {
                zZm();
            }
        }
        return BIo;
    }

    @Override // java.io.PipedOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        synchronized (this.jiA) {
            if (this.yPL) {
                Log.w(zZm, "Pipe is closed. Unable to write any more data");
                return;
            }
            zyO();
            for (int i3 = i; i3 < i + i2; i3++) {
                this.JTe.add(Byte.valueOf(bArr[i3]));
            }
        }
    }

    public final void zZm() throws IOException {
        if (!this.Mlj) {
            super.close();
            this.Mlj = true;
            this.JTe.clear();
            this.BIo.shutdown();
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        synchronized (this.jiA) {
            if (this.yPL) {
                Log.w(zZm, "Pipe is closed. Unable to write any more data");
                return;
            }
            zyO();
            for (byte b : bArr) {
                this.JTe.add(Byte.valueOf(b));
            }
        }
    }
}
