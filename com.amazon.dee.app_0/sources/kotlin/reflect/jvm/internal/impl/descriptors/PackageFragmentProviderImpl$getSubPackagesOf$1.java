package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
/* compiled from: PackageFragmentProviderImpl.kt */
/* loaded from: classes2.dex */
final class PackageFragmentProviderImpl$getSubPackagesOf$1 extends Lambda implements Function1<PackageFragmentDescriptor, FqName> {
    public static final PackageFragmentProviderImpl$getSubPackagesOf$1 INSTANCE = new PackageFragmentProviderImpl$getSubPackagesOf$1();

    PackageFragmentProviderImpl$getSubPackagesOf$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final FqName mo12165invoke(@NotNull PackageFragmentDescriptor it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        return it2.getFqName();
    }
}
