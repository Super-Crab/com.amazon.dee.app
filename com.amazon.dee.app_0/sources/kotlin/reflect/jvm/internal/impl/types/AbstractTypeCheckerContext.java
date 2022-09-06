package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Set;
import kotlin._Assertions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AbstractTypeChecker.kt */
/* loaded from: classes4.dex */
public abstract class AbstractTypeCheckerContext implements TypeSystemContext {
    private int argumentsDepth;
    @Nullable
    private ArrayDeque<SimpleTypeMarker> supertypesDeque;
    private boolean supertypesLocked;
    @Nullable
    private Set<SimpleTypeMarker> supertypesSet;

    /* compiled from: AbstractTypeChecker.kt */
    /* loaded from: classes4.dex */
    public enum LowerCapturedTypePolicy {
        CHECK_ONLY_LOWER,
        CHECK_SUBTYPE_AND_LOWER,
        SKIP_LOWER
    }

    /* compiled from: AbstractTypeChecker.kt */
    /* loaded from: classes4.dex */
    public static abstract class SupertypesPolicy {

        /* compiled from: AbstractTypeChecker.kt */
        /* loaded from: classes4.dex */
        public static abstract class DoCustomTransform extends SupertypesPolicy {
            public DoCustomTransform() {
                super(null);
            }
        }

        /* compiled from: AbstractTypeChecker.kt */
        /* loaded from: classes4.dex */
        public static final class LowerIfFlexible extends SupertypesPolicy {
            public static final LowerIfFlexible INSTANCE = new LowerIfFlexible();

            private LowerIfFlexible() {
                super(null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext.SupertypesPolicy
            @NotNull
            /* renamed from: transformType */
            public SimpleTypeMarker mo12079transformType(@NotNull AbstractTypeCheckerContext context, @NotNull KotlinTypeMarker type) {
                Intrinsics.checkParameterIsNotNull(context, "context");
                Intrinsics.checkParameterIsNotNull(type, "type");
                return context.lowerBoundIfFlexible(type);
            }
        }

        /* compiled from: AbstractTypeChecker.kt */
        /* loaded from: classes4.dex */
        public static final class None extends SupertypesPolicy {
            public static final None INSTANCE = new None();

            private None() {
                super(null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext.SupertypesPolicy
            /* renamed from: transformType  reason: collision with other method in class */
            public /* bridge */ /* synthetic */ SimpleTypeMarker mo12079transformType(AbstractTypeCheckerContext abstractTypeCheckerContext, KotlinTypeMarker kotlinTypeMarker) {
                return (SimpleTypeMarker) mo12079transformType(abstractTypeCheckerContext, kotlinTypeMarker);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext.SupertypesPolicy
            @NotNull
            /* renamed from: transformType */
            public Void mo12079transformType(@NotNull AbstractTypeCheckerContext context, @NotNull KotlinTypeMarker type) {
                Intrinsics.checkParameterIsNotNull(context, "context");
                Intrinsics.checkParameterIsNotNull(type, "type");
                throw new UnsupportedOperationException("Should not be called");
            }
        }

        /* compiled from: AbstractTypeChecker.kt */
        /* loaded from: classes4.dex */
        public static final class UpperIfFlexible extends SupertypesPolicy {
            public static final UpperIfFlexible INSTANCE = new UpperIfFlexible();

            private UpperIfFlexible() {
                super(null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext.SupertypesPolicy
            @NotNull
            /* renamed from: transformType */
            public SimpleTypeMarker mo12079transformType(@NotNull AbstractTypeCheckerContext context, @NotNull KotlinTypeMarker type) {
                Intrinsics.checkParameterIsNotNull(context, "context");
                Intrinsics.checkParameterIsNotNull(type, "type");
                return context.upperBoundIfFlexible(type);
            }
        }

        private SupertypesPolicy() {
        }

        @NotNull
        /* renamed from: transformType */
        public abstract SimpleTypeMarker mo12079transformType(@NotNull AbstractTypeCheckerContext abstractTypeCheckerContext, @NotNull KotlinTypeMarker kotlinTypeMarker);

        public /* synthetic */ SupertypesPolicy(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Nullable
    public Boolean addSubtypeConstraint(@NotNull KotlinTypeMarker subType, @NotNull KotlinTypeMarker superType) {
        Intrinsics.checkParameterIsNotNull(subType, "subType");
        Intrinsics.checkParameterIsNotNull(superType, "superType");
        return null;
    }

    public abstract boolean areEqualTypeConstructors(@NotNull TypeConstructorMarker typeConstructorMarker, @NotNull TypeConstructorMarker typeConstructorMarker2);

    public final void clear() {
        ArrayDeque<SimpleTypeMarker> arrayDeque = this.supertypesDeque;
        if (arrayDeque == null) {
            Intrinsics.throwNpe();
        }
        arrayDeque.clear();
        Set<SimpleTypeMarker> set = this.supertypesSet;
        if (set == null) {
            Intrinsics.throwNpe();
        }
        set.clear();
        this.supertypesLocked = false;
    }

    @Nullable
    public List<SimpleTypeMarker> fastCorrespondingSupertypes(@NotNull SimpleTypeMarker fastCorrespondingSupertypes, @NotNull TypeConstructorMarker constructor) {
        Intrinsics.checkParameterIsNotNull(fastCorrespondingSupertypes, "$this$fastCorrespondingSupertypes");
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        return TypeSystemContext.DefaultImpls.fastCorrespondingSupertypes(this, fastCorrespondingSupertypes, constructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeArgumentMarker get(@NotNull TypeArgumentListMarker get, int i) {
        Intrinsics.checkParameterIsNotNull(get, "$this$get");
        return TypeSystemContext.DefaultImpls.get(this, get, i);
    }

    @Nullable
    public TypeArgumentMarker getArgumentOrNull(@NotNull SimpleTypeMarker getArgumentOrNull, int i) {
        Intrinsics.checkParameterIsNotNull(getArgumentOrNull, "$this$getArgumentOrNull");
        return TypeSystemContext.DefaultImpls.getArgumentOrNull(this, getArgumentOrNull, i);
    }

    @NotNull
    public LowerCapturedTypePolicy getLowerCapturedTypePolicy(@NotNull SimpleTypeMarker subType, @NotNull CapturedTypeMarker superType) {
        Intrinsics.checkParameterIsNotNull(subType, "subType");
        Intrinsics.checkParameterIsNotNull(superType, "superType");
        return LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER;
    }

    @Nullable
    public final ArrayDeque<SimpleTypeMarker> getSupertypesDeque() {
        return this.supertypesDeque;
    }

    @Nullable
    public final Set<SimpleTypeMarker> getSupertypesSet() {
        return this.supertypesSet;
    }

    public boolean hasFlexibleNullability(@NotNull KotlinTypeMarker hasFlexibleNullability) {
        Intrinsics.checkParameterIsNotNull(hasFlexibleNullability, "$this$hasFlexibleNullability");
        return TypeSystemContext.DefaultImpls.hasFlexibleNullability(this, hasFlexibleNullability);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemOptimizationContext
    public boolean identicalArguments(@NotNull SimpleTypeMarker a, @NotNull SimpleTypeMarker b) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        return TypeSystemContext.DefaultImpls.identicalArguments(this, a, b);
    }

    public final void initialize() {
        boolean z = !this.supertypesLocked;
        if (!_Assertions.ENABLED || z) {
            this.supertypesLocked = true;
            if (this.supertypesDeque == null) {
                this.supertypesDeque = new ArrayDeque<>(4);
            }
            if (this.supertypesSet != null) {
                return;
            }
            this.supertypesSet = SmartSet.Companion.create();
            return;
        }
        throw new AssertionError("Assertion failed");
    }

    public abstract boolean isAllowedTypeVariable(@NotNull KotlinTypeMarker kotlinTypeMarker);

    public boolean isClassType(@NotNull SimpleTypeMarker isClassType) {
        Intrinsics.checkParameterIsNotNull(isClassType, "$this$isClassType");
        return TypeSystemContext.DefaultImpls.isClassType(this, isClassType);
    }

    public boolean isDefinitelyNotNullType(@NotNull KotlinTypeMarker isDefinitelyNotNullType) {
        Intrinsics.checkParameterIsNotNull(isDefinitelyNotNullType, "$this$isDefinitelyNotNullType");
        return TypeSystemContext.DefaultImpls.isDefinitelyNotNullType(this, isDefinitelyNotNullType);
    }

    public boolean isDynamic(@NotNull KotlinTypeMarker isDynamic) {
        Intrinsics.checkParameterIsNotNull(isDynamic, "$this$isDynamic");
        return TypeSystemContext.DefaultImpls.isDynamic(this, isDynamic);
    }

    public abstract boolean isErrorTypeEqualsToAnything();

    public boolean isIntegerLiteralType(@NotNull SimpleTypeMarker isIntegerLiteralType) {
        Intrinsics.checkParameterIsNotNull(isIntegerLiteralType, "$this$isIntegerLiteralType");
        return TypeSystemContext.DefaultImpls.isIntegerLiteralType(this, isIntegerLiteralType);
    }

    public boolean isNothing(@NotNull KotlinTypeMarker isNothing) {
        Intrinsics.checkParameterIsNotNull(isNothing, "$this$isNothing");
        return TypeSystemContext.DefaultImpls.isNothing(this, isNothing);
    }

    public abstract boolean isStubTypeEqualsToAnything();

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public SimpleTypeMarker lowerBoundIfFlexible(@NotNull KotlinTypeMarker lowerBoundIfFlexible) {
        Intrinsics.checkParameterIsNotNull(lowerBoundIfFlexible, "$this$lowerBoundIfFlexible");
        return TypeSystemContext.DefaultImpls.lowerBoundIfFlexible(this, lowerBoundIfFlexible);
    }

    @NotNull
    public KotlinTypeMarker prepareType(@NotNull KotlinTypeMarker type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        return type;
    }

    @NotNull
    public KotlinTypeMarker refineType(@NotNull KotlinTypeMarker type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        return type;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int size(@NotNull TypeArgumentListMarker size) {
        Intrinsics.checkParameterIsNotNull(size, "$this$size");
        return TypeSystemContext.DefaultImpls.size(this, size);
    }

    @NotNull
    /* renamed from: substitutionSupertypePolicy */
    public abstract SupertypesPolicy mo12129substitutionSupertypePolicy(@NotNull SimpleTypeMarker simpleTypeMarker);

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeConstructorMarker typeConstructor(@NotNull KotlinTypeMarker typeConstructor) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "$this$typeConstructor");
        return TypeSystemContext.DefaultImpls.typeConstructor(this, typeConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public SimpleTypeMarker upperBoundIfFlexible(@NotNull KotlinTypeMarker upperBoundIfFlexible) {
        Intrinsics.checkParameterIsNotNull(upperBoundIfFlexible, "$this$upperBoundIfFlexible");
        return TypeSystemContext.DefaultImpls.upperBoundIfFlexible(this, upperBoundIfFlexible);
    }
}
