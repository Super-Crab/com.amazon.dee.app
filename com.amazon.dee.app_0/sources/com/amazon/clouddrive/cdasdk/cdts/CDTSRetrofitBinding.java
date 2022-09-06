package com.amazon.clouddrive.cdasdk.cdts;

import io.reactivex.rxjava3.core.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
/* loaded from: classes11.dex */
public interface CDTSRetrofitBinding {
    @Streaming
    @GET("v1/thumbnail/{nodeId}")
    Single<ResponseBody> getImageThumbnail(@Path("nodeId") String str, @Query("ownerId") String str2, @Query("groupShareToken") String str3, @Query("viewBox") String str4, @Query("fit") String str5, @Query("cropOffset") String str6, @Query("cropSize") String str7);
}
