package kotlinx.serialization.json;

import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.dee.app.metrics.MetricsConstants;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JsonElement.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010*\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u0000 42\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002:\u00014B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0002¢\u0006\u0002\u0010\u0004J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u0002HÆ\u0003J\u0011\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0001H\u0096\u0003J\u0017\u0010\u0012\u001a\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0014H\u0096\u0001J\u0019\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0002HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\u0011\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u000bH\u0096\u0003J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u000bJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u001a\u001a\u00020\u000bJ\"\u0010\u001d\u001a\u0002H\u001e\"\n\b\u0000\u0010\u001e\u0018\u0001*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u000bH\u0086\b¢\u0006\u0002\u0010\u001fJ$\u0010 \u001a\u0004\u0018\u0001H\u001e\"\n\b\u0000\u0010\u001e\u0018\u0001*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u000bH\u0086\b¢\u0006\u0002\u0010\u001fJ\u000e\u0010!\u001a\u00020\"2\u0006\u0010\u001a\u001a\u00020\u000bJ\u0010\u0010#\u001a\u0004\u0018\u00010\"2\u0006\u0010\u001a\u001a\u00020\u000bJ\u000e\u0010$\u001a\u00020%2\u0006\u0010\u001a\u001a\u00020\u000bJ\u0010\u0010&\u001a\u0004\u0018\u00010%2\u0006\u0010\u001a\u001a\u00020\u000bJ\b\u0010'\u001a\u00020\u000bH\u0016J\u0011\u0010(\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0001H\u0096\u0001J\t\u0010)\u001a\u00020\u0010H\u0096\u0001J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00010+H\u0096\u0003J\u0011\u0010,\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0001H\u0096\u0001J\u000f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00010.H\u0096\u0001J\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00010.2\u0006\u0010\u001a\u001a\u00020\u000bH\u0096\u0001J\u001f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00010\u00022\u0006\u00100\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u000bH\u0096\u0001J\b\u00102\u001a\u000203H\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u00065"}, d2 = {"Lkotlinx/serialization/json/JsonArray;", "Lkotlinx/serialization/json/JsonElement;", "", "content", "(Ljava/util/List;)V", "getContent", "()Ljava/util/List;", "jsonArray", "getJsonArray", "()Lkotlinx/serialization/json/JsonArray;", "size", "", "getSize", "()I", "component1", "contains", "", "element", "containsAll", AlexaMetricsConstants.MetricsComponents.ELEMENTS, "", "copy", "equals", "other", "", MetricsConstants.Method.CACHE_GET, "index", "getArray", "getArrayOrNull", "getAs", "J", "(I)Lkotlinx/serialization/json/JsonElement;", "getAsOrNull", "getObject", "Lkotlinx/serialization/json/JsonObject;", "getObjectOrNull", "getPrimitive", "Lkotlinx/serialization/json/JsonPrimitive;", "getPrimitiveOrNull", "hashCode", "indexOf", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "subList", "fromIndex", "toIndex", "toString", "", "Companion", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@Serializable(with = JsonArraySerializer.class)
/* loaded from: classes4.dex */
public final class JsonArray extends JsonElement implements List<JsonElement>, KMappedMarker {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final List<JsonElement> content;
    @NotNull
    private final JsonArray jsonArray;

    /* compiled from: JsonElement.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lkotlinx/serialization/json/JsonArray$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/serialization/json/JsonArray;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<JsonArray> serializer() {
            return JsonArraySerializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public JsonArray(@NotNull List<? extends JsonElement> content) {
        super(null);
        Intrinsics.checkParameterIsNotNull(content, "content");
        this.content = content;
        this.jsonArray = this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ JsonArray copy$default(JsonArray jsonArray, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = jsonArray.content;
        }
        return jsonArray.copy(list);
    }

    @Override // java.util.List
    public /* synthetic */ void add(int i, JsonElement jsonElement) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: add  reason: avoid collision after fix types in other method */
    public void add2(int i, JsonElement jsonElement) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean add(JsonElement jsonElement) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends JsonElement> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends JsonElement> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    public final List<JsonElement> component1() {
        return this.content;
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof JsonElement) {
            return contains((JsonElement) obj);
        }
        return false;
    }

    public boolean contains(@NotNull JsonElement element) {
        Intrinsics.checkParameterIsNotNull(element, "element");
        return this.content.contains(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(@NotNull Collection<? extends Object> elements) {
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return this.content.containsAll(elements);
    }

    @NotNull
    public final JsonArray copy(@NotNull List<? extends JsonElement> content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        return new JsonArray(content);
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(@Nullable Object obj) {
        return Intrinsics.areEqual(this.content, obj);
    }

    @Override // java.util.List
    @NotNull
    public JsonElement get(int i) {
        JsonElement jsonElement = this.content.get(i);
        Intrinsics.checkExpressionValueIsNotNull(jsonElement, "get(...)");
        return jsonElement;
    }

    @NotNull
    public final JsonArray getArray(int i) {
        JsonElement jsonElement = this.content.get(i);
        if (!(jsonElement instanceof JsonArray)) {
            jsonElement = null;
        }
        JsonArray jsonArray = (JsonArray) jsonElement;
        if (jsonArray != null) {
            return jsonArray;
        }
        JsonElementKt.unexpectedJson("at " + i, "JsonArray");
        throw null;
    }

    @Nullable
    public final JsonArray getArrayOrNull(int i) {
        Object orNull = CollectionsKt.getOrNull(this.content, i);
        if (!(orNull instanceof JsonArray)) {
            orNull = null;
        }
        return (JsonArray) orNull;
    }

    @NotNull
    public final /* synthetic */ <J extends JsonElement> J getAs(int i) {
        JsonElement jsonElement = getContent().get(i);
        Intrinsics.reifiedOperationMarker(2, "J");
        J j = (J) jsonElement;
        if (j != null) {
            return j;
        }
        Intrinsics.reifiedOperationMarker(4, "J");
        JsonElementKt.unexpectedJson("at " + i, Reflection.getOrCreateKotlinClass(JsonElement.class).toString());
        throw null;
    }

    @Nullable
    public final /* synthetic */ <J extends JsonElement> J getAsOrNull(int i) {
        Object orNull = CollectionsKt.getOrNull(getContent(), i);
        Intrinsics.reifiedOperationMarker(2, "J");
        return (J) orNull;
    }

    @NotNull
    public final List<JsonElement> getContent() {
        return this.content;
    }

    @Override // kotlinx.serialization.json.JsonElement
    @NotNull
    public JsonArray getJsonArray() {
        return this.jsonArray;
    }

    @NotNull
    public final JsonObject getObject(int i) {
        JsonElement jsonElement = this.content.get(i);
        if (!(jsonElement instanceof JsonObject)) {
            jsonElement = null;
        }
        JsonObject jsonObject = (JsonObject) jsonElement;
        if (jsonObject != null) {
            return jsonObject;
        }
        JsonElementKt.unexpectedJson("at " + i, "JsonObject");
        throw null;
    }

    @Nullable
    public final JsonObject getObjectOrNull(int i) {
        Object orNull = CollectionsKt.getOrNull(this.content, i);
        if (!(orNull instanceof JsonObject)) {
            orNull = null;
        }
        return (JsonObject) orNull;
    }

    @NotNull
    public final JsonPrimitive getPrimitive(int i) {
        JsonElement jsonElement = this.content.get(i);
        if (!(jsonElement instanceof JsonPrimitive)) {
            jsonElement = null;
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElement;
        if (jsonPrimitive != null) {
            return jsonPrimitive;
        }
        JsonElementKt.unexpectedJson("at " + i, "JsonPrimitive");
        throw null;
    }

    @Nullable
    public final JsonPrimitive getPrimitiveOrNull(int i) {
        Object orNull = CollectionsKt.getOrNull(this.content, i);
        if (!(orNull instanceof JsonPrimitive)) {
            orNull = null;
        }
        return (JsonPrimitive) orNull;
    }

    public int getSize() {
        return this.content.size();
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return this.content.hashCode();
    }

    @Override // java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof JsonElement) {
            return indexOf((JsonElement) obj);
        }
        return -1;
    }

    public int indexOf(@NotNull JsonElement element) {
        Intrinsics.checkParameterIsNotNull(element, "element");
        return this.content.indexOf(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.content.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    @NotNull
    public Iterator<JsonElement> iterator() {
        return this.content.iterator();
    }

    @Override // java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof JsonElement) {
            return lastIndexOf((JsonElement) obj);
        }
        return -1;
    }

    public int lastIndexOf(@NotNull JsonElement element) {
        Intrinsics.checkParameterIsNotNull(element, "element");
        return this.content.lastIndexOf(element);
    }

    @Override // java.util.List
    @NotNull
    public ListIterator<JsonElement> listIterator() {
        return this.content.listIterator();
    }

    @Override // java.util.List
    @NotNull
    public ListIterator<JsonElement> listIterator(int i) {
        return this.content.listIterator(i);
    }

    @Override // java.util.List
    public /* synthetic */ JsonElement remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    /* renamed from: remove  reason: avoid collision after fix types in other method */
    public JsonElement remove2(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public void replaceAll(UnaryOperator<JsonElement> unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public /* synthetic */ JsonElement set(int i, JsonElement jsonElement) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: set  reason: avoid collision after fix types in other method */
    public JsonElement set2(int i, JsonElement jsonElement) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.List
    public void sort(Comparator<? super JsonElement> comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    @NotNull
    public List<JsonElement> subList(int i, int i2) {
        return this.content.subList(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    @NotNull
    public String toString() {
        String joinToString$default;
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(this.content, ",", "[", "]", 0, null, null, 56, null);
        return joinToString$default;
    }
}
