package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.NEv;
import com.amazon.alexa.QYV;
import com.amazon.alexa.api.AlexaExpectTextListener;
import com.amazon.alexa.api.AlexaTextResponseListener;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.api.TextResponse;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.kbp;
import dagger.Lazy;
import java.util.Iterator;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: TextAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class yjS {
    public static final String zZm = "yjS";
    public final AlexaClientEventBus BIo;
    public final Lazy<PersistentStorage> JTe;
    public final Wyh Qle;
    public final vkx jiA;
    public final Shr<AlexaTextResponseListener> zQM = new Shr<>();
    public final Shr<AlexaExpectTextListener> zyO = new Shr<>();

    @Inject
    public yjS(AlexaClientEventBus alexaClientEventBus, vkx vkxVar, Wyh wyh, @Named("TWA_SHARED_PREFS") Lazy<PersistentStorage> lazy) {
        this.BIo = alexaClientEventBus;
        this.jiA = vkxVar;
        this.Qle = wyh;
        this.JTe = lazy;
        this.BIo.zZm(this);
    }

    public void BIo() {
        this.BIo.BIo(this);
    }

    @Subscribe
    public void deregisterAlexaExpectTextListener(SbW sbW) {
        Crs crs = (Crs) sbW;
        ExtendedClient remove = this.zyO.remove(crs.jiA);
        if (remove != null) {
            StringBuilder zZm2 = C0179Pya.zZm("Deregistering Alexa expect text listener for client: ");
            zZm2.append(remove.getId());
            zZm2.toString();
        }
        zZm(crs.BIo);
    }

    @Subscribe
    public void deregisterAlexaTextResponseListener(KMu kMu) {
        KLb kLb = (KLb) kMu;
        ExtendedClient remove = this.zQM.remove(kLb.jiA);
        if (remove != null) {
            StringBuilder zZm2 = C0179Pya.zZm("Deregistering Alexa text response listener for client: ");
            zZm2.append(remove.getId());
            zZm2.toString();
        }
        zZm(kLb.BIo);
    }

    @Subscribe
    public void on(QYV.zyO zyo) {
        Log.i(zZm, "Received a text request dialog event to start sending text message");
        UYO uyo = (UYO) zyo;
        iZJ izj = new iZJ(uyo.zQM, uyo.JTe);
        if (!xrg.zZm(this.jiA)) {
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Cannot request dialog to alexa. Invalid Alexa State: ");
            zZm2.append(this.jiA.zyO());
            Log.w(str, zZm2.toString());
            this.BIo.zyO(kbp.zyO.zZm(uyo.jiA.getInvocationType(), izj.zZm(), mMl.OUT_OF_TURN_CANNOT_REQUEST_DIALOG, true));
            return;
        }
        ExtendedClient extendedClient = uyo.BIo;
        Yhs yhs = new Yhs(izj);
        gDB gdb = new gDB(izj);
        String zZm3 = izj.zZm();
        piE pie = piE.BIo;
        if (zZm3 == null) {
            zZm3 = "";
        }
        NEe BIo = this.Qle.BIo(extendedClient, yhs, gdb, new NND(pie, zZm3, null));
        BIo.zZm(uyo.Qle);
        if (BIo.zZm(uyo.zyO, uyo.jiA)) {
            return;
        }
        Log.w(zZm, "Cannot request first turn on the multi-turn dialog");
        this.Qle.zZm(BIo);
        this.BIo.zyO(kbp.zyO.zZm(uyo.jiA.getInvocationType(), izj.zZm(), mMl.OUT_OF_TURN_CANNOT_REQUEST_FIRST_TURN, true));
    }

    @Subscribe
    public void registerAlexaExpectTextListener(Ezv ezv) {
        pwz pwzVar = (pwz) ezv;
        this.zyO.zZm(pwzVar.zQM, pwzVar.jiA);
        zZm(pwzVar.BIo);
    }

    @Subscribe
    public void registerAlexaTextResponseListener(Kkr kkr) {
        Svl svl = (Svl) kkr;
        this.zQM.zZm(svl.zQM, svl.jiA);
        zZm(svl.BIo);
    }

    @Subscribe
    public void sendTextMessage(uxJ uxj) {
        Jpe jpe = (Jpe) uxj;
        this.jiA.zZm(jpe.zQM, jpe.jiA, jpe.Qle, jpe.BIo);
    }

    public final void zZm(eOP eop) {
        this.BIo.zyO(NEv.zQM.zZm(eop));
    }

    public void zZm() {
        this.JTe.mo358get().edit().clear().commitSynchronously();
    }

    @Subscribe
    public void on(AbstractC0208iNL abstractC0208iNL) {
        TextResponse textResponse = ((fru) abstractC0208iNL).BIo;
        Iterator<AlexaTextResponseListener> it2 = this.zQM.iterator();
        while (it2.hasNext()) {
            it2.next().onTextReceived(textResponse);
        }
    }

    @Subscribe
    public void on(PYl pYl) {
        Iterator<AlexaExpectTextListener> it2 = this.zyO.iterator();
        while (it2.hasNext()) {
            it2.next().onExpectTextReceived();
        }
    }
}
