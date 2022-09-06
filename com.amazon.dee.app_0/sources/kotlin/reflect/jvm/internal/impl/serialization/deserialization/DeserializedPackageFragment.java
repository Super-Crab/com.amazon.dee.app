package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
/* compiled from: DeserializedPackageFragment.kt */
/* loaded from: classes4.dex */
public abstract class DeserializedPackageFragment extends PackageFragmentDescriptorImpl {
    @NotNull
    private final StorageManager storageManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeserializedPackageFragment(@NotNull FqName fqName, @NotNull StorageManager storageManager, @NotNull ModuleDescriptor module) {
        super(module, fqName);
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(module, "module");
        this.storageManager = storageManager;
    }

    @NotNull
    /* renamed from: getClassDataFinder */
    public abstract ClassDataFinder mo12037getClassDataFinder();

    public boolean hasTopLevelClass(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        MemberScope mo11676getMemberScope = mo11676getMemberScope();
        return (mo11676getMemberScope instanceof DeserializedMemberScope) && ((DeserializedMemberScope) mo11676getMemberScope).getClassNames$deserialization().contains(name);
    }

    public abstract void initialize(@NotNull DeserializationComponents deserializationComponents);
}
