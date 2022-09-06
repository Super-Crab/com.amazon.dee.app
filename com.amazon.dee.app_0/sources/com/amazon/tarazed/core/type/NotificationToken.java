package com.amazon.tarazed.core.type;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NotificationToken.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/amazon/tarazed/core/type/NotificationToken;", "Ljava/io/Serializable;", "accountId", "", "marketplace", "token", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccountId", "()Ljava/lang/String;", "getMarketplace", "getToken", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class NotificationToken implements Serializable {
    @Nullable
    private final String accountId;
    @Nullable
    private final String marketplace;
    @Nullable
    private final String token;

    public NotificationToken(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.accountId = str;
        this.marketplace = str2;
        this.token = str3;
    }

    public static /* synthetic */ NotificationToken copy$default(NotificationToken notificationToken, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = notificationToken.accountId;
        }
        if ((i & 2) != 0) {
            str2 = notificationToken.marketplace;
        }
        if ((i & 4) != 0) {
            str3 = notificationToken.token;
        }
        return notificationToken.copy(str, str2, str3);
    }

    @Nullable
    public final String component1() {
        return this.accountId;
    }

    @Nullable
    public final String component2() {
        return this.marketplace;
    }

    @Nullable
    public final String component3() {
        return this.token;
    }

    @NotNull
    public final NotificationToken copy(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        return new NotificationToken(str, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof NotificationToken)) {
                return false;
            }
            NotificationToken notificationToken = (NotificationToken) obj;
            return Intrinsics.areEqual(this.accountId, notificationToken.accountId) && Intrinsics.areEqual(this.marketplace, notificationToken.marketplace) && Intrinsics.areEqual(this.token, notificationToken.token);
        }
        return true;
    }

    @Nullable
    public final String getAccountId() {
        return this.accountId;
    }

    @Nullable
    public final String getMarketplace() {
        return this.marketplace;
    }

    @Nullable
    public final String getToken() {
        return this.token;
    }

    public int hashCode() {
        String str = this.accountId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.marketplace;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.token;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NotificationToken(accountId=");
        outline107.append(this.accountId);
        outline107.append(", marketplace=");
        outline107.append(this.marketplace);
        outline107.append(", token=");
        return GeneratedOutlineSupport1.outline91(outline107, this.token, ")");
    }
}
