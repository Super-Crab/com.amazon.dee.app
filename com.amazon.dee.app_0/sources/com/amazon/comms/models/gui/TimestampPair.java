package com.amazon.comms.models.gui;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class TimestampPair {
    private Long absoluteTimeInMillis;
    private Long relativeTimeInMillis;

    /* loaded from: classes11.dex */
    public static class TimestampPairBuilder {
        private Long absoluteTimeInMillis;
        private Long relativeTimeInMillis;

        TimestampPairBuilder() {
        }

        public TimestampPairBuilder absoluteTimeInMillis(Long l) {
            this.absoluteTimeInMillis = l;
            return this;
        }

        public TimestampPair build() {
            return new TimestampPair(this.relativeTimeInMillis, this.absoluteTimeInMillis);
        }

        public TimestampPairBuilder relativeTimeInMillis(Long l) {
            this.relativeTimeInMillis = l;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TimestampPair.TimestampPairBuilder(relativeTimeInMillis=");
            outline107.append(this.relativeTimeInMillis);
            outline107.append(", absoluteTimeInMillis=");
            outline107.append(this.absoluteTimeInMillis);
            outline107.append(")");
            return outline107.toString();
        }
    }

    TimestampPair(Long l, Long l2) {
        this.relativeTimeInMillis = l;
        this.absoluteTimeInMillis = l2;
    }

    public static TimestampPairBuilder builder() {
        return new TimestampPairBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof TimestampPair;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimestampPair)) {
            return false;
        }
        TimestampPair timestampPair = (TimestampPair) obj;
        if (!timestampPair.canEqual(this)) {
            return false;
        }
        Long relativeTimeInMillis = getRelativeTimeInMillis();
        Long relativeTimeInMillis2 = timestampPair.getRelativeTimeInMillis();
        if (relativeTimeInMillis != null ? !relativeTimeInMillis.equals(relativeTimeInMillis2) : relativeTimeInMillis2 != null) {
            return false;
        }
        Long absoluteTimeInMillis = getAbsoluteTimeInMillis();
        Long absoluteTimeInMillis2 = timestampPair.getAbsoluteTimeInMillis();
        return absoluteTimeInMillis != null ? absoluteTimeInMillis.equals(absoluteTimeInMillis2) : absoluteTimeInMillis2 == null;
    }

    public Long getAbsoluteTimeInMillis() {
        return this.absoluteTimeInMillis;
    }

    public Long getRelativeTimeInMillis() {
        return this.relativeTimeInMillis;
    }

    public int hashCode() {
        Long relativeTimeInMillis = getRelativeTimeInMillis();
        int i = 43;
        int hashCode = relativeTimeInMillis == null ? 43 : relativeTimeInMillis.hashCode();
        Long absoluteTimeInMillis = getAbsoluteTimeInMillis();
        int i2 = (hashCode + 59) * 59;
        if (absoluteTimeInMillis != null) {
            i = absoluteTimeInMillis.hashCode();
        }
        return i2 + i;
    }
}
