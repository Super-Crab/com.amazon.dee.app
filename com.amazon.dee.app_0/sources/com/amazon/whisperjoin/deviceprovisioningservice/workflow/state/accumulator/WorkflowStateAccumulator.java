package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
/* loaded from: classes13.dex */
public class WorkflowStateAccumulator implements ObservableTransformer<WJResult, WJWorkflowStateStore> {
    private static final String TAG = "WorkflowStateAccumulator";
    private final DeviceEventWorkflowStateAccumulator mDeviceEventWorkflowStateAccumulator;
    private final WJWorkflowStateStore mInitialWorkflowState;

    public WorkflowStateAccumulator() {
        this(new WJWorkflowStateStore.Mutator(new WJWorkflowStateStore()).setLastResult(WJResult.WorkflowIdle.success()).create(), new DeviceEventWorkflowStateAccumulator());
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<WJWorkflowStateStore> apply(@NonNull Observable<WJResult> observable) {
        return observable.scan(this.mInitialWorkflowState, new BiFunction<WJWorkflowStateStore, WJResult, WJWorkflowStateStore>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.WorkflowStateAccumulator.2
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public WJWorkflowStateStore apply(WJWorkflowStateStore wJWorkflowStateStore, WJResult wJResult) throws Exception {
                WJWorkflowStateStore mo5716accumulate = WorkflowStateAccumulator.this.mDeviceEventWorkflowStateAccumulator.mo5716accumulate(wJResult, wJWorkflowStateStore);
                if (mo5716accumulate == null) {
                    WJLog.d(WorkflowStateAccumulator.TAG, "Received null state, passing on unchanged workflow update to be ignored.");
                    return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(null).create();
                }
                return mo5716accumulate;
            }
        }).flatMap(new Function<WJWorkflowStateStore, ObservableSource<WJWorkflowStateStore>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.WorkflowStateAccumulator.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<WJWorkflowStateStore> mo10358apply(@NonNull WJWorkflowStateStore wJWorkflowStateStore) throws Exception {
                if (wJWorkflowStateStore.getLastWJResult() == null) {
                    WJLog.d(WorkflowStateAccumulator.TAG, "No Last WJResult, dropping workflow state update");
                    return Observable.never();
                }
                return Observable.just(wJWorkflowStateStore);
            }
        });
    }

    WorkflowStateAccumulator(WJWorkflowStateStore wJWorkflowStateStore, DeviceEventWorkflowStateAccumulator deviceEventWorkflowStateAccumulator) {
        this.mInitialWorkflowState = wJWorkflowStateStore;
        this.mDeviceEventWorkflowStateAccumulator = deviceEventWorkflowStateAccumulator;
    }
}
