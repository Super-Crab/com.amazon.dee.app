package com.amazon.clouddrive.cdasdk.cdus;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
/* loaded from: classes11.dex */
public interface CDUSRetrofitBinding {
    @NonNull
    public static final String MD5_HEADER = "x-amzn-file-md5";
    @NonNull
    public static final String MD5_PART_HEADER = "x-amzn-part-md5";

    @DELETE("v2/upload/multipart-upload/{nodeId}")
    Completable abortMultipartUpload(@Path("nodeId") String str, @QueryMap Map<String, String> map);

    @POST("v2/upload/multipart-upload/{nodeId}/complete")
    Single<CompleteMultipartResponse> completeMultipartUpload(@Path("nodeId") String str, @QueryMap Map<String, String> map);

    @POST("v2/upload/multipart-upload")
    Single<InitiateMultipartResponse> initiateMultipartUpload(@Header("x-amzn-file-md5") String str, @QueryMap Map<String, String> map);

    @PUT("v2/upload/multipart-upload/{nodeId}")
    Single<InitiateMultipartResponse> initiateMultipartUploadForExisting(@Path("nodeId") String str, @Header("x-amzn-file-md5") String str2, @QueryMap Map<String, String> map);

    @POST("v2/upload")
    Single<NodeInfo> postNode(@Header("x-amzn-file-md5") String str, @QueryMap Map<String, String> map, @Query("accessRuleIds") List<String> list, @Body RequestBody requestBody);

    @PUT("v2/upload/{id}")
    Single<NodeInfo> putNode(@Path("id") String str, @Header("x-amzn-file-md5") String str2, @QueryMap Map<String, String> map, @Query("accessRuleIds") List<String> list, @Body RequestBody requestBody);

    @GET("v2/upload/multipart-upload/{nodeId}")
    Single<RetrieveMultipartResponse> retrieveMultipartUpload(@Path("nodeId") String str, @QueryMap Map<String, String> map);

    @PUT("v2/upload/multipart-upload/{nodeId}/parts/{partNumber}")
    Single<UploadPartResponse> uploadPart(@Header("x-amzn-part-md5") String str, @Path("nodeId") String str2, @Path("partNumber") Long l, @QueryMap Map<String, String> map, @Body RequestBody requestBody);
}
