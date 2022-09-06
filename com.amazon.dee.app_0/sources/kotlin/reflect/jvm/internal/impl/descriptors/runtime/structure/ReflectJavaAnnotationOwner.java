package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectJavaAnnotationOwner.kt */
/* loaded from: classes2.dex */
public interface ReflectJavaAnnotationOwner extends JavaAnnotationOwner {

    /* compiled from: ReflectJavaAnnotationOwner.kt */
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        @Nullable
        public static ReflectJavaAnnotation findAnnotation(ReflectJavaAnnotationOwner reflectJavaAnnotationOwner, @NotNull FqName fqName) {
            Annotation[] declaredAnnotations;
            Intrinsics.checkParameterIsNotNull(fqName, "fqName");
            AnnotatedElement mo11620getElement = reflectJavaAnnotationOwner.mo11620getElement();
            if (mo11620getElement == null || (declaredAnnotations = mo11620getElement.getDeclaredAnnotations()) == null) {
                return null;
            }
            return ReflectJavaAnnotationOwnerKt.findAnnotation(declaredAnnotations, fqName);
        }

        @NotNull
        public static List<ReflectJavaAnnotation> getAnnotations(ReflectJavaAnnotationOwner reflectJavaAnnotationOwner) {
            List<ReflectJavaAnnotation> emptyList;
            Annotation[] declaredAnnotations;
            List<ReflectJavaAnnotation> annotations;
            AnnotatedElement mo11620getElement = reflectJavaAnnotationOwner.mo11620getElement();
            if (mo11620getElement == null || (declaredAnnotations = mo11620getElement.getDeclaredAnnotations()) == null || (annotations = ReflectJavaAnnotationOwnerKt.getAnnotations(declaredAnnotations)) == null) {
                emptyList = CollectionsKt__CollectionsKt.emptyList();
                return emptyList;
            }
            return annotations;
        }

        public static boolean isDeprecatedInJavaDoc(ReflectJavaAnnotationOwner reflectJavaAnnotationOwner) {
            return false;
        }
    }

    @Nullable
    /* renamed from: getElement */
    AnnotatedElement mo11620getElement();
}
