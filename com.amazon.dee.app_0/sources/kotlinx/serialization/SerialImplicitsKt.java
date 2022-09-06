package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.auth.BuildConfig;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.builtins.CollectionSerializersKt;
import kotlinx.serialization.modules.SerialModule;
import kotlinx.serialization.modules.SerialModuleExtensionsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialImplicits.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0006\u001a&\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0006\u001a&\u0010\u0007\u001a\u00020\b\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\t\u001a&\u0010\n\u001a\u0002H\u0002\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0001H\u0087\b¢\u0006\u0002\u0010\f\u001a&\u0010\r\u001a\u0002H\u0002\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0006\u0010\u000e\u001a\u00020\bH\u0087\b¢\u0006\u0002\u0010\u000f\u001a&\u0010\u0010\u001a\u0002H\u0002\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0087\b¢\u0006\u0002\u0010\u0013\u001a'\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0015\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00112\u0006\u0010\u0016\u001a\u00020\bH\u0087\b\u001a9\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001a0\u0018\"\n\b\u0000\u0010\u0019\u0018\u0001*\u00020\u0003\"\n\b\u0001\u0010\u001a\u0018\u0001*\u00020\u0003*\u00020\u00112\u0006\u0010\u001b\u001a\u00020\bH\u0087\b\u001a&\u0010\u001c\u001a\u00020\b\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00112\u0006\u0010\u0005\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u001d\u001a'\u0010\u001c\u001a\u00020\b\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00112\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0015H\u0087\b\u001a9\u0010\u001c\u001a\u00020\b\"\n\b\u0000\u0010\u0019\u0018\u0001*\u00020\u0003\"\n\b\u0001\u0010\u001a\u0018\u0001*\u00020\u0003*\u00020\u00112\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001a0\u0018H\u0087\b¨\u0006\u001e"}, d2 = {"dump", "", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/serialization/BinaryFormat;", "value", "(Lkotlinx/serialization/BinaryFormat;Ljava/lang/Object;)[B", "dumps", "", "(Lkotlinx/serialization/BinaryFormat;Ljava/lang/Object;)Ljava/lang/String;", "load", "raw", "(Lkotlinx/serialization/BinaryFormat;[B)Ljava/lang/Object;", "loads", "hex", "(Lkotlinx/serialization/BinaryFormat;Ljava/lang/String;)Ljava/lang/Object;", "parse", "Lkotlinx/serialization/StringFormat;", "str", "(Lkotlinx/serialization/StringFormat;Ljava/lang/String;)Ljava/lang/Object;", "parseList", "", "objects", "parseMap", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, BuildConfig.FLAVOR_authtype, "stringify", "(Lkotlinx/serialization/StringFormat;Ljava/lang/Object;)Ljava/lang/String;", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerialImplicitsKt {
    @ImplicitReflectionSerializer
    @NotNull
    public static final /* synthetic */ <T> byte[] dump(@NotNull BinaryFormat dump, @NotNull T value) {
        Intrinsics.checkParameterIsNotNull(dump, "$this$dump");
        Intrinsics.checkParameterIsNotNull(value, "value");
        SerialModule context = dump.getContext();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return dump.dump(SerialModuleExtensionsKt.getContextualOrDefault(context, Reflection.getOrCreateKotlinClass(Object.class)), value);
    }

    @ImplicitReflectionSerializer
    @NotNull
    public static final /* synthetic */ <T> String dumps(@NotNull BinaryFormat dumps, @NotNull T value) {
        Intrinsics.checkParameterIsNotNull(dumps, "$this$dumps");
        Intrinsics.checkParameterIsNotNull(value, "value");
        SerialModule context = dumps.getContext();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return SerialFormatKt.dumps(dumps, SerialModuleExtensionsKt.getContextualOrDefault(context, Reflection.getOrCreateKotlinClass(Object.class)), value);
    }

    @ImplicitReflectionSerializer
    @NotNull
    public static final /* synthetic */ <T> T load(@NotNull BinaryFormat load, @NotNull byte[] raw) {
        Intrinsics.checkParameterIsNotNull(load, "$this$load");
        Intrinsics.checkParameterIsNotNull(raw, "raw");
        SerialModule context = load.getContext();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) load.load(SerialModuleExtensionsKt.getContextualOrDefault(context, Reflection.getOrCreateKotlinClass(Object.class)), raw);
    }

    @ImplicitReflectionSerializer
    @NotNull
    public static final /* synthetic */ <T> T loads(@NotNull BinaryFormat loads, @NotNull String hex) {
        Intrinsics.checkParameterIsNotNull(loads, "$this$loads");
        Intrinsics.checkParameterIsNotNull(hex, "hex");
        SerialModule context = loads.getContext();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) SerialFormatKt.loads(loads, SerialModuleExtensionsKt.getContextualOrDefault(context, Reflection.getOrCreateKotlinClass(Object.class)), hex);
    }

    @ImplicitReflectionSerializer
    @NotNull
    public static final /* synthetic */ <T> T parse(@NotNull StringFormat parse, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(parse, "$this$parse");
        Intrinsics.checkParameterIsNotNull(str, "str");
        SerialModule context = parse.getContext();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) parse.parse(SerialModuleExtensionsKt.getContextualOrDefault(context, Reflection.getOrCreateKotlinClass(Object.class)), str);
    }

    @ImplicitReflectionSerializer
    @NotNull
    public static final /* synthetic */ <T> List<T> parseList(@NotNull StringFormat parseList, @NotNull String objects) {
        Intrinsics.checkParameterIsNotNull(parseList, "$this$parseList");
        Intrinsics.checkParameterIsNotNull(objects, "objects");
        SerialModule context = parseList.getContext();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (List) parseList.parse(CollectionSerializersKt.getList(SerialModuleExtensionsKt.getContextualOrDefault(context, Reflection.getOrCreateKotlinClass(Object.class))), objects);
    }

    @ImplicitReflectionSerializer
    @NotNull
    public static final /* synthetic */ <K, V> Map<K, V> parseMap(@NotNull StringFormat parseMap, @NotNull String map) {
        Intrinsics.checkParameterIsNotNull(parseMap, "$this$parseMap");
        Intrinsics.checkParameterIsNotNull(map, "map");
        SerialModule context = parseMap.getContext();
        Intrinsics.reifiedOperationMarker(4, "K");
        KSerializer contextualOrDefault = SerialModuleExtensionsKt.getContextualOrDefault(context, Reflection.getOrCreateKotlinClass(Object.class));
        SerialModule context2 = parseMap.getContext();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        return (Map) parseMap.parse(CollectionSerializersKt.MapSerializer(contextualOrDefault, SerialModuleExtensionsKt.getContextualOrDefault(context2, Reflection.getOrCreateKotlinClass(Object.class))), map);
    }

    @ImplicitReflectionSerializer
    @NotNull
    public static final /* synthetic */ <T> String stringify(@NotNull StringFormat stringify, @NotNull T value) {
        Intrinsics.checkParameterIsNotNull(stringify, "$this$stringify");
        Intrinsics.checkParameterIsNotNull(value, "value");
        SerialModule context = stringify.getContext();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return stringify.stringify(SerialModuleExtensionsKt.getContextualOrDefault(context, Reflection.getOrCreateKotlinClass(Object.class)), value);
    }

    @ImplicitReflectionSerializer
    @NotNull
    public static final /* synthetic */ <T> String stringify(@NotNull StringFormat stringify, @NotNull List<? extends T> objects) {
        Intrinsics.checkParameterIsNotNull(stringify, "$this$stringify");
        Intrinsics.checkParameterIsNotNull(objects, "objects");
        SerialModule context = stringify.getContext();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return stringify.stringify(CollectionSerializersKt.getList(SerialModuleExtensionsKt.getContextualOrDefault(context, Reflection.getOrCreateKotlinClass(Object.class))), objects);
    }

    @ImplicitReflectionSerializer
    @NotNull
    public static final /* synthetic */ <K, V> String stringify(@NotNull StringFormat stringify, @NotNull Map<K, ? extends V> map) {
        Intrinsics.checkParameterIsNotNull(stringify, "$this$stringify");
        Intrinsics.checkParameterIsNotNull(map, "map");
        SerialModule context = stringify.getContext();
        Intrinsics.reifiedOperationMarker(4, "K");
        KSerializer contextualOrDefault = SerialModuleExtensionsKt.getContextualOrDefault(context, Reflection.getOrCreateKotlinClass(Object.class));
        SerialModule context2 = stringify.getContext();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        return stringify.stringify(CollectionSerializersKt.MapSerializer(contextualOrDefault, SerialModuleExtensionsKt.getContextualOrDefault(context2, Reflection.getOrCreateKotlinClass(Object.class))), map);
    }
}
