package io.ktor.util;

import com.amazon.alexa.auth.BuildConfig;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: StringValues.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aH\u0010\u0000\u001a\u00020\u00012\u001e\u0010\u0002\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00040\u00032\u001e\u0010\u0007\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00040\u0003H\u0002\u001a0\u0010\b\u001a\u00020\t2\u001e\u0010\n\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00040\u00032\u0006\u0010\u000b\u001a\u00020\tH\u0002\u001a\u0006\u0010\f\u001a\u00020\r\u001aM\u0010\f\u001a\u00020\r26\u0010\u000e\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00100\u000f\"\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0001¢\u0006\u0002\u0010\u0012\u001a \u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0001\u001a&\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00052\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u0001\u001a*\u0010\f\u001a\u00020\r2\u0018\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00180\u00172\b\b\u0002\u0010\u0011\u001a\u00020\u0001\u001a\u0012\u0010\u0019\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a\u001a6\u0010\u001c\u001a\u00020\u001d*\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\u00012\u0018\u0010 \u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010!\u001a.\u0010\"\u001a\u00020\r*\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\u00012\u0018\u0010 \u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010!\u001a\u001c\u0010#\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00100\u0006*\u00020\r\u001a$\u0010$\u001a\u00020\u001d*\u00020\r2\u0018\u0010%\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001d0!\u001a\u001c\u0010&\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u0017*\u00020\r¨\u0006'"}, d2 = {"entriesEquals", "", "a", "", "", "", "", "b", "entriesHashCode", "", "entries", "seed", "valuesOf", "Lio/ktor/util/StringValues;", "pairs", "", "Lkotlin/Pair;", "caseInsensitiveKey", "([Lkotlin/Pair;Z)Lio/ktor/util/StringValues;", "name", "value", "values", BuildConfig.FLAVOR_authtype, "", "", "appendAll", "Lio/ktor/util/StringValuesBuilder;", "builder", "appendFiltered", "", "source", "keepEmpty", "predicate", "Lkotlin/Function2;", MessagingControllerConstant.MESSAGING_CONTROLLER_FILTER_KEY, "flattenEntries", "flattenForEach", "block", "toMap", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class StringValuesKt {
    @NotNull
    public static final StringValuesBuilder appendAll(@NotNull StringValuesBuilder receiver$0, @NotNull StringValuesBuilder builder) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        Iterator<T> it2 = builder.entries().iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            receiver$0.appendAll((String) entry.getKey(), (List) entry.getValue());
        }
        return receiver$0;
    }

    public static final void appendFiltered(@NotNull StringValuesBuilder receiver$0, @NotNull StringValues source, boolean z, @NotNull Function2<? super String, ? super String, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(source, "source");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        source.forEach(new StringValuesKt$appendFiltered$1(receiver$0, predicate, z));
    }

    public static /* synthetic */ void appendFiltered$default(StringValuesBuilder stringValuesBuilder, StringValues stringValues, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        appendFiltered(stringValuesBuilder, stringValues, z, function2);
    }

    public static final boolean entriesEquals(Set<? extends Map.Entry<String, ? extends List<String>>> set, Set<? extends Map.Entry<String, ? extends List<String>>> set2) {
        return Intrinsics.areEqual(set, set2);
    }

    public static final int entriesHashCode(Set<? extends Map.Entry<String, ? extends List<String>>> set, int i) {
        return set.hashCode() + (i * 31);
    }

    @NotNull
    public static final StringValues filter(@NotNull StringValues receiver$0, boolean z, @NotNull Function2<? super String, ? super String, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        Set<Map.Entry<String, List<String>>> entries = receiver$0.entries();
        Map caseInsensitiveMap = receiver$0.getCaseInsensitiveName() ? CollectionsKt.caseInsensitiveMap() : new LinkedHashMap(entries.size());
        Iterator<T> it2 = entries.iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            ArrayList arrayList = new ArrayList(((List) entry.getValue()).size());
            for (Object obj : (Iterable) entry.getValue()) {
                if (predicate.mo12248invoke(entry.getKey(), (String) obj).booleanValue()) {
                    arrayList.add(obj);
                }
            }
            if (z || (!arrayList.isEmpty())) {
                caseInsensitiveMap.put(entry.getKey(), arrayList);
            }
        }
        return new StringValuesImpl(receiver$0.getCaseInsensitiveName(), caseInsensitiveMap);
    }

    @NotNull
    public static /* synthetic */ StringValues filter$default(StringValues stringValues, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return filter(stringValues, z, function2);
    }

    @NotNull
    public static final List<Pair<String, String>> flattenEntries(@NotNull StringValues receiver$0) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Set<Map.Entry<String, List<String>>> entries = receiver$0.entries();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it2 = entries.iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            Iterable<String> iterable = (Iterable) entry.getValue();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
            for (String str : iterable) {
                arrayList2.add(TuplesKt.to(entry.getKey(), str));
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, arrayList2);
        }
        return arrayList;
    }

    public static final void flattenForEach(@NotNull StringValues receiver$0, @NotNull Function2<? super String, ? super String, Unit> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        receiver$0.forEach(new StringValuesKt$flattenForEach$1(block));
    }

    @NotNull
    public static final Map<String, List<String>> toMap(@NotNull StringValues receiver$0) {
        List list;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Set<Map.Entry<String, List<String>>> entries = receiver$0.entries();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> it2 = entries.iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            list = CollectionsKt___CollectionsKt.toList((Iterable) entry.getValue());
            linkedHashMap.put((String) entry.getKey(), list);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final StringValues valuesOf(@NotNull Pair<String, ? extends List<String>>[] pairs, boolean z) {
        List asList;
        Map map;
        Intrinsics.checkParameterIsNotNull(pairs, "pairs");
        asList = ArraysKt___ArraysJvmKt.asList(pairs);
        map = MapsKt__MapsKt.toMap(asList);
        return new StringValuesImpl(z, map);
    }

    @NotNull
    public static /* synthetic */ StringValues valuesOf$default(Pair[] pairArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return valuesOf(pairArr, z);
    }

    @NotNull
    public static final StringValues valuesOf(@NotNull String name, @NotNull String value, boolean z) {
        List listOf;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        listOf = CollectionsKt__CollectionsJVMKt.listOf(value);
        return new StringValuesSingleImpl(z, name, listOf);
    }

    @NotNull
    public static /* synthetic */ StringValues valuesOf$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return valuesOf(str, str2, z);
    }

    @NotNull
    public static final StringValues valuesOf(@NotNull String name, @NotNull List<String> values, boolean z) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(values, "values");
        return new StringValuesSingleImpl(z, name, values);
    }

    @NotNull
    public static /* synthetic */ StringValues valuesOf$default(String str, List list, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return valuesOf(str, list, z);
    }

    @NotNull
    public static final StringValues valuesOf() {
        return StringValues.Companion.getEmpty();
    }

    @NotNull
    public static /* synthetic */ StringValues valuesOf$default(Map map, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return valuesOf(map, z);
    }

    @NotNull
    public static final StringValues valuesOf(@NotNull Map<String, ? extends Iterable<String>> map, boolean z) {
        List list;
        List list2;
        Intrinsics.checkParameterIsNotNull(map, "map");
        int size = map.size();
        if (size == 1) {
            Map.Entry entry = (Map.Entry) kotlin.collections.CollectionsKt.single(map.entrySet());
            list2 = CollectionsKt___CollectionsKt.toList((Iterable) entry.getValue());
            return new StringValuesSingleImpl(z, (String) entry.getKey(), list2);
        }
        Map caseInsensitiveMap = z ? CollectionsKt.caseInsensitiveMap() : new LinkedHashMap(size);
        Iterator<T> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it2.next();
            Object key = entry2.getKey();
            list = CollectionsKt___CollectionsKt.toList((Iterable) entry2.getValue());
            caseInsensitiveMap.put(key, list);
        }
        return new StringValuesImpl(z, caseInsensitiveMap);
    }
}
