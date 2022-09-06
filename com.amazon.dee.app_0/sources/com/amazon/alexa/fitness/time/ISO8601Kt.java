package com.amazon.alexa.fitness.time;

import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: ISO8601.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0002\u001a\u0010\u0010(\u001a\u00020)2\u0006\u0010&\u001a\u00020'H\u0002\u001a\b\u0010*\u001a\u00020+H\u0002\u001a#\u0010,\u001a\u0004\u0018\u00010\u0007*\u00020\u00072\u0006\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010/\u001a\n\u00100\u001a\u00020\u0001*\u000201\u001a\u0014\u00102\u001a\u000203*\u0002032\u0006\u00104\u001a\u00020%H\u0002\u001a\u0014\u00105\u001a\u000203*\u0002032\u0006\u00106\u001a\u00020)H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018\"\u001b\u0010\u001b\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001a\u001a\u0004\b\u001c\u0010\u0018\"\u001b\u0010\u001e\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u001a\u001a\u0004\b\u001f\u0010\u0018\"\u001b\u0010!\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\u001a\u001a\u0004\b\"\u0010\u0018¨\u00067"}, d2 = {"DATE_TIME_SEPARATOR", "", "ISO_8601_DATE_AND_TIME_FORMAT", "ISO_8601_DATE_AND_TIME_FORMAT_WITHOUT_OFFSET", "ISO_8601_DATE_FORMAT", "ISO_8601_UTC_TIME_ZONE_SUFFIX", "MAX_DAY", "", "MAX_HOUR", "MAX_MILLISECOND", "MAX_MINUTE", "MAX_MONTH", "MAX_SECOND", "MAX_YEAR", "MIN_DAY", "MIN_HOUR", "MIN_MILLISECOND", "MIN_MINUTE", "MIN_MONTH", "MIN_SECOND", "MIN_YEAR", "datePatternWithSeparators", "Ljava/util/regex/Pattern;", "getDatePatternWithSeparators", "()Ljava/util/regex/Pattern;", "datePatternWithSeparators$delegate", "Lkotlin/Lazy;", "datePatternWithoutSeparators", "getDatePatternWithoutSeparators", "datePatternWithoutSeparators$delegate", "timePatternWithSeparators", "getTimePatternWithSeparators", "timePatternWithSeparators$delegate", "timePatternWithoutSeparators", "getTimePatternWithoutSeparators", "timePatternWithoutSeparators$delegate", "extractSimpleDateFromMatcher", "Lcom/amazon/alexa/fitness/time/SimpleDate;", "matcher", "Ljava/util/regex/Matcher;", "extractSimpleTimeFromMatcher", "Lcom/amazon/alexa/fitness/time/SimpleTime;", "utcTimeZone", "Ljava/util/TimeZone;", "inRangeOrNull", ReactProperties.GEOFENCE_MINIMUM_RANGE, ReactProperties.GEOFENCE_MAXIMUM_RANGE, "(III)Ljava/lang/Integer;", "toISO8601", "Lcom/amazon/alexa/fitness/api/util/DateTime;", "withSimpleDateFields", "Ljava/util/Calendar;", "simpleDate", "withSimpleTimeFields", "simpleTime", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ISO8601Kt {
    private static final String DATE_TIME_SEPARATOR = "T";
    private static final String ISO_8601_DATE_AND_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    private static final String ISO_8601_DATE_AND_TIME_FORMAT_WITHOUT_OFFSET = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String ISO_8601_DATE_FORMAT = "yyyy-MM-dd";
    private static final String ISO_8601_UTC_TIME_ZONE_SUFFIX = "Z";
    private static final int MAX_DAY = 31;
    private static final int MAX_HOUR = 24;
    private static final int MAX_MILLISECOND = 999;
    private static final int MAX_MINUTE = 59;
    private static final int MAX_MONTH = 12;
    private static final int MAX_SECOND = 60;
    private static final int MAX_YEAR = 9999;
    private static final int MIN_DAY = 1;
    private static final int MIN_HOUR = 0;
    private static final int MIN_MILLISECOND = 0;
    private static final int MIN_MINUTE = 0;
    private static final int MIN_MONTH = 1;
    private static final int MIN_SECOND = 0;
    private static final int MIN_YEAR = 0;
    private static final Lazy datePatternWithSeparators$delegate;
    private static final Lazy datePatternWithoutSeparators$delegate;
    private static final Lazy timePatternWithSeparators$delegate;
    private static final Lazy timePatternWithoutSeparators$delegate;

    static {
        Lazy lazy;
        Lazy lazy2;
        Lazy lazy3;
        Lazy lazy4;
        lazy = LazyKt__LazyJVMKt.lazy(ISO8601Kt$datePatternWithSeparators$2.INSTANCE);
        datePatternWithSeparators$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(ISO8601Kt$datePatternWithoutSeparators$2.INSTANCE);
        datePatternWithoutSeparators$delegate = lazy2;
        lazy3 = LazyKt__LazyJVMKt.lazy(ISO8601Kt$timePatternWithSeparators$2.INSTANCE);
        timePatternWithSeparators$delegate = lazy3;
        lazy4 = LazyKt__LazyJVMKt.lazy(ISO8601Kt$timePatternWithoutSeparators$2.INSTANCE);
        timePatternWithoutSeparators$delegate = lazy4;
    }

    public static final /* synthetic */ SimpleDate access$extractSimpleDateFromMatcher(Matcher matcher) {
        return extractSimpleDateFromMatcher(matcher);
    }

    public static final /* synthetic */ SimpleTime access$extractSimpleTimeFromMatcher(Matcher matcher) {
        return extractSimpleTimeFromMatcher(matcher);
    }

    public static final /* synthetic */ Pattern access$getDatePatternWithSeparators$p() {
        return getDatePatternWithSeparators();
    }

    public static final /* synthetic */ Pattern access$getDatePatternWithoutSeparators$p() {
        return getDatePatternWithoutSeparators();
    }

    public static final /* synthetic */ Pattern access$getTimePatternWithSeparators$p() {
        return getTimePatternWithSeparators();
    }

    public static final /* synthetic */ Pattern access$getTimePatternWithoutSeparators$p() {
        return getTimePatternWithoutSeparators();
    }

    public static final /* synthetic */ TimeZone access$utcTimeZone() {
        return utcTimeZone();
    }

    public static final /* synthetic */ Calendar access$withSimpleDateFields(Calendar calendar, SimpleDate simpleDate) {
        return withSimpleDateFields(calendar, simpleDate);
    }

    public static final /* synthetic */ Calendar access$withSimpleTimeFields(Calendar calendar, SimpleTime simpleTime) {
        return withSimpleTimeFields(calendar, simpleTime);
    }

    public static final SimpleDate extractSimpleDateFromMatcher(Matcher matcher) throws IOException {
        Integer intOrNull;
        Integer inRangeOrNull;
        Integer intOrNull2;
        Integer inRangeOrNull2;
        Integer intOrNull3;
        Integer inRangeOrNull3;
        String group = matcher.group(1);
        Intrinsics.checkExpressionValueIsNotNull(group, "matcher.group(1)");
        intOrNull = StringsKt__StringNumberConversionsKt.toIntOrNull(group);
        if (intOrNull != null && (inRangeOrNull = inRangeOrNull(intOrNull.intValue(), 0, 9999)) != null) {
            int intValue = inRangeOrNull.intValue();
            String group2 = matcher.group(2);
            Intrinsics.checkExpressionValueIsNotNull(group2, "matcher.group(2)");
            intOrNull2 = StringsKt__StringNumberConversionsKt.toIntOrNull(group2);
            if (intOrNull2 != null && (inRangeOrNull2 = inRangeOrNull(intOrNull2.intValue(), 1, 12)) != null) {
                int intValue2 = inRangeOrNull2.intValue();
                String group3 = matcher.group(3);
                Intrinsics.checkExpressionValueIsNotNull(group3, "matcher.group(3)");
                intOrNull3 = StringsKt__StringNumberConversionsKt.toIntOrNull(group3);
                if (intOrNull3 != null && (inRangeOrNull3 = inRangeOrNull(intOrNull3.intValue(), 1, 31)) != null) {
                    return new SimpleDate(intValue, intValue2, inRangeOrNull3.intValue());
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("day '");
                outline107.append(matcher.group(3));
                outline107.append("' is out of allowed range [1, 31]");
                throw new IOException(outline107.toString());
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("month '");
            outline1072.append(matcher.group(2));
            outline1072.append("' is out of allowed range [1, 12]");
            throw new IOException(outline1072.toString());
        }
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("year '");
        outline1073.append(matcher.group(1));
        outline1073.append("' is out of allowed range [0, 9999]");
        throw new IOException(outline1073.toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x0009, code lost:
        r1 = kotlin.text.StringsKt__StringNumberConversionsKt.toIntOrNull(r1);
     */
    /* JADX WARN: Removed duplicated region for block: B:107:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final com.amazon.alexa.fitness.time.SimpleTime extractSimpleTimeFromMatcher(java.util.regex.Matcher r11) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.fitness.time.ISO8601Kt.extractSimpleTimeFromMatcher(java.util.regex.Matcher):com.amazon.alexa.fitness.time.SimpleTime");
    }

    public static final Pattern getDatePatternWithSeparators() {
        return (Pattern) datePatternWithSeparators$delegate.getValue();
    }

    public static final Pattern getDatePatternWithoutSeparators() {
        return (Pattern) datePatternWithoutSeparators$delegate.getValue();
    }

    public static final Pattern getTimePatternWithSeparators() {
        return (Pattern) timePatternWithSeparators$delegate.getValue();
    }

    public static final Pattern getTimePatternWithoutSeparators() {
        return (Pattern) timePatternWithoutSeparators$delegate.getValue();
    }

    private static final Integer inRangeOrNull(int i, int i2, int i3) {
        if (i < i2 || i > i3) {
            return null;
        }
        return Integer.valueOf(i);
    }

    @NotNull
    public static final String toISO8601(@NotNull DateTime toISO8601) {
        Intrinsics.checkParameterIsNotNull(toISO8601, "$this$toISO8601");
        return ISO8601.Companion.serializeDateTime(toISO8601);
    }

    public static final TimeZone utcTimeZone() {
        TimeZone timeZone = TimeZone.getTimeZone(Constants.UTC);
        Intrinsics.checkExpressionValueIsNotNull(timeZone, "TimeZone.getTimeZone(\"UTC\")");
        return timeZone;
    }

    public static final Calendar withSimpleDateFields(@NotNull Calendar calendar, SimpleDate simpleDate) {
        calendar.set(1, simpleDate.getYear());
        calendar.set(2, simpleDate.getMonth() - 1);
        calendar.set(5, simpleDate.getDay());
        return calendar;
    }

    public static final Calendar withSimpleTimeFields(@NotNull Calendar calendar, SimpleTime simpleTime) {
        calendar.set(11, simpleTime.getHour());
        calendar.set(12, simpleTime.getMinute());
        calendar.set(13, simpleTime.getSecond());
        calendar.set(14, simpleTime.getMillisecond());
        return calendar;
    }
}
