package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NewCapturedType.kt */
/* loaded from: classes4.dex */
public final class NewCapturedType extends SimpleType implements CapturedTypeMarker {
    @NotNull
    private final Annotations annotations;
    @NotNull
    private final CaptureStatus captureStatus;
    @NotNull
    private final NewCapturedTypeConstructor constructor;
    private final boolean isMarkedNullable;
    @Nullable
    private final UnwrappedType lowerType;

    public /* synthetic */ NewCapturedType(CaptureStatus captureStatus, NewCapturedTypeConstructor newCapturedTypeConstructor, UnwrappedType unwrappedType, Annotations annotations, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(captureStatus, newCapturedTypeConstructor, unwrappedType, (i & 8) != 0 ? Annotations.Companion.getEMPTY() : annotations, (i & 16) != 0 ? false : z);
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

    @Nullable
    public final UnwrappedType getLowerType() {
        return this.lowerType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    public MemberScope getMemberScope() {
        MemberScope createErrorScope = ErrorUtils.createErrorScope("No member resolution should be done on captured type!", true);
        Intrinsics.checkExpressionValueIsNotNull(createErrorScope, "ErrorUtils.createErrorSc…on captured type!\", true)");
        return createErrorScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    /* renamed from: getConstructor  reason: collision with other method in class */
    public NewCapturedTypeConstructor mo12131getConstructor() {
        return this.constructor;
    }

    public NewCapturedType(@NotNull CaptureStatus captureStatus, @NotNull NewCapturedTypeConstructor constructor, @Nullable UnwrappedType unwrappedType, @NotNull Annotations annotations, boolean z) {
        Intrinsics.checkParameterIsNotNull(captureStatus, "captureStatus");
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        this.captureStatus = captureStatus;
        this.constructor = constructor;
        this.lowerType = unwrappedType;
        this.annotations = annotations;
        this.isMarkedNullable = z;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.SimpleType, kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: makeNullableAsSpecified */
    public NewCapturedType mo12132makeNullableAsSpecified(boolean z) {
        return new NewCapturedType(this.captureStatus, mo12131getConstructor(), this.lowerType, mo12070getAnnotations(), z);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    /* renamed from: refine */
    public NewCapturedType mo12133refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        CaptureStatus captureStatus = this.captureStatus;
        NewCapturedTypeConstructor mo12136refine = mo12131getConstructor().mo12136refine(kotlinTypeRefiner);
        UnwrappedType unwrappedType = this.lowerType;
        return new NewCapturedType(captureStatus, mo12136refine, unwrappedType != null ? kotlinTypeRefiner.refineType(unwrappedType).unwrap() : null, mo12070getAnnotations(), isMarkedNullable());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.SimpleType, kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: replaceAnnotations */
    public NewCapturedType mo12134replaceAnnotations(@NotNull Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        return new NewCapturedType(this.captureStatus, mo12131getConstructor(), this.lowerType, newAnnotations, isMarkedNullable());
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NewCapturedType(@NotNull CaptureStatus captureStatus, @Nullable UnwrappedType unwrappedType, @NotNull TypeProjection projection, @NotNull TypeParameterDescriptor typeParameter) {
        this(captureStatus, new NewCapturedTypeConstructor(projection, null, null, typeParameter, 6, null), unwrappedType, null, false, 24, null);
        Intrinsics.checkParameterIsNotNull(captureStatus, "captureStatus");
        Intrinsics.checkParameterIsNotNull(projection, "projection");
        Intrinsics.checkParameterIsNotNull(typeParameter, "typeParameter");
    }
}
