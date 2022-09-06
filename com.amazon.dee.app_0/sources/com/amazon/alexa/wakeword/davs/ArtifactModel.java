package com.amazon.alexa.wakeword.davs;

import androidx.annotation.Nullable;
import com.amazon.alexa.wakeword.davs.AutoValue_ArtifactModel;
import com.google.auto.value.AutoValue;
import java.util.List;
@AutoValue
/* loaded from: classes11.dex */
public abstract class ArtifactModel {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract ArtifactModel build();

        public abstract Builder setArtifactData(@Nullable byte[] bArr);

        public abstract Builder setArtifactDownloadedTime(@Nullable Long l);

        public abstract Builder setArtifactIdentifier(@Nullable String str);

        public abstract Builder setEngineCompatibilityId(@Nullable String str);

        public abstract Builder setLocale(@Nullable String str);

        public abstract Builder setWakeWords(@Nullable List<String> list);
    }

    public static Builder builder() {
        return new AutoValue_ArtifactModel.Builder();
    }

    @Nullable
    public abstract byte[] getArtifactData();

    @Nullable
    public abstract Long getArtifactDownloadedTime();

    @Nullable
    public abstract String getArtifactIdentifier();

    @Nullable
    public abstract String getEngineCompatibilityId();

    @Nullable
    public abstract String getLocale();

    @Nullable
    public abstract List<String> getWakeWords();
}
