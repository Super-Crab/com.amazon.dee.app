package com.amazon.clouddrive.cdasdk.cds.source;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface CDSSourceCalls {
    @NonNull
    Single<ListSourcesResponse> listSources(@NonNull ListSourcesRequest listSourcesRequest);

    @NonNull
    Single<SourceInfoResponse> setupSource(@NonNull SetupSourceRequest setupSourceRequest);
}
