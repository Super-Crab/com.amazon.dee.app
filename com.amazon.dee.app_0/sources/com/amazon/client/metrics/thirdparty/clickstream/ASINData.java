package com.amazon.client.metrics.thirdparty.clickstream;

import com.amazon.client.metrics.thirdparty.DataPoint;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamData;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamHelper;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class ASINData {
    String mGroupingASIN;
    Long mProductGLID;

    @Deprecated
    public ASINData() {
    }

    public List<DataPoint> getDatapoints() {
        ArrayList arrayList = new ArrayList();
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.GROUPING_ASIN.getName(), this.mGroupingASIN);
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.PRODUCT_GLID.getName(), this.mProductGLID.toString());
        return arrayList;
    }

    public String getGroupingASIN() {
        return this.mGroupingASIN;
    }

    public String getProductGLID() {
        return this.mProductGLID.toString();
    }

    public ASINData setGroupingASIN(String str) {
        this.mGroupingASIN = str;
        return this;
    }

    @Deprecated
    public ASINData setProductGLID(String str) {
        try {
            this.mProductGLID = Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException unused) {
        }
        return this;
    }

    protected boolean validate() {
        String str = this.mGroupingASIN;
        return (str == null || str.isEmpty() || this.mProductGLID == null) ? false : true;
    }

    public ASINData(String str, long j) {
        this.mGroupingASIN = str;
        this.mProductGLID = Long.valueOf(j);
        if (validate()) {
            return;
        }
        throw new IllegalArgumentException("Grouping ASIN and Product GLID must not be null or empty");
    }
}
