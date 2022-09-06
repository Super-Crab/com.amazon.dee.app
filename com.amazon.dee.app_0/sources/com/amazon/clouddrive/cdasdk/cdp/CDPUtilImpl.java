package com.amazon.clouddrive.cdasdk.cdp;

import android.content.ContentResolver;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.clouddrive.cdasdk.ProgressReportingRequestBody;
import com.amazon.clouddrive.cdasdk.ProgressUpdate;
import io.reactivex.rxjava3.subjects.Subject;
import java.io.ByteArrayInputStream;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.RequestBody;
/* loaded from: classes11.dex */
public class CDPUtilImpl implements CDPUtil {
    private final ContentResolver contentResolver;

    public CDPUtilImpl(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdp.CDPUtil
    @NonNull
    public RequestBody requestBodyFromByteArray(@NonNull MediaType mediaType, @NonNull byte[] bArr) {
        return RequestBody.create(mediaType, bArr);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdp.CDPUtil
    @NonNull
    public RequestBody requestBodyFromContentUri(@NonNull MediaType mediaType, @NonNull Uri uri, long j, @Nullable Subject<ProgressUpdate> subject) {
        return ProgressReportingRequestBody.createUriSourceRequestBody(this.contentResolver, mediaType, uri, j, subject);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdp.CDPUtil
    @NonNull
    public RequestBody requestBodyFromFile(@NonNull MediaType mediaType, @NonNull File file) {
        return RequestBody.create(mediaType, file);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdp.CDPUtil
    @NonNull
    public RequestBody requestBodyFromByteArray(@NonNull MediaType mediaType, @NonNull byte[] bArr, @NonNull Subject<ProgressUpdate> subject) {
        return ProgressReportingRequestBody.createProgressRequestBody(mediaType, new ByteArrayInputStream(bArr), subject);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdp.CDPUtil
    @NonNull
    public RequestBody requestBodyFromFile(@NonNull MediaType mediaType, @NonNull File file, @NonNull Subject<ProgressUpdate> subject) {
        return ProgressReportingRequestBody.createProgressRequestBody(mediaType, file, subject);
    }
}
