package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.uTP;
import com.google.gson.Gson;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AudioPlayerComponentStateAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class oGE extends Bwo {
    public static final long BIo = TimeUnit.MILLISECONDS.convert(2, TimeUnit.HOURS);
    public xNT JTe;
    public Bha LPk;
    public long Mlj;
    public AlexaPlayerInfoState Qle;
    public final zZm jiA;
    public Puy yPL;
    public final AlexaClientEventBus zQM;
    public final uTP zyO;
    public AUQ zzR;

    @Inject
    public oGE(AlexaClientEventBus alexaClientEventBus, UCV ucv) {
        super(AvsApiConstants.AudioPlayer.zZm, AvsApiConstants.AudioPlayer.ComponentStates.PlaybackState.zZm);
        this.yPL = Puy.zZm("");
        this.Mlj = 0L;
        this.zzR = AUQ.IDLE;
        this.zQM = alexaClientEventBus;
        this.jiA = new zZm(null);
        this.zyO = ucv.zZm("audio_player_state", BIo);
        this.zyO.zZm((uTP.zZm) this.jiA);
        alexaClientEventBus.zZm(this);
    }

    @Override // com.amazon.alexa.dRG
    public synchronized ComponentState getState() {
        this.zyO.BIo();
        return ComponentState.create(zZm(), BIo());
    }

    @Subscribe
    public synchronized void on(CKO cko) {
        xNT xnt;
        if (this.Qle != null || (xnt = this.JTe) == null || !((IyB) cko).zQM.equals(xnt)) {
            this.Qle = ((IyB) cko).BIo;
            this.JTe = ((IyB) cko).zQM;
            this.zyO.zZm((uTP.BIo) this.jiA);
        }
    }

    public synchronized Vma BIo() {
        Puy puy;
        long j;
        AUQ auq;
        this.zyO.BIo();
        jiA();
        puy = this.yPL;
        j = this.Mlj;
        auq = this.zzR;
        if (auq == AUQ.PAUSED) {
            auq = AUQ.PLAYING;
        }
        return Vma.zZm(puy, j, auq);
    }

    public final void jiA() {
        if (this.zzR == AUQ.PLAYING) {
            Bha bha = this.LPk;
            long j = 0;
            long Qle = bha == null ? 0L : bha.yPL.Qle();
            if (Qle > 0) {
                j = Qle;
            }
            this.Mlj = j;
        }
    }

    public Puy zQM() {
        return this.yPL;
    }

    public synchronized void zyO() {
        this.yPL = Puy.zZm("");
        this.Mlj = 0L;
        this.zzR = AUQ.IDLE;
        this.zQM.zyO(AgS.zZm(true));
    }

    public synchronized void zZm(@Nullable Bha bha) {
        this.LPk = bha;
    }

    public synchronized void zZm(@Nullable Puy puy, @Nullable AUQ auq, long j) {
        this.zyO.BIo();
        if (puy != null) {
            this.yPL = puy;
        }
        if (auq != null) {
            this.zzR = auq;
        }
        if (j >= 0) {
            this.Mlj = j;
        }
    }

    @Subscribe
    public synchronized void on(fMa fma) {
        xNT xnt;
        AlexaPlayerInfoState alexaPlayerInfoState = this.Qle;
        if (alexaPlayerInfoState != null && (xnt = this.JTe) != null) {
            this.zQM.zyO(CKO.zZm(alexaPlayerInfoState, xnt, this.Mlj));
        }
    }

    public synchronized void zZm(kQf kqf, AUQ auq) {
        this.zyO.BIo();
        jiA();
        zZm(kqf.mo947BIo(), auq, this.Mlj);
    }

    @Subscribe
    public synchronized void on(AgS agS) {
        if (((UuG) agS).BIo) {
            this.zyO.zZm();
            this.Qle = null;
        }
    }

    @Subscribe
    public synchronized void on(TSb tSb) {
        C0169GLa c0169GLa = (C0169GLa) tSb;
        if (c0169GLa.BIo.name().equals(aVo.CONTENT.name()) && AvsApiConstants.ExternalMediaPlayer.BIo.equals(c0169GLa.zyO)) {
            this.zQM.zyO(AgS.zZm(true));
        }
    }

    /* compiled from: AudioPlayerComponentStateAuthority.java */
    /* loaded from: classes.dex */
    private class zZm implements uTP.zZm, uTP.BIo {
        public /* synthetic */ zZm(cWz cwz) {
        }

        @Override // com.amazon.alexa.uTP.zZm
        public void zZm(PersistentStorage persistentStorage, Gson gson) {
            synchronized (oGE.this) {
                String string = persistentStorage.getString("lastPlayToken", "");
                oGE oge = oGE.this;
                if (string == null) {
                    string = "";
                }
                oge.yPL = Puy.zZm(string);
                long j = 0;
                long j2 = persistentStorage.getLong("lastPlayerOffset", 0L);
                oGE oge2 = oGE.this;
                if (j2 > 0) {
                    j = j2;
                }
                oge2.Mlj = j;
                String string2 = persistentStorage.getString("lastPlayerInfoState");
                xNT xnt = null;
                oGE.this.Qle = string2 == null ? null : AlexaPlayerInfoState.valueOf(string2);
                String string3 = persistentStorage.getString("lastPlayerActivityState");
                oGE.this.zzR = string3 == null ? AUQ.IDLE : AUQ.valueOf(string3);
                String string4 = persistentStorage.getString("lastAudioItem");
                oGE oge3 = oGE.this;
                if (string4 != null) {
                    xnt = xNT.zZm(string4);
                }
                oge3.JTe = xnt;
                if (EnumSet.of(AlexaPlayerInfoState.PLAYING, AlexaPlayerInfoState.PAUSED, AlexaPlayerInfoState.BUFFERING).contains(oGE.this.Qle)) {
                    oGE.this.Qle = AlexaPlayerInfoState.DONE;
                    oGE.this.zzR = AUQ.STOPPED;
                }
                if (oGE.this.JTe != null && oGE.this.Qle != null) {
                    oGE.this.zQM.zyO(CKO.zZm(oGE.this.Qle, oGE.this.JTe, oGE.this.Mlj));
                }
            }
        }

        @Override // com.amazon.alexa.uTP.BIo
        public void zZm(PersistentStorage.Transaction transaction, Gson gson) {
            synchronized (oGE.this) {
                transaction.set("lastPlayToken", oGE.this.yPL.zZm).set("lastPlayerOffset", oGE.this.Mlj).set("lastPlayerInfoState", oGE.this.Qle.name()).set("lastAudioItem", oGE.this.JTe.getValue()).set("lastPlayerActivityState", oGE.this.zzR.name());
            }
        }
    }
}
