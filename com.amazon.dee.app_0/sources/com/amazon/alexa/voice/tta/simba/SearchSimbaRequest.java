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
/* compiled from: GetSearchResultsApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b,\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001By\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u0012J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001aJ\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\tHÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\u009e\u0001\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u00103J\u0013\u00104\u001a\u00020\t2\b\u00105\u001a\u0004\u0018\u000106HÖ\u0003J\t\u00107\u001a\u000208HÖ\u0001J\u0014\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030:H\u0016J\t\u0010;\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\r\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0014\u0010\n\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0014\u0010\u0010\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\tX\u0096\u0004¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0011\u0010\u001aR\u0014\u0010\u000b\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0014\u0010\f\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0014\u0010\u000e\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0014\u0010\u000f\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0014¨\u0006<"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/SearchSimbaRequest;", "Lcom/amazon/alexa/voice/tta/simba/SimbaBaseRequest;", "queryText", "", "promptId", "namespace", "variant", "avsResponseToken", "avsResponseEmpty", "", "clientRequestId", "locale", WebConstants.WebviewConstants.MARKETPLACE_ID, "appVersion", MetricsConfiguration.PLATFORM, "platformVersion", "dsn", "isTestTraffic", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getAppVersion", "()Ljava/lang/String;", "getAvsResponseEmpty", "()Z", "getAvsResponseToken", "getClientRequestId", "getDsn", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getLocale", "getMarketplaceId", "getNamespace", "getPlatform", "getPlatformVersion", "getPromptId", "getQueryText", "getVariant", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/amazon/alexa/voice/tta/simba/SearchSimbaRequest;", "equals", "other", "", "hashCode", "", "toMap", "", "toString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class SearchSimbaRequest implements SimbaBaseRequest {
    @NotNull
    private final String appVersion;
    private final boolean avsResponseEmpty;
    @NotNull
    private final String avsResponseToken;
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
    private final String namespace;
    @NotNull
    private final String platform;
    @NotNull
    private final String platformVersion;
    @NotNull
    private final String promptId;
    @NotNull
    private final String queryText;
    @Nullable
    private final String variant;

    public SearchSimbaRequest(@NotNull String queryText, @NotNull String promptId, @NotNull String namespace, @Nullable String str, @NotNull String avsResponseToken, boolean z, @NotNull String clientRequestId, @NotNull String locale, @NotNull String marketplaceId, @NotNull String appVersion, @NotNull String platform, @NotNull String platformVersion, @NotNull String dsn, @Nullable Boolean bool) {
        Intrinsics.checkParameterIsNotNull(queryText, "queryText");
        Intrinsics.checkParameterIsNotNull(promptId, "promptId");
        Intrinsics.checkParameterIsNotNull(namespace, "namespace");
        Intrinsics.checkParameterIsNotNull(avsResponseToken, "avsResponseToken");
        Intrinsics.checkParameterIsNotNull(clientRequestId, "clientRequestId");
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Intrinsics.checkParameterIsNotNull(marketplaceId, "marketplaceId");
        Intrinsics.checkParameterIsNotNull(appVersion, "appVersion");
        Intrinsics.checkParameterIsNotNull(platform, "platform");
        Intrinsics.checkParameterIsNotNull(platformVersion, "platformVersion");
        Intrinsics.checkParameterIsNotNull(dsn, "dsn");
        this.queryText = queryText;
        this.promptId = promptId;
        this.namespace = namespace;
        this.variant = str;
        this.avsResponseToken = avsResponseToken;
        this.avsResponseEmpty = z;
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
        return this.queryText;
    }

    @NotNull
    public final String component10() {
        return getAppVersion();
    }

    @NotNull
    public final String component11() {
        return getPlatform();
    }

    @NotNull
    public final String component12() {
        return getPlatformVersion();
    }

    @NotNull
    public final String component13() {
        return getDsn();
    }

    @Nullable
    public final Boolean component14() {
        return isTestTraffic();
    }

    @NotNull
    public final String component2() {
        return this.promptId;
    }

    @NotNull
    public final String component3() {
        return this.namespace;
    }

    @Nullable
    public final String component4() {
        return this.variant;
    }

    @NotNull
    public final String component5() {
        return this.avsResponseToken;
    }

    public final boolean component6() {
        return this.avsResponseEmpty;
    }

    @NotNull
    public final String component7() {
        return getClientRequestId();
    }

    @NotNull
    public final String component8() {
        return getLocale();
    }

    @NotNull
    public final String component9() {
        return getMarketplaceId();
    }

    @NotNull
    public final SearchSimbaRequest copy(@NotNull String queryText, @NotNull String promptId, @NotNull String namespace, @Nullable String str, @NotNull String avsResponseToken, boolean z, @NotNull String clientRequestId, @NotNull String locale, @NotNull String marketplaceId, @NotNull String appVersion, @NotNull String platform, @NotNull String platformVersion, @NotNull String dsn, @Nullable Boolean bool) {
        Intrinsics.checkParameterIsNotNull(queryText, "queryText");
        Intrinsics.checkParameterIsNotNull(promptId, "promptId");
        Intrinsics.checkParameterIsNotNull(namespace, "namespace");
        Intrinsics.checkParameterIsNotNull(avsResponseToken, "avsResponseToken");
        Intrinsics.checkParameterIsNotNull(clientRequestId, "clientRequestId");
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Intrinsics.checkParameterIsNotNull(marketplaceId, "marketplaceId");
        Intrinsics.checkParameterIsNotNull(appVersion, "appVersion");
        Intrinsics.checkParameterIsNotNull(platform, "platform");
        Intrinsics.checkParameterIsNotNull(platformVersion, "platformVersion");
        Intrinsics.checkParameterIsNotNull(dsn, "dsn");
        return new SearchSimbaRequest(queryText, promptId, namespace, str, avsResponseToken, z, clientRequestId, locale, marketplaceId, appVersion, platform, platformVersion, dsn, bool);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SearchSimbaRequest)) {
                return false;
            }
            SearchSimbaRequest searchSimbaRequest = (SearchSimbaRequest) obj;
            return Intrinsics.areEqual(this.queryText, searchSimbaRequest.queryText) && Intrinsics.areEqual(this.promptId, searchSimbaRequest.promptId) && Intrinsics.areEqual(this.namespace, searchSimbaRequest.namespace) && Intrinsics.areEqual(this.variant, searchSimbaRequest.variant) && Intrinsics.areEqual(this.avsResponseToken, searchSimbaRequest.avsResponseToken) && this.avsResponseEmpty == searchSimbaRequest.avsResponseEmpty && Intrinsics.areEqual(getClientRequestId(), searchSimbaRequest.getClientRequestId()) && Intrinsics.areEqual(getLocale(), searchSimbaRequest.getLocale()) && Intrinsics.areEqual(getMarketplaceId(), searchSimbaRequest.getMarketplaceId()) && Intrinsics.areEqual(getAppVersion(), searchSimbaRequest.getAppVersion()) && Intrinsics.areEqual(getPlatform(), searchSimbaRequest.getPlatform()) && Intrinsics.areEqual(getPlatformVersion(), searchSimbaRequest.getPlatformVersion()) && Intrinsics.areEqual(getDsn(), searchSimbaRequest.getDsn()) && Intrinsics.areEqual(isTestTraffic(), searchSimbaRequest.isTestTraffic());
        }
        return true;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseRequest
    @NotNull
    public String getAppVersion() {
        return this.appVersion;
    }

    public final boolean getAvsResponseEmpty() {
        return this.avsResponseEmpty;
    }

    @NotNull
    public final String getAvsResponseToken() {
        return this.avsResponseToken;
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

    @NotNull
    public final String getNamespace() {
        return this.namespace;
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
    public final String getPromptId() {
        return this.promptId;
    }

    @NotNull
    public final String getQueryText() {
        return this.queryText;
    }

    @Nullable
    public final String getVariant() {
        return this.variant;
    }

    public int hashCode() {
        String str = this.queryText;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.promptId;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.namespace;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.variant;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.avsResponseToken;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        boolean z = this.avsResponseEmpty;
        if (z) {
            z = true;
        }
        int i2 = z ? 1 : 0;
        int i3 = z ? 1 : 0;
        int i4 = (hashCode5 + i2) * 31;
        String clientRequestId = getClientRequestId();
        int hashCode6 = (i4 + (clientRequestId != null ? clientRequestId.hashCode() : 0)) * 31;
        String locale = getLocale();
        int hashCode7 = (hashCode6 + (locale != null ? locale.hashCode() : 0)) * 31;
        String marketplaceId = getMarketplaceId();
        int hashCode8 = (hashCode7 + (marketplaceId != null ? marketplaceId.hashCode() : 0)) * 31;
        String appVersion = getAppVersion();
        int hashCode9 = (hashCode8 + (appVersion != null ? appVersion.hashCode() : 0)) * 31;
        String platform = getPlatform();
        int hashCode10 = (hashCode9 + (platform != null ? platform.hashCode() : 0)) * 31;
        String platformVersion = getPlatformVersion();
        int hashCode11 = (hashCode10 + (platformVersion != null ? platformVersion.hashCode() : 0)) * 31;
        String dsn = getDsn();
        int hashCode12 = (hashCode11 + (dsn != null ? dsn.hashCode() : 0)) * 31;
        Boolean isTestTraffic = isTestTraffic();
        if (isTestTraffic != null) {
            i = isTestTraffic.hashCode();
        }
        return hashCode12 + i;
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
        Collection<KCallable<?>> members = Reflection.getOrCreateKotlinClass(SearchSimbaRequest.class).getMembers();
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SearchSimbaRequest(queryText=");
        outline107.append(this.queryText);
        outline107.append(", promptId=");
        outline107.append(this.promptId);
        outline107.append(", namespace=");
        outline107.append(this.namespace);
        outline107.append(", variant=");
        outline107.append(this.variant);
        outline107.append(", avsResponseToken=");
        outline107.append(this.avsResponseToken);
        outline107.append(", avsResponseEmpty=");
        outline107.append(this.avsResponseEmpty);
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
