package com.amazon.comms.ringservice.webrtc;

import android.view.SurfaceView;
import android.view.View;
import com.amazon.comms.calling.service.WebRTCViewRenderer;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import org.webrtc.RendererCommon;
import org.webrtc.WebRTCRendererShim;
/* loaded from: classes12.dex */
public class WebRTCViewRendererImpl implements WebRTCViewRenderer {
    private static final CommsLogger log = CommsLogger.getLogger(WebRTCViewRendererImpl.class);
    private final WebRTCRendererShim<?> mViewRenderer;

    /* renamed from: com.amazon.comms.ringservice.webrtc.WebRTCViewRendererImpl$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$comms$calling$service$WebRTCViewRenderer$ScalingType = new int[WebRTCViewRenderer.ScalingType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$comms$calling$service$WebRTCViewRenderer$ScalingType[WebRTCViewRenderer.ScalingType.SCALE_ASPECT_FIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$WebRTCViewRenderer$ScalingType[WebRTCViewRenderer.ScalingType.SCALE_ASPECT_FILL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$WebRTCViewRenderer$ScalingType[WebRTCViewRenderer.ScalingType.SCALE_ASPECT_BALANCED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public WebRTCViewRendererImpl(WebRTCRendererShim<?> webRTCRendererShim) {
        Preconditions.checkArgument(webRTCRendererShim != null, "SurfaceViewRenderer must be non-null.");
        this.mViewRenderer = webRTCRendererShim;
    }

    private RendererCommon.ScalingType mapScalingTypeForRendererCommon(WebRTCViewRenderer.ScalingType scalingType) {
        RendererCommon.ScalingType scalingType2 = RendererCommon.ScalingType.SCALE_ASPECT_BALANCED;
        int ordinal = scalingType.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return RendererCommon.ScalingType.SCALE_ASPECT_FILL;
            }
            if (ordinal != 2) {
                log.w("UNKNOWN Scaling Type. Defaulting to BALANCED");
                return scalingType2;
            }
            return RendererCommon.ScalingType.SCALE_ASPECT_BALANCED;
        }
        return RendererCommon.ScalingType.SCALE_ASPECT_FIT;
    }

    @Override // com.amazon.comms.calling.service.WebRTCViewRenderer
    public boolean firstFrameReceived() {
        return this.mViewRenderer.firstFrameReceived();
    }

    @Override // com.amazon.comms.calling.service.WebRTCViewRenderer
    public boolean firstFrameRendered() {
        return this.mViewRenderer.firstFrameRendered();
    }

    @Override // com.amazon.comms.calling.service.WebRTCViewRenderer
    public SurfaceView getSurfaceView() {
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetSurfaceView = ");
        outline107.append(this.mViewRenderer.toString());
        commsLogger.d(outline107.toString());
        return (SurfaceView) getView(SurfaceView.class);
    }

    @Override // com.amazon.comms.calling.service.WebRTCViewRenderer
    public <T extends View> T getView(Class<T> cls) {
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getView, view type ");
        GeneratedOutlineSupport1.outline146(cls, outline107, " = ");
        outline107.append(this.mViewRenderer.toString());
        commsLogger.d(outline107.toString());
        try {
            return cls.cast(this.mViewRenderer.mo13076getView());
        } catch (ClassCastException unused) {
            CommsLogger commsLogger2 = log;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Called getView with type ");
            GeneratedOutlineSupport1.outline146(cls, outline1072, " when the renderer contains another view type: ");
            outline1072.append(this.mViewRenderer.mo13076getView().getClass().getName());
            commsLogger2.e(outline1072.toString());
            return null;
        }
    }

    @Override // com.amazon.comms.calling.service.WebRTCViewRenderer
    public void setMirror(boolean z) {
        this.mViewRenderer.setMirror(z);
    }

    @Override // com.amazon.comms.calling.service.WebRTCViewRenderer
    public void setScalingType(WebRTCViewRenderer.ScalingType scalingType) {
        this.mViewRenderer.setScalingType(mapScalingTypeForRendererCommon(scalingType));
    }

    @Override // com.amazon.comms.calling.service.WebRTCViewRenderer
    public void setSurfaceViewShape(WebRTCViewRenderer.SurfaceViewShape surfaceViewShape, int i) {
        if (surfaceViewShape == WebRTCViewRenderer.SurfaceViewShape.CIRCLE) {
            this.mViewRenderer.setShape(WebRTCRendererShim.Shape.Circle, i);
        } else if (surfaceViewShape == WebRTCViewRenderer.SurfaceViewShape.RECTANGLE) {
            this.mViewRenderer.setShape(WebRTCRendererShim.Shape.Rectangle, i);
        } else if (surfaceViewShape != WebRTCViewRenderer.SurfaceViewShape.ROUNDED_RECTANGLE) {
        } else {
            this.mViewRenderer.setShape(WebRTCRendererShim.Shape.RoundedRectangle, i);
        }
    }
}
