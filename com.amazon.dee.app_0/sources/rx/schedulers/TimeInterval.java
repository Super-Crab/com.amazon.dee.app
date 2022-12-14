package rx.schedulers;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public class TimeInterval<T> {
    private final long intervalInMilliseconds;
    private final T value;

    public TimeInterval(long j, T t) {
        this.value = t;
        this.intervalInMilliseconds = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TimeInterval.class != obj.getClass()) {
            return false;
        }
        TimeInterval timeInterval = (TimeInterval) obj;
        if (this.intervalInMilliseconds != timeInterval.intervalInMilliseconds) {
            return false;
        }
        T t = this.value;
        if (t == null) {
            if (timeInterval.value != null) {
                return false;
            }
        } else if (!t.equals(timeInterval.value)) {
            return false;
        }
        return true;
    }

    public long getIntervalInMilliseconds() {
        return this.intervalInMilliseconds;
    }

    public T getValue() {
        return this.value;
    }

    public int hashCode() {
        long j = this.intervalInMilliseconds;
        int i = (((int) (j ^ (j >>> 32))) + 31) * 31;
        T t = this.value;
        return i + (t == null ? 0 : t.hashCode());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TimeInterval [intervalInMilliseconds=");
        outline107.append(this.intervalInMilliseconds);
        outline107.append(", value=");
        return GeneratedOutlineSupport1.outline88(outline107, this.value, "]");
    }
}
