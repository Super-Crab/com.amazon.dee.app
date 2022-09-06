package com.amazon.alexa.location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
final class LocationNetworkServiceConfig {
    @NonNull
    private final String authToken;
    @NonNull
    private final String buildStage;
    @Nullable
    private final String personId;
    @NonNull
    private final String preferredMarketplace;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocationNetworkServiceConfig(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4) {
        this.authToken = str;
        this.buildStage = str2;
        this.preferredMarketplace = str3;
        this.personId = str4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public String getAuthToken() {
        return this.authToken;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public String getBuildStage() {
        return this.buildStage;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public String getPersonId() {
        return this.personId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public String getPreferredMarketplace() {
        return this.preferredMarketplace;
    }
}
