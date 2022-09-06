package com.amazon.alexa.accessory.repositories.firmware;

import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.kota.InventoryUpdateBundle;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareProducer;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.processors.BehaviorProcessor;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes6.dex */
public final class MemoryFirmwareRepository extends BaseProducer<FirmwareProducer.ActionHandler> implements FirmwareRepository, FirmwareRepositoryV2, FirmwareProvider, FirmwareProducer {
    private final ReplaySubject<Set<Firmware.FirmwareInformation>> informationSubject = ReplaySubject.createWithSize(1);
    private final ReplaySubject<Set<InventoryUpdateBundle>> inventoryUpdateSubject = ReplaySubject.create(1);
    private final BehaviorProcessor<FirmwareUpdateStatus> updateStatusSubject = BehaviorProcessor.createDefault(FirmwareUpdateStatus.idle());

    private static Set<Integer> createSetOfIds(Set<Firmware.FirmwareInformation> set) {
        HashSet hashSet = new HashSet();
        for (Firmware.FirmwareInformation firmwareInformation : set) {
            hashSet.add(Integer.valueOf(firmwareInformation.getDeviceId()));
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Firmware.FirmwareInformation lambda$queryInformation$0(Set set) throws Throwable {
        return (Firmware.FirmwareInformation) set.iterator().next();
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareProvider
    public void informationNotAvailable(Throwable th) {
        Preconditions.mainThread();
        Preconditions.precondition(!this.informationSubject.hasValue(), "Cannot call firmware information not available after providing firmware information");
        if (this.informationSubject.hasThrowable() || this.informationSubject.hasComplete()) {
            return;
        }
        Logger.d("Firmware information not available for the accessory.");
        this.informationSubject.onError(th);
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2
    public Completable initiateFirmwareTransfer() {
        return CompletableResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.firmware.-$$Lambda$MemoryFirmwareRepository$ddpJYxLFEfn5gftEu2l8BQIl4W4
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryFirmwareRepository.this.lambda$initiateFirmwareTransfer$2$MemoryFirmwareRepository(result);
            }
        });
    }

    public /* synthetic */ void lambda$initiateFirmwareTransfer$2$MemoryFirmwareRepository(Producer.Result result) {
        getHandler().initiateFirmwareTransfer(result);
    }

    public /* synthetic */ void lambda$queryInventoryUpdateSet$1$MemoryFirmwareRepository(Producer.Result result) {
        getHandler().handleCheckInventoryUpdate(true, result);
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareProvider
    public void provideInformation(Set<Firmware.FirmwareInformation> set) {
        Preconditions.notNull(set, "firmwareInformationSet");
        Preconditions.mainThread();
        if (this.informationSubject.hasThrowable() || this.informationSubject.hasComplete()) {
            return;
        }
        Set<Firmware.FirmwareInformation> value = this.informationSubject.hasValue() ? this.informationSubject.getValue() : Collections.emptySet();
        Set<Integer> createSetOfIds = createSetOfIds(set);
        HashSet hashSet = new HashSet();
        for (Firmware.FirmwareInformation firmwareInformation : value) {
            if (!createSetOfIds.contains(Integer.valueOf(firmwareInformation.getDeviceId()))) {
                hashSet.add(firmwareInformation);
            }
        }
        hashSet.addAll(set);
        Logger.d("Firmware information added to firmware repository: " + hashSet);
        this.informationSubject.onNext(Collections.unmodifiableSet(hashSet));
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareProvider
    public void provideInventoryUpdate(Set<InventoryUpdateBundle> set) {
        Preconditions.mainThread();
        if (this.inventoryUpdateSubject.hasValue() || this.inventoryUpdateSubject.hasThrowable()) {
            return;
        }
        Logger.d("Firmware inventory update set added to firmware repository: " + set);
        this.inventoryUpdateSubject.onNext(set);
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareProvider
    public void provideInventoryUpdateError(Throwable th) {
        Preconditions.mainThread();
        Preconditions.precondition(!this.inventoryUpdateSubject.hasValue(), "Cannot call inventory update error after providing inventory update");
        if (this.inventoryUpdateSubject.hasThrowable() || this.inventoryUpdateSubject.hasComplete()) {
            return;
        }
        Logger.d("Error providing available inventory updates for the accessory.");
        this.inventoryUpdateSubject.onError(th);
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareProvider
    public void provideUpdateStatus(FirmwareUpdateStatus firmwareUpdateStatus) {
        Logger.d("Firmware update status has changed: " + firmwareUpdateStatus);
        this.updateStatusSubject.onNext(firmwareUpdateStatus);
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareRepository
    @Deprecated
    public Single<Firmware.FirmwareInformation> queryInformation() {
        return this.informationSubject.firstOrError().map($$Lambda$MemoryFirmwareRepository$iTiWw052gtGc5V4YjE6q8ezmY2o.INSTANCE);
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2
    public Single<Set<Firmware.FirmwareInformation>> queryInformationSet() {
        return this.informationSubject.firstOrError();
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2
    public Single<Set<InventoryUpdateBundle>> queryInventoryUpdateSet(boolean z) {
        Logger.d("MemoryFirmwareRepository: queryInventoryUpdateSet forceUpdateRequired: %b", Boolean.valueOf(z));
        if (z) {
            return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.firmware.-$$Lambda$MemoryFirmwareRepository$mtIN81H_KE0dS3m6AbvA0mmZYnA
                @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
                public final void call(Producer.Result result) {
                    MemoryFirmwareRepository.this.lambda$queryInventoryUpdateSet$1$MemoryFirmwareRepository(result);
                }
            });
        }
        return this.inventoryUpdateSubject.firstOrError();
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareRepository, com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2
    public Flowable<FirmwareUpdateStatus> queryUpdateStatus() {
        return this.updateStatusSubject.distinctUntilChanged();
    }

    public void release() {
        Preconditions.mainThread();
        ObservableUtils.release(this.updateStatusSubject);
        if (this.informationSubject.hasThrowable() || this.informationSubject.hasComplete() || this.inventoryUpdateSubject.hasThrowable() || this.inventoryUpdateSubject.hasComplete()) {
            return;
        }
        ObservableUtils.release(this.informationSubject);
        ObservableUtils.release(this.inventoryUpdateSubject);
    }
}
