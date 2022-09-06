package com.here.sdk.search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import com.here.sdk.core.GeoBox;
import com.here.sdk.core.GeoCircle;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.GeoCorridor;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class CategoryQuery {
    @NonNull
    public final GeoCoordinates areaCenter;
    @Nullable
    public final GeoBox boxArea;
    @NonNull
    public final List<PlaceCategory> categories;
    @Nullable
    public final GeoCircle circleArea;
    @Nullable
    public final GeoCorridor corridorArea;
    @Nullable
    public final String filter;

    public CategoryQuery(@NonNull PlaceCategory placeCategory, @NonNull GeoCoordinates geoCoordinates) {
        CategoryQuery make = make(placeCategory, geoCoordinates);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public CategoryQuery(@NonNull PlaceCategory placeCategory, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoBox geoBox) {
        CategoryQuery make = make(placeCategory, geoCoordinates, geoBox);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public CategoryQuery(@NonNull PlaceCategory placeCategory, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoCircle geoCircle) {
        CategoryQuery make = make(placeCategory, geoCoordinates, geoCircle);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public CategoryQuery(@NonNull PlaceCategory placeCategory, @NonNull GeoCorridor geoCorridor) {
        CategoryQuery make = make(placeCategory, geoCorridor);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public CategoryQuery(@NonNull PlaceCategory placeCategory, @NonNull String str, @NonNull GeoCoordinates geoCoordinates) {
        CategoryQuery make = make(placeCategory, str, geoCoordinates);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public CategoryQuery(@NonNull PlaceCategory placeCategory, @NonNull String str, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoBox geoBox) {
        CategoryQuery make = make(placeCategory, str, geoCoordinates, geoBox);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public CategoryQuery(@NonNull PlaceCategory placeCategory, @NonNull String str, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoCircle geoCircle) {
        CategoryQuery make = make(placeCategory, str, geoCoordinates, geoCircle);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public CategoryQuery(@NonNull PlaceCategory placeCategory, @NonNull String str, @NonNull GeoCorridor geoCorridor) {
        CategoryQuery make = make(placeCategory, str, geoCorridor);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public CategoryQuery(@NonNull List<PlaceCategory> list, @NonNull GeoCoordinates geoCoordinates) {
        CategoryQuery make = make(list, geoCoordinates);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public CategoryQuery(@NonNull List<PlaceCategory> list, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoBox geoBox) {
        CategoryQuery make = make(list, geoCoordinates, geoBox);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public CategoryQuery(@NonNull List<PlaceCategory> list, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoCircle geoCircle) {
        CategoryQuery make = make(list, geoCoordinates, geoCircle);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public CategoryQuery(@NonNull List<PlaceCategory> list, @NonNull GeoCorridor geoCorridor) {
        CategoryQuery make = make(list, geoCorridor);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public CategoryQuery(@NonNull List<PlaceCategory> list, @NonNull String str, @NonNull GeoCoordinates geoCoordinates) {
        CategoryQuery make = make(list, str, geoCoordinates);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public CategoryQuery(@NonNull List<PlaceCategory> list, @NonNull String str, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoBox geoBox) {
        CategoryQuery make = make(list, str, geoCoordinates, geoBox);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public CategoryQuery(@NonNull List<PlaceCategory> list, @NonNull String str, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoCircle geoCircle) {
        CategoryQuery make = make(list, str, geoCoordinates, geoCircle);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    public CategoryQuery(@NonNull List<PlaceCategory> list, @NonNull String str, @NonNull GeoCorridor geoCorridor) {
        CategoryQuery make = make(list, str, geoCorridor);
        this.categories = make.categories;
        this.filter = make.filter;
        this.areaCenter = make.areaCenter;
        this.boxArea = make.boxArea;
        this.circleArea = make.circleArea;
        this.corridorArea = make.corridorArea;
    }

    private static native CategoryQuery make(@NonNull PlaceCategory placeCategory, @NonNull GeoCoordinates geoCoordinates);

    private static native CategoryQuery make(@NonNull PlaceCategory placeCategory, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoBox geoBox);

    private static native CategoryQuery make(@NonNull PlaceCategory placeCategory, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoCircle geoCircle);

    private static native CategoryQuery make(@NonNull PlaceCategory placeCategory, @NonNull GeoCorridor geoCorridor);

    private static native CategoryQuery make(@NonNull PlaceCategory placeCategory, @NonNull String str, @NonNull GeoCoordinates geoCoordinates);

    private static native CategoryQuery make(@NonNull PlaceCategory placeCategory, @NonNull String str, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoBox geoBox);

    private static native CategoryQuery make(@NonNull PlaceCategory placeCategory, @NonNull String str, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoCircle geoCircle);

    private static native CategoryQuery make(@NonNull PlaceCategory placeCategory, @NonNull String str, @NonNull GeoCorridor geoCorridor);

    private static native CategoryQuery make(@NonNull List<PlaceCategory> list, @NonNull GeoCoordinates geoCoordinates);

    private static native CategoryQuery make(@NonNull List<PlaceCategory> list, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoBox geoBox);

    private static native CategoryQuery make(@NonNull List<PlaceCategory> list, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoCircle geoCircle);

    private static native CategoryQuery make(@NonNull List<PlaceCategory> list, @NonNull GeoCorridor geoCorridor);

    private static native CategoryQuery make(@NonNull List<PlaceCategory> list, @NonNull String str, @NonNull GeoCoordinates geoCoordinates);

    private static native CategoryQuery make(@NonNull List<PlaceCategory> list, @NonNull String str, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoBox geoBox);

    private static native CategoryQuery make(@NonNull List<PlaceCategory> list, @NonNull String str, @NonNull GeoCoordinates geoCoordinates, @NonNull GeoCircle geoCircle);

    private static native CategoryQuery make(@NonNull List<PlaceCategory> list, @NonNull String str, @NonNull GeoCorridor geoCorridor);

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CategoryQuery)) {
            return false;
        }
        CategoryQuery categoryQuery = (CategoryQuery) obj;
        return Objects.equals(this.categories, categoryQuery.categories) && Objects.equals(this.filter, categoryQuery.filter) && Objects.equals(this.areaCenter, categoryQuery.areaCenter) && Objects.equals(this.boxArea, categoryQuery.boxArea) && Objects.equals(this.circleArea, categoryQuery.circleArea) && Objects.equals(this.corridorArea, categoryQuery.corridorArea);
    }

    public int hashCode() {
        List<PlaceCategory> list = this.categories;
        int i = 0;
        int hashCode = ((list != null ? list.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        String str = this.filter;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        GeoCoordinates geoCoordinates = this.areaCenter;
        int hashCode3 = (hashCode2 + (geoCoordinates != null ? geoCoordinates.hashCode() : 0)) * 31;
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
