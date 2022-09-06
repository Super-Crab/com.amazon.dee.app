package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import com.amazon.whisperjoin.common.sharedtypes.error.WJError;
import com.amazon.whisperjoin.common.sharedtypes.error.WJErrorFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.error.DeviceRecentlyProvisionedException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.WorkflowFailureException;
/* loaded from: classes13.dex */
public class WorkflowFailureWJErrorMapper implements WJErrorMapper<WorkflowFailureException> {
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper
    public WJError map(WorkflowFailureException workflowFailureException) {
        if (workflowFailureException instanceof DeviceRecentlyProvisionedException) {
            return WJErrorFactory.Workflow.deviceRecentlyProvisioned();
        }
        return WJErrorFactory.Workflow.unknown();
    }
}
