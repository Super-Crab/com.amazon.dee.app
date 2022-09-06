package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader;
import com.amazon.alexa.client.alexaservice.interactions.ActivityTrackerChannelState;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.plk;
import com.amazon.alexa.utils.TimeProvider;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
/* compiled from: AudioActivityTrackerProvider.java */
/* loaded from: classes.dex */
public class FQX extends Bwo {
    public static final String BIo = "FQX";
    public final Map<dnp, dnp> jiA;
    public final QJr zQM;
    public final TimeProvider zyO;

    @Inject
    public FQX(QJr qJr, TimeProvider timeProvider, @Named("interaction_interface_name_overrides") Map<dnp, dnp> map) {
        super(AvsApiConstants.AudioActivityTracker.zZm, AvsApiConstants.AudioActivityTracker.ComponentStates.ActivityState.zZm);
        this.zQM = qJr;
        this.zyO = timeProvider;
        this.jiA = map;
    }

    @Override // com.amazon.alexa.dRG
    public ComponentState getState() {
        ComponentStateHeader componentStateHeader = this.zZm;
        Map<aVo, aNh> JTe = this.zQM.JTe();
        plk.zZm zZm = plk.zZm();
        if (zZm(aVo.DIALOG, JTe)) {
            zZm.zyO(zZm(JTe.get(aVo.DIALOG)));
        }
        if (zZm(aVo.COMMUNICATIONS, JTe)) {
            zZm.BIo(zZm(JTe.get(aVo.COMMUNICATIONS)));
        }
        if (zZm(aVo.ALERTS, JTe)) {
            zZm.zZm(zZm(JTe.get(aVo.ALERTS)));
        }
        if (zZm(aVo.CONTENT, JTe)) {
            zZm.zQM(zZm(JTe.get(aVo.CONTENT)));
        }
        return ComponentState.create(componentStateHeader, zZm.zZm());
    }

    public final boolean zZm(aVo avo, Map<aVo, aNh> map) {
        aNh anh = map.get(avo);
        return anh != null && !dnp.zZm.equals(((hgr) anh).zZm);
    }

    public final ActivityTrackerChannelState zZm(aNh anh) {
        hgr hgrVar = (hgr) anh;
        long currentTimeMillis = hgrVar.zQM ? 0L : this.zyO.currentTimeMillis() - hgrVar.BIo;
        String str = hgrVar.zZm + " is idle for " + currentTimeMillis + "ms";
        if (this.jiA.containsKey(hgrVar.zZm)) {
            return ActivityTrackerChannelState.zZm(this.jiA.get(hgrVar.zZm), currentTimeMillis);
        }
        return ActivityTrackerChannelState.zZm(hgrVar.zZm, currentTimeMillis);
    }
}
