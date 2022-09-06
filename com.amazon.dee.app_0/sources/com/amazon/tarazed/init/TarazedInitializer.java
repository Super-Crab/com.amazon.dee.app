package com.amazon.tarazed.init;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.dagger.injectors.LibraryInjector;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedInitializer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u001bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000e@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u0014@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001f"}, d2 = {"Lcom/amazon/tarazed/init/TarazedInitializer;", "", "()V", "INITIALIZED", "Ljava/util/concurrent/atomic/AtomicBoolean;", "TAG", "", "<set-?>", "Lcom/amazon/tarazed/activity/ActivityTracker;", "activityTracker", "getActivityTracker", "()Lcom/amazon/tarazed/activity/ActivityTracker;", "setActivityTracker", "(Lcom/amazon/tarazed/activity/ActivityTracker;)V", "Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid;", "deviceInfoUtility", "getDeviceInfoUtility", "()Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid;", "setDeviceInfoUtility", "(Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid;)V", "Lcom/amazon/tarazed/core/logging/TarazedLogger;", "logger", "getLogger", "()Lcom/amazon/tarazed/core/logging/TarazedLogger;", "setLogger", "(Lcom/amazon/tarazed/core/logging/TarazedLogger;)V", "init", "", "context", "Landroid/content/Context;", "reset", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedInitializer {
    private static final String TAG = "TarazedNotification";
    @NotNull
    public static ActivityTracker activityTracker;
    @NotNull
    public static DeviceInfoUtilityAndroid deviceInfoUtility;
    @NotNull
    public static TarazedLogger logger;
    public static final TarazedInitializer INSTANCE = new TarazedInitializer();
    private static final AtomicBoolean INITIALIZED = new AtomicBoolean();

    private TarazedInitializer() {
    }

    @NotNull
    public final ActivityTracker getActivityTracker() {
        ActivityTracker activityTracker2 = activityTracker;
        if (activityTracker2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityTracker");
        }
        return activityTracker2;
    }

    @NotNull
    public final DeviceInfoUtilityAndroid getDeviceInfoUtility() {
        DeviceInfoUtilityAndroid deviceInfoUtilityAndroid = deviceInfoUtility;
        if (deviceInfoUtilityAndroid == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceInfoUtility");
        }
        return deviceInfoUtilityAndroid;
    }

    @NotNull
    public final TarazedLogger getLogger() {
        TarazedLogger tarazedLogger = logger;
        if (tarazedLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return tarazedLogger;
    }

    public final void init(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (!INITIALIZED.getAndSet(true)) {
            LibraryInjector.initComponent(context);
            LibraryInjector.getComponent().inject(this);
            TarazedLogger tarazedLogger = logger;
            if (tarazedLogger == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            tarazedLogger.i("TarazedNotification", "Initializing Tarazed");
            return;
        }
        TarazedLogger tarazedLogger2 = logger;
        if (tarazedLogger2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        tarazedLogger2.w("TarazedNotification", "Initializer called after initialization, ignoring.");
    }

    @VisibleForTesting
    public final void reset() {
        INITIALIZED.set(false);
    }

    @Inject
    public final void setActivityTracker(@NotNull ActivityTracker activityTracker2) {
        Intrinsics.checkParameterIsNotNull(activityTracker2, "<set-?>");
        activityTracker = activityTracker2;
    }

    @Inject
    public final void setDeviceInfoUtility(@NotNull DeviceInfoUtilityAndroid deviceInfoUtilityAndroid) {
        Intrinsics.checkParameterIsNotNull(deviceInfoUtilityAndroid, "<set-?>");
        deviceInfoUtility = deviceInfoUtilityAndroid;
    }

    @Inject
    public final void setLogger(@NotNull TarazedLogger tarazedLogger) {
        Intrinsics.checkParameterIsNotNull(tarazedLogger, "<set-?>");
        logger = tarazedLogger;
    }
}
