package com.amazon.alexa.location.phase3.evaluator;

import androidx.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes9.dex */
public abstract class GeofenceTriggerEvent extends LocationEvent {
    public static final int DWELL = 2;
    public static final int ENTER = 0;
    public static final int EXIT = 1;
    public static final int NONE = -1;
    @NonNull
    public final String geofenceId;
    public final int transitionType;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface TransitionType {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GeofenceTriggerEvent(String str, long j, int i, @NonNull String str2) {
        super(str, 2, j);
        this.transitionType = i;
        this.geofenceId = str2;
    }
}
