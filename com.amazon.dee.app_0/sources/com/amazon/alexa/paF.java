package com.amazon.alexa;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.marketplace.MarketplaceAuthority;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.client.metrics.core.AlexaMetricsEvent;
import com.amazon.alexa.client.metrics.core.DefaultAlexaMetricsEvent;
import com.amazon.alexa.client.metrics.core.DefaultMetricsCounter;
import com.amazon.alexa.client.metrics.core.DeviceInformation;
import com.amazon.alexa.client.metrics.core.MetricsConnector;
import com.amazon.alexa.client.metrics.core.MetricsCounter;
import com.amazon.alexa.client.metrics.core.MetricsTimer;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
/* compiled from: MetricsService.java */
/* loaded from: classes.dex */
public class paF {
    public static final List<String> BIo = Arrays.asList("APP_CRASH", "APP_CRASH_COUNT");
    public static final String zZm = "paF";
    public final MarketplaceAuthority JTe;
    public final String LPk;
    public final Lazy<PreloadAttributionManager> Qle;
    public final Lazy<PersistentStorage> jiA;
    public List<String> yPL;
    public final DeviceInformation zQM;
    public final List<MetricsConnector> zyO;

    static {
        TimeUnit.MILLISECONDS.convert(5L, TimeUnit.SECONDS);
    }

    public paF(DeviceInformation deviceInformation, Lazy<PersistentStorage> lazy, List<MetricsConnector> list, Lazy<PreloadAttributionManager> lazy2, MarketplaceAuthority marketplaceAuthority, @Named("androidId") String str) {
        this.zQM = deviceInformation;
        this.zyO = list;
        this.jiA = lazy;
        this.Qle = lazy2;
        this.JTe = marketplaceAuthority;
        this.LPk = str;
    }

    public void BIo(String str, String str2, String str3, String str4, Map<String, Object> map) {
        HashMap hashMap = map == null ? new HashMap() : map;
        map.put("EventValue", str2);
        zZm("Error", "Error." + str, str3, str4, hashMap);
    }

    public void zZm() {
        this.yPL = new ArrayList();
        boolean z = this.jiA.mo358get().getBoolean("FirstLaunch", true);
        String string = this.jiA.mo358get().getString("AppVersion", "2.0.0.0");
        if (z) {
            zZm(AlexaMetricsConstants.MetricEvents.SERVICE_INSTALL);
            this.jiA.mo358get().edit().set("FirstLaunch", false).set("AppVersion", "2.4.1609.0").commitAsynchronously();
        } else if (!"2.4.1609.0".equals(string)) {
            zZm(AlexaMetricsConstants.MetricEvents.SERVICE_UPDATE);
            this.jiA.mo358get().edit().set("AppVersion", "2.4.1609.0").commitAsynchronously();
        }
        for (MetricsConnector metricsConnector : this.zyO) {
            metricsConnector.beginSession();
        }
    }

    public void zZm(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "_vox_speech_v2";
        }
        String str2 = str;
        if (BIo.contains(str2)) {
            if (this.yPL.contains(str2)) {
                return;
            }
            this.yPL.add(str2);
        }
        zZm("General", str2, AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE, null, null);
    }

    public void zZm(String str, String str2, String str3, @Nullable String str4, @Nullable Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("EventType", str);
        zZm(new DefaultAlexaMetricsEvent(str2, str3, str4, map));
    }

    public void zZm(AlexaMetricsEvent alexaMetricsEvent) {
        if (alexaMetricsEvent.isInvalidated()) {
            return;
        }
        Map<String, Object> customEntries = alexaMetricsEvent.getCustomEntries();
        if (customEntries != null) {
            Iterator<Map.Entry<String, Object>> it2 = customEntries.entrySet().iterator();
            while (it2.hasNext()) {
                Object value = it2.next().getValue();
                if (value == null || value.toString().isEmpty()) {
                    it2.remove();
                }
            }
            String attributionTag = this.Qle.mo358get().getAttributionTag();
            if (!TextUtils.isEmpty(attributionTag)) {
                customEntries.put("attributionTag", attributionTag);
            }
            customEntries.put("androidId", this.LPk);
            customEntries.putAll(this.zQM.getStaticDeviceProfileData());
            customEntries.putAll(this.zQM.getDynamicDeviceProfileData(true));
            if (!customEntries.containsKey("pfm")) {
                customEntries.put("pfm", this.JTe.getMarketplace().name());
            }
            if (!customEntries.containsKey("locale")) {
                customEntries.put("locale", Locale.getDefault().toLanguageTag());
            }
        }
        for (MetricsConnector metricsConnector : this.zyO) {
            metricsConnector.recordEvent(alexaMetricsEvent);
        }
    }

    public MetricsCounter zZm(String str, String str2, String str3, Map<String, Object> map) {
        return new DefaultMetricsCounter(str, str2, str3, map);
    }

    public MetricsCounter zZm(String str, String str2, Map<String, Object> map) {
        return new DefaultMetricsCounter(str, str2, map);
    }

    public void zZm(MetricsTimer metricsTimer) {
        metricsTimer.finishTimer();
        String.format("%s took %.2f seconds", metricsTimer.getEventName(), Float.valueOf(((float) metricsTimer.getElapsedTime()) / 1000.0f));
        zZm((AlexaMetricsEvent) metricsTimer);
    }
}
