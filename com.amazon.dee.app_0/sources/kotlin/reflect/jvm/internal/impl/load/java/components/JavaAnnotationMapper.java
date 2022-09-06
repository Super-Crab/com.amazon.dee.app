package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JavaAnnotationMapper.kt */
/* loaded from: classes3.dex */
public final class JavaAnnotationMapper {
    @NotNull
    private static final Name DEPRECATED_ANNOTATION_MESSAGE;
    @NotNull
    private static final Name RETENTION_ANNOTATION_VALUE;
    @NotNull
    private static final Name TARGET_ANNOTATION_ALLOWED_TARGETS;
    @NotNull
    private static final Map<FqName, FqName> javaToKotlinNameMap;
    private static final Map<FqName, FqName> kotlinToJavaNameMap;
    public static final JavaAnnotationMapper INSTANCE = new JavaAnnotationMapper();
    private static final FqName JAVA_TARGET_FQ_NAME = new FqName(Target.class.getCanonicalName());
    private static final FqName JAVA_RETENTION_FQ_NAME = new FqName(Retention.class.getCanonicalName());
    private static final FqName JAVA_DEPRECATED_FQ_NAME = new FqName(Deprecated.class.getCanonicalName());
    private static final FqName JAVA_DOCUMENTED_FQ_NAME = new FqName(Documented.class.getCanonicalName());
    private static final FqName JAVA_REPEATABLE_FQ_NAME = new FqName("java.lang.annotation.Repeatable");

    static {
        Map<FqName, FqName> mapOf;
        Map<FqName, FqName> mapOf2;
        Name identifier = Name.identifier("message");
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(\"message\")");
        DEPRECATED_ANNOTATION_MESSAGE = identifier;
        Name identifier2 = Name.identifier("allowedTargets");
        Intrinsics.checkExpressionValueIsNotNull(identifier2, "Name.identifier(\"allowedTargets\")");
        TARGET_ANNOTATION_ALLOWED_TARGETS = identifier2;
        Name identifier3 = Name.identifier("value");
        Intrinsics.checkExpressionValueIsNotNull(identifier3, "Name.identifier(\"value\")");
        RETENTION_ANNOTATION_VALUE = identifier3;
        mapOf = MapsKt__MapsKt.mapOf(TuplesKt.to(KotlinBuiltIns.FQ_NAMES.target, JAVA_TARGET_FQ_NAME), TuplesKt.to(KotlinBuiltIns.FQ_NAMES.retention, JAVA_RETENTION_FQ_NAME), TuplesKt.to(KotlinBuiltIns.FQ_NAMES.repeatable, JAVA_REPEATABLE_FQ_NAME), TuplesKt.to(KotlinBuiltIns.FQ_NAMES.mustBeDocumented, JAVA_DOCUMENTED_FQ_NAME));
        kotlinToJavaNameMap = mapOf;
        mapOf2 = MapsKt__MapsKt.mapOf(TuplesKt.to(JAVA_TARGET_FQ_NAME, KotlinBuiltIns.FQ_NAMES.target), TuplesKt.to(JAVA_RETENTION_FQ_NAME, KotlinBuiltIns.FQ_NAMES.retention), TuplesKt.to(JAVA_DEPRECATED_FQ_NAME, KotlinBuiltIns.FQ_NAMES.deprecated), TuplesKt.to(JAVA_REPEATABLE_FQ_NAME, KotlinBuiltIns.FQ_NAMES.repeatable), TuplesKt.to(JAVA_DOCUMENTED_FQ_NAME, KotlinBuiltIns.FQ_NAMES.mustBeDocumented));
        javaToKotlinNameMap = mapOf2;
    }

    private JavaAnnotationMapper() {
    }

    @Nullable
    public final AnnotationDescriptor findMappedJavaAnnotation(@NotNull FqName kotlinName, @NotNull JavaAnnotationOwner annotationOwner, @NotNull LazyJavaResolverContext c) {
        JavaAnnotation mo11638findAnnotation;
        JavaAnnotation mo11638findAnnotation2;
        Intrinsics.checkParameterIsNotNull(kotlinName, "kotlinName");
        Intrinsics.checkParameterIsNotNull(annotationOwner, "annotationOwner");
        Intrinsics.checkParameterIsNotNull(c, "c");
        if (Intrinsics.areEqual(kotlinName, KotlinBuiltIns.FQ_NAMES.deprecated) && ((mo11638findAnnotation2 = annotationOwner.mo11638findAnnotation(JAVA_DEPRECATED_FQ_NAME)) != null || annotationOwner.isDeprecatedInJavaDoc())) {
            return new JavaDeprecatedAnnotationDescriptor(mo11638findAnnotation2, c);
        }
        FqName fqName = kotlinToJavaNameMap.get(kotlinName);
        if (fqName != null && (mo11638findAnnotation = annotationOwner.mo11638findAnnotation(fqName)) != null) {
            return INSTANCE.mapOrResolveJavaAnnotation(mo11638findAnnotation, c);
        }
        return null;
    }

    @NotNull
    public final Name getDEPRECATED_ANNOTATION_MESSAGE$descriptors_jvm() {
        return DEPRECATED_ANNOTATION_MESSAGE;
    }

    @NotNull
    public final Name getRETENTION_ANNOTATION_VALUE$descriptors_jvm() {
        return RETENTION_ANNOTATION_VALUE;
    }

    @NotNull
    public final Name getTARGET_ANNOTATION_ALLOWED_TARGETS$descriptors_jvm() {
        return TARGET_ANNOTATION_ALLOWED_TARGETS;
    }

    @Nullable
    public final AnnotationDescriptor mapOrResolveJavaAnnotation(@NotNull JavaAnnotation annotation, @NotNull LazyJavaResolverContext c) {
        Intrinsics.checkParameterIsNotNull(annotation, "annotation");
        Intrinsics.checkParameterIsNotNull(c, "c");
        ClassId classId = annotation.getClassId();
        if (Intrinsics.areEqual(classId, ClassId.topLevel(JAVA_TARGET_FQ_NAME))) {
            return new JavaTargetAnnotationDescriptor(annotation, c);
        }
        if (Intrinsics.areEqual(classId, ClassId.topLevel(JAVA_RETENTION_FQ_NAME))) {
            return new JavaRetentionAnnotationDescriptor(annotation, c);
        }
        if (Intrinsics.areEqual(classId, ClassId.topLevel(JAVA_REPEATABLE_FQ_NAME))) {
            FqName fqName = KotlinBuiltIns.FQ_NAMES.repeatable;
            Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.repeatable");
            return new JavaAnnotationDescriptor(c, annotation, fqName);
        } else if (Intrinsics.areEqual(classId, ClassId.topLevel(JAVA_DOCUMENTED_FQ_NAME))) {
            FqName fqName2 = KotlinBuiltIns.FQ_NAMES.mustBeDocumented;
            Intrinsics.checkExpressionValueIsNotNull(fqName2, "KotlinBuiltIns.FQ_NAMES.mustBeDocumented");
            return new JavaAnnotationDescriptor(c, annotation, fqName2);
        } else if (!Intrinsics.areEqual(classId, ClassId.topLevel(JAVA_DEPRECATED_FQ_NAME))) {
            return new LazyJavaAnnotationDescriptor(c, annotation);
        } else {
            return null;
        }
    }
}
