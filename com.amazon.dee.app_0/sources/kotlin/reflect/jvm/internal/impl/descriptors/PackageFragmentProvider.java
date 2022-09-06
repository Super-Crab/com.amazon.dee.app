package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
/* compiled from: PackageFragmentProvider.kt */
/* loaded from: classes2.dex */
public interface PackageFragmentProvider {
    @NotNull
    List<PackageFragmentDescriptor> getPackageFragments(@NotNull FqName fqName);

    @NotNull
    /* renamed from: getSubPackagesOf */
    Collection<FqName> mo11654getSubPackagesOf(@NotNull FqName fqName, @NotNull Function1<? super Name, Boolean> function1);
}
