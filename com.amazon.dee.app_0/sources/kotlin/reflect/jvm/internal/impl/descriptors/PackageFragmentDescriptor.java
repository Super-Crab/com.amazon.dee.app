package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;
/* compiled from: PackageFragmentDescriptor.kt */
/* loaded from: classes2.dex */
public interface PackageFragmentDescriptor extends ClassOrPackageFragmentDescriptor {
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    /* renamed from: getContainingDeclaration */
    ModuleDescriptor mo11607getContainingDeclaration();

    @NotNull
    FqName getFqName();

    @NotNull
    /* renamed from: getMemberScope */
    MemberScope mo11676getMemberScope();
}
