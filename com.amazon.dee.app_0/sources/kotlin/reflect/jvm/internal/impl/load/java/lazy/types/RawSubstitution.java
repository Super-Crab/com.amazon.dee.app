package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
/* compiled from: RawType.kt */
/* loaded from: classes3.dex */
public final class RawSubstitution extends TypeSubstitution {
    public static final RawSubstitution INSTANCE = new RawSubstitution();
    private static final JavaTypeAttributes lowerTypeAttr = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null).withFlexibility(JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND);
    private static final JavaTypeAttributes upperTypeAttr = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null).withFlexibility(JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND);

    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[JavaTypeFlexibility.values().length];

        static {
            $EnumSwitchMapping$0[JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND.ordinal()] = 1;
            $EnumSwitchMapping$0[JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND.ordinal()] = 2;
            $EnumSwitchMapping$0[JavaTypeFlexibility.INFLEXIBLE.ordinal()] = 3;
        }
    }

    private RawSubstitution() {
    }

    public static /* synthetic */ TypeProjection computeProjection$default(RawSubstitution rawSubstitution, TypeParameterDescriptor typeParameterDescriptor, JavaTypeAttributes javaTypeAttributes, KotlinType kotlinType, int i, Object obj) {
        if ((i & 4) != 0) {
            kotlinType = JavaTypeResolverKt.getErasedUpperBound$default(typeParameterDescriptor, null, null, 3, null);
        }
        return rawSubstitution.computeProjection(typeParameterDescriptor, javaTypeAttributes, kotlinType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<SimpleType, Boolean> eraseInflexibleBasedOnClassDescriptor(SimpleType simpleType, ClassDescriptor classDescriptor, JavaTypeAttributes javaTypeAttributes) {
        int collectionSizeOrDefault;
        List listOf;
        if (simpleType.mo12131getConstructor().getParameters().isEmpty()) {
            return TuplesKt.to(simpleType, false);
        }
        if (KotlinBuiltIns.isArray(simpleType)) {
            TypeProjection typeProjection = simpleType.getArguments().get(0);
            Variance projectionKind = typeProjection.getProjectionKind();
            KotlinType type = typeProjection.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "componentTypeProjection.type");
            listOf = CollectionsKt__CollectionsJVMKt.listOf(new TypeProjectionImpl(projectionKind, eraseType(type)));
            return TuplesKt.to(KotlinTypeFactory.simpleType$default(simpleType.mo12070getAnnotations(), simpleType.mo12131getConstructor(), listOf, simpleType.isMarkedNullable(), null, 16, null), false);
        } else if (KotlinTypeKt.isError(simpleType)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Raw error type: ");
            outline107.append(simpleType.mo12131getConstructor());
            return TuplesKt.to(ErrorUtils.createErrorType(outline107.toString()), false);
        } else {
            MemberScope memberScope = classDescriptor.getMemberScope(INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(memberScope, "declaration.getMemberScope(RawSubstitution)");
            Annotations mo12070getAnnotations = simpleType.mo12070getAnnotations();
            TypeConstructor mo11528getTypeConstructor = classDescriptor.mo11528getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "declaration.typeConstructor");
            TypeConstructor mo11528getTypeConstructor2 = classDescriptor.mo11528getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor2, "declaration.typeConstructor");
            List<TypeParameterDescriptor> parameters = mo11528getTypeConstructor2.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "declaration.typeConstructor.parameters");
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (TypeParameterDescriptor parameter : parameters) {
                RawSubstitution rawSubstitution = INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(parameter, "parameter");
                arrayList.add(computeProjection$default(rawSubstitution, parameter, javaTypeAttributes, null, 4, null));
            }
            return TuplesKt.to(KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(mo12070getAnnotations, mo11528getTypeConstructor, arrayList, simpleType.isMarkedNullable(), memberScope, new RawSubstitution$eraseInflexibleBasedOnClassDescriptor$2(classDescriptor, simpleType, javaTypeAttributes)), true);
        }
    }

    private final KotlinType eraseType(KotlinType kotlinType) {
        ClassifierDescriptor mo12085getDeclarationDescriptor = kotlinType.mo12131getConstructor().mo12085getDeclarationDescriptor();
        if (mo12085getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            return eraseType(JavaTypeResolverKt.getErasedUpperBound$default((TypeParameterDescriptor) mo12085getDeclarationDescriptor, null, null, 3, null));
        }
        if (mo12085getDeclarationDescriptor instanceof ClassDescriptor) {
            ClassifierDescriptor mo12085getDeclarationDescriptor2 = FlexibleTypesKt.upperIfFlexible(kotlinType).mo12131getConstructor().mo12085getDeclarationDescriptor();
            if (mo12085getDeclarationDescriptor2 instanceof ClassDescriptor) {
                Pair<SimpleType, Boolean> eraseInflexibleBasedOnClassDescriptor = eraseInflexibleBasedOnClassDescriptor(FlexibleTypesKt.lowerIfFlexible(kotlinType), (ClassDescriptor) mo12085getDeclarationDescriptor, lowerTypeAttr);
                SimpleType component1 = eraseInflexibleBasedOnClassDescriptor.component1();
                boolean booleanValue = eraseInflexibleBasedOnClassDescriptor.component2().booleanValue();
                Pair<SimpleType, Boolean> eraseInflexibleBasedOnClassDescriptor2 = eraseInflexibleBasedOnClassDescriptor(FlexibleTypesKt.upperIfFlexible(kotlinType), (ClassDescriptor) mo12085getDeclarationDescriptor2, upperTypeAttr);
                SimpleType component12 = eraseInflexibleBasedOnClassDescriptor2.component1();
                boolean booleanValue2 = eraseInflexibleBasedOnClassDescriptor2.component2().booleanValue();
                if (!booleanValue && !booleanValue2) {
                    return KotlinTypeFactory.flexibleType(component1, component12);
                }
                return new RawTypeImpl(component1, component12);
            }
            throw new IllegalStateException(("For some reason declaration for upper bound is not a class but \"" + mo12085getDeclarationDescriptor2 + "\" while for lower it's \"" + mo12085getDeclarationDescriptor + '\"').toString());
        }
        throw new IllegalStateException(("Unexpected declaration kind: " + mo12085getDeclarationDescriptor).toString());
    }

    @NotNull
    public final TypeProjection computeProjection(@NotNull TypeParameterDescriptor parameter, @NotNull JavaTypeAttributes attr, @NotNull KotlinType erasedUpperBound) {
        TypeProjection makeStarProjection;
        Intrinsics.checkParameterIsNotNull(parameter, "parameter");
        Intrinsics.checkParameterIsNotNull(attr, "attr");
        Intrinsics.checkParameterIsNotNull(erasedUpperBound, "erasedUpperBound");
        int i = WhenMappings.$EnumSwitchMapping$0[attr.getFlexibility().ordinal()];
        if (i != 1) {
            if (i != 2 && i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            if (!parameter.getVariance().getAllowsOutPosition()) {
                return new TypeProjectionImpl(Variance.INVARIANT, DescriptorUtilsKt.getBuiltIns(parameter).getNothingType());
            }
            List<TypeParameterDescriptor> parameters = erasedUpperBound.mo12131getConstructor().getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "erasedUpperBound.constructor.parameters");
            if (!parameters.isEmpty()) {
                makeStarProjection = new TypeProjectionImpl(Variance.OUT_VARIANCE, erasedUpperBound);
            } else {
                makeStarProjection = JavaTypeResolverKt.makeStarProjection(parameter, attr);
            }
            return makeStarProjection;
        }
        return new TypeProjectionImpl(Variance.INVARIANT, erasedUpperBound);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean isEmpty() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    @NotNull
    /* renamed from: get  reason: collision with other method in class */
    public TypeProjectionImpl mo12121get(@NotNull KotlinType key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return new TypeProjectionImpl(eraseType(key));
    }
}
