package com.amazon.alexa.fitness.view.startTab;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageButton;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionCommandSource;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CustomStopButtonView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\fH\u0002J \u0010#\u001a\u00020$2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u000eH\u0002J\u0010\u0010(\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0014J(\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020\tH\u0014J\b\u0010.\u001a\u00020$H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n \u001a*\u0004\u0018\u00010\u00190\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n \u001a*\u0004\u0018\u00010\u001c0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/amazon/alexa/fitness/view/startTab/CustomStopButtonView;", "Landroid/widget/ImageButton;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "angle", "", "backgroundPaint", "Landroid/graphics/Paint;", "bgColor", "value", "currentAnimatedProgress", "getCurrentAnimatedProgress", "()I", "setCurrentAnimatedProgress", "(I)V", "currentProgress", "diameter", "featureQuery", "Lcom/amazon/alexa/protocols/features/FeatureQuery;", "kotlin.jvm.PlatformType", "orchestrator", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;", "progressColor", "progressPaint", "rect", "Landroid/graphics/RectF;", "calculateAngle", "progress", "drawCircle", "", "canvas", "Landroid/graphics/Canvas;", "paint", "onDraw", "onSizeChanged", "width", "height", "oldWidth", "oldHeight", "updateRect", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class CustomStopButtonView extends ImageButton {
    private float angle;
    private final Paint backgroundPaint;
    private int bgColor;
    private int currentAnimatedProgress;
    private int currentProgress;
    private float diameter;
    private final FeatureQuery featureQuery;
    private final FitnessSessionOrchestrator orchestrator;
    private int progressColor;
    private final Paint progressPaint;
    private final RectF rect;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomStopButtonView(@NotNull Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Resources resources = getResources();
        int i = R.color.neutral10;
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        this.progressColor = resources.getColor(i, context2.getTheme());
        Resources resources2 = getResources();
        int i2 = R.color.status10;
        Context context3 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context3, "context");
        this.bgColor = resources2.getColor(i2, context3.getTheme());
        this.orchestrator = (FitnessSessionOrchestrator) GeneratedOutlineSupport1.outline20(FitnessSessionOrchestrator.class);
        this.featureQuery = (FeatureQuery) GeneratedOutlineSupport1.outline20(FeatureQuery.class);
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(getResources().getDimension(R.dimen.stop_button_anim_stroke_width));
        paint.setColor(this.progressColor);
        this.progressPaint = paint;
        Paint paint2 = new Paint(1);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        paint2.setStrokeWidth(getResources().getDimension(R.dimen.stop_button_anim_stroke_width));
        paint2.setColor(this.bgColor);
        this.backgroundPaint = paint2;
        this.rect = new RectF();
    }

    private final float calculateAngle(float f) {
        return f * 3.6f;
    }

    private final void drawCircle(float f, Canvas canvas, Paint paint) {
        canvas.drawArc(this.rect, -90.0f, f, false, paint);
    }

    private final void updateRect() {
        float strokeWidth = this.backgroundPaint.getStrokeWidth();
        float f = this.diameter / 2;
        int i = (int) f;
        Point point = new Point(i, i);
        RectF rectF = this.rect;
        int i2 = point.x;
        rectF.set(((i2 - f) - strokeWidth) + 20.0f, ((i2 - f) - strokeWidth) + 20.0f, ((i2 + f) + strokeWidth) - 20.0f, ((i2 + f) + strokeWidth) - 20.0f);
    }

    public final int getCurrentAnimatedProgress() {
        return this.currentAnimatedProgress;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        int i = this.currentProgress;
        if (i == 0) {
            super.onDraw(canvas);
        } else if (i >= 100) {
            if (i < 100) {
                return;
            }
            Log.i("AFX-CustomStopButtonView", "ending fitness session");
            this.orchestrator.receive(new Command.Stop(SessionCommandSource.GUI), CustomStopButtonView$onDraw$1$1.INSTANCE);
            drawCircle(360.0f, canvas, this.progressPaint);
            super.onDraw(canvas);
        } else {
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            if (!CustomStopButtonViewKt.isDarkThemeOn(context)) {
                Log.i("AFX-CustomStopButtonView", "light mode is enabled, changing opacity of progress arc");
                this.backgroundPaint.setAlpha(39);
                this.progressPaint.setAlpha(128);
            }
            drawCircle(360.0f, canvas, this.backgroundPaint);
            drawCircle(this.angle, canvas, this.progressPaint);
            super.onDraw(canvas);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.diameter = Math.min(i, i2);
        updateRect();
    }

    public final void setCurrentAnimatedProgress(int i) {
        this.currentProgress = i;
        this.angle = calculateAngle(i);
        invalidate();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomStopButtonView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Resources resources = getResources();
        int i = R.color.neutral10;
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        this.progressColor = resources.getColor(i, context2.getTheme());
        Resources resources2 = getResources();
        int i2 = R.color.status10;
        Context context3 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context3, "context");
        this.bgColor = resources2.getColor(i2, context3.getTheme());
        this.orchestrator = (FitnessSessionOrchestrator) GeneratedOutlineSupport1.outline20(FitnessSessionOrchestrator.class);
        this.featureQuery = (FeatureQuery) GeneratedOutlineSupport1.outline20(FeatureQuery.class);
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(getResources().getDimension(R.dimen.stop_button_anim_stroke_width));
        paint.setColor(this.progressColor);
        this.progressPaint = paint;
        Paint paint2 = new Paint(1);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        paint2.setStrokeWidth(getResources().getDimension(R.dimen.stop_button_anim_stroke_width));
        paint2.setColor(this.bgColor);
        this.backgroundPaint = paint2;
        this.rect = new RectF();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomStopButtonView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Resources resources = getResources();
        int i2 = R.color.neutral10;
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        this.progressColor = resources.getColor(i2, context2.getTheme());
        Resources resources2 = getResources();
        int i3 = R.color.status10;
        Context context3 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context3, "context");
        this.bgColor = resources2.getColor(i3, context3.getTheme());
        this.orchestrator = (FitnessSessionOrchestrator) GeneratedOutlineSupport1.outline20(FitnessSessionOrchestrator.class);
        this.featureQuery = (FeatureQuery) GeneratedOutlineSupport1.outline20(FeatureQuery.class);
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(getResources().getDimension(R.dimen.stop_button_anim_stroke_width));
        paint.setColor(this.progressColor);
        this.progressPaint = paint;
        Paint paint2 = new Paint(1);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        paint2.setStrokeWidth(getResources().getDimension(R.dimen.stop_button_anim_stroke_width));
        paint2.setColor(this.bgColor);
        this.backgroundPaint = paint2;
        this.rect = new RectF();
    }
}
