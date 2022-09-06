package com.amazon.photos.uploader.internal.device;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.internal.OpenForTesting;
import com.amazon.photos.uploader.log.UploadLogger;
import com.amazon.photos.uploader.observables.UploadSummary;
import com.amazon.photos.uploader.observables.UploadSummaryKt;
import com.amazon.photos.uploader.observables.UploadSummaryObserver;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.bouncycastle.i18n.ErrorBundle;
import org.jetbrains.annotations.NotNull;
/* compiled from: BatteryReceiverRegisterHelper.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u0018H\u0002R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/photos/uploader/internal/device/BatteryReceiverRegisterHelper;", "Lcom/amazon/photos/uploader/observables/UploadSummaryObserver;", "context", "Landroid/content/Context;", "batteryState", "Lcom/amazon/photos/uploader/internal/device/BatteryState;", "schedulingCallback", "Lcom/amazon/photos/uploader/SchedulingCallback;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "(Landroid/content/Context;Lcom/amazon/photos/uploader/internal/device/BatteryState;Lcom/amazon/photos/uploader/SchedulingCallback;Lcom/amazon/photos/uploader/log/UploadLogger;)V", "batteryPowerStateReceiver", "Lcom/amazon/photos/uploader/internal/device/BatteryStateReceiver;", "batteryPowerStateReceiver$annotations", "()V", "getBatteryPowerStateReceiver", "()Lcom/amazon/photos/uploader/internal/device/BatteryStateReceiver;", "isRegistered", "", JoinPoint.SYNCHRONIZATION_LOCK, "", "started", "Ljava/util/concurrent/atomic/AtomicBoolean;", "onChanged", "", ErrorBundle.SUMMARY_ENTRY, "Lcom/amazon/photos/uploader/observables/UploadSummary;", "onUploaderStarted", "onUploaderStopped", "toggleReceiver", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BatteryReceiverRegisterHelper implements UploadSummaryObserver {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "BatteryReceiverRegisterHelper";
    @NotNull
    private final BatteryStateReceiver batteryPowerStateReceiver;
    private final Context context;
    private boolean isRegistered;
    private final Object lock;
    private final UploadLogger logger;
    private AtomicBoolean started;

    /* compiled from: BatteryReceiverRegisterHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/internal/device/BatteryReceiverRegisterHelper$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BatteryReceiverRegisterHelper(@NotNull Context context, @NotNull BatteryState batteryState, @NotNull SchedulingCallback schedulingCallback, @NotNull UploadLogger logger) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(batteryState, "batteryState");
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.context = context;
        this.logger = logger;
        this.lock = new Object();
        this.started = new AtomicBoolean(false);
        this.batteryPowerStateReceiver = new BatteryStateReceiver(batteryState, schedulingCallback);
    }

    @VisibleForTesting
    public static /* synthetic */ void batteryPowerStateReceiver$annotations() {
    }

    private final void toggleReceiver() {
        synchronized (this.lock) {
            if (!this.isRegistered) {
                this.batteryPowerStateReceiver.onReceive(this.context, this.context.registerReceiver(this.batteryPowerStateReceiver, this.batteryPowerStateReceiver.getIntentFilter$AndroidPhotosUploader_release()));
                this.isRegistered = true;
                this.logger.i$AndroidPhotosUploader_release(TAG, "Battery receiver enabled");
            } else if (this.isRegistered) {
                this.context.unregisterReceiver(this.batteryPowerStateReceiver);
                this.isRegistered = false;
                this.logger.i$AndroidPhotosUploader_release(TAG, "Battery receiver disabled");
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @NotNull
    public final BatteryStateReceiver getBatteryPowerStateReceiver() {
        return this.batteryPowerStateReceiver;
    }

    @Override // com.amazon.photos.uploader.observables.UploadSummaryObserver
    public void onChanged(@NotNull UploadSummary summary) {
        Intrinsics.checkParameterIsNotNull(summary, "summary");
        if (this.started.get() && UploadSummaryKt.hasUploadFinished(summary)) {
            toggleReceiver();
            this.started.set(false);
        } else if (this.started.getAndSet(true)) {
        } else {
            toggleReceiver();
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadSummaryObserver
    public void onUploaderStarted(@NotNull UploadSummary summary) {
        Intrinsics.checkParameterIsNotNull(summary, "summary");
    }

    @Override // com.amazon.photos.uploader.observables.UploadSummaryObserver
    public void onUploaderStopped(@NotNull UploadSummary summary) {
        Intrinsics.checkParameterIsNotNull(summary, "summary");
    }
}
