package com.amazon.alexa;

import com.amazon.alexa.UMd;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: RefreshInternalCapabilitiesChain.java */
@Singleton
/* loaded from: classes.dex */
public class RBt implements yDI {
    public static final String zZm = "RBt";
    public final bXh BIo;
    public IHN JTe = IHN.zZm;
    public final AtomicBoolean Qle = new AtomicBoolean(false);
    public final AlexaClientEventBus jiA;
    public final yDI zQM;
    public final WnL zyO;

    @Inject
    public RBt(bXh bxh, @Named("REFRESH_EXTERNAL_CAPABILITIES_CHAIN") yDI ydi, WnL wnL, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = bxh;
        this.zQM = ydi;
        this.zyO = wnL;
        this.jiA = alexaClientEventBus;
        alexaClientEventBus.zZm(this);
    }

    @Subscribe
    public void on(UMd.zZm zzm) {
        if (((Hvd) zzm).zQM) {
            this.zyO.zyO = false;
            this.Qle.set(false);
            this.zQM.zZm(this.JTe);
            this.JTe = IHN.zZm;
        }
    }

    @Override // com.amazon.alexa.yDI
    public void teardown() {
        this.jiA.BIo(this);
        this.zQM.teardown();
    }

    @Override // com.amazon.alexa.yDI
    public void zZm(IHN ihn) {
        if (this.zyO.zyO) {
            if (this.Qle.compareAndSet(false, true)) {
                this.JTe = ihn;
                this.BIo.BIo();
                return;
            }
            ihn.zZm();
            return;
        }
        this.zQM.zZm(ihn);
    }
}
