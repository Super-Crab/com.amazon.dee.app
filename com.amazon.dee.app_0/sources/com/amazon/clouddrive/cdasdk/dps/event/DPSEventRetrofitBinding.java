package com.amazon.clouddrive.cdasdk.dps.event;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
/* loaded from: classes11.dex */
public interface DPSEventRetrofitBinding {
    @POST("customerEvent")
    Single<RecordEventResponse> recordEvent(@Body RecordEventRequest recordEventRequest);
}
