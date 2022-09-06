package com.amazon.alexa.photos;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.amazon.alexa.photos.events.PhotosUploaderEventType;
import com.amazon.alexa.photos.events.UploadsStartedEvent;
import com.amazon.alexa.photos.metrics.CloudDriveMetrics;
import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.alexa.photos.util.SystemUtility;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadRequestBuilder;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import dagger.Lazy;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
/* loaded from: classes9.dex */
public class UploadOperationV2 {
    @VisibleForTesting
    static final String ANDROID_MEDIA_DIR = "Android";
    @VisibleForTesting
    static final String EXTERNAL_DOC_AUTHORITY = "com.android.externalstorage.documents";
    @VisibleForTesting
    static final String PHOTO_UPLOAD_CATEGORY = "PHOTO";
    @VisibleForTesting
    static final String PRIMARY_STORAGE_TYPE = "primary";
    @VisibleForTesting
    static final String TAG = "UploadOperationV2";
    private final Lazy<CloudDriveMetrics> cloudDriveMetrics;
    private final List<Uri> contentUris;
    private final Context context;
    private final Runnable fallbackUpload;
    private final MessagePublisher messagePublisher;
    private final String parentNodeId;
    private final Lazy<SystemUtility> systemUtil;
    private final UploadBundleManager uploadBundleManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class Factory {
        private final Lazy<CloudDriveMetrics> cloudDriveMetrics;
        private final Context context;
        private final MessagePublisher messagePublisher;
        private final Lazy<SystemUtility> systemUtil;
        private final Lazy<UploadBundleManager> uploadBundleManager;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Factory(@NonNull Lazy<UploadBundleManager> lazy, @NonNull MessagePublisher messagePublisher, @NonNull Lazy<CloudDriveMetrics> lazy2, @NonNull Lazy<SystemUtility> lazy3, @NonNull Context context) {
            this.uploadBundleManager = lazy;
            this.messagePublisher = messagePublisher;
            this.cloudDriveMetrics = lazy2;
            this.systemUtil = lazy3;
            this.context = context;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @NonNull
        public UploadOperationV2 getUploadOperation(@NonNull List<Uri> list, @NonNull String str, @NonNull Runnable runnable) {
            return new UploadOperationV2(this.uploadBundleManager.mo358get(), this.messagePublisher, this.cloudDriveMetrics, this.systemUtil, this.context, list, str, runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<UploadRequest> buildUploadRequests(@NonNull Map<Uri, String> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<Uri, String> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                UploadRequestBuilder uploadRequestBuilder = new UploadRequestBuilder(entry.getValue(), entry.getKey());
                uploadRequestBuilder.parentId(this.parentNodeId).uploadCategory("PHOTO");
                arrayList.add(uploadRequestBuilder.build());
            } else {
                Log.w(TAG, "Path of Uri was null in upload request creation");
                recordOperationalEvent(PhotosMetricsConstants.URI_MISSING_UPLOAD_REQUEST_BUILDER);
            }
        }
        return arrayList;
    }

    @Nullable
    private String getFilePath(@NonNull Uri uri, boolean z) {
        String pathFromMediaStoreDocument;
        String str;
        String[] documentParts = getDocumentParts(getDocumentId(uri));
        if (documentParts == null) {
            return null;
        }
        String str2 = documentParts[0];
        if (isExternalStorageDocument(uri)) {
            if (PRIMARY_STORAGE_TYPE.equalsIgnoreCase(str2)) {
                pathFromMediaStoreDocument = getExternalStorageDirectory() + "/" + documentParts[1];
                str = PhotosMetricsConstants.UPLOAD_V2_PRIMARY_EXTERNAL_STORAGE_URI_BATCH;
            } else {
                pathFromMediaStoreDocument = getPathFromSDCardDocument(str2, documentParts[1]);
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SD Card filePath from getPathFromSDCardDocument: ");
                outline107.append(pathFromMediaStoreDocument == null ? "null" : pathFromMediaStoreDocument);
                outline107.toString();
                str = pathFromMediaStoreDocument == null ? PhotosMetricsConstants.UPLOAD_V2_SECONDARY_EXTERNAL_STORAGE_URI_NOEXIST : PhotosMetricsConstants.UPLOAD_V2_SECONDARY_EXTERNAL_STORAGE_URI_BATCH;
            }
        } else {
            pathFromMediaStoreDocument = getPathFromMediaStoreDocument(documentParts[1]);
            str = PhotosMetricsConstants.UPLOAD_V2_MEDIA_STORE_URI_BATCH;
        }
        recordEventMetricOnFirstPass(z, str);
        return pathFromMediaStoreDocument;
    }

    @Nullable
    private String getPathFromMediaStoreDocument(@NonNull String str) {
        try {
            Cursor query = this.context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data"}, "_id = ?", new String[]{str}, null);
            if (query != null && query.moveToFirst()) {
                String string = query.getString(query.getColumnIndexOrThrow("_data"));
                query.close();
                return string;
            }
            recordOperationalEvent(PhotosMetricsConstants.CURSOR_NOT_INITIALIZED);
            if (query == null) {
                return null;
            }
            query.close();
            return null;
        } catch (Exception e) {
            recordOperationalEvent(PhotosMetricsConstants.FAILED_TO_FETCH_PATH_FROM_URI);
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FailedToFetchPathFromUriException_");
            outline107.append(e.getClass().getSimpleName());
            recordOperationalEvent(outline107.toString());
            Log.e(TAG, "Error fetching path from uri.", e);
            return null;
        }
    }

    @Nullable
    private String getPathFromSDCardDocument(@NonNull String str, @NonNull String str2) {
        for (File file : this.context.getExternalMediaDirs()) {
            String absolutePath = file.getAbsolutePath();
            if (absolutePath.contains(str)) {
                int indexOf = absolutePath.indexOf("Android");
                return absolutePath.substring(0, indexOf) + str2;
            }
        }
        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private void recordEventMetricOnFirstPass(boolean z, @NonNull String str) {
        if (z) {
            recordOperationalEvent(str);
        }
    }

    private void recordOperationalEvent(String str) {
        this.cloudDriveMetrics.mo358get().recordEvent(TAG, str);
    }

    String getDocumentId(Uri uri) {
        return DocumentsContract.getDocumentId(uri);
    }

    @Nullable
    @VisibleForTesting
    String[] getDocumentParts(@NonNull String str) {
        String[] split = str.split(":");
        if (split.length < 2) {
            recordOperationalEvent(PhotosMetricsConstants.UPLOAD_V2_UNKNOWN_DOCUMENT_URI_FORMAT);
            return null;
        }
        return split;
    }

    File getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory();
    }

    @VisibleForTesting
    Callable<Map<Uri, String>> getRealPathsFromURI(final List<Uri> list) {
        return new Callable() { // from class: com.amazon.alexa.photos.-$$Lambda$UploadOperationV2$Dylkxa68yRVO5CXkwvZAXPep2ls
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return UploadOperationV2.this.lambda$getRealPathsFromURI$2$UploadOperationV2(list);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isUploadInProgress() {
        return this.uploadBundleManager.areUploadsInProgress();
    }

    public /* synthetic */ Map lambda$getRealPathsFromURI$2$UploadOperationV2(List list) throws Exception {
        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        boolean z = true;
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            Uri uri = (Uri) it2.next();
            try {
                this.context.getContentResolver().takePersistableUriPermission(uri, 3);
                String filePath = getFilePath(uri, z);
                if (!Strings.isNullOrEmpty(filePath)) {
                    hashMap.put(uri, filePath);
                    z = false;
                } else {
                    return Collections.emptyMap();
                }
            } catch (Exception e) {
                recordOperationalEvent(PhotosMetricsConstants.FAILED_TO_FETCH_PATH_FROM_URI);
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FailedToFetchPathFromUriException_");
                outline107.append(e.getClass().getSimpleName());
                recordOperationalEvent(outline107.toString());
                Log.e(TAG, "Error fetching path from uri.", e);
                return Collections.emptyMap();
            }
        }
        return hashMap;
    }

    public /* synthetic */ void lambda$start$0$UploadOperationV2(List list) throws Throwable {
        if (!list.isEmpty()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Scheduling ");
            outline107.append(list.size());
            outline107.append(" upload requests");
            outline107.toString();
            recordOperationalEvent(PhotosMetricsConstants.UPLOAD_BUNDLE_REQUESTS_SCHEDULED);
            scheduleManualUploadRequests(list, UploadBundleManager.MANUAL_QUEUE);
            return;
        }
        this.fallbackUpload.run();
    }

    public /* synthetic */ void lambda$start$1$UploadOperationV2(Throwable th) throws Throwable {
        Log.e(TAG, "Failed Scheduling Upload Requests");
        recordOperationalEvent(PhotosMetricsConstants.UPLOAD_SCHEDULING_ERROR);
        this.fallbackUpload.run();
    }

    @VisibleForTesting
    @WorkerThread
    synchronized void scheduleManualUploadRequests(@NonNull List<UploadRequest> list, @NonNull String str) {
        if (this.uploadBundleManager.getUploadBundle() == null) {
            Log.w(TAG, "Bundle is not setup before. Setting up upload bundle.");
            recordOperationalEvent(PhotosMetricsConstants.UPLOAD_BUNDLE_NULL_ON_SETUP);
            this.uploadBundleManager.createAndSaveBundle();
        }
        if (this.uploadBundleManager.getUploadBundle() != null) {
            this.uploadBundleManager.getUploadBundle().getUploadManager().getOperations().scheduleUploads(list, str).waitForResult();
        } else {
            Log.e(TAG, "Upload Bundle was null on upload operation");
            recordOperationalEvent(PhotosMetricsConstants.UPLOAD_BUNDLE_NULL_ON_SCHEDULE_REQUESTS);
        }
    }

    public Disposable start() {
        int size = this.contentUris.size();
        String.format(Locale.US, "%d photos selected to be uploaded", Integer.valueOf(size));
        recordOperationalEvent(PhotosMetricsConstants.UPLOAD_V2_OPERATION_STARTED);
        this.messagePublisher.publishMessage(PhotosUploaderEventType.START, new UploadsStartedEvent(size));
        return Single.fromCallable(getRealPathsFromURI(this.contentUris)).map(new Function() { // from class: com.amazon.alexa.photos.-$$Lambda$UploadOperationV2$UvUtOktKg3t8tj5qOQ9W-Vs-9d8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                List buildUploadRequests;
                buildUploadRequests = UploadOperationV2.this.buildUploadRequests((Map) obj);
                return buildUploadRequests;
            }
        }).subscribeOn(Schedulers.computation()).subscribe(new Consumer() { // from class: com.amazon.alexa.photos.-$$Lambda$UploadOperationV2$DTW3DlSagg-Kl1MEiNo3vtPcLPk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                UploadOperationV2.this.lambda$start$0$UploadOperationV2((List) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.photos.-$$Lambda$UploadOperationV2$q13WTgOlwDW2bYkyu3CfZleaucM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                UploadOperationV2.this.lambda$start$1$UploadOperationV2((Throwable) obj);
            }
        });
    }

    private UploadOperationV2(@NonNull UploadBundleManager uploadBundleManager, @NonNull MessagePublisher messagePublisher, @NonNull Lazy<CloudDriveMetrics> lazy, @NonNull Lazy<SystemUtility> lazy2, @NonNull Context context, @NonNull List<Uri> list, @NonNull String str, @NonNull Runnable runnable) {
        this.uploadBundleManager = uploadBundleManager;
        this.messagePublisher = messagePublisher;
        this.cloudDriveMetrics = lazy;
        this.systemUtil = lazy2;
        this.context = context;
        this.contentUris = list;
        this.parentNodeId = str;
        this.fallbackUpload = runnable;
    }
}
