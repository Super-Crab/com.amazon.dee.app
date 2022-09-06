package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.wakeword.pryon.PryonCallbacksWrapper;
import com.google.android.exoplayer2.text.cea.Cea608Decoder;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
/* compiled from: ValidationCallbacksWrapper.java */
/* loaded from: classes.dex */
public class Rbd extends PryonCallbacksWrapper {
    public static final String TAG = "Rbd";
    public final AlexaClientEventBus BIo;
    public final Sjd callbacks;
    public volatile boolean zQM;
    public final TimeProvider zZm;
    public Long zyO;

    public Rbd(Sjd sjd, TimeProvider timeProvider, AlexaClientEventBus alexaClientEventBus, FdV fdV) {
        super(sjd, fdV);
        this.callbacks = sjd;
        this.zZm = timeProvider;
        this.BIo = alexaClientEventBus;
    }

    @Override // com.amazon.alexa.wakeword.pryon.PryonCallbacksWrapper, com.amazon.pryon.android.asr.PryonLite5000.Callbacks
    public void wakeWordDetected(String str, long j, long j2, byte[] bArr) {
        super.wakeWordDetected(str, j, j2, bArr);
        this.zQM = true;
        this.BIo.zyO(new lOT());
        Long l = this.zyO;
        if (l != null) {
            this.BIo.zyO(new JTw(this.zZm.currentTimeMillis() - l.longValue(), l.longValue()));
            this.BIo.zyO(new rIt(TimeUnit.SECONDS.toMillis(j2 / Cea608Decoder.MIN_DATA_CHANNEL_TIMEOUT_MS)));
        }
    }

    public void zZm(Throwable th) {
        Log.i(TAG, "wakeWordDetectionStopped");
        if (!this.zQM) {
            try {
                getCallbackExecutor().execute(new Xjy(this, th));
            } catch (RejectedExecutionException e) {
                Log.w(TAG, "Could not execute failure callback", e);
            }
        }
    }
}
