package com.amazon.tarazed.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.tarazed.core.TarazedIntents;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.sessionmanager.TarazedSessionAndroidService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DeviceShutdownReceiver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/tarazed/receiver/DeviceShutdownReceiver;", "Landroid/content/BroadcastReceiver;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;)V", "onReceive", "", "context", "Landroid/content/Context;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DeviceShutdownReceiver extends BroadcastReceiver {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DeviceShutdownReceiver";
    private final TarazedSessionLogger logger;

    /* compiled from: DeviceShutdownReceiver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/receiver/DeviceShutdownReceiver$Companion;", "", "()V", "TAG", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public DeviceShutdownReceiver(@NotNull TarazedSessionLogger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.logger = logger;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        if (Intrinsics.areEqual(intent.getAction(), "android.intent.action.ACTION_SHUTDOWN")) {
            this.logger.i(TAG, "Device is shutting down during session, ending active session.");
            Intent intent2 = new Intent(context, TarazedSessionAndroidService.class);
            intent2.setAction(TarazedIntents.SessionAndroidService.END_SESSION_ON_SHUTDOWN);
            context.startService(intent2);
        }
    }
}
