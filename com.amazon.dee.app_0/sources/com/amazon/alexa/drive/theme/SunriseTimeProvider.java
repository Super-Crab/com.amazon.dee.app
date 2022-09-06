package com.amazon.alexa.drive.theme;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Calendar;
import java.util.TimeZone;
/* loaded from: classes7.dex */
public class SunriseTimeProvider {
    private static final double CONST_0009 = 9.0E-4d;
    private static final double CONST_360 = 360.0d;
    private static final int DAYS_PER_4000_YEARS = 146097;
    private static final int DAYS_PER_4_YEARS = 1461;
    private static final int DAYS_PER_5_MONTHS = 153;
    private static final int DAYS_PER_CENTURY = 36524;
    private static final int JULIAN_DATE_2000_01_01 = 2451545;
    public static final double SUN_ALTITUDE_SUNRISE_SUNSET = -0.833d;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class SolarEquationVariables {
        final double delta;
        final double jtransit;
        final double julianCycle;
        final double lambda;
        final double solarMeanAnomaly;

        private SolarEquationVariables(double d, double d2, double d3, double d4, double d5) {
            this.julianCycle = d;
            this.solarMeanAnomaly = d2;
            this.lambda = d3;
            this.jtransit = d4;
            this.delta = d5;
        }
    }

    private Calendar getGregorianDate(double d) {
        double d2 = d + 0.5d;
        int i = (int) d2;
        int i2 = i + 32044;
        int i3 = i2 / DAYS_PER_4000_YEARS;
        int i4 = i2 % DAYS_PER_4000_YEARS;
        int i5 = (((i4 / DAYS_PER_CENTURY) + 1) * 3) / 4;
        int i6 = i4 - (DAYS_PER_CENTURY * i5);
        int i7 = i6 / DAYS_PER_4_YEARS;
        int i8 = i6 % DAYS_PER_4_YEARS;
        int i9 = (((i8 / 365) + 1) * 3) / 4;
        int i10 = i8 - (i9 * 365);
        int outline3 = GeneratedOutlineSupport1.outline3(i7, 4, (i5 * 100) + (i3 * 400), i9);
        int i11 = (((i10 * 5) + 308) / 153) - 2;
        int i12 = i11 + 2;
        double d3 = (d2 - i) * 24.0d;
        int i13 = (int) d3;
        int i14 = (int) ((d3 - i13) * 60.0d);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(Constants.UTC));
        calendar.set(1, (i12 / 12) + (outline3 - 4800));
        calendar.set(2, i12 % 12);
        calendar.set(5, (i10 - (((i11 + 4) * 153) / 5)) + 122 + 1);
        calendar.set(11, i13);
        calendar.set(12, i14);
        calendar.set(13, (int) (((d3 * 3600.0d) - ((i14 * 60) + (i13 * 3600))) + 0.5d));
        calendar.set(14, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(calendar.getTimeInMillis());
        return calendar2;
    }

    private double getJulianDate(Calendar calendar) {
        Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone(Constants.UTC));
        calendar2.setTimeInMillis(calendar.getTimeInMillis());
        int i = calendar2.get(1);
        int i2 = calendar2.get(2) + 1;
        int i3 = (14 - i2) / 12;
        int i4 = (i + 4800) - i3;
        int i5 = (((((i3 * 12) + i2) - 3) * 153) + 2) / 5;
        int i6 = i4 * 365;
        int i7 = i4 / 4;
        int i8 = i4 / 400;
        int i9 = calendar2.get(11);
        double d = (i9 - 12.0d) / 24.0d;
        double d2 = calendar2.get(12) / 1440.0d;
        return (calendar2.get(13) / 86400.0d) + d2 + d + ((i8 + ((i7 + (i6 + (i5 + calendar2.get(5)))) - (i4 / 100))) - 32045);
    }

    private SolarEquationVariables getSolarEquationVariables(Calendar calendar, double d) {
        double julianDate = (getJulianDate(calendar) - 2451545.0d) - CONST_0009;
        double d2 = (-d) / CONST_360;
        double round = Math.round(julianDate - d2);
        double d3 = d2 + 2451545.0009d + round;
        double radians = Math.toRadians((((d3 - 2451545.0d) * 0.98560028d) + 357.5291d) % CONST_360);
        double radians2 = Math.toRadians((((Math.toDegrees(radians) + 102.9372d) + ((Math.sin(3.0d * radians) * 3.0E-4d) + ((Math.sin(radians * 2.0d) * 0.02d) + (Math.sin(radians) * 1.9148d)))) + 180.0d) % CONST_360);
        return new SolarEquationVariables(round, radians, radians2, ((Math.sin(radians) * 0.0053d) + d3) - (Math.sin(2.0d * radians2) * 0.0069d), Math.asin(Math.sin(Math.toRadians(23.439d)) * Math.sin(radians2)));
    }

    Calendar[] getSunriseSunset(Calendar calendar, double d, double d2) {
        SolarEquationVariables solarEquationVariables = getSolarEquationVariables(calendar, d2);
        double d3 = -d2;
        double radians = Math.toRadians(d);
        double acos = Math.acos((Math.sin(Math.toRadians(-0.833d)) - (Math.sin(solarEquationVariables.delta) * Math.sin(radians))) / (Math.cos(solarEquationVariables.delta) * Math.cos(radians)));
        if (Double.isNaN(acos)) {
            return null;
        }
        double sin = (((Math.sin(solarEquationVariables.solarMeanAnomaly) * 0.0053d) + (((Math.toDegrees(acos) + d3) / CONST_360) + solarEquationVariables.julianCycle)) - (Math.sin(solarEquationVariables.lambda * 2.0d) * 0.0069d)) + 2451545.0009d;
        double d4 = solarEquationVariables.jtransit;
        Calendar gregorianDate = getGregorianDate(d4 - (sin - d4));
        Calendar gregorianDate2 = getGregorianDate(sin);
        Calendar calendar2 = Calendar.getInstance(calendar.getTimeZone());
        calendar2.setTimeInMillis(gregorianDate.getTimeInMillis());
        Calendar calendar3 = Calendar.getInstance(calendar.getTimeZone());
        calendar3.setTimeInMillis(gregorianDate2.getTimeInMillis());
        return new Calendar[]{calendar2, calendar3};
    }

    public boolean isDay(double d, double d2) {
        return isDay(Calendar.getInstance(), d, d2);
    }

    public boolean isDay(Calendar calendar, double d, double d2) {
        Calendar[] sunriseSunset = getSunriseSunset(calendar, d, d2);
        if (sunriseSunset != null) {
            return calendar.after(sunriseSunset[0]) && calendar.before(sunriseSunset[1]);
        }
        int i = calendar.get(2);
        return d > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR ? i >= 3 && i <= 10 : i < 3 || i > 10;
    }
}
