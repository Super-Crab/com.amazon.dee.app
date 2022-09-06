package kotlinx.serialization.modules;

import androidx.exifinterface.media.ExifInterface;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SerialModuleImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001Bu\u0012\u001a\u0010\u0002\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003\u0012*\u0010\u0006\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0018\u0012\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u00030\u0003\u0012&\u0010\u0007\u001a\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u00030\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J(\u0010\u000e\u001a\n\u0012\u0004\u0012\u0002H\u000f\u0018\u00010\u0005\"\b\b\u0000\u0010\u000f*\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0004H\u0016J7\u0010\u0012\u001a\f\u0012\u0006\b\u0001\u0012\u0002H\u000f\u0018\u00010\u0005\"\b\b\u0000\u0010\u000f*\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042\u0006\u0010\u0014\u001a\u0002H\u000fH\u0016¢\u0006\u0002\u0010\u0015J2\u0010\u0012\u001a\f\u0012\u0006\b\u0001\u0012\u0002H\u000f\u0018\u00010\u0005\"\b\b\u0000\u0010\u000f*\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042\u0006\u0010\u0016\u001a\u00020\bH\u0016R\"\u0010\u0002\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u0007\u001a\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u0006\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0018\u0012\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lkotlinx/serialization/modules/SerialModuleImpl;", "Lkotlinx/serialization/modules/SerialModule;", "class2Serializer", "", "Lkotlin/reflect/KClass;", "Lkotlinx/serialization/KSerializer;", "polyBase2Serializers", "polyBase2NamedSerializers", "", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "dumpTo", "", "collector", "Lkotlinx/serialization/modules/SerialModuleCollector;", "getContextual", ExifInterface.GPS_DIRECTION_TRUE, "", "kclass", "getPolymorphic", "baseClass", "value", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Lkotlinx/serialization/KSerializer;", "serializedClassName", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerialModuleImpl implements SerialModule {
    private final Map<KClass<?>, KSerializer<?>> class2Serializer;
    private final Map<KClass<?>, Map<String, KSerializer<?>>> polyBase2NamedSerializers;
    private final Map<KClass<?>, Map<KClass<?>, KSerializer<?>>> polyBase2Serializers;

    /* JADX WARN: Multi-variable type inference failed */
    public SerialModuleImpl(@NotNull Map<KClass<?>, ? extends KSerializer<?>> class2Serializer, @NotNull Map<KClass<?>, ? extends Map<KClass<?>, ? extends KSerializer<?>>> polyBase2Serializers, @NotNull Map<KClass<?>, ? extends Map<String, ? extends KSerializer<?>>> polyBase2NamedSerializers) {
        Intrinsics.checkParameterIsNotNull(class2Serializer, "class2Serializer");
        Intrinsics.checkParameterIsNotNull(polyBase2Serializers, "polyBase2Serializers");
        Intrinsics.checkParameterIsNotNull(polyBase2NamedSerializers, "polyBase2NamedSerializers");
        this.class2Serializer = class2Serializer;
        this.polyBase2Serializers = polyBase2Serializers;
        this.polyBase2NamedSerializers = polyBase2NamedSerializers;
    }

    @Override // kotlinx.serialization.modules.SerialModule
    public void dumpTo(@NotNull SerialModuleCollector collector) {
        Intrinsics.checkParameterIsNotNull(collector, "collector");
        for (Map.Entry<KClass<?>, KSerializer<?>> entry : this.class2Serializer.entrySet()) {
            KClass<?> key = entry.getKey();
            KSerializer<?> value = entry.getValue();
            if (key == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
            }
            if (value != null) {
                collector.contextual(key, value);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
            }
        }
        for (Map.Entry<KClass<?>, Map<KClass<?>, KSerializer<?>>> entry2 : this.polyBase2Serializers.entrySet()) {
            KClass<?> key2 = entry2.getKey();
            for (Map.Entry<KClass<?>, KSerializer<?>> entry3 : entry2.getValue().entrySet()) {
                KClass<?> key3 = entry3.getKey();
                KSerializer<?> value2 = entry3.getValue();
                if (key2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
                }
                if (key3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
                }
                if (value2 != null) {
                    collector.polymorphic(key2, key3, value2);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
                }
            }
        }
    }

    @Override // kotlinx.serialization.modules.SerialModule
    @Nullable
    public <T> KSerializer<T> getContextual(@NotNull KClass<T> kclass) {
        Intrinsics.checkParameterIsNotNull(kclass, "kclass");
        DeserializationStrategy deserializationStrategy = this.class2Serializer.get(kclass);
        if (!(deserializationStrategy instanceof KSerializer)) {
            deserializationStrategy = null;
        }
        return (KSerializer) deserializationStrategy;
    }

    @Override // kotlinx.serialization.modules.SerialModule
    @Nullable
    public <T> KSerializer<? extends T> getPolymorphic(@NotNull KClass<T> baseClass, @NotNull T value) {
        Intrinsics.checkParameterIsNotNull(baseClass, "baseClass");
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (!SerializationKt.isInstanceOf(value, baseClass)) {
            return null;
        }
        Map<KClass<?>, KSerializer<?>> map = this.polyBase2Serializers.get(baseClass);
        KSerializer<? extends T> kSerializer = map != null ? (KSerializer<? extends T>) map.get(Reflection.getOrCreateKotlinClass(value.getClass())) : null;
        if (!(kSerializer instanceof KSerializer)) {
            kSerializer = null;
        }
        if (kSerializer != null) {
            return kSerializer;
        }
        if (!Intrinsics.areEqual(baseClass, Reflection.getOrCreateKotlinClass(Object.class))) {
            return null;
        }
        KSerializer<? extends T> kSerializer2 = (KSerializer<? extends T>) StandardSubtypesOfAny.INSTANCE.getSubclassSerializer$kotlinx_serialization_runtime(value);
        if (kSerializer2 instanceof KSerializer) {
            return kSerializer2;
        }
        return null;
    }

    @Override // kotlinx.serialization.modules.SerialModule
    @Nullable
    public <T> KSerializer<? extends T> getPolymorphic(@NotNull KClass<T> baseClass, @NotNull String serializedClassName) {
        Intrinsics.checkParameterIsNotNull(baseClass, "baseClass");
        Intrinsics.checkParameterIsNotNull(serializedClassName, "serializedClassName");
        KSerializer<? extends T> kSerializer = Intrinsics.areEqual(baseClass, Reflection.getOrCreateKotlinClass(Object.class)) ? (KSerializer<? extends T>) StandardSubtypesOfAny.INSTANCE.getDefaultDeserializer$kotlinx_serialization_runtime(serializedClassName) : null;
        if (kSerializer != null) {
            return kSerializer;
        }
        Map<String, KSerializer<?>> map = this.polyBase2NamedSerializers.get(baseClass);
        KSerializer<? extends T> kSerializer2 = map != null ? (KSerializer<? extends T>) map.get(serializedClassName) : null;
        if (kSerializer2 instanceof KSerializer) {
            return kSerializer2;
        }
        return null;
    }
}
