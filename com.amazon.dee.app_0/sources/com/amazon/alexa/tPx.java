package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import com.amazon.alexa.api.AlexaNextDialogTurn;
import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import com.amazon.alexa.api.AlexaUserSpeechProvider;
import com.amazon.alexa.api.DialogData;
import com.amazon.alexa.api.DialogExtras;
import com.amazon.alexa.api.DialogRequestDeniedReason;
import com.amazon.alexa.api.DialogTurnData;
import com.amazon.alexa.api.LaunchType;
import com.amazon.alexa.audiocapturer.AudioCapturer;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.wakeword.AudioCapturerAuthority;
import com.amazon.alexa.wakeword.WakeWordData;
import com.amazon.alexa.wakeword.WakeWordDetectionController;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: WakeWordUserSpeechProvider.java */
@Singleton
/* loaded from: classes.dex */
public class tPx implements AlexaUserSpeechProvider {
    public static final String zZm = "tPx";
    public final WakeWordDetectionController BIo;
    public final nno JTe;
    public final GSf LPk;
    public final FQn Mlj;
    public final hrT yPL;
    public final AudioCapturerAuthority zQM;
    public final AtomicReference<AudioCapturer> zyO = new AtomicReference<>();
    public final AtomicReference<WakeWordData> jiA = new AtomicReference<>();
    public final zZm Qle = new zZm(null);
    @Nullable
    @VisibleForTesting
    public AlexaDialogExtras zzR = null;

    /* compiled from: WakeWordUserSpeechProvider.java */
    /* loaded from: classes.dex */
    private class zZm implements AlexaDialogTurnStopCallback {
        public /* synthetic */ zZm(aeV aev) {
        }

        @Override // com.amazon.alexa.api.AlexaDialogTurnStopCallback
        public void stopRecording() {
            synchronized (tPx.this) {
                if (tPx.zZm(tPx.this)) {
                    tPx.this.BIo.stopCapturing();
                }
                Log.i(tPx.zZm, "stopRecording");
                if (tPx.this.zyO.get() != null) {
                    ((AudioCapturer) tPx.this.zyO.getAndSet(null)).stopCapturing();
                }
            }
        }
    }

    @Inject
    public tPx(WakeWordDetectionController wakeWordDetectionController, AudioCapturerAuthority audioCapturerAuthority, nno nnoVar, GSf gSf, hrT hrt, FQn fQn) {
        this.BIo = wakeWordDetectionController;
        this.zQM = audioCapturerAuthority;
        this.JTe = nnoVar;
        this.LPk = gSf;
        this.yPL = hrt;
        this.Mlj = fQn;
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public synchronized void onDialogFinished(DialogData dialogData) {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("onDialogFinished: ");
        zZm2.append(dialogData.getDialogId());
        Log.i(str, zZm2.toString());
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public synchronized void onDialogRequestDenied(DialogRequestDeniedReason dialogRequestDeniedReason) {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Dialog request denied: ");
        zZm2.append(dialogRequestDeniedReason.getReason());
        zZm2.append(RealTimeTextConstants.COLON_SPACE);
        zZm2.append(dialogRequestDeniedReason.getMessage());
        Log.w(str, zZm2.toString());
        if (this.jiA.get() != null) {
            this.BIo.stopCapturing();
            this.jiA.getAndSet(null).getAudioSink().abandon();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005b A[Catch: all -> 0x0078, TryCatch #1 {, blocks: (B:3:0x0001, B:8:0x000e, B:9:0x0022, B:11:0x0028, B:13:0x0037, B:27:0x0055, B:32:0x0062, B:33:0x006d, B:30:0x005b, B:23:0x0049, B:22:0x0046, B:26:0x004d), top: B:41:0x0001, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0062 A[Catch: all -> 0x0078, TryCatch #1 {, blocks: (B:3:0x0001, B:8:0x000e, B:9:0x0022, B:11:0x0028, B:13:0x0037, B:27:0x0055, B:32:0x0062, B:33:0x006d, B:30:0x005b, B:23:0x0049, B:22:0x0046, B:26:0x004d), top: B:41:0x0001, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006d A[Catch: all -> 0x0078, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:8:0x000e, B:9:0x0022, B:11:0x0028, B:13:0x0037, B:27:0x0055, B:32:0x0062, B:33:0x006d, B:30:0x005b, B:23:0x0049, B:22:0x0046, B:26:0x004d), top: B:41:0x0001, inners: #4 }] */
    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void onDialogRequested(com.amazon.alexa.api.AlexaDialogTurn r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            java.util.concurrent.atomic.AtomicReference<com.amazon.alexa.wakeword.WakeWordData> r0 = r8.jiA     // Catch: java.lang.Throwable -> L78
            java.lang.Object r0 = r0.get()     // Catch: java.lang.Throwable -> L78
            if (r0 == 0) goto Lb
            r0 = 1
            goto Lc
        Lb:
            r0 = 0
        Lc:
            if (r0 == 0) goto L76
            java.util.concurrent.atomic.AtomicReference<com.amazon.alexa.wakeword.WakeWordData> r0 = r8.jiA     // Catch: java.lang.Throwable -> L78
            r1 = 0
            java.lang.Object r0 = r0.getAndSet(r1)     // Catch: java.lang.Throwable -> L78
            com.amazon.alexa.wakeword.WakeWordData r0 = (com.amazon.alexa.wakeword.WakeWordData) r0     // Catch: java.lang.Throwable -> L78
            com.amazon.alexa.api.AlexaAudioMetadata r3 = new com.amazon.alexa.api.AlexaAudioMetadata     // Catch: java.lang.Throwable -> L78
            com.amazon.alexa.api.AlexaProfile r2 = com.amazon.alexa.api.AlexaProfile.NEAR_FIELD     // Catch: java.lang.Throwable -> L78
            com.amazon.alexa.api.AlexaWakeWord r4 = r0.getAlexaWakeWord()     // Catch: java.lang.Throwable -> L78
            r3.<init>(r2, r4)     // Catch: java.lang.Throwable -> L78
            byte[] r2 = r0.getMetadata()     // Catch: java.io.IOException -> L4c java.lang.Throwable -> L78
            if (r2 == 0) goto L4a
            com.amazon.alexa.api.AlexaDataSink r4 = new com.amazon.alexa.api.AlexaDataSink     // Catch: java.io.IOException -> L4c java.lang.Throwable -> L78
            r4.<init>()     // Catch: java.io.IOException -> L4c java.lang.Throwable -> L78
            java.io.OutputStream r5 = r4.openForWriting()     // Catch: java.io.IOException -> L4c java.lang.Throwable -> L78
            r5.write(r2)     // Catch: java.lang.Throwable -> L3c
            r5.flush()     // Catch: java.lang.Throwable -> L3c
            r5.close()     // Catch: java.io.IOException -> L4c java.lang.Throwable -> L78
            r5 = r4
            goto L55
        L3c:
            r2 = move-exception
            throw r2     // Catch: java.lang.Throwable -> L3e
        L3e:
            r4 = move-exception
            if (r5 == 0) goto L49
            r5.close()     // Catch: java.lang.Throwable -> L45
            goto L49
        L45:
            r5 = move-exception
            r2.addSuppressed(r5)     // Catch: java.io.IOException -> L4c java.lang.Throwable -> L78
        L49:
            throw r4     // Catch: java.io.IOException -> L4c java.lang.Throwable -> L78
        L4a:
            r5 = r1
            goto L55
        L4c:
            r2 = move-exception
            java.lang.String r4 = com.amazon.alexa.tPx.zZm     // Catch: java.lang.Throwable -> L78
            java.lang.String r5 = "Unable to write wake word metadata to data sink"
            android.util.Log.w(r4, r5, r2)     // Catch: java.lang.Throwable -> L78
            goto L4a
        L55:
            com.amazon.alexa.api.AlexaDialogExtras r1 = r8.zzR     // Catch: java.lang.Throwable -> L78
            if (r1 == 0) goto L5b
        L59:
            r7 = r1
            goto L60
        L5b:
            com.amazon.alexa.api.AlexaDialogExtras r1 = r8.zZm()     // Catch: java.lang.Throwable -> L78
            goto L59
        L60:
            if (r5 == 0) goto L6d
            com.amazon.alexa.api.AlexaAudioSink r4 = r0.getAudioSink()     // Catch: java.lang.Throwable -> L78
            com.amazon.alexa.tPx$zZm r6 = r8.Qle     // Catch: java.lang.Throwable -> L78
            r2 = r9
            r2.startTurn(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L78
            goto L76
        L6d:
            com.amazon.alexa.api.AlexaAudioSink r0 = r0.getAudioSink()     // Catch: java.lang.Throwable -> L78
            com.amazon.alexa.tPx$zZm r1 = r8.Qle     // Catch: java.lang.Throwable -> L78
            r9.startTurn(r3, r0, r1, r7)     // Catch: java.lang.Throwable -> L78
        L76:
            monitor-exit(r8)
            return
        L78:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.tPx.onDialogRequested(com.amazon.alexa.api.AlexaDialogTurn):void");
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public synchronized void onDialogStarted(DialogData dialogData) {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("onDialogStarted: ");
        zZm2.append(dialogData.getDialogId());
        Log.i(str, zZm2.toString());
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public synchronized void onDialogTurnFinished(DialogTurnData dialogTurnData) {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("onDialogTurnFinished: ");
        zZm2.append(dialogTurnData.getDialogTurnId());
        Log.i(str, zZm2.toString());
        if (!this.BIo.isDetectingWakeWord()) {
            this.BIo.stopCapturing();
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public synchronized void onDialogTurnRequested(AlexaNextDialogTurn alexaNextDialogTurn) {
        this.Qle.stopRecording();
        try {
            this.zyO.set(this.zQM.getAudioCapturer(this.JTe));
            AlexaAudioSink alexaAudioSink = new AlexaAudioSink();
            if (this.zyO.get().startCapturing(alexaAudioSink)) {
                alexaNextDialogTurn.startTurn(alexaAudioSink, this.Qle);
            } else {
                Log.w(zZm, "Failed to start recording audio");
                alexaAudioSink.abandon();
            }
        } catch (IOException e) {
            Log.e(zZm, "failed to create an audio sink", e);
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public synchronized void onDialogTurnStarted(DialogTurnData dialogTurnData) {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("onDialogTurnStarted: ");
        zZm2.append(dialogTurnData.getDialogTurnId());
        Log.i(str, zZm2.toString());
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void pauseWakeWordDetection(String str) {
        Log.i(zZm, "pauseWakeWordDetection");
        this.BIo.pauseDetectingWakeWord();
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void resumeWakeWordDetection(String str) {
        Log.i(zZm, "resumeWakeWordDetection");
        this.BIo.resumeDetectingWakeWord();
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public synchronized void setWakeWordDetectionEnabled(boolean z) {
        this.LPk.zZm(z);
    }

    @VisibleForTesting
    public AlexaDialogExtras zZm() {
        boolean z = true;
        AlexaDialogExtras.Builder suppressWakewordVerification = AlexaDialogExtras.builder().setInvocationType("AlexaApp.WakeWord").setLaunchType(LaunchType.WAKE_WORD).suppressWakeSound(!this.yPL.BIo()).suppressEndpointSound(!this.yPL.zZm()).suppressWakewordVerification(true);
        AlexaUserInterfaceOptions.Builder builder = AlexaUserInterfaceOptions.builder();
        FQn fQn = this.Mlj;
        Locale Qle = fQn.zQM.Qle();
        if (Qle == null) {
            z = false;
        } else if (!FQn.zZm.equals(Qle)) {
            z = fQn.BIo.zZm(Feature.ALEXA_VOX_ANDROID_TTA_I18N_DEV);
        }
        return suppressWakewordVerification.setAlexaUserInterfaceOptions(builder.setTypingEnabled(z).build()).build();
    }

    public synchronized void zZm(@Nullable AlexaDialogExtras alexaDialogExtras) {
        if (alexaDialogExtras == null) {
            return;
        }
        AlexaDialogExtras zZm2 = zZm();
        this.zzR = DialogExtras.getBuilder(alexaDialogExtras).setLaunchType(zZm2.getLaunchType()).suppressWakewordVerification(zZm2.suppressWakewordVerification()).build();
    }

    public synchronized void zZm(WakeWordData wakeWordData) {
        this.jiA.set(wakeWordData);
    }

    public static /* synthetic */ boolean zZm(tPx tpx) {
        return !tpx.BIo.isDetectingWakeWord();
    }
}
