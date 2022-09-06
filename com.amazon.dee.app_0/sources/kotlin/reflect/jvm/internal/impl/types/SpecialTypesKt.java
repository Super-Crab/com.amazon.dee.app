package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SpecialTypes.kt */
/* loaded from: classes4.dex */
public final class SpecialTypesKt {
    @Nullable
    public static final AbbreviatedType getAbbreviatedType(@NotNull KotlinType getAbbreviatedType) {
        Intrinsics.checkParameterIsNotNull(getAbbreviatedType, "$this$getAbbreviatedType");
        UnwrappedType unwrap = getAbbreviatedType.unwrap();
        if (!(unwrap instanceof AbbreviatedType)) {
            unwrap = null;
        }
        return (AbbreviatedType) unwrap;
    }

    @Nullable
    public static final SimpleType getAbbreviation(@NotNull KotlinType getAbbreviation) {
        Intrinsics.checkParameterIsNotNull(getAbbreviation, "$this$getAbbreviation");
        AbbreviatedType abbreviatedType = getAbbreviatedType(getAbbreviation);
        if (abbreviatedType != null) {
            return abbreviatedType.getAbbreviation();
        }
        return null;
    }

    public static final boolean isDefinitelyNotNullType(@NotNull KotlinType isDefinitelyNotNullType) {
        Intrinsics.checkParameterIsNotNull(isDefinitelyNotNullType, "$this$isDefinitelyNotNullType");
        return isDefinitelyNotNullType.unwrap() instanceof DefinitelyNotNullType;
    }

    @NotNull
    public static final UnwrappedType makeDefinitelyNotNullOrNotNull(@NotNull UnwrappedType makeDefinitelyNotNullOrNotNull) {
        Intrinsics.checkParameterIsNotNull(makeDefinitelyNotNullOrNotNull, "$this$makeDefinitelyNotNullOrNotNull");
        UnwrappedType makeDefinitelyNotNull$descriptors = DefinitelyNotNullType.Companion.makeDefinitelyNotNull$descriptors(makeDefinitelyNotNullOrNotNull);
        if (makeDefinitelyNotNull$descriptors == null) {
            makeDefinitelyNotNull$descriptors = makeIntersectionTypeDefinitelyNotNullOrNotNull(makeDefinitelyNotNullOrNotNull);
        }
        return makeDefinitelyNotNull$descriptors != null ? makeDefinitelyNotNull$descriptors : makeDefinitelyNotNullOrNotNull.mo12132makeNullableAsSpecified(false);
    }

    private static final SimpleType makeIntersectionTypeDefinitelyNotNullOrNotNull(@NotNull KotlinType kotlinType) {
        IntersectionTypeConstructor makeDefinitelyNotNullOrNotNull;
        TypeConstructor mo12131getConstructor = kotlinType.mo12131getConstructor();
        if (!(mo12131getConstructor instanceof IntersectionTypeConstructor)) {
            mo12131getConstructor = null;
        }
        IntersectionTypeConstructor intersectionTypeConstructor = (IntersectionTypeConstructor) mo12131getConstructor;
        if (intersectionTypeConstructor == null || (makeDefinitelyNotNullOrNotNull = makeDefinitelyNotNullOrNotNull(intersectionTypeConstructor)) == null) {
            return null;
        }
        return makeDefinitelyNotNullOrNotNull.createType();
    }

    @NotNull
    public static final SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull(@NotNull SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull) {
        Intrinsics.checkParameterIsNotNull(makeSimpleTypeDefinitelyNotNullOrNotNull, "$this$makeSimpleTypeDefinitelyNotNullOrNotNull");
        SimpleType makeDefinitelyNotNull$descriptors = DefinitelyNotNullType.Companion.makeDefinitelyNotNull$descriptors(makeSimpleTypeDefinitelyNotNullOrNotNull);
        if (makeDefinitelyNotNull$descriptors == null) {
            makeDefinitelyNotNull$descriptors = makeIntersectionTypeDefinitelyNotNullOrNotNull(makeSimpleTypeDefinitelyNotNullOrNotNull);
        }
        return makeDefinitelyNotNull$descriptors != null ? makeDefinitelyNotNull$descriptors : makeSimpleTypeDefinitelyNotNullOrNotNull.mo12132makeNullableAsSpecified(false);
    }

    @NotNull
    public static final SimpleType withAbbreviation(@NotNull SimpleType withAbbreviation, @NotNull SimpleType abbreviatedType) {
        Intrinsics.checkParameterIsNotNull(withAbbreviation, "$this$withAbbreviation");
        Intrinsics.checkParameterIsNotNull(abbreviatedType, "abbreviatedType");
        return KotlinTypeKt.isError(withAbbreviation) ? withAbbreviation : new AbbreviatedType(withAbbreviation, abbreviatedType);
    }

    private static final IntersectionTypeConstructor makeDefinitelyNotNullOrNotNull(@NotNull IntersectionTypeConstructor intersectionTypeConstructor) {
        int collectionSizeOrDefault;
        Collection<KotlinType> mo12135getSupertypes = intersectionTypeConstructor.mo12135getSupertypes();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(mo12135getSupertypes, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        boolean z = false;
        for (KotlinType kotlinType : mo12135getSupertypes) {
            if (TypeUtils.isNullableType(kotlinType)) {
                z = true;
                kotlinType = makeDefinitelyNotNullOrNotNull(kotlinType.unwrap());
            }
            arrayList.add(kotlinType);
        }
        if (!z) {
            return null;
        }
        return new IntersectionTypeConstructor(arrayList);
    }
}
