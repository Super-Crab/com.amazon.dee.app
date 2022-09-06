package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
/* compiled from: ModuleAwareClassDescriptor.kt */
/* loaded from: classes2.dex */
public abstract class ModuleAwareClassDescriptor implements ClassDescriptor {
    public static final Companion Companion = new Companion(null);

    /* compiled from: ModuleAwareClassDescriptor.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final MemberScope getRefinedMemberScopeIfPossible$descriptors(@NotNull ClassDescriptor getRefinedMemberScopeIfPossible, @NotNull TypeSubstitution typeSubstitution, @NotNull KotlinTypeRefiner kotlinTypeRefiner) {
            MemberScope memberScope;
            Intrinsics.checkParameterIsNotNull(getRefinedMemberScopeIfPossible, "$this$getRefinedMemberScopeIfPossible");
            Intrinsics.checkParameterIsNotNull(typeSubstitution, "typeSubstitution");
            Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
            ModuleAwareClassDescriptor moduleAwareClassDescriptor = (ModuleAwareClassDescriptor) (!(getRefinedMemberScopeIfPossible instanceof ModuleAwareClassDescriptor) ? null : getRefinedMemberScopeIfPossible);
            if (moduleAwareClassDescriptor == null || (memberScope = moduleAwareClassDescriptor.getMemberScope(typeSubstitution, kotlinTypeRefiner)) == null) {
                MemberScope memberScope2 = getRefinedMemberScopeIfPossible.getMemberScope(typeSubstitution);
                Intrinsics.checkExpressionValueIsNotNull(memberScope2, "this.getMemberScope(\n   …ubstitution\n            )");
                return memberScope2;
            }
            return memberScope;
        }

        @NotNull
        public final MemberScope getRefinedUnsubstitutedMemberScopeIfPossible$descriptors(@NotNull ClassDescriptor getRefinedUnsubstitutedMemberScopeIfPossible, @NotNull KotlinTypeRefiner kotlinTypeRefiner) {
            MemberScope mo11665getUnsubstitutedMemberScope;
            Intrinsics.checkParameterIsNotNull(getRefinedUnsubstitutedMemberScopeIfPossible, "$this$getRefinedUnsubstitutedMemberScopeIfPossible");
            Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
            ModuleAwareClassDescriptor moduleAwareClassDescriptor = (ModuleAwareClassDescriptor) (!(getRefinedUnsubstitutedMemberScopeIfPossible instanceof ModuleAwareClassDescriptor) ? null : getRefinedUnsubstitutedMemberScopeIfPossible);
            if (moduleAwareClassDescriptor == null || (mo11665getUnsubstitutedMemberScope = moduleAwareClassDescriptor.mo11665getUnsubstitutedMemberScope(kotlinTypeRefiner)) == null) {
                MemberScope mo11664getUnsubstitutedMemberScope = getRefinedUnsubstitutedMemberScopeIfPossible.mo11664getUnsubstitutedMemberScope();
                Intrinsics.checkExpressionValueIsNotNull(mo11664getUnsubstitutedMemberScope, "this.unsubstitutedMemberScope");
                return mo11664getUnsubstitutedMemberScope;
            }
            return mo11665getUnsubstitutedMemberScope;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public abstract MemberScope getMemberScope(@NotNull TypeSubstitution typeSubstitution, @NotNull KotlinTypeRefiner kotlinTypeRefiner);

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    /* renamed from: getUnsubstitutedMemberScope */
    public abstract MemberScope mo11665getUnsubstitutedMemberScope(@NotNull KotlinTypeRefiner kotlinTypeRefiner);
}
