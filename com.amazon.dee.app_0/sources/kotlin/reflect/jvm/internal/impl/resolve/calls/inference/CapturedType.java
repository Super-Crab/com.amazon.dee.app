package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SubtypingRepresentatives;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: CapturedTypeConstructor.kt */
/* loaded from: classes4.dex */
public final class CapturedType extends SimpleType implements SubtypingRepresentatives, CapturedTypeMarker {
    @NotNull
    private final Annotations annotations;
    @NotNull
    private final CapturedTypeConstructor constructor;
    private final boolean isMarkedNullable;
    @NotNull
    private final TypeProjection typeProjection;

    public /* synthetic */ CapturedType(TypeProjection typeProjection, CapturedTypeConstructor capturedTypeConstructor, boolean z, Annotations annotations, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeProjection, (i & 2) != 0 ? new CapturedTypeConstructorImpl(typeProjection) : capturedTypeConstructor, (i & 4) != 0 ? false : z, (i & 8) != 0 ? Annotations.Companion.getEMPTY() : annotations);
    }

    private final KotlinType representative(Variance variance, KotlinType kotlinType) {
        if (this.typeProjection.getProjectionKind() == variance) {
            kotlinType = this.typeProjection.getType();
        }
        Intrinsics.checkExpressionValueIsNotNull(kotlinType, "if (typeProjection.proje…jection.type else default");
        return kotlinType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    @NotNull
    /* renamed from: getAnnotations */
    public Annotations mo12070getAnnotations() {
        return this.annotations;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    public List<TypeProjection> getArguments() {
        List<TypeProjection> emptyList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    public MemberScope getMemberScope() {
        MemberScope createErrorScope = ErrorUtils.createErrorScope("No member resolution should be done on captured type, it used only during constraint system resolution", true);
        Intrinsics.checkExpressionValueIsNotNull(createErrorScope, "ErrorUtils.createErrorSc…solution\", true\n        )");
        return createErrorScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.SubtypingRepresentatives
    @NotNull
    public KotlinType getSubTypeRepresentative() {
        Variance variance = Variance.OUT_VARIANCE;
        SimpleType nullableAnyType = TypeUtilsKt.getBuiltIns(this).getNullableAnyType();
        Intrinsics.checkExpressionValueIsNotNull(nullableAnyType, "builtIns.nullableAnyType");
        return representative(variance, nullableAnyType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.SubtypingRepresentatives
    @NotNull
    public KotlinType getSuperTypeRepresentative() {
        Variance variance = Variance.IN_VARIANCE;
        SimpleType nothingType = TypeUtilsKt.getBuiltIns(this).getNothingType();
        Intrinsics.checkExpressionValueIsNotNull(nothingType, "builtIns.nothingType");
        return representative(variance, nothingType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.SubtypingRepresentatives
    public boolean sameTypeConstructor(@NotNull KotlinType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        return mo12131getConstructor() == type.mo12131getConstructor();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.SimpleType
    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Captured(");
        outline107.append(this.typeProjection);
        outline107.append(')');
        outline107.append(isMarkedNullable() ? WebConstants.UriConstants.QUESTIONMARK_KEY : "");
        return outline107.toString();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    /* renamed from: getConstructor */
    public CapturedTypeConstructor mo12131getConstructor() {
        return this.constructor;
    }

    public CapturedType(@NotNull TypeProjection typeProjection, @NotNull CapturedTypeConstructor constructor, boolean z, @NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(typeProjection, "typeProjection");
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        this.typeProjection = typeProjection;
        this.constructor = constructor;
        this.isMarkedNullable = z;
        this.annotations = annotations;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.SimpleType, kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: makeNullableAsSpecified */
    public CapturedType mo12132makeNullableAsSpecified(boolean z) {
        return z == isMarkedNullable() ? this : new CapturedType(this.typeProjection, mo12131getConstructor(), z, mo12070getAnnotations());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    /* renamed from: refine */
    public CapturedType mo12133refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        TypeProjection refine = this.typeProjection.refine(kotlinTypeRefiner);
        Intrinsics.checkExpressionValueIsNotNull(refine, "typeProjection.refine(kotlinTypeRefiner)");
        return new CapturedType(refine, mo12131getConstructor(), isMarkedNullable(), mo12070getAnnotations());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.SimpleType, kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: replaceAnnotations */
    public CapturedType mo12134replaceAnnotations(@NotNull Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        return new CapturedType(this.typeProjection, mo12131getConstructor(), isMarkedNullable(), newAnnotations);
    }
}
