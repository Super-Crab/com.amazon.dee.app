package com.amazon.alexa.identity.api;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes9.dex */
public final class UserProfile {
    private final CommsProfile commsProfile;
    private final String directedId;
    private final String firstName;
    private final String lastName;
    private final String personId;

    /* loaded from: classes9.dex */
    public static final class Builder {
        private CommsProfile commsProfile;
        private String directedId;
        private String firstName;
        private String lastName;
        private String personId;

        public UserProfile build() {
            return new UserProfile(this);
        }

        public Builder withCommsProfile(CommsProfile commsProfile) {
            this.commsProfile = commsProfile;
            return this;
        }

        public Builder withDirectedId(String str) {
            this.directedId = str;
            return this;
        }

        public Builder withFirstName(String str) {
            this.firstName = str;
            return this;
        }

        public Builder withLastName(String str) {
            this.lastName = str;
            return this;
        }

        public Builder withPersonId(String str) {
            this.personId = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder from(@Nullable UserProfile userProfile) {
        if (userProfile == null) {
            return builder();
        }
        return builder().withDirectedId(userProfile.getDirectedId()).withFirstName(userProfile.getFirstName()).withLastName(userProfile.getLastName()).withPersonId(userProfile.getPersonId()).withCommsProfile(userProfile.getCommsProfile());
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || UserProfile.class != obj.getClass()) {
            return false;
        }
        UserProfile userProfile = (UserProfile) obj;
        return Objects.equals(getDirectedId(), userProfile.getDirectedId()) && Objects.equals(getFirstName(), userProfile.getFirstName()) && Objects.equals(getLastName(), userProfile.getLastName()) && Objects.equals(getPersonId(), userProfile.getPersonId()) && Objects.equals(getCommsProfile(), userProfile.getCommsProfile());
    }

    public CommsProfile getCommsProfile() {
        return this.commsProfile;
    }

    public String getDirectedId() {
        return this.directedId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPersonId() {
        return this.personId;
    }

    public int hashCode() {
        return Objects.hash(this.directedId, this.firstName, this.lastName, this.personId, this.commsProfile);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UserProfile{directedId ='");
        GeneratedOutlineSupport1.outline176(outline107, this.directedId, Chars.QUOTE, ", firstName ='");
        GeneratedOutlineSupport1.outline176(outline107, this.firstName, Chars.QUOTE, ", lastName = '");
        GeneratedOutlineSupport1.outline176(outline107, this.lastName, Chars.QUOTE, ", personId = '");
        GeneratedOutlineSupport1.outline176(outline107, this.personId, Chars.QUOTE, ", commsProfile = '");
        outline107.append(this.commsProfile);
        outline107.append(Chars.QUOTE);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    private UserProfile(Builder builder) {
        this.directedId = builder.directedId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.personId = builder.personId;
        this.commsProfile = builder.commsProfile;
    }
}
