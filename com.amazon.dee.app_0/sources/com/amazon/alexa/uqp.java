package com.amazon.alexa;

import android.media.AudioManager;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.BOa;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.voice.pryon.asr.PryonWakeWordDetectorCompat;
import com.amazon.alexa.wakeword.pryon.AudioPlaybackConfigurationHelper;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectorProvider;
import com.amazon.pryon.android.asr.PryonLite5000;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: ExternalAudioWakeWordDetector.java */
@Singleton
/* loaded from: classes.dex */
public class uqp {
    @VisibleForTesting
    public static long BIo = 20000;
    public static final String zZm = "uqp";
    public final ScheduledExecutorService JTe;
    public final FdV LPk;
    public ExecutorService Mlj;
    public final WakeWordDetectorProvider Qle;
    public ScheduledFuture dMe;
    public final TimeProvider jiA;
    public Rbd lOf;
    public final AudioPlaybackConfigurationHelper yPL;
    public final AudioManager zQM;
    public final AlexaClientEventBus zyO;
    public LDT zzR;

    @Inject
    public uqp(AudioManager audioManager, WakeWordDetectorProvider wakeWordDetectorProvider, TimeProvider timeProvider, AlexaClientEventBus alexaClientEventBus, FdV fdV, AudioPlaybackConfigurationHelper audioPlaybackConfigurationHelper) {
        ScheduledExecutorService newSingleThreadScheduledExecutor = ManagedExecutorFactory.newSingleThreadScheduledExecutor("wake-word-detector-timeout");
        this.zQM = audioManager;
        this.Qle = wakeWordDetectorProvider;
        this.jiA = timeProvider;
        this.zyO = alexaClientEventBus;
        this.JTe = newSingleThreadScheduledExecutor;
        this.LPk = fdV;
        this.yPL = audioPlaybackConfigurationHelper;
    }

    public void zZm(InputStream inputStream, OutputStream outputStream, Sjd sjd) {
        Preconditions.notNull(inputStream, "InputStream is null");
        Preconditions.notNull(outputStream, "OutputStream is null");
        Preconditions.notNull(sjd, "Callbacks is null");
        this.Mlj = ExecutorFactory.newSingleThreadExecutor("wake-word-detector");
        this.Qle.resetPryon();
        PryonWakeWordDetectorCompat mo2864get = this.Qle.mo2864get();
        if (mo2864get != null && mo2864get.getPryonLite() != null) {
            for (Map.Entry<PryonLite5000.ClientProperty, Integer> entry : this.yPL.getClientPropertiesMap(this.zQM).entrySet()) {
                String str = zZm;
                StringBuilder zZm2 = C0179Pya.zZm("Set client property | group id: ");
                zZm2.append(entry.getKey().groupId);
                zZm2.append(" property id: ");
                zZm2.append(entry.getKey().propertyId);
                zZm2.append(" | clientPropertyState: ");
                zZm2.append(entry.getValue());
                Log.i(str, zZm2.toString());
                this.Qle.setClientProperty(entry.getKey(), entry.getValue().intValue());
            }
            int samplesPerFrame = mo2864get.getPryonLite().getSamplesPerFrame();
            this.lOf = new Rbd(sjd, this.jiA, this.zyO, this.LPk);
            this.zzR = new LDT(mo2864get, this.lOf, new nzu(inputStream, outputStream, samplesPerFrame));
            this.Mlj.execute(this.zzR);
            this.dMe = this.JTe.schedule(new hyf(this, sjd), BIo, TimeUnit.MILLISECONDS);
            return;
        }
        ((BOa.BIo) sjd).BIo(new IllegalStateException("Failed to create wake word detector"));
    }

    public void zZm() {
        LDT ldt = this.zzR;
        if (ldt != null) {
            ldt.zyO.zQM();
            if (ldt.jiA.compareAndSet(false, true)) {
                ldt.zQM.zZm((Throwable) null);
            }
            this.zzR = null;
        }
        Rbd rbd = this.lOf;
        if (rbd != null) {
            rbd.release();
            this.lOf = null;
        }
        ScheduledFuture scheduledFuture = this.dMe;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.dMe = null;
        }
        ExecutorService executorService = this.Mlj;
        if (executorService != null) {
            executorService.shutdownNow();
            this.Mlj = null;
        }
    }
}
