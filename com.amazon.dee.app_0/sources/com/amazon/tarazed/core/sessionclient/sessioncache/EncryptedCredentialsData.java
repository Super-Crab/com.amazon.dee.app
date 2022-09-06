package com.amazon.tarazed.core.sessionclient.sessioncache;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: EncryptedCredentialsData.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/sessioncache/EncryptedCredentialsData;", "", "encryptedKey", "", "iv", "encryptedCredentials", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEncryptedCredentials", "()Ljava/lang/String;", "getEncryptedKey", "getIv", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class EncryptedCredentialsData {
    @NotNull
    private final String encryptedCredentials;
    @NotNull
    private final String encryptedKey;
    @NotNull
    private final String iv;

    public EncryptedCredentialsData(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        GeneratedOutlineSupport1.outline169(str, "encryptedKey", str2, "iv", str3, "encryptedCredentials");
        this.encryptedKey = str;
        this.iv = str2;
        this.encryptedCredentials = str3;
    }

    public static /* synthetic */ EncryptedCredentialsData copy$default(EncryptedCredentialsData encryptedCredentialsData, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = encryptedCredentialsData.encryptedKey;
        }
        if ((i & 2) != 0) {
            str2 = encryptedCredentialsData.iv;
        }
        if ((i & 4) != 0) {
            str3 = encryptedCredentialsData.encryptedCredentials;
        }
        return encryptedCredentialsData.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.encryptedKey;
    }

    @NotNull
    public final String component2() {
        return this.iv;
    }

    @NotNull
    public final String component3() {
        return this.encryptedCredentials;
    }

    @NotNull
    public final EncryptedCredentialsData copy(@NotNull String encryptedKey, @NotNull String iv, @NotNull String encryptedCredentials) {
        Intrinsics.checkParameterIsNotNull(encryptedKey, "encryptedKey");
        Intrinsics.checkParameterIsNotNull(iv, "iv");
        Intrinsics.checkParameterIsNotNull(encryptedCredentials, "encryptedCredentials");
        return new EncryptedCredentialsData(encryptedKey, iv, encryptedCredentials);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof EncryptedCredentialsData)) {
                return false;
            }
            EncryptedCredentialsData encryptedCredentialsData = (EncryptedCredentialsData) obj;
            return Intrinsics.areEqual(this.encryptedKey, encryptedCredentialsData.encryptedKey) && Intrinsics.areEqual(this.iv, encryptedCredentialsData.iv) && Intrinsics.areEqual(this.encryptedCredentials, encryptedCredentialsData.encryptedCredentials);
        }
        return true;
    }

    @NotNull
    public final String getEncryptedCredentials() {
        return this.encryptedCredentials;
    }

    @NotNull
    public final String getEncryptedKey() {
        return this.encryptedKey;
    }

    @NotNull
    public final String getIv() {
        return this.iv;
    }

    public int hashCode() {
        String str = this.encryptedKey;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.iv;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.encryptedCredentials;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EncryptedCredentialsData(encryptedKey=");
        outline107.append(this.encryptedKey);
        outline107.append(", iv=");
        outline107.append(this.iv);
        outline107.append(", encryptedCredentials=");
        return GeneratedOutlineSupport1.outline91(outline107, this.encryptedCredentials, ")");
    }
}
