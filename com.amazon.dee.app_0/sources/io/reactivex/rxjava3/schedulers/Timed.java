package io.reactivex.rxjava3.schedulers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
/* loaded from: classes3.dex */
public final class Timed<T> {
    final long time;
    final TimeUnit unit;
    final T value;

    public Timed(@NonNull T value, long time, @NonNull TimeUnit unit) {
        this.value = (T) Objects.requireNonNull(value, "value is null");
        this.time = time;
        this.unit = (TimeUnit) Objects.requireNonNull(unit, "unit is null");
    }

    public boolean equals(Object other) {
        if (other instanceof Timed) {
            Timed timed = (Timed) other;
            return Objects.equals(this.value, timed.value) && this.time == timed.time && Objects.equals(this.unit, timed.unit);
        }
        return false;
    }

    public int hashCode() {
        long j = this.time;
        return this.unit.hashCode() + (((this.value.hashCode() * 31) + ((int) (j ^ (j >>> 31)))) * 31);
    }

    public long time() {
        return this.time;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Timed[time=");
        outline107.append(this.time);
        outline107.append(", unit=");
        outline107.append(this.unit);
        outline107.append(", value=");
        return GeneratedOutlineSupport1.outline88(outline107, this.value, "]");
    }

    @NonNull
    public TimeUnit unit() {
        return this.unit;
    }

    @NonNull
    public T value() {
        return this.value;
    }

    public long time(@NonNull TimeUnit unit) {
        return unit.convert(this.time, this.unit);
    }
}
