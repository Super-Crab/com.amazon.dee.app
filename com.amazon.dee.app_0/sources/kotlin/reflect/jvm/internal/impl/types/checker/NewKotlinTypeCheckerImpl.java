package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerValueTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: NewKotlinTypeChecker.kt */
/* loaded from: classes4.dex */
public final class NewKotlinTypeCheckerImpl implements NewKotlinTypeChecker {
    @NotNull
    private final KotlinTypeRefiner kotlinTypeRefiner;
    @NotNull
    private final OverridingUtil overridingUtil;

    public NewKotlinTypeCheckerImpl(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        this.kotlinTypeRefiner = kotlinTypeRefiner;
        OverridingUtil createWithTypeRefiner = OverridingUtil.createWithTypeRefiner(getKotlinTypeRefiner());
        Intrinsics.checkExpressionValueIsNotNull(createWithTypeRefiner, "OverridingUtil.createWit…efiner(kotlinTypeRefiner)");
        this.overridingUtil = createWithTypeRefiner;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker
    public boolean equalTypes(@NotNull KotlinType a, @NotNull KotlinType b) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        return equalTypes(new ClassicTypeCheckerContext(false, false, false, getKotlinTypeRefiner(), 6, null), a.unwrap(), b.unwrap());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker
    @NotNull
    public KotlinTypeRefiner getKotlinTypeRefiner() {
        return this.kotlinTypeRefiner;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker
    @NotNull
    public OverridingUtil getOverridingUtil() {
        return this.overridingUtil;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker
    public boolean isSubtypeOf(@NotNull KotlinType subtype, @NotNull KotlinType supertype) {
        Intrinsics.checkParameterIsNotNull(subtype, "subtype");
        Intrinsics.checkParameterIsNotNull(supertype, "supertype");
        return isSubtypeOf(new ClassicTypeCheckerContext(true, false, false, getKotlinTypeRefiner(), 6, null), subtype.unwrap(), supertype.unwrap());
    }

    @NotNull
    public final SimpleType transformToNewType(@NotNull SimpleType type) {
        int collectionSizeOrDefault;
        int collectionSizeOrDefault2;
        List emptyList;
        int collectionSizeOrDefault3;
        KotlinType type2;
        Intrinsics.checkParameterIsNotNull(type, "type");
        TypeConstructor mo12131getConstructor = type.mo12131getConstructor();
        boolean z = true;
        IntersectionTypeConstructor intersectionTypeConstructor = null;
        r4 = null;
        UnwrappedType unwrappedType = null;
        boolean z2 = false;
        if (mo12131getConstructor instanceof CapturedTypeConstructorImpl) {
            CapturedTypeConstructorImpl capturedTypeConstructorImpl = (CapturedTypeConstructorImpl) mo12131getConstructor;
            TypeProjection projection = capturedTypeConstructorImpl.getProjection();
            if (projection.getProjectionKind() != Variance.IN_VARIANCE) {
                z = false;
            }
            if (!z) {
                projection = null;
            }
            if (projection != null && (type2 = projection.getType()) != null) {
                unwrappedType = type2.unwrap();
            }
            UnwrappedType unwrappedType2 = unwrappedType;
            if (capturedTypeConstructorImpl.getNewTypeConstructor() == null) {
                TypeProjection projection2 = capturedTypeConstructorImpl.getProjection();
                Collection<KotlinType> mo12135getSupertypes = capturedTypeConstructorImpl.mo12135getSupertypes();
                collectionSizeOrDefault3 = CollectionsKt__IterablesKt.collectionSizeOrDefault(mo12135getSupertypes, 10);
                ArrayList arrayList = new ArrayList(collectionSizeOrDefault3);
                for (KotlinType kotlinType : mo12135getSupertypes) {
                    arrayList.add(kotlinType.unwrap());
                }
                capturedTypeConstructorImpl.setNewTypeConstructor(new NewCapturedTypeConstructor(projection2, arrayList, null, 4, null));
            }
            CaptureStatus captureStatus = CaptureStatus.FOR_SUBTYPING;
            NewCapturedTypeConstructor newTypeConstructor = capturedTypeConstructorImpl.getNewTypeConstructor();
            if (newTypeConstructor == null) {
                Intrinsics.throwNpe();
            }
            return new NewCapturedType(captureStatus, newTypeConstructor, unwrappedType2, type.mo12070getAnnotations(), type.isMarkedNullable());
        } else if (mo12131getConstructor instanceof IntegerValueTypeConstructor) {
            Collection<KotlinType> mo12135getSupertypes2 = ((IntegerValueTypeConstructor) mo12131getConstructor).mo12135getSupertypes();
            collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(mo12135getSupertypes2, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
            for (KotlinType kotlinType2 : mo12135getSupertypes2) {
                arrayList2.add(TypeUtils.makeNullableAsSpecified(kotlinType2, type.isMarkedNullable()));
            }
            IntersectionTypeConstructor intersectionTypeConstructor2 = new IntersectionTypeConstructor(arrayList2);
            Annotations mo12070getAnnotations = type.mo12070getAnnotations();
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(mo12070getAnnotations, intersectionTypeConstructor2, emptyList, false, type.getMemberScope());
        } else if (!(mo12131getConstructor instanceof IntersectionTypeConstructor) || !type.isMarkedNullable()) {
            return type;
        } else {
            IntersectionTypeConstructor intersectionTypeConstructor3 = (IntersectionTypeConstructor) mo12131getConstructor;
            Collection<KotlinType> mo12135getSupertypes3 = intersectionTypeConstructor3.mo12135getSupertypes();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(mo12135getSupertypes3, 10);
            ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault);
            for (KotlinType kotlinType3 : mo12135getSupertypes3) {
                arrayList3.add(TypeUtilsKt.makeNullable(kotlinType3));
                z2 = true;
            }
            if (z2) {
                intersectionTypeConstructor = new IntersectionTypeConstructor(arrayList3);
            }
            if (intersectionTypeConstructor != null) {
                intersectionTypeConstructor3 = intersectionTypeConstructor;
            }
            return intersectionTypeConstructor3.createType();
        }
    }

    public final boolean equalTypes(@NotNull ClassicTypeCheckerContext equalTypes, @NotNull UnwrappedType a, @NotNull UnwrappedType b) {
        Intrinsics.checkParameterIsNotNull(equalTypes, "$this$equalTypes");
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        return AbstractTypeChecker.INSTANCE.equalTypes(equalTypes, a, b);
    }

    public final boolean isSubtypeOf(@NotNull ClassicTypeCheckerContext isSubtypeOf, @NotNull UnwrappedType subType, @NotNull UnwrappedType superType) {
        Intrinsics.checkParameterIsNotNull(isSubtypeOf, "$this$isSubtypeOf");
        Intrinsics.checkParameterIsNotNull(subType, "subType");
        Intrinsics.checkParameterIsNotNull(superType, "superType");
        return AbstractTypeChecker.INSTANCE.isSubtypeOf(isSubtypeOf, subType, superType);
    }

    @NotNull
    public UnwrappedType transformToNewType(@NotNull UnwrappedType type) {
        UnwrappedType flexibleType;
        Intrinsics.checkParameterIsNotNull(type, "type");
        if (type instanceof SimpleType) {
            flexibleType = transformToNewType((SimpleType) type);
        } else if (type instanceof FlexibleType) {
            FlexibleType flexibleType2 = (FlexibleType) type;
            SimpleType transformToNewType = transformToNewType(flexibleType2.getLowerBound());
            SimpleType transformToNewType2 = transformToNewType(flexibleType2.getUpperBound());
            flexibleType = (transformToNewType == flexibleType2.getLowerBound() && transformToNewType2 == flexibleType2.getUpperBound()) ? type : KotlinTypeFactory.flexibleType(transformToNewType, transformToNewType2);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return TypeWithEnhancementKt.inheritEnhancement(flexibleType, type);
    }
}
