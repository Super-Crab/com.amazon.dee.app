package com.here.sdk.search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import com.here.sdk.core.CountryCode;
import com.here.sdk.core.GeoBox;
import com.here.sdk.core.GeoCircle;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.GeoCorridor;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class TextQuery {
    @Nullable
    public final GeoCoordinates areaCenter;
    @Nullable
    public final GeoBox boxArea;
    @Nullable
    public final GeoCircle circleArea;
    @Nullable
    public final GeoCorridor corridorArea;
    @NonNull
    public final List<CountryCode> countries;
    @NonNull
    public final String query;

    public TextQuery(@NonNull String str, @NonNull GeoBox geoBox) {
        TextQuery make = make(str, geoBox);
        this.query = make.query;
        this.areaCenter = make.areaCenter;
        this.countries = make.countries;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public TextQuery(@NonNull String str, @NonNull GeoCircle geoCircle) {
        TextQuery make = make(str, geoCircle);
        this.query = make.query;
        this.areaCenter = make.areaCenter;
        this.countries = make.countries;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public TextQuery(@NonNull String str, @NonNull GeoCoordinates geoCoordinates) {
        TextQuery make = make(str, geoCoordinates);
        this.query = make.query;
        this.areaCenter = make.areaCenter;
        this.countries = make.countries;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public TextQuery(@NonNull String str, @NonNull GeoCoordinates geoCoordinates, @NonNull List<CountryCode> list) {
        TextQuery make = make(str, geoCoordinates, list);
        this.query = make.query;
        this.areaCenter = make.areaCenter;
        this.countries = make.countries;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public TextQuery(@NonNull String str, @NonNull GeoCorridor geoCorridor, @NonNull GeoCoordinates geoCoordinates) {
        TextQuery make = make(str, geoCorridor, geoCoordinates);
        this.query = make.query;
        this.areaCenter = make.areaCenter;
        this.countries = make.countries;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    private static native TextQuery make(@NonNull String str, @NonNull GeoBox geoBox);

    private static native TextQuery make(@NonNull String str, @NonNull GeoCircle geoCircle);

    private static native TextQuery make(@NonNull String str, @NonNull GeoCoordinates geoCoordinates);

    private static native TextQuery make(@NonNull String str, @NonNull GeoCoordinates geoCoordinates, @NonNull List<CountryCode> list);

    private static native TextQuery make(@NonNull String str, @NonNull GeoCorridor geoCorridor, @NonNull GeoCoordinates geoCoordinates);

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextQuery)) {
            return false;
        }
        TextQuery textQuery = (TextQuery) obj;
        return Objects.equals(this.query, textQuery.query) && Objects.equals(this.areaCenter, textQuery.areaCenter) && Objects.equals(this.countries, textQuery.countries) && Objects.equals(this.boxArea, textQuery.boxArea) && Objects.equals(this.circleArea, textQuery.circleArea) && Objects.equals(this.corridorArea, textQuery.corridorArea);
    }

    public int hashCode() {
        String str = this.query;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        GeoCoordinates geoCoordinates = this.areaCenter;
        int hashCode2 = (hashCode + (geoCoordinates != null ? geoCoordinates.hashCode() : 0)) * 31;
        List<CountryCode> list = this.countries;
        int hashCode3 = (hashCode2 + (list != null ? list.hashCode() : 0)) * 31;
        GeoBox geoBox = this.boxArea;
        int hashCode4 = (hashCode3 + (geoBox != null ? geoBox.hashCode() : 0)) * 31;
        GeoCircle geoCircle = this.circleArea;
        int hashCode5 = (hashCode4 + (geoCircle != null ? geoCircle.hashCode() : 0)) * 31;
        GeoCorridor geoCorridor = this.corridorArea;
        if (geoCorridor != null) {
            i = geoCorridor.hashCode();
        }
        return hashCode5 + i;
    }
}
