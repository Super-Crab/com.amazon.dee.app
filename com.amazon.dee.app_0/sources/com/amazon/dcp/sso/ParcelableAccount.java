package com.amazon.dcp.sso;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ParcelableAccount implements Parcelable {
    public static final Parcelable.Creator<ParcelableAccount> CREATOR = new Parcelable.Creator<ParcelableAccount>() { // from class: com.amazon.dcp.sso.ParcelableAccount.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ParcelableAccount createFromParcel(Parcel parcel) {
            return new ParcelableAccount(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ParcelableAccount[] newArray(int i) {
            return new ParcelableAccount[i];
        }
    };
    private final String mAccountName;
    private final String mAccountType;

    public static ParcelableAccount[] fromAccounts(Account... accountArr) {
        if (accountArr == null) {
            return null;
        }
        ParcelableAccount[] parcelableAccountArr = new ParcelableAccount[accountArr.length];
        for (int i = 0; i < accountArr.length; i++) {
            parcelableAccountArr[i] = new ParcelableAccount(accountArr[i]);
        }
        return parcelableAccountArr;
    }

    public static Account[] toAccounts(ParcelableAccount... parcelableAccountArr) {
        if (parcelableAccountArr == null) {
            return null;
        }
        Account[] accountArr = new Account[parcelableAccountArr.length];
        for (int i = 0; i < parcelableAccountArr.length; i++) {
            if (parcelableAccountArr[i] != null) {
                accountArr[i] = parcelableAccountArr[i].getAccount();
            }
        }
        return accountArr;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && ParcelableAccount.class == obj.getClass()) {
            ParcelableAccount parcelableAccount = (ParcelableAccount) obj;
            if (TextUtils.equals(this.mAccountName, parcelableAccount.mAccountName) && TextUtils.equals(this.mAccountType, parcelableAccount.mAccountType)) {
                return true;
            }
        }
        return false;
    }

    public Account getAccount() {
        return new Account(this.mAccountName, this.mAccountType);
    }

    public int hashCode() {
        String str = this.mAccountName;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.mAccountType;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAccountName);
        parcel.writeString(this.mAccountType);
    }

    public ParcelableAccount(Account account) {
        this(account.name, account.type);
    }

    public ParcelableAccount(String str, String str2) {
        this.mAccountName = str;
        this.mAccountType = str2;
    }

    private ParcelableAccount(Parcel parcel) {
        this.mAccountName = parcel.readString();
        this.mAccountType = parcel.readString();
    }
}
