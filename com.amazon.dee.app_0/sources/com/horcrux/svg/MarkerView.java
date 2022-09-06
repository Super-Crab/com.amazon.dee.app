package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public class MarkerView extends GroupView {
    String mAlign;
    private SVGLength mMarkerHeight;
    private String mMarkerUnits;
    private SVGLength mMarkerWidth;
    int mMeetOrSlice;
    private float mMinX;
    private float mMinY;
    private String mOrient;
    private SVGLength mRefX;
    private SVGLength mRefY;
    private float mVbHeight;
    private float mVbWidth;
    Matrix markerTransform;

    public MarkerView(ReactContext reactContext) {
        super(reactContext);
        this.markerTransform = new Matrix();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void renderMarker(Canvas canvas, Paint paint, float f, RNSVGMarkerPosition rNSVGMarkerPosition, float f2) {
        int saveAndSetupCanvas = saveAndSetupCanvas(canvas, this.mCTM);
        this.markerTransform.reset();
        Point point = rNSVGMarkerPosition.origin;
        Matrix matrix = this.markerTransform;
        float f3 = this.mScale;
        matrix.setTranslate(((float) point.x) * f3, ((float) point.y) * f3);
        double parseDouble = "auto".equals(this.mOrient) ? -1.0d : Double.parseDouble(this.mOrient);
        if (parseDouble == -1.0d) {
            parseDouble = rNSVGMarkerPosition.angle;
        }
        this.markerTransform.preRotate(((float) parseDouble) + 180.0f);
        if ("strokeWidth".equals(this.mMarkerUnits)) {
            this.markerTransform.preScale(f2, f2);
        }
        RectF rectF = new RectF(0.0f, 0.0f, (float) (relativeOnWidth(this.mMarkerWidth) / this.mScale), (float) (relativeOnHeight(this.mMarkerHeight) / this.mScale));
        if (this.mAlign != null) {
            float f4 = this.mMinX;
            float f5 = this.mScale;
            float f6 = this.mMinY;
            Matrix transform = ViewBox.getTransform(new RectF(f4 * f5, f6 * f5, (f4 + this.mVbWidth) * f5, (f6 + this.mVbHeight) * f5), rectF, this.mAlign, this.mMeetOrSlice);
            float[] fArr = new float[9];
            transform.getValues(fArr);
            this.markerTransform.preScale(fArr[0], fArr[4]);
        }
        this.markerTransform.preTranslate((float) (-relativeOnWidth(this.mRefX)), (float) (-relativeOnHeight(this.mRefY)));
        canvas.concat(this.markerTransform);
        drawGroup(canvas, paint, f);
        restoreCanvas(canvas, saveAndSetupCanvas);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.VirtualView
    public void saveDefinition() {
        if (this.mName != null) {
            getSvgView().defineMarker(this, this.mName);
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt instanceof VirtualView) {
                    ((VirtualView) childAt).saveDefinition();
                }
            }
        }
    }

    @ReactProp(name = "align")
    public void setAlign(String str) {
        this.mAlign = str;
        invalidate();
    }

    @ReactProp(name = "markerHeight")
    public void setMarkerHeight(Dynamic dynamic) {
        this.mMarkerHeight = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "markerUnits")
    public void setMarkerUnits(String str) {
        this.mMarkerUnits = str;
        invalidate();
    }

    @ReactProp(name = "markerWidth")
    public void setMarkerWidth(Dynamic dynamic) {
        this.mMarkerWidth = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "meetOrSlice")
    public void setMeetOrSlice(int i) {
        this.mMeetOrSlice = i;
        invalidate();
    }

    @ReactProp(name = "minX")
    public void setMinX(float f) {
        this.mMinX = f;
        invalidate();
    }

    @ReactProp(name = "minY")
    public void setMinY(float f) {
        this.mMinY = f;
        invalidate();
    }

    @ReactProp(name = "orient")
    public void setOrient(String str) {
        this.mOrient = str;
        invalidate();
    }

    @ReactProp(name = "refX")
    public void setRefX(Dynamic dynamic) {
        this.mRefX = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "refY")
    public void setRefY(Dynamic dynamic) {
        this.mRefY = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "vbHeight")
    public void setVbHeight(float f) {
        this.mVbHeight = f;
        invalidate();
    }

    @ReactProp(name = "vbWidth")
    public void setVbWidth(float f) {
        this.mVbWidth = f;
        invalidate();
    }
}
