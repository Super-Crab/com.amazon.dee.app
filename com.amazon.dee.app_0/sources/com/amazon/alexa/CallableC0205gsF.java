package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.audio.ScaledVolumeProcessor;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: AudioStreamProcessor.java */
/* renamed from: com.amazon.alexa.gsF  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class CallableC0205gsF implements Callable<Void> {
    public static final String zZm = "gsF";
    public final OutputStream BIo;
    public volatile boolean JTe;
    public final InputStream zQM;
    public final ScaledVolumeProcessor zyO;
    public final ByteBuffer jiA = ByteBuffer.allocate(960);
    public final AtomicReference<Float> Qle = new AtomicReference<>();

    public CallableC0205gsF(InputStream inputStream, OutputStream outputStream, ScaledVolumeProcessor scaledVolumeProcessor) {
        this.zQM = inputStream;
        this.BIo = outputStream;
        this.zyO = scaledVolumeProcessor;
    }

    @Override // java.util.concurrent.Callable
    public /* bridge */ /* synthetic */ Void call() throws Exception {
        call2();
        return null;
    }

    public final void zZm(byte[] bArr, int i) {
        ByteBuffer byteBuffer = this.jiA;
        byteBuffer.put(bArr, 0, Math.min(i, byteBuffer.remaining()));
        if (!this.jiA.hasRemaining()) {
            this.Qle.set(Float.valueOf(this.zyO.zZm(this.jiA.array(), this.jiA.capacity())));
            this.jiA.clear();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004f, code lost:
        android.util.Log.e(com.amazon.alexa.CallableC0205gsF.zZm, "No data was written to the output stream");
     */
    @Override // java.util.concurrent.Callable
    /* renamed from: call  reason: avoid collision after fix types in other method */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Void call2() throws java.io.IOException {
        /*
            r5 = this;
            r0 = 320(0x140, float:4.48E-43)
            byte[] r0 = new byte[r0]
            r1 = 0
            r2 = r1
        L6:
            boolean r3 = r5.JTe     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3d
            if (r3 != 0) goto L2a
            monitor-enter(r5)     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3d
            java.io.InputStream r3 = r5.zQM     // Catch: java.lang.Throwable -> L27
            int r3 = r3.read(r0)     // Catch: java.lang.Throwable -> L27
            if (r3 >= 0) goto L15
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L27
            goto L2a
        L15:
            if (r3 <= 0) goto L25
            java.io.OutputStream r4 = r5.BIo     // Catch: java.lang.Throwable -> L27
            r4.write(r0, r1, r3)     // Catch: java.lang.Throwable -> L27
            java.io.OutputStream r4 = r5.BIo     // Catch: java.lang.Throwable -> L27
            r4.flush()     // Catch: java.lang.Throwable -> L27
            r5.zZm(r0, r3)     // Catch: java.lang.Throwable -> L27
            r2 = 1
        L25:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L27
            goto L6
        L27:
            r0 = move-exception
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L27
            throw r0     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3d
        L2a:
            if (r2 != 0) goto L33
            java.lang.String r0 = com.amazon.alexa.CallableC0205gsF.zZm
            java.lang.String r1 = "No data was written to the output stream"
            android.util.Log.e(r0, r1)
        L33:
            java.io.OutputStream r0 = r5.BIo     // Catch: java.io.IOException -> L39
            r0.close()     // Catch: java.io.IOException -> L39
            goto L63
        L39:
            r0 = move-exception
            goto L5c
        L3b:
            r0 = move-exception
            goto L65
        L3d:
            r0 = move-exception
            java.lang.String r1 = com.amazon.alexa.CallableC0205gsF.zZm     // Catch: java.lang.Throwable -> L3b
            java.lang.String r3 = "Error transferring data from input stream to output stream"
            android.util.Log.e(r1, r3, r0)     // Catch: java.lang.Throwable -> L3b
            boolean r1 = r5.JTe     // Catch: java.lang.Throwable -> L3b
            if (r1 != 0) goto L4d
            if (r2 == 0) goto L4c
            goto L4d
        L4c:
            throw r0     // Catch: java.lang.Throwable -> L3b
        L4d:
            if (r2 != 0) goto L56
            java.lang.String r0 = com.amazon.alexa.CallableC0205gsF.zZm
            java.lang.String r1 = "No data was written to the output stream"
            android.util.Log.e(r0, r1)
        L56:
            java.io.OutputStream r0 = r5.BIo     // Catch: java.io.IOException -> L39
            r0.close()     // Catch: java.io.IOException -> L39
            goto L63
        L5c:
            java.lang.String r1 = com.amazon.alexa.CallableC0205gsF.zZm
            java.lang.String r2 = "Failed to close output stream"
            android.util.Log.e(r1, r2, r0)
        L63:
            r0 = 0
            return r0
        L65:
            if (r2 != 0) goto L6e
            java.lang.String r1 = com.amazon.alexa.CallableC0205gsF.zZm
            java.lang.String r2 = "No data was written to the output stream"
            android.util.Log.e(r1, r2)
        L6e:
            java.io.OutputStream r1 = r5.BIo     // Catch: java.io.IOException -> L74
            r1.close()     // Catch: java.io.IOException -> L74
            goto L7c
        L74:
            r1 = move-exception
            java.lang.String r2 = com.amazon.alexa.CallableC0205gsF.zZm
            java.lang.String r3 = "Failed to close output stream"
            android.util.Log.e(r2, r3, r1)
        L7c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.CallableC0205gsF.call2():java.lang.Void");
    }
}
