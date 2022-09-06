package com.amazon.whisperjoin.deviceprovisioningservice.workflow;
/* loaded from: classes13.dex */
public interface ProvisioningEventListener {
    void onComplete();

    void onError(String str, String str2, String str3);

    void onNext(String str, String str2);
}
