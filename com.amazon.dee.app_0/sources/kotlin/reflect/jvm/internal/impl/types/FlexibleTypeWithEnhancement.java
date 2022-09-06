package kotlin.reflect.jvm.internal.impl.types;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
/* compiled from: TypeWithEnhancement.kt */
/* loaded from: classes4.dex */
public final class FlexibleTypeWithEnhancement extends FlexibleType implements TypeWithEnhancement {
    @NotNull
    private final KotlinType enhancement;
    @NotNull
    private final FlexibleType origin;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlexibleTypeWithEnhancement(@NotNull FlexibleType origin, @NotNull KotlinType enhancement) {
        super(origin.getLowerBound(), origin.getUpperBound());
        Intrinsics.checkParameterIsNotNull(origin, "origin");
        Intrinsics.checkParameterIsNotNull(enhancement, "enhancement");
        this.origin = origin;
        this.enhancement = enhancement;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    @NotNull
    public SimpleType getDelegate() {
        return mo12102getOrigin().getDelegate();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancement
    @NotNull
    public KotlinType getEnhancement() {
        return this.enhancement;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: makeNullableAsSpecified */
    public UnwrappedType mo12132makeNullableAsSpecified(boolean z) {
        return TypeWithEnhancementKt.wrapEnhancement(mo12102getOrigin().mo12132makeNullableAsSpecified(z), getEnhancement().unwrap().mo12132makeNullableAsSpecified(z));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    @NotNull
    public String render(@NotNull DescriptorRenderer renderer, @NotNull DescriptorRendererOptions options) {
        Intrinsics.checkParameterIsNotNull(renderer, "renderer");
        Intrinsics.checkParameterIsNotNull(options, "options");
        if (options.getEnhancedTypes()) {
            return renderer.renderType(getEnhancement());
        }
        return mo12102getOrigin().render(renderer, options);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: replaceAnnotations */
    public UnwrappedType mo12134replaceAnnotations(@NotNull Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        return TypeWithEnhancementKt.wrapEnhancement(mo12102getOrigin().mo12134replaceAnnotations(newAnnotations), getEnhancement());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancement
    @NotNull
    /* renamed from: getOrigin */
    public FlexibleType mo12102getOrigin() {
        return this.origin;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    /* renamed from: refine */
    public FlexibleTypeWithEnhancement mo12133refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        KotlinType refineType = kotlinTypeRefiner.refineType(mo12102getOrigin());
        if (refineType != null) {
            return new FlexibleTypeWithEnhancement((FlexibleType) refineType, kotlinTypeRefiner.refineType(getEnhancement()));
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.FlexibleType");
    }
}
