package org.threeten.bp.format;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.C;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.ChronoZonedDateTime;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class DateTimeBuilder extends DefaultInterfaceTemporalAccessor implements TemporalAccessor, Cloneable {
    Chronology chrono;
    ChronoLocalDate date;
    Period excessDays;
    final Map<TemporalField, Long> fieldValues = new HashMap();
    boolean leapSecond;
    LocalTime time;
    ZoneId zone;

    public DateTimeBuilder() {
    }

    private void checkDate(LocalDate localDate) {
        if (localDate != null) {
            addObject(localDate);
            for (TemporalField temporalField : this.fieldValues.keySet()) {
                if ((temporalField instanceof ChronoField) && temporalField.isDateBased()) {
                    try {
                        long j = localDate.getLong(temporalField);
                        Long l = this.fieldValues.get(temporalField);
                        if (j != l.longValue()) {
                            throw new DateTimeException("Conflict found: Field " + temporalField + " " + j + " differs from " + temporalField + " " + l + " derived from " + localDate);
                        }
                    } catch (DateTimeException unused) {
                        continue;
                    }
                }
            }
        }
    }

    private void crossCheck() {
        LocalTime localTime;
        if (this.fieldValues.size() > 0) {
            ChronoLocalDate chronoLocalDate = this.date;
            if (chronoLocalDate != null && (localTime = this.time) != null) {
                crossCheck(chronoLocalDate.mo12887atTime(localTime));
                return;
            }
            ChronoLocalDate chronoLocalDate2 = this.date;
            if (chronoLocalDate2 != null) {
                crossCheck(chronoLocalDate2);
                return;
            }
            LocalTime localTime2 = this.time;
            if (localTime2 == null) {
                return;
            }
            crossCheck(localTime2);
        }
    }

    private Long getFieldValue0(TemporalField temporalField) {
        return this.fieldValues.get(temporalField);
    }

    private void mergeDate(ResolverStyle resolverStyle) {
        if (this.chrono instanceof IsoChronology) {
            checkDate(IsoChronology.INSTANCE.mo13053resolveDate(this.fieldValues, resolverStyle));
        } else if (!this.fieldValues.containsKey(ChronoField.EPOCH_DAY)) {
        } else {
            checkDate(LocalDate.ofEpochDay(this.fieldValues.remove(ChronoField.EPOCH_DAY).longValue()));
        }
    }

    private void mergeInstantFields() {
        if (this.fieldValues.containsKey(ChronoField.INSTANT_SECONDS)) {
            ZoneId zoneId = this.zone;
            if (zoneId != null) {
                mergeInstantFields0(zoneId);
                return;
            }
            Long l = this.fieldValues.get(ChronoField.OFFSET_SECONDS);
            if (l == null) {
                return;
            }
            mergeInstantFields0(ZoneOffset.ofTotalSeconds(l.intValue()));
        }
    }

    private void mergeInstantFields0(ZoneId zoneId) {
        ChronoZonedDateTime<?> mo13001zonedDateTime = this.chrono.mo13001zonedDateTime(Instant.ofEpochSecond(this.fieldValues.remove(ChronoField.INSTANT_SECONDS).longValue()), zoneId);
        if (this.date == null) {
            addObject(mo13001zonedDateTime.mo12942toLocalDate());
        } else {
            resolveMakeChanges(ChronoField.INSTANT_SECONDS, mo13001zonedDateTime.mo12942toLocalDate());
        }
        addFieldValue(ChronoField.SECOND_OF_DAY, mo13001zonedDateTime.toLocalTime().toSecondOfDay());
    }

    private void mergeTime(ResolverStyle resolverStyle) {
        long j = 0;
        if (this.fieldValues.containsKey(ChronoField.CLOCK_HOUR_OF_DAY)) {
            long longValue = this.fieldValues.remove(ChronoField.CLOCK_HOUR_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT && (resolverStyle != ResolverStyle.SMART || longValue != 0)) {
                ChronoField.CLOCK_HOUR_OF_DAY.checkValidValue(longValue);
            }
            ChronoField chronoField = ChronoField.HOUR_OF_DAY;
            if (longValue == 24) {
                longValue = 0;
            }
            addFieldValue(chronoField, longValue);
        }
        if (this.fieldValues.containsKey(ChronoField.CLOCK_HOUR_OF_AMPM)) {
            long longValue2 = this.fieldValues.remove(ChronoField.CLOCK_HOUR_OF_AMPM).longValue();
            if (resolverStyle != ResolverStyle.LENIENT && (resolverStyle != ResolverStyle.SMART || longValue2 != 0)) {
                ChronoField.CLOCK_HOUR_OF_AMPM.checkValidValue(longValue2);
            }
            ChronoField chronoField2 = ChronoField.HOUR_OF_AMPM;
            if (longValue2 != 12) {
                j = longValue2;
            }
            addFieldValue(chronoField2, j);
        }
        if (resolverStyle != ResolverStyle.LENIENT) {
            if (this.fieldValues.containsKey(ChronoField.AMPM_OF_DAY)) {
                ChronoField chronoField3 = ChronoField.AMPM_OF_DAY;
                chronoField3.checkValidValue(this.fieldValues.get(chronoField3).longValue());
            }
            if (this.fieldValues.containsKey(ChronoField.HOUR_OF_AMPM)) {
                ChronoField chronoField4 = ChronoField.HOUR_OF_AMPM;
                chronoField4.checkValidValue(this.fieldValues.get(chronoField4).longValue());
            }
        }
        if (this.fieldValues.containsKey(ChronoField.AMPM_OF_DAY) && this.fieldValues.containsKey(ChronoField.HOUR_OF_AMPM)) {
            addFieldValue(ChronoField.HOUR_OF_DAY, (this.fieldValues.remove(ChronoField.AMPM_OF_DAY).longValue() * 12) + this.fieldValues.remove(ChronoField.HOUR_OF_AMPM).longValue());
        }
        if (this.fieldValues.containsKey(ChronoField.NANO_OF_DAY)) {
            long longValue3 = this.fieldValues.remove(ChronoField.NANO_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.NANO_OF_DAY.checkValidValue(longValue3);
            }
            addFieldValue(ChronoField.SECOND_OF_DAY, longValue3 / C.NANOS_PER_SECOND);
            addFieldValue(ChronoField.NANO_OF_SECOND, longValue3 % C.NANOS_PER_SECOND);
        }
        if (this.fieldValues.containsKey(ChronoField.MICRO_OF_DAY)) {
            long longValue4 = this.fieldValues.remove(ChronoField.MICRO_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.MICRO_OF_DAY.checkValidValue(longValue4);
            }
            addFieldValue(ChronoField.SECOND_OF_DAY, longValue4 / 1000000);
            addFieldValue(ChronoField.MICRO_OF_SECOND, longValue4 % 1000000);
        }
        if (this.fieldValues.containsKey(ChronoField.MILLI_OF_DAY)) {
            long longValue5 = this.fieldValues.remove(ChronoField.MILLI_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.MILLI_OF_DAY.checkValidValue(longValue5);
            }
            addFieldValue(ChronoField.SECOND_OF_DAY, longValue5 / 1000);
            addFieldValue(ChronoField.MILLI_OF_SECOND, longValue5 % 1000);
        }
        if (this.fieldValues.containsKey(ChronoField.SECOND_OF_DAY)) {
            long longValue6 = this.fieldValues.remove(ChronoField.SECOND_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.SECOND_OF_DAY.checkValidValue(longValue6);
            }
            addFieldValue(ChronoField.HOUR_OF_DAY, longValue6 / 3600);
            addFieldValue(ChronoField.MINUTE_OF_HOUR, (longValue6 / 60) % 60);
            addFieldValue(ChronoField.SECOND_OF_MINUTE, longValue6 % 60);
        }
        if (this.fieldValues.containsKey(ChronoField.MINUTE_OF_DAY)) {
            long longValue7 = this.fieldValues.remove(ChronoField.MINUTE_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.MINUTE_OF_DAY.checkValidValue(longValue7);
            }
            addFieldValue(ChronoField.HOUR_OF_DAY, longValue7 / 60);
            addFieldValue(ChronoField.MINUTE_OF_HOUR, longValue7 % 60);
        }
        if (resolverStyle != ResolverStyle.LENIENT) {
            if (this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND)) {
                ChronoField chronoField5 = ChronoField.MILLI_OF_SECOND;
                chronoField5.checkValidValue(this.fieldValues.get(chronoField5).longValue());
            }
            if (this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND)) {
                ChronoField chronoField6 = ChronoField.MICRO_OF_SECOND;
                chronoField6.checkValidValue(this.fieldValues.get(chronoField6).longValue());
            }
        }
        if (this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND) && this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND)) {
            addFieldValue(ChronoField.MICRO_OF_SECOND, (this.fieldValues.get(ChronoField.MICRO_OF_SECOND).longValue() % 1000) + (this.fieldValues.remove(ChronoField.MILLI_OF_SECOND).longValue() * 1000));
        }
        if (this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND) && this.fieldValues.containsKey(ChronoField.NANO_OF_SECOND)) {
            addFieldValue(ChronoField.MICRO_OF_SECOND, this.fieldValues.get(ChronoField.NANO_OF_SECOND).longValue() / 1000);
            this.fieldValues.remove(ChronoField.MICRO_OF_SECOND);
        }
        if (this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND) && this.fieldValues.containsKey(ChronoField.NANO_OF_SECOND)) {
            addFieldValue(ChronoField.MILLI_OF_SECOND, this.fieldValues.get(ChronoField.NANO_OF_SECOND).longValue() / 1000000);
            this.fieldValues.remove(ChronoField.MILLI_OF_SECOND);
        }
        if (this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND)) {
            addFieldValue(ChronoField.NANO_OF_SECOND, this.fieldValues.remove(ChronoField.MICRO_OF_SECOND).longValue() * 1000);
        } else if (!this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND)) {
        } else {
            addFieldValue(ChronoField.NANO_OF_SECOND, this.fieldValues.remove(ChronoField.MILLI_OF_SECOND).longValue() * 1000000);
        }
    }

    private DateTimeBuilder putFieldValue0(TemporalField temporalField, long j) {
        this.fieldValues.put(temporalField, Long.valueOf(j));
        return this;
    }

    private boolean resolveFields(ResolverStyle resolverStyle) {
        int i = 0;
        loop0: while (i < 100) {
            for (Map.Entry<TemporalField, Long> entry : this.fieldValues.entrySet()) {
                TemporalField key = entry.getKey();
                TemporalAccessor resolve = key.resolve(this.fieldValues, this, resolverStyle);
                if (resolve != null) {
                    if (resolve instanceof ChronoZonedDateTime) {
                        ChronoZonedDateTime chronoZonedDateTime = (ChronoZonedDateTime) resolve;
                        ZoneId zoneId = this.zone;
                        if (zoneId == null) {
                            this.zone = chronoZonedDateTime.getZone();
                        } else if (!zoneId.equals(chronoZonedDateTime.getZone())) {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ChronoZonedDateTime must use the effective parsed zone: ");
                            outline107.append(this.zone);
                            throw new DateTimeException(outline107.toString());
                        }
                        resolve = chronoZonedDateTime.toLocalDateTime();
                    }
                    if (resolve instanceof ChronoLocalDate) {
                        resolveMakeChanges(key, (ChronoLocalDate) resolve);
                    } else if (resolve instanceof LocalTime) {
                        resolveMakeChanges(key, (LocalTime) resolve);
                    } else if (resolve instanceof ChronoLocalDateTime) {
                        ChronoLocalDateTime chronoLocalDateTime = (ChronoLocalDateTime) resolve;
                        resolveMakeChanges(key, chronoLocalDateTime.mo12900toLocalDate());
                        resolveMakeChanges(key, chronoLocalDateTime.toLocalTime());
                    } else {
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unknown type: ");
                        outline1072.append(resolve.getClass().getName());
                        throw new DateTimeException(outline1072.toString());
                    }
                } else if (!this.fieldValues.containsKey(key)) {
                    break;
                }
                i++;
            }
        }
        if (i != 100) {
            return i > 0;
        }
        throw new DateTimeException("Badly written field");
    }

    private void resolveFractional() {
        if (this.time == null) {
            if (!this.fieldValues.containsKey(ChronoField.INSTANT_SECONDS) && !this.fieldValues.containsKey(ChronoField.SECOND_OF_DAY) && !this.fieldValues.containsKey(ChronoField.SECOND_OF_MINUTE)) {
                return;
            }
            if (this.fieldValues.containsKey(ChronoField.NANO_OF_SECOND)) {
                long longValue = this.fieldValues.get(ChronoField.NANO_OF_SECOND).longValue();
                this.fieldValues.put(ChronoField.MICRO_OF_SECOND, Long.valueOf(longValue / 1000));
                this.fieldValues.put(ChronoField.MILLI_OF_SECOND, Long.valueOf(longValue / 1000000));
                return;
            }
            this.fieldValues.put(ChronoField.NANO_OF_SECOND, 0L);
            this.fieldValues.put(ChronoField.MICRO_OF_SECOND, 0L);
            this.fieldValues.put(ChronoField.MILLI_OF_SECOND, 0L);
        }
    }

    private void resolveInstant() {
        if (this.date == null || this.time == null) {
            return;
        }
        Long l = this.fieldValues.get(ChronoField.OFFSET_SECONDS);
        if (l != null) {
            this.fieldValues.put(ChronoField.INSTANT_SECONDS, Long.valueOf(this.date.mo12887atTime(this.time).atZone(ZoneOffset.ofTotalSeconds(l.intValue())).getLong(ChronoField.INSTANT_SECONDS)));
        } else if (this.zone == null) {
        } else {
            this.fieldValues.put(ChronoField.INSTANT_SECONDS, Long.valueOf(this.date.mo12887atTime(this.time).atZone(this.zone).getLong(ChronoField.INSTANT_SECONDS)));
        }
    }

    private void resolveMakeChanges(TemporalField temporalField, ChronoLocalDate chronoLocalDate) {
        if (this.chrono.equals(chronoLocalDate.mo13054getChronology())) {
            long epochDay = chronoLocalDate.toEpochDay();
            Long put = this.fieldValues.put(ChronoField.EPOCH_DAY, Long.valueOf(epochDay));
            if (put == null || put.longValue() == epochDay) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Conflict found: ");
            outline107.append(LocalDate.ofEpochDay(put.longValue()));
            outline107.append(" differs from ");
            outline107.append(LocalDate.ofEpochDay(epochDay));
            outline107.append(" while resolving  ");
            outline107.append(temporalField);
            throw new DateTimeException(outline107.toString());
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ChronoLocalDate must use the effective parsed chronology: ");
        outline1072.append(this.chrono);
        throw new DateTimeException(outline1072.toString());
    }

    private void resolveTimeInferZeroes(ResolverStyle resolverStyle) {
        Long l = this.fieldValues.get(ChronoField.HOUR_OF_DAY);
        Long l2 = this.fieldValues.get(ChronoField.MINUTE_OF_HOUR);
        Long l3 = this.fieldValues.get(ChronoField.SECOND_OF_MINUTE);
        Long l4 = this.fieldValues.get(ChronoField.NANO_OF_SECOND);
        if (l == null) {
            return;
        }
        if (l2 == null && (l3 != null || l4 != null)) {
            return;
        }
        if (l2 != null && l3 == null && l4 != null) {
            return;
        }
        if (resolverStyle != ResolverStyle.LENIENT) {
            if (resolverStyle == ResolverStyle.SMART && l.longValue() == 24 && ((l2 == null || l2.longValue() == 0) && ((l3 == null || l3.longValue() == 0) && (l4 == null || l4.longValue() == 0)))) {
                l = 0L;
                this.excessDays = Period.ofDays(1);
            }
            int checkValidIntValue = ChronoField.HOUR_OF_DAY.checkValidIntValue(l.longValue());
            if (l2 != null) {
                int checkValidIntValue2 = ChronoField.MINUTE_OF_HOUR.checkValidIntValue(l2.longValue());
                if (l3 != null) {
                    int checkValidIntValue3 = ChronoField.SECOND_OF_MINUTE.checkValidIntValue(l3.longValue());
                    if (l4 != null) {
                        addObject(LocalTime.of(checkValidIntValue, checkValidIntValue2, checkValidIntValue3, ChronoField.NANO_OF_SECOND.checkValidIntValue(l4.longValue())));
                    } else {
                        addObject(LocalTime.of(checkValidIntValue, checkValidIntValue2, checkValidIntValue3));
                    }
                } else if (l4 == null) {
                    addObject(LocalTime.of(checkValidIntValue, checkValidIntValue2));
                }
            } else if (l3 == null && l4 == null) {
                addObject(LocalTime.of(checkValidIntValue, 0));
            }
        } else {
            long longValue = l.longValue();
            if (l2 == null) {
                int safeToInt = Jdk8Methods.safeToInt(Jdk8Methods.floorDiv(longValue, 24L));
                addObject(LocalTime.of(Jdk8Methods.floorMod(longValue, 24), 0));
                this.excessDays = Period.ofDays(safeToInt);
            } else if (l3 != null) {
                if (l4 == null) {
                    l4 = 0L;
                }
                long safeAdd = Jdk8Methods.safeAdd(Jdk8Methods.safeAdd(Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(longValue, 3600000000000L), Jdk8Methods.safeMultiply(l2.longValue(), 60000000000L)), Jdk8Methods.safeMultiply(l3.longValue(), (long) C.NANOS_PER_SECOND)), l4.longValue());
                addObject(LocalTime.ofNanoOfDay(Jdk8Methods.floorMod(safeAdd, 86400000000000L)));
                this.excessDays = Period.ofDays((int) Jdk8Methods.floorDiv(safeAdd, 86400000000000L));
            } else {
                long safeAdd2 = Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(longValue, 3600L), Jdk8Methods.safeMultiply(l2.longValue(), 60L));
                addObject(LocalTime.ofSecondOfDay(Jdk8Methods.floorMod(safeAdd2, 86400L)));
                this.excessDays = Period.ofDays((int) Jdk8Methods.floorDiv(safeAdd2, 86400L));
            }
        }
        this.fieldValues.remove(ChronoField.HOUR_OF_DAY);
        this.fieldValues.remove(ChronoField.MINUTE_OF_HOUR);
        this.fieldValues.remove(ChronoField.SECOND_OF_MINUTE);
        this.fieldValues.remove(ChronoField.NANO_OF_SECOND);
    }

    DateTimeBuilder addFieldValue(TemporalField temporalField, long j) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Long fieldValue0 = getFieldValue0(temporalField);
        if (fieldValue0 != null && fieldValue0.longValue() != j) {
            throw new DateTimeException("Conflict found: " + temporalField + " " + fieldValue0 + " differs from " + temporalField + " " + j + RealTimeTextConstants.COLON_SPACE + this);
        }
        return putFieldValue0(temporalField, j);
    }

    void addObject(ChronoLocalDate chronoLocalDate) {
        this.date = chronoLocalDate;
    }

    public <R> R build(TemporalQuery<R> temporalQuery) {
        return temporalQuery.mo13072queryFrom(this);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Long fieldValue0 = getFieldValue0(temporalField);
        if (fieldValue0 == null) {
            ChronoLocalDate chronoLocalDate = this.date;
            if (chronoLocalDate != null && chronoLocalDate.isSupported(temporalField)) {
                return this.date.getLong(temporalField);
            }
            LocalTime localTime = this.time;
            if (localTime != null && localTime.isSupported(temporalField)) {
                return this.time.getLong(temporalField);
            }
            throw new DateTimeException(GeneratedOutlineSupport1.outline82("Field not found: ", temporalField));
        }
        return fieldValue0.longValue();
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        ChronoLocalDate chronoLocalDate;
        LocalTime localTime;
        if (temporalField == null) {
            return false;
        }
        return this.fieldValues.containsKey(temporalField) || ((chronoLocalDate = this.date) != null && chronoLocalDate.isSupported(temporalField)) || ((localTime = this.time) != null && localTime.isSupported(temporalField));
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.zoneId()) {
            return (R) this.zone;
        }
        if (temporalQuery == TemporalQueries.chronology()) {
            return (R) this.chrono;
        }
        if (temporalQuery == TemporalQueries.localDate()) {
            ChronoLocalDate chronoLocalDate = this.date;
            if (chronoLocalDate == null) {
                return null;
            }
            return (R) LocalDate.from((TemporalAccessor) chronoLocalDate);
        } else if (temporalQuery == TemporalQueries.localTime()) {
            return (R) this.time;
        } else {
            if (temporalQuery != TemporalQueries.zone() && temporalQuery != TemporalQueries.offset()) {
                if (temporalQuery != TemporalQueries.precision()) {
                    return temporalQuery.mo13072queryFrom(this);
                }
                return null;
            }
            return temporalQuery.mo13072queryFrom(this);
        }
    }

    public DateTimeBuilder resolve(ResolverStyle resolverStyle, Set<TemporalField> set) {
        ChronoLocalDate chronoLocalDate;
        if (set != null) {
            this.fieldValues.keySet().retainAll(set);
        }
        mergeInstantFields();
        mergeDate(resolverStyle);
        mergeTime(resolverStyle);
        if (resolveFields(resolverStyle)) {
            mergeInstantFields();
            mergeDate(resolverStyle);
            mergeTime(resolverStyle);
        }
        resolveTimeInferZeroes(resolverStyle);
        crossCheck();
        Period period = this.excessDays;
        if (period != null && !period.isZero() && (chronoLocalDate = this.date) != null && this.time != null) {
            this.date = chronoLocalDate.mo13059plus((TemporalAmount) this.excessDays);
            this.excessDays = Period.ZERO;
        }
        resolveFractional();
        resolveInstant();
        return this;
    }

    public String toString() {
        StringBuilder outline105 = GeneratedOutlineSupport1.outline105(128, "DateTimeBuilder[");
        if (this.fieldValues.size() > 0) {
            outline105.append("fields=");
            outline105.append(this.fieldValues);
        }
        outline105.append(", ");
        outline105.append(this.chrono);
        outline105.append(", ");
        outline105.append(this.zone);
        outline105.append(", ");
        outline105.append(this.date);
        outline105.append(", ");
        outline105.append(this.time);
        outline105.append(JsonReaderKt.END_LIST);
        return outline105.toString();
    }

    void addObject(LocalTime localTime) {
        this.time = localTime;
    }

    public DateTimeBuilder(TemporalField temporalField, long j) {
        addFieldValue(temporalField, j);
    }

    private void crossCheck(TemporalAccessor temporalAccessor) {
        Iterator<Map.Entry<TemporalField, Long>> it2 = this.fieldValues.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<TemporalField, Long> next = it2.next();
            TemporalField key = next.getKey();
            long longValue = next.getValue().longValue();
            if (temporalAccessor.isSupported(key)) {
                try {
                    long j = temporalAccessor.getLong(key);
                    if (j == longValue) {
                        it2.remove();
                    } else {
                        throw new DateTimeException("Cross check failed: " + key + " " + j + " vs " + key + " " + longValue);
                    }
                } catch (RuntimeException unused) {
                    continue;
                }
            }
        }
    }

    private void resolveMakeChanges(TemporalField temporalField, LocalTime localTime) {
        long nanoOfDay = localTime.toNanoOfDay();
        Long put = this.fieldValues.put(ChronoField.NANO_OF_DAY, Long.valueOf(nanoOfDay));
        if (put == null || put.longValue() == nanoOfDay) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Conflict found: ");
        outline107.append(LocalTime.ofNanoOfDay(put.longValue()));
        outline107.append(" differs from ");
        outline107.append(localTime);
        outline107.append(" while resolving  ");
        outline107.append(temporalField);
        throw new DateTimeException(outline107.toString());
    }
}
