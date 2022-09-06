package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
/* compiled from: ErrorType.kt */
/* loaded from: classes4.dex */
public final class UnresolvedType extends ErrorType {
    @NotNull
    private final String presentableName;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnresolvedType(@NotNull String presentableName, @NotNull TypeConstructor constructor, @NotNull MemberScope memberScope, @NotNull List<? extends TypeProjection> arguments, boolean z) {
        super(constructor, memberScope, arguments, z, null, 16, null);
        Intrinsics.checkParameterIsNotNull(presentableName, "presentableName");
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        Intrinsics.checkParameterIsNotNull(memberScope, "memberScope");
        Intrinsics.checkParameterIsNotNull(arguments, "arguments");
        this.presentableName = presentableName;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.ErrorType
    @NotNull
    public String getPresentableName() {
        return this.presentableName;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.ErrorType, kotlin.reflect.jvm.internal.impl.types.UnwrappedType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    /* renamed from: refine */
    public UnresolvedType mo12133refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        return this;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.ErrorType, kotlin.reflect.jvm.internal.impl.types.SimpleType, kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: makeNullableAsSpecified */
    public SimpleType mo12132makeNullableAsSpecified(boolean z) {
        return new UnresolvedType(getPresentableName(), mo12131getConstructor(), getMemberScope(), getArguments(), z);
    }
}
