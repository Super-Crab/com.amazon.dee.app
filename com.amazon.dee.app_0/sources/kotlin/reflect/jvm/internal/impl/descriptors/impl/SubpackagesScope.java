package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindExclude;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SubpackagesScope.kt */
/* loaded from: classes2.dex */
public class SubpackagesScope extends MemberScopeImpl {
    private final FqName fqName;
    private final ModuleDescriptor moduleDescriptor;

    public SubpackagesScope(@NotNull ModuleDescriptor moduleDescriptor, @NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "moduleDescriptor");
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        this.moduleDescriptor = moduleDescriptor;
        this.fqName = fqName;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    /* renamed from: getContributedDescriptors */
    public Collection<DeclarationDescriptor> mo12065getContributedDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter) {
        List emptyList;
        List emptyList2;
        Intrinsics.checkParameterIsNotNull(kindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        if (!kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getPACKAGES_MASK())) {
            emptyList2 = CollectionsKt__CollectionsKt.emptyList();
            return emptyList2;
        } else if (this.fqName.isRoot() && kindFilter.getExcludes().contains(DescriptorKindExclude.TopLevelPackages.INSTANCE)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else {
            Collection<FqName> subPackagesOf = this.moduleDescriptor.getSubPackagesOf(this.fqName, nameFilter);
            ArrayList arrayList = new ArrayList(subPackagesOf.size());
            for (FqName fqName : subPackagesOf) {
                Name shortName = fqName.shortName();
                Intrinsics.checkExpressionValueIsNotNull(shortName, "subFqName.shortName()");
                if (nameFilter.mo12165invoke(shortName).booleanValue()) {
                    CollectionsKt.addIfNotNull(arrayList, getPackage(shortName));
                }
            }
            return arrayList;
        }
    }

    @Nullable
    protected final PackageViewDescriptor getPackage(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (name.isSpecial()) {
            return null;
        }
        ModuleDescriptor moduleDescriptor = this.moduleDescriptor;
        FqName child = this.fqName.child(name);
        Intrinsics.checkExpressionValueIsNotNull(child, "fqName.child(name)");
        PackageViewDescriptor packageViewDescriptor = moduleDescriptor.getPackage(child);
        if (!packageViewDescriptor.isEmpty()) {
            return packageViewDescriptor;
        }
        return null;
    }
}
