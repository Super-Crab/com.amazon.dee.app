package com.amazon.leaderselection;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class AccountIdentifier implements Parcelable {
    private final String value;
    static final AccountIdentifier UNKNOWN = create("UNKNOWN");
    public static final Parcelable.Creator<AccountIdentifier> CREATOR = new a();

    /* loaded from: classes12.dex */
    static class a implements Parcelable.Creator<AccountIdentifier> {
        a() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AccountIdentifier mo4076createFromParcel(Parcel parcel) {
            return new AccountIdentifier(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AccountIdentifier[] mo4077newArray(int i) {
            return new AccountIdentifier[i];
        }
    }

    protected AccountIdentifier(Parcel parcel) {
        this.value = parcel.readString();
    }

    private AccountIdentifier(String str) {
        this.value = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AccountIdentifier create(String str) {
        return (str == null || str.isEmpty()) ? UNKNOWN : new AccountIdentifier(str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && AccountIdentifier.class == obj.getClass()) {
            return Objects.equals(this.value, ((AccountIdentifier) obj).value);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return Objects.hash(this.value);
    }

    public String toString() {
        return getValue();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.value);
    }
}
