package com.amazon.alexa.voice.tta.simba;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.reflect.KCallable;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: GetSuggestionsRequestApi.kt */
@Parcelize
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B_\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0004HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010\u0017J\u0011\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010!\u001a\u00020\u0004HÆ\u0003J\t\u0010\"\u001a\u00020\u0004HÆ\u0003J\t\u0010#\u001a\u00020\u0004HÆ\u0003J\t\u0010$\u001a\u00020\u0004HÆ\u0003J\t\u0010%\u001a\u00020\u0004HÆ\u0003J\t\u0010&\u001a\u00020\u0004HÆ\u0003J\t\u0010'\u001a\u00020\u0004HÆ\u0003J|\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÆ\u0001¢\u0006\u0002\u0010)J\t\u0010*\u001a\u00020+HÖ\u0001J\u0013\u0010,\u001a\u00020\u000f2\b\u0010-\u001a\u0004\u0018\u00010.HÖ\u0003J\t\u0010/\u001a\u00020+HÖ\u0001J\u0014\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000401H\u0016J\t\u00102\u001a\u00020\u0004HÖ\u0001J\u0019\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020+HÖ\u0001R\u0014\u0010\n\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0007\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0014\u0010\u000b\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0096\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u000e\u0010\u0017R\u0014\u0010\b\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0014\u0010\t\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0014\u0010\f\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0014\u0010\r\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012¨\u00068"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/SuggestionsSimbaRequest;", "Lcom/amazon/alexa/voice/tta/simba/SimbaBaseRequest;", "Landroid/os/Parcelable;", "queryText", "", "gatedFeatureList", "", "clientRequestId", "locale", WebConstants.WebviewConstants.MARKETPLACE_ID, "appVersion", "dsn", MetricsConfiguration.PLATFORM, "platformVersion", "isTestTraffic", "", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getAppVersion", "()Ljava/lang/String;", "getClientRequestId", "getDsn", "getGatedFeatureList", "()Ljava/util/List;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getLocale", "getMarketplaceId", "getPlatform", "getPlatformVersion", "getQueryText", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/amazon/alexa/voice/tta/simba/SuggestionsSimbaRequest;", "describeContents", "", "equals", "other", "", "hashCode", "toMap", "", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class SuggestionsSimbaRequest implements SimbaBaseRequest, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    @NotNull
    private final String appVersion;
    @NotNull
    private final String clientRequestId;
    @NotNull
    private final String dsn;
    @Nullable
    private final List<String> gatedFeatureList;
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
    private final String queryText;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object createFromParcel(@NotNull Parcel in) {
            Boolean bool;
            Intrinsics.checkParameterIsNotNull(in, "in");
            String readString = in.readString();
            ArrayList<String> createStringArrayList = in.createStringArrayList();
            String readString2 = in.readString();
            String readString3 = in.readString();
            String readString4 = in.readString();
            String readString5 = in.readString();
            String readString6 = in.readString();
            String readString7 = in.readString();
            String readString8 = in.readString();
            if (in.readInt() != 0) {
                bool = Boolean.valueOf(in.readInt() != 0);
            } else {
                bool = null;
            }
            return new SuggestionsSimbaRequest(readString, createStringArrayList, readString2, readString3, readString4, readString5, readString6, readString7, readString8, bool);
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object[] newArray(int i) {
            return new SuggestionsSimbaRequest[i];
        }
    }

    public SuggestionsSimbaRequest(@NotNull String queryText, @Nullable List<String> list, @NotNull String clientRequestId, @NotNull String locale, @NotNull String marketplaceId, @NotNull String appVersion, @NotNull String dsn, @NotNull String platform, @NotNull String platformVersion, @Nullable Boolean bool) {
        Intrinsics.checkParameterIsNotNull(queryText, "queryText");
        Intrinsics.checkParameterIsNotNull(clientRequestId, "clientRequestId");
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Intrinsics.checkParameterIsNotNull(marketplaceId, "marketplaceId");
        Intrinsics.checkParameterIsNotNull(appVersion, "appVersion");
        Intrinsics.checkParameterIsNotNull(dsn, "dsn");
        Intrinsics.checkParameterIsNotNull(platform, "platform");
        Intrinsics.checkParameterIsNotNull(platformVersion, "platformVersion");
        this.queryText = queryText;
        this.gatedFeatureList = list;
        this.clientRequestId = clientRequestId;
        this.locale = locale;
        this.marketplaceId = marketplaceId;
        this.appVersion = appVersion;
        this.dsn = dsn;
        this.platform = platform;
        this.platformVersion = platformVersion;
        this.isTestTraffic = bool;
    }

    @NotNull
    public final String component1() {
        return this.queryText;
    }

    @Nullable
    public final Boolean component10() {
        return isTestTraffic();
    }

    @Nullable
    public final List<String> component2() {
        return this.gatedFeatureList;
    }

    @NotNull
    public final String component3() {
        return getClientRequestId();
    }

    @NotNull
    public final String component4() {
        return getLocale();
    }

    @NotNull
    public final String component5() {
        return getMarketplaceId();
    }

    @NotNull
    public final String component6() {
        return getAppVersion();
    }

    @NotNull
    public final String component7() {
        return getDsn();
    }

    @NotNull
    public final String component8() {
        return getPlatform();
    }

    @NotNull
    public final String component9() {
        return getPlatformVersion();
    }

    @NotNull
    public final SuggestionsSimbaRequest copy(@NotNull String queryText, @Nullable List<String> list, @NotNull String clientRequestId, @NotNull String locale, @NotNull String marketplaceId, @NotNull String appVersion, @NotNull String dsn, @NotNull String platform, @NotNull String platformVersion, @Nullable Boolean bool) {
        Intrinsics.checkParameterIsNotNull(queryText, "queryText");
        Intrinsics.checkParameterIsNotNull(clientRequestId, "clientRequestId");
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Intrinsics.checkParameterIsNotNull(marketplaceId, "marketplaceId");
        Intrinsics.checkParameterIsNotNull(appVersion, "appVersion");
        Intrinsics.checkParameterIsNotNull(dsn, "dsn");
        Intrinsics.checkParameterIsNotNull(platform, "platform");
        Intrinsics.checkParameterIsNotNull(platformVersion, "platformVersion");
        return new SuggestionsSimbaRequest(queryText, list, clientRequestId, locale, marketplaceId, appVersion, dsn, platform, platformVersion, bool);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SuggestionsSimbaRequest)) {
                return false;
            }
            SuggestionsSimbaRequest suggestionsSimbaRequest = (SuggestionsSimbaRequest) obj;
            return Intrinsics.areEqual(this.queryText, suggestionsSimbaRequest.queryText) && Intrinsics.areEqual(this.gatedFeatureList, suggestionsSimbaRequest.gatedFeatureList) && Intrinsics.areEqual(getClientRequestId(), suggestionsSimbaRequest.getClientRequestId()) && Intrinsics.areEqual(getLocale(), suggestionsSimbaRequest.getLocale()) && Intrinsics.areEqual(getMarketplaceId(), suggestionsSimbaRequest.getMarketplaceId()) && Intrinsics.areEqual(getAppVersion(), suggestionsSimbaRequest.getAppVersion()) && Intrinsics.areEqual(getDsn(), suggestionsSimbaRequest.getDsn()) && Intrinsics.areEqual(getPlatform(), suggestionsSimbaRequest.getPlatform()) && Intrinsics.areEqual(getPlatformVersion(), suggestionsSimbaRequest.getPlatformVersion()) && Intrinsics.areEqual(isTestTraffic(), suggestionsSimbaRequest.isTestTraffic());
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

    @Nullable
    public final List<String> getGatedFeatureList() {
        return this.gatedFeatureList;
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
    public final String getQueryText() {
        return this.queryText;
    }

    public int hashCode() {
        String str = this.queryText;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List<String> list = this.gatedFeatureList;
        int hashCode2 = (hashCode + (list != null ? list.hashCode() : 0)) * 31;
        String clientRequestId = getClientRequestId();
        int hashCode3 = (hashCode2 + (clientRequestId != null ? clientRequestId.hashCode() : 0)) * 31;
        String locale = getLocale();
        int hashCode4 = (hashCode3 + (locale != null ? locale.hashCode() : 0)) * 31;
        String marketplaceId = getMarketplaceId();
        int hashCode5 = (hashCode4 + (marketplaceId != null ? marketplaceId.hashCode() : 0)) * 31;
        String appVersion = getAppVersion();
        int hashCode6 = (hashCode5 + (appVersion != null ? appVersion.hashCode() : 0)) * 31;
        String dsn = getDsn();
        int hashCode7 = (hashCode6 + (dsn != null ? dsn.hashCode() : 0)) * 31;
        String platform = getPlatform();
        int hashCode8 = (hashCode7 + (platform != null ? platform.hashCode() : 0)) * 31;
        String platformVersion = getPlatformVersion();
        int hashCode9 = (hashCode8 + (platformVersion != null ? platformVersion.hashCode() : 0)) * 31;
        Boolean isTestTraffic = isTestTraffic();
        if (isTestTraffic != null) {
            i = isTestTraffic.hashCode();
        }
        return hashCode9 + i;
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
        Collection<KCallable<?>> members = Reflection.getOrCreateKotlinClass(SuggestionsSimbaRequest.class).getMembers();
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SuggestionsSimbaRequest(queryText=");
        outline107.append(this.queryText);
        outline107.append(", gatedFeatureList=");
        outline107.append(this.gatedFeatureList);
        outline107.append(", clientRequestId=");
        outline107.append(getClientRequestId());
        outline107.append(", locale=");
        outline107.append(getLocale());
        outline107.append(", marketplaceId=");
        outline107.append(getMarketplaceId());
        outline107.append(", appVersion=");
        outline107.append(getAppVersion());
        outline107.append(", dsn=");
        outline107.append(getDsn());
        outline107.append(", platform=");
        outline107.append(getPlatform());
        outline107.append(", platformVersion=");
        outline107.append(getPlatformVersion());
        outline107.append(", isTestTraffic=");
        outline107.append(isTestTraffic());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel parcel, int i) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.queryText);
        parcel.writeStringList(this.gatedFeatureList);
        parcel.writeString(this.clientRequestId);
        parcel.writeString(this.locale);
        parcel.writeString(this.marketplaceId);
        parcel.writeString(this.appVersion);
        parcel.writeString(this.dsn);
        parcel.writeString(this.platform);
        parcel.writeString(this.platformVersion);
        Boolean bool = this.isTestTraffic;
        if (bool != null) {
            parcel.writeInt(1);
            z = bool.booleanValue();
        } else {
            z = false;
        }
        int i2 = z ? 1 : 0;
        int i3 = z ? 1 : 0;
        parcel.writeInt(i2);
    }
}
