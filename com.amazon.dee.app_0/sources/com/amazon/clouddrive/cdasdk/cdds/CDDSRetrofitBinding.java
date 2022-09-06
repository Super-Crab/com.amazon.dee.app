package com.amazon.clouddrive.cdasdk.cdds;

import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
/* loaded from: classes11.dex */
public interface CDDSRetrofitBinding {
    @Streaming
    @GET("nodes/{id}/content")
    Single<ResponseBody> downloadNode(@Path("id") String str, @QueryMap Map<String, String> map);
}
