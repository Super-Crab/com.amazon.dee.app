package com.amazon.alexa.accessorykit.metrics;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.repositories.device.DeviceSupplier;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Collections;
/* loaded from: classes6.dex */
public final class SessionMetricsListener extends AccessorySessionListener {
    private final DeviceSupplier deviceSupplier;
    private final AccessoryMetricsService metricsService;

    public SessionMetricsListener(AccessoryMetricsService accessoryMetricsService, DeviceSupplier deviceSupplier) {
        this.metricsService = accessoryMetricsService;
        this.deviceSupplier = deviceSupplier;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$0(Device device, Device device2) {
        return device.getDeviceId().intValue() - device2.getDeviceId().intValue();
    }

    @SuppressLint({"CheckResult"})
    private void recordAccessorySessionMetric(final String str, final String str2) {
        this.deviceSupplier.getDeviceGroup(str).subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.metrics.-$$Lambda$SessionMetricsListener$TbLUfIsCl_b0Z8VRysPmH6cWCVA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SessionMetricsListener.this.lambda$recordAccessorySessionMetric$1$SessionMetricsListener(str2, (DeviceGroup) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.metrics.-$$Lambda$SessionMetricsListener$wJAiUn67q2MG4Y0RoWcXWj37NWw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                Logger.d("Can't to get device from supplier, can't record metrics for address %s", str);
            }
        });
    }

    public /* synthetic */ void lambda$recordAccessorySessionMetric$1$SessionMetricsListener(String str, DeviceGroup deviceGroup) throws Throwable {
        if (deviceGroup.getDevices().isEmpty()) {
            return;
        }
        this.metricsService.recordCounter(str, ((Device) Collections.max(deviceGroup.getDevices(), $$Lambda$SessionMetricsListener$IZVvpwHEseTD95iuJji40sNEZs.INSTANCE)).getType(), 1.0d, Collections.emptyMap());
    }

    @Override // com.amazon.alexa.accessory.AccessorySessionListener
    public void onAccessorySessionConnected(Accessory accessory) {
        recordAccessorySessionMetric(accessory.getAddress(), "onAccessorySessionConnected");
    }

    @Override // com.amazon.alexa.accessory.AccessorySessionListener
    public void onAccessorySessionCreated(Accessory accessory) {
        recordAccessorySessionMetric(accessory.getAddress(), "onAccessorySessionCreated");
    }

    @Override // com.amazon.alexa.accessory.AccessorySessionListener
    public void onAccessorySessionDisconnected(Accessory accessory) {
        recordAccessorySessionMetric(accessory.getAddress(), "onAccessorySessionDisconnected");
    }

    @Override // com.amazon.alexa.accessory.AccessorySessionListener
    public void onAccessorySessionFailed(Accessory accessory, Throwable th) {
        recordAccessorySessionMetric(accessory.getAddress(), "onAccessorySessionFailed");
    }

    @Override // com.amazon.alexa.accessory.AccessorySessionListener
    public void onAccessorySessionReleased(Accessory accessory) {
        recordAccessorySessionMetric(accessory.getAddress(), "onAccessorySessionReleased");
    }

    @Override // com.amazon.alexa.accessory.AccessorySessionListener
    public void onAccessorySessionTransportChanged(Accessory accessory, AccessoryTransport.Type type, Accessory accessory2, AccessoryTransport.Type type2) {
        recordAccessorySessionMetric(accessory2.getAddress(), "onAccessorySessionTransportChanged");
    }
}
