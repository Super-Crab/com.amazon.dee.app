package com.amazon.alexa.photos;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.amazon.alexa.photos.metrics.CloudDriveMetrics;
import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.alexa.photos.util.SystemUtility;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.ConflictError;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtendedClient;
import com.amazon.clouddrive.extended.model.UploadFileExtendedRequest;
import com.amazon.clouddrive.model.CollectionProperties;
import com.amazon.clouddrive.model.ConflictResolution;
import com.amazon.clouddrive.model.CreateNodeRequest;
import com.amazon.clouddrive.model.CreateNodeResponse;
import com.amazon.clouddrive.model.ListNodesRequest;
import com.amazon.clouddrive.model.Node;
import com.amazon.clouddrive.model.QueryAndBlock;
import com.amazon.clouddrive.model.QueryFilter;
import com.amazon.clouddrive.model.QueryObject;
import com.amazon.clouddrive.model.Suppress;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import dagger.Lazy;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class CdsRequestHelper {
    private static final String DEFAULT_IMAGE_EXTENSION = ".jpg";
    static final String FOLDER_QUERY_FILTER = "folderIds";
    static final String LABEL = "DevicesAlbum";
    static final String LIST_NODES_FILTER_FOR_DEVICES_ALBUM = "status:(AVAILABLE) AND kind:%s AND labels:DevicesAlbum";
    static final String LIST_NODES_SORT_ORDER = "[\"createdDate DESC\"]";
    static final String NAME_CONFLICT = "NAME_ALREADY_EXISTS";
    private static final String TAG = "CdsRequestHelper";
    private final String albumName;
    private final Lazy<AmazonCloudDriveExtendedClient> cdClient;
    private final Lazy<CloudDriveMetrics> cloudDriveMetrics;
    private final Context context;
    private final Lazy<SystemUtility> systemUtil;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CdsRequestHelper(@NonNull Lazy<AmazonCloudDriveExtendedClient> lazy, @NonNull Lazy<CloudDriveMetrics> lazy2, @NonNull Lazy<SystemUtility> lazy3, @NonNull Context context) {
        this.cdClient = lazy;
        this.cloudDriveMetrics = lazy2;
        this.systemUtil = lazy3;
        this.context = context;
        this.albumName = context.getString(R.string.alexa_collection_devices_album_display_name);
    }

    @NonNull
    private String createAlbumAndReturnParentFolderId(@NonNull String str) throws InterruptedException, CloudDriveException {
        try {
            long currentTimeMillis = this.systemUtil.mo358get().currentTimeMillis();
            this.cdClient.mo358get().createNode(getAlbumRequest(str));
            recordDuration(PhotosMetricsConstants.CREATE_ALBUM, currentTimeMillis);
            return str;
        } catch (CloudDriveException e) {
            recordErrorMetrics("CloudDriveExceptionAlbum", "Failed to create Alexa Album", e);
            throw e;
        } catch (InterruptedException e2) {
            recordErrorMetrics("InterruptedExceptionAlbum", "Interrupted while creating Alexa Album", e2);
            throw e2;
        }
    }

    @NonNull
    private String createParentFolder() throws InterruptedException, CloudDriveException {
        try {
            long currentTimeMillis = this.systemUtil.mo358get().currentTimeMillis();
            CreateNodeResponse createNode = this.cdClient.mo358get().createNode(getCreateFolderRequest());
            recordDuration(PhotosMetricsConstants.CREATE_FOLDER, currentTimeMillis);
            return createNode.getId();
        } catch (CloudDriveException e) {
            if ((e instanceof ConflictError) && "NAME_ALREADY_EXISTS".equals(e.getCode())) {
                recordErrorMetrics("NameConflictErrorFolder", "Folder node already exists", e);
                return ((ConflictError) e).getNodeId();
            }
            recordErrorMetrics("CloudDriveExceptionFolder", "Failed to create Alexa Folder", e);
            throw e;
        } catch (InterruptedException e2) {
            recordErrorMetrics("InterruptedExceptionFolder", "Interrupted while creating Alexa Folder", e2);
            throw e2;
        }
    }

    @NonNull
    private CreateNodeRequest getAlbumRequest(@NonNull String str) {
        CreateNodeRequest createNodeRequest = new CreateNodeRequest(this.albumName, "VISUAL_COLLECTION");
        createNodeRequest.setLabels(Collections.singletonList(LABEL));
        CollectionProperties collectionProperties = new CollectionProperties();
        QueryObject queryObject = new QueryObject();
        QueryAndBlock queryAndBlock = new QueryAndBlock();
        QueryFilter queryFilter = new QueryFilter(FOLDER_QUERY_FILTER);
        queryFilter.setValues(Collections.singletonList(str));
        queryAndBlock.setFilters(Collections.singletonList(queryFilter));
        queryObject.setInclude(Collections.singletonList(queryAndBlock));
        collectionProperties.setQuery(queryObject);
        createNodeRequest.setCollectionProperties(collectionProperties);
        return createNodeRequest;
    }

    @NonNull
    private CreateNodeRequest getCreateFolderRequest() {
        CreateNodeRequest createNodeRequest = new CreateNodeRequest(this.albumName, "FOLDER");
        createNodeRequest.setLabels(Collections.singletonList(LABEL));
        createNodeRequest.withConflictResolution(ConflictResolution.RENAME);
        return createNodeRequest;
    }

    private long getFileLength(@NonNull ContentResolver contentResolver, @NonNull Uri uri) throws FileNotFoundException {
        try {
            AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, "r");
            if (openAssetFileDescriptor != null) {
                long length = openAssetFileDescriptor.getLength();
                openAssetFileDescriptor.close();
                return length;
            }
            throw new FileNotFoundException("Unable to read File Length");
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    @NonNull
    private String getFileName(@NonNull ContentResolver contentResolver, @NonNull Uri uri) {
        try {
            Cursor query = contentResolver.query(uri, null, null, null, null);
            if (query != null) {
                query.moveToFirst();
                String string = query.getString(query.getColumnIndex("_display_name"));
                if (!Strings.isNullOrEmpty(string)) {
                    query.close();
                    return string;
                }
                throw new FileNotFoundException("Unable to read File Name");
            }
            throw new FileNotFoundException("Unable to read File Name");
        } catch (Exception unused) {
            String lastPathSegment = uri.getLastPathSegment();
            if (Strings.isNullOrEmpty(lastPathSegment)) {
                return Math.abs(new Random().nextLong()) + ".jpg";
            }
            return GeneratedOutlineSupport1.outline72(lastPathSegment, ".jpg");
        }
    }

    static /* synthetic */ String lambda$recordDuration$1(String str) {
        return str;
    }

    static /* synthetic */ String lambda$recordErrorMetrics$0(String str) {
        return str;
    }

    private void recordDuration(final String str, long j) {
        this.cloudDriveMetrics.mo358get().recordSimpleDuration(TAG, new MetricName() { // from class: com.amazon.alexa.photos.-$$Lambda$CdsRequestHelper$AKXOd3f6VALNc6x1zxEdoLxUZlg
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            public final String getEventName() {
                return str;
            }
        }, this.systemUtil.mo358get().currentTimeMillis() - j);
    }

    private void recordErrorMetrics(final String str, String str2, Throwable th) {
        this.cloudDriveMetrics.mo358get().recordSimpleErrorEvent(TAG, new MetricName() { // from class: com.amazon.alexa.photos.-$$Lambda$CdsRequestHelper$ziy2VfVqClHftJfq9aV4TGBxQ8Q
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            public final String getEventName() {
                return str;
            }
        }, str2, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean doesFolderHaveLinkedAlbum(@NonNull List<Node> list, @NonNull String str) {
        for (Node node : list) {
            try {
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                Log.e(TAG, "Failed to determine if Album and Folder are linked", e);
                recordErrorMetrics(PhotosMetricsConstants.ALBUM_MISSING_FOLDER_PROPERTY, "Album has no folder collection property", e);
            }
            if (str.equals(node.getCollectionProperties().getQuery().getInclude().get(0).getFilters().get(0).getValues().get(0))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListNodesRequest getListNodesDevicesAlbumRequest() {
        ListNodesRequest listNodesRequest = new ListNodesRequest();
        listNodesRequest.withFilters(String.format(LIST_NODES_FILTER_FOR_DEVICES_ALBUM, "VISUAL_COLLECTION"));
        listNodesRequest.setSort(LIST_NODES_SORT_ORDER);
        return listNodesRequest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListNodesRequest getListNodesDevicesFolderRequest() {
        ListNodesRequest listNodesRequest = new ListNodesRequest();
        listNodesRequest.withFilters(String.format(LIST_NODES_FILTER_FOR_DEVICES_ALBUM, "FOLDER"));
        listNodesRequest.setSort(LIST_NODES_SORT_ORDER);
        return listNodesRequest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @WorkerThread
    public UploadFileExtendedRequest getUploadFileRequest(@NonNull Uri uri, @NonNull String str) throws FileNotFoundException {
        ContentResolver contentResolver = this.context.getContentResolver();
        UploadFileExtendedRequest uploadFileExtendedRequest = new UploadFileExtendedRequest(getFileName(contentResolver, uri), contentResolver.openInputStream(uri), getFileLength(contentResolver, uri));
        uploadFileExtendedRequest.setParents(Collections.singletonList(str));
        uploadFileExtendedRequest.setSuppress(Suppress.Deduplication);
        return uploadFileExtendedRequest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public String setupAlbum() throws InterruptedException, CloudDriveException {
        return createAlbumAndReturnParentFolderId(createParentFolder());
    }
}
