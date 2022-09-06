package com.amazon.alexa;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.metrics.core.DefaultMetricsCounter;
import com.amazon.alexa.client.metrics.core.DefaultMetricsTimer;
import com.amazon.alexa.client.metrics.core.MetricsTimer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
/* compiled from: SearchUiMetricsAuthority.kt */
@Singleton
/* loaded from: classes.dex */
public final class KPv {
    public static final String zZm = "KPv";
    public final Lazy BIo;
    public final dagger.Lazy<ClientConfiguration> jiA;
    public final AlexaClientEventBus zQM;
    public final paF zyO;

    @Inject
    public KPv(@NotNull AlexaClientEventBus eventBus, @NotNull paF metricsService, @NotNull dagger.Lazy<ClientConfiguration> clientConfigurationLazy) {
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull(eventBus, "eventBus");
        Intrinsics.checkParameterIsNotNull(metricsService, "metricsService");
        Intrinsics.checkParameterIsNotNull(clientConfigurationLazy, "clientConfigurationLazy");
        this.zQM = eventBus;
        this.zyO = metricsService;
        this.jiA = clientConfigurationLazy;
        lazy = LazyKt__LazyJVMKt.lazy(new qqO(this));
        this.BIo = lazy;
        this.zQM.zZm(this);
    }

    public final void BIo() {
        this.zQM.BIo(this);
    }

    @Subscribe
    public final void on(@NotNull AQg uiEventReceivedEvent) {
        Intrinsics.checkParameterIsNotNull(uiEventReceivedEvent, "uiEventReceivedEvent");
        C0236vPD c0236vPD = (C0236vPD) uiEventReceivedEvent;
        UiEventName eventName = c0236vPD.BIo;
        Intrinsics.checkExpressionValueIsNotNull(eventName, "eventName");
        AlexaMetricsName metricsName = eventName.getMetricsName();
        Intrinsics.checkExpressionValueIsNotNull(metricsName, "eventName.metricsName");
        String value = metricsName.getValue();
        GeneratedOutlineSupport1.outline158("Received UI event with metric name ", value);
        switch (fGY.zZm[eventName.ordinal()]) {
            case 1:
            case 2:
                zZm(this, uiEventReceivedEvent, AlexaMetadataBundleKey.SOURCE, true, null, null, null, 56);
                return;
            case 3:
            case 4:
                zZm(this, uiEventReceivedEvent, AlexaMetadataBundleKey.SOURCE, false, null, null, null, 56);
                return;
            case 5:
                zZm(this, uiEventReceivedEvent, AlexaMetadataBundleKey.SOURCE, false, null, null, null, 48);
                return;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                zZm(this, uiEventReceivedEvent, (Map) null, (String[]) null, (AlexaMetadataBundleKey) null, 14);
                return;
            case 24:
            case 25:
            case 26:
                zZm(this, uiEventReceivedEvent, (Map) null, (String[]) null, AlexaMetadataBundleKey.SOURCE_CONTEXT, 6);
                return;
            case 27:
            case 28:
            case 29:
                long j = c0236vPD.zQM.getLong(AlexaMetadataBundleKey.LATENCY_REALTIME_MS.name(), -1L);
                if (j == -1) {
                    return;
                }
                paF paf = this.zyO;
                UiEventName uiEventName = c0236vPD.BIo;
                Intrinsics.checkExpressionValueIsNotNull(uiEventName, "event.uiEventName");
                AlexaMetricsName metricsName2 = uiEventName.getMetricsName();
                Intrinsics.checkExpressionValueIsNotNull(metricsName2, "event.uiEventName.metricsName");
                paf.zZm((MetricsTimer) new DefaultMetricsTimer(metricsName2.getValue(), "vox_speech", zZm(), null, j, false));
                return;
            default:
                String str = zZm;
                Log.e(str, "UI event with metric name " + value + " is not handled");
                return;
        }
    }

    public final String zZm() {
        return (String) this.BIo.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void zZm(KPv kPv, AQg aQg, Map map, String[] strArr, AlexaMetadataBundleKey alexaMetadataBundleKey, int i) {
        if ((i & 2) != 0) {
            map = null;
        }
        if ((i & 4) != 0) {
            strArr = new String[0];
        }
        if ((i & 8) != 0) {
            alexaMetadataBundleKey = null;
        }
        kPv.zZm(aQg, map, strArr, alexaMetadataBundleKey);
    }

    public final void zZm(AQg aQg, Map<String, ? extends Object> map, String[] strArr, AlexaMetadataBundleKey alexaMetadataBundleKey) {
        double d;
        C0236vPD c0236vPD = (C0236vPD) aQg;
        UiEventName uiEventName = c0236vPD.BIo;
        Intrinsics.checkExpressionValueIsNotNull(uiEventName, "event.uiEventName");
        AlexaMetricsName metricsName = uiEventName.getMetricsName();
        Intrinsics.checkExpressionValueIsNotNull(metricsName, "event.uiEventName.metricsName");
        String metricName = metricsName.getValue();
        if (!(strArr.length == 0)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(metricName, ":");
            outline113.append(TextUtils.join(":", strArr));
            metricName = outline113.toString();
        }
        if (alexaMetadataBundleKey != null) {
            Object obj = c0236vPD.zQM.get(alexaMetadataBundleKey.name());
            if (!(obj instanceof Number)) {
                if (obj == null) {
                    String str = zZm;
                    Log.e(str, "recordOccurrenceOfEvent: count expected in " + alexaMetadataBundleKey + " but no value is present");
                    return;
                }
                String str2 = zZm;
                Log.e(str2, "recordOccurrenceOfEvent: invalid count '" + obj + "' in field " + alexaMetadataBundleKey);
                return;
            }
            d = ((Number) obj).doubleValue();
        } else {
            d = 1.0d;
        }
        Intrinsics.checkExpressionValueIsNotNull(metricName, "metricName");
        DefaultMetricsCounter defaultMetricsCounter = new DefaultMetricsCounter(metricName, "vox_speech", zZm(), map);
        defaultMetricsCounter.incrementCounterByValue(d);
        this.zyO.zZm(defaultMetricsCounter);
    }

    public static /* synthetic */ void zZm(KPv kPv, AQg aQg, AlexaMetadataBundleKey alexaMetadataBundleKey, boolean z, String str, Map map, AlexaMetadataBundleKey alexaMetadataBundleKey2, int i) {
        if ((i & 8) != 0) {
            str = "UNKNOWN";
        }
        kPv.zZm(aQg, alexaMetadataBundleKey, z, str, (i & 16) != 0 ? null : map, (i & 32) != 0 ? null : alexaMetadataBundleKey2);
    }

    public final void zZm(AQg aQg, AlexaMetadataBundleKey alexaMetadataBundleKey, boolean z, String str, Map<String, ? extends Object> map, AlexaMetadataBundleKey alexaMetadataBundleKey2) {
        String string = ((C0236vPD) aQg).zQM.getString(alexaMetadataBundleKey.name());
        if (string == null) {
            string = str;
        }
        if (z || string == null) {
            zZm(this, aQg, map, (String[]) null, alexaMetadataBundleKey2, 4);
        }
        if (string != null) {
            zZm(aQg, map, new String[]{string}, alexaMetadataBundleKey2);
        }
    }
}
