package com.amazon.alexa.accessorykit.metrics;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.connector.DefaultKinesisConnector;
import com.amazon.alexa.mobilytics.event.metadata.AMAMetadata;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class MetricRecorder implements AccessoryMetricsService {
    private static final String TAG = "MetricRecorder";
    private final Lazy<Mobilytics> lazyMobilytics;
    private final Map<String, MobilyticsMetricsTimer> mobilyticsTimerMap;

    public MetricRecorder(Lazy<Mobilytics> lazy) {
        Preconditions.notNull(lazy, DefaultKinesisConnector.COMPONENT);
        this.lazyMobilytics = lazy;
        this.mobilyticsTimerMap = new HashMap();
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void createTimer(String str, String str2, String str3, String str4) {
        Preconditions.notNull(str, "metricIdentifier");
        Preconditions.notNull(str2, "name");
        Preconditions.notNull(str3, JsonFields.COMPONENT);
        DefaultMobilyticsMetricsTimer defaultMobilyticsMetricsTimer = new DefaultMobilyticsMetricsTimer(str2, str3, str4, OwnerIdentifier.APP_ACCESSORIES_GENERAL);
        synchronized (this.mobilyticsTimerMap) {
            Logger.v("%s: createTimer: name=%s, component=%s", TAG, str2, str3);
            this.mobilyticsTimerMap.put(str, defaultMobilyticsMetricsTimer);
        }
    }

    @VisibleForTesting
    AMAMetadata getAMAMetadata(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        AMAMetadata aMAMetadata = new AMAMetadata();
        Logger.v("%s customEntries: ", TAG, map);
        for (String str : map.keySet()) {
            if (str.startsWith(AccessoryMetricsConstants.VALUE_PREFIX)) {
                aMAMetadata.setValue(str, Double.valueOf(map.get(str).toString()));
            } else {
                char c = 65535;
                switch (str.hashCode()) {
                    case -1697287268:
                        if (str.equals(AccessoryMetricsConstants.COMBINED_VALUES)) {
                            c = 11;
                            break;
                        }
                        break;
                    case -1353995670:
                        if (str.equals(AccessoryMetricsConstants.SEQUENCE_NUMBER)) {
                            c = '\n';
                            break;
                        }
                        break;
                    case -1168984641:
                        if (str.equals("firmware_accessory_0")) {
                            c = 3;
                            break;
                        }
                        break;
                    case -1168984640:
                        if (str.equals("firmware_accessory_1")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -1168984639:
                        if (str.equals(AccessoryMetricsConstants.FIRMWARE_VERSION_2)) {
                            c = 5;
                            break;
                        }
                        break;
                    case -814623346:
                        if (str.equals("firmware_accessory")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -780038359:
                        if (str.equals(AccessoryMetricsConstants.TIMESTAMP)) {
                            c = '\b';
                            break;
                        }
                        break;
                    case -233843581:
                        if (str.equals("dialogId")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 421933536:
                        if (str.equals(AccessoryMetricsConstants.DIALOG_TURN_ID)) {
                            c = 7;
                            break;
                        }
                        break;
                    case 489403459:
                        if (str.equals("deviceType_accessory")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1551664347:
                        if (str.equals(AccessoryMetricsConstants.BOOT_NUMBER)) {
                            c = '\t';
                            break;
                        }
                        break;
                    case 1725993572:
                        if (str.equals("deviceId_accessory")) {
                            c = 0;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        aMAMetadata = aMAMetadata.withDeviceSerialNumber(map.get(str).toString());
                        continue;
                    case 1:
                        aMAMetadata = aMAMetadata.withDeviceType(map.get(str).toString());
                        continue;
                    case 2:
                        aMAMetadata = aMAMetadata.withFirmwareVersionName(map.get(str).toString());
                        continue;
                    case 3:
                        aMAMetadata = aMAMetadata.withFirmAcc1(map.get(str).toString());
                        continue;
                    case 4:
                        aMAMetadata = aMAMetadata.withFirmAcc2(map.get(str).toString());
                        continue;
                    case 5:
                        aMAMetadata = aMAMetadata.withFirmAcc3(map.get(str).toString());
                        continue;
                    case 6:
                        aMAMetadata.setDialogId(map.get(str).toString());
                        continue;
                    case 7:
                        aMAMetadata.setDialogTurnId(map.get(str).toString());
                        continue;
                    case '\b':
                        aMAMetadata = aMAMetadata.withTimestampAccessories(Long.valueOf(map.get(str).toString()).longValue());
                        continue;
                    case '\t':
                        aMAMetadata.setBootNumber(map.get(str).toString());
                        continue;
                    case '\n':
                        aMAMetadata.setSequenceNumber(map.get(str).toString());
                        continue;
                    case 11:
                        break;
                    default:
                        Logger.e("%s: add corresponding mapping to support this attribute: %s", TAG, str);
                        continue;
                }
            }
        }
        return aMAMetadata;
    }

    @VisibleForTesting
    MobilyticsMetricsTimer getTimer(String str, String str2, String str3, long j, boolean z) {
        return new DefaultMobilyticsMetricsTimer(str, str2, str3, j, z, OwnerIdentifier.APP_ACCESSORIES_GENERAL);
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordCounter(String str, String str2, double d, Map<String, Object> map) {
        Preconditions.notNull(str, JsonFields.EVENT_NAME);
        Preconditions.notNull(str2, "componentName");
        Mobilytics mo358get = this.lazyMobilytics.mo358get();
        MobilyticsMetricsCounter createCounter = mo358get.createCounter(str, str2, "alexa_accessories", OwnerIdentifier.APP_ACCESSORIES_GENERAL);
        AMAMetadata aMAMetadata = getAMAMetadata(map);
        if (aMAMetadata != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(aMAMetadata);
            createCounter.setEventMetadata(arrayList);
        }
        createCounter.incrementCounterByValue((long) d);
        mo358get.recordCounter(createCounter);
        Logger.v("MetricRecorder: Recorded accessory metric counter: %s, value: %f, attributes %s", str, Double.valueOf(d), map);
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordCriticalEvent(String str, String str2, String str3, Throwable th) {
        Preconditions.notNull(str, "errorName");
        Preconditions.notNull(str2, "errorTitle");
        Preconditions.notNull(str3, "subComponent");
        Preconditions.notNull(th, "throwable");
        this.lazyMobilytics.mo358get().recordCriticalEvent(str, str2, "alexa_accessories", str3, th, OwnerIdentifier.APP_ACCESSORIES_GENERAL);
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordOccurrence(String str, String str2, boolean z, Map<String, Object> map) {
        Preconditions.notNull(str, JsonFields.EVENT_NAME);
        Preconditions.notNull(str2, "componentName");
        recordCounter(str, str2, z ? 1.0d : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, map);
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordTime(String str, String str2, long j, Map<String, Object> map) {
        Preconditions.notNull(str, JsonFields.EVENT_NAME);
        Preconditions.notNull(str2, "componentName");
        MobilyticsMetricsTimer timer = getTimer(str, str2, "alexa_accessories", j, false);
        timer.finishTimer(0L);
        AMAMetadata aMAMetadata = getAMAMetadata(map);
        if (aMAMetadata != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(aMAMetadata);
            timer.setEventMetadata(arrayList);
        }
        this.lazyMobilytics.mo358get().recordTimer(timer);
        Logger.v("MetricRecorder: Recorded accessory metric timer: %s, value: %d, attributes %s", str, Long.valueOf(j), map);
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordTimer(String str) {
        Preconditions.notNull(str, "metricIdentifier");
        synchronized (this.mobilyticsTimerMap) {
            MobilyticsMetricsTimer remove = this.mobilyticsTimerMap.remove(str);
            if (remove != null) {
                this.lazyMobilytics.mo358get().recordTimer(remove);
                Logger.v("%s: recordTimer: metricIdentifier=%s, latency=%d", TAG, str, Long.valueOf(remove.getElapsedTime()));
            }
        }
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordWarningEvent(String str, String str2, String str3, Throwable th) {
        Preconditions.notNull(str, "errorName");
        Preconditions.notNull(str2, "errorTitle");
        Preconditions.notNull(str3, "subComponent");
        Preconditions.notNull(th, "throwable");
        this.lazyMobilytics.mo358get().recordWarningEvent(str, str2, "alexa_accessories", str3, th, OwnerIdentifier.APP_ACCESSORIES_GENERAL);
    }
}
