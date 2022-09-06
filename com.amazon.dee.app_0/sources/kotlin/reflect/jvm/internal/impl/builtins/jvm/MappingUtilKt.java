package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: mappingUtil.kt */
/* loaded from: classes2.dex */
public final class MappingUtilKt {
    @NotNull
    public static final TypeConstructorSubstitution createMappedTypeParametersSubstitution(@NotNull ClassDescriptor from, @NotNull ClassDescriptor to) {
        int collectionSizeOrDefault;
        int collectionSizeOrDefault2;
        List zip;
        Map map;
        Intrinsics.checkParameterIsNotNull(from, "from");
        Intrinsics.checkParameterIsNotNull(to, "to");
        boolean z = from.getDeclaredTypeParameters().size() == to.getDeclaredTypeParameters().size();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError(from + " and " + to + " should have same number of type parameters, but " + from.getDeclaredTypeParameters().size() + " / " + to.getDeclaredTypeParameters().size() + " found");
        }
        TypeConstructorSubstitution.Companion companion = TypeConstructorSubstitution.Companion;
        List<TypeParameterDescriptor> declaredTypeParameters = from.getDeclaredTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(declaredTypeParameters, "from.declaredTypeParameters");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(declaredTypeParameters, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (TypeParameterDescriptor typeParameterDescriptor : declaredTypeParameters) {
            arrayList.add(typeParameterDescriptor.mo11528getTypeConstructor());
        }
        List<TypeParameterDescriptor> declaredTypeParameters2 = to.getDeclaredTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(declaredTypeParameters2, "to.declaredTypeParameters");
        collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(declaredTypeParameters2, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
        for (TypeParameterDescriptor it2 : declaredTypeParameters2) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            SimpleType defaultType = it2.getDefaultType();
            Intrinsics.checkExpressionValueIsNotNull(defaultType, "it.defaultType");
            arrayList2.add(TypeUtilsKt.asTypeProjection(defaultType));
        }
        zip = CollectionsKt___CollectionsKt.zip(arrayList, arrayList2);
        map = MapsKt__MapsKt.toMap(zip);
        return TypeConstructorSubstitution.Companion.createByConstructorsMap$default(companion, map, false, 2, null);
    }
}
