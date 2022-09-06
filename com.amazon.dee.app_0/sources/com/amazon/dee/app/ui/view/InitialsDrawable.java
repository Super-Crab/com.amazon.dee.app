package com.amazon.dee.app.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.dee.app.ui.util.UiUtils;
/* loaded from: classes12.dex */
public class InitialsDrawable extends Drawable {
    Paint backgroundPaint;
    Context context;
    String initial;
    float measuredTextWidth;
    Paint textPaint;

    public InitialsDrawable(@NonNull Context context, @NonNull String str) {
        this.initial = str;
        this.context = context;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.backgroundPaint == null) {
            this.backgroundPaint = new Paint();
            this.backgroundPaint.setStyle(Paint.Style.FILL);
            this.backgroundPaint.setColor(-10326664);
            this.backgroundPaint.setFlags(1);
        }
        if (this.textPaint == null) {
            this.textPaint = new Paint();
            this.textPaint.setTypeface(FontResolver.getFont(this.context, Font.AMAZON_EMBER_LIGHT));
            this.textPaint.setStyle(Paint.Style.FILL);
            this.textPaint.setFlags(1);
            this.textPaint.setColor(-986641);
            this.textPaint.setTextSize(UiUtils.transformSpToPx(this.context, 36));
            this.measuredTextWidth = this.textPaint.measureText(this.initial);
        }
        Rect bounds = getBounds();
        if (!bounds.isEmpty()) {
            float width = bounds.width() / 2;
            float height = bounds.height() / 2;
            canvas.drawCircle(width, height, height, this.backgroundPaint);
            canvas.drawText(this.initial, width - (this.measuredTextWidth / 2.0f), height - ((this.textPaint.ascent() + this.textPaint.descent()) / 2.0f), this.textPaint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
