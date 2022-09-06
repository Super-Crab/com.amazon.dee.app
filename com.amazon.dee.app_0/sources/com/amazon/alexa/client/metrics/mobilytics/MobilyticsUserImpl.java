package com.amazon.alexa.client.metrics.mobilytics;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
/* loaded from: classes6.dex */
public class MobilyticsUserImpl implements MobilyticsUser {
    private final String directedId;
    private final String marketplaceId;

    /* loaded from: classes6.dex */
    public static final class Builder {
        private String directedId;
        private String marketplaceId;

        public MobilyticsUserImpl build() {
            return new MobilyticsUserImpl(this);
        }

        public Builder withDirectedId(String str) {
            this.directedId = str;
            return this;
        }

        public Builder withMarketplaceId(String str) {
            this.marketplaceId = str;
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    @Nullable
    public String attribute(MobilyticsUser.Attribute attribute) {
        return "Unknown";
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    @NonNull
    public String directedId() {
        String str = this.directedId;
        return (str == null || TextUtils.isEmpty(str)) ? "Unknown" : this.directedId;
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    public boolean hasFeature(@NonNull String str) {
        return false;
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    @NonNull
    public String marketplaceId() {
        String str = this.marketplaceId;
        return (str == null || TextUtils.isEmpty(str)) ? "Unknown" : this.marketplaceId;
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    @NonNull
    public String personId() {
        return "Unknown";
    }

    private MobilyticsUserImpl(Builder builder) {
        this.directedId = builder.directedId;
        this.marketplaceId = builder.marketplaceId;
    }
}
