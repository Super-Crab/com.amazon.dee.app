package com.amazon.photos.uploader;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.WorkManager;
import com.amazon.photos.uploader.internal.workers.BlockerEvaluatorWorker;
import com.amazon.photos.uploader.internal.workers.SchedulerWorker;
import com.amazon.photos.uploader.internal.workers.SchedulerWorkerKt;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SchedulingCallback.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u001f\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\t\u001a\u00020\nJ\u0017\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0002\b\rJ\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\rJ\u001f\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0002\b\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/photos/uploader/SchedulingCallback;", "", "uploadFrameworkContext", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "workManager", "Landroidx/work/WorkManager;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "(Lcom/amazon/photos/uploader/UploadFrameworkContext;Landroidx/work/WorkManager;Lcom/amazon/photos/uploader/log/UploadLogger;)V", "reevaluateBlockers", "Landroidx/work/Operation;", "constraints", "Landroidx/work/Constraints;", "reevaluateBlockers$AndroidPhotosUploader_release", "delay", "", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SchedulingCallback {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "SchedulingCallback";
    private final UploadLogger logger;
    private final UploadFrameworkContext uploadFrameworkContext;
    private final WorkManager workManager;

    /* compiled from: SchedulingCallback.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/SchedulingCallback$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SchedulingCallback(@NotNull UploadFrameworkContext uploadFrameworkContext, @NotNull WorkManager workManager, @NotNull UploadLogger logger) {
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "uploadFrameworkContext");
        Intrinsics.checkParameterIsNotNull(workManager, "workManager");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.uploadFrameworkContext = uploadFrameworkContext;
        this.workManager = workManager;
        this.logger = logger;
    }

    @NotNull
    public final Operation reevaluateBlockers() {
        this.logger.i$AndroidPhotosUploader_release(TAG, "reevaluateBlockers with no delay and no constraints.");
        return reevaluateBlockers$AndroidPhotosUploader_release(0L, null);
    }

    @NotNull
    public final Operation reevaluateBlockers$AndroidPhotosUploader_release(@Nullable Constraints constraints) {
        UploadLogger uploadLogger = this.logger;
        uploadLogger.i$AndroidPhotosUploader_release(TAG, "reevaluateBlockers with " + constraints + '.');
        return reevaluateBlockers$AndroidPhotosUploader_release(0L, constraints);
    }

    @NotNull
    public final Operation reevaluateBlockers$AndroidPhotosUploader_release(long j) {
        this.logger.i$AndroidPhotosUploader_release(TAG, GeneratedOutlineSupport1.outline57("reevaluateBlockers with ", j, " ms delay."));
        return reevaluateBlockers$AndroidPhotosUploader_release(j, null);
    }

    @NotNull
    public final Operation reevaluateBlockers$AndroidPhotosUploader_release(long j, @Nullable Constraints constraints) {
        UploadLogger uploadLogger = this.logger;
        uploadLogger.i$AndroidPhotosUploader_release(TAG, "reevaluateBlockers with " + j + " ms delay and " + constraints + " constraints.");
        Data build = new Data.Builder().putString("HASHED_DIRECTED_ID_KEY", this.uploadFrameworkContext.getHashedDirectedId()).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Data.Builder()\n         …hashedDirectedId).build()");
        OneTimeWorkRequest.Builder addTag = new OneTimeWorkRequest.Builder(BlockerEvaluatorWorker.class).setInputData(build).setInitialDelay(j, TimeUnit.MILLISECONDS).addTag(UploadManager.WORK_MANAGER_TAG);
        Intrinsics.checkExpressionValueIsNotNull(addTag, "OneTimeWorkRequest\n     …Manager.WORK_MANAGER_TAG)");
        OneTimeWorkRequest.Builder builder = addTag;
        if (constraints != null) {
            builder.setConstraints(constraints);
        }
        OneTimeWorkRequest build2 = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(build2, "evaluatorRequestBuilder.build()");
        OneTimeWorkRequest oneTimeWorkRequest = build2;
        OneTimeWorkRequest build3 = new OneTimeWorkRequest.Builder(SchedulerWorker.class).addTag(UploadManager.WORK_MANAGER_TAG).addTag(SchedulerWorkerKt.SCHEDULE_TAG).setInputData(build).build();
        Intrinsics.checkExpressionValueIsNotNull(build3, "OneTimeWorkRequest.Build…utData(inputData).build()");
        OneTimeWorkRequest oneTimeWorkRequest2 = build3;
        ExistingWorkPolicy existingWorkPolicy = constraints == null ? ExistingWorkPolicy.REPLACE : ExistingWorkPolicy.APPEND;
        UploadLogger uploadLogger2 = this.logger;
        uploadLogger2.i$AndroidPhotosUploader_release(TAG, "reevaluateBlockers begin unique work using work policy " + existingWorkPolicy + '.');
        Operation enqueue = this.workManager.beginUniqueWork(SchedulingCallbackKt.SCHEDULER_CHAIN_UNIQUE_NAME, existingWorkPolicy, oneTimeWorkRequest).then(oneTimeWorkRequest2).enqueue();
        Intrinsics.checkExpressionValueIsNotNull(enqueue, "workManager.beginUniqueW…               .enqueue()");
        return enqueue;
    }
}
