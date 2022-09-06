package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import java.lang.Throwable;
/* loaded from: classes13.dex */
public interface ErrorDetailsProvider<T extends Throwable> {
    int getDetails(T t);
}
