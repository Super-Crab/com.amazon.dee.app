package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassOrPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: context.kt */
/* loaded from: classes3.dex */
public final class ContextKt {
    @NotNull
    public static final LazyJavaResolverContext child(@NotNull LazyJavaResolverContext child, @NotNull TypeParameterResolver typeParameterResolver) {
        Intrinsics.checkParameterIsNotNull(child, "$this$child");
        Intrinsics.checkParameterIsNotNull(typeParameterResolver, "typeParameterResolver");
        return new LazyJavaResolverContext(child.getComponents(), typeParameterResolver, child.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    @NotNull
    public static final LazyJavaResolverContext childForClassOrPackage(@NotNull LazyJavaResolverContext childForClassOrPackage, @NotNull ClassOrPackageFragmentDescriptor containingDeclaration, @Nullable JavaTypeParameterListOwner javaTypeParameterListOwner, int i) {
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull(childForClassOrPackage, "$this$childForClassOrPackage");
        Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
        lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new ContextKt$childForClassOrPackage$1(childForClassOrPackage, containingDeclaration));
        return child(childForClassOrPackage, containingDeclaration, javaTypeParameterListOwner, i, lazy);
    }

    public static /* synthetic */ LazyJavaResolverContext childForClassOrPackage$default(LazyJavaResolverContext lazyJavaResolverContext, ClassOrPackageFragmentDescriptor classOrPackageFragmentDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            javaTypeParameterListOwner = null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return childForClassOrPackage(lazyJavaResolverContext, classOrPackageFragmentDescriptor, javaTypeParameterListOwner, i);
    }

    @NotNull
    public static final LazyJavaResolverContext childForMethod(@NotNull LazyJavaResolverContext childForMethod, @NotNull DeclarationDescriptor containingDeclaration, @NotNull JavaTypeParameterListOwner typeParameterOwner, int i) {
        Intrinsics.checkParameterIsNotNull(childForMethod, "$this$childForMethod");
        Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
        Intrinsics.checkParameterIsNotNull(typeParameterOwner, "typeParameterOwner");
        return child(childForMethod, containingDeclaration, typeParameterOwner, i, childForMethod.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    public static /* synthetic */ LazyJavaResolverContext childForMethod$default(LazyJavaResolverContext lazyJavaResolverContext, DeclarationDescriptor declarationDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return childForMethod(lazyJavaResolverContext, declarationDescriptor, javaTypeParameterListOwner, i);
    }

    @Nullable
    public static final JavaTypeQualifiersByElementType computeNewDefaultTypeQualifiers(@NotNull LazyJavaResolverContext computeNewDefaultTypeQualifiers, @NotNull Annotations additionalAnnotations) {
        EnumMap<AnnotationTypeQualifierResolver.QualifierApplicabilityType, NullabilityQualifierWithMigrationStatus> nullabilityQualifiers;
        Intrinsics.checkParameterIsNotNull(computeNewDefaultTypeQualifiers, "$this$computeNewDefaultTypeQualifiers");
        Intrinsics.checkParameterIsNotNull(additionalAnnotations, "additionalAnnotations");
        if (computeNewDefaultTypeQualifiers.getComponents().getAnnotationTypeQualifierResolver().getDisabled()) {
            return computeNewDefaultTypeQualifiers.getDefaultTypeQualifiers();
        }
        ArrayList<NullabilityQualifierWithApplicability> arrayList = new ArrayList();
        Iterator<AnnotationDescriptor> it2 = additionalAnnotations.iterator();
        while (it2.hasNext()) {
            NullabilityQualifierWithApplicability extractDefaultNullabilityQualifier = extractDefaultNullabilityQualifier(computeNewDefaultTypeQualifiers, it2.next());
            if (extractDefaultNullabilityQualifier != null) {
                arrayList.add(extractDefaultNullabilityQualifier);
            }
        }
        if (arrayList.isEmpty()) {
            return computeNewDefaultTypeQualifiers.getDefaultTypeQualifiers();
        }
        JavaTypeQualifiersByElementType defaultTypeQualifiers = computeNewDefaultTypeQualifiers.getDefaultTypeQualifiers();
        EnumMap enumMap = (defaultTypeQualifiers == null || (nullabilityQualifiers = defaultTypeQualifiers.getNullabilityQualifiers()) == null) ? new EnumMap(AnnotationTypeQualifierResolver.QualifierApplicabilityType.class) : new EnumMap((EnumMap) nullabilityQualifiers);
        boolean z = false;
        for (NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability : arrayList) {
            NullabilityQualifierWithMigrationStatus component1 = nullabilityQualifierWithApplicability.component1();
            for (AnnotationTypeQualifierResolver.QualifierApplicabilityType qualifierApplicabilityType : nullabilityQualifierWithApplicability.component2()) {
                enumMap.put((EnumMap) qualifierApplicabilityType, (AnnotationTypeQualifierResolver.QualifierApplicabilityType) component1);
                z = true;
            }
        }
        return !z ? computeNewDefaultTypeQualifiers.getDefaultTypeQualifiers() : new JavaTypeQualifiersByElementType(enumMap);
    }

    @NotNull
    public static final LazyJavaResolverContext copyWithNewDefaultTypeQualifiers(@NotNull LazyJavaResolverContext copyWithNewDefaultTypeQualifiers, @NotNull Annotations additionalAnnotations) {
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull(copyWithNewDefaultTypeQualifiers, "$this$copyWithNewDefaultTypeQualifiers");
        Intrinsics.checkParameterIsNotNull(additionalAnnotations, "additionalAnnotations");
        if (additionalAnnotations.isEmpty()) {
            return copyWithNewDefaultTypeQualifiers;
        }
        JavaResolverComponents components = copyWithNewDefaultTypeQualifiers.getComponents();
        TypeParameterResolver typeParameterResolver = copyWithNewDefaultTypeQualifiers.getTypeParameterResolver();
        lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new ContextKt$copyWithNewDefaultTypeQualifiers$1(copyWithNewDefaultTypeQualifiers, additionalAnnotations));
        return new LazyJavaResolverContext(components, typeParameterResolver, lazy);
    }

    private static final NullabilityQualifierWithApplicability extractDefaultNullabilityQualifier(@NotNull LazyJavaResolverContext lazyJavaResolverContext, AnnotationDescriptor annotationDescriptor) {
        NullabilityQualifierWithMigrationStatus extractNullability;
        NullabilityQualifierWithMigrationStatus copy$default;
        AnnotationTypeQualifierResolver annotationTypeQualifierResolver = lazyJavaResolverContext.getComponents().getAnnotationTypeQualifierResolver();
        NullabilityQualifierWithApplicability resolveQualifierBuiltInDefaultAnnotation = annotationTypeQualifierResolver.resolveQualifierBuiltInDefaultAnnotation(annotationDescriptor);
        if (resolveQualifierBuiltInDefaultAnnotation != null) {
            return resolveQualifierBuiltInDefaultAnnotation;
        }
        AnnotationTypeQualifierResolver.TypeQualifierWithApplicability resolveTypeQualifierDefaultAnnotation = annotationTypeQualifierResolver.resolveTypeQualifierDefaultAnnotation(annotationDescriptor);
        if (resolveTypeQualifierDefaultAnnotation != null) {
            AnnotationDescriptor component1 = resolveTypeQualifierDefaultAnnotation.component1();
            List<AnnotationTypeQualifierResolver.QualifierApplicabilityType> component2 = resolveTypeQualifierDefaultAnnotation.component2();
            ReportLevel resolveJsr305CustomState = annotationTypeQualifierResolver.resolveJsr305CustomState(annotationDescriptor);
            if (resolveJsr305CustomState == null) {
                resolveJsr305CustomState = annotationTypeQualifierResolver.resolveJsr305AnnotationState(component1);
            }
            if (!resolveJsr305CustomState.isIgnore() && (extractNullability = lazyJavaResolverContext.getComponents().getSignatureEnhancement().extractNullability(component1)) != null && (copy$default = NullabilityQualifierWithMigrationStatus.copy$default(extractNullability, null, resolveJsr305CustomState.isWarning(), 1, null)) != null) {
                return new NullabilityQualifierWithApplicability(copy$default, component2);
            }
        }
        return null;
    }

    @NotNull
    public static final LazyJavaResolverContext replaceComponents(@NotNull LazyJavaResolverContext replaceComponents, @NotNull JavaResolverComponents components) {
        Intrinsics.checkParameterIsNotNull(replaceComponents, "$this$replaceComponents");
        Intrinsics.checkParameterIsNotNull(components, "components");
        return new LazyJavaResolverContext(components, replaceComponents.getTypeParameterResolver(), replaceComponents.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    private static final LazyJavaResolverContext child(@NotNull LazyJavaResolverContext lazyJavaResolverContext, DeclarationDescriptor declarationDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, Lazy<JavaTypeQualifiersByElementType> lazy) {
        TypeParameterResolver typeParameterResolver;
        JavaResolverComponents components = lazyJavaResolverContext.getComponents();
        if (javaTypeParameterListOwner != null) {
            typeParameterResolver = new LazyJavaTypeParameterResolver(lazyJavaResolverContext, declarationDescriptor, javaTypeParameterListOwner, i);
        } else {
            typeParameterResolver = lazyJavaResolverContext.getTypeParameterResolver();
        }
        return new LazyJavaResolverContext(components, typeParameterResolver, lazy);
    }
}
