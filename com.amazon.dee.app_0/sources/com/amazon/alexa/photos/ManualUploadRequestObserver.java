package com.amazon.alexa.photos;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.photos.UploadBundleManager;
import com.amazon.alexa.photos.events.PhotosUploaderEventType;
import com.amazon.alexa.photos.events.UploadFailureEvent;
import com.amazon.alexa.photos.events.UploadSuccessEvent;
import com.amazon.alexa.photos.events.UploadsBlockedEvent;
import com.amazon.alexa.photos.events.UploadsCompleteEvent;
import com.amazon.alexa.photos.events.UploadsProcessingCompleteEvent;
import com.amazon.alexa.photos.events.UploadsProgressStatusEvent;
import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.clouddrive.cdasdk.cds.child.AddChildRequest;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.clouddrive.cdasdk.cds.common.ResourceVersion;
import com.amazon.photos.autosave.AutosaveEventObserver;
import com.amazon.photos.autosave.AutosaveUtilKt;
import com.amazon.photos.autosave.model.MediaType;
import com.amazon.photos.uploadbundle.UploadBundle;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.blockers.LowBatteryBlocker;
import com.amazon.photos.uploader.blockers.MeteredConnectionBlocker;
import com.amazon.photos.uploader.blockers.NoNetworkBlocker;
import com.amazon.photos.uploader.blockers.PauseBlocker;
import com.amazon.photos.uploader.blockers.QuotaExceededBlocker;
import com.amazon.photos.uploader.blockers.StoragePermissionBlocker;
import com.amazon.photos.uploader.observables.AbandonedRequestInfo;
import com.amazon.photos.uploader.observables.QueueSummary;
import com.amazon.photos.uploader.observables.UploadRequestObserver;
import com.amazon.photos.uploader.observables.UploadSummary;
import com.amazon.photos.uploader.observables.UploadSummaryObserver;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/* loaded from: classes9.dex */
public class ManualUploadRequestObserver implements UploadRequestObserver, UploadSummaryObserver, AutosaveEventObserver {
    private static final long FINAL_NOTIFICATION_POST_DELAY_MILLIS = TimeUnit.MILLISECONDS.toMillis(500);
    private static final String UNKNOWN_EXCEPTION = "_Ex_Unknown";
    private static final String UPLOAD_STATUS_CHANNEL_ID = "upload_status_channel_id";
    @NonNull
    private final CDClient cdClient;
    @NonNull
    private final CdsRequestHelperV2 cdsRequestHelper;
    @NonNull
    private final Context context;
    @Nullable
    @VisibleForTesting
    Bitmap currentNotificationUploadIcon;
    @NonNull
    private final Logger logger;
    @NonNull
    private final MessagePublisher messagePublisher;
    @NonNull
    private final Metrics metrics;
    @NonNull
    private final Class<?> notificationDeepLinkLauncherClass;
    @Nullable
    private final NotificationManager notificationManager;
    private PendingIntent pendingDeepLinkIntent;
    @NonNull
    private final PhotosFeatureGuardian photosFeatureGuardian;
    @NonNull
    private final TaskManager taskManager;
    @NonNull
    private final Handler uiHandler;
    @NonNull
    private final WeakReference<UploadBundleManager> uploadBundleManagerWeakRef;
    private final UploadBundleManager.UploaderStatus uploaderStatus;
    public final String METRICS_TAG = "ManualUploadRequestObserver";
    public final String TAG = "ManualUploadRequestObse";
    @VisibleForTesting
    int currentManualBatchSuccessCount = 0;
    @VisibleForTesting
    int currentManualBatchFailureCount = 0;
    @VisibleForTesting
    List<Long> currentManualBatchQuotaBlockedRequests = new ArrayList();

    /* renamed from: com.amazon.alexa.photos.ManualUploadRequestObserver$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$photos$ManualUploadRequestObserver$UploadNotificationType = new int[UploadNotificationType.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$photos$uploader$UploadErrorCategory;

        static {
            try {
                $SwitchMap$com$amazon$alexa$photos$ManualUploadRequestObserver$UploadNotificationType[UploadNotificationType.PROGRESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$photos$ManualUploadRequestObserver$UploadNotificationType[UploadNotificationType.COMPLETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$photos$ManualUploadRequestObserver$UploadNotificationType[UploadNotificationType.STOPPED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$amazon$photos$uploader$UploadErrorCategory = new int[UploadErrorCategory.values().length];
            try {
                $SwitchMap$com$amazon$photos$uploader$UploadErrorCategory[UploadErrorCategory.FILE_NOT_PRESENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$photos$uploader$UploadErrorCategory[UploadErrorCategory.APPLICATION_CANCELLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$photos$uploader$UploadErrorCategory[UploadErrorCategory.INVALID_PARAMETER_FILE_LENGTH_MISMATCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes9.dex */
    public static class AlbumNotFoundException extends Exception {
        AlbumNotFoundException(@NonNull String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes9.dex */
    public static class FolderNodeNotFoundException extends Exception {
        FolderNodeNotFoundException(@NonNull String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes9.dex */
    public enum UploadNotificationType {
        PROGRESS,
        COMPLETE,
        STOPPED
    }

    public ManualUploadRequestObserver(@NonNull Context context, @NonNull MessagePublisher messagePublisher, @NonNull CdsRequestHelperV2 cdsRequestHelperV2, @NonNull CDClient cDClient, @NonNull Metrics metrics, @NonNull UploadBundleManager uploadBundleManager, @NonNull UploadBundleManager.UploaderStatus uploaderStatus, @NonNull Class<?> cls, @NonNull TaskManager taskManager, @NonNull PhotosFeatureGuardian photosFeatureGuardian, @NonNull Logger logger) {
        NotificationManager notificationManager;
        this.messagePublisher = messagePublisher;
        this.cdsRequestHelper = cdsRequestHelperV2;
        this.cdClient = cDClient;
        this.metrics = metrics;
        this.context = context.getApplicationContext();
        this.uploadBundleManagerWeakRef = new WeakReference<>(uploadBundleManager);
        this.notificationDeepLinkLauncherClass = cls;
        this.taskManager = taskManager;
        this.photosFeatureGuardian = photosFeatureGuardian;
        this.logger = logger;
        this.notificationManager = (NotificationManager) context.getSystemService("notification");
        this.uiHandler = new Handler(context.getMainLooper());
        if (photosFeatureGuardian.isAutosaveFeatureAvailable() && Build.VERSION.SDK_INT > 26 && (notificationManager = this.notificationManager) != null) {
            notificationManager.createNotificationChannel(new NotificationChannel(UPLOAD_STATUS_CHANNEL_ID, context.getResources().getString(R.string.notification_channel_name_upload), 2));
        }
        this.uploaderStatus = uploaderStatus;
    }

    private void addChildToAlbum(String str, String str2) {
        AddChildRequest addChildRequest = new AddChildRequest();
        addChildRequest.setParentId(str);
        addChildRequest.setChildId(str2);
        addChildRequest.setResourceVersion(ResourceVersion.V2);
        long currentTime = getCurrentTime();
        this.cdClient.getCDSCalls().getChildCalls().addChild(addChildRequest).blockingGet();
        this.metrics.recordSimpleDuration("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$k4Ve8Zixuya8lwL8wkwqoFUy3U.INSTANCE, getCurrentTime() - currentTime);
    }

    @WorkerThread
    private boolean addNodeToPhotosAlbumSync(@NonNull String str, boolean z) {
        try {
            addChildToAlbum(getAssociatedAlbumWithDeviceFolder(getDeviceFolderNodeId()), str);
            this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$67FCsefVwAWhFMSFisipU9N01o.INSTANCE, new MetricRecordingType[0]);
            this.logger.d("ManualUploadRequestObse", "Successfully added node to album for the flow Md5ConflictAlbumAddedSuccess");
            publishSuccessMessage(str, z);
            return true;
        } catch (Exception e) {
            this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$ZtLuZYDGc3T_W7F75pfQaYV00k.INSTANCE, new MetricRecordingType[0]);
            this.metrics.recordSimpleEvent("ManualUploadRequestObserver", new MetricName() { // from class: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$8Qg-GDK4tMfzZ1IR3wkZEKuPSTI
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                public final String getEventName() {
                    return ManualUploadRequestObserver.this.lambda$addNodeToPhotosAlbumSync$16$ManualUploadRequestObserver(e);
                }
            }, new MetricRecordingType[0]);
            this.logger.e("ManualUploadRequestObse", "Failed to find album for the upload for the flow Md5ConflictAlbumAddedFailed", e);
            if (z) {
                this.messagePublisher.publishMessage(PhotosUploaderEventType.FAILURE, new UploadFailureEvent(e.getMessage(), CdsPhotosUploader.CANNOT_ADD_TO_PARENT, 14));
            }
            return false;
        }
    }

    @VisibleForTesting
    static boolean areAllUploadsBlocked(@NonNull UploadSummary uploadSummary) {
        for (QueueSummary queueSummary : uploadSummary.getQueueSummaries()) {
            if (queueSummary.getBlockedRequestCount() > 0 && queueSummary.getQueuedRequestCount() == 0 && queueSummary.getRunningRequestCount() == 0) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    static boolean areUploadsRunning(@NonNull UploadSummary uploadSummary) {
        for (QueueSummary queueSummary : uploadSummary.getQueueSummaries()) {
            if (hasPendingRequests(queueSummary)) {
                return true;
            }
        }
        return false;
    }

    private void clearCompletedUploadRecords() {
        UploadBundle uploadBundle = this.uploadBundleManagerWeakRef.get().getUploadBundle();
        if (uploadBundle != null) {
            uploadBundle.getUploadManager().getOperations().clearCompletedUploadRecords().waitForResult();
        }
    }

    private void evaluateLowBatteryBlocker(@NonNull Map<QueueSummary, Set<Blocker>> map) {
        for (Map.Entry<QueueSummary, Set<Blocker>> entry : map.entrySet()) {
            if (hasIncompleteRequests(entry.getKey()) && entry.getValue().contains(LowBatteryBlocker.INSTANCE)) {
                this.uploaderStatus.setHasLowBattery(true);
                this.uploaderStatus.setUploadInProgress(false);
                this.messagePublisher.publishMessage(PhotosUploaderEventType.LOW_BATTERY, new UploadsBlockedEvent(LowBatteryBlocker.INSTANCE.getName()));
                Logger logger = this.logger;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Upload requests blocked by low battery on Queue = ");
                outline107.append(entry.getKey().getQueue().getName());
                logger.v("ManualUploadRequestObse", outline107.toString());
                this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$47bgw6_5uRFc4FazywIvvoWDeg.INSTANCE, new MetricRecordingType[0]);
                return;
            }
        }
        this.uploaderStatus.setHasLowBattery(false);
    }

    private void evaluateNoNetworkBlocker(@NonNull Collection<Blocker> collection) {
        if (collection.contains(NoNetworkBlocker.INSTANCE)) {
            this.uploaderStatus.setHasInsufficientNetwork(true);
            this.uploaderStatus.setUploadInProgress(false);
            this.messagePublisher.publishMessage(PhotosUploaderEventType.INSUFFICIENT_NETWORK, new UploadsBlockedEvent(NoNetworkBlocker.INSTANCE.getName()));
            this.logger.v("ManualUploadRequestObse", "Upload requests blocked by no network.");
            this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$ULdW_G8WhGIMymtc23hbVbOhF8.INSTANCE, new MetricRecordingType[0]);
            return;
        }
        this.uploaderStatus.setHasInsufficientNetwork(false);
    }

    private void evaluateNoPermissionsBlocker(@NonNull Collection<Blocker> collection) {
        if (collection.contains(StoragePermissionBlocker.INSTANCE)) {
            this.uploaderStatus.setHasNoPermission(true);
            this.uploaderStatus.setUploadInProgress(false);
            this.messagePublisher.publishMessage(PhotosUploaderEventType.NO_PERMISSIONS, new UploadsBlockedEvent(StoragePermissionBlocker.INSTANCE.getName()));
            this.logger.v("ManualUploadRequestObse", "Upload requests blocked by no storage permissions.");
            this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$Bh_tmIonwFQY7BFUkV_jfKRYxj8.INSTANCE, new MetricRecordingType[0]);
            return;
        }
        this.uploaderStatus.setHasNoPermission(false);
    }

    private void evaluateNoWifiBlocker(@NonNull Map<QueueSummary, Set<Blocker>> map) {
        for (Map.Entry<QueueSummary, Set<Blocker>> entry : map.entrySet()) {
            if (hasIncompleteRequests(entry.getKey()) && entry.getValue().contains(MeteredConnectionBlocker.INSTANCE)) {
                this.uploaderStatus.setHasNoWifi(true);
                this.uploaderStatus.setUploadInProgress(false);
                this.messagePublisher.publishMessage(PhotosUploaderEventType.NO_WIFI, new UploadsBlockedEvent(MeteredConnectionBlocker.INSTANCE.getName()));
                Logger logger = this.logger;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Upload requests blocked by no wifi on Queue = ");
                outline107.append(entry.getKey().getQueue().getName());
                logger.v("ManualUploadRequestObse", outline107.toString());
                this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$YmhrpbPPoG7sGDU87Df8KTv2O8.INSTANCE, new MetricRecordingType[0]);
                return;
            }
        }
        this.uploaderStatus.setHasNoWifi(false);
    }

    private void evaluatePauseBlocker(@NonNull Collection<Blocker> collection) {
        if (collection.contains(PauseBlocker.INSTANCE)) {
            this.uploaderStatus.setExternallyPaused(true);
            this.uploaderStatus.setUploadInProgress(false);
            this.messagePublisher.publishMessage(PhotosUploaderEventType.REMOTELY_DISABLED, new UploadsBlockedEvent(PauseBlocker.INSTANCE.getName()));
            this.logger.v("ManualUploadRequestObse", "Upload requests blocked by external pause.");
            this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$FJrEXI_uwfW3cyfumJYSu18teBI.INSTANCE, new MetricRecordingType[0]);
            return;
        }
        this.uploaderStatus.setExternallyPaused(false);
    }

    private void evaluateQuotaBlocker(@NonNull Set<Blocker> set) {
        if (set.contains(QuotaExceededBlocker.INSTANCE)) {
            this.uploaderStatus.setOutOfPhotoQuota(true);
            this.uploaderStatus.setUploadInProgress(false);
            this.messagePublisher.publishMessage(PhotosUploaderEventType.NO_STORAGE, new UploadsBlockedEvent(QuotaExceededBlocker.INSTANCE.getName()));
            this.logger.v("ManualUploadRequestObse", "Upload requests blocked by over quota.");
            this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$v7bTiiF6AJIal24cLiSERvVg7Hg.INSTANCE, new MetricRecordingType[0]);
            return;
        }
        this.uploaderStatus.setOutOfPhotoQuota(false);
    }

    private String getAssociatedAlbumWithDeviceFolder(String str) throws AlbumNotFoundException {
        this.logger.d("ManualUploadRequestObse", "Attempting to find an existing Album");
        this.metrics.recordSimpleDuration("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$zJs8GIki6iFIhjTIYEgMHm78gJY.INSTANCE, getCurrentTime() - getCurrentTime());
        String albumIdAssociatedWithFolder = this.cdsRequestHelper.getAlbumIdAssociatedWithFolder(this.cdClient.getCDSCalls().getNodeCalls().listNodes(this.cdsRequestHelper.getListNodeDevicesAlbumRequest()).blockingGet().getData(), str);
        if (albumIdAssociatedWithFolder != null) {
            return albumIdAssociatedWithFolder;
        }
        this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$6ym200_KlqWR8tepmR99viNtMik.INSTANCE, new MetricRecordingType[0]);
        throw new AlbumNotFoundException("No album found associated with the folder");
    }

    @NonNull
    private String getDeviceFolderNodeId() throws FolderNodeNotFoundException {
        this.logger.d("ManualUploadRequestObse", "Attempting to find an existing Folder");
        this.metrics.recordSimpleDuration("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$mjbZ_9FMqLfNVbyXD7Sk4QSc9Q.INSTANCE, getCurrentTime() - getCurrentTime());
        List<NodeInfo> data = this.cdClient.getCDSCalls().getNodeCalls().listNodes(this.cdsRequestHelper.getListNodeDevicesFolderRequest()).blockingGet().getData();
        ClientMetric clientMetric = new ClientMetric();
        clientMetric.addCounter($$Lambda$ManualUploadRequestObserver$QBQUf_8e0NDxwe2Clhh5F_6FQ.INSTANCE, data.size());
        this.metrics.recordCustomMetric("ManualUploadRequestObserver", clientMetric, new MetricRecordingType[0]);
        if (!data.isEmpty()) {
            String id = data.get(0).getId();
            if (id != null) {
                return id;
            }
            this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$LEIO7oZI1Ma058ntMKvupSiz9A.INSTANCE, new MetricRecordingType[0]);
            throw new FolderNodeNotFoundException("Folder node id not found");
        }
        this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$tXqbO6DfcVH3T4JwtuS05qntRk.INSTANCE, new MetricRecordingType[0]);
        throw new FolderNodeNotFoundException("Folder node not found");
    }

    private static int getErrorCodeForCategory(@NonNull UploadErrorCategory uploadErrorCategory) {
        switch (uploadErrorCategory.ordinal()) {
            case 11:
                return 11;
            case 12:
                return 13;
            case 13:
                return 12;
            default:
                return 10;
        }
    }

    private String getExceptionSuffix(@NonNull String str, @NonNull Exception exc) {
        if (!Strings.isNullOrEmpty(exc.getClass().getSimpleName())) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, "_Ex_");
            outline113.append(exc.getClass().getSimpleName());
            return outline113.toString();
        }
        return GeneratedOutlineSupport1.outline72(str, UNKNOWN_EXCEPTION);
    }

    @NonNull
    private String getNotificationBodyText(@NonNull UploadNotificationType uploadNotificationType) {
        Resources resources = this.context.getResources();
        if (uploadNotificationType == UploadNotificationType.STOPPED) {
            return resources.getString(R.string.upload_notification_body_text_upload_stopped);
        }
        return resources.getString(R.string.upload_notification_body_text);
    }

    @NonNull
    private String getNotificationTitle(@NonNull UploadNotificationType uploadNotificationType) {
        Resources resources = this.context.getResources();
        int ordinal = uploadNotificationType.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return resources.getQuantityString(R.plurals.upload_notification_title_upload_complete, this.uploaderStatus.getCurrentUploadCount(), Integer.valueOf(this.uploaderStatus.getCurrentUploadCount()));
            }
            if (ordinal == 2) {
                return resources.getString(R.string.upload_notification_title_upload_stopped);
            }
            throw new IllegalArgumentException("Invalid Notification Type");
        }
        return resources.getString(R.string.upload_notification_title_uploading_photos_x_of_y, Integer.valueOf(this.uploaderStatus.getCurrentUploadCount()), Integer.valueOf(this.uploaderStatus.getSelectedCount()));
    }

    private static boolean hasIncompleteRequests(@NonNull QueueSummary queueSummary) {
        return hasPendingRequests(queueSummary) || queueSummary.getBlockedRequestCount() > 0;
    }

    private static boolean hasPendingRequests(@NonNull QueueSummary queueSummary) {
        return queueSummary.getQueuedRequestCount() > 0 || queueSummary.getRunningRequestCount() > 0;
    }

    private static boolean isManualUpload(@NonNull UploadRequest uploadRequest) {
        return UploadBundleManager.MANUAL_QUEUE.equals(uploadRequest.getQueue());
    }

    private void onManualUploadsComplete() {
        this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$qtIysqRVI7ZUGg7NtEGA_NTyp3s.INSTANCE, new MetricRecordingType[0]);
        this.messagePublisher.publishMessage(PhotosUploaderEventType.COMPLETE, new UploadsCompleteEvent(this.currentManualBatchSuccessCount, this.currentManualBatchQuotaBlockedRequests.size() + this.currentManualBatchFailureCount));
        resetManualUploadBatch();
    }

    private void onUploaderProcessingComplete() {
        if (this.photosFeatureGuardian.isAutosaveFeatureAvailable()) {
            this.messagePublisher.publishMessage(PhotosUploaderEventType.UPLOAD_PROCESSING_COMPLETE, new UploadsProcessingCompleteEvent(this.uploaderStatus.getCurrentUploadCount(), this.uploaderStatus.getSelectedCount()));
            makeUploaderOperationsLongRunning(createNotification(UploadNotificationType.COMPLETE), true);
            this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$mfoj6husJ0cLuUBYsdCp9D8wPSc.INSTANCE, new MetricRecordingType[0]);
        }
        resetFullBatch();
    }

    private void publishSuccessMessage(@Nullable String str, boolean z) {
        if (z) {
            this.messagePublisher.publishMessage(PhotosUploaderEventType.SUCCESS, new UploadSuccessEvent(str));
        }
        this.messagePublisher.publishMessage(PhotosUploaderEventType.UPLOAD_PROGRESS_STATUS, new UploadsProgressStatusEvent(this.uploaderStatus.getCurrentUploadCount(), this.uploaderStatus.getSelectedCount()));
    }

    private void resetFullBatch() {
        this.uploaderStatus.setUploadInProgress(false);
        this.currentNotificationUploadIcon = null;
        clearCompletedUploadRecords();
    }

    private void resetManualUploadBatch() {
        this.currentManualBatchSuccessCount = 0;
        this.currentManualBatchFailureCount = 0;
        UploadBundle uploadBundle = this.uploadBundleManagerWeakRef.get().getUploadBundle();
        if (uploadBundle != null) {
            uploadBundle.getUploadManager().getOperations().cancelUploads(this.currentManualBatchQuotaBlockedRequests).waitForResult();
        }
        this.currentManualBatchQuotaBlockedRequests.clear();
    }

    private void updateUploaderRequestStatus(UploadSummary uploadSummary) {
        Collection<Blocker> globalBlockers = uploadSummary.getGlobalBlockers();
        HashMap hashMap = new HashMap();
        HashSet hashSet = new HashSet();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (QueueSummary queueSummary : uploadSummary.getQueueSummaries()) {
            i += queueSummary.getCompletedRequestCount();
            i2 += queueSummary.getRunningRequestCount();
            i3 += queueSummary.getQueuedRequestCount();
            Set<Blocker> set = (Set) MoreObjects.firstNonNull(hashMap.get(queueSummary), new HashSet());
            set.addAll(queueSummary.getQueueBlockers());
            hashMap.put(queueSummary, set);
            hashSet.addAll(queueSummary.getRequestBlockers());
        }
        this.uploaderStatus.setCurrentUploadCount(i);
        this.uploaderStatus.setSelectedCount(i + i2 + i3);
        if (this.uploaderStatus.isAutoSaveEnabled()) {
            evaluatePauseBlocker(globalBlockers);
            evaluateNoNetworkBlocker(globalBlockers);
            evaluateNoWifiBlocker(hashMap);
            evaluateQuotaBlocker(hashSet);
            evaluateNoPermissionsBlocker(globalBlockers);
            evaluateLowBatteryBlocker(hashMap);
        }
    }

    private boolean wereManualUploadsAttempted() {
        return this.currentManualBatchQuotaBlockedRequests.size() + (this.currentManualBatchSuccessCount + this.currentManualBatchFailureCount) > 0;
    }

    @VisibleForTesting
    boolean areManualUploadsComplete(@NonNull UploadSummary uploadSummary) {
        if (uploadSummary.getGlobalBlockers().size() > 0) {
            return false;
        }
        for (QueueSummary queueSummary : uploadSummary.getQueueSummaries()) {
            if (UploadBundleManager.MANUAL_QUEUE.equals(queueSummary.getQueue().getName()) && hasPendingRequests(queueSummary)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean areUploadsInProgress() {
        return this.uploaderStatus.isUploadInProgress();
    }

    @VisibleForTesting
    Notification createNotification(@NonNull UploadNotificationType uploadNotificationType) {
        return new NotificationCompat.Builder(this.context, UPLOAD_STATUS_CHANNEL_ID).setSmallIcon(R.drawable.ic_alexa).setLargeIcon(uploadNotificationType == UploadNotificationType.STOPPED ? null : this.currentNotificationUploadIcon).setContentTitle(getNotificationTitle(uploadNotificationType)).setContentText(getNotificationBodyText(uploadNotificationType)).setContentIntent(getUploadSettingsDeepLinkIntent()).build();
    }

    @VisibleForTesting
    long getCurrentTime() {
        return System.currentTimeMillis();
    }

    @VisibleForTesting
    PendingIntent getUploadSettingsDeepLinkIntent() {
        if (this.pendingDeepLinkIntent == null) {
            Intent addFlags = new Intent(this.context, this.notificationDeepLinkLauncherClass).addFlags(603979776);
            addFlags.addFlags(268435456);
            addFlags.setData(Uri.parse("v2/elements-amazon-photos/photos/upload-settings"));
            addFlags.setAction("android.intent.action.VIEW");
            this.pendingDeepLinkIntent = PendingIntent.getActivity(this.context, 0, addFlags, 0);
        }
        return this.pendingDeepLinkIntent;
    }

    @VisibleForTesting
    boolean isRequestFromAutosaveQueue(UploadRequest uploadRequest) {
        return AutosaveUtilKt.isFromAutosave(uploadRequest);
    }

    public /* synthetic */ String lambda$addNodeToPhotosAlbumSync$16$ManualUploadRequestObserver(Exception exc) {
        return getExceptionSuffix(PhotosMetricsConstants.MD5_CONFLICT_FAILED_TO_ADD_TO_ALBUM, exc);
    }

    @VisibleForTesting
    void makeUploaderOperationsLongRunning(@NonNull final Notification notification, @NonNull Boolean bool) {
        if (!this.photosFeatureGuardian.isAutosaveFeatureAvailable()) {
            this.logger.w("ManualUploadRequestObse", "AutoSave feature is Unavailable. Cannot make operation long-running.");
            this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$onJcBohEamN9k7MKBfJxwBPwwa4.INSTANCE, new MetricRecordingType[0]);
        } else if (this.notificationManager != null) {
            UploadBundle uploadBundle = this.uploadBundleManagerWeakRef.get().getUploadBundle();
            if (uploadBundle != null) {
                uploadBundle.getUploadManager().getOperations().updateNotification(this.notificationManager, R.id.upload_notification_id, notification, bool.booleanValue());
                if (bool.booleanValue()) {
                    this.uiHandler.postDelayed(new Runnable() { // from class: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$iLC0gQSoMkibykFDGqxM3fmBi10
                        @Override // java.lang.Runnable
                        public final void run() {
                            ManualUploadRequestObserver.this.lambda$makeUploaderOperationsLongRunning$25$ManualUploadRequestObserver(notification);
                        }
                    }, FINAL_NOTIFICATION_POST_DELAY_MILLIS);
                    this.logger.d("ManualUploadRequestObse", "Post delayed final notification on App side.");
                }
                this.logger.i("ManualUploadRequestObse", "Successfully applied notification update.");
                return;
            }
            this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$vShlAsD7WszunSTcS2YOnE1YTZc.INSTANCE, new MetricRecordingType[0]);
            this.logger.w("ManualUploadRequestObse", "No upload bundle. Cannot make operation long-running.");
        } else {
            this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$7Q9Lu9c6i_UeZ0Ec2KuWSFlMwrA.INSTANCE, new MetricRecordingType[0]);
            this.logger.w("ManualUploadRequestObse", "No NotificationManager defined. Cannot make operation long-running.");
        }
    }

    @Override // com.amazon.photos.autosave.AutosaveEventObserver
    public void onAutosaveStateChanged(@NonNull MediaType mediaType, boolean z) {
        this.logger.v("ManualUploadRequestObse", "onAutosaveStateChanged triggered");
        this.uploaderStatus.setDiscoveryInProgress(z && mediaType == MediaType.PHOTO);
    }

    @Override // com.amazon.photos.uploader.observables.UploadSummaryObserver
    public void onChanged(@NonNull UploadSummary uploadSummary) {
        Logger logger = this.logger;
        logger.v("ManualUploadRequestObse", "onChanged upload summary = " + uploadSummary);
        updateUploaderRequestStatus(uploadSummary);
        if (wereManualUploadsAttempted() && areManualUploadsComplete(uploadSummary)) {
            onManualUploadsComplete();
        }
        if (uploaderProcessingComplete(uploadSummary)) {
            onUploaderProcessingComplete();
        } else if (areUploadsRunning(uploadSummary) && this.photosFeatureGuardian.isAutosaveFeatureAvailable()) {
            makeUploaderOperationsLongRunning(createNotification(UploadNotificationType.PROGRESS), false);
            this.uploaderStatus.setDiscoveryInProgress(false);
        } else if (!areAllUploadsBlocked(uploadSummary) || !this.photosFeatureGuardian.isAutosaveFeatureAvailable()) {
        } else {
            makeUploaderOperationsLongRunning(createNotification(UploadNotificationType.STOPPED), true);
            this.uploaderStatus.setUploadInProgress(false);
            this.uploaderStatus.setDiscoveryInProgress(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onDestroy() {
        NotificationManager notificationManager = this.notificationManager;
        if (notificationManager != null) {
            notificationManager.cancel(R.id.upload_notification_id);
        }
    }

    @Override // com.amazon.photos.autosave.AutosaveEventObserver
    public void onNoUploadsScanComplete() {
        this.logger.v("ManualUploadRequestObse", "onNoUploadsScanComplete triggered");
        this.uploaderStatus.setDiscoveryInProgress(false);
        this.uploaderStatus.setUploadInProgress(false);
        this.messagePublisher.publishMessage(PhotosUploaderEventType.UPLOAD_PROCESSING_COMPLETE, new UploadsProcessingCompleteEvent(0, 0));
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onRequestAdded(@NonNull UploadRequest uploadRequest) {
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onRequestsAbandoned(@NonNull List<AbandonedRequestInfo> list) {
        for (AbandonedRequestInfo abandonedRequestInfo : list) {
            this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$zX6Cgbc9psBPqY2S8iRi_8iMg.INSTANCE, new MetricRecordingType[0]);
            if (isManualUpload(abandonedRequestInfo.getUploadRequest())) {
                this.currentManualBatchFailureCount++;
                UploadErrorCategory errorCategory = abandonedRequestInfo.getErrorCategory();
                this.messagePublisher.publishMessage(PhotosUploaderEventType.FAILURE, new UploadFailureEvent(errorCategory.name(), abandonedRequestInfo.getReason().name(), getErrorCodeForCategory(errorCategory)));
            }
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadBlocked(@NonNull UploadRequest uploadRequest, @NonNull Blocker blocker) {
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadBlocked(@NonNull UploadRequest uploadRequest, @NonNull Set<? extends Blocker> set) {
        if (set.contains(QuotaExceededBlocker.INSTANCE)) {
            this.metrics.recordSimpleEvent("ManualUploadRequestObse", $$Lambda$ManualUploadRequestObserver$UZpdKE41YqfaLVBizqQmnCtAKcY.INSTANCE, new MetricRecordingType[0]);
            if (!isManualUpload(uploadRequest)) {
                return;
            }
            this.currentManualBatchQuotaBlockedRequests.add(Long.valueOf(uploadRequest.getId()));
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadFailed(@NonNull UploadRequest uploadRequest, @Nullable Throwable th, @NonNull UploadErrorCategory uploadErrorCategory) {
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadProgressUpdate(@NonNull UploadRequest uploadRequest, long j, long j2) {
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadStarted(@NonNull final UploadRequest uploadRequest) {
        this.uploaderStatus.setUploadInProgress(true);
        this.metrics.recordSimpleEvent("ManualUploadRequestObserver", $$Lambda$ManualUploadRequestObserver$CPUM_twyl31BmSTN6oNYLBpNc.INSTANCE, new MetricRecordingType[0]);
        this.taskManager.getExecutor(0).submit(new Runnable() { // from class: com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$Y_l5fAJPaZx-4ahgw-OTmkeG2vg
            @Override // java.lang.Runnable
            public final void run() {
                ManualUploadRequestObserver.this.lambda$onUploadStarted$3$ManualUploadRequestObserver(uploadRequest);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onUploadSucceeded(@androidx.annotation.NonNull com.amazon.photos.uploader.UploadRequest r6, @androidx.annotation.NonNull com.amazon.photos.uploader.ResultMetadata r7) {
        /*
            r5 = this;
            boolean r6 = isManualUpload(r6)
            com.amazon.clouddrive.android.core.interfaces.Metrics r0 = r5.metrics
            com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$mibKYQ4M-wk1YFjBTxbQTISHRDM r1 = com.amazon.alexa.photos.$$Lambda$ManualUploadRequestObserver$mibKYQ4Mwk1YFjBTxbQTISHRDM.INSTANCE
            r2 = 0
            com.amazon.clouddrive.android.core.interfaces.MetricRecordingType[] r3 = new com.amazon.clouddrive.android.core.interfaces.MetricRecordingType[r2]
            java.lang.String r4 = "ManualUploadRequestObserver"
            r0.recordSimpleEvent(r4, r1, r3)
            java.lang.String r0 = "NODE_ID_KEY"
            java.lang.String r0 = r7.getString(r0)
            r1 = 1
            if (r0 == 0) goto L2c
            if (r6 == 0) goto L28
            java.lang.String r3 = "IS_SUCCESSFUL_WITH_CONFLICT"
            boolean r7 = r7.getBoolean(r3, r2)
            if (r7 == 0) goto L28
            boolean r7 = r5.addNodeToPhotosAlbumSync(r0, r6)
            goto L3a
        L28:
            r5.publishSuccessMessage(r0, r6)
            goto L39
        L2c:
            com.amazon.clouddrive.android.core.interfaces.Metrics r7 = r5.metrics
            com.amazon.alexa.photos.-$$Lambda$ManualUploadRequestObserver$FapJsgHakjd5eJ2rYtqXCVsvzpY r0 = com.amazon.alexa.photos.$$Lambda$ManualUploadRequestObserver$FapJsgHakjd5eJ2rYtqXCVsvzpY.INSTANCE
            com.amazon.clouddrive.android.core.interfaces.MetricRecordingType[] r2 = new com.amazon.clouddrive.android.core.interfaces.MetricRecordingType[r2]
            r7.recordSimpleEvent(r4, r0, r2)
            r7 = 0
            r5.publishSuccessMessage(r7, r6)
        L39:
            r7 = r1
        L3a:
            if (r6 == 0) goto L49
            if (r7 == 0) goto L44
            int r6 = r5.currentManualBatchSuccessCount
            int r6 = r6 + r1
            r5.currentManualBatchSuccessCount = r6
            goto L49
        L44:
            int r6 = r5.currentManualBatchFailureCount
            int r6 = r6 + r1
            r5.currentManualBatchFailureCount = r6
        L49:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.photos.ManualUploadRequestObserver.onUploadSucceeded(com.amazon.photos.uploader.UploadRequest, com.amazon.photos.uploader.ResultMetadata):void");
    }

    @Override // com.amazon.photos.uploader.observables.UploadSummaryObserver
    public void onUploaderStarted(@NonNull UploadSummary uploadSummary) {
        if (areUploadsRunning(uploadSummary)) {
            updateUploaderRequestStatus(uploadSummary);
            this.messagePublisher.publishMessage(PhotosUploaderEventType.UPLOAD_PROGRESS_STATUS, new UploadsProgressStatusEvent(this.uploaderStatus.getCurrentUploadCount(), this.uploaderStatus.getSelectedCount()));
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadSummaryObserver
    public void onUploaderStopped(@NonNull UploadSummary uploadSummary) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: sendUploadNotification */
    public void lambda$makeUploaderOperationsLongRunning$25$ManualUploadRequestObserver(@NonNull Notification notification) {
        this.notificationManager.notify(R.id.upload_notification_id, notification);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    @WorkerThread
    /* renamed from: updateCurrentUploadNotification */
    public void lambda$onUploadStarted$3$ManualUploadRequestObserver(@NonNull UploadRequest uploadRequest) {
        try {
            this.currentNotificationUploadIcon = Glide.with(this.context).mo1635asBitmap().mo6758load(uploadRequest.getContentUri()).mo1615apply(new RequestOptions().mo1572centerCrop().mo1577disallowHardwareConfig()).submit(this.context.getResources().getDimensionPixelSize(R.dimen.notification_image_width), this.context.getResources().getDimensionPixelSize(R.dimen.notification_image_height)).get();
        } catch (Exception unused) {
            this.currentNotificationUploadIcon = null;
        }
    }

    @VisibleForTesting
    boolean uploaderProcessingComplete(@NonNull UploadSummary uploadSummary) {
        if (uploadSummary.getGlobalBlockers().size() > 0) {
            return false;
        }
        int i = 0;
        for (QueueSummary queueSummary : uploadSummary.getQueueSummaries()) {
            i += queueSummary.getCompletedRequestCount();
            if (hasPendingRequests(queueSummary)) {
                return false;
            }
        }
        return i != 0;
    }
}
