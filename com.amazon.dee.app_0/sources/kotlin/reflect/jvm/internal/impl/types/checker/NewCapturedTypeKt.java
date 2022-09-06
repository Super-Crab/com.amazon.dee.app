package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NewCapturedType.kt */
/* loaded from: classes4.dex */
public final class NewCapturedTypeKt {
    @Nullable
    public static final SimpleType captureFromArguments(@NotNull SimpleType type, @NotNull CaptureStatus status) {
        boolean z;
        List<Pair> zip;
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(status, "status");
        if (type.getArguments().size() != type.mo12131getConstructor().getParameters().size()) {
            return null;
        }
        List<TypeProjection> arguments = type.getArguments();
        boolean z2 = true;
        if (!(arguments instanceof Collection) || !arguments.isEmpty()) {
            Iterator<T> it2 = arguments.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                if (((TypeProjection) it2.next()).getProjectionKind() == Variance.INVARIANT) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (!z) {
                    z2 = false;
                    break;
                }
            }
        }
        if (z2) {
            return null;
        }
        List<TypeParameterDescriptor> parameters = type.mo12131getConstructor().getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "type.constructor.parameters");
        zip = CollectionsKt___CollectionsKt.zip(arguments, parameters);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(zip, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Pair pair : zip) {
            TypeProjection typeProjection = (TypeProjection) pair.component1();
            TypeParameterDescriptor parameter = (TypeParameterDescriptor) pair.component2();
            if (typeProjection.getProjectionKind() != Variance.INVARIANT) {
                UnwrappedType unwrap = (typeProjection.isStarProjection() || typeProjection.getProjectionKind() != Variance.IN_VARIANCE) ? null : typeProjection.getType().unwrap();
                Intrinsics.checkExpressionValueIsNotNull(parameter, "parameter");
                typeProjection = TypeUtilsKt.asTypeProjection(new NewCapturedType(status, unwrap, typeProjection, parameter));
            }
            arrayList.add(typeProjection);
        }
        TypeSubstitutor buildSubstitutor = TypeConstructorSubstitution.Companion.create(type.mo12131getConstructor(), arrayList).buildSubstitutor();
        int size = arguments.size();
        for (int i = 0; i < size; i++) {
            TypeProjection typeProjection2 = arguments.get(i);
            TypeProjection typeProjection3 = (TypeProjection) arrayList.get(i);
            if (typeProjection2.getProjectionKind() != Variance.INVARIANT) {
                TypeParameterDescriptor typeParameterDescriptor = type.mo12131getConstructor().getParameters().get(i);
                Intrinsics.checkExpressionValueIsNotNull(typeParameterDescriptor, "type.constructor.parameters[index]");
                List<KotlinType> upperBounds = typeParameterDescriptor.getUpperBounds();
                Intrinsics.checkExpressionValueIsNotNull(upperBounds, "type.constructor.parameters[index].upperBounds");
                ArrayList arrayList2 = new ArrayList();
                for (KotlinType kotlinType : upperBounds) {
                    arrayList2.add(NewKotlinTypeChecker.Companion.getDefault().transformToNewType(buildSubstitutor.safeSubstitute(kotlinType, Variance.INVARIANT).unwrap()));
                }
                if (!typeProjection2.isStarProjection() && typeProjection2.getProjectionKind() == Variance.OUT_VARIANCE) {
                    arrayList2.add(NewKotlinTypeChecker.Companion.getDefault().transformToNewType(typeProjection2.getType().unwrap()));
                }
                KotlinType type2 = typeProjection3.getType();
                if (type2 != null) {
                    ((NewCapturedType) type2).mo12131getConstructor().initializeSupertypes(arrayList2);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.checker.NewCapturedType");
                }
            }
        }
        return KotlinTypeFactory.simpleType$default(type.mo12070getAnnotations(), type.mo12131getConstructor(), arrayList, type.isMarkedNullable(), null, 16, null);
    }
}
