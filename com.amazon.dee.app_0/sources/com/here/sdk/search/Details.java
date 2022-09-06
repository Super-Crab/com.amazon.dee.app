package com.here.sdk.search;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class Details {
    @NonNull
    public final List<PlaceCategory> categories;
    @NonNull
    public final List<Contact> contacts;
    @NonNull
    public final List<WebEditorial> editorials;
    @NonNull
    public final List<WebImage> images;
    @NonNull
    public final List<OpeningHours> openingHours;
    @NonNull
    public final List<WebRating> ratings;
    @NonNull
    public final List<SupplierReference> references;

    public Details(@NonNull List<Contact> list, @NonNull List<OpeningHours> list2, @NonNull List<PlaceCategory> list3, @NonNull List<WebImage> list4, @NonNull List<WebEditorial> list5, @NonNull List<WebRating> list6, @NonNull List<SupplierReference> list7) {
        this.contacts = list;
        this.openingHours = list2;
        this.categories = list3;
        this.images = list4;
        this.editorials = list5;
        this.ratings = list6;
        this.references = list7;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Details)) {
            return false;
        }
        Details details = (Details) obj;
        return Objects.equals(this.contacts, details.contacts) && Objects.equals(this.openingHours, details.openingHours) && Objects.equals(this.categories, details.categories) && Objects.equals(this.images, details.images) && Objects.equals(this.editorials, details.editorials) && Objects.equals(this.ratings, details.ratings) && Objects.equals(this.references, details.references);
    }

    @NonNull
    public native List<PlaceCategory> getPrimaryCategories();

    public int hashCode() {
        List<Contact> list = this.contacts;
        int i = 0;
        int hashCode = ((list != null ? list.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        List<OpeningHours> list2 = this.openingHours;
        int hashCode2 = (hashCode + (list2 != null ? list2.hashCode() : 0)) * 31;
        List<PlaceCategory> list3 = this.categories;
        int hashCode3 = (hashCode2 + (list3 != null ? list3.hashCode() : 0)) * 31;
        List<WebImage> list4 = this.images;
        int hashCode4 = (hashCode3 + (list4 != null ? list4.hashCode() : 0)) * 31;
        List<WebEditorial> list5 = this.editorials;
        int hashCode5 = (hashCode4 + (list5 != null ? list5.hashCode() : 0)) * 31;
        List<WebRating> list6 = this.ratings;
        int hashCode6 = (hashCode5 + (list6 != null ? list6.hashCode() : 0)) * 31;
        List<SupplierReference> list7 = this.references;
        if (list7 != null) {
            i = list7.hashCode();
        }
        return hashCode6 + i;
    }
}
