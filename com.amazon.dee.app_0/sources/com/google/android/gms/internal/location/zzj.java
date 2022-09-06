package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition;
/* loaded from: classes2.dex */
abstract class zzj extends ActivityRecognition.zza<Status> {
    public zzj(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    /* renamed from: createFailedResult */
    public /* synthetic */ Result mo7471createFailedResult(Status status) {
        return status;
    }
}
