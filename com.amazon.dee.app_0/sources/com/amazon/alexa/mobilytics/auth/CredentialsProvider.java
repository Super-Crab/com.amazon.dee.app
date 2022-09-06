package com.amazon.alexa.mobilytics.auth;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.configuration.Endpoint;
import com.amazonaws.regions.Regions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes9.dex */
public interface CredentialsProvider {

    /* loaded from: classes9.dex */
    public interface Factory {
        CredentialsProvider create(@NonNull Endpoint endpoint);

        CredentialsProvider create(@NonNull String str, @NonNull Regions regions);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface Type {
        public static final int COGNITO_CREDENTIALS_PROVIDER = 0;
    }

    String id(boolean z);

    int type();
}
