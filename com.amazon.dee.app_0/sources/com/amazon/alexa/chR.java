package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.messages.MessageMetadata;
import com.amazon.alexa.utils.TimeProvider;
import dagger.Lazy;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: AudioPlayerInteractionFactory.java */
@Singleton
/* loaded from: classes.dex */
public class chR {
    public final AlexaClientEventBus BIo;
    public final TimeProvider Qle;
    public final Lazy<ClientConfiguration> jiA;
    public final ScheduledExecutorService zQM;
    public final shl zZm;
    public final Ycj zyO;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AudioPlayerInteractionFactory.java */
    /* loaded from: classes.dex */
    public static class zZm implements Ycj {
        public final fla zZm;

        public zZm(fla flaVar) {
            this.zZm = flaVar;
        }
    }

    @Inject
    public chR(shl shlVar, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService, AlexaClientEventBus alexaClientEventBus, fla flaVar, Lazy<ClientConfiguration> lazy, TimeProvider timeProvider) {
        this.zZm = shlVar;
        this.BIo = alexaClientEventBus;
        this.zQM = scheduledExecutorService;
        this.zyO = new zZm(flaVar);
        this.jiA = lazy;
        this.Qle = timeProvider;
    }

    public BXc zZm() {
        return new BXc();
    }

    public Bha zZm(VIX vix, oGE oge, MessageMetadata messageMetadata) {
        AlexaClientEventBus alexaClientEventBus = this.BIo;
        return new Bha(vix, alexaClientEventBus, this.zZm, new Ygi(new rwQ(AvsApiConstants.AudioPlayer.Events.ProgressReportDelayElapsed.zZm, alexaClientEventBus, oge), new rwQ(AvsApiConstants.AudioPlayer.Events.ProgressReportIntervalElapsed.zZm, this.BIo, oge), vix), this.zQM, oge, this.Qle, messageMetadata, this.zyO, this.jiA);
    }
}
