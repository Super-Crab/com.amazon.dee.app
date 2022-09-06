package org.webrtc;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.webrtc.EglBase;
import org.webrtc.RendererCommon;
import org.webrtc.SurfaceViewDrawer;
import org.webrtc.WebRTCRendererShim;
/* loaded from: classes5.dex */
public class SurfaceViewRendererShim extends SurfaceViewRenderer implements WebRTCRendererShim<SurfaceView> {
    private static final int MAX_ALPHA = 255;
    private static final int MIN_ALPHA = 0;
    private static final String TAG = SurfaceViewRendererShim.class.getSimpleName();
    protected final RendererCommon.GlDrawer drawer;
    private final int[] eglBase;
    private boolean firstFrameReceived;
    private boolean firstFrameRendered;
    private final ShapeMode shapeMode;

    /* renamed from: org.webrtc.SurfaceViewRendererShim$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$SurfaceViewDrawer$Shape = new int[SurfaceViewDrawer.Shape.values().length];

        static {
            try {
                $SwitchMap$org$webrtc$SurfaceViewDrawer$Shape[SurfaceViewDrawer.Shape.Circle.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$webrtc$SurfaceViewDrawer$Shape[SurfaceViewDrawer.Shape.Rectangle.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$webrtc$SurfaceViewDrawer$Shape[SurfaceViewDrawer.Shape.RoundedRectangle.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes5.dex */
    public enum ShapeMode {
        Static,
        Dynamic
    }

    public SurfaceViewRendererShim(Context context) {
        this(context, null);
    }

    private static WebRTCRendererShim.Shape convertFromLegacyShape(SurfaceViewDrawer.Shape shape) {
        int ordinal = shape.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return WebRTCRendererShim.Shape.Rectangle;
            }
            if (ordinal == 2) {
                return WebRTCRendererShim.Shape.RoundedRectangle;
            }
            throw new IllegalStateException("Unknown SurfaceView shape specified: " + shape);
        }
        return WebRTCRendererShim.Shape.Circle;
    }

    private int[] getEglBase(ShapeMode shapeMode, WebRTCRendererShim.Shape shape) {
        return (shapeMode == ShapeMode.Static && shape == WebRTCRendererShim.Shape.Rectangle) ? EglBase.CONFIG_PLAIN : EglBase.CONFIG_RGBA;
    }

    @Override // org.webrtc.WebRTCRendererShim
    public void enableRenderBlack(boolean z) {
        GeneratedOutlineSupport1.outline173("enableRenderBlack enable = ", z, TAG);
    }

    @Override // org.webrtc.WebRTCRendererShim
    public boolean firstFrameReceived() {
        return this.firstFrameReceived;
    }

    @Override // org.webrtc.WebRTCRendererShim
    public boolean firstFrameRendered() {
        return this.firstFrameRendered;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.webrtc.WebRTCRendererShim
    /* renamed from: getView */
    public SurfaceView mo13076getView() {
        return this;
    }

    @Override // org.webrtc.SurfaceViewRenderer, org.webrtc.WebRTCRendererShim
    public void init(EglBase.Context context, RendererCommon.RendererEvents rendererEvents) {
        init(context, rendererEvents, this.eglBase, this.drawer);
    }

    @Override // android.view.View
    protected boolean onSetAlpha(int i) {
        if (i >= 0 && i <= 255) {
            RendererCommon.GlDrawer glDrawer = this.drawer;
            if (!(glDrawer instanceof SurfaceViewDrawer)) {
                Log.e(TAG, "Can not set alpha, unsupported drawer or shape.");
                return true;
            }
            ((SurfaceViewDrawer) glDrawer).setAlpha(i / 255.0f);
            return true;
        }
        Log.e(TAG, "Can not set alpha, value is not in valid range.");
        return true;
    }

    @Override // org.webrtc.WebRTCRendererShim
    public void setFirstFrameReceived() {
        this.firstFrameReceived = true;
    }

    @Override // org.webrtc.WebRTCRendererShim
    public void setFirstFrameRendered() {
        this.firstFrameRendered = true;
    }

    public void setFirstFrameRendererd() {
        setFirstFrameRendered();
    }

    @Override // org.webrtc.WebRTCRendererShim
    public void setShape(WebRTCRendererShim.Shape shape, int i) {
        if (this.shapeMode == ShapeMode.Dynamic) {
            RendererCommon.GlDrawer glDrawer = this.drawer;
            if (glDrawer instanceof SurfaceViewDrawer) {
                ((SurfaceViewDrawer) glDrawer).setShape(shape, i);
                return;
            } else {
                Log.e(TAG, String.format("Request to change shape in dynamic mode but drawer is not SurfaceViewDrawer! Shape: %s", shape));
                return;
            }
        }
        Log.w(TAG, String.format("Cannot change static drawer shape: %s", shape));
    }

    public SurfaceViewRendererShim(Context context, AttributeSet attributeSet) {
        this(ShapeMode.Dynamic, WebRTCRendererShim.Shape.Rectangle, 0.0f, context, attributeSet, TAG);
    }

    public SurfaceViewRendererShim(ShapeMode shapeMode, WebRTCRendererShim.Shape shape, Context context) {
        this(shapeMode, shape, 0.0f, context);
    }

    public SurfaceViewRendererShim(ShapeMode shapeMode, WebRTCRendererShim.Shape shape, float f, Context context) {
        this(shapeMode, shape, f, context, (AttributeSet) null, TAG);
    }

    public SurfaceViewRendererShim(ShapeMode shapeMode, WebRTCRendererShim.Shape shape, float f, Context context, AttributeSet attributeSet, String str) {
        super(context, attributeSet);
        this.firstFrameRendered = false;
        this.firstFrameReceived = false;
        this.shapeMode = shapeMode;
        if (this.shapeMode == ShapeMode.Static && shape == WebRTCRendererShim.Shape.Rectangle) {
            this.drawer = new GlRectDrawer();
            GeneratedOutlineSupport1.outline163("Creating GlRectDrawer for ", str, TAG);
        } else {
            this.drawer = new SurfaceViewDrawer(shape, f);
            GeneratedOutlineSupport1.outline163("Creating SurfaceViewDrawer for ", str, TAG);
        }
        this.eglBase = getEglBase(shapeMode, shape);
        if (this.eglBase == EglBase.CONFIG_RGBA) {
            getHolder().setFormat(-3);
        }
    }

    public void setShape(SurfaceViewDrawer.Shape shape, int i) {
        setShape(convertFromLegacyShape(shape), i);
    }

    public SurfaceViewRendererShim(ShapeMode shapeMode, SurfaceViewDrawer.Shape shape, float f, Context context, AttributeSet attributeSet, String str) {
        this(shapeMode, convertFromLegacyShape(shape), f, context, attributeSet, str);
    }
}
