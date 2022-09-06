package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AbstractTypeConstructor.kt */
/* loaded from: classes4.dex */
public abstract class AbstractTypeConstructor implements TypeConstructor {
    private final NotNullLazyValue<Supertypes> supertypes;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AbstractTypeConstructor.kt */
    /* loaded from: classes4.dex */
    public final class ModuleViewTypeConstructor implements TypeConstructor {
        private final KotlinTypeRefiner kotlinTypeRefiner;
        private final Lazy refinedSupertypes$delegate;
        final /* synthetic */ AbstractTypeConstructor this$0;

        public ModuleViewTypeConstructor(@NotNull AbstractTypeConstructor abstractTypeConstructor, KotlinTypeRefiner kotlinTypeRefiner) {
            Lazy lazy;
            Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
            this.this$0 = abstractTypeConstructor;
            this.kotlinTypeRefiner = kotlinTypeRefiner;
            lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new AbstractTypeConstructor$ModuleViewTypeConstructor$refinedSupertypes$2(this));
            this.refinedSupertypes$delegate = lazy;
        }

        private final List<KotlinType> getRefinedSupertypes() {
            return (List) this.refinedSupertypes$delegate.getValue();
        }

        public boolean equals(@Nullable Object obj) {
            return this.this$0.equals(obj);
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        public KotlinBuiltIns getBuiltIns() {
            KotlinBuiltIns builtIns = this.this$0.getBuiltIns();
            Intrinsics.checkExpressionValueIsNotNull(builtIns, "this@AbstractTypeConstructor.builtIns");
            return builtIns;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        /* renamed from: getDeclarationDescriptor */
        public ClassifierDescriptor mo12085getDeclarationDescriptor() {
            return this.this$0.mo12085getDeclarationDescriptor();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        public List<TypeParameterDescriptor> getParameters() {
            List<TypeParameterDescriptor> parameters = this.this$0.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "this@AbstractTypeConstructor.parameters");
            return parameters;
        }

        public int hashCode() {
            return this.this$0.hashCode();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return this.this$0.isDenotable();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        /* renamed from: refine */
        public TypeConstructor mo12136refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
            Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
            return this.this$0.mo12136refine(kotlinTypeRefiner);
        }

        @NotNull
        public String toString() {
            return this.this$0.toString();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        /* renamed from: getSupertypes  reason: collision with other method in class */
        public List<KotlinType> mo12135getSupertypes() {
            return getRefinedSupertypes();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AbstractTypeConstructor.kt */
    /* loaded from: classes4.dex */
    public static final class Supertypes {
        @NotNull
        private final Collection<KotlinType> allSupertypes;
        @NotNull
        private List<? extends KotlinType> supertypesWithoutCycles;

        /* JADX WARN: Multi-variable type inference failed */
        public Supertypes(@NotNull Collection<? extends KotlinType> allSupertypes) {
            List<? extends KotlinType> listOf;
            Intrinsics.checkParameterIsNotNull(allSupertypes, "allSupertypes");
            this.allSupertypes = allSupertypes;
            listOf = CollectionsKt__CollectionsJVMKt.listOf(ErrorUtils.ERROR_TYPE_FOR_LOOP_IN_SUPERTYPES);
            this.supertypesWithoutCycles = listOf;
        }

        @NotNull
        public final Collection<KotlinType> getAllSupertypes() {
            return this.allSupertypes;
        }

        @NotNull
        public final List<KotlinType> getSupertypesWithoutCycles() {
            return this.supertypesWithoutCycles;
        }

        public final void setSupertypesWithoutCycles(@NotNull List<? extends KotlinType> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.supertypesWithoutCycles = list;
        }
    }

    public AbstractTypeConstructor(@NotNull StorageManager storageManager) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        this.supertypes = storageManager.createLazyValueWithPostCompute(new AbstractTypeConstructor$supertypes$1(this), AbstractTypeConstructor$supertypes$2.INSTANCE, new AbstractTypeConstructor$supertypes$3(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x000b, code lost:
        r4 = kotlin.collections.CollectionsKt___CollectionsKt.plus((java.util.Collection) r0.supertypes.mo12560invoke().getAllSupertypes(), (java.lang.Iterable) r0.getAdditionalNeighboursInSupertypeGraph(r4));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.Collection<kotlin.reflect.jvm.internal.impl.types.KotlinType> computeNeighbours(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.types.TypeConstructor r3, boolean r4) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
            if (r0 != 0) goto L6
            r0 = 0
            goto L7
        L6:
            r0 = r3
        L7:
            kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor r0 = (kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor) r0
            if (r0 == 0) goto L22
            kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue<kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$Supertypes> r1 = r0.supertypes
            java.lang.Object r1 = r1.mo12560invoke()
            kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$Supertypes r1 = (kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor.Supertypes) r1
            java.util.Collection r1 = r1.getAllSupertypes()
            java.util.Collection r4 = r0.getAdditionalNeighboursInSupertypeGraph(r4)
            java.util.List r4 = kotlin.collections.CollectionsKt.plus(r1, r4)
            if (r4 == 0) goto L22
            goto L2b
        L22:
            java.util.Collection r4 = r3.mo12135getSupertypes()
            java.lang.String r3 = "supertypes"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r3)
        L2b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor.computeNeighbours(kotlin.reflect.jvm.internal.impl.types.TypeConstructor, boolean):java.util.Collection");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public abstract Collection<KotlinType> computeSupertypes();

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public KotlinType defaultSupertypeIfEmpty() {
        return null;
    }

    @NotNull
    protected Collection<KotlinType> getAdditionalNeighboursInSupertypeGraph(boolean z) {
        List emptyList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: getDeclarationDescriptor */
    public abstract ClassifierDescriptor mo12085getDeclarationDescriptor();

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public abstract SupertypeLoopChecker getSupertypeLoopChecker();

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: refine */
    public TypeConstructor mo12136refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        return new ModuleViewTypeConstructor(this, kotlinTypeRefiner);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportScopesLoopError(@NotNull KotlinType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportSupertypeLoopError(@NotNull KotlinType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: getSupertypes  reason: collision with other method in class */
    public List<KotlinType> mo12135getSupertypes() {
        return this.supertypes.mo12560invoke().getSupertypesWithoutCycles();
    }
}
