package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import java.util.HashSet;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: ExternalMediaPlayerStateProvider.java */
@Singleton
/* loaded from: classes.dex */
public class Boy extends Bwo {
    public static final String BIo = "Boy";
    public final zmg zQM;
    public final XTJ zyO;

    @Inject
    public Boy(zmg zmgVar, XTJ xtj) {
        super(AvsApiConstants.ExternalMediaPlayer.zZm, AvsApiConstants.ExternalMediaPlayer.ComponentStates.ExternalMediaPlayerState.zZm);
        this.zQM = zmgVar;
        this.zyO = xtj;
        xtj.zZm(XjE.PLAYER_IN_FOCUS, vQe.zZm);
    }

    @Override // com.amazon.alexa.dRG
    public ComponentState getState() {
        HashSet hashSet = new HashSet();
        vQe vqe = vQe.zZm;
        Map<vQe, JYe> zZm = this.zQM.zZm();
        for (Map.Entry<vQe, JYe> entry : zZm.entrySet()) {
            vQe key = entry.getKey();
            JYe value = entry.getValue();
            if (value.HvC()) {
                C0179Pya.zZm("Media browser player which reports being in focus: ", (Object) key);
                vqe = key;
            }
            HkJ zzR = value.zzR();
            if (zzR != null) {
                hashSet.add(zzR);
            }
        }
        if (vQe.zZm.equals(vqe)) {
            vqe = this.zyO.zyO(XjE.PLAYER_IN_FOCUS);
            String str = "cached playerInFocus: " + vqe;
            if (!zZm.containsKey(vqe)) {
                vqe = vQe.zZm;
            }
        }
        Log.i(BIo, "updated playerInFocus: " + vqe);
        this.zyO.BIo(XjE.PLAYER_IN_FOCUS, vqe);
        return ComponentState.create(this.zZm, BGK.zZm("JR410H5Y27", zmg.BIo, vqe, hashSet));
    }
}
