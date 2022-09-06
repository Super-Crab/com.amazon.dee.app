package com.amazon.deecomms.alexa;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.Enum;
/* loaded from: classes12.dex */
public abstract class InCallEvent<T extends Enum, V> {
    protected static final String ACTION = "ACTION";
    public static final String IN_CALL_EVENT = "inCallEvent";
    @Nullable
    protected String callId;
    @NonNull
    protected final V data;
    @NonNull
    protected final T eventName;

    public InCallEvent(@NonNull T t, @Nullable String str, @NonNull V v) {
        this.eventName = t;
        this.data = v;
        this.callId = str;
    }

    @Nullable
    public String getCallId() {
        return this.callId;
    }

    @NonNull
    public Object getData() {
        return this.data;
    }

    @NonNull
    public String getEventName() {
        return this.eventName.toString();
    }

    @NonNull
    public abstract Bundle toBundle();
}
