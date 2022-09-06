package com.amazon.photos.uploader.internal.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BatteryState.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u0000 -2\u00020\u0001:\u0001-B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001f\u0010 \u001a\u00020\u001c2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0002\b#J\u0015\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\"H\u0000¢\u0006\u0002\b&J\u0015\u0010'\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\"H\u0000¢\u0006\u0002\b(J\u0010\u0010)\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\"H\u0002J\u0010\u0010*\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\"H\u0002J\r\u0010+\u001a\u00020\u001cH\u0000¢\u0006\u0002\b,R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u001b\u0010\u0012R\u001e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u001c@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001e\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u001c@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/amazon/photos/uploader/internal/device/BatteryState;", "", "context", "Landroid/content/Context;", "systemUtil", "Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "(Landroid/content/Context;Lcom/amazon/photos/uploader/internal/utils/SystemUtil;)V", "<set-?>", "", "batteryPercent", "batteryPercent$annotations", "()V", "getBatteryPercent", "()I", "chargingAction", "", "chargingAction$annotations", "getChargingAction", "()Ljava/lang/String;", "chargingActions", "", "getChargingActions", "()Ljava/util/Set;", "chargingType", "getChargingType", "dischargingAction", "dischargingAction$annotations", "getDischargingAction", "", "isCharging", "()Z", "isInPowerSaverMode", "updateAllStates", "batteryChangedIntent", "Landroid/content/Intent;", "updateAllStates$AndroidPhotosUploader_release", "updateBatteryPercent", MAPAccountManager.KEY_INTENT, "updateBatteryPercent$AndroidPhotosUploader_release", "updateChargingState", "updateChargingState$AndroidPhotosUploader_release", "updateChargingType", "updateIsCharging", "updatePowerState", "updatePowerState$AndroidPhotosUploader_release", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BatteryState {
    @NotNull
    private static final Set<String> BATTERY_ACTIONS;
    private static final int BATTERY_PLUGGED_ANY = 7;
    @NotNull
    private static final Set<String> BATTERY_STATUS_CHANGE_ACTION;
    public static final Companion Companion = new Companion(null);
    public static final int EXTRA_NOT_FOUND = -1;
    public static final int LOW_BATTERY_PERCENT = 15;
    private int batteryPercent;
    @SuppressLint({"InlinedApi"})
    @NotNull
    private final String chargingAction;
    @NotNull
    private final Set<String> chargingActions;
    private int chargingType;
    private final Context context;
    @SuppressLint({"InlinedApi"})
    @NotNull
    private final String dischargingAction;
    private boolean isCharging;
    private boolean isInPowerSaverMode;
    private final SystemUtil systemUtil;

    /* compiled from: BatteryState.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0016\u0010\f\u001a\u00020\t8\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u0002R\u000e\u0010\u000e\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/photos/uploader/internal/device/BatteryState$Companion;", "", "()V", "BATTERY_ACTIONS", "", "", "getBATTERY_ACTIONS", "()Ljava/util/Set;", "BATTERY_PLUGGED_ANY", "", "BATTERY_STATUS_CHANGE_ACTION", "getBATTERY_STATUS_CHANGE_ACTION", "EXTRA_NOT_FOUND", "EXTRA_NOT_FOUND$annotations", "LOW_BATTERY_PERCENT", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void EXTRA_NOT_FOUND$annotations() {
        }

        @NotNull
        public final Set<String> getBATTERY_ACTIONS() {
            return BatteryState.BATTERY_ACTIONS;
        }

        @NotNull
        public final Set<String> getBATTERY_STATUS_CHANGE_ACTION() {
            return BatteryState.BATTERY_STATUS_CHANGE_ACTION;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        Set<String> of;
        Set<String> of2;
        of = SetsKt__SetsJVMKt.setOf("android.intent.action.BATTERY_CHANGED");
        BATTERY_STATUS_CHANGE_ACTION = of;
        of2 = SetsKt__SetsKt.setOf((Object[]) new String[]{"android.intent.action.BATTERY_LOW", "android.intent.action.BATTERY_OKAY"});
        BATTERY_ACTIONS = of2;
    }

    public BatteryState(@NotNull Context context, @NotNull SystemUtil systemUtil) {
        Set<String> of;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        this.context = context;
        this.systemUtil = systemUtil;
        this.chargingAction = this.systemUtil.isOsVersionAtLeastM() ? "android.os.action.CHARGING" : "android.intent.action.ACTION_POWER_CONNECTED";
        this.dischargingAction = this.systemUtil.isOsVersionAtLeastM() ? "android.os.action.DISCHARGING" : "android.intent.action.ACTION_POWER_DISCONNECTED";
        of = SetsKt__SetsKt.setOf((Object[]) new String[]{this.chargingAction, this.dischargingAction});
        this.chargingActions = of;
        this.batteryPercent = 100;
        updateAllStates$AndroidPhotosUploader_release(null, this.context);
    }

    public static /* synthetic */ void batteryPercent$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void chargingAction$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void dischargingAction$annotations() {
    }

    private final boolean updateChargingType(Intent intent) {
        int intExtra = intent.getIntExtra("plugged", -1);
        if (intExtra != -1) {
            int i = this.chargingType;
            this.chargingType = intExtra;
            return false | (this.chargingType != i);
        }
        return false;
    }

    private final boolean updateIsCharging(Intent intent) {
        int intExtra = intent.getIntExtra("plugged", -1);
        boolean z = this.isCharging;
        boolean z2 = true;
        if (intExtra != -1) {
            this.isCharging = (intExtra & 7) != 0;
        } else if (intent.getAction() != null) {
            Set<String> set = this.chargingActions;
            String action = intent.getAction();
            if (action == null) {
                Intrinsics.throwNpe();
            }
            if (set.contains(action)) {
                this.isCharging = Intrinsics.areEqual(this.chargingAction, intent.getAction());
            }
        }
        if (this.isCharging == z) {
            z2 = false;
        }
        return false | z2;
    }

    public final int getBatteryPercent() {
        return this.batteryPercent;
    }

    @NotNull
    public final String getChargingAction() {
        return this.chargingAction;
    }

    @NotNull
    public final Set<String> getChargingActions() {
        return this.chargingActions;
    }

    public final int getChargingType() {
        return this.chargingType;
    }

    @NotNull
    public final String getDischargingAction() {
        return this.dischargingAction;
    }

    public final boolean isCharging() {
        return this.isCharging;
    }

    public final boolean isInPowerSaverMode() {
        return this.isInPowerSaverMode;
    }

    public final boolean updateAllStates$AndroidPhotosUploader_release(@Nullable Intent intent, @NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if ((intent == null || (!Intrinsics.areEqual("android.intent.action.BATTERY_CHANGED", intent.getAction()))) && (intent = context.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"))) == null) {
            return false;
        }
        return updateChargingState$AndroidPhotosUploader_release(intent) | updateBatteryPercent$AndroidPhotosUploader_release(intent) | false | updatePowerState$AndroidPhotosUploader_release();
    }

    public final boolean updateBatteryPercent$AndroidPhotosUploader_release(@NotNull Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        int intExtra = intent.getIntExtra(ModelTransformer.KEY_BATTERY_LEVEL, -1);
        int intExtra2 = intent.getIntExtra(ModelTransformer.KEY_BATTERY_SCALE, -1);
        if (intExtra == -1 || intExtra2 <= 0) {
            return false;
        }
        boolean z = true;
        boolean z2 = this.batteryPercent > 15;
        this.batteryPercent = (intExtra * 100) / intExtra2;
        if (z2 == (this.batteryPercent > 15)) {
            z = false;
        }
        return false | z;
    }

    public final boolean updateChargingState$AndroidPhotosUploader_release(@NotNull Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        return updateChargingType(intent) | updateIsCharging(intent);
    }

    public final boolean updatePowerState$AndroidPhotosUploader_release() {
        PowerManager powerManager = (PowerManager) this.context.getSystemService("power");
        boolean z = this.isInPowerSaverMode;
        this.isInPowerSaverMode = powerManager != null ? powerManager.isPowerSaveMode() : false;
        return (this.isInPowerSaverMode != z) | false;
    }
}
