package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.OnLayoutEvent;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import com.horcrux.svg.SVGLength;
import java.util.ArrayList;
import javax.annotation.Nullable;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public abstract class VirtualView extends ReactViewGroup {
    private static final int CLIP_RULE_EVENODD = 0;
    static final int CLIP_RULE_NONZERO = 1;
    private static final double M_SQRT1_2l = 0.7071067811865476d;
    private static final float[] sRawMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private double canvasDiagonal;
    private float canvasHeight;
    private float canvasWidth;
    ArrayList<PathElement> elements;
    private double fontSize;
    private GlyphContext glyphContext;
    RectF mBox;
    Matrix mCTM;
    boolean mCTMInvertible;
    private Path mCachedClipPath;
    private RectF mClientRect;
    RectF mClipBounds;
    @Nullable
    private String mClipPath;
    Region mClipRegion;
    Path mClipRegionPath;
    int mClipRule;
    final ReactContext mContext;
    String mDisplay;
    RectF mFillBounds;
    Path mFillPath;
    Matrix mInvCTM;
    Matrix mInvMatrix;
    final Matrix mInvTransform;
    boolean mInvertible;
    RectF mMarkerBounds;
    @Nullable
    String mMarkerEnd;
    @Nullable
    String mMarkerMid;
    Path mMarkerPath;
    Region mMarkerRegion;
    @Nullable
    String mMarkerStart;
    @Nullable
    String mMask;
    Matrix mMatrix;
    String mName;
    private boolean mOnLayout;
    float mOpacity;
    Path mPath;
    PointerEvents mPointerEvents;
    Region mRegion;
    private boolean mResponsible;
    final float mScale;
    RectF mStrokeBounds;
    Path mStrokePath;
    Region mStrokeRegion;
    private GroupView mTextRoot;
    Matrix mTransform;
    boolean mTransformInvertible;
    private SvgView svgView;

    /* renamed from: com.horcrux.svg.VirtualView$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$SVGLength$UnitType = new int[SVGLength.UnitType.values().length];

        static {
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.EMS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.EXS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.CM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.MM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.IN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.PT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.PC.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VirtualView(ReactContext reactContext) {
        super(reactContext);
        this.mOpacity = 1.0f;
        this.mCTM = new Matrix();
        this.mMatrix = new Matrix();
        this.mTransform = new Matrix();
        this.mInvCTM = new Matrix();
        this.mInvMatrix = new Matrix();
        this.mInvTransform = new Matrix();
        this.mInvertible = true;
        this.mCTMInvertible = true;
        this.mTransformInvertible = true;
        this.fontSize = -1.0d;
        this.canvasDiagonal = -1.0d;
        this.canvasHeight = -1.0f;
        this.canvasWidth = -1.0f;
        this.mContext = reactContext;
        this.mScale = DisplayMetricsHolder.getScreenDisplayMetrics().density;
    }

    private void clearParentCache() {
        VirtualView virtualView = this;
        while (true) {
            ViewParent parent = virtualView.getParent();
            if (!(parent instanceof VirtualView)) {
                return;
            }
            virtualView = (VirtualView) parent;
            if (virtualView.mPath == null) {
                return;
            }
            virtualView.clearCache();
        }
    }

    private double fromRelativeFast(SVGLength sVGLength) {
        double fontSizeFromContext;
        switch (sVGLength.unit.ordinal()) {
            case 3:
                fontSizeFromContext = getFontSizeFromContext();
                break;
            case 4:
                fontSizeFromContext = getFontSizeFromContext() / 2.0d;
                break;
            case 5:
            default:
                fontSizeFromContext = 1.0d;
                break;
            case 6:
                fontSizeFromContext = 35.43307d;
                break;
            case 7:
                fontSizeFromContext = 3.543307d;
                break;
            case 8:
                fontSizeFromContext = 90.0d;
                break;
            case 9:
                fontSizeFromContext = 1.25d;
                break;
            case 10:
                fontSizeFromContext = 15.0d;
                break;
        }
        return sVGLength.value * fontSizeFromContext * this.mScale;
    }

    private double getCanvasDiagonal() {
        double d = this.canvasDiagonal;
        if (d != -1.0d) {
            return d;
        }
        this.canvasDiagonal = Math.sqrt(Math.pow(getCanvasHeight(), 2.0d) + Math.pow(getCanvasWidth(), 2.0d)) * M_SQRT1_2l;
        return this.canvasDiagonal;
    }

    private float getCanvasHeight() {
        float f = this.canvasHeight;
        if (f != -1.0f) {
            return f;
        }
        GroupView textRoot = getTextRoot();
        if (textRoot == null) {
            this.canvasHeight = getSvgView().getCanvasBounds().height();
        } else {
            this.canvasHeight = textRoot.getGlyphContext().getHeight();
        }
        return this.canvasHeight;
    }

    private float getCanvasWidth() {
        float f = this.canvasWidth;
        if (f != -1.0f) {
            return f;
        }
        GroupView textRoot = getTextRoot();
        if (textRoot == null) {
            this.canvasWidth = getSvgView().getCanvasBounds().width();
        } else {
            this.canvasWidth = textRoot.getGlyphContext().getWidth();
        }
        return this.canvasWidth;
    }

    private double getFontSizeFromContext() {
        double d = this.fontSize;
        if (d != -1.0d) {
            return d;
        }
        GroupView textRoot = getTextRoot();
        if (textRoot == null) {
            return 12.0d;
        }
        if (this.glyphContext == null) {
            this.glyphContext = textRoot.getGlyphContext();
        }
        this.fontSize = this.glyphContext.getFontSize();
        return this.fontSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearCache() {
        this.canvasDiagonal = -1.0d;
        this.canvasHeight = -1.0f;
        this.canvasWidth = -1.0f;
        this.fontSize = -1.0d;
        this.mStrokeRegion = null;
        this.mMarkerRegion = null;
        this.mRegion = null;
        this.mPath = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearChildCache() {
        clearCache();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof VirtualView) {
                ((VirtualView) childAt).clearChildCache();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clip(Canvas canvas, Paint paint) {
        Path clipPath = getClipPath(canvas, paint);
        if (clipPath != null) {
            canvas.clipPath(clipPath);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void draw(Canvas canvas, Paint paint, float f);

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectF getClientRect() {
        return this.mClientRect;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Path getClipPath() {
        return this.mCachedClipPath;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public GroupView getParentTextRoot() {
        ViewParent parent = getParent();
        if (!(parent instanceof VirtualView)) {
            return null;
        }
        return ((VirtualView) parent).getTextRoot();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Path getPath(Canvas canvas, Paint paint);

    /* JADX INFO: Access modifiers changed from: package-private */
    public SvgView getSvgView() {
        SvgView svgView = this.svgView;
        if (svgView != null) {
            return svgView;
        }
        ViewParent parent = getParent();
        if (parent == null) {
            return null;
        }
        if (parent instanceof SvgView) {
            this.svgView = (SvgView) parent;
        } else if (parent instanceof VirtualView) {
            this.svgView = ((VirtualView) parent).getSvgView();
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RNSVG: ");
            outline107.append(getClass().getName());
            outline107.append(" should be descendant of a SvgView.");
            FLog.e(ReactConstants.TAG, outline107.toString());
        }
        return this.svgView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public GroupView getTextRoot() {
        if (this.mTextRoot == null) {
            VirtualView virtualView = this;
            while (true) {
                if (virtualView == null) {
                    break;
                }
                if (virtualView instanceof GroupView) {
                    GroupView groupView = (GroupView) virtualView;
                    if (groupView.getGlyphContext() != null) {
                        this.mTextRoot = groupView;
                        break;
                    }
                }
                ViewParent parent = virtualView.getParent();
                virtualView = !(parent instanceof VirtualView) ? null : (VirtualView) parent;
            }
        }
        return this.mTextRoot;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int hitTest(float[] fArr);

    @Override // android.view.View
    public void invalidate() {
        if (!(this instanceof RenderableView) || this.mPath != null) {
            clearCache();
            clearParentCache();
            super.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isResponsible() {
        return this.mResponsible;
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        RectF rectF = this.mClientRect;
        if (rectF == null) {
            return;
        }
        if (!(this instanceof GroupView)) {
            setLeft((int) Math.floor(rectF.left));
            setTop((int) Math.floor(this.mClientRect.top));
            setRight((int) Math.ceil(this.mClientRect.right));
            setBottom((int) Math.ceil(this.mClientRect.bottom));
        }
        setMeasuredDimension((int) Math.ceil(this.mClientRect.width()), (int) Math.ceil(this.mClientRect.height()));
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
    protected void onMeasure(int i, int i2) {
        int defaultSize;
        int defaultSize2;
        RectF rectF = this.mClientRect;
        if (rectF != null) {
            defaultSize = (int) Math.ceil(rectF.width());
        } else {
            defaultSize = ViewGroup.getDefaultSize(getSuggestedMinimumWidth(), i);
        }
        RectF rectF2 = this.mClientRect;
        if (rectF2 != null) {
            defaultSize2 = (int) Math.ceil(rectF2.height());
        } else {
            defaultSize2 = ViewGroup.getDefaultSize(getSuggestedMinimumHeight(), i2);
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double relativeOnHeight(SVGLength sVGLength) {
        double d;
        float canvasHeight;
        SVGLength.UnitType unitType = sVGLength.unit;
        if (unitType == SVGLength.UnitType.NUMBER) {
            d = sVGLength.value;
            canvasHeight = this.mScale;
        } else if (unitType == SVGLength.UnitType.PERCENTAGE) {
            d = sVGLength.value / 100.0d;
            canvasHeight = getCanvasHeight();
        } else {
            return fromRelativeFast(sVGLength);
        }
        return d * canvasHeight;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double relativeOnOther(SVGLength sVGLength) {
        double d;
        double canvasDiagonal;
        SVGLength.UnitType unitType = sVGLength.unit;
        if (unitType == SVGLength.UnitType.NUMBER) {
            d = sVGLength.value;
            canvasDiagonal = this.mScale;
        } else if (unitType == SVGLength.UnitType.PERCENTAGE) {
            d = sVGLength.value / 100.0d;
            canvasDiagonal = getCanvasDiagonal();
        } else {
            return fromRelativeFast(sVGLength);
        }
        return d * canvasDiagonal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double relativeOnWidth(SVGLength sVGLength) {
        double d;
        float canvasWidth;
        SVGLength.UnitType unitType = sVGLength.unit;
        if (unitType == SVGLength.UnitType.NUMBER) {
            d = sVGLength.value;
            canvasWidth = this.mScale;
        } else if (unitType == SVGLength.UnitType.PERCENTAGE) {
            d = sVGLength.value / 100.0d;
            canvasWidth = getCanvasWidth();
        } else {
            return fromRelativeFast(sVGLength);
        }
        return d * canvasWidth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void render(Canvas canvas, Paint paint, float f) {
        draw(canvas, paint, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void restoreCanvas(Canvas canvas, int i) {
        canvas.restoreToCount(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int saveAndSetupCanvas(Canvas canvas, Matrix matrix) {
        int save = canvas.save();
        this.mCTM.setConcat(this.mMatrix, this.mTransform);
        canvas.concat(this.mCTM);
        this.mCTM.preConcat(matrix);
        this.mCTMInvertible = this.mCTM.invert(this.mInvCTM);
        return save;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void saveDefinition() {
        if (this.mName != null) {
            getSvgView().defineTemplate(this, this.mName);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setClientRect(RectF rectF) {
        RectF rectF2 = this.mClientRect;
        if (rectF2 == null || !rectF2.equals(rectF)) {
            this.mClientRect = rectF;
            RectF rectF3 = this.mClientRect;
            if (rectF3 == null) {
                return;
            }
            int ceil = (int) Math.ceil(rectF3.width());
            int ceil2 = (int) Math.ceil(this.mClientRect.height());
            int floor = (int) Math.floor(this.mClientRect.left);
            int floor2 = (int) Math.floor(this.mClientRect.top);
            int ceil3 = (int) Math.ceil(this.mClientRect.right);
            int ceil4 = (int) Math.ceil(this.mClientRect.bottom);
            setMeasuredDimension(ceil, ceil2);
            if (!(this instanceof GroupView)) {
                setLeft(floor);
                setTop(floor2);
                setRight(ceil3);
                setBottom(ceil4);
            }
            if (!this.mOnLayout) {
                return;
            }
            ((UIManagerModule) this.mContext.getNativeModule(UIManagerModule.class)).mo6949getEventDispatcher().dispatchEvent(OnLayoutEvent.obtain(getId(), floor, floor2, ceil, ceil2));
        }
    }

    @ReactProp(name = "clipPath")
    public void setClipPath(String str) {
        this.mCachedClipPath = null;
        this.mClipPath = str;
        invalidate();
    }

    @ReactProp(defaultInt = 1, name = "clipRule")
    public void setClipRule(int i) {
        this.mClipRule = i;
        invalidate();
    }

    @ReactProp(name = "display")
    public void setDisplay(String str) {
        this.mDisplay = str;
        invalidate();
    }

    @ReactProp(name = "markerEnd")
    public void setMarkerEnd(String str) {
        this.mMarkerEnd = str;
        invalidate();
    }

    @ReactProp(name = "markerMid")
    public void setMarkerMid(String str) {
        this.mMarkerMid = str;
        invalidate();
    }

    @ReactProp(name = "markerStart")
    public void setMarkerStart(String str) {
        this.mMarkerStart = str;
        invalidate();
    }

    @ReactProp(name = "mask")
    public void setMask(String str) {
        this.mMask = str;
        invalidate();
    }

    @ReactProp(name = "matrix")
    public void setMatrix(Dynamic dynamic) {
        ReadableType type = dynamic.getType();
        if (!dynamic.isNull() && type.equals(ReadableType.Array)) {
            int matrixData = PropHelper.toMatrixData(dynamic.asArray(), sRawMatrix, this.mScale);
            if (matrixData == 6) {
                if (this.mMatrix == null) {
                    this.mMatrix = new Matrix();
                    this.mInvMatrix = new Matrix();
                }
                this.mMatrix.setValues(sRawMatrix);
                this.mInvertible = this.mMatrix.invert(this.mInvMatrix);
            } else if (matrixData != -1) {
                FLog.w(ReactConstants.TAG, "RNSVG: Transform matrices must be of size 6");
            }
        } else {
            this.mMatrix.reset();
            this.mInvMatrix.reset();
            this.mInvertible = true;
        }
        super.invalidate();
        clearParentCache();
    }

    @ReactProp(name = "name")
    public void setName(String str) {
        this.mName = str;
        invalidate();
    }

    @ReactProp(name = ViewProps.ON_LAYOUT)
    public void setOnLayout(boolean z) {
        this.mOnLayout = z;
        invalidate();
    }

    @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
    public void setOpacity(float f) {
        this.mOpacity = f;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPointerEvents(PointerEvents pointerEvents) {
        this.mPointerEvents = pointerEvents;
    }

    @ReactProp(name = "responsible")
    public void setResponsible(boolean z) {
        this.mResponsible = z;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Path getClipPath(Canvas canvas, Paint paint) {
        if (this.mClipPath != null) {
            ClipPathView clipPathView = (ClipPathView) getSvgView().getDefinedClipPath(this.mClipPath);
            if (clipPathView != null) {
                Path path = this.mClipRule == 0 ? clipPathView.getPath(canvas, paint) : clipPathView.getPath(canvas, paint, Region.Op.UNION);
                path.transform(clipPathView.mMatrix);
                path.transform(clipPathView.mTransform);
                int i = this.mClipRule;
                if (i == 0) {
                    path.setFillType(Path.FillType.EVEN_ODD);
                } else if (i != 1) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RNSVG: clipRule: ");
                    outline107.append(this.mClipRule);
                    outline107.append(" unrecognized");
                    FLog.w(ReactConstants.TAG, outline107.toString());
                }
                this.mCachedClipPath = path;
            } else {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("RNSVG: Undefined clipPath: ");
                outline1072.append(this.mClipPath);
                FLog.w(ReactConstants.TAG, outline1072.toString());
            }
        }
        return getClipPath();
    }
}
