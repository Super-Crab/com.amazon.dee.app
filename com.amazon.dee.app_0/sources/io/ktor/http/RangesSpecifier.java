package io.ktor.http;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.util.KtorExperimentalAPI;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: RangesSpecifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007B\u001d\u0012\b\b\u0002\u0010\u0002\u001a\u00020\b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\bHÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\b2\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\u001e\u0010\u0016\u001a\u00020\u00122\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u0018H\u0007J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00052\u0006\u0010\u001b\u001a\u00020\u001cJ\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00052\u0006\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u0015J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\b\u0010\u001f\u001a\u00020\bH\u0016J\u001f\u0010 \u001a\b\u0012\u0004\u0012\u0002H!0\u0005\"\u0004\b\u0000\u0010!*\u0004\u0018\u0001H!H\u0002¢\u0006\u0002\u0010\"R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006#"}, d2 = {"Lio/ktor/http/RangesSpecifier;", "", "unit", "Lio/ktor/http/RangeUnits;", "ranges", "", "Lio/ktor/http/ContentRange;", "(Lio/ktor/http/RangeUnits;Ljava/util/List;)V", "", "(Ljava/lang/String;Ljava/util/List;)V", "getRanges", "()Ljava/util/List;", "getUnit", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "isValid", "rangeUnitPredicate", "Lkotlin/Function1;", "merge", "Lkotlin/ranges/LongRange;", "length", "", "maxRangeCount", "mergeToSingle", "toString", "toList", ExifInterface.GPS_DIRECTION_TRUE, "(Ljava/lang/Object;)Ljava/util/List;", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class RangesSpecifier {
    @NotNull
    private final List<ContentRange> ranges;
    @NotNull
    private final String unit;

    /* JADX WARN: Multi-variable type inference failed */
    public RangesSpecifier(@NotNull String unit, @NotNull List<? extends ContentRange> ranges) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        Intrinsics.checkParameterIsNotNull(ranges, "ranges");
        this.unit = unit;
        this.ranges = ranges;
        if (!this.ranges.isEmpty()) {
            return;
        }
        throw new IllegalArgumentException("It should be at least one range".toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* synthetic */ RangesSpecifier copy$default(RangesSpecifier rangesSpecifier, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rangesSpecifier.unit;
        }
        if ((i & 2) != 0) {
            list = rangesSpecifier.ranges;
        }
        return rangesSpecifier.copy(str, list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @KtorExperimentalAPI
    public static /* synthetic */ boolean isValid$default(RangesSpecifier rangesSpecifier, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = RangesSpecifier$isValid$1.INSTANCE;
        }
        return rangesSpecifier.isValid(function1);
    }

    @NotNull
    public static /* synthetic */ List merge$default(RangesSpecifier rangesSpecifier, long j, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 50;
        }
        return rangesSpecifier.merge(j, i);
    }

    private final <T> List<T> toList(@Nullable T t) {
        List<T> listOf;
        List<T> emptyList;
        if (t == null) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        listOf = CollectionsKt__CollectionsJVMKt.listOf(t);
        return listOf;
    }

    @NotNull
    public final String component1() {
        return this.unit;
    }

    @NotNull
    public final List<ContentRange> component2() {
        return this.ranges;
    }

    @NotNull
    public final RangesSpecifier copy(@NotNull String unit, @NotNull List<? extends ContentRange> ranges) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        Intrinsics.checkParameterIsNotNull(ranges, "ranges");
        return new RangesSpecifier(unit, ranges);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof RangesSpecifier)) {
                return false;
            }
            RangesSpecifier rangesSpecifier = (RangesSpecifier) obj;
            return Intrinsics.areEqual(this.unit, rangesSpecifier.unit) && Intrinsics.areEqual(this.ranges, rangesSpecifier.ranges);
        }
        return true;
    }

    @NotNull
    public final List<ContentRange> getRanges() {
        return this.ranges;
    }

    @NotNull
    public final String getUnit() {
        return this.unit;
    }

    public int hashCode() {
        String str = this.unit;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List<ContentRange> list = this.ranges;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x004d, code lost:
        if (r2.getTo() >= r2.getFrom()) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x005d, code lost:
        if (((io.ktor.http.ContentRange.TailFrom) r2).getFrom() < 0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0060, code lost:
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006e, code lost:
        if (((io.ktor.http.ContentRange.Suffix) r2).getLastCount() < 0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0071, code lost:
        continue;
     */
    @io.ktor.util.KtorExperimentalAPI
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isValid(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.Boolean> r9) {
        /*
            r8 = this;
            java.lang.String r0 = "rangeUnitPredicate"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            java.lang.String r0 = r8.unit
            java.lang.Object r9 = r9.mo12165invoke(r0)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            r0 = 0
            r1 = 1
            if (r9 == 0) goto L7e
            java.util.List<io.ktor.http.ContentRange> r9 = r8.ranges
            boolean r2 = r9 instanceof java.util.Collection
            if (r2 == 0) goto L23
            boolean r2 = r9.isEmpty()
            if (r2 == 0) goto L23
        L21:
            r9 = r1
            goto L7b
        L23:
            java.util.Iterator r9 = r9.iterator()
        L27:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L21
            java.lang.Object r2 = r9.next()
            io.ktor.http.ContentRange r2 = (io.ktor.http.ContentRange) r2
            boolean r3 = r2 instanceof io.ktor.http.ContentRange.Bounded
            r4 = 0
            if (r3 == 0) goto L51
            io.ktor.http.ContentRange$Bounded r2 = (io.ktor.http.ContentRange.Bounded) r2
            long r6 = r2.getFrom()
            int r3 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r3 < 0) goto L4f
            long r3 = r2.getTo()
            long r5 = r2.getFrom()
            int r2 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r2 >= 0) goto L60
        L4f:
            r2 = r1
            goto L71
        L51:
            boolean r3 = r2 instanceof io.ktor.http.ContentRange.TailFrom
            if (r3 == 0) goto L62
            io.ktor.http.ContentRange$TailFrom r2 = (io.ktor.http.ContentRange.TailFrom) r2
            long r2 = r2.getFrom()
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L60
            goto L4f
        L60:
            r2 = r0
            goto L71
        L62:
            boolean r3 = r2 instanceof io.ktor.http.ContentRange.Suffix
            if (r3 == 0) goto L75
            io.ktor.http.ContentRange$Suffix r2 = (io.ktor.http.ContentRange.Suffix) r2
            long r2 = r2.getLastCount()
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L60
            goto L4f
        L71:
            if (r2 == 0) goto L27
            r9 = r0
            goto L7b
        L75:
            kotlin.NoWhenBranchMatchedException r9 = new kotlin.NoWhenBranchMatchedException
            r9.<init>()
            throw r9
        L7b:
            if (r9 == 0) goto L7e
            r0 = r1
        L7e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.RangesSpecifier.isValid(kotlin.jvm.functions.Function1):boolean");
    }

    @NotNull
    public final List<LongRange> merge(long j, int i) {
        if (this.ranges.size() > i) {
            return toList(mergeToSingle(j));
        }
        return merge(j);
    }

    @Nullable
    public final LongRange mergeToSingle(long j) {
        Object next;
        long coerceAtMost;
        List<LongRange> longRanges = RangesKt.toLongRanges(this.ranges, j);
        Object obj = null;
        if (longRanges.isEmpty()) {
            return null;
        }
        Iterator<T> it2 = longRanges.iterator();
        if (!it2.hasNext()) {
            next = null;
        } else {
            next = it2.next();
            long longValue = ((LongRange) next).mo11373getStart().longValue();
            while (it2.hasNext()) {
                Object next2 = it2.next();
                long longValue2 = ((LongRange) next2).mo11373getStart().longValue();
                if (longValue > longValue2) {
                    next = next2;
                    longValue = longValue2;
                }
            }
        }
        if (next == null) {
            Intrinsics.throwNpe();
        }
        long longValue3 = ((LongRange) next).mo11373getStart().longValue();
        Iterator<T> it3 = longRanges.iterator();
        if (it3.hasNext()) {
            Object next3 = it3.next();
            long longValue4 = ((LongRange) next3).mo11372getEndInclusive().longValue();
            obj = next3;
            while (it3.hasNext()) {
                Object next4 = it3.next();
                long longValue5 = ((LongRange) next4).mo11372getEndInclusive().longValue();
                if (longValue4 < longValue5) {
                    obj = next4;
                    longValue4 = longValue5;
                }
            }
        }
        if (obj == null) {
            Intrinsics.throwNpe();
        }
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(((LongRange) obj).mo11372getEndInclusive().longValue(), j - 1);
        return new LongRange(longValue3, coerceAtMost);
    }

    @NotNull
    public String toString() {
        String joinToString$default;
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(this.ranges, ",", GeneratedOutlineSupport1.outline91(new StringBuilder(), this.unit, Config.Compare.EQUAL_TO), null, 0, null, null, 60, null);
        return joinToString$default;
    }

    public /* synthetic */ RangesSpecifier(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? RangeUnits.Bytes.getUnitToken() : str, list);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RangesSpecifier(@NotNull RangeUnits unit, @NotNull List<? extends ContentRange> ranges) {
        this(unit.getUnitToken(), ranges);
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        Intrinsics.checkParameterIsNotNull(ranges, "ranges");
    }

    @NotNull
    public final List<LongRange> merge(long j) {
        return RangesKt.mergeRangesKeepOrder(RangesKt.toLongRanges(this.ranges, j));
    }
}
