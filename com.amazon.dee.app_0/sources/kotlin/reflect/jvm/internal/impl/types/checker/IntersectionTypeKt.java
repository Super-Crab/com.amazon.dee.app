package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.DynamicTypesKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import org.jetbrains.annotations.NotNull;
/* compiled from: IntersectionType.kt */
/* loaded from: classes4.dex */
public final class IntersectionTypeKt {
    @NotNull
    public static final UnwrappedType intersectTypes(@NotNull List<? extends UnwrappedType> types) {
        int collectionSizeOrDefault;
        int collectionSizeOrDefault2;
        SimpleType lowerBound;
        Intrinsics.checkParameterIsNotNull(types, "types");
        int size = types.size();
        if (size != 0) {
            if (size != 1) {
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(types, 10);
                ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                boolean z = false;
                boolean z2 = false;
                for (UnwrappedType unwrappedType : types) {
                    z = z || KotlinTypeKt.isError(unwrappedType);
                    if (unwrappedType instanceof SimpleType) {
                        lowerBound = (SimpleType) unwrappedType;
                    } else if (unwrappedType instanceof FlexibleType) {
                        if (DynamicTypesKt.isDynamic(unwrappedType)) {
                            return unwrappedType;
                        }
                        lowerBound = ((FlexibleType) unwrappedType).getLowerBound();
                        z2 = true;
                    } else {
                        throw new NoWhenBranchMatchedException();
                    }
                    arrayList.add(lowerBound);
                }
                if (z) {
                    SimpleType createErrorType = ErrorUtils.createErrorType("Intersection of error types: " + types);
                    Intrinsics.checkExpressionValueIsNotNull(createErrorType, "ErrorUtils.createErrorTy… of error types: $types\")");
                    return createErrorType;
                } else if (!z2) {
                    return TypeIntersector.INSTANCE.intersectTypes$descriptors(arrayList);
                } else {
                    collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(types, 10);
                    ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
                    for (UnwrappedType unwrappedType2 : types) {
                        arrayList2.add(FlexibleTypesKt.upperIfFlexible(unwrappedType2));
                    }
                    return KotlinTypeFactory.flexibleType(TypeIntersector.INSTANCE.intersectTypes$descriptors(arrayList), TypeIntersector.INSTANCE.intersectTypes$descriptors(arrayList2));
                }
            }
            return (UnwrappedType) CollectionsKt.single((List<? extends Object>) types);
        }
        throw new IllegalStateException("Expected some types".toString());
    }
}
