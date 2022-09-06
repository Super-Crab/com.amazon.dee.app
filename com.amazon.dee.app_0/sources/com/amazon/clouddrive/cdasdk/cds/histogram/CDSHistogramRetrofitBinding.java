package com.amazon.clouddrive.cdasdk.cds.histogram;

import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
/* loaded from: classes11.dex */
public interface CDSHistogramRetrofitBinding {
    @GET("histogram")
    Single<GetHistogramResponse> getHistogram(@QueryMap Map<String, String> map);
}
