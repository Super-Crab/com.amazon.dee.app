package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.UtilsKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.util.collectionUtils.ScopeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JvmPackageScope.kt */
/* loaded from: classes3.dex */
public final class JvmPackageScope implements MemberScope {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmPackageScope.class), "kotlinScopes", "getKotlinScopes()Ljava/util/List;"))};
    private final LazyJavaResolverContext c;
    @NotNull
    private final LazyJavaPackageScope javaScope;
    private final NotNullLazyValue kotlinScopes$delegate;
    private final LazyJavaPackageFragment packageFragment;

    public JvmPackageScope(@NotNull LazyJavaResolverContext c, @NotNull JavaPackage jPackage, @NotNull LazyJavaPackageFragment packageFragment) {
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(jPackage, "jPackage");
        Intrinsics.checkParameterIsNotNull(packageFragment, "packageFragment");
        this.c = c;
        this.packageFragment = packageFragment;
        this.javaScope = new LazyJavaPackageScope(this.c, jPackage, this.packageFragment);
        this.kotlinScopes$delegate = this.c.getStorageManager().createLazyValue(new JvmPackageScope$kotlinScopes$2(this));
    }

    private final List<MemberScope> getKotlinScopes() {
        return (List) StorageKt.getValue(this.kotlinScopes$delegate, this, $$delegatedProperties[0]);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @Nullable
    /* renamed from: getContributedClassifier */
    public ClassifierDescriptor mo12030getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        recordLookup(name, location);
        ClassDescriptor mo12030getContributedClassifier = this.javaScope.mo12030getContributedClassifier(name, location);
        if (mo12030getContributedClassifier != null) {
            return mo12030getContributedClassifier;
        }
        ClassifierDescriptor classifierDescriptor = null;
        for (MemberScope memberScope : getKotlinScopes()) {
            ClassifierDescriptor mo12030getContributedClassifier2 = memberScope.mo12030getContributedClassifier(name, location);
            if (mo12030getContributedClassifier2 != null) {
                if (!(mo12030getContributedClassifier2 instanceof ClassifierDescriptorWithTypeParameters) || !((ClassifierDescriptorWithTypeParameters) mo12030getContributedClassifier2).isExpect()) {
                    return mo12030getContributedClassifier2;
                }
                if (classifierDescriptor == null) {
                    classifierDescriptor = mo12030getContributedClassifier2;
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
        Intrinsics.checkParameterIsNotNull(kindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        LazyJavaPackageScope lazyJavaPackageScope = this.javaScope;
        List<MemberScope> kotlinScopes = getKotlinScopes();
        Collection<DeclarationDescriptor> mo12065getContributedDescriptors = lazyJavaPackageScope.mo12065getContributedDescriptors(kindFilter, nameFilter);
        for (MemberScope memberScope : kotlinScopes) {
            mo12065getContributedDescriptors = ScopeUtilsKt.concat(mo12065getContributedDescriptors, memberScope.mo12065getContributedDescriptors(kindFilter, nameFilter));
        }
        if (mo12065getContributedDescriptors != null) {
            return mo12065getContributedDescriptors;
        }
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    /* renamed from: getContributedFunctions */
    public Collection<SimpleFunctionDescriptor> mo12099getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
        Set emptySet;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        recordLookup(name, location);
        LazyJavaPackageScope lazyJavaPackageScope = this.javaScope;
        List<MemberScope> kotlinScopes = getKotlinScopes();
        Collection<? extends SimpleFunctionDescriptor> mo12099getContributedFunctions = lazyJavaPackageScope.mo12099getContributedFunctions(name, location);
        Collection collection = mo12099getContributedFunctions;
        for (MemberScope memberScope : kotlinScopes) {
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
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        recordLookup(name, location);
        LazyJavaPackageScope lazyJavaPackageScope = this.javaScope;
        List<MemberScope> kotlinScopes = getKotlinScopes();
        Collection<? extends PropertyDescriptor> mo12100getContributedVariables = lazyJavaPackageScope.mo12100getContributedVariables(name, location);
        Collection collection = mo12100getContributedVariables;
        for (MemberScope memberScope : kotlinScopes) {
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
        List<MemberScope> kotlinScopes = getKotlinScopes();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (MemberScope memberScope : kotlinScopes) {
            CollectionsKt__MutableCollectionsKt.addAll(linkedHashSet, memberScope.getFunctionNames());
        }
        linkedHashSet.addAll(this.javaScope.getFunctionNames());
        return linkedHashSet;
    }

    @NotNull
    public final LazyJavaPackageScope getJavaScope$descriptors_jvm() {
        return this.javaScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getVariableNames() {
        List<MemberScope> kotlinScopes = getKotlinScopes();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (MemberScope memberScope : kotlinScopes) {
            CollectionsKt__MutableCollectionsKt.addAll(linkedHashSet, memberScope.getVariableNames());
        }
        linkedHashSet.addAll(this.javaScope.getVariableNames());
        return linkedHashSet;
    }

    public void recordLookup(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        UtilsKt.record(this.c.getComponents().getLookupTracker(), location, this.packageFragment, name);
    }
}
