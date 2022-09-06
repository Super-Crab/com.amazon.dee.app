package com.amazon.alexa.identity.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes9.dex */
public final class UserIdentity {
    private static final String TAG = "UserIdentity";
    private final String accessToken;
    private final String countryOfResidence;
    private final UserProfile delegatedProfile;
    private final String directedId;
    private final Marketplace effectiveMarketplace;
    private final String email;
    private final boolean eulaAccepted;
    private final Set<String> features;
    private final boolean hasActiveDevices;
    private final String id;
    private final Marketplace marketplace;
    private final String name;
    private final Marketplace originalMarketplace;
    private final Map<String, String> tokens;
    private final UserProfile userProfile;

    /* loaded from: classes9.dex */
    public static final class Builder {
        private String accessToken;
        private String countryOfResidence;
        private UserProfile delegatedProfile;
        private String directedId;
        private Marketplace effectiveMarketplace;
        private String email;
        private boolean eulaAccepted;
        private boolean hasActiveDevices;
        private String id;
        private Marketplace marketplace;
        private String name;
        private Marketplace originalMarketplace;
        private UserProfile userProfile;
        private Map<String, String> tokens = new HashMap();
        private Set<String> features = new HashSet();

        public UserIdentity build() {
            return new UserIdentity(this);
        }

        public Builder hasAcceptedEula(boolean z) {
            this.eulaAccepted = z;
            return this;
        }

        public Builder hasActiveDevices(boolean z) {
            this.hasActiveDevices = z;
            return this;
        }

        @Deprecated
        public Builder withAccessToken(String str) {
            this.accessToken = str;
            return this;
        }

        public Builder withCountryOfResidence(String str) {
            this.countryOfResidence = str;
            return this;
        }

        public Builder withDelegatedProfile(UserProfile userProfile) {
            this.delegatedProfile = userProfile;
            return this;
        }

        public Builder withDirectedId(String str) {
            this.directedId = str;
            return this;
        }

        public Builder withEffectiveMarketplace(Marketplace marketplace) {
            this.effectiveMarketplace = marketplace;
            return this;
        }

        public Builder withEmail(String str) {
            this.email = str;
            return this;
        }

        public Builder withFeatures(@Nullable Set<String> set) {
            this.features.clear();
            if (set != null) {
                this.features.addAll(set);
            }
            return this;
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withMarketplace(Marketplace marketplace) {
            this.marketplace = marketplace;
            return this;
        }

        public Builder withName(String str) {
            this.name = str;
            return this;
        }

        public Builder withOriginalMarketplace(Marketplace marketplace) {
            this.originalMarketplace = marketplace;
            return this;
        }

        public Builder withTokens(@Nullable Map<String, String> map) {
            this.tokens.clear();
            if (map != null) {
                this.tokens.putAll(map);
            }
            return this;
        }

        public Builder withUserProfile(UserProfile userProfile) {
            this.userProfile = userProfile;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder from(@Nullable UserIdentity userIdentity) {
        if (userIdentity == null) {
            return builder();
        }
        return builder().withId(userIdentity.getId()).withDirectedId(userIdentity.getDirectedId()).withName(userIdentity.getName()).withEmail(userIdentity.getEmail()).withFeatures(userIdentity.getFeatures()).withOriginalMarketplace(userIdentity.getOriginalMarketplace()).withMarketplace(userIdentity.getMarketplace()).withCountryOfResidence(userIdentity.getCountryOfResidence()).withEffectiveMarketplace(userIdentity.getEffectiveMarketplace()).hasAcceptedEula(userIdentity.hasAcceptedEula()).hasActiveDevices(userIdentity.hasActiveDevices()).withAccessToken(userIdentity.getAccessToken()).withUserProfile(userIdentity.getUserProfile()).withDelegatedProfile(userIdentity.getDelegatedProfile()).withTokens(userIdentity.getTokens());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || UserIdentity.class != obj.getClass()) {
            return false;
        }
        UserIdentity userIdentity = (UserIdentity) obj;
        return Objects.equals(this.id, userIdentity.getId()) && Objects.equals(this.directedId, userIdentity.getDirectedId()) && Objects.equals(this.name, userIdentity.getName()) && Objects.equals(this.email, userIdentity.getEmail()) && Objects.equals(this.features, userIdentity.getFeatures()) && Objects.equals(this.marketplace, userIdentity.getMarketplace()) && Objects.equals(this.effectiveMarketplace, userIdentity.getEffectiveMarketplace()) && Objects.equals(this.countryOfResidence, userIdentity.countryOfResidence) && Objects.equals(Boolean.valueOf(this.eulaAccepted), Boolean.valueOf(userIdentity.hasAcceptedEula())) && Objects.equals(Boolean.valueOf(this.hasActiveDevices), Boolean.valueOf(userIdentity.hasActiveDevices())) && Objects.equals(this.accessToken, userIdentity.getAccessToken()) && Objects.equals(this.userProfile, userIdentity.getUserProfile()) && Objects.equals(this.delegatedProfile, userIdentity.getDelegatedProfile()) && Objects.equals(this.tokens, userIdentity.getTokens());
    }

    @Deprecated
    public String getAccessToken() {
        return this.accessToken;
    }

    public String getCountryOfResidence() {
        return this.countryOfResidence;
    }

    public UserProfile getDelegatedProfile() {
        return this.delegatedProfile;
    }

    public String getDirectedId() {
        return this.directedId;
    }

    public Marketplace getEffectiveMarketplace() {
        return this.effectiveMarketplace;
    }

    public String getEmail() {
        return this.email;
    }

    @NonNull
    public Set<String> getFeatures() {
        return this.features;
    }

    public String getId() {
        return this.id;
    }

    public Marketplace getMarketplace() {
        return this.marketplace;
    }

    public String getName() {
        return this.name;
    }

    public Marketplace getOriginalMarketplace() {
        return this.originalMarketplace;
    }

    public String getToken(String str) {
        if (!this.tokens.containsKey(str)) {
            return null;
        }
        return this.tokens.get(str);
    }

    @NonNull
    public Map<String, String> getTokens() {
        return this.tokens;
    }

    public UserProfile getUserProfile() {
        return this.userProfile;
    }

    public boolean hasAcceptedEula() {
        return this.eulaAccepted;
    }

    public boolean hasActiveDevices() {
        return this.hasActiveDevices;
    }

    @Deprecated
    public boolean hasFeature(String str) {
        return this.features.contains(str);
    }

    public int hashCode() {
        return Objects.hash(this.id, this.directedId, this.name, this.email, this.features, this.marketplace, this.effectiveMarketplace, this.countryOfResidence, Boolean.valueOf(this.eulaAccepted), Boolean.valueOf(this.hasActiveDevices), this.accessToken, this.userProfile, this.delegatedProfile, this.tokens);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UserIdentity{name = '");
        GeneratedOutlineSupport1.outline176(outline107, this.name, Chars.QUOTE, ", email = '");
        GeneratedOutlineSupport1.outline176(outline107, this.email, Chars.QUOTE, ", id = '");
        GeneratedOutlineSupport1.outline176(outline107, this.id, Chars.QUOTE, ", directedId = '");
        GeneratedOutlineSupport1.outline176(outline107, this.directedId, Chars.QUOTE, ", marketplace = '");
        outline107.append(this.marketplace);
        outline107.append("', effectiveMarketplace = '");
        outline107.append(this.effectiveMarketplace);
        outline107.append("', countryOfResidence = '");
        outline107.append(this.countryOfResidence);
        outline107.append("', features = ");
        outline107.append(this.features);
        outline107.append(", eulaAccepted = ");
        outline107.append(this.eulaAccepted);
        outline107.append(", hasActiveDevices = ");
        outline107.append(this.hasActiveDevices);
        outline107.append(", tokens = ");
        outline107.append(this.tokens);
        outline107.append(", accessToken = ");
        outline107.append(this.accessToken);
        outline107.append(", userProfile = ");
        outline107.append(this.userProfile);
        outline107.append(", delegatedProfile = ");
        outline107.append(this.delegatedProfile);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    private UserIdentity(Builder builder) {
        this.id = builder.id;
        this.directedId = builder.directedId;
        this.name = builder.name;
        this.email = builder.email;
        this.features = Collections.unmodifiableSet(builder.features);
        this.originalMarketplace = builder.originalMarketplace;
        this.marketplace = builder.marketplace;
        this.countryOfResidence = builder.countryOfResidence;
        this.effectiveMarketplace = builder.effectiveMarketplace;
        this.eulaAccepted = builder.eulaAccepted;
        this.hasActiveDevices = builder.hasActiveDevices;
        this.accessToken = builder.accessToken;
        this.userProfile = builder.userProfile;
        this.delegatedProfile = builder.delegatedProfile;
        this.tokens = Collections.unmodifiableMap(builder.tokens);
    }
}
