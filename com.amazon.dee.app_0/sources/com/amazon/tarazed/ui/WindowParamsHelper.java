package com.amazon.tarazed.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Window;
import android.view.WindowManager;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.dagger.injectors.LibraryInjector;
import com.amazon.tarazed.receiver.ToggleQAModeReceiver;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: WindowParamsHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u0004J\u000e\u0010!\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\"\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010#\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010$\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u000e\u0010%\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010&\u001a\u00020'H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u0015@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006("}, d2 = {"Lcom/amazon/tarazed/ui/WindowParamsHelper;", "", "()V", "FIRST_AMAZON_SYSTEM_WINDOW_FIRE_OS_5", "", "MODAL_DIM_ALPHA", "", "MODAL_DIM_TRANSPARENT", "WINDOW_TYPE_KINDLE_CONNECT", "WINDOW_TYPE_KINDLE_CONNECT_FIRE_OS_5", "<set-?>", "Lcom/amazon/tarazed/activity/ActivityTracker;", "activityTracker", "getActivityTracker$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/activity/ActivityTracker;", "setActivityTracker$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/activity/ActivityTracker;)V", "injected", "", "isQaMode", "()Z", "Landroid/content/SharedPreferences;", "sharedPreferences", "getSharedPreferences$TarazedAndroidLibrary_release", "()Landroid/content/SharedPreferences;", "setSharedPreferences$TarazedAndroidLibrary_release", "(Landroid/content/SharedPreferences;)V", "createDraggableLayoutParams", "Landroid/view/WindowManager$LayoutParams;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", ReactProperties.HereMapMarker.X, ReactProperties.HereMapMarker.Y, "createModalLayoutParams", "createNonModalLayoutParams", "createStaticLayoutParams", "getActivityFlags", "getWindowType", "injectIfNeeded", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class WindowParamsHelper {
    private static final int FIRST_AMAZON_SYSTEM_WINDOW_FIRE_OS_5 = 2101;
    public static final WindowParamsHelper INSTANCE = new WindowParamsHelper();
    private static final float MODAL_DIM_ALPHA = 0.6f;
    private static final float MODAL_DIM_TRANSPARENT = 0.0f;
    private static final int WINDOW_TYPE_KINDLE_CONNECT = 2998;
    private static final int WINDOW_TYPE_KINDLE_CONNECT_FIRE_OS_5 = 2107;
    @NotNull
    public static ActivityTracker activityTracker;
    private static boolean injected;
    @NotNull
    public static SharedPreferences sharedPreferences;

    private WindowParamsHelper() {
    }

    private final int getActivityFlags(DeviceInfoUtility deviceInfoUtility) {
        Window window;
        WindowManager.LayoutParams attributes;
        Window window2;
        WindowManager.LayoutParams attributes2;
        injectIfNeeded();
        int i = 1024;
        int i2 = 0;
        if (!deviceInfoUtility.is1PDevice()) {
            ActivityTracker activityTracker2 = activityTracker;
            if (activityTracker2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activityTracker");
            }
            Activity currentActivity = activityTracker2.getCurrentActivity();
            i = 1024 & ((currentActivity == null || (window2 = currentActivity.getWindow()) == null || (attributes2 = window2.getAttributes()) == null) ? 0 : attributes2.flags);
        }
        if (!deviceInfoUtility.is1PDevice()) {
            ActivityTracker activityTracker3 = activityTracker;
            if (activityTracker3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activityTracker");
            }
            Activity currentActivity2 = activityTracker3.getCurrentActivity();
            if (currentActivity2 != null && (window = currentActivity2.getWindow()) != null && (attributes = window.getAttributes()) != null) {
                i2 = attributes.flags;
            }
            i2 &= Integer.MIN_VALUE;
        }
        return i | i2;
    }

    private final void injectIfNeeded() {
        if (injected) {
            return;
        }
        LibraryInjector.getComponent().inject(this);
        injected = true;
    }

    private final boolean isQaMode() {
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        return sharedPreferences2.getBoolean(ToggleQAModeReceiver.QA_MODE_PREFERENCE_KEY, false);
    }

    @NotNull
    public final WindowManager.LayoutParams createDraggableLayoutParams(@NotNull DeviceInfoUtility deviceInfoUtility, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, i, i2, getWindowType(deviceInfoUtility), isQaMode() ? 16777248 : 16777256, -3);
        layoutParams.gravity = 8388659;
        return layoutParams;
    }

    @NotNull
    public final WindowManager.LayoutParams createModalLayoutParams(@NotNull DeviceInfoUtility deviceInfoUtility) {
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        WindowManager.LayoutParams createNonModalLayoutParams = createNonModalLayoutParams(deviceInfoUtility);
        createNonModalLayoutParams.flags &= -25;
        createNonModalLayoutParams.flags |= 2;
        createNonModalLayoutParams.dimAmount = MODAL_DIM_ALPHA;
        return createNonModalLayoutParams;
    }

    @NotNull
    public final WindowManager.LayoutParams createNonModalLayoutParams(@NotNull DeviceInfoUtility deviceInfoUtility) {
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(getWindowType(deviceInfoUtility), getActivityFlags(deviceInfoUtility) | 16777368, -3);
        layoutParams.dimAmount = 0.0f;
        return layoutParams;
    }

    @NotNull
    public final WindowManager.LayoutParams createStaticLayoutParams(@NotNull DeviceInfoUtility deviceInfoUtility) {
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        return new WindowManager.LayoutParams(getWindowType(deviceInfoUtility), getActivityFlags(deviceInfoUtility) | 16777400, -3);
    }

    @NotNull
    public final ActivityTracker getActivityTracker$TarazedAndroidLibrary_release() {
        ActivityTracker activityTracker2 = activityTracker;
        if (activityTracker2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityTracker");
        }
        return activityTracker2;
    }

    @NotNull
    public final SharedPreferences getSharedPreferences$TarazedAndroidLibrary_release() {
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        return sharedPreferences2;
    }

    public final int getWindowType(@NotNull DeviceInfoUtility deviceInfoUtility) {
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        if (deviceInfoUtility.is1PDevice()) {
            return deviceInfoUtility.isAtLeastFos6() ? WINDOW_TYPE_KINDLE_CONNECT : WINDOW_TYPE_KINDLE_CONNECT_FIRE_OS_5;
        }
        return 2;
    }

    @Inject
    public final void setActivityTracker$TarazedAndroidLibrary_release(@NotNull ActivityTracker activityTracker2) {
        Intrinsics.checkParameterIsNotNull(activityTracker2, "<set-?>");
        activityTracker = activityTracker2;
    }

    @Inject
    public final void setSharedPreferences$TarazedAndroidLibrary_release(@NotNull SharedPreferences sharedPreferences2) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences2, "<set-?>");
        sharedPreferences = sharedPreferences2;
    }
}
