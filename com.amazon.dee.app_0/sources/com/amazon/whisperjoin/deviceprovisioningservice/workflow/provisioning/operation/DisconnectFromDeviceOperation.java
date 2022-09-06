package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation;

import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.Action;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
/* loaded from: classes13.dex */
public class DisconnectFromDeviceOperation extends DeviceOperation<Action.Disconnect> {
    private static final String TAG = "DisconnectFromDeviceOperation";

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<WJResult> apply(@NonNull Observable<Action.Disconnect> observable) {
        return observable.flatMap(new Function<Action.Disconnect, ObservableSource<WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.DisconnectFromDeviceOperation.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<WJResult> mo10358apply(@NonNull Action.Disconnect disconnect) throws Exception {
                WJProvisionee wJProvisionee = disconnect.getWJProvisionee();
                wJProvisionee.disconnect();
                return Observable.just(WJResult.Disconnection.success(wJProvisionee));
            }
        });
    }
}
