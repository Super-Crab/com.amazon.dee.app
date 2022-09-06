package com.amazon.dee.app.ui.clouddrive;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;
import com.amazon.dee.app.R;
/* loaded from: classes12.dex */
public class ViewBoxOverlayView extends View implements IGetImageBounds {
    private static final int DEFAULT_CROPHEIGHT = 426;
    private static final int DEFAULT_CROPWIDTH = 640;
    private static final float DEFAULT_LINE_THICKNESS_DP = 0.5f;
    private static final int DEFAULT_MARGINTOP = 10;
    private static final int DEFAULT_OVERLAY_COLOR = Color.argb(220, 41, 48, 63);
    private static final int SEMI_TRANSPARENT = -1426063361;
    private RectF bitmapRect;
    private Paint borderPaint;
    private float borderThickness;
    private Path clipPath;
    private CropGeometry cropGeometry;
    private int cropWindowHeight;
    private int cropWindowWidth;
    private int marginSide;
    private int marginTop;
    private int overlayColor;

    /* renamed from: com.amazon.dee.app.ui.clouddrive.ViewBoxOverlayView$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$dee$app$ui$clouddrive$CropGeometry = new int[CropGeometry.values().length];

        static {
            try {
                $SwitchMap$com$amazon$dee$app$ui$clouddrive$CropGeometry[CropGeometry.CIRCULAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$ui$clouddrive$CropGeometry[CropGeometry.RECTANGULAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public ViewBoxOverlayView(Context context) {
        super(context);
        this.cropGeometry = CropGeometry.RECTANGULAR;
        init();
    }

    private void init() {
        int i = this.marginTop;
        int i2 = this.cropWindowHeight + i;
        int i3 = this.marginSide;
        int i4 = this.cropWindowWidth + i3;
        this.borderPaint = newBorderPaint();
        setCropRect(new RectF(i3, i, i4, i2));
        this.clipPath = new Path();
    }

    private Paint newBorderPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(SEMI_TRANSPARENT);
        paint.setStrokeWidth(this.borderThickness);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }

    public RectF getCropWindowRect() {
        return this.bitmapRect;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IGetImageBounds
    public Rect getImageBounds() {
        return new Rect((int) CropRect.LEFT.getPosition(), (int) CropRect.TOP.getPosition(), (int) CropRect.RIGHT.getPosition(), (int) CropRect.BOTTOM.getPosition());
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(1, null);
        canvas.save();
        if (this.cropGeometry.ordinal() != 1) {
            this.clipPath.addRect(this.bitmapRect, Path.Direction.CW);
            canvas.clipPath(this.clipPath, Region.Op.DIFFERENCE);
            canvas.drawColor(this.overlayColor);
        } else {
            this.clipPath.addOval(this.bitmapRect, Path.Direction.CW);
            canvas.clipPath(this.clipPath, Region.Op.DIFFERENCE);
            canvas.drawColor(this.overlayColor);
        }
        this.clipPath.reset();
        canvas.restore();
        canvas.drawRect(this.bitmapRect, this.borderPaint);
    }

    public void setCropGeometry(CropGeometry cropGeometry) {
        this.cropGeometry = cropGeometry;
    }

    public void setCropRect(RectF rectF) {
        this.bitmapRect = rectF;
        CropRect.LEFT.setPosition(rectF.left);
        CropRect.RIGHT.setPosition(rectF.right);
        CropRect.TOP.setPosition(rectF.top);
        CropRect.BOTTOM.setPosition(rectF.bottom);
    }

    public ViewBoxOverlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.cropGeometry = CropGeometry.RECTANGULAR;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ViewBoxOverlayView, 0, 0);
        try {
            this.marginTop = obtainStyledAttributes.getDimensionPixelSize(4, 10);
            this.marginSide = 0;
            this.cropWindowWidth = obtainStyledAttributes.getDimensionPixelSize(2, 640);
            this.cropWindowHeight = obtainStyledAttributes.getDimensionPixelSize(1, 426);
            this.overlayColor = obtainStyledAttributes.getColor(7, DEFAULT_OVERLAY_COLOR);
            this.borderThickness = obtainStyledAttributes.getDimension(0, DEFAULT_LINE_THICKNESS_DP);
            obtainStyledAttributes.recycle();
            init();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
