package com.amazon.alexa;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.Ppr;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.feature.consumer.api.FeatureFlagListener;
import java.util.Collection;
import java.util.TimeZone;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: TimeZoneAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class zDj {
    public final Box BIo;
    public TimeZone jiA;
    public final gSO zQM;
    public final AlexaClientEventBus zZm;
    public final ScheduledExecutorService zyO;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TimeZoneAuthority.java */
    /* loaded from: classes.dex */
    public class BIo implements Runnable {
        public /* synthetic */ BIo(Jhv jhv) {
        }

        @Override // java.lang.Runnable
        public void run() {
            zDj zdj = zDj.this;
            if (zdj.jiA == null) {
                zdj.jiA = zdj.BIo.jiA();
            }
            TimeZone timeZone = zdj.jiA;
            TimeZone timeZone2 = TimeZone.getDefault();
            if (!timeZone2.equals(zDj.this.jiA)) {
                zDj.this.zZm.zyO(JjI.BIo().zZm(Message.create(Header.builder().setNamespace(AvsApiConstants.System.zZm).setName(AvsApiConstants.System.Events.TimeZoneChanged.zZm).build(), Ejh.zZm(SOo.zZm(timeZone2.getID())))).zZm(new zZm(timeZone2)).zZm());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TimeZoneAuthority.java */
    /* loaded from: classes.dex */
    public class zQM implements Runnable {
        public /* synthetic */ zQM(Jhv jhv) {
        }

        @Override // java.lang.Runnable
        public void run() {
            zDj.this.zZm.zyO(JjI.BIo().zZm(zDj.this.zZm()).zZm());
        }
    }

    /* compiled from: TimeZoneAuthority.java */
    /* loaded from: classes.dex */
    private class zZm extends UcG {
        public final TimeZone zZm;

        public zZm(TimeZone timeZone) {
            this.zZm = timeZone;
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onFailure(RrI rrI, @Nullable Integer num, @Nullable Exception exc) {
            zDj.this.zZm.zyO(Ppr.zZm(Ppr.zZm.TIME_ZONE, num));
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onSuccess(RrI rrI, Collection<Message> collection) {
            zDj.this.zZm(this.zZm);
            zDj.this.zZm.zyO(Ppr.zZm(Ppr.zZm.TIME_ZONE));
        }
    }

    @Inject
    public zDj(AlexaClientEventBus alexaClientEventBus, Box box, gSO gso, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService) {
        this.zZm = alexaClientEventBus;
        this.BIo = box;
        this.zQM = gso;
        this.zyO = scheduledExecutorService;
        alexaClientEventBus.zZm(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void BIo(boolean z) {
        if (z) {
            zQM();
        }
    }

    @Subscribe
    public synchronized void on(Bob bob) {
        if (bob.BIo()) {
            this.zQM.zZm(Feature.ALEXA_VOX_ANDROID_DLS, new FeatureFlagListener() { // from class: com.amazon.alexa.-$$Lambda$zDj$dRLcQmsGtRsHjE2XCDfyurg2Cg8
                @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagListener
                public final void onFeatureServiceReady(boolean z) {
                    zDj.this.zZm(z);
                }
            });
        }
    }

    public void zQM() {
        this.zyO.execute(new BIo(null));
    }

    @VisibleForTesting
    public synchronized void zZm(TimeZone timeZone) {
        this.jiA = timeZone;
        this.BIo.zZm(timeZone);
    }

    public void BIo() {
        this.zyO.execute(new zQM(null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void zZm(boolean z) {
        if (z) {
            zQM();
        }
    }

    @Subscribe
    public synchronized void on(Txs txs) {
        this.zQM.zZm(Feature.ALEXA_VOX_ANDROID_DLS, new FeatureFlagListener() { // from class: com.amazon.alexa.-$$Lambda$zDj$MXDHJepyqVYZzPVQWDpX8ewtTBg
            @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagListener
            public final void onFeatureServiceReady(boolean z) {
                zDj.this.BIo(z);
            }
        });
    }

    public Message zZm() {
        return Message.create(Header.builder().setNamespace(AvsApiConstants.System.zZm).setName(AvsApiConstants.System.Events.TimeZoneReport.zZm).build(), yaQ.zZm(this.BIo.jiA().getID()));
    }
}
