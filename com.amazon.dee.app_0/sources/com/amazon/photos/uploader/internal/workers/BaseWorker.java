package com.amazon.photos.uploader.internal.workers;

import android.content.Context;
import androidx.work.CoroutineWorker;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.deecomms.common.Constants;
import com.amazon.photos.uploader.internal.NotificationUpdatesNotifier;
import com.amazon.photos.uploader.observables.NotificationObserver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BaseWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b&\u0018\u0000 %2\u00020\u00012\u00020\u0002:\u0001%B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0011\u0010\u0016\u001a\u00020\u0017H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u001aH$J\b\u0010\u001b\u001a\u00020\u001cH$J\b\u0010\u001d\u001a\u00020\u0015H$J\u0011\u0010\u001e\u001a\u00020\u0017H¤@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0019\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\u0015H\u0002J\r\u0010#\u001a\u00020\u0015H\u0000¢\u0006\u0002\b$R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u0004\u0018\u00010\u000eX \u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, d2 = {"Lcom/amazon/photos/uploader/internal/workers/BaseWorker;", "Landroidx/work/CoroutineWorker;", "Lcom/amazon/photos/uploader/observables/NotificationObserver;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "attemptCount", "", "componentFailureHappened", "", "componentInjected", "notificationUpdatesNotifier", "Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;", "getNotificationUpdatesNotifier$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;", "setNotificationUpdatesNotifier$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;)V", "startForegroundDone", "componentsSetup", "", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMetricsObj", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "getTag", "", "injectMethod", "mainTask", "onNotificationUpdate", Constants.BUNDLE_KEY_NOTIFICATION_ID, "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerNotificationObserver", "unregisterNotificationObserver", "unregisterNotificationObserver$AndroidPhotosUploader_release", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class BaseWorker extends CoroutineWorker implements NotificationObserver {
    public static final Companion Companion = new Companion(null);
    public static final int MAX_RETRIES = 5;
    private int attemptCount;
    private boolean componentFailureHappened;
    private boolean componentInjected;
    private boolean startForegroundDone;

    /* compiled from: BaseWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/internal/workers/BaseWorker$Companion;", "", "()V", "MAX_RETRIES", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(workerParams, "workerParams");
    }

    private final void componentsSetup() {
        if (!this.componentInjected) {
            injectMethod();
            this.componentInjected = true;
            getMetricsObj().recordSimpleEvent(getTag(), BaseWorker$componentsSetup$1.INSTANCE, new MetricRecordingType[0]);
            if (!this.componentFailureHappened) {
                return;
            }
            getMetricsObj().recordSimpleEvent(getTag(), BaseWorker$componentsSetup$2.INSTANCE, new MetricRecordingType[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object doWork$suspendImpl(com.amazon.photos.uploader.internal.workers.BaseWorker r4, kotlin.coroutines.Continuation r5) {
        /*
            boolean r0 = r5 instanceof com.amazon.photos.uploader.internal.workers.BaseWorker$doWork$1
            if (r0 == 0) goto L13
            r0 = r5
            com.amazon.photos.uploader.internal.workers.BaseWorker$doWork$1 r0 = (com.amazon.photos.uploader.internal.workers.BaseWorker$doWork$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.photos.uploader.internal.workers.BaseWorker$doWork$1 r0 = new com.amazon.photos.uploader.internal.workers.BaseWorker$doWork$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r4 = r0.L$0
            com.amazon.photos.uploader.internal.workers.BaseWorker r4 = (com.amazon.photos.uploader.internal.workers.BaseWorker) r4
            kotlin.ResultKt.throwOnFailure(r5)
            goto L4c
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            r5 = 0
            r4.startForegroundDone = r5     // Catch: java.lang.IllegalStateException -> L52
            r4.componentsSetup()     // Catch: java.lang.IllegalStateException -> L52
            r4.registerNotificationObserver()
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.mainTask(r0)
            if (r5 != r1) goto L4c
            return r1
        L4c:
            androidx.work.ListenableWorker$Result r5 = (androidx.work.ListenableWorker.Result) r5
            r4.unregisterNotificationObserver$AndroidPhotosUploader_release()
            return r5
        L52:
            r4.componentFailureHappened = r3
            int r5 = r4.attemptCount
            r0 = 5
            if (r5 >= r0) goto L81
            int r5 = r5 + r3
            r4.attemptCount = r5
            java.lang.String r5 = r4.getTag()
            java.lang.String r0 = "Error injecting components to worker on attempt "
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            int r4 = r4.attemptCount
            r0.append(r4)
            java.lang.String r4 = ". Retrying..."
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            android.util.Log.w(r5, r4)
            androidx.work.ListenableWorker$Result r4 = androidx.work.ListenableWorker.Result.retry()
            java.lang.String r5 = "Result.retry()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
            return r4
        L81:
            java.lang.String r4 = r4.getTag()
            java.lang.String r5 = "Component injection failed."
            android.util.Log.e(r4, r5)
            androidx.work.ListenableWorker$Result r4 = androidx.work.ListenableWorker.Result.failure()
            java.lang.String r5 = "Result.failure()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.internal.workers.BaseWorker.doWork$suspendImpl(com.amazon.photos.uploader.internal.workers.BaseWorker, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object onNotificationUpdate$suspendImpl(com.amazon.photos.uploader.internal.workers.BaseWorker r5, int r6, kotlin.coroutines.Continuation r7) {
        /*
            boolean r0 = r7 instanceof com.amazon.photos.uploader.internal.workers.BaseWorker$onNotificationUpdate$1
            if (r0 == 0) goto L13
            r0 = r7
            com.amazon.photos.uploader.internal.workers.BaseWorker$onNotificationUpdate$1 r0 = (com.amazon.photos.uploader.internal.workers.BaseWorker$onNotificationUpdate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.photos.uploader.internal.workers.BaseWorker$onNotificationUpdate$1 r0 = new com.amazon.photos.uploader.internal.workers.BaseWorker$onNotificationUpdate$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L43
            if (r2 != r3) goto L3b
            java.lang.Object r5 = r0.L$3
            android.app.Notification r5 = (android.app.Notification) r5
            java.lang.Object r5 = r0.L$2
            android.app.Notification r5 = (android.app.Notification) r5
            java.lang.Object r5 = r0.L$1
            com.amazon.photos.uploader.internal.NotificationUpdatesNotifier r5 = (com.amazon.photos.uploader.internal.NotificationUpdatesNotifier) r5
            int r5 = r0.I$0
            java.lang.Object r5 = r0.L$0
            com.amazon.photos.uploader.internal.workers.BaseWorker r5 = (com.amazon.photos.uploader.internal.workers.BaseWorker) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L71
        L3b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L43:
            kotlin.ResultKt.throwOnFailure(r7)
            boolean r7 = r5.startForegroundDone
            if (r7 == 0) goto L4d
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L4d:
            com.amazon.photos.uploader.internal.NotificationUpdatesNotifier r7 = r5.getNotificationUpdatesNotifier$AndroidPhotosUploader_release()
            if (r7 == 0) goto L73
            android.app.Notification r2 = r7.getNotification$AndroidPhotosUploader_release()
            if (r2 == 0) goto L73
            androidx.work.ForegroundInfo r4 = new androidx.work.ForegroundInfo
            r4.<init>(r6, r2)
            r0.L$0 = r5
            r0.I$0 = r6
            r0.L$1 = r7
            r0.L$2 = r2
            r0.L$3 = r2
            r0.label = r3
            java.lang.Object r6 = r5.setForeground(r4, r0)
            if (r6 != r1) goto L71
            return r1
        L71:
            r5.startForegroundDone = r3
        L73:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.internal.workers.BaseWorker.onNotificationUpdate$suspendImpl(com.amazon.photos.uploader.internal.workers.BaseWorker, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void registerNotificationObserver() {
        NotificationUpdatesNotifier notificationUpdatesNotifier$AndroidPhotosUploader_release = getNotificationUpdatesNotifier$AndroidPhotosUploader_release();
        if (notificationUpdatesNotifier$AndroidPhotosUploader_release != null) {
            notificationUpdatesNotifier$AndroidPhotosUploader_release.addNotificationObserver(this);
        }
    }

    @Override // androidx.work.CoroutineWorker
    @Nullable
    public Object doWork(@NotNull Continuation<? super ListenableWorker.Result> continuation) {
        return doWork$suspendImpl(this, continuation);
    }

    @NotNull
    protected abstract Metrics getMetricsObj();

    @Nullable
    public abstract NotificationUpdatesNotifier getNotificationUpdatesNotifier$AndroidPhotosUploader_release();

    @NotNull
    protected abstract String getTag();

    protected abstract void injectMethod();

    @Nullable
    protected abstract Object mainTask(@NotNull Continuation<? super ListenableWorker.Result> continuation);

    @Override // com.amazon.photos.uploader.observables.NotificationObserver
    @Nullable
    public Object onNotificationUpdate(int i, @NotNull Continuation<? super Unit> continuation) {
        return onNotificationUpdate$suspendImpl(this, i, continuation);
    }

    public abstract void setNotificationUpdatesNotifier$AndroidPhotosUploader_release(@Nullable NotificationUpdatesNotifier notificationUpdatesNotifier);

    public final void unregisterNotificationObserver$AndroidPhotosUploader_release() {
        NotificationUpdatesNotifier notificationUpdatesNotifier$AndroidPhotosUploader_release = getNotificationUpdatesNotifier$AndroidPhotosUploader_release();
        if (notificationUpdatesNotifier$AndroidPhotosUploader_release != null) {
            notificationUpdatesNotifier$AndroidPhotosUploader_release.removeNotificationObserver(this);
        }
    }
}
