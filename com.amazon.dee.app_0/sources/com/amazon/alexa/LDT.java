package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.voice.pryon.asr.PryonWakeWordDetectorCompat;
import com.amazon.pryon.android.asr.PryonLite5000;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: WakeWordDetectionRunnable.java */
/* loaded from: classes.dex */
public class LDT implements Runnable {
    public static final String zZm = "LDT";
    public final PryonWakeWordDetectorCompat BIo;
    public final AtomicBoolean jiA = new AtomicBoolean(false);
    public final Rbd zQM;
    public final nzu zyO;

    public LDT(PryonWakeWordDetectorCompat pryonWakeWordDetectorCompat, Rbd rbd, nzu nzuVar) {
        this.BIo = pryonWakeWordDetectorCompat;
        this.zQM = rbd;
        this.zyO = nzuVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        PryonLite5000 pryonLite;
        Log.i(zZm, "Beginning transfer of audio buffer to Pryon native component.");
        Rbd rbd = this.zQM;
        rbd.zyO = Long.valueOf(rbd.zZm.currentTimeMillis());
        try {
            this.BIo.setPryonLiteCallbacks(this.zQM);
            pryonLite = this.BIo.getPryonLite();
        } finally {
            try {
                this.BIo.removePryonLiteCallbacks();
                this.zyO.BIo();
                Log.i(zZm, "audio capturer released");
                Log.i(zZm, "Transfer of audio buffer to Pryon is ending.");
                return;
            } catch (Throwable th) {
            }
        }
        if (pryonLite != null) {
            int i = 0;
            while (i < 300) {
                nzu nzuVar = this.zyO;
                if (!(nzuVar.LPk && nzuVar.JTe)) {
                    break;
                } else if (this.zQM.zQM) {
                    this.zyO.zyO();
                } else {
                    i++;
                    int pushAudio = pryonLite.pushAudio(this.zyO.zZm());
                    if (pushAudio != 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Failed to push audio samples to Pryon. Error code = ");
                        sb.append(pushAudio);
                        throw new IOException(sb.toString());
                    }
                }
            }
            if (this.jiA.compareAndSet(false, true)) {
                this.zQM.zZm((Throwable) null);
            }
            this.BIo.removePryonLiteCallbacks();
            this.zyO.BIo();
            Log.i(zZm, "audio capturer released");
            Log.i(zZm, "Transfer of audio buffer to Pryon is ending.");
            return;
        }
        throw new IllegalStateException("Pryon initialization failed");
    }
}
