package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ModuleDescriptor.kt */
/* loaded from: classes2.dex */
public interface ModuleDescriptor extends DeclarationDescriptor {

    /* compiled from: ModuleDescriptor.kt */
    /* loaded from: classes2.dex */
    public static final class Capability<T> {
        @NotNull
        private final String name;

        public Capability(@NotNull String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.name = name;
        }

        @NotNull
        public String toString() {
            return this.name;
        }
    }

    /* compiled from: ModuleDescriptor.kt */
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static <R, D> R accept(ModuleDescriptor moduleDescriptor, @NotNull DeclarationDescriptorVisitor<R, D> visitor, D d) {
            Intrinsics.checkParameterIsNotNull(visitor, "visitor");
            return visitor.visitModuleDeclaration(moduleDescriptor, d);
        }

        @Nullable
        public static DeclarationDescriptor getContainingDeclaration(ModuleDescriptor moduleDescriptor) {
            return null;
        }
    }

    @NotNull
    KotlinBuiltIns getBuiltIns();

    @Nullable
    <T> T getCapability(@NotNull Capability<T> capability);

    @NotNull
    List<ModuleDescriptor> getExpectedByModules();

    @NotNull
    PackageViewDescriptor getPackage(@NotNull FqName fqName);

    @NotNull
    Collection<FqName> getSubPackagesOf(@NotNull FqName fqName, @NotNull Function1<? super Name, Boolean> function1);

    boolean shouldSeeInternalsOf(@NotNull ModuleDescriptor moduleDescriptor);
}
