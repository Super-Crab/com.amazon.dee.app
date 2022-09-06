package com.amazon.alexa.accessorykit.accessories.session.firmware;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.kota.InventoryUpdateBundle;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareUpdateStatus;
import com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRN;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRNEventId;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Set;
/* loaded from: classes6.dex */
public class FirmwareRepositoryModule {
    private static final String TAG = "FirmwareRepositoryModule:";
    private final SessionSupplier clientSessionSupplier;
    private final ArrayModelTransformer<Set<Firmware.FirmwareInformation>> firmwareModelTransformer;
    private final ArrayModelTransformer<Set<InventoryUpdateBundle>> inventoryUpdateModelTransformer;
    private final RxRN rxRN;
    private final MapModelTransformer<FirmwareUpdateStatus> updateStatusTransformer;

    public FirmwareRepositoryModule(ContainerProvider containerProvider, RxRN rxRN, SessionSupplier sessionSupplier) {
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(rxRN, "rxRN");
        Preconditions.notNull(sessionSupplier, "clientSessionSupplier");
        this.clientSessionSupplier = sessionSupplier;
        FirmwareModelTransformer firmwareModelTransformer = new FirmwareModelTransformer(containerProvider);
        this.firmwareModelTransformer = firmwareModelTransformer;
        this.inventoryUpdateModelTransformer = new InventoryUpdateBundleModelTransformer(containerProvider);
        this.updateStatusTransformer = firmwareModelTransformer;
        this.rxRN = rxRN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$queryUpdateStatus$0(FirmwareUpdateStatus firmwareUpdateStatus, FirmwareUpdateStatus firmwareUpdateStatus2) throws Throwable {
        if (((int) (firmwareUpdateStatus.getProgress() * 100.0f)) != ((int) (firmwareUpdateStatus2.getProgress() * 100.0f)) && firmwareUpdateStatus.isCompleted() == firmwareUpdateStatus2.isCompleted() && firmwareUpdateStatus.isCompletedWithError() == firmwareUpdateStatus2.isCompletedWithError() && firmwareUpdateStatus.isIdle() == firmwareUpdateStatus2.isIdle() && firmwareUpdateStatus.isInProgress() == firmwareUpdateStatus2.isInProgress()) {
            if ((firmwareUpdateStatus.getCause() == null) == (firmwareUpdateStatus2.getCause() == null) && firmwareUpdateStatus.getDeviceId() == firmwareUpdateStatus2.getDeviceId()) {
                return true;
            }
        }
        return false;
    }

    public void initiateFirmwareTransfer(String str, final Promise promise) {
        Logger.d("%s: initiateFirmwareTransfer, identifier: %s", TAG, str);
        Completable initiateFirmwareTransfer = this.clientSessionSupplier.getSession(str).getFirmwareRepository().initiateFirmwareTransfer();
        Action action = new Action() { // from class: com.amazon.alexa.accessorykit.accessories.session.firmware.-$$Lambda$FirmwareRepositoryModule$d-NGNjfzDX7UeexPKV8VQ4arDvc
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(true);
            }
        };
        promise.getClass();
        initiateFirmwareTransfer.subscribe(action, new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.firmware.-$$Lambda$uy64BVvzETLGSEBSP5-7B5ZCU4o
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.reject((Throwable) obj);
            }
        });
    }

    public void queryInformationSet(ReadableMap readableMap, String str) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.firmwareModelTransformer, this.clientSessionSupplier.getSession(str).getFirmwareRepository().queryInformationSet().toObservable());
    }

    public void queryInventoryUpdateBundleSet(ReadableMap readableMap, String str, boolean z) {
        Logger.d("%s: queryInventoryUpdateBundleSet, identifier: %s, shouldForceCheckInventoryUpdate: %b", TAG, str, Boolean.valueOf(z));
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.inventoryUpdateModelTransformer, this.clientSessionSupplier.getSession(str).getFirmwareRepository().queryInventoryUpdateSet(z).toObservable());
    }

    public void queryUpdateStatus(ReadableMap readableMap, String str) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.updateStatusTransformer, this.clientSessionSupplier.getSession(str).getFirmwareRepository().queryUpdateStatus().distinctUntilChanged($$Lambda$FirmwareRepositoryModule$FMtam4fmgPUyx2MdPio3PakgYS8.INSTANCE).toObservable());
    }
}
