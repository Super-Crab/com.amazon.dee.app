package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.Objects;
/* loaded from: classes.dex */
public final class PackageCandidateIdentifier {
    public final String componentId;
    public final Integer currentVersion;
    public final String deviceSerialNumber;
    public final String deviceType;

    /* loaded from: classes.dex */
    public static final class Builder {
        private String componentId;
        private Integer currentVersion;
        private String deviceSerialNumber;
        private String deviceType;

        public PackageCandidateIdentifier build() {
            return new PackageCandidateIdentifier(this);
        }

        public Builder componentId(String str) {
            this.componentId = str;
            return this;
        }

        public Builder currentVersion(Integer num) {
            this.currentVersion = num;
            return this;
        }

        public Builder deviceSerialNumber(String str) {
            this.deviceSerialNumber = str;
            return this;
        }

        public Builder deviceType(String str) {
            this.deviceType = str;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PackageCandidateIdentifier.class != obj.getClass()) {
            return false;
        }
        PackageCandidateIdentifier packageCandidateIdentifier = (PackageCandidateIdentifier) obj;
        return Objects.equals(this.deviceType, packageCandidateIdentifier.deviceType) && Objects.equals(this.deviceSerialNumber, packageCandidateIdentifier.deviceSerialNumber) && Objects.equals(this.componentId, packageCandidateIdentifier.componentId) && Objects.equals(this.currentVersion, packageCandidateIdentifier.currentVersion);
    }

    public int hashCode() {
        return Objects.hash(this.deviceType, this.deviceSerialNumber, this.componentId, this.currentVersion);
    }

    private PackageCandidateIdentifier(Builder builder) {
        this.deviceType = builder.deviceType;
        this.deviceSerialNumber = builder.deviceSerialNumber;
        this.componentId = builder.componentId;
        this.currentVersion = builder.currentVersion;
        String str = this.deviceType;
        boolean z = true;
        Preconditions.precondition(str != null && !str.isEmpty(), "deviceType is empty!");
        String str2 = this.deviceSerialNumber;
        Preconditions.precondition(str2 != null && !str2.isEmpty(), "deviceSerialNumber is empty!");
        String str3 = this.componentId;
        Preconditions.precondition((str3 == null || str3.isEmpty()) ? false : z, "componentId is empty!");
        Preconditions.notNull(this.currentVersion, "currentVersion");
    }
}
