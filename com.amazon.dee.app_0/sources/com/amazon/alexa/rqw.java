package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.NEv;
import com.amazon.alexa.api.AlexaDriveModeListener;
import com.amazon.alexa.api.DriveModeState;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectorProvider;
import com.amazon.pryon.android.asr.PryonLite5000;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: DriveModeAuthority.java */
/* loaded from: classes.dex */
public class rqw {
    public static final String zZm = "rqw";
    public final AlexaClientEventBus BIo;
    public volatile boolean Qle;
    public final WakeWordDetectorProvider jiA;
    public final Shr<AlexaDriveModeListener> zQM = new Shr<>();
    public final Set<ExtendedClient> zyO = new HashSet();
    public volatile boolean LPk = true;
    public volatile DriveModeState JTe = DriveModeState.NONE;

    @Inject
    public rqw(AlexaClientEventBus alexaClientEventBus, WakeWordDetectorProvider wakeWordDetectorProvider) {
        this.BIo = alexaClientEventBus;
        this.jiA = wakeWordDetectorProvider;
        this.BIo.zZm(this);
    }

    public String BIo() {
        int i = jSz.zZm[this.JTe.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? "None" : "Default" : "AutoBluetooth" : "Accessory";
    }

    @Subscribe
    public void on(DqQ dqQ) {
        EOT eot = (EOT) dqQ;
        ExtendedClient extendedClient = eot.zQM;
        AlexaDriveModeListener alexaDriveModeListener = eot.jiA;
        if (!this.zQM.zZm((Shr<AlexaDriveModeListener>) alexaDriveModeListener)) {
            this.zQM.zZm(extendedClient, alexaDriveModeListener);
            alexaDriveModeListener.onDriveModeEnabled(this.Qle);
            alexaDriveModeListener.onDriveModeState(this.JTe);
            alexaDriveModeListener.onDriveModeThemeChanged(this.LPk);
        }
        zZm(eot.BIo);
    }

    public boolean zQM() {
        return this.Qle;
    }

    public final void zZm() {
        if (!this.zQM.isEmpty() || !this.zyO.isEmpty()) {
            return;
        }
        this.Qle = false;
        this.JTe = DriveModeState.NONE;
    }

    public final void zZm(eOP eop) {
        this.BIo.zyO(NEv.zQM.zZm(eop));
    }

    @Subscribe
    public void on(lyG lyg) {
        vly vlyVar = (vly) lyg;
        this.zQM.remove(vlyVar.jiA);
        zZm();
        zZm(vlyVar.BIo);
    }

    @Subscribe
    public void on(ShT shT) {
        int i;
        zZD zzd = (zZD) shT;
        ExtendedClient extendedClient = zzd.zQM;
        boolean z = zzd.jiA;
        if (this.Qle != z) {
            this.Qle = z;
            if (z) {
                Log.i(zZm, "setEnabled | setting pryon client automotive property to active");
                this.zyO.add(extendedClient);
                i = 1;
            } else {
                Log.i(zZm, "setEnabled | setting pryon client automotive property to in-active");
                this.zyO.remove(extendedClient);
                i = 0;
            }
            this.jiA.setClientProperty(PryonLite5000.ClientProperty.AUTOMOTIVE_MODE, i);
            for (AlexaDriveModeListener alexaDriveModeListener : this.zQM.zZm()) {
                alexaDriveModeListener.onDriveModeEnabled(z);
            }
        }
        zZm(zzd.BIo);
    }

    @Subscribe
    public void on(AbstractC0230smc abstractC0230smc) {
        BPW bpw = (BPW) abstractC0230smc;
        ExtendedClient extendedClient = bpw.zQM;
        DriveModeState driveModeState = bpw.jiA;
        if (this.JTe != driveModeState) {
            this.JTe = driveModeState;
            this.zyO.add(extendedClient);
            for (AlexaDriveModeListener alexaDriveModeListener : this.zQM.zZm()) {
                alexaDriveModeListener.onDriveModeState(driveModeState);
            }
        }
        zZm(bpw.BIo);
    }

    @Subscribe
    public void on(Tkm tkm) {
        TEg tEg = (TEg) tkm;
        boolean z = tEg.jiA;
        if (this.LPk != z) {
            this.LPk = z;
            for (AlexaDriveModeListener alexaDriveModeListener : this.zQM.zZm()) {
                alexaDriveModeListener.onDriveModeThemeChanged(z);
            }
        }
        zZm(tEg.BIo);
    }

    @Subscribe
    public void on(xZV xzv) {
        uyC uyc = (uyC) xzv;
        this.zQM.BIo(uyc.BIo);
        this.zyO.remove(uyc.BIo);
        zZm();
    }
}
