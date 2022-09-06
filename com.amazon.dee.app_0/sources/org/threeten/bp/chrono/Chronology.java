package org.threeten.bp.chrono;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: classes5.dex */
public abstract class Chronology implements Comparable<Chronology> {
    private static final Method LOCALE_METHOD;
    public static final TemporalQuery<Chronology> FROM = new TemporalQuery<Chronology>() { // from class: org.threeten.bp.chrono.Chronology.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: queryFrom  reason: avoid collision after fix types in other method */
        public Chronology mo13072queryFrom(TemporalAccessor temporalAccessor) {
            return Chronology.from(temporalAccessor);
        }
    };
    private static final ConcurrentHashMap<String, Chronology> CHRONOS_BY_ID = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Chronology> CHRONOS_BY_TYPE = new ConcurrentHashMap<>();

    static {
        Method method;
        try {
            method = Locale.class.getMethod("getUnicodeLocaleType", String.class);
        } catch (Throwable unused) {
            method = null;
        }
        LOCALE_METHOD = method;
    }

    public static Chronology from(TemporalAccessor temporalAccessor) {
        Jdk8Methods.requireNonNull(temporalAccessor, "temporal");
        Chronology chronology = (Chronology) temporalAccessor.query(TemporalQueries.chronology());
        return chronology != null ? chronology : IsoChronology.INSTANCE;
    }

    public static Set<Chronology> getAvailableChronologies() {
        init();
        return new HashSet(CHRONOS_BY_ID.values());
    }

    private static void init() {
        if (CHRONOS_BY_ID.isEmpty()) {
            register(IsoChronology.INSTANCE);
            register(ThaiBuddhistChronology.INSTANCE);
            register(MinguoChronology.INSTANCE);
            register(JapaneseChronology.INSTANCE);
            register(HijrahChronology.INSTANCE);
            CHRONOS_BY_ID.putIfAbsent("Hijrah", HijrahChronology.INSTANCE);
            CHRONOS_BY_TYPE.putIfAbsent("islamic", HijrahChronology.INSTANCE);
            Iterator it2 = ServiceLoader.load(Chronology.class, Chronology.class.getClassLoader()).iterator();
            while (it2.hasNext()) {
                Chronology chronology = (Chronology) it2.next();
                CHRONOS_BY_ID.putIfAbsent(chronology.getId(), chronology);
                String calendarType = chronology.getCalendarType();
                if (calendarType != null) {
                    CHRONOS_BY_TYPE.putIfAbsent(calendarType, chronology);
                }
            }
        }
    }

    public static Chronology of(String str) {
        init();
        Chronology chronology = CHRONOS_BY_ID.get(str);
        if (chronology != null) {
            return chronology;
        }
        Chronology chronology2 = CHRONOS_BY_TYPE.get(str);
        if (chronology2 == null) {
            throw new DateTimeException(GeneratedOutlineSupport1.outline72("Unknown chronology: ", str));
        }
        return chronology2;
    }

    public static Chronology ofLocale(Locale locale) {
        String str;
        init();
        Jdk8Methods.requireNonNull(locale, "locale");
        Method method = LOCALE_METHOD;
        if (method != null) {
            try {
                str = (String) method.invoke(locale, "ca");
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
            }
        } else {
            if (locale.equals(JapaneseChronology.LOCALE)) {
                str = "japanese";
            }
            str = "iso";
        }
        if (str != null && !"iso".equals(str) && !"iso8601".equals(str)) {
            Chronology chronology = CHRONOS_BY_TYPE.get(str);
            if (chronology == null) {
                throw new DateTimeException(GeneratedOutlineSupport1.outline72("Unknown calendar system: ", str));
            }
            return chronology;
        }
        return IsoChronology.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Chronology readExternal(DataInput dataInput) throws IOException {
        return of(dataInput.readUTF());
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private static void register(Chronology chronology) {
        CHRONOS_BY_ID.putIfAbsent(chronology.getId(), chronology);
        String calendarType = chronology.getCalendarType();
        if (calendarType != null) {
            CHRONOS_BY_TYPE.putIfAbsent(calendarType, chronology);
        }
    }

    private Object writeReplace() {
        return new Ser((byte) 11, this);
    }

    /* renamed from: date */
    public abstract ChronoLocalDate mo13043date(int i, int i2, int i3);

    /* renamed from: date */
    public ChronoLocalDate mo13044date(Era era, int i, int i2, int i3) {
        return mo13043date(prolepticYear(era, i), i2, i3);
    }

    /* renamed from: date */
    public abstract ChronoLocalDate mo13045date(TemporalAccessor temporalAccessor);

    /* renamed from: dateEpochDay */
    public abstract ChronoLocalDate mo13046dateEpochDay(long j);

    /* renamed from: dateNow */
    public ChronoLocalDate mo13047dateNow() {
        return mo13048dateNow(Clock.systemDefaultZone());
    }

    /* renamed from: dateYearDay */
    public abstract ChronoLocalDate mo13050dateYearDay(int i, int i2);

    /* renamed from: dateYearDay */
    public ChronoLocalDate mo13051dateYearDay(Era era, int i, int i2) {
        return mo13050dateYearDay(prolepticYear(era, i), i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <D extends ChronoLocalDate> D ensureChronoLocalDate(Temporal temporal) {
        D d = (D) temporal;
        if (equals(d.mo13054getChronology())) {
            return d;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Chrono mismatch, expected: ");
        outline107.append(getId());
        outline107.append(", actual: ");
        outline107.append(d.mo13054getChronology().getId());
        throw new ClassCastException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <D extends ChronoLocalDate> ChronoLocalDateTimeImpl<D> ensureChronoLocalDateTime(Temporal temporal) {
        ChronoLocalDateTimeImpl<D> chronoLocalDateTimeImpl = (ChronoLocalDateTimeImpl) temporal;
        if (equals(chronoLocalDateTimeImpl.mo12900toLocalDate().mo13054getChronology())) {
            return chronoLocalDateTimeImpl;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Chrono mismatch, required: ");
        outline107.append(getId());
        outline107.append(", supplied: ");
        outline107.append(chronoLocalDateTimeImpl.mo12900toLocalDate().mo13054getChronology().getId());
        throw new ClassCastException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <D extends ChronoLocalDate> ChronoZonedDateTimeImpl<D> ensureChronoZonedDateTime(Temporal temporal) {
        ChronoZonedDateTimeImpl<D> chronoZonedDateTimeImpl = (ChronoZonedDateTimeImpl) temporal;
        if (equals(chronoZonedDateTimeImpl.mo12942toLocalDate().mo13054getChronology())) {
            return chronoZonedDateTimeImpl;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Chrono mismatch, required: ");
        outline107.append(getId());
        outline107.append(", supplied: ");
        outline107.append(chronoZonedDateTimeImpl.mo12942toLocalDate().mo13054getChronology().getId());
        throw new ClassCastException(outline107.toString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Chronology) && compareTo((Chronology) obj) == 0;
    }

    /* renamed from: eraOf */
    public abstract Era mo13052eraOf(int i);

    public abstract List<Era> eras();

    public abstract String getCalendarType();

    public String getDisplayName(TextStyle textStyle, Locale locale) {
        return new DateTimeFormatterBuilder().appendChronologyText(textStyle).toFormatter(locale).format(new DefaultInterfaceTemporalAccessor() { // from class: org.threeten.bp.chrono.Chronology.2
            @Override // org.threeten.bp.temporal.TemporalAccessor
            public long getLong(TemporalField temporalField) {
                throw new UnsupportedTemporalTypeException(GeneratedOutlineSupport1.outline82("Unsupported field: ", temporalField));
            }

            @Override // org.threeten.bp.temporal.TemporalAccessor
            public boolean isSupported(TemporalField temporalField) {
                return false;
            }

            @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
            public <R> R query(TemporalQuery<R> temporalQuery) {
                if (temporalQuery == TemporalQueries.chronology()) {
                    return (R) Chronology.this;
                }
                return (R) super.query(temporalQuery);
            }
        });
    }

    public abstract String getId();

    public int hashCode() {
        return getClass().hashCode() ^ getId().hashCode();
    }

    public abstract boolean isLeapYear(long j);

    /* renamed from: localDateTime */
    public ChronoLocalDateTime<?> mo12999localDateTime(TemporalAccessor temporalAccessor) {
        try {
            return mo13045date(temporalAccessor).mo12887atTime(LocalTime.from(temporalAccessor));
        } catch (DateTimeException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to obtain ChronoLocalDateTime from TemporalAccessor: ");
            outline107.append(temporalAccessor.getClass());
            throw new DateTimeException(outline107.toString(), e);
        }
    }

    public ChronoPeriod period(int i, int i2, int i3) {
        return new ChronoPeriodImpl(this, i, i2, i3);
    }

    public abstract int prolepticYear(Era era, int i);

    public abstract ValueRange range(ChronoField chronoField);

    /* renamed from: resolveDate */
    public abstract ChronoLocalDate mo13053resolveDate(Map<TemporalField, Long> map, ResolverStyle resolverStyle);

    public String toString() {
        return getId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateResolveMap(Map<TemporalField, Long> map, ChronoField chronoField, long j) {
        Long l = map.get(chronoField);
        if (l != null && l.longValue() != j) {
            throw new DateTimeException("Invalid state, field: " + chronoField + " " + l + " conflicts with " + chronoField + " " + j);
        }
        map.put(chronoField, Long.valueOf(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(getId());
    }

    /* renamed from: zonedDateTime */
    public ChronoZonedDateTime<?> mo13002zonedDateTime(TemporalAccessor temporalAccessor) {
        try {
            ZoneId from = ZoneId.from(temporalAccessor);
            try {
                return mo13001zonedDateTime(Instant.from(temporalAccessor), from);
            } catch (DateTimeException unused) {
                return ChronoZonedDateTimeImpl.ofBest(ensureChronoLocalDateTime(mo12999localDateTime(temporalAccessor)), from, null);
            }
        } catch (DateTimeException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to obtain ChronoZonedDateTime from TemporalAccessor: ");
            outline107.append(temporalAccessor.getClass());
            throw new DateTimeException(outline107.toString(), e);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Chronology chronology) {
        return getId().compareTo(chronology.getId());
    }

    /* renamed from: dateNow */
    public ChronoLocalDate mo13049dateNow(ZoneId zoneId) {
        return mo13048dateNow(Clock.system(zoneId));
    }

    /* renamed from: dateNow */
    public ChronoLocalDate mo13048dateNow(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        return mo13045date(LocalDate.now(clock));
    }

    /* renamed from: zonedDateTime */
    public ChronoZonedDateTime<?> mo13001zonedDateTime(Instant instant, ZoneId zoneId) {
        return ChronoZonedDateTimeImpl.ofInstant(this, instant, zoneId);
    }
}
