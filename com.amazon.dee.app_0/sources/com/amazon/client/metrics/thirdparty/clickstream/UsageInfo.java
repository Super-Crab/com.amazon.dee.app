package com.amazon.client.metrics.thirdparty.clickstream;

import com.amazon.client.metrics.thirdparty.DataPoint;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamData;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class UsageInfo implements ClickStreamInfo {
    ASINData mASINData;
    long mCustomerID;
    Map<ClickStreamData, String> mDatapoints;
    String mHitType;
    Boolean mIsCustomerHit;
    Boolean mIsPrimeCustomer;
    String mPageAction;
    String mPageAssemblyType;
    String mPageType;
    String mPageTypeID;
    String mSiteVariant;
    String mSubPageType;
    String mTabID;
    String mTeamName;

    public UsageInfo(String str, String str2, String str3, String str4) {
        validateString("Page Type", str);
        validateString("Hit Type", str2);
        validateString("Team Name", str3);
        validateString("Site Variant", str4);
        this.mPageType = str;
        this.mHitType = str2;
        this.mTeamName = str3;
        this.mSiteVariant = str4;
        this.mDatapoints = new HashMap();
    }

    private void addAsinDataPoints(List<DataPoint> list) {
        if (list == null || list.isEmpty() || isASINDataEmpty(this.mASINData)) {
            return;
        }
        String str = this.mPageTypeID;
        if (str != null) {
            str.isEmpty();
        }
        for (DataPoint dataPoint : this.mASINData.getDatapoints()) {
            list.add(dataPoint);
        }
    }

    private void addInitialDataPoints(List<DataPoint> list) {
        ClickStreamHelper.addDatapoint(list, ClickStreamData.PAGE_TYPE.getName(), this.mPageType);
        ClickStreamHelper.addDatapoint(list, ClickStreamData.HIT_TYPE.getName(), this.mHitType);
        ClickStreamHelper.addDatapoint(list, ClickStreamData.TEAM_NAME.getName(), this.mTeamName);
        ClickStreamHelper.addDatapoint(list, ClickStreamData.SITE_VARIANT.getName(), this.mSiteVariant);
    }

    private boolean isASINDataEmpty(ASINData aSINData) {
        if (this.mASINData == null) {
            return true;
        }
        String str = aSINData.mGroupingASIN;
        return (str == null || str.isEmpty()) && aSINData.mProductGLID == null;
    }

    private void validateString(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            throw new IllegalArgumentException(String.format("%s cannot be null or empty", str));
        }
    }

    public ASINData getASINData() {
        return this.mASINData;
    }

    @Override // com.amazon.client.metrics.thirdparty.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        ArrayList arrayList = new ArrayList();
        addInitialDataPoints(arrayList);
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.PAGE_ACTION.getName(), this.mPageAction);
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.SUB_PAGE_TYPE.getName(), this.mSubPageType);
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.PAGE_TYPE_ID.getName(), this.mPageTypeID);
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.TAB_ID.getName(), this.mTabID);
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.PAGE_ASSEMBLY_TYPE.getName(), this.mPageAssemblyType);
        if (this.mIsCustomerHit != null) {
            ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.IS_CUSTOMER_HIT.getName(), this.mIsCustomerHit.toString());
        }
        if (this.mIsPrimeCustomer != null) {
            ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.IS_PRIME_CUSTOMER.getName(), this.mIsPrimeCustomer.toString());
        }
        addAsinDataPoints(arrayList);
        return arrayList;
    }

    public String getHitType() {
        return this.mHitType;
    }

    public String getPageAction() {
        return this.mPageAction;
    }

    public String getPageAssemblyType() {
        return this.mPageAssemblyType;
    }

    public String getPageType() {
        return this.mPageType;
    }

    public String getPageTypeID() {
        return this.mPageTypeID;
    }

    public String getSiteVariant() {
        return this.mSiteVariant;
    }

    public String getSubPageType() {
        return this.mSubPageType;
    }

    public String getTabID() {
        return this.mTabID;
    }

    public String getTeamName() {
        return this.mTeamName;
    }

    public boolean isCustomerHit() {
        return this.mIsCustomerHit.booleanValue();
    }

    public boolean isPrimeCustomer() {
        return this.mIsPrimeCustomer.booleanValue();
    }

    @Deprecated
    public UsageInfo setASINData(ASINData aSINData) {
        this.mASINData = aSINData;
        return this;
    }

    public UsageInfo setIsCustomerHit(boolean z) {
        this.mIsCustomerHit = Boolean.valueOf(z);
        return this;
    }

    public UsageInfo setIsPrimeCustomer(boolean z) {
        this.mIsPrimeCustomer = Boolean.valueOf(z);
        return this;
    }

    public UsageInfo setPageAction(String str) {
        this.mPageAction = str;
        return this;
    }

    public UsageInfo setPageAssemblyType(String str) {
        this.mPageAssemblyType = str;
        return this;
    }

    public UsageInfo setPageTypeID(String str) {
        this.mPageTypeID = str;
        return this;
    }

    public UsageInfo setSubPageType(String str) {
        this.mSubPageType = str;
        return this;
    }

    public UsageInfo setTabID(String str) {
        this.mTabID = str;
        return this;
    }

    public UsageInfo setASINData(String str, Long l) {
        this.mASINData = new ASINData(str, l.longValue());
        return this;
    }
}
