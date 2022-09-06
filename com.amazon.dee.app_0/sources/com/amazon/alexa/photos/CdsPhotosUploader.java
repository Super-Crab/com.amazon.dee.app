package com.amazon.alexa.photos;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.photos.UploadOperation;
import com.amazon.alexa.photos.UploadOperationV2;
import com.amazon.alexa.photos.api.PhotosUploader;
import com.amazon.alexa.photos.events.PhotosUploaderEventType;
import com.amazon.alexa.photos.events.UploadsCompleteEvent;
import com.amazon.alexa.photos.metrics.CloudDriveMetrics;
import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.alexa.photos.util.SystemUtility;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtendedClient;
import com.amazon.clouddrive.model.ListNodesRequest;
import com.amazon.clouddrive.model.ListNodesResponse;
import com.amazon.clouddrive.model.Node;
import com.amazon.photos.autosave.model.MediaType;
import com.amazon.photos.uploadbundle.UploadBundle;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes9.dex */
public class CdsPhotosUploader implements PhotosUploader {
    public static final int CANNOT_ADD_ITEM_TO_ALEXA_ALBUM = 14;
    public static final String CANNOT_ADD_TO_PARENT = "Cannot Add To Parent";
    public static final int FILE_NOT_FOUND_CODE = 11;
    public static final int GENERIC_FAILURE_CODE = 10;
    public static final int INTERRUPTED_CODE = 12;
    public static final int INVALID_FILE_LENGTH_CODE = 13;
    private static final String TAG = "CdsPhotosUploader";
    private final Lazy<AmazonCloudDriveExtendedClient> cdClient;
    private final CdsRequestHelper cdsRequestHelper;
    private final Lazy<CloudDriveMetrics> cloudDriveMetrics;
    private final Context context;
    private final CompositeDisposable disposables;
    private final Lazy<PhotosFeatureGuardian> featureGuardian;
    private final MessagePublisher messagePublisher;
    private final Lazy<SystemUtility> systemUtil;
    private final Lazy<UploadBundleManager> uploadBundleManager;
    @Nullable
    private UploadOperation uploadOperation;
    private final UploadOperation.Factory uploadOperationFactory;
    @Nullable
    private UploadOperationV2 uploadOperationV2;
    private final UploadOperationV2.Factory uploadOperationV2Factory;

    public CdsPhotosUploader(@NonNull Context context, @NonNull Lazy<AmazonCloudDriveExtendedClient> lazy, @NonNull Lazy<UploadBundleManager> lazy2, @NonNull Lazy<PhotosFeatureGuardian> lazy3, @NonNull final ComponentRegistry componentRegistry) {
        this(context, lazy, lazy2, lazy3, componentRegistry, new Lazy() { // from class: com.amazon.alexa.photos.-$$Lambda$CdsPhotosUploader$QsB-heqyq1K-ydKKdO2IBeCrQAA
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return CdsPhotosUploader.lambda$new$1(ComponentRegistry.this);
            }
        }, $$Lambda$pcUChfT7zFFUCsOfc4JFcq1xF0s.INSTANCE);
    }

    static /* synthetic */ String lambda$listNodes$3(String str) {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CloudDriveMetrics lambda$new$1(final ComponentRegistry componentRegistry) {
        return new CloudDriveMetrics(new Lazy() { // from class: com.amazon.alexa.photos.-$$Lambda$CdsPhotosUploader$qH33tyriqA889Ur8OYSEhaEE-N4
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return CdsPhotosUploader.lambda$null$0(ComponentRegistry.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Mobilytics lambda$null$0(ComponentRegistry componentRegistry) {
        return (Mobilytics) componentRegistry.getLazy(Mobilytics.class).mo10268get();
    }

    @NonNull
    private ListNodesResponse listNodes(@NonNull ListNodesRequest listNodesRequest, @NonNull final String str) throws InterruptedException, CloudDriveException {
        final String outline72 = GeneratedOutlineSupport1.outline72(PhotosMetricsConstants.LIST_NODES_SUCCESS, str);
        try {
            long currentTimeMillis = this.systemUtil.mo358get().currentTimeMillis();
            ListNodesResponse listNodes = this.cdClient.mo358get().listNodes(listNodesRequest);
            this.cloudDriveMetrics.mo358get().recordSimpleDuration(TAG, new MetricName() { // from class: com.amazon.alexa.photos.-$$Lambda$CdsPhotosUploader$du17oSWxlvBPgjKIiCNO0_RmXJ4
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                public final String getEventName() {
                    return outline72;
                }
            }, this.systemUtil.mo358get().currentTimeMillis() - currentTimeMillis);
            return listNodes;
        } catch (CloudDriveException e) {
            this.cloudDriveMetrics.mo358get().recordSimpleErrorEvent(TAG, new MetricName() { // from class: com.amazon.alexa.photos.-$$Lambda$CdsPhotosUploader$_Xp91dGXUaj6qZ99u1vj3fd3woc
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                public final String getEventName() {
                    String outline722;
                    outline722 = GeneratedOutlineSupport1.outline72(PhotosMetricsConstants.CLOUD_DRIVE_EXCEPTION, str);
                    return outline722;
                }
            }, GeneratedOutlineSupport1.outline72("Failed to find Alexa ", str), e);
            throw e;
        } catch (InterruptedException e2) {
            this.cloudDriveMetrics.mo358get().recordSimpleErrorEvent(TAG, new MetricName() { // from class: com.amazon.alexa.photos.-$$Lambda$CdsPhotosUploader$bSPPIFZbo5oKI1yxO8f2Zl5_OmE
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                public final String getEventName() {
                    String outline722;
                    outline722 = GeneratedOutlineSupport1.outline72(PhotosMetricsConstants.INTERRUPTED_EXCEPTION, str);
                    return outline722;
                }
            }, GeneratedOutlineSupport1.outline72("Interrupted while finding Alexa ", str), e2);
            throw e2;
        }
    }

    private void recordListNodesCount(@NonNull List<Node> list, @NonNull final String str) {
        this.cloudDriveMetrics.mo358get().recordSimpleCounter(TAG, new MetricName() { // from class: com.amazon.alexa.photos.-$$Lambda$CdsPhotosUploader$G0HdYK3wsVL2-6OHjTf88i9oLCg
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            public final String getEventName() {
                String outline72;
                outline72 = GeneratedOutlineSupport1.outline72(PhotosMetricsConstants.LIST_NODES_RESPONSE_COUNT, str);
                return outline72;
            }
        }, list.size());
    }

    private void recordOperationalEvent(String str) {
        this.cloudDriveMetrics.mo358get().recordEvent(TAG, str);
    }

    private void startUploadOperation(@NonNull List<Uri> list, @NonNull String str) {
        this.uploadOperation = this.uploadOperationFactory.getUploadOperation(list, str);
        this.disposables.add(this.uploadOperation.start());
    }

    private void startV2UploadOperation(@NonNull List<Uri> list, @NonNull String str, @NonNull Runnable runnable) {
        UploadOperationV2.Factory factory = this.uploadOperationV2Factory;
        if (factory != null) {
            this.uploadOperationV2 = factory.getUploadOperation(list, str, runnable);
            this.disposables.add(this.uploadOperationV2.start());
        }
    }

    @VisibleForTesting
    boolean allDocumentUris(List<Uri> list) {
        for (Uri uri : list) {
            if (!isDocumentUri(uri)) {
                recordOperationalEvent(PhotosMetricsConstants.NON_DOCUMENT_URI_PRESENT);
                return false;
            }
        }
        recordOperationalEvent(PhotosMetricsConstants.ALL_DOCUMENT_URIS_PRESENT);
        return true;
    }

    @Override // com.amazon.alexa.photos.api.PhotosUploader
    public void cancelUploads() {
        this.disposables.clear();
        this.uploadOperation = null;
        this.uploadOperationV2 = null;
    }

    @Override // com.amazon.alexa.photos.api.PhotosUploader
    @NonNull
    public Single<String> createAlexaAlbum() {
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.photos.-$$Lambda$CdsPhotosUploader$rOXNvj1bdJcX1shE9pDIISDZB5U
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return CdsPhotosUploader.this.lambda$createAlexaAlbum$2$CdsPhotosUploader();
            }
        });
    }

    Runnable getFallbackUploadRunnable(@NonNull final List<Uri> list, @NonNull final String str) {
        return new Runnable() { // from class: com.amazon.alexa.photos.-$$Lambda$CdsPhotosUploader$ThNiRXg6e8ouWUf47Zo8jcy00H0
            @Override // java.lang.Runnable
            public final void run() {
                CdsPhotosUploader.this.lambda$getFallbackUploadRunnable$7$CdsPhotosUploader(list, str);
            }
        };
    }

    @VisibleForTesting
    boolean isDocumentUri(Uri uri) {
        return DocumentsContract.isDocumentUri(this.context, uri);
    }

    @Override // com.amazon.alexa.photos.api.PhotosUploader
    public boolean isUploadInProgress() {
        UploadOperation uploadOperation;
        UploadOperationV2 uploadOperationV2 = this.uploadOperationV2;
        return (uploadOperationV2 != null && uploadOperationV2.isUploadInProgress()) || ((uploadOperation = this.uploadOperation) != null && uploadOperation.isUploadInProgress());
    }

    public /* synthetic */ String lambda$createAlexaAlbum$2$CdsPhotosUploader() throws Exception {
        recordOperationalEvent(PhotosMetricsConstants.FINDING_ALEXA_ALBUM);
        List<Node> data = listNodes(this.cdsRequestHelper.getListNodesDevicesAlbumRequest(), PhotosMetricsConstants.ALBUM_SUFFIX).getData();
        recordListNodesCount(data, PhotosMetricsConstants.ALBUM_SUFFIX);
        if (data.isEmpty()) {
            recordOperationalEvent(PhotosMetricsConstants.NO_ALBUM_FOUND);
            return this.cdsRequestHelper.setupAlbum();
        }
        recordOperationalEvent(PhotosMetricsConstants.FINDING_ALEXA_FOLDER);
        List<Node> data2 = listNodes(this.cdsRequestHelper.getListNodesDevicesFolderRequest(), PhotosMetricsConstants.FOLDER_SUFFIX).getData();
        recordListNodesCount(data2, PhotosMetricsConstants.FOLDER_SUFFIX);
        if (data2.isEmpty()) {
            recordOperationalEvent(PhotosMetricsConstants.NO_FOLDER_FOUND);
            return this.cdsRequestHelper.setupAlbum();
        }
        recordOperationalEvent(PhotosMetricsConstants.FOLDER_FOUND);
        String id = data2.get(0).getId();
        if (!this.cdsRequestHelper.doesFolderHaveLinkedAlbum(data, id)) {
            return this.cdsRequestHelper.setupAlbum();
        }
        recordOperationalEvent(PhotosMetricsConstants.RETURN_EXISTING_FOLDER);
        return id;
    }

    public /* synthetic */ void lambda$getFallbackUploadRunnable$7$CdsPhotosUploader(List list, String str) {
        this.disposables.clear();
        this.uploadOperationV2 = null;
        startUploadOperation(list, str);
    }

    @Override // com.amazon.alexa.photos.api.PhotosUploader
    public void setAutoSaveEnabled(boolean z) {
        UploadBundle uploadBundle = this.uploadBundleManager.mo358get().getUploadBundle();
        if (uploadBundle != null) {
            if (this.featureGuardian.mo358get().isAutosaveFeatureAvailable()) {
                uploadBundle.getAutosaveManager().getPreferences().setAutosavePreference(MediaType.PHOTO, z);
                recordOperationalEvent(z ? PhotosMetricsConstants.ENABLE_AUTO_SAVE : PhotosMetricsConstants.DISABLE_AUTO_SAVE);
                if (z) {
                    uploadBundle.getUploadManager().getOperations().reupdateBlockersSync();
                    uploadBundle.getUploadManager().getOperations().invalidateBlockers();
                    return;
                }
                uploadBundle.getDiscovery().getOperations().disable();
                return;
            }
            recordOperationalEvent(PhotosMetricsConstants.ATTEMPT_TOGGLE_AUTOSAVE_WEBLAB_OFF);
        }
    }

    @Override // com.amazon.alexa.photos.api.PhotosUploader
    public void setCellularDataEnabled(boolean z) {
        UploadBundle uploadBundle = this.uploadBundleManager.mo358get().getUploadBundle();
        if (uploadBundle != null) {
            if (this.featureGuardian.mo358get().isAutosaveFeatureAvailable()) {
                uploadBundle.getAutosaveManager().getPreferences().setMeteredNetworkPreferences(MediaType.PHOTO, z);
                recordOperationalEvent(z ? PhotosMetricsConstants.ENABLE_CELLULAR_DATA : PhotosMetricsConstants.DISABLE_CELLULAR_DATA);
                return;
            }
            recordOperationalEvent(PhotosMetricsConstants.ATTEMPT_TOGGLE_CELLULAR_WEBLAB_OFF);
        }
    }

    @Override // com.amazon.alexa.photos.api.PhotosUploader
    public void upload(@NonNull List<Uri> list, @NonNull String str) {
        boolean z = false;
        if (list.isEmpty()) {
            Log.w(TAG, "No photos selected to be uploaded");
            recordOperationalEvent(PhotosMetricsConstants.EMPTY_SELECTION);
            this.messagePublisher.publishMessage(PhotosUploaderEventType.COMPLETE, new UploadsCompleteEvent(0, 0));
            return;
        }
        if (!this.featureGuardian.mo358get().isAutosaveFeatureAvailable()) {
            if (isUploadInProgress()) {
                Log.e(TAG, "Cannot proceed with new uploads, uploads are already in progress.");
                recordOperationalEvent(PhotosMetricsConstants.UPLOAD_REJECTED);
                return;
            }
            cancelUploads();
        }
        if (this.featureGuardian.mo358get().isUploaderV2Available() && allDocumentUris(list)) {
            startV2UploadOperation(list, str, getFallbackUploadRunnable(list, str));
            z = true;
        }
        if (z) {
            return;
        }
        startUploadOperation(list, str);
    }

    public CdsPhotosUploader(@NonNull Context context, @NonNull Lazy<AmazonCloudDriveExtendedClient> lazy, @NonNull Lazy<UploadBundleManager> lazy2, @NonNull Lazy<PhotosFeatureGuardian> lazy3, @NonNull ComponentRegistry componentRegistry, @NonNull Lazy<CloudDriveMetrics> lazy4, @NonNull Lazy<SystemUtility> lazy5) {
        this(context, lazy, lazy2, lazy3, lazy4, lazy5, new CdsRequestHelper(lazy, lazy4, lazy5, context), new MessagePublisher(componentRegistry.getLazy(EventBus.class)));
    }

    private CdsPhotosUploader(@NonNull Context context, @NonNull Lazy<AmazonCloudDriveExtendedClient> lazy, @NonNull Lazy<UploadBundleManager> lazy2, @NonNull Lazy<PhotosFeatureGuardian> lazy3, @NonNull Lazy<CloudDriveMetrics> lazy4, @NonNull Lazy<SystemUtility> lazy5, @NonNull CdsRequestHelper cdsRequestHelper, @NonNull MessagePublisher messagePublisher) {
        this(context, lazy, lazy2, lazy4, lazy5, cdsRequestHelper, messagePublisher, lazy3, new UploadOperation.Factory(lazy, lazy4, lazy5, cdsRequestHelper, messagePublisher), new UploadOperationV2.Factory(lazy2, messagePublisher, lazy4, lazy5, context));
    }

    @VisibleForTesting
    CdsPhotosUploader(@NonNull Context context, @NonNull Lazy<AmazonCloudDriveExtendedClient> lazy, @NonNull Lazy<UploadBundleManager> lazy2, @NonNull Lazy<CloudDriveMetrics> lazy3, @NonNull Lazy<SystemUtility> lazy4, @NonNull CdsRequestHelper cdsRequestHelper, @NonNull MessagePublisher messagePublisher, @NonNull Lazy<PhotosFeatureGuardian> lazy5, @NonNull UploadOperation.Factory factory, @NonNull UploadOperationV2.Factory factory2) {
        this.disposables = new CompositeDisposable();
        this.context = context;
        this.cdClient = lazy;
        this.uploadBundleManager = lazy2;
        this.cloudDriveMetrics = lazy3;
        this.systemUtil = lazy4;
        this.cdsRequestHelper = cdsRequestHelper;
        this.messagePublisher = messagePublisher;
        this.featureGuardian = lazy5;
        this.uploadOperationFactory = factory;
        this.uploadOperationV2Factory = factory2;
    }
}
