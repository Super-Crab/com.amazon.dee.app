package com.amazon.alexa;

import com.amazon.alexa.UMd;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: RefreshExternalCapabilitiesChain.java */
@Singleton
/* loaded from: classes.dex */
public class TqI implements yDI {
    public static final String zZm = "TqI";
    public final bXh BIo;
    public final AlexaClientEventBus jiA;
    public final yDI zQM;
    public final WnL zyO;
    public IHN Qle = IHN.zZm;
    public short JTe = 0;

    @Inject
    public TqI(bXh bxh, @Named("ESTABLISH_DOWNCHANNEL_CHAIN") yDI ydi, WnL wnL, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = bxh;
        this.zQM = ydi;
        this.zyO = wnL;
        this.jiA = alexaClientEventBus;
        this.jiA.zZm(this);
    }

    @Subscribe
    public void on(UMd.BIo bIo) {
        this.Qle.zZm(hlN.REFRESHING_EXTERNAL_CAPABILITIES_TIME_OUT);
        zZm();
    }

    @Override // com.amazon.alexa.yDI
    public void teardown() {
        this.jiA.BIo(this);
        this.zQM.teardown();
    }

    @Override // com.amazon.alexa.yDI
    public void zZm(IHN ihn) {
        if (this.zyO.jiA) {
            this.Qle = ihn;
            this.JTe = (short) (this.JTe + 1);
            this.BIo.zZm();
        }
        this.zQM.zZm(ihn);
    }

    @Subscribe
    public void on(UMd.zZm zzm) {
        Hvd hvd = (Hvd) zzm;
        if (!hvd.zQM) {
            WnL wnL = this.zyO;
            wnL.jiA = false;
            if (hvd.BIo) {
                wnL.LPk = true;
            }
            this.zQM.zZm(this.Qle);
            zZm();
        }
    }

    public final void zZm() {
        this.JTe = (short) (this.JTe - 1);
        if (this.JTe <= 0) {
            this.Qle = IHN.zZm;
            this.JTe = (short) 0;
        }
    }
}
