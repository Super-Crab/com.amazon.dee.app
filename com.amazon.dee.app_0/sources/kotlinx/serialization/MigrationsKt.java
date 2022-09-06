package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.auth.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.NullableSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Migrations.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a$\u0010\u001c\u001a\n\u0012\u0004\u0012\u0002H\u0006\u0018\u00010\u0004\"\b\b\u0000\u0010\u0006*\u00020\u0015*\b\u0012\u0004\u0012\u0002H\u00060\u001dH\u0007\u001aK\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H 0\u001f\"\b\b\u0000\u0010\u0006*\u00020\u0015\"\n\b\u0001\u0010 *\u0004\u0018\u0001H\u0006*\u0012\u0012\u0004\u0012\u0002H 0!j\b\u0012\u0004\u0012\u0002H `\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00060\u001dH\u0007¢\u0006\u0002\u0010$\u001a\u0014\u0010%\u001a\u00020&*\u00020&2\u0006\u0010'\u001a\u00020\u0001H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"6\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00050\u0004\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"T\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f0\u0004\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u00040\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"6\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00060\u0004\"\b\b\u0000\u0010\u0006*\u00020\u0015*\b\u0012\u0004\u0012\u0002H\u00060\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\b\u001a\u0004\b\u0017\u0010\n\"6\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00190\u0004\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001a\u0010\b\u001a\u0004\b\u001b\u0010\n*>\b\u0007\u0010(\"\u00020)2\u00020)B0\b*\u0012\b\b\u0002\u0012\u0004\b\b(+\u0012\"\b,\u0012\u001e\b\u000bB\u001a\b-\u0012\f\b.\u0012\b\b\fJ\u0004\b\b(/\u0012\b\b0\u0012\u0004\b\b(1*>\b\u0007\u00102\"\u0002032\u000203B0\b*\u0012\b\b\u0002\u0012\u0004\b\b(4\u0012\"\b,\u0012\u001e\b\u000bB\u001a\b-\u0012\f\b.\u0012\b\b\fJ\u0004\b\b(5\u0012\b\b0\u0012\u0004\b\b(6¨\u00067"}, d2 = {"enumReflectiveAccessMessage", "", "message", "list", "Lkotlinx/serialization/KSerializer;", "", ExifInterface.GPS_DIRECTION_TRUE, "list$annotations", "(Lkotlinx/serialization/KSerializer;)V", "getList", "(Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/KSerializer;", BuildConfig.FLAVOR_authtype, "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/Pair;", "map$annotations", "(Lkotlin/Pair;)V", "getMap", "(Lkotlin/Pair;)Lkotlinx/serialization/KSerializer;", "nullable", "", "nullable$annotations", "getNullable", "set", "", "set$annotations", "getSet", "compiledSerializer", "Lkotlin/reflect/KClass;", "toNativeArray", "", ExifInterface.LONGITUDE_EAST, "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "eClass", "(Ljava/util/ArrayList;Lkotlin/reflect/KClass;)[Ljava/lang/Object;", "withName", "Lkotlinx/serialization/SerialDescriptor;", "name", "ElementValueDecoder", "Lkotlinx/serialization/builtins/AbstractDecoder;", "Lkotlin/Deprecated;", "Renamed to AbstractDecoder", "replaceWith", "Lkotlin/ReplaceWith;", "imports", "kotlinx.serialization.builtins.AbstractDecoder", "expression", "AbstractDecoder", "ElementValueEncoder", "Lkotlinx/serialization/builtins/AbstractEncoder;", "Renamed to AbstractEncoder", "kotlinx.serialization.builtins.AbstractEncoder", "AbstractEncoder", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MigrationsKt {
    private static final String enumReflectiveAccessMessage = "Deprecated because reflected operations on enums are not supported correctly on Kotlin/JS and Kotlin/Native.\nPrefer using reified functions or enum serializers.";
    private static final String message = "Mapper was renamed to Properties to better reflect its semantics and extracted to separate artifact kotlinx-serialization-properties";

    @Deprecated(message = "Renamed to AbstractDecoder", replaceWith = @ReplaceWith(expression = "AbstractDecoder", imports = {"kotlinx.serialization.builtins.AbstractDecoder"}))
    public static /* synthetic */ void ElementValueDecoder$annotations() {
    }

    @Deprecated(message = "Renamed to AbstractEncoder", replaceWith = @ReplaceWith(expression = "AbstractEncoder", imports = {"kotlinx.serialization.builtins.AbstractEncoder"}))
    public static /* synthetic */ void ElementValueEncoder$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This function accidentally slipped to a public API surface and is not intended for public use since it does not have clear specification.", replaceWith = @ReplaceWith(expression = "serializerOrNull", imports = {}))
    @ImplicitReflectionSerializer
    @Nullable
    public static final <T> KSerializer<T> compiledSerializer(@NotNull KClass<T> compiledSerializer) {
        Intrinsics.checkParameterIsNotNull(compiledSerializer, "$this$compiledSerializer");
        return SerializationKt.compiledSerializerImpl(compiledSerializer);
    }

    @NotNull
    public static final <T> KSerializer<List<T>> getList(@NotNull KSerializer<T> list) {
        Intrinsics.checkParameterIsNotNull(list, "$this$list");
        return new ArrayListSerializer(list);
    }

    @NotNull
    public static final <K, V> KSerializer<Map<K, V>> getMap(@NotNull Pair<? extends KSerializer<K>, ? extends KSerializer<V>> map) {
        Intrinsics.checkParameterIsNotNull(map, "$this$map");
        return new LinkedHashMapSerializer(map.getFirst(), map.getSecond());
    }

    @NotNull
    public static final <T> KSerializer<T> getNullable(@NotNull KSerializer<T> nullable) {
        Intrinsics.checkParameterIsNotNull(nullable, "$this$nullable");
        return nullable.getDescriptor().isNullable() ? nullable : new NullableSerializer(nullable);
    }

    @NotNull
    public static final <T> KSerializer<Set<T>> getSet(@NotNull KSerializer<T> set) {
        Intrinsics.checkParameterIsNotNull(set, "$this$set");
        return new LinkedHashSetSerializer(set);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of the same extension from builtins package", replaceWith = @ReplaceWith(expression = "list", imports = {"kotlinx.serialization.builtins.list"}))
    @LowPriorityInOverloadResolution
    public static /* synthetic */ void list$annotations(KSerializer kSerializer) {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of the MapSerializer() factory function", replaceWith = @ReplaceWith(expression = "MapSerializer(this.first, this.second)", imports = {"kotlinx.serialization.builtins.MapSerializer"}))
    public static /* synthetic */ void map$annotations(Pair pair) {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of the same extension from builtins package", replaceWith = @ReplaceWith(expression = "nullable", imports = {"kotlinx.serialization.builtins.nullable"}))
    @LowPriorityInOverloadResolution
    public static /* synthetic */ void nullable$annotations(KSerializer kSerializer) {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of the same extension from builtins package", replaceWith = @ReplaceWith(expression = "set", imports = {"kotlinx.serialization.builtins.set"}))
    @LowPriorityInOverloadResolution
    public static /* synthetic */ void set$annotations(KSerializer kSerializer) {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This function accidentally slipped to a public API surface and is not intended for public use since it does not have clear specification. Provide your own replacement.")
    @NotNull
    public static final <T, E extends T> E[] toNativeArray(@NotNull ArrayList<E> toNativeArray, @NotNull KClass<T> eClass) {
        Intrinsics.checkParameterIsNotNull(toNativeArray, "$this$toNativeArray");
        Intrinsics.checkParameterIsNotNull(eClass, "eClass");
        return (E[]) SerializationKt.toNativeArrayImpl(toNativeArray, eClass);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of PrimitiveDescriptor factory function", replaceWith = @ReplaceWith(expression = "PrimitiveDescriptor(name, this.kind)", imports = {}))
    @NotNull
    public static final SerialDescriptor withName(@NotNull SerialDescriptor withName, @NotNull String name) {
        Intrinsics.checkParameterIsNotNull(withName, "$this$withName");
        Intrinsics.checkParameterIsNotNull(name, "name");
        throw new IllegalStateException("No longer supported".toString());
    }
}
