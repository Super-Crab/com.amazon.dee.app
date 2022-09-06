package kotlin.reflect.jvm.internal.impl.types;

import kotlin._Assertions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.checker.NewTypeVariableConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.NullabilityChecker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SpecialTypes.kt */
/* loaded from: classes4.dex */
public final class DefinitelyNotNullType extends DelegatingSimpleType implements CustomTypeVariable, DefinitelyNotNullTypeMarker {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SimpleType original;

    /* compiled from: SpecialTypes.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        private final boolean makesSenseToBeDefinitelyNotNull(UnwrappedType unwrappedType) {
            return TypeUtilsKt.canHaveUndefinedNullability(unwrappedType) && !NullabilityChecker.INSTANCE.isSubtypeOfAny(unwrappedType);
        }

        @Nullable
        public final DefinitelyNotNullType makeDefinitelyNotNull$descriptors(@NotNull UnwrappedType type) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            if (type instanceof DefinitelyNotNullType) {
                return (DefinitelyNotNullType) type;
            }
            if (!makesSenseToBeDefinitelyNotNull(type)) {
                return null;
            }
            if (type instanceof FlexibleType) {
                FlexibleType flexibleType = (FlexibleType) type;
                boolean areEqual = Intrinsics.areEqual(flexibleType.getLowerBound().mo12131getConstructor(), flexibleType.getUpperBound().mo12131getConstructor());
                if (_Assertions.ENABLED && !areEqual) {
                    throw new AssertionError("DefinitelyNotNullType for flexible type (" + type + ") can be created only from type variable with the same constructor for bounds");
                }
            }
            return new DefinitelyNotNullType(FlexibleTypesKt.lowerIfFlexible(type), null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private DefinitelyNotNullType(SimpleType simpleType) {
        this.original = simpleType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    @NotNull
    protected SimpleType getDelegate() {
        return this.original;
    }

    @NotNull
    public final SimpleType getOriginal() {
        return this.original;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    public boolean isMarkedNullable() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.CustomTypeVariable
    public boolean isTypeVariable() {
        return (getDelegate().mo12131getConstructor() instanceof NewTypeVariableConstructor) || (getDelegate().mo12131getConstructor().mo12085getDeclarationDescriptor() instanceof TypeParameterDescriptor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.CustomTypeVariable
    @NotNull
    public KotlinType substitutionResult(@NotNull KotlinType replacement) {
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        return SpecialTypesKt.makeDefinitelyNotNullOrNotNull(replacement.unwrap());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.SimpleType
    @NotNull
    public String toString() {
        return getDelegate() + "!!";
    }

    public /* synthetic */ DefinitelyNotNullType(SimpleType simpleType, DefaultConstructorMarker defaultConstructorMarker) {
        this(simpleType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.SimpleType, kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: makeNullableAsSpecified */
    public SimpleType mo12132makeNullableAsSpecified(boolean z) {
        return z ? getDelegate().mo12132makeNullableAsSpecified(z) : this;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    @NotNull
    /* renamed from: replaceDelegate */
    public DefinitelyNotNullType mo12116replaceDelegate(@NotNull SimpleType delegate) {
        Intrinsics.checkParameterIsNotNull(delegate, "delegate");
        return new DefinitelyNotNullType(delegate);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.SimpleType, kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: replaceAnnotations */
    public DefinitelyNotNullType mo12134replaceAnnotations(@NotNull Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        return new DefinitelyNotNullType(getDelegate().mo12134replaceAnnotations(newAnnotations));
    }
}
