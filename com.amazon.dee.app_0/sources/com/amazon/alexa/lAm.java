package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: EstablishDownchannelChain.java */
@Singleton
/* loaded from: classes.dex */
public class lAm implements yDI {
    public static final String zZm = "lAm";
    @VisibleForTesting
    public final yDI BIo;
    public IHN JTe = IHN.zZm;
    public final AlexaClientEventBus Qle;
    public final qxC jiA;
    public final dAN zQM;
    public final WnL zyO;

    @Inject
    public lAm(dAN dan, @Named("PUBLISH_CAPABILITIES_CHAIN") yDI ydi, qxC qxc, WnL wnL, AlexaClientEventBus alexaClientEventBus) {
        this.zQM = dan;
        this.zyO = wnL;
        this.jiA = qxc;
        this.Qle = alexaClientEventBus;
        this.BIo = ydi;
        this.Qle.zZm(this);
    }

    @Subscribe
    public void on(mUQ muq) {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("on DownchannelStateEvent: ");
        hVb hvb = (hVb) muq;
        zZm2.append(hvb.BIo);
        Log.i(str, zZm2.toString());
        WnL wnL = this.zyO;
        boolean z = hvb.BIo;
        wnL.JTe = !z;
        if (z) {
            wnL.zQM = false;
            wnL.BIo = false;
            return;
        }
        Log.i(zZm, "Downchannel is not established, waiting for downchannel establishment");
        this.zyO.jiA();
    }

    @Override // com.amazon.alexa.yDI
    public void teardown() {
        this.Qle.BIo(this);
        this.BIo.teardown();
    }

    @Override // com.amazon.alexa.yDI
    public void zZm(IHN ihn) {
        if (!this.zyO.JTe && !this.zyO.Qle) {
            Log.i(zZm, "Downchannel is already connected. Moving to the next chain.");
            this.BIo.zZm(ihn);
            return;
        }
        if (this.zyO.BIo) {
            if (this.jiA.zQM()) {
                Log.i(zZm, "The state mismatches. Network Connectivity is actually available.");
                this.zyO.BIo = false;
            } else {
                ihn.zZm();
                return;
            }
        }
        DVu zyO = this.zQM.zyO();
        if (zyO == null) {
            ihn.zZm(hlN.ESTABLISHING_DOWN_CHANNEL_FAILED);
            Log.e(zZm, "DownchannelScheduler is not available.");
            return;
        }
        this.JTe = ihn;
        zyO.BIo();
    }

    @Subscribe
    public void on(yHQ yhq) {
        WnL wnL = this.zyO;
        boolean z = ((dpf) yhq).BIo;
        wnL.Qle = !z;
        if (z) {
            this.BIo.zZm(this.JTe);
        } else {
            Log.i(zZm, "Lost downchannel connectivity, waiting for downchannel connectivity");
            this.zyO.jiA();
        }
        this.JTe = IHN.zZm;
    }

    @Subscribe
    public void on(PMW pmw) {
        this.zyO.JTe = true;
        MqA mqA = (MqA) pmw;
        int ordinal = mqA.BIo.ordinal();
        if (ordinal == 0) {
            this.zyO.zzR = mqA.zQM;
        } else if (ordinal == 1) {
            this.zyO.Mlj = mqA.zyO;
        } else if (ordinal != 3) {
            this.zyO.BIo = true;
        } else {
            this.zyO.zQM = true;
        }
        this.JTe.zZm(hlN.ESTABLISHING_DOWN_CHANNEL_FAILED);
        this.JTe = IHN.zZm;
    }
}
