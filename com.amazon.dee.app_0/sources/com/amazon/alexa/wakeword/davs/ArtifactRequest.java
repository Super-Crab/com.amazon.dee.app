package com.amazon.alexa.wakeword.davs;

import com.amazon.alexa.wakeword.davs.AutoValue_ArtifactRequest;
import com.amazon.alexa.wakeword.davs.C$AutoValue_ArtifactRequest;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.List;
import java.util.Map;
@AutoValue
/* loaded from: classes11.dex */
public abstract class ArtifactRequest {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract ArtifactRequest build();

        public abstract Builder setArtifactKey(String str);

        public abstract Builder setArtifactType(String str);

        public abstract Builder setFilters(Map<String, List<String>> map);
    }

    public static Builder builder() {
        return new C$AutoValue_ArtifactRequest.Builder();
    }

    public static TypeAdapter<ArtifactRequest> typeAdapter(Gson gson) {
        return new AutoValue_ArtifactRequest.GsonTypeAdapter(gson);
    }

    public abstract String getArtifactKey();

    public abstract String getArtifactType();

    public abstract Map<String, List<String>> getFilters();
}
