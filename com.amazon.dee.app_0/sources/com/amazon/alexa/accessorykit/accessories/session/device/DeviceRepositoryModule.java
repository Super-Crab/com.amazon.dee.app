package com.amazon.alexa.accessorykit.accessories.session.device;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.repositories.device.DeviceFeatures;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier;
import com.amazon.alexa.accessoryclient.common.api.ConnectionStatus;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformerHelper;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRN;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRNEventId;
import com.amazon.alexa.accessorykit.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.Set;
/* loaded from: classes6.dex */
public final class DeviceRepositoryModule {
    private final AccessoryMetricsService accessoryMetricsService;
    private final SessionSupplier clientSessionSupplier;
    private final MapModelTransformer<Device.DeviceConfiguration> deviceConfigurationTransformer;
    private final MapModelTransformer<DeviceFeatures> deviceFeaturesTransformer;
    private final ArrayModelTransformer<Set<Device.DeviceInformation>> deviceModelTransformer;
    private final RxRN rxRN;

    public DeviceRepositoryModule(ContainerProvider containerProvider, RxRN rxRN, SessionSupplier sessionSupplier, AccessoryMetricsService accessoryMetricsService) {
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(rxRN, "rxRN");
        Preconditions.notNull(sessionSupplier, "clientSessionSupplier");
        Preconditions.notNull(accessoryMetricsService, "accessoryMetricsService");
        this.clientSessionSupplier = sessionSupplier;
        this.deviceModelTransformer = new DeviceInformationSetModelTransformer(containerProvider);
        this.deviceConfigurationTransformer = new DeviceConfigurationModelTransformer(containerProvider);
        this.rxRN = rxRN;
        this.accessoryMetricsService = accessoryMetricsService;
        this.deviceFeaturesTransformer = new DeviceFeaturesModelTransformer(containerProvider, new ArrayModelTransformerHelper(containerProvider, new DeviceFeatureModelTransformer(containerProvider, new ArrayModelTransformerHelper(containerProvider, new SubFeatureModelTransformer(containerProvider)))));
    }

    public static void publishMetricsWithDeviceType(DeviceRepositoryV2 deviceRepositoryV2, final String str, final boolean z, final AccessoryMetricsService accessoryMetricsService) {
        deviceRepositoryV2.queryDeviceInformationSet().firstOrError().map($$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w.INSTANCE).map($$Lambda$fFtSfZI18QY7Io9iFa3QkWDvcnQ.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.device.-$$Lambda$DeviceRepositoryModule$z4XDDH18H7HdLSt9-DssOr6mrts
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryMetricsService.this.recordOccurrence(str, (String) obj, z, null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.device.-$$Lambda$DeviceRepositoryModule$WzWvKLq_n8oF0S4IP6rdoEGW7AA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                AccessoryMetricsService.this.recordOccurrence(str, "UNKNOWN", z, null);
            }
        });
    }

    public /* synthetic */ void lambda$null$0$DeviceRepositoryModule(DeviceRepositoryV2 deviceRepositoryV2, Common.ErrorCode errorCode) throws Throwable {
        publishMetricsWithDeviceType(deviceRepositoryV2, MetricsConstants.Oobe.START_SETUP_REQUEST_SUCCESS, true, this.accessoryMetricsService);
    }

    public /* synthetic */ void lambda$null$1$DeviceRepositoryModule(DeviceRepositoryV2 deviceRepositoryV2, Throwable th) throws Throwable {
        publishMetricsWithDeviceType(deviceRepositoryV2, MetricsConstants.Oobe.START_SETUP_REQUEST_SUCCESS, false, this.accessoryMetricsService);
    }

    public /* synthetic */ void lambda$null$4$DeviceRepositoryModule(DeviceRepositoryV2 deviceRepositoryV2, Common.ErrorCode errorCode) throws Throwable {
        publishMetricsWithDeviceType(deviceRepositoryV2, MetricsConstants.Oobe.COMPLETE_SETUP_REQUEST_SUCCESS, true, this.accessoryMetricsService);
    }

    public /* synthetic */ void lambda$null$5$DeviceRepositoryModule(DeviceRepositoryV2 deviceRepositoryV2, Throwable th) throws Throwable {
        publishMetricsWithDeviceType(deviceRepositoryV2, MetricsConstants.Oobe.COMPLETE_SETUP_REQUEST_SUCCESS, false, this.accessoryMetricsService);
    }

    public /* synthetic */ SingleSource lambda$requestCompleteSetup$6$DeviceRepositoryModule(String str, final DeviceRepositoryV2 deviceRepositoryV2, boolean z, ConnectionStatus connectionStatus) throws Throwable {
        if (connectionStatus == ConnectionStatus.NONEXISTENT) {
            this.accessoryMetricsService.recordOccurrence(MetricsConstants.Oobe.COMPLETE_SETUP_FAILED_NO_SESSION, "UNKNOWN", true, null);
            return Single.error(new Exception(GeneratedOutlineSupport1.outline72("No session for identifier ", str)));
        }
        this.accessoryMetricsService.recordOccurrence(MetricsConstants.Oobe.COMPLETE_SETUP_FAILED_NO_SESSION, "UNKNOWN", false, null);
        return deviceRepositoryV2.requestCompleteSetup(z).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.device.-$$Lambda$DeviceRepositoryModule$AACc2JCJc5V6Bu1LA4rF7W75XEQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DeviceRepositoryModule.this.lambda$null$4$DeviceRepositoryModule(deviceRepositoryV2, (Common.ErrorCode) obj);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.device.-$$Lambda$DeviceRepositoryModule$SZvlccQKDlTgg3NoaXB5yPXyRSA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DeviceRepositoryModule.this.lambda$null$5$DeviceRepositoryModule(deviceRepositoryV2, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$requestStartSetup$2$DeviceRepositoryModule(String str, final DeviceRepositoryV2 deviceRepositoryV2, ConnectionStatus connectionStatus) throws Throwable {
        if (connectionStatus == ConnectionStatus.NONEXISTENT) {
            this.accessoryMetricsService.recordOccurrence(MetricsConstants.Oobe.START_SETUP_FAILED_NO_SESSION, "UNKNOWN", true, null);
            return Single.error(new Exception(GeneratedOutlineSupport1.outline72("No session for identifier ", str)));
        }
        this.accessoryMetricsService.recordOccurrence(MetricsConstants.Oobe.START_SETUP_FAILED_NO_SESSION, "UNKNOWN", false, null);
        return deviceRepositoryV2.requestStartSetup().doOnSuccess(new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.device.-$$Lambda$DeviceRepositoryModule$v6nxDwjAsGofd6dWYuoAIHPjNDY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DeviceRepositoryModule.this.lambda$null$0$DeviceRepositoryModule(deviceRepositoryV2, (Common.ErrorCode) obj);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.device.-$$Lambda$DeviceRepositoryModule$8hvM-zkUw9E7phifWNR9evhdYm4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DeviceRepositoryModule.this.lambda$null$1$DeviceRepositoryModule(deviceRepositoryV2, (Throwable) obj);
            }
        });
    }

    public void queryConfiguration(ReadableMap readableMap, String str) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.deviceConfigurationTransformer, this.clientSessionSupplier.getSession(str).getDeviceRepository().queryDeviceConfiguration());
    }

    public void queryDeviceFeatures(ReadableMap readableMap, String str) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.deviceFeaturesTransformer, this.clientSessionSupplier.getSession(str).getDeviceRepository().queryDeviceFeatures().toObservable());
    }

    public void queryInformationSet(ReadableMap readableMap, String str) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.deviceModelTransformer, this.clientSessionSupplier.getSession(str).getDeviceRepository().queryDeviceInformationSet());
    }

    public void requestCompleteSetup(final String str, final boolean z, final Promise promise) {
        AccessorySession session = this.clientSessionSupplier.getSession(str);
        final DeviceRepositoryV2 deviceRepository = session.getDeviceRepository();
        Single<R> flatMap = session.queryConnectionStatus().flatMap(new Function() { // from class: com.amazon.alexa.accessorykit.accessories.session.device.-$$Lambda$DeviceRepositoryModule$qfUk72rSMx1RhDYbEwyzjrMiSO8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DeviceRepositoryModule.this.lambda$requestCompleteSetup$6$DeviceRepositoryModule(str, deviceRepository, z, (ConnectionStatus) obj);
            }
        });
        Consumer consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.device.-$$Lambda$DeviceRepositoryModule$AyTaXG0PnohQCxbFKDxCFQMmxgk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve(Integer.valueOf(((Common.ErrorCode) obj).getNumber()));
            }
        };
        promise.getClass();
        flatMap.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    public void requestOverrideAssistant(String str, boolean z, final Promise promise) {
        Single<Common.ErrorCode> requestOverrideAssistant = this.clientSessionSupplier.getSession(str).getDeviceRepository().requestOverrideAssistant(z);
        Consumer<? super Common.ErrorCode> consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.device.-$$Lambda$DeviceRepositoryModule$na6jY1w5b6rDBn5AkH3fs7fGe6A
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve(Integer.valueOf(((Common.ErrorCode) obj).getNumber()));
            }
        };
        promise.getClass();
        requestOverrideAssistant.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    public void requestStartSetup(final String str, final Promise promise) {
        AccessorySession session = this.clientSessionSupplier.getSession(str);
        final DeviceRepositoryV2 deviceRepository = session.getDeviceRepository();
        Single<R> flatMap = session.queryConnectionStatus().flatMap(new Function() { // from class: com.amazon.alexa.accessorykit.accessories.session.device.-$$Lambda$DeviceRepositoryModule$tRyq4sJRISLUFDpbKrygn5A8EXM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DeviceRepositoryModule.this.lambda$requestStartSetup$2$DeviceRepositoryModule(str, deviceRepository, (ConnectionStatus) obj);
            }
        });
        Consumer consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.device.-$$Lambda$DeviceRepositoryModule$p5fmkfvwB2qLcVSQCtN4ygODg-4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve(Integer.valueOf(((Common.ErrorCode) obj).getNumber()));
            }
        };
        promise.getClass();
        flatMap.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }
}
