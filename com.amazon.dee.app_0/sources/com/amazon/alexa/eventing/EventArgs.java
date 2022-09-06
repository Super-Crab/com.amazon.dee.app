package com.amazon.alexa.eventing;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
@Deprecated
/* loaded from: classes7.dex */
public class EventArgs<T> {
    static final EventArgs<Object> EMPTY = new EventArgs<>(null, false);
    static final EventArgs<Object> ERROR = new EventArgs<>(null, true);
    volatile boolean canceled;
    final boolean error;
    final T value;

    EventArgs(T t, boolean z) {
        this.value = t;
        this.error = z;
    }

    public static <T> EventArgs<T> empty() {
        return (EventArgs<T>) EMPTY;
    }

    public static <T> EventArgs<T> error() {
        return (EventArgs<T>) ERROR;
    }

    public static <T> EventArgs<T> of(@NonNull T t) {
        return new EventArgs<>(t, false);
    }

    public void cancel() {
        this.canceled = true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || EventArgs.class != obj.getClass()) {
            return false;
        }
        EventArgs eventArgs = (EventArgs) obj;
        if (this.canceled == eventArgs.canceled && this.error == eventArgs.error) {
            T t = this.value;
            if (t != null) {
                if (t.equals(eventArgs.value)) {
                    return true;
                }
            } else if (eventArgs.value == null) {
                return true;
            }
        }
        return false;
    }

    public T get() {
        return this.value;
    }

    public boolean hasValue() {
        return this.value != null;
    }

    public int hashCode() {
        T t = this.value;
        return ((((t != null ? t.hashCode() : 0) * 31) + (this.canceled ? 1 : 0)) * 31) + (this.error ? 1 : 0);
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public boolean isEmpty() {
        return this.value == null && !this.error;
    }

    public boolean isError() {
        return this.error;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EventArgs{value=");
        outline107.append(this.value);
        outline107.append(", error=");
        outline107.append(this.error);
        outline107.append(", canceled=");
        outline107.append(this.canceled);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
