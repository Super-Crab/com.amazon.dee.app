package com.amazon.alexa.identity.api;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes9.dex */
public final class CommsProfile {
    private final String aor;
    private final String commsId;
    private final String email;
    private final String hashedCommsId;
    private final String householdId;
    private final String phoneNumber;

    /* loaded from: classes9.dex */
    public static final class Builder {
        private String aor;
        private String commsId;
        private String email;
        private String hashedCommsId;
        private String householdId;
        private String phoneNumber;

        public CommsProfile build() {
            return new CommsProfile(this);
        }

        public Builder withAor(String str) {
            this.aor = str;
            return this;
        }

        public Builder withCommsId(String str) {
            this.commsId = str;
            return this;
        }

        public Builder withEmail(String str) {
            this.email = str;
            return this;
        }

        public Builder withHashedCommsId(String str) {
            this.hashedCommsId = str;
            return this;
        }

        public Builder withHouseholdId(String str) {
            this.householdId = str;
            return this;
        }

        public Builder withPhoneNumber(String str) {
            this.phoneNumber = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder from(@Nullable CommsProfile commsProfile) {
        if (commsProfile == null) {
            return builder();
        }
        return builder().withCommsId(commsProfile.getCommsId()).withPhoneNumber(commsProfile.getPhoneNumber()).withAor(commsProfile.getAor()).withHashedCommsId(commsProfile.getHashedCommsId()).withHouseholdId(commsProfile.getHouseholdId()).withEmail(commsProfile.getEmail());
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CommsProfile.class != obj.getClass()) {
            return false;
        }
        CommsProfile commsProfile = (CommsProfile) obj;
        return Objects.equals(getCommsId(), commsProfile.getCommsId()) && Objects.equals(getPhoneNumber(), commsProfile.getPhoneNumber()) && Objects.equals(getAor(), commsProfile.getAor()) && Objects.equals(getHashedCommsId(), commsProfile.getHashedCommsId()) && Objects.equals(getHouseholdId(), commsProfile.getHouseholdId()) && Objects.equals(getEmail(), commsProfile.getEmail());
    }

    public String getAor() {
        return this.aor;
    }

    public String getCommsId() {
        return this.commsId;
    }

    public String getEmail() {
        return this.email;
    }

    public String getHashedCommsId() {
        return this.hashedCommsId;
    }

    public String getHouseholdId() {
        return this.householdId;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public int hashCode() {
        return Objects.hash(this.commsId, this.phoneNumber, this.aor, this.hashedCommsId, this.householdId, this.email);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Comms{commsId ='");
        GeneratedOutlineSupport1.outline176(outline107, this.commsId, Chars.QUOTE, ", phoneNumber ='");
        GeneratedOutlineSupport1.outline176(outline107, this.phoneNumber, Chars.QUOTE, ", aor ='");
        GeneratedOutlineSupport1.outline176(outline107, this.aor, Chars.QUOTE, ", hashedCommsId ='");
        GeneratedOutlineSupport1.outline176(outline107, this.hashedCommsId, Chars.QUOTE, ", householdId ='");
        GeneratedOutlineSupport1.outline176(outline107, this.householdId, Chars.QUOTE, ", email ='");
        return GeneratedOutlineSupport1.outline90(outline107, this.email, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    private CommsProfile(Builder builder) {
        this.commsId = builder.commsId;
        this.phoneNumber = builder.phoneNumber;
        this.aor = builder.aor;
        this.hashedCommsId = builder.hashedCommsId;
        this.householdId = builder.householdId;
        this.email = builder.email;
    }
}
