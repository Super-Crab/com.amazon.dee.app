package com.amazon.alexa.accessory.internal.session;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySupplier;
import com.amazon.alexa.accessory.capabilities.system.SystemCapability;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.RedactionUtil;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Collections;
import java.util.Set;
/* loaded from: classes.dex */
public final class SessionSystemCallback implements SystemCapability.Callback {
    private final AccessorySupplier accessorySupplier;
    private final Callback callback;
    private final DeviceRepositoryV2 deviceRepository;
    private final Supplier supplier;

    /* renamed from: com.amazon.alexa.accessory.internal.session.SessionSystemCallback$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$System$ResetConnection$ResetReason = new int[System.ResetConnection.ResetReason.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$System$ResetConnection$ResetReason[System.ResetConnection.ResetReason.AAP_REFUSED_MAX_CONNECTIONS_REACHED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$System$ResetConnection$ResetReason[System.ResetConnection.ResetReason.UNKNOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes.dex */
    public interface Callback {
        void disconnect();
    }

    /* loaded from: classes.dex */
    public interface Supplier {
        Accessory getConnectedAccessory();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SessionSystemCallback(AccessorySupplier accessorySupplier, Callback callback, Supplier supplier, DeviceRepositoryV2 deviceRepositoryV2) {
        Preconditions.notNull(accessorySupplier, "accessorySupplier");
        Preconditions.notNull(callback, "callback");
        Preconditions.notNull(supplier, "supplier");
        Preconditions.notNull(deviceRepositoryV2, "deviceRepository");
        this.accessorySupplier = accessorySupplier;
        this.callback = callback;
        this.supplier = supplier;
        this.deviceRepository = deviceRepositoryV2;
    }

    private static DeviceGroup.StandbyReason getStandbyReason(System.ResetConnection.ResetReason resetReason) {
        if (resetReason.ordinal() != 1) {
            return DeviceGroup.StandbyReason.UNKNOWN;
        }
        return DeviceGroup.StandbyReason.AAP_CONNECTION_REFUSAL;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onResetConnection$1(Accessory accessory, Throwable th) throws Throwable {
        Logger.d("ERROR: Failed to reset connection for accessory %s", th, accessory);
        Logger.e("Failed to reset connection for accessory %s", th, RedactionUtil.redact(accessory));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Device.DeviceInformation lambda$publishDeviceResetConnectionMetrics$3(Set set) throws Throwable {
        return (Device.DeviceInformation) Collections.max(set, $$Lambda$SessionSystemCallback$EECRROvN60IFoAEIl7bNLXlzU3c.INSTANCE);
    }

    private void publishDeviceResetConnectionMetrics(final int i, final boolean z, final AccessoryMetricsService accessoryMetricsService) {
        this.deviceRepository.queryDeviceInformationSet().map($$Lambda$SessionSystemCallback$FqphYIK3DbAJY1Ksz1RMm1PbYE.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$SessionSystemCallback$4EHURIq6RhnymUIikUhqfQk_iHk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SessionSystemCallback.this.lambda$publishDeviceResetConnectionMetrics$4$SessionSystemCallback(i, z, accessoryMetricsService, (Device.DeviceInformation) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$SessionSystemCallback$6kjj4D74ijdkwd3yZ3mnCFQv7mE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SessionSystemCallback.this.lambda$publishDeviceResetConnectionMetrics$5$SessionSystemCallback(i, z, accessoryMetricsService, (Throwable) obj);
            }
        });
    }

    private void publishDeviceResetConnectionMetricsForDeviceType(int i, boolean z, AccessoryMetricsService accessoryMetricsService, String str) {
        StringBuilder sb = new StringBuilder();
        if (z && i == 0) {
            sb.append(MetricsConstants.Session.DEVICE_RESET_CONNECTION_FORCE_DISCONNECT_NO_TIMEOUT);
        } else if (z) {
            sb.append(MetricsConstants.Session.DEVICE_RESET_CONNECTION_FORCE_DISCONNECT_HAS_TIMEOUT);
        } else if (i == 0) {
            sb.append(MetricsConstants.Session.DEVICE_RESET_CONNECTION_NOT_FORCE_DISCONNECT_NO_TIMEOUT);
        } else {
            sb.append(MetricsConstants.Session.DEVICE_RESET_CONNECTION_NOT_FORCE_DISCONNECT_HAS_TIMEOUT);
        }
        accessoryMetricsService.recordOccurrence(GeneratedOutlineSupport1.outline91(sb, ":", str), MetricsConstants.Session.DEVICE_RESET_CONNECTION_RECEIVED, true, null);
    }

    public /* synthetic */ void lambda$onResetConnection$0$SessionSystemCallback(boolean z, AccessoryMetricsService accessoryMetricsService) throws Throwable {
        if (z) {
            this.callback.disconnect();
            accessoryMetricsService.recordOccurrence(MetricsConstants.Session.SESSION_RELEASED, MetricsConstants.Session.SESSION_RELEASED_REASON_RESET_CONNECTION_ACCESSORY_INITIATED, true, null);
        }
    }

    public /* synthetic */ void lambda$publishDeviceResetConnectionMetrics$4$SessionSystemCallback(int i, boolean z, AccessoryMetricsService accessoryMetricsService, Device.DeviceInformation deviceInformation) throws Throwable {
        publishDeviceResetConnectionMetricsForDeviceType(i, z, accessoryMetricsService, deviceInformation.getDeviceType());
    }

    public /* synthetic */ void lambda$publishDeviceResetConnectionMetrics$5$SessionSystemCallback(int i, boolean z, AccessoryMetricsService accessoryMetricsService, Throwable th) throws Throwable {
        Logger.e("Failed to query device information set", th);
        publishDeviceResetConnectionMetricsForDeviceType(i, z, accessoryMetricsService, "UNKNOWN_DEVICE_TYPE");
    }

    @Override // com.amazon.alexa.accessory.capabilities.system.SystemCapability.Callback
    @SuppressLint({"CheckResult"})
    public void onResetConnection(int i, final boolean z, System.ResetConnection.ResetReason resetReason) {
        Preconditions.mainThread();
        final AccessoryMetricsService accessoryMetricsService = AccessoryMetricsServiceHolder.getInstance().get();
        publishDeviceResetConnectionMetrics(i, z, accessoryMetricsService);
        final Accessory connectedAccessory = this.supplier.getConnectedAccessory();
        this.accessorySupplier.standby(connectedAccessory, i, getStandbyReason(resetReason)).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$SessionSystemCallback$henuSVl16TKXNK8nJROjtlz41Jc
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                SessionSystemCallback.this.lambda$onResetConnection$0$SessionSystemCallback(z, accessoryMetricsService);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$SessionSystemCallback$9hr_9CUHB-K3fMAnM4kIOXNiFAo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SessionSystemCallback.lambda$onResetConnection$1(Accessory.this, (Throwable) obj);
            }
        });
    }
}
