package com.amazon.alexa.accessory.metrics;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public final class SessionInformationMetricsReporter implements MetricsReporter {
    private final DeviceRepositoryV2 deviceRepository;
    private final FirmwareRepositoryV2 firmwareRepository;
    private final MetricsReporter metricsReporter;
    private final TransportSupplier transportSupplier;

    /* loaded from: classes.dex */
    public interface TransportSupplier {
        AccessoryTransport.Type getTransportType();
    }

    public SessionInformationMetricsReporter(MetricsReporter metricsReporter, DeviceRepositoryV2 deviceRepositoryV2, FirmwareRepositoryV2 firmwareRepositoryV2, TransportSupplier transportSupplier) {
        Preconditions.notNull(metricsReporter, "metricsReporter");
        Preconditions.notNull(deviceRepositoryV2, "deviceRepository");
        Preconditions.notNull(firmwareRepositoryV2, "firmwareRepository");
        Preconditions.notNull(transportSupplier, "transportSupplier");
        this.metricsReporter = metricsReporter;
        this.deviceRepository = deviceRepositoryV2;
        this.firmwareRepository = firmwareRepositoryV2;
        this.transportSupplier = transportSupplier;
    }

    @SuppressLint({"CheckResult"})
    private Single<CustomMetricsEvent> addDeviceInformation(final CustomMetricsEvent customMetricsEvent) {
        return this.deviceRepository.queryDeviceInformationSet().firstOrError().flattenAsObservable($$Lambda$SessionInformationMetricsReporter$vEHZfFHybQavsNBZNtgjrC1Qh4.INSTANCE).observeOn(AndroidSchedulers.mainThread()).concatMap(new Function() { // from class: com.amazon.alexa.accessory.metrics.-$$Lambda$SessionInformationMetricsReporter$IAE835R3tQgscjCFskYC_FrYNlM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return SessionInformationMetricsReporter.lambda$addDeviceInformation$1(CustomMetricsEvent.this, (Device.DeviceInformation) obj);
            }
        }).toList().map(new Function() { // from class: com.amazon.alexa.accessory.metrics.-$$Lambda$SessionInformationMetricsReporter$gEkhpOX-nfpB4-Ex1AFUOkXmVRU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                List list = (List) obj;
                return CustomMetricsEvent.this;
            }
        }).onErrorReturnItem(customMetricsEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    public Single<CustomMetricsEvent> addFirmwareInformation(final CustomMetricsEvent customMetricsEvent) {
        return this.firmwareRepository.queryInformationSet().map($$Lambda$kTr_FAzIzBku4_pbIPWuzYj2wAQ.INSTANCE).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.accessory.metrics.-$$Lambda$SessionInformationMetricsReporter$h_TvF0kY9LbTh8hB7DhJ5hl5fbs
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SessionInformationMetricsReporter.lambda$addFirmwareInformation$3(CustomMetricsEvent.this, (Firmware.FirmwareInformation) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.metrics.-$$Lambda$SessionInformationMetricsReporter$TwolRKauRKrSmE7vVRMfT6dMSa4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Firmware.FirmwareInformation firmwareInformation = (Firmware.FirmwareInformation) obj;
                return CustomMetricsEvent.this;
            }
        }).onErrorReturnItem(customMetricsEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    public Single<MetricsEvent> addTransportInformation(final CustomMetricsEvent customMetricsEvent) {
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.metrics.-$$Lambda$SessionInformationMetricsReporter$SU8r7z-CGuH9TrnN1tZdr7b8v2Q
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return SessionInformationMetricsReporter.this.lambda$addTransportInformation$5$SessionInformationMetricsReporter(customMetricsEvent);
            }
        });
    }

    static /* synthetic */ Iterable lambda$addDeviceInformation$0(Set set) throws Throwable {
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ObservableSource lambda$addDeviceInformation$1(CustomMetricsEvent customMetricsEvent, Device.DeviceInformation deviceInformation) throws Throwable {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(MetricsConstants.Session.DEVICE_TYPE_PREFIX);
        outline107.append(deviceInformation.getDeviceId());
        customMetricsEvent.putString(outline107.toString(), deviceInformation.getDeviceType());
        customMetricsEvent.putString(MetricsConstants.Session.DEVICE_SERIAL_NUMBER_PREFIX + deviceInformation.getDeviceId(), deviceInformation.getSerialNumber());
        return Observable.just(deviceInformation);
    }

    static /* synthetic */ CustomMetricsEvent lambda$addDeviceInformation$2(CustomMetricsEvent customMetricsEvent, List list) throws Throwable {
        return customMetricsEvent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$addFirmwareInformation$3(CustomMetricsEvent customMetricsEvent, Firmware.FirmwareInformation firmwareInformation) throws Throwable {
        customMetricsEvent.putString(MetricsConstants.Session.FIRMWARE_NAME, firmwareInformation.getName());
        customMetricsEvent.putString(MetricsConstants.Session.FIRMWARE_LOCALE, firmwareInformation.getLocale());
        customMetricsEvent.putString(MetricsConstants.Session.FIRMWARE_VERSION_NAME, firmwareInformation.getVersionName());
    }

    static /* synthetic */ CustomMetricsEvent lambda$addFirmwareInformation$4(CustomMetricsEvent customMetricsEvent, Firmware.FirmwareInformation firmwareInformation) throws Throwable {
        return customMetricsEvent;
    }

    public /* synthetic */ SingleSource lambda$addTransportInformation$5$SessionInformationMetricsReporter(CustomMetricsEvent customMetricsEvent) throws Throwable {
        customMetricsEvent.putString(MetricsConstants.Session.TRANSPORT_TYPE, this.transportSupplier.getTransportType().name());
        return Single.just(customMetricsEvent);
    }

    @Override // com.amazon.alexa.accessory.metrics.MetricsReporter
    @SuppressLint({"CheckResult"})
    public void recordEvent(MetricsEvent metricsEvent) {
        Preconditions.notNull(metricsEvent, "event");
        Single onErrorReturnItem = addDeviceInformation(new CustomMetricsEvent(metricsEvent)).flatMap(new Function() { // from class: com.amazon.alexa.accessory.metrics.-$$Lambda$SessionInformationMetricsReporter$q-VlgOClvZ4pSS7mj92vfHYPXDU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Single addFirmwareInformation;
                addFirmwareInformation = SessionInformationMetricsReporter.this.addFirmwareInformation((CustomMetricsEvent) obj);
                return addFirmwareInformation;
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessory.metrics.-$$Lambda$SessionInformationMetricsReporter$L61Ds_L0TS9w-V00fqK6D-0r9hc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Single addTransportInformation;
                addTransportInformation = SessionInformationMetricsReporter.this.addTransportInformation((CustomMetricsEvent) obj);
                return addTransportInformation;
            }
        }).onErrorReturnItem(metricsEvent);
        final MetricsReporter metricsReporter = this.metricsReporter;
        metricsReporter.getClass();
        onErrorReturnItem.subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.metrics.-$$Lambda$4XzB1H-zYhKF209N_y492Yl-XYM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MetricsReporter.this.recordEvent((MetricsEvent) obj);
            }
        });
    }
}
