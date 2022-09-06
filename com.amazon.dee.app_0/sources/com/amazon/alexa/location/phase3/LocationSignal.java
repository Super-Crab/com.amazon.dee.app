package com.amazon.alexa.location.phase3;

import androidx.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;
/* loaded from: classes9.dex */
public class LocationSignal {
    public static final int SENSITIVITY_LOCAL_RESTRICTED = 2;
    public static final int SENSITIVITY_NOT_RESTRICTED = 0;
    public static final int SENSITIVITY_REMOTE_RESTRICTED = 1;
    public static final int SENSITIVITY_TO_BE_DETERMINED = -1;
    public final int dataSensitivity;
    public final String id = UUID.randomUUID().toString();
    public final long timestamp;
    public final String type;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface Sensitivity {
    }

    public LocationSignal(@NonNull String str, int i, long j) {
        this.type = str;
        this.dataSensitivity = i;
        this.timestamp = j;
    }
}
