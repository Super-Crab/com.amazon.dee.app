package com.amazon.tarazed.rotation;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import com.amazon.tarazed.core.signaling.events.TarazedEvent;
import com.amazon.tarazed.core.types.rotation.Rotation;
import com.amazon.tarazed.core.types.rotation.RotationEvent;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedDisplayListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/tarazed/rotation/TarazedDisplayListener;", "Landroid/hardware/display/DisplayManager$DisplayListener;", "context", "Landroid/content/Context;", "iotManager", "Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "displayManager", "Landroid/hardware/display/DisplayManager;", "sequence", "", "mapRotation", "rotation", "onDisplayAdded", "", "displayId", "onDisplayChanged", "onDisplayRemoved", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedDisplayListener implements DisplayManager.DisplayListener {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String EVENT_TYPE_ROTATION = "rotation";
    private static final String METRIC_ROTATION_FAILURE = "RotationFailure";
    private static final String TAG = "TarazedDisplayListener";
    private final Context context;
    private final DisplayManager displayManager;
    private final TarazedIoTManager iotManager;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    private int sequence;

    /* compiled from: TarazedDisplayListener.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/tarazed/rotation/TarazedDisplayListener$Companion;", "", "()V", "EVENT_TYPE_ROTATION", "", "METRIC_ROTATION_FAILURE", "TAG", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedDisplayListener(@NotNull Context context, @NotNull TarazedIoTManager iotManager, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(iotManager, "iotManager");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.context = context;
        this.iotManager = iotManager;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        Object systemService = this.context.getSystemService("display");
        if (systemService != null) {
            this.displayManager = (DisplayManager) systemService;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.hardware.display.DisplayManager");
    }

    private final int mapRotation(int i) {
        if (i != 0) {
            if (i == 1) {
                return Rotation.ROTATION_90.getDegrees();
            }
            if (i == 2) {
                return Rotation.ROTATION_180.getDegrees();
            }
            if (i == 3) {
                return Rotation.ROTATION_270.getDegrees();
            }
            throw new RotationException(GeneratedOutlineSupport1.outline49("Invalid rotation: ", i));
        }
        return Rotation.ROTATION_0.getDegrees();
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int i) {
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        tarazedSessionLogger.i(TAG, "Display added: " + i);
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int i) {
        if (i != 0) {
            this.logger.i(TAG, "Display ID " + i + " is not the default display, ignoring");
            return;
        }
        Display display = this.displayManager.getDisplay(i);
        if (display == null) {
            this.logger.e(TAG, "Display for ID " + i + " is null");
            this.metricsHelper.addCount(TAG, METRIC_ROTATION_FAILURE, 1.0d);
            return;
        }
        try {
            this.sequence++;
            this.iotManager.sendEvent(Json.Default.stringify(TarazedEvent.Companion.serializer(RotationEvent.Companion.serializer()), new TarazedEvent("rotation", new RotationEvent(this.sequence, mapRotation(display.getRotation())))), AWSIotMqttQos.QOS1);
        } catch (RotationException e) {
            this.logger.e(TAG, "Exception occurred handling rotation", e);
            this.metricsHelper.addCount(TAG, METRIC_ROTATION_FAILURE, 1.0d);
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int i) {
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        tarazedSessionLogger.i(TAG, "Display removed: " + i);
    }
}
