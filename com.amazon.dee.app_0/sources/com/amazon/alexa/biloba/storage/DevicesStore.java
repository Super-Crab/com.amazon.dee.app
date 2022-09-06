package com.amazon.alexa.biloba.storage;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.dependency.BilobaScope;
import com.amazon.alexa.biloba.generated.models.DevicesResponse;
import com.amazon.alexa.biloba.generated.models.Message;
import com.amazon.alexa.biloba.generated.network.api.DevicesApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.model.Device;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.functions.Action1;
@Singleton
@BilobaScope
/* loaded from: classes6.dex */
public class DevicesStore extends DataStore {
    public static final String MESSAGE_RESPONSE_KEY_NO_DEVICES = "noDevicesMessage";
    public static final String MESSAGE_RESPONSE_KEY_ONLY_MOBILE_DEVICES = "onlyMobileDevicesMessage";
    private static final String TAG = "DevicesStore";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    @Inject
    Lazy<DevicesApi> deviceApi;
    private final MutableLiveData<List<Device>> devices;
    private final MutableLiveData<Map<String, Message>> messages;

    @Inject
    public DevicesStore() {
        this.devices = new MutableLiveData<>();
        this.messages = new MutableLiveData<>();
    }

    @Override // com.amazon.alexa.biloba.storage.DataStore
    public void clear() {
        this.devices.setValue(new ArrayList());
    }

    public LiveData<List<Device>> getDevices() {
        return this.devices;
    }

    public LiveData<Map<String, Message>> getMessages() {
        return this.messages;
    }

    synchronized void handleDevicesResponse(DevicesResponse devicesResponse) {
        this.devices.setValue(devicesResponse.getDevices());
        this.messages.setValue(devicesResponse.getMessages());
    }

    public /* synthetic */ void lambda$requestDevices$0$DevicesStore(MobilyticsMetricsTimer mobilyticsMetricsTimer, DevicesResponse devicesResponse) {
        this.crashMetadata.mo358get().put("fetch_all_devices_success", true);
        int size = devicesResponse.getDevices() != null ? devicesResponse.getDevices().size() : 0;
        this.crashMetadata.mo358get().put("fetch_all_devices_size", size);
        String str = TAG;
        LogUtils.d(str, "Received " + size + " devices from the Devices API");
        handleDevicesResponse(devicesResponse);
        setIsLoading(false);
        postError(null);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("getDevicesApi.Success", true);
    }

    public /* synthetic */ void lambda$requestDevices$1$DevicesStore(Throwable th) {
        LogUtils.e(TAG, th.getMessage());
        this.crashMetadata.mo358get().put("fetch_all_devices_success", false);
        this.crashReporter.mo358get().reportNonFatal(th);
        setIsLoading(false);
        postError(th);
        this.bilobaMetricsService.mo358get().recordOccurrence("getDevicesApi.Success", false);
    }

    public void requestDevices(String str) {
        LogUtils.i(TAG, "sendRequest for all devices");
        setIsLoading(true);
        LogUtils.i(TAG, "sendRequest for all devices through service");
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("getDevicesApi.Time");
        this.deviceApi.mo358get().getDevicesObservable(str, null).subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$DevicesStore$ftVazzEU_RbHfD-wpwqPQ9dXdEc
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DevicesStore.this.lambda$requestDevices$0$DevicesStore(startTimer, (DevicesResponse) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$DevicesStore$cMk798sQM3D7QUQsqXXJrTuSBKE
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DevicesStore.this.lambda$requestDevices$1$DevicesStore((Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    public DevicesStore(Lazy<DevicesApi> lazy, Lazy<CrashReporter> lazy2, Lazy<CrashMetadata> lazy3, Lazy<BilobaMetricsService> lazy4) {
        this();
        this.deviceApi = lazy;
        this.crashReporter = lazy2;
        this.crashMetadata = lazy3;
        this.bilobaMetricsService = lazy4;
    }
}
