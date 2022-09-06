package com.amazon.tarazed.core.rotation.android;

import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.rotation.RotationListener;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DisplayRotationListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00122\u00020\u00012\u00020\u0002:\u0001\u0012B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/tarazed/core/rotation/android/DisplayRotationListener;", "Lcom/amazon/tarazed/core/rotation/RotationListener;", "Landroid/hardware/display/DisplayManager$DisplayListener;", "context", "Landroid/content/Context;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "displayManager", "Landroid/hardware/display/DisplayManager;", "onDisplayAdded", "", "displayId", "", "onDisplayChanged", "onDisplayRemoved", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DisplayRotationListener extends RotationListener implements DisplayManager.DisplayListener {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String METRIC_INVALID_DISPLAY = "InvalidDisplay";
    private static final String TAG = "DisplayRotationListener";
    private final Context context;
    private final DisplayManager displayManager;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;

    /* compiled from: DisplayRotationListener.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/rotation/android/DisplayRotationListener$Companion;", "", "()V", "METRIC_INVALID_DISPLAY", "", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DisplayRotationListener(@NotNull Context context, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        super(null, 1, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.context = context;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        Object systemService = this.context.getSystemService("display");
        if (systemService != null) {
            this.displayManager = (DisplayManager) systemService;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.hardware.display.DisplayManager");
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int i) {
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        tarazedSessionLogger.i(TAG, "Display added: " + i);
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int i) {
        if (i != 0) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            tarazedSessionLogger.i(TAG, "Display ID " + i + " is not the default display, ignoring");
            return;
        }
        Display display = this.displayManager.getDisplay(i);
        if (display == null) {
            TarazedSessionLogger tarazedSessionLogger2 = this.logger;
            tarazedSessionLogger2.e(TAG, "Display for ID " + i + " is null");
            return;
        }
        if (!display.isValid()) {
            this.logger.w(TAG, "Display is not valid, bad behavior may occur");
            this.metricsHelper.addCountHighPriority(TAG, METRIC_INVALID_DISPLAY, 1.0d);
        }
        Point point = new Point();
        display.getRealSize(point);
        notifyHandlers(point.x, point.y);
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int i) {
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        tarazedSessionLogger.i(TAG, "Display removed: " + i);
    }
}
