package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: BatteryAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class kjl {
    public boolean BIo;
    public boolean zZm;

    @Inject
    public kjl(AlexaClientEventBus alexaClientEventBus, DtD dtD, ktr ktrVar) {
        alexaClientEventBus.zZm(this);
    }

    public boolean BIo() {
        return this.BIo;
    }

    @Subscribe
    public synchronized void on(eoR eor) {
        this.zZm = ((qgZ) eor).BIo;
    }

    public boolean zZm() {
        return this.zZm;
    }

    @Subscribe
    public synchronized void on(ahl ahlVar) {
        this.BIo = ((rQh) ahlVar).BIo;
    }
}
