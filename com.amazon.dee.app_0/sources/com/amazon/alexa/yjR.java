package com.amazon.alexa;

import android.support.v4.media.session.MediaControllerCompat;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: MediaChangeListenerFactory.java */
@Singleton
/* loaded from: classes.dex */
public class yjR {
    public final OWw BIo;
    public final qKe zQM;
    public final AlexaClientEventBus zZm;
    public final ScheduledExecutorService zyO;

    @Inject
    public yjR(AlexaClientEventBus alexaClientEventBus, OWw oWw, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService, qKe qke) {
        this.zZm = alexaClientEventBus;
        this.BIo = oWw;
        this.zyO = scheduledExecutorService;
        this.zQM = qke;
    }

    public YEL zZm(vQe vqe, MediaControllerCompat mediaControllerCompat, gSO gso, vkx vkxVar) {
        return new YEL(this.zZm, vqe, this.BIo, this.zyO, this.zQM, mediaControllerCompat, gso, vkxVar);
    }
}
