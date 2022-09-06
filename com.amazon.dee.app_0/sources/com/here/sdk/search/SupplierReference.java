package com.here.sdk.search;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class SupplierReference {
    @NonNull
    public final String id;
    @NonNull
    public final String supplier;

    public SupplierReference(@NonNull String str, @NonNull String str2) {
        this.supplier = str;
        this.id = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SupplierReference)) {
            return false;
        }
        SupplierReference supplierReference = (SupplierReference) obj;
        return Objects.equals(this.supplier, supplierReference.supplier) && Objects.equals(this.id, supplierReference.id);
    }

    public int hashCode() {
        String str = this.supplier;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        String str2 = this.id;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }
}
