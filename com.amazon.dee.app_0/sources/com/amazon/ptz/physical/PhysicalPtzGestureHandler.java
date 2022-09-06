package com.amazon.ptz.physical;

import android.util.Log;
import com.amazon.alexa.directive.AlexaDirective;
import com.amazon.alexa.rangecontroller.lib.model.serialization.RcSerializer;
import com.amazon.ptz.digital.DigitalZoomState;
import com.amazon.ptz.gestures.Gesture;
import com.amazon.ptz.gestures.GestureType;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.metrics.MetricRecorder;
import com.amazon.ptz.physical.communication.PhysicalPtzCommandCache;
import com.amazon.ptz.physical.communication.PhysicalPtzDirectiveSender;
import com.amazon.ptz.util.Direction;
import com.amazon.ptz.util.DirectiveHelpers;
import com.amazon.ptz.util.LogTag;
import com.amazon.ptzcontroller.lib.model.api.ptz.directive.CameraPtzInstance;
import com.amazon.ptzcontroller.lib.model.factory.PtzDirectiveFactory;
import com.google.common.annotations.VisibleForTesting;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import lombok.Generated;
/* loaded from: classes13.dex */
public class PhysicalPtzGestureHandler implements GestureHandler {
    @Nonnull
    private final List<CameraPtzInstance> cameraPtzInstances;
    @Nonnull
    private final MetricRecorder metricRecorder;
    @Nonnull
    private final PhysicalPtzCommandCache physicalPtzCommandCache;
    @Nonnull
    private final PhysicalPtzDirectiveSender physicalPtzDirectiveSender;
    @Nonnull
    private final RcSerializer rcSerializer;
    @Nonnull
    private final int viewHeightPixels;
    @Nonnull
    private final int viewWidthPixels;
    @Nonnull
    private final DigitalZoomState zoomState;
    private static final String TAG = LogTag.forClass(PhysicalPtzGestureHandler.class);
    private static final List<GestureType> PHYSICAL_GESTURES = Collections.unmodifiableList(Arrays.asList(GestureType.PAN_TILT));

    /* renamed from: com.amazon.ptz.physical.PhysicalPtzGestureHandler$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ptz$util$Direction = new int[Direction.values().length];

        static {
            try {
                $SwitchMap$com$amazon$ptz$util$Direction[Direction.UP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ptz$util$Direction[Direction.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ptz$util$Direction[Direction.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ptz$util$Direction[Direction.LEFT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public PhysicalPtzGestureHandler(@Nonnull PhysicalPtzDirectiveSender physicalPtzDirectiveSender, @Nonnull List<CameraPtzInstance> list, @Nonnull DigitalZoomState digitalZoomState, @Nonnull PhysicalPtzCommandCache physicalPtzCommandCache, @Nonnull int i, @Nonnull int i2, @Nonnull RcSerializer rcSerializer, @Nonnull MetricRecorder metricRecorder) {
        if (physicalPtzDirectiveSender != null) {
            if (list == null) {
                throw new IllegalArgumentException("cameraPtzInstances is null");
            }
            if (digitalZoomState == null) {
                throw new IllegalArgumentException("zoomState is null");
            }
            if (physicalPtzCommandCache == null) {
                throw new IllegalArgumentException("physicalPtzCommandCache is null");
            }
            if (rcSerializer == null) {
                throw new IllegalArgumentException("rcSerializer is null");
            }
            if (metricRecorder == null) {
                throw new IllegalArgumentException("metricRecorder is null");
            }
            this.physicalPtzDirectiveSender = physicalPtzDirectiveSender;
            this.cameraPtzInstances = list;
            this.zoomState = digitalZoomState;
            this.physicalPtzCommandCache = physicalPtzCommandCache;
            this.viewHeightPixels = i;
            this.viewWidthPixels = i2;
            this.rcSerializer = rcSerializer;
            this.metricRecorder = metricRecorder;
            return;
        }
        throw new IllegalArgumentException("physicalPtzDirectiveSender is null");
    }

    @Override // com.amazon.ptz.gestures.handlers.GestureHandler
    public boolean canHandle(Gesture gesture) {
        return PHYSICAL_GESTURES.contains(gesture.getGestureType()) && this.zoomState.isZoomedOut();
    }

    @VisibleForTesting
    AlexaDirective getPtzDirective(Direction direction, float f, float f2, String str) {
        int ordinal = direction.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return PtzDirectiveFactory.forAdjustRangeValue(CameraPtzInstance.CAMERA_TILT, -Math.round((f2 / this.viewHeightPixels) * 100.0f), str);
        }
        if (ordinal != 2 && ordinal != 3) {
            throw new IllegalArgumentException("Unexpected direction encountered in getPtzDirective");
        }
        return PtzDirectiveFactory.forAdjustRangeValue(CameraPtzInstance.CAMERA_PAN, Math.round((f / this.viewWidthPixels) * 100.0f), str);
    }

    @Override // com.amazon.ptz.gestures.handlers.GestureHandler
    public void handle(Gesture gesture) {
        GestureType gestureType = gesture.getGestureType();
        if (!canHandle(gesture)) {
            Log.e(TAG, "Attempted to route an unsupported gesture to the physical route. This gesture type is not supported: " + gestureType);
            return;
        }
        String str = "Handling a gesture of type: " + gestureType;
        float deltaX = gesture.getTransformInfo().getDeltaX();
        float deltaY = gesture.getTransformInfo().getDeltaY();
        Direction direction = Direction.getDirection(deltaX, deltaY);
        CameraPtzInstance mapToPtzInstance = Direction.mapToPtzInstance(direction);
        if (!this.cameraPtzInstances.contains(mapToPtzInstance)) {
            String str2 = "Ignoring passed gesture as the remote camera does not support this capability: " + mapToPtzInstance;
            this.metricRecorder.recordPhysicalPtzRequestNotSupported(mapToPtzInstance);
            return;
        }
        this.metricRecorder.recordPhysicalPtzRequest(mapToPtzInstance);
        String generateCorrelationToken = DirectiveHelpers.generateCorrelationToken();
        AlexaDirective ptzDirective = getPtzDirective(direction, deltaX, deltaY, generateCorrelationToken);
        this.physicalPtzDirectiveSender.sendCommand(this.rcSerializer.toJson(ptzDirective));
        this.physicalPtzCommandCache.onNewCommandSent(generateCorrelationToken, ptzDirective);
    }
}
