package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: MediaBrowserPlayerFinder.java */
@Singleton
/* loaded from: classes.dex */
public class Lnt {
    public final Qva BIo;
    public final ExecutorService zQM;
    public final zmg zZm;
    public final AlexaClientEventBus zyO;

    @Inject
    public Lnt(AlexaClientEventBus alexaClientEventBus, zmg zmgVar, Qva qva) {
        ExecutorService newSingleThreadCachedThreadPool = ManagedExecutorFactory.newSingleThreadCachedThreadPool("player-finder");
        this.zZm = zmgVar;
        this.BIo = qva;
        this.zQM = newSingleThreadCachedThreadPool;
        this.zyO = alexaClientEventBus;
        alexaClientEventBus.zZm(this);
        zZm();
    }

    public void BIo() {
        this.zyO.BIo(this);
    }

    @Subscribe
    public synchronized void on(AzW azW) {
        zZm();
    }

    public final void zZm() {
        this.zQM.submit(new mDy(this));
    }

    @Subscribe
    public synchronized void on(ery eryVar) {
        zZm();
    }

    @Subscribe
    public synchronized void on(DUu dUu) {
        zZm();
    }

    @Subscribe
    public synchronized void on(Bob bob) {
        if (bob.BIo()) {
            zZm();
        }
    }
}
