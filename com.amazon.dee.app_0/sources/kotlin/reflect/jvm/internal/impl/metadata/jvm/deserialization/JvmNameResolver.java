package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: JvmNameResolver.kt */
/* loaded from: classes4.dex */
public final class JvmNameResolver implements NameResolver {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final List<String> PREDEFINED_STRINGS;
    private static final Map<String, Integer> PREDEFINED_STRINGS_MAP;
    private final Set<Integer> localNameIndices;
    @NotNull
    private final List<JvmProtoBuf.StringTableTypes.Record> records;
    @NotNull
    private final String[] strings;
    @NotNull
    private final JvmProtoBuf.StringTableTypes types;

    /* compiled from: JvmNameResolver.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[JvmProtoBuf.StringTableTypes.Record.Operation.values().length];

        static {
            $EnumSwitchMapping$0[JvmProtoBuf.StringTableTypes.Record.Operation.NONE.ordinal()] = 1;
            $EnumSwitchMapping$0[JvmProtoBuf.StringTableTypes.Record.Operation.INTERNAL_TO_CLASS_ID.ordinal()] = 2;
            $EnumSwitchMapping$0[JvmProtoBuf.StringTableTypes.Record.Operation.DESC_TO_CLASS_ID.ordinal()] = 3;
        }
    }

    static {
        List<String> listOf;
        Iterable<IndexedValue> withIndex;
        int collectionSizeOrDefault;
        int mapCapacity;
        int coerceAtLeast;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"kotlin/Any", "kotlin/Nothing", "kotlin/Unit", "kotlin/Throwable", "kotlin/Number", "kotlin/Byte", "kotlin/Double", "kotlin/Float", "kotlin/Int", "kotlin/Long", "kotlin/Short", "kotlin/Boolean", "kotlin/Char", "kotlin/CharSequence", "kotlin/String", "kotlin/Comparable", "kotlin/Enum", "kotlin/Array", "kotlin/ByteArray", "kotlin/DoubleArray", "kotlin/FloatArray", "kotlin/IntArray", "kotlin/LongArray", "kotlin/ShortArray", "kotlin/BooleanArray", "kotlin/CharArray", "kotlin/Cloneable", "kotlin/Annotation", "kotlin/collections/Iterable", "kotlin/collections/MutableIterable", "kotlin/collections/Collection", "kotlin/collections/MutableCollection", "kotlin/collections/List", "kotlin/collections/MutableList", "kotlin/collections/Set", "kotlin/collections/MutableSet", "kotlin/collections/Map", "kotlin/collections/MutableMap", "kotlin/collections/Map.Entry", "kotlin/collections/MutableMap.MutableEntry", "kotlin/collections/Iterator", "kotlin/collections/MutableIterator", "kotlin/collections/ListIterator", "kotlin/collections/MutableListIterator"});
        PREDEFINED_STRINGS = listOf;
        withIndex = CollectionsKt___CollectionsKt.withIndex(PREDEFINED_STRINGS);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(withIndex, 10);
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(collectionSizeOrDefault);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (IndexedValue indexedValue : withIndex) {
            linkedHashMap.put((String) indexedValue.getValue(), Integer.valueOf(indexedValue.getIndex()));
        }
        PREDEFINED_STRINGS_MAP = linkedHashMap;
    }

    public JvmNameResolver(@NotNull JvmProtoBuf.StringTableTypes types, @NotNull String[] strings) {
        Intrinsics.checkParameterIsNotNull(types, "types");
        Intrinsics.checkParameterIsNotNull(strings, "strings");
        this.types = types;
        this.strings = strings;
        List<Integer> localNameList = this.types.getLocalNameList();
        this.localNameIndices = localNameList.isEmpty() ? SetsKt__SetsKt.emptySet() : CollectionsKt___CollectionsKt.toSet(localNameList);
        ArrayList arrayList = new ArrayList();
        List<JvmProtoBuf.StringTableTypes.Record> recordList = this.types.getRecordList();
        arrayList.ensureCapacity(recordList.size());
        for (JvmProtoBuf.StringTableTypes.Record record : recordList) {
            Intrinsics.checkExpressionValueIsNotNull(record, "record");
            int range = record.getRange();
            for (int i = 0; i < range; i++) {
                arrayList.add(record);
            }
        }
        arrayList.trimToSize();
        this.records = arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    @NotNull
    public String getQualifiedClassName(int i) {
        return getString(i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    @NotNull
    public String getString(int i) {
        String string;
        JvmProtoBuf.StringTableTypes.Record record = this.records.get(i);
        if (record.hasString()) {
            string = record.getString();
        } else {
            if (record.hasPredefinedIndex()) {
                int size = PREDEFINED_STRINGS.size();
                int predefinedIndex = record.getPredefinedIndex();
                if (predefinedIndex >= 0 && size > predefinedIndex) {
                    string = PREDEFINED_STRINGS.get(record.getPredefinedIndex());
                }
            }
            string = this.strings[i];
        }
        if (record.getSubstringIndexCount() >= 2) {
            List<Integer> substringIndexList = record.getSubstringIndexList();
            Integer begin = substringIndexList.get(0);
            Integer end = substringIndexList.get(1);
            Intrinsics.checkExpressionValueIsNotNull(begin, "begin");
            if (Intrinsics.compare(0, begin.intValue()) <= 0) {
                int intValue = begin.intValue();
                Intrinsics.checkExpressionValueIsNotNull(end, "end");
                if (Intrinsics.compare(intValue, end.intValue()) <= 0 && Intrinsics.compare(end.intValue(), string.length()) <= 0) {
                    Intrinsics.checkExpressionValueIsNotNull(string, "string");
                    string = string.substring(begin.intValue(), end.intValue());
                    Intrinsics.checkExpressionValueIsNotNull(string, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                }
            }
        }
        String string2 = string;
        if (record.getReplaceCharCount() >= 2) {
            List<Integer> replaceCharList = record.getReplaceCharList();
            Intrinsics.checkExpressionValueIsNotNull(string2, "string");
            string2 = StringsKt__StringsJVMKt.replace$default(string2, (char) replaceCharList.get(0).intValue(), (char) replaceCharList.get(1).intValue(), false, 4, (Object) null);
        }
        String string3 = string2;
        JvmProtoBuf.StringTableTypes.Record.Operation operation = record.getOperation();
        if (operation == null) {
            operation = JvmProtoBuf.StringTableTypes.Record.Operation.NONE;
        }
        int i2 = WhenMappings.$EnumSwitchMapping$0[operation.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                Intrinsics.checkExpressionValueIsNotNull(string3, "string");
                string3 = StringsKt__StringsJVMKt.replace$default(string3, '$', '.', false, 4, (Object) null);
            } else if (i2 == 3) {
                if (string3.length() >= 2) {
                    Intrinsics.checkExpressionValueIsNotNull(string3, "string");
                    string3 = string3.substring(1, string3.length() - 1);
                    Intrinsics.checkExpressionValueIsNotNull(string3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                }
                String string4 = string3;
                Intrinsics.checkExpressionValueIsNotNull(string4, "string");
                string3 = StringsKt__StringsJVMKt.replace$default(string4, '$', '.', false, 4, (Object) null);
            }
        }
        Intrinsics.checkExpressionValueIsNotNull(string3, "string");
        return string3;
    }

    @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    public boolean isLocalClassName(int i) {
        return this.localNameIndices.contains(Integer.valueOf(i));
    }
}
