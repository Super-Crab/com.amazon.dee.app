package com.amazon.alexa.wakeword.davs;
/* loaded from: classes11.dex */
public interface ArtifactDownloadResultListener {
    void onArtifactAlreadyUpToDate(long j, ArtifactModel artifactModel);

    void onArtifactDownloadFailure(long j, String str, Exception exc, String str2);

    void onArtifactDownloadInterrupted(long j);

    void onArtifactDownloadSuccess(long j, ArtifactModel artifactModel);
}
