package com.amazon.tarazed.core.registry;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.tarazed.core.registry.component.TarazedComponent;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedComponentRegistry.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000bJ%\u0010\f\u001a\u0004\u0018\u0001H\r\"\b\b\u0000\u0010\r*\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u0005¢\u0006\u0002\u0010\u000fJ,\u0010\u0010\u001a\u00020\n\"\b\b\u0000\u0010\r*\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\r0\bJ,\u0010\u0010\u001a\u00020\n\"\b\b\u0000\u0010\r*\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u00122\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\r0\bR\u001e\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0007\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\b0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/tarazed/core/registry/TarazedComponentRegistry;", "", "()V", "components", "", "Ljava/lang/Class;", "Lcom/amazon/tarazed/core/registry/component/TarazedComponent;", "creationBlocks", "Lkotlin/Function0;", "clearRegisteredComponents", "", "clearRegisteredComponents$TarazedMobileCore_release", "getComponent", ExifInterface.GPS_DIRECTION_TRUE, "interfaceClass", "(Ljava/lang/Class;)Lcom/amazon/tarazed/core/registry/component/TarazedComponent;", "registerComponent", "creationBlock", "Lkotlin/reflect/KClass;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedComponentRegistry {
    public static final TarazedComponentRegistry INSTANCE = new TarazedComponentRegistry();
    private static final Map<Class<?>, TarazedComponent> components = new LinkedHashMap();
    private static final Map<Class<?>, Function0<TarazedComponent>> creationBlocks = new LinkedHashMap();

    private TarazedComponentRegistry() {
    }

    public final void clearRegisteredComponents$TarazedMobileCore_release() {
        components.clear();
        creationBlocks.clear();
    }

    @Nullable
    public final <T extends TarazedComponent> T getComponent(@NotNull Class<T> interfaceClass) {
        Intrinsics.checkParameterIsNotNull(interfaceClass, "interfaceClass");
        T t = (T) components.get(interfaceClass);
        if (t == null) {
            Function0<TarazedComponent> function0 = creationBlocks.get(interfaceClass);
            if (function0 == null) {
                return null;
            }
            t = (T) function0.mo12560invoke();
            components.put(interfaceClass, t);
            if (t == null) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
        }
        return t;
    }

    public final <T extends TarazedComponent> void registerComponent(@NotNull Class<T> interfaceClass, @NotNull Function0<? extends T> creationBlock) {
        Intrinsics.checkParameterIsNotNull(interfaceClass, "interfaceClass");
        Intrinsics.checkParameterIsNotNull(creationBlock, "creationBlock");
        creationBlocks.put(interfaceClass, creationBlock);
    }

    public final <T extends TarazedComponent> void registerComponent(@NotNull KClass<T> interfaceClass, @NotNull Function0<? extends T> creationBlock) {
        Intrinsics.checkParameterIsNotNull(interfaceClass, "interfaceClass");
        Intrinsics.checkParameterIsNotNull(creationBlock, "creationBlock");
        registerComponent(JvmClassMappingKt.getJavaClass((KClass) interfaceClass), creationBlock);
    }
}
