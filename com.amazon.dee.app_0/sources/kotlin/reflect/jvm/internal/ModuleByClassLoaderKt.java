package kotlin.reflect.jvm.internal;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: moduleByClassLoader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\u0010\u0010\u0007\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\bH\u0000\" \u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"moduleByClassLoader", "Ljava/util/concurrent/ConcurrentMap;", "Lkotlin/reflect/jvm/internal/WeakClassLoaderBox;", "Ljava/lang/ref/WeakReference;", "Lkotlin/reflect/jvm/internal/impl/descriptors/runtime/components/RuntimeModuleData;", "clearModuleByClassLoaderCache", "", "getOrCreateModule", "Ljava/lang/Class;", "kotlin-reflection"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ModuleByClassLoaderKt {
    private static final ConcurrentMap<WeakClassLoaderBox, WeakReference<RuntimeModuleData>> moduleByClassLoader = new ConcurrentHashMap();

    public static final void clearModuleByClassLoaderCache() {
        moduleByClassLoader.clear();
    }

    @NotNull
    public static final RuntimeModuleData getOrCreateModule(@NotNull Class<?> getOrCreateModule) {
        Intrinsics.checkParameterIsNotNull(getOrCreateModule, "$this$getOrCreateModule");
        ClassLoader safeClassLoader = ReflectClassUtilKt.getSafeClassLoader(getOrCreateModule);
        WeakClassLoaderBox weakClassLoaderBox = new WeakClassLoaderBox(safeClassLoader);
        WeakReference<RuntimeModuleData> weakReference = moduleByClassLoader.get(weakClassLoaderBox);
        if (weakReference != null) {
            RuntimeModuleData it2 = weakReference.get();
            if (it2 != null) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                return it2;
            }
            moduleByClassLoader.remove(weakClassLoaderBox, weakReference);
        }
        RuntimeModuleData create = RuntimeModuleData.Companion.create(safeClassLoader);
        while (true) {
            try {
                WeakReference<RuntimeModuleData> putIfAbsent = moduleByClassLoader.putIfAbsent(weakClassLoaderBox, new WeakReference<>(create));
                if (putIfAbsent == null) {
                    return create;
                }
                RuntimeModuleData runtimeModuleData = putIfAbsent.get();
                if (runtimeModuleData != null) {
                    return runtimeModuleData;
                }
                moduleByClassLoader.remove(weakClassLoaderBox, putIfAbsent);
            } finally {
                weakClassLoaderBox.setTemporaryStrongRef(null);
            }
        }
    }
}
