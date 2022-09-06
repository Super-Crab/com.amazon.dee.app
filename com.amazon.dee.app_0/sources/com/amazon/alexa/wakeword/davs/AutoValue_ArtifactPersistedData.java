package com.amazon.alexa.wakeword.davs;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.wakeword.davs.ArtifactPersistedData;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
final class AutoValue_ArtifactPersistedData extends ArtifactPersistedData {
    private final String artifactIdentifier;
    private final long downloadTime;
    private final String engineCompatibilityId;
    private final String locale;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Builder extends ArtifactPersistedData.Builder {
        private String artifactIdentifier;
        private Long downloadTime;
        private String engineCompatibilityId;
        private String locale;

        @Override // com.amazon.alexa.wakeword.davs.ArtifactPersistedData.Builder
        public ArtifactPersistedData build() {
            String str = "";
            if (this.downloadTime == null) {
                str = GeneratedOutlineSupport1.outline72(str, " downloadTime");
            }
            if (str.isEmpty()) {
                return new AutoValue_ArtifactPersistedData(this.locale, this.artifactIdentifier, this.downloadTime.longValue(), this.engineCompatibilityId);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.wakeword.davs.ArtifactPersistedData.Builder
        public ArtifactPersistedData.Builder setArtifactIdentifier(@Nullable String str) {
            this.artifactIdentifier = str;
            return this;
        }

        @Override // com.amazon.alexa.wakeword.davs.ArtifactPersistedData.Builder
        public ArtifactPersistedData.Builder setDownloadTime(long j) {
            this.downloadTime = Long.valueOf(j);
            return this;
        }

        @Override // com.amazon.alexa.wakeword.davs.ArtifactPersistedData.Builder
        public ArtifactPersistedData.Builder setEngineCompatibilityId(@Nullable String str) {
            this.engineCompatibilityId = str;
            return this;
        }

        @Override // com.amazon.alexa.wakeword.davs.ArtifactPersistedData.Builder
        public ArtifactPersistedData.Builder setLocale(@Nullable String str) {
            this.locale = str;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ArtifactPersistedData)) {
            return false;
        }
        ArtifactPersistedData artifactPersistedData = (ArtifactPersistedData) obj;
        String str = this.locale;
        if (str != null ? str.equals(artifactPersistedData.getLocale()) : artifactPersistedData.getLocale() == null) {
            String str2 = this.artifactIdentifier;
            if (str2 != null ? str2.equals(artifactPersistedData.getArtifactIdentifier()) : artifactPersistedData.getArtifactIdentifier() == null) {
                if (this.downloadTime == artifactPersistedData.getDownloadTime()) {
                    String str3 = this.engineCompatibilityId;
                    if (str3 == null) {
                        if (artifactPersistedData.getEngineCompatibilityId() == null) {
                            return true;
                        }
                    } else if (str3.equals(artifactPersistedData.getEngineCompatibilityId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactPersistedData
    @Nullable
    public String getArtifactIdentifier() {
        return this.artifactIdentifier;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactPersistedData
    public long getDownloadTime() {
        return this.downloadTime;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactPersistedData
    @Nullable
    public String getEngineCompatibilityId() {
        return this.engineCompatibilityId;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactPersistedData
    @Nullable
    public String getLocale() {
        return this.locale;
    }

    public int hashCode() {
        String str = this.locale;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.artifactIdentifier;
        int hashCode2 = str2 == null ? 0 : str2.hashCode();
        long j = this.downloadTime;
        int i2 = (((hashCode ^ hashCode2) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        String str3 = this.engineCompatibilityId;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return i2 ^ i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ArtifactPersistedData{locale=");
        outline107.append(this.locale);
        outline107.append(", artifactIdentifier=");
        outline107.append(this.artifactIdentifier);
        outline107.append(", downloadTime=");
        outline107.append(this.downloadTime);
        outline107.append(", engineCompatibilityId=");
        return GeneratedOutlineSupport1.outline91(outline107, this.engineCompatibilityId, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private AutoValue_ArtifactPersistedData(@Nullable String str, @Nullable String str2, long j, @Nullable String str3) {
        this.locale = str;
        this.artifactIdentifier = str2;
        this.downloadTime = j;
        this.engineCompatibilityId = str3;
    }
}
