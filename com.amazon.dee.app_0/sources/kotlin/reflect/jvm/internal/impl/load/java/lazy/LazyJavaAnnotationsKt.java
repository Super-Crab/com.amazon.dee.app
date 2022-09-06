package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner;
import org.jetbrains.annotations.NotNull;
/* compiled from: LazyJavaAnnotations.kt */
/* loaded from: classes3.dex */
public final class LazyJavaAnnotationsKt {
    @NotNull
    public static final Annotations resolveAnnotations(@NotNull LazyJavaResolverContext resolveAnnotations, @NotNull JavaAnnotationOwner annotationsOwner) {
        Intrinsics.checkParameterIsNotNull(resolveAnnotations, "$this$resolveAnnotations");
        Intrinsics.checkParameterIsNotNull(annotationsOwner, "annotationsOwner");
        return new LazyJavaAnnotations(resolveAnnotations, annotationsOwner);
    }
}
