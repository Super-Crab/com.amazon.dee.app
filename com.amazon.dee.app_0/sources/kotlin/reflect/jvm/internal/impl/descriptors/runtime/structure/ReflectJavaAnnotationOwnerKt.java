package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectJavaAnnotationOwner.kt */
/* loaded from: classes2.dex */
public final class ReflectJavaAnnotationOwnerKt {
    @Nullable
    public static final ReflectJavaAnnotation findAnnotation(@NotNull Annotation[] findAnnotation, @NotNull FqName fqName) {
        Annotation annotation;
        Intrinsics.checkParameterIsNotNull(findAnnotation, "$this$findAnnotation");
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        int length = findAnnotation.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                annotation = null;
                break;
            }
            annotation = findAnnotation[i];
            if (Intrinsics.areEqual(ReflectClassUtilKt.getClassId(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation))).asSingleFqName(), fqName)) {
                break;
            }
            i++;
        }
        if (annotation != null) {
            return new ReflectJavaAnnotation(annotation);
        }
        return null;
    }

    @NotNull
    public static final List<ReflectJavaAnnotation> getAnnotations(@NotNull Annotation[] getAnnotations) {
        Intrinsics.checkParameterIsNotNull(getAnnotations, "$this$getAnnotations");
        ArrayList arrayList = new ArrayList(getAnnotations.length);
        for (Annotation annotation : getAnnotations) {
            arrayList.add(new ReflectJavaAnnotation(annotation));
        }
        return arrayList;
    }
}
