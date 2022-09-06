package com.amazon.alexa;

import com.amazon.alexa.aQE;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.interactions.AutoValue_VisualActivityTrackerPayload;
import com.amazon.alexa.client.alexaservice.interactions.VisualActivityTrackerChannelState;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import java.util.Map;
import javax.inject.Inject;
/* compiled from: VisualActivityTrackerProvider.java */
/* loaded from: classes.dex */
public class IOV extends Bwo {
    public static final String BIo = "IOV";
    public final Rpb zQM;

    @Inject
    public IOV(Rpb rpb) {
        super(AvsApiConstants.VisualActivityTracker.zZm, AvsApiConstants.VisualActivityTracker.ComponentStates.ActivityState.zZm);
        this.zQM = rpb;
    }

    @Override // com.amazon.alexa.dRG
    public ComponentState getState() {
        AutoValue_VisualActivityTrackerPayload autoValue_VisualActivityTrackerPayload;
        Map<JiQ, aNh> BIo2 = this.zQM.BIo();
        aNh anh = BIo2.get(JiQ.DIALOG_UI);
        if (anh == null || !((hgr) anh).zQM) {
            autoValue_VisualActivityTrackerPayload = null;
        } else {
            aQE.zZm zzm = (aQE.zZm) Gzu.zZm();
            zzm.zZm = VisualActivityTrackerChannelState.zZm(((hgr) BIo2.get(JiQ.DIALOG_UI)).zZm);
            autoValue_VisualActivityTrackerPayload = new AutoValue_VisualActivityTrackerPayload(zzm.zZm);
        }
        if (autoValue_VisualActivityTrackerPayload != null) {
            return ComponentState.create(this.zZm, autoValue_VisualActivityTrackerPayload);
        }
        return null;
    }
}
