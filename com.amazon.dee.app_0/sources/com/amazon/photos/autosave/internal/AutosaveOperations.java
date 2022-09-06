package com.amazon.photos.autosave.internal;

import androidx.annotation.WorkerThread;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.photos.autosave.AutosaveFrameworkContext;
import com.amazon.photos.autosave.AutosaveManager;
import com.amazon.photos.autosave.internal.workers.AutosaveWorker;
import com.amazon.photos.autosave.internal.workers.AutosaveWorkerKt;
import com.amazon.photos.autosave.internal.workers.CancelAndRescheduleWorker;
import com.amazon.photos.autosave.internal.workers.CancelAndRescheduleWorkerKt;
import com.amazon.photos.autosave.internal.workers.CancelUploadsWorker;
import com.amazon.photos.autosave.internal.workers.CancelUploadsWorkerKt;
import com.amazon.photos.autosave.model.AutosaveBucket;
import com.amazon.photos.autosave.model.MediaType;
import com.amazon.photos.discovery.Discovery;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveOperations.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0000\u0018\u0000 +2\u00020\u0001:\u0001+B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ(\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0015\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0017J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0013H\u0002J\u001d\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u001dJ\u0015\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u001dJ\u0018\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0013H\u0002J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001b\u0010\u001f\u001a\u00020\u00152\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0000¢\u0006\u0002\b J\u0016\u0010!\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J\u0015\u0010\"\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b#J\r\u0010$\u001a\u00020\u0015H\u0001¢\u0006\u0002\b%J'\u0010&\u001a\u00020\u00152\u000e\b\u0002\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b(J\r\u0010)\u001a\u00020\u0015H\u0000¢\u0006\u0002\b*R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/amazon/photos/autosave/internal/AutosaveOperations;", "", "workManager", "Landroidx/work/WorkManager;", "autosaveFrameworkContext", "Lcom/amazon/photos/autosave/AutosaveFrameworkContext;", "discovery", "Lcom/amazon/photos/discovery/Discovery;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "(Landroidx/work/WorkManager;Lcom/amazon/photos/autosave/AutosaveFrameworkContext;Lcom/amazon/photos/discovery/Discovery;Lcom/amazon/clouddrive/android/core/interfaces/Logger;)V", "autosaveRequest", "Landroidx/work/OneTimeWorkRequest;", "itemIds", "", "", "mediaType", "Lcom/amazon/photos/autosave/model/MediaType;", "isFullScan", "", "cancelAndReschedule", "", "addToFamily", "cancelAndReschedule$AndroidPhotosAutosave_release", "cancelAndRescheduleRequest", "cancelQueuedUploads", "autosaveBucket", "Lcom/amazon/photos/autosave/model/AutosaveBucket;", "deleteBucket", "cancelQueuedUploads$AndroidPhotosAutosave_release", "cancelQueuedUploadsRequest", "cancelUploadsById", "cancelUploadsById$AndroidPhotosAutosave_release", "cancelUploadsByIdRequest", "cancelUploadsOnly", "cancelUploadsOnly$AndroidPhotosAutosave_release", "destroy", "destroy$AndroidPhotosAutosave_release", "triggerAutosave", "dedupedItemIdList", "triggerAutosave$AndroidPhotosAutosave_release", "triggerDiscoveryScan", "triggerDiscoveryScan$AndroidPhotosAutosave_release", "Companion", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveOperations {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String FULL_SCAN_TAG = "FULL_SCAN";
    @NotNull
    public static final String TAG = "AutosaveOperations";
    private final AutosaveFrameworkContext autosaveFrameworkContext;
    private final Discovery discovery;
    private final Logger logger;
    private final WorkManager workManager;

    /* compiled from: AutosaveOperations.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/photos/autosave/internal/AutosaveOperations$Companion;", "", "()V", "FULL_SCAN_TAG", "", "TAG", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AutosaveOperations(@NotNull WorkManager workManager, @NotNull AutosaveFrameworkContext autosaveFrameworkContext, @NotNull Discovery discovery, @NotNull Logger logger) {
        Intrinsics.checkParameterIsNotNull(workManager, "workManager");
        Intrinsics.checkParameterIsNotNull(autosaveFrameworkContext, "autosaveFrameworkContext");
        Intrinsics.checkParameterIsNotNull(discovery, "discovery");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.workManager = workManager;
        this.autosaveFrameworkContext = autosaveFrameworkContext;
        this.discovery = discovery;
        this.logger = logger;
    }

    private final OneTimeWorkRequest autosaveRequest(List<Long> list, MediaType mediaType, boolean z) {
        long[] longArray;
        OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(AutosaveWorker.class);
        Data.Builder putString = new Data.Builder().putString("HASHED_DIRECTED_ID_KEY", this.autosaveFrameworkContext.getHashedDirectedId$AndroidPhotosAutosave_release());
        longArray = CollectionsKt___CollectionsKt.toLongArray(list);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(AutosaveWorker.TAG);
        outline107.append(mediaType.name());
        OneTimeWorkRequest.Builder addTag = builder.setInputData(putString.putLongArray(AutosaveWorkerKt.DEDUPED_ITEM_IDS, longArray).putString(AutosaveWorkerKt.HANDLE_MEDIA_TYPE, mediaType.name()).build()).addTag(AutosaveManager.WORK_MANAGER_TAG).addTag(AutosaveWorker.TAG).addTag(outline107.toString());
        if (z) {
            addTag.addTag(FULL_SCAN_TAG);
        }
        OneTimeWorkRequest build = addTag.build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OneTimeWorkRequest.Build…\n                .build()");
        return build;
    }

    static /* synthetic */ OneTimeWorkRequest autosaveRequest$default(AutosaveOperations autosaveOperations, List list, MediaType mediaType, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            mediaType = MediaType.PHOTO;
        }
        return autosaveOperations.autosaveRequest(list, mediaType, z);
    }

    private final OneTimeWorkRequest cancelAndRescheduleRequest(boolean z) {
        OneTimeWorkRequest build = new OneTimeWorkRequest.Builder(CancelAndRescheduleWorker.class).setInputData(new Data.Builder().putString("HASHED_DIRECTED_ID_KEY", this.autosaveFrameworkContext.getHashedDirectedId$AndroidPhotosAutosave_release()).putBoolean(CancelAndRescheduleWorkerKt.ADD_TO_FAMILY, z).build()).addTag(AutosaveManager.WORK_MANAGER_TAG).addTag(CancelAndRescheduleWorker.TAG).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OneTimeWorkRequest.Build…\n                .build()");
        return build;
    }

    private final OneTimeWorkRequest cancelQueuedUploadsRequest(MediaType mediaType) {
        OneTimeWorkRequest build = new OneTimeWorkRequest.Builder(CancelUploadsWorker.class).setInputData(new Data.Builder().putString("HASHED_DIRECTED_ID_KEY", this.autosaveFrameworkContext.getHashedDirectedId$AndroidPhotosAutosave_release()).putBoolean(CancelUploadsWorkerKt.DELETE_QUEUED_ITEMS, true).putString(CancelUploadsWorkerKt.MEDIA_TYPE, mediaType.name()).build()).addTag(AutosaveManager.WORK_MANAGER_TAG).addTag(CancelUploadsWorker.TAG).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OneTimeWorkRequest.Build…\n                .build()");
        return build;
    }

    private final OneTimeWorkRequest cancelUploadsByIdRequest(List<Long> list) {
        long[] longArray;
        OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(CancelUploadsWorker.class);
        Data.Builder putString = new Data.Builder().putString("HASHED_DIRECTED_ID_KEY", this.autosaveFrameworkContext.getHashedDirectedId$AndroidPhotosAutosave_release());
        longArray = CollectionsKt___CollectionsKt.toLongArray(list);
        OneTimeWorkRequest build = builder.setInputData(putString.putLongArray(CancelUploadsWorkerKt.DELETED_ITEM_IDS, longArray).putBoolean(CancelUploadsWorkerKt.DELETE_QUEUED_ITEMS, true).build()).addTag(AutosaveManager.WORK_MANAGER_TAG).addTag(CancelUploadsWorker.TAG).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OneTimeWorkRequest.Build…\n                .build()");
        return build;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void triggerAutosave$AndroidPhotosAutosave_release$default(AutosaveOperations autosaveOperations, List list, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            list = CollectionsKt__CollectionsKt.emptyList();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        autosaveOperations.triggerAutosave$AndroidPhotosAutosave_release(list, z);
    }

    public final void cancelAndReschedule$AndroidPhotosAutosave_release(boolean z) {
        Logger logger = this.logger;
        logger.i(TAG, "Cancel and reschedule, addToFamily: " + z + '.');
        Operation cancelAllWorkByTag = this.workManager.cancelAllWorkByTag(AutosaveWorker.TAG);
        Intrinsics.checkExpressionValueIsNotNull(cancelAllWorkByTag, "workManager.cancelAllWorkByTag(AutosaveWorker.TAG)");
        cancelAllWorkByTag.getResult().get();
        this.workManager.enqueueUniqueWork(CancelAndRescheduleWorkerKt.AUTOSAVE_CANCEL_RESCHEDULE_UNIQUE_NAME, ExistingWorkPolicy.APPEND, cancelAndRescheduleRequest(z));
    }

    public final void cancelQueuedUploads$AndroidPhotosAutosave_release(@NotNull MediaType mediaType) {
        Intrinsics.checkParameterIsNotNull(mediaType, "mediaType");
        Logger logger = this.logger;
        logger.i(TAG, "Cancel Queued Uploads for " + mediaType + '.');
        WorkManager workManager = this.workManager;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(AutosaveWorker.TAG);
        outline107.append(mediaType.name());
        Operation cancelAllWorkByTag = workManager.cancelAllWorkByTag(outline107.toString());
        Intrinsics.checkExpressionValueIsNotNull(cancelAllWorkByTag, "workManager.cancelAllWor…ker.TAG + mediaType.name)");
        cancelAllWorkByTag.getResult().get();
        this.workManager.enqueueUniqueWork(CancelUploadsWorkerKt.AUTOSAVE_CANCEL_UPLOADS_UNIQUE_NAME, ExistingWorkPolicy.APPEND, cancelQueuedUploadsRequest(mediaType));
    }

    public final void cancelUploadsById$AndroidPhotosAutosave_release(@NotNull List<Long> itemIds) {
        Intrinsics.checkParameterIsNotNull(itemIds, "itemIds");
        Logger logger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cancel Queued Uploads for id list with ");
        outline107.append(itemIds.size());
        outline107.append(" items.");
        logger.i(TAG, outline107.toString());
        this.workManager.enqueueUniqueWork(CancelUploadsWorkerKt.AUTOSAVE_CANCEL_UPLOADS_UNIQUE_NAME, ExistingWorkPolicy.APPEND, cancelUploadsByIdRequest(itemIds));
    }

    public final void cancelUploadsOnly$AndroidPhotosAutosave_release(@NotNull MediaType mediaType) {
        Intrinsics.checkParameterIsNotNull(mediaType, "mediaType");
        Logger logger = this.logger;
        logger.i(TAG, "Cancel Queued Uploads only for " + mediaType + '.');
        this.workManager.enqueueUniqueWork(CancelUploadsWorkerKt.AUTOSAVE_CANCEL_UPLOADS_UNIQUE_NAME, ExistingWorkPolicy.APPEND, cancelQueuedUploadsRequest(mediaType));
    }

    @WorkerThread
    public final void destroy$AndroidPhotosAutosave_release() {
        Operation cancelAllWorkByTag = this.workManager.cancelAllWorkByTag(AutosaveManager.WORK_MANAGER_TAG);
        Intrinsics.checkExpressionValueIsNotNull(cancelAllWorkByTag, "workManager.cancelAllWor…Manager.WORK_MANAGER_TAG)");
        cancelAllWorkByTag.getResult().get();
        this.workManager.pruneWork();
    }

    public final void triggerAutosave$AndroidPhotosAutosave_release(@NotNull List<Long> dedupedItemIdList, boolean z) {
        Intrinsics.checkParameterIsNotNull(dedupedItemIdList, "dedupedItemIdList");
        Logger logger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Autosave triggered, given id count: ");
        outline107.append(dedupedItemIdList.size());
        outline107.append(", isFullScan: ");
        outline107.append(z);
        outline107.append('.');
        logger.i(TAG, outline107.toString());
        this.workManager.enqueueUniqueWork(AutosaveWorkerKt.AUTOSAVE_CHAIN_PHOTOS_UNIQUE_NAME, ExistingWorkPolicy.APPEND, autosaveRequest(dedupedItemIdList, MediaType.PHOTO, z));
        this.workManager.enqueueUniqueWork(AutosaveWorkerKt.AUTOSAVE_CHAIN_VIDEOS_UNIQUE_NAME, ExistingWorkPolicy.APPEND, autosaveRequest(dedupedItemIdList, MediaType.VIDEO, z));
    }

    public final void triggerDiscoveryScan$AndroidPhotosAutosave_release() {
        this.logger.i(TAG, "Discovery full scan triggered from Autosave.");
        this.discovery.getOperations().rescan();
    }

    public final void cancelQueuedUploads$AndroidPhotosAutosave_release(@NotNull AutosaveBucket autosaveBucket, boolean z) {
        Intrinsics.checkParameterIsNotNull(autosaveBucket, "autosaveBucket");
        Logger logger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cancel Queued Uploads for bucket ");
        outline107.append(autosaveBucket.getId());
        outline107.append(", delete bucket: ");
        outline107.append(z);
        outline107.append('.');
        logger.i(TAG, outline107.toString());
        this.workManager.enqueueUniqueWork(CancelUploadsWorkerKt.AUTOSAVE_CANCEL_UPLOADS_UNIQUE_NAME, ExistingWorkPolicy.APPEND, cancelQueuedUploadsRequest(autosaveBucket, z));
    }

    private final OneTimeWorkRequest cancelQueuedUploadsRequest(AutosaveBucket autosaveBucket, boolean z) {
        OneTimeWorkRequest build = new OneTimeWorkRequest.Builder(CancelUploadsWorker.class).setInputData(new Data.Builder().putString("HASHED_DIRECTED_ID_KEY", this.autosaveFrameworkContext.getHashedDirectedId$AndroidPhotosAutosave_release()).putBoolean(CancelUploadsWorkerKt.DELETE_QUEUED_ITEMS, true).putString(CancelUploadsWorkerKt.AUTOSAVE_FOLDER_PATH, autosaveBucket.getBucketPath()).putBoolean(CancelUploadsWorkerKt.DELETE_AUTOSAVE_BUCKET, z).build()).addTag(AutosaveManager.WORK_MANAGER_TAG).addTag(CancelUploadsWorker.TAG).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OneTimeWorkRequest.Build…\n                .build()");
        return build;
    }
}
