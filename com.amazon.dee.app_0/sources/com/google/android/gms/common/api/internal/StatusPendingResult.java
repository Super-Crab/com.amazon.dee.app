package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
@KeepForSdk
/* loaded from: classes2.dex */
public class StatusPendingResult extends BasePendingResult<Status> {
    public StatusPendingResult(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    /* renamed from: createFailedResult */
    public /* synthetic */ Status mo7471createFailedResult(Status status) {
        return status;
    }

    @Deprecated
    public StatusPendingResult(Looper looper) {
        super(looper);
    }
}
