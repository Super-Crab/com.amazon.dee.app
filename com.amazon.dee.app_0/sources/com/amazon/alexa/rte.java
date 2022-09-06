package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.BaP;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.kbp;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: VoiceInteractionAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class rte extends ZOR {
    public static final String Qle = "rte";

    @Inject
    public rte(AlexaClientEventBus alexaClientEventBus, Wiq wiq) {
        super(alexaClientEventBus, wiq, ExecutorFactory.newSingleThreadExecutor("v-i-tracker"));
    }

    @Subscribe
    public void on(MyZ myZ) {
        zZm(myZ);
    }

    @Override // com.amazon.alexa.ZOR
    public String zZm() {
        return Qle;
    }

    @Subscribe
    public void on(GSR gsr) {
        CSi cSi = (CSi) gsr;
        if (cSi.jiA) {
            return;
        }
        zZm(this.zQM.zZm(cSi.BIo, cSi.zQM, cSi.zyO, gsr.zZm));
    }

    @Subscribe
    public void on(GUc gUc) {
        ghu ghuVar = (ghu) gUc;
        zZm(this.zQM.zZm(ghuVar.BIo, ghuVar.zQM, ghuVar.zyO));
    }

    @Subscribe
    public void on(EOM eom) {
        jcN jcn = (jcN) eom;
        XWx xWx = jcn.BIo;
        DialogRequestIdentifier dialogRequestIdentifier = jcn.zQM;
        if (xWx == null && dialogRequestIdentifier == null) {
            Log.w(Qle, "invalid request to update voice interaction progress");
        } else if (xWx != null) {
            zZm(this.zQM.zZm(xWx, jcn.zyO));
        } else {
            zZm(this.zQM.zZm(dialogRequestIdentifier, jcn.zyO));
        }
    }

    @Subscribe
    public void on(kbp.jiA jia) {
        C0200dqd c0200dqd = (C0200dqd) jia;
        if (c0200dqd.zQM) {
            return;
        }
        zZm(this.zQM.zZm(c0200dqd.BIo, jia.zZm));
    }

    @Subscribe
    public void on(kbp.BIo bIo) {
        sbP sbp = (sbP) bIo;
        zZm(this.zQM.BIo(sbp.BIo, sbp.zQM, bIo.zZm));
    }

    @Subscribe
    public void on(kbp.zZm zzm) {
        C0192cdA c0192cdA = (C0192cdA) zzm;
        if (c0192cdA.jiA) {
            return;
        }
        zZm(this.zQM.zZm(c0192cdA.BIo, c0192cdA.zyO, zzm.zZm));
    }

    @Subscribe
    public void on(kbp.zQM zqm) {
        gMf gmf = (gMf) zqm;
        if (gmf.jiA) {
            return;
        }
        zZm(this.zQM.zZm(gmf.BIo, gmf.zQM, gmf.zyO, zqm.zZm));
    }

    @Subscribe
    public void on(kbp.zyO zyo) {
        WwG wwG = (WwG) zyo;
        if (wwG.jiA) {
            return;
        }
        String str = wwG.BIo;
        String str2 = wwG.zQM;
        Pmp pmp = Pmp.FAILURE;
        pRk prk = wwG.zyO;
        this.zZm.zyO(BaP.zZm.zZm(str, DialogRequestIdentifier.NONE, str2));
        this.zZm.zyO(BaP.zQM.zZm(str, DialogRequestIdentifier.NONE, pmp, prk, null, 0L));
    }
}
