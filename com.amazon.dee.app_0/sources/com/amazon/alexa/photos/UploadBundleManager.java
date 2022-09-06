package com.amazon.alexa.photos;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.photos.hva.HVAEvent;
import com.amazon.alexa.photos.metrics.CloudDriveMetrics;
import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.alexa.photos.uploadV2.CdaSdkMetrics;
import com.amazon.alexa.photos.uploadV2.CdaSdkPreferences;
import com.amazon.alexa.photos.uploadV2.CloudDriveClientProvider;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.auth.AuthenticatedURLConnectionFactory;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.photos.autosave.AutosavePreferences;
import com.amazon.photos.autosave.model.MediaType;
import com.amazon.photos.uploadbundle.UploadBundle;
import com.amazon.photos.uploadbundle.UploadBundleBuilder;
import com.amazon.photos.uploader.PauseResume;
import com.amazon.photos.uploader.Priority;
import com.amazon.photos.uploader.Queue;
import com.amazon.photos.uploader.UploadManager;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.blockers.LowBatteryBlocker;
import com.amazon.photos.uploader.blockers.MeteredConnectionBlocker;
import com.amazon.photos.uploader.blockers.NoNetworkBlocker;
import com.amazon.photos.uploader.blockers.PauseBlocker;
import com.amazon.photos.uploader.blockers.QuotaExceededBlocker;
import com.amazon.photos.uploader.blockers.StoragePermissionBlocker;
import com.amazon.photos.uploader.customblockers.BlockerReevaluator;
import com.amazon.photos.uploader.dao.BlockerDao;
import com.amazon.photos.uploader.log.UploadLogger;
import com.amazon.photos.uploader.observables.QueueSummary;
import com.amazon.photos.uploader.observables.UploadSummary;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import dagger.Lazy;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.security.auth.DestroyFailedException;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes9.dex */
public class UploadBundleManager {
    public static final String MANUAL_QUEUE = "ALEXA_MANUAL_QUEUE";
    private static final String TAG = "UploadBundleManager";
    public static final int UPLOAD_SETTINGS_NOTIFICATION_INTENT = 123213132;
    @NonNull
    private final PhotosAppInfoProvider appInfoProvider;
    @NonNull
    private final CloudDriveClientProvider cdClientProvider;
    @NonNull
    private final Context context;
    @NonNull
    private final LazyComponent<EventBus> eventBus;
    @NonNull
    private final LazyComponent<IdentityService> identityService;
    @NonNull
    private final Logger logger;
    private ManualUploadRequestObserver manualUploadRequestObserver;
    @NonNull
    private final MessagePublisher messagePublisher;
    @NonNull
    private final CloudDriveMetrics metrics;
    @NonNull
    private final Class<?> notificationDeepLinkLauncherClass;
    @VisibleForTesting
    MultiFilterSubscriber pauseBundleEventSubscriber;
    @NonNull
    private final Lazy<PhotosFeatureGuardian> photosFeatureGuardian;
    @VisibleForTesting
    MultiFilterSubscriber resumeBundleEventSubcriber;
    @NonNull
    private final LazyComponent<TaskManager> taskManager;
    @Nullable
    @VisibleForTesting
    public UploadBundle uploadBundle;
    @VisibleForTesting
    UploaderStatus uploaderStatus;
    @NonNull
    private final Lazy<? extends AuthenticatedURLConnectionFactory> urlConnectionFactory;

    /* loaded from: classes9.dex */
    public static class UploaderStatus {
        private int currentUploadCount;
        private boolean hasFailedAuthentication;
        private boolean hasInsufficientNetwork;
        private boolean hasLowBattery;
        private boolean hasNoPermission;
        private boolean hasNoWifi;
        private boolean isAutoSaveEnabled;
        private boolean isCellularDataEnabled;
        private boolean isDiscoveryInProgress;
        private boolean isExternallyPaused;
        private boolean isRemotelyDisabled;
        private boolean isUploadInProgress;
        private boolean nearPhotoQuota;
        private boolean outOfPhotoQuota;
        private int selectedCount;

        public int getCurrentUploadCount() {
            return this.currentUploadCount;
        }

        public int getSelectedCount() {
            return this.selectedCount;
        }

        public boolean isAutoSaveEnabled() {
            return this.isAutoSaveEnabled;
        }

        public boolean isCellularDataEnabled() {
            return this.isCellularDataEnabled;
        }

        public boolean isDiscoveryInProgress() {
            return this.isDiscoveryInProgress;
        }

        public boolean isExternallyPaused() {
            return this.isExternallyPaused;
        }

        public boolean isHasInsufficientNetwork() {
            return this.hasInsufficientNetwork;
        }

        public boolean isHasLowBattery() {
            return this.hasLowBattery;
        }

        public boolean isHasNoPermission() {
            return this.hasNoPermission;
        }

        public boolean isHasNoWifi() {
            return this.hasNoWifi;
        }

        public boolean isOutOfPhotoQuota() {
            return this.outOfPhotoQuota;
        }

        public boolean isRemotelyDisabled() {
            return this.isRemotelyDisabled;
        }

        public boolean isUploadInProgress() {
            return this.isUploadInProgress;
        }

        public void setAutoSaveEnabled(boolean z) {
            this.isAutoSaveEnabled = z;
        }

        public void setCellularDataEnabled(boolean z) {
            this.isCellularDataEnabled = z;
        }

        public void setCurrentUploadCount(int i) {
            this.currentUploadCount = i;
        }

        public void setDiscoveryInProgress(boolean z) {
            this.isDiscoveryInProgress = z;
        }

        public void setExternallyPaused(boolean z) {
            this.isExternallyPaused = z;
        }

        public void setHasInsufficientNetwork(boolean z) {
            this.hasInsufficientNetwork = z;
        }

        public void setHasLowBattery(boolean z) {
            this.hasLowBattery = z;
        }

        public void setHasNoPermission(boolean z) {
            this.hasNoPermission = z;
        }

        public void setHasNoWifi(boolean z) {
            this.hasNoWifi = z;
        }

        public void setOutOfPhotoQuota(boolean z) {
            this.outOfPhotoQuota = z;
        }

        public void setRemotelyDisabled(boolean z) {
            this.isRemotelyDisabled = z;
        }

        public void setSelectedCount(int i) {
            this.selectedCount = i;
        }

        public void setUploadInProgress(boolean z) {
            this.isUploadInProgress = z;
        }

        @NonNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UploaderStatus{isDiscoveryInProgress=");
            outline107.append(this.isDiscoveryInProgress);
            outline107.append(", isUploadInProgress=");
            outline107.append(this.isUploadInProgress);
            outline107.append(", selectedCount=");
            outline107.append(this.selectedCount);
            outline107.append(", currentUploadCount=");
            outline107.append(this.currentUploadCount);
            outline107.append(", isExternallyPaused=");
            outline107.append(this.isExternallyPaused);
            outline107.append(", isRemotelyDisabled=");
            outline107.append(this.isRemotelyDisabled);
            outline107.append(", hasFailedAuthentication=");
            outline107.append(this.hasFailedAuthentication);
            outline107.append(", hasNoPermission=");
            outline107.append(this.hasNoPermission);
            outline107.append(", hasNoWifi=");
            outline107.append(this.hasNoWifi);
            outline107.append(", hasLowBattery=");
            outline107.append(this.hasLowBattery);
            outline107.append(", hasInsufficientNetwork=");
            outline107.append(this.hasInsufficientNetwork);
            outline107.append(", nearPhotoQuota=");
            outline107.append(this.nearPhotoQuota);
            outline107.append(", outOfPhotoQuota=");
            outline107.append(this.outOfPhotoQuota);
            outline107.append(", isAutoSaveEnabled=");
            outline107.append(this.isAutoSaveEnabled);
            outline107.append(", isCellularDataEnabled=");
            outline107.append(this.isCellularDataEnabled);
            outline107.append(JsonReaderKt.END_OBJ);
            return outline107.toString();
        }
    }

    public UploadBundleManager(@NonNull Context context, @NonNull PhotosAppInfoProvider photosAppInfoProvider, @NonNull final ComponentRegistry componentRegistry, @NonNull Class<?> cls, @NonNull Lazy<? extends AuthenticatedURLConnectionFactory> lazy, @NonNull Lazy<PhotosFeatureGuardian> lazy2) {
        this(context, photosAppInfoProvider, new CloudDriveMetrics(new Lazy() { // from class: com.amazon.alexa.photos.-$$Lambda$UploadBundleManager$YqW6mzRJ_z5K8irLswZdJANkkRc
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return UploadBundleManager.lambda$new$0(ComponentRegistry.this);
            }
        }), componentRegistry.getLazy(EventBus.class), componentRegistry, cls, lazy, lazy2, new PhotosLogger());
    }

    private void invalidateBlockers(@NonNull UploadBundle uploadBundle) {
        uploadBundle.getUploadManager().getOperations().invalidateBlockers();
    }

    private boolean isActiveQueue(@NonNull QueueSummary queueSummary) {
        return queueSummary.getQueuedRequestCount() > 0 || queueSummary.getRunningRequestCount() > 0 || queueSummary.getBlockedRequestCount() > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Mobilytics lambda$new$0(ComponentRegistry componentRegistry) {
        return (Mobilytics) componentRegistry.getLazy(Mobilytics.class).mo10268get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$updateQueueBlockers$1(Queue queue) {
        return queue != null ? queue.getName() : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reevaluateBlockers() {
        UploadBundle uploadBundle = getUploadBundle();
        UploadManager uploadManager = uploadBundle != null ? uploadBundle.getUploadManager() : null;
        if (uploadManager == null) {
            this.metrics.recordEvent(TAG, PhotosMetricsConstants.UPLOAD_MANAGER_NULL_REEVALUATE_BLOCKERS);
            return;
        }
        this.logger.d(TAG, "Re-evaluating blockers");
        uploadManager.getOperations().reevaluteBlockers();
    }

    private void setupObservers(@NonNull UploadBundle uploadBundle) {
        this.uploaderStatus = new UploaderStatus();
        this.manualUploadRequestObserver = createManualUploadRequestObserver(this.uploaderStatus);
        Scheduler.Worker createWorker = Schedulers.io().createWorker();
        uploadBundle.getUploadManager().getUploadSummaryObservable().addObserver(this.manualUploadRequestObserver, createWorker);
        uploadBundle.getUploadManager().getUploadRequestObservable().addObserver(this.manualUploadRequestObserver, createWorker);
        uploadBundle.getAutosaveManager().addAutosaveEventObserver(this.manualUploadRequestObserver, Schedulers.computation());
        this.pauseBundleEventSubscriber = this.eventBus.mo10268get().getSubscriber();
        this.pauseBundleEventSubscriber.subscribeFilter(new EventTypeMessageFilter(HVAEvent.STARTED_OUT_BOUND.getValue()), new MessageHandler() { // from class: com.amazon.alexa.photos.-$$Lambda$MT-hcVDOq4luhMUvDovPRfmjrvA
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                UploadBundleManager.this.onHvaStartedPauseBundle(message);
            }
        });
        this.resumeBundleEventSubcriber = this.eventBus.mo10268get().getSubscriber();
        this.resumeBundleEventSubcriber.subscribeFilter(new EventTypeMessageFilter(HVAEvent.ENDED_OUT_BOUND.getValue()), new MessageHandler() { // from class: com.amazon.alexa.photos.-$$Lambda$Dli1w-arh8l8Y-tQvUZ7x8zR3C8
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                UploadBundleManager.this.onHvaEndedResumeBundle(message);
            }
        });
    }

    private void updateLowBatteryStatus(@NonNull UploadSummary uploadSummary, @NonNull Map<String, List<Blocker>> map, @NonNull UploaderStatus uploaderStatus) {
        for (QueueSummary queueSummary : uploadSummary.getQueueSummaries()) {
            List<Blocker> list = map.get(queueSummary.getQueue().getName());
            if (isActiveQueue(queueSummary) && list != null && list.contains(LowBatteryBlocker.INSTANCE)) {
                uploaderStatus.setHasLowBattery(true);
                return;
            }
        }
        uploaderStatus.setHasLowBattery(false);
    }

    private void updateNoWifiStatus(@NonNull UploadSummary uploadSummary, @NonNull Map<String, List<Blocker>> map, @NonNull UploaderStatus uploaderStatus) {
        for (QueueSummary queueSummary : uploadSummary.getQueueSummaries()) {
            List<Blocker> list = map.get(queueSummary.getQueue().getName());
            if (isActiveQueue(queueSummary) && list != null && list.contains(MeteredConnectionBlocker.INSTANCE)) {
                uploaderStatus.setHasNoWifi(true);
                return;
            }
        }
        uploaderStatus.setHasNoWifi(false);
    }

    private void updateUploaderBlockers(@NonNull UploaderStatus uploaderStatus, @NonNull UploadManager uploadManager) {
        BlockerDao blockerDao = uploadManager.getDaos().getBlockerDao();
        updateGlobalBlockers(uploaderStatus, blockerDao);
        updateQueueBlockers(uploaderStatus, blockerDao, uploadManager.getQueueManager().getQueues());
        updateRequestBlockers(uploaderStatus, blockerDao);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean areUploadsInProgress() {
        ManualUploadRequestObserver manualUploadRequestObserver = this.manualUploadRequestObserver;
        return manualUploadRequestObserver != null && manualUploadRequestObserver.areUploadsInProgress();
    }

    @WorkerThread
    public synchronized void createAndSaveBundle() {
        if (this.uploadBundle == null) {
            UserIdentity user = this.identityService.mo10268get().getUser(TAG);
            String directedId = user != null ? user.getDirectedId() : null;
            if (!Strings.isNullOrEmpty(directedId)) {
                this.uploadBundle = createUploaderBundle(directedId);
                setupObservers(this.uploadBundle);
                invalidateBlockers(this.uploadBundle);
            } else {
                this.metrics.recordEvent(TAG, PhotosMetricsConstants.UPLOAD_BUNDLE_ACCOUNT_ID_MISSING_ON_INIT);
                this.logger.e(TAG, "Account id unavailable on Upload Bundle initialization");
            }
        }
    }

    @VisibleForTesting
    ManualUploadRequestObserver createManualUploadRequestObserver(@NonNull UploaderStatus uploaderStatus) {
        return new ManualUploadRequestObserver(this.context, this.messagePublisher, new CdsRequestHelperV2(getCDClient(), this.metrics), getCDClient(), this.metrics, this, uploaderStatus, this.notificationDeepLinkLauncherClass, this.taskManager.mo10268get(), this.photosFeatureGuardian.mo358get(), this.logger);
    }

    @VisibleForTesting
    UploadBundle createUploaderBundle(@NonNull String str) throws IllegalArgumentException {
        return new UploadBundleBuilder(this.metrics, getCDClient(), this.logger, str, this.context, this.appInfoProvider.getApplicationId(), this.appInfoProvider.getApplicationName()).addQueue(new Queue(MANUAL_QUEUE, Priority.HIGH, Collections.emptySet())).maxParallelUploads(1).urlConnectionProvider(new UploadBundleUrlConnectionProvider(this.appInfoProvider, this.urlConnectionFactory.mo358get())).blockerReevaluator(new BlockerReevaluator() { // from class: com.amazon.alexa.photos.-$$Lambda$UploadBundleManager$tpl4Z8mj79xkpocRZTiPKzDEuVk
            @Override // com.amazon.photos.uploader.customblockers.BlockerReevaluator
            public final void reevaluateBlockers() {
                UploadBundleManager.this.reevaluateBlockers();
            }
        }).useDiscoveryDailyMonitor(false).onlyScanCameraFolders(true).loggingSecurityLevel(UploadLogger.SecurityLevel.OBFUSCATED).build();
    }

    @WorkerThread
    public synchronized void destroyUploaderBundle() {
        if (this.uploadBundle != null && !this.uploadBundle.isDestroyed()) {
            UploadManager uploadManager = this.uploadBundle.getUploadManager();
            if (this.manualUploadRequestObserver != null) {
                uploadManager.getUploadRequestObservable().removeObserver(this.manualUploadRequestObserver);
                uploadManager.getUploadSummaryObservable().removeObserver(this.manualUploadRequestObserver);
                this.uploadBundle.getAutosaveManager().removeAutosaveEventObserver(this.manualUploadRequestObserver);
                this.manualUploadRequestObserver.onDestroy();
                this.manualUploadRequestObserver = null;
            }
            if (this.pauseBundleEventSubscriber != null) {
                this.eventBus.mo10268get().unsubscribe(this.pauseBundleEventSubscriber);
                this.pauseBundleEventSubscriber = null;
            }
            if (this.resumeBundleEventSubcriber != null) {
                this.eventBus.mo10268get().unsubscribe(this.resumeBundleEventSubcriber);
                this.resumeBundleEventSubcriber = null;
            }
            try {
                this.uploadBundle.destroy();
                this.uploadBundle = null;
            } catch (DestroyFailedException unused) {
                this.logger.e(TAG, "Destroy Upload Bundle failed.");
                this.metrics.recordEvent(TAG, PhotosMetricsConstants.UPLOAD_BUNDLE_DESTROY_ERROR);
            }
        }
        if (this.uploadBundle == null) {
            this.cdClientProvider.destroy();
        }
    }

    @VisibleForTesting
    CDClient getCDClient() {
        return this.cdClientProvider.getCDClient();
    }

    @Nullable
    public UploaderStatus getCurrentUploaderStatus() {
        updateStatus();
        Logger logger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getCurrentUploaderStatus status = ");
        outline107.append(this.uploaderStatus);
        logger.v(TAG, outline107.toString());
        return this.uploaderStatus;
    }

    @Nullable
    public UploadBundle getUploadBundle() {
        return this.uploadBundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public synchronized void onHvaEndedResumeBundle(@NonNull Message message) {
        UploadBundle uploadBundle = getUploadBundle();
        if (uploadBundle != null) {
            this.logger.d(TAG, "onHvaEndedResumeBundle");
            this.metrics.recordEvent(TAG, PhotosMetricsConstants.HVA_UPLOAD_BUNDLE_RESUMED);
            if (uploadBundle.getUploadManager().getOperations().getPauseResumeState() == PauseResume.RESUME) {
                this.logger.w(TAG, "onHvaEndedResumeBundle Uploader is already resumed");
                this.metrics.recordEvent(TAG, PhotosMetricsConstants.HVA_UPLOAD_BUNDLE_ALREADY_RESUMED);
            }
            if (!uploadBundle.getAutosaveManager().getPreferences().isAutosaveEnabled(MediaType.PHOTO) && !uploadBundle.getAutosaveManager().getPreferences().isAutosaveEnabled(MediaType.VIDEO)) {
                uploadBundle.getUploadManager().getOperations().resumeAllUploads().waitForResult();
            }
            uploadBundle.resumeAllWork();
        } else {
            this.logger.w(TAG, "onHvaEndedResumeBundle Null Upload Bundle");
            this.metrics.recordEvent(TAG, PhotosMetricsConstants.HVA_EVENT_END_NULL_UPLOAD_BUNDLE);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public synchronized void onHvaStartedPauseBundle(@NonNull Message message) {
        UploadBundle uploadBundle = getUploadBundle();
        if (uploadBundle != null) {
            this.logger.d(TAG, "onHvaStartedPauseBundle");
            if (uploadBundle.getUploadManager().getOperations().getPauseResumeState() == PauseResume.PAUSE) {
                this.logger.w(TAG, "onHvaStartedPauseBundle Uploader is already paused");
                this.metrics.recordEvent(TAG, PhotosMetricsConstants.HVA_UPLOAD_BUNDLE_ALREADY_PAUSED);
            }
            this.metrics.recordEvent(TAG, PhotosMetricsConstants.HVA_UPLOAD_BUNDLE_PAUSED);
            uploadBundle.pauseAllWork();
        } else {
            this.logger.w(TAG, "onHvaStartedPauseBundle Null Upload Bundle");
            this.metrics.recordEvent(TAG, PhotosMetricsConstants.HVA_EVENT_START_NULL_UPLOAD_BUNDLE);
        }
    }

    @VisibleForTesting
    void updateGlobalBlockers(@NonNull UploaderStatus uploaderStatus, @NonNull BlockerDao blockerDao) {
        HashSet newHashSet = Sets.newHashSet(blockerDao.getGlobalBlockers());
        uploaderStatus.setExternallyPaused(newHashSet.contains(PauseBlocker.INSTANCE));
        uploaderStatus.setHasInsufficientNetwork(newHashSet.contains(NoNetworkBlocker.INSTANCE));
        uploaderStatus.setHasNoPermission(newHashSet.contains(StoragePermissionBlocker.INSTANCE));
    }

    @VisibleForTesting
    void updateQueueBlockers(@NonNull UploaderStatus uploaderStatus, @NonNull BlockerDao blockerDao, @NonNull Collection<Queue> collection) {
        Map<String, List<Blocker>> blockersForQueues = blockerDao.getBlockersForQueues(Sets.newHashSet(Collections2.transform(collection, $$Lambda$UploadBundleManager$Gpg1OKLV_ac7lHN_5luSl4JzB4M.INSTANCE)));
        UploadSummary uploadSummary = blockerDao.getUploadSummary();
        updateNoWifiStatus(uploadSummary, blockersForQueues, uploaderStatus);
        updateLowBatteryStatus(uploadSummary, blockersForQueues, uploaderStatus);
    }

    @VisibleForTesting
    void updateRequestBlockers(@NonNull UploaderStatus uploaderStatus, @NonNull BlockerDao blockerDao) {
        HashSet hashSet = new HashSet();
        for (QueueSummary queueSummary : blockerDao.getUploadSummary().getQueueSummaries()) {
            hashSet.addAll(queueSummary.getRequestBlockers());
        }
        uploaderStatus.setOutOfPhotoQuota(hashSet.contains(QuotaExceededBlocker.INSTANCE));
    }

    @VisibleForTesting
    void updateStatus() {
        UploadBundle uploadBundle;
        if (this.uploaderStatus == null || (uploadBundle = getUploadBundle()) == null) {
            return;
        }
        AutosavePreferences preferences = uploadBundle.getAutosaveManager().getPreferences();
        boolean z = false;
        boolean z2 = preferences.isAutosaveEnabled(MediaType.PHOTO) || preferences.isAutosaveEnabled(MediaType.VIDEO);
        if (preferences.isAutosaveAllowedOnMetered(MediaType.PHOTO) || preferences.isAutosaveAllowedOnMetered(MediaType.VIDEO)) {
            z = true;
        }
        this.uploaderStatus.setAutoSaveEnabled(z2);
        this.uploaderStatus.setCellularDataEnabled(z);
        if (!z2) {
            return;
        }
        updateUploaderBlockers(this.uploaderStatus, uploadBundle.getUploadManager());
    }

    public UploadBundleManager(@NonNull Context context, @NonNull PhotosAppInfoProvider photosAppInfoProvider, @NonNull Lazy<CloudDriveMetrics> lazy, @NonNull ComponentRegistry componentRegistry, @NonNull Class<?> cls, @NonNull Lazy<? extends AuthenticatedURLConnectionFactory> lazy2, @NonNull Lazy<PhotosFeatureGuardian> lazy3) {
        this(context, photosAppInfoProvider, lazy.mo358get(), componentRegistry.getLazy(EventBus.class), componentRegistry, cls, lazy2, lazy3, new PhotosLogger());
    }

    private UploadBundleManager(@NonNull Context context, @NonNull PhotosAppInfoProvider photosAppInfoProvider, @NonNull CloudDriveMetrics cloudDriveMetrics, @NonNull LazyComponent<EventBus> lazyComponent, @NonNull ComponentRegistry componentRegistry, @NonNull Class<?> cls, @NonNull Lazy<? extends AuthenticatedURLConnectionFactory> lazy, @NonNull Lazy<PhotosFeatureGuardian> lazy2, @NonNull Logger logger) {
        this(context, photosAppInfoProvider, componentRegistry.getLazy(IdentityService.class), new MessagePublisher(lazyComponent), new CdaSdkPreferences(context, cloudDriveMetrics, logger), new CdaSdkMetrics(cloudDriveMetrics), cloudDriveMetrics, lazyComponent, cls, lazy, componentRegistry.getLazy(TaskManager.class), lazy2, logger);
    }

    @VisibleForTesting
    UploadBundleManager(@NonNull Context context, @NonNull PhotosAppInfoProvider photosAppInfoProvider, @NonNull LazyComponent<IdentityService> lazyComponent, @NonNull MessagePublisher messagePublisher, @NonNull CdaSdkPreferences cdaSdkPreferences, @NonNull CdaSdkMetrics cdaSdkMetrics, @NonNull CloudDriveMetrics cloudDriveMetrics, @NonNull LazyComponent<EventBus> lazyComponent2, @NonNull Class<?> cls, @NonNull Lazy<? extends AuthenticatedURLConnectionFactory> lazy, @NonNull LazyComponent<TaskManager> lazyComponent3, @NonNull Lazy<PhotosFeatureGuardian> lazy2, @NonNull Logger logger) {
        this.identityService = lazyComponent;
        this.messagePublisher = messagePublisher;
        this.context = context;
        this.metrics = cloudDriveMetrics;
        this.appInfoProvider = photosAppInfoProvider;
        this.cdClientProvider = new CloudDriveClientProvider(context, photosAppInfoProvider, lazyComponent, cdaSdkPreferences, cdaSdkMetrics, cloudDriveMetrics);
        this.eventBus = lazyComponent2;
        this.notificationDeepLinkLauncherClass = cls;
        this.urlConnectionFactory = lazy;
        this.taskManager = lazyComponent3;
        this.photosFeatureGuardian = lazy2;
        this.logger = logger;
    }
}
