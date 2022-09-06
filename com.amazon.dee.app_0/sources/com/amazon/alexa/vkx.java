package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.AbstractC0238xdr;
import com.amazon.alexa.api.AlexaAttentionSystemListener;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.AlexaState;
import com.amazon.alexa.api.AlexaStateExtras;
import com.amazon.alexa.api.AlexaStateListenerProxy;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.fuM;
import com.amazon.alexa.kbp;
import com.amazon.alexa.utils.ApiThreadHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AlexaStateAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class vkx {
    public static final String zZm = "vkx";
    public final AlexaClientEventBus BIo;
    public String LPk;
    public Future<?> yPL;
    public final ScheduledExecutorService zQM;
    public final Shr<AlexaAttentionSystemListener> jiA = new Shr<>();
    public final CDz<AlexaStateListenerProxy> zyO = new CDz<>();
    public final TreeSet<wSq> Qle = new TreeSet<>();
    public wSq JTe = wSq.UNKNOWN;

    /* compiled from: AlexaStateAuthority.java */
    /* loaded from: classes.dex */
    private class zZm implements Runnable {
        public /* synthetic */ zZm(lOf lof) {
        }

        @Override // java.lang.Runnable
        public void run() {
            vkx.this.BIo.zyO(new yQC());
        }
    }

    @Inject
    public vkx(AlexaClientEventBus alexaClientEventBus, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService) {
        this.BIo = alexaClientEventBus;
        this.zQM = scheduledExecutorService;
        this.BIo.zZm(this);
        zyO(wSq.IDLE);
    }

    public void JTe() {
        this.BIo.zyO(new TWY(AbstractC0238xdr.zZm.CANCEL_USER_INTERACTION));
        BIo();
    }

    public void LPk() {
        this.BIo.BIo(this);
    }

    public void Qle() {
        if (EnumSet.of(wSq.LISTENING, wSq.PREPARING_TO_LISTEN).contains(this.JTe)) {
            this.BIo.zyO(fuM.zQM.zZm(fuM.zyO.BUTTON_PRESS));
        }
    }

    public void jiA() {
        if (EnumSet.of(wSq.PREPARING_TO_LISTEN, wSq.LISTENING, wSq.THINKING, wSq.PREPARING_TO_SPEAK, wSq.SPEAKING).contains(this.JTe)) {
            this.BIo.zyO(new TWY(AbstractC0238xdr.zZm.CANCEL_USER_INTERACTION));
        }
        BIo();
    }

    @Subscribe
    public void on(TTH tth) {
        if (((nAN) tth).jiA) {
            zQM(wSq.ERROR);
            synchronized (this) {
                this.yPL = this.zQM.schedule(new zZm(null), 2L, TimeUnit.SECONDS);
            }
        }
    }

    public final void yPL() {
        synchronized (this.Qle) {
            if (!this.Qle.isEmpty()) {
                zyO(this.Qle.first());
            } else {
                zyO(wSq.IDLE);
            }
        }
    }

    @VisibleForTesting
    public void zQM() {
        this.LPk = null;
    }

    public void BIo(wSq wsq) {
        C0179Pya.zZm("removeState: ", (Object) wsq);
        synchronized (this.Qle) {
            if (!this.Qle.remove(wsq)) {
                String str = zZm;
                StringBuilder sb = new StringBuilder();
                sb.append("Attempted to end ");
                sb.append(wsq);
                sb.append(", but it was not an active state");
                Log.w(str, sb.toString());
            }
            yPL();
        }
    }

    public final void zQM(wSq wsq) {
        C0179Pya.zZm("replaceActiveStates: ", (Object) wsq);
        synchronized (this.Qle) {
            this.Qle.clear();
            this.Qle.add(wsq);
            yPL();
        }
    }

    public void zZm(ExtendedClient extendedClient, AlexaStateListenerProxy alexaStateListenerProxy) {
        this.zyO.zZm(extendedClient, (ExtendedClient) alexaStateListenerProxy);
        ApiThreadHelper.runOnUiThread(new lOf(this, alexaStateListenerProxy, extendedClient));
    }

    public wSq zyO() {
        return this.JTe;
    }

    public final void zyO(wSq wsq) {
        Log.i(zZm, String.format("Alexa state change (%s -> %s)", this.JTe, wsq));
        wSq wsq2 = this.JTe;
        this.JTe = wsq;
        if (wsq2 == wsq) {
            Log.i(zZm, "Internal state is not changed, ignoring.");
            return;
        }
        if (wsq2 == wSq.LISTENING) {
            zQM();
        }
        this.BIo.jiA(new RUl(wsq));
        zZm();
        synchronized (this.Qle) {
            this.Qle.remove(wSq.ERROR);
        }
        if (wsq2.zZm().equals(wsq.zZm())) {
            Log.i(zZm, "Not updating state listeners. New external state is the same as the old state.");
            return;
        }
        AlexaState zZm2 = wsq.zZm();
        String str = zZm;
        Log.i(str, "Updating listeners with new state: " + zZm2);
        synchronized (this.zyO) {
            Iterator<T> it2 = this.zyO.iterator();
            while (it2.hasNext()) {
                ApiThreadHelper.runOnUiThread(new uzr(this, (AlexaStateListenerProxy) it2.next(), zZm2));
            }
        }
        synchronized (this.jiA) {
            Iterator<AlexaAttentionSystemListener> it3 = this.jiA.iterator();
            while (it3.hasNext()) {
                ApiThreadHelper.runOnUiThread(new HvC(this, it3.next(), zZm2));
            }
        }
    }

    public void zZm(ExtendedClient extendedClient, AlexaAttentionSystemListener alexaAttentionSystemListener) {
        if (!this.jiA.zZm((Shr<AlexaAttentionSystemListener>) alexaAttentionSystemListener)) {
            this.jiA.zZm(extendedClient, alexaAttentionSystemListener);
        }
        ApiThreadHelper.runOnUiThread(new dMe(this, alexaAttentionSystemListener));
    }

    @Subscribe
    public void on(gdD gdd) {
        synchronized (this) {
            this.yPL = null;
        }
        BIo(wSq.ERROR);
    }

    public void zZm(AlexaAttentionSystemListener alexaAttentionSystemListener) {
        this.jiA.remove(alexaAttentionSystemListener);
    }

    public static /* synthetic */ AlexaStateExtras zQM(vkx vkxVar) {
        if (vkxVar.JTe == wSq.LISTENING) {
            return new AlexaStateExtras(vkxVar.LPk);
        }
        return new AlexaStateExtras((String) null);
    }

    public final void BIo() {
        synchronized (this.Qle) {
            this.Qle.clear();
            yPL();
        }
    }

    public void zZm(AlexaStateListenerProxy alexaStateListenerProxy) {
        this.zyO.remove(alexaStateListenerProxy);
    }

    public void zZm(esV esv, AlexaDialogExtras alexaDialogExtras) {
        if (EnumSet.of(wSq.IDLE, wSq.SPEAKING, wSq.ERROR).contains(this.JTe)) {
            zZm(wSq.PREPARING_TO_LISTEN);
            this.BIo.zyO(new Yme(esv, AlexaDialogRequest.builder().setInvocationType(alexaDialogExtras.getInvocationType()).setLaunchType(alexaDialogExtras.getLaunchType()).build(), alexaDialogExtras));
            return;
        }
        this.BIo.zyO(kbp.zyO.zZm(alexaDialogExtras.getInvocationType(), mMl.OUT_OF_TURN_START_DIALOG_NOT_ALLOWED));
    }

    @Subscribe
    public void on(xZV xzv) {
        uyC uyc = (uyC) xzv;
        this.zyO.BIo(uyc.BIo);
        this.jiA.BIo(uyc.BIo);
    }

    public void zZm(ExtendedClient extendedClient, String str, AlexaDialogExtras alexaDialogExtras, eOP eop) {
        if (EnumSet.of(wSq.IDLE, wSq.SPEAKING, wSq.ERROR).contains(this.JTe)) {
            AlexaDialogRequest build = AlexaDialogRequest.builder().setInvocationType(alexaDialogExtras.getInvocationType()).setLaunchType(alexaDialogExtras.getLaunchType()).build();
            this.BIo.zyO(new UYO(extendedClient, str, esV.zZm(alexaDialogExtras.getLaunchType()), build, alexaDialogExtras, eop));
            return;
        }
        this.BIo.zyO(kbp.zyO.zZm(alexaDialogExtras.getInvocationType(), "", mMl.OUT_OF_TURN_START_DIALOG_NOT_ALLOWED, true));
    }

    public void zZm(wSq wsq) {
        C0179Pya.zZm("addState: ", (Object) wsq);
        synchronized (this.Qle) {
            this.Qle.add(wsq);
            StringBuilder sb = new StringBuilder();
            sb.append("  Current states: ");
            sb.append(this.Qle);
            sb.toString();
            yPL();
        }
    }

    public void zZm(String str) {
        GeneratedOutlineSupport1.outline158("setting wakeword: ", str);
        this.LPk = str;
    }

    public final void zZm() {
        synchronized (this) {
            if (this.yPL != null) {
                this.yPL.cancel(false);
                this.yPL = null;
            }
        }
    }
}
