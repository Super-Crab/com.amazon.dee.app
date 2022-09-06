package com.amazon.tarazed.annotations.drawables;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.amazon.tarazed.R;
import com.amazon.tarazed.core.types.annotations.AnnotationColor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AnnotationDrawable.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b \u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H&R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/amazon/tarazed/annotations/drawables/AnnotationDrawable;", "", "context", "Landroid/content/Context;", "color", "Lcom/amazon/tarazed/core/types/annotations/AnnotationColor;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/types/annotations/AnnotationColor;)V", "currentStrokeWidth", "", "getCurrentStrokeWidth", "()F", "paint", "Landroid/graphics/Paint;", "getPaint", "()Landroid/graphics/Paint;", "draw", "", "canvas", "Landroid/graphics/Canvas;", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class AnnotationDrawable {
    private final Context context;
    @NotNull
    private final Paint paint;

    public AnnotationDrawable(@NotNull Context context, @NotNull AnnotationColor color) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(color, "color");
        this.context = context;
        this.paint = new Paint();
        this.paint.setDither(true);
        this.paint.setAntiAlias(true);
        this.paint.setColor(Color.rgb(color.getR(), color.getG(), color.getB()));
        this.paint.setStrokeWidth(getCurrentStrokeWidth());
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeCap(Paint.Cap.SQUARE);
        this.paint.setStrokeJoin(Paint.Join.MITER);
    }

    private final float getCurrentStrokeWidth() {
        return this.context.getResources().getDimension(R.dimen.default_stroke_width);
    }

    public abstract void draw(@NotNull Canvas canvas);

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final Paint getPaint() {
        return this.paint;
    }
}
