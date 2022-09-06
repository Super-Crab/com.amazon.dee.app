package com.amazon.whisperjoin.deviceprovisioningservice.workflow;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.Action;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionsExecutor;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceEventStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.WorkflowEventReporter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.WorkflowResultLogger;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.WorkflowStateAccumulator;
import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes13.dex */
public class WorkflowStateStream {
    private static final String TAG = "WorkflowStateStream";
    private final DeviceActionsExecutor mActionExecutor;
    private final Observable<Action> mActionsStream;
    private final DeviceEventStream mDeviceEventStream;
    private final WorkflowEventReporter mWorkflowEventReporter;
    private final WorkflowResultLogger mWorkflowResultLogger;
    private Observable<WJWorkflowStateStore> mWorkflowStateStream = buildWorkflowStream();

    public WorkflowStateStream(Observable<Action> observable, DeviceActionsExecutor deviceActionsExecutor, DeviceEventStream deviceEventStream, WorkflowEventReporter workflowEventReporter, WorkflowResultLogger workflowResultLogger) {
        this.mActionsStream = observable;
        this.mActionExecutor = deviceActionsExecutor;
        this.mDeviceEventStream = deviceEventStream;
        this.mWorkflowEventReporter = workflowEventReporter;
        this.mWorkflowResultLogger = workflowResultLogger;
    }

    private Observable<WJWorkflowStateStore> buildWorkflowStream() {
        return Observable.merge(this.mActionsStream.compose(this.mActionExecutor), this.mDeviceEventStream.getStream().ofType(WJResult.Disconnection.class)).compose(new WorkflowStateAccumulator()).doOnNext(this.mWorkflowResultLogger).doOnNext(this.mWorkflowEventReporter).doFinally(new io.reactivex.rxjava3.functions.Action() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowStateStream.1
            @Override // io.reactivex.rxjava3.functions.Action
            public void run() throws Exception {
                WJLog.d(WorkflowStateStream.TAG, "Cleaning up workflow stream");
            }
        });
    }

    public Observable<WJWorkflowStateStore> getStream() {
        return this.mWorkflowStateStream;
    }
}
