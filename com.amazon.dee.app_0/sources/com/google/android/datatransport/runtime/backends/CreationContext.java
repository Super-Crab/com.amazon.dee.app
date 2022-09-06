package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.auto.value.AutoValue;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
@AutoValue
/* loaded from: classes2.dex */
public abstract class CreationContext {
    private static final String DEFAULT_BACKEND_NAME = "cct";

    public static CreationContext create(Context context, Clock clock, Clock clock2) {
        return new AutoValue_CreationContext(context, clock, clock2, DEFAULT_BACKEND_NAME);
    }

    public abstract Context getApplicationContext();

    @NonNull
    public abstract String getBackendName();

    public abstract Clock getMonotonicClock();

    public abstract Clock getWallClock();

    public static CreationContext create(Context context, Clock clock, Clock clock2, String str) {
        return new AutoValue_CreationContext(context, clock, clock2, str);
    }
}
