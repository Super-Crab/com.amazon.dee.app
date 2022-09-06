package com.amazon.alexa.wakeword.davs;

import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public class ArtifactDownloadException extends Exception {
    private final ArtifactDownloadFailure downloadFailureReason;
    private final Exception originalException;

    private ArtifactDownloadException(ArtifactDownloadFailure artifactDownloadFailure, @Nullable Exception exc) {
        super(exc);
        this.originalException = exc;
        this.downloadFailureReason = artifactDownloadFailure;
    }

    public static ArtifactDownloadException create(ArtifactDownloadFailure artifactDownloadFailure, Exception exc) {
        return new ArtifactDownloadException(artifactDownloadFailure, exc);
    }

    public ArtifactDownloadFailure getDownloadFailureReason() {
        return this.downloadFailureReason;
    }

    public Exception getUnderlyingException() {
        return this.originalException;
    }

    public static ArtifactDownloadException create(ArtifactDownloadFailure artifactDownloadFailure) {
        return new ArtifactDownloadException(artifactDownloadFailure, null);
    }
}
