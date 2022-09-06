package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.util.collectionUtils.ScopeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ChainedMemberScope.kt */
/* loaded from: classes4.dex */
public final class ChainedMemberScope implements MemberScope {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String debugName;
    private final List<MemberScope> scopes;

    /* compiled from: ChainedMemberScope.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final MemberScope create(@NotNull String debugName, @NotNull List<? extends MemberScope> scopes) {
            Intrinsics.checkParameterIsNotNull(debugName, "debugName");
            Intrinsics.checkParameterIsNotNull(scopes, "scopes");
            int size = scopes.size();
            if (size != 0) {
                if (size != 1) {
                    return new ChainedMemberScope(debugName, scopes);
                }
                return (MemberScope) CollectionsKt.single((List<? extends Object>) scopes);
            }
            return MemberScope.Empty.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ChainedMemberScope(@NotNull String debugName, @NotNull List<? extends MemberScope> scopes) {
        Intrinsics.checkParameterIsNotNull(debugName, "debugName");
        Intrinsics.checkParameterIsNotNull(scopes, "scopes");
        this.debugName = debugName;
        this.scopes = scopes;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @Nullable
    /* renamed from: getContributedClassifier */
    public ClassifierDescriptor mo12030getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        ClassifierDescriptor classifierDescriptor = null;
        for (MemberScope memberScope : this.scopes) {
            ClassifierDescriptor mo12030getContributedClassifier = memberScope.mo12030getContributedClassifier(name, location);
            if (mo12030getContributedClassifier != null) {
                if (!(mo12030getContributedClassifier instanceof ClassifierDescriptorWithTypeParameters) || !((ClassifierDescriptorWithTypeParameters) mo12030getContributedClassifier).isExpect()) {
                    return mo12030getContributedClassifier;
                }
                if (classifierDescriptor == null) {
                    classifierDescriptor = mo12030getContributedClassifier;
                }
            }
        }
        return classifierDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    /* renamed from: getContributedDescriptors */
    public Collection<DeclarationDescriptor> mo12065getContributedDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter) {
        Set emptySet;
        Set emptySet2;
        Intrinsics.checkParameterIsNotNull(kindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        List<MemberScope> list = this.scopes;
        if (list.isEmpty()) {
            emptySet2 = SetsKt__SetsKt.emptySet();
            return emptySet2;
        }
        Collection<DeclarationDescriptor> collection = null;
        for (MemberScope memberScope : list) {
            collection = ScopeUtilsKt.concat(collection, memberScope.mo12065getContributedDescriptors(kindFilter, nameFilter));
        }
        if (collection != null) {
            return collection;
        }
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    /* renamed from: getContributedFunctions */
    public Collection<SimpleFunctionDescriptor> mo12099getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
        Set emptySet;
        Set emptySet2;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        List<MemberScope> list = this.scopes;
        if (list.isEmpty()) {
            emptySet2 = SetsKt__SetsKt.emptySet();
            return emptySet2;
        }
        Collection<SimpleFunctionDescriptor> collection = null;
        for (MemberScope memberScope : list) {
            collection = ScopeUtilsKt.concat(collection, memberScope.mo12099getContributedFunctions(name, location));
        }
        if (collection != null) {
            return collection;
        }
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    /* renamed from: getContributedVariables */
    public Collection<PropertyDescriptor> mo12100getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
        Set emptySet;
        Set emptySet2;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        List<MemberScope> list = this.scopes;
        if (list.isEmpty()) {
            emptySet2 = SetsKt__SetsKt.emptySet();
            return emptySet2;
        }
        Collection<PropertyDescriptor> collection = null;
        for (MemberScope memberScope : list) {
            collection = ScopeUtilsKt.concat(collection, memberScope.mo12100getContributedVariables(name, location));
        }
        if (collection != null) {
            return collection;
        }
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getFunctionNames() {
        List<MemberScope> list = this.scopes;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (MemberScope memberScope : list) {
            CollectionsKt__MutableCollectionsKt.addAll(linkedHashSet, memberScope.getFunctionNames());
        }
        return linkedHashSet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getVariableNames() {
        List<MemberScope> list = this.scopes;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (MemberScope memberScope : list) {
            CollectionsKt__MutableCollectionsKt.addAll(linkedHashSet, memberScope.getVariableNames());
        }
        return linkedHashSet;
    }

    @NotNull
    public String toString() {
        return this.debugName;
    }
}
