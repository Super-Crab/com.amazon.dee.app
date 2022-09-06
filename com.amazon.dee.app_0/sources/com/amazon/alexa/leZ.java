package com.amazon.alexa;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.TTH;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaClient;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.AlexaDialogTurnMetricsCallback;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import com.amazon.alexa.api.AlexaProfile;
import com.amazon.alexa.api.AlexaSupportedInitiationType;
import com.amazon.alexa.api.AlexaUserSpeechProviderMetadata;
import com.amazon.alexa.api.AlexaUserSpeechProviderScope;
import com.amazon.alexa.api.AudioFormat;
import com.amazon.alexa.api.DialogExtras;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.api.LaunchType;
import com.amazon.alexa.api.UserPerceivedLatencyData;
import com.amazon.alexa.client.alexaservice.audio.ScaledVolumeProcessor;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.kbp;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.amazon.alexa.wakeword.RecordingTracker;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: DefaultInternalUserSpeechProvider.java */
@Singleton
/* loaded from: classes.dex */
public class leZ implements Lbc {
    public static final String zZm = "leZ";
    public final Dtt HvC;
    public final shl JTe;
    public final AlexaClientEventBus LPk;
    public final TimeProvider Mlj;
    public final piE Qle;
    public final ExecutorService dMe;
    public final RecordingTracker jiA;
    public final hrT lOf;
    public final CrashReporter uzr;
    public jiA vkx;
    public AlexaDialogExtras wDP;
    public final Context yPL;
    public final ScaledVolumeProcessor zzR;
    public static final AlexaDialogExtras BIo = AlexaDialogExtras.builder().setInvocationType(piE.zZm.getValue()).setLaunchType(LaunchType.TAP_TO_TALK).build();
    public static final AlexaAudioMetadata zQM = new AlexaAudioMetadata.Builder().setAlexaProfile(AlexaProfile.NEAR_FIELD).setAudioFormat(AudioFormat.AUDIO_L16_RATE_16000_CHANNELS_1.toString()).build();
    public static final AlexaUserSpeechProviderMetadata zyO = AlexaUserSpeechProviderMetadata.create(Collections.singleton(AlexaSupportedInitiationType.TAP_TO_TALK), Collections.emptySet(), AlexaUserSpeechProviderScope.APPLICATION);

    /* compiled from: DefaultInternalUserSpeechProvider.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    static class BIo extends jiA {
        public final AlexaDialogExtras Mlj;
        public final CrashReporter dMe;
        public final Jpo lOf;
        public final AlexaAudioMetadata zzR;

        public BIo(AlexaClientEventBus alexaClientEventBus, AlexaDialogExtras alexaDialogExtras, AlexaAudioMetadata alexaAudioMetadata, shl shlVar, ScaledVolumeProcessor scaledVolumeProcessor, TimeProvider timeProvider, ExecutorService executorService, RecordingTracker recordingTracker, CrashReporter crashReporter, Jpo jpo) {
            super(alexaClientEventBus, shlVar, scaledVolumeProcessor, executorService, recordingTracker, timeProvider);
            this.Mlj = alexaDialogExtras;
            this.zzR = alexaAudioMetadata;
            this.dMe = crashReporter;
            this.lOf = jpo;
        }

        @Override // com.amazon.alexa.leZ.jiA
        public Iek zZm() {
            return new zZm(this.zZm, this.Mlj, this.zzR, this.lOf, this, this, this.BIo, this.zyO, this.dMe, this.Qle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DefaultInternalUserSpeechProvider.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public static abstract class jiA implements AlexaDialogTurnStopCallback, AlexaDialogTurnMetricsCallback {
        public final shl BIo;
        public Future<?> JTe;
        public zZm LPk = zZm.CREATED;
        public final TimeProvider Qle;
        public final RecordingTracker jiA;
        public Iek yPL;
        public final ExecutorService zQM;
        public final AlexaClientEventBus zZm;
        public final ScaledVolumeProcessor zyO;

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: DefaultInternalUserSpeechProvider.java */
        /* loaded from: classes.dex */
        public enum zZm {
            CREATED,
            RECORDING,
            DONE
        }

        public jiA(AlexaClientEventBus alexaClientEventBus, shl shlVar, ScaledVolumeProcessor scaledVolumeProcessor, ExecutorService executorService, RecordingTracker recordingTracker, TimeProvider timeProvider) {
            this.zZm = alexaClientEventBus;
            this.BIo = shlVar;
            this.zyO = scaledVolumeProcessor;
            this.zQM = executorService;
            this.jiA = recordingTracker;
            this.Qle = timeProvider;
            recordingTracker.onCapturerCreated();
        }

        public final void BIo() {
            boolean z;
            if (this.LPk == zZm.CREATED) {
                this.yPL = zZm();
                String str = leZ.zZm;
                StringBuilder zZm2 = C0179Pya.zZm("Created Recording runnable ");
                zZm2.append(this.yPL);
                zZm2.toString();
                this.JTe = this.zQM.submit(this.yPL);
                Iek iek = this.yPL;
                if (iek.wDP) {
                    z = false;
                } else {
                    if (!iek.Mlj.block(5000L)) {
                        iek.zZm(bij.RECORDING_ERROR_START_TIMEOUT, "Failed to start recording before timeout: 5000");
                        iek.BIo();
                    }
                    z = iek.HvC;
                }
                if (z) {
                    this.LPk = zZm.RECORDING;
                    this.jiA.onCapturingStarted();
                    return;
                }
                this.LPk = zZm.DONE;
                this.jiA.onCapturingFinished();
                Log.e(leZ.zZm, "Unable to start recording.");
                this.zZm.zyO(TTH.zZm("Unable to start recording.", TTH.zZm.INTERNAL_ERROR));
                return;
            }
            String str2 = leZ.zZm;
            StringBuilder zZm3 = C0179Pya.zZm("Attempted to start recording with a TurnTask which has already run: ");
            zZm3.append(this.LPk);
            Log.e(str2, zZm3.toString());
        }

        @Override // com.amazon.alexa.api.AlexaDialogTurnMetricsCallback
        public void onUserPerceivedLatencyData(UserPerceivedLatencyData userPerceivedLatencyData) {
            String str = leZ.zZm;
            StringBuilder zZm2 = C0179Pya.zZm("UPL for ");
            zZm2.append(userPerceivedLatencyData.getDialogRequestId());
            zZm2.append(RealTimeTextConstants.COLON_SPACE);
            zZm2.append(userPerceivedLatencyData.getEstimatedUserPerceivedLatency());
            zZm2.toString();
        }

        @Override // com.amazon.alexa.api.AlexaDialogTurnStopCallback
        public final void stopRecording() {
            Log.i(leZ.zZm, "stopRecording");
            if (this.LPk == zZm.RECORDING && !this.JTe.isDone()) {
                if (this.yPL != null) {
                    C0179Pya.zZm(C0179Pya.zZm("stopping Recording runnable: "), this.yPL, leZ.zZm);
                    this.yPL.BIo();
                    Iek iek = this.yPL;
                    if (iek.vkx && !iek.zzR.block(5000L)) {
                        iek.zZm(bij.RECORDING_ERROR_STOP_TIMEOUT, "Failed to stop recording quickly. Timeout: 5000");
                    }
                    this.yPL = null;
                }
                Future<?> future = this.JTe;
                if (future != null) {
                    future.cancel(false);
                    this.JTe = null;
                }
            }
            this.LPk = zZm.DONE;
            this.jiA.onCapturingFinished();
        }

        public abstract Iek zZm();
    }

    /* compiled from: DefaultInternalUserSpeechProvider.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    static class zyO extends jiA {
        public final yWg Mlj;
        public final CrashReporter zzR;

        public zyO(AlexaClientEventBus alexaClientEventBus, shl shlVar, ScaledVolumeProcessor scaledVolumeProcessor, TimeProvider timeProvider, ExecutorService executorService, RecordingTracker recordingTracker, CrashReporter crashReporter, yWg ywg) {
            super(alexaClientEventBus, shlVar, scaledVolumeProcessor, executorService, recordingTracker, timeProvider);
            this.Mlj = ywg;
            this.zzR = crashReporter;
        }

        @Override // com.amazon.alexa.leZ.jiA
        public Iek zZm() {
            return new zQM(this.zZm, this.Mlj, this, this, this.BIo, this.zyO, this.zzR, this.Qle);
        }
    }

    @Inject
    public leZ(Context context, AlexaClientEventBus alexaClientEventBus, shl shlVar, ScaledVolumeProcessor scaledVolumeProcessor, TimeProvider timeProvider, RecordingTracker recordingTracker, hrT hrt, CrashReporter crashReporter, Dtt dtt) {
        ExecutorService newSingleThreadCachedThreadPool = ManagedExecutorFactory.newSingleThreadCachedThreadPool("internal-speech-provider-recording-thread");
        this.jiA = recordingTracker;
        this.Qle = piE.zZm;
        this.JTe = shlVar;
        this.LPk = alexaClientEventBus;
        this.yPL = context;
        this.zzR = scaledVolumeProcessor;
        this.Mlj = timeProvider;
        this.lOf = hrt;
        this.uzr = crashReporter;
        this.HvC = dtt;
        this.dMe = newSingleThreadCachedThreadPool;
    }

    @Override // com.amazon.alexa.mqw
    public void BIo(qSf qsf) {
        C0179Pya.zZm("onDialogFinished: ", (Object) qsf);
    }

    @Override // com.amazon.alexa.mqw
    public boolean BIo() {
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && leZ.class == obj.getClass()) {
            return Objects.equals(this.Qle, ((leZ) obj).Qle);
        }
        return false;
    }

    @Override // com.amazon.alexa.mqw
    public ExtendedClient getClient() {
        return AlexaClient.CLIENT;
    }

    @Override // com.amazon.alexa.mqw
    public AlexaUserSpeechProviderMetadata getMetadata() {
        return zyO;
    }

    public int hashCode() {
        return Objects.hash(this.Qle);
    }

    @Override // com.amazon.alexa.mqw
    public void pauseWakeWordDetection(String str) {
    }

    @Override // com.amazon.alexa.mqw
    public void resumeWakeWordDetection(String str) {
    }

    @Override // com.amazon.alexa.mqw
    public void setWakeWordDetectionEnabled(boolean z) {
    }

    public final boolean zQM() {
        jiA jia = this.vkx;
        if (jia != null) {
            if (jia.LPk == jiA.zZm.RECORDING) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.mqw
    public piE zZm() {
        return this.Qle;
    }

    public final boolean zyO() {
        int zZm2 = this.HvC.zZm(this.yPL, "android.permission.RECORD_AUDIO");
        if (zQM()) {
            Log.w(zZm, "We cannot start recording while already recording.");
            this.LPk.zyO(TTH.zZm("We cannot start recording while already recording.", TTH.zZm.INTERNAL_ERROR));
            return false;
        }
        if (zZm2 == 0) {
            if (!zQM()) {
                return true;
            }
            Log.e(zZm, "Cannot start recording while already recording");
            this.LPk.zyO(TTH.zZm("Cannot start recording while already recording", TTH.zZm.INTERNAL_ERROR));
        } else {
            Log.e(zZm, "Do not have permission to record audio");
            this.LPk.zyO(TTH.zZm("Do not have permission to record audio", TTH.zZm.INTERNAL_ERROR));
        }
        return false;
    }

    @Override // com.amazon.alexa.mqw
    public void BIo(XWx xWx) {
        C0179Pya.zZm("onDialogTurnStarted: ", (Object) xWx);
    }

    @Override // com.amazon.alexa.mqw
    public void zZm(Jpo jpo, AlexaDialogRequest alexaDialogRequest) {
        AlexaDialogExtras build;
        String invocationType = alexaDialogRequest.getInvocationType();
        AlexaDialogExtras alexaDialogExtras = this.wDP;
        if (alexaDialogExtras != null && !alexaDialogExtras.equals(DialogExtras.zZm)) {
            AlexaDialogExtras.Builder zZm2 = zZm(this.wDP);
            if (TextUtils.isEmpty(this.wDP.getInvocationType())) {
                if (TextUtils.isEmpty(invocationType)) {
                    zZm2.setInvocationType(piE.zZm.getValue());
                } else {
                    zZm2.setInvocationType(invocationType);
                }
            }
            if (LaunchType.UNKNOWN.equals(this.wDP.getLaunchType())) {
                zZm2.setLaunchType(LaunchType.TAP_TO_TALK);
            }
            this.wDP = null;
            build = zZm2.build();
        } else {
            AlexaDialogExtras.Builder zZm3 = zZm(BIo);
            if (!TextUtils.isEmpty(invocationType)) {
                zZm3.setInvocationType(invocationType);
            }
            build = zZm3.build();
        }
        AlexaDialogExtras alexaDialogExtras2 = build;
        if (zyO()) {
            this.vkx = new BIo(this.LPk, alexaDialogExtras2, zQM, this.JTe, this.zzR, this.Mlj, this.dMe, this.jiA, this.uzr, jpo);
            this.vkx.BIo();
        }
    }

    /* compiled from: DefaultInternalUserSpeechProvider.java */
    /* loaded from: classes.dex */
    static class zQM extends Iek {
        public final yWg Qgh;
        public final AlexaDialogTurnStopCallback Tbw;
        public final AlexaDialogTurnMetricsCallback XWf;

        public zQM(AlexaClientEventBus alexaClientEventBus, yWg ywg, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback, shl shlVar, ScaledVolumeProcessor scaledVolumeProcessor, CrashReporter crashReporter, TimeProvider timeProvider) {
            super(alexaClientEventBus, shlVar, scaledVolumeProcessor, crashReporter, timeProvider);
            this.Qgh = ywg;
            this.Tbw = alexaDialogTurnStopCallback;
            this.XWf = alexaDialogTurnMetricsCallback;
        }

        @Override // com.amazon.alexa.Iek
        public void zZm(bij bijVar) {
            this.jiA.zyO(kbp.zQM.zZm(this.Qgh.BIo(), bijVar));
        }

        @Override // com.amazon.alexa.Iek
        public void zZm(cIy ciy) {
            this.Qgh.zZm(ciy, this.Tbw, null, this.XWf, null);
        }
    }

    /* compiled from: DefaultInternalUserSpeechProvider.java */
    /* loaded from: classes.dex */
    static class zZm extends Iek {
        public final AlexaDialogTurnStopCallback NXS;
        public final AlexaDialogExtras Qgh;
        public final AlexaAudioMetadata Tbw;
        public final Jpo XWf;
        public final AlexaDialogTurnMetricsCallback uuO;

        public zZm(AlexaClientEventBus alexaClientEventBus, AlexaDialogExtras alexaDialogExtras, AlexaAudioMetadata alexaAudioMetadata, Jpo jpo, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback, shl shlVar, ScaledVolumeProcessor scaledVolumeProcessor, CrashReporter crashReporter, TimeProvider timeProvider) {
            super(alexaClientEventBus, shlVar, scaledVolumeProcessor, crashReporter, timeProvider);
            this.Qgh = alexaDialogExtras;
            this.Tbw = alexaAudioMetadata;
            this.XWf = jpo;
            this.NXS = alexaDialogTurnStopCallback;
            this.uuO = alexaDialogTurnMetricsCallback;
        }

        @Override // com.amazon.alexa.Iek
        public void zZm(bij bijVar) {
            this.jiA.zyO(kbp.zQM.zZm(this.XWf.BIo(), bijVar));
        }

        @Override // com.amazon.alexa.Iek
        public void zZm(cIy ciy) {
            this.XWf.zZm(ciy, this.NXS, this.Tbw, this.uuO, this.Qgh);
        }
    }

    @Override // com.amazon.alexa.mqw
    public void zZm(qSf qsf) {
        C0179Pya.zZm("onDialogStarted: ", (Object) qsf);
    }

    @Override // com.amazon.alexa.mqw
    public void zZm(XWx xWx) {
        C0179Pya.zZm("onDialogTurnFinished: ", (Object) xWx);
    }

    @Override // com.amazon.alexa.mqw
    public void zZm(yWg ywg) {
        if (zyO()) {
            this.vkx = new zyO(this.LPk, this.JTe, this.zzR, this.Mlj, this.dMe, this.jiA, this.uzr, ywg);
            this.vkx.BIo();
        }
    }

    public final AlexaDialogExtras.Builder zZm(AlexaDialogExtras alexaDialogExtras) {
        return DialogExtras.getBuilder(alexaDialogExtras).suppressWakeSound(!this.lOf.BIo()).suppressEndpointSound(!this.lOf.zZm());
    }
}
