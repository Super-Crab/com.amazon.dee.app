package com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.listeners;

import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.enums.ArtifactDownloadFailureReason;
import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactIdentifier;
import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactLocation;
import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactMetadata;
/* loaded from: classes.dex */
public interface ArtifactDownloadResponse {
    void onComplete(ArtifactMetadata artifactMetadata, ArtifactIdentifier artifactIdentifier, ArtifactLocation artifactLocation);

    void onFailure(ArtifactMetadata artifactMetadata, ArtifactDownloadFailureReason artifactDownloadFailureReason);
}
