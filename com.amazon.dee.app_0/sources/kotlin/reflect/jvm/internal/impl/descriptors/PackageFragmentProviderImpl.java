package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: PackageFragmentProviderImpl.kt */
/* loaded from: classes2.dex */
public final class PackageFragmentProviderImpl implements PackageFragmentProvider {
    private final Collection<PackageFragmentDescriptor> packageFragments;

    /* JADX WARN: Multi-variable type inference failed */
    public PackageFragmentProviderImpl(@NotNull Collection<? extends PackageFragmentDescriptor> packageFragments) {
        Intrinsics.checkParameterIsNotNull(packageFragments, "packageFragments");
        this.packageFragments = packageFragments;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @NotNull
    public List<PackageFragmentDescriptor> getPackageFragments(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Collection<PackageFragmentDescriptor> collection = this.packageFragments;
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (Intrinsics.areEqual(((PackageFragmentDescriptor) obj).getFqName(), fqName)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @NotNull
    /* renamed from: getSubPackagesOf */
    public Collection<FqName> mo11654getSubPackagesOf(@NotNull FqName fqName, @NotNull Function1<? super Name, Boolean> nameFilter) {
        Sequence asSequence;
        Sequence map;
        Sequence filter;
        List list;
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        asSequence = CollectionsKt___CollectionsKt.asSequence(this.packageFragments);
        map = SequencesKt___SequencesKt.map(asSequence, PackageFragmentProviderImpl$getSubPackagesOf$1.INSTANCE);
        filter = SequencesKt___SequencesKt.filter(map, new PackageFragmentProviderImpl$getSubPackagesOf$2(fqName));
        list = SequencesKt___SequencesKt.toList(filter);
        return list;
    }
}
