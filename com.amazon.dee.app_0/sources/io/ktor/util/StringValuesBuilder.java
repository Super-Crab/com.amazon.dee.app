package io.ktor.util;

import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.dee.app.metrics.MetricsConstants;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StringValues.kt */
@InternalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010#\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000fJ\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019J\u001c\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001aJ\u000e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019J\u001c\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001aJ\b\u0010\u001c\u001a\u00020\u0019H\u0016J\u0006\u0010\u001d\u001a\u00020\u0014J\u0011\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u000fH\u0086\u0002J\u0016\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000fJ\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00102\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u001e\u0010 \u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0#0\"0!J\u0013\u0010$\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\u000fH\u0086\u0002J\u0016\u0010%\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010#2\u0006\u0010\u0015\u001a\u00020\u000fJ\u0006\u0010&\u001a\u00020\u0003J\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000f0(J\u000e\u0010)\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000fJ\u0016\u0010)\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000fJ\u0006\u0010*\u001a\u00020\u0014J\u0019\u0010+\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000fH\u0086\u0002R\u001a\u0010\u0007\u001a\u00020\u0003X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR&\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00100\u000eX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006,"}, d2 = {"Lio/ktor/util/StringValuesBuilder;", "", "caseInsensitiveName", "", "size", "", "(ZI)V", "built", "getBuilt", "()Z", "setBuilt", "(Z)V", "getCaseInsensitiveName", "values", "", "", "", "getValues", "()Ljava/util/Map;", "append", "", "name", "value", "appendAll", "stringValues", "Lio/ktor/util/StringValues;", "", "appendMissing", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, MetricsConstants.Method.CACHE_CLEAR, "contains", "ensureListForKey", "entries", "", "", "", MetricsConstants.Method.CACHE_GET, "getAll", "isEmpty", "names", "", BulkOperationType.remove, "removeKeysWithNoEntries", "set", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public class StringValuesBuilder {
    private boolean built;
    private final boolean caseInsensitiveName;
    @NotNull
    private final Map<String, List<String>> values;

    public StringValuesBuilder() {
        this(false, 0, 3, null);
    }

    public StringValuesBuilder(boolean z, int i) {
        this.caseInsensitiveName = z;
        this.values = this.caseInsensitiveName ? CollectionsKt.caseInsensitiveMap() : new LinkedHashMap<>(i);
    }

    private final List<String> ensureListForKey(String str, int i) {
        if (!this.built) {
            List<String> list = this.values.get(str);
            if (list != null) {
                return list;
            }
            ArrayList arrayList = new ArrayList(i);
            this.values.put(str, arrayList);
            return arrayList;
        }
        throw new IllegalStateException("Cannot modify a builder when final structure has already been built");
    }

    public final void append(@NotNull String name, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        ensureListForKey(name, 1).add(value);
    }

    public final void appendAll(@NotNull StringValues stringValues) {
        Intrinsics.checkParameterIsNotNull(stringValues, "stringValues");
        stringValues.forEach(new StringValuesBuilder$appendAll$1(this));
    }

    public final void appendMissing(@NotNull StringValues stringValues) {
        Intrinsics.checkParameterIsNotNull(stringValues, "stringValues");
        stringValues.forEach(new StringValuesBuilder$appendMissing$1(this));
    }

    @NotNull
    /* renamed from: build */
    public StringValues mo10292build() {
        if (!this.built) {
            this.built = true;
            return new StringValuesImpl(this.caseInsensitiveName, this.values);
        }
        throw new IllegalArgumentException("ValueMapBuilder can only build a single ValueMap".toString());
    }

    public final void clear() {
        this.values.clear();
    }

    public final boolean contains(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return this.values.containsKey(name);
    }

    @NotNull
    public final Set<Map.Entry<String, List<String>>> entries() {
        return CollectionsJvmKt.unmodifiable(this.values.entrySet());
    }

    @Nullable
    public final String get(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        List<String> all = getAll(name);
        if (all != null) {
            return (String) kotlin.collections.CollectionsKt.firstOrNull((List<? extends Object>) all);
        }
        return null;
    }

    @Nullable
    public final List<String> getAll(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return this.values.get(name);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean getBuilt() {
        return this.built;
    }

    public final boolean getCaseInsensitiveName() {
        return this.caseInsensitiveName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final Map<String, List<String>> getValues() {
        return this.values;
    }

    public final boolean isEmpty() {
        return this.values.isEmpty();
    }

    @NotNull
    public final Set<String> names() {
        return this.values.keySet();
    }

    public final void remove(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.values.remove(name);
    }

    public final void removeKeysWithNoEntries() {
        Map<String, List<String>> map = this.values;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getValue().isEmpty()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            remove((String) entry2.getKey());
        }
    }

    public final void set(@NotNull String name, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        List<String> ensureListForKey = ensureListForKey(name, 1);
        ensureListForKey.clear();
        ensureListForKey.add(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setBuilt(boolean z) {
        this.built = z;
    }

    public final void appendAll(@NotNull String name, @NotNull Iterable<String> values) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(values, "values");
        Collection collection = (Collection) (!(values instanceof Collection) ? null : values);
        CollectionsKt__MutableCollectionsKt.addAll(ensureListForKey(name, collection != null ? collection.size() : 2), values);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0014, code lost:
        r0 = kotlin.collections.CollectionsKt___CollectionsKt.toSet(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void appendMissing(@org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull java.lang.Iterable<java.lang.String> r6) {
        /*
            r4 = this;
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "values"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r0 = r4.values
            java.lang.Object r0 = r0.get(r5)
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L1b
            java.util.Set r0 = kotlin.collections.CollectionsKt.toSet(r0)
            if (r0 == 0) goto L1b
            goto L1f
        L1b:
            java.util.Set r0 = kotlin.collections.SetsKt.emptySet()
        L1f:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r6 = r6.iterator()
        L28:
            boolean r2 = r6.hasNext()
            if (r2 == 0) goto L41
            java.lang.Object r2 = r6.next()
            r3 = r2
            java.lang.String r3 = (java.lang.String) r3
            boolean r3 = r0.contains(r3)
            r3 = r3 ^ 1
            if (r3 == 0) goto L28
            r1.add(r2)
            goto L28
        L41:
            r4.appendAll(r5, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.StringValuesBuilder.appendMissing(java.lang.String, java.lang.Iterable):void");
    }

    public final boolean contains(@NotNull String name, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        List<String> list = this.values.get(name);
        if (list != null) {
            return list.contains(value);
        }
        return false;
    }

    public final boolean remove(@NotNull String name, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        List<String> list = this.values.get(name);
        if (list != null) {
            return list.remove(value);
        }
        return false;
    }

    public /* synthetic */ StringValuesBuilder(boolean z, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? 8 : i);
    }
}
