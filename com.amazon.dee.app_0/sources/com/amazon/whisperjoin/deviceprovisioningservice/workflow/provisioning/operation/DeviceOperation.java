package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.Action;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import io.reactivex.rxjava3.core.ObservableTransformer;
/* loaded from: classes13.dex */
public abstract class DeviceOperation<TAction extends Action> implements ObservableTransformer<TAction, WJResult> {
}
