package com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models;

import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.AutoValue_ArtifactManifest;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes.dex */
public abstract class ArtifactManifest {
    public static TypeAdapter<ArtifactManifest> typeAdapter(Gson gson) {
        return new AutoValue_ArtifactManifest.GsonTypeAdapter(gson);
    }

    public abstract String getArtifactIdentifier();

    public abstract String getArtifactKey();

    public abstract Long getArtifactSize();

    public abstract Long getArtifactTimeToLive();

    public abstract String getArtifactType();

    public abstract Checksum getChecksum();

    public abstract String getDownloadUrl();

    public abstract Long getUrlExpiryEpoch();
}
