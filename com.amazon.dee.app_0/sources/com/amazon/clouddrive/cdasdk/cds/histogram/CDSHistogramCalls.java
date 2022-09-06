package com.amazon.clouddrive.cdasdk.cds.histogram;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface CDSHistogramCalls {
    @NonNull
    Single<GetHistogramResponse> getHistogram(@NonNull GetHistogramRequest getHistogramRequest);
}
