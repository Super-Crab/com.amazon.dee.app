package com.amazon.tarazed.annotations;

import android.view.View;
import com.amazon.tarazed.core.types.annotations.AnnotationPoint;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AnnotationPointHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/tarazed/annotations/AnnotationPointHelper;", "", "()V", "compensateForViewOffset", "Lcom/amazon/tarazed/core/types/annotations/AnnotationPoint;", "xInPixels", "", "yInPixels", "view", "Landroid/view/View;", "compensateForViewOffset$TarazedAndroidLibrary_release", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AnnotationPointHelper {
    public static final AnnotationPointHelper INSTANCE = new AnnotationPointHelper();

    private AnnotationPointHelper() {
    }

    @NotNull
    public final AnnotationPoint compensateForViewOffset$TarazedAndroidLibrary_release(float f, float f2, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new AnnotationPoint(f - iArr[0], f2 - iArr[1]);
    }
}
