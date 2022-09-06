package com.amazon.tarazed.annotations.drawables;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.tarazed.annotations.AnnotationPointKt;
import com.amazon.tarazed.core.types.annotations.AnnotationColor;
import com.amazon.tarazed.core.types.annotations.AnnotationPoint;
import com.amazon.tarazed.core.types.annotations.FreeformAnnotation;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FreeformDrawable.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0013\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0001¢\u0006\u0002\b\u001aJ\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u001d\u001a\u00020\u0016H\u0002R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/tarazed/annotations/drawables/FreeformDrawable;", "Lcom/amazon/tarazed/annotations/drawables/AnnotationDrawable;", "annotation", "Lcom/amazon/tarazed/core/types/annotations/FreeformAnnotation;", "annotationView", "Landroid/view/View;", "context", "Landroid/content/Context;", "color", "Lcom/amazon/tarazed/core/types/annotations/AnnotationColor;", "deviceInfo", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "(Lcom/amazon/tarazed/core/types/annotations/FreeformAnnotation;Landroid/view/View;Landroid/content/Context;Lcom/amazon/tarazed/core/types/annotations/AnnotationColor;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;)V", "firstControlPoints", "", "Lcom/amazon/tarazed/core/types/annotations/AnnotationPoint;", RouteParameter.PATH, "secondControlPoints", "computeFirstControlValue", "", "rhs", "draw", "", "canvas", "Landroid/graphics/Canvas;", "getPath", "getPath$TarazedAndroidLibrary_release", "handleOnePoint", "handleTwoPoints", "updateControlPoints", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class FreeformDrawable extends AnnotationDrawable {
    private final List<AnnotationPoint> firstControlPoints;
    private final List<AnnotationPoint> path;
    private final List<AnnotationPoint> secondControlPoints;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FreeformDrawable(@NotNull FreeformAnnotation annotation, @NotNull View annotationView, @NotNull Context context, @NotNull AnnotationColor color, @NotNull DeviceInfoUtility deviceInfo) {
        super(context, color);
        Intrinsics.checkParameterIsNotNull(annotation, "annotation");
        Intrinsics.checkParameterIsNotNull(annotationView, "annotationView");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(color, "color");
        Intrinsics.checkParameterIsNotNull(deviceInfo, "deviceInfo");
        this.firstControlPoints = new ArrayList();
        this.secondControlPoints = new ArrayList();
        this.path = new ArrayList();
        getPaint().setStrokeCap(Paint.Cap.ROUND);
        getPaint().setStrokeJoin(Paint.Join.ROUND);
        for (AnnotationPoint annotationPoint : annotation.getPath()) {
            this.path.add(AnnotationPointKt.transformForView(annotationPoint, annotationView, deviceInfo.is1PDevice()));
        }
    }

    private final double[] computeFirstControlValue(double[] dArr) {
        int length = dArr.length;
        double[] dArr2 = new double[length];
        double[] dArr3 = new double[length];
        double d = 2.0d;
        dArr2[0] = dArr[0] / 2.0d;
        int i = 1;
        while (i < length) {
            dArr3[i] = 1 / d;
            d = (i < length + (-1) ? 4.0d : 3.5d) - dArr3[i];
            dArr2[i] = (dArr[i] - dArr2[i - 1]) / d;
            i++;
        }
        for (int i2 = 1; i2 < length; i2++) {
            int i3 = length - i2;
            int i4 = i3 - 1;
            dArr2[i4] = dArr2[i4] - (dArr3[i3] * dArr2[i3]);
        }
        return dArr2;
    }

    private final void handleOnePoint(Canvas canvas) {
        AnnotationPoint annotationPoint = this.path.get(0);
        canvas.drawPoint(annotationPoint.component1(), annotationPoint.component2(), getPaint());
    }

    private final void handleTwoPoints(Canvas canvas) {
        AnnotationPoint annotationPoint = this.path.get(0);
        AnnotationPoint annotationPoint2 = this.path.get(1);
        if (Intrinsics.areEqual(annotationPoint, annotationPoint2)) {
            canvas.drawPoint(annotationPoint.getX(), annotationPoint.getY(), getPaint());
        } else {
            canvas.drawLine(annotationPoint.getX(), annotationPoint.getY(), annotationPoint2.getX(), annotationPoint2.getY(), getPaint());
        }
    }

    private final void updateControlPoints() {
        int i;
        float x;
        double y;
        int i2;
        int i3;
        this.firstControlPoints.clear();
        this.secondControlPoints.clear();
        int size = this.path.size() - 1;
        double[] dArr = new double[size];
        int i4 = size - 1;
        int i5 = 1;
        while (true) {
            i = 4;
            if (i5 >= i4) {
                break;
            }
            dArr[i5] = (this.path.get(i3).getX() * 2) + (this.path.get(i5).getX() * 4);
            i5++;
        }
        float f = 2;
        dArr[0] = (this.path.get(1).getX() * f) + this.path.get(0).getX();
        float f2 = 8;
        dArr[i4] = (this.path.get(size).getX() + (this.path.get(i4).getX() * f2)) / 2.0d;
        double[] computeFirstControlValue = computeFirstControlValue(dArr);
        int i6 = 1;
        while (i6 < i4) {
            float y2 = this.path.get(i6).getY() * i;
            dArr[i6] = (this.path.get(i2).getY() * f) + y2;
            i6++;
            i = 4;
        }
        dArr[0] = (this.path.get(1).getY() * f) + this.path.get(0).getY();
        dArr[i4] = (this.path.get(size).getY() + (this.path.get(i4).getY() * f2)) / 2.0d;
        double[] computeFirstControlValue2 = computeFirstControlValue(dArr);
        for (int i7 = 0; i7 < size; i7++) {
            AnnotationPoint annotationPoint = new AnnotationPoint((float) computeFirstControlValue[i7], (float) computeFirstControlValue2[i7]);
            if (i7 < i4) {
                int i8 = i7 + 1;
                x = (float) ((this.path.get(i8).getX() * f) - computeFirstControlValue[i8]);
                y = (this.path.get(i8).getY() * f) - computeFirstControlValue2[i8];
            } else {
                double d = 2;
                x = (float) ((this.path.get(size).getX() + computeFirstControlValue[i4]) / d);
                y = (this.path.get(size).getY() + computeFirstControlValue2[i4]) / d;
            }
            AnnotationPoint annotationPoint2 = new AnnotationPoint(x, (float) y);
            this.firstControlPoints.add(annotationPoint);
            this.secondControlPoints.add(annotationPoint2);
        }
    }

    @Override // com.amazon.tarazed.annotations.drawables.AnnotationDrawable
    public void draw(@NotNull Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        if (this.path.size() == 0) {
            return;
        }
        if (this.path.size() == 1) {
            handleOnePoint(canvas);
        } else if (this.path.size() == 2) {
            handleTwoPoints(canvas);
        } else {
            updateControlPoints();
            AnnotationPoint annotationPoint = this.path.get(0);
            float component1 = annotationPoint.component1();
            float component2 = annotationPoint.component2();
            Path path = new Path();
            path.moveTo(component1, component2);
            int size = this.path.size();
            for (int i = 1; i < size; i++) {
                AnnotationPoint annotationPoint2 = this.path.get(i);
                float component12 = annotationPoint2.component1();
                float component22 = annotationPoint2.component2();
                int i2 = i - 1;
                AnnotationPoint annotationPoint3 = this.firstControlPoints.get(i2);
                float component13 = annotationPoint3.component1();
                float component23 = annotationPoint3.component2();
                AnnotationPoint annotationPoint4 = this.secondControlPoints.get(i2);
                path.cubicTo(component13, component23, annotationPoint4.component1(), annotationPoint4.component2(), component12, component22);
            }
            canvas.drawPath(path, getPaint());
        }
    }

    @VisibleForTesting
    @NotNull
    public final List<AnnotationPoint> getPath$TarazedAndroidLibrary_release() {
        return this.path;
    }
}
