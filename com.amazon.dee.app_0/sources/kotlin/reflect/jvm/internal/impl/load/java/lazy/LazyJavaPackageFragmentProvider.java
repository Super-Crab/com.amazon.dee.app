package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.TypeParameterResolver;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageFragment;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.CacheWithNotNullValues;
import org.jetbrains.annotations.NotNull;
/* compiled from: LazyJavaPackageFragmentProvider.kt */
/* loaded from: classes3.dex */
public final class LazyJavaPackageFragmentProvider implements PackageFragmentProvider {
    private final LazyJavaResolverContext c;
    private final CacheWithNotNullValues<FqName, LazyJavaPackageFragment> packageFragments;

    public LazyJavaPackageFragmentProvider(@NotNull JavaResolverComponents components) {
        Lazy lazyOf;
        Intrinsics.checkParameterIsNotNull(components, "components");
        TypeParameterResolver.EMPTY empty = TypeParameterResolver.EMPTY.INSTANCE;
        lazyOf = LazyKt__LazyKt.lazyOf(null);
        this.c = new LazyJavaResolverContext(components, empty, lazyOf);
        this.packageFragments = this.c.getStorageManager().createCacheWithNotNullValues();
    }

    private final LazyJavaPackageFragment getPackageFragment(FqName fqName) {
        JavaPackage findPackage = this.c.getComponents().getFinder().findPackage(fqName);
        if (findPackage != null) {
            return this.packageFragments.computeIfAbsent(fqName, new LazyJavaPackageFragmentProvider$getPackageFragment$1(this, findPackage));
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @NotNull
    public List<LazyJavaPackageFragment> getPackageFragments(@NotNull FqName fqName) {
        List<LazyJavaPackageFragment> listOfNotNull;
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        listOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull(getPackageFragment(fqName));
        return listOfNotNull;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    /* renamed from: getSubPackagesOf */
    public /* bridge */ /* synthetic */ Collection mo11654getSubPackagesOf(FqName fqName, Function1 function1) {
        return mo11654getSubPackagesOf(fqName, (Function1<? super Name, Boolean>) function1);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @NotNull
    /* renamed from: getSubPackagesOf  reason: collision with other method in class */
    public List<FqName> mo11654getSubPackagesOf(@NotNull FqName fqName, @NotNull Function1<? super Name, Boolean> nameFilter) {
        List<FqName> emptyList;
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        LazyJavaPackageFragment packageFragment = getPackageFragment(fqName);
        List<FqName> subPackageFqNames$descriptors_jvm = packageFragment != null ? packageFragment.getSubPackageFqNames$descriptors_jvm() : null;
        if (subPackageFqNames$descriptors_jvm != null) {
            return subPackageFqNames$descriptors_jvm;
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }
}
