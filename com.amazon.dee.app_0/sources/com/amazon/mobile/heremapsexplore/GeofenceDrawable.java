package com.amazon.mobile.heremapsexplore;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
/* loaded from: classes13.dex */
public class GeofenceDrawable extends ShapeDrawable {
    private final Paint fillPaint;
    private boolean showBorder;
    private final Paint strokePaint;

    public GeofenceDrawable(int i, int i2) {
        super(new OvalShape());
        this.fillPaint = getPaint();
        this.strokePaint = new Paint(this.fillPaint);
        this.showBorder = false;
        this.fillPaint.setColor(i);
        this.strokePaint.setColor(i2);
        this.strokePaint.setStyle(Paint.Style.STROKE);
    }

    @Override // android.graphics.drawable.ShapeDrawable
    protected void onDraw(Shape shape, Canvas canvas, Paint paint) {
        shape.draw(canvas, paint);
        if (this.showBorder) {
            shape.draw(canvas, this.strokePaint);
        }
    }

    public void setShowBorder(boolean z) {
        this.showBorder = z;
    }

    public void setStrokeWidth(float f) {
        this.strokePaint.setStrokeWidth(f);
    }
}
