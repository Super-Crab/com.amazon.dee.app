package kotlinx.serialization.modules;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SerialModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J(\u0010\u0007\u001a\n\u0012\u0004\u0012\u0002H\t\u0018\u00010\b\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\fH\u0016J7\u0010\r\u001a\f\u0012\u0006\b\u0001\u0012\u0002H\t\u0018\u00010\b\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\t0\f2\u0006\u0010\u000f\u001a\u0002H\tH\u0016¢\u0006\u0002\u0010\u0010J2\u0010\r\u001a\f\u0012\u0006\b\u0001\u0012\u0002H\t\u0018\u00010\b\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\t0\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¨\u0006\u0013"}, d2 = {"Lkotlinx/serialization/modules/EmptyModule;", "Lkotlinx/serialization/modules/SerialModule;", "()V", "dumpTo", "", "collector", "Lkotlinx/serialization/modules/SerialModuleCollector;", "getContextual", "Lkotlinx/serialization/KSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "kclass", "Lkotlin/reflect/KClass;", "getPolymorphic", "baseClass", "value", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Lkotlinx/serialization/KSerializer;", "serializedClassName", "", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class EmptyModule implements SerialModule {
    public static final EmptyModule INSTANCE = new EmptyModule();

    private EmptyModule() {
    }

    @Override // kotlinx.serialization.modules.SerialModule
    public void dumpTo(@NotNull SerialModuleCollector collector) {
        Intrinsics.checkParameterIsNotNull(collector, "collector");
    }

    @Override // kotlinx.serialization.modules.SerialModule
    @Nullable
    public <T> KSerializer<T> getContextual(@NotNull KClass<T> kclass) {
        Intrinsics.checkParameterIsNotNull(kclass, "kclass");
        return null;
    }

    @Override // kotlinx.serialization.modules.SerialModule
    @Nullable
    public <T> KSerializer<? extends T> getPolymorphic(@NotNull KClass<T> baseClass, @NotNull T value) {
        Intrinsics.checkParameterIsNotNull(baseClass, "baseClass");
        Intrinsics.checkParameterIsNotNull(value, "value");
        return null;
    }

    @Override // kotlinx.serialization.modules.SerialModule
    @Nullable
    public <T> KSerializer<? extends T> getPolymorphic(@NotNull KClass<T> baseClass, @NotNull String serializedClassName) {
        Intrinsics.checkParameterIsNotNull(baseClass, "baseClass");
        Intrinsics.checkParameterIsNotNull(serializedClassName, "serializedClassName");
        return null;
    }
}
