package com.amazon.alexa.fitness.utils;

import com.amazon.alexa.fitness.ui.R;
import com.sun.mail.imap.IMAPStore;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DayOfWeekPosition.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/fitness/utils/DayOfWeekPosition;", "", "value", "", "(I)V", "getValue", "()I", "Companion", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DayOfWeekPosition {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Map<Integer, Integer> dateStringMap;
    private final int value;

    /* compiled from: DayOfWeekPosition.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fR\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/fitness/utils/DayOfWeekPosition$Companion;", "", "()V", "dateStringMap", "", "", "getDateStringMap", "()Ljava/util/Map;", "getId", "value", "(I)Ljava/lang/Integer;", "getStartOfWeekDateInMillis", "", IMAPStore.ID_DATE, "Ljava/util/Date;", "(Ljava/util/Date;)Ljava/lang/Long;", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Map<Integer, Integer> getDateStringMap() {
            return DayOfWeekPosition.dateStringMap;
        }

        @Nullable
        public final Integer getId(int i) {
            return getDateStringMap().get(Integer.valueOf(i));
        }

        @Nullable
        public final Long getStartOfWeekDateInMillis(@NotNull Date date) {
            Intrinsics.checkParameterIsNotNull(date, "date");
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(date);
            calendar.set(7, 2);
            calendar.set(11, 0);
            calendar.clear(12);
            calendar.clear(13);
            calendar.clear(14);
            Date time = calendar.getTime();
            Intrinsics.checkExpressionValueIsNotNull(time, "calendar.time");
            return Long.valueOf(time.getTime());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        Map<Integer, Integer> mapOf;
        mapOf = MapsKt__MapsKt.mapOf(TuplesKt.to(2, Integer.valueOf(R.id.monday)), TuplesKt.to(3, Integer.valueOf(R.id.tuesday)), TuplesKt.to(4, Integer.valueOf(R.id.wednesday)), TuplesKt.to(5, Integer.valueOf(R.id.thursday)), TuplesKt.to(6, Integer.valueOf(R.id.friday)), TuplesKt.to(7, Integer.valueOf(R.id.saturday)), TuplesKt.to(1, Integer.valueOf(R.id.sunday)));
        dateStringMap = mapOf;
    }

    public DayOfWeekPosition(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }
}
