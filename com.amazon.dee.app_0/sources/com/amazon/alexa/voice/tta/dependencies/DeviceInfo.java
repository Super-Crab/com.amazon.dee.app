package com.amazon.alexa.voice.tta.dependencies;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DeviceInformationProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/voice/tta/dependencies/DeviceInfo;", "", "appVersion", "", MetricsConfiguration.PLATFORM, "platformVersion", "dsn", WebConstants.WebviewConstants.MARKETPLACE_ID, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAppVersion", "()Ljava/lang/String;", "getDsn", "getMarketplaceId", "getPlatform", "getPlatformVersion", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class DeviceInfo {
    @NotNull
    private final String appVersion;
    @NotNull
    private final String dsn;
    @NotNull
    private final String marketplaceId;
    @NotNull
    private final String platform;
    @NotNull
    private final String platformVersion;

    public DeviceInfo(@NotNull String appVersion, @NotNull String platform, @NotNull String platformVersion, @NotNull String dsn, @NotNull String marketplaceId) {
        Intrinsics.checkParameterIsNotNull(appVersion, "appVersion");
        Intrinsics.checkParameterIsNotNull(platform, "platform");
        Intrinsics.checkParameterIsNotNull(platformVersion, "platformVersion");
        Intrinsics.checkParameterIsNotNull(dsn, "dsn");
        Intrinsics.checkParameterIsNotNull(marketplaceId, "marketplaceId");
        this.appVersion = appVersion;
        this.platform = platform;
        this.platformVersion = platformVersion;
        this.dsn = dsn;
        this.marketplaceId = marketplaceId;
    }

    public static /* synthetic */ DeviceInfo copy$default(DeviceInfo deviceInfo, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deviceInfo.appVersion;
        }
        if ((i & 2) != 0) {
            str2 = deviceInfo.platform;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = deviceInfo.platformVersion;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = deviceInfo.dsn;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = deviceInfo.marketplaceId;
        }
        return deviceInfo.copy(str, str6, str7, str8, str5);
    }

    @NotNull
    public final String component1() {
        return this.appVersion;
    }

    @NotNull
    public final String component2() {
        return this.platform;
    }

    @NotNull
    public final String component3() {
        return this.platformVersion;
    }

    @NotNull
    public final String component4() {
        return this.dsn;
    }

    @NotNull
    public final String component5() {
        return this.marketplaceId;
    }

    @NotNull
    public final DeviceInfo copy(@NotNull String appVersion, @NotNull String platform, @NotNull String platformVersion, @NotNull String dsn, @NotNull String marketplaceId) {
        Intrinsics.checkParameterIsNotNull(appVersion, "appVersion");
        Intrinsics.checkParameterIsNotNull(platform, "platform");
        Intrinsics.checkParameterIsNotNull(platformVersion, "platformVersion");
        Intrinsics.checkParameterIsNotNull(dsn, "dsn");
        Intrinsics.checkParameterIsNotNull(marketplaceId, "marketplaceId");
        return new DeviceInfo(appVersion, platform, platformVersion, dsn, marketplaceId);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DeviceInfo)) {
                return false;
            }
            DeviceInfo deviceInfo = (DeviceInfo) obj;
            return Intrinsics.areEqual(this.appVersion, deviceInfo.appVersion) && Intrinsics.areEqual(this.platform, deviceInfo.platform) && Intrinsics.areEqual(this.platformVersion, deviceInfo.platformVersion) && Intrinsics.areEqual(this.dsn, deviceInfo.dsn) && Intrinsics.areEqual(this.marketplaceId, deviceInfo.marketplaceId);
        }
        return true;
    }

    @NotNull
    public final String getAppVersion() {
        return this.appVersion;
    }

    @NotNull
    public final String getDsn() {
        return this.dsn;
    }

    @NotNull
    public final String getMarketplaceId() {
        return this.marketplaceId;
    }

    @NotNull
    public final String getPlatform() {
        return this.platform;
    }

    @NotNull
    public final String getPlatformVersion() {
        return this.platformVersion;
    }

    public int hashCode() {
        String str = this.appVersion;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.platform;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.platformVersion;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.dsn;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.marketplaceId;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return hashCode4 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceInfo(appVersion=");
        outline107.append(this.appVersion);
        outline107.append(", platform=");
        outline107.append(this.platform);
        outline107.append(", platformVersion=");
        outline107.append(this.platformVersion);
        outline107.append(", dsn=");
        outline107.append(this.dsn);
        outline107.append(", marketplaceId=");
        return GeneratedOutlineSupport1.outline91(outline107, this.marketplaceId, ")");
    }
}
