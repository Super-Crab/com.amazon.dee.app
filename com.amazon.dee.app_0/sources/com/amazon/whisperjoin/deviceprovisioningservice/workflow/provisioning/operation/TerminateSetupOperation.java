package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.ProvisioningManagerProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.Action;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.DeviceSession;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes13.dex */
public class TerminateSetupOperation extends DeviceOperation<Action.TerminateSetup> {
    private static final String TAG = "TerminateSetupOperation";
    private final ProvisioningManagerProvider mProvisioningManagerProvider;

    public TerminateSetupOperation(ProvisioningManagerProvider provisioningManagerProvider) {
        this.mProvisioningManagerProvider = provisioningManagerProvider;
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<WJResult> apply(@NonNull Observable<Action.TerminateSetup> observable) {
        return observable.flatMap(new Function<Action.TerminateSetup, ObservableSource<WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.TerminateSetupOperation.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<WJResult> mo10358apply(@NonNull Action.TerminateSetup terminateSetup) throws Exception {
                WJWorkflowStateStore data = terminateSetup.getData();
                WJLog.d(TerminateSetupOperation.TAG, "Terminating Setup");
                Iterator<Map.Entry<WJProvisionee, DeviceSession>> activeSessions = data.getActiveSessions();
                while (activeSessions.hasNext()) {
                    WJProvisionee key = activeSessions.next().getKey();
                    String str = TerminateSetupOperation.TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Closing Connection to ");
                    outline107.append(key.getPeripheralDeviceDetails().toString());
                    WJLog.d(str, outline107.toString());
                    key.disconnect();
                }
                if (data.isDiscoveryActive()) {
                    WJLog.d(TerminateSetupOperation.TAG, "Stopping Discovery");
                    TerminateSetupOperation.this.mProvisioningManagerProvider.stopDiscovery();
                }
                return Observable.just(WJResult.WorkflowIdle.success());
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.TerminateSetupOperation.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<? extends WJResult> mo10358apply(@NonNull Throwable th) throws Exception {
                return Observable.just(WJResult.WorkflowIdle.error(th));
            }
        });
    }
}
