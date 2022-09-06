package kotlin.reflect.jvm.internal.impl.types.typeUtil;

import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedType;
import kotlin.reflect.jvm.internal.impl.types.checker.NewTypeVariableConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TypeUtils.kt */
/* loaded from: classes4.dex */
public final class TypeUtilsKt {
    @NotNull
    public static final TypeProjection asTypeProjection(@NotNull KotlinType asTypeProjection) {
        Intrinsics.checkParameterIsNotNull(asTypeProjection, "$this$asTypeProjection");
        return new TypeProjectionImpl(asTypeProjection);
    }

    public static final boolean canHaveUndefinedNullability(@NotNull UnwrappedType canHaveUndefinedNullability) {
        Intrinsics.checkParameterIsNotNull(canHaveUndefinedNullability, "$this$canHaveUndefinedNullability");
        return (canHaveUndefinedNullability.mo12131getConstructor() instanceof NewTypeVariableConstructor) || (canHaveUndefinedNullability.mo12131getConstructor().mo12085getDeclarationDescriptor() instanceof TypeParameterDescriptor) || (canHaveUndefinedNullability instanceof NewCapturedType);
    }

    public static final boolean contains(@NotNull KotlinType contains, @NotNull Function1<? super UnwrappedType, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(contains, "$this$contains");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return TypeUtils.contains(contains, predicate);
    }

    public static final boolean containsTypeAliasParameters(@NotNull KotlinType containsTypeAliasParameters) {
        Intrinsics.checkParameterIsNotNull(containsTypeAliasParameters, "$this$containsTypeAliasParameters");
        return contains(containsTypeAliasParameters, TypeUtilsKt$containsTypeAliasParameters$1.INSTANCE);
    }

    @NotNull
    public static final TypeProjection createProjection(@NotNull KotlinType type, @NotNull Variance projectionKind, @Nullable TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(projectionKind, "projectionKind");
        if ((typeParameterDescriptor != null ? typeParameterDescriptor.getVariance() : null) == projectionKind) {
            projectionKind = Variance.INVARIANT;
        }
        return new TypeProjectionImpl(projectionKind, type);
    }

    @NotNull
    public static final KotlinBuiltIns getBuiltIns(@NotNull KotlinType builtIns) {
        Intrinsics.checkParameterIsNotNull(builtIns, "$this$builtIns");
        KotlinBuiltIns builtIns2 = builtIns.mo12131getConstructor().getBuiltIns();
        Intrinsics.checkExpressionValueIsNotNull(builtIns2, "constructor.builtIns");
        return builtIns2;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0072 A[SYNTHETIC] */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.reflect.jvm.internal.impl.types.KotlinType getRepresentativeUpperBound(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r8) {
        /*
            java.lang.String r0 = "$this$representativeUpperBound"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            java.util.List r0 = r8.getUpperBounds()
            java.lang.String r1 = "upperBounds"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r0 = r0.isEmpty()
            r2 = 1
            r0 = r0 ^ r2
            boolean r3 = kotlin._Assertions.ENABLED
            if (r3 == 0) goto L32
            if (r0 == 0) goto L1b
            goto L32
        L1b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Upper bounds should not be empty: "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>(r8)
            throw r0
        L32:
            java.util.List r0 = r8.getUpperBounds()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.util.Iterator r0 = r0.iterator()
        L3d:
            boolean r3 = r0.hasNext()
            r4 = 0
            if (r3 == 0) goto L72
            java.lang.Object r3 = r0.next()
            r5 = r3
            kotlin.reflect.jvm.internal.impl.types.KotlinType r5 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r5
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r5 = r5.mo12131getConstructor()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r5 = r5.mo12085getDeclarationDescriptor()
            boolean r6 = r5 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
            if (r6 != 0) goto L58
            goto L59
        L58:
            r4 = r5
        L59:
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r4
            r5 = 0
            if (r4 == 0) goto L6f
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r6 = r4.getKind()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r7 = kotlin.reflect.jvm.internal.impl.descriptors.ClassKind.INTERFACE
            if (r6 == r7) goto L6f
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r4 = r4.getKind()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r6 = kotlin.reflect.jvm.internal.impl.descriptors.ClassKind.ANNOTATION_CLASS
            if (r4 == r6) goto L6f
            r5 = r2
        L6f:
            if (r5 == 0) goto L3d
            goto L73
        L72:
            r3 = r4
        L73:
            kotlin.reflect.jvm.internal.impl.types.KotlinType r3 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r3
            if (r3 == 0) goto L78
            goto L8b
        L78:
            java.util.List r8 = r8.getUpperBounds()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r1)
            java.lang.Object r8 = kotlin.collections.CollectionsKt.first(r8)
            java.lang.String r0 = "upperBounds.first()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r0)
            r3 = r8
            kotlin.reflect.jvm.internal.impl.types.KotlinType r3 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r3
        L8b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.getRepresentativeUpperBound(kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor):kotlin.reflect.jvm.internal.impl.types.KotlinType");
    }

    public static final boolean isSubtypeOf(@NotNull KotlinType isSubtypeOf, @NotNull KotlinType superType) {
        Intrinsics.checkParameterIsNotNull(isSubtypeOf, "$this$isSubtypeOf");
        Intrinsics.checkParameterIsNotNull(superType, "superType");
        return KotlinTypeChecker.DEFAULT.isSubtypeOf(isSubtypeOf, superType);
    }

    public static final boolean isTypeAliasParameter(@NotNull ClassifierDescriptor isTypeAliasParameter) {
        Intrinsics.checkParameterIsNotNull(isTypeAliasParameter, "$this$isTypeAliasParameter");
        return (isTypeAliasParameter instanceof TypeParameterDescriptor) && (((TypeParameterDescriptor) isTypeAliasParameter).mo11607getContainingDeclaration() instanceof TypeAliasDescriptor);
    }

    public static final boolean isTypeParameter(@NotNull KotlinType isTypeParameter) {
        Intrinsics.checkParameterIsNotNull(isTypeParameter, "$this$isTypeParameter");
        return TypeUtils.isTypeParameter(isTypeParameter);
    }

    @NotNull
    public static final KotlinType makeNotNullable(@NotNull KotlinType makeNotNullable) {
        Intrinsics.checkParameterIsNotNull(makeNotNullable, "$this$makeNotNullable");
        KotlinType makeNotNullable2 = TypeUtils.makeNotNullable(makeNotNullable);
        Intrinsics.checkExpressionValueIsNotNull(makeNotNullable2, "TypeUtils.makeNotNullable(this)");
        return makeNotNullable2;
    }

    @NotNull
    public static final KotlinType makeNullable(@NotNull KotlinType makeNullable) {
        Intrinsics.checkParameterIsNotNull(makeNullable, "$this$makeNullable");
        KotlinType makeNullable2 = TypeUtils.makeNullable(makeNullable);
        Intrinsics.checkExpressionValueIsNotNull(makeNullable2, "TypeUtils.makeNullable(this)");
        return makeNullable2;
    }

    @NotNull
    public static final KotlinType replaceAnnotations(@NotNull KotlinType replaceAnnotations, @NotNull Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(replaceAnnotations, "$this$replaceAnnotations");
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        return (!replaceAnnotations.mo12070getAnnotations().isEmpty() || !newAnnotations.isEmpty()) ? replaceAnnotations.unwrap().mo12134replaceAnnotations(newAnnotations) : replaceAnnotations;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [kotlin.reflect.jvm.internal.impl.types.UnwrappedType] */
    @NotNull
    public static final KotlinType replaceArgumentsWithStarProjections(@NotNull KotlinType replaceArgumentsWithStarProjections) {
        int collectionSizeOrDefault;
        SimpleType simpleType;
        int collectionSizeOrDefault2;
        int collectionSizeOrDefault3;
        Intrinsics.checkParameterIsNotNull(replaceArgumentsWithStarProjections, "$this$replaceArgumentsWithStarProjections");
        UnwrappedType unwrap = replaceArgumentsWithStarProjections.unwrap();
        if (unwrap instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrap;
            SimpleType lowerBound = flexibleType.getLowerBound();
            if (!lowerBound.mo12131getConstructor().getParameters().isEmpty() && lowerBound.mo12131getConstructor().mo12085getDeclarationDescriptor() != null) {
                List<TypeParameterDescriptor> parameters = lowerBound.mo12131getConstructor().getParameters();
                Intrinsics.checkExpressionValueIsNotNull(parameters, "constructor.parameters");
                collectionSizeOrDefault3 = CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters, 10);
                ArrayList arrayList = new ArrayList(collectionSizeOrDefault3);
                for (TypeParameterDescriptor typeParameterDescriptor : parameters) {
                    arrayList.add(new StarProjectionImpl(typeParameterDescriptor));
                }
                lowerBound = TypeSubstitutionKt.replace$default(lowerBound, (List) arrayList, (Annotations) null, 2, (Object) null);
            }
            SimpleType upperBound = flexibleType.getUpperBound();
            if (!upperBound.mo12131getConstructor().getParameters().isEmpty() && upperBound.mo12131getConstructor().mo12085getDeclarationDescriptor() != null) {
                List<TypeParameterDescriptor> parameters2 = upperBound.mo12131getConstructor().getParameters();
                Intrinsics.checkExpressionValueIsNotNull(parameters2, "constructor.parameters");
                collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters2, 10);
                ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
                for (TypeParameterDescriptor typeParameterDescriptor2 : parameters2) {
                    arrayList2.add(new StarProjectionImpl(typeParameterDescriptor2));
                }
                upperBound = TypeSubstitutionKt.replace$default(upperBound, (List) arrayList2, (Annotations) null, 2, (Object) null);
            }
            simpleType = KotlinTypeFactory.flexibleType(lowerBound, upperBound);
        } else if (unwrap instanceof SimpleType) {
            SimpleType simpleType2 = (SimpleType) unwrap;
            boolean isEmpty = simpleType2.mo12131getConstructor().getParameters().isEmpty();
            simpleType = simpleType2;
            if (!isEmpty) {
                ClassifierDescriptor mo12085getDeclarationDescriptor = simpleType2.mo12131getConstructor().mo12085getDeclarationDescriptor();
                simpleType = simpleType2;
                if (mo12085getDeclarationDescriptor != null) {
                    List<TypeParameterDescriptor> parameters3 = simpleType2.mo12131getConstructor().getParameters();
                    Intrinsics.checkExpressionValueIsNotNull(parameters3, "constructor.parameters");
                    collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters3, 10);
                    ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault);
                    for (TypeParameterDescriptor typeParameterDescriptor3 : parameters3) {
                        arrayList3.add(new StarProjectionImpl(typeParameterDescriptor3));
                    }
                    simpleType = TypeSubstitutionKt.replace$default(simpleType2, (List) arrayList3, (Annotations) null, 2, (Object) null);
                }
            }
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return TypeWithEnhancementKt.inheritEnhancement(simpleType, unwrap);
    }

    public static final boolean requiresTypeAliasExpansion(@NotNull KotlinType requiresTypeAliasExpansion) {
        Intrinsics.checkParameterIsNotNull(requiresTypeAliasExpansion, "$this$requiresTypeAliasExpansion");
        return contains(requiresTypeAliasExpansion, TypeUtilsKt$requiresTypeAliasExpansion$1.INSTANCE);
    }
}
