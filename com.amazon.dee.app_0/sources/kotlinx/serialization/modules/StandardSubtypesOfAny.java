package kotlinx.serialization.modules;

import com.amazon.alexa.auth.BuildConfig;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.BooleanCompanionObject;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.ShortCompanionObject;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PolymorphicSerializer;
import kotlinx.serialization.SerializationKt;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.builtins.CollectionSerializersKt;
import kotlinx.serialization.builtins.PrimitiveSerializersKt;
import kotlinx.serialization.internal.HashMapSerializer;
import kotlinx.serialization.internal.HashSetSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StandardSubtypesOfAny.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u000bJ\u001b\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\u0001H\u0000¢\u0006\u0002\b\u000eR\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/serialization/modules/StandardSubtypesOfAny;", "", "()V", "deserializingMap", "", "", "Lkotlinx/serialization/KSerializer;", BuildConfig.FLAVOR_authtype, "Lkotlin/reflect/KClass;", "getDefaultDeserializer", "serializedClassName", "getDefaultDeserializer$kotlinx_serialization_runtime", "getSubclassSerializer", "objectToCheck", "getSubclassSerializer$kotlinx_serialization_runtime", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class StandardSubtypesOfAny {
    public static final StandardSubtypesOfAny INSTANCE = new StandardSubtypesOfAny();
    private static final Map<String, KSerializer<?>> deserializingMap;
    private static final Map<KClass<?>, KSerializer<?>> map;

    static {
        Map<KClass<?>, KSerializer<?>> mapOf;
        int mapCapacity;
        mapOf = MapsKt__MapsKt.mapOf(TuplesKt.to(Reflection.getOrCreateKotlinClass(List.class), CollectionSerializersKt.ListSerializer(BuiltinSerializersKt.getNullable(new PolymorphicSerializer(Reflection.getOrCreateKotlinClass(Object.class))))), TuplesKt.to(Reflection.getOrCreateKotlinClass(LinkedHashSet.class), CollectionSerializersKt.SetSerializer(BuiltinSerializersKt.getNullable(new PolymorphicSerializer(Reflection.getOrCreateKotlinClass(Object.class))))), TuplesKt.to(Reflection.getOrCreateKotlinClass(HashSet.class), new HashSetSerializer(BuiltinSerializersKt.getNullable(new PolymorphicSerializer(Reflection.getOrCreateKotlinClass(Object.class))))), TuplesKt.to(Reflection.getOrCreateKotlinClass(Set.class), CollectionSerializersKt.SetSerializer(BuiltinSerializersKt.getNullable(new PolymorphicSerializer(Reflection.getOrCreateKotlinClass(Object.class))))), TuplesKt.to(Reflection.getOrCreateKotlinClass(LinkedHashMap.class), new LinkedHashMapSerializer(BuiltinSerializersKt.getNullable(new PolymorphicSerializer(Reflection.getOrCreateKotlinClass(Object.class))), BuiltinSerializersKt.getNullable(new PolymorphicSerializer(Reflection.getOrCreateKotlinClass(Object.class))))), TuplesKt.to(Reflection.getOrCreateKotlinClass(HashMap.class), new HashMapSerializer(BuiltinSerializersKt.getNullable(new PolymorphicSerializer(Reflection.getOrCreateKotlinClass(Object.class))), BuiltinSerializersKt.getNullable(new PolymorphicSerializer(Reflection.getOrCreateKotlinClass(Object.class))))), TuplesKt.to(Reflection.getOrCreateKotlinClass(Map.class), new LinkedHashMapSerializer(BuiltinSerializersKt.getNullable(new PolymorphicSerializer(Reflection.getOrCreateKotlinClass(Object.class))), BuiltinSerializersKt.getNullable(new PolymorphicSerializer(Reflection.getOrCreateKotlinClass(Object.class))))), TuplesKt.to(Reflection.getOrCreateKotlinClass(Map.Entry.class), BuiltinSerializersKt.MapEntrySerializer(BuiltinSerializersKt.getNullable(new PolymorphicSerializer(Reflection.getOrCreateKotlinClass(Object.class))), BuiltinSerializersKt.getNullable(new PolymorphicSerializer(Reflection.getOrCreateKotlinClass(Object.class))))), TuplesKt.to(Reflection.getOrCreateKotlinClass(String.class), PrimitiveSerializersKt.serializer(StringCompanionObject.INSTANCE)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Character.TYPE), PrimitiveSerializersKt.serializer(CharCompanionObject.INSTANCE)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Integer.TYPE), PrimitiveSerializersKt.serializer(IntCompanionObject.INSTANCE)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Byte.TYPE), PrimitiveSerializersKt.serializer(ByteCompanionObject.INSTANCE)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Short.TYPE), PrimitiveSerializersKt.serializer(ShortCompanionObject.INSTANCE)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Long.TYPE), PrimitiveSerializersKt.serializer(LongCompanionObject.INSTANCE)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Double.TYPE), PrimitiveSerializersKt.serializer(DoubleCompanionObject.INSTANCE)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Float.TYPE), PrimitiveSerializersKt.serializer(FloatCompanionObject.INSTANCE)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Boolean.TYPE), PrimitiveSerializersKt.serializer(BooleanCompanionObject.INSTANCE)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Unit.class), PrimitiveSerializersKt.UnitSerializer()));
        map = mapOf;
        Map<KClass<?>, KSerializer<?>> map2 = map;
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(map2.size());
        LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
        Iterator<T> it2 = map2.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            linkedHashMap.put(((KSerializer) entry.getValue()).getDescriptor().getSerialName(), entry.getValue());
        }
        deserializingMap = linkedHashMap;
    }

    private StandardSubtypesOfAny() {
    }

    @Nullable
    public final KSerializer<?> getDefaultDeserializer$kotlinx_serialization_runtime(@NotNull String serializedClassName) {
        Intrinsics.checkParameterIsNotNull(serializedClassName, "serializedClassName");
        return deserializingMap.get(serializedClassName);
    }

    @Nullable
    public final KSerializer<?> getSubclassSerializer$kotlinx_serialization_runtime(@NotNull Object objectToCheck) {
        Intrinsics.checkParameterIsNotNull(objectToCheck, "objectToCheck");
        for (Map.Entry<KClass<?>, KSerializer<?>> entry : map.entrySet()) {
            KSerializer<?> value = entry.getValue();
            if (SerializationKt.isInstanceOf(objectToCheck, entry.getKey())) {
                return value;
            }
        }
        return null;
    }
}
