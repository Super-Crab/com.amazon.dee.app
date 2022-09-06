package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface EndpointConfiguration {
    @NonNull
    String getApsServiceUrl();

    @NonNull
    String getCdrsServiceUrl();

    @NonNull
    String getContentUrl();

    @NonNull
    String getDpsServiceUrl();

    @NonNull
    String getMetadataUrl();

    @NonNull
    String getPromptoServiceUrl();

    @NonNull
    String getThumbnailUrl();
}
