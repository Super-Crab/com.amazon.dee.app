package com.amazon.alexa;

import com.amazon.alexa.GkO;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.xxR;
import java.util.HashSet;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: PlaybackStateReporterProvider.java */
@Singleton
/* loaded from: classes.dex */
public class HTC extends Bwo {
    public final zmg BIo;

    @Inject
    public HTC(zmg zmgVar) {
        super(AvsApiConstants.Alexa.PlaybackStateReporter.zZm, AvsApiConstants.Alexa.PlaybackStateReporter.ComponentStates.PlaybackState.zZm);
        this.BIo = zmgVar;
    }

    @Override // com.amazon.alexa.dRG
    public synchronized ComponentState getState() {
        GkO.zZm zZm;
        Map<vQe, JYe> zZm2 = this.BIo.zZm();
        HashSet hashSet = new HashSet();
        for (JYe jYe : zZm2.values()) {
            Kyp lOf = jYe.lOf();
            if (lOf != null) {
                hashSet.add(lOf);
            }
        }
        zZm = GkO.zZm();
        if (!hashSet.isEmpty()) {
            ((xxR.zZm) zZm).LPk = hashSet;
        }
        return ComponentState.create(this.zZm, zZm.zZm());
    }
}
