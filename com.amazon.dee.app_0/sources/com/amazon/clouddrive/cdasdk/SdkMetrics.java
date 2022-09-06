package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface SdkMetrics {

    /* loaded from: classes11.dex */
    public enum Service {
        CDS,
        Prompto,
        APS,
        OneLens,
        CDTS,
        CDP,
        CDUS,
        CDDS,
        CDRS,
        DPS
    }

    void recordCallError(@NonNull Service service, @NonNull String str, @NonNull Exception exc);

    void recordCallSuccess(@NonNull Service service, @NonNull String str, long j);
}
