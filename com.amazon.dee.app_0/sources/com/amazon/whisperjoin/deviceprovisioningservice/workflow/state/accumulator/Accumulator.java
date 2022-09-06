package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
/* loaded from: classes13.dex */
public interface Accumulator<T extends Event, R> {
    /* renamed from: accumulate */
    R mo5716accumulate(T t, WJWorkflowStateStore wJWorkflowStateStore);
}
