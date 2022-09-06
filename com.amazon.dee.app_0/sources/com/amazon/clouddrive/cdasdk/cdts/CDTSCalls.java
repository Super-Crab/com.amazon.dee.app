package com.amazon.clouddrive.cdasdk.cdts;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
import java.io.InputStream;
/* loaded from: classes11.dex */
public interface CDTSCalls {
    @NonNull
    Single<InputStream> getImageThumbnail(@NonNull ThumbnailRequest thumbnailRequest);
}
