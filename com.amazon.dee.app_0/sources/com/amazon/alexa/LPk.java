package com.amazon.alexa;

import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.AbstractC0238xdr;
import com.amazon.alexa.QJr;
import com.amazon.alexa.api.AlexaAudioChannel;
import com.amazon.alexa.api.AlexaAudioPlaybackListenerProxy;
import com.amazon.alexa.api.AlexaAudioPlaybackStatusListener;
import com.amazon.alexa.api.AlexaPlaybackState;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.ApiThreadHelper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AlexaAudioPlaybackAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class LPk {
    public static final String zZm = "LPk";
    public final QJr JTe;
    public final AlexaClientEventBus Qle;
    public final SKB jiA;
    public final fla zyO;
    public final CDz<AlexaAudioPlaybackListenerProxy> BIo = new CDz<>();
    public final Shr<AlexaAudioPlaybackStatusListener> zQM = new Shr<>();
    public AlexaPlaybackState yPL = AlexaPlaybackState.NONE;
    public final Map<AlexaAudioChannel, Boolean> LPk = new HashMap();

    /* compiled from: AlexaAudioPlaybackAuthority.java */
    /* loaded from: classes.dex */
    private class zZm implements QJr.BIo {
        public /* synthetic */ zZm(JTe jTe) {
        }

        @Override // com.amazon.alexa.QJr.BIo
        public void zZm() {
            LPk.this.jiA();
        }
    }

    @Inject
    public LPk(AlexaClientEventBus alexaClientEventBus, SKB skb, fla flaVar, QJr qJr) {
        this.Qle = alexaClientEventBus;
        this.zyO = flaVar;
        this.jiA = skb;
        this.JTe = qJr;
        QJr qJr2 = this.JTe;
        qJr2.zyO.add(new zZm(null));
        alexaClientEventBus.zZm(this);
    }

    @VisibleForTesting
    public void jiA() {
        try {
            boolean z = false;
            for (Map.Entry<aVo, aNh> entry : this.JTe.JTe().entrySet()) {
                Boolean valueOf = Boolean.valueOf(((hgr) entry.getValue()).zQM);
                AlexaAudioChannel valueOf2 = AlexaAudioChannel.valueOf(entry.getKey().name());
                if (this.LPk.get(valueOf2) != valueOf) {
                    z = true;
                }
                this.LPk.put(valueOf2, valueOf);
            }
            if (!z) {
                return;
            }
            Iterator<AlexaAudioPlaybackStatusListener> it2 = this.zQM.iterator();
            while (it2.hasNext()) {
                it2.next().onAudioPlaybackStatusChanged(this.LPk);
            }
        } catch (Exception e) {
            Log.e(zZm, "Exception while updating audio playback status", e);
        }
    }

    @Subscribe
    public void on(qZM qzm) {
        CYr cYr = (CYr) qzm;
        if (this.yPL.equals(cYr.BIo)) {
            return;
        }
        this.yPL = cYr.BIo;
        Iterator<T> it2 = this.BIo.iterator();
        while (it2.hasNext()) {
            ApiThreadHelper.runOnUiThread(new JTe(this, (AlexaAudioPlaybackListenerProxy) it2.next()));
        }
    }

    public void zyO() {
        this.zyO.zQM();
    }

    public void BIo() {
        if (this.jiA.zZm()) {
            this.Qle.zyO(new mzc());
            return;
        }
        AlexaPlaybackState alexaPlaybackState = this.yPL;
        if (alexaPlaybackState != AlexaPlaybackState.STOPPABLE_AND_NAVIGABLE && alexaPlaybackState != AlexaPlaybackState.STOPPABLE) {
            return;
        }
        this.zyO.zZm.zyO(new TWY(AbstractC0238xdr.zZm.PAUSE_CONTROL));
    }

    public void zQM() {
        if (this.yPL == AlexaPlaybackState.NONE) {
            this.zyO.BIo();
        }
    }

    public void zZm(ExtendedClient extendedClient, AlexaAudioPlaybackListenerProxy alexaAudioPlaybackListenerProxy) {
        this.BIo.zZm(extendedClient, (ExtendedClient) alexaAudioPlaybackListenerProxy);
        try {
            alexaAudioPlaybackListenerProxy.onAudioPlaybackChanged(this.yPL);
        } catch (RemoteException e) {
            Log.e(zZm, "Remote exception while registering audio playback listener", e);
            this.Qle.zyO(xZV.zZm(extendedClient));
        }
    }

    public void zZm(AlexaAudioPlaybackListenerProxy alexaAudioPlaybackListenerProxy) {
        ExtendedClient remove = this.BIo.remove(alexaAudioPlaybackListenerProxy);
        if (remove != null) {
            StringBuilder zZm2 = C0179Pya.zZm("Deregistering audio playback listener for client: ");
            zZm2.append(remove.getId());
            zZm2.toString();
        }
    }

    @Subscribe
    public void on(xZV xzv) {
        this.BIo.BIo(((uyC) xzv).BIo);
    }

    public void zZm(ExtendedClient extendedClient, AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        this.zQM.zZm(extendedClient, alexaAudioPlaybackStatusListener);
        if (!this.LPk.isEmpty()) {
            alexaAudioPlaybackStatusListener.onAudioPlaybackStatusChanged(this.LPk);
        }
    }

    public void zZm(AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        ExtendedClient remove = this.zQM.remove(alexaAudioPlaybackStatusListener);
        if (remove != null) {
            StringBuilder zZm2 = C0179Pya.zZm("Deregistering audio playback status listener for client: ");
            zZm2.append(remove.getId());
            zZm2.toString();
        }
    }

    public void zZm() {
        this.zyO.zZm();
    }
}
