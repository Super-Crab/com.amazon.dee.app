package kotlin.reflect.jvm.internal.impl.types;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: TypeAliasExpander.kt */
/* loaded from: classes4.dex */
public final class TypeAliasExpander {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final TypeAliasExpander NON_REPORTING = new TypeAliasExpander(TypeAliasExpansionReportStrategy.DO_NOTHING.INSTANCE, false);
    private final TypeAliasExpansionReportStrategy reportStrategy;
    private final boolean shouldCheckBounds;

    /* compiled from: TypeAliasExpander.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void assertRecursionDepth(int i, TypeAliasDescriptor typeAliasDescriptor) {
            if (i <= 100) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Too deep recursion while expanding type alias ");
            outline107.append(typeAliasDescriptor.getName());
            throw new AssertionError(outline107.toString());
        }

        public final void checkBoundsInTypeAlias(@NotNull TypeAliasExpansionReportStrategy reportStrategy, @NotNull KotlinType unsubstitutedArgument, @NotNull KotlinType typeArgument, @NotNull TypeParameterDescriptor typeParameterDescriptor, @NotNull TypeSubstitutor substitutor) {
            Intrinsics.checkParameterIsNotNull(reportStrategy, "reportStrategy");
            Intrinsics.checkParameterIsNotNull(unsubstitutedArgument, "unsubstitutedArgument");
            Intrinsics.checkParameterIsNotNull(typeArgument, "typeArgument");
            Intrinsics.checkParameterIsNotNull(typeParameterDescriptor, "typeParameterDescriptor");
            Intrinsics.checkParameterIsNotNull(substitutor, "substitutor");
            for (KotlinType kotlinType : typeParameterDescriptor.getUpperBounds()) {
                KotlinType safeSubstitute = substitutor.safeSubstitute(kotlinType, Variance.INVARIANT);
                Intrinsics.checkExpressionValueIsNotNull(safeSubstitute, "substitutor.safeSubstitu…ound, Variance.INVARIANT)");
                if (!KotlinTypeChecker.DEFAULT.isSubtypeOf(typeArgument, safeSubstitute)) {
                    reportStrategy.boundsViolationInSubstitution(safeSubstitute, unsubstitutedArgument, typeArgument, typeParameterDescriptor);
                }
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TypeAliasExpander(@NotNull TypeAliasExpansionReportStrategy reportStrategy, boolean z) {
        Intrinsics.checkParameterIsNotNull(reportStrategy, "reportStrategy");
        this.reportStrategy = reportStrategy;
        this.shouldCheckBounds = z;
    }

    private final void checkRepeatedAnnotations(Annotations annotations, Annotations annotations2) {
        HashSet hashSet = new HashSet();
        Iterator<AnnotationDescriptor> it2 = annotations.iterator();
        while (it2.hasNext()) {
            hashSet.add(it2.next().getFqName());
        }
        for (AnnotationDescriptor annotationDescriptor : annotations2) {
            if (hashSet.contains(annotationDescriptor.getFqName())) {
                this.reportStrategy.repeatedAnnotation(annotationDescriptor);
            }
        }
    }

    private final void checkTypeArgumentsSubstitution(KotlinType kotlinType, KotlinType kotlinType2) {
        TypeSubstitutor create = TypeSubstitutor.create(kotlinType2);
        Intrinsics.checkExpressionValueIsNotNull(create, "TypeSubstitutor.create(substitutedType)");
        int i = 0;
        for (Object obj : kotlinType2.getArguments()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            TypeProjection typeProjection = (TypeProjection) obj;
            if (!typeProjection.isStarProjection()) {
                KotlinType type = typeProjection.getType();
                Intrinsics.checkExpressionValueIsNotNull(type, "substitutedArgument.type");
                if (!TypeUtilsKt.containsTypeAliasParameters(type)) {
                    TypeProjection typeProjection2 = kotlinType.getArguments().get(i);
                    TypeParameterDescriptor typeParameter = kotlinType.mo12131getConstructor().getParameters().get(i);
                    if (this.shouldCheckBounds) {
                        Companion companion = Companion;
                        TypeAliasExpansionReportStrategy typeAliasExpansionReportStrategy = this.reportStrategy;
                        KotlinType type2 = typeProjection2.getType();
                        Intrinsics.checkExpressionValueIsNotNull(type2, "unsubstitutedArgument.type");
                        KotlinType type3 = typeProjection.getType();
                        Intrinsics.checkExpressionValueIsNotNull(type3, "substitutedArgument.type");
                        Intrinsics.checkExpressionValueIsNotNull(typeParameter, "typeParameter");
                        companion.checkBoundsInTypeAlias(typeAliasExpansionReportStrategy, type2, type3, typeParameter, create);
                    }
                }
            }
            i = i2;
        }
    }

    private final DynamicType combineAnnotations(@NotNull DynamicType dynamicType, Annotations annotations) {
        return dynamicType.mo12134replaceAnnotations(createCombinedAnnotations(dynamicType, annotations));
    }

    private final SimpleType combineNullability(@NotNull SimpleType simpleType, KotlinType kotlinType) {
        SimpleType makeNullableIfNeeded = TypeUtils.makeNullableIfNeeded(simpleType, kotlinType.isMarkedNullable());
        Intrinsics.checkExpressionValueIsNotNull(makeNullableIfNeeded, "TypeUtils.makeNullableIf…romType.isMarkedNullable)");
        return makeNullableIfNeeded;
    }

    private final SimpleType combineNullabilityAndAnnotations(@NotNull SimpleType simpleType, KotlinType kotlinType) {
        return combineAnnotations(combineNullability(simpleType, kotlinType), kotlinType.mo12070getAnnotations());
    }

    private final SimpleType createAbbreviation(@NotNull TypeAliasExpansion typeAliasExpansion, Annotations annotations, boolean z) {
        TypeConstructor mo11528getTypeConstructor = typeAliasExpansion.getDescriptor().mo11528getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "descriptor.typeConstructor");
        return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(annotations, mo11528getTypeConstructor, typeAliasExpansion.getArguments(), z, MemberScope.Empty.INSTANCE);
    }

    private final Annotations createCombinedAnnotations(@NotNull KotlinType kotlinType, Annotations annotations) {
        return KotlinTypeKt.isError(kotlinType) ? kotlinType.mo12070getAnnotations() : AnnotationsKt.composeAnnotations(annotations, kotlinType.mo12070getAnnotations());
    }

    private final TypeProjection expandNonArgumentTypeProjection(TypeProjection typeProjection, TypeAliasExpansion typeAliasExpansion, int i) {
        int collectionSizeOrDefault;
        UnwrappedType unwrap = typeProjection.getType().unwrap();
        if (DynamicTypesKt.isDynamic(unwrap)) {
            return typeProjection;
        }
        SimpleType asSimpleType = TypeSubstitutionKt.asSimpleType(unwrap);
        if (KotlinTypeKt.isError(asSimpleType) || !TypeUtilsKt.requiresTypeAliasExpansion(asSimpleType)) {
            return typeProjection;
        }
        TypeConstructor mo12131getConstructor = asSimpleType.mo12131getConstructor();
        ClassifierDescriptor mo12085getDeclarationDescriptor = mo12131getConstructor.mo12085getDeclarationDescriptor();
        int i2 = 0;
        boolean z = mo12131getConstructor.getParameters().size() == asSimpleType.getArguments().size();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Unexpected malformed type: " + asSimpleType);
        } else if (mo12085getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            return typeProjection;
        } else {
            if (mo12085getDeclarationDescriptor instanceof TypeAliasDescriptor) {
                TypeAliasDescriptor typeAliasDescriptor = (TypeAliasDescriptor) mo12085getDeclarationDescriptor;
                if (typeAliasExpansion.isRecursion(typeAliasDescriptor)) {
                    this.reportStrategy.recursiveTypeAlias(typeAliasDescriptor);
                    Variance variance = Variance.INVARIANT;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Recursive type alias: ");
                    outline107.append(typeAliasDescriptor.getName());
                    return new TypeProjectionImpl(variance, ErrorUtils.createErrorType(outline107.toString()));
                }
                List<TypeProjection> arguments = asSimpleType.getArguments();
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arguments, 10);
                ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                for (Object obj : arguments) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    arrayList.add(expandTypeProjection((TypeProjection) obj, typeAliasExpansion, mo12131getConstructor.getParameters().get(i2), i + 1));
                    i2 = i3;
                }
                SimpleType expandRecursively = expandRecursively(TypeAliasExpansion.Companion.create(typeAliasExpansion, typeAliasDescriptor, arrayList), asSimpleType.mo12070getAnnotations(), asSimpleType.isMarkedNullable(), i + 1, false);
                SimpleType substituteArguments = substituteArguments(asSimpleType, typeAliasExpansion, i);
                if (!DynamicTypesKt.isDynamic(expandRecursively)) {
                    expandRecursively = SpecialTypesKt.withAbbreviation(expandRecursively, substituteArguments);
                }
                return new TypeProjectionImpl(typeProjection.getProjectionKind(), expandRecursively);
            }
            SimpleType substituteArguments2 = substituteArguments(asSimpleType, typeAliasExpansion, i);
            checkTypeArgumentsSubstitution(asSimpleType, substituteArguments2);
            return new TypeProjectionImpl(typeProjection.getProjectionKind(), substituteArguments2);
        }
    }

    private final SimpleType expandRecursively(TypeAliasExpansion typeAliasExpansion, Annotations annotations, boolean z, int i, boolean z2) {
        TypeProjection expandTypeProjection = expandTypeProjection(new TypeProjectionImpl(Variance.INVARIANT, typeAliasExpansion.getDescriptor().getUnderlyingType()), typeAliasExpansion, null, i);
        KotlinType type = expandTypeProjection.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "expandedProjection.type");
        SimpleType asSimpleType = TypeSubstitutionKt.asSimpleType(type);
        if (KotlinTypeKt.isError(asSimpleType)) {
            return asSimpleType;
        }
        boolean z3 = expandTypeProjection.getProjectionKind() == Variance.INVARIANT;
        if (_Assertions.ENABLED && !z3) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Type alias expansion: result for ");
            outline107.append(typeAliasExpansion.getDescriptor());
            outline107.append(" is ");
            outline107.append(expandTypeProjection.getProjectionKind());
            outline107.append(", should be invariant");
            throw new AssertionError(outline107.toString());
        }
        checkRepeatedAnnotations(asSimpleType.mo12070getAnnotations(), annotations);
        SimpleType makeNullableIfNeeded = TypeUtils.makeNullableIfNeeded(combineAnnotations(asSimpleType, annotations), z);
        Intrinsics.checkExpressionValueIsNotNull(makeNullableIfNeeded, "expandedType.combineAnno…fNeeded(it, isNullable) }");
        return z2 ? SpecialTypesKt.withAbbreviation(makeNullableIfNeeded, createAbbreviation(typeAliasExpansion, annotations, z)) : makeNullableIfNeeded;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final kotlin.reflect.jvm.internal.impl.types.TypeProjection expandTypeProjection(kotlin.reflect.jvm.internal.impl.types.TypeProjection r4, kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansion r5, kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r6, int r7) {
        /*
            r3 = this;
            kotlin.reflect.jvm.internal.impl.types.TypeAliasExpander$Companion r0 = kotlin.reflect.jvm.internal.impl.types.TypeAliasExpander.Companion
            kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor r1 = r5.getDescriptor()
            kotlin.reflect.jvm.internal.impl.types.TypeAliasExpander.Companion.access$assertRecursionDepth(r0, r7, r1)
            boolean r0 = r4.isStarProjection()
            java.lang.String r1 = "TypeUtils.makeStarProjec…ypeParameterDescriptor!!)"
            if (r0 == 0) goto L1e
            if (r6 != 0) goto L16
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L16:
            kotlin.reflect.jvm.internal.impl.types.TypeProjection r4 = kotlin.reflect.jvm.internal.impl.types.TypeUtils.makeStarProjection(r6)
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r1)
            return r4
        L1e:
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r4.getType()
            java.lang.String r2 = "underlyingProjection.type"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r2 = r0.mo12131getConstructor()
            kotlin.reflect.jvm.internal.impl.types.TypeProjection r2 = r5.getReplacement(r2)
            if (r2 == 0) goto Lc0
            boolean r7 = r2.isStarProjection()
            if (r7 == 0) goto L44
            if (r6 != 0) goto L3c
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L3c:
            kotlin.reflect.jvm.internal.impl.types.TypeProjection r4 = kotlin.reflect.jvm.internal.impl.types.TypeUtils.makeStarProjection(r6)
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r1)
            return r4
        L44:
            kotlin.reflect.jvm.internal.impl.types.KotlinType r7 = r2.getType()
            kotlin.reflect.jvm.internal.impl.types.UnwrappedType r7 = r7.unwrap()
            kotlin.reflect.jvm.internal.impl.types.Variance r1 = r2.getProjectionKind()
            java.lang.String r2 = "argument.projectionKind"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            kotlin.reflect.jvm.internal.impl.types.Variance r4 = r4.getProjectionKind()
            java.lang.String r2 = "underlyingProjection.projectionKind"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r2)
            if (r4 != r1) goto L61
            goto L72
        L61:
            kotlin.reflect.jvm.internal.impl.types.Variance r2 = kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT
            if (r4 != r2) goto L66
            goto L72
        L66:
            if (r1 != r2) goto L69
            goto L73
        L69:
            kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy r4 = r3.reportStrategy
            kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor r2 = r5.getDescriptor()
            r4.conflictingProjection(r2, r6, r7)
        L72:
            r4 = r1
        L73:
            if (r6 == 0) goto L7c
            kotlin.reflect.jvm.internal.impl.types.Variance r1 = r6.getVariance()
            if (r1 == 0) goto L7c
            goto L7e
        L7c:
            kotlin.reflect.jvm.internal.impl.types.Variance r1 = kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT
        L7e:
            java.lang.String r2 = "typeParameterDescriptor?…nce ?: Variance.INVARIANT"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            if (r1 != r4) goto L86
            goto L98
        L86:
            kotlin.reflect.jvm.internal.impl.types.Variance r2 = kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT
            if (r1 != r2) goto L8b
            goto L98
        L8b:
            if (r4 != r2) goto L8f
            r4 = r2
            goto L98
        L8f:
            kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy r1 = r3.reportStrategy
            kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor r5 = r5.getDescriptor()
            r1.conflictingProjection(r5, r6, r7)
        L98:
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r5 = r0.mo12070getAnnotations()
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r6 = r7.mo12070getAnnotations()
            r3.checkRepeatedAnnotations(r5, r6)
            boolean r5 = r7 instanceof kotlin.reflect.jvm.internal.impl.types.DynamicType
            if (r5 == 0) goto Lb2
            kotlin.reflect.jvm.internal.impl.types.DynamicType r7 = (kotlin.reflect.jvm.internal.impl.types.DynamicType) r7
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r5 = r0.mo12070getAnnotations()
            kotlin.reflect.jvm.internal.impl.types.DynamicType r5 = r3.combineAnnotations(r7, r5)
            goto Lba
        Lb2:
            kotlin.reflect.jvm.internal.impl.types.SimpleType r5 = kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt.asSimpleType(r7)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r5 = r3.combineNullabilityAndAnnotations(r5, r0)
        Lba:
            kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl r6 = new kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl
            r6.<init>(r4, r5)
            return r6
        Lc0:
            kotlin.reflect.jvm.internal.impl.types.TypeProjection r4 = r3.expandNonArgumentTypeProjection(r4, r5, r7)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.TypeAliasExpander.expandTypeProjection(kotlin.reflect.jvm.internal.impl.types.TypeProjection, kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansion, kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor, int):kotlin.reflect.jvm.internal.impl.types.TypeProjection");
    }

    private final SimpleType substituteArguments(@NotNull SimpleType simpleType, TypeAliasExpansion typeAliasExpansion, int i) {
        int collectionSizeOrDefault;
        TypeConstructor mo12131getConstructor = simpleType.mo12131getConstructor();
        List<TypeProjection> arguments = simpleType.getArguments();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arguments, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        int i2 = 0;
        for (Object obj : arguments) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            TypeProjection typeProjection = (TypeProjection) obj;
            TypeProjection expandTypeProjection = expandTypeProjection(typeProjection, typeAliasExpansion, mo12131getConstructor.getParameters().get(i2), i + 1);
            if (!expandTypeProjection.isStarProjection()) {
                expandTypeProjection = new TypeProjectionImpl(expandTypeProjection.getProjectionKind(), TypeUtils.makeNullableIfNeeded(expandTypeProjection.getType(), typeProjection.getType().isMarkedNullable()));
            }
            arrayList.add(expandTypeProjection);
            i2 = i3;
        }
        return TypeSubstitutionKt.replace$default(simpleType, (List) arrayList, (Annotations) null, 2, (Object) null);
    }

    @NotNull
    public final SimpleType expand(@NotNull TypeAliasExpansion typeAliasExpansion, @NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(typeAliasExpansion, "typeAliasExpansion");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        return expandRecursively(typeAliasExpansion, annotations, false, 0, true);
    }

    private final SimpleType combineAnnotations(@NotNull SimpleType simpleType, Annotations annotations) {
        return KotlinTypeKt.isError(simpleType) ? simpleType : TypeSubstitutionKt.replace$default(simpleType, (List) null, createCombinedAnnotations(simpleType, annotations), 1, (Object) null);
    }
}
