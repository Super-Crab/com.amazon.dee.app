package com.amazon.photos.uploader.internal.workers;

import android.content.Context;
import androidx.work.WorkManager;
import androidx.work.WorkerParameters;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.internal.InternalEvaluator;
import com.amazon.photos.uploader.internal.NotificationUpdatesNotifier;
import com.amazon.photos.uploader.internal.dagger.UploadManagerMap;
import com.amazon.photos.uploader.log.UploadLogger;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReevaluateWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 52\u00020\u0001:\u00015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010.\u001a\u00020\u0016H\u0014J\b\u0010/\u001a\u00020\bH\u0014J\b\u00100\u001a\u000201H\u0014J\u0011\u00102\u001a\u000203H\u0094@ø\u0001\u0000¢\u0006\u0002\u00104R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u0016@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR(\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\t\u001a\u0004\u0018\u00010\u001c@QX\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010#\u001a\u00020\"2\u0006\u0010\t\u001a\u00020\"@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R$\u0010)\u001a\u00020(2\u0006\u0010\t\u001a\u00020(@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-\u0082\u0002\u0004\n\u0002\b\u0019¨\u00066"}, d2 = {"Lcom/amazon/photos/uploader/internal/workers/ReevaluateWorker;", "Lcom/amazon/photos/uploader/internal/workers/BaseWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "hashedDirectedId", "", "<set-?>", "Lcom/amazon/photos/uploader/internal/InternalEvaluator;", "internalEvaluator", "getInternalEvaluator", "()Lcom/amazon/photos/uploader/internal/InternalEvaluator;", "setInternalEvaluator", "(Lcom/amazon/photos/uploader/internal/InternalEvaluator;)V", "Lcom/amazon/photos/uploader/log/UploadLogger;", "logger", "getLogger", "()Lcom/amazon/photos/uploader/log/UploadLogger;", "setLogger", "(Lcom/amazon/photos/uploader/log/UploadLogger;)V", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "metrics", "getMetrics", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "setMetrics", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;", "notificationUpdatesNotifier", "getNotificationUpdatesNotifier$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;", "setNotificationUpdatesNotifier$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;)V", "Lcom/amazon/photos/uploader/SchedulingCallback;", "schedulingCallback", "getSchedulingCallback", "()Lcom/amazon/photos/uploader/SchedulingCallback;", "setSchedulingCallback", "(Lcom/amazon/photos/uploader/SchedulingCallback;)V", "Landroidx/work/WorkManager;", "workManager", "getWorkManager", "()Landroidx/work/WorkManager;", "setWorkManager", "(Landroidx/work/WorkManager;)V", "getMetricsObj", "getTag", "injectMethod", "", "mainTask", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ReevaluateWorker extends BaseWorker {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "ReevaluateWorker";
    private final String hashedDirectedId;
    @NotNull
    public InternalEvaluator internalEvaluator;
    @NotNull
    public UploadLogger logger;
    @NotNull
    public Metrics metrics;
    @Nullable
    private NotificationUpdatesNotifier notificationUpdatesNotifier;
    @NotNull
    public SchedulingCallback schedulingCallback;
    @NotNull
    public WorkManager workManager;

    /* compiled from: ReevaluateWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/internal/workers/ReevaluateWorker$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReevaluateWorker(@NotNull Context appContext, @NotNull WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        Intrinsics.checkParameterIsNotNull(workerParams, "workerParams");
        this.hashedDirectedId = workerParams.getInputData().getString("HASHED_DIRECTED_ID_KEY");
    }

    @NotNull
    public final InternalEvaluator getInternalEvaluator() {
        InternalEvaluator internalEvaluator = this.internalEvaluator;
        if (internalEvaluator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalEvaluator");
        }
        return internalEvaluator;
    }

    @NotNull
    public final UploadLogger getLogger() {
        UploadLogger uploadLogger = this.logger;
        if (uploadLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return uploadLogger;
    }

    @NotNull
    public final Metrics getMetrics() {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return metrics;
    }

    @Override // com.amazon.photos.uploader.internal.workers.BaseWorker
    @NotNull
    protected Metrics getMetricsObj() {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return metrics;
    }

    @Override // com.amazon.photos.uploader.internal.workers.BaseWorker
    @Nullable
    public NotificationUpdatesNotifier getNotificationUpdatesNotifier$AndroidPhotosUploader_release() {
        return this.notificationUpdatesNotifier;
    }

    @NotNull
    public final SchedulingCallback getSchedulingCallback() {
        SchedulingCallback schedulingCallback = this.schedulingCallback;
        if (schedulingCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("schedulingCallback");
        }
        return schedulingCallback;
    }

    @Override // com.amazon.photos.uploader.internal.workers.BaseWorker
    @NotNull
    protected String getTag() {
        return TAG;
    }

    @NotNull
    public final WorkManager getWorkManager() {
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        return workManager;
    }

    @Override // com.amazon.photos.uploader.internal.workers.BaseWorker
    protected void injectMethod() {
        UploadManagerMap uploadManagerMap = UploadManagerMap.INSTANCE;
        String str = this.hashedDirectedId;
        if (str != null) {
            uploadManagerMap.getUploadManagerForAccount$AndroidPhotosUploader_release(str).getComponent$AndroidPhotosUploader_release().inject(this);
            return;
        }
        throw new IllegalArgumentException("No hashed directed id associated with worker.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @Override // com.amazon.photos.uploader.internal.workers.BaseWorker
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mainTask(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.amazon.photos.uploader.internal.workers.ReevaluateWorker$mainTask$1
            if (r0 == 0) goto L13
            r0 = r5
            com.amazon.photos.uploader.internal.workers.ReevaluateWorker$mainTask$1 r0 = (com.amazon.photos.uploader.internal.workers.ReevaluateWorker$mainTask$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.photos.uploader.internal.workers.ReevaluateWorker$mainTask$1 r0 = new com.amazon.photos.uploader.internal.workers.ReevaluateWorker$mainTask$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.amazon.photos.uploader.internal.workers.ReevaluateWorker r0 = (com.amazon.photos.uploader.internal.workers.ReevaluateWorker) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L49
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            com.amazon.photos.uploader.internal.workers.ReevaluateWorker$mainTask$2 r5 = new com.amazon.photos.uploader.internal.workers.ReevaluateWorker$mainTask$2
            r2 = 0
            r5.<init>(r4, r2)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r5, r0)
            if (r5 != r1) goto L49
            return r1
        L49:
            java.lang.String r0 = "coroutineScope {\n       … chain is replaced.\n    }"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.internal.workers.ReevaluateWorker.mainTask(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Inject
    public final void setInternalEvaluator(@NotNull InternalEvaluator internalEvaluator) {
        Intrinsics.checkParameterIsNotNull(internalEvaluator, "<set-?>");
        this.internalEvaluator = internalEvaluator;
    }

    @Inject
    public final void setLogger(@NotNull UploadLogger uploadLogger) {
        Intrinsics.checkParameterIsNotNull(uploadLogger, "<set-?>");
        this.logger = uploadLogger;
    }

    @Inject
    public final void setMetrics(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "<set-?>");
        this.metrics = metrics;
    }

    @Override // com.amazon.photos.uploader.internal.workers.BaseWorker
    @Inject
    public void setNotificationUpdatesNotifier$AndroidPhotosUploader_release(@Nullable NotificationUpdatesNotifier notificationUpdatesNotifier) {
        this.notificationUpdatesNotifier = notificationUpdatesNotifier;
    }

    @Inject
    public final void setSchedulingCallback(@NotNull SchedulingCallback schedulingCallback) {
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "<set-?>");
        this.schedulingCallback = schedulingCallback;
    }

    @Inject
    public final void setWorkManager(@NotNull WorkManager workManager) {
        Intrinsics.checkParameterIsNotNull(workManager, "<set-?>");
        this.workManager = workManager;
    }
}
