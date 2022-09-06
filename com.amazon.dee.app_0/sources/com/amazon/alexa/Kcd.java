package com.amazon.alexa;

import android.os.Bundle;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.utils.TimeProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: DriveModeVoiceCardMetricsAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class Kcd extends ZhG {
    public final rqw jiA;
    public final AlexaClientEventBus zyO;

    @Inject
    public Kcd(AlexaClientEventBus alexaClientEventBus, paF paf, TimeProvider timeProvider, rqw rqwVar, Lazy<ClientConfiguration> lazy) {
        super(paf, timeProvider, lazy);
        this.zyO = alexaClientEventBus;
        this.jiA = rqwVar;
        alexaClientEventBus.zZm(this);
    }

    @Subscribe
    public void on(AQg aQg) {
        C0236vPD c0236vPD = (C0236vPD) aQg;
        UiEventName uiEventName = c0236vPD.BIo;
        Bundle bundle = c0236vPD.zQM;
        if (!"DRIVE_MODE".equals(bundle.getString("cardMode"))) {
            return;
        }
        switch (eYN.zZm[uiEventName.ordinal()]) {
            case 1:
                yPL(bundle);
                return;
            case 2:
                JTe(bundle);
                return;
            case 3:
                jiA(bundle);
                Qle(bundle);
                return;
            case 4:
                String string = bundle.getString(AlexaMetadataBundleKey.CARD_NAME.name());
                BIo(string + "Displayed");
                return;
            case 5:
                zQM(bundle);
                if (bundle.getBoolean(AlexaMetadataBundleKey.SUCCESS.name())) {
                    return;
                }
                Qle(bundle);
                return;
            case 6:
                zyO(bundle);
                return;
            case 7:
                BIo(zQM(bundle.getString(AlexaMetadataBundleKey.CARD_NAME.name())));
                return;
            case 8:
                BIo(zQM(bundle.getString(AlexaMetadataBundleKey.CARD_NAME.name())));
                return;
            case 9:
                zZm(bundle, true);
                return;
            case 10:
                zZm(bundle, false);
                return;
            case 11:
                zZm(bundle);
                return;
            case 12:
                BIo(bundle);
                return;
            case 13:
                LPk(bundle);
                return;
            default:
                return;
        }
    }

    public void zQM() {
        this.zyO.BIo(this);
    }

    @Override // com.amazon.alexa.ZhG
    public String zZm() {
        return "DriveMode";
    }

    public final String zQM(String str) {
        String[] split = str.split("_", 2);
        if (split.length == 2) {
            StringBuilder sb = new StringBuilder();
            sb.append(split[0]);
            sb.append("_");
            sb.append(this.jiA.BIo());
            return C0179Pya.zZm(sb, split[1], "Selected");
        }
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, "Selected");
        outline113.append(this.jiA.BIo());
        return outline113.toString();
    }
}
