package com.amazon.alexa.accessory.notificationpublisher.artifactdownloader;

import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.listeners.ArtifactDownloadResponse;
import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactMetadata;
/* loaded from: classes.dex */
public interface ArtifactDownloader {
    void downloadArtifact(ArtifactMetadata artifactMetadata, ArtifactDownloadResponse artifactDownloadResponse);

    Long getLastUpdatedTimeForArtifact(ArtifactMetadata artifactMetadata);

    String getPathForArtifact(ArtifactMetadata artifactMetadata);

    void removeArtifact(ArtifactMetadata artifactMetadata);
}
