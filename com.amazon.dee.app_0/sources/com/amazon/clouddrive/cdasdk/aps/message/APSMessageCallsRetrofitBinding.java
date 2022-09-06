package com.amazon.clouddrive.cdasdk.aps.message;

import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
/* loaded from: classes11.dex */
public interface APSMessageCallsRetrofitBinding {
    @POST("{resourceVersion}/experiences/notification")
    Single<SendNotificationOutput> sendNotification(@Path("resourceVersion") String str, @QueryMap Map<String, String> map);
}
