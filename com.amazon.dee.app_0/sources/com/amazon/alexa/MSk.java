package com.amazon.alexa;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.PJz;
import com.amazon.alexa.api.AlexaAudioChannel;
import com.amazon.alexa.api.AlexaAudioInteractionProxy;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: ExternalInteraction.java */
/* loaded from: classes.dex */
public class MSk extends jDH {
    public static final String BIo = "MSk";
    public final ExtendedClient jiA;
    public final AlexaAudioInteractionProxy zQM;
    public final AlexaClientEventBus zyO;

    public MSk(IYE iye, AlexaAudioInteractionProxy alexaAudioInteractionProxy, AlexaClientEventBus alexaClientEventBus, ExtendedClient extendedClient) {
        super(iye);
        this.zQM = alexaAudioInteractionProxy;
        this.zyO = alexaClientEventBus;
        this.jiA = extendedClient;
        alexaClientEventBus.zZm(this);
    }

    @Override // com.amazon.alexa.ndD
    public dnp BIo() {
        try {
            return dnp.zZm(this.zQM.getInteractionComponentName());
        } catch (RemoteException e) {
            zZm(e);
            return dnp.zZm;
        }
    }

    public PJz JTe() {
        return PJz.zZm(PJz.zQM.EXTERNAL_STREAM, PJz.BIo.NO_AUDIOFOCUS);
    }

    public aVo LPk() {
        AlexaAudioChannel alexaAudioChannel;
        try {
            alexaAudioChannel = this.zQM.getAlexaAudioChannel();
        } catch (RemoteException e) {
            zZm(e);
            alexaAudioChannel = null;
        }
        if (AlexaAudioChannel.ALERTS.equals(alexaAudioChannel)) {
            return aVo.ALERTS;
        }
        if (AlexaAudioChannel.DIALOG.equals(alexaAudioChannel)) {
            return aVo.DIALOG;
        }
        if (AlexaAudioChannel.CONTENT.equals(alexaAudioChannel)) {
            return aVo.CONTENT;
        }
        if (AlexaAudioChannel.COMMUNICATIONS.equals(alexaAudioChannel)) {
            return aVo.COMMUNICATIONS;
        }
        if (!AlexaAudioChannel.IMPORTANT.equals(alexaAudioChannel)) {
            return null;
        }
        return aVo.IMPORTANT;
    }

    @Override // com.amazon.alexa.jDH
    public void Qle() {
        try {
            this.zQM.onPause();
        } catch (RemoteException e) {
            zZm(e);
        }
    }

    @Override // com.amazon.alexa.jDH
    public void jiA() {
        try {
            this.zQM.onForeground();
        } catch (RemoteException e) {
            zZm(e);
        }
    }

    @Subscribe
    public void on(xZV xzv) {
        if (((uyC) xzv).BIo.equals(this.jiA)) {
            this.zyO.zyO(LBB.zZm(zZm()));
        }
    }

    public void yPL() {
        this.zyO.zyO(mZe.zZm(LPk(), this, JTe(), DialogRequestIdentifier.NONE));
    }

    @Override // com.amazon.alexa.ndD
    public void zQM() {
        try {
            this.zQM.onStop();
        } catch (RemoteException e) {
            zZm(e);
        }
    }

    public final void zZm(RemoteException remoteException) {
        Log.e(BIo, "RemoteException while informing external interaction lifecycle events", remoteException);
        this.zyO.zyO(xZV.zZm(this.jiA));
    }

    @Override // com.amazon.alexa.jDH
    public void zyO() {
        try {
            this.zQM.onBackground();
        } catch (RemoteException e) {
            zZm(e);
        }
    }

    @Subscribe
    public void on(LBB lbb) {
        if (((IDp) lbb).BIo.equals(zZm())) {
            this.zyO.BIo(this);
        }
    }
}
