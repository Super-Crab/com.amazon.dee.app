package com.amazon.alexa;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.Stage;
import com.amazon.alexa.client.metrics.core.DefaultMetricsTimer;
import com.amazon.alexa.client.metrics.core.MetricsCounter;
import com.amazon.alexa.client.metrics.core.MetricsTimer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: TextUiMetricsAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class bwE {
    public static final String zZm = "bwE";
    public final AlexaClientEventBus BIo;
    public final paF zQM;
    public final Lazy<ClientConfiguration> zyO;

    @Inject
    public bwE(AlexaClientEventBus alexaClientEventBus, paF paf, Lazy<ClientConfiguration> lazy) {
        this.BIo = alexaClientEventBus;
        this.zQM = paf;
        this.zyO = lazy;
        alexaClientEventBus.zZm(this);
    }

    public void BIo() {
        this.BIo.BIo(this);
    }

    @Subscribe
    public void on(AQg aQg) {
        C0236vPD c0236vPD = (C0236vPD) aQg;
        UiEventName uiEventName = c0236vPD.BIo;
        String value = uiEventName.getMetricsName().getValue();
        GeneratedOutlineSupport1.outline158("Received UI event with metric name ", value);
        switch (kpw.zZm[uiEventName.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                zZm(value, null);
                return;
            case 10:
            case 11:
                HashMap hashMap = new HashMap();
                hashMap.put("namespace", c0236vPD.zQM.getString(AlexaMetadataBundleKey.SOURCE.name()));
                zZm(value, hashMap);
                return;
            case 12:
            case 13:
                String name = uiEventName.name();
                Bundle bundle = c0236vPD.zQM;
                long j = bundle.getLong(AlexaMetadataBundleKey.LATENCY_REALTIME_MS.name(), -1L);
                String string = bundle.getString(AlexaMetadataBundleKey.SOURCE.name());
                if (j < 0) {
                    return;
                }
                if (string != null) {
                    name = GeneratedOutlineSupport1.outline75(name, ":", string);
                }
                this.zQM.zZm((MetricsTimer) new DefaultMetricsTimer(name, "vox_speech", zZm(), null, j, false));
                return;
            default:
                return;
        }
    }

    public final void zZm(String str, @Nullable Map<String, Object> map) {
        MetricsCounter zZm2 = this.zQM.zZm(str, "vox_speech", zZm(), map);
        zZm2.incrementCounter();
        this.zQM.zZm(zZm2);
    }

    public final String zZm() {
        Stage stage = this.zyO.mo358get().getStage();
        if (stage == null) {
            stage = Stage.PROD;
        }
        StringBuilder zZm2 = C0179Pya.zZm("AlexaMobileAndroid_");
        zZm2.append(stage.toString());
        return zZm2.toString();
    }
}
