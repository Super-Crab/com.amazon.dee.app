package com.amazon.clouddrive.cdasdk.cdus;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.clouddrive.cdasdk.ProgressUpdate;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.Subject;
import java.io.File;
import okhttp3.MediaType;
/* loaded from: classes11.dex */
public interface CDUSCalls {
    @NonNull
    Single<CompleteMultipartResponse> completeMultipartUpload(@NonNull CompleteMultipartRequest completeMultipartRequest, @NonNull String str);

    @NonNull
    String getBaseUrl();

    @NonNull
    Single<InitiateMultipartResponse> initiateMultipartUpload(@NonNull InitiateMultipartRequest initiateMultipartRequest, @NonNull String str);

    @NonNull
    Single<NodeInfo> postNode(@NonNull UploadContentRequest uploadContentRequest, @NonNull MediaType mediaType, @NonNull Uri uri, long j, @Nullable Subject<ProgressUpdate> subject, @NonNull String str);

    @NonNull
    Single<NodeInfo> postNode(@NonNull UploadContentRequest uploadContentRequest, @NonNull MediaType mediaType, @NonNull File file, @NonNull Subject<ProgressUpdate> subject, @NonNull String str);

    @NonNull
    Single<NodeInfo> postNode(@NonNull UploadContentRequest uploadContentRequest, @NonNull MediaType mediaType, @NonNull File file, @NonNull String str);

    @NonNull
    Single<NodeInfo> putNode(@NonNull String str, @NonNull UpdateContentRequest updateContentRequest, @NonNull MediaType mediaType, @NonNull Uri uri, long j, @Nullable Subject<ProgressUpdate> subject, @NonNull String str2);

    @NonNull
    Single<NodeInfo> putNode(@NonNull String str, @NonNull UpdateContentRequest updateContentRequest, @NonNull MediaType mediaType, @NonNull File file, @NonNull Subject<ProgressUpdate> subject, @NonNull String str2);

    @NonNull
    Single<NodeInfo> putNode(@NonNull String str, @NonNull UpdateContentRequest updateContentRequest, @NonNull MediaType mediaType, @NonNull File file, @NonNull String str2);

    @NonNull
    Single<RetrieveMultipartResponse> retrieveMultipartUpload(@NonNull RetrieveMultipartRequest retrieveMultipartRequest, @NonNull String str);

    @NonNull
    Single<UploadPartResponse> uploadPart(@NonNull UploadPartRequest uploadPartRequest, @NonNull String str, @NonNull Long l, @NonNull String str2, @NonNull MediaType mediaType, @NonNull byte[] bArr);

    @NonNull
    Single<UploadPartResponse> uploadPart(@NonNull UploadPartRequest uploadPartRequest, @NonNull String str, @NonNull Long l, @NonNull String str2, @NonNull MediaType mediaType, @NonNull byte[] bArr, @NonNull Subject<ProgressUpdate> subject);
}
