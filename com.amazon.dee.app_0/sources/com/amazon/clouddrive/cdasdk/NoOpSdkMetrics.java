package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.SdkMetrics;
/* loaded from: classes11.dex */
class NoOpSdkMetrics implements SdkMetrics {
    @Override // com.amazon.clouddrive.cdasdk.SdkMetrics
    public void recordCallError(@NonNull SdkMetrics.Service service, @NonNull String str, @NonNull Exception exc) {
    }

    @Override // com.amazon.clouddrive.cdasdk.SdkMetrics
    public void recordCallSuccess(@NonNull SdkMetrics.Service service, @NonNull String str, long j) {
    }
}
