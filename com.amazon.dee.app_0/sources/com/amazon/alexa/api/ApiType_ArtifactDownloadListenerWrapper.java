package com.amazon.alexa.api;
/* loaded from: classes6.dex */
class ApiType_ArtifactDownloadListenerWrapper extends i implements AlexaArtifactDownloadListener {
    private final AlexaArtifactDownloadListener alexaArtifactDownloadListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_ArtifactDownloadListenerWrapper(AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
        this.alexaArtifactDownloadListener = alexaArtifactDownloadListener;
    }

    @Override // com.amazon.alexa.api.i, com.amazon.alexa.api.AlexaArtifactDownloadListener
    public void onArtifactAlreadyUpToDate(java.util.Locale locale) {
        this.alexaArtifactDownloadListener.onArtifactAlreadyUpToDate(locale);
    }

    @Override // com.amazon.alexa.api.i, com.amazon.alexa.api.AlexaArtifactDownloadListener
    public void onArtifactDownloadFailure(ArtifactDownloadListenerFailure artifactDownloadListenerFailure) {
        this.alexaArtifactDownloadListener.onArtifactDownloadFailure(artifactDownloadListenerFailure);
    }

    @Override // com.amazon.alexa.api.i, com.amazon.alexa.api.AlexaArtifactDownloadListener
    public void onArtifactDownloadSuccess(java.util.Locale locale) {
        this.alexaArtifactDownloadListener.onArtifactDownloadSuccess(locale);
    }
}
