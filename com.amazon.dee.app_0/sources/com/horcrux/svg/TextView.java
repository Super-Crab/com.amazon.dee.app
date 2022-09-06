package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.view.View;
import android.view.ViewParent;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.horcrux.svg.TextProperties;
import java.util.ArrayList;
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public class TextView extends GroupView {
    double cachedAdvance;
    private TextProperties.AlignmentBaseline mAlignmentBaseline;
    private String mBaselineShift;
    @Nullable
    private ArrayList<SVGLength> mDeltaX;
    @Nullable
    private ArrayList<SVGLength> mDeltaY;
    SVGLength mInlineSize;
    TextProperties.TextLengthAdjust mLengthAdjust;
    @Nullable
    private ArrayList<SVGLength> mPositionX;
    @Nullable
    private ArrayList<SVGLength> mPositionY;
    @Nullable
    private ArrayList<SVGLength> mRotate;
    SVGLength mTextLength;

    public TextView(ReactContext reactContext) {
        super(reactContext);
        this.mInlineSize = null;
        this.mTextLength = null;
        this.mBaselineShift = null;
        this.mLengthAdjust = TextProperties.TextLengthAdjust.spacing;
        this.cachedAdvance = Double.NaN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.VirtualView
    public void clearCache() {
        this.cachedAdvance = Double.NaN;
        super.clearCache();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        setupGlyphContext(canvas);
        clip(canvas, paint);
        getGroupPath(canvas, paint);
        pushGlyphContext();
        drawGroup(canvas, paint, f);
        popGlyphContext();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextProperties.AlignmentBaseline getAlignmentBaseline() {
        TextProperties.AlignmentBaseline alignmentBaseline;
        if (this.mAlignmentBaseline == null) {
            for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
                if ((parent instanceof TextView) && (alignmentBaseline = ((TextView) parent).mAlignmentBaseline) != null) {
                    this.mAlignmentBaseline = alignmentBaseline;
                    return alignmentBaseline;
                }
            }
        }
        if (this.mAlignmentBaseline == null) {
            this.mAlignmentBaseline = TextProperties.AlignmentBaseline.baseline;
        }
        return this.mAlignmentBaseline;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getBaselineShift() {
        String str;
        if (this.mBaselineShift == null) {
            for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
                if ((parent instanceof TextView) && (str = ((TextView) parent).mBaselineShift) != null) {
                    this.mBaselineShift = str;
                    return str;
                }
            }
        }
        return this.mBaselineShift;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Path getGroupPath(Canvas canvas, Paint paint) {
        Path path = ((VirtualView) this).mPath;
        if (path != null) {
            return path;
        }
        pushGlyphContext();
        ((VirtualView) this).mPath = super.getPath(canvas, paint);
        popGlyphContext();
        return ((VirtualView) this).mPath;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = ((VirtualView) this).mPath;
        if (path != null) {
            return path;
        }
        setupGlyphContext(canvas);
        return getGroupPath(canvas, paint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double getSubtreeTextChunksTotalAdvance(Paint paint) {
        if (!Double.isNaN(this.cachedAdvance)) {
            return this.cachedAdvance;
        }
        double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof TextView) {
                d += ((TextView) childAt).getSubtreeTextChunksTotalAdvance(paint);
            }
        }
        this.cachedAdvance = d;
        return d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextView getTextAnchorRoot() {
        ArrayList<FontData> arrayList = getTextRootGlyphContext().mFontContext;
        ViewParent parent = getParent();
        ViewParent viewParent = parent;
        TextView textView = this;
        for (int size = arrayList.size() - 1; size >= 0 && (viewParent instanceof TextView) && arrayList.get(size).textAnchor != TextProperties.TextAnchor.start && textView.mPositionX == null; size--) {
            textView = (TextView) viewParent;
            viewParent = textView.getParent();
        }
        return textView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextView getTextContainer() {
        ViewParent parent = getParent();
        TextView textView = this;
        while (parent instanceof TextView) {
            textView = (TextView) parent;
            parent = textView.getParent();
        }
        return textView;
    }

    @Override // com.horcrux.svg.VirtualView, android.view.View
    public void invalidate() {
        if (((VirtualView) this).mPath == null) {
            return;
        }
        super.invalidate();
        getTextContainer().clearChildCache();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView
    public void pushGlyphContext() {
        getTextRootGlyphContext().pushContext(!(this instanceof TextPathView) && !(this instanceof TSpanView), this, this.mFont, this.mPositionX, this.mPositionY, this.mDeltaX, this.mDeltaY, this.mRotate);
    }

    @ReactProp(name = "baselineShift")
    public void setBaselineShift(Dynamic dynamic) {
        this.mBaselineShift = SVGLength.toString(dynamic);
        invalidate();
    }

    @ReactProp(name = "dx")
    public void setDeltaX(Dynamic dynamic) {
        this.mDeltaX = SVGLength.arrayFrom(dynamic);
        invalidate();
    }

    @ReactProp(name = "dy")
    public void setDeltaY(Dynamic dynamic) {
        this.mDeltaY = SVGLength.arrayFrom(dynamic);
        invalidate();
    }

    @ReactProp(name = "inlineSize")
    public void setInlineSize(Dynamic dynamic) {
        this.mInlineSize = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "lengthAdjust")
    public void setLengthAdjust(@Nullable String str) {
        this.mLengthAdjust = TextProperties.TextLengthAdjust.valueOf(str);
        invalidate();
    }

    @ReactProp(name = "alignmentBaseline")
    public void setMethod(@Nullable String str) {
        this.mAlignmentBaseline = TextProperties.AlignmentBaseline.getEnum(str);
        invalidate();
    }

    @ReactProp(name = ReactProperties.HereMapMarker.X)
    public void setPositionX(Dynamic dynamic) {
        this.mPositionX = SVGLength.arrayFrom(dynamic);
        invalidate();
    }

    @ReactProp(name = ReactProperties.HereMapMarker.Y)
    public void setPositionY(Dynamic dynamic) {
        this.mPositionY = SVGLength.arrayFrom(dynamic);
        invalidate();
    }

    @ReactProp(name = "rotate")
    public void setRotate(Dynamic dynamic) {
        this.mRotate = SVGLength.arrayFrom(dynamic);
        invalidate();
    }

    @ReactProp(name = "textLength")
    public void setTextLength(Dynamic dynamic) {
        this.mTextLength = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "verticalAlign")
    public void setVerticalAlign(@Nullable String str) {
        if (str != null) {
            String trim = str.trim();
            int lastIndexOf = trim.lastIndexOf(32);
            try {
                this.mAlignmentBaseline = TextProperties.AlignmentBaseline.getEnum(trim.substring(lastIndexOf));
            } catch (IllegalArgumentException unused) {
                this.mAlignmentBaseline = TextProperties.AlignmentBaseline.baseline;
            }
            try {
                this.mBaselineShift = trim.substring(0, lastIndexOf);
            } catch (IndexOutOfBoundsException unused2) {
                this.mBaselineShift = null;
            }
        } else {
            this.mAlignmentBaseline = TextProperties.AlignmentBaseline.baseline;
            this.mBaselineShift = null;
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView
    public Path getPath(Canvas canvas, Paint paint, Region.Op op) {
        return getPath(canvas, paint);
    }
}
