package com.amazon.alexa.fitness.utils;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import com.amazon.alexa.fitness.api.afits.ActivitySummary;
import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.view.workoutTab.ListItem;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.dee.sdk.iotsoftap.Constants;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt__MathJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FormatUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0004\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\b\u0010\t\u001a\u0004\u0018\u00010\u0001\u001a\u000e\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0003\u001a\u001c\u0010\n\u001a\u00020\u00012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u001a\u000e\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001\u001a\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u0001\u001a\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000f\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0014\u001a\u00020\u00012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0016\u001a\u000e\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001\u001a\u000e\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001\u001a\"\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\f*\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\f\u001a\n\u0010\u001a\u001a\u00020\u0005*\u00020\u0005\u001a\n\u0010\u001b\u001a\u00020\u0005*\u00020\u0005\u001a\f\u0010\u001c\u001a\u0004\u0018\u00010\u0001*\u00020\u0005\u001a\f\u0010\u001d\u001a\u0004\u0018\u00010\u0001*\u00020\u0005\u001a\n\u0010\u001e\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u001f\u001a\u00020\u0013*\u00020\u0001\u001a\f\u0010 \u001a\u0004\u0018\u00010\u0001*\u00020\u0013\u001a\f\u0010!\u001a\u0004\u0018\u00010\u0001*\u00020\"\u001a\n\u0010#\u001a\u00020\u0001*\u00020\u0013\u001a\u0014\u0010$\u001a\u0004\u0018\u00010\u0001*\u00020\r2\u0006\u0010%\u001a\u00020\u0001\u001a\n\u0010\u000e\u001a\u00020\u0001*\u00020\u0013\u001a\n\u0010&\u001a\u00020\u0001*\u00020\u0013\u001a\n\u0010'\u001a\u00020\u0001*\u00020\u0013\u001a3\u0010(\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\f2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,¢\u0006\u0002\u0010-\u001a\n\u0010.\u001a\u00020\u0001*\u00020\u0003\u001a\n\u0010\u0018\u001a\u00020\u0001*\u00020\u0013\u001a\n\u0010/\u001a\u00020\u0001*\u00020\u0013\u001a\u0016\u00100\u001a\u0004\u0018\u00010\u0001*\u00020\r2\b\u00101\u001a\u0004\u0018\u00010\u0001\u001a\f\u00102\u001a\u0004\u0018\u00010\u0001*\u00020\r\u001a\u001c\u00103\u001a\n 4*\u0004\u0018\u00010\u00010\u0001*\u00020\u00132\u0006\u00105\u001a\u00020\u0001H\u0002\u001a\u0018\u00106\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*\u001a\f\u00107\u001a\u0004\u0018\u00010\u0001*\u00020\u0013\u001a\f\u00108\u001a\u0004\u0018\u00010\u0001*\u00020\u0005\u001a\n\u00109\u001a\u00020\u0005*\u00020:\u001a\n\u0010;\u001a\u00020\u0005*\u00020:\u001a\n\u0010<\u001a\u00020\u0001*\u00020:\u001a\u0016\u0010=\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\f\u001a\u001e\u0010>\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010)\u001a\u00020*\u001a\n\u0010?\u001a\u00020,*\u00020@\u001a\u0012\u0010A\u001a\u00020\u0001*\u00020\u00012\u0006\u0010)\u001a\u00020*\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"DEFAULT_DURATION", "", "HOUR_IN_SECONDS", "", "KILOMETERS_IN_MILE", "", "METERS_IN_KM", "METERS_IN_MILE", "TAG", "getDistanceUnit", "getFormattedCalories", "caloriesValue", "Lkotlin/Pair;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Units;", "getFormattedDate", "dateString", "getFormattedDateInMs", "", "getFormattedDateTime", "Lcom/amazon/alexa/fitness/api/util/DateTime;", "getFormattedDuration", "durationValue", "(Ljava/lang/Integer;)Ljava/lang/String;", "getFormattedFullDate", "getFormattedTime", "convertMilesPerHourToMinutesPerMile", "convertMinutesPerKmToMinutesPerMile", "convertMinutesPerMileToMinutesPerKm", "convertToLocalDistance", "convertToLocalDistanceWithoutUnits", "convertUTCToLocalTimeZone", "convertUTCToLocalTimeZoneDateTime", "formatPrimaryText", "formatSecondaryText", "Lcom/amazon/alexa/fitness/view/workoutTab/ListItem;", "getDayOfWeek", "getDistance", "distanceText", "getFormattedMonth", "getFormattedMonthDay", "getFormattedPaceInDeviceLocale", "context", "Landroid/content/Context;", "withUnits", "", "(Lkotlin/Pair;Landroid/content/Context;Ljava/lang/Boolean;)Ljava/lang/String;", "getFormattedString", "getFullDate", "getPace", "paceText", "getPaceUnit", "getSimpleDateFormat", "kotlin.jvm.PlatformType", "format", "getTime", "getTimeOfDay", "getWeeklyFormattedTime", "kmToMi", "", "miToKm", "secondsToDurationString", "toDistanceString", "toDistanceStringWithUnits", "usesMetricsSystem", "Ljava/util/Locale;", "withDistanceUnits", "AlexaMobileAndroidFitnessUI_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FormatUtilKt {
    private static final String DEFAULT_DURATION = "0:00";
    private static final int HOUR_IN_SECONDS = 3600;
    private static final double KILOMETERS_IN_MILE = 1.60934d;
    private static final int METERS_IN_KM = 1000;
    private static final double METERS_IN_MILE = 1609.34d;
    private static final String TAG = "AFX-FormatUtils";

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Units.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;

        static {
            $EnumSwitchMapping$0[Units.Kilometers.ordinal()] = 1;
            $EnumSwitchMapping$0[Units.Miles.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[Units.values().length];
            $EnumSwitchMapping$1[Units.Miles.ordinal()] = 1;
            $EnumSwitchMapping$1[Units.Kilometers.ordinal()] = 2;
            $EnumSwitchMapping$2 = new int[Units.values().length];
            $EnumSwitchMapping$2[Units.Miles.ordinal()] = 1;
            $EnumSwitchMapping$3 = new int[Units.values().length];
            $EnumSwitchMapping$3[Units.MinutesPerMile.ordinal()] = 1;
            $EnumSwitchMapping$4 = new int[Units.values().length];
            $EnumSwitchMapping$4[Units.MinutesPerMile.ordinal()] = 1;
        }
    }

    @NotNull
    public static final Pair<Double, Units> convertMilesPerHourToMinutesPerMile(@NotNull Pair<Double, ? extends Units> convertMilesPerHourToMinutesPerMile) {
        Intrinsics.checkParameterIsNotNull(convertMilesPerHourToMinutesPerMile, "$this$convertMilesPerHourToMinutesPerMile");
        if (convertMilesPerHourToMinutesPerMile.getFirst().doubleValue() == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            return new Pair<>(Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR), Units.MinutesPerMile);
        }
        return new Pair<>(Double.valueOf(60 / convertMilesPerHourToMinutesPerMile.getFirst().doubleValue()), Units.MinutesPerMile);
    }

    public static final double convertMinutesPerKmToMinutesPerMile(double d) {
        return d * KILOMETERS_IN_MILE;
    }

    public static final double convertMinutesPerMileToMinutesPerKm(double d) {
        return d / KILOMETERS_IN_MILE;
    }

    @Nullable
    public static final String convertToLocalDistance(double d) {
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        if (usesMetricsSystem(locale)) {
            Units units = Units.Kilometers;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {Double.valueOf(d / 1000)};
            String format = String.format("%.2f", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            return getDistance(units, format);
        }
        Units units2 = Units.Miles;
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        Object[] objArr2 = {Double.valueOf(d / METERS_IN_MILE)};
        String format2 = String.format("%.2f", Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
        return getDistance(units2, format2);
    }

    @Nullable
    public static final String convertToLocalDistanceWithoutUnits(double d) {
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        if (usesMetricsSystem(locale)) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {Double.valueOf(d / 1000)};
            String format = String.format("%.2f", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            return format;
        }
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        Object[] objArr2 = {Double.valueOf(d / METERS_IN_MILE)};
        String format2 = String.format("%.2f", Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
        return format2;
    }

    @NotNull
    public static final String convertUTCToLocalTimeZone(@NotNull String convertUTCToLocalTimeZone) {
        Date parse;
        Intrinsics.checkParameterIsNotNull(convertUTCToLocalTimeZone, "$this$convertUTCToLocalTimeZone");
        TimeZone timeZone = TimeZone.getTimeZone(Constants.UTC);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        simpleDateFormat.setTimeZone(timeZone);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        simpleDateFormat2.setTimeZone(timeZone);
        try {
            parse = simpleDateFormat.parse(convertUTCToLocalTimeZone);
        } catch (ParseException unused) {
            parse = simpleDateFormat2.parse(convertUTCToLocalTimeZone);
        }
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        simpleDateFormat3.setTimeZone(TimeZone.getDefault());
        String format = simpleDateFormat3.format(parse);
        Intrinsics.checkExpressionValueIsNotNull(format, "localTimeZoneDateFormat.format(utcDate)");
        return format;
    }

    @NotNull
    public static final DateTime convertUTCToLocalTimeZoneDateTime(@NotNull String convertUTCToLocalTimeZoneDateTime) {
        Intrinsics.checkParameterIsNotNull(convertUTCToLocalTimeZoneDateTime, "$this$convertUTCToLocalTimeZoneDateTime");
        String convertUTCToLocalTimeZone = convertUTCToLocalTimeZone(convertUTCToLocalTimeZoneDateTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        Date parse = simpleDateFormat.parse(convertUTCToLocalTimeZone);
        Intrinsics.checkExpressionValueIsNotNull(parse, "localTimeZoneDateFormat.parse(localTimeString)");
        return new DateTime(parse.getTime());
    }

    @Nullable
    public static final String formatPrimaryText(@NotNull DateTime formatPrimaryText) {
        Intrinsics.checkParameterIsNotNull(formatPrimaryText, "$this$formatPrimaryText");
        Context currentContext = FullScreenUtil.Companion.getCurrentContext();
        if (currentContext != null) {
            return currentContext.getString(R.string.workout_history_list_primary_text, getTimeOfDay(formatPrimaryText));
        }
        return null;
    }

    @Nullable
    public static final String formatSecondaryText(@NotNull ListItem formatSecondaryText) {
        List listOfNotNull;
        String joinToString$default;
        Intrinsics.checkParameterIsNotNull(formatSecondaryText, "$this$formatSecondaryText");
        ActivitySummary activitySummary = formatSecondaryText.getFitnessSession().getActivitySummary();
        Double caloriesInKcal = activitySummary.getCaloriesInKcal();
        Context currentContext = FullScreenUtil.Companion.getCurrentContext();
        String str = null;
        if (currentContext != null) {
            String[] strArr = new String[4];
            strArr[0] = convertToLocalDistance(activitySummary.getDistanceInMeters());
            strArr[1] = (caloriesInKcal == null || caloriesInKcal.doubleValue() < ((double) 0)) ? null : currentContext.getResources().getQuantityString(R.plurals.workout_history_list_secondary_text_cal, (int) caloriesInKcal.doubleValue(), Integer.valueOf((int) caloriesInKcal.doubleValue()));
            if (activitySummary.getPaceInMinutesPerKm() > 0) {
                str = getFormattedPaceInDeviceLocale$default(new Pair(Double.valueOf(activitySummary.getPaceInMinutesPerKm()), Units.MinutesPerKilometer), null, null, 3, null);
            }
            strArr[2] = str;
            strArr[3] = currentContext.getResources().getQuantityString(R.plurals.workout_history_list_secondary_text_step, activitySummary.getStepCount(), Integer.valueOf(activitySummary.getStepCount()));
            listOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull((Object[]) strArr);
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(listOfNotNull, " • ", null, null, 0, null, null, 62, null);
            return joinToString$default;
        }
        return null;
    }

    @NotNull
    public static final String getDayOfWeek(@NotNull DateTime getDayOfWeek) {
        Intrinsics.checkParameterIsNotNull(getDayOfWeek, "$this$getDayOfWeek");
        String simpleDateFormat = getSimpleDateFormat(getDayOfWeek, "EEEE");
        Intrinsics.checkExpressionValueIsNotNull(simpleDateFormat, "this.getSimpleDateFormat(\"EEEE\")");
        return simpleDateFormat;
    }

    @Nullable
    public static final String getDistance(@NotNull Units getDistance, @NotNull String distanceText) {
        Intrinsics.checkParameterIsNotNull(getDistance, "$this$getDistance");
        Intrinsics.checkParameterIsNotNull(distanceText, "distanceText");
        if (WhenMappings.$EnumSwitchMapping$2[getDistance.ordinal()] != 1) {
            Context currentContext = FullScreenUtil.Companion.getCurrentContext();
            if (currentContext == null) {
                return null;
            }
            return currentContext.getString(R.string.workout_detailed_view_km, distanceText);
        }
        Context currentContext2 = FullScreenUtil.Companion.getCurrentContext();
        if (currentContext2 == null) {
            return null;
        }
        return currentContext2.getString(R.string.workout_detailed_view_miles, distanceText);
    }

    @Nullable
    public static final String getDistanceUnit() {
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        if (usesMetricsSystem(locale)) {
            Context currentContext = FullScreenUtil.Companion.getCurrentContext();
            if (currentContext == null) {
                return null;
            }
            return currentContext.getString(R.string.distanceUnits_km);
        }
        Context currentContext2 = FullScreenUtil.Companion.getCurrentContext();
        if (currentContext2 == null) {
            return null;
        }
        return currentContext2.getString(R.string.distanceUnits);
    }

    @NotNull
    public static final String getFormattedCalories(@Nullable Pair<Double, ? extends Units> pair) {
        Double first;
        return getFormattedCalories((pair == null || (first = pair.getFirst()) == null) ? -1 : (int) first.doubleValue());
    }

    @NotNull
    public static final String getFormattedDate(@NotNull DateTime getFormattedDate) {
        Intrinsics.checkParameterIsNotNull(getFormattedDate, "$this$getFormattedDate");
        String format = DateFormat.getDateInstance(1, Locale.getDefault()).format(Long.valueOf(getFormattedDate.getEpochMilli()));
        Intrinsics.checkExpressionValueIsNotNull(format, "DateFormat.getDateInstan…).format(this.epochMilli)");
        return format;
    }

    public static final long getFormattedDateInMs(@NotNull String dateString) {
        Intrinsics.checkParameterIsNotNull(dateString, "dateString");
        DateTime formattedDateTime = getFormattedDateTime(dateString);
        if (formattedDateTime != null) {
            return formattedDateTime.getEpochMilli();
        }
        return 0L;
    }

    private static final DateTime getFormattedDateTime(String str) {
        Date parse;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            parse = simpleDateFormat.parse(str);
        } catch (ParseException unused) {
            parse = simpleDateFormat2.parse(str);
        }
        if (parse != null) {
            return new DateTime(parse.getTime());
        }
        return null;
    }

    @NotNull
    public static final String getFormattedDuration(@Nullable Integer num) {
        String formatElapsedTime;
        return (num == null || (formatElapsedTime = DateUtils.formatElapsedTime((long) num.intValue())) == null) ? DEFAULT_DURATION : formatElapsedTime;
    }

    @NotNull
    public static final String getFormattedFullDate(@NotNull String dateString) {
        String fullDate;
        Intrinsics.checkParameterIsNotNull(dateString, "dateString");
        DateTime formattedDateTime = getFormattedDateTime(dateString);
        return (formattedDateTime == null || (fullDate = getFullDate(formattedDateTime)) == null) ? "" : fullDate;
    }

    @NotNull
    public static final String getFormattedMonth(@NotNull DateTime getFormattedMonth) {
        Intrinsics.checkParameterIsNotNull(getFormattedMonth, "$this$getFormattedMonth");
        String simpleDateFormat = getSimpleDateFormat(getFormattedMonth, "MMMM");
        Intrinsics.checkExpressionValueIsNotNull(simpleDateFormat, "this.getSimpleDateFormat(\"MMMM\")");
        return simpleDateFormat;
    }

    @NotNull
    public static final String getFormattedMonthDay(@NotNull DateTime getFormattedMonthDay) {
        Intrinsics.checkParameterIsNotNull(getFormattedMonthDay, "$this$getFormattedMonthDay");
        String simpleDateFormat = getSimpleDateFormat(getFormattedMonthDay, "MMM d");
        Intrinsics.checkExpressionValueIsNotNull(simpleDateFormat, "this.getSimpleDateFormat(\"MMM d\")");
        return simpleDateFormat;
    }

    @NotNull
    public static final String getFormattedPaceInDeviceLocale(@NotNull Pair<Double, ? extends Units> getFormattedPaceInDeviceLocale, @Nullable Context context, @Nullable Boolean bool) {
        double doubleValue;
        int roundToInt;
        Intrinsics.checkParameterIsNotNull(getFormattedPaceInDeviceLocale, "$this$getFormattedPaceInDeviceLocale");
        if (context == null) {
            Log.e(TAG, "missing context to get formatted pace");
            return FullScreenUpdaterUtilKt.DEFAULT_DATA;
        } else if (getFormattedPaceInDeviceLocale.getFirst().doubleValue() <= FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            return FullScreenUpdaterUtilKt.DEFAULT_DATA;
        } else {
            Locale locale = Locale.getDefault();
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
            if (usesMetricsSystem(locale)) {
                Units second = getFormattedPaceInDeviceLocale.getSecond();
                Units units = Units.MinutesPerKilometer;
                Double first = getFormattedPaceInDeviceLocale.getFirst();
                doubleValue = second == units ? first.doubleValue() : convertMinutesPerMileToMinutesPerKm(first.doubleValue());
            } else {
                Units second2 = getFormattedPaceInDeviceLocale.getSecond();
                Units units2 = Units.MinutesPerMile;
                Double first2 = getFormattedPaceInDeviceLocale.getFirst();
                doubleValue = second2 == units2 ? first2.doubleValue() : convertMinutesPerKmToMinutesPerMile(first2.doubleValue());
            }
            roundToInt = MathKt__MathJVMKt.roundToInt(doubleValue * 60);
            String time$default = getTime$default(roundToInt, null, 1, null);
            if (time$default == null) {
                Log.e(TAG, "pace could not be formatted for device locale");
                return FullScreenUpdaterUtilKt.DEFAULT_DATA;
            } else if (bool == null) {
                return time$default;
            } else {
                String withDistanceUnits = bool.booleanValue() ? withDistanceUnits(time$default, context) : time$default;
                return withDistanceUnits != null ? withDistanceUnits : time$default;
            }
        }
    }

    public static /* synthetic */ String getFormattedPaceInDeviceLocale$default(Pair pair, Context context, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            context = FullScreenUtil.Companion.getCurrentContext();
        }
        if ((i & 2) != 0) {
            bool = true;
        }
        return getFormattedPaceInDeviceLocale(pair, context, bool);
    }

    @NotNull
    public static final String getFormattedString(int i) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        if (numberFormat != null) {
            DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
            decimalFormat.applyPattern("#,###");
            String format = decimalFormat.format(i);
            Intrinsics.checkExpressionValueIsNotNull(format, "formatter.format(this.toDouble())");
            return format;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.text.DecimalFormat");
    }

    @NotNull
    public static final String getFormattedTime(@NotNull DateTime getFormattedTime) {
        Intrinsics.checkParameterIsNotNull(getFormattedTime, "$this$getFormattedTime");
        String format = DateFormat.getTimeInstance(3, Locale.getDefault()).format(Long.valueOf(getFormattedTime.getEpochMilli()));
        Intrinsics.checkExpressionValueIsNotNull(format, "DateFormat.getTimeInstan…).format(this.epochMilli)");
        return format;
    }

    @NotNull
    public static final String getFullDate(@NotNull DateTime getFullDate) {
        Intrinsics.checkParameterIsNotNull(getFullDate, "$this$getFullDate");
        String format = DateFormat.getDateInstance(0, Locale.getDefault()).format(Long.valueOf(getFullDate.getEpochMilli()));
        Intrinsics.checkExpressionValueIsNotNull(format, "DateFormat.getDateInstan…).format(this.epochMilli)");
        return format;
    }

    @Nullable
    public static final String getPace(@NotNull Units getPace, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(getPace, "$this$getPace");
        if (WhenMappings.$EnumSwitchMapping$3[getPace.ordinal()] != 1) {
            Context currentContext = FullScreenUtil.Companion.getCurrentContext();
            if (currentContext == null) {
                return null;
            }
            return currentContext.getString(R.string.pace_format_km, str);
        }
        Context currentContext2 = FullScreenUtil.Companion.getCurrentContext();
        if (currentContext2 == null) {
            return null;
        }
        return currentContext2.getString(R.string.pace_format_miles, str);
    }

    @Nullable
    public static final String getPaceUnit(@NotNull Units getPaceUnit) {
        Intrinsics.checkParameterIsNotNull(getPaceUnit, "$this$getPaceUnit");
        if (WhenMappings.$EnumSwitchMapping$4[getPaceUnit.ordinal()] != 1) {
            Context currentContext = FullScreenUtil.Companion.getCurrentContext();
            if (currentContext == null) {
                return null;
            }
            return currentContext.getString(R.string.pace_format_km_unit);
        }
        Context currentContext2 = FullScreenUtil.Companion.getCurrentContext();
        if (currentContext2 == null) {
            return null;
        }
        return currentContext2.getString(R.string.pace_format_miles_unit);
    }

    private static final String getSimpleDateFormat(@NotNull DateTime dateTime, String str) {
        return new SimpleDateFormat(str).format(Long.valueOf(dateTime.getEpochMilli()));
    }

    @Nullable
    public static final String getTime(int i, @Nullable Context context) {
        if (i >= 3600) {
            if (context == null) {
                return null;
            }
            return context.getString(R.string.pace_large_duration_placeholder);
        } else if (context == null) {
            return null;
        } else {
            return context.getString(R.string.pace_duration_format, Integer.valueOf(i / 60), Integer.valueOf(i % 60));
        }
    }

    public static /* synthetic */ String getTime$default(int i, Context context, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            context = FullScreenUtil.Companion.getCurrentContext();
        }
        return getTime(i, context);
    }

    @Nullable
    public static final String getTimeOfDay(@NotNull DateTime getTimeOfDay) {
        int i;
        Intrinsics.checkParameterIsNotNull(getTimeOfDay, "$this$getTimeOfDay");
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(new Date(getTimeOfDay.getEpochMilli()));
        int i2 = calendar.get(11);
        if (4 <= i2 && 11 >= i2) {
            i = R.string.morning;
        } else if (12 <= i2 && 17 >= i2) {
            i = R.string.afternoon;
        } else {
            i = R.string.evening;
        }
        Context currentContext = FullScreenUtil.Companion.getCurrentContext();
        if (currentContext != null) {
            return currentContext.getString(i);
        }
        return null;
    }

    @Nullable
    public static final String getWeeklyFormattedTime(double d) {
        int roundToInt;
        roundToInt = MathKt__MathJVMKt.roundToInt(d);
        if (roundToInt < 3600) {
            Context currentContext = FullScreenUtil.Companion.getCurrentContext();
            if (currentContext == null) {
                return null;
            }
            return currentContext.getString(R.string.pace_duration_format, Integer.valueOf(roundToInt / 60), Integer.valueOf(roundToInt % 60));
        }
        int i = roundToInt / 3600;
        int i2 = roundToInt % 3600;
        int i3 = i2 / 60;
        int i4 = i2 % 60;
        Context currentContext2 = FullScreenUtil.Companion.getCurrentContext();
        if (currentContext2 == null) {
            return null;
        }
        return currentContext2.getString(R.string.weekly_total_time, Integer.valueOf(i), Integer.valueOf(i3), Integer.valueOf(i4));
    }

    public static final double kmToMi(@NotNull Number kmToMi) {
        Intrinsics.checkParameterIsNotNull(kmToMi, "$this$kmToMi");
        return kmToMi.doubleValue() / KILOMETERS_IN_MILE;
    }

    public static final double miToKm(@NotNull Number miToKm) {
        Intrinsics.checkParameterIsNotNull(miToKm, "$this$miToKm");
        return miToKm.doubleValue() * KILOMETERS_IN_MILE;
    }

    @NotNull
    public static final String secondsToDurationString(@NotNull Number secondsToDurationString) {
        Intrinsics.checkParameterIsNotNull(secondsToDurationString, "$this$secondsToDurationString");
        long longValue = secondsToDurationString.longValue();
        long j = 3600;
        long j2 = longValue % j;
        long j3 = 60;
        long j4 = j2 % j3;
        long j5 = j2 / j3;
        if (longValue >= j) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {Long.valueOf(longValue / j), Long.valueOf(j5), Long.valueOf(j4)};
            String format = String.format("%d:%02d:%02d", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            return format;
        }
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        Object[] objArr2 = {Long.valueOf(j5), Long.valueOf(j4)};
        String format2 = String.format("%d:%02d", Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
        return format2;
    }

    @NotNull
    public static final String toDistanceString(@NotNull Pair<Double, ? extends Units> toDistanceString) {
        Intrinsics.checkParameterIsNotNull(toDistanceString, "$this$toDistanceString");
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        Double d = null;
        if (usesMetricsSystem(locale)) {
            int i = WhenMappings.$EnumSwitchMapping$0[toDistanceString.getSecond().ordinal()];
            if (i == 1) {
                d = toDistanceString.getFirst();
            } else if (i == 2) {
                d = Double.valueOf(miToKm(toDistanceString.getFirst()));
            }
        } else {
            int i2 = WhenMappings.$EnumSwitchMapping$1[toDistanceString.getSecond().ordinal()];
            if (i2 == 1) {
                d = toDistanceString.getFirst();
            } else if (i2 == 2) {
                d = Double.valueOf(kmToMi(toDistanceString.getFirst()));
            }
        }
        if (d != null) {
            double doubleValue = d.doubleValue();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {Double.valueOf(doubleValue)};
            String format = String.format("%.2f", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            return format;
        }
        return FullScreenUpdaterUtilKt.DEFAULT_DATA;
    }

    @NotNull
    public static final String toDistanceStringWithUnits(@NotNull Pair<Double, ? extends Units> toDistanceStringWithUnits, @NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(toDistanceStringWithUnits, "$this$toDistanceStringWithUnits");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        String string = context.getString(usesMetricsSystem(locale) ? R.string.workout_detailed_view_km : R.string.workout_detailed_view_miles, toDistanceString(toDistanceStringWithUnits));
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(resId, this.toDistanceString())");
        return string;
    }

    public static final boolean usesMetricsSystem(@NotNull Locale usesMetricsSystem) {
        Intrinsics.checkParameterIsNotNull(usesMetricsSystem, "$this$usesMetricsSystem");
        String country = usesMetricsSystem.getCountry();
        Intrinsics.checkExpressionValueIsNotNull(country, "this.country");
        Locale locale = Locale.ROOT;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ROOT");
        String upperCase = country.toUpperCase(locale);
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase(locale)");
        int hashCode = upperCase.hashCode();
        return hashCode == 2267 ? !upperCase.equals("GB") : hashCode == 2438 ? !upperCase.equals("LR") : hashCode == 2464 ? !upperCase.equals("MM") : hashCode != 2718 || !upperCase.equals("US");
    }

    @NotNull
    public static final String withDistanceUnits(@NotNull String withDistanceUnits, @NotNull Context context) {
        int i;
        Intrinsics.checkParameterIsNotNull(withDistanceUnits, "$this$withDistanceUnits");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        if (usesMetricsSystem(locale)) {
            i = R.string.pace_format_km;
        } else {
            i = R.string.pace_format_miles;
        }
        String string = context.getString(i, withDistanceUnits);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(paceFormatResId, this)");
        return string;
    }

    @NotNull
    public static final String getFormattedCalories(int i) {
        return i < 0 ? FullScreenUpdaterUtilKt.DEFAULT_DATA : getFormattedString(i);
    }

    @NotNull
    public static final String getFormattedDate(@NotNull String dateString) {
        String formattedDate;
        Intrinsics.checkParameterIsNotNull(dateString, "dateString");
        DateTime formattedDateTime = getFormattedDateTime(dateString);
        return (formattedDateTime == null || (formattedDate = getFormattedDate(formattedDateTime)) == null) ? "" : formattedDate;
    }

    @NotNull
    public static final String getFormattedTime(@NotNull String dateString) {
        String formattedTime;
        Intrinsics.checkParameterIsNotNull(dateString, "dateString");
        DateTime formattedDateTime = getFormattedDateTime(dateString);
        return (formattedDateTime == null || (formattedTime = getFormattedTime(formattedDateTime)) == null) ? "" : formattedTime;
    }
}
