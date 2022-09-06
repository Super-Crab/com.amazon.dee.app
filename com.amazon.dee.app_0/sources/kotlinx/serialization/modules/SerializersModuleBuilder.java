package kotlinx.serialization.modules;

import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.MapsKt___MapsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.sequences.Sequence;
import kotlinx.serialization.KSerializer;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SerialModuleBuilders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\r\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\fJ.\u0010\r\u001a\u00020\u000e\"\b\b\u0000\u0010\u000f*\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0006H\u0016J\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000bJT\u0010\u0015\u001a\u00020\u000e2\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00052\u001a\u0010\u0017\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00050\u0018\"\u0006\u0012\u0002\b\u00030\u00052\u001f\b\u0002\u0010\u0019\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001b\u0012\u0004\u0012\u00020\u000e0\u001a¢\u0006\u0002\b\u001c¢\u0006\u0002\u0010\u001dJF\u0010\u0015\u001a\u00020\u000e\"\b\b\u0000\u0010\u001e*\u00020\u0010\"\b\b\u0001\u0010\u001f*\u0002H\u001e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u001e0\u00052\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001f0\u00052\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001f0\u0006H\u0016JQ\u0010\u0015\u001a\u00020\u000e\"\b\b\u0000\u0010\u001e*\u00020\u00102\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u001e0\u00052\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u0002H\u001e\u0018\u00010\u00062\u001f\b\u0002\u0010\u0019\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001e0\u001b\u0012\u0004\u0012\u00020\u000e0\u001a¢\u0006\u0002\b\u001cJH\u0010\u0015\u001a\u00020\u000e\"\n\b\u0000\u0010\u001e\u0018\u0001*\u00020\u00102\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u0002H\u001e\u0018\u00010\u00062\u001f\b\n\u0010\u0019\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001e0\u001b\u0012\u0004\u0012\u00020\u000e0\u001a¢\u0006\u0002\b\u001cH\u0086\bJP\u0010#\u001a\u00020\u000e\"\b\b\u0000\u0010\u001e*\u00020\u0010\"\b\b\u0001\u0010\u001f*\u0002H\u001e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u001e0\u00052\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u001f0\u00052\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u001f0\u00062\b\b\u0002\u0010&\u001a\u00020'H\u0001J8\u0010(\u001a\u00020\u000e\"\b\b\u0000\u0010\u000f*\u00020\u00102\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00062\b\b\u0002\u0010&\u001a\u00020'H\u0001R\"\u0010\u0003\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u0007\u001a\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\t\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0018\u0012\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lkotlinx/serialization/modules/SerializersModuleBuilder;", "Lkotlinx/serialization/modules/SerialModuleCollector;", "()V", "class2Serializer", "", "Lkotlin/reflect/KClass;", "Lkotlinx/serialization/KSerializer;", "polyBase2NamedSerializers", "", "polyBase2Serializers", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lkotlinx/serialization/modules/SerialModule;", "build$kotlinx_serialization_runtime", "contextual", "", ExifInterface.GPS_DIRECTION_TRUE, "", "kClass", "serializer", "include", "other", "polymorphic", "baseClass", "baseClasses", "", "buildAction", "Lkotlin/Function1;", "Lkotlinx/serialization/modules/PolymorphicModuleBuilder;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/reflect/KClass;[Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function1;)V", "Base", "Sub", "actualClass", "actualSerializer", "baseSerializer", "registerPolymorphicSerializer", "concreteClass", "concreteSerializer", "allowOverwrite", "", "registerSerializer", "forClass", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerializersModuleBuilder implements SerialModuleCollector {
    private final Map<KClass<?>, KSerializer<?>> class2Serializer = new HashMap();
    private final Map<KClass<?>, Map<KClass<?>, KSerializer<?>>> polyBase2Serializers = new HashMap();
    private final Map<KClass<?>, Map<String, KSerializer<?>>> polyBase2NamedSerializers = new HashMap();

    public static /* synthetic */ void polymorphic$default(SerializersModuleBuilder serializersModuleBuilder, KClass kClass, KSerializer kSerializer, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            kSerializer = null;
        }
        if ((i & 4) != 0) {
            function1 = SerializersModuleBuilder$polymorphic$1.INSTANCE;
        }
        serializersModuleBuilder.polymorphic(kClass, kSerializer, function1);
    }

    public static /* synthetic */ void registerPolymorphicSerializer$default(SerializersModuleBuilder serializersModuleBuilder, KClass kClass, KClass kClass2, KSerializer kSerializer, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        serializersModuleBuilder.registerPolymorphicSerializer(kClass, kClass2, kSerializer, z);
    }

    public static /* synthetic */ void registerSerializer$default(SerializersModuleBuilder serializersModuleBuilder, KClass kClass, KSerializer kSerializer, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        serializersModuleBuilder.registerSerializer(kClass, kSerializer, z);
    }

    @NotNull
    public final SerialModule build$kotlinx_serialization_runtime() {
        return new SerialModuleImpl(this.class2Serializer, this.polyBase2Serializers, this.polyBase2NamedSerializers);
    }

    @Override // kotlinx.serialization.modules.SerialModuleCollector
    public <T> void contextual(@NotNull KClass<T> kClass, @NotNull KSerializer<T> serializer) {
        Intrinsics.checkParameterIsNotNull(kClass, "kClass");
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        registerSerializer$default(this, kClass, serializer, false, 4, null);
    }

    public final void include(@NotNull SerialModule other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        other.dumpTo(this);
    }

    @Override // kotlinx.serialization.modules.SerialModuleCollector
    public <Base, Sub extends Base> void polymorphic(@NotNull KClass<Base> baseClass, @NotNull KClass<Sub> actualClass, @NotNull KSerializer<Sub> actualSerializer) {
        Intrinsics.checkParameterIsNotNull(baseClass, "baseClass");
        Intrinsics.checkParameterIsNotNull(actualClass, "actualClass");
        Intrinsics.checkParameterIsNotNull(actualSerializer, "actualSerializer");
        registerPolymorphicSerializer$default(this, baseClass, actualClass, actualSerializer, false, 8, null);
    }

    @JvmName(name = "registerPolymorphicSerializer")
    public final <Base, Sub extends Base> void registerPolymorphicSerializer(@NotNull KClass<Base> baseClass, @NotNull KClass<Sub> concreteClass, @NotNull KSerializer<Sub> concreteSerializer, boolean z) {
        Sequence asSequence;
        Object obj;
        boolean z2;
        Intrinsics.checkParameterIsNotNull(baseClass, "baseClass");
        Intrinsics.checkParameterIsNotNull(concreteClass, "concreteClass");
        Intrinsics.checkParameterIsNotNull(concreteSerializer, "concreteSerializer");
        String serialName = concreteSerializer.getDescriptor().getSerialName();
        Map<KClass<?>, Map<KClass<?>, KSerializer<?>>> map = this.polyBase2Serializers;
        Map<KClass<?>, KSerializer<?>> map2 = map.get(baseClass);
        if (map2 == null) {
            map2 = new HashMap<>();
            map.put(baseClass, map2);
        }
        Map<KClass<?>, KSerializer<?>> map3 = map2;
        KSerializer<?> kSerializer = map3.get(concreteClass);
        Map<KClass<?>, Map<String, KSerializer<?>>> map4 = this.polyBase2NamedSerializers;
        Map<String, KSerializer<?>> map5 = map4.get(baseClass);
        if (map5 == null) {
            map5 = new HashMap<>();
            map4.put(baseClass, map5);
        }
        Map<String, KSerializer<?>> map6 = map5;
        if (z) {
            if (kSerializer != null) {
                map6.remove(kSerializer.getDescriptor().getSerialName());
            }
            map3.put(concreteClass, concreteSerializer);
            map6.put(serialName, concreteSerializer);
            return;
        }
        if (kSerializer != null) {
            if (!(!Intrinsics.areEqual(kSerializer, concreteSerializer))) {
                map6.remove(kSerializer.getDescriptor().getSerialName());
            } else {
                throw new SerializerAlreadyRegisteredException(baseClass, concreteClass);
            }
        }
        KSerializer<?> kSerializer2 = map6.get(serialName);
        if (kSerializer2 != null) {
            Map<KClass<?>, KSerializer<?>> map7 = this.polyBase2Serializers.get(baseClass);
            if (map7 == null) {
                Intrinsics.throwNpe();
            }
            asSequence = MapsKt___MapsKt.asSequence(map7);
            Iterator it2 = asSequence.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it2.next();
                if (((KSerializer) ((Map.Entry) obj).getValue()) == kSerializer2) {
                    z2 = true;
                    continue;
                } else {
                    z2 = false;
                    continue;
                }
                if (z2) {
                    break;
                }
            }
            throw new IllegalArgumentException("Multiple polymorphic serializers for base class '" + baseClass + "' have the same serial name '" + serialName + "': '" + concreteClass + "' and '" + ((Map.Entry) obj) + Chars.QUOTE);
        }
        map3.put(concreteClass, concreteSerializer);
        map6.put(serialName, concreteSerializer);
    }

    @JvmName(name = "registerSerializer")
    public final <T> void registerSerializer(@NotNull KClass<T> forClass, @NotNull KSerializer<T> serializer, boolean z) {
        KSerializer<?> kSerializer;
        Intrinsics.checkParameterIsNotNull(forClass, "forClass");
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        if (!z && (kSerializer = this.class2Serializer.get(forClass)) != null && (!Intrinsics.areEqual(kSerializer, serializer))) {
            String serialName = serializer.getDescriptor().getSerialName();
            String serialName2 = kSerializer.getDescriptor().getSerialName();
            throw new SerializerAlreadyRegisteredException("Serializer for " + forClass + " already registered in this module: " + kSerializer + " (" + serialName2 + "), attempted to register " + serializer + " (" + serialName + ')');
        }
        this.class2Serializer.put(forClass, serializer);
    }

    public static /* synthetic */ void polymorphic$default(SerializersModuleBuilder serializersModuleBuilder, KSerializer kSerializer, Function1 buildAction, int i, Object obj) {
        if ((i & 1) != 0) {
            kSerializer = null;
        }
        if ((i & 2) != 0) {
            buildAction = SerializersModuleBuilder$polymorphic$2.INSTANCE;
        }
        Intrinsics.checkParameterIsNotNull(buildAction, "buildAction");
        Intrinsics.reifiedOperationMarker(4, "Base");
        serializersModuleBuilder.polymorphic(Reflection.getOrCreateKotlinClass(Object.class), kSerializer, buildAction);
    }

    public final <Base> void polymorphic(@NotNull KClass<Base> baseClass, @Nullable KSerializer<Base> kSerializer, @NotNull Function1<? super PolymorphicModuleBuilder<Base>, Unit> buildAction) {
        Intrinsics.checkParameterIsNotNull(baseClass, "baseClass");
        Intrinsics.checkParameterIsNotNull(buildAction, "buildAction");
        PolymorphicModuleBuilder polymorphicModuleBuilder = new PolymorphicModuleBuilder(baseClass, kSerializer);
        buildAction.mo12165invoke(polymorphicModuleBuilder);
        polymorphicModuleBuilder.buildTo$kotlinx_serialization_runtime(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void polymorphic$default(SerializersModuleBuilder serializersModuleBuilder, KClass kClass, KClass[] kClassArr, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = SerializersModuleBuilder$polymorphic$3.INSTANCE;
        }
        serializersModuleBuilder.polymorphic(kClass, kClassArr, function1);
    }

    public final /* synthetic */ <Base> void polymorphic(@Nullable KSerializer<Base> kSerializer, @NotNull Function1<? super PolymorphicModuleBuilder<Base>, Unit> buildAction) {
        Intrinsics.checkParameterIsNotNull(buildAction, "buildAction");
        Intrinsics.reifiedOperationMarker(4, "Base");
        polymorphic(Reflection.getOrCreateKotlinClass(Object.class), kSerializer, buildAction);
    }

    public final void polymorphic(@NotNull KClass<?> baseClass, @NotNull KClass<?>[] baseClasses, @NotNull Function1<? super PolymorphicModuleBuilder<Object>, Unit> buildAction) {
        Intrinsics.checkParameterIsNotNull(baseClass, "baseClass");
        Intrinsics.checkParameterIsNotNull(baseClasses, "baseClasses");
        Intrinsics.checkParameterIsNotNull(buildAction, "buildAction");
        PolymorphicModuleBuilder polymorphicModuleBuilder = new PolymorphicModuleBuilder(baseClass, null, 2, null);
        buildAction.mo12165invoke(polymorphicModuleBuilder);
        polymorphicModuleBuilder.buildTo$kotlinx_serialization_runtime(this);
        for (KClass<?> kClass : baseClasses) {
            if (kClass == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
            }
            polymorphicModuleBuilder.changeBase$kotlinx_serialization_runtime(kClass, null).buildTo$kotlinx_serialization_runtime(this);
        }
    }
}
