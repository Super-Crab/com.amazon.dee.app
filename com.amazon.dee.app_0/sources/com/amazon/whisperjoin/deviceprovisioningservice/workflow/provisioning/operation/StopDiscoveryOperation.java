package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.ProvisioningManagerProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.Action;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
/* loaded from: classes13.dex */
public class StopDiscoveryOperation extends DeviceOperation<Action.StopDiscovery> {
    private static final String TAG = "StopDiscoveryOperation";
    private final ProvisioningManagerProvider mProvisioningManager;

    public StopDiscoveryOperation(ProvisioningManagerProvider provisioningManagerProvider) {
        this.mProvisioningManager = provisioningManagerProvider;
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<WJResult> apply(@NonNull Observable<Action.StopDiscovery> observable) {
        return observable.flatMap(new Function<Action.StopDiscovery, ObservableSource<WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.StopDiscoveryOperation.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<WJResult> mo10358apply(@NonNull Action.StopDiscovery stopDiscovery) throws Exception {
                WJLog.d(StopDiscoveryOperation.TAG, "Stopping Discovery");
                StopDiscoveryOperation.this.mProvisioningManager.stopDiscovery();
                return Observable.just(WJResult.Discovery.idle());
            }
        }).onErrorReturn(new Function<Throwable, WJResult>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.StopDiscoveryOperation.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public WJResult mo10358apply(@NonNull Throwable th) throws Exception {
                return WJResult.Discovery.error(th);
            }
        });
    }
}
