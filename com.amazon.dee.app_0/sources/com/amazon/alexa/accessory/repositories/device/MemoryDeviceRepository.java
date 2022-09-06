package com.amazon.alexa.accessory.repositories.device;

import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.device.DeviceProducer;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes6.dex */
public final class MemoryDeviceRepository extends BaseProducer<DeviceProducer.ActionHandler> implements DeviceRepositoryV2, DeviceProvider, DeviceProducer {
    private static final String TAG = "MemoryDeviceRepository";
    private final ReplaySubject<Set<Device.DeviceInformation>> deviceInformationSubject = ReplaySubject.createWithSize(1);
    private final ReplaySubject<Device.DeviceConfiguration> deviceConfigurationSubject = ReplaySubject.createWithSize(1);
    private final ReplaySubject<DeviceFeatures> deviceFeaturesSubject = ReplaySubject.createWithSize(1);

    private static Set<Integer> createSetOfIds(Set<Device.DeviceInformation> set) {
        HashSet hashSet = new HashSet();
        for (Device.DeviceInformation deviceInformation : set) {
            hashSet.add(Integer.valueOf(deviceInformation.getDeviceId()));
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$queryDeviceInformation$0(Set set) throws Throwable {
        return !set.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Device.DeviceInformation lambda$queryDeviceInformation$1(Set set) throws Throwable {
        return (Device.DeviceInformation) set.toArray()[0];
    }

    public /* synthetic */ void lambda$requestCompleteSetup$2$MemoryDeviceRepository(boolean z, Producer.Result result) {
        getHandler().handleCompleteSetup(z, result);
    }

    public /* synthetic */ void lambda$requestOverrideAssistant$3$MemoryDeviceRepository(boolean z, Producer.Result result) {
        getHandler().handleOverrideAssistant(z, result);
    }

    public /* synthetic */ void lambda$requestUpdateDeviceInformation$4$MemoryDeviceRepository(String str, int i, Producer.Result result) {
        getHandler().handleUpdateDeviceInformation(str, i, result);
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceProvider
    public void provideDeviceConfiguration(Device.DeviceConfiguration deviceConfiguration) {
        Preconditions.notNull(deviceConfiguration, "deviceConfiguration");
        Preconditions.mainThread();
        Logger.d("Device configuration added to device repository: " + deviceConfiguration);
        this.deviceConfigurationSubject.onNext(deviceConfiguration);
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceProvider
    public void provideDeviceFeatures(Device.DeviceFeatures deviceFeatures) {
        Preconditions.notNull(deviceFeatures, NetworkConstants.FEATURES_KEY);
        Preconditions.mainThread();
        Logger.d("Device features provided to device repository: " + deviceFeatures);
        this.deviceFeaturesSubject.onNext(DeviceFeatures.fromProtobuf(deviceFeatures));
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceProvider
    public void provideDeviceFeaturesError(Throwable th) {
        Preconditions.notNull(th, "throwable");
        Preconditions.mainThread();
        Logger.d("%s: Device features provided an error to the device repository: ", th, TAG);
        this.deviceFeaturesSubject.onError(th);
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceProvider
    public void provideDeviceInformationSet(Set<Device.DeviceInformation> set) {
        Set<Device.DeviceInformation> emptySet;
        Preconditions.notNull(set, "deviceInformationSet");
        Preconditions.mainThread();
        Logger.d("Device information set provided to device repository: " + set);
        if (this.deviceInformationSubject.hasValue()) {
            emptySet = this.deviceInformationSubject.getValue();
        } else {
            emptySet = Collections.emptySet();
        }
        Set<Integer> createSetOfIds = createSetOfIds(set);
        HashSet hashSet = new HashSet();
        for (Device.DeviceInformation deviceInformation : emptySet) {
            if (!createSetOfIds.contains(Integer.valueOf(deviceInformation.getDeviceId()))) {
                hashSet.add(deviceInformation);
            }
        }
        hashSet.addAll(set);
        Logger.d("Device information set added to device repository: " + hashSet);
        this.deviceInformationSubject.onNext(Collections.unmodifiableSet(hashSet));
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceRepository
    public Observable<Device.DeviceConfiguration> queryDeviceConfiguration() {
        return this.deviceConfigurationSubject;
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2
    public Single<DeviceFeatures> queryDeviceFeatures() {
        return this.deviceFeaturesSubject.firstOrError();
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceRepository
    public Observable<Device.DeviceInformation> queryDeviceInformation() {
        return this.deviceInformationSubject.filter($$Lambda$MemoryDeviceRepository$_cU3Jjq1gt_2Lf6818xqHr_GhA.INSTANCE).map($$Lambda$MemoryDeviceRepository$cSjaNNqFMcjv6qJ5aRdhrV9imU.INSTANCE);
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2
    public Observable<Set<Device.DeviceInformation>> queryDeviceInformationSet() {
        return this.deviceInformationSubject;
    }

    public void release() {
        ObservableUtils.release(this.deviceInformationSubject);
        ObservableUtils.release(this.deviceConfigurationSubject);
        ObservableUtils.release(this.deviceFeaturesSubject);
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceRepository
    public Single<Common.ErrorCode> requestCompleteSetup(final boolean z) {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.device.-$$Lambda$MemoryDeviceRepository$eAr0VXLCkUgQjBSLh_TR_FJEc38
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryDeviceRepository.this.lambda$requestCompleteSetup$2$MemoryDeviceRepository(z, result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceRepository
    public Single<Common.ErrorCode> requestOverrideAssistant(final boolean z) {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.device.-$$Lambda$MemoryDeviceRepository$FCPI167k4P8Fa0XJ60GTqfxZFJU
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryDeviceRepository.this.lambda$requestOverrideAssistant$3$MemoryDeviceRepository(z, result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceRepository
    public Single<Common.ErrorCode> requestStartSetup() {
        final DeviceProducer.ActionHandler handler = getHandler();
        handler.getClass();
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.device.-$$Lambda$kEr989cy6NpyD6yBELjWfwKpkzg
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                DeviceProducer.ActionHandler.this.handleStartSetup(result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceRepository
    public Single<Common.ErrorCode> requestUpdateDeviceInformation(String str) {
        return requestUpdateDeviceInformation(str, 0);
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2
    public Single<Common.ErrorCode> requestUpdateDeviceInformation(final String str, final int i) {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.device.-$$Lambda$MemoryDeviceRepository$9_Mw8Ckd1GUwanbbMibL6Iq_fA8
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryDeviceRepository.this.lambda$requestUpdateDeviceInformation$4$MemoryDeviceRepository(str, i, result);
            }
        });
    }
}
