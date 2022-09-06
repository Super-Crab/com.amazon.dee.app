package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.amazon.devicesetupservice.v1.ProvisionerInfo;
/* loaded from: classes13.dex */
public abstract class AbstractDiscoveredProvisioneeRequest {
    protected ProvisionerInfo mProvisionerInfo;

    public AbstractDiscoveredProvisioneeRequest() {
    }

    public ProvisionerInfo getProvisionerInfo() {
        return this.mProvisionerInfo;
    }

    public AbstractDiscoveredProvisioneeRequest(ProvisionerInfo provisionerInfo) {
        this.mProvisionerInfo = provisionerInfo;
    }
}
