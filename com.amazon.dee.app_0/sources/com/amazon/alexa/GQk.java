package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.BaP;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.Stage;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.client.metrics.core.MetricsCounter;
import dagger.Lazy;
import java.util.HashMap;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: DriveModeMetricsAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class GQk {
    public static final String zZm = "GQk";
    public final AlexaClientEventBus BIo;
    public final Lazy<ClientConfiguration> jiA;
    public final paF zQM;
    public final rqw zyO;

    @Inject
    public GQk(AlexaClientEventBus alexaClientEventBus, paF paf, rqw rqwVar, Lazy<ClientConfiguration> lazy) {
        this.BIo = alexaClientEventBus;
        this.zQM = paf;
        this.zyO = rqwVar;
        this.jiA = lazy;
        alexaClientEventBus.zZm(this);
    }

    @Subscribe
    public void on(BaP.zQM zqm) {
        String sb;
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Voice Interaction result: isEnabled (");
        zZm2.append(this.zyO.zQM());
        zZm2.append("), state (");
        zZm2.append(this.zyO.BIo());
        zZm2.append(")");
        Log.i(str, zZm2.toString());
        if (this.zyO.zQM()) {
            if (((mqC) zqm).jiA != null) {
                StringBuilder zZm3 = C0179Pya.zZm("DriveMode_VoiceInteractionFailure");
                zZm3.append(this.zyO.BIo());
                sb = zZm3.toString();
            } else {
                StringBuilder zZm4 = C0179Pya.zZm("DriveMode_VoiceInteractionSuccess");
                zZm4.append(this.zyO.BIo());
                sb = zZm4.toString();
            }
            zZm(zqm, sb);
        }
    }

    public void zZm() {
        this.BIo.BIo(this);
    }

    public final void zZm(BaP baP, String str) {
        HashMap hashMap = new HashMap();
        String value = baP.BIo().getValue();
        String zQM = baP.zQM();
        hashMap.put("dialogId", value);
        hashMap.put(AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE, zQM);
        paF paf = this.zQM;
        Stage stage = this.jiA.mo358get().getStage();
        if (stage == null) {
            stage = Stage.PROD;
        }
        StringBuilder zZm2 = C0179Pya.zZm("AlexaMobileAndroid_");
        zZm2.append(stage.toString());
        MetricsCounter zZm3 = paf.zZm(str, "DriveMode", zZm2.toString(), hashMap);
        zZm3.incrementCounter();
        this.zQM.zZm(zZm3);
    }

    @Subscribe
    public void on(BaP.zZm zzm) {
        if (this.zyO.zQM()) {
            StringBuilder zZm2 = C0179Pya.zZm("DriveMode_VoiceInteractionAttempt");
            zZm2.append(this.zyO.BIo());
            zZm(zzm, zZm2.toString());
        }
    }
}
