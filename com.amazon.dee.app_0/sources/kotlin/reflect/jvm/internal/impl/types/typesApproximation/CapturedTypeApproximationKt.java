package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CapturedTypeApproximation.kt */
/* loaded from: classes4.dex */
public final class CapturedTypeApproximationKt {

    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Variance.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[Variance.INVARIANT.ordinal()] = 1;
            $EnumSwitchMapping$0[Variance.IN_VARIANCE.ordinal()] = 2;
            $EnumSwitchMapping$0[Variance.OUT_VARIANCE.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[Variance.values().length];
            $EnumSwitchMapping$1[Variance.IN_VARIANCE.ordinal()] = 1;
            $EnumSwitchMapping$1[Variance.OUT_VARIANCE.ordinal()] = 2;
        }
    }

    @NotNull
    public static final ApproximationBounds<KotlinType> approximateCapturedTypes(@NotNull KotlinType type) {
        List<Pair> zip;
        Object replaceTypeArguments;
        Intrinsics.checkParameterIsNotNull(type, "type");
        if (FlexibleTypesKt.isFlexible(type)) {
            ApproximationBounds<KotlinType> approximateCapturedTypes = approximateCapturedTypes(FlexibleTypesKt.lowerIfFlexible(type));
            ApproximationBounds<KotlinType> approximateCapturedTypes2 = approximateCapturedTypes(FlexibleTypesKt.upperIfFlexible(type));
            return new ApproximationBounds<>(TypeWithEnhancementKt.inheritEnhancement(KotlinTypeFactory.flexibleType(FlexibleTypesKt.lowerIfFlexible(approximateCapturedTypes.getLower()), FlexibleTypesKt.upperIfFlexible(approximateCapturedTypes2.getLower())), type), TypeWithEnhancementKt.inheritEnhancement(KotlinTypeFactory.flexibleType(FlexibleTypesKt.lowerIfFlexible(approximateCapturedTypes.getUpper()), FlexibleTypesKt.upperIfFlexible(approximateCapturedTypes2.getUpper())), type));
        }
        TypeConstructor mo12131getConstructor = type.mo12131getConstructor();
        if (CapturedTypeConstructorKt.isCaptured(type)) {
            if (mo12131getConstructor != null) {
                TypeProjection projection = ((CapturedTypeConstructor) mo12131getConstructor).getProjection();
                CapturedTypeApproximationKt$approximateCapturedTypes$1 capturedTypeApproximationKt$approximateCapturedTypes$1 = new CapturedTypeApproximationKt$approximateCapturedTypes$1(type);
                KotlinType type2 = projection.getType();
                Intrinsics.checkExpressionValueIsNotNull(type2, "typeProjection.type");
                KotlinType mo12165invoke = capturedTypeApproximationKt$approximateCapturedTypes$1.mo12165invoke(type2);
                int i = WhenMappings.$EnumSwitchMapping$1[projection.getProjectionKind().ordinal()];
                if (i == 1) {
                    SimpleType nullableAnyType = TypeUtilsKt.getBuiltIns(type).getNullableAnyType();
                    Intrinsics.checkExpressionValueIsNotNull(nullableAnyType, "type.builtIns.nullableAnyType");
                    return new ApproximationBounds<>(mo12165invoke, nullableAnyType);
                } else if (i == 2) {
                    SimpleType nothingType = TypeUtilsKt.getBuiltIns(type).getNothingType();
                    Intrinsics.checkExpressionValueIsNotNull(nothingType, "type.builtIns.nothingType");
                    return new ApproximationBounds<>(capturedTypeApproximationKt$approximateCapturedTypes$1.mo12165invoke((KotlinType) nothingType), mo12165invoke);
                } else {
                    throw new AssertionError("Only nontrivial projections should have been captured, not: " + projection);
                }
            }
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.resolve.calls.inference.CapturedTypeConstructor");
        } else if (!type.getArguments().isEmpty() && type.getArguments().size() == mo12131getConstructor.getParameters().size()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            List<TypeProjection> arguments = type.getArguments();
            List<TypeParameterDescriptor> parameters = mo12131getConstructor.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "typeConstructor.parameters");
            zip = CollectionsKt___CollectionsKt.zip(arguments, parameters);
            for (Pair pair : zip) {
                TypeProjection typeProjection = (TypeProjection) pair.component1();
                TypeParameterDescriptor typeParameter = (TypeParameterDescriptor) pair.component2();
                Intrinsics.checkExpressionValueIsNotNull(typeParameter, "typeParameter");
                TypeArgument typeArgument = toTypeArgument(typeProjection, typeParameter);
                if (typeProjection.isStarProjection()) {
                    arrayList.add(typeArgument);
                    arrayList2.add(typeArgument);
                } else {
                    ApproximationBounds<TypeArgument> approximateProjection = approximateProjection(typeArgument);
                    arrayList.add(approximateProjection.component1());
                    arrayList2.add(approximateProjection.component2());
                }
            }
            boolean z = false;
            if (!arrayList.isEmpty()) {
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    } else if (!((TypeArgument) it2.next()).isConsistent()) {
                        z = true;
                        break;
                    }
                }
            }
            if (z) {
                replaceTypeArguments = TypeUtilsKt.getBuiltIns(type).getNothingType();
                Intrinsics.checkExpressionValueIsNotNull(replaceTypeArguments, "type.builtIns.nothingType");
            } else {
                replaceTypeArguments = replaceTypeArguments(type, arrayList);
            }
            return new ApproximationBounds<>(replaceTypeArguments, replaceTypeArguments(type, arrayList2));
        } else {
            return new ApproximationBounds<>(type, type);
        }
    }

    @Nullable
    public static final TypeProjection approximateCapturedTypesIfNecessary(@Nullable TypeProjection typeProjection, boolean z) {
        if (typeProjection == null) {
            return null;
        }
        if (typeProjection.isStarProjection()) {
            return typeProjection;
        }
        KotlinType type = typeProjection.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "typeProjection.type");
        if (!TypeUtils.contains(type, CapturedTypeApproximationKt$approximateCapturedTypesIfNecessary$1.INSTANCE)) {
            return typeProjection;
        }
        Variance projectionKind = typeProjection.getProjectionKind();
        Intrinsics.checkExpressionValueIsNotNull(projectionKind, "typeProjection.projectionKind");
        if (projectionKind == Variance.OUT_VARIANCE) {
            return new TypeProjectionImpl(projectionKind, approximateCapturedTypes(type).getUpper());
        }
        if (z) {
            return new TypeProjectionImpl(projectionKind, approximateCapturedTypes(type).getLower());
        }
        return substituteCapturedTypesWithProjections(typeProjection);
    }

    private static final ApproximationBounds<TypeArgument> approximateProjection(TypeArgument typeArgument) {
        ApproximationBounds<KotlinType> approximateCapturedTypes = approximateCapturedTypes(typeArgument.getInProjection());
        ApproximationBounds<KotlinType> approximateCapturedTypes2 = approximateCapturedTypes(typeArgument.getOutProjection());
        return new ApproximationBounds<>(new TypeArgument(typeArgument.getTypeParameter(), approximateCapturedTypes.component2(), approximateCapturedTypes2.component1()), new TypeArgument(typeArgument.getTypeParameter(), approximateCapturedTypes.component1(), approximateCapturedTypes2.component2()));
    }

    private static final KotlinType replaceTypeArguments(@NotNull KotlinType kotlinType, List<TypeArgument> list) {
        int collectionSizeOrDefault;
        boolean z = kotlinType.getArguments().size() == list.size();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Incorrect type arguments " + list);
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (TypeArgument typeArgument : list) {
            arrayList.add(toTypeProjection(typeArgument));
        }
        return TypeSubstitutionKt.replace$default(kotlinType, arrayList, (Annotations) null, 2, (Object) null);
    }

    private static final TypeProjection substituteCapturedTypesWithProjections(TypeProjection typeProjection) {
        TypeSubstitutor create = TypeSubstitutor.create(new TypeConstructorSubstitution() { // from class: kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt$substituteCapturedTypesWithProjections$typeSubstitutor$1
            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution
            @Nullable
            public TypeProjection get(@NotNull TypeConstructor key) {
                Intrinsics.checkParameterIsNotNull(key, "key");
                if (!(key instanceof CapturedTypeConstructor)) {
                    key = null;
                }
                CapturedTypeConstructor capturedTypeConstructor = (CapturedTypeConstructor) key;
                if (capturedTypeConstructor != null) {
                    if (capturedTypeConstructor.getProjection().isStarProjection()) {
                        return new TypeProjectionImpl(Variance.OUT_VARIANCE, capturedTypeConstructor.getProjection().getType());
                    }
                    return capturedTypeConstructor.getProjection();
                }
                return null;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(create, "TypeSubstitutor.create(o…ojection\n        }\n    })");
        return create.substituteWithoutApproximation(typeProjection);
    }

    private static final TypeArgument toTypeArgument(@NotNull TypeProjection typeProjection, TypeParameterDescriptor typeParameterDescriptor) {
        int i = WhenMappings.$EnumSwitchMapping$0[TypeSubstitutor.combine(typeParameterDescriptor.getVariance(), typeProjection).ordinal()];
        if (i == 1) {
            KotlinType type = typeProjection.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "type");
            KotlinType type2 = typeProjection.getType();
            Intrinsics.checkExpressionValueIsNotNull(type2, "type");
            return new TypeArgument(typeParameterDescriptor, type, type2);
        } else if (i == 2) {
            KotlinType type3 = typeProjection.getType();
            Intrinsics.checkExpressionValueIsNotNull(type3, "type");
            SimpleType nullableAnyType = DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor).getNullableAnyType();
            Intrinsics.checkExpressionValueIsNotNull(nullableAnyType, "typeParameter.builtIns.nullableAnyType");
            return new TypeArgument(typeParameterDescriptor, type3, nullableAnyType);
        } else if (i != 3) {
            throw new NoWhenBranchMatchedException();
        } else {
            SimpleType nothingType = DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor).getNothingType();
            Intrinsics.checkExpressionValueIsNotNull(nothingType, "typeParameter.builtIns.nothingType");
            KotlinType type4 = typeProjection.getType();
            Intrinsics.checkExpressionValueIsNotNull(type4, "type");
            return new TypeArgument(typeParameterDescriptor, nothingType, type4);
        }
    }

    private static final TypeProjection toTypeProjection(@NotNull TypeArgument typeArgument) {
        boolean isConsistent = typeArgument.isConsistent();
        if (_Assertions.ENABLED && !isConsistent) {
            DescriptorRenderer withOptions = DescriptorRenderer.Companion.withOptions(CapturedTypeApproximationKt$toTypeProjection$1$descriptorRenderer$1.INSTANCE);
            StringBuilder outline108 = GeneratedOutlineSupport1.outline108("Only consistent enhanced type projection can be converted to type projection, but ", JsonReaderKt.BEGIN_LIST);
            outline108.append(withOptions.render(typeArgument.getTypeParameter()));
            outline108.append(": <");
            outline108.append(withOptions.renderType(typeArgument.getInProjection()));
            outline108.append(", ");
            outline108.append(withOptions.renderType(typeArgument.getOutProjection()));
            outline108.append(">]");
            outline108.append(" was found");
            throw new AssertionError(outline108.toString());
        }
        CapturedTypeApproximationKt$toTypeProjection$2 capturedTypeApproximationKt$toTypeProjection$2 = new CapturedTypeApproximationKt$toTypeProjection$2(typeArgument);
        if (Intrinsics.areEqual(typeArgument.getInProjection(), typeArgument.getOutProjection())) {
            return new TypeProjectionImpl(typeArgument.getInProjection());
        }
        if (KotlinBuiltIns.isNothing(typeArgument.getInProjection()) && typeArgument.getTypeParameter().getVariance() != Variance.IN_VARIANCE) {
            return new TypeProjectionImpl(capturedTypeApproximationKt$toTypeProjection$2.mo12165invoke(Variance.OUT_VARIANCE), typeArgument.getOutProjection());
        }
        return KotlinBuiltIns.isNullableAny(typeArgument.getOutProjection()) ? new TypeProjectionImpl(capturedTypeApproximationKt$toTypeProjection$2.mo12165invoke(Variance.IN_VARIANCE), typeArgument.getInProjection()) : new TypeProjectionImpl(capturedTypeApproximationKt$toTypeProjection$2.mo12165invoke(Variance.OUT_VARIANCE), typeArgument.getOutProjection());
    }
}
