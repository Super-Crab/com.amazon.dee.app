package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedTypeConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CapturedTypeConstructor.kt */
/* loaded from: classes4.dex */
public final class CapturedTypeConstructorImpl implements CapturedTypeConstructor {
    @Nullable
    private NewCapturedTypeConstructor newTypeConstructor;
    @NotNull
    private final TypeProjection projection;

    public CapturedTypeConstructorImpl(@NotNull TypeProjection projection) {
        Intrinsics.checkParameterIsNotNull(projection, "projection");
        this.projection = projection;
        boolean z = getProjection().getProjectionKind() != Variance.INVARIANT;
        if (!_Assertions.ENABLED || z) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Only nontrivial projections can be captured, not: ");
        outline107.append(getProjection());
        throw new AssertionError(outline107.toString());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public KotlinBuiltIns getBuiltIns() {
        KotlinBuiltIns builtIns = getProjection().getType().mo12131getConstructor().getBuiltIns();
        Intrinsics.checkExpressionValueIsNotNull(builtIns, "projection.type.constructor.builtIns");
        return builtIns;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @Nullable
    /* renamed from: getDeclarationDescriptor */
    public Void mo12085getDeclarationDescriptor() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    /* renamed from: getDeclarationDescriptor  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ ClassifierDescriptor mo12085getDeclarationDescriptor() {
        return (ClassifierDescriptor) mo12085getDeclarationDescriptor();
    }

    @Nullable
    public final NewCapturedTypeConstructor getNewTypeConstructor() {
        return this.newTypeConstructor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public List<TypeParameterDescriptor> getParameters() {
        List<TypeParameterDescriptor> emptyList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor
    @NotNull
    public TypeProjection getProjection() {
        return this.projection;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: getSupertypes */
    public Collection<KotlinType> mo12135getSupertypes() {
        KotlinType nullableAnyType;
        List listOf;
        if (getProjection().getProjectionKind() == Variance.OUT_VARIANCE) {
            nullableAnyType = getProjection().getType();
        } else {
            nullableAnyType = getBuiltIns().getNullableAnyType();
        }
        Intrinsics.checkExpressionValueIsNotNull(nullableAnyType, "if (projection.projectio… builtIns.nullableAnyType");
        listOf = CollectionsKt__CollectionsJVMKt.listOf(nullableAnyType);
        return listOf;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public boolean isDenotable() {
        return false;
    }

    public final void setNewTypeConstructor(@Nullable NewCapturedTypeConstructor newCapturedTypeConstructor) {
        this.newTypeConstructor = newCapturedTypeConstructor;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CapturedTypeConstructor(");
        outline107.append(getProjection());
        outline107.append(')');
        return outline107.toString();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: refine */
    public CapturedTypeConstructorImpl mo12136refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        TypeProjection refine = getProjection().refine(kotlinTypeRefiner);
        Intrinsics.checkExpressionValueIsNotNull(refine, "projection.refine(kotlinTypeRefiner)");
        return new CapturedTypeConstructorImpl(refine);
    }
}
