package com.amazon.photos.uploader.internal.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.internal.OpenForTesting;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BatteryStateReceiver.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/photos/uploader/internal/device/BatteryStateReceiver;", "Landroid/content/BroadcastReceiver;", "batteryState", "Lcom/amazon/photos/uploader/internal/device/BatteryState;", "schedulingCallback", "Lcom/amazon/photos/uploader/SchedulingCallback;", "(Lcom/amazon/photos/uploader/internal/device/BatteryState;Lcom/amazon/photos/uploader/SchedulingCallback;)V", "intentFilter", "Landroid/content/IntentFilter;", "getIntentFilter$AndroidPhotosUploader_release", "()Landroid/content/IntentFilter;", "onReceive", "", "context", "Landroid/content/Context;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BatteryStateReceiver extends BroadcastReceiver {
    private final BatteryState batteryState;
    private final SchedulingCallback schedulingCallback;

    public BatteryStateReceiver(@NotNull BatteryState batteryState, @NotNull SchedulingCallback schedulingCallback) {
        Intrinsics.checkParameterIsNotNull(batteryState, "batteryState");
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        this.batteryState = batteryState;
        this.schedulingCallback = schedulingCallback;
    }

    @NotNull
    public final IntentFilter getIntentFilter$AndroidPhotosUploader_release() {
        IntentFilter intentFilter = new IntentFilter();
        for (String str : BatteryState.Companion.getBATTERY_STATUS_CHANGE_ACTION()) {
            intentFilter.addAction(str);
        }
        for (String str2 : BatteryState.Companion.getBATTERY_ACTIONS()) {
            intentFilter.addAction(str2);
        }
        for (String str3 : this.batteryState.getChargingActions()) {
            intentFilter.addAction(str3);
        }
        intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
        return intentFilter;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@Nullable Context context, @Nullable Intent intent) {
        String action;
        if (intent == null || context == null || (action = intent.getAction()) == null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(action, "intent.action ?: return");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new BatteryStateReceiver$onReceive$1(this, action, intent, context, null), 3, null);
    }
}
