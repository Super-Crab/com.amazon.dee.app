package com.amazon.alexa;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.PJz;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.uxs;
import java.io.IOException;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: PromptPlayer.java */
@Singleton
/* loaded from: classes.dex */
public class BrT {
    public static final String zZm = "BrT";
    public final Context BIo;
    public final JEn JTe;
    public final gSO LPk;
    public boolean Mlj = false;
    public final MediaPlayer Qle;
    public final MBE jiA;
    public final C0245zoo yPL;
    public final AlexaClientEventBus zQM;
    public final vkx zyO;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: PromptPlayer.java */
    /* loaded from: classes.dex */
    public class zZm extends zJO implements MediaPlayer.OnCompletionListener {
        public final Context JTe;
        public final MBE LPk;
        public final ZFm Qle;
        public final kSj jiA;

        public zZm(kSj ksj, Context context, AlexaClientEventBus alexaClientEventBus, vkx vkxVar, MBE mbe, ZFm zFm) {
            super(alexaClientEventBus, vkxVar);
            this.jiA = ksj;
            this.zQM.zZm(wSq.SPEAKING);
            this.JTe = context;
            this.LPk = mbe;
            this.Qle = zFm;
        }

        @Override // com.amazon.alexa.zJO
        public void LPk() {
            this.zQM.BIo(wSq.SPEAKING);
        }

        @Override // com.amazon.alexa.zJO, com.amazon.alexa.jDH
        public void jiA() {
            Resources resources;
            String zZm = ((C0217lcZ) BrT.this.JTe).zZm(this.Qle);
            String str = "file path of downloaded dynamic prompt: " + zZm;
            if (!zZm.isEmpty()) {
                BrT.this.zZm(zZm, this);
                return;
            }
            Log.i(BrT.zZm, "davs downloaded failed, playing default resource file");
            Locale Qle = this.LPk.Qle();
            if (Qle != null && !Locale.getDefault().equals(Qle)) {
                Configuration configuration = new Configuration();
                configuration.setLocale(Qle);
                resources = this.JTe.createConfigurationContext(configuration).getResources();
            } else {
                resources = this.JTe.getResources();
            }
            AssetFileDescriptor openRawResourceFd = resources.openRawResourceFd(this.jiA.zZm());
            if (openRawResourceFd != null) {
                BrT.this.zZm(openRawResourceFd, this);
            } else {
                JTe();
            }
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            JTe();
        }

        @Override // com.amazon.alexa.jDH
        public void zZm(IkF ikF) {
            BrT.this.Qle.setAudioAttributes(ikF.BIo);
        }
    }

    @Inject
    public BrT(Context context, AlexaClientEventBus alexaClientEventBus, vkx vkxVar, MediaPlayer mediaPlayer, MBE mbe, JEn jEn, gSO gso, C0245zoo c0245zoo) {
        this.BIo = context;
        this.zQM = alexaClientEventBus;
        this.Qle = mediaPlayer;
        this.zyO = vkxVar;
        this.jiA = mbe;
        this.JTe = jEn;
        this.LPk = gso;
        this.yPL = c0245zoo;
        alexaClientEventBus.zZm(this);
    }

    @Subscribe
    public void on(YHu yHu) {
        this.Mlj = true;
    }

    public void BIo() {
        this.zQM.BIo(this);
    }

    @Subscribe
    public void on(ycT yct) {
        this.Mlj = false;
    }

    @Subscribe
    public void on(FOR r3) {
        if (this.LPk.zZm(Feature.ALEXA_VOX_ANDROID_TTS_FOR_ISSUES)) {
            dCD dcd = (dCD) r3;
            if (dcd.BIo) {
                if (this.yPL.BIo()) {
                    zZm(kSj.NETWORK_TRANSITION_AUTO, ZFm.NETWORK_TRANSITION_AUTO, dcd.zyO);
                    this.zQM.zyO(uxs.zZm(uxs.zZm.NETWORK_TRANSITION_AUTO));
                    return;
                }
                zZm(kSj.NETWORK_TRANSITION_NON_AUTO, ZFm.NETWORK_TRANSITION_NON_AUTO, dcd.zyO);
                this.zQM.zyO(uxs.zZm(uxs.zZm.NETWORK_TRANSITION_NON_AUTO));
            } else if (dcd.zQM) {
                zZm(kSj.NETWORK_LOW_BANDWIDTH, ZFm.NETWORK_LOW_BANDWIDTH, dcd.zyO);
                this.zQM.zyO(uxs.zZm(uxs.zZm.NETWORK_LOW_BANDWIDTH));
            } else if (this.yPL.BIo()) {
                zZm(kSj.CONNECTIVITY_ISSUE_AUTO, ZFm.CONNECTIVITY_ISSUE_AUTO, dcd.zyO);
                this.zQM.zyO(uxs.zZm(uxs.zZm.CONNECTIVITY_ISSUE_AUTO));
            } else {
                zZm(kSj.CONNECTIVITY_ISSUE_NON_AUTO, ZFm.CONNECTIVITY_ISSUE_NON_AUTO, dcd.zyO);
                this.zQM.zyO(uxs.zZm(uxs.zZm.CONNECTIVITY_ISSUE_NON_AUTO));
            }
        } else if (this.Mlj) {
        } else {
            zZm(kSj.NOT_CONNECTED, ZFm.NOT_CONNECTED, ((dCD) r3).zyO);
            this.zQM.zyO(uxs.zZm(uxs.zZm.NOT_CONNECTED));
        }
    }

    public final void zZm() {
        if (this.Mlj) {
            zZm(kSj.ALEXA_DOWN, ZFm.ALEXA_DOWN, DialogRequestIdentifier.NONE);
            this.zQM.zyO(uxs.zZm(uxs.zZm.ALEXA_DOWN));
        }
    }

    public final void zZm(kSj ksj, ZFm zFm, DialogRequestIdentifier dialogRequestIdentifier) {
        this.zQM.zyO(mZe.zZm(aVo.DIALOG, new zZm(ksj, this.BIo, this.zQM, this.zyO, this.jiA, zFm), PJz.zZm(PJz.zQM.MUSIC, PJz.BIo.TRANSIENT_EXCLUSIVE, PJz.zyO.MEDIA, PJz.zZm.MUSIC), dialogRequestIdentifier));
    }

    public final void zZm(@NonNull AssetFileDescriptor assetFileDescriptor, @NonNull zZm zzm) {
        try {
            this.Qle.reset();
            this.Qle.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            assetFileDescriptor.close();
            this.Qle.setOnCompletionListener(zzm);
            this.Qle.prepare();
            this.Qle.start();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public final void zZm(@NonNull String str, @NonNull zZm zzm) {
        try {
            this.Qle.reset();
            this.Qle.setDataSource(str);
            this.Qle.setOnCompletionListener(zzm);
            this.Qle.prepare();
            this.Qle.start();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    @Subscribe
    public void on(cXw cxw) {
        if (!((gLt) cxw).BIo) {
            zZm();
        }
    }

    @Subscribe
    public void on(cre creVar) {
        zZm();
    }

    @Subscribe
    public void on(CKO cko) {
        if (!((IyB) cko).BIo.equals(AlexaPlayerInfoState.ERROR) || this.Mlj) {
            return;
        }
        zZm(kSj.LOST_CONNECTION, ZFm.LOST_CONNECTION, DialogRequestIdentifier.NONE);
        this.zQM.zyO(uxs.zZm(uxs.zZm.LOST_CONNECTION));
    }

    @Subscribe
    public void on(hyR hyr) {
        zZm();
    }
}
