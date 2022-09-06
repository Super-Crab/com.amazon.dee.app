package com.here.sdk.routing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.LocalizedTexts;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
final class Link {
    int baseDurationInSeconds;
    @Nullable
    Double consumptionInKilowattHours;
    @NonNull
    DirectedLink directedLink;
    int durationInSeconds;
    @Nullable
    DynamicSpeedInfo dynamicSpeedInfo;
    int lengthInMeters;
    @NonNull
    List<GeoCoordinates> polyline;
    @NonNull
    LocalizedTexts routeNumbers;
    @Nullable
    Double speedLimitInMetersPerSecond;
    @NonNull
    List<StreetAttributes> streetAttributes;
    @NonNull
    LocalizedTexts streetNames;

    Link(@NonNull DirectedLink directedLink, @NonNull List<GeoCoordinates> list, int i, @Nullable DynamicSpeedInfo dynamicSpeedInfo, @NonNull List<StreetAttributes> list2, int i2, int i3, @NonNull LocalizedTexts localizedTexts, @NonNull LocalizedTexts localizedTexts2, @Nullable Double d, @Nullable Double d2) {
        this.directedLink = directedLink;
        this.polyline = list;
        this.lengthInMeters = i;
        this.dynamicSpeedInfo = dynamicSpeedInfo;
        this.streetAttributes = list2;
        this.durationInSeconds = i2;
        this.baseDurationInSeconds = i3;
        this.streetNames = localizedTexts;
        this.routeNumbers = localizedTexts2;
        this.speedLimitInMetersPerSecond = d;
        this.consumptionInKilowattHours = d2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Link)) {
            return false;
        }
        Link link = (Link) obj;
        return Objects.equals(this.directedLink, link.directedLink) && Objects.equals(this.polyline, link.polyline) && this.lengthInMeters == link.lengthInMeters && Objects.equals(this.dynamicSpeedInfo, link.dynamicSpeedInfo) && Objects.equals(this.streetAttributes, link.streetAttributes) && this.durationInSeconds == link.durationInSeconds && this.baseDurationInSeconds == link.baseDurationInSeconds && Objects.equals(this.streetNames, link.streetNames) && Objects.equals(this.routeNumbers, link.routeNumbers) && Objects.equals(this.speedLimitInMetersPerSecond, link.speedLimitInMetersPerSecond) && Objects.equals(this.consumptionInKilowattHours, link.consumptionInKilowattHours);
    }

    public int hashCode() {
        DirectedLink directedLink = this.directedLink;
        int i = 0;
        int hashCode = ((directedLink != null ? directedLink.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        List<GeoCoordinates> list = this.polyline;
        int hashCode2 = (((hashCode + (list != null ? list.hashCode() : 0)) * 31) + this.lengthInMeters) * 31;
        DynamicSpeedInfo dynamicSpeedInfo = this.dynamicSpeedInfo;
        int hashCode3 = (hashCode2 + (dynamicSpeedInfo != null ? dynamicSpeedInfo.hashCode() : 0)) * 31;
        List<StreetAttributes> list2 = this.streetAttributes;
        int hashCode4 = (((((hashCode3 + (list2 != null ? list2.hashCode() : 0)) * 31) + this.durationInSeconds) * 31) + this.baseDurationInSeconds) * 31;
        LocalizedTexts localizedTexts = this.streetNames;
        int hashCode5 = (hashCode4 + (localizedTexts != null ? localizedTexts.hashCode() : 0)) * 31;
        LocalizedTexts localizedTexts2 = this.routeNumbers;
        int hashCode6 = (hashCode5 + (localizedTexts2 != null ? localizedTexts2.hashCode() : 0)) * 31;
        Double d = this.speedLimitInMetersPerSecond;
        int hashCode7 = (hashCode6 + (d != null ? d.hashCode() : 0)) * 31;
        Double d2 = this.consumptionInKilowattHours;
        if (d2 != null) {
            i = d2.hashCode();
        }
        return hashCode7 + i;
    }
}
