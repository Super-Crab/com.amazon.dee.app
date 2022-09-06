package kotlin.reflect.jvm.internal.impl.types;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin._Assertions;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: flexibleTypes.kt */
/* loaded from: classes4.dex */
public final class FlexibleTypeImpl extends FlexibleType implements CustomTypeVariable {
    public static final Companion Companion = new Companion(null);
    @JvmField
    public static boolean RUN_SLOW_ASSERTIONS;
    private boolean assertionsDone;

    /* compiled from: flexibleTypes.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlexibleTypeImpl(@NotNull SimpleType lowerBound, @NotNull SimpleType upperBound) {
        super(lowerBound, upperBound);
        Intrinsics.checkParameterIsNotNull(lowerBound, "lowerBound");
        Intrinsics.checkParameterIsNotNull(upperBound, "upperBound");
    }

    private final void runAssertions() {
        if (!RUN_SLOW_ASSERTIONS || this.assertionsDone) {
            return;
        }
        this.assertionsDone = true;
        boolean z = !FlexibleTypesKt.isFlexible(getLowerBound());
        if (_Assertions.ENABLED && !z) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Lower bound of a flexible type can not be flexible: ");
            outline107.append(getLowerBound());
            throw new AssertionError(outline107.toString());
        }
        boolean z2 = !FlexibleTypesKt.isFlexible(getUpperBound());
        if (_Assertions.ENABLED && !z2) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Upper bound of a flexible type can not be flexible: ");
            outline1072.append(getUpperBound());
            throw new AssertionError(outline1072.toString());
        }
        boolean areEqual = true ^ Intrinsics.areEqual(getLowerBound(), getUpperBound());
        if (_Assertions.ENABLED && !areEqual) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Lower and upper bounds are equal: ");
            outline1073.append(getLowerBound());
            outline1073.append(" == ");
            outline1073.append(getUpperBound());
            throw new AssertionError(outline1073.toString());
        }
        boolean isSubtypeOf = KotlinTypeChecker.DEFAULT.isSubtypeOf(getLowerBound(), getUpperBound());
        if (!_Assertions.ENABLED || isSubtypeOf) {
            return;
        }
        StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Lower bound ");
        outline1074.append(getLowerBound());
        outline1074.append(" of a flexible type must be a subtype of the upper bound ");
        outline1074.append(getUpperBound());
        throw new AssertionError(outline1074.toString());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    @NotNull
    public SimpleType getDelegate() {
        runAssertions();
        return getLowerBound();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.CustomTypeVariable
    public boolean isTypeVariable() {
        return (getLowerBound().mo12131getConstructor().mo12085getDeclarationDescriptor() instanceof TypeParameterDescriptor) && Intrinsics.areEqual(getLowerBound().mo12131getConstructor(), getUpperBound().mo12131getConstructor());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: makeNullableAsSpecified */
    public UnwrappedType mo12132makeNullableAsSpecified(boolean z) {
        return KotlinTypeFactory.flexibleType(getLowerBound().mo12132makeNullableAsSpecified(z), getUpperBound().mo12132makeNullableAsSpecified(z));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    @NotNull
    public String render(@NotNull DescriptorRenderer renderer, @NotNull DescriptorRendererOptions options) {
        Intrinsics.checkParameterIsNotNull(renderer, "renderer");
        Intrinsics.checkParameterIsNotNull(options, "options");
        if (options.getDebugMode()) {
            StringBuilder outline104 = GeneratedOutlineSupport1.outline104('(');
            outline104.append(renderer.renderType(getLowerBound()));
            outline104.append("..");
            outline104.append(renderer.renderType(getUpperBound()));
            outline104.append(')');
            return outline104.toString();
        }
        return renderer.renderFlexibleType(renderer.renderType(getLowerBound()), renderer.renderType(getUpperBound()), TypeUtilsKt.getBuiltIns(this));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: replaceAnnotations */
    public UnwrappedType mo12134replaceAnnotations(@NotNull Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        return KotlinTypeFactory.flexibleType(getLowerBound().mo12134replaceAnnotations(newAnnotations), getUpperBound().mo12134replaceAnnotations(newAnnotations));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.CustomTypeVariable
    @NotNull
    public KotlinType substitutionResult(@NotNull KotlinType replacement) {
        UnwrappedType flexibleType;
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        UnwrappedType unwrap = replacement.unwrap();
        if (unwrap instanceof FlexibleType) {
            flexibleType = unwrap;
        } else if (!(unwrap instanceof SimpleType)) {
            throw new NoWhenBranchMatchedException();
        } else {
            SimpleType simpleType = (SimpleType) unwrap;
            flexibleType = KotlinTypeFactory.flexibleType(simpleType, simpleType.mo12132makeNullableAsSpecified(true));
        }
        return TypeWithEnhancementKt.inheritEnhancement(flexibleType, unwrap);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    /* renamed from: refine */
    public FlexibleType mo12133refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        KotlinType refineType = kotlinTypeRefiner.refineType(getLowerBound());
        if (refineType != null) {
            SimpleType simpleType = (SimpleType) refineType;
            KotlinType refineType2 = kotlinTypeRefiner.refineType(getUpperBound());
            if (refineType2 != null) {
                return new FlexibleTypeImpl(simpleType, (SimpleType) refineType2);
            }
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
    }
}
