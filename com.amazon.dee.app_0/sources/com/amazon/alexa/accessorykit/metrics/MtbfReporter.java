package com.amazon.alexa.accessorykit.metrics;

import android.os.SystemClock;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessorykit.metrics.MtbfReporter;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashObserver;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public final class MtbfReporter extends AccessorySessionListener implements CrashObserver {
    private static final String MTBF_NAME = "accessories.mean_time_between_failures";
    static final String SEND_PRIORITY = "sendPriority";
    private final CrashMetadata crashMetadata;
    private final AccessoryMetricsService metricsService;
    private final SessionSupplier sessionSupplier;
    private final Map<String, Integer> deviceTypeCountMap = new HashMap();
    private final Map<String, AccessoryDeviceInfo> accessoryDeviceInfoMap = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class AccessoryDeviceInfo {
        private long connectedAt;
        private String deviceTypeId;
        private int firmwareVersion;

        private AccessoryDeviceInfo() {
            this.firmwareVersion = -1;
        }

        public AccessoryDeviceInfo setConnectedAt(long j) {
            this.connectedAt = j;
            return this;
        }

        public AccessoryDeviceInfo setDeviceTypeId(String str) {
            this.deviceTypeId = str;
            return this;
        }

        public AccessoryDeviceInfo setFirmwareVersion(int i) {
            this.firmwareVersion = i;
            return this;
        }
    }

    public MtbfReporter(SessionSupplier sessionSupplier, CrashMetadata crashMetadata, AccessoryMetricsService accessoryMetricsService) {
        this.sessionSupplier = sessionSupplier;
        this.crashMetadata = crashMetadata;
        this.metricsService = accessoryMetricsService;
    }

    private void decrementDeviceTypeCount(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        int intValue = this.deviceTypeCountMap.containsKey(str) ? this.deviceTypeCountMap.get(str).intValue() - 1 : 0;
        if (intValue > 0) {
            this.deviceTypeCountMap.put(str, Integer.valueOf(intValue));
            this.crashMetadata.put(str, intValue);
            return;
        }
        this.deviceTypeCountMap.remove(str);
        this.crashMetadata.put(str, 0);
    }

    private Single<AccessoryDeviceInfo> getAccessoryDeviceInfo(final Accessory accessory) {
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessorykit.metrics.-$$Lambda$MtbfReporter$jO4SRIPwSpm4Ge7OBvGrc_LVyOM
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return MtbfReporter.this.lambda$getAccessoryDeviceInfo$9$MtbfReporter(accessory);
            }
        }).subscribeOn(AndroidSchedulers.mainThread());
    }

    private void incrementDeviceTypeCount(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        int i = 1;
        if (this.deviceTypeCountMap.containsKey(str)) {
            i = 1 + this.deviceTypeCountMap.get(str).intValue();
        }
        this.deviceTypeCountMap.put(str, Integer.valueOf(i));
        this.crashMetadata.put(str, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$2(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation.getDeviceId() - deviceInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Device.DeviceInformation lambda$null$3(Set set) throws Throwable {
        return (Device.DeviceInformation) Collections.max(set, $$Lambda$MtbfReporter$wSAjteABw8sx9NQ_y0uEuDAav7I.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$5(Firmware.FirmwareInformation firmwareInformation, Firmware.FirmwareInformation firmwareInformation2) {
        return firmwareInformation.getDeviceId() - firmwareInformation2.getDeviceId();
    }

    static /* synthetic */ AccessoryDeviceInfo lambda$null$7(AccessoryDeviceInfo accessoryDeviceInfo, Set set) throws Throwable {
        return accessoryDeviceInfo;
    }

    public /* synthetic */ SingleSource lambda$getAccessoryDeviceInfo$9$MtbfReporter(Accessory accessory) throws Throwable {
        final AccessorySession session = this.sessionSupplier.getSession(accessory);
        if (session == null) {
            return Single.error(new IllegalStateException("Can not get the accessory session."));
        }
        return session.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError().map($$Lambda$MtbfReporter$SX5uLJBPEvm4rZ03BKcLh1sjLJs.INSTANCE).map(new Function() { // from class: com.amazon.alexa.accessorykit.metrics.-$$Lambda$MtbfReporter$hkf0y-YMPYYiQiGoSD1FuHoJelE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MtbfReporter.this.lambda$null$4$MtbfReporter((Device.DeviceInformation) obj);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessorykit.metrics.-$$Lambda$MtbfReporter$RPDK1XS8Zh--BxtT_vpLz6P0Ouc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                SingleSource onErrorReturnItem;
                onErrorReturnItem = AccessorySession.this.getFirmwareRepositoryV2().queryInformationSet().doOnSuccess(new Consumer() { // from class: com.amazon.alexa.accessorykit.metrics.-$$Lambda$MtbfReporter$eogfDUhlSStpZLqvFVPpcUUCCs4
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Object obj2) {
                        MtbfReporter.AccessoryDeviceInfo.this.setFirmwareVersion(((Firmware.FirmwareInformation) Collections.max((Set) obj2, $$Lambda$MtbfReporter$KM3O_60XiGiNCfyVkAIKoGfJLI.INSTANCE)).getVersion());
                    }
                }).map(new Function() { // from class: com.amazon.alexa.accessorykit.metrics.-$$Lambda$MtbfReporter$JUrtpmb_XzLHUR58mXi8ySBPm0E
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply */
                    public final Object mo10358apply(Object obj2) {
                        Set set = (Set) obj2;
                        return MtbfReporter.AccessoryDeviceInfo.this;
                    }
                }).onErrorReturnItem((MtbfReporter.AccessoryDeviceInfo) obj);
                return onErrorReturnItem;
            }
        });
    }

    public /* synthetic */ AccessoryDeviceInfo lambda$null$4$MtbfReporter(Device.DeviceInformation deviceInformation) throws Throwable {
        AccessoryDeviceInfo accessoryDeviceInfo = new AccessoryDeviceInfo();
        accessoryDeviceInfo.setDeviceTypeId(deviceInformation.getDeviceType());
        return accessoryDeviceInfo;
    }

    public /* synthetic */ void lambda$onAccessorySessionConnected$0$MtbfReporter(Accessory accessory, AccessoryDeviceInfo accessoryDeviceInfo) throws Throwable {
        incrementDeviceTypeCount(accessoryDeviceInfo.deviceTypeId);
        accessoryDeviceInfo.setConnectedAt(SystemClock.elapsedRealtime());
        this.accessoryDeviceInfoMap.put(accessory.getAddress(), accessoryDeviceInfo);
    }

    @Override // com.amazon.alexa.accessory.AccessorySessionListener
    public void onAccessorySessionConnected(final Accessory accessory) {
        getAccessoryDeviceInfo(accessory).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.metrics.-$$Lambda$MtbfReporter$ymZ_kStEkiH_O3Dt5omZy47tHhU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MtbfReporter.this.lambda$onAccessorySessionConnected$0$MtbfReporter(accessory, (MtbfReporter.AccessoryDeviceInfo) obj);
            }
        }, $$Lambda$MtbfReporter$qr5Sq7SL_uAxlsAhWk7h_59mdA.INSTANCE);
    }

    @Override // com.amazon.alexa.accessory.AccessorySessionListener
    public void onAccessorySessionReleased(Accessory accessory) {
        String address = accessory.getAddress();
        if (this.accessoryDeviceInfoMap.containsKey(address)) {
            decrementDeviceTypeCount(this.accessoryDeviceInfoMap.get(address).deviceTypeId);
            this.accessoryDeviceInfoMap.remove(address);
            return;
        }
        Logger.e("MtbfReporter can't find accessory type in cache, this should never happen.");
    }

    @Override // com.amazon.alexa.crashreporting.api.CrashObserver
    public void onCrash() {
        for (AccessoryDeviceInfo accessoryDeviceInfo : this.accessoryDeviceInfoMap.values()) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - accessoryDeviceInfo.connectedAt;
            HashMap hashMap = new HashMap();
            hashMap.put("sendPriority", 1);
            hashMap.put("deviceType_accessory", accessoryDeviceInfo.deviceTypeId);
            if (accessoryDeviceInfo.firmwareVersion != -1) {
                hashMap.put("firmware_accessory", Integer.valueOf(accessoryDeviceInfo.firmwareVersion));
            }
            this.metricsService.recordTime(MTBF_NAME, "alexa_accessories", elapsedRealtime, hashMap);
        }
    }
}
