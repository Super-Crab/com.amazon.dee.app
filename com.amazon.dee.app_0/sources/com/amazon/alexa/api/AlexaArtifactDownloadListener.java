package com.amazon.alexa.api;
/* loaded from: classes6.dex */
public interface AlexaArtifactDownloadListener {
    void onArtifactAlreadyUpToDate(java.util.Locale locale);

    void onArtifactDownloadFailure(ArtifactDownloadListenerFailure artifactDownloadListenerFailure);

    void onArtifactDownloadSuccess(java.util.Locale locale);
}
