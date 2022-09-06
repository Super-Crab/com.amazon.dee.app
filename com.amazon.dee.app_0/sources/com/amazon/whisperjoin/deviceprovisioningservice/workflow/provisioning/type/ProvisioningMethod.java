package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;
/* loaded from: classes13.dex */
public enum ProvisioningMethod {
    FFS(com.amazon.devicesetupservice.reporting.ProvisioningMethod.FFS),
    MANUAL("MANUAL");
    
    private final String mName;

    ProvisioningMethod(String str) {
        this.mName = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mName;
    }
}
