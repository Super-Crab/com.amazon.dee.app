package com.amazon.alexa;

import android.app.KeyguardManager;
import android.os.Build;
import android.util.Log;
import com.amazon.alexa.VIE;
import com.amazon.alexa.api.AlexaPreferences;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.iocomponent.AutoValue_SessionState;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.configuration.Feature;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: TrustedStatesComponentStateProvider.java */
@Singleton
/* loaded from: classes.dex */
public class AYd extends Bwo {
    public static final String BIo = "AYd";
    public final VIE zQM;

    @Inject
    public AYd(VIE vie) {
        super(AvsApiConstants.Alexa.IOComponents.zZm, AvsApiConstants.Alexa.IOComponents.ComponentStates.TrustedStates.zZm);
        this.zQM = vie;
    }

    @Override // com.amazon.alexa.dRG
    public ComponentState getState() {
        List emptyList;
        boolean isDeviceSecure;
        Log.i(BIo, "getStates() from TrustedStatesComponentStateProvider");
        VIE.zZm zzm = null;
        if (this.zQM == null) {
            return null;
        }
        String str = BIo;
        StringBuilder zZm = C0179Pya.zZm("UnlockState:");
        zZm.append(((adp) this.zQM).BIo());
        Log.i(str, zZm.toString());
        VIE.BIo BIo2 = ((adp) this.zQM).BIo();
        String zZm2 = ((adp) this.zQM).zZm();
        adp adpVar = (adp) this.zQM;
        boolean z = false;
        if (!adpVar.Qle.zZm()) {
            KeyguardManager keyguardManager = adpVar.Qle.zZm;
            if (keyguardManager == null) {
                isDeviceSecure = false;
            } else {
                int i = Build.VERSION.SDK_INT;
                isDeviceSecure = keyguardManager.isDeviceSecure();
            }
            if (isDeviceSecure) {
                zzm = VIE.zZm.SECURE_UNKNOWN;
            } else {
                zzm = VIE.zZm.NONE;
            }
        }
        adp adpVar2 = (adp) this.zQM;
        if (adpVar2.jiA != null && adpVar2.uzr.isBSR() && adpVar2.dMe.zZm(Feature.ALEXA_HANDS_FREE_FEATURE_GATING_BLOCK_SENSITIVE_REQUEST)) {
            z = true;
        }
        if (z) {
            String str2 = adp.zZm;
            StringBuilder zZm3 = C0179Pya.zZm("Sending metric for device lock state: ");
            zZm3.append(adpVar2.BIo());
            zZm3.append(", and last unlock timestamp: ");
            zZm3.append(adpVar2.zZm());
            com.amazon.alexa.handsfree.protocols.utils.Log.d(str2, zZm3.toString());
            adpVar2.lOf.zyO(new Gop(adpVar2.BIo(), adpVar2.zZm()));
            if (adpVar2.BIo() != VIE.BIo.UNLOCKED && !adpVar2.zZm(AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_NONE) && !adpVar2.zZm(AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_PERSONAL)) {
                emptyList = Collections.emptyList();
            } else {
                String type = adpVar2.jiA.getType();
                String format = adpVar2.JTe.format(adpVar2.LPk.getCurrentTimestamp());
                if (format != null) {
                    Long l = 100000000L;
                    if (type != null) {
                        String str3 = "";
                        if (l == null) {
                            str3 = C0179Pya.zZm(str3, " longestTimeUntrustedInMilliseconds");
                        }
                        if (str3.isEmpty()) {
                            emptyList = Collections.singletonList(new AutoValue_SessionState(format, null, l.longValue(), type, "9a9d4e7cefa64504a299b80da7c78109"));
                        } else {
                            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str3));
                        }
                    } else {
                        throw new NullPointerException("Null deviceType");
                    }
                } else {
                    throw new NullPointerException("Null sessionStartTime");
                }
            }
        } else {
            emptyList = Collections.emptyList();
        }
        return ComponentState.create(this.zZm, pUe.zZm(BIo2, zZm2, zzm, emptyList));
    }
}
