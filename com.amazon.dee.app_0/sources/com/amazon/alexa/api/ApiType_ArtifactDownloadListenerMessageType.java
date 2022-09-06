package com.amazon.alexa.api;

import com.amazon.alexa.api.messages.AlexaMessageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public enum ApiType_ArtifactDownloadListenerMessageType implements AlexaMessageType {
    UNKNOWN,
    ON_ARTIFACT_DOWNLOAD_SUCCESS_JAVA_UTIL_LOCALE,
    ON_ARTIFACT_ALREADY_UP_TO_DATE_JAVA_UTIL_LOCALE,
    ON_ARTIFACT_DOWNLOAD_FAILURE_COM_AMAZON_ALEXA_API_ARTIFACT_DOWNLOAD_LISTENER_FAILURE;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ApiType_ArtifactDownloadListenerMessageType fromOrdinal(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("ordinal ", i, " is out of bound"));
        }
        return values()[i];
    }
}
