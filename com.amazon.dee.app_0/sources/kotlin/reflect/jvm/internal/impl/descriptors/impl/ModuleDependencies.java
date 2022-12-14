package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
/* compiled from: ModuleDescriptorImpl.kt */
/* loaded from: classes2.dex */
public interface ModuleDependencies {
    @NotNull
    List<ModuleDescriptorImpl> getAllDependencies();

    @NotNull
    List<ModuleDescriptorImpl> getExpectedByDependencies();

    @NotNull
    Set<ModuleDescriptorImpl> getModulesWhoseInternalsAreVisible();
}
