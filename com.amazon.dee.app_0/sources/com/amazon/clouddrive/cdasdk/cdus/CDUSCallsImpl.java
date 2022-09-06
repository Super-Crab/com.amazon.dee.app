package com.amazon.clouddrive.cdasdk.cdus;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.clouddrive.cdasdk.CDClientImpl;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import com.amazon.clouddrive.cdasdk.ProgressUpdate;
import com.amazon.clouddrive.cdasdk.cdp.CDPUtil;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.Subject;
import java.io.File;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class CDUSCallsImpl implements CDUSCalls {
    @NonNull
    private final CDUSCallUtil callUtil;
    @NonNull
    private final CDPUtil cdpUtil = CDClientImpl.getAppComponent().getCDPUtil();
    @NonNull
    private final ClientConfig clientConfig;
    @NonNull
    private final CDUSRetrofitBinding retrofitBinding;

    public CDUSCallsImpl(@NonNull ClientConfig clientConfig, @NonNull Retrofit retrofit) {
        this.clientConfig = clientConfig;
        this.callUtil = new CDUSCallUtil(clientConfig);
        this.retrofitBinding = (CDUSRetrofitBinding) retrofit.create(CDUSRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.CDUSCalls
    @NonNull
    public Single<CompleteMultipartResponse> completeMultipartUpload(@NonNull CompleteMultipartRequest completeMultipartRequest, @NonNull final String str) {
        return this.callUtil.createCallWithQueryParameters("completeMultipartUpload", completeMultipartRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cdus.-$$Lambda$CDUSCallsImpl$wVZJfe5i7kmFuW6s2PY6tDR0nTI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDUSCallsImpl.this.lambda$completeMultipartUpload$4$CDUSCallsImpl(str, (Map) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.CDUSCalls
    @NonNull
    public String getBaseUrl() {
        return this.clientConfig.getEndpointConfiguration().getContentUrl();
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.CDUSCalls
    @NonNull
    public Single<InitiateMultipartResponse> initiateMultipartUpload(@NonNull InitiateMultipartRequest initiateMultipartRequest, @NonNull final String str) {
        return this.callUtil.createCallWithQueryParameters("initiateMultipartUpload", initiateMultipartRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cdus.-$$Lambda$CDUSCallsImpl$7jUDTaA6PFyRmNc0hg9r34PC7oQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDUSCallsImpl.this.lambda$initiateMultipartUpload$2$CDUSCallsImpl(str, (Map) obj);
            }
        });
    }

    public /* synthetic */ Single lambda$completeMultipartUpload$4$CDUSCallsImpl(String str, Map map) throws Throwable {
        return this.retrofitBinding.completeMultipartUpload(str, map);
    }

    public /* synthetic */ Single lambda$initiateMultipartUpload$2$CDUSCallsImpl(String str, Map map) throws Throwable {
        return this.retrofitBinding.initiateMultipartUpload(str, map);
    }

    public /* synthetic */ Single lambda$postNode$0$CDUSCallsImpl(String str, UploadContentRequest uploadContentRequest, RequestBody requestBody, Map map) throws Throwable {
        return this.retrofitBinding.postNode(str, map, uploadContentRequest.getAccessRuleIds(), requestBody);
    }

    public /* synthetic */ Single lambda$putNode$1$CDUSCallsImpl(String str, String str2, UpdateContentRequest updateContentRequest, RequestBody requestBody, Map map) throws Throwable {
        return this.retrofitBinding.putNode(str, str2, map, updateContentRequest.getAccessRuleIds(), requestBody);
    }

    public /* synthetic */ Single lambda$retrieveMultipartUpload$3$CDUSCallsImpl(String str, Map map) throws Throwable {
        return this.retrofitBinding.retrieveMultipartUpload(str, map);
    }

    public /* synthetic */ Single lambda$uploadPart$5$CDUSCallsImpl(String str, String str2, Long l, RequestBody requestBody, Map map) throws Throwable {
        return this.retrofitBinding.uploadPart(str, str2, l, map, requestBody);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.CDUSCalls
    @NonNull
    public Single<NodeInfo> postNode(@NonNull UploadContentRequest uploadContentRequest, @NonNull MediaType mediaType, @NonNull File file, @NonNull String str) {
        return postNode(uploadContentRequest, this.cdpUtil.requestBodyFromFile(mediaType, file), str);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.CDUSCalls
    @NonNull
    public Single<NodeInfo> putNode(@NonNull String str, @NonNull UpdateContentRequest updateContentRequest, @NonNull MediaType mediaType, @NonNull File file, @NonNull String str2) {
        return putNode(str, updateContentRequest, this.cdpUtil.requestBodyFromFile(mediaType, file), str2);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.CDUSCalls
    @NonNull
    public Single<RetrieveMultipartResponse> retrieveMultipartUpload(@NonNull RetrieveMultipartRequest retrieveMultipartRequest, @NonNull final String str) {
        return this.callUtil.createCallWithQueryParameters("retrieveMultipartUpload", retrieveMultipartRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cdus.-$$Lambda$CDUSCallsImpl$bbvRZ-az__gXiKO3zPVHnwI8tqs
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDUSCallsImpl.this.lambda$retrieveMultipartUpload$3$CDUSCallsImpl(str, (Map) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.CDUSCalls
    @NonNull
    public Single<UploadPartResponse> uploadPart(@NonNull UploadPartRequest uploadPartRequest, @NonNull String str, @NonNull Long l, @NonNull String str2, @NonNull MediaType mediaType, @NonNull byte[] bArr) {
        return uploadPart(uploadPartRequest, str, l, str2, this.cdpUtil.requestBodyFromByteArray(mediaType, bArr));
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.CDUSCalls
    @NonNull
    public Single<NodeInfo> postNode(@NonNull UploadContentRequest uploadContentRequest, @NonNull MediaType mediaType, @NonNull File file, @NonNull Subject<ProgressUpdate> subject, @NonNull String str) {
        return postNode(uploadContentRequest, this.cdpUtil.requestBodyFromFile(mediaType, file, subject), str);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.CDUSCalls
    @NonNull
    public Single<NodeInfo> putNode(@NonNull String str, @NonNull UpdateContentRequest updateContentRequest, @NonNull MediaType mediaType, @NonNull File file, @NonNull Subject<ProgressUpdate> subject, @NonNull String str2) {
        return putNode(str, updateContentRequest, this.cdpUtil.requestBodyFromFile(mediaType, file, subject), str2);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.CDUSCalls
    @NonNull
    public Single<UploadPartResponse> uploadPart(@NonNull UploadPartRequest uploadPartRequest, @NonNull String str, @NonNull Long l, @NonNull String str2, @NonNull MediaType mediaType, @NonNull byte[] bArr, @NonNull Subject<ProgressUpdate> subject) {
        return uploadPart(uploadPartRequest, str, l, str2, this.cdpUtil.requestBodyFromByteArray(mediaType, bArr, subject));
    }

    @NonNull
    private Single<NodeInfo> postNode(@NonNull final UploadContentRequest uploadContentRequest, @NonNull final RequestBody requestBody, @NonNull final String str) {
        return this.callUtil.createCallWithQueryParameters("postNode", uploadContentRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cdus.-$$Lambda$CDUSCallsImpl$zETb8xkx9haWFFoFVd9Q3nBdsbk
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDUSCallsImpl.this.lambda$postNode$0$CDUSCallsImpl(str, uploadContentRequest, requestBody, (Map) obj);
            }
        });
    }

    @NonNull
    private Single<UploadPartResponse> uploadPart(@NonNull UploadPartRequest uploadPartRequest, @NonNull final String str, @NonNull final Long l, @NonNull final String str2, @NonNull final RequestBody requestBody) {
        return this.callUtil.createCallWithQueryParameters("uploadPart", uploadPartRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cdus.-$$Lambda$CDUSCallsImpl$pP8mP7Lsz97DO-UttPlgogib2rc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDUSCallsImpl.this.lambda$uploadPart$5$CDUSCallsImpl(str2, str, l, requestBody, (Map) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.CDUSCalls
    @NonNull
    public Single<NodeInfo> postNode(@NonNull UploadContentRequest uploadContentRequest, @NonNull MediaType mediaType, @NonNull Uri uri, long j, @Nullable Subject<ProgressUpdate> subject, @NonNull String str) {
        return postNode(uploadContentRequest, this.cdpUtil.requestBodyFromContentUri(mediaType, uri, j, subject), str);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.CDUSCalls
    @NonNull
    public Single<NodeInfo> putNode(@NonNull String str, @NonNull UpdateContentRequest updateContentRequest, @NonNull MediaType mediaType, @NonNull Uri uri, long j, @Nullable Subject<ProgressUpdate> subject, @NonNull String str2) {
        return putNode(str, updateContentRequest, this.cdpUtil.requestBodyFromContentUri(mediaType, uri, j, subject), str2);
    }

    @NonNull
    private Single<NodeInfo> putNode(@NonNull final String str, @NonNull final UpdateContentRequest updateContentRequest, @NonNull final RequestBody requestBody, @NonNull final String str2) {
        return this.callUtil.createCallWithQueryParameters("putNode", updateContentRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cdus.-$$Lambda$CDUSCallsImpl$ql8pcbQzP50VpBksj1zu-bWBcRM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDUSCallsImpl.this.lambda$putNode$1$CDUSCallsImpl(str, str2, updateContentRequest, requestBody, (Map) obj);
            }
        });
    }
}
