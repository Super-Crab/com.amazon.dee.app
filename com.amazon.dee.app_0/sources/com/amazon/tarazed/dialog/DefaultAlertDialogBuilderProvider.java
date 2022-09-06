package com.amazon.tarazed.dialog;

import android.content.Context;
import com.amazon.tarazed.R;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DefaultAlertDialogBuilderProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/tarazed/dialog/DefaultAlertDialogBuilderProvider;", "Lcom/amazon/tarazed/dialog/AlertDialogBuilderProvider;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;)V", "provideDialogBuilder", "Lcom/amazon/tarazed/dialog/AlertDialogBuilder;", "context", "Landroid/content/Context;", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DefaultAlertDialogBuilderProvider implements AlertDialogBuilderProvider {
    private final DeviceInfoUtility deviceInfoUtility;
    private final TarazedSessionLogger logger;

    public DefaultAlertDialogBuilderProvider(@NotNull TarazedSessionLogger logger, @NotNull DeviceInfoUtility deviceInfoUtility) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        this.logger = logger;
        this.deviceInfoUtility = deviceInfoUtility;
    }

    @Override // com.amazon.tarazed.dialog.AlertDialogBuilderProvider
    @NotNull
    public AlertDialogBuilder provideDialogBuilder(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (this.deviceInfoUtility.isTouchableDevice()) {
            if (this.deviceInfoUtility.is1PDevice()) {
                return new AlertDialogBuilder(context, this.logger, R.style.TouchableDialogDefault);
            }
            return new AlertDialogBuilder(context, this.logger, R.style.TouchableDialog3pTheme);
        }
        return new AlertDialogBuilder(context, this.logger);
    }
}
