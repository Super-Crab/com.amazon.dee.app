package kotlinx.serialization.json;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.dee.app.metrics.MetricsConstants;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JsonElement.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u0000 42\u00020\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0002:\u00014B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0002¢\u0006\u0002\u0010\u0005J\u0015\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0002HÆ\u0003J\u0011\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0003H\u0096\u0001J\u0011\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u0001H\u0096\u0001J\u001f\u0010 \u001a\u00020\u00002\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0002HÆ\u0001J\u0013\u0010!\u001a\u00020\u001c2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0096\u0002J\u0013\u0010$\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001d\u001a\u00020\u0003H\u0096\u0003J\u000e\u0010%\u001a\u00020&2\u0006\u0010\u001d\u001a\u00020\u0003J\u0010\u0010'\u001a\u0004\u0018\u00010&2\u0006\u0010\u001d\u001a\u00020\u0003J\"\u0010(\u001a\u0002H)\"\n\b\u0000\u0010)\u0018\u0001*\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0003H\u0086\b¢\u0006\u0002\u0010*J$\u0010+\u001a\u0004\u0018\u0001H)\"\n\b\u0000\u0010)\u0018\u0001*\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0003H\u0086\b¢\u0006\u0002\u0010*J\u000e\u0010,\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u001d\u001a\u00020\u0003J\u000e\u0010.\u001a\u00020/2\u0006\u0010\u001d\u001a\u00020\u0003J\u0010\u00100\u001a\u0004\u0018\u00010/2\u0006\u0010\u001d\u001a\u00020\u0003J\b\u00101\u001a\u00020\u0013H\u0016J\t\u00102\u001a\u00020\u001cH\u0096\u0001J\b\u00103\u001a\u00020\u0003H\u0016R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R$\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\n0\tX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0011\u0010\fR\u0012\u0010\u0012\u001a\u00020\u0013X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u0017X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u00065"}, d2 = {"Lkotlinx/serialization/json/JsonObject;", "Lkotlinx/serialization/json/JsonElement;", "", "", "content", "(Ljava/util/Map;)V", "getContent", "()Ljava/util/Map;", "entries", "", "", "getEntries", "()Ljava/util/Set;", "jsonObject", "getJsonObject", "()Lkotlinx/serialization/json/JsonObject;", QuickTimeAtomTypes.ATOM_KEYS, "getKeys", "size", "", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", "component1", "containsKey", "", "key", "containsValue", "value", "copy", "equals", "other", "", MetricsConstants.Method.CACHE_GET, "getArray", "Lkotlinx/serialization/json/JsonArray;", "getArrayOrNull", "getAs", "J", "(Ljava/lang/String;)Lkotlinx/serialization/json/JsonElement;", "getAsOrNull", "getObject", "getObjectOrNull", "getPrimitive", "Lkotlinx/serialization/json/JsonPrimitive;", "getPrimitiveOrNull", "hashCode", "isEmpty", "toString", "Companion", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@Serializable(with = JsonObjectSerializer.class)
/* loaded from: classes4.dex */
public final class JsonObject extends JsonElement implements Map<String, JsonElement>, KMappedMarker {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Map<String, JsonElement> content;
    @NotNull
    private final JsonObject jsonObject;

    /* compiled from: JsonElement.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lkotlinx/serialization/json/JsonObject$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/serialization/json/JsonObject;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<JsonObject> serializer() {
            return JsonObjectSerializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public JsonObject(@NotNull Map<String, ? extends JsonElement> content) {
        super(null);
        Intrinsics.checkParameterIsNotNull(content, "content");
        this.content = content;
        this.jsonObject = this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ JsonObject copy$default(JsonObject jsonObject, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            map = jsonObject.content;
        }
        return jsonObject.copy(map);
    }

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    public final Map<String, JsonElement> component1() {
        return this.content;
    }

    @Override // java.util.Map
    public /* synthetic */ JsonElement compute(String str, BiFunction<? super String, ? super JsonElement, ? extends JsonElement> biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: compute  reason: avoid collision after fix types in other method */
    public JsonElement compute2(String str, BiFunction<? super String, ? super JsonElement, ? extends JsonElement> biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public /* synthetic */ JsonElement computeIfAbsent(String str, Function<? super String, ? extends JsonElement> function) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: computeIfAbsent  reason: avoid collision after fix types in other method */
    public JsonElement computeIfAbsent2(String str, Function<? super String, ? extends JsonElement> function) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public /* synthetic */ JsonElement computeIfPresent(String str, BiFunction<? super String, ? super JsonElement, ? extends JsonElement> biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: computeIfPresent  reason: avoid collision after fix types in other method */
    public JsonElement computeIfPresent2(String str, BiFunction<? super String, ? super JsonElement, ? extends JsonElement> biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final /* bridge */ boolean containsKey(Object obj) {
        if (obj instanceof String) {
            return containsKey((String) obj);
        }
        return false;
    }

    public boolean containsKey(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.content.containsKey(key);
    }

    @Override // java.util.Map
    public final /* bridge */ boolean containsValue(Object obj) {
        if (obj instanceof JsonElement) {
            return containsValue((JsonElement) obj);
        }
        return false;
    }

    public boolean containsValue(@NotNull JsonElement value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        return this.content.containsValue(value);
    }

    @NotNull
    public final JsonObject copy(@NotNull Map<String, ? extends JsonElement> content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        return new JsonObject(content);
    }

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<String, JsonElement>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public boolean equals(@Nullable Object obj) {
        return Intrinsics.areEqual(this.content, obj);
    }

    @Override // java.util.Map
    public final /* bridge */ JsonElement get(Object obj) {
        if (obj instanceof String) {
            return get((String) obj);
        }
        return null;
    }

    @Nullable
    public JsonElement get(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.content.get(key);
    }

    @NotNull
    public final JsonArray getArray(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Object value = MapsKt.getValue(this, key);
        if (!(value instanceof JsonArray)) {
            value = null;
        }
        JsonArray jsonArray = (JsonArray) value;
        if (jsonArray != null) {
            return jsonArray;
        }
        JsonElementKt.unexpectedJson(key, "JsonArray");
        throw null;
    }

    @Nullable
    public final JsonArray getArrayOrNull(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        JsonElement jsonElement = this.content.get(key);
        if (!(jsonElement instanceof JsonArray)) {
            jsonElement = null;
        }
        return (JsonArray) jsonElement;
    }

    @NotNull
    public final /* synthetic */ <J extends JsonElement> J getAs(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Object obj = get((Object) key);
        Intrinsics.reifiedOperationMarker(2, "J");
        J j = (J) obj;
        if (j != null) {
            return j;
        }
        Intrinsics.reifiedOperationMarker(4, "J");
        JsonElementKt.unexpectedJson(key, Reflection.getOrCreateKotlinClass(JsonElement.class).toString());
        throw null;
    }

    @Nullable
    public final /* synthetic */ <J extends JsonElement> J getAsOrNull(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        JsonElement jsonElement = getContent().get(key);
        Intrinsics.reifiedOperationMarker(2, "J");
        return (J) jsonElement;
    }

    @NotNull
    public final Map<String, JsonElement> getContent() {
        return this.content;
    }

    @NotNull
    public Set<Map.Entry<String, JsonElement>> getEntries() {
        return this.content.entrySet();
    }

    @Override // kotlinx.serialization.json.JsonElement
    @NotNull
    public JsonObject getJsonObject() {
        return this.jsonObject;
    }

    @NotNull
    public Set<String> getKeys() {
        return this.content.keySet();
    }

    @NotNull
    public final JsonObject getObject(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Object value = MapsKt.getValue(this, key);
        if (!(value instanceof JsonObject)) {
            value = null;
        }
        JsonObject jsonObject = (JsonObject) value;
        if (jsonObject != null) {
            return jsonObject;
        }
        JsonElementKt.unexpectedJson(key, "JsonObject");
        throw null;
    }

    @Nullable
    public final JsonObject getObjectOrNull(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        JsonElement jsonElement = this.content.get(key);
        if (!(jsonElement instanceof JsonObject)) {
            jsonElement = null;
        }
        return (JsonObject) jsonElement;
    }

    @NotNull
    public final JsonPrimitive getPrimitive(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Object value = MapsKt.getValue(this, key);
        if (!(value instanceof JsonPrimitive)) {
            value = null;
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) value;
        if (jsonPrimitive != null) {
            return jsonPrimitive;
        }
        JsonElementKt.unexpectedJson(key, "JsonPrimitive");
        throw null;
    }

    @Nullable
    public final JsonPrimitive getPrimitiveOrNull(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        JsonElement jsonElement = this.content.get(key);
        if (!(jsonElement instanceof JsonPrimitive)) {
            jsonElement = null;
        }
        return (JsonPrimitive) jsonElement;
    }

    public int getSize() {
        return this.content.size();
    }

    @NotNull
    public Collection<JsonElement> getValues() {
        return this.content.values();
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.content.hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.content.isEmpty();
    }

    @Override // java.util.Map
    public final /* bridge */ Set<String> keySet() {
        return getKeys();
    }

    @Override // java.util.Map
    public /* synthetic */ JsonElement merge(String str, JsonElement jsonElement, BiFunction<? super JsonElement, ? super JsonElement, ? extends JsonElement> biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: merge  reason: avoid collision after fix types in other method */
    public JsonElement merge2(String str, JsonElement jsonElement, BiFunction<? super JsonElement, ? super JsonElement, ? extends JsonElement> biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public /* synthetic */ JsonElement put(String str, JsonElement jsonElement) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: put  reason: avoid collision after fix types in other method */
    public JsonElement put2(String str, JsonElement jsonElement) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends JsonElement> map) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public /* synthetic */ JsonElement putIfAbsent(String str, JsonElement jsonElement) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: putIfAbsent  reason: avoid collision after fix types in other method */
    public JsonElement putIfAbsent2(String str, JsonElement jsonElement) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public JsonElement remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public boolean remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public /* synthetic */ JsonElement replace(String str, JsonElement jsonElement) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: replace  reason: avoid collision after fix types in other method */
    public JsonElement replace2(String str, JsonElement jsonElement) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public /* synthetic */ boolean replace(String str, JsonElement jsonElement, JsonElement jsonElement2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: replace  reason: avoid collision after fix types in other method */
    public boolean replace2(String str, JsonElement jsonElement, JsonElement jsonElement2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public void replaceAll(BiFunction<? super String, ? super JsonElement, ? extends JsonElement> biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @NotNull
    public String toString() {
        String joinToString$default;
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(this.content.entrySet(), ",", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE, 0, null, JsonObject$toString$1.INSTANCE, 24, null);
        return joinToString$default;
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<JsonElement> values() {
        return getValues();
    }
}
