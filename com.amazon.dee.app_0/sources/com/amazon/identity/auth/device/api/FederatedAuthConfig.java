package com.amazon.identity.auth.device.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class FederatedAuthConfig implements Parcelable {
    public static final Parcelable.Creator<FederatedAuthConfig> CREATOR = new Parcelable.Creator<FederatedAuthConfig>() { // from class: com.amazon.identity.auth.device.api.FederatedAuthConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public FederatedAuthConfig mo4047createFromParcel(Parcel parcel) {
            return new FederatedAuthConfig(parcel.readString(), parcel.readString(), IdentityProvider.values()[parcel.readInt()]);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public FederatedAuthConfig[] mo4048newArray(int i) {
            return new FederatedAuthConfig[i];
        }
    };
    private final String mAccessToken;
    private final IdentityProvider mIdentityProvider;
    private final String mRelyingParty;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum IdentityProvider {
        GOOGLE("GOOGLE"),
        FACEBOOK("FACEBOOK"),
        LOGIN_WITH_AMAZON("LoginWithAmazon");
        
        private final String mValue;

        IdentityProvider(String str) {
            this.mValue = str;
        }

        @FireOsSdk
        public String getValue() {
            return this.mValue;
        }
    }

    @FireOsSdk
    public FederatedAuthConfig(String str, String str2, IdentityProvider identityProvider) {
        if (str != null) {
            if (str2 == null) {
                throw new IllegalArgumentException("relyingParty is required");
            }
            if (identityProvider != null) {
                this.mAccessToken = str;
                this.mRelyingParty = str2;
                this.mIdentityProvider = identityProvider;
                return;
            }
            throw new IllegalArgumentException("identityProvider is required");
        }
        throw new IllegalArgumentException("mAccessToken is required");
    }

    @Override // android.os.Parcelable
    @FireOsSdk
    public int describeContents() {
        return 0;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public IdentityProvider getIdentityProvider() {
        return this.mIdentityProvider;
    }

    public String getRelyingParty() {
        return this.mRelyingParty;
    }

    @Override // android.os.Parcelable
    @FireOsSdk
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAccessToken);
        parcel.writeString(this.mRelyingParty);
        parcel.writeInt(this.mIdentityProvider.ordinal());
    }
}
