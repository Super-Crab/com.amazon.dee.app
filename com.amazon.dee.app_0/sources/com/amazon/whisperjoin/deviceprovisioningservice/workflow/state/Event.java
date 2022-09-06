package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes13.dex */
public abstract class Event<T> {
    public final T data;
    public final Throwable error;
    public final State state;

    /* loaded from: classes13.dex */
    public enum State {
        IDLE,
        IN_PROGRESS,
        UPDATE,
        SUCCESS,
        ERROR
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Event(T t, State state, Throwable th) {
        this.data = t;
        this.state = state;
        this.error = th;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Event event = (Event) obj;
        T t = this.data;
        if (t == null ? event.data != null : !t.equals(event.data)) {
            return false;
        }
        Throwable th = this.error;
        if (th == null ? event.error != null : !th.equals(event.error)) {
            return false;
        }
        return this.state == event.state;
    }

    public T getData() {
        return this.data;
    }

    public Throwable getError() {
        return this.error;
    }

    public State getState() {
        return this.state;
    }

    public int hashCode() {
        T t = this.data;
        int i = 0;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        Throwable th = this.error;
        if (th != null) {
            i = th.hashCode();
        }
        return this.state.hashCode() + ((hashCode + i) * 31);
    }

    public boolean isState(State state) {
        return state.equals(this.state);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Event{data=");
        outline107.append(this.data);
        outline107.append(", error=");
        outline107.append(this.error);
        outline107.append(", state=");
        outline107.append(this.state);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
