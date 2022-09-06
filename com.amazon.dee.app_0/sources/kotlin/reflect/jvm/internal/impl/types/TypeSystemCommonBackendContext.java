package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TypeSystemCommonBackendContext.kt */
/* loaded from: classes4.dex */
public interface TypeSystemCommonBackendContext extends TypeSystemContext {

    /* compiled from: TypeSystemCommonBackendContext.kt */
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static boolean isMarkedNullable(TypeSystemCommonBackendContext typeSystemCommonBackendContext, @NotNull KotlinTypeMarker isMarkedNullable) {
            Intrinsics.checkParameterIsNotNull(isMarkedNullable, "$this$isMarkedNullable");
            return (isMarkedNullable instanceof SimpleTypeMarker) && typeSystemCommonBackendContext.isMarkedNullable((SimpleTypeMarker) isMarkedNullable);
        }

        @NotNull
        public static KotlinTypeMarker makeNullable(TypeSystemCommonBackendContext typeSystemCommonBackendContext, @NotNull KotlinTypeMarker makeNullable) {
            SimpleTypeMarker withNullability;
            Intrinsics.checkParameterIsNotNull(makeNullable, "$this$makeNullable");
            SimpleTypeMarker asSimpleType = typeSystemCommonBackendContext.asSimpleType(makeNullable);
            return (asSimpleType == null || (withNullability = typeSystemCommonBackendContext.withNullability(asSimpleType, true)) == null) ? makeNullable : withNullability;
        }
    }

    @Nullable
    FqNameUnsafe getClassFqNameUnsafe(@NotNull TypeConstructorMarker typeConstructorMarker);

    @Nullable
    PrimitiveType getPrimitiveArrayType(@NotNull TypeConstructorMarker typeConstructorMarker);

    @Nullable
    PrimitiveType getPrimitiveType(@NotNull TypeConstructorMarker typeConstructorMarker);

    @NotNull
    KotlinTypeMarker getRepresentativeUpperBound(@NotNull TypeParameterMarker typeParameterMarker);

    @Nullable
    KotlinTypeMarker getSubstitutedUnderlyingType(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @Nullable
    TypeParameterMarker getTypeParameterClassifier(@NotNull TypeConstructorMarker typeConstructorMarker);

    boolean hasAnnotation(@NotNull KotlinTypeMarker kotlinTypeMarker, @NotNull FqName fqName);

    boolean isInlineClass(@NotNull TypeConstructorMarker typeConstructorMarker);

    boolean isMarkedNullable(@NotNull KotlinTypeMarker kotlinTypeMarker);

    boolean isUnderKotlinPackage(@NotNull TypeConstructorMarker typeConstructorMarker);

    @NotNull
    KotlinTypeMarker makeNullable(@NotNull KotlinTypeMarker kotlinTypeMarker);
}
