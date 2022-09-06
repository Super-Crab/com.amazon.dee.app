package io.ktor.util;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Ranges.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0015\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0087\u0002\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"length", "", "Lkotlin/ranges/LongRange;", "length$annotations", "(Lkotlin/ranges/LongRange;)V", "getLength", "(Lkotlin/ranges/LongRange;)J", "contains", "", "other", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class RangesKt {
    @InternalAPI
    public static final boolean contains(@NotNull LongRange receiver$0, @NotNull LongRange other) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return other.mo11373getStart().longValue() >= receiver$0.mo11373getStart().longValue() && other.mo11372getEndInclusive().longValue() <= receiver$0.mo11372getEndInclusive().longValue();
    }

    public static final long getLength(@NotNull LongRange receiver$0) {
        long coerceAtLeast;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast((receiver$0.mo11372getEndInclusive().longValue() - receiver$0.mo11373getStart().longValue()) + 1, 0L);
        return coerceAtLeast;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Not supported anymore.")
    public static /* synthetic */ void length$annotations(LongRange longRange) {
    }
}
