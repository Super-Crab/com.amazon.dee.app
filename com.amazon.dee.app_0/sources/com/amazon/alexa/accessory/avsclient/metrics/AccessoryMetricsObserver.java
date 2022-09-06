package com.amazon.alexa.accessory.avsclient.metrics;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.capabilities.metrics.AccessoryMetric;
import com.amazon.alexa.accessory.capabilities.metrics.MetricsObserver;
import com.amazon.alexa.accessory.internal.util.Int64Util;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.protocol.Metrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class AccessoryMetricsObserver implements MetricsObserver {
    private final SparseArray<Handler> handlers;
    private final AccessoryMetricsService metricsService;

    /* renamed from: com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsObserver$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$capabilities$metrics$AccessoryMetric$Destination = new int[AccessoryMetric.Destination.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$capabilities$metrics$AccessoryMetric$Type;

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$metrics$AccessoryMetric$Destination[AccessoryMetric.Destination.USER_PRESENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$metrics$AccessoryMetric$Destination[AccessoryMetric.Destination.APP_USAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$com$amazon$alexa$accessory$capabilities$metrics$AccessoryMetric$Type = new int[AccessoryMetric.Type.values().length];
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$metrics$AccessoryMetric$Type[AccessoryMetric.Type.TIMER.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$metrics$AccessoryMetric$Type[AccessoryMetric.Type.COUNTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public interface Handler {
        void handle(AccessorySession accessorySession, Metrics.MetricsEvent metricsEvent, Map<String, Object> map, AccessoryMetricsService accessoryMetricsService);
    }

    public AccessoryMetricsObserver(AccessoryMetricsService accessoryMetricsService) {
        Preconditions.notNull(accessoryMetricsService, "metricsService");
        this.metricsService = accessoryMetricsService;
        this.handlers = new MetricMappings().getMap();
    }

    private static boolean isDEMMetrics(AccessoryMetric accessoryMetric) {
        Logger.d("AccessoryMetricsObserver.isDEMMetrics is triggered");
        AccessoryMetric.Destination destination = accessoryMetric.getDestination();
        if (destination == null) {
            return false;
        }
        int ordinal = destination.ordinal();
        return ordinal == 0 || ordinal == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$0(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation.getDeviceId() - deviceInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$3(Firmware.FirmwareInformation firmwareInformation, Firmware.FirmwareInformation firmwareInformation2) {
        return firmwareInformation.getDeviceId() - firmwareInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$null$4(Map map, Set set) throws Throwable {
        map.put("firmware_accessory", Integer.valueOf(((Firmware.FirmwareInformation) Collections.min(set, $$Lambda$AccessoryMetricsObserver$J9pcSt7RaSTsdrYJ4pcXOJdZvAI.INSTANCE)).getVersion()));
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            Firmware.FirmwareInformation firmwareInformation = (Firmware.FirmwareInformation) it2.next();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("firmware_accessory_");
            outline107.append(firmwareInformation.getDeviceId());
            map.put(outline107.toString(), Integer.valueOf(firmwareInformation.getVersion()));
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Device.DeviceInformation lambda$onMetricsAvailable$1(Set set) throws Throwable {
        return (Device.DeviceInformation) Collections.max(set, $$Lambda$AccessoryMetricsObserver$Du6lIzA84kGkPr59dZ4dJhWinU.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$onMetricsAvailable$2(Device.DeviceInformation deviceInformation) throws Throwable {
        HashMap hashMap = new HashMap();
        hashMap.put("deviceType_accessory", deviceInformation.getDeviceType());
        hashMap.put("deviceId_accessory", deviceInformation.getSerialNumber());
        return hashMap;
    }

    private void processMetrics(List<AccessoryMetric> list, Map<String, Object> map) {
        Logger.d("AccessoryMetricsObserver.processMetrics is triggered");
        for (AccessoryMetric accessoryMetric : list) {
            HashMap hashMap = new HashMap(map);
            hashMap.put(AccessoryMetricsConstants.TIMESTAMP, Long.valueOf(accessoryMetric.getTimestamp()));
            long bootNumber = accessoryMetric.getBootNumber();
            long sequenceNumber = accessoryMetric.getSequenceNumber();
            hashMap.put(AccessoryMetricsConstants.BOOT_NUMBER, String.valueOf(bootNumber));
            hashMap.put(AccessoryMetricsConstants.SEQUENCE_NUMBER, String.valueOf(sequenceNumber));
            String name = accessoryMetric.getName();
            List<AccessoryMetric.Value> values = accessoryMetric.getValues();
            if (values != null && !values.isEmpty()) {
                if (isDEMMetrics(accessoryMetric)) {
                    Logger.d("AccessoryMetricsObserver.processMetrics,skipping since its a DEM metric: %s", accessoryMetric.toString());
                } else {
                    int ordinal = accessoryMetric.getType().ordinal();
                    if (ordinal == 0) {
                        CounterMetricsHandler.handle(accessoryMetric, hashMap, this.metricsService);
                    } else if (ordinal != 1) {
                        Logger.e("AccessoryMetricsObserver dropping accessoryMetric %s, unknown metric type.", name);
                    } else {
                        TimerMetricsHandler.handle(accessoryMetric, hashMap, this.metricsService);
                    }
                }
            } else {
                Logger.e("AccessoryMetricsObserver dropping accessoryMetric %s with no value.", name);
            }
        }
    }

    private void processUnknownMetrics(AccessorySession accessorySession, List<Metrics.MetricsEvent> list, Map<String, Object> map) {
        Logger.d("Processing %d metric(s) received from the accessory", Integer.valueOf(list.size()));
        for (Metrics.MetricsEvent metricsEvent : list) {
            HashMap hashMap = new HashMap(map);
            hashMap.put(AccessoryMetricsConstants.TIMESTAMP, Long.valueOf(Int64Util.getLong(metricsEvent.getTimestampHi(), metricsEvent.getTimestampLo())));
            long j = Int64Util.getLong(0, metricsEvent.getBootNum());
            long j2 = Int64Util.getLong(0, metricsEvent.getSeqNum());
            hashMap.put(AccessoryMetricsConstants.BOOT_NUMBER, String.valueOf(j));
            hashMap.put(AccessoryMetricsConstants.SEQUENCE_NUMBER, String.valueOf(j2));
            Handler handler = this.handlers.get(metricsEvent.getKey());
            if (handler != null) {
                handler.handle(accessorySession, metricsEvent, hashMap, this.metricsService);
            } else {
                Logger.e("Dropping unknown metric (%s) received from the accessory.", Integer.valueOf(metricsEvent.getKey()));
            }
        }
    }

    public /* synthetic */ void lambda$onMetricsAvailable$6$AccessoryMetricsObserver(List list, AccessorySession accessorySession, List list2, Map map) throws Throwable {
        processMetrics(list, map);
        processUnknownMetrics(accessorySession, list2, map);
    }

    @Override // com.amazon.alexa.accessory.capabilities.metrics.MetricsObserver
    @SuppressLint({"CheckResult"})
    public void onMetricsAvailable(final AccessorySession accessorySession, final List<AccessoryMetric> list, final List<Metrics.MetricsEvent> list2) {
        Preconditions.notNull(accessorySession, "accessorySession");
        Preconditions.notNull(list, "accessoryMetrics");
        Preconditions.notNull(list2, "unknownMetricsEvents");
        accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError().map($$Lambda$AccessoryMetricsObserver$lEbr6AFsQ0YIXNWXVYh_ScS_ptU.INSTANCE).map($$Lambda$AccessoryMetricsObserver$oxZ7vkfzfSYG9GfmWLU7_oI8zHE.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.avsclient.metrics.-$$Lambda$AccessoryMetricsObserver$Xc9LUw2FQF6YbtLj-qI0S8RLZv8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                SingleSource onErrorReturnItem;
                onErrorReturnItem = AccessorySession.this.getFirmwareRepositoryV2().queryInformationSet().map(new Function() { // from class: com.amazon.alexa.accessory.avsclient.metrics.-$$Lambda$AccessoryMetricsObserver$RhibhHqcShJLGEM3fA5_KdawePg
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply */
                    public final Object mo10358apply(Object obj2) {
                        Map map = r1;
                        AccessoryMetricsObserver.lambda$null$4(map, (Set) obj2);
                        return map;
                    }
                }).onErrorReturnItem((Map) obj);
                return onErrorReturnItem;
            }
        }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.metrics.-$$Lambda$AccessoryMetricsObserver$ppD3zNt__UYD3WZcR9DoEoJbIJ4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryMetricsObserver.this.lambda$onMetricsAvailable$6$AccessoryMetricsObserver(list, accessorySession, list2, (Map) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.metrics.-$$Lambda$AccessoryMetricsObserver$dU4QeaSFRUNckccWb-OJEtBujP0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                Logger.e("AccessoryMetricsObserver failed to query device information, dropping %d accessoryMetrics received.", Integer.valueOf(list2.size() + list.size()));
            }
        });
    }
}
