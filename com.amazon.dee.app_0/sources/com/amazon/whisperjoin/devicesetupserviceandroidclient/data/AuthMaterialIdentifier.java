package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes13.dex */
public class AuthMaterialIdentifier {
    private final String mAuthMaterialIndex;
    private final String mProductIndex;

    /* loaded from: classes13.dex */
    public static class Builder {
        private String mAuthMaterialIndex;
        private String mProductIndex;

        public AuthMaterialIdentifier build() {
            validate();
            return new AuthMaterialIdentifier(this);
        }

        public Builder setAuthMaterialIndex(String str) {
            this.mAuthMaterialIndex = str;
            return this;
        }

        public Builder setProductIndex(String str) {
            this.mProductIndex = str;
            return this;
        }

        protected void validate() {
            RequestInputValidator.validateAuthMaterialIndex(this.mAuthMaterialIndex);
            RequestInputValidator.validateProductIndex(this.mProductIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AuthMaterialIdentifier(Builder builder) {
        this.mAuthMaterialIndex = builder.mAuthMaterialIndex;
        this.mProductIndex = builder.mProductIndex;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthMaterialIdentifier authMaterialIdentifier = (AuthMaterialIdentifier) obj;
        return Objects.equal(this.mAuthMaterialIndex, authMaterialIdentifier.mAuthMaterialIndex) && Objects.equal(this.mProductIndex, authMaterialIdentifier.mProductIndex);
    }

    public String getAuthMaterialIndex() {
        return this.mAuthMaterialIndex;
    }

    public String getProductIndex() {
        return this.mProductIndex;
    }

    public int hashCode() {
        return Objects.hashCode(this.mAuthMaterialIndex, this.mProductIndex);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mAuthMaterialIndex", this.mAuthMaterialIndex).add("mProductIndex", this.mProductIndex).toString();
    }
}
