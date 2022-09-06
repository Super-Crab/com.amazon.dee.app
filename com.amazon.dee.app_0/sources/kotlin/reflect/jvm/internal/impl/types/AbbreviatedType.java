package kotlin.reflect.jvm.internal.impl.types;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
/* compiled from: SpecialTypes.kt */
/* loaded from: classes4.dex */
public final class AbbreviatedType extends DelegatingSimpleType {
    @NotNull
    private final SimpleType abbreviation;
    @NotNull
    private final SimpleType delegate;

    public AbbreviatedType(@NotNull SimpleType delegate, @NotNull SimpleType abbreviation) {
        Intrinsics.checkParameterIsNotNull(delegate, "delegate");
        Intrinsics.checkParameterIsNotNull(abbreviation, "abbreviation");
        this.delegate = delegate;
        this.abbreviation = abbreviation;
    }

    @NotNull
    public final SimpleType getAbbreviation() {
        return this.abbreviation;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    @NotNull
    protected SimpleType getDelegate() {
        return this.delegate;
    }

    @NotNull
    public final SimpleType getExpandedType() {
        return getDelegate();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    @NotNull
    /* renamed from: replaceDelegate */
    public AbbreviatedType mo12116replaceDelegate(@NotNull SimpleType delegate) {
        Intrinsics.checkParameterIsNotNull(delegate, "delegate");
        return new AbbreviatedType(delegate, this.abbreviation);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.SimpleType, kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: makeNullableAsSpecified */
    public AbbreviatedType mo12132makeNullableAsSpecified(boolean z) {
        return new AbbreviatedType(getDelegate().mo12132makeNullableAsSpecified(z), this.abbreviation.mo12132makeNullableAsSpecified(z));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.SimpleType, kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: replaceAnnotations */
    public AbbreviatedType mo12134replaceAnnotations(@NotNull Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        return new AbbreviatedType(getDelegate().mo12134replaceAnnotations(newAnnotations), this.abbreviation);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType, kotlin.reflect.jvm.internal.impl.types.UnwrappedType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    /* renamed from: refine */
    public AbbreviatedType mo12133refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        KotlinType refineType = kotlinTypeRefiner.refineType(getDelegate());
        if (refineType != null) {
            SimpleType simpleType = (SimpleType) refineType;
            KotlinType refineType2 = kotlinTypeRefiner.refineType(this.abbreviation);
            if (refineType2 != null) {
                return new AbbreviatedType(simpleType, (SimpleType) refineType2);
            }
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
    }
}
