package com.amazon.alexa.voice.tta.simba;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.reflect.KCallable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UpdateInteractionApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0012Jj\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\u00020\f2\b\u0010%\u001a\u0004\u0018\u00010&HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\u0014\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030*H\u0016J\t\u0010+\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0007\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0014\u0010\n\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u000b\u0010\u0012R\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0014\u0010\b\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0014\u0010\t\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006,"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/UpdateInteractionSimbaRequest;", "Lcom/amazon/alexa/voice/tta/simba/SimbaBaseRequest;", "resultId", "", "clientRequestId", "locale", WebConstants.WebviewConstants.MARKETPLACE_ID, "appVersion", MetricsConfiguration.PLATFORM, "platformVersion", "dsn", "isTestTraffic", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getAppVersion", "()Ljava/lang/String;", "getClientRequestId", "getDsn", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getLocale", "getMarketplaceId", "getPlatform", "getPlatformVersion", "getResultId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/amazon/alexa/voice/tta/simba/UpdateInteractionSimbaRequest;", "equals", "other", "", "hashCode", "", "toMap", "", "toString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class UpdateInteractionSimbaRequest implements SimbaBaseRequest {
    @NotNull
    private final String appVersion;
    @NotNull
    private final String clientRequestId;
    @NotNull
    private final String dsn;
    @Nullable
    private final Boolean isTestTraffic;
    @NotNull
    private final String locale;
    @NotNull
    private final String marketplaceId;
    @NotNull
    private final String platform;
    @NotNull
    private final String platformVersion;
    @NotNull
    private final String resultId;

    public UpdateInteractionSimbaRequest(@NotNull String resultId, @NotNull String clientRequestId, @NotNull String locale, @NotNull String marketplaceId, @NotNull String appVersion, @NotNull String platform, @NotNull String platformVersion, @NotNull String dsn, @Nullable Boolean bool) {
        Intrinsics.checkParameterIsNotNull(resultId, "resultId");
        Intrinsics.checkParameterIsNotNull(clientRequestId, "clientRequestId");
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Intrinsics.checkParameterIsNotNull(marketplaceId, "marketplaceId");
        Intrinsics.checkParameterIsNotNull(appVersion, "appVersion");
        Intrinsics.checkParameterIsNotNull(platform, "platform");
        Intrinsics.checkParameterIsNotNull(platformVersion, "platformVersion");
        Intrinsics.checkParameterIsNotNull(dsn, "dsn");
        this.resultId = resultId;
        this.clientRequestId = clientRequestId;
        this.locale = locale;
        this.marketplaceId = marketplaceId;
        this.appVersion = appVersion;
        this.platform = platform;
        this.platformVersion = platformVersion;
        this.dsn = dsn;
        this.isTestTraffic = bool;
    }

    @NotNull
    public final String component1() {
        return this.resultId;
    }

    @NotNull
    public final String component2() {
        return getClientRequestId();
    }

    @NotNull
    public final String component3() {
        return getLocale();
    }

    @NotNull
    public final String component4() {
        return getMarketplaceId();
    }

    @NotNull
    public final String component5() {
        return getAppVersion();
    }

    @NotNull
    public final String component6() {
        return getPlatform();
    }

    @NotNull
    public final String component7() {
        return getPlatformVersion();
    }

    @NotNull
    public final String component8() {
        return getDsn();
    }

    @Nullable
    public final Boolean component9() {
        return isTestTraffic();
    }

    @NotNull
    public final UpdateInteractionSimbaRequest copy(@NotNull String resultId, @NotNull String clientRequestId, @NotNull String locale, @NotNull String marketplaceId, @NotNull String appVersion, @NotNull String platform, @NotNull String platformVersion, @NotNull String dsn, @Nullable Boolean bool) {
        Intrinsics.checkParameterIsNotNull(resultId, "resultId");
        Intrinsics.checkParameterIsNotNull(clientRequestId, "clientRequestId");
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Intrinsics.checkParameterIsNotNull(marketplaceId, "marketplaceId");
        Intrinsics.checkParameterIsNotNull(appVersion, "appVersion");
        Intrinsics.checkParameterIsNotNull(platform, "platform");
        Intrinsics.checkParameterIsNotNull(platformVersion, "platformVersion");
        Intrinsics.checkParameterIsNotNull(dsn, "dsn");
        return new UpdateInteractionSimbaRequest(resultId, clientRequestId, locale, marketplaceId, appVersion, platform, platformVersion, dsn, bool);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof UpdateInteractionSimbaRequest)) {
                return false;
            }
            UpdateInteractionSimbaRequest updateInteractionSimbaRequest = (UpdateInteractionSimbaRequest) obj;
            return Intrinsics.areEqual(this.resultId, updateInteractionSimbaRequest.resultId) && Intrinsics.areEqual(getClientRequestId(), updateInteractionSimbaRequest.getClientRequestId()) && Intrinsics.areEqual(getLocale(), updateInteractionSimbaRequest.getLocale()) && Intrinsics.areEqual(getMarketplaceId(), updateInteractionSimbaRequest.getMarketplaceId()) && Intrinsics.areEqual(getAppVersion(), updateInteractionSimbaRequest.getAppVersion()) && Intrinsics.areEqual(getPlatform(), updateInteractionSimbaRequest.getPlatform()) && Intrinsics.areEqual(getPlatformVersion(), updateInteractionSimbaRequest.getPlatformVersion()) && Intrinsics.areEqual(getDsn(), updateInteractionSimbaRequest.getDsn()) && Intrinsics.areEqual(isTestTraffic(), updateInteractionSimbaRequest.isTestTraffic());
        }
        return true;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseRequest
    @NotNull
    public String getAppVersion() {
        return this.appVersion;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseRequest
    @NotNull
    public String getClientRequestId() {
        return this.clientRequestId;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseRequest
    @NotNull
    public String getDsn() {
        return this.dsn;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseRequest
    @NotNull
    public String getLocale() {
        return this.locale;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseRequest
    @NotNull
    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseRequest
    @NotNull
    public String getPlatform() {
        return this.platform;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseRequest
    @NotNull
    public String getPlatformVersion() {
        return this.platformVersion;
    }

    @NotNull
    public final String getResultId() {
        return this.resultId;
    }

    public int hashCode() {
        String str = this.resultId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String clientRequestId = getClientRequestId();
        int hashCode2 = (hashCode + (clientRequestId != null ? clientRequestId.hashCode() : 0)) * 31;
        String locale = getLocale();
        int hashCode3 = (hashCode2 + (locale != null ? locale.hashCode() : 0)) * 31;
        String marketplaceId = getMarketplaceId();
        int hashCode4 = (hashCode3 + (marketplaceId != null ? marketplaceId.hashCode() : 0)) * 31;
        String appVersion = getAppVersion();
        int hashCode5 = (hashCode4 + (appVersion != null ? appVersion.hashCode() : 0)) * 31;
        String platform = getPlatform();
        int hashCode6 = (hashCode5 + (platform != null ? platform.hashCode() : 0)) * 31;
        String platformVersion = getPlatformVersion();
        int hashCode7 = (hashCode6 + (platformVersion != null ? platformVersion.hashCode() : 0)) * 31;
        String dsn = getDsn();
        int hashCode8 = (hashCode7 + (dsn != null ? dsn.hashCode() : 0)) * 31;
        Boolean isTestTraffic = isTestTraffic();
        if (isTestTraffic != null) {
            i = isTestTraffic.hashCode();
        }
        return hashCode8 + i;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseRequest
    @Nullable
    public Boolean isTestTraffic() {
        return this.isTestTraffic;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseRequest
    @NotNull
    public Map<String, String> toMap() {
        int collectionSizeOrDefault;
        int mapCapacity;
        int coerceAtLeast;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Collection<KCallable<?>> members = Reflection.getOrCreateKotlinClass(UpdateInteractionSimbaRequest.class).getMembers();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(members, 10);
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(collectionSizeOrDefault);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(coerceAtLeast);
        for (Object obj : members) {
            linkedHashMap2.put(((KCallable) obj).getName(), obj);
        }
        for (Object obj2 : linkedHashMap2.keySet()) {
            linkedHashMap.put(obj2, String.valueOf(linkedHashMap2.get((String) obj2)));
        }
        return linkedHashMap;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UpdateInteractionSimbaRequest(resultId=");
        outline107.append(this.resultId);
        outline107.append(", clientRequestId=");
        outline107.append(getClientRequestId());
        outline107.append(", locale=");
        outline107.append(getLocale());
        outline107.append(", marketplaceId=");
        outline107.append(getMarketplaceId());
        outline107.append(", appVersion=");
        outline107.append(getAppVersion());
        outline107.append(", platform=");
        outline107.append(getPlatform());
        outline107.append(", platformVersion=");
        outline107.append(getPlatformVersion());
        outline107.append(", dsn=");
        outline107.append(getDsn());
        outline107.append(", isTestTraffic=");
        outline107.append(isTestTraffic());
        outline107.append(")");
        return outline107.toString();
    }
}
