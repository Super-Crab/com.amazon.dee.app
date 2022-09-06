package com.amazon.tarazed.annotations;

import android.graphics.Point;
import android.view.View;
import com.amazon.tarazed.core.types.annotations.AnnotationPoint;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AnnotationPoint.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006\b"}, d2 = {"transformForView", "Lcom/amazon/tarazed/core/types/annotations/AnnotationPoint;", "view", "Landroid/view/View;", "is1PDevice", "", "transformForView1P", "transformForView3P", "TarazedAndroidLibrary_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AnnotationPointKt {
    @NotNull
    public static final AnnotationPoint transformForView(@NotNull AnnotationPoint transformForView, @NotNull View view, boolean z) {
        Intrinsics.checkParameterIsNotNull(transformForView, "$this$transformForView");
        Intrinsics.checkParameterIsNotNull(view, "view");
        return z ? transformForView1P(transformForView, view) : transformForView3P(transformForView, view);
    }

    private static final AnnotationPoint transformForView1P(@NotNull AnnotationPoint annotationPoint, View view) {
        Point point = new Point();
        view.getDisplay().getRealSize(point);
        float y = annotationPoint.getY();
        int i = point.y;
        return new AnnotationPoint(annotationPoint.getX() * point.x, (y * i) - (i - view.getHeight()));
    }

    private static final AnnotationPoint transformForView3P(@NotNull AnnotationPoint annotationPoint, View view) {
        Point point = new Point();
        view.getDisplay().getRealSize(point);
        float y = annotationPoint.getY() * point.y;
        return AnnotationPointHelper.INSTANCE.compensateForViewOffset$TarazedAndroidLibrary_release(annotationPoint.getX() * point.x, y, view);
    }
}
