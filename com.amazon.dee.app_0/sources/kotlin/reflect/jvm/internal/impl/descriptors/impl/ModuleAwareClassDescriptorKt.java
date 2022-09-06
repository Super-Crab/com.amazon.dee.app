package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
/* compiled from: ModuleAwareClassDescriptor.kt */
/* loaded from: classes2.dex */
public final class ModuleAwareClassDescriptorKt {
    @NotNull
    public static final MemberScope getRefinedMemberScopeIfPossible(@NotNull ClassDescriptor getRefinedMemberScopeIfPossible, @NotNull TypeSubstitution typeSubstitution, @NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(getRefinedMemberScopeIfPossible, "$this$getRefinedMemberScopeIfPossible");
        Intrinsics.checkParameterIsNotNull(typeSubstitution, "typeSubstitution");
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        return ModuleAwareClassDescriptor.Companion.getRefinedMemberScopeIfPossible$descriptors(getRefinedMemberScopeIfPossible, typeSubstitution, kotlinTypeRefiner);
    }

    @NotNull
    public static final MemberScope getRefinedUnsubstitutedMemberScopeIfPossible(@NotNull ClassDescriptor getRefinedUnsubstitutedMemberScopeIfPossible, @NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(getRefinedUnsubstitutedMemberScopeIfPossible, "$this$getRefinedUnsubstitutedMemberScopeIfPossible");
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        return ModuleAwareClassDescriptor.Companion.getRefinedUnsubstitutedMemberScopeIfPossible$descriptors(getRefinedUnsubstitutedMemberScopeIfPossible, kotlinTypeRefiner);
    }
}
