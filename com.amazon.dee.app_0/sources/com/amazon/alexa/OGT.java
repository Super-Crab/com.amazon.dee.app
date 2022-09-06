package com.amazon.alexa;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.QYV;
import com.amazon.alexa.Vxb;
import com.amazon.alexa.api.AlexaClient;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.AlexaSupportedInitiationType;
import com.amazon.alexa.api.AlexaUserSpeechProvider;
import com.amazon.alexa.api.AlexaUserSpeechProviderMetadata;
import com.amazon.alexa.api.AlexaUserSpeechProviderScope;
import com.amazon.alexa.api.DialogRequestDeniedReason;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.kbp;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import dagger.Lazy;
import java.util.Collections;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: UserSpeechProviderAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class OGT {
    public static final String zZm = "OGT";
    public final Context BIo;
    public final vkx JTe;
    public final Lazy<dSq> LPk;
    public final Wyh Qle;
    public final iDr jiA;
    public final AlexaHandsFreeDeviceInformation yPL;
    public final AlexaClientEventBus zQM;
    public final jdJ zyO;

    @Inject
    public OGT(Context context, AlexaClientEventBus alexaClientEventBus, jdJ jdj, iDr idr, Wyh wyh, vkx vkxVar, Lazy<dSq> lazy, AlexaHandsFreeDeviceInformation alexaHandsFreeDeviceInformation) {
        this.BIo = context;
        this.zQM = alexaClientEventBus;
        this.zyO = jdj;
        this.jiA = idr;
        this.Qle = wyh;
        this.JTe = vkxVar;
        this.LPk = lazy;
        this.yPL = alexaHandsFreeDeviceInformation;
        alexaClientEventBus.zZm(this);
        if (zZm()) {
            Log.w(zZm, "System wakeword USP is added");
            this.zyO.zZm(AlexaClient.CLIENT, this.LPk.mo358get(), AlexaUserSpeechProviderMetadata.create(Collections.singleton(AlexaSupportedInitiationType.WAKE_WORD), Collections.singleton("alexa"), AlexaUserSpeechProviderScope.SYSTEM));
            return;
        }
        Log.w(zZm, "System wakeword USP is not added");
    }

    public void BIo() {
        if (zZm()) {
            this.zyO.zZm(AlexaClient.CLIENT, this.LPk.mo358get());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x007d, code lost:
        if (r4.BIo() != false) goto L12;
     */
    @org.greenrobot.eventbus.Subscribe
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void on(com.amazon.alexa.QYV.BIo r7) {
        /*
            Method dump skipped, instructions count: 391
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.OGT.on(com.amazon.alexa.QYV$BIo):void");
    }

    @VisibleForTesting
    public boolean zZm() {
        return this.yPL.isCurrentDeviceHandsFree() && "com.amazon.dee.app".equals(this.BIo.getPackageName());
    }

    public final void zZm(mqw mqwVar, DialogRequestDeniedReason dialogRequestDeniedReason) {
        AlexaUserSpeechProvider zZm2 = this.zyO.zZm(mqwVar);
        if (zZm2 != null) {
            String str = zZm;
            StringBuilder zZm3 = C0179Pya.zZm("Denying dialog request: ");
            zZm3.append(mqwVar.getClient().getId());
            Log.i(str, zZm3.toString());
            zZm2.onDialogRequestDenied(dialogRequestDeniedReason);
        }
    }

    public final NEe zZm(mqw mqwVar) {
        yTq ytq = new yTq(mqwVar);
        gfp gfpVar = new gfp(mqwVar);
        String softwareVersion = mqwVar.getMetadata().getSoftwareVersion();
        piE zZm2 = mqwVar.zZm();
        if (softwareVersion == null) {
            softwareVersion = "";
        }
        return this.Qle.zZm(mqwVar.getClient(), ytq, gfpVar, new NND(zZm2, softwareVersion, mqwVar.getMetadata().getProviderScope()));
    }

    @Subscribe
    public void on(QYV.zZm zzm) {
        Log.i(zZm, "onNewDialogRequestEvent");
        lDN ldn = (lDN) zzm;
        AlexaDialogRequest alexaDialogRequest = ldn.zyO;
        mqw mqwVar = ldn.zQM;
        esV esv = ldn.BIo;
        if (this.jiA.zZm(this.zyO, mqwVar, esv)) {
            if (!xrg.zZm(this.JTe)) {
                StringBuilder zZm2 = C0179Pya.zZm("Cannot request dialog to alexa. Invalid Alexa State: ");
                zZm2.append(this.JTe.zyO());
                String sb = zZm2.toString();
                Log.w(zZm, sb);
                zZm(mqwVar, new DialogRequestDeniedReason(DialogRequestDeniedReason.Reason.OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_BARGE_IN, sb));
                this.zQM.zyO(kbp.zyO.zZm(alexaDialogRequest.getInvocationType(), mMl.OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_BARGE_IN));
                return;
            }
            NEe zZm3 = zZm(mqwVar);
            if (!zZm3.zZm(esv, ldn.zyO)) {
                Log.w(zZm, "Cannot request first turn on the multiturn dialog");
                this.zQM.zyO(kbp.zyO.zZm(alexaDialogRequest.getInvocationType(), mMl.OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_START_FIRST_TURN));
                this.Qle.zZm(zZm3);
                return;
            }
            this.JTe.zZm(ldn.jiA);
            return;
        }
        StringBuilder zZm4 = C0179Pya.zZm("Dialog request disallowed from client: ");
        zZm4.append(ldn.zQM.getClient().getId());
        zZm4.append(", invocationType: ");
        zZm4.append(ldn.zyO.getInvocationType());
        String sb2 = zZm4.toString();
        zZm(mqwVar, new DialogRequestDeniedReason(DialogRequestDeniedReason.Reason.SOURCE_ARBITRATION, sb2));
        Log.i(zZm, sb2);
        this.zQM.zyO(kbp.zyO.zZm(alexaDialogRequest.getInvocationType(), mMl.SOURCE_ARBITRATION));
    }

    @Subscribe
    public void on(TxC txC) {
        Log.i(zZm, "onUserSpeechProvidersUpdatedEvent");
        this.jiA.zZm(this.zyO);
    }

    @Subscribe
    public void on(Vxb.zZm zzm) {
        Log.i(zZm, "Received Pause WakeWordTrackerEvent");
        for (mqw mqwVar : this.zyO.zZm()) {
            mqwVar.pauseWakeWordDetection("alexa");
        }
    }

    @Subscribe
    public void on(Vxb.BIo bIo) {
        Log.i(zZm, "Received Resume WakeWordTrackerEvent");
        for (mqw mqwVar : this.zyO.zZm()) {
            mqwVar.resumeWakeWordDetection("alexa");
        }
    }
}
