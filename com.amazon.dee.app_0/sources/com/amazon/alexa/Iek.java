package com.amazon.alexa;

import android.media.AudioRecord;
import android.os.ConditionVariable;
import android.util.Log;
import com.amazon.alexa.TTH;
import com.amazon.alexa.client.alexaservice.audio.ScaledVolumeProcessor;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.audio.AcousticEchoCancelerWrapperFactory;
import com.amazon.alexa.utils.validation.Preconditions;
/* compiled from: AbstractTurnRecordingRunnable.java */
/* loaded from: classes.dex */
public abstract class Iek implements Runnable {
    public static final int BIo = AudioRecord.getMinBufferSize(16000, 16, 2);
    public static final int zQM = Math.max(1280, BIo);
    public static final String zZm = "Iek";
    public volatile boolean HvC;
    public final TimeProvider JTe;
    public final AcousticEchoCancelerWrapperFactory LPk;
    public final ConditionVariable Mlj;
    public final CrashReporter Qle;
    public Aml dMe;
    public final AlexaClientEventBus jiA;
    public AudioRecord lOf;
    public int noQ;
    public boolean uzr;
    public volatile boolean vkx;
    public volatile boolean wDP;
    public final ScaledVolumeProcessor yPL;
    public final shl zyO;
    public final ConditionVariable zzR;

    public Iek(AlexaClientEventBus alexaClientEventBus, shl shlVar, ScaledVolumeProcessor scaledVolumeProcessor, CrashReporter crashReporter, TimeProvider timeProvider) {
        AcousticEchoCancelerWrapperFactory acousticEchoCancelerWrapperFactory = new AcousticEchoCancelerWrapperFactory();
        this.uzr = false;
        this.HvC = false;
        this.vkx = false;
        this.wDP = false;
        this.noQ = 0;
        Preconditions.notNull(alexaClientEventBus, "eventBus is null ");
        Preconditions.notNull(shlVar, "attachmentStore is null ");
        Preconditions.notNull(scaledVolumeProcessor, "scaledVolumeProcessor is null ");
        this.jiA = alexaClientEventBus;
        this.zyO = shlVar;
        this.yPL = scaledVolumeProcessor;
        this.Qle = crashReporter;
        this.JTe = timeProvider;
        this.LPk = acousticEchoCancelerWrapperFactory;
        this.Mlj = new ConditionVariable(false);
        this.zzR = new ConditionVariable(false);
    }

    public void BIo() {
        Log.i(zZm, "Stopping recording");
        this.wDP = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x0134, code lost:
        if (r0 != null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x015f, code lost:
        if (r0 == null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0161, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0164, code lost:
        r11.zzR.open();
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0169, code lost:
        return;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.Iek.run():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00cc, code lost:
        zZm(com.amazon.alexa.bij.RECORDING_ERROR_FAILED_TO_READ_FROM_AUDIO_RECORD, "Error copying bytes to attachment");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zZm() {
        /*
            Method dump skipped, instructions count: 334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.Iek.zZm():void");
    }

    public abstract void zZm(bij bijVar);

    public abstract void zZm(cIy ciy);

    public final void zZm(bij bijVar, String str) {
        Log.e(zZm, str);
        zZm(bijVar);
        this.jiA.zyO(new nEp());
        this.jiA.zyO(TTH.zZm(str, TTH.zZm.INTERNAL_ERROR));
    }
}
