package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.client.metrics.core.DefaultMetricsTimer;
import com.amazon.alexa.client.metrics.core.MetricsCounter;
import com.amazon.alexa.client.metrics.core.MetricsTimer;
import com.amazon.alexa.jrC;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AlexaLauncherMetricsAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class PWS {
    public static final String zZm = "PWS";
    public final AlexaClientEventBus BIo;
    public final paF zQM;

    @Inject
    public PWS(AlexaClientEventBus alexaClientEventBus, paF paf) {
        this.BIo = alexaClientEventBus;
        this.zQM = paf;
        alexaClientEventBus.zZm(this);
    }

    @Subscribe
    public void on(vUW vuw) {
        Log.i(zZm, "logging metrics for AlexaLauncher events.");
        StringBuilder sb = new StringBuilder();
        sb.append("LaunchEventType-");
        syk sykVar = (syk) vuw;
        sb.append(sykVar.Qle);
        zZm(sb.toString());
        zZm("LaunchOutcome-" + sykVar.zyO.name());
    }

    public void zZm() {
        this.BIo.BIo(this);
    }

    public final void zZm(String str) {
        MetricsCounter zZm2 = this.zQM.zZm(str, AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE, null);
        zZm2.incrementCounter();
        this.zQM.zZm(zZm2);
    }

    @Subscribe
    public void on(jrC.BIo bIo) {
        Log.i(zZm, "logging metrics for requesting device unlock.");
        zZm("DeviceUnlockRequested");
    }

    @Subscribe
    public void on(jrC.zZm zzm) {
        Log.i(zZm, "logging metrics for AlexaLauncher directives.");
        DbX dbX = (DbX) zzm;
        String str = dbX.BIo;
        zZm("LauncherDirective-" + str);
        StringBuilder zZm2 = C0179Pya.zZm("launcher directive process time: ");
        zZm2.append(dbX.zQM);
        zZm2.toString();
        this.zQM.zZm((MetricsTimer) new DefaultMetricsTimer("LauncherDirectiveProcessTime", AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE, null, dbX.zQM, false));
        StringBuilder zZm3 = C0179Pya.zZm("launcher directive UPL: ");
        zZm3.append(dbX.zyO);
        zZm3.toString();
        this.zQM.zZm((MetricsTimer) new DefaultMetricsTimer("LauncherDirectiveUPL", AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE, null, dbX.zyO, false));
    }
}
