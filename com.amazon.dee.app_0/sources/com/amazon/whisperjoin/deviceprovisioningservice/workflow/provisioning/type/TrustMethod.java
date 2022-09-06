package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;
/* loaded from: classes13.dex */
public enum TrustMethod {
    AUTHENTICATED("AUTHENTICATED"),
    UNAUTHENTICATED("UNAUTHENTICATED");
    
    private final String mName;

    TrustMethod(String str) {
        this.mName = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mName;
    }
}
