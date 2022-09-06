package com.amazon.tarazed.annotations.drawables;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import com.amazon.tarazed.annotations.AnnotationPointKt;
import com.amazon.tarazed.core.types.annotations.AnnotationColor;
import com.amazon.tarazed.core.types.annotations.AnnotationPoint;
import com.amazon.tarazed.core.types.annotations.LineAnnotation;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: LineDrawable.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/tarazed/annotations/drawables/LineDrawable;", "Lcom/amazon/tarazed/annotations/drawables/AnnotationDrawable;", "annotation", "Lcom/amazon/tarazed/core/types/annotations/LineAnnotation;", "annotationView", "Landroid/view/View;", "context", "Landroid/content/Context;", "color", "Lcom/amazon/tarazed/core/types/annotations/AnnotationColor;", "deviceInfo", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "(Lcom/amazon/tarazed/core/types/annotations/LineAnnotation;Landroid/view/View;Landroid/content/Context;Lcom/amazon/tarazed/core/types/annotations/AnnotationColor;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;)V", "p0", "Lcom/amazon/tarazed/core/types/annotations/AnnotationPoint;", "p1", "draw", "", "canvas", "Landroid/graphics/Canvas;", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class LineDrawable extends AnnotationDrawable {
    private final AnnotationPoint p0;
    private final AnnotationPoint p1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LineDrawable(@NotNull LineAnnotation annotation, @NotNull View annotationView, @NotNull Context context, @NotNull AnnotationColor color, @NotNull DeviceInfoUtility deviceInfo) {
        super(context, color);
        Intrinsics.checkParameterIsNotNull(annotation, "annotation");
        Intrinsics.checkParameterIsNotNull(annotationView, "annotationView");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(color, "color");
        Intrinsics.checkParameterIsNotNull(deviceInfo, "deviceInfo");
        this.p0 = AnnotationPointKt.transformForView(annotation.getP0(), annotationView, deviceInfo.is1PDevice());
        this.p1 = AnnotationPointKt.transformForView(annotation.getP1(), annotationView, deviceInfo.is1PDevice());
    }

    @Override // com.amazon.tarazed.annotations.drawables.AnnotationDrawable
    public void draw(@NotNull Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        canvas.drawLine(this.p0.getX(), this.p0.getY(), this.p1.getX(), this.p1.getY(), getPaint());
    }
}
