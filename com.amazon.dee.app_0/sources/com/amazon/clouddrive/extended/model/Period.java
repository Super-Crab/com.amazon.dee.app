package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class Period implements Comparable<Period> {
    private long numberOfIntervals;
    private String timeInterval;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Period) && compareTo((Period) obj) == 0;
    }

    public long getNumberOfIntervals() {
        return this.numberOfIntervals;
    }

    public String getTimeInterval() {
        return this.timeInterval;
    }

    public int hashCode() {
        return (getTimeInterval() == null ? 0 : getTimeInterval().hashCode()) + 1 + ((int) getNumberOfIntervals());
    }

    public void setNumberOfIntervals(long j) {
        this.numberOfIntervals = j;
    }

    public void setTimeInterval(String str) {
        this.timeInterval = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(Period period) {
        if (period == null) {
            return -1;
        }
        if (period == this) {
            return 0;
        }
        String timeInterval = getTimeInterval();
        String timeInterval2 = period.getTimeInterval();
        if (timeInterval != timeInterval2) {
            if (timeInterval == null) {
                return -1;
            }
            if (timeInterval2 == null) {
                return 1;
            }
            int compareTo = timeInterval.compareTo(timeInterval2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        if (getNumberOfIntervals() < period.getNumberOfIntervals()) {
            return -1;
        }
        return getNumberOfIntervals() > period.getNumberOfIntervals() ? 1 : 0;
    }
}
