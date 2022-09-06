package com.amazon.photos.uploader.internal;

import android.app.Notification;
import android.app.NotificationManager;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.deecomms.common.Constants;
import com.amazon.photos.uploader.internal.workers.SchedulerWorkerKt;
import com.amazon.photos.uploader.observables.NotificationObserver;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NotificationUpdatesNotifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0000\u0018\u0000 !2\u00020\u0001:\u0001!B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0011J\b\u0010\u0018\u001a\u00020\u0016H\u0002J-\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u001fJ\u000e\u0010 \u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0011R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR2\u0010\u000f\u001a&\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011 \u0012*\u0012\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011\u0018\u00010\u00130\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;", "", "workManager", "Landroidx/work/WorkManager;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "(Landroidx/work/WorkManager;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "lastNotificationUpdateTime", "", "notification", "Landroid/app/Notification;", "getNotification$AndroidPhotosUploader_release", "()Landroid/app/Notification;", "setNotification$AndroidPhotosUploader_release", "(Landroid/app/Notification;)V", "notificationObservers", "", "Lcom/amazon/photos/uploader/observables/NotificationObserver;", "kotlin.jvm.PlatformType", "", "notificationUpdateThrottleTimeMillis", "addNotificationObserver", "", "observer", "isAnyWorkerRunning", "onNotificationUpdated", "notificationManager", "Landroid/app/NotificationManager;", Constants.BUNDLE_KEY_NOTIFICATION_ID, "", "isLastNotification", "onNotificationUpdated$AndroidPhotosUploader_release", "removeNotificationObserver", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class NotificationUpdatesNotifier {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "NotificationUpdatesNotifier";
    private static final long WAIT_FOR_WORKER_INTERVAL_MS = 1000;
    private static final long WAIT_FOR_WORKER_TIMEOUT_MS = 300000;
    private long lastNotificationUpdateTime;
    private final Metrics metrics;
    @Nullable
    private Notification notification;
    private final Set<NotificationObserver> notificationObservers;
    private final long notificationUpdateThrottleTimeMillis;
    private final WorkManager workManager;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: NotificationUpdatesNotifier.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier$Companion;", "", "()V", "TAG", "", "WAIT_FOR_WORKER_INTERVAL_MS", "", "WAIT_FOR_WORKER_TIMEOUT_MS", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[WorkInfo.State.values().length];

        static {
            $EnumSwitchMapping$0[WorkInfo.State.RUNNING.ordinal()] = 1;
        }
    }

    public NotificationUpdatesNotifier(@NotNull WorkManager workManager, @NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(workManager, "workManager");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.workManager = workManager;
        this.metrics = metrics;
        this.notificationUpdateThrottleTimeMillis = TimeUnit.SECONDS.toMillis(1L);
        this.lastNotificationUpdateTime = System.currentTimeMillis();
        this.notificationObservers = Collections.newSetFromMap(new ConcurrentHashMap());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isAnyWorkerRunning() {
        boolean z;
        List<WorkInfo> uploadWorkers = this.workManager.getWorkInfosByTag(SchedulerWorkerKt.UPLOAD_TAG).get();
        List<WorkInfo> list = this.workManager.getWorkInfosByTag(SchedulerWorkerKt.SCHEDULE_TAG).get();
        Intrinsics.checkExpressionValueIsNotNull(list, "workManager.getWorkInfosByTag(SCHEDULE_TAG).get()");
        uploadWorkers.addAll(list);
        Intrinsics.checkExpressionValueIsNotNull(uploadWorkers, "uploadWorkers");
        if (uploadWorkers.isEmpty()) {
            return false;
        }
        for (WorkInfo workInfo : uploadWorkers) {
            Intrinsics.checkExpressionValueIsNotNull(workInfo, "workInfo");
            if (WhenMappings.$EnumSwitchMapping$0[workInfo.getState().ordinal()] != 1) {
                z = false;
                continue;
            } else {
                z = true;
                continue;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final boolean addNotificationObserver(@NotNull NotificationObserver observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        return this.notificationObservers.add(observer);
    }

    @Nullable
    public final Notification getNotification$AndroidPhotosUploader_release() {
        return this.notification;
    }

    public final boolean onNotificationUpdated$AndroidPhotosUploader_release(@NotNull NotificationManager notificationManager, int i, @NotNull Notification notification, boolean z) {
        Intrinsics.checkParameterIsNotNull(notificationManager, "notificationManager");
        Intrinsics.checkParameterIsNotNull(notification, "notification");
        long currentTimeMillis = System.currentTimeMillis();
        this.notification = notification;
        if (z || currentTimeMillis - this.lastNotificationUpdateTime >= this.notificationUpdateThrottleTimeMillis) {
            notificationManager.notify(i, notification);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new NotificationUpdatesNotifier$onNotificationUpdated$1(this, z, notificationManager, i, notification, null), 3, null);
            this.lastNotificationUpdateTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    public final boolean removeNotificationObserver(@NotNull NotificationObserver observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        return this.notificationObservers.remove(observer);
    }

    public final void setNotification$AndroidPhotosUploader_release(@Nullable Notification notification) {
        this.notification = notification;
    }
}
