package com.amazon.alexa.fitness.time;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.util.Error;
import com.amazon.alexa.fitness.util.Result;
import com.amazon.alexa.fitness.util.ResultOrError;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.jetbrains.annotations.NotNull;
/* compiled from: ISO8601.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/time/ISO8601;", "", "()V", "Companion", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ISO8601 {
    public static final Companion Companion = new Companion(null);

    /* compiled from: ISO8601.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00042\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0002J\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\u0006\u001a\u00020\u0004J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\u0006\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\"\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0004H\u0002¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/fitness/time/ISO8601$Companion;", "", "()V", "format", "", "kotlin.jvm.PlatformType", "input", "Lcom/amazon/alexa/fitness/api/util/DateTime;", "tz", "Ljava/util/TimeZone;", "parseDate", "Lcom/amazon/alexa/fitness/util/ResultOrError;", "parseDateTime", "serializeDate", "serializeDateTime", "serializeDateTimeInLocalTimeZone", "timeZone", "zulu", "", "unsafeParseDate", "unsafeParseDateTime", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        private final String format(String str, DateTime dateTime, TimeZone timeZone) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.US);
            simpleDateFormat.setTimeZone(timeZone);
            return simpleDateFormat.format(new Date(dateTime.getEpochMilli()));
        }

        static /* synthetic */ String format$default(Companion companion, String str, DateTime dateTime, TimeZone timeZone, int i, Object obj) {
            if ((i & 4) != 0) {
                timeZone = ISO8601Kt.access$utcTimeZone();
            }
            return companion.format(str, dateTime, timeZone);
        }

        public static /* synthetic */ String serializeDateTimeInLocalTimeZone$default(Companion companion, DateTime dateTime, TimeZone timeZone, boolean z, int i, Object obj) throws IllegalStateException {
            if ((i & 2) != 0) {
                Calendar calendar = Calendar.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance()");
                timeZone = calendar.getTimeZone();
                Intrinsics.checkExpressionValueIsNotNull(timeZone, "Calendar.getInstance().timeZone");
            }
            if ((i & 4) != 0) {
                z = true;
            }
            return companion.serializeDateTimeInLocalTimeZone(dateTime, timeZone, z);
        }

        private final DateTime unsafeParseDate(String str) throws IOException {
            SimpleDate access$extractSimpleDateFromMatcher;
            Matcher matcherWithSeparators = ISO8601Kt.access$getDatePatternWithSeparators$p().matcher(str);
            Matcher matcherWithoutSeparators = ISO8601Kt.access$getDatePatternWithoutSeparators$p().matcher(str);
            if (matcherWithSeparators.matches()) {
                Intrinsics.checkExpressionValueIsNotNull(matcherWithSeparators, "matcherWithSeparators");
                access$extractSimpleDateFromMatcher = ISO8601Kt.access$extractSimpleDateFromMatcher(matcherWithSeparators);
            } else if (!matcherWithoutSeparators.matches()) {
                throw new IOException(GeneratedOutlineSupport1.outline75("Date '", str, "' does not match ISO-8601 format"));
            } else {
                Intrinsics.checkExpressionValueIsNotNull(matcherWithoutSeparators, "matcherWithoutSeparators");
                access$extractSimpleDateFromMatcher = ISO8601Kt.access$extractSimpleDateFromMatcher(matcherWithoutSeparators);
            }
            Calendar calendar = Calendar.getInstance(ISO8601Kt.access$utcTimeZone(), Locale.US);
            calendar.clear();
            Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance(utc…       .apply { clear() }");
            return new DateTime(ISO8601Kt.access$withSimpleDateFields(calendar, access$extractSimpleDateFromMatcher).getTimeInMillis());
        }

        private final DateTime unsafeParseDateTime(String str) throws IOException {
            List split$default;
            SimpleDate access$extractSimpleDateFromMatcher;
            SimpleTime access$extractSimpleTimeFromMatcher;
            split$default = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{ExifInterface.GPS_DIRECTION_TRUE}, false, 0, 6, (Object) null);
            if (1 == split$default.size()) {
                return unsafeParseDate(str);
            }
            if (2 >= split$default.size()) {
                String str2 = (String) split$default.get(0);
                String str3 = (String) split$default.get(1);
                Matcher dateMatcherWithSeparators = ISO8601Kt.access$getDatePatternWithSeparators$p().matcher(str2);
                Matcher dateMatcherWithoutSeparators = ISO8601Kt.access$getDatePatternWithoutSeparators$p().matcher(str2);
                if (dateMatcherWithSeparators.matches()) {
                    Intrinsics.checkExpressionValueIsNotNull(dateMatcherWithSeparators, "dateMatcherWithSeparators");
                    access$extractSimpleDateFromMatcher = ISO8601Kt.access$extractSimpleDateFromMatcher(dateMatcherWithSeparators);
                    Matcher timeMatcher = ISO8601Kt.access$getTimePatternWithSeparators$p().matcher(str3);
                    if (timeMatcher.matches()) {
                        Intrinsics.checkExpressionValueIsNotNull(timeMatcher, "timeMatcher");
                        access$extractSimpleTimeFromMatcher = ISO8601Kt.access$extractSimpleTimeFromMatcher(timeMatcher);
                    } else {
                        throw new IOException(GeneratedOutlineSupport1.outline75("Time '", str3, "' does not match ISO-8601 format"));
                    }
                } else if (dateMatcherWithoutSeparators.matches()) {
                    Intrinsics.checkExpressionValueIsNotNull(dateMatcherWithoutSeparators, "dateMatcherWithoutSeparators");
                    access$extractSimpleDateFromMatcher = ISO8601Kt.access$extractSimpleDateFromMatcher(dateMatcherWithoutSeparators);
                    Matcher timeMatcher2 = ISO8601Kt.access$getTimePatternWithoutSeparators$p().matcher(str3);
                    if (timeMatcher2.matches()) {
                        Intrinsics.checkExpressionValueIsNotNull(timeMatcher2, "timeMatcher");
                        access$extractSimpleTimeFromMatcher = ISO8601Kt.access$extractSimpleTimeFromMatcher(timeMatcher2);
                    } else {
                        throw new IOException(GeneratedOutlineSupport1.outline75("Time '", str3, "' does not match ISO-8601 format"));
                    }
                } else {
                    throw new IOException(GeneratedOutlineSupport1.outline75("Date '", str2, "' does not match ISO-8601 format"));
                }
                Calendar calendar = Calendar.getInstance(access$extractSimpleTimeFromMatcher.getTimeZone(), Locale.US);
                calendar.clear();
                Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance(sim…       .apply { clear() }");
                return new DateTime(ISO8601Kt.access$withSimpleTimeFields(ISO8601Kt.access$withSimpleDateFields(calendar, access$extractSimpleDateFromMatcher), access$extractSimpleTimeFromMatcher).getTimeInMillis());
            }
            throw new IOException(GeneratedOutlineSupport1.outline75("Date Time '", str, "' does not match ISO-8601 format"));
        }

        @NotNull
        public final ResultOrError<DateTime> parseDate(@NotNull String input) {
            Intrinsics.checkParameterIsNotNull(input, "input");
            try {
                return new Result(unsafeParseDate(input));
            } catch (IOException e) {
                return new Error(e);
            }
        }

        @NotNull
        public final ResultOrError<DateTime> parseDateTime(@NotNull String input) {
            Intrinsics.checkParameterIsNotNull(input, "input");
            try {
                return new Result(unsafeParseDateTime(input));
            } catch (IOException e) {
                return new Error(e);
            }
        }

        @NotNull
        public final String serializeDate(@NotNull DateTime input) {
            Intrinsics.checkParameterIsNotNull(input, "input");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            simpleDateFormat.setTimeZone(ISO8601Kt.access$utcTimeZone());
            String format = simpleDateFormat.format(new Date(input.getEpochMilli()));
            Intrinsics.checkExpressionValueIsNotNull(format, "SimpleDateFormat(ISO_860…t(Date(input.epochMilli))");
            return format;
        }

        @NotNull
        public final String serializeDateTime(@NotNull DateTime input) throws IllegalStateException {
            Intrinsics.checkParameterIsNotNull(input, "input");
            return GeneratedOutlineSupport1.outline91(new StringBuilder(), format$default(this, "yyyy-MM-dd'T'HH:mm:ss.SSS", input, null, 4, null), "Z");
        }

        @NotNull
        public final String serializeDateTimeInLocalTimeZone(@NotNull DateTime input, @NotNull TimeZone timeZone, boolean z) throws IllegalStateException {
            Intrinsics.checkParameterIsNotNull(input, "input");
            Intrinsics.checkParameterIsNotNull(timeZone, "timeZone");
            double floor = Math.floor(timeZone.getOffset(input.getEpochMilli()) / 1000.0d);
            String str = floor < ((double) 0) ? ProcessIdUtil.DEFAULT_PROCESSID : "+";
            int i = (int) (floor / 3600.0d);
            ISO8601$Companion$serializeDateTimeInLocalTimeZone$buildOffset$1 iSO8601$Companion$serializeDateTimeInLocalTimeZone$buildOffset$1 = new ISO8601$Companion$serializeDateTimeInLocalTimeZone$buildOffset$1(z);
            return format("yyyy-MM-dd'T'HH:mm:ss", input, timeZone) + iSO8601$Companion$serializeDateTimeInLocalTimeZone$buildOffset$1.invoke((ISO8601$Companion$serializeDateTimeInLocalTimeZone$buildOffset$1) str, (String) Integer.valueOf(i), Integer.valueOf((int) ((floor / 60.0d) - (i * 60.0d))));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private ISO8601() {
    }
}
