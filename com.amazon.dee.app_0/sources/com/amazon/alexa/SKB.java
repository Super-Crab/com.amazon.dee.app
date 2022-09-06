package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: ExternalMediaPlayerPlaybackAuthority.java */
/* loaded from: classes.dex */
public class SKB {
    public static final String zZm = "SKB";
    public final AlexaClientEventBus BIo;
    public final Set<vQe> zQM = Collections.newSetFromMap(new ConcurrentHashMap());

    @Inject
    public SKB(AlexaClientEventBus alexaClientEventBus) {
        this.BIo = alexaClientEventBus;
        this.BIo.zZm(this);
    }

    @Subscribe
    public void on(MwZ mwZ) {
        cqx cqxVar = (cqx) mwZ;
        C0179Pya.zZm(C0179Pya.zZm("Media session playback was started: "), cqxVar.BIo, zZm);
        this.zQM.add(cqxVar.BIo);
    }

    public boolean zZm() {
        return !this.zQM.isEmpty();
    }

    @Subscribe
    public void on(gad gadVar) {
        yag yagVar = (yag) gadVar;
        C0179Pya.zZm(C0179Pya.zZm("Media session playback was paused: "), yagVar.BIo, zZm);
        this.zQM.remove(yagVar.BIo);
    }
}
