package com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models;

import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.AutoValue_ArtifactMetadata;
import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.C$AutoValue_ArtifactMetadata;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.List;
import java.util.Map;
@AutoValue
/* loaded from: classes.dex */
public abstract class ArtifactMetadata {

    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class Builder {
        public abstract ArtifactMetadata build();

        public abstract Builder setArtifactKey(String str);

        public abstract Builder setArtifactType(String str);

        public abstract Builder setFilters(Map<String, List<String>> map);
    }

    public static Builder builder() {
        return new C$AutoValue_ArtifactMetadata.Builder();
    }

    public static TypeAdapter<ArtifactMetadata> typeAdapter(Gson gson) {
        return new AutoValue_ArtifactMetadata.GsonTypeAdapter(gson);
    }

    public abstract String getArtifactKey();

    public abstract String getArtifactType();

    public abstract Map<String, List<String>> getFilters();
}
