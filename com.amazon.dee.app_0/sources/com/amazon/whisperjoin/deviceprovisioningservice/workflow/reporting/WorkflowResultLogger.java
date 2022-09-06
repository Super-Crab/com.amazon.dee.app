package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.util.ProvisioneeInfoMessage;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorUtils;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Locale;
/* loaded from: classes13.dex */
public class WorkflowResultLogger implements Consumer<WJWorkflowStateStore> {
    private static final String TAG = "WorkflowResultLogger";
    private final ProvisioningMethod mProvisioningMethod;
    private final WJErrorMapper<Throwable> mWJErrorMapper;

    public WorkflowResultLogger(ProvisioningMethod provisioningMethod, WJErrorMapper<Throwable> wJErrorMapper) {
        this.mProvisioningMethod = provisioningMethod;
        this.mWJErrorMapper = wJErrorMapper;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public void accept(WJWorkflowStateStore wJWorkflowStateStore) throws Exception {
        WJResult lastWJResult = wJWorkflowStateStore.getLastWJResult();
        String simpleName = lastWJResult.getClass().getSimpleName();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProvisioningMethod: [");
        outline107.append(this.mProvisioningMethod.toString());
        outline107.append("]");
        String sb = outline107.toString();
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("WJResult: [", simpleName, ", ");
        outline115.append(lastWJResult.getState().toString());
        outline115.append("]");
        String sb2 = outline115.toString();
        String create = ProvisioneeInfoMessage.create(lastWJResult.getWJProvisionee());
        WJLog.i(TAG, "New Workflow WJResult");
        WJLog.i(TAG, sb);
        WJLog.i(TAG, sb2);
        WJLog.i(TAG, create);
        if (lastWJResult.isState(Event.State.ERROR)) {
            WJLog.i(TAG, String.format(Locale.ENGLISH, "ErrorCode: [%s]", this.mWJErrorMapper.map(WJErrorUtils.getRootCause(lastWJResult.getError())).toString()));
        }
    }
}
