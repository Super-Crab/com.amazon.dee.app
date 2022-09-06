package org.threeten.bp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.C;
import java.io.Serializable;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.TemporalAmount;
/* loaded from: classes5.dex */
public abstract class Clock {

    /* loaded from: classes5.dex */
    static final class FixedClock extends Clock implements Serializable {
        private static final long serialVersionUID = 7430389292664866958L;
        private final Instant instant;
        private final ZoneId zone;

        FixedClock(Instant instant, ZoneId zoneId) {
            this.instant = instant;
            this.zone = zoneId;
        }

        @Override // org.threeten.bp.Clock
        public boolean equals(Object obj) {
            if (obj instanceof FixedClock) {
                FixedClock fixedClock = (FixedClock) obj;
                return this.instant.equals(fixedClock.instant) && this.zone.equals(fixedClock.zone);
            }
            return false;
        }

        @Override // org.threeten.bp.Clock
        public ZoneId getZone() {
            return this.zone;
        }

        @Override // org.threeten.bp.Clock
        public int hashCode() {
            return this.instant.hashCode() ^ this.zone.hashCode();
        }

        @Override // org.threeten.bp.Clock
        public Instant instant() {
            return this.instant;
        }

        @Override // org.threeten.bp.Clock
        public long millis() {
            return this.instant.toEpochMilli();
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FixedClock[");
            outline107.append(this.instant);
            outline107.append(",");
            outline107.append(this.zone);
            outline107.append("]");
            return outline107.toString();
        }

        @Override // org.threeten.bp.Clock
        public Clock withZone(ZoneId zoneId) {
            return zoneId.equals(this.zone) ? this : new FixedClock(this.instant, zoneId);
        }
    }

    /* loaded from: classes5.dex */
    static final class OffsetClock extends Clock implements Serializable {
        private static final long serialVersionUID = 2007484719125426256L;
        private final Clock baseClock;
        private final Duration offset;

        OffsetClock(Clock clock, Duration duration) {
            this.baseClock = clock;
            this.offset = duration;
        }

        @Override // org.threeten.bp.Clock
        public boolean equals(Object obj) {
            if (obj instanceof OffsetClock) {
                OffsetClock offsetClock = (OffsetClock) obj;
                return this.baseClock.equals(offsetClock.baseClock) && this.offset.equals(offsetClock.offset);
            }
            return false;
        }

        @Override // org.threeten.bp.Clock
        public ZoneId getZone() {
            return this.baseClock.getZone();
        }

        @Override // org.threeten.bp.Clock
        public int hashCode() {
            return this.baseClock.hashCode() ^ this.offset.hashCode();
        }

        @Override // org.threeten.bp.Clock
        public Instant instant() {
            return this.baseClock.instant().mo13059plus((TemporalAmount) this.offset);
        }

        @Override // org.threeten.bp.Clock
        public long millis() {
            return Jdk8Methods.safeAdd(this.baseClock.millis(), this.offset.toMillis());
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("OffsetClock[");
            outline107.append(this.baseClock);
            outline107.append(",");
            outline107.append(this.offset);
            outline107.append("]");
            return outline107.toString();
        }

        @Override // org.threeten.bp.Clock
        public Clock withZone(ZoneId zoneId) {
            return zoneId.equals(this.baseClock.getZone()) ? this : new OffsetClock(this.baseClock.withZone(zoneId), this.offset);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class SystemClock extends Clock implements Serializable {
        private static final long serialVersionUID = 6740630888130243051L;
        private final ZoneId zone;

        SystemClock(ZoneId zoneId) {
            this.zone = zoneId;
        }

        @Override // org.threeten.bp.Clock
        public boolean equals(Object obj) {
            if (obj instanceof SystemClock) {
                return this.zone.equals(((SystemClock) obj).zone);
            }
            return false;
        }

        @Override // org.threeten.bp.Clock
        public ZoneId getZone() {
            return this.zone;
        }

        @Override // org.threeten.bp.Clock
        public int hashCode() {
            return this.zone.hashCode() + 1;
        }

        @Override // org.threeten.bp.Clock
        public Instant instant() {
            return Instant.ofEpochMilli(millis());
        }

        @Override // org.threeten.bp.Clock
        public long millis() {
            return System.currentTimeMillis();
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SystemClock[");
            outline107.append(this.zone);
            outline107.append("]");
            return outline107.toString();
        }

        @Override // org.threeten.bp.Clock
        public Clock withZone(ZoneId zoneId) {
            return zoneId.equals(this.zone) ? this : new SystemClock(zoneId);
        }
    }

    /* loaded from: classes5.dex */
    static final class TickClock extends Clock implements Serializable {
        private static final long serialVersionUID = 6504659149906368850L;
        private final Clock baseClock;
        private final long tickNanos;

        TickClock(Clock clock, long j) {
            this.baseClock = clock;
            this.tickNanos = j;
        }

        @Override // org.threeten.bp.Clock
        public boolean equals(Object obj) {
            if (obj instanceof TickClock) {
                TickClock tickClock = (TickClock) obj;
                return this.baseClock.equals(tickClock.baseClock) && this.tickNanos == tickClock.tickNanos;
            }
            return false;
        }

        @Override // org.threeten.bp.Clock
        public ZoneId getZone() {
            return this.baseClock.getZone();
        }

        @Override // org.threeten.bp.Clock
        public int hashCode() {
            int hashCode = this.baseClock.hashCode();
            long j = this.tickNanos;
            return hashCode ^ ((int) (j ^ (j >>> 32)));
        }

        @Override // org.threeten.bp.Clock
        public Instant instant() {
            if (this.tickNanos % 1000000 == 0) {
                long millis = this.baseClock.millis();
                return Instant.ofEpochMilli(millis - Jdk8Methods.floorMod(millis, this.tickNanos / 1000000));
            }
            Instant instant = this.baseClock.instant();
            return instant.minusNanos(Jdk8Methods.floorMod(instant.getNano(), this.tickNanos));
        }

        @Override // org.threeten.bp.Clock
        public long millis() {
            long millis = this.baseClock.millis();
            return millis - Jdk8Methods.floorMod(millis, this.tickNanos / 1000000);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TickClock[");
            outline107.append(this.baseClock);
            outline107.append(",");
            outline107.append(Duration.ofNanos(this.tickNanos));
            outline107.append("]");
            return outline107.toString();
        }

        @Override // org.threeten.bp.Clock
        public Clock withZone(ZoneId zoneId) {
            return zoneId.equals(this.baseClock.getZone()) ? this : new TickClock(this.baseClock.withZone(zoneId), this.tickNanos);
        }
    }

    protected Clock() {
    }

    public static Clock fixed(Instant instant, ZoneId zoneId) {
        Jdk8Methods.requireNonNull(instant, "fixedInstant");
        Jdk8Methods.requireNonNull(zoneId, "zone");
        return new FixedClock(instant, zoneId);
    }

    public static Clock offset(Clock clock, Duration duration) {
        Jdk8Methods.requireNonNull(clock, "baseClock");
        Jdk8Methods.requireNonNull(duration, "offsetDuration");
        return duration.equals(Duration.ZERO) ? clock : new OffsetClock(clock, duration);
    }

    public static Clock system(ZoneId zoneId) {
        Jdk8Methods.requireNonNull(zoneId, "zone");
        return new SystemClock(zoneId);
    }

    public static Clock systemDefaultZone() {
        return new SystemClock(ZoneId.systemDefault());
    }

    public static Clock systemUTC() {
        return new SystemClock(ZoneOffset.UTC);
    }

    public static Clock tick(Clock clock, Duration duration) {
        Jdk8Methods.requireNonNull(clock, "baseClock");
        Jdk8Methods.requireNonNull(duration, "tickDuration");
        if (!duration.isNegative()) {
            long nanos = duration.toNanos();
            if (nanos % 1000000 != 0 && C.NANOS_PER_SECOND % nanos != 0) {
                throw new IllegalArgumentException("Invalid tick duration");
            }
            return nanos <= 1 ? clock : new TickClock(clock, nanos);
        }
        throw new IllegalArgumentException("Tick duration must not be negative");
    }

    public static Clock tickMinutes(ZoneId zoneId) {
        return new TickClock(system(zoneId), 60000000000L);
    }

    public static Clock tickSeconds(ZoneId zoneId) {
        return new TickClock(system(zoneId), C.NANOS_PER_SECOND);
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public abstract ZoneId getZone();

    public int hashCode() {
        return super.hashCode();
    }

    public abstract Instant instant();

    public long millis() {
        return instant().toEpochMilli();
    }

    public abstract Clock withZone(ZoneId zoneId);
}
