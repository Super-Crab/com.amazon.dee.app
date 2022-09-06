package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
/* compiled from: TypeIntersectionScope.kt */
/* loaded from: classes4.dex */
public final class TypeIntersectionScope extends AbstractScopeAdapter {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ChainedMemberScope workerScope;

    /* compiled from: TypeIntersectionScope.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final MemberScope create(@NotNull String message, @NotNull Collection<? extends KotlinType> types) {
            int collectionSizeOrDefault;
            Intrinsics.checkParameterIsNotNull(message, "message");
            Intrinsics.checkParameterIsNotNull(types, "types");
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(types, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (KotlinType kotlinType : types) {
                arrayList.add(kotlinType.getMemberScope());
            }
            ChainedMemberScope chainedMemberScope = new ChainedMemberScope(message, arrayList);
            return types.size() <= 1 ? chainedMemberScope : new TypeIntersectionScope(chainedMemberScope, null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private TypeIntersectionScope(ChainedMemberScope chainedMemberScope) {
        this.workerScope = chainedMemberScope;
    }

    @JvmStatic
    @NotNull
    public static final MemberScope create(@NotNull String str, @NotNull Collection<? extends KotlinType> collection) {
        return Companion.create(str, collection);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.AbstractScopeAdapter, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    /* renamed from: getContributedDescriptors */
    public Collection<DeclarationDescriptor> mo12065getContributedDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter) {
        List plus;
        Intrinsics.checkParameterIsNotNull(kindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        Collection<DeclarationDescriptor> mo12065getContributedDescriptors = super.mo12065getContributedDescriptors(kindFilter, nameFilter);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : mo12065getContributedDescriptors) {
            if (((DeclarationDescriptor) obj) instanceof CallableDescriptor) {
                arrayList.add(obj);
            } else {
                arrayList2.add(obj);
            }
        }
        Pair pair = new Pair(arrayList, arrayList2);
        List list = (List) pair.component1();
        List list2 = (List) pair.component2();
        if (list != null) {
            plus = CollectionsKt___CollectionsKt.plus((Collection) OverridingUtilsKt.selectMostSpecificInEachOverridableGroup(list, TypeIntersectionScope$getContributedDescriptors$2.INSTANCE), (Iterable) list2);
            return plus;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Collection<org.jetbrains.kotlin.descriptors.CallableDescriptor>");
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.AbstractScopeAdapter, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    /* renamed from: getContributedFunctions */
    public Collection<SimpleFunctionDescriptor> mo12099getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        return OverridingUtilsKt.selectMostSpecificInEachOverridableGroup(super.mo12099getContributedFunctions(name, location), TypeIntersectionScope$getContributedFunctions$1.INSTANCE);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.AbstractScopeAdapter, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    /* renamed from: getContributedVariables */
    public Collection<PropertyDescriptor> mo12100getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        return OverridingUtilsKt.selectMostSpecificInEachOverridableGroup(super.mo12100getContributedVariables(name, location), TypeIntersectionScope$getContributedVariables$1.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.AbstractScopeAdapter
    @NotNull
    /* renamed from: getWorkerScope */
    public ChainedMemberScope mo12035getWorkerScope() {
        return this.workerScope;
    }

    public /* synthetic */ TypeIntersectionScope(ChainedMemberScope chainedMemberScope, DefaultConstructorMarker defaultConstructorMarker) {
        this(chainedMemberScope);
    }
}
