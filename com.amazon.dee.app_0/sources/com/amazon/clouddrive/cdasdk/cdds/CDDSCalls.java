package com.amazon.clouddrive.cdasdk.cdds;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
import java.io.InputStream;
/* loaded from: classes11.dex */
public interface CDDSCalls {
    @NonNull
    Single<InputStream> downloadNode(@NonNull DownloadNodeRequest downloadNodeRequest);
}
