package com.amazon.identity.auth.device.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.redesign.view.carousel.ChipIconTitleViewHolder;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class AccountStatusPolicy implements Parcelable {
    public static final Parcelable.Creator<AccountStatusPolicy> CREATOR = new Parcelable.Creator<AccountStatusPolicy>() { // from class: com.amazon.identity.auth.device.api.AccountStatusPolicy.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AccountStatusPolicy mo4045createFromParcel(Parcel parcel) {
            return new AccountStatusPolicy(Policy.values()[parcel.readInt()]);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AccountStatusPolicy[] mo4046newArray(int i) {
            return new AccountStatusPolicy[i];
        }
    };
    private final Policy mPolicy;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum Policy {
        DEFAULT(ChipIconTitleViewHolder.STATE_DEFAULT),
        P1("P1");
        
        private final String mValue;

        Policy(String str) {
            this.mValue = str;
        }

        @FireOsSdk
        public String getValue() {
            return this.mValue;
        }
    }

    @FireOsSdk
    public AccountStatusPolicy(Policy policy) {
        this.mPolicy = policy;
    }

    @Override // android.os.Parcelable
    @FireOsSdk
    public int describeContents() {
        return 0;
    }

    public Policy getAccountStatusPolicy() {
        return this.mPolicy;
    }

    @Override // android.os.Parcelable
    @FireOsSdk
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mPolicy.ordinal());
    }
}
