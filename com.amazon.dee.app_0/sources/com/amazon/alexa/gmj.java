package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.TimeProvider;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Timeout;
/* compiled from: AttachmentRequestBody.java */
/* loaded from: classes.dex */
public class gmj extends RequestBody {
    @VisibleForTesting
    public static final MediaType BIo = MediaType.parse("application/octet-stream");
    public static final String zZm = "gmj";
    public final long JTe;
    public final TimeProvider Qle;
    public final ojb jiA;
    public final Aml zQM;
    public final LFH zyO;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AttachmentRequestBody.java */
    /* loaded from: classes.dex */
    public static class BIo extends zZm {
        public BIo(Throwable th) {
            super(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AttachmentRequestBody.java */
    /* loaded from: classes.dex */
    public static class zQM extends zZm {
        public zQM(Throwable th) {
            super(th);
        }
    }

    /* compiled from: AttachmentRequestBody.java */
    /* loaded from: classes.dex */
    static abstract class zZm extends IOException {
        public zZm(Throwable th) {
            super(th);
        }
    }

    public gmj(ojb ojbVar, LFH lfh, Aml aml) {
        long zQM2;
        TimeProvider timeProvider = new TimeProvider();
        this.zQM = aml;
        this.zyO = lfh;
        this.jiA = ojbVar;
        this.Qle = timeProvider;
        Long valueOf = Long.valueOf(ojbVar.zZm());
        ZVp dataFormat = aml.getDataFormat();
        if (valueOf == null) {
            zQM2 = -1;
        } else {
            zQM2 = dataFormat.zQM() * valueOf.longValue();
        }
        this.JTe = zQM2;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return BIo;
    }

    /* JADX WARN: Type inference failed for: r9v0, types: [long] */
    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws zZm {
        int i;
        int i2;
        int i3;
        long j;
        long j2;
        long j3;
        InputStream inputStream;
        long j4;
        Throwable th;
        Timeout mo12584timeout = bufferedSink.mo12584timeout();
        Log.i(zZm, String.format("writeTo with request timeout: %d seconds, write bytes timeout: %d ms", Long.valueOf(((RzL) this.jiA).zZm), Long.valueOf(((RzL) this.jiA).BIo)));
        long elapsedRealTime = this.Qle.elapsedRealTime();
        ?? elapsedRealTime2 = this.Qle.elapsedRealTime();
        long convert = TimeUnit.MILLISECONDS.convert(((RzL) this.jiA).zZm, TimeUnit.SECONDS);
        try {
            try {
                try {
                    InputStream inputStream2 = this.zQM.getInputStream();
                    try {
                        byte[] bArr = new byte[this.zQM.getDataFormat().zyO()];
                        j4 = elapsedRealTime2;
                        int i4 = 0;
                        int i5 = 0;
                        while (!this.zQM.isFinished()) {
                            try {
                                try {
                                    int zZm2 = zZm(inputStream2, i4, bArr);
                                    if (zZm2 < 0) {
                                        break;
                                    }
                                    i5 += zZm2;
                                    inputStream = inputStream2;
                                    long j5 = i5;
                                    try {
                                        long j6 = this.JTe;
                                        if (j6 > 0 && j5 >= j6) {
                                            break;
                                        }
                                        long elapsedRealTime3 = this.Qle.elapsedRealTime();
                                        try {
                                            mo12584timeout.timeout(((RzL) this.jiA).BIo, TimeUnit.MILLISECONDS);
                                            try {
                                                bufferedSink.mo12595write(bArr, 0, zZm2);
                                                try {
                                                    bufferedSink.flush();
                                                    i4 += zZm2;
                                                    mo12584timeout.clearTimeout();
                                                    if (this.Qle.elapsedRealTime() - elapsedRealTime > convert) {
                                                        this.zyO.zZm(i4, elapsedRealTime3);
                                                        if (inputStream != null) {
                                                            inputStream.close();
                                                        }
                                                        Log.i(zZm, "Attachment bytes written: " + i4 + " finalWriteTime: " + elapsedRealTime3);
                                                        mo12584timeout.clearTimeout();
                                                        mo12584timeout.clearDeadline();
                                                        return;
                                                    }
                                                    inputStream2 = inputStream;
                                                    j4 = elapsedRealTime3;
                                                } catch (IOException e) {
                                                    throw new zQM(e);
                                                }
                                            } catch (IOException e2) {
                                                throw new zQM(e2);
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            try {
                                                throw th;
                                            } catch (Throwable th3) {
                                                if (inputStream != null) {
                                                    try {
                                                        inputStream.close();
                                                    } catch (Throwable th4) {
                                                        th.addSuppressed(th4);
                                                    }
                                                }
                                                throw th3;
                                            }
                                        }
                                    } catch (Throwable th5) {
                                        th = th5;
                                        th = th;
                                        throw th;
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    inputStream = inputStream2;
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                inputStream = inputStream2;
                            }
                        }
                        inputStream = inputStream2;
                        try {
                            long j7 = j4;
                            try {
                                this.zyO.BIo(i4, j7);
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (zZm e3) {
                                        e = e3;
                                        i3 = i4;
                                        j3 = j7;
                                        zZm(e);
                                        throw null;
                                    } catch (IOException e4) {
                                        e = e4;
                                        i2 = i4;
                                        j2 = j7;
                                        zZm(new BIo(e));
                                        throw null;
                                    }
                                }
                                Log.i(zZm, "Attachment bytes written: " + i4 + " finalWriteTime: " + j7);
                                mo12584timeout.clearTimeout();
                                mo12584timeout.clearDeadline();
                            } catch (Throwable th8) {
                                th = th8;
                                j4 = j7;
                                th = th;
                                throw th;
                            }
                        } catch (Throwable th9) {
                            th = th9;
                            th = th;
                            throw th;
                        }
                    } catch (Throwable th10) {
                        th = th10;
                        inputStream = inputStream2;
                        j4 = elapsedRealTime2;
                    }
                } catch (Throwable th11) {
                    th = th11;
                    i = elapsedRealTime2;
                    j = 0;
                    Log.i(zZm, "Attachment bytes written: " + i + " finalWriteTime: " + j);
                    mo12584timeout.clearTimeout();
                    mo12584timeout.clearDeadline();
                    throw th;
                }
            } catch (zZm e5) {
                e = e5;
                zZm(e);
                throw null;
            } catch (IOException e6) {
                e = e6;
                zZm(new BIo(e));
                throw null;
            }
        } catch (zZm e7) {
            e = e7;
            i3 = 0;
            j3 = elapsedRealTime2;
        } catch (IOException e8) {
            e = e8;
            i2 = 0;
            j2 = elapsedRealTime2;
        } catch (Throwable th12) {
            th = th12;
            i = 0;
            j = elapsedRealTime2;
            Log.i(zZm, "Attachment bytes written: " + i + " finalWriteTime: " + j);
            mo12584timeout.clearTimeout();
            mo12584timeout.clearDeadline();
            throw th;
        }
    }

    public final int zZm(InputStream inputStream, int i, byte[] bArr) throws BIo {
        try {
            return inputStream.read(bArr);
        } catch (IOException e) {
            if ("Remote side is dead".equals(e.getMessage()) && i >= 0) {
                Log.w(zZm, e.getMessage(), e);
                return -1;
            }
            throw new BIo(e);
        }
    }

    public final void zZm(zZm zzm) throws zZm {
        Log.e(zZm, zzm.getMessage(), zzm);
        this.zyO.onError(zzm);
        throw zzm;
    }
}
