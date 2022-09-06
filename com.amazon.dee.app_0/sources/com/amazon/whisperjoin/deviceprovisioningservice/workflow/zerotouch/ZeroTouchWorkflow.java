package com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch;

import com.amazon.whisperjoin.common.sharedtypes.devices.AbstractPeripheralDeviceDetails;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.ZeroTouchWorkflowUpdate;
import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes13.dex */
public interface ZeroTouchWorkflow<T extends AbstractPeripheralDeviceDetails> {
    Observable<ZeroTouchWorkflowUpdate> run(T t);
}
