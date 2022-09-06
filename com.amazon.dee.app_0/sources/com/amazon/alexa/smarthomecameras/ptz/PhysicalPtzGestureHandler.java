package com.amazon.alexa.smarthomecameras.ptz;

import android.util.DisplayMetrics;
import android.util.Log;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.smarthomecameras.constants.MobilyticsConstants;
import com.amazon.alexa.smarthomecameras.directives.AlexaDirective;
import com.amazon.alexa.smarthomecameras.directives.DirectiveSender;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.ptz.directives.PhysicalPtzDirectiveFactory;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.util.CamerasLogger;
import com.amazon.ptz.digital.DigitalZoomState;
import com.amazon.ptz.gestures.Gesture;
import com.amazon.ptz.gestures.GestureType;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.util.Direction;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes10.dex */
public class PhysicalPtzGestureHandler implements GestureHandler {
    private static final List<GestureType> PHYSICAL_GESTURES = Collections.unmodifiableList(Arrays.asList(GestureType.PAN_TILT));
    private static final String TAG = "PhysicalPtzGestureHandler";
    private final DevicePayload devicePayload;
    private final PhysicalPtzDirectiveFactory directiveFactory;
    private final DirectiveSender directiveSender;
    private final DisplayMetrics displayMetrics;
    private final FeatureServiceV2 featureServiceV2;
    private final CamerasMobilyticsService metricsService;
    private final DigitalZoomState zoomState;

    /* renamed from: com.amazon.alexa.smarthomecameras.ptz.PhysicalPtzGestureHandler$1  reason: invalid class name */
    /* loaded from: classes10.dex */
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

    public PhysicalPtzGestureHandler(DigitalZoomState digitalZoomState, DisplayMetrics displayMetrics, PhysicalPtzDirectiveFactory physicalPtzDirectiveFactory, DirectiveSender directiveSender, FeatureServiceV2 featureServiceV2, CamerasMobilyticsService camerasMobilyticsService, DevicePayload devicePayload) {
        Preconditions.checkNotNull(digitalZoomState, "DigitalZoomState cannot be null");
        Preconditions.checkNotNull(displayMetrics, "DisplayMetrics cannot be null");
        Preconditions.checkNotNull(physicalPtzDirectiveFactory, "PTZDirectiveFactory cannot be null");
        Preconditions.checkNotNull(directiveSender, "DirectiveSender cannot be null");
        Preconditions.checkNotNull(featureServiceV2, "FeaureQuery cannot be null");
        Preconditions.checkNotNull(camerasMobilyticsService, "Metrics Service cannot be null");
        Preconditions.checkNotNull(devicePayload, "DevicePayload cannot be null");
        this.zoomState = digitalZoomState;
        this.displayMetrics = displayMetrics;
        this.directiveFactory = physicalPtzDirectiveFactory;
        this.directiveSender = directiveSender;
        this.featureServiceV2 = featureServiceV2;
        this.metricsService = camerasMobilyticsService;
        this.devicePayload = devicePayload;
    }

    private AlexaDirective createPtzDirective(Direction direction, float f, float f2) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Creating PTZ Directive: ");
        outline107.append(direction.toString());
        CamerasLogger.logInfo(CamerasLogger.LVC_TAG, outline107.toString());
        int ordinal = direction.ordinal();
        if (ordinal == 0) {
            this.metricsService.recordOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.PHYSICAL_TILT_UP, this.devicePayload.getManufacturerName());
            this.metricsService.recordOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.PHYSICAL_TILT_ENGAGED, this.devicePayload.getManufacturerName());
            return this.directiveFactory.createPhysicalTiltDirective(-Math.round((f2 / this.displayMetrics.heightPixels) * 100.0f));
        } else if (ordinal == 1) {
            this.metricsService.recordOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.PHYSICAL_TILT_DOWN, this.devicePayload.getManufacturerName());
            this.metricsService.recordOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.PHYSICAL_TILT_ENGAGED, this.devicePayload.getManufacturerName());
            return this.directiveFactory.createPhysicalTiltDirective(-Math.round((f2 / this.displayMetrics.heightPixels) * 100.0f));
        } else if (ordinal == 2) {
            this.metricsService.recordOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.PHYSICAL_PAN_LEFT, this.devicePayload.getManufacturerName());
            this.metricsService.recordOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.PHYSICAL_PAN_ENGAGED, this.devicePayload.getManufacturerName());
            return this.directiveFactory.createPhysicalPanDirective(Math.round((f / this.displayMetrics.widthPixels) * 100.0f));
        } else if (ordinal == 3) {
            this.metricsService.recordOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.PHYSICAL_PAN_RIGHT, this.devicePayload.getManufacturerName());
            this.metricsService.recordOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.PHYSICAL_PAN_ENGAGED, this.devicePayload.getManufacturerName());
            return this.directiveFactory.createPhysicalPanDirective(Math.round((f / this.displayMetrics.widthPixels) * 100.0f));
        } else {
            throw new IllegalArgumentException("Unexpected direction encountered in getPtzDirective");
        }
    }

    @Override // com.amazon.ptz.gestures.handlers.GestureHandler
    public boolean canHandle(Gesture gesture) {
        return PHYSICAL_GESTURES.contains(gesture.getGestureType()) && this.zoomState.isZoomedOut();
    }

    @Override // com.amazon.ptz.gestures.handlers.GestureHandler
    public void handle(Gesture gesture) {
        GestureType gestureType = gesture.getGestureType();
        if (!canHandle(gesture)) {
            String str = TAG;
            Log.e(str, "Attempted to route an unsupported gesture tothe physical route. This gesture type is not supported: " + gestureType);
            return;
        }
        CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "Handling a gesture of type: " + gestureType);
        float deltaX = gesture.getTransformInfo().getDeltaX();
        float deltaY = gesture.getTransformInfo().getDeltaY();
        Direction direction = Direction.getDirection(deltaX, deltaY);
        CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "Supporting Physical Tilt in Physical Tilt weblab");
        this.directiveSender.sendDirective(createPtzDirective(direction, deltaX, deltaY));
    }
}
