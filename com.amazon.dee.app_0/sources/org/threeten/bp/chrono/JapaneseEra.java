package org.threeten.bp.chrono;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.LocalDate;
import org.threeten.bp.jdk8.DefaultInterfaceEra;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: classes5.dex */
public final class JapaneseEra extends DefaultInterfaceEra implements Serializable {
    private static final int ADDITIONAL_VALUE = 4;
    static final int ERA_OFFSET = 2;
    private static final long serialVersionUID = 1466499369062886794L;
    private final int eraValue;
    private final transient String name;
    private final transient LocalDate since;
    public static final JapaneseEra MEIJI = new JapaneseEra(-1, LocalDate.of(1868, 9, 8), "Meiji");
    public static final JapaneseEra TAISHO = new JapaneseEra(0, LocalDate.of(1912, 7, 30), "Taisho");
    public static final JapaneseEra SHOWA = new JapaneseEra(1, LocalDate.of(1926, 12, 25), "Showa");
    public static final JapaneseEra HEISEI = new JapaneseEra(2, LocalDate.of(1989, 1, 8), "Heisei");
    public static final JapaneseEra REIWA = new JapaneseEra(3, LocalDate.of(2019, 5, 1), "Reiwa");
    private static final AtomicReference<JapaneseEra[]> KNOWN_ERAS = new AtomicReference<>(new JapaneseEra[]{MEIJI, TAISHO, SHOWA, HEISEI, REIWA});

    private JapaneseEra(int i, LocalDate localDate, String str) {
        this.eraValue = i;
        this.since = localDate;
        this.name = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JapaneseEra from(LocalDate localDate) {
        if (!localDate.isBefore(MEIJI.since)) {
            JapaneseEra[] japaneseEraArr = KNOWN_ERAS.get();
            for (int length = japaneseEraArr.length - 1; length >= 0; length--) {
                JapaneseEra japaneseEra = japaneseEraArr[length];
                if (localDate.compareTo((ChronoLocalDate) japaneseEra.since) >= 0) {
                    return japaneseEra;
                }
            }
            return null;
        }
        throw new DateTimeException("Date too early: " + localDate);
    }

    public static JapaneseEra of(int i) {
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS.get();
        if (i >= MEIJI.eraValue && i <= japaneseEraArr[japaneseEraArr.length - 1].eraValue) {
            return japaneseEraArr[ordinal(i)];
        }
        throw new DateTimeException("japaneseEra is invalid");
    }

    private static int ordinal(int i) {
        return i + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JapaneseEra readExternal(DataInput dataInput) throws IOException {
        return of(dataInput.readByte());
    }

    private Object readResolve() throws ObjectStreamException {
        try {
            return of(this.eraValue);
        } catch (DateTimeException e) {
            InvalidObjectException invalidObjectException = new InvalidObjectException("Invalid era");
            invalidObjectException.initCause(e);
            throw invalidObjectException;
        }
    }

    public static JapaneseEra registerEra(LocalDate localDate, String str) {
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS.get();
        if (japaneseEraArr.length <= 5) {
            Jdk8Methods.requireNonNull(localDate, "since");
            Jdk8Methods.requireNonNull(str, "name");
            if (localDate.isAfter(REIWA.since)) {
                JapaneseEra japaneseEra = new JapaneseEra(4, localDate, str);
                JapaneseEra[] japaneseEraArr2 = (JapaneseEra[]) Arrays.copyOf(japaneseEraArr, 6);
                japaneseEraArr2[5] = japaneseEra;
                if (!KNOWN_ERAS.compareAndSet(japaneseEraArr, japaneseEraArr2)) {
                    throw new DateTimeException("Only one additional Japanese era can be added");
                }
                return japaneseEra;
            }
            throw new DateTimeException("Invalid since date for additional Japanese era, must be after Reiwa");
        }
        throw new DateTimeException("Only one additional Japanese era can be added");
    }

    public static JapaneseEra valueOf(String str) {
        JapaneseEra[] japaneseEraArr;
        Jdk8Methods.requireNonNull(str, "japaneseEra");
        for (JapaneseEra japaneseEra : KNOWN_ERAS.get()) {
            if (str.equals(japaneseEra.name)) {
                return japaneseEra;
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Era not found: ", str));
    }

    public static JapaneseEra[] values() {
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS.get();
        return (JapaneseEra[]) Arrays.copyOf(japaneseEraArr, japaneseEraArr.length);
    }

    private Object writeReplace() {
        return new Ser((byte) 2, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalDate endDate() {
        int ordinal = ordinal(this.eraValue);
        JapaneseEra[] values = values();
        if (ordinal >= values.length - 1) {
            return LocalDate.MAX;
        }
        return values[ordinal + 1].startDate().minusDays(1L);
    }

    @Override // org.threeten.bp.chrono.Era
    public int getValue() {
        return this.eraValue;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField == ChronoField.ERA) {
            return JapaneseChronology.INSTANCE.range(ChronoField.ERA);
        }
        return super.range(temporalField);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalDate startDate() {
        return this.since;
    }

    public String toString() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(getValue());
    }
}
