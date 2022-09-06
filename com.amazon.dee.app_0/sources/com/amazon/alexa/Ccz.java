package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.ARM;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.kbp;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: TextInteractionAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class Ccz extends ZOR {
    public static final String Qle = "Ccz";

    @Inject
    public Ccz(AlexaClientEventBus alexaClientEventBus, qHS qhs) {
        super(alexaClientEventBus, qhs, ExecutorFactory.newSingleThreadExecutor("t-i-tracker"));
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
        if (!cSi.jiA) {
            return;
        }
        zZm(this.zQM.zZm(cSi.BIo, cSi.zQM, cSi.zyO, gsr.zZm));
    }

    @Subscribe
    public void on(urz urzVar) {
        tai taiVar = (tai) urzVar;
        zZm(this.zQM.zZm(taiVar.BIo, taiVar.zQM, taiVar.zyO));
    }

    @Subscribe
    public void on(kbp.jiA jia) {
        C0200dqd c0200dqd = (C0200dqd) jia;
        if (!c0200dqd.zQM) {
            return;
        }
        zZm(this.zQM.zZm(c0200dqd.BIo, jia.zZm));
    }

    @Subscribe
    public void on(kbp.zZm zzm) {
        C0192cdA c0192cdA = (C0192cdA) zzm;
        if (!c0192cdA.jiA) {
            return;
        }
        XWx xWx = c0192cdA.BIo;
        DialogRequestIdentifier dialogRequestIdentifier = c0192cdA.zQM;
        if (xWx == null && dialogRequestIdentifier == null) {
            Log.w(Qle, "invalid request to abandon text interaction");
        } else if (xWx != null) {
            zZm(this.zQM.zZm(xWx, c0192cdA.zyO, zzm.zZm));
        } else {
            zZm(this.zQM.zZm(dialogRequestIdentifier, c0192cdA.zyO, zzm.zZm));
        }
    }

    @Subscribe
    public void on(kbp.zQM zqm) {
        gMf gmf = (gMf) zqm;
        if (!gmf.jiA) {
            return;
        }
        zZm(this.zQM.zZm(gmf.BIo, gmf.zQM, gmf.zyO, zqm.zZm));
    }

    @Subscribe
    public void on(kbp.zyO zyo) {
        WwG wwG = (WwG) zyo;
        if (!wwG.jiA) {
            return;
        }
        String str = wwG.BIo;
        String str2 = wwG.zQM;
        Pmp pmp = Pmp.FAILURE;
        pRk prk = wwG.zyO;
        this.zZm.zyO(ARM.zZm.zZm(str, str2));
        this.zZm.zyO(ARM.BIo.zZm(str, DialogRequestIdentifier.NONE, pmp, prk, null, 0L));
    }
}
