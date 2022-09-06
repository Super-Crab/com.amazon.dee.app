package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import com.amazon.whisperjoin.common.sharedtypes.error.WJError;
import java.lang.Throwable;
/* loaded from: classes13.dex */
public interface WJErrorMapper<T extends Throwable> {
    WJError map(T t);
}
