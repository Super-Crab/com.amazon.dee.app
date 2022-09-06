package org.joda.time.base;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.ReadableDuration;
import org.joda.time.format.FormatUtils;
/* loaded from: classes5.dex */
public abstract class AbstractDuration implements ReadableDuration {
    @Override // java.lang.Comparable
    public int compareTo(ReadableDuration readableDuration) {
        int i = (getMillis() > readableDuration.getMillis() ? 1 : (getMillis() == readableDuration.getMillis() ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    @Override // org.joda.time.ReadableDuration
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ReadableDuration) && getMillis() == ((ReadableDuration) obj).getMillis();
    }

    @Override // org.joda.time.ReadableDuration
    public int hashCode() {
        long millis = getMillis();
        return (int) (millis ^ (millis >>> 32));
    }

    @Override // org.joda.time.ReadableDuration
    public boolean isEqual(ReadableDuration readableDuration) {
        if (readableDuration == null) {
            readableDuration = Duration.ZERO;
        }
        return compareTo(readableDuration) == 0;
    }

    @Override // org.joda.time.ReadableDuration
    public boolean isLongerThan(ReadableDuration readableDuration) {
        if (readableDuration == null) {
            readableDuration = Duration.ZERO;
        }
        return compareTo(readableDuration) > 0;
    }

    @Override // org.joda.time.ReadableDuration
    public boolean isShorterThan(ReadableDuration readableDuration) {
        if (readableDuration == null) {
            readableDuration = Duration.ZERO;
        }
        return compareTo(readableDuration) < 0;
    }

    @Override // org.joda.time.ReadableDuration
    public Duration toDuration() {
        return new Duration(getMillis());
    }

    @Override // org.joda.time.ReadableDuration
    public Period toPeriod() {
        return new Period(getMillis());
    }

    @Override // org.joda.time.ReadableDuration
    public String toString() {
        long millis = getMillis();
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("PT");
        boolean z = millis < 0;
        FormatUtils.appendUnpaddedInteger(outline103, millis);
        while (true) {
            int i = 3;
            if (outline103.length() >= (z ? 7 : 6)) {
                break;
            }
            if (!z) {
                i = 2;
            }
            outline103.insert(i, "0");
        }
        if ((millis / 1000) * 1000 == millis) {
            outline103.setLength(outline103.length() - 3);
        } else {
            outline103.insert(outline103.length() - 3, ".");
        }
        outline103.append('S');
        return outline103.toString();
    }
}
