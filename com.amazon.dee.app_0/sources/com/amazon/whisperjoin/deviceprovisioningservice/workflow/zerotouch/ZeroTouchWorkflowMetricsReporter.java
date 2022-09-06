package com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch;

import com.amazon.whisperjoin.deviceprovisioningservice.metrics.FFSProvisioningServiceMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.ZeroTouchWorkflowUpdate;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes13.dex */
public class ZeroTouchWorkflowMetricsReporter implements Consumer<ZeroTouchWorkflowUpdate> {
    private final FFSProvisioningServiceMetricsRecorder mFFSProvisioningServiceMetricsRecorder;

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowMetricsReporter$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$ZeroTouchWorkflowUpdate$State = new int[ZeroTouchWorkflowUpdate.State.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$ZeroTouchWorkflowUpdate$State[ZeroTouchWorkflowUpdate.State.PREPARING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$ZeroTouchWorkflowUpdate$State[ZeroTouchWorkflowUpdate.State.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$ZeroTouchWorkflowUpdate$State[ZeroTouchWorkflowUpdate.State.FAILURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public ZeroTouchWorkflowMetricsReporter(FFSProvisioningServiceMetricsRecorder fFSProvisioningServiceMetricsRecorder) {
        this.mFFSProvisioningServiceMetricsRecorder = fFSProvisioningServiceMetricsRecorder;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public void accept(ZeroTouchWorkflowUpdate zeroTouchWorkflowUpdate) throws Exception {
        int ordinal = zeroTouchWorkflowUpdate.getState().ordinal();
        if (ordinal == 0) {
            this.mFFSProvisioningServiceMetricsRecorder.onWorkflowSetupAttemptStart();
        } else if (ordinal == 3) {
            this.mFFSProvisioningServiceMetricsRecorder.onWorkflowSetupSuccess();
        } else if (ordinal != 4) {
        } else {
            this.mFFSProvisioningServiceMetricsRecorder.onWorkflowSetupFailure();
        }
    }
}
