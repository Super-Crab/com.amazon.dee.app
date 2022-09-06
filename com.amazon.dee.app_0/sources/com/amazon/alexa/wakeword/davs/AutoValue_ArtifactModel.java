package com.amazon.alexa.wakeword.davs;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.wakeword.davs.ArtifactModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes11.dex */
final class AutoValue_ArtifactModel extends ArtifactModel {
    private final byte[] artifactData;
    private final Long artifactDownloadedTime;
    private final String artifactIdentifier;
    private final String engineCompatibilityId;
    private final String locale;
    private final List<String> wakeWords;

    /* loaded from: classes11.dex */
    static final class Builder extends ArtifactModel.Builder {
        private byte[] artifactData;
        private Long artifactDownloadedTime;
        private String artifactIdentifier;
        private String engineCompatibilityId;
        private String locale;
        private List<String> wakeWords;

        @Override // com.amazon.alexa.wakeword.davs.ArtifactModel.Builder
        public ArtifactModel build() {
            return new AutoValue_ArtifactModel(this.artifactIdentifier, this.locale, this.engineCompatibilityId, this.artifactDownloadedTime, this.wakeWords, this.artifactData);
        }

        @Override // com.amazon.alexa.wakeword.davs.ArtifactModel.Builder
        public ArtifactModel.Builder setArtifactData(@Nullable byte[] bArr) {
            this.artifactData = bArr;
            return this;
        }

        @Override // com.amazon.alexa.wakeword.davs.ArtifactModel.Builder
        public ArtifactModel.Builder setArtifactDownloadedTime(@Nullable Long l) {
            this.artifactDownloadedTime = l;
            return this;
        }

        @Override // com.amazon.alexa.wakeword.davs.ArtifactModel.Builder
        public ArtifactModel.Builder setArtifactIdentifier(@Nullable String str) {
            this.artifactIdentifier = str;
            return this;
        }

        @Override // com.amazon.alexa.wakeword.davs.ArtifactModel.Builder
        public ArtifactModel.Builder setEngineCompatibilityId(@Nullable String str) {
            this.engineCompatibilityId = str;
            return this;
        }

        @Override // com.amazon.alexa.wakeword.davs.ArtifactModel.Builder
        public ArtifactModel.Builder setLocale(@Nullable String str) {
            this.locale = str;
            return this;
        }

        @Override // com.amazon.alexa.wakeword.davs.ArtifactModel.Builder
        public ArtifactModel.Builder setWakeWords(@Nullable List<String> list) {
            this.wakeWords = list;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ArtifactModel)) {
            return false;
        }
        ArtifactModel artifactModel = (ArtifactModel) obj;
        String str = this.artifactIdentifier;
        if (str != null ? str.equals(artifactModel.getArtifactIdentifier()) : artifactModel.getArtifactIdentifier() == null) {
            String str2 = this.locale;
            if (str2 != null ? str2.equals(artifactModel.getLocale()) : artifactModel.getLocale() == null) {
                String str3 = this.engineCompatibilityId;
                if (str3 != null ? str3.equals(artifactModel.getEngineCompatibilityId()) : artifactModel.getEngineCompatibilityId() == null) {
                    Long l = this.artifactDownloadedTime;
                    if (l != null ? l.equals(artifactModel.getArtifactDownloadedTime()) : artifactModel.getArtifactDownloadedTime() == null) {
                        List<String> list = this.wakeWords;
                        if (list != null ? list.equals(artifactModel.getWakeWords()) : artifactModel.getWakeWords() == null) {
                            if (Arrays.equals(this.artifactData, artifactModel instanceof AutoValue_ArtifactModel ? ((AutoValue_ArtifactModel) artifactModel).artifactData : artifactModel.getArtifactData())) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactModel
    @Nullable
    public byte[] getArtifactData() {
        return this.artifactData;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactModel
    @Nullable
    public Long getArtifactDownloadedTime() {
        return this.artifactDownloadedTime;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactModel
    @Nullable
    public String getArtifactIdentifier() {
        return this.artifactIdentifier;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactModel
    @Nullable
    public String getEngineCompatibilityId() {
        return this.engineCompatibilityId;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactModel
    @Nullable
    public String getLocale() {
        return this.locale;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactModel
    @Nullable
    public List<String> getWakeWords() {
        return this.wakeWords;
    }

    public int hashCode() {
        String str = this.artifactIdentifier;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.locale;
        int hashCode2 = (hashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.engineCompatibilityId;
        int hashCode3 = (hashCode2 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        Long l = this.artifactDownloadedTime;
        int hashCode4 = (hashCode3 ^ (l == null ? 0 : l.hashCode())) * 1000003;
        List<String> list = this.wakeWords;
        if (list != null) {
            i = list.hashCode();
        }
        return ((hashCode4 ^ i) * 1000003) ^ Arrays.hashCode(this.artifactData);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ArtifactModel{artifactIdentifier=");
        outline107.append(this.artifactIdentifier);
        outline107.append(", locale=");
        outline107.append(this.locale);
        outline107.append(", engineCompatibilityId=");
        outline107.append(this.engineCompatibilityId);
        outline107.append(", artifactDownloadedTime=");
        outline107.append(this.artifactDownloadedTime);
        outline107.append(", wakeWords=");
        outline107.append(this.wakeWords);
        outline107.append(", artifactData=");
        outline107.append(Arrays.toString(this.artifactData));
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    private AutoValue_ArtifactModel(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Long l, @Nullable List<String> list, @Nullable byte[] bArr) {
        this.artifactIdentifier = str;
        this.locale = str2;
        this.engineCompatibilityId = str3;
        this.artifactDownloadedTime = l;
        this.wakeWords = list;
        this.artifactData = bArr;
    }
}
