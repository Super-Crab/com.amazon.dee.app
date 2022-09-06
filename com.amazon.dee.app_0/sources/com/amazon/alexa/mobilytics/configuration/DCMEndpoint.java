package com.amazon.alexa.mobilytics.configuration;

import androidx.annotation.NonNull;
import com.google.common.base.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes9.dex */
public class DCMEndpoint implements Endpoint {
    private final String priority;
    private final String tag;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface Priority {
        public static final String HIGH = "High";
        public static final String NORMAL = "Normal";
    }

    public DCMEndpoint(@NonNull String str, @NonNull String str2) {
        this.priority = (String) Preconditions.checkNotNull(str);
        this.tag = (String) Preconditions.checkNotNull(str2);
    }

    @NonNull
    public String priority() {
        return this.priority;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.Endpoint
    @NonNull
    public String tag() {
        return this.tag;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.Endpoint
    public int type() {
        return 1;
    }
}
