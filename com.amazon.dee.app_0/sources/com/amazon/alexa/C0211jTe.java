package com.amazon.alexa;

import android.content.Context;
import android.os.Bundle;
import com.amazon.alexa.api.AlexaClient;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.fuM;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.voice.metrics.VoiceMetricsConstants;
import com.amazon.alexa.voice.ui.CardMetricsInteractorImpl;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: StandardVoiceCardMetricsAuthority.java */
@Singleton
/* renamed from: com.amazon.alexa.jTe  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0211jTe extends ZhG {
    public long JTe;
    public final String Qle;
    public final TimeProvider jiA;
    public final AlexaClientEventBus zyO;

    @Inject
    public C0211jTe(Context context, AlexaClientEventBus alexaClientEventBus, paF paf, TimeProvider timeProvider, Lazy<ClientConfiguration> lazy) {
        super(paf, timeProvider, lazy);
        this.JTe = -1L;
        this.zyO = alexaClientEventBus;
        this.jiA = timeProvider;
        this.Qle = context.getPackageName();
        alexaClientEventBus.zZm(this);
    }

    @Subscribe
    public void on(fuM.BIo bIo) {
        long longValue;
        Don don = (Don) bIo;
        fuM.zZm zzm = don.zQM;
        ExtendedClient extendedClient = don.BIo;
        extendedClient.getActiveSubClient().getPackageName();
        if (this.JTe > 0) {
            String packageName = extendedClient.getActiveSubClient().getPackageName();
            if (!(packageName.equals(this.Qle) || packageName.equals(AlexaClient.CLIENT.getPackageName())) || !fuM.zZm.BUTTON_PRESS.equals(zzm)) {
                return;
            }
            long elapsedRealTime = this.jiA.elapsedRealTime();
            long j = this.JTe;
            this.JTe = -1L;
            Long valueOf = Long.valueOf(elapsedRealTime);
            if (valueOf == null) {
                longValue = this.jiA.elapsedRealTime();
            } else {
                longValue = valueOf.longValue();
            }
            long j2 = longValue - j;
            if (j2 <= 0) {
                return;
            }
            zZm(VoiceMetricsConstants.VOX_TAP_TO_VOICE_RECORD_START, AlexaMetricsConstants.MetricsComponents.CARD_INGRESS_COMPONENT_NAME, j2, null);
        }
    }

    public void zQM() {
        this.zyO.BIo(this);
    }

    @Override // com.amazon.alexa.ZhG
    public String zZm() {
        return "vox_speech";
    }

    @Subscribe
    public void on(AQg aQg) {
        C0236vPD c0236vPD = (C0236vPD) aQg;
        UiEventName uiEventName = c0236vPD.BIo;
        Bundle bundle = c0236vPD.zQM;
        if ("DRIVE_MODE".equals(bundle.getString("cardMode"))) {
            return;
        }
        switch (sYc.zZm[uiEventName.ordinal()]) {
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
                BIo(CardMetricsInteractorImpl.EVENT_CARD_SHOWN + string);
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
                this.JTe = bundle.getLong(AlexaMetadataBundleKey.EVENT_REALTIME_MS.name(), -1L);
                return;
            case 8:
                String string2 = bundle.getString(AlexaMetadataBundleKey.CARD_NAME.name());
                BIo("\tUserNavigation.LinkOut." + string2);
                return;
            case 9:
                String string3 = bundle.getString(AlexaMetadataBundleKey.CARD_NAME.name());
                BIo(CardMetricsInteractorImpl.EVENT_INTERNAL_NAVIGATION + string3);
                return;
            case 10:
                zZm(bundle, true);
                return;
            case 11:
                zZm(bundle, false);
                return;
            case 12:
                zZm(bundle);
                return;
            case 13:
                BIo(bundle);
                return;
            case 14:
                LPk(bundle);
                return;
            default:
                return;
        }
    }
}
