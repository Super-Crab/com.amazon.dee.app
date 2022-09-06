package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.Trace;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.opus.OpusEncoder;
import com.amazon.opus.OpusError;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ExecutorService;
/* compiled from: OpusEncodingInputStream.java */
/* loaded from: classes.dex */
public class wul extends InputStream {
    public static final ZVp BIo = ZVp.LPCM16;
    public static final String zZm = "wul";
    public OpusEncoder JTe;
    public volatile boolean LPk;
    public final ExecutorService Qle;
    public final PipedInputStream jiA;
    public final InputStream zQM;
    public final PipedOutputStream zyO;

    /* compiled from: OpusEncodingInputStream.java */
    /* loaded from: classes.dex */
    private class zZm implements Runnable {
        public /* synthetic */ zZm(nmd nmdVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.i(wul.zZm, "Beginning Opus encoding streams");
            try {
                try {
                    byte[] bArr = new byte[640];
                    byte[] bArr2 = new byte[80];
                    int i = 0;
                    int i2 = 0;
                    while (wul.this.LPk) {
                        int i3 = 0;
                        while (true) {
                            if (i3 < 640) {
                                int read = wul.this.zQM.read();
                                if (read != -1) {
                                    bArr[i3] = (byte) read;
                                    i3++;
                                } else {
                                    String str = wul.zZm;
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        String str2 = wul.zZm;
                        StringBuilder sb = new StringBuilder();
                        sb.append(" Raw bytes read in current run : ");
                        sb.append(i3);
                        sb.toString();
                        i += i3;
                        String str3 = wul.zZm;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Total bytes read so far: ");
                        sb2.append(i);
                        sb2.toString();
                        if (i3 > 0) {
                            String str4 = wul.zZm;
                            int encode = wul.this.zZm().encode(bArr, DeviceConfigConstants.VIDEO_WIDTH_320, bArr2);
                            wul.this.zyO.write(bArr2);
                            i2 += encode;
                        } else {
                            String str5 = wul.zZm;
                        }
                    }
                    String str6 = wul.zZm;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("totalRawBytesRead ");
                    sb3.append(i);
                    sb3.toString();
                    String str7 = wul.zZm;
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("totalEncodedBytesRead ");
                    sb4.append(i2);
                    sb4.toString();
                    try {
                        wul.this.zyO.close();
                        wul.this.zQM.close();
                    } catch (IOException unused) {
                        Log.e(wul.zZm, "Failed to close streams");
                    }
                } catch (OpusError e) {
                    Log.e(wul.zZm, "Encountered an error during Opus encoding", e);
                    try {
                        wul.this.zyO.close();
                        wul.this.zQM.close();
                    } catch (IOException unused2) {
                        Log.e(wul.zZm, "Failed to close streams");
                    }
                }
                Log.i(wul.zZm, "PcmRecorder released");
                Log.i(wul.zZm, "Opus-encoding the stream is ending");
            }
        }
    }

    public wul(InputStream inputStream) {
        ExecutorService newSingleThreadExecutor = ManagedExecutorFactory.newSingleThreadExecutor("opus-encoder");
        this.LPk = true;
        this.zQM = inputStream;
        this.zyO = new PipedOutputStream();
        this.jiA = new PipedInputStream(48000);
        this.Qle = newSingleThreadExecutor;
        try {
            this.zyO.connect(this.jiA);
        } catch (IOException e) {
            Log.e(zZm, "Failed to connect stream", e);
        }
        newSingleThreadExecutor.submit(new zZm(null));
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.LPk = false;
        this.Qle.shutdown();
        this.jiA.close();
        this.zQM.close();
        OpusEncoder opusEncoder = this.JTe;
        if (opusEncoder != null) {
            opusEncoder.close();
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.jiA.read();
    }

    public OpusEncoder zZm() {
        if (this.JTe == null) {
            this.JTe = new OpusEncoder();
            this.JTe.init(16000, 1, OpusEncoder.OPUS_APPLICATION_AUDIO);
            this.JTe.setBitrate(Trace.PAYLOAD_DATA_MAX_LENGTH);
            this.JTe.setComplexity(4);
            this.JTe.useVbr(0);
        }
        return this.JTe;
    }
}
