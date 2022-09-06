package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: KotlinTypeRefiner.kt */
/* loaded from: classes4.dex */
public abstract class KotlinTypeRefiner {

    /* compiled from: KotlinTypeRefiner.kt */
    /* loaded from: classes4.dex */
    public static final class Default extends KotlinTypeRefiner {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner
        @Nullable
        public ClassDescriptor findClassAcrossModuleDependencies(@NotNull ClassId classId) {
            Intrinsics.checkParameterIsNotNull(classId, "classId");
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner
        @NotNull
        public <S extends MemberScope> S getOrPutScopeForClass(@NotNull ClassDescriptor classDescriptor, @NotNull Function0<? extends S> compute) {
            Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
            Intrinsics.checkParameterIsNotNull(compute, "compute");
            return compute.mo12560invoke();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner
        public boolean isRefinementNeededForModule(@NotNull ModuleDescriptor moduleDescriptor) {
            Intrinsics.checkParameterIsNotNull(moduleDescriptor, "moduleDescriptor");
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner
        public boolean isRefinementNeededForTypeConstructor(@NotNull TypeConstructor typeConstructor) {
            Intrinsics.checkParameterIsNotNull(typeConstructor, "typeConstructor");
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner
        @Nullable
        /* renamed from: refineDescriptor */
        public ClassDescriptor mo12130refineDescriptor(@NotNull DeclarationDescriptor descriptor) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner
        @NotNull
        public Collection<KotlinType> refineSupertypes(@NotNull ClassDescriptor classDescriptor) {
            Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
            TypeConstructor mo11528getTypeConstructor = classDescriptor.mo11528getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "classDescriptor.typeConstructor");
            Collection<KotlinType> mo12135getSupertypes = mo11528getTypeConstructor.mo12135getSupertypes();
            Intrinsics.checkExpressionValueIsNotNull(mo12135getSupertypes, "classDescriptor.typeConstructor.supertypes");
            return mo12135getSupertypes;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner
        @NotNull
        public KotlinType refineType(@NotNull KotlinType type) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            return type;
        }
    }

    @Nullable
    public abstract ClassDescriptor findClassAcrossModuleDependencies(@NotNull ClassId classId);

    @NotNull
    public abstract <S extends MemberScope> S getOrPutScopeForClass(@NotNull ClassDescriptor classDescriptor, @NotNull Function0<? extends S> function0);

    public abstract boolean isRefinementNeededForModule(@NotNull ModuleDescriptor moduleDescriptor);

    public abstract boolean isRefinementNeededForTypeConstructor(@NotNull TypeConstructor typeConstructor);

    @Nullable
    /* renamed from: refineDescriptor */
    public abstract ClassifierDescriptor mo12130refineDescriptor(@NotNull DeclarationDescriptor declarationDescriptor);

    @NotNull
    public abstract Collection<KotlinType> refineSupertypes(@NotNull ClassDescriptor classDescriptor);

    @NotNull
    public abstract KotlinType refineType(@NotNull KotlinType kotlinType);
}
