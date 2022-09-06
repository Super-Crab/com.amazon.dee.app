package com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch;

import java.util.UUID;
/* loaded from: classes13.dex */
public class WorkflowIdProvider {
    public String createWorkflowId() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
