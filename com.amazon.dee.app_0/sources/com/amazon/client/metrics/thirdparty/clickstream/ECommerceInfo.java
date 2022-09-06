package com.amazon.client.metrics.thirdparty.clickstream;

import com.amazon.client.metrics.thirdparty.DataPoint;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamData;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class ECommerceInfo implements ClickStreamInfo {
    boolean mIsGlanceView;
    boolean mIsPrimeEligibleItem;
    Map<String, String> mOrderData;
    String mPageAction;

    public ECommerceInfo(String str, Map<String, String> map) {
        validateString(str);
        validateMap(map);
        this.mPageAction = str;
        this.mOrderData = map;
    }

    private void addInitialDataPoints(List<DataPoint> list) {
        ClickStreamHelper.addDatapoint(list, ClickStreamData.ECOMMERCE_PAGE_ACTION.getName(), this.mPageAction);
        ClickStreamHelper.addDatapoint(list, ClickStreamData.ORDER_DATA.getName(), ClickStreamHelper.mapToString(this.mOrderData));
    }

    private void validateMap(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("Invalid Map. Cannot be null or empty");
        }
    }

    private void validateString(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Invalid string. Cannot be null or empty");
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        ArrayList arrayList = new ArrayList();
        addInitialDataPoints(arrayList);
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.IS_PRIME_ELIGIBLE_ITEM.getName(), Boolean.toString(this.mIsPrimeEligibleItem));
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.IS_GLANCE_VIEW.getName(), Boolean.toString(this.mIsGlanceView));
        return arrayList;
    }

    public ECommerceInfo isGlanceView(Boolean bool) {
        this.mIsGlanceView = bool.booleanValue();
        return this;
    }

    public ECommerceInfo isPrimeEligibleItem(Boolean bool) {
        this.mIsPrimeEligibleItem = bool.booleanValue();
        return this;
    }
}
