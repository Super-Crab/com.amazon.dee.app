package com.amazon.clouddrive.handlers;

import com.amazon.clouddrive.model.CloudDriveRequest;
/* loaded from: classes11.dex */
public interface AsyncHandler<REQUEST extends CloudDriveRequest, RESULT> {
    void onCanceled(REQUEST request);

    void onError(REQUEST request, Exception exc);

    void onSuccess(REQUEST request, RESULT result);
}
