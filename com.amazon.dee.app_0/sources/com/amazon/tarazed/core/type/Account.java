package com.amazon.tarazed.core.type;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Account.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J9\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0019"}, d2 = {"Lcom/amazon/tarazed/core/type/Account;", "Ljava/io/Serializable;", "accountId", "", "preferredMarketplace", "deviceType", "deviceSerialNumber", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccountId", "()Ljava/lang/String;", "getDeviceSerialNumber", "getDeviceType", "getPreferredMarketplace", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class Account implements Serializable {
    @Nullable
    private final String accountId;
    @Nullable
    private final String deviceSerialNumber;
    @Nullable
    private final String deviceType;
    @Nullable
    private final String preferredMarketplace;

    public Account(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        this.accountId = str;
        this.preferredMarketplace = str2;
        this.deviceType = str3;
        this.deviceSerialNumber = str4;
    }

    public static /* synthetic */ Account copy$default(Account account, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = account.accountId;
        }
        if ((i & 2) != 0) {
            str2 = account.preferredMarketplace;
        }
        if ((i & 4) != 0) {
            str3 = account.deviceType;
        }
        if ((i & 8) != 0) {
            str4 = account.deviceSerialNumber;
        }
        return account.copy(str, str2, str3, str4);
    }

    @Nullable
    public final String component1() {
        return this.accountId;
    }

    @Nullable
    public final String component2() {
        return this.preferredMarketplace;
    }

    @Nullable
    public final String component3() {
        return this.deviceType;
    }

    @Nullable
    public final String component4() {
        return this.deviceSerialNumber;
    }

    @NotNull
    public final Account copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        return new Account(str, str2, str3, str4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Account)) {
                return false;
            }
            Account account = (Account) obj;
            return Intrinsics.areEqual(this.accountId, account.accountId) && Intrinsics.areEqual(this.preferredMarketplace, account.preferredMarketplace) && Intrinsics.areEqual(this.deviceType, account.deviceType) && Intrinsics.areEqual(this.deviceSerialNumber, account.deviceSerialNumber);
        }
        return true;
    }

    @Nullable
    public final String getAccountId() {
        return this.accountId;
    }

    @Nullable
    public final String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    @Nullable
    public final String getDeviceType() {
        return this.deviceType;
    }

    @Nullable
    public final String getPreferredMarketplace() {
        return this.preferredMarketplace;
    }

    public int hashCode() {
        String str = this.accountId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.preferredMarketplace;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.deviceType;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.deviceSerialNumber;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Account(accountId=");
        outline107.append(this.accountId);
        outline107.append(", preferredMarketplace=");
        outline107.append(this.preferredMarketplace);
        outline107.append(", deviceType=");
        outline107.append(this.deviceType);
        outline107.append(", deviceSerialNumber=");
        return GeneratedOutlineSupport1.outline91(outline107, this.deviceSerialNumber, ")");
    }
}
