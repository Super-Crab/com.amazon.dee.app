package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
/* compiled from: CompositePackageFragmentProvider.kt */
/* loaded from: classes2.dex */
public final class CompositePackageFragmentProvider implements PackageFragmentProvider {
    private final List<PackageFragmentProvider> providers;

    /* JADX WARN: Multi-variable type inference failed */
    public CompositePackageFragmentProvider(@NotNull List<? extends PackageFragmentProvider> providers) {
        Intrinsics.checkParameterIsNotNull(providers, "providers");
        this.providers = providers;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @NotNull
    public List<PackageFragmentDescriptor> getPackageFragments(@NotNull FqName fqName) {
        List<PackageFragmentDescriptor> list;
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        ArrayList arrayList = new ArrayList();
        for (PackageFragmentProvider packageFragmentProvider : this.providers) {
            arrayList.addAll(packageFragmentProvider.getPackageFragments(fqName));
        }
        list = CollectionsKt___CollectionsKt.toList(arrayList);
        return list;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @NotNull
    /* renamed from: getSubPackagesOf */
    public Collection<FqName> mo11654getSubPackagesOf(@NotNull FqName fqName, @NotNull Function1<? super Name, Boolean> nameFilter) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        HashSet hashSet = new HashSet();
        for (PackageFragmentProvider packageFragmentProvider : this.providers) {
            hashSet.addAll(packageFragmentProvider.mo11654getSubPackagesOf(fqName, nameFilter));
        }
        return hashSet;
    }
}
