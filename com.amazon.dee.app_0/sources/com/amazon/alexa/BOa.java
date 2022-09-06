package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.QYV;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaWakeWord;
import com.amazon.alexa.api.AudioFormat;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.fuM;
import com.amazon.alexa.kbp;
import com.amazon.alexa.wakeword.WakeWordArbitration;
import com.amazon.alexa.wakeword.precondition.WakeWordPrecondition;
import java.io.IOException;
import java.io.OutputStream;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: WakeWordVerifier.java */
@Singleton
/* loaded from: classes.dex */
public class BOa {
    public static final String zZm = "BOa";
    public final AlexaClientEventBus BIo;
    public volatile boolean JTe;
    public final uqp Qle;
    public final WakeWordArbitration jiA;
    public final shl zQM;
    public final Wyh zyO;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: WakeWordVerifier.java */
    /* loaded from: classes.dex */
    public static class zZm implements WakeWordPrecondition {
        public /* synthetic */ zZm(TdX tdX) {
        }

        @Override // com.amazon.alexa.wakeword.precondition.WakeWordPrecondition
        public boolean isWakeWordAllowed() {
            return true;
        }

        @Override // com.amazon.alexa.wakeword.precondition.WakeWordPrecondition
        public void subscribe(WakeWordPrecondition.ChangeListener changeListener) {
        }

        @Override // com.amazon.alexa.wakeword.precondition.WakeWordPrecondition
        public void unsubscribe(WakeWordPrecondition.ChangeListener changeListener) {
        }
    }

    @Inject
    public BOa(AlexaClientEventBus alexaClientEventBus, shl shlVar, WakeWordArbitration wakeWordArbitration, Wyh wyh, uqp uqpVar) {
        this.BIo = alexaClientEventBus;
        this.zQM = shlVar;
        this.jiA = wakeWordArbitration;
        this.zyO = wyh;
        this.Qle = uqpVar;
        this.BIo.zZm(this);
    }

    @Subscribe
    public synchronized void on(MyZ myZ) {
        this.Qle.Qle.initWakeWordModelUpdates();
    }

    public void zyO() {
        uqp uqpVar = this.Qle;
        uqpVar.zZm();
        uqpVar.Qle.release();
    }

    public void BIo(NEe nEe) {
        AlexaAudioMetadata zyO = nEe.zyO();
        OGm yPL = nEe.yPL();
        if (zZm(zyO)) {
            Log.i(zZm, "going to start wake word verification");
            zZm(nEe, yPL);
            return;
        }
        Log.i(zZm, "no wake word verification needed");
        yPL.Mlj = esV.UNKNOWN;
        yPL.jiA().LPk();
        zZm(nEe);
    }

    public void zQM() {
        this.Qle.zZm();
        this.JTe = false;
    }

    public static boolean zZm(AlexaAudioMetadata alexaAudioMetadata) {
        return alexaAudioMetadata.getAlexaWakeword() != null && AudioFormat.AUDIO_L16_RATE_16000_CHANNELS_1.name().equals(alexaAudioMetadata.getAlexaAudioFormat());
    }

    @Subscribe
    public synchronized void on(kOA koa) {
        zQM();
    }

    @Subscribe
    public synchronized void on(fuM.jiA jia) {
        zQM();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: WakeWordVerifier.java */
    /* loaded from: classes.dex */
    public class BIo implements Sjd {
        public final OGm BIo;
        public final Lzl zQM;
        public final NEe zZm;
        public final Gcr zyO;

        public BIo(NEe nEe, OGm oGm, Lzl lzl, Gcr gcr) {
            this.zZm = nEe;
            this.BIo = oGm;
            this.zQM = lzl;
            this.zyO = gcr;
        }

        public void BIo(Throwable th) {
            Log.e(BOa.zZm, "wake word engine failed", th);
            BOa.this.BIo.zyO(kbp.zZm.zZm(this.zZm.Mlj(), mMl.WAKE_WORD_ENGINE_NOT_READY));
            zZm();
        }

        @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionCallbacks
        public void onClassificationEvent(byte[] bArr, int i, byte[] bArr2) {
        }

        @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionCallbacks
        public void onEnrollmentExampleEvent(AlexaWakeWord alexaWakeWord, short[] sArr, byte[] bArr) {
        }

        @Override // com.amazon.alexa.wakeword.pryon.WakeWordDetectionCallbacks
        public void onWakeWordDetected(AlexaWakeWord alexaWakeWord, @Nullable byte[] bArr) {
            AlexaAudioMetadata zZm;
            String str = BOa.zZm;
            StringBuilder zZm2 = C0179Pya.zZm("wake word verification success: ");
            zZm2.append(alexaWakeWord.getStartIndexInSamples());
            zZm2.append(", ");
            zZm2.append(alexaWakeWord.getEndIndexInSamples());
            Log.i(str, zZm2.toString());
            if (bArr != null) {
                BOa.zZm(BOa.this, this.zQM, bArr);
            }
            BOa.this.JTe = false;
            try {
                AlexaAudioMetadata zyO = this.zZm.zyO();
                int startIndexInSamples = alexaWakeWord.getStartIndexInSamples() > 8000 ? (int) (alexaWakeWord.getStartIndexInSamples() - 8000) : 0;
                Log.i(BOa.zZm, "updateAlexaAudioMetadata");
                if (startIndexInSamples > 0) {
                    zZm = zZm(zyO, new AlexaWakeWord(alexaWakeWord.getWakeWordName(), 8000L, alexaWakeWord.getEndIndexInSamples() - startIndexInSamples));
                } else {
                    zZm = zZm(zyO, alexaWakeWord);
                }
                this.BIo.lOf = zZm;
                this.BIo.Mlj = esV.WAKE_WORD;
                this.zQM.zZm(startIndexInSamples);
                BOa.this.zZm(this.zZm);
            } catch (IOException e) {
                Log.e(BOa.zZm, "Failed to adjust wake word pre roll", e);
                zZm(e);
            }
            this.zyO.zyO = false;
        }

        public void zQM(Throwable th) {
            Log.e(BOa.zZm, "audio to verify wake word is incomplete", th);
            BOa.this.BIo.zyO(kbp.zZm.zZm(this.zZm.Mlj(), mMl.WAKE_WORD_AUDIO_INCOMPLETE));
            zZm();
        }

        public void zZm(Throwable th) {
            Log.e(BOa.zZm, "wake word verification failed", th);
            BOa.this.BIo.zyO(kbp.zZm.zZm(this.zZm.Mlj(), mMl.INVALID_WAKE_WORD));
            zZm();
        }

        public final AlexaAudioMetadata zZm(AlexaAudioMetadata alexaAudioMetadata, AlexaWakeWord alexaWakeWord) {
            AlexaAudioMetadata.Builder builder = new AlexaAudioMetadata.Builder();
            builder.setAlexaProfile(alexaAudioMetadata.getAlexaProfile());
            builder.setAlexaWakeWord(alexaWakeWord);
            if (alexaAudioMetadata.getAlexaAudioFormat() != null) {
                builder.setAudioFormat(alexaAudioMetadata.getAlexaAudioFormat());
            }
            return builder.build();
        }

        public final void zZm() {
            BOa.this.JTe = false;
            BOa.this.zyO.zZm(this.zZm);
            this.zyO.zyO = false;
            BOa.this.BIo();
        }
    }

    @VisibleForTesting
    public void zZm(NEe nEe, OGm oGm) {
        if (zZm()) {
            Log.w(zZm, "trying to start multiple wake word validation processes");
            this.BIo.zyO(kbp.zyO.zZm(nEe.jiA().getInvocationType(), ((NND) nEe.lOf()).BIo, bij.INTERNAL_CLIENT_ERROR_CONCURRENT_WAKEWORD_VERIFICATION));
            this.zyO.zZm(nEe);
            return;
        }
        Lzl jiA = oGm.jiA();
        Gcr zZm2 = jiA.zZm();
        if (zZm2.zyO) {
            Log.i(zZm, "starting wake word verification");
            this.JTe = true;
            this.Qle.zZm(zZm2.BIo, zZm2.zQM, new BIo(nEe, oGm, jiA, zZm2));
            return;
        }
        this.BIo.zyO(kbp.zyO.zZm(nEe.jiA().getInvocationType(), ((NND) nEe.lOf()).BIo, bij.INTERNAL_CLIENT_ERROR_WAKEWORD_VERIFICATION_BLOCKED));
        this.zyO.zZm(nEe);
    }

    public final void BIo() {
        zZm zzm = new zZm(null);
        this.jiA.addPrecondition(zzm, true);
        this.jiA.removePreconditions(zzm);
    }

    @VisibleForTesting
    public void zZm(NEe nEe) {
        this.BIo.zyO(QYV.Qle.zZm(nEe));
        this.BIo.zyO(EOM.zZm(nEe.Mlj(), YOj.VALIDATED));
    }

    @VisibleForTesting
    public boolean zZm() {
        return this.JTe;
    }

    public static /* synthetic */ void zZm(BOa bOa, Lzl lzl, byte[] bArr) {
        Aml zZm2 = bOa.zQM.zZm();
        try {
            OutputStream outputStream = zZm2.getOutputStream();
            outputStream.write(bArr);
            outputStream.flush();
            outputStream.close();
            lzl.zZm(zZm2.getAttachmentIdentifier());
        } catch (IOException e) {
            Log.e(zZm, "Failed to construct wake word engine metadata", e);
            bOa.zQM.BIo(zZm2.getAttachmentIdentifier());
        }
    }
}
