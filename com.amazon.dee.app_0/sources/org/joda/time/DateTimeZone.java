package org.joda.time;

import com.amazon.dee.sdk.iotsoftap.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.joda.time.chrono.BaseChronology;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.FormatUtils;
import org.joda.time.tz.DefaultNameProvider;
import org.joda.time.tz.FixedDateTimeZone;
import org.joda.time.tz.NameProvider;
import org.joda.time.tz.Provider;
import org.joda.time.tz.UTCProvider;
import org.joda.time.tz.ZoneInfoProvider;
/* loaded from: classes5.dex */
public abstract class DateTimeZone implements Serializable {
    private static final int MAX_MILLIS = 86399999;
    private static final long serialVersionUID = 5546345482340108586L;
    private final String iID;
    public static final DateTimeZone UTC = UTCDateTimeZone.INSTANCE;
    private static final AtomicReference<Provider> cProvider = new AtomicReference<>();
    private static final AtomicReference<NameProvider> cNameProvider = new AtomicReference<>();
    private static final AtomicReference<DateTimeZone> cDefault = new AtomicReference<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class LazyInit {
        static final Map<String, String> CONVERSION_MAP = buildMap();
        static final DateTimeFormatter OFFSET_FORMATTER = buildFormatter();

        LazyInit() {
        }

        private static DateTimeFormatter buildFormatter() {
            return new DateTimeFormatterBuilder().appendTimeZoneOffset(null, true, 2, 4).toFormatter().withChronology(new BaseChronology() { // from class: org.joda.time.DateTimeZone.LazyInit.1
                private static final long serialVersionUID = -3128740902654445468L;

                @Override // org.joda.time.chrono.BaseChronology, org.joda.time.Chronology
                public DateTimeZone getZone() {
                    return null;
                }

                @Override // org.joda.time.chrono.BaseChronology, org.joda.time.Chronology
                public String toString() {
                    return AnonymousClass1.class.getName();
                }

                @Override // org.joda.time.chrono.BaseChronology, org.joda.time.Chronology
                public Chronology withUTC() {
                    return this;
                }

                @Override // org.joda.time.chrono.BaseChronology, org.joda.time.Chronology
                public Chronology withZone(DateTimeZone dateTimeZone) {
                    return this;
                }
            });
        }

        private static Map<String, String> buildMap() {
            HashMap outline134 = GeneratedOutlineSupport1.outline134("GMT", Constants.UTC, "WET", "WET");
            outline134.put("CET", "CET");
            outline134.put("MET", "CET");
            outline134.put("ECT", "CET");
            outline134.put("EET", "EET");
            outline134.put("MIT", "Pacific/Apia");
            outline134.put("HST", "Pacific/Honolulu");
            outline134.put("AST", "America/Anchorage");
            outline134.put("PST", "America/Los_Angeles");
            outline134.put("MST", "America/Denver");
            outline134.put("PNT", "America/Phoenix");
            outline134.put("CST", "America/Chicago");
            outline134.put("EST", "America/New_York");
            outline134.put("IET", "America/Indiana/Indianapolis");
            outline134.put("PRT", "America/Puerto_Rico");
            outline134.put("CNT", "America/St_Johns");
            outline134.put("AGT", "America/Argentina/Buenos_Aires");
            outline134.put("BET", "America/Sao_Paulo");
            outline134.put("ART", "Africa/Cairo");
            outline134.put("CAT", "Africa/Harare");
            outline134.put("EAT", "Africa/Addis_Ababa");
            outline134.put("NET", "Asia/Yerevan");
            outline134.put("PLT", "Asia/Karachi");
            outline134.put("IST", "Asia/Kolkata");
            outline134.put("BST", "Asia/Dhaka");
            outline134.put("VST", "Asia/Ho_Chi_Minh");
            outline134.put("CTT", "Asia/Shanghai");
            outline134.put("JST", "Asia/Tokyo");
            outline134.put("ACT", "Australia/Darwin");
            outline134.put("AET", "Australia/Sydney");
            outline134.put("SST", "Pacific/Guadalcanal");
            outline134.put("NST", "Pacific/Auckland");
            return Collections.unmodifiableMap(outline134);
        }
    }

    /* loaded from: classes5.dex */
    private static final class Stub implements Serializable {
        private static final long serialVersionUID = -6471952376487863581L;
        private transient String iID;

        Stub(String str) {
            this.iID = str;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException {
            this.iID = objectInputStream.readUTF();
        }

        private Object readResolve() throws ObjectStreamException {
            return DateTimeZone.forID(this.iID);
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeUTF(this.iID);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DateTimeZone(String str) {
        if (str != null) {
            this.iID = str;
            return;
        }
        throw new IllegalArgumentException("Id must not be null");
    }

    private static DateTimeZone fixedOffsetZone(String str, int i) {
        return i == 0 ? UTC : new FixedDateTimeZone(str, null, i, i);
    }

    public static DateTimeZone forID(String str) {
        if (str == null) {
            return getDefault();
        }
        if (str.equals(Constants.UTC)) {
            return UTC;
        }
        DateTimeZone zone = getProvider().getZone(str);
        if (zone != null) {
            return zone;
        }
        if (!str.startsWith("+") && !str.startsWith(ProcessIdUtil.DEFAULT_PROCESSID)) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("The datetime zone id '", str, "' is not recognised"));
        }
        int parseOffset = parseOffset(str);
        return ((long) parseOffset) == 0 ? UTC : fixedOffsetZone(printOffset(parseOffset), parseOffset);
    }

    public static DateTimeZone forOffsetHours(int i) throws IllegalArgumentException {
        return forOffsetHoursMinutes(i, 0);
    }

    public static DateTimeZone forOffsetHoursMinutes(int i, int i2) throws IllegalArgumentException {
        if (i == 0 && i2 == 0) {
            return UTC;
        }
        if (i < -23 || i > 23) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Hours out of range: ", i));
        }
        if (i2 < -59 || i2 > 59) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Minutes out of range: ", i2));
        }
        if (i > 0 && i2 < 0) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Positive hours must not have negative minutes: ", i2));
        }
        int i3 = i * 60;
        try {
            return forOffsetMillis(FieldUtils.safeMultiply(i3 < 0 ? i3 - Math.abs(i2) : i3 + i2, 60000));
        } catch (ArithmeticException unused) {
            throw new IllegalArgumentException("Offset is too large");
        }
    }

    public static DateTimeZone forOffsetMillis(int i) {
        if (i < -86399999 || i > MAX_MILLIS) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Millis out of range: ", i));
        }
        return fixedOffsetZone(printOffset(i), i);
    }

    public static DateTimeZone forTimeZone(TimeZone timeZone) {
        if (timeZone == null) {
            return getDefault();
        }
        String id = timeZone.getID();
        if (id == null) {
            throw new IllegalArgumentException("The TimeZone id must not be null");
        }
        if (id.equals(Constants.UTC)) {
            return UTC;
        }
        DateTimeZone dateTimeZone = null;
        String convertedId = getConvertedId(id);
        Provider provider = getProvider();
        if (convertedId != null) {
            dateTimeZone = provider.getZone(convertedId);
        }
        if (dateTimeZone == null) {
            dateTimeZone = provider.getZone(id);
        }
        if (dateTimeZone != null) {
            return dateTimeZone;
        }
        if (convertedId != null || (!id.startsWith("GMT+") && !id.startsWith("GMT-"))) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("The datetime zone id '", id, "' is not recognised"));
        }
        int parseOffset = parseOffset(id.substring(3));
        return ((long) parseOffset) == 0 ? UTC : fixedOffsetZone(printOffset(parseOffset), parseOffset);
    }

    public static Set<String> getAvailableIDs() {
        return getProvider().getAvailableIDs();
    }

    private static String getConvertedId(String str) {
        return LazyInit.CONVERSION_MAP.get(str);
    }

    public static DateTimeZone getDefault() {
        DateTimeZone dateTimeZone = cDefault.get();
        if (dateTimeZone == null) {
            try {
                String property = System.getProperty("user.timezone");
                if (property != null) {
                    dateTimeZone = forID(property);
                }
            } catch (RuntimeException unused) {
            }
            if (dateTimeZone == null) {
                try {
                    dateTimeZone = forTimeZone(TimeZone.getDefault());
                } catch (IllegalArgumentException unused2) {
                }
            }
            if (dateTimeZone == null) {
                dateTimeZone = UTC;
            }
            return !cDefault.compareAndSet(null, dateTimeZone) ? cDefault.get() : dateTimeZone;
        }
        return dateTimeZone;
    }

    private static NameProvider getDefaultNameProvider() {
        NameProvider nameProvider = null;
        try {
            String property = System.getProperty("org.joda.time.DateTimeZone.NameProvider");
            if (property != null) {
                try {
                    nameProvider = (NameProvider) Class.forName(property).newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SecurityException unused) {
        }
        return nameProvider == null ? new DefaultNameProvider() : nameProvider;
    }

    private static Provider getDefaultProvider() {
        try {
            String property = System.getProperty("org.joda.time.DateTimeZone.Provider");
            if (property != null) {
                try {
                    return validateProvider((Provider) Class.forName(property).newInstance());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SecurityException unused) {
        }
        try {
            String property2 = System.getProperty("org.joda.time.DateTimeZone.Folder");
            if (property2 != null) {
                try {
                    return validateProvider(new ZoneInfoProvider(new File(property2)));
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
        } catch (SecurityException unused2) {
        }
        try {
            return validateProvider(new ZoneInfoProvider("org/joda/time/tz/data"));
        } catch (Exception e3) {
            e3.printStackTrace();
            return new UTCProvider();
        }
    }

    public static NameProvider getNameProvider() {
        NameProvider nameProvider = cNameProvider.get();
        if (nameProvider == null) {
            NameProvider defaultNameProvider = getDefaultNameProvider();
            return !cNameProvider.compareAndSet(null, defaultNameProvider) ? cNameProvider.get() : defaultNameProvider;
        }
        return nameProvider;
    }

    public static Provider getProvider() {
        Provider provider = cProvider.get();
        if (provider == null) {
            Provider defaultProvider = getDefaultProvider();
            return !cProvider.compareAndSet(null, defaultProvider) ? cProvider.get() : defaultProvider;
        }
        return provider;
    }

    private static int parseOffset(String str) {
        return -((int) LazyInit.OFFSET_FORMATTER.parseMillis(str));
    }

    private static String printOffset(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        if (i >= 0) {
            stringBuffer.append('+');
        } else {
            stringBuffer.append('-');
            i = -i;
        }
        int i2 = i / 3600000;
        FormatUtils.appendPaddedInteger(stringBuffer, i2, 2);
        int i3 = i - (i2 * 3600000);
        int i4 = i3 / 60000;
        stringBuffer.append(JsonReaderKt.COLON);
        FormatUtils.appendPaddedInteger(stringBuffer, i4, 2);
        int i5 = i3 - (i4 * 60000);
        if (i5 == 0) {
            return stringBuffer.toString();
        }
        int i6 = i5 / 1000;
        stringBuffer.append(JsonReaderKt.COLON);
        FormatUtils.appendPaddedInteger(stringBuffer, i6, 2);
        int i7 = i5 - (i6 * 1000);
        if (i7 == 0) {
            return stringBuffer.toString();
        }
        stringBuffer.append('.');
        FormatUtils.appendPaddedInteger(stringBuffer, i7, 3);
        return stringBuffer.toString();
    }

    public static void setDefault(DateTimeZone dateTimeZone) throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("DateTimeZone.setDefault"));
        }
        if (dateTimeZone != null) {
            cDefault.set(dateTimeZone);
            return;
        }
        throw new IllegalArgumentException("The datetime zone must not be null");
    }

    public static void setNameProvider(NameProvider nameProvider) throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("DateTimeZone.setNameProvider"));
        }
        if (nameProvider == null) {
            nameProvider = getDefaultNameProvider();
        }
        cNameProvider.set(nameProvider);
    }

    public static void setProvider(Provider provider) throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("DateTimeZone.setProvider"));
        }
        if (provider == null) {
            provider = getDefaultProvider();
        } else {
            validateProvider(provider);
        }
        cProvider.set(provider);
    }

    private static Provider validateProvider(Provider provider) {
        Set<String> availableIDs = provider.getAvailableIDs();
        if (availableIDs == null || availableIDs.size() == 0) {
            throw new IllegalArgumentException("The provider doesn't have any available ids");
        }
        if (!availableIDs.contains(Constants.UTC)) {
            throw new IllegalArgumentException("The provider doesn't support UTC");
        }
        if (!UTC.equals(provider.getZone(Constants.UTC))) {
            throw new IllegalArgumentException("Invalid UTC zone provided");
        }
        return provider;
    }

    public long adjustOffset(long j, boolean z) {
        long j2 = j - 10800000;
        long offset = getOffset(j2);
        long offset2 = getOffset(10800000 + j);
        if (offset <= offset2) {
            return j;
        }
        long j3 = offset - offset2;
        long nextTransition = nextTransition(j2);
        long j4 = nextTransition - j3;
        return (j < j4 || j >= nextTransition + j3) ? j : j - j4 >= j3 ? z ? j : j - j3 : z ? j + j3 : j;
    }

    public long convertLocalToUTC(long j, boolean z) {
        long j2;
        int offset = getOffset(j);
        long j3 = j - offset;
        int offset2 = getOffset(j3);
        if (offset != offset2 && (z || offset < 0)) {
            long nextTransition = nextTransition(j3);
            long j4 = Long.MAX_VALUE;
            if (nextTransition == j3) {
                nextTransition = Long.MAX_VALUE;
            }
            long j5 = j - offset2;
            long nextTransition2 = nextTransition(j5);
            if (nextTransition2 != j5) {
                j4 = nextTransition2;
            }
            if (nextTransition != j4) {
                if (z) {
                    throw new IllegalInstantException(j, getID());
                }
                long j6 = offset;
                j2 = j - j6;
                if ((j ^ j2) >= 0 && (j ^ j6) < 0) {
                    throw new ArithmeticException("Subtracting time zone offset caused overflow");
                }
                return j2;
            }
        }
        offset = offset2;
        long j62 = offset;
        j2 = j - j62;
        if ((j ^ j2) >= 0) {
        }
        return j2;
    }

    public long convertLocalToUTC(long j, boolean z, long j2) {
        int offset = getOffset(j2);
        long j3 = j - offset;
        return getOffset(j3) == offset ? j3 : convertLocalToUTC(j, z);
    }

    public long convertUTCToLocal(long j) {
        long offset = getOffset(j);
        long j2 = j + offset;
        if ((j ^ j2) >= 0 || (j ^ offset) < 0) {
            return j2;
        }
        throw new ArithmeticException("Adding time zone offset caused overflow");
    }

    public abstract boolean equals(Object obj);

    public final String getID() {
        return this.iID;
    }

    public long getMillisKeepLocal(DateTimeZone dateTimeZone, long j) {
        if (dateTimeZone == null) {
            dateTimeZone = getDefault();
        }
        DateTimeZone dateTimeZone2 = dateTimeZone;
        return dateTimeZone2 == this ? j : dateTimeZone2.convertLocalToUTC(convertUTCToLocal(j), false, j);
    }

    public final String getName(long j) {
        return getName(j, null);
    }

    public String getName(long j, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        String nameKey = getNameKey(j);
        if (nameKey == null) {
            return this.iID;
        }
        NameProvider nameProvider = getNameProvider();
        String name = nameProvider instanceof DefaultNameProvider ? ((DefaultNameProvider) nameProvider).getName(locale, this.iID, nameKey, isStandardOffset(j)) : nameProvider.getName(locale, this.iID, nameKey);
        return name != null ? name : printOffset(getOffset(j));
    }

    public abstract String getNameKey(long j);

    public abstract int getOffset(long j);

    public final int getOffset(ReadableInstant readableInstant) {
        return getOffset(readableInstant == null ? DateTimeUtils.currentTimeMillis() : readableInstant.getMillis());
    }

    public int getOffsetFromLocal(long j) {
        int offset = getOffset(j);
        long j2 = j - offset;
        int offset2 = getOffset(j2);
        if (offset != offset2) {
            if (offset - offset2 < 0) {
                long nextTransition = nextTransition(j2);
                if (nextTransition == j2) {
                    nextTransition = Long.MAX_VALUE;
                }
                long j3 = j - offset2;
                long nextTransition2 = nextTransition(j3);
                if (nextTransition2 == j3) {
                    nextTransition2 = Long.MAX_VALUE;
                }
                if (nextTransition != nextTransition2) {
                    return offset;
                }
            }
        } else if (offset >= 0) {
            long previousTransition = previousTransition(j2);
            if (previousTransition < j2) {
                int offset3 = getOffset(previousTransition);
                if (j2 - previousTransition <= offset3 - offset) {
                    return offset3;
                }
            }
        }
        return offset2;
    }

    public final String getShortName(long j) {
        return getShortName(j, null);
    }

    public String getShortName(long j, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        String nameKey = getNameKey(j);
        if (nameKey == null) {
            return this.iID;
        }
        NameProvider nameProvider = getNameProvider();
        String shortName = nameProvider instanceof DefaultNameProvider ? ((DefaultNameProvider) nameProvider).getShortName(locale, this.iID, nameKey, isStandardOffset(j)) : nameProvider.getShortName(locale, this.iID, nameKey);
        return shortName != null ? shortName : printOffset(getOffset(j));
    }

    public abstract int getStandardOffset(long j);

    public int hashCode() {
        return getID().hashCode() + 57;
    }

    public abstract boolean isFixed();

    public boolean isLocalDateTimeGap(LocalDateTime localDateTime) {
        if (isFixed()) {
            return false;
        }
        try {
            localDateTime.toDateTime(this);
            return false;
        } catch (IllegalInstantException unused) {
            return true;
        }
    }

    public boolean isStandardOffset(long j) {
        return getOffset(j) == getStandardOffset(j);
    }

    public abstract long nextTransition(long j);

    public abstract long previousTransition(long j);

    public String toString() {
        return getID();
    }

    public TimeZone toTimeZone() {
        return TimeZone.getTimeZone(this.iID);
    }

    protected Object writeReplace() throws ObjectStreamException {
        return new Stub(this.iID);
    }
}
