package com.dee.app.metrics;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public class MetricComponentName {
    private String categoryId;
    private String method;
    private String routeName;

    /* loaded from: classes2.dex */
    public static class Builder {
        private String categoryId;
        private String method;
        private String routeName;

        public MetricComponentName build() {
            return new MetricComponentName(this.categoryId, this.method, this.routeName);
        }

        public Builder withCategoryId(String str) {
            this.categoryId = str;
            return this;
        }

        public Builder withMethod(String str) {
            this.method = str;
            return this;
        }

        public Builder withRouteName(String str) {
            this.routeName = str;
            return this;
        }
    }

    public boolean equals(Object obj) {
        boolean equals;
        boolean equals2;
        boolean equals3;
        if (obj instanceof MetricComponentName) {
            MetricComponentName metricComponentName = (MetricComponentName) obj;
            String str = this.categoryId;
            if (str == null) {
                equals = metricComponentName.categoryId == null;
            } else {
                equals = str.equals(metricComponentName.categoryId);
            }
            String str2 = this.method;
            if (str2 == null) {
                equals2 = metricComponentName.method == null;
            } else {
                equals2 = str2.equals(metricComponentName.method);
            }
            String str3 = this.routeName;
            String str4 = metricComponentName.routeName;
            if (str3 == null) {
                equals3 = str4 == null;
            } else {
                equals3 = str3.equals(str4);
            }
            return equals && equals2 && equals3;
        }
        return false;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public String getMethod() {
        return this.method;
    }

    public String getRouteName() {
        return this.routeName;
    }

    public int hashCode() {
        String str = this.categoryId;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + 31) * 31;
        String str2 = this.method;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.routeName;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public void setCategoryId(@NonNull String str) {
        this.categoryId = str;
    }

    public void setMethod(@NonNull String str) {
        this.method = str;
    }

    public void setRouteName(@NonNull String str) {
        this.routeName = str;
    }

    public String toString() {
        String[] strArr;
        String str = "";
        String str2 = str;
        for (String str3 : new String[]{this.categoryId, this.method, this.routeName}) {
            if (!TextUtils.isEmpty(str3)) {
                str = GeneratedOutlineSupport1.outline72(GeneratedOutlineSupport1.outline72(str, str2), str3);
                str2 = String.valueOf('.');
            }
        }
        return str;
    }

    private MetricComponentName(String str, String str2, String str3) {
        this.categoryId = str;
        this.method = str2;
        this.routeName = str3;
    }

    public MetricComponentName(@NonNull MetricComponentName metricComponentName) {
        this.categoryId = metricComponentName.getCategoryId();
        this.method = metricComponentName.getMethod();
        this.routeName = metricComponentName.getRouteName();
    }
}
