package io.ktor.http;

import com.amazon.alexa.mobilytics.configuration.Config;
import io.ktor.http.ContentRange;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Ranges.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000\u001a \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\t\u001a\u00020\nH\u0000¨\u0006\u000b"}, d2 = {"parseRangesSpecifier", "Lio/ktor/http/RangesSpecifier;", "rangeSpec", "", "mergeRangesKeepOrder", "", "Lkotlin/ranges/LongRange;", "toLongRanges", "Lio/ktor/http/ContentRange;", "contentLength", "", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class RangesKt {
    @NotNull
    public static final List<LongRange> mergeRangesKeepOrder(@NotNull List<LongRange> receiver$0) {
        List<LongRange> sortedWith;
        List<LongRange> filterNotNull;
        int lastIndex;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        sortedWith = CollectionsKt___CollectionsKt.sortedWith(receiver$0, new Comparator<T>() { // from class: io.ktor.http.RangesKt$mergeRangesKeepOrder$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int compareValues;
                compareValues = ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((LongRange) t).mo11373getStart().longValue()), Long.valueOf(((LongRange) t2).mo11373getStart().longValue()));
                return compareValues;
            }
        });
        ArrayList arrayList = new ArrayList(receiver$0.size());
        for (LongRange longRange : sortedWith) {
            if (arrayList.isEmpty()) {
                arrayList.add(longRange);
            } else if (((LongRange) CollectionsKt.last((List<? extends Object>) arrayList)).mo11372getEndInclusive().longValue() < longRange.mo11373getStart().longValue() - 1) {
                arrayList.add(longRange);
            } else {
                LongRange longRange2 = (LongRange) CollectionsKt.last((List<? extends Object>) arrayList);
                lastIndex = CollectionsKt__CollectionsKt.getLastIndex(arrayList);
                arrayList.set(lastIndex, new LongRange(longRange2.mo11373getStart().longValue(), Math.max(longRange2.mo11372getEndInclusive().longValue(), longRange.mo11372getEndInclusive().longValue())));
            }
        }
        LongRange[] longRangeArr = new LongRange[receiver$0.size()];
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            LongRange range = (LongRange) it2.next();
            int i = 0;
            int size = receiver$0.size();
            while (true) {
                if (i < size) {
                    Intrinsics.checkExpressionValueIsNotNull(range, "range");
                    if (io.ktor.util.RangesKt.contains(range, receiver$0.get(i))) {
                        longRangeArr[i] = range;
                        break;
                    }
                    i++;
                }
            }
        }
        filterNotNull = ArraysKt___ArraysKt.filterNotNull(longRangeArr);
        return filterNotNull;
    }

    @Nullable
    public static final RangesSpecifier parseRangesSpecifier(@NotNull String rangeSpec) {
        int indexOf$default;
        List<String> split$default;
        int collectionSizeOrDefault;
        boolean startsWith$default;
        int indexOf$default2;
        Pair pair;
        ContentRange bounded;
        String removePrefix;
        Intrinsics.checkParameterIsNotNull(rangeSpec, "rangeSpec");
        try {
            indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) rangeSpec, Config.Compare.EQUAL_TO, 0, false, 6, (Object) null);
            if (indexOf$default != -1) {
                boolean z = false;
                String substring = rangeSpec.substring(0, indexOf$default);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                String substring2 = rangeSpec.substring(indexOf$default + 1);
                Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
                Pair pair2 = TuplesKt.to(substring, substring2);
                String str = (String) pair2.component1();
                split$default = StringsKt__StringsKt.split$default((CharSequence) ((String) pair2.component2()), new char[]{JsonReaderKt.COMMA}, false, 0, 6, (Object) null);
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(split$default, 10);
                ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                for (String str2 : split$default) {
                    startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str2, ProcessIdUtil.DEFAULT_PROCESSID, false, 2, null);
                    if (startsWith$default) {
                        removePrefix = StringsKt__StringsKt.removePrefix(str2, (CharSequence) ProcessIdUtil.DEFAULT_PROCESSID);
                        bounded = new ContentRange.Suffix(Long.parseLong(removePrefix));
                    } else {
                        indexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) str2, ProcessIdUtil.DEFAULT_PROCESSID, 0, false, 6, (Object) null);
                        if (indexOf$default2 != -1) {
                            String substring3 = str2.substring(0, indexOf$default2);
                            Intrinsics.checkExpressionValueIsNotNull(substring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            String substring4 = str2.substring(indexOf$default2 + 1);
                            Intrinsics.checkExpressionValueIsNotNull(substring4, "(this as java.lang.String).substring(startIndex)");
                            pair = TuplesKt.to(substring3, substring4);
                        } else {
                            pair = TuplesKt.to("", "");
                        }
                        String str3 = (String) pair.component1();
                        String str4 = (String) pair.component2();
                        bounded = str4.length() > 0 ? new ContentRange.Bounded(Long.parseLong(str3), Long.parseLong(str4)) : new ContentRange.TailFrom(Long.parseLong(str3));
                    }
                    arrayList.add(bounded);
                }
                if (!arrayList.isEmpty()) {
                    if (str.length() == 0) {
                        z = true;
                    }
                    if (!z) {
                        RangesSpecifier rangesSpecifier = new RangesSpecifier(str, arrayList);
                        if (!RangesSpecifier.isValid$default(rangesSpecifier, null, 1, null)) {
                            return null;
                        }
                        return rangesSpecifier;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    @NotNull
    public static final List<LongRange> toLongRanges(@NotNull List<? extends ContentRange> receiver$0, long j) {
        int collectionSizeOrDefault;
        long coerceAtLeast;
        LongRange until;
        long coerceAtMost;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(receiver$0, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (ContentRange contentRange : receiver$0) {
            if (contentRange instanceof ContentRange.Bounded) {
                ContentRange.Bounded bounded = (ContentRange.Bounded) contentRange;
                long from = bounded.getFrom();
                coerceAtMost = RangesKt___RangesKt.coerceAtMost(bounded.getTo(), j - 1);
                until = new LongRange(from, coerceAtMost);
            } else if (contentRange instanceof ContentRange.TailFrom) {
                until = RangesKt___RangesKt.until(((ContentRange.TailFrom) contentRange).getFrom(), j);
            } else if (!(contentRange instanceof ContentRange.Suffix)) {
                throw new NoWhenBranchMatchedException();
            } else {
                coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(j - ((ContentRange.Suffix) contentRange).getLastCount(), 0L);
                until = RangesKt___RangesKt.until(coerceAtLeast, j);
            }
            arrayList.add(until);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (!((LongRange) obj).isEmpty()) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }
}
